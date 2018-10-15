package com.dan.road.pbft.message;

/**
 * 准备消息
 * Created by dan on 2018/10/15.
 */
public class Prepare {
    /**
     * 视图编号
     */
    private Integer viewId;
    /**
     * 消息序号
     */
    private Integer number;
    /**
     * 消息摘要
     */
    private String digest;
    /**
     * 副本编号
     */
    private Integer nodeId;
}
