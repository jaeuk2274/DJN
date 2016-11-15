package net.su.app.appMarket.dataAccessObject;

import java.util.List;

import net.su.app.appMarket.valueObject.AppMarketValueObject;
import net.su.logger.Logger;
import net.su.market.valueObject.AgremtValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;


/**
 * 앱 마켓 메뉴 관련 공통 기능 DAO입니다.
 * 
 * @see   net.su.app.appMarket.dataAccessObject
 * @version  1.0 
 * @ author 이인호, 2016/09/20
 */

@Repository
public class AppMarketDataAccessObject extends SqlSessionDaoSupport{
	
	/**
    * 앱 히스토리 등록하는 메서드
    *
    * @param   AppmarketValueObject 
    * @return  
    * @exception  Exception
    */
	public void appInsertHistory(AppMarketValueObject appmarketValueObject)throws Exception {
		logger.info("앱 히스토리 등록");
		getSqlSession().insert("appMarketMapper.appInsertHistory",appmarketValueObject);
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
		int appCartItemCheck = getSqlSession().selectOne("appMarketMapper.appCartItemCheck", prodctValueObject);
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
		getSqlSession().insert("appMarketMapper.appCartItemCreat", prodctValueObject);
	}
	
	/**
    * 장바구니에 상품을 등록하는 메서드
    *
    * @param   ProdctValueObject 
    * @return  
    * @exception  Exception
    */
	public void appPackgCartItemCreat(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		getSqlSession().insert("appMarketMapper.appPackgCartItemCreat", prodctValueObject);
	}
		
	
	/**
    * 장바구니에 상품을 추가 등록하는 메서드
    *
    * @param   ProdctValueObject 
    * @return  
    * @exception  Exception
    */
	public void appCartItemUpdate(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		getSqlSession().update("appMarketMapper.appCartItemUpdate", prodctValueObject);
	}
	
	/**
    * 장바구니에 상품을 추가 등록하는 메서드
    *
    * @param   ProdctValueObject 
    * @return  
    * @exception  Exception
    */
	public void appPackgCartItemUpdate(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		getSqlSession().update("appMarketMapper.appPackgCartItemUpdate", prodctValueObject);
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

		List<ProdctValueObject> appProdctSearchList = getSqlSession().selectList("appMarketMapper.appProdctSearch", prodctSechText);
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
		ProdctValueObject appProdctRead = getSqlSession().selectOne("appMarketMapper.appProdctRead", prodctValueObject);
		
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
		
		ProdctValueObject appReltnProdctRead = getSqlSession().selectOne("appMarketMapper.appReltnProdctRead", prodctValueObject);
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
		
		AgremtValueObject appDeliveryAgremt = getSqlSession().selectOne("appMarketMapper.appDeliveryAgremtRead", agremtValueObject);
		return appDeliveryAgremt;
	}
	
	/**
    * 신선식품 판매시작시간 count 조회
    *
    * @param   ProdctValueObject 
    * @return  int
    * @exception  Exception
    */
	public int selectSelStrtTimeCount(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);

		int  selStrtTimeCount = getSqlSession().selectOne("appMarketMapper.selectSelStrtTimeCount", prodctValueObject);
		return selStrtTimeCount;

	}

}
