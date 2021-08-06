package com.sqq.test.java_8.arithmetic.linkedlist;

/**
 * @author sqq
 */
public class Node {

    /**
     *下一个节点
     */
    public Node next = null;

    /**
     * 节点数据
     */
   public int data;

    public Node(int data) {
        this.data = data;
    }

    public Node(Node next, int data) {
        this.next = next;
        this.data = data;
    }
}
