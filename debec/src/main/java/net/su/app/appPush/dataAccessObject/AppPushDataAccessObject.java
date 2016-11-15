package net.su.app.appPush.dataAccessObject;

import java.util.HashMap;
import java.util.List;

import net.su.custmr.valueObject.CustmrValueObject;
import net.su.logger.Logger;
import net.su.market.valueObject.EmpValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class AppPushDataAccessObject extends SqlSessionDaoSupport{	
	/**
 	* 고객 토근값 조회 하는 메서드
    *
    * @param   void
    * @return  List<String>
    * @exception  Exception
    */
	public List<String> selectWebCusmtrToken() throws Exception{	
		List<String> token = getSqlSession().selectList("appPushMapper.selectWebCustmrToken");
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
		String token = getSqlSession().selectOne("appPushMapper.selectCusmtrToken", custmrSeq);
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
		CustmrValueObject custmrVo = getSqlSession().selectOne("appPushMapper.selectCustmrRead", custmrSeq);
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
		List<String> empTokenList = getSqlSession().selectList("appPushMapper.selectEmpToken");
		return empTokenList;
	}	
}
