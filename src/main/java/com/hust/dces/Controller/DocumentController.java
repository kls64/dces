package com.hust.dces.Controller;

import com.hust.dces.Entity.Document;
import com.hust.dces.Entity.User;
import com.hust.dces.Service.DocumentService;
import com.hust.dces.Utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;
    @PostMapping("/analyze")
    public String fileAnalyze(HttpServletRequest request, Document document, @RequestParam("myFile") MultipartFile file){//提交文件保存到后台并分析
        //1.保存文件到硬盘上 利用工具类
        String fileName= file.getOriginalFilename();
        String filePath= FileUtil.getUpLoadFilePath();
        document.setDocname(fileName);
        fileName = System.currentTimeMillis()+fileName;
        try {
            FileUtil.uploadFile(file.getBytes(),filePath,fileName);
        }catch (Exception e){
            e.printStackTrace();
        }
        //2.保存文件名称到数据里
        document.setDocpath(fileName);
        document.setDoctype("1");
        //暂时给一个虚假的类别
        Date date=new Date();//获取一个java.util包下的Date对象
        Timestamp time=new Timestamp(date.getTime());//然后将时间转换成数据库类型的datetime类型
        document.setUploadtime(time);
        User user= (User) request.getSession().getAttribute("currentUser");
        document.setUserid(user.getUserid());
        documentService.addDoc(document);
        return "analyze"; // analyze.html
    }

    @GetMapping("/appeal")
    public String userappeal(){

        return "appeal"; // appeal.html
    }

    @GetMapping("/submitfile")
    public String usersf(){

        return "submitfile"; // submitfile.html
    }
}
