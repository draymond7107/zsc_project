package com.zsc.tbpractice.design.simpleFactory;


/**
 * Ipad实现发送短信接口
 *
 * @author ZhangSuchao
 * @create 2019/6/21
 * @since 1.0.0
 */

public class Ipad implements Sender {
    @Override
    public String sendEmail(String message) {
        System.out.println("Ipad发送短信");
        return null;
    }
}
