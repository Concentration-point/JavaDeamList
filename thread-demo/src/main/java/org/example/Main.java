package org.example;

import java.nio.channels.MulticastChannel;
import java.sql.SQLOutput;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        //MyPool myThreadPool = new MyPool(2,4,1, TimeUnit.SECONDS,new ArrayBlockingQueue<>(2));
        MyThreadPool myThreadPool = new MyThreadPool(2,4,1, TimeUnit.SECONDS,new ArrayBlockingQueue<>(2), new ThrowHandlerImpl());

        for (int i = 0; i < 10; i++) {
            myThreadPool.execute(() ->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
            });
        }


        System.out.println("主线程没有被阻塞");
    }
}