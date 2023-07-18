package com.jianghu.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.jianghu.api.Person;
import com.jianghu.api.ProviderService;

import java.time.ZonedDateTime;

/**
 * @description: dubbo实现类
 * @author: OF3848
 * @create: 2021-05-11 16:39
 */
@Service(version = "2.0.0")
public class ProviderServiceImpl2 implements ProviderService {

    /**
     * 定义一个dubbo方法
     *
     * @param world
     * @return
     */
    @Override
    public String sayHello(String world) {
        System.out.println("服务被调用2：" + world);
        return world;
    }

    @Override
    public ZonedDateTime sayTime(ZonedDateTime time) {
        return time;
    }

    @Override
    public Person sayTime(Person person) {
        person.setName("李四");
        return person;
    }
}
