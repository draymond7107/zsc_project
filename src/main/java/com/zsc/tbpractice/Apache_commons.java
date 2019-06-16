package com.zsc.tbpractice;

import com.zsc.base.utils.JsonUtils;
import com.zsc.general.entity.Usertb;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * @author ZhangSuchao
 * @create 2019/5/29
 * @since 1.0.0
 */
public class Apache_commons {


    public static void main(String[] args) throws Exception{
        final String s = BeanUtilsTest.copyBean();
        System.out.println(s);
    }


}

class BeanUtilsTest{

    public static String  copyBean() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Usertb usertb=new Usertb();
        usertb.setAge(12);
        usertb.setUcreatetime(new Date());
        usertb.setUname("zhangsan");
        Usertb o =(Usertb) BeanUtils.cloneBean(usertb);
        return JsonUtils.toStringNoEx(o);
    }
}

