package com.zsc.thread.base;


/**
 * 继承Thread方式实现多线程
 * thread不共享，每一个调用此run()的线程，都会从ticket=10开始(可以使用static修饰，共享ticket变量)
 * 如果使用Runnable方式，多个线程会共享Runnable的资源
 *
 * @author ZhangSuchao
 * @create 2019/5/21
 * @since 1.0.0
 */

public class ThreadRunnable extends Thread {
    private Integer ticket = 10;

    public ThreadRunnable() {
    }

    public ThreadRunnable(Runnable target) {
        super(target);
    }

    public ThreadRunnable(Runnable target, String name) {
        super(target, name);
    }

    @Override
    public void run() {
        System.out.println("MyThread1运行");

        int m = 0;
        while (m < 10) {
            System.out.println(Thread.currentThread().getName() + "  " + m++);
        }
    }

    //Thread与Runnable同时重写，只会运行Thread的重写
    public static void main(String[] args) {
        CreateRunnable myRunnable = new CreateRunnable();
        ThreadRunnable myThread = new ThreadRunnable(myRunnable, "hhh");
        ThreadRunnable myThread1 = new ThreadRunnable(myRunnable, "bbbbb");
        myThread.start();
        myThread1.start();

    }

}
