package com.zsc.thread.base;


/**
 * 业务逻辑的实例对象Runnable只有一个，将业务与控制分开（控制：new多个thread  业务：Runnable的run()方法）
 * 县城执行的时候改变里面的数据会有线程安全问题
 * <p>
 * 使用的策略模式，Thread相同，不同的Runnable实现，执行不同的方法
 *
 * @author ZhangSuchao
 * @create 2019/5/21
 * @since 1.0.0
 */

public class CreateRunnable implements Runnable {
    private int i = 0;      //i写到对象里与写到方法里得到不同的结果

    @Override
    public void run() {
        //1--100的和
        int sum = 0;
        while (i <= 100) {
            sum += i;
            i++;
            System.out.println(Thread.currentThread().getName() + " ===" + sum);
        }
    }

    public static void main(String[] args) {

        CreateRunnable myRunnable = new CreateRunnable();
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);
        Thread thread3 = new Thread(myRunnable);

        thread1.start();
        thread2.start();
        thread3.start();

        //// TODO: 2019/7/24  会有线程安全的问题:最后结果不是5050
    }
}
