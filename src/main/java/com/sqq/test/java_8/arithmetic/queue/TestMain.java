package com.sqq.test.java_8.arithmetic.queue;

/**
 * @author sqq
 * @Date 2021/1/27
 */
public class TestMain {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
        MyQueue myQueue = new MyQueue(6);
        myQueue.enQueue("sss");
        myQueue.enQueue(600);
        myQueue.enQueue(500);
        myQueue.enQueue(400);
        myQueue.enQueue(300);
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.output();
        myQueue.enQueue(200);
        myQueue.enQueue("100");
        myQueue.output();

    }
}
