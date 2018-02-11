package com.yunxin.auth;

import com.yunxin.Config;
import com.yunxin.entity.User;
import com.yunxin.service.UserService;
import com.yunxin.utils.JsonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/auth")
public class Auth {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/register")
    public Object register(String username,String password,@RequestParam(defaultValue = "0") Integer locked){
        User user = new User();
        user.setLocked(locked);
        user.setUsername(username);
        user.setPassword(password);
        userService.createUser(user);
        return user;
    }


    @ResponseBody
    @RequestMapping("/login")
    public Object login(String username, String password, HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);//记住密码
        subject.login(token);
        User user = userService.findByUsername(username);
        session.setAttribute(Config.sessionUserName,user);
        return JsonUtil.genSuccess();
    }

    @ResponseBody
    @RequestMapping("/logout")
    public Object logout(){
        SecurityUtils.getSubject().logout();
        return JsonUtil.genSuccess();
    }

    @ResponseBody
    @RequestMapping("/test")
    public Object test(HttpSession session, ShiroHttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("系统管理员");
        System.out.println("系统管理员");
        return JsonUtil.genSuccess();
    }

    @ResponseBody
    @RequestMapping("/test2")
    public Object test2(){
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission("登录权限");
        System.out.println("登录权限");
        return JsonUtil.genSuccess();
    }
}
