package net.su.market.service;


import java.util.List;

import javax.annotation.Resource;

import net.su.logger.Logger;
import net.su.market.dataAccessObject.VactnDataAccessObject;
import net.su.market.valueObject.EmpValueObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 직원관리 메뉴 관련 서비스입니다.
 * 
 * @see   net.su.market.service.VactnServiceImpl
 * 
 * @version  1.0 
 * @ author 이인호, 2016/08/16
 */

@Service
public class VactnServiceImpl implements VactnService{
	
	@Resource
	private VactnDataAccessObject VactnDAO;
	
	/**
	 * 휴가관리 휴가조회로 이동하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  List<EmpValueObject>
	 * @exception  Exception
	 */
	
	public List<EmpValueObject> selectVactnList(EmpValueObject empValueObject) throws Exception{
		Logger.info(null);
		
		int selectEmpCount = VactnDAO.selectVactnCount(empValueObject);	// 휴가 전체 수
		empValueObject.setTotalRecordCount(selectEmpCount);
		
		List<EmpValueObject> selectVactnList = VactnDAO.selectVactnList(empValueObject);
		
		return selectVactnList;
	}
	
	/**
	 * 개인 휴가조회로 이동하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  List<EmpValueObject>
	 * @exception  Exception
	 */
	
	public List<EmpValueObject> selectPersnlVactnList(EmpValueObject empValueObject) throws Exception{
		Logger.info(null);
		
		int selectEmpCount = VactnDAO.selectPersnlVactnCount(empValueObject);	// 개인 휴가 전체 수
		empValueObject.setTotalRecordCount(selectEmpCount);
		
		List<EmpValueObject> selectVactnList = VactnDAO.selectPersnlVactnList(empValueObject);
		
		return selectVactnList;
	}
	
	
	/**
	 * 휴가관리 사용 연가조회하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  List<EmpValueObject>
	 * @exception  Exception
	 */
	
	public List<EmpValueObject> selectAnlVactnListPopup(EmpValueObject empValueObject) throws Exception{
		Logger.info(null);
		
		List<EmpValueObject> selectAnlVactnList = VactnDAO.selectAnlVactnListPopup(empValueObject);
		
		return selectAnlVactnList;
	}
	
	/**
	 * 휴가관리 휴가 상세조회하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  List<EmpValueObject>
	 * @exception  Exception
	 */
	
	public List<EmpValueObject> selectVactnReadPopup(EmpValueObject empValueObject) throws Exception{
		Logger.info(null);
		
		List<EmpValueObject> selectVactnRead = VactnDAO.selectVactnReadPopup(empValueObject);
		
		return selectVactnRead;
	}
	
	/**
	 * 휴가관리 휴가 등록하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  void
	 * @exception  Exception
	 */
	
	@Transactional
	public void insertVactn(EmpValueObject empValueObject) throws Exception{
		Logger.info(null);
		
		VactnDAO.insertVactn(empValueObject);
	}

	/**
	 * 휴가관리 휴가 수정하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  void
	 * @exception  Exception
	 */
	
	@Transactional
	public void updateVactn(EmpValueObject empValueObject) throws Exception{
		VactnDAO.updateVactn(empValueObject);
	}
	
	
	/**
	 * 휴가관리 휴가 삭제하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  void
	 * @exception  Exception
	 */
	
	public void deleteVactn(EmpValueObject empValueObject) throws Exception {
		VactnDAO.deleteVactn(empValueObject);
	}
	
	/**
	 * 휴가관리 휴가 다중 삭제하는 메서드입니다.
	 *
	 * @param   String[]  
	 * @return  void
	 * @exception  Exception
	 */
	
	public void deleteVactnList(String[] data) throws Exception{
		Logger.info(null);
		VactnDAO.deleteVactnList(data);
	}
}
