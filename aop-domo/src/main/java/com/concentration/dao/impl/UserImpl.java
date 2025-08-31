package com.concentration.dao.impl;

import com.concentration.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserImpl implements UserDao {


    @Override
    public void selectUser() {
        System.out.println("查询用户");
    }

    @Override
    public void insertUser() {
        System.out.println("插入用户");
    }

    @Override
    public void updateUser() {
        System.out.println("更新用户");
    }

    @Override
    public void deleteUser() {
        System.out.println("删除用户");
    }
}
