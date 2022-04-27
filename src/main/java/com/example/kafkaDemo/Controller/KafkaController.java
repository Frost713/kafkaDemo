package com.example.kafkaDemo.Controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.kafkaDemo.kafka.producer.KafkaProducerService;
import com.example.kafkaDemo.kafka.utils.KafkaUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Frost
 * @date 2021/11/15 10:18
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Resource
    private KafkaUtils kafkaUtils;


    @Resource
    private KafkaProducerService kafkaProducerService;

    @GetMapping(value = "/test")
    public ResponseEntity test(Integer orderItemId, Integer orderId, Integer productId) {
        List<String> allTopic = kafkaUtils.getAllTopic();
        return ResponseEntity.ok(allTopic);
    }

    @PostMapping(value = "/producer")
    public ResponseEntity kafkaProducer(@RequestParam("topic") String topic,@RequestParam("message") String message) {
        JSONObject jsonObject = JSON.parseObject(message);
        kafkaProducerService.send(topic, jsonObject);
        return ResponseEntity.ok(true);
    }
}
