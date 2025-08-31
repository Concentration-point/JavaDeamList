package org.example.lab02;


public class OneByOne {
    public static final Object lock = new Object();
    public static int count = 1;
    public static final int MaxCount = 100;


    public void showodd() throws InterruptedException {
        synchronized (lock){
            while (count <= MaxCount){
                // 如果是偶数，等待
                while (count % 2 == 0){
                    lock.wait();
                }
                if (count <= MaxCount) {
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                    count ++;
                    lock.notifyAll();
                }
            }


        }
    }

    public void showeven() throws InterruptedException {
        synchronized (lock){
            while (count <= MaxCount){
                while (count % 2 == 1){
                    lock.wait();
                }
                if(count <= MaxCount){
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                    count ++;
                    lock.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OneByOne oneByOne = new OneByOne();
        new Thread(() -> {
            try {
                oneByOne.showodd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "线程1").start();


        Thread t2 = new Thread(() -> {
            try {
                oneByOne.showeven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "线程2");
        t2.start();

        System.out.println(t2.getState());
    }



}
