package com.sqq.test.java_8.arithmetic.queue;

/**
 * @author sqq
 * 使用循环数组实现队列
 */
public class MyQueue {

    private Object[] array;

    /**
     * 队头
     */
    private int front;
    /**
     * 队尾
     */
    private int rear;

    public MyQueue(int capacity){
        this.array = new Object[capacity];
    }

    /**
     * 入队
     * @param element
     * @throws Exception
     */
    public void enQueue(Object element) throws Exception {
        // 一直到（队尾下标+1）%数组长度 = 队头下标时，代表此队列真的已经满了
        if((rear + 1) % array.length == front){
            throw new Exception("队列已满！");
        }
        array[rear] = element;
        rear = (rear + 1) % array.length;
    }

    /**
     * 出队
     * @return
     * @throws Exception
     */
    public Object deQueue() throws Exception {
        if(rear == front){
            throw new Exception("队列已空！");
        }
        Object deQueueElement = array[front];
        front = (front + 1) % array.length;
        return deQueueElement;
    }

    /**
     * 输出
     */
    public void output(){
        for (int i = front; i != rear; i = (i +1)%array.length){
            System.out.print(array[i] + "  ");
        }
    }
}
