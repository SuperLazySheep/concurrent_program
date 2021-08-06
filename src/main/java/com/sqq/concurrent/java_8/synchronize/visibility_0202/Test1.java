package com.sqq.concurrent.java_8.synchronize.visibility_0202;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author sqq
 * @Date 2021/2/2
 */
@Slf4j(topic = "run.test")
public class Test1 {

    volatile static boolean run = true;

    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(() -> {
//            while(run){
//
//            }
//        });
//        thread.start();
//
//        TimeUnit.SECONDS.sleep(1);
//        log.debug("停止 t");
//        run = false;
//        System.out.println();
        Student1 student1 = new Student1();
        String d = Optional.ofNullable(student1.getName()).orElse("为空");
        System.out.println(d);

    }

static class Student1{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
