package net.su.prodct.display.valueObject;

import net.su.login.valueObject.PageValueObject;

/**
 * 메인 진열 상품관리 VO입니다.
 * 
 * @see   net.su.login.valueObject.PageValueObject
 * @version  1.0 
 * @ author 김동욱, 2016/08/10
 */
 
public class MainDisplayValueObject extends PageValueObject {
	//메인 진열 상품 목록 리스트
	private int seqnc; //진열 순서
	private int purchsPric; //매입가
	private String img; //이미지
	private long prodctSeq; //상품번호
	private String prodctNme; //상품명
	private int selPric; //판매가
	private int evntPric; //행사가
	private int selAmount; //판매수량
	
	// 메인 진열 상품 목록 검색 필요 정보
	private String mainDisplaySechText; // 상품명 검색어
	private String mainDisplayMinPric; // 최소 판매가
	private String mainDisplayMaxPric; // 최대 판매가
	
	// 메인 진열 상품 상품 테이블 목록 검색 필요 정보
	private String prodctSechText; //상품명
	
	// 메인 진열 상품 임시 테이블 목록 검색 필요 정보
	private String tempSechText; //상품명
	
	//메인 진열 상품 목록의 상품 추가 목록
	private int qunt; //상품 재고
	
	//카테고리 목록
	private int catgrSeq=0; //카테고리seq
	
	public String getProdctSechText() {
		return prodctSechText;
	}
	public void setProdctSechText(String prodctSechText) {
		this.prodctSechText = prodctSechText;
	}
	public String getTempSechText() {
		return tempSechText;
	}
	public void setTempSechText(String tempSechText) {
		this.tempSechText = tempSechText;
	}
	public int getQunt() {
		return qunt;
	}
	public void setQunt(int qunt) {
		this.qunt = qunt;
	}
	public int getSeqnc() {
		return seqnc;
	}
	public void setSeqnc(int seqnc) {
		this.seqnc = seqnc;
	}
	public int getPurchsPric() {
		return purchsPric;
	}
	public void setPurchsPric(int purchsPric) {
		this.purchsPric = purchsPric;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public long getProdctSeq() {
		return prodctSeq;
	}
	public void setProdctSeq(long prodctSeq) {
		this.prodctSeq = prodctSeq;
	}
	public String getProdctNme() {
		return prodctNme;
	}
	public void setProdctNme(String prodctNme) {
		this.prodctNme = prodctNme;
	}
	public int getSelPric() {
		return selPric;
	}
	public void setSelPric(int selPric) {
		this.selPric = selPric;
	}
	public int getEvntPric() {
		return evntPric;
	}
	public void setEvntPric(int evntPric) {
		this.evntPric = evntPric;
	}
	public int getSelAmount() {
		return selAmount;
	}
	public void setSelAmount(int selAmount) {
		this.selAmount = selAmount;
	}
	public String getMainDisplaySechText() {
		return mainDisplaySechText;
	}
	public void setMainDisplaySechText(String mainDisplaySechText) {
		this.mainDisplaySechText = mainDisplaySechText;
	}
	public String getMainDisplayMinPric() {
		return mainDisplayMinPric;
	}
	public void setMainDisplayMinPric(String mainDisplayMinPric) {
		this.mainDisplayMinPric = mainDisplayMinPric;
	}
	public String getMainDisplayMaxPric() {
		return mainDisplayMaxPric;
	}
	public void setMainDisplayMaxPric(String mainDisplayMaxPric) {
		this.mainDisplayMaxPric = mainDisplayMaxPric;
	}
	public int getCatgrSeq() {
		return catgrSeq;
	}
	public void setCatgrSeq(int catgrSeq) {
		this.catgrSeq = catgrSeq;
	}
}
