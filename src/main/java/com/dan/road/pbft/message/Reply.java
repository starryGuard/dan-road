package com.dan.road.pbft.message;

import lombok.Getter;
import lombok.ToString;

/**
 * 响应信息
 * Created by dan on 2018/10/15.
 */
@Getter @ToString
public class Reply {
    /**
     * 视图编号
     */
    private Integer viewId;
    /**
     * 时间戳
     */
    private Long timeStamp;
    /**
     * 客户端编号
     */
    private Integer clientId;
    /**
     * 副本节点编号
     */
    private Integer nodeId;
    /**
     * 请求执行结果
     */
    private Boolean result;
}
