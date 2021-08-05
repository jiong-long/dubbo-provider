package com.jianghu.mq.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description: rabbitMQ 生产者
 * @author: OF3848
 * @create: 2021-08-05 18:47
 */
public class Producer {

    private final static String QUEUE_NAME = "queue_name";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 获得信道
        Channel channel = RabbitMqUtils.getChannel();
        // 开启发布确认
        channel.confirmSelect();

        // 异步确认消息
        ConfirmCallback ackCallback = (deliveryTag, multiple) -> {
            System.out.println("消息发送成功" + deliveryTag);
        };

        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
            System.out.println("消息发送失败" + deliveryTag);
        };
        channel.addConfirmListener(ackCallback, nackCallback);

        // 声明交换器 durable队列持久化
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        for (int i = 0; i < 10; i++) {
            String message = "Hello World!" + i;
            // 异步消息确认的 deliveryTag = channel.getNextPublishSeqNo();
            // 消息持久化 MessageProperties.PERSISTENT_TEXT_PLAIN
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            // 单个发布确认，循环体外就是批量发布确认
            // boolean confirms = channel.waitForConfirms();
        }
        System.out.println("消息发送成功");
    }
}
