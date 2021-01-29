package org.koala.task.dispatch.common.enums;

import lombok.Getter;

@Getter
public enum ZnodeTypeEnum {

    LEADER("/leader"), SLAVES("/slaves"), TASKS("/tasks"), ASSIGN("/assign");

    private String path;

    ZnodeTypeEnum(String path) {
        this.path = path;
    }

}
