package com.hust.dces.Controller;

import com.hust.dces.Entity.User;
import com.hust.dces.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    // http://localhost:8080/user/login
    @GetMapping("/login")
    public String userlogin(){

        return "login";   // login.html

    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, ModelAndView mv, User u){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        u = userService.loginuser(username, password);
        if(u != null ){
            if (u.getTypeid() == 2){
                return "redirect:/user/index";
            }
            else {
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
    public String userindex(){

        return "index"; // index.html
    }

    @GetMapping("/indexadmin")
    public String adminindex(){

        return "indexadmin"; // indexadmin.html
    }


    @GetMapping("/personalinfo")
    public String userinfo(){

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



    @GetMapping("/update")
    public String userupdate(){

        return "update"; // update.html
    }

    @GetMapping("/loginfailed")
    public String userlf(){

        return "loginfailed"; // loginfailed.html
    }


}
