package com.dan.road.pbft.msg;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

import static com.dan.road.pbft.enums.MsgType.*;

@Getter @Setter
public class ViewChangeMsg extends Message {

	/**
	 * 视图编号
	 */
	private int viewId;

	/**
	 * 节点编号
	 */
	private int nodeId;

	/**
	 * 稳定状态的序列号
	 */
	private int stableNumber;
	/**
	 * 稳定状态的lastReply集合
	 */
	private Map<Integer, LastReply> stableLastReplyMap;

	/**
	 * checkPoint消息集合
	 */
	private Set<Message> C;

	/**
	 * 序列号大于稳定状态序列号的prePrepare集合
	 */
	private Map<Integer, Set<Message>> P;
	
	public ViewChangeMsg(int v, int sn, Map<Integer, LastReply> ss,
			Set<Message> C, Map<Integer, Set<Message>> P, int i, int senderId, int receiveId, long receiveTime) {
		super(senderId, receiveId, receiveTime);
		long sLen = ss == null ? 0 : ss.size() * LASTREPLEN;
		this.type = VIEW_CHANGE.getValue();
		this.len = VCHMSGBASELEN + sLen + accumulateLen(C) + accumulateLen(P);
		this.viewId = v;
		this.stableNumber = sn;
		this.stableLastReplyMap = ss;
		this.C = C;
		this.P = P;
		this.nodeId = i;
	}
	
	public Message copy(int receiveId, long receiveTime) {
		//ss, C, P是浅复制，不过没有关系，不会修改它们的值
		return new ViewChangeMsg(viewId, stableNumber, stableLastReplyMap, C, P, nodeId, senderId, receiveId, receiveTime);
	}
	
	public boolean equals(Object obj) {
        if (obj instanceof ViewChangeMsg) {
        	ViewChangeMsg msg = (ViewChangeMsg) obj;
            return (viewId == msg.viewId && stableNumber == msg.stableNumber && nodeId == msg.nodeId);
        }
        return super.equals(obj);
    }
        
    public int hashCode() {
        String str = "" + viewId + stableNumber + nodeId;
        return str.hashCode();
    }
    
    public String toString() {
    	return super.toString() + "视图编号:"+viewId+";稳定点序列号:"+stableNumber;
    }
}
