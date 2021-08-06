package com.sqq.concurrent.java_8.synchronize.cas_0205;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author sqq
 *  原子累加器
 */
@Slf4j(topic = "run.原子累加器")
public class TestDemo {
    public static void main(String[] args) {
        demo(
                () -> new AtomicLong(0),
                (adder) ->adder.getAndIncrement()
        );

        demo(
                () -> new LongAdder(),
                adder -> adder.increment()
        );

    }


    private static <T> void demo(Supplier<T> adderSupplier, Consumer<T> action){
        T adder = adderSupplier.get();
        long start = System.nanoTime();
        ArrayList<Thread> ts = new ArrayList<>();
        //4个线程，没人累加50万
        for(int i = 0; i < 4; i++){
            ts.add(new Thread(() -> {
                for (int j = 0; j < 500000; j++){
                    action.accept(adder);
                }
            },"t" + i));
        }
        ts.forEach( t -> t.start());
        ts.forEach(t ->{
            try{
//                log.debug("执行 {}", t);
                t.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        long end = System.nanoTime();
        System.out.println(adder + " cost: " + (end -start)/1000_000);
    }
}
