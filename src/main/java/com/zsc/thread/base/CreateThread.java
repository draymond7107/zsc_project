package com.zsc.thread.base;

/**
 * 线程的创建
 */
public class CreateThread extends Thread {

    private int num = 100;

    @Override
    public void run() {
        while (num>0){
            System.out.println(num--);
        }
    }


    public static void main(String[] args) {
        CreateThread createThread =new CreateThread();
        createThread.start();
    }
}
