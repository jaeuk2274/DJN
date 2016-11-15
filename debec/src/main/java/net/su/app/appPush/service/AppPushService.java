package net.su.app.appPush.service;

import java.util.List;

import net.su.custmr.valueObject.CustmrValueObject;
import net.su.market.valueObject.EmpValueObject;


public interface AppPushService {
	/**
 	* 고객 토근값 조회 하는 메서드
    *
    * @param   void
    * @return  List<String>
    * @exception  Exception
    */
	public List<String> selectWebCusmtrToken() throws Exception;
	
	/**
    * 고객 토근값 조회 하는 메서드
    *
    * @param   int
    * @return  String
    * @exception  Exception
    */
	public String selectCusmtrToken(int custmrSeq) throws Exception;
	
	/**
    * 고객 정보 조회 하는 메서드
    *
    * @param   int
    * @return  CustmrValueObject
    * @exception  Exception
    */
	public CustmrValueObject selectCustmrRead(int custmrSeq) throws Exception;
	
	/**
    * 직원 토큰값  조회 하는 메서드
    *
    * @param   void
    * @return  List<String>
    * @exception  Exception
    */
	public List<String> selectEmpToken() throws Exception;
}
