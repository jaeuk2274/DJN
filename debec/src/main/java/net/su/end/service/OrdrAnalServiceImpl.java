package net.su.end.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.su.end.dataAccessObject.OrdrAnalDataAccessObject;
import net.su.end.valueObject.OrdrAnalValueObject;
import net.su.end.valueObject.SalsValueObject;
import net.su.logger.Logger;

@Service
public class OrdrAnalServiceImpl implements OrdrAnalService{

	@Resource
	private OrdrAnalDataAccessObject ordrAnalDAO;
	
	/**
		* 일별주문의 분석 리스트(검색조건 포함)를 보여주는 메서드입니다.
		*
		* @param   SalsValueObject
		* @return  List<OrdrAnalValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrAnalValueObject> dayOrdrAnal(SalsValueObject salsValueObject){
		Logger.info(null);	
		List<OrdrAnalValueObject> ordrList = ordrAnalDAO.dayOrdrAnal(salsValueObject);
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
		List<OrdrAnalValueObject> ordrList = ordrAnalDAO.monthOrdrAnal(salsValueObject);
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
		List<OrdrAnalValueObject> ordrList = ordrAnalDAO.ordrTypeList(salsValueObject);
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
		List<OrdrAnalValueObject> ordrList = ordrAnalDAO.prodctOrdrAnal(salsValueObject);
		return ordrList;
	}
}
