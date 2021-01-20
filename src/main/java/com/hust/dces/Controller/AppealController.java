package com.hust.dces.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("appeal")
public class AppealController {
    @GetMapping("/sseresolv")
    public String adminsresolv(){

        return "sseresolv"; // sseresolv.html
    }
}
