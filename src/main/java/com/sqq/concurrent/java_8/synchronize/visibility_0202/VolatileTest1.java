package com.sqq.concurrent.java_8.synchronize.visibility_0202;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sqq
 * @Date 2021/2/2
 *
 * 设计模式 - 犹豫模式 - 监控线程
 */

public class VolatileTest1 {
    static ThreadLocal threadLocal = new ThreadLocal<Object>();


    public static void main(String[] args) throws InterruptedException {
        TwoPhase twoPhase = new TwoPhase();
        twoPhase.start();
        Thread.sleep(2000);
        twoPhase.stop();
    }
}
@Slf4j(topic = "run.test")
class TwoPhase{
    private Thread thread;
    private volatile boolean flag = false;

    private boolean starting = false;

    public void start(){

        synchronized (this){
            if(starting){
                return;
            }
            starting = true;
        }

        new Thread(() ->{
            while(true){
                 thread = Thread.currentThread();
                if(flag){
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.debug("执行操作");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"监控线程").start();
    }

    public void stop(){
        flag = true;
        thread.interrupt();
    }
}
