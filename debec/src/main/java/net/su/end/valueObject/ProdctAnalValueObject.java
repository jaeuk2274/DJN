package net.su.end.valueObject;
/**
 * 상품분석  VO입니다.
 * 
 * @see   net.su.end.valueObject.ProdctAnalValueObject
 * @version  1.0 
 * @ author 김대현, 2016/09/07
 */

public class ProdctAnalValueObject {
	// view 테이블
	private String ordrDat;
	private String topCatgrNme;
	private String midCatgrNme;
	private String botCatgrNme;
	private String catgrNme;
	
	// 카테고리
	private String selectedTopCatgrNme="대분류";
	private String selectedMidCatgrNme="중분류";
	private String selectedBotCatgrNme="소분류";
	private String catgrSize;
	
	// 출고 테이블
	private int qunt = 0;			// 매출수량, 교환환불수량
	private int instckPric;			// 입고가격
	private int selPric;			// 판매가격
	
	// 상품 테이블
	private String prodctNme;		// 제품명
	private Long prodctSeq;			// 제품PK
	
	// 검색
	private String btnDatSech;	// 일자 버튼 검색
	private String beginDatepicker="";	// 기간 검색 (시작일)
	private String endDatepicker="";	// 기간 검색(마지막일)
	private String prodctNmeSech="";	// 상품명 검색
	private int beginSelPrice=0;		// 상품 검색 시작가격
	private int endSelPrice=0;		// 상품 검색 마지막가격
	
	// 판매 분류 분석 변수
	private int retrnQunt;			// 환불 수량
	private int relQunt;			// 판매 수량
	private int netInc;				// 순이익
	private float marginRat;			// 마진율
	private int totalSelPric; 		// 총 판매액
	private int retrnConTnTotalSelPric; // 환불 수량 포함 판매 금액

	private String startDatSech;		// 시작일
	private String endDatSech;		// 시작일
	private String sechType;		// 시작일
	
	// 상품분석 테이블, 환불비율 차트, 환불상품순위테이블
	private int stckQunt;	// 재고수량
	private int refndRat;	// 환불비율
	private int refndQunt;	// 환불수량
	private int selQunt;	// 판매수량
	private int profit;		// 순이익
	private int margnRat;	// 마진율
	private int selSum;		// 판매금액합계
	
	
	
