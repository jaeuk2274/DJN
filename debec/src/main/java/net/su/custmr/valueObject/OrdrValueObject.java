package net.su.custmr.valueObject;

import java.util.List;

import net.su.login.valueObject.PageValueObject;

/**
 * 주문  관련  VO입니다.
 * 
 * @see   net.su.custmr.valueObject.OrdrValueObject
 * @version  1.0 
 * @ author 최재욱, 2016/08/09
 */
public class OrdrValueObject extends PageValueObject{

	// 주문번호 tb
	private int ordrNumbrSeq; // 주문번호
	private String ordrDat; // 주문 일자
	private int custmrSeq; // 고객 번호
	private String custmrNme; // 주문자 (고객)이름	
	private String custmrMobl; // 주문자 (고객)휴대전화
	private String recvrNme; // 수령자 이름
	private String recvrMobl; // 수령자 휴대전화
	private String recvrPostcd; // 수령자 주소 우편번호
	private String recvrAddrss; // 수령자 주소 
	private String recvrDetlAddrss; // 수령자 상세주소, 앱
	private String recvrDetlAddress; // 수령자 상세주소, 웹
	private String hopDelvrDat; // 희망 배송 일자
	private String hopDelvrTim; // 희망 배송 시간	
	private int ordrNumbrCount; // 상태별 주문횟수
	private String ordrCanlDat; // 주문취소날짜
	private String ordrPamntDat; // 주문결제날짜
	private String reqmnt; // 주문 희망사항
		
	private int empSeq; // 배달원, 직원 고유값
	private String empNme; // 배달원 이름
	private String empMobl; // 배달원 휴대전화		
	private String pamntMethd; // 결제 방식
	private String recvMethd; // 수령 방식

	private String ordrStat; // 주문 상태	
	private String cartImg; // 임시 이미지 저장
	private String cartImg1; // 주문 장바구니 이미지1
	private String cartImg2; // 주문 장바구니 이미지2
	private String cartImg3; // 주문 장바구니 이미지3	
	private int selPric; // 상품 판매가	
	private int purchsPric; // 상품 할인가	
	
	// 주문 상품리스트
	private long prodctSeq; //주문 상품번호
	private String prodctNme; // 주문 상품명
	private String mainImg; //상품 메인이미지
	private String ordrAmont; // 주문 상품 수량	
	private int ordrProdctAmont; // 주문 상품 전체 수량
	private int allPric; //주문 상품 전체 가격
	
	// 검색, 실시간
	private int ordrStatSeq; // 주문 상태 조건 검색
	private int pricSum; // 주문건당 전체 가격의 합
	private int prodctCount; // 주문건당 전체 상품 개수
	private String firstProdctNme; // 주문건당 첫번째 상품 이름		
	// 주문 목록 검색 필요 정보
	private String ordrSechText; // 고객명 검색어
	private String ordrSechRecvMethd = "all"; // 수령 방식 검색 조건
	private String ordrSechPamntMethd = "all"; // 결제 방식 검색 조건	
	// 전화 주문 등록 구분 필요 정보
	private String custmrClassify = "비회원"; // 고객 분류
	// 일괄 변경 관련 배열
	private int ordrChkGroup[] ; // 주문 상태 일괄 변경을 위한 배열
	// 돌아가기, 실시간 or 주문리스트 구분
	private String backPage="실시간";
	// 이미지 일괄 삭제
	private int imgChkGrop[]; // 이미지 체크 그룹
	// 직원
	private String empImg; // 직원 이미지
	
	// 배송지 관리
	private int shipngPlcSeq=0; // 배송지 seq
	private String shipngPlcNme=""; // 배송지 이름
	
	// 주문 결제 관련
	private List<Integer> prodctSeqGroup = null;
	private List<Integer> packgSeqGroup = null;
	private String prodctType;
	
	//공동 구매 관련
	private int gropPurchsProdctSeq; //공동구매 상품 SEQ
	
	private String recmndCheck; // 주문 추천 여부
	
	
	
