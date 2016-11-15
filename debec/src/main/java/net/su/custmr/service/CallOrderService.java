package net.su.custmr.service;

import java.util.List;

import net.su.custmr.valueObject.CallOrderValueObject;
import net.su.custmr.valueObject.CustmrValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

public interface CallOrderService {

	/**
    * (조회) 고객 선택 팝업창 고객 리스트 조회 메서드입니다.
    *
    * @param CustmrValueObject
    * @return  custmrList
    * @exception  Exception
    */
	public List<CustmrValueObject> custmrList(CustmrValueObject custmrValueObject) throws Exception;
	
	/**
    * (조회) 배송지명 리스트 조회 메서드입니다.
    *
    * @param   custmrSeq
    * @return  shippingPlaceList
    * @exception  Exception
    */
	public List<CallOrderValueObject> shippingPlaceList(int custmrSeq) throws Exception;

	/**
    * (조회) 배송지 선택시 배송지 정보 조회 메서드입니다.
    *
    * @param   @RequestParam Model, CallOrderValueObject
    * @return  
    * @exception  Exception
    */
	public CallOrderValueObject shipngPlcInfo(int shipngPlcSeq) throws Exception;
		
	/**
    * (조회) 고객 선택시 고객 정보 조회 메서드입니다.
    *
    * @param   custmrSeq
    * @return  
    * @exception  Exception
    */
	public CustmrValueObject custmrInfo(int custmrSeq) throws Exception;

	/**
    * 상품추가 테이블1 생성 메서드입니다.
    *
    * @param   @RequestParam Model, HttpServletRequest, ProdctFestivalValueObject
    * @return  
    * @exception  Exception
    */
	public List<ProdctValueObject> prodctAddList(ProdctValueObject prodctValueObject) throws Exception;
	
	/**
    * 상품추가 테이블2 생성 메서드입니다.
    *
    * @param   @RequestParam Model, HttpServletRequest, ProdctValueObject
    * @return  
    * @exception  Exception
    */
	public List<ProdctValueObject> prodctAddTempList(ProdctValueObject prodctValueObject) throws Exception;

	/**
    * 상품 선택 추가 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void prodctAdTempCreate(String[] data) throws Exception;
	
	/**
    * 상품 선택 제거 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void prodctAddTempDelete(String[] data) throws Exception;

	/**
    * 상품 리스트(임시 테이블 리스트) 가져오는 메서드입니다. 
    *
    * @param   @RequestParam 
    * @return  
    * @exception  Exception
    */
	public List<ProdctValueObject> prodctTempList() throws Exception;

	/**
    * 최종 등록 시 상품 리스트 임시테이블 수량 수정 메서드입니다.
    *
    * @param   @RequestParam CallOrderValueObject
    * @return  
    * @exception  Exception
    */
	public void callOrderProdctTempUpdate(CallOrderValueObject callOrderValueObject) throws Exception;

	/**
    * 전화주문 최종 등록 메서드입니다.
    *
    * @param   @RequestParam CallOrderValueObject
    * @return  
    * @exception  Exception
    */
	public void callOrderCreate(CallOrderValueObject callOrderValueObject) throws Exception;


}
