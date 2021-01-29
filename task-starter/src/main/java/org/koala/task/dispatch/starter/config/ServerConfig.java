package org.koala.task.dispatch.starter.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.curator.framework.CuratorFramework;
import org.koala.task.dispatch.executor.Server;
import org.koala.task.dispatch.executor.environment.EnvironmentInitializationProcess;
import org.koala.task.dispatch.executor.leader.LeaderInitializeProcess;
import org.koala.task.dispatch.executor.slave.SlaveInitializeProcess;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "server")
public class ServerConfig {

    private String name;
    private String host;

    @Bean
    public Server server(CuratorFramework client, LeaderInitializeProcess leaderInitializeProcess, SlaveInitializeProcess slaveInitializeProcess, EnvironmentInitializationProcess environmentInitializationProcess) {
        return new Server(name, host, client, leaderInitializeProcess, slaveInitializeProcess, environmentInitializationProcess);
    }

}
