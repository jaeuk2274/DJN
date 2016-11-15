package net.su.end.service;

import java.util.List;

import net.su.end.valueObject.SalsValueObject;

public interface SalsService {
	
	/**
		* 일별매출의 분석 리스트(검색조건 포함)를 보여주는 메서드입니다.
		*
		* @param   SalsValueObject
		* @return  List<SalsValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<SalsValueObject> DaySalsAnal(SalsValueObject salsValueObject);
	
	/**
		* 월별매출의 분석 리스트(검색조건 포함)를 보여주는 메서드입니다.
		*
		* @param   SalsValueObject
		* @return  List<SalsValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<SalsValueObject> monthSalsAnal(SalsValueObject salsValueObject);
}
