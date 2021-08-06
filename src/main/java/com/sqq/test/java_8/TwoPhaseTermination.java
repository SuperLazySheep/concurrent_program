package com.sqq.test.java_8;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sqq
 * 两阶段终止模式   sleep 和 interrupt
 */
@Slf4j(topic = "run.test")
@Data
public class TwoPhaseTermination {
    private Thread currentThread;

    /**
     * 启动监听
     */
    public void start() {
        currentThread = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                //是否被打断
                if (current.isInterrupted()) {
                    log.debug("全部终止");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.debug("执行监控记录 ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    current.interrupt();
                }
            }
        }, "monitor");
        currentThread.start();
    }

    /**
     * 停止监听
     */
    public void stop() {
        currentThread.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination termination = new TwoPhaseTermination();
        termination.start();
        Thread.sleep(3500);
        termination.stop();
//        System.arraycopy();
    }
}
