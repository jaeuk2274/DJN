package net.su.custmr.valueObject;

import java.math.BigInteger;

import net.su.login.valueObject.PageValueObject;

public class PushValueObject extends PageValueObject{

	/* 검색 조건 */
	private int searchCnd = 0;//검색 조건
	private String searchWrd = "";//검색 단어
	private int searchAry = 0;//정렬 조건
	private int searchCnt = 0; //정렬 내림차순, 오름차순 조건
	private String searchDateStart = "";//시작 시간
	private String searchDateEnd = "";//마지막 시간
	
	private int pushTempltSeq; // 푸시 알림 템플릿 고유값
	private int pushSeq; //푸시 고유값
	private String pushTitl; // 제목
	private String pushMesg; // 내용
	private String regstrtnDat; // 등록일
	private String regstr; // 등록자
	private String pushUrl; // URL
	private String pushHedln; // 헤드라인
	private String pushDat; //푸시 저장일
	
	private int despRecrdSeq; // 발송내역 고유값
	private int sucsCont; // 성공 횟수
	private int falCont; // 실패 횟수
	private int openCont; // 열어본 횟수
	private int dateOption = 1; // 날짜 옵션
	
	//상품
	private int ordrNumbrSeq; // 주문번호
	private int selPric; // 상품 판매가
	
	private long prodctSeq = 0; // 상품 번호
	private String prodctNme; // 상품 이름
	//거래처
	private String clintNme;	// 거래처 명
	private int clintSeq;	// 거래처 코드
	//고객
	private int custmrSeq; // 고객 고유값
	private String custmrId; // 고객 id
	private String custmrNme; // 고객 이름
	private String custmrGendr; // 고객 성별
	private String custmrToken; //고객 토큰
	
	private long packgSeq; //상품번호
	private String packgType; //구분
	private String packgNme; //패키지 명
	private int allPric; //총 가격
	private String packgDisplayCheck = "all"; //진열여부
	
	private int ordrAmont; //주문 횟수
	
	private int ordrStarDat; //주문 시작기간
	private int ordrEndDat; //주문 종료기간	
	
	private String stat; // 공동구매 상태
	private int gropPurchsSeq; // 공동구매 고유값
	private int pushChkGroup[]; // 푸시  체크 그룹
	
