package org.example.lab02;

/**
 *  线程同步和异步例子
 */
public class SynAsy {
    // 同步， 顺序执行
    public static void syn() {
        System.out.println("开始煮饭");
        System.out.println("洗米");
        System.out.println("倒水");
        System.out.println("煮饭");
    }
    // 异步执行
    public static void asy() throws InterruptedException {
        System.out.println("开始煎包子，需要3秒");
        Thread.sleep(3000);

        new Thread(() -> {
            System.out.println("煮牛奶");
        }).start();
        new Thread(() -> {
            System.out.println("放动漫");
        }).start();
        new Thread(() -> {
            System.out.println("戴耳机");
        }).start();
    }


    public static void main(String[] args) throws InterruptedException {
        // 同步
        syn();
        Thread.sleep(1000);
        // 异步
        asy();
    }
}
