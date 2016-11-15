package net.su.market.service;

import java.util.List;

import net.su.login.valueObject.LoginValueObject;
import net.su.market.valueObject.DJNPointValueObject;
import net.su.market.valueObject.DJNValueObject;
import net.su.market.valueObject.RecmndResnValueObject;
import net.su.market.valueObject.RecmndValueObject;

public interface DJNService {
	
	/**
		* 오늘의 년월(YY-MM)을 조회하는 메서드입니다.
		*
		* @param   
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	public String selectTYearMonth() throws Exception; 
	
	/**
		* 이달의 대장남 정보를 조회하는 메서드입니다.
		*
		* @param   DJNValueObject
		* @return  DJNValueObject
		* @exception  예외처리 상황을 적어주세요
	*/
	public DJNValueObject selectDJN(DJNValueObject djnVo) throws Exception; 
	
	/**
		* 대장남 포인트 구분(포인트 값 유무)을 조회하는 메서드입니다.
		*
		* @param   DJNValueObject
		* @return  DJNValueObject
		* @exception  예외처리 상황을 적어주세요
	*/
	public int selectDJNPointDivision(DJNValueObject djnVo) throws Exception;
	
	/**
		* 이달의 대장남 고객 추천사유 조회하는 메서드입니다.
		*
		* @param   DJNValueObject
		* @return  List<RecmndResnValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<RecmndResnValueObject> selectDjnRecmndResn(DJNValueObject djnVo2) throws Exception; 
	
	/**
		* 직원별 대장남 포인트 조회하는 메서드입니다.
		*
		* @param   DJNValueObject
		* @return  List<DJNValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<DJNValueObject> selectDJNPointList(DJNValueObject djnVo) throws Exception; 

	/**
		* 대장남 포인트 선정기준을 조회하는 메서드입니다.
		*
		* @param   
		* @return  DJNPointValueObject
		* @exception  예외처리 상황을 적어주세요
	*/
	public DJNPointValueObject selectDjnPointPerct() throws Exception; 
	
	/**
		* 직원별 고객 추천사유를 조회하는 메서드입니다.
		*
		* @param   DJNValueObject
		* @return  List<RecmndResnValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<RecmndResnValueObject> selectRecmndResn(DJNValueObject djnVo) throws Exception; 
	
	/**
		*대장남 포인트 선정기준을 수정하는 메서드입니다.
		*
		* @param   DJNPointValueObject
		* @return  updateDjnPointPerct
		* @exception  예외처리 상황을 적어주세요
	*/
	public void updateDjnPointPerct(DJNPointValueObject djnPointVo) throws Exception; 
	
	/**
		*모든직원 리스트를 조회하는 메서드입니다.
		*
		* @param   
		* @return  List<DJNValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<DJNValueObject> selectEmpList() throws Exception; 
	
	/**
		*(수상자 제외)직원 리스트를 조회하는 메서드입니다.
		*
		* @param   int
		* @return  List<DJNValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<DJNValueObject> selectExceptAwrdEmpList(int empSeq) throws Exception; 
	
	/**
		*직원 추천 메서드입니다.
		*
		* @param   RecmndValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void recmndEmp(RecmndValueObject recmndVo) throws Exception; 

	/**
		*직원 추천 여부 확인 메서드 입니다.
		*
		* @param   LoginValueObject
		* @return  int
		* @exception  예외처리 상황을 적어주세요
	*/
	public int memRecmnChck(LoginValueObject loginUser) throws Exception;
}
