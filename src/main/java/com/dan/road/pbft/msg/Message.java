package com.dan.road.pbft.msg;


import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public class Message {

	public static final long PRQMSGLEN = 0;					//PreRequest消息是虚拟消息

	public static final long REQMSGLEN = 100;				//Request消息的大小(bytes),可按实际情况设置

	public static final long PPRMSGLEN = 4 + REQMSGLEN;		//RrePrepare消息的大小

	public static final long PREMSGLEN = 36;				//Prepare消息的大小

	public static final long COMMSGLEN = 36;				//Commit消息的大小

	public static final long REPMSGLEN = 16;				//Reply消息的大小

	public static final long CKPMSGBASELEN = 4;				//CheckPoint消息的基础大小（还需要动态加上s集合大小）

	public static final long VCHMSGBASELEN = 4;				//ViewChange消息的基础大小

	public static final long NEVMSGBASELEN = 3;				//NewView消息的基础大小

	public static final long LASTREPLEN = REPMSGLEN - 3;	//LastReply的大小

	public static final long TIMMSGLEN = 0;					//TimeOut消息是虚拟消息

	public static final long CLTMSGLEN = 0;					//CliTimeOut消息是虚拟消息

	public static Comparator<Message> cmp = (c1, c2) -> (int) (c1.receiveTime - c2.receiveTime);
	
	public int type;				//消息类型	
	
	public int senderId;				//消息发送端id

	public int receiveId;  				//消息接收端id
	
	public long receiveTime;  			//消息接收时间
	
	public long len;				//消息大小
	
	public Message(int senderId, int receiveId, long receiveTime) {
		this.senderId = senderId;
		this.receiveId = receiveId;
		this.receiveTime = receiveTime;
	}

	public static long accumulateLen(Set<Message> set) {
		long len = 0L;
		if(set != null) {
			for(Message m : set) {
				len += m.len;
			}
		}
		return len;
	}
	
	public static long accumulateLen(Map<Integer, Set<Message>> map) {
		long len = 0L;
		if(map != null) {
			for(Integer n : map.keySet()) {
				len += accumulateLen(map.get(n));
			}
		}
		return len;
	}
	
	public Message copy(int receiveId, long receiveTime) {
		return new Message(senderId, receiveId, receiveTime);
	}
	
	public boolean equals(Object obj) {
        if (obj instanceof Message) {
        	Message msg = (Message) obj;
            return (type == msg.type && senderId == msg.senderId && receiveId == msg.receiveId && receiveTime == msg.receiveTime);
        }
        return super.equals(obj);
    }
        
    public int hashCode() {
        String str = "" + type + senderId + receiveId + receiveTime;
        return str.hashCode();
    }
    
    public String toString() {
		String[] typeName = {"Request","PrePrepare","Prepare","Commit","Reply"
				,"CheckPoint","ViewChange","NewView","TimeOut","CliTimeOut"};
		return "消息类型:"+typeName[type]+";发送者id:"
				+senderId+";接收者id:"+receiveId+";消息接收时间戳:"+receiveTime+";";
    }

}
