package com.jianghu.mq.rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

/**
 * @description: 死信生产者
 * @author: OF3848
 * @create: 2021-08-08 00:12
 */
public class Dlx {
    private final static String NORMAL_EXCHANGE_NAME = "normal_exchange_name";

    public static void main(String[] args) {
        try (Channel channel = RabbitMqUtils.getChannel()) {
            channel.exchangeDeclare(NORMAL_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            // 设置消息过期时间10S
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .expiration("10000").build();
            for (int i = 0; i < 10; i++) {
                String message = "Hello World!" + i;
                channel.basicPublish(NORMAL_EXCHANGE_NAME, "normal", null, message.getBytes("UTF-8"));
            }
            System.out.println("消息发送成功");

            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
