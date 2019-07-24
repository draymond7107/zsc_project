package com.zsc.thread.base;

/**
 * 线程与线程组
 */
public class ThreadAndThreadGroup {

    //如果没有传Thread则使用创建该线程的ThreadGroup
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());   //main线程

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        ThreadGroup threadGroup = thread.getThreadGroup();
        String name = threadGroup.getName();
        System.out.println("thread的ThreadGroup===" + name);

        int activeCount = threadGroup.activeCount();
        System.out.println("当前改组的活跃线程数量为：" + activeCount);    //2 thread+main线程
        Thread[] threadArr = new Thread[activeCount];

        //将正在运行的线程放到该threadArr中
        int enumerate = threadGroup.enumerate(threadArr);
        System.out.println("enumerate的数量为=="+enumerate);

        for (Thread thread1 : threadArr) {
            System.out.println(thread1);
        }
    }
}
