package com.dan.road.pbft.enums;

import lombok.Getter;

/**
 * 消息类型
 * Created by dan on 2018/10/16.
 */
@Getter
public enum MsgType {

    REQUEST(0, "客户端请求"),

    PRE_PREPARE(1, "预准备消息"),

    PREPARE(2, "准备消息"),

    COMMIT(3, "确认消息"),

    REPLY(4, "响应消息"),

    CHECKPOINT(5, "检查点消息"),

    VIEW_CHANGE(6, "视图变更消息"),

    NEW_VIEW(7, "创建新视图消息"),

    TIMEOUT(8, "超时消息"),

    CLI_TIMEOUT(9, "客户端超时消息");

    private int value;
    private String describe;
    MsgType(int value, String describe){
        this.value = value;
        this.describe = describe;
    }
}