	public String getRecmndCheck() {
		return recmndCheck;
	}
	public void setRecmndCheck(String recmndCheck) {
		this.recmndCheck = recmndCheck;
	}
	public int getGropPurchsProdctSeq() {
		return gropPurchsProdctSeq;
	}
	public void setGropPurchsProdctSeq(int gropPurchsProdctSeq) {
		this.gropPurchsProdctSeq = gropPurchsProdctSeq;
	}
	public String getProdctType() {
		return prodctType;
	}
	public void setProdctType(String prodctType) {
		this.prodctType = prodctType;
	}
	public String getReqmnt() {
		return reqmnt;
	}
	public void setReqmnt(String reqmnt) {
		this.reqmnt = reqmnt;
	}
	public List<Integer> getProdctSeqGroup() {
		return prodctSeqGroup;
	}
	public void setProdctSeqGroup(List<Integer> prodctSeqGroup) {
		this.prodctSeqGroup = prodctSeqGroup;
	}
	public List<Integer> getPackgSeqGroup() {
		return packgSeqGroup;
	}
	public void setPackgSeqGroup(List<Integer> packgSeqGroup) {
		this.packgSeqGroup = packgSeqGroup;
	}
	public String getMainImg() {
		return mainImg;
	}
	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}
	public String getOrdrPamntDat() {
		return ordrPamntDat;
	}
	public void setOrdrPamntDat(String ordrPamntDat) {
		this.ordrPamntDat = ordrPamntDat;
	}
	public String getOrdrCanlDat() {
		return ordrCanlDat;
	}
	public void setOrdrCanlDat(String ordrCanlDat) {
		this.ordrCanlDat = ordrCanlDat;
	}
	public int getAllPric() {
		return allPric;
	}
	public void setAllPric(int allPric) {
		this.allPric = allPric;
	}
	public int getOrdrProdctAmont() {
		return ordrProdctAmont;
	}
	public void setOrdrProdctAmont(int ordrProdctAmont) {
		this.ordrProdctAmont = ordrProdctAmont;
	}
	public long getProdctSeq() {
		return prodctSeq;
	}
	public void setProdctSeq(long prodctSeq) {
		this.prodctSeq = prodctSeq;
	}
	public int getCustmrSeq() {
		return custmrSeq;
	}
	public void setCustmrSeq(int custmrSeq) {
		this.custmrSeq = custmrSeq;
	}
	public int getOrdrNumbrCount() {
		return ordrNumbrCount;
	}
	public void setOrdrNumbrCount(int ordrNumbrCount) {
		this.ordrNumbrCount = ordrNumbrCount;
	}
	public int getPurchsPric() {
		return purchsPric;
	}
	public void setPurchsPric(int purchsPric) {
		this.purchsPric = purchsPric;
	}
	public int getShipngPlcSeq() {
		return shipngPlcSeq;
	}
	public void setShipngPlcSeq(int shipngPlcSeq) {
		this.shipngPlcSeq = shipngPlcSeq;
	}
	public String getShipngPlcNme() {
		return shipngPlcNme;
	}
	public void setShipngPlcNme(String shipngPlcNme) {
		this.shipngPlcNme = shipngPlcNme;
	}
	public String getEmpImg() {
		return empImg;
	}
	public void setEmpImg(String empImg) {
		this.empImg = empImg;
	}
	public int[] getImgChkGrop() {
		return imgChkGrop;
	}
	public void setImgChkGrop(int[] imgChkGrop) {
		this.imgChkGrop = imgChkGrop;
	}
	public String getCartImg() {
		return cartImg;
	}
	public void setCartImg(String cartImg) {
		this.cartImg = cartImg;
	}
	public int getEmpSeq() {
		return empSeq;
	}
	public void setEmpSeq(int empSeq) {
		this.empSeq = empSeq;
	}
	public String getBackPage() {
		return backPage;
	}
	public void setBackPage(String backPage) {
		this.backPage = backPage;
	}
	public int[] getOrdrChkGroup() {
		return ordrChkGroup;
	}
	public void setOrdrChkGroup(int[] ordrChkGroup) {
		this.ordrChkGroup = ordrChkGroup;
	}
	public int getOrdrNumbrSeq() {
		return ordrNumbrSeq;
	}
	public void setOrdrNumbrSeq(int ordrNumbrSeq) {
		this.ordrNumbrSeq = ordrNumbrSeq;
	}
	public String getOrdrDat() {
		return ordrDat;
	}
	public void setOrdrDat(String ordrDat) {
		this.ordrDat = ordrDat;
	}
	public String getCustmrNme() {
		return custmrNme;
	}
	public void setCustmrNme(String custmrNme) {
		this.custmrNme = custmrNme;
	}
	public String getCustmrMobl() {
		return custmrMobl;
	}
	public void setCustmrMobl(String custmrMobl) {
		this.custmrMobl = custmrMobl;
	}
	public String getRecvrNme() {
		return recvrNme;
	}
	public void setRecvrNme(String recvrNme) {
		this.recvrNme = recvrNme;
	}
	public String getRecvrMobl() {
		return recvrMobl;
	}
	public void setRecvrMobl(String recvrMobl) {
		this.recvrMobl = recvrMobl;
	}
	public String getRecvrPostcd() {
		return recvrPostcd;
	}
	public void setRecvrPostcd(String recvrPostcd) {
		this.recvrPostcd = recvrPostcd;
	}
	public String getRecvrAddrss() {
		return recvrAddrss;
	}
	public void setRecvrAddrss(String recvrAddrss) {
		this.recvrAddrss = recvrAddrss;
	}
	public String getRecvrDetlAddrss() {
		return recvrDetlAddrss;
	}
	public void setRecvrDetlAddrss(String recvrDetlAddrss) {
		this.recvrDetlAddrss = recvrDetlAddrss;
	}
	public String getHopDelvrDat() {
		return hopDelvrDat;
	}
	public void setHopDelvrDat(String hopDelvrDat) {
		this.hopDelvrDat = hopDelvrDat;
	}
	public String getHopDelvrTim() {
		return hopDelvrTim;
	}
	public void setHopDelvrTim(String hopDelvrTim) {
		this.hopDelvrTim = hopDelvrTim;
	}
	public String getEmpNme() {
		return empNme;
	}
	public void setEmpNme(String empNme) {
		this.empNme = empNme;
	}
	public String getEmpMobl() {
		return empMobl;
	}
	public void setEmpMobl(String empMobl) {
		this.empMobl = empMobl;
	}
	public String getPamntMethd() {
		return pamntMethd;
	}
	public void setPamntMethd(String pamntMethd) {
		this.pamntMethd = pamntMethd;
	}
	public String getRecvMethd() {
		return recvMethd;
	}
	public void setRecvMethd(String recvMethd) {
		this.recvMethd = recvMethd;
	}
	public String getOrdrStat() {
		return ordrStat;
	}
	public void setOrdrStat(String ordrStat) {
		this.ordrStat = ordrStat;
	}
	public String getCartImg1() {
		return cartImg1;
	}
	public void setCartImg1(String cartImg1) {
		this.cartImg1 = cartImg1;
	}
	public String getCartImg2() {
		return cartImg2;
	}
	public void setCartImg2(String cartImg2) {
		this.cartImg2 = cartImg2;
	}
	public String getCartImg3() {
		return cartImg3;
	}
	public void setCartImg3(String cartImg3) {
		this.cartImg3 = cartImg3;
	}
	public int getSelPric() {
		return selPric;
	}
	public void setSelPric(int selPric) {
		this.selPric = selPric;
	}
	public String getProdctNme() {
		return prodctNme;
	}
	public void setProdctNme(String prodctNme) {
		this.prodctNme = prodctNme;
	}
	public String getOrdrAmont() {
		return ordrAmont;
	}
	public void setOrdrAmont(String ordrAmont) {
		this.ordrAmont = ordrAmont;
	}
	public int getOrdrStatSeq() {
		return ordrStatSeq;
	}
	public void setOrdrStatSeq(int ordrStatSeq) {
		this.ordrStatSeq = ordrStatSeq;
	}
	public int getPricSum() {
		return pricSum;
	}
	public void setPricSum(int pricSum) {
		this.pricSum = pricSum;
	}
	public int getProdctCount() {
		return prodctCount;
	}
	public void setProdctCount(int prodctCount) {
		this.prodctCount = prodctCount;
	}
	public String getFirstProdctNme() {
		return firstProdctNme;
	}
	public void setFirstProdctNme(String firstProdctNme) {
		this.firstProdctNme = firstProdctNme;
	}
	public String getOrdrSechText() {
		return ordrSechText;
	}
	public void setOrdrSechText(String ordrSechText) {
		this.ordrSechText = ordrSechText;
	}
	public String getOrdrSechRecvMethd() {
		return ordrSechRecvMethd;
	}
	public void setOrdrSechRecvMethd(String ordrSechRecvMethd) {
		this.ordrSechRecvMethd = ordrSechRecvMethd;
	}
	public String getOrdrSechPamntMethd() {
		return ordrSechPamntMethd;
	}
	public void setOrdrSechPamntMethd(String ordrSechPamntMethd) {
		this.ordrSechPamntMethd = ordrSechPamntMethd;
	}
	public String getCustmrClassify() {
		return custmrClassify;
	}
	public void setCustmrClassify(String custmrClassify) {
		this.custmrClassify = custmrClassify;
	}
	public String getRecvrDetlAddress() {
		return recvrDetlAddress;
	}
	public void setRecvrDetlAddress(String recvrDetlAddress) {
		this.recvrDetlAddress = recvrDetlAddress;
	}
	
}
