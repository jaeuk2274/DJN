package net.su.market.valueObject;
/**
	 * 추천사유 관련  VO입니다.
	 * 
	 * @see   net.su.market.valueObject.RecmndResnValueObject
	 * @version  1.0 
	 * @ author 시상일, 2016/08/24
*/
public class RecmndResnValueObject {
	private int recmndResnCont; //추천사유  횟수
	private String recmndResn; // 추천사유
	
	public int getRecmndResnCont() {
		return recmndResnCont;
	}
	public void setRecmndResnCont(int recmndResnCont) {
		this.recmndResnCont = recmndResnCont;
	}
	public String getRecmndResn() {
		return recmndResn;
	}
	public void setRecmndResn(String recmndResn) {
		this.recmndResn = recmndResn;
	}
}
