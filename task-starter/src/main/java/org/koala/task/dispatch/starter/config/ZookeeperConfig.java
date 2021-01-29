package org.koala.task.dispatch.starter.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "zookeeper")
public class ZookeeperConfig {

    private String hosts;
    private int sessionTimeout;
    private int connectionTimeout;
    private int baseSleepTimeMs;
    private int maxRetry;

    @Bean
    public CuratorFramework client() {
        CuratorFramework client = CuratorFrameworkFactory.newClient(hosts, sessionTimeout, connectionTimeout, new ExponentialBackoffRetry(1, maxRetry));
        client.start();
        return client;
    }

}
