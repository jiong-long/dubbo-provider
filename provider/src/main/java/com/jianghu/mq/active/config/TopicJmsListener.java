package com.jianghu.mq.active.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * @description:
 * @author: OF3848
 * @create: 2021-08-17 23:41
 */
//@Configuration
public class TopicJmsListener {

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        // 设置为Topic模式
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }
}
