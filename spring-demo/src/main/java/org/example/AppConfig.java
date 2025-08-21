package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example")
public class AppConfig {

    @Bean(initMethod = "myInit", destroyMethod = "myDestroy")
    public UserService userService() {
        UserService userService = new UserService();
        userService.setUsername("Concentration-point"); // 这里进行属性设置
        return userService;
    }
}