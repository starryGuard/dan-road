package com.dan.road.pbft.msg;

import static com.dan.road.pbft.enums.MsgType.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
public class RequestMsg extends Message {
	
	private String operate;
	
	private long timeStamp;
	
	private int clientId;

	/**
	 * <REQUEST, o, t, c>
	 * @param operate 请求的操作
	 * @param timeStamp 时间戳
	 * @param clientId 客户端ID
	 */
	public RequestMsg(String operate, long timeStamp, int clientId, int senderId, int receiveId, long receiveTime) {
		super(senderId, receiveId, receiveTime);
		this.type = REQUEST.getValue();
		this.len = REQMSGLEN;
		this.operate = operate;
		this.timeStamp = timeStamp;
		this.clientId = clientId;
	}
	
	public boolean equals(Object obj) {
        if (obj instanceof RequestMsg) {
        	RequestMsg msg = (RequestMsg) obj;
            return (Objects.equals(operate, msg.operate) && timeStamp == msg.timeStamp && clientId == msg.clientId);
        }
        return super.equals(obj);
    }
        
    public int hashCode() {
        String str = operate + timeStamp + clientId;
        return str.hashCode();
    }
    
    public String toString() {
    	return super.toString() + "时间戳:"+ timeStamp +";客户端编号:" + clientId;
    }
	
}
