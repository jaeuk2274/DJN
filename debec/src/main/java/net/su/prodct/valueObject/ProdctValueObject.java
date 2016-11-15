package net.su.prodct.valueObject;

import java.util.List;

public class ProdctValueObject extends CatgrValueObject{
	
	//일반 VO 값
	private long prodctSeq;
	private long prodctFileSeq;
	private String prodctNme;
	private int prodctPurchsPric=0;
	private int prodctSelPric=0;
	private String prodctDisplyCheck;
	private String catgrSeq;
	private String prodctStck="";
	private int qunt=0;
	private int prodctNum;
	private int prodctType;
	private int discntRat;
	
	//패키지
	private int packgSeq;
	
	//신선식품
	private String selStrtTime;
	private String selEndTime;
	private int selStrtTimeCount;
	
	//거래처 seq
	private int clintSeq;
	private String clintNme;
	private String clintMangrNum;
	
	//행사
	private int schedlSeq=0;
	private String evntStat;
	private String schedlTitl;
	private int evntDiscntRat;
	private int evntBridgSeq;
	private String schedlStartDat;
	private String schedlEndDat;
	
	//관련 상품
	private int reltnProdctSeqOne=0;
	private int reltnProdctSeqTwo=0;
	private int reltnProdctSeqThree=0;
	
	// 검색 조건
	private int prodctSechPriceOne=0;
	private int prodctSechPriceTwo=0;
	private String prodctSechText=null;
	private String tempSechText=null;
	
	// 순이익과 마진률
	private int prodctNetinc=0;
	private double prodctMargnrat=0;
	private double prodctSalePric=0;
	
	//중복확인
	private long dupliCheck;
	private String prodctMainImage="";
	private String prodctDetlImageOne="";
	private String prodctDetlImageTwo="";
	private String prodctDetlImageThree="";

	private int pageType;
	private int updateType;
	
	// 장바구니 관련 - 앱 들어가면서 추가한것
	private int cartBridgSeq=0; // 장바구니 상품 브릿지 seq(상품)
	private int gropPurchsCartBridgSeq=0; // 장바구니 공동구매 브릿지 seq(공동구매)
	private int packgBridgCartSeq=0; // 패키지 브릿지 장바구니 seq(패키지)
		
	private int prodctAmont=0; // 상품 수량 (장바구니)
	private List<Integer> cartChkGroup = null; // 상품 브릿지 SEQ 그룹
	private List<Integer> cartGropPurchsChkGroup = null; // 상품 브릿지 SEQ 그룹
	private List<Integer> cartPackgChkGroup = null; // 패키지 브릿지 SEQ 그룹
	
	private int cusSeq=0;
	
	// 히스토리
	private String historyDat="";
	
	
	// 연관상품 관련
	private int reltnProdctOneSeq;
	private String reltnProdctOneImg;
	private String reltnProdctOneNme;
	private int reltnProdctOneSelPric;
	private int reltnProdctOneDiscntPric;
	
	private int reltnProdctTwoSeq;
	private String reltnProdctTwoImg;
	private String reltnProdctTwoNme;
	private int reltnProdctTwoSelPric;
	private int reltnProdctTwoDiscntPric;
	
