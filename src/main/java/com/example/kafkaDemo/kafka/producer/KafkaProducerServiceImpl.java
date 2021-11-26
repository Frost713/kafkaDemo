package com.example.kafkaDemo.kafka.producer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @Author: Frost
 * @Date: 2021/11/12 23:58
 */
@Service
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void send(String topic, Object object) {
        if (StringUtils.isBlank(topic)) {
            log.info("topic is null");
            return;
        }
        kafkaTemplate.send(topic, JSON.toJSONString(object, SerializerFeature.WriteNullStringAsEmpty)).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("发送消息失败：{}", ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("发送消息成功：{}-{}-{}", result.getRecordMetadata().topic(), result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            }
        });
    }
}
