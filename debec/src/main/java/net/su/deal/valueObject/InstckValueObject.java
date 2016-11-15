package net.su.deal.valueObject;

import net.su.login.valueObject.PageValueObject;

public class InstckValueObject extends PageValueObject{
	
// 입고 테이블
	private int instckSeq;		// 입고SEQ
	private int qunt;			// 입고수량
	private String dat;			// 입고일
	private int prodctSeq;		// 상품SEQ
	private int purchsPric;		// 매입가(매입단가)

//	입고내역 팝업
	private String prodctNme;	// 상품명
	private int price;	// 금액(입고수량 * 매입단가) -> 쿼리에서 계산
	
//	입고거래장 테이블
	private int instckExchngFlorSeq;		// 입고거래장 SEQ
	private String instckExchngFlorTitl;	// 입고거래장 제목
	private int instckExchngFlorPric;		// 입고거래장 거래금액
	private String instckExchngFlorImg1;	// 입고거래장 이미지1
	private String instckExchngFlorImg2;	// 입고거래장 이미지2
	private String instckExchngFlorImg3;	// 입고거래장 이미지3
	private String instckExchngFlorRegstrtnDat;	// 입고거래장 등록날짜
	private String instckExchngFlorCnt;			// 입고거래장 내용
	private int clintSeq;						// 거래처SEQ
	
//	입고거래장, 입고내역 검색
	private String beginDatepicker="";		// 입고거래장 등록일 검색조건(시작일)
	private String endDatepicker="";		// 입고거래장 등록일 검색조건 (마지막일)
	
// 입고내역 검색
	private String prodctNmeText="";		// 상품명으로 검색
	
// 거래처 테이블(입고거래장)
	private String clintNme;			// 거래처명

// 입고거래 관리
	private String sechSelectBox="instck_exchng_flor_titl";		// 검색 조건(셀렉트박스)
	private String sechText="";			// 검색 조건(텍스트)
	
// 체크한 입고거래장
	private int changeGroup[];		// 입고거래장 체크박스 배열
	
	
	
	
	
	public int[] getChangeGroup() {
		return changeGroup;
	}
	public void setChangeGroup(int[] changeGroup) {
		this.changeGroup = changeGroup;
	}
	public String getSechSelectBox() {
		return sechSelectBox;
	}
	public void setSechSelectBox(String sechSelectBox) {
		this.sechSelectBox = sechSelectBox;
	}
	public String getSechText() {
		return sechText;
	}
	public void setSechText(String sechText) {
		this.sechText = sechText;
	}
	public String getProdctNmeText() {
		return prodctNmeText;
	}
	public void setProdctNmeText(String prodctNmeText) {
		this.prodctNmeText = prodctNmeText;
	}
	public String getProdctNme() {
		return prodctNme;
	}
	public void setProdctNme(String prodctNme) {
		this.prodctNme = prodctNme;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getInstckSeq() {
		return instckSeq;
	}
	public void setInstckSeq(int instckSeq) {
		this.instckSeq = instckSeq;
	}
	public int getQunt() {
		return qunt;
	}
	public void setQunt(int qunt) {
		this.qunt = qunt;
	}
	public String getDat() {
		return dat;
	}
	public void setDat(String dat) {
		this.dat = dat;
	}
	public int getProdctSeq() {
		return prodctSeq;
	}
	public void setProdctSeq(int prodctSeq) {
		this.prodctSeq = prodctSeq;
	}
	public int getPurchsPric() {
		return purchsPric;
	}
	public void setPurchsPric(int purchsPric) {
		this.purchsPric = purchsPric;
	}
	public String getClintNme() {
		return clintNme;
	}
	public void setClintNme(String clintNme) {
		this.clintNme = clintNme;
	}
	public String getBeginDatepicker() {
		return beginDatepicker;
	}
	public void setBeginDatepicker(String beginDatepicker) {
		this.beginDatepicker = beginDatepicker;
	}
	public String getEndDatepicker() {
		return endDatepicker;
	}
	public void setEndDatepicker(String endDatepicker) {
		this.endDatepicker = endDatepicker;
	}
	public int getInstckExchngFlorSeq() {
		return instckExchngFlorSeq;
	}
	public void setInstckExchngFlorSeq(int instckExchngFlorSeq) {
		this.instckExchngFlorSeq = instckExchngFlorSeq;
	}
	public String getInstckExchngFlorTitl() {
		return instckExchngFlorTitl;
	}
	public void setInstckExchngFlorTitl(String instckExchngFlorTitl) {
		this.instckExchngFlorTitl = instckExchngFlorTitl;
	}
	public int getInstckExchngFlorPric() {
		return instckExchngFlorPric;
	}
	public void setInstckExchngFlorPric(int instckExchngFlorPric) {
		this.instckExchngFlorPric = instckExchngFlorPric;
	}
	public String getInstckExchngFlorImg1() {
		return instckExchngFlorImg1;
	}
	public void setInstckExchngFlorImg1(String instckExchngFlorImg1) {
		this.instckExchngFlorImg1 = instckExchngFlorImg1;
	}
	public String getInstckExchngFlorImg2() {
		return instckExchngFlorImg2;
	}
	public void setInstckExchngFlorImg2(String instckExchngFlorImg2) {
		this.instckExchngFlorImg2 = instckExchngFlorImg2;
	}
	public String getInstckExchngFlorImg3() {
		return instckExchngFlorImg3;
	}
	public void setInstckExchngFlorImg3(String instckExchngFlorImg3) {
		this.instckExchngFlorImg3 = instckExchngFlorImg3;
	}
	public String getInstckExchngFlorRegstrtnDat() {
		return instckExchngFlorRegstrtnDat;
	}
	public void setInstckExchngFlorRegstrtnDat(String instckExchngFlorRegstrtnDat) {
		this.instckExchngFlorRegstrtnDat = instckExchngFlorRegstrtnDat;
	}
	public String getInstckExchngFlorCnt() {
		return instckExchngFlorCnt;
	}
	public void setInstckExchngFlorCnt(String instckExchngFlorCnt) {
		this.instckExchngFlorCnt = instckExchngFlorCnt;
	}
	public int getClintSeq() {
		return clintSeq;
	}
	public void setClintSeq(int clintSeq) {
		this.clintSeq = clintSeq;
	}
	
	
	
}
