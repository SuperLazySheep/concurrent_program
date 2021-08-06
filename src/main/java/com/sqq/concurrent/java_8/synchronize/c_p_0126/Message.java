package com.sqq.concurrent.java_8.synchronize.c_p_0126;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sqq
 * @Date 2021/1/26
 */
@Data
@Slf4j(topic = "run.message")
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private int id;
    private String message;
}
