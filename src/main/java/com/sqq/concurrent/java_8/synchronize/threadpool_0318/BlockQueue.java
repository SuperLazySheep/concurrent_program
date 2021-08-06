package com.sqq.concurrent.java_8.synchronize.threadpool_0318;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sqq
 * @Date 2021/3/18
 * 模拟阻塞队列
 */
@Slf4j(topic = "run.BlockQueue")
public class BlockQueue<T> {

    /**
     * 任务队列
     */
    private Deque<T> queue  = new ArrayDeque<>();

    /**
     * 锁
     */
    private ReentrantLock lock = new ReentrantLock();

    /**
     * 生产者条件
     */
    private Condition fullWaitSet = lock.newCondition();

    /**
     * 消费者条件
     */
    private Condition emptyWaitSet = lock.newCondition();

    /**
     * 容量
     */
    private int capCity;

    public BlockQueue(int capCity) {
        this.capCity = capCity;
    }

    /**
     *  带超时的 堵塞获取
     * @param timeout
     * @param timeUnit
     * @return
     */
    public T poll(long timeout, TimeUnit timeUnit){
        lock.lock();
        try{
            long nanos = timeUnit.toNanos(timeout);
            while(queue.isEmpty()){
                try {
                    if(nanos <= 0)
                        return null;
                   nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞获取
     * @return
     */
    public T take(){
        lock.lock();
        try {
            while(queue.isEmpty()){
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞插入
     * @param task
     */
   public void put(T task){
        lock.lock();
        try{
            while(queue.size() == capCity){
                try {
                    log.debug("等待放入堵塞队列中..{}....", task);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("放入堵塞队列中... {}", task);
            queue.addLast(task);
            emptyWaitSet.signal();
        }finally {
            lock.unlock();
        }
   }

    /**
     * 带超时时间的 插入
     * @param task
     * @param timeOut
     * @param timeUnit
     * @return
     */
   public boolean offer(T task, long timeOut, TimeUnit timeUnit){
       lock.lock();
       try{
           long nanos = timeUnit.toNanos(timeOut);
           while(queue.size() == capCity){
               try {
                   if(nanos <= 0){
                       return false;
                   }
                   log.debug("等待放入堵塞队列中..{}....", task);
                   nanos = fullWaitSet.awaitNanos(nanos);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           log.debug("放入堵塞队列中... {}", task);
           queue.addLast(task);
           emptyWaitSet.signal();
           return true;
       }finally {
           lock.unlock();
       }
   }

    /**
     * 执行方式
     * @param rejectPolicy
     * @param task
     */
    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try{
            if(queue.size() == capCity){
                rejectPolicy.reject(this, task);

            }else{ //空闲
                log.debug("加入任务队列 {}", task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }

        }finally {
            lock.unlock();
        }
    }
}
