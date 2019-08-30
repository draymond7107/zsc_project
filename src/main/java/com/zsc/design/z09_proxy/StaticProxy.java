package com.zsc.design.z09_proxy;

/**
 * @author ZhangSuchao
 * @create 2019/8/30
 * @since 1.0.0
 */
public interface StaticProxy{}


//Phone 接口，定义“描述参数的方法”
 interface Phone {
    public void parameter();
}

//接口实现类
class Huawei implements Phone {
    //parameter是要增强（被代理）的方法
    @Override
    public void parameter() {
        System.out.println("华为mate30，颜色：白色；屏幕尺寸：5.1");
    }
}

//代理类，需要内部维护一个“被代理的对象（华为）”
class Proxy implements Phone {
    private Huawei huawei;

    public Proxy() {     //new Proxy()的时候创建Huawei对象
        super();
        this.huawei = new Huawei();
    }
    @Override
    public void parameter() {   //其他类调用该Proxy的方法，先执行proxy.before(),再调用 huawei.parameter()的方法，再执行proxy.after();达到方法的增强
        this.before();
        huawei.parameter();
        this.after();
    }
    //private修饰防止外界直接调用
    private void before() {
        System.out.println("开始打印手机参数：：：：");
    }
    private void after() {
        System.out.println("打印手机参数完成：：：：");
    }
}

//测试
class MainTest {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.parameter();  //调用代理的方法，而不是直接调用Huawei的方法
   /*    结果：
            开始打印手机参数：：：：
            华为mate30，颜色：白色；屏幕尺寸：5.1
            打印手机参数完成：：：：*/
    }
}