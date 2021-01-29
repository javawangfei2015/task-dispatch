package org.koala.task.dispatch.executor.environment;

import org.koala.task.dispatch.executor.Server;

public abstract class EnvironmentInitializationProcess {

    public final void initialize(Server server) {
        createTasksNode(server);
        createSlavesNode(server);
        createAssign(server);
    }

    abstract void createTasksNode(Server server);

    abstract void createSlavesNode(Server server);

    abstract void createAssign(Server server);
}
