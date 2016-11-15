package net.su.custmr.dataAccessObject;

import java.util.List;

import net.su.custmr.valueObject.CallOrderValueObject;
import net.su.custmr.valueObject.CustmrValueObject;
import net.su.logger.Logger;
import net.su.prodct.valueObject.DebecFestivalValueObject;
import net.su.prodct.valueObject.ProdctFestivalValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class CallOrderDataAccessObject extends SqlSessionDaoSupport {

	/**
    * (조회) 고객 선택 팝업창 고객 리스트 조회 메서드입니다.
    *
    * @param   @RequestParam CustmrValueObject
    * @return  custmrList
    * @exception  Exception
    */
	public List<CustmrValueObject> custmrList(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		List<CustmrValueObject> custmrList = getSqlSession().selectList("callOrderMapper.custmrList", custmrValueObject);
		
		return custmrList;
	}
	
	/**
    * (조회) 고객 선택 팝업창 고객 리스트 카운트 조회 메서드입니다.
    *
    * @param   @RequestParam custmrValueObject
    * @return  custmrListCount
    * @exception  Exception
    */
	public int custmrListCount(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		int custmrListCount = getSqlSession().selectOne("callOrderMapper.custmrListCount", custmrValueObject);	
		return custmrListCount;
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
		List<CallOrderValueObject> shippingPlaceList = getSqlSession().selectList("callOrderMapper.shippingPlaceList", custmrSeq);
		
		return shippingPlaceList;
	}

	public CallOrderValueObject shipngPlcInfo(int shipngPlcSeq) throws Exception {
		Logger.info(null);
		CallOrderValueObject shipngPlcInfo  = getSqlSession().selectOne("callOrderMapper.shipngPlcInfo", shipngPlcSeq);
		
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
		CustmrValueObject custmrInfo  = getSqlSession().selectOne("callOrderMapper.custmrInfo", custmrSeq);
		
		return custmrInfo;
	}

	public int prodctAddListCount(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		int prodctAddListCount = getSqlSession().selectOne("callOrderMapper.prodctAddListCount", prodctValueObject);	
		return prodctAddListCount;
	}

	public List<ProdctValueObject> prodctAddList(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		List<ProdctValueObject> prodctAdList = getSqlSession().selectList("callOrderMapper.prodctAddList", prodctValueObject);	
		return prodctAdList;
	}

	public int prodctAdTempCount(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		int prodctAdTempCount = getSqlSession().selectOne("callOrderMapper.prodctAdTempCount", prodctValueObject);	
		return prodctAdTempCount;
	}
	
	/**
    * 상품추가 테이블2 생성 메서드입니다.
    *
    * @param   @RequestParam Model, HttpServletRequest, ProdctValueObject
    * @return  
    * @exception  Exception
    */	
	public List<ProdctValueObject> prodctAdTempList(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		List<ProdctValueObject> prodctAdTempList = getSqlSession().selectList("callOrderMapper.prodctAdTempList", prodctValueObject);	
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
		for (int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			getSqlSession().insert("callOrderMapper.prodctAdTempCreate", prodctSeq);
		}	
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
		for (int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			getSqlSession().delete("callOrderMapper.prodctAddTempDelete", prodctSeq);	
		}	
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
		List<ProdctValueObject> prodctTempList = getSqlSession().selectList("callOrderMapper.prodctTempList");	
		
		return prodctTempList;

	}

	/**
    * 최종 등록 시 상품 리스트 임시테이블 수량 수정 후 상품리스트 등록 메서드입니다.
    *
    * @param   @RequestParam CallOrderValueObject
    * @return  
    * @exception  Exception
    */
	public void callOrderProdctTempUpdate(CallOrderValueObject callOrderValueObject) throws Exception {
		ProdctValueObject prodctValueObject = new ProdctValueObject();

		long[] prodctSeq_arry = callOrderValueObject.getProdctSeq_arry();
		int[] prodctNumber_arry = callOrderValueObject.getProdctNumber_arry();

		for (int i=0; i<prodctSeq_arry.length; i++){
			prodctValueObject.setProdctSeq(prodctSeq_arry[i]);
			prodctValueObject.setProdctNum(prodctNumber_arry[i]);
			
			getSqlSession().update("callOrderMapper.callOrderProdctTempUpdate", prodctValueObject); // 상품 리스트 수정
		}
		
		Logger.info(null);
		List<ProdctValueObject> prodctTempList = getSqlSession().selectList("callOrderMapper.prodctAdTempList2");	
		for (int i=0; i<prodctTempList.size(); i++){
			prodctValueObject = prodctTempList.get(i);
			getSqlSession().insert("callOrderMapper.prodctCreate", prodctValueObject); //전화 주문 상품 리스트 등록	
			
		}	
		getSqlSession().delete("callOrderMapper.prodctTempAllDelete");	
	}

	/**
    * 전화주문 최종 등록 메서드입니다.
    *
    * @param   @RequestParam CallOrderValueObject
    * @return  
    * @exception  Exception
    */
	public void callOrderCreate(CallOrderValueObject callOrderValueObject) {
		if(callOrderValueObject.getCustmrSeq() == 0) {
			getSqlSession().insert("callOrderMapper.noCustmrCallOrderCreate", callOrderValueObject);	
		}
		else {
			getSqlSession().insert("callOrderMapper.custmrCallOrderCreate", callOrderValueObject);	
		}
	}	
}

