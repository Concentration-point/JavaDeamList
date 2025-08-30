#### 继承Thread类
实现简单，适合简单任务，不能共享变量资源

#### 实现Runnable接口
```java
public class MyRunnable implements Runnable{  
  
    private int ticket = 10;  
    @Override  
    public void run() {  
        while (ticket  0){  
            System.out.println(Thread.currentThread().getName() + 正在卖第 + ticket --  + 张票);  
        }  
    }  
  
    public static void main(String[] args) {  
        MyRunnable myRunnable = new MyRunnable();  
        Thread t1 = new Thread(myRunnable);  
        Thread t2 = new Thread(myRunnable);  
        Thread t3 = new Thread(myRunnable);  
        t1.start();  
        t2.start();  
        t3.start();  
  
    }  
}
```
将Runnable作为任务参数传入Thread中可以实现线程共享变量，但是无法保证线程安全，上述代码会出现超卖问题，因为`ticket --` 这句代码不是原子操作，但很难复现（看系统负载），如果加入休眠时间问题就体现出来了：
```java
public void run() {  
    while (ticket  0){  
        try {  
            Thread.sleep(200);  
        } catch (InterruptedException e) {  
            throw new RuntimeException(e);  
        }  
        System.out.println(Thread.currentThread().getName() + 正在卖第 + ticket --  + 张票);  
    }  
}
```
放运行截图太占空间了，也影响观感，请运行代码进行验证


#### 实现Callable接口 + FutureTask， 需要返回值时使用
```java
public class MyCallable implements CallableInteger {  
    @Override  
    public Integer call() throws Exception {  
        Thread.sleep(2000);  
        return 100;  
    }  
  
    public static void main(String[] args) {  
        System.out.println(主线程开始);  
        MyCallable myCallable = new MyCallable();  
        ExecutorService executorService = Executors.newSingleThreadExecutor();  
        FutureInteger future = executorService.submit(myCallable);  
          在get()方法中会阻塞当前线程，直到获取到结果或者抛出异常，处理异常也在此方法中处理  
        try {  
            Integer result = future.get();  
            System.out.println(结果： + result);  
        } catch (InterruptedException e) {  
            throw new RuntimeException(e);  
        } catch (ExecutionException e) {  
            throw new RuntimeException(e);  
        }  
         被阻塞  
        System.out.println(主线程结束);  
  
         关闭线程池，释放资源  
        executorService.shutdown();  
    }  
}
```
有点懵逼？简述一下Callable，Future，ExecutorService之间的关系：
Callable是你提交的任务，ExecutorService是执行该任务的机器，你把任务提交给它，它返回一个凭据Future给你，等任务执行完成你凭借着Future获取结果。

打个比喻：就像你买奶茶，Executorservice好比取餐的窗口，你把任务callable交给窗口，窗口给你一个排队号Future，之后凭号取奶茶                                                          
-- b站莲城的老李




