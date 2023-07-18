package com.jianghu.api;

import java.time.ZonedDateTime;

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

    /**
     * 日期序列化和反序列化
     * @param time
     * @return
     */
    ZonedDateTime sayTime(ZonedDateTime time);
}
