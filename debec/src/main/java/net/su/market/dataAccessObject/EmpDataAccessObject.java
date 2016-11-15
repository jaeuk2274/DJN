package net.su.market.dataAccessObject;

import java.util.List;

import net.su.logger.Logger;
import net.su.market.valueObject.EmpValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * 직원관리 메뉴 관련 DAO입니다.
 * 
 * @see   net.su.market.dataAccessObject.EmpDataAccessObject
 * @version  1.0 
 * @ author 이인호, 2016/08/09
 */


@Repository
public class EmpDataAccessObject extends SqlSessionDaoSupport{
	
	/**
	 * 직원관리 직원 수를 조회하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  List<EmpValueObject>
	 * @exception  Exception
	 */
	
	public int selectEmpCount(EmpValueObject empValueObject) throws Exception {
		Logger.info(null);		
		int selectEmpCount = getSqlSession().selectOne("empMapper.selectEmpCount", empValueObject);	
		return selectEmpCount;
	}
	
	/**
	 * 직원관리 직원조회로 이동하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  List<EmpValueObject>
	 * @exception  Exception
	 */
	
	public List<EmpValueObject> selectEmpList(EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		List<EmpValueObject> selectEmpList = getSqlSession().selectList("empMapper.selectEmpList", empValueObject);
		return selectEmpList;
	}
	
	/**
	 * 직원관리 직원 상세조회로 이동하는 메서드입니다.
	 *
	 * @param   int 
	 * @return  List<EmpValueObject>
	 * @exception  Exception
	 */
	
	public List<EmpValueObject> selectEmpRead(int empSeq) throws Exception {
		Logger.info(null);
		List<EmpValueObject> selectEmpRead = getSqlSession().selectList("empMapper.selectEmpRead", empSeq);
		return selectEmpRead;
	}
	
	/**
	 * 직원관리 직원 등록하는 메서드입니다.
	 *
	 * @param   EmpValueObject 
	 * @return  void
	 * @exception  Exception
	 */
	
	public void empCreate(EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		
		getSqlSession().insert("empMapper.empCreate", empValueObject);
		getSqlSession().insert("empMapper.empAthrtyCreate", empValueObject);
	}
		
	
	/**
	 * 이미지 업로드를 위해 현재
	 * 직원 테이블의 SEQ 최고값을 조회하는 메서드입니다.
	 *
	 * @param   -
	 * @return  int
	 * @exception  Exception
	 */
	
	public int maxEmpSeq() throws Exception {
		Logger.info(null);
		int maxEmpSeq = getSqlSession().selectOne("empMapper.maxEmpSeq");
		return maxEmpSeq;
	}
	

	/**
	 * 직원관리 직원정보 수정하는 메서드입니다.
	 *
	 * @param   EmpValueObject 
	 * @return  void
	 * @exception  Exception
	 */
	
	public void empUpdate(EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		getSqlSession().update("empMapper.empUpdate", empValueObject);
		getSqlSession().update("empMapper.empAthrtyUpdate", empValueObject);
	}
	
	/**
	 * 직원관리 직원 개인정보 수정하는 메서드입니다.
	 *
	 * @param   EmpValueObject 
	 * @return  void
	 * @exception  Exception
	 */
	
	public void empPersnlUpdate(EmpValueObject empValueObject) throws Exception{
		Logger.info(null);
			
		getSqlSession().update("empMapper.empPersnlUpdate", empValueObject);
	}
	
	/**
	 * 직원관리 직원 정보 삭제하는 메서드입니다.
	 *
	 * @param   EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	public void empDelete(EmpValueObject empValueObject) throws Exception{
		Logger.info(null);
		getSqlSession().delete("empMapper.empDelete", empValueObject);
	}
	
	/**
	 * 직원관리 직원 비밀번호를 수정하는 메서드입니다.
	 *
	 * @param   EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */
	
	public void empPWUpdate(EmpValueObject empValueObject) throws Exception{
		Logger.info(null);
		getSqlSession().update("empMapper.empPWUpdate", empValueObject);
	}
}
