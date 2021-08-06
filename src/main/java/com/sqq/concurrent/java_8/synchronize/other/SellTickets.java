package com.sqq.concurrent.java_8.synchronize.other;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author sqq
 * @Date 2021/1/7
 */
@Slf4j(topic = "run.test---")
public class SellTickets {


    //线程t1,线程t2
    public static void main(String[] args) {
        GuardedObject object = new GuardedObject();

        new Thread(() -> {
            log.debug("begin");
            Object response = object.get(2000);
            log.debug("结果是{}", response);
        }, "t1").start();

        new Thread(() -> {
            log.debug("begin");
            try {
                TimeUnit.SECONDS.sleep(3);
                object.complete(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}

/**
 * 增强超时效果
 */
class GuardedObject {

    /**
     * 结果
     */
    private Object response;

    /**
     * @param timeout 等待时间
     * @return
     */
    public Object   get(long timeout) {
        synchronized (this) {
            long begin = System.currentTimeMillis();
            long passTime = 0;
            //没有结果
            while (response == null) {
                long waitTime = timeout - passTime;
                if (waitTime <= 0) {
                    break;
                }
                try {
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 每次等待的时间
                passTime = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    /**
     * 产生结果
     */
    public void complete(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}