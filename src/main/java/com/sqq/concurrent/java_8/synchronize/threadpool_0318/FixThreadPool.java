package com.sqq.concurrent.java_8.synchronize.threadpool_0318;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sqq
 * @Date 2021/3/25
 *
 * 创建固定大小的线程池
 */
@Slf4j(topic = "run.FixThreadPool")
public class FixThreadPool {
    public static void main(String[] args) throws FileNotFoundException {
//        ExecutorService pool = Executors.newFixedThreadPool(2, new ThreadFactory() {
//
//            AtomicInteger atomicInteger = new AtomicInteger(1);
//
//            @Override
//            public Thread newThread(Runnable r) {
//                return new Thread(r, "my_Thread_Pool" + atomicInteger.getAndIncrement());
//            }
//        });
//
//        pool.execute(() ->{
//            log.debug("1");
//        });
//        pool.execute(() ->{
//            log.debug("2");
//        });
//        pool.execute(() ->{
//            log.debug("3");
//        });
//        int a = 1, b= 2,c = 3;
//        System.out.println(a ^ b);
//        a=b=c;
//        System.out.println("c  ---" + c + " b ---" + b + "a ---" + a  );

        Thread thread = new Thread(() -> {
            System.out.println("1");
        });
        thread.setDaemon(true);
        thread.start();

        System.out.println("main");
    }
}
