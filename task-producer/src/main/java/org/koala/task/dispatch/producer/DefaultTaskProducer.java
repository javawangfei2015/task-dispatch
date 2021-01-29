package org.koala.task.dispatch.producer;

import cn.hutool.json.JSONUtil;
import org.koala.task.dispatch.common.enums.ZnodeTypeEnum;
import org.koala.task.dispatch.common.vo.Task;
import org.koala.task.dispatch.executor.Server;
import org.koala.task.dispatch.zookeeper.utils.ZnodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultTaskProducer implements TaskProducer {

    @Autowired
    private Server server;

    @Override
    public void submit(Task task, ExecuteCallback executeCallback) {
        ZnodeUtil.createPersistentSeqNode(server.getClient(), ZnodeTypeEnum.TASKS.getPath(), JSONUtil.parse(task).toString());
    }
}
