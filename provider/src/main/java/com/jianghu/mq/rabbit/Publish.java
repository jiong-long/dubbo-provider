package com.jianghu.mq.rabbit;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

/**
 * @description: 发布订阅模式---发布者
 * @author: OF3848
 * @create: 2021-08-06 14:50
 */
public class Publish {

    private final static String EXCHANGE_NAME = "exchange_name";

    public static void main(String[] args) {
        try (Channel channel = RabbitMqUtils.getChannel()) {
            // 声明交换机（分发:发布/订阅模式）
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
            for (int i = 0; i < 10; i++) {
                String message = "Hello World!" + i;
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            }
            System.out.println("消息发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
