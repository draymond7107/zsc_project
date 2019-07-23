package com.zsc.io;


import org.springframework.stereotype.Component;

import java.io.File;

/**
 * //文件(夹/目录）的操作
 * 推荐连接：https://www.cnblogs.com/dreamyu/p/6551137.html
 *
 * @author ZhangSuchao
 * @create 2019/4/29
 * @since 1.0.0
 */
@Component
public class FileIo {

    public static void main(String[] args) {
        File file = new File("F:\\io");
        getAllFileAndDir(file);
    }

    public static String file1() {
        /*
        1：创建目录
        2：判断是否存在
        3：
        */


        //1：创建目录 TODO 修改 /  window与linux不同
        File file = new File("F:/hello.mp3");
        //2：判断是否存在
        if (file.exists()) {
            boolean isFile = file.isFile();
            boolean isDirectory = file.isDirectory();
            long length = file.length();                    //得到文件大小
            String absolutePath = file.getAbsolutePath();   //得到文件路径

        }
        //3：创建文件夹
        File dir = new File("F:/ioTest.txt");
        //判断是否存在
        if (dir.exists()) {
            System.out.println("文件夹已经存在");
        } else {
            dir.mkdir();//创建
        }
        //4：列出一个文件夹下的所有文件
        File file1 = new File("D:/server");
        if (file1.exists()) {
            boolean directory = file1.isDirectory();
            if (directory) {
                String[] list = file1.list();
                for (String s :
                        list) {
                    System.out.println(s);
                }

            }
        }


        return null;
    }

    /**
     * 5:列出文件夹下所有文件与文件夹（递归调用）
     * 这个是返回的什么
     * TODO 返回数据不对，需要详细看下方法的返回信息
     *
     * @param file
     * @return
     */
    public static String getAllFileAndDir(File file) {
        if (!file.exists()) return null;
        if (file.isFile()) {
            System.out.println(file.getName());
        }
        File[] files = file.listFiles();

        for (File s :
                files) {
            if (s.isFile()) {
                System.out.println("文件" + file.getName());
            } else {
                System.out.println("文件夹" + file.getName());
                getAllFileAndDir(s);
            }
        }

        return null;
    }
}
