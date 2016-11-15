package net.su.market.valueObject;
/**
	 * 추천하기 관련  VO입니다.
	 * 
	 * @see   net.su.market.valueObject.RecmndValueObject
	 * @version  1.0 
	 * @ author 시상일, 2016/08/24
*/
public class RecmndValueObject {
	
	private int giveRecmndEmp; //추천하는 직원
	private int getRecmndEmp; //추천받는 직원
	private String recmndDat; //추천일
	
	public int getGiveRecmndEmp() {
		return giveRecmndEmp;
	}
	public void setGiveRecmndEmp(int giveRecmndEmp) {
		this.giveRecmndEmp = giveRecmndEmp;
	}
	public int getGetRecmndEmp() {
		return getRecmndEmp;
	}
	public void setGetRecmndEmp(int getRecmndEmp) {
		this.getRecmndEmp = getRecmndEmp;
	}
	public String getRecmndDat() {
		return recmndDat;
	}
	public void setRecmndDat(String recmndDat) {
		this.recmndDat = recmndDat;
	}

}
