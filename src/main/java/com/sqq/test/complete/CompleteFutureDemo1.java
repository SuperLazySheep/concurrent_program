package com.sqq.test.complete;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sqq
 * @Date 2021/6/3
 */
@Slf4j(topic = "run.test")
public class CompleteFutureDemo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFutureOne = new CompletableFuture<>();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(() ->{
            try {
                log.debug("one");
                Thread.sleep(3000);
                completableFutureOne.complete("异步任务执行结果");
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //2返回之前的completeFuture
        CompletableFuture<String> completableFutureTwo = completableFutureOne.whenComplete((s, throwable) -> {
            log.debug("two");
            System.out.println("当异步任务执行完毕时打印异步任务的执行结果: " + s);
        });

        //3.返回新的completeFuture
        CompletableFuture<Integer> three = completableFutureTwo.thenApplyAsync(s -> {
            log.debug("Three");
            System.out.println("当异步任务执行结束时, 根据上一次的异步任务结果, 继续开始一个新的异步任务!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.length();
        });
        log.debug("ending");
        System.out.println("阻塞方式获取执行结果:" + three.get());
        cachedThreadPool.shutdown();
    }
}
