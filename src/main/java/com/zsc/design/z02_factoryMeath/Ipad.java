package com.zsc.design.z02_factoryMeath;


/**
 * Ipad实现发送短信接口
 *
 * @author ZhangSuchao
 * @create 2019/6/21
 * @since 1.0.0
 */

public class Ipad implements Sender {

    @Override
    public void sendEmail() {
        System.out.println("Ipad发送短信");
    }
}
