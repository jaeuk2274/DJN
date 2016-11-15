package net.su.app.appLogin.valueObject;

public class ShippingPlaceValueObject {
	private int shipngPlcSeq; //배송지 고유값
	private String shipngPlcNme;  //배송지 명
	private int shipngPlcPostcod;  // 배송지 우편번호
	private String shipngPlcAdrs;  // 배송지 주소
	private String shipngPlcDetalAdrs; // 배송지 상세주소
	private String recvrNme;  // 수령자 이름
	private String recvrMobl;  // 수령자 연락처
	private int custmrSeq;  // 고객 고유값 
	private String existCheck; // 기본배송지 체크
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
	public int getShipngPlcPostcod() {
		return shipngPlcPostcod;
	}
	public void setShipngPlcPostcod(int shipngPlcPostcod) {
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
	public int getCustmrSeq() {
		return custmrSeq;
	}
	public void setCustmrSeq(int custmrSeq) {
		this.custmrSeq = custmrSeq;
	}
	public String getExistCheck() {
		return existCheck;
	}
	public void setExistCheck(String existCheck) {
		this.existCheck = existCheck;
	}
}