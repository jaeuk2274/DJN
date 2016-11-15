package net.su.market.dataAccessObject;

import java.util.List;

import net.su.logger.Logger;
import net.su.market.valueObject.EmpValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class VactnDataAccessObject extends SqlSessionDaoSupport{
	
	/**
	 * 휴가관리 총 휴가 수를 조회하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  int
	 * @exception  Exception
	 */
	
	public int selectVactnCount(EmpValueObject empValueObject) throws Exception {
		Logger.info(null);		
		int selectVactnCount = getSqlSession().selectOne("vactnMapper.selectVactnCount", empValueObject);	
		return selectVactnCount;
	}
	
	/**
	 * 휴가관리 총 휴가 수를 조회하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  int
	 * @exception  Exception
	 */
	
	public int selectPersnlVactnCount(EmpValueObject empValueObject) throws Exception {
		Logger.info(null);		
		int selectVactnCount = getSqlSession().selectOne("vactnMapper.selectPersnlVactnCount", empValueObject);	
		return selectVactnCount;
	}
	
	/**
	 * 직원관리 직원조회로 이동하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  List<EmpValueObject>
	 * @exception  Exception
	 */
	
	public List<EmpValueObject> selectVactnList(EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		List<EmpValueObject> selectVactnList = getSqlSession().selectList("vactnMapper.selectVactnList", empValueObject);
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
		List<EmpValueObject> selectVactnList = getSqlSession().selectList("vactnMapper.selectPersnlVactnList", empValueObject);
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
		
		List<EmpValueObject> selectAnlVactnList = getSqlSession().selectList("vactnMapper.selectAnlVactnList", empValueObject);
		
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
		
		List<EmpValueObject> selectVactnRead = getSqlSession().selectList("vactnMapper.selectVactnRead", empValueObject);
		
		return selectVactnRead;
	}
	
	/**
	 * 휴가관리 휴가 등록하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  void
	 * @exception  Exception
	 */

	public void insertVactn(EmpValueObject empValueObject) throws Exception{
		Logger.info(null);
		
		getSqlSession().insert("vactnMapper.insertVactnSchedl", empValueObject);
		getSqlSession().insert("vactnMapper.insertVactn", empValueObject);
		
	}
	
	/**
	 * 휴가관리 휴가 수정하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  void
	 * @exception  Exception
	 */
	
	public void updateVactn(EmpValueObject empValueObject) throws Exception{
		getSqlSession().update("vactnMapper.updateVactn", empValueObject);
	}	
	
	/**
	 * 휴가관리 휴가 삭제하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  void
	 * @exception  Exception
	 */
	
	public void deleteVactn(EmpValueObject empValueObject) throws Exception {
		getSqlSession().delete("vactnMapper.deleteVactn", empValueObject);
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
		for (int i=0; i<data.length; i++){
			int schedlSeq = Integer.parseInt(data[i]);
			System.out.println("skdhsek"+schedlSeq);
			getSqlSession().delete("vactnMapper.deleteVactnList", schedlSeq);	
		}
	}

}
