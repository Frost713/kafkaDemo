package com.example.kafkaDemo.kafka.producer;

/**
 * @Author: Frost
 * @Date: 2021/11/12 23:57
 */
public interface KafkaProducerService {

    void send(String topic, Object object);
}

