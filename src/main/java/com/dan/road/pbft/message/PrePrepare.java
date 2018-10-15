package com.dan.road.pbft.message;

import lombok.Getter;
import lombok.ToString;

/**
 * 预准备消息
 * Created by dan on 2018/10/15.
 */
@Getter @ToString
public class PrePrepare {
    /**
     * 视图编号
     */
    private Integer viewId;
    /**
     * 消息序号
     */
    private Long number;
    /**
     * 消息签名
     */
    private String digest;
}
