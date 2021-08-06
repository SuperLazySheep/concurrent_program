package com.sqq.concurrent.java_8.synchronize.cas_0205;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sqq
 * @Date 2021/2/5
 *
 * 无锁方式-- 采用juc提供的原子性引用对象 -- 底层采用cas算法方式
 */
@Data
public class Account {

    private AtomicInteger balance;

    public Account(Integer balance){
        this.balance = new AtomicInteger(balance);
    }

    public Integer getBalance(){
        return balance.get();
    }

    public void  withDraw(Integer amount){
     /*   while(true){
            int pre = this.getBalance();
            int next = pre - amount;
            if(balance.compareAndSet(pre,next)){
                break;
            }
        }*/
        balance.getAndAdd( -1 * amount);
    }
}
