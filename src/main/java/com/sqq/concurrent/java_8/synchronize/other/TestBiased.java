package com.sqq.concurrent.java_8.synchronize.other;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;


/**
 * @author sqq
 * @Date 2020/12/30
 * 测试偏向锁的状态  markWord 后三位 默认001， 偏向101
 *                         后两位 轻量级00  重量10
 */
@Slf4j(topic = "run.test")
public class TestBiased {
    public static void main(String[] args) throws InterruptedException {
        Dog dog = new Dog();
        log.debug(ClassLayout.parseInstance(dog).toPrintable());
        TimeUnit.SECONDS.sleep(4);
        System.out.println(ClassLayout.parseInstance(new Dog()).toPrintable());
    }

}

class Dog{

}