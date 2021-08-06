package com.sqq.concurrent.java_8.synchronize.test0127;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sqq
 * @Date 2021/1/27
 *
 * 锁重入
 * 锁超时 tryLock(long,Timeunit)
 * 锁中断
 *
 * newCondition 不同的等待房间
 */
public class ReentrantLockTest {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        condition1.await();
        condition1.signal();   // 唤醒 condition1中的一个
        condition1.signalAll();  //唤醒全部

    }

}
