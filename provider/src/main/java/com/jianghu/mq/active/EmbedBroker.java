package com.jianghu.mq.active;

import org.apache.activemq.broker.BrokerService;

/**
 * @description: 内嵌activeMQ
 * @author: OF3848
 * @create: 2021-08-17 11:04
 */
public class EmbedBroker {
    public static void main(String[] args) throws Exception {
        //创建一个迷你的ActiveMQ的实例
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        //创建此Broker的访问地址和端口
        brokerService.addConnector("tcp://localhost:61616");
        //启动broker服务
        brokerService.start();
        // 阻塞，否则直接关闭
        System.in.read();
    }
}
