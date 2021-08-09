package com.jianghu.mq.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description: ttl消费者，监听消息
 * @author: OF3848
 * @create: 2021-08-08 22:45
 */
@Component
public class TtlListener {

    @RabbitListener(queues = "QD")
    public void receiveD(Message message){
        String msg = new String(message.getBody());
        System.out.println(msg);
    }
}
