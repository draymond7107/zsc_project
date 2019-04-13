package com.zsc.base.utils;


import org.apache.poi.ss.formula.functions.T;

import java.util.Arrays;
import java.util.List;

/**
 * 集合工具类
 *
 * @author ZhangSuchao
 * @create 2019/4/13
 * @since 1.0.0
 */

public class CollectionUtils extends org.apache.commons.collections.CollectionUtils {

    public static boolean isEmpty(List<T> list) {

        return (list == null || list.isEmpty()) ? true : false;
    }

    public static boolean isNotEmpty(List<T> list) {

        return !isEmpty(list);
    }



}
