package com.sqq.test.java_7;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sqq
 */
@Slf4j(topic = "run.test")
public class RunnableTest {
    public static void main(String[] args) {
        Runnable run = new Runnable(){
            @Override
            public void run() {
                log.debug("hello");
            }
        };

        new Thread(run,"runnable").start();
        log.debug("running");
    }
}
