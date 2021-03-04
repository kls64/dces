package com.hust.dces.Controller;

import com.hust.dces.Entity.Document;
import com.hust.dces.Entity.Keywords;
import com.hust.dces.Entity.User;
import com.hust.dces.Service.DocumentService;
import com.hust.dces.Service.KeywordsService;
import com.hust.dces.Service.SensitiveWordService;
import com.hust.dces.Utils.BadWordUtil2;
import com.hust.dces.Utils.FileUtil;
import com.hust.dces.Utils.JavaRunPython;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private SensitiveWordService sensitiveWordService;

    @Autowired
    private KeywordsService keywordsService;

    @PostMapping("/upload")
    public String fileUpload(HttpServletRequest request, @RequestParam("myFile") MultipartFile file,Model model) throws IOException {
        // 获取前端传过来的文档信息
        String fileName = file.getOriginalFilename();
        String fileType = request.getParameter("fileType");
        User user = (User) request.getSession().getAttribute("currentUser");

        String newFileDir = user.getUserid() + "_" + fileName + "_" + System.currentTimeMillis();
        String filePath = FileUtil.getUpLoadFilePath() + File.separator + newFileDir; // 存储文件的父目录绝对路径

        Document document = new Document();
        document.setUserid(user.getUserid());
        document.setDocname(fileName);
        String fileAbsolutePath = filePath + File.separator + fileName;
        document.setDocpath(fileAbsolutePath);
        document.setDoctype(fileType);
        Date date = new Date();//获取一个java.util包下的Date对象
        Timestamp time = new Timestamp(date.getTime());//然后将时间转换成数据库类型的datetime类型
        document.setUploadtime(time);
        documentService.addDoc(document); // 将文档信息保存到数据库中

        // 先保存数据库，再将文件保存到磁盘上
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName); // 以绝对路径存入
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获取新提交的文档 id
        Integer newDocId = documentService.findDocIdByDocpath(fileAbsolutePath);

        // 调用 python 程序处理文档
        String pyIntepreter = "python";
        String command = "C:\\Users\\kls\\Desktop\\textprocessing\\main.py";
        String[] cmdArr = new String[] {pyIntepreter, command, fileAbsolutePath, filePath};
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmdArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), "gb2312"));
            String line = null;
            while ((line = in.readLine()) != null) {
                // System.out.println(line);
                if(line.equals("文档前10大关键词: ")){
                    break;
                }
            }
            Keywords keywords = new Keywords();
            keywords.setDocid(newDocId);
            String wordSequence = "", countSequence = "";
            // 接收处理关键词
            while((line = in.readLine()) != null) {
                System.out.println(line);
                String[] keyWordsInfo =  line.split("/");
                wordSequence += "," + keyWordsInfo[0];
                countSequence += "," + keyWordsInfo[2];
            }
            System.out.println(wordSequence.length());
            System.out.println(countSequence.length());
            wordSequence = wordSequence.substring(1);
            countSequence = countSequence.substring(1);
            keywords.setWord(wordSequence);
            keywords.setWordcount(countSequence);
            System.out.format("%d\t%s\t%s", keywords.getDocid(), keywords.getWord(), keywords.getWordcount());
            keywordsService.addKeywords(keywords); // 写入到数据库

            in.close();
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
//
//        Document document1 = documentService.findDocumentByDocName(document.getDocname());
//        Integer newDocId = document1.getDocid();

        return "redirect:/document/analyze/" + newDocId;
//        return "analyze"; // analyze.html
    }

    @GetMapping("/analyze/{docid}") // 这里可以写@PostMapping("/submitfile")?
    public String fileAnalyze(@PathVariable("docid") Integer docid, Model model) {
//        // 这里将来要写具体的分析业务，根据传进来的 docid 来查找具体的分析结果

        Document document = documentService.findDocumentByDocId(docid);
        String docPath = document.getDocpath();
        File file = new File(docPath);
        String docParentPath = file .getParent();
        String txtPath = docParentPath + File.separator + "txts";
        File dir = new File(txtPath);
        String[] names = dir.list();
        String txtAbsPath = txtPath;
        for(String name : names){
            txtAbsPath += (File.separator + name);
        }

        JavaRunPython.textGraph(txtAbsPath,Integer.toString(docid));
        //这里查找数据库里的所有敏感词并且生成为txt文件
        List<String> sensitiveWords = sensitiveWordService.findAllSensitiveWordsName();
        FileUtil.storeAsTxT("sens_words.txt",sensitiveWords);

        //敏感词txt文件生成
        //开始读取文本
        String txt = FileUtil.readTxt(txtAbsPath);
        //读取完毕
        //敏感词检测
        Set<String> s = BadWordUtil2.words;
        Map<String,String> map = BadWordUtil2.wordMap;
        Set<String> set = BadWordUtil2.getBadWord(txt, 2);
        if (set.size() > 0) {
            documentService.updateStatusById(docid,true);
        }
        System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);

        model.addAttribute("sens_words",set);
        model.addAttribute("appealDocId",docid);
        //

        // 读取数据库中的分析结果，整合生成的结果返回到页面中。
        Keywords docKeywords = keywordsService.findKeywordsByDocid(docid);
        model.addAttribute("keywords",docKeywords.getWord());
        model.addAttribute("keywordsWeight",docKeywords.getWordcount());

        return "analyze"; // analyze.html
    }

    @GetMapping("/submitfile")
    public String userSubmitFile() {

        return "submitfile"; // submitfile.html
    }

    @GetMapping("/deleteFile/{docid}")
    public String userDeleteFile(@PathVariable("docid") Integer docid) {
        Document delDoc = documentService.findDocumentByDocId(docid);
        FileUtil.deleteFile(delDoc.getDocpath());
        documentService.deleteDocumentByDocid(docid);
        return "redirect:/user/index";
    }

    @PostMapping("/graph_show")
    public String fileAnalyze(@Param("docId") String docId){
        System.out.println("--------------2222-----------------------");
//        return "redirect:/document/graph_show";
        return "graph_show"+docId; // graph_show.html
    }
    @GetMapping("/graph_show/{docId}")
    public String fileAnalyze2(@PathVariable("docId") String docId){
//        return "redirect:/document/graph_show";
        return "graph_show"+docId; // graph_show.html
    }

}
