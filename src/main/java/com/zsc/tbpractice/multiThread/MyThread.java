package com.zsc.tbpractice.multiThread;


/**
 * 继承Thread方式实现多线程
 * thread不共享，每一个调用此run()的线程，都会从ticket=10开始
 * 如果使用Runnable方式，多个线程会共享Runnable的资源
 *
 * @author ZhangSuchao
 * @create 2019/5/21
 * @since 1.0.0
 */

public class MyThread extends Thread {
    private Integer ticket = 10;

    @Override
    public void run() {
        System.out.println("run运行");
        synchronized (this) {
            try {
                String name = Thread.currentThread().getName();

                while (this.ticket > 1) {
               //     Thread.sleep(1000);
                    ticket--;
                    System.out.println(name + " ::::" + ticket);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
