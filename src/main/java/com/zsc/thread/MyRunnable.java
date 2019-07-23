package com.zsc.thread;


/**
 * @author ZhangSuchao
 * @create 2019/5/21
 * @since 1.0.0
 */

public class MyRunnable implements Runnable {
    private int i = 0;      //i写到对象里与写到方法里得到不同的结果

    @Override
    public void run() {
        //1--100的和
        int sum = 0;
        while (i <= 10) {
            sum += i;
            i++;
            System.out.println(Thread.currentThread().getName() + " ===" + sum);
        }

    }
}
