package com.dome.springboot.curator.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceInstanceBuilder;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DomeZookeeperConf {
    private Log log = LogFactory.getLog(this.getClass());
    @Value("${zk.url}")
    private String  zkUrl;


    private boolean isLeader = false;

    @Bean
    public CuratorFramework getCuratorFramework() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkUrl, retryPolicy);
        client.getCuratorListenable().addListener(new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework curatorFramework, CuratorEvent event) throws Exception {
                WatchedEvent we = event.getWatchedEvent();
                String path = we.getPath();
                if(path == null){
                    return;
                }
                Watcher.Event.EventType type = we.getType();

                log.info("zookeeper 变化 "+type+":"+we.getPath());


                Stat stat = client.checkExists().watched().forPath(we.getPath());
                log.info("zookeeper stat :"+stat);

            }
        });
//      Curator 进行领导人选举
//         LeaderSelectorListenerAdapter listener = new
//                LeaderSelectorListenerAdapter() {
//                    public void takeLeadership(CuratorFramework client) throws Exception
//                    {
//                        log.info("get leadership");
//                        isLeader = true;
//                        //或者进行其他操作
//                    }
//                };
//
//        LeaderSelector selector = new LeaderSelector(client, "/schedule",
//                listener);
//        selector.autoRequeue();
//        selector.start();


        client.start();
        registerSerivce(client);

        ServiceInstance<Map> service = findService(client, "book");



        return client;
    }


    protected void registerSerivce(CuratorFramework client) throws Exception {

        // 构造一个服务描述
        ServiceInstanceBuilder<Map> service = ServiceInstance.builder();
        service.address("192.168.1.100");
        service.port(8080);
        service.name("book");
        Map config = new HashMap();
        config.put("url", "/api/v3/book");
        service.payload(config);

        ServiceInstance<Map> instance = service.build();

        ServiceDiscovery<Map> serviceDiscovery = ServiceDiscoveryBuilder.builder(Map.class).client(client)
                .serializer(new JsonInstanceSerializer<Map>(Map.class)).basePath("/service").build();
        // 服务注册
        serviceDiscovery.registerService(instance);

        System.out.println("ZooKeeoper服务注册成功");
        serviceDiscovery.start();

    }

    protected ServiceInstance<Map> findService(CuratorFramework client, String serviceName) throws Exception {

        ServiceDiscovery<Map> serviceDiscovery = ServiceDiscoveryBuilder.builder(Map.class).client(client)
                .serializer(new JsonInstanceSerializer<Map>(Map.class)).basePath("/service").build();

        serviceDiscovery.start();

        Collection<ServiceInstance<Map>> all = serviceDiscovery.queryForInstances(serviceName);
        if (all.size() == 0) {
            return null;
        } else {
            // 取第一个服务
            ServiceInstance<Map> service = new ArrayList<ServiceInstance<Map>>(all).get(0);
            System.out.println("ZooKeeper的服務地址："+service.getAddress());
            System.out.println("ZooKeeper的服務端口："+service.getPayload());
            return service;

        }

    }
}

