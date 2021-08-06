package com.sqq.concurrent.java_8.synchronize.other;

/**
 * @author sqq
 * @Date 2020/12/25
 * 八大锁
 *
 *
 */
public class SynchronizedQuestion {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = a;
        System.out.println(a .equals(b) );
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(System.currentTimeMillis());
    }

}
