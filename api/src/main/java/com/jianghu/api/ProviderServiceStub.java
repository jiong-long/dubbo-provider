package com.jianghu.api;

import java.time.ZonedDateTime;

/**
 * @description: 本地存根，提供方在客户端执行部分逻辑
 * @author: OF3848
 * @create: 2021-05-11 16:37
 */
public class ProviderServiceStub implements ProviderService{

    private final ProviderService providerService;

    public ProviderServiceStub(ProviderService providerService){
        this.providerService = providerService;
    }

    /**
     * 定义一个dubbo方法
     *
     * @param world
     * @return
     */
    @Override
    public String sayHello(String world) {
        if("".equals(world)){
            return "参数不能为空";
        }
        return providerService.sayHello(world);
    }

    @Override
    public ZonedDateTime sayTime(ZonedDateTime time) {
        return null;
    }
}
