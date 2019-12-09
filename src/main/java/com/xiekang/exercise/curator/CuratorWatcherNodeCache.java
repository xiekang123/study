package com.xiekang.exercise.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
* @Description:  NodeCache：监听节点的新增、修改操作。
* @Author:  xiekang
* @CreateDate: 2019/12/4 16:15
*/
public class CuratorWatcherNodeCache {

    private static String CONNECT_ADDR = "192.168.0.41:2181,192.168.0.42:2181,192.168.0.75:2181";
    private static final int SESSION_TIMEOUT = 5000;

    public static void main(String[] args) throws Exception{
        //重试策略，初试时间1秒，重试10次
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        //通过工厂创建Curator
        CuratorFramework curator = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_TIMEOUT).retryPolicy(policy).build();
        //开启连接
        curator.start();

        //最后一个参数表示是否进行压缩
        NodeCache nodeCache = new NodeCache(curator, "/xiekang", false);
        nodeCache.start(true);
        //只会监听节点的创建和修改，删除不会监听
        nodeCache.getListenable().addListener(()->{
            System.out.println("路径：" + nodeCache.getCurrentData().getPath());
            System.out.println("数据：" + new String(nodeCache.getCurrentData().getData()));
            System.out.println("状态：" + nodeCache.getCurrentData().getStat());
        });

        curator.create().forPath("/xiekang", "1234".getBytes());
        Thread.sleep(1000);
        curator.setData().forPath("/xiekang", "5678".getBytes());
        Thread.sleep(1000);
        curator.delete().forPath("/xiekang");
        Thread.sleep(50000);
        curator.close();
    }
}
