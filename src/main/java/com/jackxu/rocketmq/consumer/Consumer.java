package com.jackxu.rocketmq.consumer;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author jackxu
 */
@Component
@RocketMQMessageListener(topic = "springboot-topic", consumerGroup = "springboot-consumer-group",
        selectorExpression = "tag1", selectorType = SelectorType.TAG,
        messageModel = MessageModel.CLUSTERING, consumeMode = ConsumeMode.CONCURRENTLY)
public class Consumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        try {
            System.out.println("接收到rocketmq消息:" + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}