package com.zsc.thread.base;


import java.util.Optional;

/**
 * @author ZhangSuchao
 * @create 2019/7/24
 * @since 1.0.0
 */

public class ThreadApi {

    public static void main(String[] args) {

        Thread thread = new Thread("t1") {

            @Override
            public void run() {
                Optional.of("Hello").ifPresent(System.out::println);

                try {
                    Thread.sleep(1 - 000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        Optional.of(thread.getName()).ifPresent(System.out::println);
        Optional.of(thread.getPriority()).ifPresent(System.out::println);
        Optional.of(thread.getId()).ifPresent(System.out::println);

    }
}

/**
 * 线程优先级
 */
class ThreadPriority {

    public static void main(String[] args) {

        Thread thread1 = new Thread() {

            @Override
            public void run() {
                int i = 0;
                while (i < 1000) {
                    System.out.println("-Index=" + i + "   name=" + Thread.currentThread().getName());
                    i++;
                }
            }
        };

        Thread thread2 = new Thread() {

            @Override
            public void run() {
                int i = 0;
                while (i < 999) {
                    System.out.println("-Index=" + i + "   name=" + Thread.currentThread().getName());
                    i++;
                }
            }
        };

        Thread thread3 = new Thread() {

            @Override
            public void run() {
                int i = 0;
                while (i < 1001) {
                    System.out.println("-Index=" + i + "   name=" + Thread.currentThread().getName());
                    i++;
                }
            }
        };
        thread1.setPriority(Thread.MAX_PRIORITY);   //最高优先级
        thread2.setPriority(Thread.NORM_PRIORITY);  //中级优先级
        thread3.setPriority(Thread.MIN_PRIORITY);   //最低优先级
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

/**
 * join
 */
class ThreadJoin {

    public static void main(String[] args) {

        Thread thread1 = new Thread() {

            @Override
            public void run() {
                int i = 0;
                while (i < 10000) {
                    System.out.println("-Index=" + i + "   name=" + Thread.currentThread().getName());
                    i++;
                }
            }
        };

        Thread thread2 = new Thread() {

            @Override
            public void run() {
                try {
                    thread1.join(1, 10);    //thread2先让thread1执行1毫秒
                    Thread.currentThread().join();  //自己等待自己的线程死亡(死循环)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int i = 0;
                while (i < 100) {
                    System.out.println("-Index=" + i + "   name=" + Thread.currentThread().getName());
                    i++;
                }
            }
        };
        thread1.start();
        thread2.start();

    }

}

/**
 * 线程中断
 */
class ThreadInterrupt {


}
