package com.sqq.concurrent.java_8.synchronize.threadpool_0318;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author sqq
 * @Date 2021/3/20
 */
@Slf4j(topic = "run.TestPool")
public class TestPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1, 1000, TimeUnit.MILLISECONDS, 1, (queue, task) ->{
            // 死等
//            queue.put(task);
            //超时等待
//            queue.offer(task, 500,TimeUnit.MILLISECONDS);
            // 放弃
//            log.debug("放弃 {}", task);
            // 抛出异常,第三个抛出异常，第四个就不会执行了
//            throw new RuntimeException("执行任务失败" + task);
            // 让调用者自己执行任务
//            task.run();
        });
        for (int i = 1; i <= 3; i++){
            int j = i;
            threadPool.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("...{}...", j);
            });
        }
    }
}
