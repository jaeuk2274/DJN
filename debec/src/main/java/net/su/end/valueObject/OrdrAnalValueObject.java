package net.su.end.valueObject;

public class OrdrAnalValueObject extends SalsValueObject{
	// 일,월 주문 분석
	private int calOrdrCont; //전화주문수
	private int appOrdrCont; //앱 주문수
	private int calSalsPric; //전화주문 매출액
	private int appSalsPric; //앱 매출액
	
	// 상품별 주문분석
	private long prodctSeq; //상품코드
	private String prodctNme; //상품명
	private int selPric; //판매가
	private int ordrAmont; //판매수량
	private int selTot; //판매합계
	private String rank; //판매순위
		
	public int getCalOrdrCont() {
		return calOrdrCont;
	}
	public void setCalOrdrCont(int calOrdrCont) {
		this.calOrdrCont = calOrdrCont;
	}
	public int getAppOrdrCont() {
		return appOrdrCont;
	}
	public void setAppOrdrCont(int appOrdrCont) {
		this.appOrdrCont = appOrdrCont;
	}
	public int getCalSalsPric() {
		return calSalsPric;
	}
	public void setCalSalsPric(int calSalsPric) {
		this.calSalsPric = calSalsPric;
	}
	public int getAppSalsPric() {
		return appSalsPric;
	}
	public void setAppSalsPric(int appSalsPric) {
		this.appSalsPric = appSalsPric;
	}

	public String getProdctNme() {
		return prodctNme;
	}
	public void setProdctNme(String prodctNme) {
		this.prodctNme = prodctNme;
	}
	public int getSelPric() {
		return selPric;
	}
	public void setSelPric(int selPric) {
		this.selPric = selPric;
	}
	public int getOrdrAmont() {
		return ordrAmont;
	}
	public void setOrdrAmont(int ordrAmont) {
		this.ordrAmont = ordrAmont;
	}
	public int getSelTot() {
		return selTot;
	}
	public void setSelTot(int selTot) {
		this.selTot = selTot;
	}
	public long getProdctSeq() {
		return prodctSeq;
	}
	public void setProdctSeq(long prodctSeq) {
		this.prodctSeq = prodctSeq;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
}
