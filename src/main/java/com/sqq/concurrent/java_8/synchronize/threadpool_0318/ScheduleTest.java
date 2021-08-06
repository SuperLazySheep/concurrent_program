package com.sqq.concurrent.java_8.synchronize.threadpool_0318;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author sqq
 * @Date 2021/3/31
 */
@Slf4j(topic = "run.ScheduleTest")
public class ScheduleTest {

    public static void main(String[] args) {

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 获取下周四的时间
        LocalDateTime time = now.withHour(18).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.THURSDAY);

        if (now.compareTo(time) > 0) {
            time = time.plusWeeks(1);
        }

        // 间隔时间开启
        long initialDelay = Duration.between(now, time).toMillis();
        // 任务间隔时间
        long period = 1000 * 60 * 60 * 24 * 7;

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            log.debug("run..");
        }, initialDelay, period, TimeUnit.MILLISECONDS);
    }
}
