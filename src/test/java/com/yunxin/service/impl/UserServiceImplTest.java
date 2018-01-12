package com.yunxin.service.impl;

import com.yunxin.auth.PasswordHelper;
import com.yunxin.entity.User;
import com.yunxin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring 配置文件
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUser() throws Exception {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setLocked(1);
        userService.createUser(user);
        System.out.println(user);
    }

    @Test
    public void changePassword() throws Exception {
    }

    @Test
    public void correlationRoles() throws Exception {
    }

    @Test
    public void uncorrelationRoles() throws Exception {
    }

    @Test
    public void findByUsername() throws Exception {
    }

    @Test
    public void findRoles() throws Exception {
        Set<String> st = userService.findRoles("admin");
       for (String str :st){
           System.out.println(st);
       }
    }

    @Test
    public void findPermissions() throws Exception {
        Set<String> st = userService.findPermissions("admin");
        for (String str :st){
            System.out.println(st);
        }
    }

}