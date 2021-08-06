package com.sqq.concurrent.java_8.synchronize.test0127;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sqq
 * @Date 2021/2/1
 *
 * 方式1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaitNotify {

    /**
     * 等待标记
     */
    private int flag;

    /**
     * 循环次数
     */
    private int loopNumber;


    /**
     * 输出
     */
    public void print(String str, int waitFlag, int nextFlag){
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this){
                while (flag != waitFlag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(str);
                //继续
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}
