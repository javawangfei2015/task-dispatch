package org.koala.task.dispatch.executor;

import lombok.Getter;
import lombok.Setter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.koala.task.dispatch.common.enums.ZnodeTypeEnum;
import org.koala.task.dispatch.executor.environment.EnvironmentInitializationProcess;
import org.koala.task.dispatch.executor.leader.LeaderInitializeProcess;
import org.koala.task.dispatch.executor.slave.SlaveInitializeProcess;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Server {

    private String name;
    private String host;
    private CuratorFramework client;
    private Set<CuratorCache> caches = new HashSet<>();
    private LeaderLatch leaderLatch;

    private LeaderInitializeProcess leaderInitializeProcess;
    private SlaveInitializeProcess slaveInitializeProcess;
    private EnvironmentInitializationProcess environmentInitializationProcess;

    public Server(String name, String host, final CuratorFramework client,
                  LeaderInitializeProcess leaderInitializeProcess,
                  SlaveInitializeProcess slaveInitializeProcess,
                  EnvironmentInitializationProcess environmentInitializationProcess) {

        this.name = name;
        this.host = host;
        this.client = client;
        this.leaderInitializeProcess = leaderInitializeProcess;
        this.slaveInitializeProcess = slaveInitializeProcess;
        this.environmentInitializationProcess = environmentInitializationProcess;

        environmentInitializationProcess.initialize(this);
        doSlave(this);
        doElection(this);
    }


    private void doElection(final Server server) {
        try {
            leaderLatch = new LeaderLatch(client, ZnodeTypeEnum.LEADER.getPath());

            leaderLatch.addListener(new LeaderLatchListener() {
                public void isLeader() {
                    doLeader(server);
                }
                public void notLeader() {
                }
            });

            leaderLatch.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void doLeader(Server server) {
        leaderInitializeProcess.initialize(server);
    }

    private void doSlave(Server server) {
        slaveInitializeProcess.initialize(server);
    }
}
