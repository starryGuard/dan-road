package com.dan.road.pbft.msg;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LastReply {

	/**
	 * 客户端Id
	 */
	private int clientId;

	/**
	 * 时间戳
	 */
	private long timeStamp;

	/**
	 * 响应结果
	 */
	private String r;
	
	public LastReply(int c, long t, String r) {
		this.clientId = c;
		this.timeStamp = t;
		this.r = r;
	}
	
	public boolean equals(Object obj) {
        if (obj instanceof LastReply) {
        	LastReply lr = (LastReply) obj;
            return (clientId == lr.clientId && timeStamp == lr.timeStamp && r.equals(lr.r));
        }
        return super.equals(obj);
    }
}
