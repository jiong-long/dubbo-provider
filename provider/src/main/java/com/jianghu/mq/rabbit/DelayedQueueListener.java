package com.jianghu.mq.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

/**
 * @description: 基于插件的延迟队列
 * @author: OF3848
 * @create: 2021-08-09 00:01
 */
@Component
public class DelayedQueueListener {

    //@RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE_NAME)
    public void receiveDelayQueue(Message message){
        String msg = new String(message.getBody());
        System.out.println(msg);
    }
}
