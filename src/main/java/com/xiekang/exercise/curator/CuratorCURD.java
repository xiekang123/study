package com.xiekang.exercise.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* @Description:
 *
 * 参考博客：https://blog.csdn.net/haoyuyang/article/details/53469269
 *
 *1.使用CuratorFrameworkFactory的两个静态工厂方法（参数不同）来创建zookeeper客户端对象。
 * 参数1：connectString，zookeeper服务器地址及端口号，多个zookeeper服务器地址以“,”分隔。
 * 参数2：sessionTimeoutMs，会话超时时间，单位毫秒，默认为60000ms。
 * 参数3：connectionTimeoutMs，连接超时时间，单位毫秒，默认为15000ms。
 * 参数4：retryPolicy，重试连接策略，有四种实现，
 * 分别为：ExponentialBackoffRetry（重试指定的次数, 且每一次重试之间停顿的时间逐渐增加）、
 * RetryNtimes（指定最大重试次数的重试策略）、
 * RetryOneTimes（仅重试一次）、
 * RetryUntilElapsed（一直重试直到达到规定的时间）
* @Author:  xiekang
* @CreateDate: 2019/12/4 14:01
*/
public class CuratorCURD {

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

        ExecutorService executor = Executors.newCachedThreadPool();

        /**创建节点，creatingParentsIfNeeded()方法的意思是如果父节点不存在，则在创建节点的同时创建父节点；
         * withMode()方法指定创建的节点类型，跟原生的Zookeeper API一样，不设置默认为PERSISTENT类型。
         * */

        /**
         *  create创建节点方法可选的链式项：creatingParentsIfNeeded（是否同时创建父节点）、
         *  withMode（创建的节点类型）、forPath（创建的节点路径）、withACL（安全项）
         *
         *  inBackground绑定异步回调方法。比如在创建节点时绑定一个回调方法，
         *  该回调方法可以输出服务器的状态码以及服务器的事件类型等信息，还可以加入一个线程池进行优化操作。
         */
        curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .inBackground((framework, event) -> { //添加回调
                    System.out.println("Code：" + event.getResultCode());
                    System.out.println("Type：" + event.getType());
                    System.out.println("Path：" + event.getPath());
                }, executor).forPath("/test/test-01", "xiekang".getBytes());
        Thread.sleep(5000); //为了能够看到回调信息
        /**
         * 获取节点数据
         */
        String data = new String(curator.getData().forPath("/test/test-01"));
        System.out.println(data);
        /**
         * 判断指定节点是否存在
         */
        Stat stat = curator.checkExists().forPath("/test/test-01");
        System.out.println(stat);
        /**
         *更新节点数据
         */
        curator.setData().forPath("/test/test-01", "c1新内容".getBytes());
        data = new String(curator.getData().forPath("/test/test-01"));
        System.out.println(data);
        /**
         *获取子节点
         */
        List<String> children = curator.getChildren().forPath("/test");
        for(String child : children) {
            System.out.println(child);
        }
        /**
         *放心的删除节点，deletingChildrenIfNeeded()方法表示如果存在子节点的话，同时删除子节点
         *
         * delete删除节点方法可选的链式项：deletingChildrenIfNeeded（是否同时删除子节点）、
         * guaranteed（安全删除）、withVersion（版本检查）、forPath（删除的节点路径）
         */
        curator.delete().guaranteed().deletingChildrenIfNeeded().forPath("/test");
        curator.close();
    }
}
