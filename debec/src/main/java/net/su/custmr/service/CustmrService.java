package net.su.custmr.service;

import java.util.List;

import net.su.custmr.valueObject.CustmrValueObject;

public interface CustmrService {

	public List<CustmrValueObject> selectCustmrList(CustmrValueObject custmrValueObject) throws Exception;// 고객 리스트	

	public void changeCustmrCardChkGroup(int checkNmeCardGroup[]) throws Exception; // 고객 카드 여부 일괄 변경
	
	public CustmrValueObject custmrRead(CustmrValueObject custmrValueObject) throws Exception; // 고객 상세보기
	
	public void custmrUpdate(CustmrValueObject custmrValueObject) throws Exception; // 고객 정보 변경
	
	public void custmrDelete(CustmrValueObject custmrValueObject) throws Exception; // 고객 정보 삭제
	
	public void changeQuscncCustmrGroup(int checkNmeCardGroup[]) throws Exception; // 휴면고객 일반회원 일괄 변경
	
	public void deleteQuscncCustmrGroup(int checkNmeCardGroup[]) throws Exception; // 휴면고객 일괄 삭제
	
}
