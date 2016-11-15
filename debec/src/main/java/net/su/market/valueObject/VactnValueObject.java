package net.su.market.valueObject;

public class VactnValueObject extends AthrtyValueObject {
	
	/* 검색 조건 */
	private int searchDuty = 0;//직책검색 조건
	private int searchType = 0;//종류검색 조건
	private String searchStartDat = "";//시작일검색 단어
	private String searchEndDat = "";//종료일검색 단어
	
	/* 휴가 정보 */
	private int schedlSeq;//휴가 고유값
	private String vactnType;// 휴가종류
	private String schedlResn;//사유
	private String schedlStartDat;//시작일
	private String schedlEndDat;//종료일
	private int term;//휴가 일수
	
	
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public int getSearchDuty() {
		return searchDuty;
	}
	public void setSearchDuty(int searchDuty) {
		this.searchDuty = searchDuty;
	}
	public int getSearchType() {
		return searchType;
	}
	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}
	public String getSearchStartDat() {
		return searchStartDat;
	}
	public void setSearchStartDat(String searchStartDat) {
		this.searchStartDat = searchStartDat;
	}
	public String getSearchEndDat() {
		return searchEndDat;
	}
	public void setSearchEndDat(String searchEndDat) {
		this.searchEndDat = searchEndDat;
	}
	public int getSchedlSeq() {
		return schedlSeq;
	}
	public void setSchedlSeq(int schedlSeq) {
		this.schedlSeq = schedlSeq;
	}
	public String getVactnType() {
		return vactnType;
	}
	public void setVactnType(String vactnType) {
		this.vactnType = vactnType;
	}
	public String getSchedlResn() {
		return schedlResn;
	}
	public void setSchedlResn(String schedlResn) {
		this.schedlResn = schedlResn;
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
}
