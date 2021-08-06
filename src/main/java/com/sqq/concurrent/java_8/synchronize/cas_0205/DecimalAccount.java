package com.sqq.concurrent.java_8.synchronize.cas_0205;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author sqq
 *
 *
 */
public class DecimalAccount {
    private AtomicReference<BigDecimal> balance;


    public DecimalAccount(BigDecimal bigDecimal){
        this.balance = new AtomicReference<>(bigDecimal);
    }

    public BigDecimal get(){
        return balance.get();
    }

    public void withDraw(BigDecimal account){
        while(true){
            BigDecimal prev = this.get();
            BigDecimal next = prev.subtract(account);
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}
