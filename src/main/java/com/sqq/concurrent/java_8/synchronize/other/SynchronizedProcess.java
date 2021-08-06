package com.sqq.concurrent.java_8.synchronize.other;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sqq
 * 面向过程
 */
@Slf4j(topic = "run.test------")
public class SynchronizedProcess {

    static int count = 0;
    static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock){
                    count++;    // 2500
                }
            }
        },"t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
//                synchronized (lock){
                    count--;   //
//                }
            }
        },"t2");

            t1.start();
            t2.start();
            t1.join();
            t2.join();

        log.debug("{}",count);
    }
}
