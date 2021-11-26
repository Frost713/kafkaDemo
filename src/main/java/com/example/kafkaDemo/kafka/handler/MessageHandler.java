package com.example.kafkaDemo.kafka.handler;

/**
 * @Author: Frost
 * @Date: 2021/11/12 23:18
 */
public interface MessageHandler {

    void handle(String message);
}
