package com.jianghu.mq.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

/**
 * @description: 发布确认
 * @author: OF3848
 * @create: 2021-08-09 22:53
 */
@Component
public class WarningQueueListener {

    //@RabbitListener(queues = ConfirmQueueConfig.WARNING_QUEUE_NAME)
    public void receiveWarningQueue(Message message){
        String msg = new String(message.getBody());
        System.out.println("警告交换机：" + msg);
    }
}
