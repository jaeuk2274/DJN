package net.su.app.appMarket.controller;

import java.util.List;

import javax.annotation.Resource;

import net.su.app.appMarket.service.AppMarketService;
import net.su.app.appMarket.valueObject.AppMarketValueObject;
import net.su.logger.Logger;
import net.su.market.valueObject.AgremtValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 앱 마켓 메뉴 관련 공통 기능 컨트롤러입니다.
 * 
 * @see   net.su.app.appMarket.controller
 * @version  1.0 
 * @ author 이인호, 2016/09/20
 */

@Controller
public class AppMarketController {

	@Resource
	private AppMarketService appMarketService;
	
	/**
    * 장바구니에 상품을 등록하는 메서드
    *
    * @param   ProdctValueObject, int custmrSeq, prodctSeq, prodctAmont 
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/appCartItemCreat.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void appCartItemCreat(ProdctValueObject prodctValueObject, @RequestParam(value="custmrSeq") int custmrSeq, @RequestParam(value="prodctSeq") int prodctSeq, @RequestParam(value="prodctAmont") int prodctAmont, @RequestParam(value="prodctType") int prodctType) throws Exception {
		Logger.info(null);
		
		prodctValueObject.setProdctSeq(prodctSeq);
		prodctValueObject.setCusSeq(custmrSeq);
		prodctValueObject.setProdctAmont(prodctAmont);
		prodctValueObject.setProdctType(prodctType);
		appMarketService.appCartItemCreat(prodctValueObject);

	}
	
	/**
    * 상품을 검색하는 메서드
    *
    * @param   ProdctValueObject, String prodctSechText 
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/appProdctSearch.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<ProdctValueObject> appProdctSearch(ProdctValueObject prodctValueObject, @RequestParam(value="prodctSechText") String prodctSechText) throws Exception {
		Logger.info(null);

		List<ProdctValueObject> appProdctSearchList = appMarketService.appProdctSearch(prodctSechText);
		return appProdctSearchList;
	}
	
	/**
    * 상품을 상세 조회하는 메서드
    *
    * @param   ProdctValueObject 
    * @return  ProdctValueObject
    * @exception  Exception
    */
	@RequestMapping(value = "/appProdctRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ProdctValueObject appProdctRead(ProdctValueObject prodctValueObject, @RequestParam(value="prodctSeq") int prodctSeq,@RequestParam(value="custmrSeq") int custmrSeq) throws Exception {
		Logger.info(null);
		
		prodctValueObject.setProdctSeq(prodctSeq);
		ProdctValueObject appProdctRead = appMarketService.appProdctRead(prodctValueObject);
		
		AppMarketValueObject appMarketValueObject = new AppMarketValueObject();
		appMarketValueObject.setCustmrSeq(custmrSeq);
		appMarketValueObject.setProdctSeq(prodctSeq);
		appMarketService.appInsertHistory(appMarketValueObject);
		
		return appProdctRead;

	}
	
	/**
    * 연관 상품을 상세 조회하는 메서드
    *
    * @param   ProdctValueObject 
    * @return  ProdctValueObject
    * @exception  Exception
    */
	@RequestMapping(value = "/appReltnProdctRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ProdctValueObject appReltnProdctRead(ProdctValueObject prodctValueObject, @RequestParam(value="prodctSeq") int prodctSeq) throws Exception {
		Logger.info(null);
		
		prodctValueObject.setProdctSeq(prodctSeq);
		ProdctValueObject appReltnProdctRead = appMarketService.appReltnProdctRead(prodctValueObject);
		return appReltnProdctRead;
	}
	
	
	/**
    * 안내 약관을 조회하는 메서드
    *
    * @param   AgremtValueObject 
    * @return  int
    * @exception  Exception
    */
	@RequestMapping(value = "/appDeliveryAgremtRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AgremtValueObject appDeliveryAgremtRead(AgremtValueObject agremtValueObject, @RequestParam(value="useAgremtSeq") int useAgremtSeq) throws Exception {
		Logger.info(null);
		
		agremtValueObject.setUseAgremtSeq(useAgremtSeq);
		AgremtValueObject appDeliveryAgremt = appMarketService.appDeliveryAgremtRead(agremtValueObject);
		return appDeliveryAgremt;
	}
}
