package com.jackxu.rocketmq.producer;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author jackxu
 */
@Component
public class MessageSender {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void syncSend() {
        /**
         * 发送可靠同步消息 ,可以拿到SendResult 返回数据
         * 同步发送是指消息发送出去后，会在收到mq发出响应之后才会发送下一个数据包的通讯方式。
         * 参数1： topic:tag
         * 参数2:  消息体 可以为一个对象
         * 参数3： 超时时间 毫秒
         */
        SendResult result = rocketMQTemplate.syncSend("springboot-topic:tag", "这是一条同步消息", 10000);
        System.out.println(result);
    }


    public void asyncSend() throws Exception {
        /**
         * 发送 可靠异步消息
         * 发送消息后，不等mq响应，接着发送下一个数据包。发送方通过设置回调接口接收服务器的响应，并可对响应结果进行处理。
         * 参数1： topic:tag
         * 参数2:  消息体 可以为一个对象
         * 参数3： 回调对象
         */
        rocketMQTemplate.asyncSend("springboot-topic:tag1", "这是一条异步消息", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("回调sendResult:" + sendResult);
            }

            @Override
            public void onException(Throwable e) {
                System.out.println(e.getMessage());
            }
        });
        TimeUnit.SECONDS.sleep(100000);
    }


    public void sendOneWay() {
        /**
         * 发送单向消息，特点为只负责发送消息，不等待服务器回应且没有回调函数触发，即只发送请求不等待应答。
         * 此方式发送消息的过程耗时非常短，一般在微秒级别。应用场景：适用于某些耗时非常短，但对可靠性要求并
         * 不高的场景，例如日志收集。
         * 参数1： topic:tag
         * 参数2:  消息体 可以为一个对象
         */
        rocketMQTemplate.sendOneWay("springboot-topic:tag1", "这是一条单向消息");
    }


    public void sendOneWayOrderly() {
        /**
         * 发送单向的顺序消息
         * 参数1： topic:tag
         * 参数2:  消息体 可以为一个对象
         */
        rocketMQTemplate.sendOneWayOrderly("springboot-topic:tag1", "这是一条顺序消息", "8888");
    }

}