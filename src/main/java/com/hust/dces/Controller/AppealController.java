package com.hust.dces.Controller;

import com.hust.dces.Entity.Appealdoc;
import com.hust.dces.Entity.Document;
import com.hust.dces.Entity.User;
import com.hust.dces.Service.AppealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("apl")
public class AppealController {

    @Autowired
    private AppealService appealService;

    @GetMapping("/appeal")
    public String userappeal(){

        return "appeal"; // appeal.html
    }

    @PostMapping("/appeal")
    public String userappeal(HttpServletRequest request, Appealdoc appealdoc){
        User user = (User) request.getSession().getAttribute("currentUser"); // 获取当前用户
        Document document = (Document) request.getSession().getAttribute("currentDocument"); // 获取当前文档，但docid无法显示
        // Document document = appealService.discoverDocumentByUserId(user.getUserid());
        // userid和文档是一对多的关系，这里返回了不止一个文档
        appealdoc.setUserid(user.getUserid()); // 设置userid
        appealdoc.setDocname(document.getDocname()); // 设置docname
        Date date=new Date();//获取一个java.util包下的Date对象
        Timestamp time=new Timestamp(date.getTime());//然后将时间转换成数据库类型的datetime类型
        appealdoc.setAppealtime(time);
        appealService.addAppeal(appealdoc);
        return "redirect:/user/index";
    }

    @GetMapping("/sseresolv")
    public String adminsresolv(){

        return "sseresolv"; // sseresolv.html
    }
}
