package org.koala.task.dispatch.executor.leader;

import org.koala.task.dispatch.executor.Server;

public abstract class LeaderInitializeProcess {

    public final void initialize(Server server) {
        unWatch(server);
        unRegisterToSlave(server);
        watchTasksNode(server);
    }

    abstract void unWatch(Server server);

    abstract void unRegisterToSlave(Server server);

    abstract void watchTasksNode(Server server);

}
