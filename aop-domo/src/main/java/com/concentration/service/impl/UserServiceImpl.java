package com.concentration.service.impl;

import com.concentration.annotation.MyAnnotation;
import com.concentration.dao.UserDao;
import com.concentration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void getUserList() {
        userDao.selectUser();
    }

    @Override
    @MyAnnotation
    public void saveUser() {
        userDao.insertUser();
    }

    @Override
    @MyAnnotation
    public void updateUser() {
        userDao.updateUser();
    }

    @Override
    @MyAnnotation
    public void deleteUser() {
        userDao.deleteUser();
    }
}
