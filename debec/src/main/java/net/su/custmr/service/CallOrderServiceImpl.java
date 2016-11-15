package net.su.custmr.service;

import java.util.List;

import javax.annotation.Resource;

import net.su.custmr.dataAccessObject.CallOrderDataAccessObject;
import net.su.custmr.valueObject.CallOrderValueObject;
import net.su.custmr.valueObject.CustmrValueObject;
import net.su.logger.Logger;
import net.su.prodct.valueObject.DebecFestivalValueObject;
import net.su.prodct.valueObject.ProdctFestivalValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

import org.springframework.stereotype.Service;

@Service
public class CallOrderServiceImpl implements CallOrderService {

	@Resource
	CallOrderDataAccessObject  CallOrderDataAccessObject;
	
	/**
    * (조회) 고객 선택 팝업창 고객 리스트 조회 메서드입니다.
    *
    * @param   @RequestParam CustmrValueObject
    * @return  custmrList
    * @exception  Exception
    */
	public List<CustmrValueObject> custmrList(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		int custmrListCount = CallOrderDataAccessObject.custmrListCount(custmrValueObject);	// 행사 리스트 전체 수 (페이징)
		custmrValueObject.setTotalRecordCount(custmrListCount); // 행사 리스트 전체 수 셋팅 (페이징)
		List<CustmrValueObject> custmrList = CallOrderDataAccessObject.custmrList(custmrValueObject);
		
		return custmrList;
	}
	
	/**
    * (조회) 배송지명 리스트 조회 메서드입니다.
    *
    * @param   custmrSeq
    * @return  shippingPlaceList
    * @exception  Exception
    */
	public List<CallOrderValueObject> shippingPlaceList(int custmrSeq) throws Exception {
		Logger.info(null);
		List<CallOrderValueObject> shippingPlaceList = CallOrderDataAccessObject.shippingPlaceList(custmrSeq);
		
		return shippingPlaceList;
	}
	
	/**
    * (조회) 배송지 선택시 배송지 정보 조회 메서드입니다.
    *
    * @param   @RequestParam Model, CallOrderValueObject
    * @return  
    * @exception  Exception
    */
	public CallOrderValueObject shipngPlcInfo(int shipngPlcSeq) throws Exception {
		CallOrderValueObject shipngPlcInfo = CallOrderDataAccessObject.shipngPlcInfo(shipngPlcSeq); //배송지 정보
		
		return shipngPlcInfo;
	}
		
	/**
    * (조회) 고객 선택시 고객 정보 조회 메서드입니다.
    *
    * @param   custmrSeq
    * @return  
    * @exception  Exception
    */
	public CustmrValueObject custmrInfo(int custmrSeq) throws Exception {
		Logger.info(null);
		CustmrValueObject custmrInfo = CallOrderDataAccessObject.custmrInfo(custmrSeq); //고객 정보
		
		return custmrInfo;
	}
	
	/**
    * 상품추가 테이블1 생성 메서드입니다.
    *
    * @param   @RequestParam Model, HttpServletRequest, ProdctFestivalValueObject
    * @return  
    * @exception  Exception
    */
	public List<ProdctValueObject> prodctAddList(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		int prodctAddListCount = CallOrderDataAccessObject.prodctAddListCount(prodctValueObject); //임시테이블에 없고 판매중이고 재고 있는 상품카운트
		System.out.println("상품테이블 상품 수 = " + prodctAddListCount);
		prodctValueObject.setTotalRecordCount(prodctAddListCount);//VO 전체 카운트 담아줌
		List<ProdctValueObject> prodctAdList = CallOrderDataAccessObject.prodctAddList(prodctValueObject);
		
		return prodctAdList;
	}
	
	/**
    * 상품추가 테이블2 생성 메서드입니다.
    *
    * @param   @RequestParam Model, HttpServletRequest, ProdctValueObject
    * @return  
    * @exception  Exception
    */
	public List<ProdctValueObject> prodctAddTempList(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		int prodctAdTempCount = CallOrderDataAccessObject.prodctAdTempCount(prodctValueObject);
		System.out.println("임시테이블 상품 수 = " + prodctAdTempCount);
		prodctValueObject.setTotalRecordCount(prodctAdTempCount);
		List<ProdctValueObject> prodctAdTempList = CallOrderDataAccessObject.prodctAdTempList(prodctValueObject);
		
		return prodctAdTempList;
	}
	/**
    * 상품 선택 추가 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void prodctAdTempCreate(String[] data) throws Exception {
		Logger.info(null);
		CallOrderDataAccessObject.prodctAdTempCreate(data);
	}
	
	/**
    * 상품 선택 제거 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void prodctAddTempDelete(String[] data) throws Exception {
		Logger.info(null);
		CallOrderDataAccessObject.prodctAddTempDelete(data);
	}
	
	/**
    * 상품 리스트(임시 테이블 리스트) 가져오는 메서드입니다. 
    *
    * @param   @RequestParam 
    * @return  
    * @exception  Exception
    */
	public List<ProdctValueObject> prodctTempList() throws Exception {
		Logger.info(null);
		List<ProdctValueObject> prodctTempList = CallOrderDataAccessObject.prodctTempList();
		
		return prodctTempList;
	}
	
	/**
    * 최종 등록 시 상품 리스트 임시테이블 수량 수정 메서드입니다.
    *
    * @param   @RequestParam CallOrderValueObject
    * @return  
    * @exception  Exception
    */
	public void callOrderProdctTempUpdate(CallOrderValueObject callOrderValueObject) throws Exception {
		Logger.info(null);
		CallOrderDataAccessObject.callOrderProdctTempUpdate(callOrderValueObject);
	}
	
	/**
    * 전화주문 최종 등록 메서드입니다.
    *
    * @param   @RequestParam CallOrderValueObject
    * @return  
    * @exception  Exception
    */
	public void callOrderCreate(CallOrderValueObject callOrderValueObject) throws Exception {
		Logger.info(null);
		CallOrderDataAccessObject.callOrderCreate(callOrderValueObject);
	}
}
