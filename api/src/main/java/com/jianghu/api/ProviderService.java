package com.jianghu.api;

/**
 * @description: 定义dubbo接口
 * @author: OF3848
 * @create: 2021-05-11 16:37
 */
public interface ProviderService {

    /**
     * 定义一个dubbo方法
     * @param world
     * @return
     */
    String sayHello(String world);
}
