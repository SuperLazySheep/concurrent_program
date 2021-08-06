package com.sqq.concurrent.java_8.synchronize.test0127;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sqq
 * @Date 2021/2/2
 */
public class ConstPool {
    private Integer poolMaxSize = 3;

    private Integer semaPermits = 5;
    /**
     * 常量容器
     */
    private List<String> list = new ArrayList<>();
    /**
     * 同时只能有10个线程进入常量池
     */
    private Semaphore semaphore = new Semaphore(semaPermits);
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public ConstPool() {
        for (int i = 0; i <poolMaxSize; i++) {
            list.add("zhengzz" + i);
        }
    }

    public String get() {
        String res = null;
        try {
            semaphore.acquire();
            {
                lock.lock();
                {
                    while (list.size() == 0) {
                        condition.await();
                    }
                    res = list.remove(0);
                }
                lock.unlock();
            }
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void put(String str) {
        try {
            semaphore.acquire();
            {
                lock.lock();
                {
                    this.list.add(str);
                    condition.signalAll();
                }
                lock.unlock();
            }
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConstPool constPool = new ConstPool();
        for (int i=0; i<7; i++) {
            new Thread(new Service(constPool), "t" + i).start();
        }
    }

    static class Service implements Runnable{
        private ConstPool pool;
        private int count = 0;

        public Service(ConstPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            for (int i=0; i<100000; i++) {
                String getString = pool.get();
                System.out.println(Thread.currentThread().getName() + "获取到常量" + getString + " 累计:" + (++count));
                pool.put(getString);
            }
        }
    }


}

