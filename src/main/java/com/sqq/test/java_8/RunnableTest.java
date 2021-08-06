package com.sqq.test.java_8;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author sqq
 */
@Slf4j(topic = "run.test")
public class RunnableTest {
    public static void main(String[] args) {
//        Runnable run = () -> {log.debug("hello");};
//
//        new Thread(run,"runnable").start();
//        log.debug("running");
        String s = "daaFSF1515";
        boolean fs = StringUtils.containsIgnoreCase(s, "fs");
        System.out.println(fs);
    }
}
