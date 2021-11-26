package com.example.kafkaDemo.kafka.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @Author: Frost
 * @Date: 2021/11/12 23:28
 */

@Component("kafkaTest")
@Slf4j
public class AiHandler implements MessageHandler {

    @Override
    public void handle(String message) {
        System.out.println("数据接受测试，AI");
    }
}
