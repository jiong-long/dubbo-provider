package com.jianghu.mq.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

/**
 * @description: 发布确认
 * @author: OF3848
 * @create: 2021-08-09 22:53
 */
@Component
public class ConfirmQueueListener {

    //@RabbitListener(queues = ConfirmQueueConfig.CONFIRM_QUEUE_NAME)
    public void receiveConfirmQueue(Message message){
        String msg = new String(message.getBody());
        System.out.println("正常交换机：" + msg);
    }
}
