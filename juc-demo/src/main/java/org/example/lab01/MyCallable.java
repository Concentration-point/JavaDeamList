package org.example.lab01;

import java.util.concurrent.*;

public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(2000);
        return 100;
    }

    public static void main(String[] args) {
        System.out.println("主线程开始");
        MyCallable myCallable = new MyCallable();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(myCallable);
        //  在get()方法中会阻塞当前线程，直到获取到结果或者抛出异常，处理异常也在此方法中处理
        try {
            Integer result = future.get();
            System.out.println("结果：" + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        // 被阻塞
        System.out.println("主线程结束");

        // 关闭线程池，释放资源
        executorService.shutdown();
    }
}
