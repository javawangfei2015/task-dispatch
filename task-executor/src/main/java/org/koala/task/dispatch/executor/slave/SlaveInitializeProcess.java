package org.koala.task.dispatch.executor.slave;

import org.koala.task.dispatch.executor.Server;

public abstract class SlaveInitializeProcess {

    public final void initialize(Server server) {
        unWatch(server);
        registerToSlave(server);
        watchToAssign(server);
    }

    abstract void unWatch(Server server);

    abstract void registerToSlave(Server server);

    abstract void watchToAssign(Server server);

}
