package com.sqq.concurrent.java_8.synchronize.threadpool_0318;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * @author sqq
 * @Date 2021/3/18
 * 模拟线程池
 */
@Slf4j(topic = "run.ThreadPool")
public class ThreadPool {

    /**
     * 任务队列
     */
    private BlockQueue<Runnable> taskQueue;

    /**
     * 线程集合
     */
    private HashSet<Work> works = new HashSet<>();

    /**
     * 核心线程数
     */
    private int coreSize;

    /**
     *  超时时间
     */
    private long timeout;

    private TimeUnit timeUnit;

    /**
     * 拒绝策略
     */
    private RejectPolicy rejectPolicy;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapCity, RejectPolicy rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockQueue<>(queueCapCity);
        this.rejectPolicy = rejectPolicy;
    }

    /**
     *  执行任务
     * @param task
     */
    public void execute(Runnable task){
        //当任务数没有超过coreSize, 交给work去执行
        //当超过coreSize, 任务放进堵塞队列
        synchronized (works){
            if(works.size() < coreSize){
                Work worker = new Work(task);
                log.debug("新增work... {} task...{}", worker, task);
                works.add(worker);
                worker.start();
            }else{
                taskQueue.tryPut(rejectPolicy,task);
                // 1.死等
                // 2.带超时间的等待
                // 3.让调用者放弃任务执行
                // 4.让调用者抛出异常
                // 5.让调用者自己去执行任务
            }
        }
    }

    class Work extends Thread{
        private Runnable task;

        public Work(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行 task不为空, 并且当堵塞队列里面任务执行完成
//            while (task != null || (task = taskQueue.take()) != null){
            // 超时获取 退出while循环, 移除任务
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null){
                try{
                    log.debug("正在执行...{}", task);
                    task.run();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    task = null;
                }
            }

            // 当执行完成 移除work
            synchronized (works){
                log.debug("移除任务... {}", this);
                works.remove(this);
            }
        }
    }

}
