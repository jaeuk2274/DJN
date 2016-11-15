package net.su.app.appMarket.service;

import java.util.List;


import net.su.app.appMarket.valueObject.AppMarketValueObject;
import net.su.market.valueObject.AgremtValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

/**
 * 앱 마켓 메뉴 관련 공통 기능 서비스입니다.
 * 
 * @see   net.su.app.appMarket.service
 * @version  1.0 
 * @ author 이인호, 2016/09/20
 */

public interface AppMarketService {
	
	/**
    * 앱 히스토리 등록하는 메서드
    *
    * @param   AppmarketValueObject
    * @return  
    * @exception  Exception
    */
	public void appInsertHistory(AppMarketValueObject appmarketValueObject) throws Exception;

	/**
    * 장바구니에 상품을 등록하는 메서드
    *
    * @param   ProdctValueObject
    * @return  
    * @exception  Exception
    */
	public void appCartItemCreat(ProdctValueObject prodctValueObjectt) throws Exception;
	
	/**
    * 상품을 검색하는 메서드
    *
    * @param   String
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> appProdctSearch(String prodctSechText) throws Exception;
	
	/**
    * 상품을 상세 조회하는 메서드
    *
    * @param   ProdctValueObject 
    * @return  ProdctValueObject
    * @exception  Exception
    */
	public ProdctValueObject appProdctRead(ProdctValueObject prodctValueObject) throws Exception;
	
	/**
    * 연관 상품을 상세 조회하는 메서드
    *
    * @param   ProdctValueObject 
    * @return  ProdctValueObject
    * @exception  Exception
    */
	public ProdctValueObject appReltnProdctRead(ProdctValueObject prodctValueObject) throws Exception;
	
	/**
    * 안내 약관을 조회하는 메서드
    *
    * @param   AgremtValueObject 
    * @return  AgremtValueObject
    * @exception  Exception
    */
	public AgremtValueObject appDeliveryAgremtRead(AgremtValueObject agremtValueObject) throws Exception;

}
