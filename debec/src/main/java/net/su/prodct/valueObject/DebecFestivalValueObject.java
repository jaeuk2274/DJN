package net.su.prodct.valueObject;

import net.su.login.valueObject.PageValueObject;

public class DebecFestivalValueObject extends CatgrValueObject {
	
	private int schedlSeq; // 일정 고유값
	private String schedlTitl; // 일정 제목
	private String schedlStartDat; // 일정 시작날짜
	private String schedlEndDat; // 일정 종료날짜
	private String evntStat; //행사 적용상태
	private String evntImg=""; //행사 배너 이미지
	private long prodctSeq; // 상품 고유값
	private int prodctCount; //상품 갯수
	private int debecFestivalChkGroup[]; // 그룹으로 한번에 처리하기 위한 행사 SEQ 배열
	private String division = "C"; //등록화면/수정화면 구분값
	private int schedlCount; //진행 행사 갯수
	
	public int getSchedlSeq() {
		return schedlSeq;
	}
	public void setSchedlSeq(int schedlSeq) {
		this.schedlSeq = schedlSeq;
	}
	public long getProdctSeq() {
		return prodctSeq;
	}
	public void setProdctSeq(long prodctSeq) {
		this.prodctSeq = prodctSeq;
	}
	public String getSchedlTitl() {
		return schedlTitl;
	}
	public void setSchedlTitl(String schedlTitl) {
		this.schedlTitl = schedlTitl;
	}
	public String getSchedlStartDat() {
		return schedlStartDat;
	}
	public void setSchedlStartDat(String schedlStartDat) {
		this.schedlStartDat = schedlStartDat;
	}
	public String getSchedlEndDat() {
		return schedlEndDat;
	}
	public void setSchedlEndDat(String schedlEndDat) {
		this.schedlEndDat = schedlEndDat;
	}
	public String getEvntStat() {
		return evntStat;
	}
	public void setEvntStat(String evntStat) {
		this.evntStat = evntStat;
	}
	public String getEvntImg() {
		return evntImg;
	}
	public void setEvntImg(String evntImg) {
		this.evntImg = evntImg;
	}
	public int getProdctCount() {
		return prodctCount;
	}
	public void setProdctCount(int prodctCount) {
		this.prodctCount = prodctCount;
	}
	public int[] getDebecFestivalChkGroup() {
		return debecFestivalChkGroup;
	}
	public void setDebecFestivalChkGroup(int[] debecFestivalChkGroup) {
		this.debecFestivalChkGroup = debecFestivalChkGroup;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public int getSchedlCount() {
		return schedlCount;
	}
	public void setSchedlCount(int schedlCount) {
		this.schedlCount = schedlCount;
	}
}
