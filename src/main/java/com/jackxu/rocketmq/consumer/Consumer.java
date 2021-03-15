package com.jackxu.rocketmq.consumer;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;

/**
 * @author jackxu
 */
@RocketMQMessageListener(topic = "jackxu-springboot-topic", consumerGroup = "springboot-consumer-group",
        selectorExpression = "tagA", selectorType = SelectorType.TAG,
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