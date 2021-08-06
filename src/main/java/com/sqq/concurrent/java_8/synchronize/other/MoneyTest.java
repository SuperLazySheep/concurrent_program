package com.sqq.concurrent.java_8.synchronize.other;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author sqq
 * @Date 2020/12/29
 * 转账
 */
@Slf4j(topic = "run.test")
public class MoneyTest {
    private int money;

    public MoneyTest(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * 转账
     */
    public void transfer(MoneyTest target, int amount){
        synchronized (MoneyTest.class) {
            if (this.money >= amount) {
                this.setMoney(this.money - amount);
                target.setMoney(target.getMoney() + amount);
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        MoneyTest a = new MoneyTest(1000);
        MoneyTest b = new MoneyTest(1000);
        Thread aa = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a.transfer(b, randomGet());
            }
        });

        Thread bb = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                b.transfer(a, randomGet());
            }
        });
        aa.start();
        bb.start();
        aa.join();
        bb.join();
        // 查看转账2000次后的总金额
        log.debug("total:{}",(a.getMoney() + b.getMoney()));

    }

    public static int randomGet(){
        Random random = new Random();
        return random.nextInt(100) + 1;
    }
}
