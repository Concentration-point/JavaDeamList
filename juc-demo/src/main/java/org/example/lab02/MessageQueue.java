package org.example.lab02;


/**
 * 利用线程之间的协作实现简易的生产消费模型
 */
public class MessageQueue {
    public String message;
    public boolean hasMessage = false;
    public final Object lock = new Object();

    public void productor(String message) throws InterruptedException {
        synchronized (lock){
            while(hasMessage){
                // 等待消费者消费
                lock.wait();
            }
            System.out.println("生产消息："+ message);
            this.message = message;
            hasMessage = true;
            lock.notifyAll();
        }
    }

    public String consumer() throws InterruptedException {
        synchronized (lock){
            while (!hasMessage){
                lock.wait();
            }

            hasMessage = false;
            lock.notifyAll();
            return message;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();

        Thread producerThread = new Thread(() -> {
            try {
                messageQueue.productor("1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        producerThread.start();

        Thread consumerThread = new Thread(() -> {
            try {
                String msg = messageQueue.consumer();
                System.out.println("消费消息："+ msg);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        consumerThread.start();



    }
}
