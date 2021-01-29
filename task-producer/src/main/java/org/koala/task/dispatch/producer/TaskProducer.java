package org.koala.task.dispatch.producer;

import org.koala.task.dispatch.common.vo.Task;

public interface TaskProducer {

    void submit(Task task, ExecuteCallback executeCallback);

}
