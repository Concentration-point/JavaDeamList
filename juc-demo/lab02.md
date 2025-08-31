#### 同步和异步
同步：顺序执行，每一步执行结束之后才能轮到下一步，阻塞
异步：你先干着，我忙别的去，非阻塞
异步编程常用于耗时操作，如上传大文件，生成报表等。如果需要立即结果则使用同步编程
demo见`SynAsy.java`

#### 简易消息队列和奇偶数输出
利用线程之间的等待和唤醒机制实现**生产消费**模型
这里需要注意的是，`wait(),notify(),notifyall()`等方法需要在同步代码块中进行，否则报错。这是因为其底层原理都使用了锁监视器，也就是说你得拥有这把锁，你才能进行释放或通知

常见的线程协作编码格式：
```java
public class WaitNotifyExample {
    private final Object lock = new Object(); // 共享锁对象
    public boolean condition = false;
    
    public void waitMethod() throws InterruptedException {
        synchronized (lock) {
           // 不符合条件即等待
            while(!condition){
	            lock.wait(); //释放锁，进入等待状态
            }
            改变condition
            lock.notifyall(); // 唤醒等待线程
        }
    }
    
    public void notifyMethod() {
        synchronized (lock) {
	        while(!condition){
	            lock.wait(); //释放锁，进入等待状态
            }
            改变condition
            lock.notifyall(); // 唤醒等待线程
        }
    }
}
```
在`MessageQueue.java`和`OneByOne`中可以看到这个编码格式。