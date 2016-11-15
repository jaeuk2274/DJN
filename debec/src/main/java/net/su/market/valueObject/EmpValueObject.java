package net.su.market.valueObject;

/**
 * 직원관리 VO입니다.
 * 
 * @see   net.su.market.valueObject.EmpValueObject
 * @version  1.0 
 * @ author 이인호, 2016/08/09
 */

public class EmpValueObject extends VactnValueObject {
	
	/* 검색 조건 */
	private int searchCnd = 0;//검색 조건
	private String searchWrd = "";//검색 단어
	private int searchAry = 0;//정렬 조건
	
	/* 직원 정보 */
	private int empSeq; //직원 고유값
	private String id; //아이디
	private String pw; //비밀번호
	private String nme; //이름
	private String gendr; //성별
	private int postcd; //우편번호
	private String adrs; //주소
	private String detlAdrs; //상세주소
	private String birth; //생년월일
	private String hirDat; //입사일
	private String tel; //전화번호
	private String mobl; //휴대전화
	private String duty; //직책
	private String task; //업무
	private String stat; //상태
	private String imgRot; //이미지 파일 경로
	private int empCount; //회원정보 확인값
	private String token; //직원 토큰값
	private String pushCheck; //푸쉬알림 체크값
	private int getEmpSeq;	// 직원 추천을 받는 empSeq
	private int giveEmpSeq;	// 직원 추천을 하는 empSeq

	
	
	public int getGetEmpSeq() {
		return getEmpSeq;
	}
	public void setGetEmpSeq(int getEmpSeq) {
		this.getEmpSeq = getEmpSeq;
	}
	public int getGiveEmpSeq() {
		return giveEmpSeq;
	}
	public void setGiveEmpSeq(int giveEmpSeq) {
		this.giveEmpSeq = giveEmpSeq;
	}
	public String getPushCheck() {
		return pushCheck;
	}
	public void setPushCheck(String pushCheck) {
		this.pushCheck = pushCheck;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getSearchCnd() {
		return searchCnd;
	}
	public void setSearchCnd(int searchCnd) {
		this.searchCnd = searchCnd;
	}
	public String getSearchWrd() {
		return searchWrd;
	}
	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}
	public int getSearchAry() {
		return searchAry;
	}
	public void setSearchAry(int searchAry) {
		this.searchAry = searchAry;
	}
	public int getEmpSeq() {
		return empSeq;
	}
	public void setEmpSeq(int empSeq) {
		this.empSeq = empSeq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNme() {
		return nme;
	}
	public void setNme(String nme) {
		this.nme = nme;
	}
	public String getGendr() {
		return gendr;
	}
	public void setGendr(String gendr) {
		this.gendr = gendr;
	}
	public int getPostcd() {
		return postcd;
	}
	public void setPostcd(int postcd) {
		this.postcd = postcd;
	}
	public String getAdrs() {
		return adrs;
	}
	public void setAdrs(String adrs) {
		this.adrs = adrs;
	}
	public String getDetlAdrs() {
		return detlAdrs;
	}
	public void setDetlAdrs(String detlAdrs) {
		this.detlAdrs = detlAdrs;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getHirDat() {
		return hirDat;
	}
	public void setHirDat(String hirDat) {
		this.hirDat = hirDat;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobl() {
		return mobl;
	}
	public void setMobl(String mobl) {
		this.mobl = mobl;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getImgRot() {
		return imgRot;
	}
	public void setImgRot(String imgRot) {
		this.imgRot = imgRot;
	}
   public int getEmpCount() {
      return empCount;
   }
   public void setEmpCount(int empCount) {
      this.empCount = empCount;
   }
   
}