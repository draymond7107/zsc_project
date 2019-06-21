package com.zsc.tbpractice.design.simpleFactory;


/**
 * @author ZhangSuchao
 * @create 2019/6/21
 * @since 1.0.0
 */

public class PhoneFactory1 {

    public  Sender createSender(Sender sender) {

        if (sender instanceof HuaWei) {
            return new HuaWei();
        } else if (sender instanceof Ipad) {
            return new Ipad();
        } else {
            throw new RuntimeException();
        }

    }
}
