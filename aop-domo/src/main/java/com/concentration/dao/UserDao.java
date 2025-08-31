package com.concentration.dao;


import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    void selectUser();

    void insertUser();

    void updateUser();

    void deleteUser();
}
