package net.su.app.appMarket.controller;

import java.util.List;

import javax.annotation.Resource;

import net.su.app.appMarket.service.AppMarketService;
import net.su.app.appMarket.service.AppTogthrService;
import net.su.app.appMarket.valueObject.AppMarketValueObject;
import net.su.logger.Logger;
import net.su.prodct.service.TogthrService;
import net.su.prodct.valueObject.TogthrValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 앱 마켓 다함께 관련 컨트롤러입니다.
 * 
 * @see   net.su.app.appMarket.controller.AppTogthrController
 * @version  1.0 
 * @ author 시상일, 2016/09/30
 */
@Controller
public class AppTogthrController {
	@Resource
	private AppMarketService appMarketService;
	@Resource
	private AppTogthrService appTogthrService;
	@Resource
	private TogthrService togthrService;
	
	/**
	* 앱 다함께 상품 갯수 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/appTogthrProdctCount.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public int appTogthrProdctCount() throws Exception {
		Logger.info("앱 다함께 상품 진열 관리 갯수 조회 컨트롤러 가기 전 입니다.");
		int appTogthrProdctCount = appTogthrService.selectAppTogthrProdctCount();
		return appTogthrProdctCount;
	}
	
	/**
	* 앱 다함께 상품 목록 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  List<TogthrValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/appTogthrProdctList.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<TogthrValueObject>appTogthrProdctList() throws Exception {
		Logger.info("앱 다함께 상품 진열 관리 목록 조회 컨트롤러 가기 전 입니다.");
		List<TogthrValueObject> appTogthrProdctList = appTogthrService.selectAppTogthrProdctList();
		Logger.info("앱 다함께 상품 진열 관리 목록 조회 컨트롤러 후 입니다.");
		return appTogthrProdctList;
	}
	
	/**
	* 앱 고객 다함께 상품 진열상품 상세조회 메서드입니다.
	*
	* @param  @RequestParam(value="gropPurchsSeq")long , @RequestParam(value="custmrSeq") int
	* @return  TogthrValueObject
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/appCusTogthrDetail.do", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public TogthrValueObject appCusTogthrDetail(@RequestParam(value="gropPurchsProdctSeq") int gropPurchsProdctSeq )throws Exception{
		Logger.info("앱 고객 다함께 상품 진열 상세조회 컨트롤러 입니다.");
		
		TogthrValueObject appCusTogthrDetail = appTogthrService.selectAppTogthrDetail(gropPurchsProdctSeq);
		
		return appCusTogthrDetail;
	}
	
	/**
	* 앱 관리자 다함께 상품 진열상품 상세조회 메서드입니다.
	*
	* @param  @RequestParam(value="gropPurchsSeq")long
	* @return  TogthrValueObject
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/appEmpTogthrDetail.do", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public TogthrValueObject appEmpTogthrDetail(@RequestParam(value="gropPurchsProdctSeq") int gropPurchsProdctSeq )throws Exception{
		Logger.info("앱 관리자 다함께 상품 진열 상세조회 컨트롤러 입니다.");
		
		TogthrValueObject appEmpTogthrDetail = appTogthrService.selectAppTogthrDetail(gropPurchsProdctSeq);
		
		return appEmpTogthrDetail;
	}
}
