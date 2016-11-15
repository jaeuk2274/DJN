package net.su.custmr.valueObject;

import net.su.login.valueObject.PageValueObject;

/**
 * 고객관리  관련  VO입니다.
 * 
 * @see   net.su.custmr.valueObject.CustmrValueObject
 * @version  1.0 
 * @ author 최재욱, 2016/08/09
 */
public class CustmrValueObject extends PageValueObject{

	// custmr_tb - 고객 테이블 (시작)
	private int custmrSeq; // 고객 고유값
	private String custmrId; // 고객 id            
	private String custmrPw; // 고객 비밀번호
	private String custmrNme; // 고객 이름
	private String custmrGendr; // 고객 성별
	private String custmrBirth; // 고객 생년월일
	private String custmrTelHp; // 고객 전화번호
	private String custmrMobl; // 고객 휴대전화
	private int custmrPostcd; // 고객 우편번호
	private String custmrAdrs; // 고객 기본주소
	private String custmrDetalAdrs; // 고객 상세주소
	private String custmrEml; // 고객 이메일
	private int custmrCardNumbr; // 고객 카드번호	
	private int custmrNumbr; // 고객 번호
	private String custmrCardCheck; // 고객 카드여부
	private String custmrQuscncCheck; // 고객 휴먼여부
	private String custmrNicknme; // 고객 닉네임
	private String custmrToken; //고객 토큰
	private int shipngPlcSeq; //배송지 고유값
	private String loginDat; // 최종 로그인 일자
	private String dat; // 마지막 접속 경과일
	private String birthdayYear; //년
	private String birthdayMonth; //년
	private String birthdayDay; //년
	// custmr_tb - 고객 테이블 (끝)
	
	// 고객 목록 검색 필요 정보
	private String custmrSechOption = "id"; // 고객 검색 조건
	public String getDat() {
		return dat;
	}
	public void setDat(String dat) {
		this.dat = dat;
	}
	public String getLoginDat() {
		return loginDat;
	}
	public void setLoginDat(String loginDat) {
		this.loginDat = loginDat;
	}
	private String custmrSechText =""; // 고객 검색 검색어
	private String custmrSechCardCheck = "all"; // 고객 검색 카드여부
	//
	private int custmrChkGroup[]; // 그룹으로 한번에 처리하기 위한 고객 SEQ 배열
	// 고객 마이페이지 관련 정보
	private int cartCount;  // 장바구니 관리 총 개수
	private int historyCount;  // 히스토리 관리 총 개수
	//고객정보 확인값
	private int cusCount; //회원정보 확인값
	private char pushCheck; // 푸시알림 체크
	
	private int recmndEmpSeq; // 추천할 직원 seq 
	private int recmndResnCodeSeq; //  추천 이유 코드

