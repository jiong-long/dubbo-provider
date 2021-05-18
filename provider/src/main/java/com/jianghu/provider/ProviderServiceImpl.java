package com.jianghu.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.jianghu.api.ProviderService;

/**
 * @description: dubbo实现类
 * @author: OF3848
 * @create: 2021-05-11 16:39
 */
@Service
public class ProviderServiceImpl implements ProviderService {

    /**
     * 定义一个dubbo方法
     *
     * @param world
     * @return
     */
    @Override
    public String sayHello(String world) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("服务被调用：" + world);
        return world;
    }
}
