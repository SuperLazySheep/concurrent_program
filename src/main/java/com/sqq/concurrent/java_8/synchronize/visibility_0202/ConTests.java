package com.sqq.concurrent.java_8.synchronize.visibility_0202;

/**
 * @author sqq
 * @Date 2021/3/17
 */

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j(topic = "run.test")
public class ConTests {
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) throws InterruptedException  {
        ConTests test = new ConTests();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();
        producer.start();
        consumer.start();
        Thread.sleep(100);
        producer.interrupt();
        consumer.interrupt();
    }

    class Consumer extends Thread{
        @Override
        public void run() {
            consume();
        }
        volatile boolean flag=true;
        private void consume() {
            while(flag){
                lock.lock();
                try {
                    if(queue.isEmpty()){
                        try {
                            System.out.println("队列空，等待数据 " + Thread.currentThread().getName());
                            notEmpty.await();
                            System.out.println("1111111111 " + Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            flag = false;
                        }
                    }
                    queue.poll();                //每次移走队首元素
                    notFull.signal();
                    System.out.println("从队列取走一个元素，队列剩余"+queue.size()+"个元素 " + Thread.currentThread().getName());
                } finally{
                    lock.unlock();
                }
            }
        }
    }

    class Producer extends Thread{
        @Override
        public void run() {
            produce();
        }
        volatile boolean flag=true;
        private void produce() {
            while(flag){
                lock.lock();
                try {
                    if(queue.size() == queueSize){
                        try {
                            System.out.println("队列满，等待有空余空间 " + Thread.currentThread().getName());
                            notFull.await();
                            System.out.println("22222 " + Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            flag = false;
                        }
                    }
                    queue.offer(1);        //每次插入一个元素
                    notEmpty.signal();
                    System.out.println("向队列取中插入一个元素，队列剩余空间："+(queueSize-queue.size()) + Thread.currentThread().getName());
                } finally{
                    lock.unlock();
                }
            }
        }
    }
}
