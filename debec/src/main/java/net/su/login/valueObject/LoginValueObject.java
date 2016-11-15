package net.su.login.valueObject;

import net.su.market.valueObject.AthrtyValueObject;

public class LoginValueObject extends AthrtyValueObject {

	private int empSeq;// 회원고유값
	private String empId;// 회원ID
	private String empPw;// 회원PW
	private String empNme;// 회원 이름
	private int idResult;// 중복 결과
	private int idCheck; // 아이디체크
	private int idCount; // 아이디 카운트
	private String empMobl; //회원 휴대폰번호
	private int custmrSeq; // 고객 고유값
	private String custmrId; // 고객 id            
	private String custmrPw; // 고객 비밀번호
	private String custmrNme; // 고객 이름
	private String custmrMobl; // 고객 휴대전화
	private int selectLogin; //로그인 관리자 고객 구분
	private String token; // 토큰
	private String loginDat; // 최종 로그인 일자
	
	
	
	
	public String getLoginDat() {
		return loginDat;
	}
	public void setLoginDat(String loginDat) {
		this.loginDat = loginDat;
	}
	public int getEmpSeq() {
		return empSeq;
	}
	public void setEmpSeq(int empSeq) {
		this.empSeq = empSeq;
	}
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpPw() {
		return empPw;
	}
	public void setEmpPw(String empPw) {
		this.empPw = empPw;
	}
	public String getEmpNme() {
		return empNme;
	}
	public void setEmpNme(String empNme) {
		this.empNme = empNme;
	}
	public String getEmpMobl() {
		return empMobl;
	}
	public void setEmpMobl(String empMobl) {
		this.empMobl = empMobl;
	}
	public int getIdResult() {
		return idResult;
	}
	public void setIdResult(int idResult) {
		this.idResult = idResult;
	}
	public int getIdCheck() {
		return idCheck;
	}
	public void setIdCheck(int idCheck) {
		this.idCheck = idCheck;
	}
	public int getIdCount() {
		return idCount;
	}
	public void setIdCount(int idCount) {
		this.idCount = idCount;
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
	public String getCustmrMobl() {
		return custmrMobl;
	}
	public void setCustmrMobl(String custmrMobl) {
		this.custmrMobl = custmrMobl;
	}
	public int getSelectLogin() {
		return selectLogin;
	}
	public void setSelectLogin(int selectLogin) {
		this.selectLogin = selectLogin;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}