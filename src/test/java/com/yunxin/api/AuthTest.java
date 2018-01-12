package com.yunxin.api;

import com.yunxin.auth.PasswordHelper;
import com.yunxin.entity.User;
import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring 配置文件
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class AuthTest {




    @Test
    public void test() {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        System.out.println(token.toString());
        subject.login(token);
        subject.checkRole("系统管理员");//判断是否具有角色
        subject.checkPermission("登录权限");//判断是否具有权限
    }

}
