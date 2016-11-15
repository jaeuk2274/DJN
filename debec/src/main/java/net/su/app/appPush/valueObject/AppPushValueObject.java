package net.su.app.appPush.valueObject;

public class AppPushValueObject {
	private String regId; //기기 토큰값
	private String sendTitl; //제목
	private String sendMesg; //내용
	private String sendUrl; //보낼 URL
	private String type; //푸시 타입
	
	
	public String getSendUrl() {
		return sendUrl;
	}
	public void setSendUrl(String sendUrl) {
		this.sendUrl = sendUrl;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}	
	public String getSendTitl() {
		return sendTitl;
	}
	public void setSendTitl(String sendTitl) {
		this.sendTitl = sendTitl;
	}
	public String getSendMesg() {
		return sendMesg;
	}
	public void setSendMesg(String sendMesg) {
		this.sendMesg = sendMesg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}