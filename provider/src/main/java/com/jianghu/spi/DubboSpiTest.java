package com.jianghu.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @description: Dubbo SPI 测试
 * @author: OF3848
 * @create: 2021-05-26 15:04
 */
public class DubboSpiTest {
    public static void main(String[] args) {
        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();
        Robot bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();
    }
}
