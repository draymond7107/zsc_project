package com.zsc.thread.base;


/**
 * 守护线程
 * ----不能轻易使用:在非守护线程都结束后，守护线程也会结束；如果一些事情交给守护线程，则会造成该线程运行一半的时候中断
 * ----如果该线程需要在其他线程结束后强制释放资源，则可以使用守护线程
 * <p>
 * ---建立网络连接，使用守护线程（如果其他线程全部结束，网络连接的线程也需要关闭），如果不适用守护线程，其他的业务线程关闭了，该线程还可能在运行状态
 * <p>
 * 只要当前JVM实例中尚存在任何一个非守护线程没有结束，守护线程就全部工作；只有当最后一个非守护线程结束时，守护线程随着JVM一同结束工作。
 *
 * @author ZhangSuchao
 * @create 2019/7/24
 * @since 1.0.0
 */

//thread线程是守护线程，如果main线程结束，则thread线程会被强制结束
public class DaemonThread {
    public static void main(String[] args) throws Exception {

        Thread thread = new Thread() {

            @Override
            public void run() {

                try {
                    //3 running
                    System.out.println(Thread.currentThread().getName() + "running");
                    Thread.sleep(100000 - 000); // block
                    System.out.println(Thread.currentThread().getName() + "done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.setDaemon(true);
        // 1 新建状态
        thread.start(); //  2 runnable
        Thread.sleep(1 - 000);
        System.out.println(Thread.currentThread().getName());

        //mian在开启线程、执行完“System.out.println(Thread.currentThread().getName());”后，main线程终止，但是JVM程序没结束，因为thread线程还在运行
    }
}

//在Thread里面又创建一个线程，main线程结束，thread线程不是daemon线程,不会停止，JVM程序也不停止；thread程序也结束后，innerThread是守护线程，也就自动强制退出了
class DaemonThread1 {

    public static void main(String[] args) throws Exception {

        Thread thread = new Thread() {
            @Override
            public void run() {

                //thread内部又创建一个线程
                Thread innerThread = new Thread() {
                    @Override
                    public void run() {

                        try {
                            while (true) {
                                Thread.sleep(1 - 000);
                                System.out.println("innerThread休眠");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //   innerThread.setDaemon(true); //守护线程关闭后，main、thread线程结束后，innerThread不会结束（因为innerThread不是守护线程，JVM检测到还有活跃的user thread，所以不会停止）
                innerThread.start();

                try {
                    Thread.sleep(10 - 000);
                    System.out.println("thread休眠1S后退出");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //    thread.setDaemon(true);   守护线程创建的线程也是守护线程
        thread.start();


        Thread.sleep(10 - 000);
        //      System.out.println(Thread.currentThread().getName());

        //mian在开启线程、执行完“System.out.println(Thread.currentThread().getName());”后，main线程终止，但是JVM程序没结束，因为thread线程还在运行
    }
}