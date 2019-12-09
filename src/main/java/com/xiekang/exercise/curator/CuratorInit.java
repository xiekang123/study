package com.xiekang.exercise.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorInit {
    private static String CONNECT_ADDR = "192.168.0.41:2181,192.168.0.42:2181,192.168.0.75:2181";
    private static final int SESSION_TIMEOUT = 5000;

    public static CuratorFramework connect(){
        //重试策略，初试时间1秒，重试10次
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        //通过工厂创建Curator
        CuratorFramework curator = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_TIMEOUT).retryPolicy(policy).build();
        //开启连接
        curator.start();
        return curator;
    }
}
