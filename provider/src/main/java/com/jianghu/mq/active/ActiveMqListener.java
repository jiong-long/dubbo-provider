package com.jianghu.mq.active;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @description:
 * @author: OF3848
 * @create: 2021-08-17 23:21
 */
@Component
public class ActiveMqListener {

    @JmsListener(destination = "queue-name")
    public void receiveMsg(TextMessage textMessage) throws JMSException {
        String text = textMessage.getText();
        System.out.println(text);
    }

    @JmsListener(destination = "topic-name", containerFactory="jmsListenerContainerTopic")
    public void receiveMsg1(TextMessage textMessage) throws JMSException {
        String text = textMessage.getText();
        System.out.println("JmsListener1---" + text);
    }

    @JmsListener(destination = "topic-name", containerFactory="jmsListenerContainerTopic")
    public void receiveMsg2(TextMessage textMessage) throws JMSException {
        String text = textMessage.getText();
        System.out.println("JmsListener2---" + text);
    }
}
