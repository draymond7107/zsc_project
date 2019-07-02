package com.zsc.tbpractice.gather.list;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ZhangSuchao
 * @create 2019/6/28
 * @since 1.0.0
 */

public class ListTest {

    public static void main(String[] args) {

//        arrayListTest();
        linkedListTest();

    }

    public static void linkedListTest() {
        List list = new LinkedList<Integer>();
        list.add(1);
        Integer o =(Integer) list.get(0);
        System.out.println(o);


        MyLinkedList myLinkedList = new MyLinkedList<Integer>();
    }

    public static void arrayListTest() {
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


        MyArrayList<Integer> myArrayList2 = new MyArrayList(20);
        int length = myArrayList2.length();
        int size = myArrayList2.size();
        System.out.println(length + "  " + size);
    }
}
