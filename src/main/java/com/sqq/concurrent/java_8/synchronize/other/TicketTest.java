package com.sqq.concurrent.java_8.synchronize.other;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @author sqq
 */
@Slf4j(topic = "run.test")
public class TicketTest {

    private int count;

    public TicketTest(int count) {
        this.count = count;
    }

    /**
     * 剩余票
     */
    public int getCount() {
        return count;
    }

    /**
     * 卖票
     */
    public int sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        TicketTest ticket = new TicketTest(2000);
        System.out.println(randomAmount());

        List<Thread> list = new ArrayList<>();
        // 用来存储买出去多少张票
        List<Integer> sellCount = new Vector<>();
        for (int i = 0; i < 4000; i++) {
            Thread thread = new Thread(() -> {
                int count = ticket.sell(randomAmount());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sellCount.add(count);
            });
            list.add(thread);
            thread.start();
        }

        list.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 买出去的票求和
        log.debug("selled count:{}", sellCount.stream().mapToInt(c -> c).sum());
        // 剩余票数
        log.debug("remainder count:{}", ticket.getCount());

    }

    /**
     * 线程安全
     */
    static Random random = new Random();

    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }


}
