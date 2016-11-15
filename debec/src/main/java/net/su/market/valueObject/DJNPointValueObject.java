package net.su.market.valueObject;
/**
	 * 대장남 선정기준 관련  VO입니다.
	 * 
	 * @see   net.su.market.valueObject.DJNPointValueObject
	 * @version  1.0 
	 * @ author 시상일, 2016/08/24
 */
public class DJNPointValueObject {

	private int empRecmnd; //직원추천 비율
	private int custmrRecmnd; //고객추천 비율
	private int custmrPrais; //고객칭찬 비율
	private int deliCont; //배달횟수 비율
	
	public int getEmpRecmnd() {
		return empRecmnd;
	}
	public void setEmpRecmnd(int empRecmnd) {
		this.empRecmnd = empRecmnd;
	}
	public int getCustmrRecmnd() {
		return custmrRecmnd;
	}
	public void setCustmrRecmnd(int custmrRecmnd) {
		this.custmrRecmnd = custmrRecmnd;
	}
	public int getCustmrPrais() {
		return custmrPrais;
	}
	public void setCustmrPrais(int custmrPrais) {
		this.custmrPrais = custmrPrais;
	}
	public int getDeliCont() {
		return deliCont;
	}
	public void setDeliCont(int deliCont) {
		this.deliCont = deliCont;
	}
}
