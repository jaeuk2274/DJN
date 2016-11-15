package net.su.prodct.display.valueObject;

import net.su.login.valueObject.PageValueObject;

public class HotdlValueObject extends PageValueObject{
	//핫딜 상품 목록 리스트
	private long prodctSeq; //상품번호
	private String img; //이미지
	private String prodctNme; //상품명
	private int remngAmont; //남은수량
	private int purchsPric; //매입가
	private int discntPric; //핫딜할인가
	private int selPric; //판매가
	private int amont; //핫딜 수량
	private int qunt; //재고량
	private String prodctType; //상품종류

	//카테고리 목록
	private int catgrSeq=0; //카테고리seq
	 
	//핫딜 상품 상품 테이블 목록 검색 필요 정보
	private String prodctSechText; //상품명
	
	// 메인 진열 상품 목록 검색 필요 정보
	private String hotdlSechText; // 상품명 검색어
	private String hotdlMinPric; // 최소 할인가
	private String hotdlMaxPric; // 최대 할인가
	
	
	public int getCatgrSeq() {
		return catgrSeq;
	}
	public void setCatgrSeq(int catgrSeq) {
		this.catgrSeq = catgrSeq;
	}
	public String getProdctSechText() {
		return prodctSechText;
	}
	public void setProdctSechText(String prodctSechText) {
		this.prodctSechText = prodctSechText;
	}
	public int getQunt() {
		return qunt;
	}
	public void setQunt(int qunt) {
		this.qunt = qunt;
	}
	public int getSelPric() {
		return selPric;
	}
	public void setSelPric(int selPric) {
		this.selPric = selPric;
	}
	public int getAmont() {
		return amont;
	}
	public void setAmont(int amont) {
		this.amont = amont;
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
	public int getRemngAmont() {
		return remngAmont;
	}
	public void setRemngAmont(int remngAmont) {
		this.remngAmont = remngAmont;
	}
	public int getPurchsPric() {
		return purchsPric;
	}
	public void setPurchsPric(int purchsPric) {
		this.purchsPric = purchsPric;
	}
	public int getDiscntPric() {
		return discntPric;
	}
	public void setDiscntPric(int discntPric) {
		this.discntPric = discntPric;
	}
	public String getHotdlSechText() {
		return hotdlSechText;
	}
	public void setHotdlSechText(String hotdlSechText) {
		this.hotdlSechText = hotdlSechText;
	}
	public String getHotdlMinPric() {
		return hotdlMinPric;
	}
	public void setHotdlMinPric(String hotdlMinPric) {
		this.hotdlMinPric = hotdlMinPric;
	}
	public String getHotdlMaxPric() {
		return hotdlMaxPric;
	}
	public void setHotdlMaxPric(String hotdlMaxPric) {
		this.hotdlMaxPric = hotdlMaxPric;
	}
	
	public String getProdctType() {
		return prodctType;
	}
	public void setProdctType(String prodctType) {
		this.prodctType = prodctType;
	}
}
