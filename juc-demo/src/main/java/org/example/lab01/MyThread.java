package org.example.lab01;

public class MyThread extends Thread{
    // 继承Thread类
    public void run(){
        System.out.println(Thread.currentThread().getName() + ": Hello JUC");
    }

    public static void main(String[] args) {
        MyThread MyThread1 = new MyThread();
        MyThread1.setName("MyThread1");


        // 实现Runnable接口， 函数式编程
        Thread MyThread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": Hello JUC");
        }, "MyThread2");


        MyThread1.start();
        MyThread2.start();

    }
}
