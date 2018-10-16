package com.dan.road.pbft.msg;
import lombok.Getter;
import lombok.Setter;

import static com.dan.road.pbft.enums.MsgType.*;

@Getter @Setter
public class ReplyMsg extends Message {

	/**
	 * 视图编号
	 */
	private int viewId;
	/**
	 * 客户端时间戳
	 */
	private long timeStamp;
	/**
	 * 客户端编号
	 */
	private int clientId;
	/**
	 * 节点编号
	 */
	private int nodeId;
	/**
	 * 处理返回结果
	 */
	private String result;
	
	public ReplyMsg(int v, long t, int c, int i, String r, int senderId, int receiveId, long receiveTime) {
		super(senderId, receiveId, receiveTime);
		this.type = REPLY.getValue();
		this.len = REPMSGLEN;
		this.viewId = v;
		this.timeStamp = t;
		this.clientId = c;
		this.nodeId = i;
		this.result = r;
	}
	
	public boolean equals(Object obj) {
        if (obj instanceof ReplyMsg) {
        	ReplyMsg msg = (ReplyMsg) obj;
            return (viewId == msg.viewId && timeStamp == msg.timeStamp && clientId == msg.clientId && nodeId == msg.nodeId && result.equals(msg.result));
        }
        return super.equals(obj);
    }
        
    public int hashCode() {
        String str = "" + viewId + timeStamp + clientId + nodeId + result;
        return str.hashCode();
    }
    
    public String toString() {
    	return super.toString() + "视图编号:"+viewId+";时间戳:"+timeStamp+";客户端编号:"+clientId;
    }
}