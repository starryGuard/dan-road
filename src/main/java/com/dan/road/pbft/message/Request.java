package com.dan.road.pbft.message;

import com.sun.javafx.binding.StringFormatter;
import jnr.ffi.annotations.In;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bouncycastle.cert.ocsp.Req;

/**
 * 客户端请求
 * Created by dan on 2018/10/15.
 */
@Getter @ToString
public class Request {
    /**
     * 时间戳
     */
    private Long timeStamp;
    /**
     * 客户端编号
     */
    private Integer clientId;
    /**
     * 操作
     */
   private String operation;

    /**
     * 消息签名
     */
    private String Signature;

    public Request(Integer clientId) {
        this.clientId = clientId;
        timeStamp = System.currentTimeMillis();
    }

}
