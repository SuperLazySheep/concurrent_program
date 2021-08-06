package com.sqq.test.complete;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author sqq
 * @Date 2021/6/3
 */
@Slf4j(topic = "run.test")
public class CompleteFutureDemo2 {
    public static void main(String[] args) {

        CompletableFuture<Object> completableFuture = CompletableFuture.supplyAsync(() -> {
            log.debug("1");
            System.out.println(111);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "111111";
        });

        CompletableFuture<Object> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            log.debug("3");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(333);
            return "3333";
        });
    }
}
