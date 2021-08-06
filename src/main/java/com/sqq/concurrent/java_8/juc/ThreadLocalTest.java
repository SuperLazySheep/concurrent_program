package com.sqq.concurrent.java_8.juc;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @author sqq
 * @Date 2021/3/3
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Transfor transfor = new Transfor(bank);
        Thread t1 = new Thread(transfor);
        Thread t2 = new Thread(transfor);
        t1.start();
        t2.start();
        System.out.println(bank.get());
    }
}

class Bank {
    /**
     *  2 ways to override the initialValue() method
      */

    private ThreadLocal<Integer> money = ThreadLocal.withInitial(() -> 100);

    /**
     * ThreadLocal<Integer> money = new ThreadLocal<Integer>() {
     *
     * @Override protected Integer initialValue() {
     * return 100;
     * }
     * };
      */

    public int get() {
        return money.get();
    }

    public void set() {
        money.set(money.get() + 10);
    }
}

class Transfor implements Runnable {

    private Bank b;

    public Transfor(Bank bank) {
        b = bank;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            b.set();
            System.out.println(Thread.currentThread() + " ---- " + b.get());
        }
    }
}

