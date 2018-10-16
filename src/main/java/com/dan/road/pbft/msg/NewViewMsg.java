package com.dan.road.pbft.msg;
import lombok.Getter;
import lombok.Setter;

import static com.dan.road.pbft.enums.MsgType.*;

import java.util.Set;

@Getter @Setter
public class NewViewMsg extends Message {

	/**
	 * 视图编号
	 */
	private int viewId;
	/**
	 * 节点编号
	 */
	private int nodeId;
	
	private Set<Message> V;

	private Set<Message> O;

	private Set<Message> N;
	
	public NewViewMsg(int v, Set<Message> V, Set<Message> O, Set<Message> N,
			int i, int senderId, int receiveId, long receiveTime) {
		super(senderId, receiveId, receiveTime);
		this.type = NEW_VIEW.getValue();
		this.len = NEVMSGBASELEN + accumulateLen(V) + accumulateLen(O) + accumulateLen(N);
		this.viewId = v;
		this.V = V;
		this.O = O;
		this.N = N;
		this.nodeId = i;
	}
	
	public Message copy(int receiveId, long receiveTime) {
		//V O N是浅复制，不过没有关系，不会修改它们的值
		return new NewViewMsg(viewId, V, O, N, nodeId, senderId, receiveId, receiveTime);
	}
	
	public boolean equals(Object obj) {
        if (obj instanceof NewViewMsg) {
        	NewViewMsg msg = (NewViewMsg) obj;
            return (viewId == msg.viewId && nodeId == msg.nodeId);
        }
        return super.equals(obj);
    }
        
    public int hashCode() {
        String str = "" + viewId + nodeId;
        return str.hashCode();
    }
    
    public String toString() {
    	return super.toString() + "视图编号:"+viewId;
    }
}
