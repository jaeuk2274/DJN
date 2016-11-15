package net.su.deal.valueObject;

import java.util.List;

import net.su.login.valueObject.PageValueObject;

public class ClintValueObject extends PageValueObject{

//	거래처 테이블
	private int clintSeq;	// 거래처 코드
	private String clintRepresntatv;	// 거래처 대표자
	private String clintNum;	// 거래처번호
	private String clintFax;	// 거래처 팩스
	private String clintMangr;	// 거래처 담당자
	private String clintMangrNum;	// 거래처 담당자 번호
	private String clintAdrs;	// 거래처 주소
	private String clintDetl;	// 거래처 상세주소
	private String clintBankAcout;	// 거래처 계좌은행
	private String clintAcoutNum;	// 거래처 계좌번호
	private String clintSurtaxCheck;	// 거래처 부가세 여부
	private String clintNme;	// 거래처 명
	private String clintPostcd;	// 거래처 우편번호
	
	private int clintBridgSeq;		// 거래처 상품 브릿지 seq
	private int clintBridgChkGroup[];   // 거래처 상품 브릿지 seq 체크 그룹 
	private List<Integer> chkList;
	
//	검색	
	private String clintListSelectBox="clint_nme";	// 거래처 목록 검색 셀렉트박스
	private String clintListSearchText="";	// 거래처 목록 검색 텍스트필드
	
// 상품 테이블
	private long prodctSeq;		// 상품seq
	private String mainImg;;	// 상품 이미지
	private String prodctNme;	// 상품명
	private int qunt;			// 재고량
	private int purchsPric;		//매입가
	private int selPric; 		//판매가
	private String displyCheck;	//진열여부
	private int catgrSeq=0;		// 카테고리seq

// ProdctTable.jsp 검색
	private String prodctSechText="";	// 검색어
	
// TempTable.jsp 검색
	private String tempSechText="";		// 검색어	
	private int changeGroup[];		// 거래처 체크박스 배열	
	

	public List<Integer> getChkList() {
		return chkList;
	}

	public void setChkList(List<Integer> chkList) {
		this.chkList = chkList;
	}

	public int getClintSeq() {
		return clintSeq;
	}

	public void setClintSeq(int clintSeq) {
		this.clintSeq = clintSeq;
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

	public String getClintAdrs() {
		return clintAdrs;
	}

	public void setClintAdrs(String clintAdrs) {
		this.clintAdrs = clintAdrs;
	}

	public String getClintDetl() {
		return clintDetl;
	}

	public void setClintDetl(String clintDetl) {
		this.clintDetl = clintDetl;
	}

	public String getClintBankAcout() {
		return clintBankAcout;
	}

	public void setClintBankAcout(String clintBankAcout) {
		this.clintBankAcout = clintBankAcout;
	}

	public String getClintAcoutNum() {
		return clintAcoutNum;
	}

	public void setClintAcoutNum(String clintAcoutNum) {
		this.clintAcoutNum = clintAcoutNum;
	}

	public String getClintSurtaxCheck() {
		return clintSurtaxCheck;
	}

	public void setClintSurtaxCheck(String clintSurtaxCheck) {
		this.clintSurtaxCheck = clintSurtaxCheck;
	}

	public String getClintNme() {
		return clintNme;
	}

	public void setClintNme(String clintNme) {
		this.clintNme = clintNme;
	}

	public String getClintPostcd() {
		return clintPostcd;
	}

	public void setClintPostcd(String clintPostcd) {
		this.clintPostcd = clintPostcd;
	}

	public int getClintBridgSeq() {
		return clintBridgSeq;
	}

	public void setClintBridgSeq(int clintBridgSeq) {
		this.clintBridgSeq = clintBridgSeq;
	}

	public int[] getClintBridgChkGroup() {
		return clintBridgChkGroup;
	}

	public void setClintBridgChkGroup(int[] clintBridgChkGroup) {
		this.clintBridgChkGroup = clintBridgChkGroup;
	}

	public String getClintListSelectBox() {
		return clintListSelectBox;
	}

	public void setClintListSelectBox(String clintListSelectBox) {
		this.clintListSelectBox = clintListSelectBox;
	}

	public String getClintListSearchText() {
		return clintListSearchText;
	}

	public void setClintListSearchText(String clintListSearchText) {
		this.clintListSearchText = clintListSearchText;
	}

	public long getProdctSeq() {
		return prodctSeq;
	}

	public void setProdctSeq(long prodctSeq) {
		this.prodctSeq = prodctSeq;
	}

	public String getMainImg() {
		return mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
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

	public int[] getChangeGroup() {
		return changeGroup;
	}

	public void setChangeGroup(int[] changeGroup) {
		this.changeGroup = changeGroup;
	}
	
}
