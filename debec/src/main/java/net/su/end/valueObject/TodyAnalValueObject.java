package net.su.end.valueObject;

public class TodyAnalValueObject {
	
// 주문번호 테이블	
	private String ordr_dat;		// 주문날짜
	private int ordrNumbrSeq;		// 주문번호PK
	private int ordrTypeSeq;		// 주문타입PK
	private int ordrAmont;			// 주문수량
	
// 출고 테이블
	private int qunt = 0;			// 매출수량, 교환환불수량
	private int instckPric;			// 입고가격
	private int selPric;			// 판매가격

// 반품 테이블
	private int retrnQunt;			// 반품 수량
	
// 상품 테이블
	private String prodctNme;		// 제품명
	private Long prodctSeq;			// 제품PK
	
// 카테고리
	private String topCatgrNme="대분류";
	private String midCatgrNme="중분류";
	private String botCatgrNme="소분류";
	
// 투데이 리포트
	private int salsPric=0; // 매출액
	private String tody;	// 오늘 날짜(2016년 08월 25일)
	private String day;		// 요일(목요일)
	private String now;		// 현재(2016-08-25 11:49)
	private int todyOrdrAmont;	// 급상승 매출조회 (개수 부분)
	
// 투데이리포트 - 일일매출
	private String salsTime;	// 매출 시간

// 투데이리포트 - 매출현황 등록
	private String excelFile;	// 엑셀파일 경로
	
	
	// 아래는 재욱이꺼
	
// 매출 분석
	private String salsDay; // 매출 일자
	private int purchsPric=0; // 매입액
//	private int salsPric=0; // 매출액
	private int netinc=0; // 순이익
	private double margnRat=0; // 마진율
	
	//카테고리 관련 (콤보박스)

	private String selectedTopCatgrNme="대분류";
	private String selectedMidCatgrNme="중분류";
	private String selectedBotCatgrNme="소분류";
	private String catgrSize;
	
	//검색조건
	private String btnDatSech="1일"; // 버튼 날짜 선택
	private String startDatSech=""; // 날짜 검색 시작일
	private String endDatSech=""; // 날짜 검색 종료일
	private String prodctNmeSech=""; // 상품명 검색
	
	
	
	public int getRetrnQunt() {
		return retrnQunt;
	}
	public void setRetrnQunt(int retrnQunt) {
		this.retrnQunt = retrnQunt;
	}
	public String getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(String excelFile) {
		this.excelFile = excelFile;
	}
	public String getSalsTime() {
		return salsTime;
	}
	public void setSalsTime(String salsTime) {
		this.salsTime = salsTime;
	}
	public int getTodyOrdrAmont() {
		return todyOrdrAmont;
	}
	public void setTodyOrdrAmont(int todyOrdrAmont) {
		this.todyOrdrAmont = todyOrdrAmont;
	}
	public int getQunt() {
		return qunt;
	}
	public void setQunt(int qunt) {
		this.qunt = qunt;
	}
	public String getTody() {
		return tody;
	}
	public void setTody(String tody) {
		this.tody = tody;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getNow() {
		return now;
	}
	public void setNow(String now) {
		this.now = now;
	}
	public String getOrdr_dat() {
		return ordr_dat;
	}
	public void setOrdr_dat(String ordr_dat) {
		this.ordr_dat = ordr_dat;
	}
	public int getOrdrNumbrSeq() {
		return ordrNumbrSeq;
	}
	public void setOrdrNumbrSeq(int ordrNumbrSeq) {
		this.ordrNumbrSeq = ordrNumbrSeq;
	}
	public int getOrdrTypeSeq() {
		return ordrTypeSeq;
	}
	public void setOrdrTypeSeq(int ordrTypeSeq) {
		this.ordrTypeSeq = ordrTypeSeq;
	}
	public Long getProdctSeq() {
		return prodctSeq;
	}
	public void setProdctSeq(Long prodctSeq) {
		this.prodctSeq = prodctSeq;
	}
	public String getProdctNme() {
		return prodctNme;
	}
	public void setProdctNme(String prodctNme) {
		this.prodctNme = prodctNme;
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
	public int getOrdrAmont() {
		return ordrAmont;
	}
	public void setOrdrAmont(int ordrAmont) {
		this.ordrAmont = ordrAmont;
	}
	public String getProdctNmeSech() {
		return prodctNmeSech;
	}
	public void setProdctNmeSech(String prodctNmeSech) {
		this.prodctNmeSech = prodctNmeSech;
	}
	public String getBtnDatSech() {
		return btnDatSech;
	}
	public void setBtnDatSech(String btnDatSech) {
		this.btnDatSech = btnDatSech;
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
	public String getSalsDay() {
		return salsDay;
	}
	public void setSalsDay(String salsDay) {
		this.salsDay = salsDay;
	}
	public int getPurchsPric() {
		return purchsPric;
	}
	public void setPurchsPric(int purchsPric) {
		this.purchsPric = purchsPric;
	}
	public int getSalsPric() {
		return salsPric;
	}
	public void setSalsPric(int salsPric) {
		this.salsPric = salsPric;
	}
	public int getNetinc() {
		return netinc;
	}
	public void setNetinc(int netinc) {
		this.netinc = netinc;
	}
	public double getMargnRat() {
		return margnRat;
	}
	public void setMargnRat(double margnRat) {
		this.margnRat = margnRat;
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
}

