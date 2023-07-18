package com.jianghu.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.jianghu.api.ProviderService;

import java.time.ZonedDateTime;

/**
 * @description: dubbo实现类
 * @author: OF3848
 * @create: 2021-05-11 16:39
 *
 * version：默认为""，消费者调用也是""
 * stub：本地存根，提供者项目消费者那边执行的方法
 */
@Service(version = "1.0.0", stub = "true")
public class ProviderServiceImpl implements ProviderService {

    /**
     * 定义一个dubbo方法
     *
     * @param world
     * @return
     */
    @Override
    public String sayHello(String world) {
        System.out.println("服务被调用：" + world);
        return world;
    }

    @Override
    public ZonedDateTime sayTime(ZonedDateTime time) {
        return time;
    }
}
