package com.zsc.jvm.jvmThread;


/**
 * 模拟线程栈的大小与线程总量的联系
 * 没试出来，创建了397424个线程还没有停止
 * <p>
 * 构造的Thread,传入stackSize代表该线程占用的stack大小，默认值为0，0代表会忽略该参数，该参数会被JNI函数（虚拟机函数）使用，
 * 该参数在一些平台有效，在一些平台无效，一般不在创建线程的时候显示的设置，而是在JVM里面设置-Xss 10M
 *
 * @author ZhangSuchao
 * @create 2019/7/24
 * @since 1.0.0
 */

public class ThreadNumJvm {

    private static int counter;

    public static void main(String[] args) {

        try {

            //开启无限的线程
            int i = 0;
            while (i < Integer.MAX_VALUE) {
                counter++;
                System.out.println("counter===" + counter);
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            byte[] b = new byte[1024 * 1024 * 2];

                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("counter===" + counter);
        }


    }

}
