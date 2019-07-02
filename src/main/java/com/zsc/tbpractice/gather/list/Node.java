package com.zsc.tbpractice.gather.list;


/**
 * @author ZhangSuchao
 * @create 2019/7/2
 * @since 1.0.0
 */

public class Node<E> {

    private Object obj;

    private Node<E> pre;

    private Node<E> next;

    public Node() {
    }

    public Node(Object obj, Node<E> pre, Node<E> next) {
        this.obj = obj;
        this.pre = pre;
        this.next = next;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Node<E> getPre() {
        return pre;
    }

    public void setPre(Node<E> pre) {
        this.pre = pre;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}
