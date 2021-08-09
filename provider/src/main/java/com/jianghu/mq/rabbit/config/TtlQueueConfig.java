package com.jianghu.mq.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: TTL SpringBoot 配置类
 * @author: OF3848
 * @create: 2021-08-08 22:10
 */
@Configuration
public class TtlQueueConfig {
    // 死信交换机
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    // 死信队列
    public static final String DEAD_LETTER_QUEUE = "QD";

    @Bean("yExchange")
    public DirectExchange yExchange(){
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    @Bean("queueD")
    public Queue queueD(){
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    @Bean
    public Binding queueDBindingY(@Qualifier("queueD") Queue queueD,
                                  @Qualifier("yExchange") DirectExchange yExchange){
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");
    }

}
