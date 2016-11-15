package net.su.market.valueObject;

/**
 * 이용약관  관련  VO입니다.
 * 
 * @see   net.su.market.valueObject.AgremtValueObject
 * @version  1.0 
 * @ author 최재욱, 2016/08/09
 */
public class AgremtValueObject{
	private int useAgremtSeq = 1;
	private String titl;
	private String cnt;
	private String standrd;
	private String useCheck;
	
	private String showStd="N";
	
		
	public String getShowStd() {
		return showStd;
	}
	public void setShowStd(String showStd) {
		this.showStd = showStd;
	}
	public String getUseCheck() {
		return useCheck;
	}
	public void setUseCheck(String useCheck) {
		this.useCheck = useCheck;
	}
	public int getUseAgremtSeq() {
		return useAgremtSeq;
	}
	public void setUseAgremtSeq(int useAgremtSeq) {
		this.useAgremtSeq = useAgremtSeq;
	}
	public String getTitl() {
		return titl;
	}
	public void setTitl(String titl) {
		this.titl = titl;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String getStandrd() {
		return standrd;
	}
	public void setStandrd(String standrd) {
		this.standrd = standrd;
	}
	
	
}
