package com.sqq.test.java_7;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author sqq
 */
@Slf4j(topic = "run.test")
public class CallableTest {
    @SneakyThrows
    public static void main(String[] args) {
         Callable callable = () -> {
             log.debug("call running");
             return "100";
         };
         ExecutorService executor = Executors.newFixedThreadPool(2);
         Future future = executor.submit(callable);
         log.debug("" + future.get());
    }
}
