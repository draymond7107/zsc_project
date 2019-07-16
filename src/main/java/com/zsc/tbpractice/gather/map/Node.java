package com.zsc.tbpractice.gather.map;


import java.io.Serializable;

/**
 * Map的Node节点
 *
 * @author ZhangSuchao
 * @create 2019/7/16
 * @since 1.0.0
 */

public class Node<K, V> implements Serializable {
    /**
     * map<key,value> 形式，需要存的值：
     * hash(key)
     * key
     * V
     * Node<K,V> 下一个节点
     */

    int hash;

    int key;

    V value;

    Node<K, V> nextNode;

    public Node() {
    }

    public int getHash() {
        return hash;
    }

    public Node(int hash, int key, V value, Node<K, V> nextNode) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.nextNode = nextNode;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<K, V> nextNode) {
        this.nextNode = nextNode;
    }
}
