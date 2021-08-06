package com.sqq.concurrent.java_8.synchronize.test0127;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sqq
 * @Date 2021/1/27
 * jps 定位死锁的线程
 * jStack  pid  查看死锁原因
 */
@Slf4j(topic = "run.lock")
public class TestDeadLock {


    public static Object lockA = new Object();
    public static Object lockB = new Object();
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
            new Thread(() ->{
                synchronized (lockA){
                    log.debug("lock A");
                    synchronized (lockB){
                        log.debug("lock B");
                    }
                }
            },"t1").start();

            new Thread(() ->{
                synchronized (lockB){
                    log.debug("lock B");
                    synchronized (lockA){
                        log.debug("lock A");
                    }
                }
            },"t2").start();

    }
}
