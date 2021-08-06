package com.sqq.concurrent.java_8.synchronize.test0127;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sqq
 * @Date 2021/2/2
 *
 * 使用
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LockTest extends ReentrantLock {

    private int loopNUmber;

    public void print(String str, Condition current, Condition nextCon){
        for (int i = 0; i <loopNUmber ; i++) {
           lock();
            try{
                current.await();
                System.out.print(str);
                nextCon.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }
        }
    }
}
