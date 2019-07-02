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
        defaultArr = new Object[10];
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IndexOutOfBoundsException("集合容量不能小于0");
        }
        defaultArr = new Object[capacity];
    }

    /**
     * 追加
     *
     * @param t
     * @return
     */
    public boolean add(E t) {
        if (size == 0) {
            defaultArr[0] = t;
        } else {
            if (size >= this.length()) {
                Object[] temporary = new Object[defaultArr.length * 3 / 2];
                System.arraycopy(defaultArr, 0, temporary, 0, defaultArr.length);
                defaultArr = temporary;
            }
        }
        defaultArr[size++] = t;
        return true;
    }

    /**
     * 将元素插入对用的位置
     *
     * @param index
     * @param e
     * @return
     */
    public boolean add(int index, E e) {
        //是否<0
        isOutOfRange(index);
        //是否需要扩容
        boolean needAddCapity = isNeedAddCapity(index);
        //扩容
        if (needAddCapity) {
            Object[] temporary = new Object[defaultArr.length * 3 / 2];
            defaultArr = temporary;
        }
        //角标在有元素的最后面追加
        if (index == size) {
            defaultArr[++size] = e;
        } else {
            int addLength = defaultArr.length - index - 1;
            System.arraycopy(defaultArr, index, defaultArr, index + 1, addLength);
            defaultArr[index] = e;
        }
        return true;
    }

    /**
     * 是否需要扩容
     *
     * @param indx
     * @return true需要扩容 false 不许要
     */
    private boolean isNeedAddCapity(int indx) {

        if (indx >= defaultArr.length) {
            return true;
        }
        return false;
    }

    /**
     * 是否超过范围
     *
     * @param index
     * @return
     */
    public boolean isOutOfRange(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return true;
    }

    /**
     * 获取
     *
     * @param index
     * @return
     */

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
        int later = size - index;
        if (later > 0)  //如果是最后一个，不会再创建新的数组
            copy(defaultArr, index, defaultArr, index + 1, later);
        //size为10时，最大角标为9, defaultArr[--size]整好为9，remove一个，最后一个角标为null
        defaultArr[--size] = null;
        return oldVal;
    }


    public int length() {
        return defaultArr.length;
    }

    public int size() {
        return size;
    }

    /**
     * @param src     源数组
     * @param index   源数组要复制的起始位置
     * @param dest    目标数组（将原数组复制到目标数组）
     * @param destPos 目标数组起始位置（从目标数组的哪个下标开始复制操作）
     * @param length  复制源数组的长度
     */
    public void copy(Object src, int index, Object dest, int destPos, int length) {
        System.arraycopy(src, index, dest, destPos, length);
    }

}
