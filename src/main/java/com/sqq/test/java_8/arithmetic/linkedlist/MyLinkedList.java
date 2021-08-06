package com.sqq.test.java_8.arithmetic.linkedlist;


/**
 * @author sqq
 * @descripe 单链表
 */
public class MyLinkedList {
    //头结点指针
    private Node head;
    //尾节点指针
    private Node last;
    //链表实际长度
    private int size;

    /**
     * 插入元素
     * @param data  插入元素
     * @param index 插入位置
     */
    public void add(int data, int index){
        if(data < 0 || index > size){
            throw new IndexOutOfBoundsException("超出节点范围！");
        }
        Node insertedNode= new Node(data);
        if(size == 0){
            //空链表
            head = insertedNode;
            last = insertedNode;
        }else if (index == 0){
            //插入头部
            insertedNode.next = head;
            head = insertedNode;
        }else if (index == size){
            //插在尾部
            last.next = insertedNode;
            last = insertedNode;
        }else{
            //插在中间
            Node prevNode = this.get(index - 1);
            insertedNode.next = prevNode.next;
           prevNode.next = insertedNode;
        }
        size++;
    }

    /**
     * 查找元素
     * @param index  查找的位置
     * @return
     */
    public Node get(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("超出节点范围！");
        }
        Node temp = head;
        for(int i = 0; i < index; i++){
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 输出
     */
    public void output(){
        Node temp = head;
        while(temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    /**
     * 删除元素
     * @param index 删除的位置
     * @return
     */
    public Node remove(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("超出节点范围！");
        }
        Node removeNode = null;
        if(index == 0){
            //头
            removeNode = head;
            head = head.next;
        }else if(index == size - 1){
            //尾
            Node prevNode = get(index - 1);
            removeNode = prevNode.next;
            prevNode.next = null;
            last = prevNode;
        }else{
            //中间
            Node prevNode = get(index - 1);
            removeNode = prevNode.next;
            prevNode.next = prevNode.next.next;
        }
        size--;
        return removeNode;
    }
}