	public String getCatgrNme() {
		return catgrNme;
	}
	public void setCatgrNme(String catgrNme) {
		this.catgrNme = catgrNme;
	}
	public int getStckQunt() {
		return stckQunt;
	}
	public void setStckQunt(int stckQunt) {
		this.stckQunt = stckQunt;
	}
	public int getRefndQunt() {
		return refndQunt;
	}
	public void setRefndQunt(int refndQunt) {
		this.refndQunt = refndQunt;
	}
	public int getSelQunt() {
		return selQunt;
	}
	public void setSelQunt(int selQunt) {
		this.selQunt = selQunt;
	}
	public int getProfit() {
		return profit;
	}
	public void setProfit(int profit) {
		this.profit = profit;
	}
	public int getMargnRat() {
		return margnRat;
	}
	public void setMargnRat(int margnRat) {
		this.margnRat = margnRat;
	}
	public int getSelSum() {
		return selSum;
	}
	public void setSelSum(int selSum) {
		this.selSum = selSum;
	}
	public int getRefndRat() {
		return refndRat;
	}
	public void setRefndRat(int refndRat) {
		this.refndRat = refndRat;
	}
	public String getOrdrDat() {
		return ordrDat;
	}
	public void setOrdrDat(String ordrDat) {
		this.ordrDat = ordrDat;
	}
	public String getTopCatgrNme() {
		return topCatgrNme;
	}
	public void setTopCatgrNme(String topCatgrNme) {
		this.topCatgrNme = topCatgrNme;
	}
	public String getMidCatgrNme() {
		return midCatgrNme;
	}
	public void setMidCatgrNme(String midCatgrNme) {
		this.midCatgrNme = midCatgrNme;
	}
	public String getBotCatgrNme() {
		return botCatgrNme;
	}
	public void setBotCatgrNme(String botCatgrNme) {
		this.botCatgrNme = botCatgrNme;
	}
	public String getProdctNmeSech() {
		return prodctNmeSech;
	}
	public void setProdctNmeSech(String prodctNmeSech) {
		this.prodctNmeSech = prodctNmeSech;
	}
	public int getBeginSelPrice() {
		return beginSelPrice;
	}
	public void setBeginSelPrice(int beginSelPrice) {
		this.beginSelPrice = beginSelPrice;
	}
	public int getEndSelPrice() {
		return endSelPrice;
	}
	public void setEndSelPrice(int endSelPrice) {
		this.endSelPrice = endSelPrice;
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
	public String getBtnDatSech() {
		return btnDatSech;
	}
	public void setBtnDatSech(String btnDatSech) {
		this.btnDatSech = btnDatSech;
	}
	public String getSelectedTopCatgrNme() {
		return selectedTopCatgrNme;
	}
	public void setSelectedTopCatgrNme(String selectedTopCatgrNme) {
		this.selectedTopCatgrNme = selectedTopCatgrNme;
	}
	public String getSelectedMidCatgrNme() {
		return selectedMidCatgrNme;
	}
	public void setSelectedMidCatgrNme(String selectedMidCatgrNme) {
		this.selectedMidCatgrNme = selectedMidCatgrNme;
	}
	public String getSelectedBotCatgrNme() {
		return selectedBotCatgrNme;
	}
	public void setSelectedBotCatgrNme(String selectedBotCatgrNme) {
		this.selectedBotCatgrNme = selectedBotCatgrNme;
	}
	public String getCatgrSize() {
		return catgrSize;
	}
	public void setCatgrSize(String catgrSize) {
		this.catgrSize = catgrSize;
	}
	public int getQunt() {
		return qunt;
	}
	public void setQunt(int qunt) {
		this.qunt = qunt;
	}
	public int getInstckPric() {
		return instckPric;
	}
	public void setInstckPric(int instckPric) {
		this.instckPric = instckPric;
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
	public Long getProdctSeq() {
		return prodctSeq;
	}
	public void setProdctSeq(Long prodctSeq) {
		this.prodctSeq = prodctSeq;
	}
	public int getRetrnQunt() {
		return retrnQunt;
	}
	public void setRetrnQunt(int retrnQunt) {
		this.retrnQunt = retrnQunt;
	}
	public int getRelQunt() {
		return relQunt;
	}
	public void setRelQunt(int relQunt) {
		this.relQunt = relQunt;
	}
	public int getNetInc() {
		return netInc;
	}
	public void setNetInc(int netInc) {
		this.netInc = netInc;
	}
	public float getMarginRat() {
		return marginRat;
	}
	public void setMarginRat(float marginRat) {
		this.marginRat = marginRat;
	}
	public int getTotalSelPric() {
		return totalSelPric;
	}
	public void setTotalSelPric(int totalSelPric) {
		this.totalSelPric = totalSelPric;
	}
	public String getStartDatSech() {
		return startDatSech;
	}
	public void setStartDatSech(String startDatSech) {
		this.startDatSech = startDatSech;
	}
	public String getEndDatSech() {
		return endDatSech;
	}
	public void setEndDatSech(String endDatSech) {
		this.endDatSech = endDatSech;
	}
	public String getSechType() {
		return sechType;
	}
	public void setSechType(String sechType) {
		this.sechType = sechType;
	}
	public int getRetrnConTnTotalSelPric() {
		return retrnConTnTotalSelPric;
	}
	public void setRetrnConTnTotalSelPric(int retrnConTnTotalSelPric) {
		this.retrnConTnTotalSelPric = retrnConTnTotalSelPric;
	}
	
	
}
