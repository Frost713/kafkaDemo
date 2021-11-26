package com.example.kafkaDemo.kafka.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Frost
 * @Date: 2021/11/12 23:24
 */
@Component
public class HandlerContext {

    @Autowired
    public final Map<String, MessageHandler> map = new ConcurrentHashMap<>();

    /**
     * 放入对应的策略
     *
     * @param map {key: topicName value：MessageHandler}
     */
    public HandlerContext(Map<String, MessageHandler> map) {
        this.map.clear();
        map.forEach(this.map::put);
    }


    /**
     * 不同的topic进行MessageHandler的策略获取，通过公共kafka 监听器来触发不同的handler
     *
     * @param handler
     * @return
     */
    public MessageHandler getHandler(String handler) {
        MessageHandler messageHandler = map.get(handler);
        if (messageHandler == null) {
            throw new RuntimeException();
        }
        return messageHandler;
    }
}

