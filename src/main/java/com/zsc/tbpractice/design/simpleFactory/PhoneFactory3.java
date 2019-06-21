package com.zsc.tbpractice.design.simpleFactory;


/**
 * @author ZhangSuchao
 * @create 2019/6/21
 * @since 1.0.0
 */

public class PhoneFactory3 {

    public static HuaWei createHuawei() {
        return new HuaWei();
    }

    public static Ipad createIpad() {
        return new Ipad();

    }
}
