package com.sqq.concurrent.java_8.synchronize.other;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.locks.LockSupport;

/**
 * @author sqq
 * @Date 2020/12/31
 */
@Slf4j(topic = "run.test")
public class Test1 {

    public static void main(String[] args) throws InterruptedException {
//        Dog d = new Dog();
//        Thread t1 = new Thread(() -> {
//            log.debug("t1------------111111");
//            synchronized (d) {
//                log.debug("t1------------2222222");
//                try {
//                    d.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                log.debug("t1------------333333");
//            }
//        }, "t1");
//        t1.start();
//        new Thread(() -> {
//            try {
//                Thread.sleep(6000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            synchronized (d) {
//                log.debug("notify");
//                LockSupport.unpark(t1);
//            }
//        }, "t2").start();
        test();
    }

    public static void test(){
        String s = "　　";
        System.out.println(s.charAt(1));
    }
}
