package org.koala.task.dispatch.executor.environment;

import org.koala.task.dispatch.common.enums.ZnodeTypeEnum;
import org.koala.task.dispatch.executor.Server;
import org.koala.task.dispatch.zookeeper.utils.ZnodeUtil;
import org.springframework.stereotype.Component;

@Component
public class DefaultEnvironmentInitializationProcess extends EnvironmentInitializationProcess {

    @Override
    void createTasksNode(Server server) {
        if(!ZnodeUtil.checkExistNode(server.getClient(), ZnodeTypeEnum.TASKS.getPath())) {
            ZnodeUtil.createPersistentNode(server.getClient(), ZnodeTypeEnum.TASKS.getPath());
        }
    }

    @Override
    void createSlavesNode(Server server) {
        if(!ZnodeUtil.checkExistNode(server.getClient(), ZnodeTypeEnum.SLAVES.getPath())) {
            ZnodeUtil.createPersistentNode(server.getClient(), ZnodeTypeEnum.SLAVES.getPath());
        }
    }

    @Override
    void createAssign(Server server) {
        if(!ZnodeUtil.checkExistNode(server.getClient(), ZnodeTypeEnum.ASSIGN.getPath())) {
            ZnodeUtil.createPersistentNode(server.getClient(), ZnodeTypeEnum.ASSIGN.getPath());
        }
    }
}
