package com.jianghu.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @description: 入门
 * @author: OF3848
 * @create: 2021-08-23 23:56
 */
public class HelloWorldTest {

    private static final String ZK_UEL = "127.0.0.1:2181";

    private ZooKeeper zooKeeper;

    @Before
    public void init() throws IOException {
        zooKeeper = new ZooKeeper(ZK_UEL, 2000, event -> {
        });
    }

    @Test
    public void create() throws KeeperException, InterruptedException {
        // 创建节点（如果不用path接收，则节点创建不成功）
        String path = zooKeeper.create("/path", "你好".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/", false);
        for (String child : children) {
            System.out.println(child);
        }
    }

    @Test
    public void exist() throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists("/path", false);
        System.out.println(exists != null);
    }
}
