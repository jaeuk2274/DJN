package net.su.end.valueObject;

import net.su.login.valueObject.PageValueObject;
/**
 * 주문분석(일별,월별) 관련  VO입니다.
 * 
 * @see   net.su.end.valueObject.SalsValueObject
 * @version  1.0 
 * @ author 최재욱, 2016/08/22
 */
public class SalsValueObject extends PageValueObject{

	// 주문 분석
	private String salsDay; // 주문 일자
	private int purchsPric=0; // 매입액
	private int salsPric=0; // 주문액
	private int netinc=0; // 순이익
	private double margnRat=0; // 마진율
	
	//카테고리 관련 (콤보박스)
	private String topCatgrNme="대분류";
	private String midCatgrNme="중분류";
	private String botCatgrNme="소분류";
	private String selectedTopCatgrNme="대분류";
	private String selectedMidCatgrNme="중분류";
	private String selectedBotCatgrNme="소분류";
	private String catgrSize;
	
	//검색조건
	private String ordrType="전체 주문"; //주문종류(상일 추가)
	private String btnDatSech="3day"; // 버튼 날짜 선택
	private String startDatSech=""; // 날짜 검색 시작일
	private String endDatSech=""; // 날짜 검색 종료일
	private String prodctNmeSech=""; // 상품명 검색
	private int prodctpricSech1=0; //상품 판매가 검색 시작가격(상일 추가)
	private int prodctpricSech2=0; //상품 판매가 검색 끝가격(상일 추가)
	
	public String getOrdrType() {
		return ordrType;
	}
	public void setOrdrType(String ordrType) {
		this.ordrType = ordrType;
	}
	public String getProdctNmeSech() {
		return prodctNmeSech;
	}
	public void setProdctNmeSech(String prodctNmeSech) {
		this.prodctNmeSech = prodctNmeSech;
	}
	public String getBtnDatSech() {
		return btnDatSech;
	}
	public void setBtnDatSech(String btnDatSech) {
		this.btnDatSech = btnDatSech;
	}
	public String getStartDatSech() {
		return startDatSech;
	}
	public void setStartDatSech(String startDatSech) {
		this.startDatSech = startDatSech;
	}
	public String getEndDatSech() {
		return endDatSech;
	}
	public void setEndDatSech(String endDatSech) {
		this.endDatSech = endDatSech;
	}
	public String getSalsDay() {
		return salsDay;
	}
	public void setSalsDay(String salsDay) {
		this.salsDay = salsDay;
	}
	public int getPurchsPric() {
		return purchsPric;
	}
	public void setPurchsPric(int purchsPric) {
		this.purchsPric = purchsPric;
	}
	public int getSalsPric() {
		return salsPric;
	}
	public void setSalsPric(int salsPric) {
		this.salsPric = salsPric;
	}
	public int getNetinc() {
		return netinc;
	}
	public void setNetinc(int netinc) {
		this.netinc = netinc;
	}
	public double getMargnRat() {
		return margnRat;
	}
	public void setMargnRat(double margnRat) {
		this.margnRat = margnRat;
	}
	public String getTopCatgrNme() {
		return topCatgrNme;
	}
	public void setTopCatgrNme(String topCatgrNme) {
		this.topCatgrNme = topCatgrNme;
	}
	public String getMidCatgrNme() {
		return midCatgrNme;
	}
	public void setMidCatgrNme(String midCatgrNme) {
		this.midCatgrNme = midCatgrNme;
	}
	public String getBotCatgrNme() {
		return botCatgrNme;
	}
	public void setBotCatgrNme(String botCatgrNme) {
		this.botCatgrNme = botCatgrNme;
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
	public String getCatgrSize() {
		return catgrSize;
	}
	public void setCatgrSize(String catgrSize) {
		this.catgrSize = catgrSize;
	}
	public int getProdctpricSech1() {
		return prodctpricSech1;
	}
	public void setProdctpricSech1(int prodctpricSech1) {
		this.prodctpricSech1 = prodctpricSech1;
	}
	public int getProdctpricSech2() {
		return prodctpricSech2;
	}
	public void setProdctpricSech2(int prodctpricSech2) {
		this.prodctpricSech2 = prodctpricSech2;
	}
}
