package net.su.app.appMarket.service;

import java.util.List;

import javax.annotation.Resource;

import net.su.app.appMarket.dataAccessObject.AppMarketDataAccessObject;
import net.su.app.appMarket.valueObject.AppMarketValueObject;
import net.su.logger.Logger;
import net.su.market.valueObject.AgremtValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

import org.springframework.stereotype.Service;


/**
 * 앱 마켓 메뉴 관련 공통 기능 서비스 인터페이스입니다.
 * 
 * @see   net.su.app.appMarket.service
 * @version  1.0 
 * @ author 이인호, 2016/09/20
 */

@Service
public class AppMarketServiceImpl implements AppMarketService{

	@Resource
	private AppMarketDataAccessObject appMarketDAO;
	
	/**
    * 앱 히스토리 등록하는 메서드
    *
    * @param   AppmarketValueObject 
    * @return  
    * @exception  Exception
    */
	public void appInsertHistory(AppMarketValueObject appmarketValueObject) throws Exception{
		Logger.info("앱 히스토리 등록");
		appMarketDAO.appInsertHistory(appmarketValueObject);
	}
	
	/**
    * 장바구니에 상품을 조회하는 메서드
    *
    * @param   ProdctValueObject 
    * @return  int
    * @exception  Exception
    */
	public int appCartItemCheck(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		
		int appCartItemCheck = appMarketDAO.appCartItemCheck(prodctValueObject);
		return appCartItemCheck;
	}
	
	/**
    * 장바구니에 상품을 등록하는 메서드
    *
    * @param   ProdctValueObject
    * @return  
    * @exception  Exception
    */
	public void appCartItemCreat(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		
		int prodctType = prodctValueObject.getProdctType();
		int appCartItemCheck = appMarketDAO.appCartItemCheck(prodctValueObject);
		
		if(appCartItemCheck == 0){
			switch(prodctType){
				case 0:
				case 2:
				case 3:
				case 4:
				case 5:
					appMarketDAO.appCartItemCreat(prodctValueObject);
					break;
				case 1:
					appMarketDAO.appPackgCartItemCreat(prodctValueObject);
					break;
			}
		}
		
		else {
			switch(prodctType){
				case 0:
				case 2:
				case 3:
				case 4:
				case 5:
					appMarketDAO.appCartItemUpdate(prodctValueObject);
					break;
				case 1:
					appMarketDAO.appPackgCartItemUpdate(prodctValueObject);
					break;
			}
		}
	}
	
	/**
    * 상품을 검색하는 메서드
    *
    * @param   String
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> appProdctSearch(String prodctSechText) throws Exception {
		Logger.info(null);

		List<ProdctValueObject> appProdctSearchList = appMarketDAO.appProdctSearch(prodctSechText);
		return appProdctSearchList;

	}
	
	/**
    * 상품을 상세 조회하는 메서드
    *
    * @param   ProdctValueObject 
    * @return  ProdctValueObject
    * @exception  Exception
    */
	public ProdctValueObject appProdctRead(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		ProdctValueObject appProdctRead = appMarketDAO.appProdctRead(prodctValueObject);
		int selStrtTimeCount = appMarketDAO.selectSelStrtTimeCount(prodctValueObject);
		appProdctRead.setSelStrtTimeCount(selStrtTimeCount);
		return appProdctRead;
	}
	
	/**
    * 연관 상품을 상세 조회하는 메서드
    *
    * @param   ProdctValueObject 
    * @return  ProdctValueObject
    * @exception  Exception
    */
	public ProdctValueObject appReltnProdctRead(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		
		ProdctValueObject appReltnProdctRead = appMarketDAO.appReltnProdctRead(prodctValueObject);
		return appReltnProdctRead;
	}
	
	/**
    * 안내 약관을 조회하는 메서드
    *
    * @param   AgremtValueObject 
    * @return  AgremtValueObject
    * @exception  Exception
    */
	public AgremtValueObject appDeliveryAgremtRead(AgremtValueObject agremtValueObject) throws Exception {
		Logger.info(null);
		
		AgremtValueObject appDeliveryAgremt = appMarketDAO.appDeliveryAgremtRead(agremtValueObject);
		return appDeliveryAgremt;
	}
	
}
