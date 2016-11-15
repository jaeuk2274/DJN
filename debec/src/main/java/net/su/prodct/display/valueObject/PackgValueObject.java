package net.su.prodct.display.valueObject;

import net.su.login.valueObject.PageValueObject;

public class PackgValueObject extends PageValueObject {
	//패키지 목록 리스트
	private int packgSeq; //상품번호
	private String packgType = "all"; //구분
	private String packgCnt; //패키지 내용
	private String packgNme; //패키지 명
	private int allPric; //총 가격
	private String packgDisplayCheck = "all"; //진열여부
	private String prodctType; //상품종류
	
	//패키지 목록 검색 필요 정보
	private String packgSechText; // 패키지명 검색어
	private String packgSechType = "clint"; // 구분여부 	
	
	// 패키지 상품 테이블 목록 검색 필요 정보
	private String prodctSechText; //상품명	
	// 패키지 상품 임시 테이블 목록 검색 필요 정보
	private String tempSechText; //상품명
	
	//카테고리 목록
	private int catgrSeq=0; //카테고리seq
	
	//거래처 상품 필요 정보
	private String mainImg; //메인이미지
	private String detlImg1; //상세이미지1
	private String detlImg2; //상세이미지2
	private String detlImg3; //상세이미지3
	private long prodctSeq; //상품번호
	private String prodctNme; //상품명
	private int purchsPric; //매입가
	private int selPric; //판매가
	private int qunt; //재고
	private int clintSeq; //거래처 번호
	private String clintNme; //거래처 명
	private String clintRepresntatv; //거래처 번호
	private String clintNum; //거래처 번호
	private String clintFax; //거래처 번호
	private String clintMangr; //거래처 번호
	private String clintMangrNum; //거래처 번호
	private String prodctSeqSet; //상품seq 3개  묶음
	
	public String getProdctSeqSet() {
		return prodctSeqSet;
	}
	public void setProdctSeqSet(String prodctSeqSet) {
		this.prodctSeqSet = prodctSeqSet;
	}
	public int getCatgrSeq() {
		return catgrSeq;
	}
	public void setCatgrSeq(int catgrSeq) {
		this.catgrSeq = catgrSeq;
	}
	public String getClintRepresntatv() {
		return clintRepresntatv;
	}
	public void setClintRepresntatv(String clintRepresntatv) {
		this.clintRepresntatv = clintRepresntatv;
	}
	public String getClintNum() {
		return clintNum;
	}
	public void setClintNum(String clintNum) {
		this.clintNum = clintNum;
	}
	public String getClintFax() {
		return clintFax;
	}
	public void setClintFax(String clintFax) {
		this.clintFax = clintFax;
	}
	public String getClintMangr() {
		return clintMangr;
	}
	public void setClintMangr(String clintMangr) {
		this.clintMangr = clintMangr;
	}
	public String getClintMangrNum() {
		return clintMangrNum;
	}
	public void setClintMangrNum(String clintMangrNum) {
		this.clintMangrNum = clintMangrNum;
	}
	public String getPackgCnt() {
		return packgCnt;
	}
	public void setPackgCnt(String packgCnt) {
		this.packgCnt = packgCnt;
	}
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
	public int getClintSeq() {
		return clintSeq;
	}
	public void setClintSeq(int clintSeq) {
		this.clintSeq = clintSeq;
	}
	public String getMainImg() {
		return mainImg;
	}
	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
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
	public int getPurchsPric() {
		return purchsPric;
	}
	public void setPurchsPric(int purchsPric) {
		this.purchsPric = purchsPric;
	}
	public int getSelPric() {
		return selPric;
	}
	public void setSelPric(int selPric) {
		this.selPric = selPric;
	}
	public int getQunt() {
		return qunt;
	}
	public void setQunt(int qunt) {
		this.qunt = qunt;
	}
	public int getPackgSeq() {
		return packgSeq;
	}
	public void setPackgSeq(int packgSeq) {
		this.packgSeq = packgSeq;
	}
	public String getPackgType() {
		return packgType;
	}
	public void setPackgType(String packgType) {
		this.packgType = packgType;
	}
	public String getClintNme() {
		return clintNme;
	}
	public void setClintNme(String clintNme) {
		this.clintNme = clintNme;
	}
	public String getPackgNme() {
		return packgNme;
	}
	public void setPackgNme(String packgNme) {
		this.packgNme = packgNme;
	}
	public int getAllPric() {
		return allPric;
	}
	public void setAllPric(int allPric) {
		this.allPric = allPric;
	}
	public String getPackgDisplayCheck() {
		return packgDisplayCheck;
	}
	public void setPackgDisplayCheck(String packgDisplayCheck) {
		this.packgDisplayCheck = packgDisplayCheck;
	}
	public String getPackgSechText() {
		return packgSechText;
	}
	public void setPackgSechText(String packgSechText) {
		this.packgSechText = packgSechText;
	}
	public String getPackgSechType() {
		return packgSechType;
	}
	public void setPackgSechType(String packgSechType) {
		this.packgSechType = packgSechType;
	}
	public String getDetlImg1() {
		return detlImg1;
	}
	public void setDetlImg1(String detlImg1) {
		this.detlImg1 = detlImg1;
	}
	public String getDetlImg2() {
		return detlImg2;
	}
	public void setDetlImg2(String detlImg2) {
		this.detlImg2 = detlImg2;
	}
	public String getDetlImg3() {
		return detlImg3;
	}
	public void setDetlImg3(String detlImg3) {
		this.detlImg3 = detlImg3;
	}
	public String getProdctType() {
		return prodctType;
	}
	public void setProdctType(String prodctType) {
		this.prodctType = prodctType;
	}
	
}
