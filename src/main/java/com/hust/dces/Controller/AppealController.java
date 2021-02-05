package com.hust.dces.Controller;

import com.hust.dces.Entity.Appealdoc;
import com.hust.dces.Entity.Document;
import com.hust.dces.Entity.User;
import com.hust.dces.Service.AppealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
        // 获取当前用户
        User user = (User) request.getSession().getAttribute("currentUser");
        // 获取当前文档，但docid无法显示
        Document document = (Document) request.getSession().getAttribute("currentDocument");
        // Document document = appealService.discoverDocumentByUserId(user.getUserid());
        // userid和文档是一对多的关系，这里返回了不止一个文档
        appealdoc.setUserid(user.getUserid()); // 设置userid
        appealdoc.setDocname(document.getDocname()); // 设置docname
        Date date = new Date();//获取一个java.util包下的Date对象
        Timestamp time = new Timestamp(date.getTime());//然后将时间转换成数据库类型的datetime类型
        appealdoc.setAppealtime(time);
        appealService.addAppeal(appealdoc);
        // 保存appeal文档到session中
        // HttpSession session = request.getSession();
        // session.setAttribute("currentAppeal",appealdoc);
        return "redirect:/user/index";
    }

    @GetMapping("/sseresolv")
    public String adminsresolv(Model model){
        List<Appealdoc> aplLists = appealService.findAllAppeal();
        model.addAttribute("apls",aplLists);
        return "sseresolv"; // sseresolv.html
    }

    @GetMapping("/appealresolv/{id}")
    public String adminaresolv(@PathVariable("id") Integer appealid,Model model){
        Appealdoc appealdoc = appealService.findAppealByID(appealid);
        model.addAttribute("apl",appealdoc);
        return "appealresolv"; // appealresolv.html
    }

    @PostMapping("/appealresolv")
    public String adminaresolv(Appealdoc appealdoc){
        appealdoc.setStatus(true);
        Date date = new Date();//获取一个java.util包下的Date对象
        Timestamp time = new Timestamp(date.getTime());//然后将时间转换成数据库类型的datetime类型
        appealdoc.setFeedbacktime(time);
        appealService.updateAppealByID(appealdoc);
        return "redirect:/apl/sseresolv";

    }

    @GetMapping("/review")
    public String userreview(HttpServletRequest request, Model model){
        User user = (User) request.getSession().getAttribute("currentUser");
        Integer userID = user.getUserid();
        List<Appealdoc> appealList = appealService.findAppealByUserId(userID);
        model.addAttribute("apllist",appealList);
        return "review"; // review.html
    }


   /* @GetMapping("/sseresolv")
    public String adminsresolv(HttpServletRequest request, Model model){
        // 获取当前文档
        // Document document = (Document) request.getSession().getAttribute("currentDocument");
        // 获取当前申诉
        Appealdoc appealdoc = (Appealdoc) request.getSession().getAttribute("currentAppeal");
        // 根据docname寻找申诉
        Appealdoc appeal = appealService.findAppealByDocname(appealdoc.getDocname());
        model.addAttribute("appeal",appeal);
        return "sseresolv"; // sseresolv.html
    }

    @PostMapping("/sseresolv")
    public String adminsresolv(Appealdoc appeal){
        // 获取当前文档
        // Document document = (Document) request.getSession().getAttribute("currentDocument");
        // 根据docname寻找申诉
        // Appealdoc appealdoc = appealService.findAppealByDocname(document.getDocname());
        // model.addAttribute("appealdoc",appealdoc);
        // 判断申诉处理状态，如果未处理，进行处理
        if (!appeal.isStatus()){
            appeal.setStatus(true);
            Date date = new Date();//获取一个java.util包下的Date对象
            Timestamp time = new Timestamp(date.getTime());//然后将时间转换成数据库类型的datetime类型
            appeal.setFeedbacktime(time);
            appealService.updateAppealByID(appeal);
            return "redirect:/user/indexadmin";
        }
        else {
            // 如果已处理，则返回无申诉页面
            return "redirect:/apl/noappeal";
        }
    }

    @GetMapping("/noappeal")
    public String adminnoappeal(){

        return "noappeal"; // noappeal.html
    }*/


}
