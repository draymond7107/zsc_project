package com.zsc.gather.list;


/**
 * linkedList 的List存的是first与last节点
 *
 * @author ZhangSuchao
 * @create 2019/7/2
 * @since 1.0.0
 */

public class MyLinkedList<E> {

    private int size;

    private Node<E> first;

    private Node<E> last;


    public void add(Object obj) {
        //添加第一个元素，first的obj都为obj;pre,next都为null
        if (first == null) {
            first = new Node<>(obj, null, null);
            last = new Node<>(obj, null, null);
        } else {
            //下一个节点的数据
            // FIXME: 2019/7/2  last.getNext() 是空的   last.setNext(eNode) 能否重新赋值
            Node<E> eNode = new Node<E>(obj, last.getNext(), null);
            last.setNext(eNode); //更新没插入新的节点前的最后一个节点
            //将下一个节点的地址指向last
            last = eNode;
        }
        size++;
    }

    // FIXME:2019/7/2

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        //从前面查找
        if (index <= size / 2) {
            Node<E> eNode = first;
            for (int i = 0; i <= index; i++) {
                eNode = first.getNext();
            }
            return (E) eNode.getObj();
        } else {
            //倒着查找
            // FIXME: 2019/7/2
            Node<E> lastNode = last;
            return null;
        }

    }


    public MyLinkedList() {

    }

    public MyLinkedList(Node first, Node last) {
        this.first = first;
        this.last = last;
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }
}
