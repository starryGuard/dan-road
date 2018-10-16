package com.dan.road.pbft.msg;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import static com.dan.road.pbft.enums.MsgType.*;

@Getter @Setter
public class CheckPointMsg extends Message{

	/**
	 * 视图编号
	 */
	private int viewId;

	/**
	 * 消息序列号
	 */
	private int number;

	/**
	 * lastReply集合
	 */
	private Map<Integer, LastReply> lastReplyMap;

	/**
	 * 节点ID
	 */
	private int nodeId;
	
	public CheckPointMsg(int v, int n, Map<Integer, LastReply> lastReplyMap, int i, int senderId, int receiveId, long receiveTime) {
		super(senderId, receiveId, receiveTime);
		long appendLen = lastReplyMap == null ? 0L : lastReplyMap.size() * LASTREPLEN;
		this.type = CHECKPOINT.getValue();
		this.len = CKPMSGBASELEN + appendLen;
		this.viewId = v;
		this.number = n;
		this.lastReplyMap = lastReplyMap;
		this.nodeId = i;
	}
	
	public Message copy(int receiveId, long receiveTime) {
		//s是浅复制，不过没有关系，不会修改s的值
		return new CheckPointMsg(viewId, number, lastReplyMap, nodeId, senderId, receiveId, receiveTime);
	}
	
	public boolean equals(Object obj) {
        if (obj instanceof CheckPointMsg) {
        	CheckPointMsg msg = (CheckPointMsg) obj;
            return (viewId == msg.viewId && number == msg.number && nodeId == msg.nodeId);
        }
        return super.equals(obj);
    }
        
    public int hashCode() {
        String str = "" + viewId + number + nodeId;
        return str.hashCode();
    }
    
    public String toString() {
    	return super.toString() + "视图编号:"+viewId+";序列号:"+number;
    }
}