	private int empSeq; //직원 고유값
	
	
	public int getEmpSeq() {
		return empSeq;
	}
	public void setEmpSeq(int empSeq) {
		this.empSeq = empSeq;
	}
	public String getPushUrl() {
		return pushUrl;
	}
	public void setPushUrl(String pushUrl) {
		this.pushUrl = pushUrl;
	}
	public String getPushHedln() {
		return pushHedln;
	}
	public void setPushHedln(String pushHedln) {
		this.pushHedln = pushHedln;
	}
	public String getPushDat() {
		return pushDat;
	}
	public void setPushDat(String pushDat) {
		this.pushDat = pushDat;
	}
	public String getPushTitl() {
		return pushTitl;
	}
	public void setPushTitl(String pushTitl) {
		this.pushTitl = pushTitl;
	}
	public String getPushMesg() {
		return pushMesg;
	}
	public void setPushMesg(String pushMesg) {
		this.pushMesg = pushMesg;
	}
	public int getPushTempltSeq() {
		return pushTempltSeq;
	}
	public void setPushTempltSeq(int pushTempltSeq) {
		this.pushTempltSeq = pushTempltSeq;
	}
	public int getPushSeq() {
		return pushSeq;
	}
	public void setPushSeq(int pushSeq) {
		this.pushSeq = pushSeq;
	}
	public int getSearchCnt() {
		return searchCnt;
	}
	public void setSearchCnt(int searchCnt) {
		this.searchCnt = searchCnt;
	}
	public String getCustmrToken() {
		return custmrToken;
	}
	public void setCustmrToken(String custmrToken) {
		this.custmrToken = custmrToken;
	}
	public int[] getPushChkGroup() {
		return pushChkGroup;
	}
	public void setPushChkGroup(int[] pushChkGroup) {
		this.pushChkGroup = pushChkGroup;
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
	public String getSearchDateStart() {
		return searchDateStart;
	}
	public void setSearchDateStart(String searchDateStart) {
		this.searchDateStart = searchDateStart;
	}
	public String getSearchDateEnd() {
		return searchDateEnd;
	}
	public void setSearchDateEnd(String searchDateEnd) {
		this.searchDateEnd = searchDateEnd;
	}
	public String getRegstrtnDat() {
		return regstrtnDat;
	}
	public void setRegstrtnDat(String regstrtnDat) {
		this.regstrtnDat = regstrtnDat;
	}
	public String getRegstr() {
		return regstr;
	}
	public void setRegstr(String regstr) {
		this.regstr = regstr;
	}
	public int getSucsCont() {
		return sucsCont;
	}
	public void setSucsCont(int sucsCont) {
		this.sucsCont = sucsCont;
	}
	public int getFalCont() {
		return falCont;
	}
	public void setFalCont(int falCont) {
		this.falCont = falCont;
	}
	public int getDateOption() {
		return dateOption;
	}
	public void setDateOption(int dateOption) {
		this.dateOption = dateOption;
	}
	public int getOpenCont() {
		return openCont;
	}
	public void setOpenCont(int openCont) {
		this.openCont = openCont;
	}
	public int getDespRecrdSeq() {
		return despRecrdSeq;
	}
	public void setDespRecrdSeq(int despRecrdSeq) {
		this.despRecrdSeq = despRecrdSeq;
	}
	public int getOrdrNumbrSeq() {
		return ordrNumbrSeq;
	}
	public void setOrdrNumbrSeq(int ordrNumbrSeq) {
		this.ordrNumbrSeq = ordrNumbrSeq;
	}
	public int getSelPric() {
		return selPric;
	}
	public void setSelPric(int selPric) {
		this.selPric = selPric;
	}
	public long getProdctSeq() {
		return prodctSeq;
	}
	public void setProdctSeq(long prodctSeq) {
		this.prodctSeq = prodctSeq;
	}
	public String getProdctNme() {
		return prodctNme;
	}
	public void setProdctNme(String prodctNme) {
		this.prodctNme = prodctNme;
	}
	public String getClintNme() {
		return clintNme;
	}
	public void setClintNme(String clintNme) {
		this.clintNme = clintNme;
	}
	public int getClintSeq() {
		return clintSeq;
	}
	public void setClintSeq(int clintSeq) {
		this.clintSeq = clintSeq;
	}
	public int getCustmrSeq() {
		return custmrSeq;
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
	public long getPackgSeq() {
		return packgSeq;
	}
	public void setPackgSeq(long packgSeq) {
		this.packgSeq = packgSeq;
	}
	public String getPackgType() {
		return packgType;
	}
	public void setPackgType(String packgType) {
		this.packgType = packgType;
	}
	public String getPackgNme() {
		return packgNme;
	}
	public void setPackgNme(String packgNme) {
		this.packgNme = packgNme;
	}
	public int getAllPric() {
		return allPric;
	}
	public void setAllPric(int allPric) {
		this.allPric = allPric;
	}
	public String getPackgDisplayCheck() {
		return packgDisplayCheck;
	}
	public void setPackgDisplayCheck(String packgDisplayCheck) {
		this.packgDisplayCheck = packgDisplayCheck;
	}
	public int getOrdrAmont() {
		return ordrAmont;
	}
	public void setOrdrAmont(int ordrAmont) {
		this.ordrAmont = ordrAmont;
	}
	public int getOrdrStarDat() {
		return ordrStarDat;
	}
	public void setOrdrStarDat(int ordrStarDat) {
		this.ordrStarDat = ordrStarDat;
	}
	public int getOrdrEndDat() {
		return ordrEndDat;
	}
	public void setOrdrEndDat(int ordrEndDat) {
		this.ordrEndDat = ordrEndDat;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public int getGropPurchsSeq() {
		return gropPurchsSeq;
	}
	public void setGropPurchsSeq(int gropPurchsSeq) {
		this.gropPurchsSeq = gropPurchsSeq;
	}
}