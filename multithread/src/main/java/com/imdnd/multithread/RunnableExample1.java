package com.imdnd.multithread;

/**
 * Created by adam on 2017/6/5.
 */
public class RunnableExample1 {

    public static void main(String[] args) {
        MyRunnable mt = new MyRunnable();

        // 启动3个线程t1,t2,t3(它们共用一个Runnable对象)，这3个线程一共卖10张票！
        Thread t1 = new Thread(mt);
        Thread t2 = new Thread(mt);
        Thread t3 = new Thread(mt);
        t1.start();
        t2.start();
        t3.start();
    }


}

// RunnableTest.java 源码
class MyRunnable implements Runnable {
    private int ticket = 10;

    public void run() {
        for (int i = 0; i < 20; i++) {
            if (this.ticket > 0) {
                System.out.println(Thread.currentThread().getName() + " 卖票：ticket" + this.ticket--);
            }
        }
    }
};
