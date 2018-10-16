package com.dan.road.pbft.msg;
import lombok.Getter;
import lombok.Setter;

import static com.dan.road.pbft.enums.MsgType.*;

@Getter @Setter
public class TimeOutMsg extends Message {

	/**
	 * 视图编号
	 */
	private int viewId;

	/**
	 * 消息序号
	 */
	private int number;
	
	//消息结构
	//<TIMEOUT, v, n>:v表示视图编号;n表示序号;
	public TimeOutMsg(int v, int n, int senderId, int receiveId, long receiveTime) {
		super(senderId, receiveId, receiveTime);
		this.type = TIMEOUT.getValue();
		this.len = TIMMSGLEN;
		this.viewId = v;
		this.number = n;
	}
	
	public boolean equals(Object obj) {
        if (obj instanceof TimeOutMsg) {
        	TimeOutMsg msg = (TimeOutMsg) obj;
            return (viewId == msg.viewId && number == msg.number);
        }
        return super.equals(obj);
    }
        
    public int hashCode() {
        String str = "" + viewId + number;
        return str.hashCode();
    }
    
    public String toString() {
    	return super.toString() + "视图编号:"+viewId+";序号:"+number;
    }
}
