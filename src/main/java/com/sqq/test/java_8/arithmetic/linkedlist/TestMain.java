package com.sqq.test.java_8.arithmetic.linkedlist;

import java.util.LinkedList;

/**
 * @author sqq
 */
public class TestMain {

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add(0,0);
        myLinkedList.add(1,1);
        myLinkedList.add(2,2);
        myLinkedList.add(3,3);
        myLinkedList.remove(2);
        myLinkedList.add(2222,2);
//        myLinkedList.output();
        Node node = myLinkedList.get(2);
        System.out.println(node.data);
    }
}
