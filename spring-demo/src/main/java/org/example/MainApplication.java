package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApplication {
    public static void main(String[] args) {
        System.out.println(">>>> 开始启动 Spring IOC 容器...");
        // 创建容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("\n>>>> Spring IOC 容器启动完毕，开始使用 Bean...");
        // 获取并使用 Bean
        UserService userService = context.getBean(UserService.class);
        System.out.println(userService.getUserInfo());

        System.out.println("\n>>>> 准备关闭 Spring IOC 容器...");
        // 关闭容器，触发销毁流程
        context.close();
        System.out.println(">>>> Spring IOC 容器已关闭。");
    }
}