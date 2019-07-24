package com.zsc.jvm;


/**
 * 模拟线程 栈溢出异常
 *
 * @author ZhangSuchao
 * @create 2019/7/24
 * @since 1.0.0
 */

public class ThreadJvm {

    //方法区
    private int i = 0;

    private static int counter = 0;
    private int[] arr = new int[100];

    // ############ main栈溢出  ################
    public static void main(String[] args) {

        //虚拟机栈的局部变量表
        int s = 0;

        // 栈溢出异常 java.lang.StackOverflowError
        try {
            add(0);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            //counter=12546 执行12546次后栈溢出
            System.out.println("counter=" + counter);
        }
    }

    //无限递归(模拟栈溢出异常)
    private static void add(int i) {
        counter++;
        add(i + 1);

    }

}


class ThreadJvmTest1 {

    private static int counter = 0;

    public static void main(String[] args) {

        // ############ 默认栈大小thread 栈溢出  ################
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    add(0);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //counter==12811  创建的线程默认stackSize与main线程差不多 默认值才可以递归 12811次
                    //counter==990071
                    System.out.println("counter==" + counter);
                }

            }

            private void add(int i) {
                counter++;
                add(i++);
            }

        });
        thread.start();
    }
}

class ThreadJvmTest2 {

    private static int counter = 0;
    public static void main(String[] args) {

        // ############ 自定义栈大小thread 栈溢出  ################
        Thread thread = new Thread(null, new Runnable() {
            @Override
            public void run() {
                try {
                    add(0);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //counter==12811  创建的线程默认stackSize与main线程差不多 默认值才可以递归 12811次
                    //counter==990071
                    System.out.println("counter==" + counter);
                }
            }
            private void add(int i) {
                counter++;
                add(i++);
            }

        }, "test", 1 << 24);
        thread.start();
    }
}