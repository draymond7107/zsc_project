package com.zsc.design.z01_simpleFactory;


/**
 * HuaWei手机实现发送短信接口
 *
 * @author ZhangSuchao
 * @create 2019/6/21
 * @since 1.0.0
 */

public class HuaWei implements Sender {

    @Override
    public String sendEmail(String message) {
        System.out.println("华为手机发送短息");
        return null;
    }
}
