package net.su.app.appMarket.valueObject;

public class AppMarketValueObject {
	
	private long prodctSeq; // 상품seq
	private int custmrSeq; //고객seq
	
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
}
