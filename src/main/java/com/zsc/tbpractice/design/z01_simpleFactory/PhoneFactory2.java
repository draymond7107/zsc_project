package com.zsc.tbpractice.design.z01_simpleFactory;


/**
 * @author ZhangSuchao
 * @create 2019/6/21
 * @since 1.0.0
 */

public class PhoneFactory2 {

    public HuaWei createHuawei() {
        return new HuaWei();
    }

    public Ipad createIpad() {
        return new Ipad();

    }
}
