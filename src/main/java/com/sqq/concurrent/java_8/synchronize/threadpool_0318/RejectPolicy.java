package com.sqq.concurrent.java_8.synchronize.threadpool_0318;

/**
 * @author sqq
 * @Date 2021/3/23
 * 拒绝策略
 */
@FunctionalInterface
public interface RejectPolicy<T> {

    /**
     * 当队列满时的处理方式
     * @param blockQueue
     * @param task
     */
    void reject(BlockQueue<T> blockQueue, T task);
}
