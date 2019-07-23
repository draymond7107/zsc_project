package com.zsc.design.z01_simpleFactory;


/**
 * @author ZhangSuchao
 * @create 2019/6/21
 * @since 1.0.0
 */

public class TestMain {

    public static void main(String[] args) {
//        phoneFactory1();
//        phoneFactory2();
        phoneFactory3();
    }

    public static void phoneFactory1() {
        PhoneFactory1 phoneFactory = new PhoneFactory1();
        HuaWei huaWei = new HuaWei();
        Sender sender = phoneFactory.createSender(huaWei);
        sender.sendEmail("hhhhhh");
    }

    private static void phoneFactory2() {
        PhoneFactory2 phoneFactory2 = new PhoneFactory2();
        HuaWei huawei = phoneFactory2.createHuawei();
        huawei.sendEmail("a");
        Ipad ipad = phoneFactory2.createIpad();
        ipad.sendEmail("b");
    }

    private static void phoneFactory3() {

        HuaWei huawei = PhoneFactory3.createHuawei();
        huawei.sendEmail("a");
        Ipad ipad = PhoneFactory3.createIpad();
        ipad.sendEmail("b");
    }

}
