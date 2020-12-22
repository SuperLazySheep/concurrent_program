package com.sqq.test.java_8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author sqq
 */
@Slf4j(topic = "run.test")
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<>(() ->{
            log.debug("call running");
            return 100;
        });
        new Thread(futureTask).start();
        log.debug(futureTask.get()+ "");
    }
}
