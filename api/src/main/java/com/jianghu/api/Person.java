package com.jianghu.api;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @description:
 * @author: WangJinLong
 * @create: 2023-07-18 14:33
 */
public class Person implements Serializable {
    private String name;

    private ZonedDateTime birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(ZonedDateTime birthday) {
        this.birthday = birthday;
    }
}
