package com.zsc.design.z01_simpleFactory;


/**
 * @author ZhangSuchao
 * @create 2019/6/21
 * @since 1.0.0
 */

public class PhoneFactory3 {
    /*脱离了多态的范围*/
    public static HuaWei createHuawei() {
        return new HuaWei();
    }

    public static Ipad createIpad() {
        return new Ipad();

    }
}
