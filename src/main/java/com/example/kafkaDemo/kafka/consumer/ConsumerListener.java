package com.example.kafkaDemo.kafka.consumer;

import com.example.kafkaDemo.kafka.handler.HandlerContext;
import com.example.kafkaDemo.kafka.handler.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 公共kafka消费者监听器，使用策略模式来进行不同场景的消息处理
 *
 * @Author: Frost
 * @Date: 2021/11/12 23:23
 */
@Slf4j
@Component
public class ConsumerListener {

    @Resource
    private HandlerContext handlerContext;

    @KafkaListener(topics = "#{'${kafka.listener.topics}'.split(',')}", groupId = "${kafka.listener.group-id}")
    public void listen(ConsumerRecord<?, ?> record) {
        log.info("监听kafka消息,topic={},partition={},offset={}", record.topic(), record.partition(), record.offset());
        String topic = record.topic();
        try {
            MessageHandler handler = handlerContext.getHandler(topic);
            String message = String.valueOf(record.value());
            handler.handle(message);
        } catch (Exception e) {
            log.error("该topic消息策略不存在");
        }


    }
}

