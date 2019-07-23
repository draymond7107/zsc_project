package com.zsc.io;


import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author ZhangSuchao
 * @create 2019/4/29
 * @since 1.0.0
 */
@Component
public class InputStreamIo {
    public static void main(String[] args) {
        fileOutputIo();
        fileInputIo();
    }

    public static String fileOutputIo() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("F:/io/io_file1.txt",true);

            String str = "2019/4/29 20：23";
            byte[] bytes = str.getBytes();
            fos.write(bytes);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }


    public static String fileInputIo() {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("F:/io/io_file1.txt");

            byte[] bytes = new byte[1024];
            int n = 0;
            while ((n = fis.read(bytes)) != -1) {
                String str = new String(bytes, 0, n);
                System.out.println(str);
            }

        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