	private int reltnProdctThreeSeq;
	private String reltnProdctThreeImg;
	private String reltnProdctThreeNme;
	private int reltnProdctThreeSelPric;
	private int reltnProdctThreeDiscntPric;
	
	
	public String getHistoryDat() {
		return historyDat;
	}
	public void setHistoryDat(String historyDat) {
		this.historyDat = historyDat;
	}
	public int getGropPurchsCartBridgSeq() {
		return gropPurchsCartBridgSeq;
	}
	public void setGropPurchsCartBridgSeq(int gropPurchsCartBridgSeq) {
		this.gropPurchsCartBridgSeq = gropPurchsCartBridgSeq;
	}
	public int getPackgBridgCartSeq() {
		return packgBridgCartSeq;
	}
	public void setPackgBridgCartSeq(int packgBridgCartSeq) {
		this.packgBridgCartSeq = packgBridgCartSeq;
	}
	public List<Integer> getCartGropPurchsChkGroup() {
		return cartGropPurchsChkGroup;
	}
	public void setCartGropPurchsChkGroup(List<Integer> cartGropPurchsChkGroup) {
		this.cartGropPurchsChkGroup = cartGropPurchsChkGroup;
	}
	public List<Integer> getCartPackgChkGroup() {
		return cartPackgChkGroup;
	}
	public void setCartPackgChkGroup(List<Integer> cartPackgChkGroup) {
		this.cartPackgChkGroup = cartPackgChkGroup;
	}
	public long getProdctSeq() {
		return prodctSeq;
	}
	public void setProdctSeq(long prodctSeq) {
		this.prodctSeq = prodctSeq;
	}
	public long getProdctFileSeq() {
		return prodctFileSeq;
	}
	public void setProdctFileSeq(long prodctFileSeq) {
		this.prodctFileSeq = prodctFileSeq;
	}
	public String getProdctNme() {
		return prodctNme;
	}
	public void setProdctNme(String prodctNme) {
		this.prodctNme = prodctNme;
	}
	public int getProdctPurchsPric() {
		return prodctPurchsPric;
	}
	public void setProdctPurchsPric(int prodctPurchsPric) {
		this.prodctPurchsPric = prodctPurchsPric;
	}
	public int getProdctSelPric() {
		return prodctSelPric;
	}
	public void setProdctSelPric(int prodctSelPric) {
		this.prodctSelPric = prodctSelPric;
	}
	public String getProdctDisplyCheck() {
		return prodctDisplyCheck;
	}
	public void setProdctDisplyCheck(String prodctDisplyCheck) {
		this.prodctDisplyCheck = prodctDisplyCheck;
	}
	public String getCatgrSeq() {
		return catgrSeq;
	}
	public void setCatgrSeq(String catgrSeq) {
		this.catgrSeq = catgrSeq;
	}
	public String getProdctStck() {
		return prodctStck;
	}
	public void setProdctStck(String prodctStck) {
		this.prodctStck = prodctStck;
	}
	public int getQunt() {
		return qunt;
	}
	public void setQunt(int qunt) {
		this.qunt = qunt;
	}
	public int getProdctNum() {
		return prodctNum;
	}
	public void setProdctNum(int prodctNum) {
		this.prodctNum = prodctNum;
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
	public int getSchedlSeq() {
		return schedlSeq;
	}
	public void setSchedlSeq(int schedlSeq) {
		this.schedlSeq = schedlSeq;
	}
	public String getEvntStat() {
		return evntStat;
	}
	public void setEvntStat(String evntStat) {
		this.evntStat = evntStat;
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
	public int getReltnProdctSeqOne() {
		return reltnProdctSeqOne;
	}
	public void setReltnProdctSeqOne(int reltnProdctSeqOne) {
		this.reltnProdctSeqOne = reltnProdctSeqOne;
	}
	public int getReltnProdctSeqTwo() {
		return reltnProdctSeqTwo;
	}
	public void setReltnProdctSeqTwo(int reltnProdctSeqTwo) {
		this.reltnProdctSeqTwo = reltnProdctSeqTwo;
	}
	public int getReltnProdctSeqThree() {
		return reltnProdctSeqThree;
	}
	public void setReltnProdctSeqThree(int reltnProdctSeqThree) {
		this.reltnProdctSeqThree = reltnProdctSeqThree;
	}
	public int getProdctSechPriceOne() {
		return prodctSechPriceOne;
	}
	public void setProdctSechPriceOne(int prodctSechPriceOne) {
		this.prodctSechPriceOne = prodctSechPriceOne;
	}
	public int getProdctSechPriceTwo() {
		return prodctSechPriceTwo;
	}
	public void setProdctSechPriceTwo(int prodctSechPriceTwo) {
		this.prodctSechPriceTwo = prodctSechPriceTwo;
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
	public int getProdctNetinc() {
		return prodctNetinc;
	}
	public void setProdctNetinc(int prodctNetinc) {
		this.prodctNetinc = prodctNetinc;
	}
	public double getProdctMargnrat() {
		return prodctMargnrat;
	}
	public void setProdctMargnrat(double prodctMargnrat) {
		this.prodctMargnrat = prodctMargnrat;
	}
	public double getProdctSalePric() {
		return prodctSalePric;
	}
	public void setProdctSalePric(double prodctSalePric) {
		this.prodctSalePric = prodctSalePric;
	}
	public long getDupliCheck() {
		return dupliCheck;
	}
	public void setDupliCheck(long dupliCheck) {
		this.dupliCheck = dupliCheck;
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
	public int getPageType() {
		return pageType;
	}
	public void setPageType(int pageType) {
		this.pageType = pageType;
	}
	public int getUpdateType() {
		return updateType;
	}
	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}
	public int getCartBridgSeq() {
		return cartBridgSeq;
	}
	public void setCartBridgSeq(int cartBridgSeq) {
		this.cartBridgSeq = cartBridgSeq;
	}
	public int getProdctAmont() {
		return prodctAmont;
	}
	public void setProdctAmont(int prodctAmont) {
		this.prodctAmont = prodctAmont;
	}
	public List<Integer> getCartChkGroup() {
		return cartChkGroup;
	}
	public void setCartChkGroup(List<Integer> cartChkGroup) {
		this.cartChkGroup = cartChkGroup;
	}
	public int getCusSeq() {
		return cusSeq;
	}
	public void setCusSeq(int cusSeq) {
		this.cusSeq = cusSeq;
	}
	public int getProdctType() {
		return prodctType;
	}
	public void setProdctType(int prodctType) {
		this.prodctType = prodctType;
	}
	public int getEvntDiscntRat() {
		return evntDiscntRat;
	}
	public void setEvntDiscntRat(int evntDiscntRat) {
		this.evntDiscntRat = evntDiscntRat;
	}
	public int getEvntBridgSeq() {
		return evntBridgSeq;
	}
	public void setEvntBridgSeq(int evntBridgSeq) {
		this.evntBridgSeq = evntBridgSeq;
	}
	public int getPackgSeq() {
		return packgSeq;
	}
	public void setPackgSeq(int packgSeq) {
		this.packgSeq = packgSeq;
	}
	public String getSelStrtTime() {
		return selStrtTime;
	}
	public void setSelStrtTime(String selStrtTime) {
		this.selStrtTime = selStrtTime;
	}
	public int getDiscntRat() {
		return discntRat;
	}
	public void setDiscntRat(int discntRat) {
		this.discntRat = discntRat;
	}
	public int getReltnProdctOneSeq() {
		return reltnProdctOneSeq;
	}
	public void setReltnProdctOneSeq(int reltnProdctOneSeq) {
		this.reltnProdctOneSeq = reltnProdctOneSeq;
	}
	public String getReltnProdctOneImg() {
		return reltnProdctOneImg;
	}
	public void setReltnProdctOneImg(String reltnProdctOneImg) {
		this.reltnProdctOneImg = reltnProdctOneImg;
	}
	public String getReltnProdctOneNme() {
		return reltnProdctOneNme;
	}
	public void setReltnProdctOneNme(String reltnProdctOneNme) {
		this.reltnProdctOneNme = reltnProdctOneNme;
	}
	public int getReltnProdctOneSelPric() {
		return reltnProdctOneSelPric;
	}
	public void setReltnProdctOneSelPric(int reltnProdctOneSelPric) {
		this.reltnProdctOneSelPric = reltnProdctOneSelPric;
	}
	public int getReltnProdctOneDiscntPric() {
		return reltnProdctOneDiscntPric;
	}
	public void setReltnProdctOneDiscntPric(int reltnProdctOneDiscntPric) {
		this.reltnProdctOneDiscntPric = reltnProdctOneDiscntPric;
	}
	public int getReltnProdctTwoSeq() {
		return reltnProdctTwoSeq;
	}
	public void setReltnProdctTwoSeq(int reltnProdctTwoSeq) {
		this.reltnProdctTwoSeq = reltnProdctTwoSeq;
	}
	public String getReltnProdctTwoImg() {
		return reltnProdctTwoImg;
	}
	public void setReltnProdctTwoImg(String reltnProdctTwoImg) {
		this.reltnProdctTwoImg = reltnProdctTwoImg;
	}
	public String getReltnProdctTwoNme() {
		return reltnProdctTwoNme;
	}
	public void setReltnProdctTwoNme(String reltnProdctTwoNme) {
		this.reltnProdctTwoNme = reltnProdctTwoNme;
	}
	public int getReltnProdctTwoSelPric() {
		return reltnProdctTwoSelPric;
	}
	public void setReltnProdctTwoSelPric(int reltnProdctTwoSelPric) {
		this.reltnProdctTwoSelPric = reltnProdctTwoSelPric;
	}
	public int getReltnProdctTwoDiscntPric() {
		return reltnProdctTwoDiscntPric;
	}
	public void setReltnProdctTwoDiscntPric(int reltnProdctTwoDiscntPric) {
		this.reltnProdctTwoDiscntPric = reltnProdctTwoDiscntPric;
	}
	public int getReltnProdctThreeSeq() {
		return reltnProdctThreeSeq;
	}
	public void setReltnProdctThreeSeq(int reltnProdctThreeSeq) {
		this.reltnProdctThreeSeq = reltnProdctThreeSeq;
	}
	public String getReltnProdctThreeImg() {
		return reltnProdctThreeImg;
	}
	public void setReltnProdctThreeImg(String reltnProdctThreeImg) {
		this.reltnProdctThreeImg = reltnProdctThreeImg;
	}
	public String getReltnProdctThreeNme() {
		return reltnProdctThreeNme;
	}
	public void setReltnProdctThreeNme(String reltnProdctThreeNme) {
		this.reltnProdctThreeNme = reltnProdctThreeNme;
	}
	public int getReltnProdctThreeSelPric() {
		return reltnProdctThreeSelPric;
	}
	public void setReltnProdctThreeSelPric(int reltnProdctThreeSelPric) {
		this.reltnProdctThreeSelPric = reltnProdctThreeSelPric;
	}
	public int getReltnProdctThreeDiscntPric() {
		return reltnProdctThreeDiscntPric;
	}
	public void setReltnProdctThreeDiscntPric(int reltnProdctThreeDiscntPric) {
		this.reltnProdctThreeDiscntPric = reltnProdctThreeDiscntPric;
	}
	public String getSelEndTime() {
		return selEndTime;
	}
	public void setSelEndTime(String selEndTime) {
		this.selEndTime = selEndTime;
	}
	public int getSelStrtTimeCount() {
		return selStrtTimeCount;
	}
	public void setSelStrtTimeCount(int selStrtTimeCount) {
		this.selStrtTimeCount = selStrtTimeCount;
	}
	
}
