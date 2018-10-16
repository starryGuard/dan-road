package com.dan.road.pbft.msg;
import lombok.Getter;
import lombok.Setter;

import static com.dan.road.pbft.enums.MsgType.*;

@Getter @Setter
public class PrepareMsg extends Message {
	/**
	 * 视图编号
	 */
	private int viewId;

	/**
	 * 消息序号
	 */
	private int number;

	/**
	 * 节点编号
	 */
	private int nodeId;

	/**
	 * 消息摘要
	 */
	private String digest;
	
	public PrepareMsg(int v, int n, String d, int i, int senderId, int receiveId, long receiveTime) {
		super(senderId, receiveId, receiveTime);
		this.type = PREPARE.getValue();
		this.len = PREMSGLEN;
		this.viewId = v;
		this.number = n;
		this.digest = d;
		this.nodeId = i;
	}
	
	public Message copy(int receiveId, long receiveTime) {
		return new PrepareMsg(viewId, number, digest, nodeId, senderId, receiveId, receiveTime);
	}
	
	public boolean equals(Object obj) {
        if (obj instanceof PrepareMsg) {
        	PrepareMsg msg = (PrepareMsg) obj;
            return (viewId == msg.viewId && number == msg.number && digest.equals(msg.digest) && nodeId == msg.nodeId);
        }
        return super.equals(obj);
    }
        
    public int hashCode() {
        String str = "" + viewId + number + digest + nodeId;
        return str.hashCode();
    }
    
    public String toString() {
    	return super.toString() + "视图编号:" + viewId + ";序列号:" + number;
    }
}
