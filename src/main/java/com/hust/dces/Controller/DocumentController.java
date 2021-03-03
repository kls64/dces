package com.hust.dces.Controller;

import com.hust.dces.Entity.Document;
import com.hust.dces.Entity.User;
import com.hust.dces.Service.DocumentService;
import com.hust.dces.Service.SensitiveWordService;
import com.hust.dces.Utils.BadWordUtil2;
import com.hust.dces.Utils.FileUtil;
import com.hust.dces.Utils.JavaRunPython;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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

    @PostMapping("/upload")
    public String fileUpload(HttpServletRequest request, @RequestParam("myFile") MultipartFile file,Model model) throws IOException {
        //提交文件保存到后台并分析
        // 1.保存文件到硬盘上 利用工具类
        String fileName = file.getOriginalFilename();
        String fileType = request.getParameter("fileType");
        String filePath = FileUtil.getUpLoadFilePath();
        Document document = new Document();
        document.setDocname(fileName);
        fileName = System.currentTimeMillis() + fileName;
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 2.保存文件名称到数据里
        document.setDocpath(filePath + File.separator + fileName);
        document.setDoctype(fileType);
        //暂时给一个虚假的类别
        Date date = new Date();//获取一个java.util包下的Date对象
        Timestamp time = new Timestamp(date.getTime());//然后将时间转换成数据库类型的datetime类型
        document.setUploadtime(time);
        User user = (User) request.getSession().getAttribute("currentUser");
        document.setUserid(user.getUserid());
        documentService.addDoc(document); // 将文档信息保存到数据库中

        Document document1 = documentService.findDocumentByDocName(document.getDocname());
        Integer docId = document1.getDocid();

        // 这里将上传的文档信息保留在session中，便于后续操作
        HttpSession session = request.getSession();
        session.setAttribute("currentDocument", document);
        // 这里docid传不进session中，因为刚把一个新的 document 写进数据库中，还没有生成 docid。
        // 这里可以不将 docid 放进 Session 中

        return "redirect:/document/analyze/" + docId;
//        return "analyze"; // analyze.html
    }

    @GetMapping("/analyze/{docid}") // 这里可以写@PostMapping("/submitfile")?
    public String fileAnalyze(@PathVariable("docid") Integer docid, Model model) {
        // 这里将来要写具体的分析业务，根据传进来的 docid 来查找具体的分析结果
        JavaRunPython.textGraph(docid);
        System.out.println(docid);
        //这里查找数据库里的所有敏感词并且生成为txt文件
        List<String> sensitiveWords = sensitiveWordService.findAllSensitiveWordsName();
        FileUtil.storeAsTxT("sens_words.txt",sensitiveWords);
        //敏感词txt文件生成
        //开始读取文本
        Document document = documentService.findDocumentByDocId(docid);
        String docPath = document.getDocpath();
        String txt = FileUtil.readTxt(docPath);
        //读取完毕
        //敏感词检测
        Set<String> s = BadWordUtil2.words;
        Map<String,String> map = BadWordUtil2.wordMap;
        Set<String> set = BadWordUtil2.getBadWord(txt, 2);
        System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
        model.addAttribute("sens_words",set);
        model.addAttribute("appealDocId",docid);
        //
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

    @GetMapping("/graph_show")
    public String fileAnalyze(){
        System.out.println("--------------2222-----------------------");
//        return "redirect:/document/graph_show";
        return "graph_show"; // graph_show.html
    }

}
