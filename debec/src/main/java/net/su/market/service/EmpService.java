package net.su.market.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.su.market.valueObject.EmpValueObject;

/**
 * 직원관리 메뉴 관련 서비스입니다.
 * 
 * @see   net.su.market.service.EmpService
 * @version  1.0 
 * @ author 이인호, 2016/08/09
 */

public interface EmpService {

	/**
	 * 직원관리 직원조회로 이동하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  List<EmpValueObject>
	 * @exception  Exception
	 */
	
	public List<EmpValueObject> selectEmpList(EmpValueObject empValueObject) throws Exception;

	/**
	 * 직원관리 직원 상세조회로 이동하는 메서드입니다.
	 *
	 * @param   int 
	 * @return  List<EmpValueObject>
	 * @exception  Exception
	 */
	
	public List<EmpValueObject> selectEmpRead(int empSeq) throws Exception;
	
	
	/**
	 * 직원관리 직원 등록하는 메서드입니다.
	 *
	 * @param   MultipartHttpServletRequest, EmpValueObject 
	 * @return  void
	 * @exception  Exception
	 */
	
	public void empCreate(MultipartHttpServletRequest multiRequest, EmpValueObject empValueObject) throws Exception;
	
	/**
	 * 직원관리 직원정보 수정하는 메서드입니다.
	 *
	 * @param   EmpValueObject 
	 * @return  void
	 * @exception  Exception
	 */
	
	public void empUpdate(MultipartHttpServletRequest multiRequest, EmpValueObject empValueObject) throws Exception;
	
	/**
	 * 직원관리 직원 개인정보 수정하는 메서드입니다.
	 *
	 * @param   EmpValueObject 
	 * @return  void
	 * @exception  Exception
	 */
	
	public void empPersnlUpdate(MultipartHttpServletRequest multiRequest, EmpValueObject empValueObject) throws Exception;

	/**
	 * 직원관리 직원 정보 삭제하는 메서드입니다.
	 *
	 * @param   EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	public void empDelete(EmpValueObject empValueObject) throws Exception;

	/**
	 * 직원관리 직원 비밀번호를 수정하는 메서드입니다.
	 *
	 * @param   EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */
	
	public void empPWUpdate(EmpValueObject empValueObject) throws Exception;
}
