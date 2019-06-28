package com.zsc.tbpractice.gather.list;

/**
 * @author ZhangSuchao
 * @create 2019/6/28
 * @since 1.0.0
 */

public class MyArrayList<E> {
    //默认值为0
    private int size;

    private Object[] defaultArr;


    public MyArrayList() {
        defaultArr = new Object[3];
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IndexOutOfBoundsException("集合容量不能小于0");
        }
        defaultArr = new Object[capacity];
    }

    public boolean add(E t) {
        if (size == 0) {
            defaultArr[0] = t;
        } else {
            if (size >= defaultArr.length) {
                Object[] temporary = new Object[defaultArr.length * 3 / 2];
                System.arraycopy(defaultArr, 0, temporary, 0, defaultArr.length);
                defaultArr = temporary;
            }
        }
        defaultArr[size++] = t;
        return true;
    }

    public Object get(int index) {
        if (size < index) {
            throw new IndexOutOfBoundsException();
        }
        return defaultArr[index];

    }

}
