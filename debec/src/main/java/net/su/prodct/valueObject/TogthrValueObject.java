package net.su.prodct.valueObject;

import net.su.login.valueObject.PageValueObject;

public class TogthrValueObject extends PageValueObject{
	
	// 상품 테이블
	private int gropPurchsProdctSeq; //공동구매 seq
	private long prodctSeq;		// 상품seq
	private String img; //이미지
	private String prodctNme;	// 상품명
	private int qunt;			// 재고량
	private int purchsPric;		//매입가
	private int selPric; 		//판매 가격
	private String displyCheck;	//진열여부
	private int catgrSeq;		// 카테고리seq
	private long gropPurchsSeq; // 공동구매 바코드
	private int minOrdrAmont; // 최소 수량
	private int maxOrdrAmont; // 최대 수량
	private String ordrStarDat; // 주문시작기간
	private String ordrEndDat; // 주문종료기간
	private String stat; // 상태
	private String titl; // 제목
	private int existPric; // 기존가
	private int prodctSeqLength; // 상품seq 길이
	private int joinPersns; // 참여인원

	//이미지
	private String prodctMainImage="";
	private String prodctDetlImageOne="";
	private String prodctDetlImageTwo="";
	private String prodctDetlImageThree="";
	
	//검색 
	private String firstSelPric; // 판매가 첫 텍스트
	private String LastSelPric; // 판매가 두번째 텍스트
	
	public int getGropPurchsProdctSeq() {
		return gropPurchsProdctSeq;
	}
	public void setGropPurchsProdctSeq(int gropPurchsProdctSeq) {
		this.gropPurchsProdctSeq = gropPurchsProdctSeq;
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
	public int getQunt() {
		return qunt;
	}
	public void setQunt(int qunt) {
		this.qunt = qunt;
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
	public String getDisplyCheck() {
		return displyCheck;
	}
	public void setDisplyCheck(String displyCheck) {
		this.displyCheck = displyCheck;
	}
	public int getCatgrSeq() {
		return catgrSeq;
	}
	public void setCatgrSeq(int catgrSeq) {
		this.catgrSeq = catgrSeq;
	}
	public long getGropPurchsSeq() {
		return gropPurchsSeq;
	}
	public void setGropPurchsSeq(long gropPurchsSeq) {
		this.gropPurchsSeq = gropPurchsSeq;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getTitl() {
		return titl;
	}
	public void setTitl(String titl) {
		this.titl = titl;
	}
	public int getMinOrdrAmont() {
		return minOrdrAmont;
	}
	public void setMinOrdrAmont(int minOrdrAmont) {
		this.minOrdrAmont = minOrdrAmont;
	}
	public int getMaxOrdrAmont() {
		return maxOrdrAmont;
	}
	public void setMaxOrdrAmont(int maxOrdrAmont) {
		this.maxOrdrAmont = maxOrdrAmont;
	}
	public String getOrdrStarDat() {
		return ordrStarDat;
	}
	public void setOrdrStarDat(String ordrStarDat) {
		this.ordrStarDat = ordrStarDat;
	}
	public String getOrdrEndDat() {
		return ordrEndDat;
	}
	public void setOrdrEndDat(String ordrEndDat) {
		this.ordrEndDat = ordrEndDat;
	}
	public int getExistPric() {
		return existPric;
	}
	public void setExistPric(int existPric) {
		this.existPric = existPric;
	}
	public int getProdctSeqLength() {
		return prodctSeqLength;
	}
	public void setProdctSeqLength(int prodctSeqLength) {
		this.prodctSeqLength = prodctSeqLength;
	}
	public String getFirstSelPric() {
		return firstSelPric;
	}
	public void setFirstSelPric(String firstSelPric) {
		this.firstSelPric = firstSelPric;
	}
	public String getLastSelPric() {
		return LastSelPric;
	}
	public void setLastSelPric(String lastSelPric) {
		LastSelPric = lastSelPric;
	}
	public String getProdctMainImage() {
		return prodctMainImage;
	}
	public void setProdctMainImage(String prodctMainImage) {
		this.prodctMainImage = prodctMainImage;
	}
	public String getProdctDetlImageOne() {
		return prodctDetlImageOne;
	}
	public void setProdctDetlImageOne(String prodctDetlImageOne) {
		this.prodctDetlImageOne = prodctDetlImageOne;
	}
	public String getProdctDetlImageTwo() {
		return prodctDetlImageTwo;
	}
	public void setProdctDetlImageTwo(String prodctDetlImageTwo) {
		this.prodctDetlImageTwo = prodctDetlImageTwo;
	}
	public String getProdctDetlImageThree() {
		return prodctDetlImageThree;
	}
	public void setProdctDetlImageThree(String prodctDetlImageThree) {
		this.prodctDetlImageThree = prodctDetlImageThree;
	}
	public int getJoinPersns() {
		return joinPersns;
	}
	public void setJoinPersns(int joinPersns) {
		this.joinPersns = joinPersns;
	}
}