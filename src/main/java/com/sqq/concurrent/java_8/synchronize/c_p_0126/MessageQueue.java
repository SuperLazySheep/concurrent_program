package com.sqq.concurrent.java_8.synchronize.c_p_0126;

import cn.hutool.http.HttpUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.locks.LockSupport;

/**
 * @author sqq
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j(topic = "run.queue")
public class MessageQueue {

    private LinkedList<Message> queue;

    private int capacity;

    public MessageQueue(int capacity){
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    /**
     * 消费
     * @return
     */
    public Message task() {
        synchronized (queue) {
            while (queue.isEmpty()) {
                try {
                    log.debug("没货了 ");
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }
    }

    /**
     * 生产
     */
    public void put (Message message){
        synchronized (queue){
            while (queue.size() == capacity){
                try {
                    log.debug("库存上限了");
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add(message);
            queue.notifyAll();
        }
    }


    /**
     *测试
     */
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(2);

        //生产
        for(int i = 1; i<=4; i++ ){
            int id= i;
            new Thread(() ->{
                messageQueue.put(new Message(id,"肯德基"));
                log.debug("生鸡了  {}",id);
            },"生产者" + i).start();
        }

        //消费
       new Thread(()->{
           while(true){
               Message task = messageQueue.task();
               log.debug("消费了鸡");
           }
       },"消费者").start();
    }


}
