package com.zsc.design.z09_proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 * @author ZhangSuchao
 * @create 2019/9/4
 * @since 1.0.0
 */

public interface InvocationProxy {
}

interface Mobile {
    //打电话的功能
    void call(String phoneNumber);
}

class HuaweiMobile implements Mobile {

    @Override
    public void call(String phoneNumber) {
        System.out.println("拨打手机号为" + phoneNumber + "的手机");
    }
}

/**
 * 动态代理实现
 */
class DynamicProxy implements InvocationHandler {

    private Object targetObject;   //目标对象

    //绑定关系，也就是关联到哪个接口（与具体的实现类绑定）的哪些方法将被调用时，执行invoke方法。
    public Object newProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        //该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
        //第一个参数指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器
        //第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口
        //第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法
        //根据传入的目标返回一个代理对象
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(), this);
    }

    //Object proxy:被代理的对象
    //Method method:要调用的方法
    //Object[] args:方法调用时所需要参数
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)  {
        //执行前加强的工作
        System.out.println("start-->>");
        //调用目标方法
        Object invoke = null;
        try {
            invoke = method.invoke(targetObject, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //执行后加强的工作
        System.out.println("end-->>");
        return invoke;
    }

    //测试
    public static void main(String[] args) {
        DynamicProxy dynamicProxy = new DynamicProxy();
        Mobile mobile = (Mobile) dynamicProxy.newProxyInstance(new HuaweiMobile());
        mobile.call("1301111111111");
    }
}





