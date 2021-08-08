package com.jianghu.mq.rabbit;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

/**
 * @description: topic 生产者
 * @author: OF3848
 * @create: 2021-08-06 23:13
 */
public class Topic {
    private final static String EXCHANGE_NAME = "topic_name";

    public static void main(String[] args) {
        try (Channel channel = RabbitMqUtils.getChannel()) {
            // 声明交换机（分发:发布/订阅模式）
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            for (int i = 0; i < 1; i++) {
                String message = "Hello World!" + i;
                channel.basicPublish(EXCHANGE_NAME, "*.home", null, message.getBytes("UTF-8"));
            }
            System.out.println("消息发送成功");

            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
