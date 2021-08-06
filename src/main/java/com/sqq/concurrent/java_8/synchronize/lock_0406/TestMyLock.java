package com.sqq.concurrent.java_8.synchronize.lock_0406;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author sqq
 * @Date 2021/4/6
 */
@Slf4j(topic = "run.TestMyLock")
public class TestMyLock {


    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(() -> {
            lock.lock();
           try {
               TimeUnit.SECONDS.sleep(1);
               log.debug("locking...");
           } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               log.debug("unlocking...");
               lock.unlock();

           }
        },"t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                log.debug("locking...");
            }finally {
                log.debug("unlocking...");
                lock.unlock();

            }
        },"t2").start();
    }
}

class MyLock implements Lock {

    private MySync mySync = new MySync();

    /**
     *  独占锁
      */
    class MySync extends AbstractQueuedSynchronizer{

        /**
         * 获取锁
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {

            if(compareAndSetState(0,1)){
                //设置自己为当前锁
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 是否持有只有独占锁
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        public Condition newCondition(){
            return new ConditionObject();
        }
    }

    /**
     * 加锁 (不成功 会进入等待队列)
     */
    @Override
    public void lock() {
        mySync.acquire(1);
    }

    /**
     *  加锁 可打断
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        mySync.acquireInterruptibly(1);

    }

    /**
     *  尝试加锁
     * @return
     */
    @Override
    public boolean tryLock() {
        return mySync.tryAcquire(1);
    }

    /**
     *  尝试加锁 (超时时间)
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
       return mySync.tryAcquireNanos(1,unit.toNanos(time));
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        mySync.release(1);
    }

    /**
     * 条件变量
     * @return
     */
    @Override
    public Condition newCondition() {
        return mySync.newCondition();
    }
}

