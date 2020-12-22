package com.sqq.test.java_7;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;

/**
 * @author sqq
 * @Date 2020/12/22
 */
public class Sleep_Interrupt {
    public static void main(String[] args) {
//        TimeUnit.SECONDS.sleep(1);
        // 让出cpu的使用权
//        Thread.yield();
        Thread t = new Thread();
                t.setPriority(Thread.MAX_PRIORITY);
    }
}
