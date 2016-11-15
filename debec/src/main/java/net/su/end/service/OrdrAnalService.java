package net.su.end.service;

import java.util.List;

import net.su.end.valueObject.OrdrAnalValueObject;
import net.su.end.valueObject.SalsValueObject;


public interface OrdrAnalService {
	
	/**
		* 일별주문의 분석 리스트(검색조건 포함)를 보여주는 메서드입니다.
		*
		* @param   SalsValueObject
		* @return  List<OrdrAnalValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrAnalValueObject> dayOrdrAnal(SalsValueObject salsValueObject);
	
	/**
		* 월별주문의 분석 리스트(검색조건 포함)를 보여주는 메서드입니다.
		*
		* @param   SalsValueObject
		* @return  List<OrdrAnalValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrAnalValueObject> monthOrdrAnal(SalsValueObject salsValueObject);

	/**
		* 주문 종류 리스트를 조회하는 메서드입니다.
		*
		* @param   SalsValueObject
		* @return  List<OrdrAnalValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrAnalValueObject> ordrTypeList(SalsValueObject salsValueObject);
	
	/**
		* 상품별 주문의 분석 리스트(검색조건 포함)를 보여주는 메서드입니다.
		*
		* @param   SalsValueObject
		* @return  List<OrdrAnalValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrAnalValueObject> prodctOrdrAnal(SalsValueObject salsValueObject);
}
