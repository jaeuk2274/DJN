package net.su.custmr.valueObject;

import net.su.login.valueObject.PageValueObject;

public class CallOrderValueObject extends CustmrValueObject {

	private String custmrClassify = "noCustmr"; // 고객 분류
	private int ordrNumbrSeq; //주문번호 고유값
	private String ordrNme; //주문자 이름
	private String ordrMobl; //주문자 연락처
	private String ordrDat; //주문 날짜
	private String recvrNme; //수령자 이름
	private String recvrMobl; //수령자 연락처
	private String recvrPostcd; //수령자 우편번호
	private String recvrAddrss; //수령자 주소
	private String recvrDetlAddress; //수령자 상세주소
	private String shipngPlc; //배송지 선택
	private String recvrMethd; //수령방식
	private String pamntMethd; //결제방식
	private String reqmnt; //요구사항
	private String hopDelvrTim; //희망배송시간
	private int ordrTypeSeq; //주문종류 고유값
	private int ordrStatSeq; //주문상태 고유값
	private int shipngPlcSeq; //배송지 고유값
	private String shipngPlcNme; //배송지명
	private String shipngPlcPostcod; //배송지 우편번호
	private String shipngPlcAdrs; //배송지 주소
	private String shipngPlcDetalAdrs; //배송지 상세주소
	private long[] prodctSeq_arry;
	private int[] prodctNumber_arry;
	
	public String getCustmrClassify() {
		return custmrClassify;
	}
	public void setCustmrClassify(String custmrClassify) {
		this.custmrClassify = custmrClassify;
	}
	public int getOrdrNumbrSeq() {
		return ordrNumbrSeq;
	}
	public void setOrdrNumbrSeq(int ordrNumbrSeq) {
		this.ordrNumbrSeq = ordrNumbrSeq;
	}
	public String getOrdrNme() {
		return ordrNme;
	}
	public void setOrdrNme(String ordrNme) {
		this.ordrNme = ordrNme;
	}
	public String getOrdrMobl() {
		return ordrMobl;
	}
	public void setOrdrMobl(String ordrMobl) {
		this.ordrMobl = ordrMobl;
	}
	public String getOrdrDat() {
		return ordrDat;
	}
	public void setOrdrDat(String ordrDat) {
		this.ordrDat = ordrDat;
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
	public String getRecvrDetlAddress() {
		return recvrDetlAddress;
	}
	public void setRecvrDetlAddress(String recvrDetlAddress) {
		this.recvrDetlAddress = recvrDetlAddress;
	}
	public String getShipngPlc() {
		return shipngPlc;
	}
	public void setShipngPlc(String shipngPlc) {
		this.shipngPlc = shipngPlc;
	}
	public String getRecvrMethd() {
		return recvrMethd;
	}
	public void setRecvrMethd(String recvrMethd) {
		this.recvrMethd = recvrMethd;
	}
	public String getPamntMethd() {
		return pamntMethd;
	}
	public void setPamntMethd(String pamntMethd) {
		this.pamntMethd = pamntMethd;
	}
	public String getReqmnt() {
		return reqmnt;
	}
	public void setReqmnt(String reqmnt) {
		this.reqmnt = reqmnt;
	}
	public String getHopDelvrTim() {
		return hopDelvrTim;
	}
	public void setHopDelvrTim(String hopDelvrTim) {
		this.hopDelvrTim = hopDelvrTim;
	}
	public int getOrdrTypeSeq() {
		return ordrTypeSeq;
	}
	public void setOrdrTypeSeq(int ordrTypeSeq) {
		this.ordrTypeSeq = ordrTypeSeq;
	}
	public int getOrdrStatSeq() {
		return ordrStatSeq;
	}
	public void setOrdrStatSeq(int ordrStatSeq) {
		this.ordrStatSeq = ordrStatSeq;
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
	public String getShipngPlcPostcod() {
		return shipngPlcPostcod;
	}
	public void setShipngPlcPostcod(String shipngPlcPostcod) {
		this.shipngPlcPostcod = shipngPlcPostcod;
	}
	public String getShipngPlcAdrs() {
		return shipngPlcAdrs;
	}
	public void setShipngPlcAdrs(String shipngPlcAdrs) {
		this.shipngPlcAdrs = shipngPlcAdrs;
	}
	public String getShipngPlcDetalAdrs() {
		return shipngPlcDetalAdrs;
	}
	public void setShipngPlcDetalAdrs(String shipngPlcDetalAdrs) {
		this.shipngPlcDetalAdrs = shipngPlcDetalAdrs;
	}
	public long[] getProdctSeq_arry() {
		return prodctSeq_arry;
	}
	public void setProdctSeq_arry(long[] prodctSeq_arry) {
		this.prodctSeq_arry = prodctSeq_arry;
	}
	public int[] getProdctNumber_arry() {
		return prodctNumber_arry;
	}
	public void setProdctNumber_arry(int[] prodctNumber_arry) {
		this.prodctNumber_arry = prodctNumber_arry;
	}
}