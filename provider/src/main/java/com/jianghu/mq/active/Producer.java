package com.jianghu.mq.active;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @description: activeMq HelloWorld
 * @author: OF3848
 * @create: 2021-08-15 22:24
 */
public class Producer {

    /**
     * MQ地址
     */
    private static final String BROKER_URL = "tcp://127.0.0.1:61616";

    /**
     * 队列名称
     */
    private static final String QUEUE_NAME = "queue_01";

    public static void main(String[] args) throws JMSException {
        // 1、创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        // 2、获取连接
        Connection connection = connectionFactory.createConnection();
        // 3、启动连接
        connection.start();
        // 4、创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 5、创建队列
        Queue queue = session.createQueue(QUEUE_NAME);
        // 6、创建生产者
        MessageProducer producer = session.createProducer(queue);
        for (int i = 0; i < 3; i++) {
            // 7、创建消息
            TextMessage textMessage = session.createTextMessage("HelloWorld!!!" + i);
            // 8、发送消息
            producer.send(textMessage);
        }
        // 9、关闭资源
        producer.close();
        session.close();
        connection.close();
    }
}