	public String getBirthdayYear() {
		return birthdayYear;
	}
	public void setBirthdayYear(String birthdayYear) {
		this.birthdayYear = birthdayYear;
	}
	public String getBirthdayMonth() {
		return birthdayMonth;
	}
	public void setBirthdayMonth(String birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}
	public String getBirthdayDay() {
		return birthdayDay;
	}
	public void setBirthdayDay(String birthdayDay) {
		this.birthdayDay = birthdayDay;
	}
	public int getRecmndEmpSeq() {
		return recmndEmpSeq;
	}
	public void setRecmndEmpSeq(int recmndEmpSeq) {
		this.recmndEmpSeq = recmndEmpSeq;
	}
	public int getRecmndResnCodeSeq() {
		return recmndResnCodeSeq;
	}
	public void setRecmndResnCodeSeq(int recmndResnCodeSeq) {
		this.recmndResnCodeSeq = recmndResnCodeSeq;
	}
	public char getPushCheck() {
		return pushCheck;
	}
	public void setPushCheck(char pushCheck) {
		this.pushCheck = pushCheck;
	}
	public int getCusCount() {
		return cusCount;
	}
	public void setCusCount(int cusCount) {
		this.cusCount = cusCount;
	}
	public int getCustmrSeq() {
		return custmrSeq;
	}
	public int getCartCount() {
		return cartCount;
	}
	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}
	public int getHistoryCount() {
		return historyCount;
	}
	public void setHistoryCount(int historyCount) {
		this.historyCount = historyCount;
	}
	public void setCustmrSeq(int custmrSeq) {
		this.custmrSeq = custmrSeq;
	}
	public String getCustmrId() {
		return custmrId;
	}
	public void setCustmrId(String custmrId) {
		this.custmrId = custmrId;
	}
	public String getCustmrPw() {
		return custmrPw;
	}
	public void setCustmrPw(String custmrPw) {
		this.custmrPw = custmrPw;
	}
	public String getCustmrNme() {
		return custmrNme;
	}
	public void setCustmrNme(String custmrNme) {
		this.custmrNme = custmrNme;
	}
	public String getCustmrGendr() {
		return custmrGendr;
	}
	public void setCustmrGendr(String custmrGendr) {
		this.custmrGendr = custmrGendr;
	}
	public String getCustmrBirth() {
		return custmrBirth;
	}
	public void setCustmrBirth(String custmrBirth) {
		this.custmrBirth = custmrBirth;
	}
	public String getCustmrTelHp() {
		return custmrTelHp;
	}
	public void setCustmrTelHp(String custmrTelHp) {
		this.custmrTelHp = custmrTelHp;
	}
	public String getCustmrMobl() {
		return custmrMobl;
	}
	public void setCustmrMobl(String custmrMobl) {
		this.custmrMobl = custmrMobl;
	}
	public int getCustmrPostcd() {
		return custmrPostcd;
	}
	public void setCustmrPostcd(int custmrPostcd) {
		this.custmrPostcd = custmrPostcd;
	}
	public String getCustmrAdrs() {
		return custmrAdrs;
	}
	public void setCustmrAdrs(String custmrAdrs) {
		this.custmrAdrs = custmrAdrs;
	}
	public String getCustmrDetalAdrs() {
		return custmrDetalAdrs;
	}
	public void setCustmrDetalAdrs(String custmrDetalAdrs) {
		this.custmrDetalAdrs = custmrDetalAdrs;
	}
	public String getCustmrEml() {
		return custmrEml;
	}
	public void setCustmrEml(String custmrEml) {
		this.custmrEml = custmrEml;
	}
	public int getCustmrCardNumbr() {
		return custmrCardNumbr;
	}
	public void setCustmrCardNumbr(int custmrCardNumbr) {
		this.custmrCardNumbr = custmrCardNumbr;
	}
	public int getCustmrNumbr() {
		return custmrNumbr;
	}
	public void setCustmrNumbr(int custmrNumbr) {
		this.custmrNumbr = custmrNumbr;
	}
	public String getCustmrCardCheck() {
		return custmrCardCheck;
	}
	public void setCustmrCardCheck(String custmrCardCheck) {
		this.custmrCardCheck = custmrCardCheck;
	}
	public String getCustmrQuscncCheck() {
		return custmrQuscncCheck;
	}
	public void setCustmrQuscncCheck(String custmrQuscncCheck) {
		this.custmrQuscncCheck = custmrQuscncCheck;
	}
	public String getCustmrNicknme() {
		return custmrNicknme;
	}
	public void setCustmrNicknme(String custmrNicknme) {
		this.custmrNicknme = custmrNicknme;
	}
	public String getCustmrSechOption() {
		return custmrSechOption;
	}
	public void setCustmrSechOption(String custmrSechOption) {
		this.custmrSechOption = custmrSechOption;
	}
	public String getCustmrSechText() {
		return custmrSechText;
	}
	public void setCustmrSechText(String custmrSechText) {
		this.custmrSechText = custmrSechText;
	}
	public String getCustmrSechCardCheck() {
		return custmrSechCardCheck;
	}
	public void setCustmrSechCardCheck(String custmrSechCardCheck) {
		this.custmrSechCardCheck = custmrSechCardCheck;
	}
	public int[] getCustmrChkGroup() {
		return custmrChkGroup;
	}
	public void setCustmrChkGroup(int[] custmrChkGroup) {
		this.custmrChkGroup = custmrChkGroup;
	}
	public String getCustmrToken() {
		return custmrToken;
	}
	public void setCustmrToken(String custmrToken) {
		this.custmrToken = custmrToken;
	}
	public int getShipngPlcSeq() {
		return shipngPlcSeq;
	}
	public void setShipngPlcSeq(int shipngPlcSeq) {
		this.shipngPlcSeq = shipngPlcSeq;
	}
}