package com.zsc.tbpractice.thread;


/**
 * @author ZhangSuchao
 * @create 2019/5/21
 * @since 1.0.0
 */

public class MyRunnable implements Runnable {
    int i = 0;      //i写到对象里与写到方法里得到不同的结果

    @Override
    public void run() {

        int sum = 0;

        while (this.i <= 10) {
            sum += i;
            i++;
            System.out.println(Thread.currentThread().getName() + " ===" + sum);
        }

    }
}
