package org.koala.task.dispatch.producer;

import org.koala.task.dispatch.common.vo.Task;

public interface ExecuteCallback {

    void success(Task task);

    void fail(Task task, Throwable throwable);

}
