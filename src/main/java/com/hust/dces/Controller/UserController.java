package com.hust.dces.Controller;

import com.hust.dces.Entity.Document;
import com.hust.dces.Entity.User;
import com.hust.dces.Service.DocumentService;
import com.hust.dces.Service.UserService;
import com.hust.dces.Utils.EmailRegisterValidator;
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

    EmailRegisterValidator erv = null;

    // http://localhost:8080/user/login
    @GetMapping("/login")
    public String userlogin() {

        return "login";   // login.html

    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model, User frontUser) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User dbUser = userService.loginuser(username, password);
        // 首先判断数据库中有此用户，接着判断数据库查询到的用户类型与前端提交过来的类型是否一致
        if (null != dbUser && dbUser.getTypeid() == frontUser.getTypeid()) {
            if (dbUser.getTypeid() == 1) {
//                model.addAttribute("user",u);
                HttpSession session = request.getSession();
                // 注意此处放的 dbUser 是包含数据库的 userid 的；下同
                session.setAttribute("currentUser", dbUser); //应该把登录信息储存在内置对象session中以便多次访问
                return "redirect:/user/index";
            } else {
//                model.addAttribute("user",u);
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", dbUser); //应该把登录信息储存在内置对象session中以便多次访问
                return "redirect:/user/indexadmin";
            }
        }
        return "redirect:/user/login?LoginFailed=yes";
    }

    @GetMapping("/register")
    public String userRegister() {

        return "register";  // register.html
    }

    // 用户发送验证码
    @PostMapping("/getVC")
    public String getVerificationCode(HttpServletRequest request) // 必须得有返回值
    {
        String userEmail = request.getParameter("userEmail");
        erv = new EmailRegisterValidator(6, 60);
        try {
            System.out.println(userEmail);
            erv.sendVerificationCodeToEmail(userEmail);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return "register";
    }

    @PostMapping("/register")
    public String addUserInfo(HttpServletRequest request, User user) {
        String userVC = request.getParameter("userVerifyCode");
        if (erv.isCorrectAndNotExpired(userVC)) // 验证码正确且成功
        {
            userService.addUserInfo(user);
            return "redirect:/user/login?RegisterSuccess=yes";
        } else // 验证码错误或过期
        {
            erv = null;
            return "redirect:/user/register?VCError=yes";
        }

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
        model.addAttribute("documents", documents);
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
