package com.zsc.tbpractice.design.simpleFactory;


/**
 * 发送者短信接口
 *
 * @author ZhangSuchao
 * @create 2019/6/21
 * @since 1.0.0
 */

public interface Sender {
    //发送邮件
    public String sendEmail(String message);
}
