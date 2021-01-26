package com.hust.dces.Controller;

import com.hust.dces.Entity.Document;
import com.hust.dces.Entity.User;
import com.hust.dces.Service.DocumentService;
import com.hust.dces.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DocumentService documentService;

    // http://localhost:8080/user/login
    @GetMapping("/login")
    public String userlogin(){

        return "login";   // login.html

    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model, User u){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        u = userService.loginuser(username, password);
        if(u != null ){
            if (u.getTypeid() == 2){
//                model.addAttribute("user",u);
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", u);//应该把登录信息储存在内置对象session中以便多次访问
                return "redirect:/user/index";
            }
            else {
//                model.addAttribute("user",u);
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", u);//应该把登录信息储存在内置对象session中以便多次访问
                return "redirect:/user/indexadmin";
            }
        }
        return "redirect:/user/loginfailed";
    }

    @GetMapping("/register")
    public String userregister(){

        return "register";  // register.html
    }

    @PostMapping("/register")
    public String addUserInfo(User user){
        userService.addUserInfo(user);
        return "redirect:/user/login";
    }

    @GetMapping("/forgot-password")
    public String userfp(){

        return "forgot-password"; // forgot-password.html

    }

    @GetMapping("/index")
    public String userindex(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("currentUser");
        Integer userId = user.getUserid();
        List<Document> documents = documentService.findDocumentByUserId(userId);
        model.addAttribute("documents",documents);
        return "index"; // index.html
    }

    @GetMapping("/indexadmin")
    public String adminindex(){

        return "indexadmin"; // indexadmin.html
    }


    @GetMapping("/personalinfo")
    public String userinfo(HttpServletRequest request, Model model){
        User user = (User) request.getSession().getAttribute("currentUser");
        Integer userID = user.getUserid();
        List<User> userList = userService.checkUserByUserID(userID);
        model.addAttribute("userList",userList);
        return "personalinfo"; // personalinfo.html
    }

    @GetMapping("/review")
    public String userreview(){

        return "review"; // review.html
    }

    @GetMapping("/ssemanage")
    public String adminsmanage(){

        return "ssemanage"; // ssemanage.html
    }



    @GetMapping("/update/{id}")
    public String userupdate(@PathVariable("id") Integer userid, Model model){
        User user = userService.findUserByUserID(userid);
        model.addAttribute("user",user);
        return "update"; // update.html
    }

    @PostMapping("/update")
    public String userupdate(User user){
        userService.updateUserByID(user);
        return "redirect:/user/login";
    }

    @GetMapping("/loginfailed")
    public String userlf(){

        return "loginfailed"; // loginfailed.html
    }


}
