package net.su.market.valueObject;
/**
	 * 이달의 대장남 관련  VO입니다.
	 * 
	 * @see   net.su.market.valueObject.DJNValueObject
	 * @version  1.0 
	 * @ author 시상일, 2016/08/24
*/
public class DJNValueObject {
	
	private int empSeq; //직원고유값
	private String selectMon; //조회 기준월
	private String empNme; //직원이름
	private String empDuty; //직원직책
	private String empTask; //직원업무
	private int empRecmndPoint = 0; //직원추천 포인트
	private int custmrRecmndPoint = 0; //고객추천 포인트
	private int deliContPoint = 0; //배달횟수 포인트
	private int custmrPraisPoint = 0; //고객칭찬 포인트
	private int totPoint; //포인트 합계
	private String empImg; //직원사진.
	private String dat;	// 투표기간

	public String getDat() {
		return dat;
	}
	public void setDat(String dat) {
		this.dat = dat;
	}
	public int getEmpSeq() {
		return empSeq;
	}
	public void setEmpSeq(int empSeq) {
		this.empSeq = empSeq;
	}
	public String getSelectMon() {
		return selectMon;
	}
	public void setSelectMon(String selectMon) {
		this.selectMon = selectMon;
	}
	public String getEmpNme() {
		return empNme;
	}
	public void setEmpNme(String empNme) {
		this.empNme = empNme;
	}
	public String getEmpDuty() {
		return empDuty;
	}
	public void setEmpDuty(String empDuty) {
		this.empDuty = empDuty;
	}
	public String getEmpTask() {
		return empTask;
	}
	public void setEmpTask(String empTask) {
		this.empTask = empTask;
	}
	public int getEmpRecmndPoint() {
		return empRecmndPoint;
	}
	public void setEmpRecmndPoint(int empRecmndPoint) {
		this.empRecmndPoint = empRecmndPoint;
	}
	public int getCustmrRecmndPoint() {
		return custmrRecmndPoint;
	}
	public void setCustmrRecmndPoint(int custmrRecmndPoint) {
		this.custmrRecmndPoint = custmrRecmndPoint;
	}
	public int getDeliContPoint() {
		return deliContPoint;
	}
	public void setDeliContPoint(int deliContPoint) {
		this.deliContPoint = deliContPoint;
	}
	public int getCustmrPraisPoint() {
		return custmrPraisPoint;
	}
	public void setCustmrPraisPoint(int custmrPraisPoint) {
		this.custmrPraisPoint = custmrPraisPoint;
	}
	public int getTotPoint() {
		return totPoint;
	}
	public void setTotPoint(int totPoint) {
		this.totPoint = totPoint;
	}
	public String getEmpImg() {
		return empImg;
	}
	public void setEmpImg(String empImg) {
		this.empImg = empImg;
	}
}
