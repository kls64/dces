package com.hust.dces.Controller;

import com.hust.dces.Entity.Document;
import com.hust.dces.Entity.User;
import com.hust.dces.Service.DocumentService;
import com.hust.dces.Utils.FileUtil;
import com.hust.dces.Utils.JavaRunPython;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/upload")
    public String fileUpload(HttpServletRequest request, @RequestParam("myFile") MultipartFile file) throws IOException {
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
        // 这里将上传的文档信息保留在session中，便于后续操作
        HttpSession session = request.getSession();
        session.setAttribute("currentDocument", document);
        // 这里docid传不进session中，因为刚把一个新的 document 写进数据库中，还没有生成 docid。
        // 这里可以不将 docid 放进 Session 中
        // JavaRunPython.textGraph();
        Integer newDocId = document.getDocid();
        return "redirect:/document/analyze/" + newDocId;
    }

    @GetMapping("/analyze/{docid}") // 这里可以写@PostMapping("/submitfile")?
    public String fileAnalyze(@PathVariable("docid") Integer docid) {
        // 这里将来要写具体的分析业务，根据传进来的 docid 来查找具体的分析结果
        JavaRunPython.textGraph();
        System.out.println(docid);
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

        return "graph_show"; // graph_show.html
    }

}
