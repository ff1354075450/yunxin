package com.yunxin.service.impl;

import com.yunxin.auth.PasswordHelper;
import com.yunxin.dao.UserDAO;
import com.yunxin.entity.User;
import com.yunxin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDAO userDAO;

    @Override
    public User createUser(User user) {
        PasswordHelper.encryptPassword(user);//生成数据库密码
        boolean result = userDAO.createUser(user);
        System.out.println("create:"+result);
        return user;
    }

    @Override
    public void changePassword(Long userId, String newPassword) {

    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findUser(username);
    }

    @Override
    public Set<String> findRoles(String username) {
        return userDAO.findRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return userDAO.findPermissions(username);
    }
}
