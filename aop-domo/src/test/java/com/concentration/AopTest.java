package com.concentration;

import com.concentration.proxy.MyProxyFactory;
import com.concentration.proxyobject.Rental;
import com.concentration.proxyobject.Tenant;
import com.concentration.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AopTest {

    @Autowired
    public UserService userService;

    @Test
    void test(){
        userService.getUserList();
        userService.saveUser();
        userService.updateUser();
        userService.deleteUser();
    }

}
