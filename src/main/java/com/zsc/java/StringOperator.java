package com.zsc.java;


import java.io.UnsupportedEncodingException;

/**
 * String类的基础操作
 *
 * @author ZhangSuchao
 * @create 2019/5/9
 * @since 1.0.0
 */

public class StringOperator {

//    public static void main(String[] args) {
//
//
////        newString();
////        charAt1();
////        codePointAt1();
//
//    }

    //1 构造器生成String对象
    // 1.1 初始化新创建的String对象，使其表示空字符序列。
    public static String newString() {
        String str1 = new String();
        str1 = "abc";
        System.out.println(str1);       // abc

        //  1.2 通过使用平台的默认字符集解码指定的字节数组来

        byte[] bytes = new byte[6];
        bytes[0] = 0;
        bytes[1] = 1;
        bytes[2] = 2;
        bytes[3] = 3;
        bytes[4] = 4;
        bytes[5] = 5;
        String str2 = new String(bytes);
        System.out.println("通过使用平台的默认字符集解码指定的字节数组来:str2=" + str2);  // 什么也没有:原因？？

        //  1.3 String通过使用指定的字符集解码指定的字节数组
        try {
            String str3 = new String(bytes, "UTF-8");
            System.out.println("通过使用指定的字符集解码指定的字节数组:str3=" + str3);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //    1.4  分配一个新的，String以便它表示当前包含在字符数组参数中的字符序列
        char[] chars = new char[6];
        chars[0] = '0';
        chars[1] = '1';
        chars[2] = '2';
        chars[3] = '3';
        chars[4] = '4';
        chars[5] = '5';

        String str4 = new String(chars);
        System.out.println("分配一个新的，String以便它表示当前包含在字符数组参数中的字符序列。:str4=" + str4);  // str4=012345
        //  1.5
        String str5 = new String(chars, 2, 3);
        System.out.println("分配String包含字符数组参数的子数组中的字符的new:str5=" + str5);  //  str5=234

        //  1.6  分配String包含Unicode代码点数组参数的子数组中的字符的new : String(int[] codePoints, int offset, int count) ?????
        int[] ints = new int[3];
        ints[0] = 0;
        ints[1] = 1;
        ints[2] = 2;
        String str6 = new String(ints, 1, 2);
        System.out.println("分配String包含Unicode代码点数组参数的子数组中的字符的new:str6=" + str6);   //   str6=识别不出来？？？

        //  1.7 初始化新创建的String对象，使其表示与参数相同的字符序列; 换句话说，新创建的字符串是参数字符串的副本
        String str7 = new String("abcde");
        System.out.println("初始化新创建的String对象，使其表示与参数相同的字符序列; 换句话说，新创建的字符串是参数字符串的副本:str7=" + str7);   //str7=abcde
        //  1.8  分配一个新字符串，其中包含当前包含在字符串缓冲区参数中的字符序列
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(1);
        stringBuffer.append("2");
        stringBuffer.append("我");
        stringBuffer.append(true);
        stringBuffer.append(1L);

        String str8 = new String(stringBuffer);
        System.out.println("分配一个新字符串，其中包含当前包含在字符串缓冲区参数中的字符序列:str8=" + str8);        //str8=12我true1
        return null;
    }

    //2 返回char指定索引处的值
    public static void charAt1() {

        String str1 = "我qwertyuiop";
        char c0 = str1.charAt(0);
        System.out.println("返回char指定索引处的值:c0=" + c0); //  c0=我  //1个char占用2/4个子节 ？？
        char c1 = str1.charAt(1);
        System.out.println("返回char指定索引处的值:c1=" + c1); //  c1=q
    }

    //3 返回指定索引处的字符（Unicode代码点）
    public static void codePointAt1() {

        String str1 = "我qwertyuiop";
        int i0 = str1.codePointAt(0);
        System.out.println("返回char指定索引处的值:i0=" + i0); // i0=25105   "我使用Unicode的代码点：25105表示"        作用？？？
        int i1 = str1.codePointAt(1);
        System.out.println("返回char指定索引处的值:i1=" + i1); //  i1=113

    }

    //################################################################################################################################
    public static void main(String[] args) {
        codePointBefore1();
        codePointCount1();
        compareTo1();
        compareToIgnoreCase1();
        concat1();
        contains1();
    }

    //  4   返回指定索引之前的字符（Unicode代码点）
    public static void codePointBefore1() {
        String str1 = "我qwertyuiop";
        int i1 = str1.codePointBefore(1);
        System.out.println(" 返回指定索引之前的字符（Unicode代码点）:i1=" + i1); //  i1=25105
        int i2 = str1.codePointBefore(2);
        System.out.println(" 返回指定索引之前的字符（Unicode代码点）:i2=" + i2); // i2=113

    }

    //  5   当且仅当此字符串包含指定的char值序列时，才返回true：contains(CharSequence s)
    public static void contains1() {

        String str1 = "qwertyuiop";
        String str2 = "wer";

        boolean contains = str1.contains(str2);
        System.out.println(contains);
    }


    //  5     返回指定文本范围内的Unicode代码点数String   作用？？？？？
    public static void codePointCount1() {
        String str1 = "我们wertyuiop";
        int i05 = str1.codePointCount(0, 5);
        System.out.println("返回指定文本范围内的Unicode代码点数String:i05=" + i05);   //    i05=5

    }

    //  6   按字典顺序比较两个字符串       (依遍历次比较，直到char值不同返回前一个-后一个char值)
    public static void compareTo1() {

        String str1 = "av";
        String str2 = "b";

        int i = str1.compareTo(str2);
        System.out.println("按字典顺序比较两个字符串:i=" + i);        //i=-1

    }

    //  7   按字典顺序比较两个字符串，忽略大小写差异 ？？？？怎么计算的
    public static void compareToIgnoreCase1() {
        String str1 = "AB";
        String str2 = "AN";

        int i = str1.compareToIgnoreCase(str2);
        System.out.println("按字典顺序比较两个字符串:i=" + i);        //i=-12
    }

    //  8   ·将指定的字符串连接到此字符串的末尾
    public static void concat1() {

        String str1 = "abc";
        String str2 = "abc";

        String concat = str1.concat(str2);
        System.out.println(concat);
    }

    //  9   将此字符串与指定字符串进行比较CharSequence：contentEquals(CharSequence cs)
    public static void contentEquals() {


    }

    //###########################################################################################
    //拼接多个字符串
    public static String join() {
        String str1 = "i";
        String str2 = "love";
        String str3 = "java";
        String joinStr = String.join(" ", str1, str2, str3);

        System.out.println("joinStr=" + joinStr);   //结果：joinStr=i love java
        return joinStr;
    }


}
