package com.zsc.tbpractice.gather.list;


import com.zsc.base.utils.JsonUtils;

import java.util.ArrayList;

/**
 * @author ZhangSuchao
 * @create 2019/6/28
 * @since 1.0.0
 */

public class ListTest {

    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.add(12);
        Integer integer = arrayList.get(0);

        MyArrayList<Integer> myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.add(6);
        myArrayList.add(7);
        myArrayList.add(8);
        myArrayList.add(9);
        myArrayList.add(10);
        myArrayList.add(11);
        myArrayList.add(12);


        Integer integer1 = myArrayList.get(10);
        Integer integer2 = myArrayList.get(11);

    }
}
