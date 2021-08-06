package com.sqq.concurrent.java_8.synchronize.threadpool_0318;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author sqq
 * @Date 2021/4/1
 * 1.7 之后 新的线程池 fork/join
 */
public class ForkJoinTest {

    public static void main(String[] args) {
        ForkJoinPool joinPool = new ForkJoinPool(4);
        System.out.println(joinPool.invoke(new MyTask(5)));
    }
}
@Slf4j(topic = "run.MyTask")
    class MyTask extends RecursiveTask<Integer> {

        private int n;

        public MyTask(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            // 终止条件
            if(n == 1){
                log.debug("join()  {}", n);
                return n;
            }

            MyTask myTask = new MyTask(n - 1);

            //让一个线程去执行次任务
            myTask.fork();
            log.debug("fork()   {}  +  {}", n,myTask);

            // 拆分运行
            return n + myTask.join();
        }
    }
