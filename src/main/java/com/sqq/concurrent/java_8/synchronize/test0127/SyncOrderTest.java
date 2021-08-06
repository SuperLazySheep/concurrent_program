package com.sqq.concurrent.java_8.synchronize.test0127;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author sqq
 * @Date 2021/2/1
 *
 * @descript 同步模式之顺序控制
 */
@Slf4j(topic = "run.test")
public class SyncOrderTest {
    static Object lock = new Object();
    static boolean flag = false;


    public static void main(String[] args) {
        partWay();
    }

    /**
     * part unPart 方式
     */
    public static void partWay(){
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.debug("t1  part");
        }, "t1");

        Thread t2 = new Thread(() -> {
            LockSupport.unpark(t1);
            log.debug("t2 ....");
        }, "t2");
        t1.start();
        t2.start();
    }

    /**
     *wait 方式
     */
    public static void waitWay() {
        new Thread( () ->{
            synchronized (lock){
                while (!flag){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                log.debug("t1  运行了");
            }
        },"t1").start();

        new Thread( () ->{
            synchronized (lock){
                flag = true;
                lock.notify();
                log.debug("t2 运行了");
            }

        },"t2").start();
    }
}
