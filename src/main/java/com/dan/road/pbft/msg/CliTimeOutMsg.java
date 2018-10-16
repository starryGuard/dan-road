package com.dan.road.pbft.msg;
import lombok.Getter;
import lombok.Setter;

import static com.dan.road.pbft.enums.MsgType.*;

@Getter @Setter
public class CliTimeOutMsg extends Message {

	/**
	 * 请求时间戳
	 */
	private long timeStamp;

	public CliTimeOutMsg(long t, int senderId, int receiveId, long receiveTime) {
		super(senderId, receiveId, receiveTime);
		this.type = CLI_TIMEOUT.getValue();
		this.len = CLTMSGLEN;
		this.timeStamp = t;
	}
	
	public boolean equals(Object obj) {
        if (obj instanceof CliTimeOutMsg) {
        	CliTimeOutMsg msg = (CliTimeOutMsg) obj;
            return (timeStamp == msg.timeStamp);
        }
        return super.equals(obj);
    }
        
    public int hashCode() {
        String str = "" + timeStamp;
        return str.hashCode();
    }
	
    public String toString() {
    	return super.toString() + "请求时间戳:"+ timeStamp;
    }

}
