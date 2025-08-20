package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {
    private final int maxsize;
    private final int coresize;
    private int timeout;
    private TimeUnit unit;
    private BlockingQueue<Runnable> blockingQueue;
    private RejectHandler rejectHandler;


    public MyThreadPool(int coresize, int maxsize, int timeout, TimeUnit unit,
                        BlockingQueue<Runnable> blockingQueue, RejectHandler reject) {
        this.maxsize = maxsize;
        this.coresize = coresize;
        this.timeout = timeout;
        this.unit = unit;
        this.blockingQueue = blockingQueue;
        this.rejectHandler = reject;
    }




    List<Thread> coreList = new ArrayList<>();

    List<Thread> supportList = new ArrayList<>();




    public void execute(Runnable command){
       // 使用offer而不是add，offer会有一个返回值来判断时候添加成功
        if(coreList.size() < coresize){
            Thread thread1 = new coreTask(command);  // 每一个新建的线程都应该去阻塞队列中获取任务，所以传的是task
            coreList.add(thread1);
            thread1.start();
            return;
        }
        if(blockingQueue.offer(command)) return;
        if(coreList.size() + supportList.size() < maxsize){
            Thread thread2 = new supportTask(command);
            supportList.add(thread2);
            thread2.start();
            return;
        }
        if (!blockingQueue.offer(command)) {
            rejectHandler.reject(command, this);
        }
    }

    class coreTask extends Thread{
        private final Runnable firstTask;
        public coreTask(Runnable runnable) {
            firstTask = runnable;
        }

        @Override
        public void run() {
            firstTask.run();
            while(true){
                try {
                    Runnable take = blockingQueue.take();
                    take.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    class supportTask extends Thread{
        private Runnable firstTask;
        public supportTask(Runnable runnable) {
            firstTask = runnable;
        }

        @Override
        public void run() {
            firstTask.run();
            while(true){
                try {
                    Runnable poll = blockingQueue.poll(timeout, unit);
                    if(poll == null){
                        break;
                    }
                    poll.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("辅助线程结束");
        }
    }
}
