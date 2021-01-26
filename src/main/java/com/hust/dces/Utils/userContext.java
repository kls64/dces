package com.hust.dces.Utils;

import com.hust.dces.Entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/*
 * 保存和获取当前用户的工具类
 */
public class userContext {
    private static final String CURRENT_USER_IN_SESSION = "user";

    // 得到session
    private static HttpSession getSession(){

        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
    }

    // 设置当前用户到session中
    public static void putCurrentUser(User currentUser){
        getSession().setAttribute(CURRENT_USER_IN_SESSION, currentUser);
    }

    // 获取当前用户
    public static User getCurrentUser(){
        return (User) getSession().getAttribute(CURRENT_USER_IN_SESSION);
    }
}
