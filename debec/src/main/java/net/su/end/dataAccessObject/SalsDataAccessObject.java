package net.su.end.dataAccessObject;

import java.util.List;

import net.su.end.valueObject.SalsValueObject;
import net.su.logger.Logger;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class SalsDataAccessObject extends SqlSessionDaoSupport {

	/**
		* 일별매출의 분석 리스트(검색조건 포함)를 보여주는 메서드입니다.
		*
		* @param   SalsValueObject
		* @return  List<SalsValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<SalsValueObject> DaySalsAnal(SalsValueObject salsValueObject){
		Logger.info(null);	
		if(salsValueObject.getStartDatSech() != "" && salsValueObject.getEndDatSech() != "") {			
			salsValueObject.setBtnDatSech("날짜");
		}
		List<SalsValueObject> salsList = getSqlSession().selectList("salsMapper.DaySalsAnal", salsValueObject);
		return salsList;
	}
	
	/**
		* 월별매출의 분석 리스트(검색조건 포함)를 보여주는 메서드입니다.
		*
		* @param   SalsValueObject
		* @return  List<SalsValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<SalsValueObject> monthSalsAnal(SalsValueObject salsValueObject){
		Logger.info(null);	
		if(salsValueObject.getStartDatSech() != "" && salsValueObject.getEndDatSech() != "") {			
			salsValueObject.setBtnDatSech("날짜");
		}
		List<SalsValueObject> salsList = getSqlSession().selectList("salsMapper.monthSalsAnal", salsValueObject);
		return salsList;
	}
}
