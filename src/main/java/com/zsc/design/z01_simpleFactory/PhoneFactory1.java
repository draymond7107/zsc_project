package com.zsc.design.z01_simpleFactory;


/**
 * @author ZhangSuchao
 * @create 2019/6/21
 * @since 1.0.0
 */

public class PhoneFactory1 {

    public Sender createSender(Sender sender) {
        /*使用多态*/
        if (sender instanceof HuaWei) {
            return new HuaWei();
        } else if (sender instanceof Ipad) {
            return new Ipad();
        } else {
            throw new RuntimeException();
        }

    }
}
