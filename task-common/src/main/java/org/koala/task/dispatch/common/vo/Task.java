package org.koala.task.dispatch.common.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Task<T> {

    private String id;
    private LocalDateTime localDateTime;

    private T payload;

}
