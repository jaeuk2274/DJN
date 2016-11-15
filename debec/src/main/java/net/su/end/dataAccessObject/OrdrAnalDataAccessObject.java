package net.su.end.dataAccessObject;

import java.util.List;

import net.su.end.valueObject.OrdrAnalValueObject;
import net.su.end.valueObject.SalsValueObject;
import net.su.logger.Logger;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class OrdrAnalDataAccessObject extends SqlSessionDaoSupport {

	/**
		* 일별주문의 분석 리스트(검색조건 포함)를 보여주는 메서드입니다.
		*
		* @param   SalsValueObject
		* @return  List<OrdrAnalValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrAnalValueObject> dayOrdrAnal(SalsValueObject salsValueObject){
		Logger.info(null);	
		if(salsValueObject.getStartDatSech() != "" && salsValueObject.getEndDatSech() != "") {			
			salsValueObject.setBtnDatSech("날짜");
		}
		List<OrdrAnalValueObject> ordrList = getSqlSession().selectList("ordrAnalMapper.dayOrdrAnal", salsValueObject);
		Logger.info(null);
		return ordrList;
	}
	
	/**
		* 월별주문의 분석 리스트(검색조건 포함)를 보여주는 메서드입니다.
		*
		* @param   SalsValueObject
		* @return  List<OrdrAnalValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrAnalValueObject> monthOrdrAnal(SalsValueObject salsValueObject){
		Logger.info(null);	
		if(salsValueObject.getStartDatSech() != "" && salsValueObject.getEndDatSech() != "") {			
			salsValueObject.setBtnDatSech("날짜");
		}
		List<OrdrAnalValueObject> ordrList = getSqlSession().selectList("ordrAnalMapper.monthOrdrAnal", salsValueObject);
		Logger.info(null);
		return ordrList;
	}
	
	/**
		* 주문 종류 리스트를 조회하는 메서드입니다.
		*
		* @param   
		* @return  List<OrdrAnalValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrAnalValueObject> ordrTypeList(SalsValueObject salsValueObject){
		List<OrdrAnalValueObject> ordrList = getSqlSession().selectList("ordrAnalMapper.ordrList",salsValueObject);
		return ordrList;
	}
	
	/**
		* 상품별 주문의 분석 리스트(검색조건 포함)를 보여주는 메서드입니다.
		*
		* @param   SalsValueObject
		* @return  List<OrdrAnalValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrAnalValueObject> prodctOrdrAnal(SalsValueObject salsValueObject){
		Logger.info(null);	
		if(salsValueObject.getStartDatSech() != "" && salsValueObject.getEndDatSech() != "") {			
			salsValueObject.setBtnDatSech("날짜");
		}
		String ordrType = salsValueObject.getOrdrType();
		if( ordrType.equals("전체 주문")){
			Logger.info("전체 주문");
			List<OrdrAnalValueObject> ordrList = getSqlSession().selectList("ordrAnalMapper.totProdctOrdrAnal", salsValueObject);
			return ordrList;
		}
		else if(ordrType.equals("앱 주문")||ordrType.equals("전화 주문")) {
			Logger.info("앱 주문 or 전화주문");
			List<OrdrAnalValueObject> ordrList = getSqlSession().selectList("ordrAnalMapper.prodctOrdrAnal", salsValueObject);
			return ordrList;
		}
		return null;
	}
}
