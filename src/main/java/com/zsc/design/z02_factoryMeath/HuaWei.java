package com.zsc.design.z02_factoryMeath;


/**
 * HuaWei手机实现发送短信接口
 *
 * @author ZhangSuchao
 * @create 2019/6/21
 * @since 1.0.0
 */

public class HuaWei implements Sender {

    @Override
    public void sendEmail() {
        System.out.println("华为手机发送短息");
    }
}
