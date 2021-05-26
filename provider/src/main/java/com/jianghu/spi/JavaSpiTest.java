package com.jianghu.spi;

import java.util.ServiceLoader;

/**
 * @description: Java SPI 测试
 * @author: OF3848
 * @create: 2021-05-26 15:03
 */
public class JavaSpiTest {
    public static void main(String[] args) {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }
}
