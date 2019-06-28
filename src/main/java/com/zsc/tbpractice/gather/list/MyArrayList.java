package com.zsc.tbpractice.gather.list;

import org.springframework.transaction.annotation.Transactional;

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

    public E get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) defaultArr[index];
    }

    /**
     * 删除
     *
     * @param index
     */
    @Transactional
    public E remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldVal = (E) defaultArr[index];

        //index后面的全部元素的长度
        int later = size - index - 1;
        if (later > 0)  //如果是最后一个，不会再创建新的数组
            System.arraycopy(defaultArr, index + 1, defaultArr, index, later);
        //size为10时，最大角标为9, defaultArr[--size]整好为9，remove一个，最后一个角标为null
        defaultArr[--size] = null;
        return oldVal;
    }

}
