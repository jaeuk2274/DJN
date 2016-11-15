package net.su.prodct.display.valueObject;

import net.su.login.valueObject.PageValueObject;


public class FreshValueObject extends PageValueObject{
	//신선 식품 목록 리스트
	private long prodctSeq; //상품번호
	private String img; //이미지
	private String prodctNme; //상품명
	private int selPric; //판매가
	private String selStrtTime; //판매시작시간
	private String stat; //상태
	private int qunt; //재고량
	private String prodctType; //상품종류

	//신선 식품 상품 테이블 목록 검색 필요 정보
	private String prodctSechText; //상품명
	
	//카테고리 목록
	private int catgrSeq=0; //카테고리seq
	
	//신선 식품 목록 검색 필요 정보
	private String freshSechText; // 상품명 검색어
	private String freshMinPric; // 최소 할인가
	private String freshMaxPric; // 최대 할인가
	
	public int getCatgrSeq() {
		return catgrSeq;
	}
	public void setCatgrSeq(int catgrSeq) {
		this.catgrSeq = catgrSeq;
	}
	public int getQunt() {
		return qunt;
	}
	public void setQunt(int qunt) {
		this.qunt = qunt;
	}
	public String getProdctSechText() {
		return prodctSechText;
	}
	public void setProdctSechText(String prodctSechText) {
		this.prodctSechText = prodctSechText;
	}
	public long getProdctSeq() {
		return prodctSeq;
	}
	public void setProdctSeq(long prodctSeq) {
		this.prodctSeq = prodctSeq;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
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
	public String getSelStrtTime() {
		return selStrtTime;
	}
	public void setSelStrtTime(String selStrtTime) {
		this.selStrtTime = selStrtTime;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getFreshSechText() {
		return freshSechText;
	}
	public void setFreshSechText(String freshSechText) {
		this.freshSechText = freshSechText;
	}
	public String getFreshMinPric() {
		return freshMinPric;
	}
	public void setFreshMinPric(String freshMinPric) {
		this.freshMinPric = freshMinPric;
	}
	public String getFreshMaxPric() {
		return freshMaxPric;
	}
	public void setFreshMaxPric(String freshMaxPric) {
		this.freshMaxPric = freshMaxPric;
	}
	
	public String getProdctType() {
		return prodctType;
	}
	public void setProdctType(String prodctType) {
		this.prodctType = prodctType;
	}
}
