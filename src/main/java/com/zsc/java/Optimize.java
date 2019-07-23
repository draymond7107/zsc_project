package com.zsc.java;


import java.util.StringTokenizer;

/**
 * 优化
 * 1：String剪切后使用new对象的方式，避免以空间换时间
 *
 * @author ZhangSuchao
 * @create 2019/6/19
 * @since 1.0.0
 */

public class Optimize {
    public static void main(String[] args) {
        //  test1();
        test2();
    }

    /**
     * 避免内存泄漏
     */
    public static void test1() {
        String sss = "abv";
        String str = new String("abv");
        boolean equals = sss.equals(str);

        String substring = sss.substring(1, 2);
        //不会引起内存泄漏
        String s = new String(substring);
        //str的引用的数据
        String intern = str.intern();
    }

    /**
     * 字符串分割优化
     */
    public static void test2() {
        String str = "a,b,c";
        str.endsWith("s");
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        while (stringTokenizer.hasMoreElements()) {
            String s = stringTokenizer.nextToken();
            System.out.println(s);
        }
    }


}
