package com.hust.dces.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    // http://localhost:8080/user/login
    @GetMapping("/login")
    public String userlogin(){

        return "login";   // login.html

    }

    @GetMapping("/register")
    public String userregister(){

        return "register";  // register.html
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

    @GetMapping("/analyze")
    public String fileanalyze(){

        return "analyze"; // analyze.html
    }

    @GetMapping("/appeal")
    public String userappeal(){

        return "appeal"; // appeal.html
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

    @GetMapping("/sseresolv")
    public String adminsresolv(){

        return "sseresolv"; // sseresolv.html
    }

    @GetMapping("/submitfile")
    public String usersf(){

        return "submitfile"; // submitfile.html
    }

    @GetMapping("/update")
    public String userupdate(){

        return "update"; // update.html
    }


}
