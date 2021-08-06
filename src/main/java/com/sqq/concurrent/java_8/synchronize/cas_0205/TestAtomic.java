package com.sqq.concurrent.java_8.synchronize.cas_0205;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sqq
 * @Date 2021/2/5
 */
public class TestAtomic {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(0);
        System.out.println(integer.incrementAndGet());  //1
        System.out.println(integer.compareAndSet(0,3));  // false
        System.out.println(integer.addAndGet(4));  // 5
        System.out.println(integer.updateAndGet(x -> x * 5));
//        System.out.println();
    }

}
