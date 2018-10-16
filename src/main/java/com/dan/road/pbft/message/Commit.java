package com.dan.road.pbft.message;

/**
 * Commit消息
 * Created by dan on 2018/10/15.
 */
public class Commit {
    /**
     * 视图编号
     */
    private  Integer viewId;
    /**
     * 消息序号
     */
    private Integer number;
    /**
     * 消息签名摘要
     */
    private String digest;
    /**
     * 副本索引
     */
    private Integer index;
    /**
     * 签名
     */
    private String Signature;

}
