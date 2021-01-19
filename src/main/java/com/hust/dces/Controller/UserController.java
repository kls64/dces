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
}
