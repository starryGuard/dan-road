package com.dan.road.pbft.msg;
import lombok.Getter;
import lombok.Setter;

import static com.dan.road.pbft.enums.MsgType.*;

@Getter @Setter
public class PrePrepareMsg extends Message {
	/**
	 * 视图编号
	 */
	private int viewId;

	/**
	 * 消息序号
	 */
	private int number;

	/**
	 * request消息体
	 */
	private Message message;

	/**
	 * 节点编号
	 */
	private int nodeId;

	public PrePrepareMsg(int viewId, int number, Message message, int nodeId, int senderId, int receiveId, long receiveTime) {
		super(senderId, receiveId, receiveTime);
		this.type = PRE_PREPARE.getValue();
		this.len = PPRMSGLEN;
		this.viewId = viewId;
		this.number = number;
		this.message = message;
		this.nodeId = nodeId;
	}
	
	public Message copy(int receiveId, long receiveTime) {
		//m是浅复制，不过没有关系，不会修改它的值
		return new PrePrepareMsg(viewId, number, message, nodeId, senderId, receiveId, receiveTime);
	}
	
	public boolean equals(Object obj) {
        if (obj instanceof PrePrepareMsg) {
        	PrePrepareMsg msg = (PrePrepareMsg) obj;
            return (viewId == msg.viewId && number == msg.number && nodeId == msg.nodeId && ((message == null && msg.message == null) || (message != null && message.equals(msg.message))));
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
