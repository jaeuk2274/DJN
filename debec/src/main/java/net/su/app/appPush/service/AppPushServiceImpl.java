package net.su.app.appPush.service;

import java.util.List;

import javax.annotation.Resource;

import net.su.app.appPush.dataAccessObject.AppPushDataAccessObject;
import net.su.custmr.valueObject.CustmrValueObject;
import net.su.market.valueObject.EmpValueObject;

import org.springframework.stereotype.Service;

@Service
public class AppPushServiceImpl implements AppPushService{

	@Resource
	private AppPushDataAccessObject appPushDao;
	
	/**
 	* 고객 토근값 조회 하는 메서드
    *
    * @param   void
    * @return  List<String>
    * @exception  Exception
    */
	public List<String> selectWebCusmtrToken() throws Exception{	
		List<String> token = appPushDao.selectWebCusmtrToken();
		return token;
	}	
	
	/**
    * 고객 토근값 조회 하는 메서드
    *
    * @param   int
    * @return  String
    * @exception  Exception
    */
	public String selectCusmtrToken(int custmrSeq) throws Exception{	
		String token = appPushDao.selectCusmtrToken(custmrSeq);
		return token;
	}	
	
	/**
    * 고객 정보 조회 하는 메서드
    *
    * @param   int
    * @return  CustmrValueObject
    * @exception  Exception
    */
	public CustmrValueObject selectCustmrRead(int custmrSeq) throws Exception{	
		CustmrValueObject custmrVo = appPushDao.selectCustmrRead(custmrSeq);
		return custmrVo;
	}	
	
	/**
    * 직원 토큰값  조회 하는 메서드
    *
    * @param   void
    * @return  List<String>
    * @exception  Exception
    */
	public List<String> selectEmpToken() throws Exception{	
		List<String> empTokenList = appPushDao.selectEmpToken();
		return empTokenList;
	}
}