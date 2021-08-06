package com.sqq.concurrent.java_8.synchronize.test0127;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * @author sqq
 * @Date 2021/2/1
 *
 * 交替输出问题
 * 线程 1 输出 a 5 次，线程 2 输出 b 5 次，线程 3 输出 c 5 次。现在要求输出 abcabcabcabcabc 怎么实现
 */
public class CompareAndPrint {
    public static void main(String[] args) {

        try {
            reentLock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方式1
     */
    public static void waitNotify() {
        WaitNotify syncWaitNotify = new WaitNotify(1, 5);
        new Thread(() -> {
            syncWaitNotify.print("a", 1, 2);
        }).start();
        new Thread(() -> {
            syncWaitNotify.print("b", 2, 3);
        }).start();
        new Thread(() -> {
            syncWaitNotify.print("c", 3, 1);
        }).start();
    }

    /**
     * 方式2
     */
    public static void reentLock() throws InterruptedException {
        LockTest lockTest = new LockTest(5);
        Condition a = lockTest.newCondition();
        Condition b = lockTest.newCondition();
        Condition c = lockTest.newCondition();

        new Thread(() ->{
          lockTest.print("a", a, b);
        }).start();
        new Thread(() ->{
            lockTest.print("b", b, c);
        }).start();
        new Thread(() ->{
            lockTest.print("c", c, a);
        }).start();

        TimeUnit.SECONDS.sleep(1);
        lockTest.lock();
        try{
            System.out.println("开始了。。。。。。。。。。。。");
            a.signal();
        }finally {
            lockTest.unlock();
        }
    }
}
