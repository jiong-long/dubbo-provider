如需启动SpringBoot进行测试时，请按下列步骤操作

####1、RabbitMQ 
放开 com.jianghu.mq.rabbit.config 目录下的 @Configuration 注解
放开所有 @RabbitListener 注解

####2、Dubbo
放开 @EnableDubbo 注解