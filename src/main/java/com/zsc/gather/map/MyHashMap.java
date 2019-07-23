package com.zsc.gather.map;


import java.util.*;

/**
 * @author ZhangSuchao
 * @create 2019/7/16
 * @since 1.0.0
 */

public class MyHashMap<K, V> {
// TODO: 2019/7/23 目前的hashMap还看不明白，后期添加实现方式
    /**
     * 实际长度
     */
    private int size;

    /**
     * 初始默认容量
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * 最大容量
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 加载因子
     */
    static final double LOAD_FACTOR = 0.75d;

    /**
     * TREEIFY_THRESHOLD，树化阈值，即上文所说的，超过该值，则把链表转换成红黑树。
     *
     * @param args
     */
    static final int ADD_THRESHOLD_VALUE = 8;
    /**
     * 阈值：减少元素的时候使用
     */
    static final int REDUCE_THESHOLD_VALUE = 6;

    /**
     * 如果在创建HashMap实例时没有给定capacity、loadFactor则默认值分别是16和0.75。
     * 当好多bin被映射到同一个桶时，如果这个桶中bin的数量小于TREEIFY_THRESHOLD当然不会转化成树形结构存储；
     * 如果这个桶中bin的数量大于了 TREEIFY_THRESHOLD ，但是capacity小于MIN_TREEIFY_CAPACITY 则依然使用链表结构进行存储，
     * 此时会对HashMap进行扩容；如果capacity大于了MIN_TREEIFY_CAPACITY ，则会进行树化
     */
    static final int MIN_TREEIFY_CAPACITY = 64;

    /**
     * 1 计算key:
     *
     * @param key
     * @param value
     * @return
     */

    public V put(K key, V value) {
        //


        return null;
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        List list = new ArrayList();
        List list1 = new LinkedList();

    }


}
