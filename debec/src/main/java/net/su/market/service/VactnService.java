package net.su.market.service;

import java.util.List;

import net.su.market.valueObject.EmpValueObject;


/**
 * 직원관리 메뉴 관련 서비스입니다.
 * 
 * @see   net.su.market.service.VactnService
 * @version  1.0 
 * @ author 이인호, 2016/08/16
 */

public interface VactnService {

	/**
	 * 휴가관리 휴가조회로 이동하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  List<EmpValueObject>
	 * @exception  Exception
	 */
	
	public List<EmpValueObject> selectVactnList(EmpValueObject empValueObject) throws Exception;
	
	/**
	 * 개인 휴가조회로 이동하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  List<EmpValueObject>
	 * @exception  Exception
	 */
	
	public List<EmpValueObject> selectPersnlVactnList(EmpValueObject empValueObject) throws Exception;
	
	/**
	 * 휴가관리 사용 연가조회하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  List<EmpValueObject>
	 * @exception  Exception
	 */
	
	public List<EmpValueObject> selectAnlVactnListPopup(EmpValueObject empValueObject) throws Exception;

	
	/**
	 * 휴가관리 휴가 상세조회하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  List<EmpValueObject>
	 * @exception  Exception
	 */
	
	public List<EmpValueObject> selectVactnReadPopup(EmpValueObject empValueObject) throws Exception;
	
	/**
	 * 휴가관리 휴가 등록하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  void
	 * @exception  Exception
	 */
	
	public void insertVactn(EmpValueObject empValueObject) throws Exception;
	
	/**
	 * 휴가관리 휴가 수정하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  void
	 * @exception  Exception
	 */
	
	public void updateVactn(EmpValueObject empValueObject) throws Exception;
	
	
	/**
	 * 휴가관리 휴가 삭제하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  void
	 * @exception  Exception
	 */
	
	public void deleteVactn(EmpValueObject empValueObject) throws Exception;
	
	/**
	 * 휴가관리 휴가 다중 삭제하는 메서드입니다.
	 *
	 * @param   String[]  
	 * @return  void
	 * @exception  Exception
	 */
	
	public void deleteVactnList(String[] data) throws Exception;
}
