package net.su.deal.valueObject;

import net.su.login.valueObject.PageValueObject;

public class StckValueObject extends PageValueObject{
	
	//	재고 테이블 && 상품 테이블
	private long prodctSeq;
	private long[] prodctCheck;
	private String prodctNme;
	private int purchsPric;
	private String prodctMainImage;
	private int qunt;
	private int stckType;
	// 거래처 테이블
	
	private int clintSeq;
	private String clintNme;
	private String clintMangrNum;
	
	// 검색 조건
	private String clintSechText=null;
	private String prodctSechText=null;
	private int firstQunt;
	private int secondQunt;
	
	// 전체 상품 갯수
	private int prodctCount;
	
	//입고 내역 추가 컬럼
	private int totlPric; // 주문 총액
	private String dat; // 날짜
	private String selectedTopCatgrNme="대분류";
	private String selectedMidCatgrNme="중분류";
	private String selectedBotCatgrNme="소분류";
	
	//카테고리
	private String catgrSeq; 
	
	public long getProdctSeq() {
		return prodctSeq;
	}
	public void setProdctSeq(long prodctSeq) {
		this.prodctSeq = prodctSeq;
	}
	
	
	public long[] getProdctCheck() {
		return prodctCheck;
	}
	public void setProdctCheck(long[] prodctCheck) {
		this.prodctCheck = prodctCheck;
	}
	
	public String getProdctNme() {
		return prodctNme;
	}
	public void setProdctNme(String prodctNme) {
		this.prodctNme = prodctNme;
	}
	public int getPurchsPric() {
		return purchsPric;
	}
	public void setPurchsPric(int purchsPric) {
		this.purchsPric = purchsPric;
	}
	public int getQunt() {
		return qunt;
	}
	public void setQunt(int qunt) {
		this.qunt = qunt;
	}
	public int getStckType() {
		return stckType;
	}
	public void setStckType(int stckType) {
		this.stckType = stckType;
	}
	
	
	public int getClintSeq() {
		return clintSeq;
	}
	public void setClintSeq(int clintSeq) {
		this.clintSeq = clintSeq;
	}
	public String getClintNme() {
		return clintNme;
	}
	public void setClintNme(String clintNme) {
		this.clintNme = clintNme;
	}
	public String getClintMangrNum() {
		return clintMangrNum;
	}
	public void setClintMangrNum(String clintMangrNum) {
		this.clintMangrNum = clintMangrNum;
	}

	
	
	
	public String getClintSechText() {
		return clintSechText;
	}
	public void setClintSechText(String clintSechText) {
		this.clintSechText = clintSechText;
	}
	public String getProdctSechText() {
		return prodctSechText;
	}
	public void setProdctSechText(String prodctSechText) {
		this.prodctSechText = prodctSechText;
	}
	public int getFirstQunt() {
		return firstQunt;
	}
	public void setFirstQunt(int firstQunt) {
		this.firstQunt = firstQunt;
	}
	public int getSecondQunt() {
		return secondQunt;
	}
	public void setSecondQunt(int secondQunt) {
		this.secondQunt = secondQunt;
	}
	
	
	
	public String getSelectedTopCatgrNme() {
		return selectedTopCatgrNme;
	}
	public void setSelectedTopCatgrNme(String selectedTopCatgrNme) {
		this.selectedTopCatgrNme = selectedTopCatgrNme;
	}
	public String getSelectedMidCatgrNme() {
		return selectedMidCatgrNme;
	}
	public void setSelectedMidCatgrNme(String selectedMidCatgrNme) {
		this.selectedMidCatgrNme = selectedMidCatgrNme;
	}
	public String getSelectedBotCatgrNme() {
		return selectedBotCatgrNme;
	}
	public void setSelectedBotCatgrNme(String selectedBotCatgrNme) {
		this.selectedBotCatgrNme = selectedBotCatgrNme;
	}
	public String getProdctMainImage() {
		return prodctMainImage;
	}
	public void setProdctMainImage(String prodctMainImage) {
		this.prodctMainImage = prodctMainImage;
	}
	public int getProdctCount() {
		return prodctCount;
	}
	public void setProdctCount(int prodctCount) {
		this.prodctCount = prodctCount;
	}
	
	
	public int getTotlPric() {
		return totlPric;
	}
	public void setTotlPric(int totlPric) {
		this.totlPric = totlPric;
	}
	public String getDat() {
		return dat;
	}
	public void setDat(String dat) {
		this.dat = dat;
	}
	
	
	public String getCatgrSeq() {
		return catgrSeq;
	}
	public void setCatgrSeq(String catgrSeq) {
		this.catgrSeq = catgrSeq;
	}
	
	
	
	
}
