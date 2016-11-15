package net.su.app.appMyPg.controller;

import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.swing.text.html.HTML;




import net.su.app.appMyPg.service.AppCusMyPgService;
import net.su.custmr.valueObject.CustmrValueObject;
import net.su.custmr.valueObject.OrdrValueObject;
import net.su.logger.Logger;
import net.su.market.valueObject.EmpValueObject;
import net.su.prodct.valueObject.CatgrValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppCusMyPgController {

	@Resource
	private AppCusMyPgService appMyPgService;
	

	/**
    * 장바구니 상품 목록들을 조회하는 메서드(상품만)
    *
    * @param   @RequestParam("cusSeq") int
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/cartList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<ProdctValueObject> cartList(@RequestParam("cusSeq") int cusSeq) throws Exception {
		Logger.info(null);
		List<ProdctValueObject> cartList = appMyPgService.cartList(cusSeq);
		return cartList;	
	}

	
	/**
    * 장바구니 패키지 목록들을 조회하는 메서드(공동구매만)
    *
    * @param   @RequestParam("cusSeq") int
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/cartPackgList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<ProdctValueObject> cartPackgList(@RequestParam("cusSeq") int cusSeq) throws Exception {
		Logger.info(null);
		List<ProdctValueObject> cartList = appMyPgService.cartPackgList(cusSeq);
		return cartList;	
	}
	
	
	/**
    * 장바구니 상품의 수량을 수정하는 메서드
    *
    * @param   ProdctValueObject 
    * @return  void
    * @exception  Exception
    */
	@RequestMapping(value = "/cartProdctAmontUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void cartProdctAmontUpdate(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		System.out.println(prodctValueObject.getProdctAmont());
		System.out.println(prodctValueObject.getCartBridgSeq());
		appMyPgService.cartProdctAmontUpdate(prodctValueObject);	
	}
	
	
	
	/**
    * 장바구니 상품의 수량을 수정하는 메서드(패키지)
    *
    * @param   ProdctValueObject 
    * @return  void
    * @exception  Exception
    */
	@RequestMapping(value = "/cartPackgAmontUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void cartPackgAmontUpdate(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		System.out.println(prodctValueObject.getProdctAmont());
		System.out.println(prodctValueObject.getCartBridgSeq());
		System.out.println(prodctValueObject.getPackgBridgCartSeq());
		appMyPgService.cartPackgAmontUpdate(prodctValueObject);	
	}
	
	
	
	/**
    * 체크된 장바구니 상품들 예상 합계 구하기
    *
    * @param   ProdctValueObject
    * @return  int
    * @exception  Exception
    */
	@RequestMapping(value = "/predictSumPriceRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public int predictSumPriceRead(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);		
		System.out.println("패키지그룹 :" +prodctValueObject.getCartPackgChkGroup());
		int predictSumPrice = appMyPgService.predictSumPriceRead(prodctValueObject);
		return predictSumPrice;
	}
	
	
	/**
    * 장바구니 안의 상품을 삭제하는 메서드(개별)
    *
    * @param   ProdctValueObject 
    * @return  void
    * @exception  Exception
    */
	@RequestMapping(value = "/cartProdctDelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void cartProdctDelete(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		System.out.println(prodctValueObject.getCartBridgSeq());
		appMyPgService.cartProdctDelete(prodctValueObject);
	}
	
	
	/**
    * 장바구니 안의 패키지를 삭제하는 메서드(개별)
    *
    * @param   ProdctValueObject 
    * @return  void
    * @exception  Exception
    */
	@RequestMapping(value = "/cartPackgDelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void cartPackgDelete(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		System.out.println(prodctValueObject.getCartBridgSeq());
		appMyPgService.cartPackgDelete(prodctValueObject);
	}
	
	
	
	/**
    * 장바구니 안의 선택된 상품을 삭제하는 메서드(일괄 삭제)
    *
    * @param   ProdctValueObject 
    * @return  void
    * @exception  Exception
    */
	@RequestMapping(value = "/cartProdctGroupDelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void cartProdctGroupDelete(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		System.out.println(prodctValueObject.getCartChkGroup());
		appMyPgService.cartProdctGroupDelete(prodctValueObject);
	}
	
	
	/**
    * 장바구니 -> 주문하기 할 때 기본 배송지를 조회하는 메서드 
    *
    * @param   @RequestParam("cusSeq")
    * @return  OrdrValueObject
    * @exception  Exception
    */
	@RequestMapping(value = "/existShippingPlaceRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public OrdrValueObject existShippingPlaceRead(@RequestParam("cusSeq") int cusSeq) throws Exception {
		Logger.info(null);
		OrdrValueObject ordrValueObject = appMyPgService.existShippingPlaceRead(cusSeq);
		return ordrValueObject;
	}
	
	/**
    * 배송지 리스트를 조회하는 메서드
    *
    * @param   @RequestParam("cusSeq")
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/ShippingPlaceList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<OrdrValueObject> ShippingPlaceList(@RequestParam("cusSeq") int cusSeq) throws Exception {
		Logger.info(null);
		List<OrdrValueObject> ordrVOList = appMyPgService.shippingPlaceList(cusSeq);
		return ordrVOList;
	}
	
	
	/**
    * 배송지를 변경할때 변경한 배송지를 상세조회하는 메서드
    *
    * @param   @RequestParam("shipngPlcSeq")
    * @return  OrdrValueObject
    * @exception  Exception
    */
	@RequestMapping(value = "/shippingPlaceRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public OrdrValueObject shippingPlaceRead(@RequestParam("shipngPlcSeq") int shipngPlcSeq) throws Exception {
		Logger.info(null);
		OrdrValueObject ordrValueObject = appMyPgService.shippingPlaceRead(shipngPlcSeq);
		return ordrValueObject;
	}
	

	/**
    * 고객 오른쪽 패널  - 쇼핑정보 조회 (주문 취소 제외 가장 최신 주문 정보 1건)
    * ex. 주문 완료   / 한입 사과 외 3건
    * @param   @RequestParam("cusSeq")
    * @return  OrdrValueObject
    * @exception  Exception
    */
	@RequestMapping(value = "/cusRightPanelOrdrInfo.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public OrdrValueObject cusRightPanelOrdrInfo(@RequestParam("cusSeq") int cusSeq) throws Exception {
		Logger.info(null);
		OrdrValueObject ordrValueObject = appMyPgService.cusRightPanelOrdrInfo(cusSeq);
		return ordrValueObject;
	}
	
	
	/**
    * 고객 오른쪽 패널  - 장바구니 상품 리스트 조회 (5개 까지만)
    * 
    * @param   @RequestParam("cusSeq")
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/cusRightPanelCartList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<OrdrValueObject> cusRightPanelCartList(@RequestParam("cusSeq") int cusSeq) throws Exception {
		Logger.info(null);
		List<OrdrValueObject> ordrVOList = appMyPgService.cusRightPanelCartList(cusSeq);
		return ordrVOList;
	}
	
	
	/**
    * 고객 오른쪽 패널  - 히스토리 상품 리스트 조회 (5개 까지만)
    * 
    * @param   @RequestParam("cusSeq")
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/cusRightPanelHistoryList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<OrdrValueObject> cusRightPanelHistoryList(@RequestParam("cusSeq") int cusSeq) throws Exception {
		Logger.info(null);
		List<OrdrValueObject> ordrVOList = appMyPgService.cusRightPanelHistoryList(cusSeq);
		return ordrVOList;
	}
	
	
	/**
    * 고객 오른쪽 패널  - 직원품 리스트 조회 
    * 
    * @param   void
    * @return  List<EmpValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/cusRightPanelEmpList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<EmpValueObject> cusRightPanelEmpList() throws Exception {
		Logger.info(null);
		List<EmpValueObject> empVOList = appMyPgService.cusRightPanelEmpList();
		return empVOList;
	}

	
	/**
    * 고객 마이페이지 메인 , 주문상태별 횟수 조회
    * 
    * @param   int
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/cusOrdrCountList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<OrdrValueObject> cusOrdrCountList(@RequestParam(value="cusSeq") int cusSeq) throws Exception {
		Logger.info(null);
		List<OrdrValueObject> ordrCountList = appMyPgService.cusOrdrCountList(cusSeq);
		return ordrCountList;
	}
	
	/**
    * 고객 정보 조회, 장바구니 개수, 히스토리 개수
    * 
    * @param   int
    * @return  CustmrValueObject
    * @exception  Exception
    */
	@RequestMapping(value = "/cusInfoCount.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public CustmrValueObject cusInfoCount(@RequestParam(value="cusSeq") int cusSeq) throws Exception {
		Logger.info(null);
		CustmrValueObject CustmrValueObject = appMyPgService.cusInfoCount(cusSeq);
		return CustmrValueObject;
	}
	
	
	
	/**
    * 간편결제를 할 때, 상품명을 받아오는 메서드 ex. 물망초 외 3건
    *
    * @param   OrdrValueObject 
    * @return  OrdrValueObject
    * @exception  Exception
    */
//	@RequestMapping(value = "/ordrCretFirstProdctRead.do", method = {RequestMethod.GET, RequestMethod.POST})
//	@ResponseBody
//	public OrdrValueObject ordrCretFirstProdctRead(OrdrValueObject ordrValueObject) throws Exception {
//		Logger.info(null);
//		OrdrValueObject firstProdct = appMyPgService.ordrCretFirstProdctRead(ordrValueObject);
//		return firstProdct;
//	}
	
	
	/**
    * 주문하기에서 결제를 하는 메서드(카드, 현금)
    *
    * @param   ProdctValueObject 
    * @return  void
    * @exception  Exception
    */
	@RequestMapping(value = "/ordrCretInsert.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void ordrCretInsert(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);
		System.out.println("수령자 : " +ordrValueObject.getRecvrNme());
		System.out.println("수령자핸드폰 : " +ordrValueObject.getRecvrMobl());
		System.out.println("수령자 우편번호 : " +ordrValueObject.getRecvrPostcd());
		System.out.println("요구사항 : " +ordrValueObject.getRecvrPostcd());
		System.out.println("결제 방법 : " +ordrValueObject.getPamntMethd());
		System.out.println("결제 가격 : " +ordrValueObject.getPricSum());
		System.out.println("희망배송 시간 : " +ordrValueObject.getHopDelvrTim());
		System.out.println("구매 상품 seq  : " +ordrValueObject.getProdctSeqGroup());
		System.out.println("구매  패키지 seq  : " +ordrValueObject.getPackgSeqGroup());
		System.out.println("구매 수량  : " +ordrValueObject.getOrdrAmont());
		System.out.println("공동구매 상품 seq  : " +ordrValueObject.getGropPurchsProdctSeq());
		appMyPgService.ordrCretInsert(ordrValueObject);
	}
	

	
	/**
    * 개인정보 확인, 값 조회
    *
    * @param   CustmrValueObject 
    * @return  CustmrValueObject
    * @exception  Exception
    */
	@RequestMapping(value = "/cusInfoConfirmRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public CustmrValueObject selectCusInfoCount(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		CustmrValueObject custmrInfo = appMyPgService.selectCusInfoCount(custmrValueObject);
		return custmrInfo;
	}
	
	
	/**
    * 고객 pw 조회
    *
    * @param   CustmrValueObject 
    * @return  CustmrValueObject
    * @exception  Exception
    */
	@RequestMapping(value = "/cusPwRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public CustmrValueObject cusPwRead(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		CustmrValueObject custmrInfo = appMyPgService.cusPwRead(custmrValueObject);
		return custmrInfo;
	}
	
	
	/**
    * 고객 정보 삭제
    *
    * @param   CustmrValueObject 
    * @return  void
    * @exception  Exception
    */
	@RequestMapping(value = "/cusDelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void cusDelete(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		appMyPgService.cusDelete(custmrValueObject);
	}
	
	
	/**
    * 고객 정보 수정
    *
    * @param   CustmrValueObject 
    * @return  void
    * @exception  Exception
    */
	@RequestMapping(value = "/cusInfoUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void cusInfoUpdate(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		appMyPgService.cusInfoUpdate(custmrValueObject);
	}
	
	
	/**
    * 고객 비밀번호 수정
    *
    * @param   CustmrValueObject 
    * @return  void
    * @exception  Exception
    */
	@RequestMapping(value = "/cusPwUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void cusPwUpdate(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		appMyPgService.cusPwUpdate(custmrValueObject);
	}
	
	
	/**
    * 행사 상품 리스트 조회
    *
    * @param   void
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/cusLeftPanelEventProdctList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<OrdrValueObject> cusLeftPanelEventProdctList() throws Exception {
		Logger.info(null);
		List<OrdrValueObject> ordrVOList = appMyPgService.cusLeftPanelEventProdctList();
		return ordrVOList;
	}
	
	
	/** 고객 왼쪽 패널 대분류 카테고리 리스트 조회
    * 
    *
    * @param   void
    * @return  List<CatgrValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/cusLeftPanelTopCatgrList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<CatgrValueObject> cusLeftPanelTopCatgrList() throws Exception {
		Logger.info(null);
		List<CatgrValueObject> catgrList = appMyPgService.cusLeftPanelTopCatgrList();
		return catgrList;
	}
	
	
	/** 고객 왼쪽 패널 중분류 카테고리 리스트 조회
    * 
    *
    * @param   void
    * @return  List<CatgrValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/cusLeftPanelMidCatgrList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<CatgrValueObject> cusLeftPanelMidCatgrList() throws Exception {
		Logger.info(null);
		List<CatgrValueObject> catgrList = appMyPgService.cusLeftPanelMidCatgrList();
		return catgrList;
	}
	
	
	/** 고객 왼쪽 패널 중분류 카테고리 선택 -> 상품 리스트 소분류 카테고리 리스트 조회
    * 
    *
    * @param   void
    * @return  List<CatgrValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/prodctListBotCatgrList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<CatgrValueObject> prodctListBotCatgrList(CatgrValueObject catgrValueObject) throws Exception {
		Logger.info(null);
		System.out.println("3333333 / "+catgrValueObject.getCatgrSeq());
		List<CatgrValueObject> catgrList = appMyPgService.prodctListBotCatgrList(catgrValueObject);
		return catgrList;	
	}
	

	
	
	/**
    * 주문하기에서 결제를 하는 메서드(간편결제)
    *
    * @param   ProdctValueObject 
    * @return  void
    * @exception  Exception
    */
	@RequestMapping(value = "/AGSMobile_start.do", method = {RequestMethod.GET, RequestMethod.POST})
//		@ResponseBody
	public String AGSMobile_start(@RequestParam("buyProdctNmeCount") String buyProdctNmeCount, @RequestParam("pricSum") int pricSum, @RequestParam("custmrNme") String custmrNme, @RequestParam("custmrMobl") String custmrMobl, @RequestParam("custmrEml") String custmrEml, Model model) throws Exception {
		Logger.info(null);
		System.out.println("총 가격 : " +pricSum);	
		model.addAttribute("pricSum", pricSum);	// 검색 조건, 페이지 정보들	
		
		model.addAttribute("buyProdctNmeCount",URLDecoder.decode(buyProdctNmeCount,"UTF-8"));	// 검색 조건, 페이지 정보들
		model.addAttribute("custmrNme",URLDecoder.decode(custmrNme,"UTF-8"));	// 검색 조건, 페이지 정보들
		model.addAttribute("custmrMobl", custmrMobl);	// 검색 조건, 페이지 정보들
		model.addAttribute("custmrEml", custmrEml);	// 검색 조건, 페이지 정보들
		return "pay/AGSMobile_start";
	}
	
	// 결제 성공 페이지
	@RequestMapping(value = "/AGSMobile_approve.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String AGSMobile_approve() throws Exception {
		Logger.info(null);
		
		return "pay/AGSMobile_approve";
	}
	
	
	// 결제 취소 페이지
	@RequestMapping(value = "/AGSMobile_user_cancel.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String AGSMobile_user_cancel() throws Exception {
		Logger.info(null);
		return "pay/AGSMobile_user_cancel";
	}
		
		
	// 결제 통보 페이지
	@RequestMapping(value = "/AGSMobile_virtual_result.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String AGSMobile_virtual_result() throws Exception {
		Logger.info(null);
		return "pay/AGSMobile_virtual_result";
	}
	
		
	
	
	
	/**
    * 일반상품 주문 목록 리스트를 조회하는 메서드(전체, 주문완료, 배송중, 배송완료, 취소) 
    *
    * @param   OrdrValueObject 
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/cusOrdrList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<OrdrValueObject> cusOrdrList(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);
		System.out.println(ordrValueObject.getCustmrSeq());
		System.out.println(ordrValueObject.getOrdrStatSeq());
		List<OrdrValueObject> cusOrdrList = appMyPgService.cusOrdrList(ordrValueObject);
		return cusOrdrList;
	}
	
	
	/**
    * 공동구매 주문 목록 리스트를 조회하는 메서드(전체, 주문완료, 배송중, 배송완료, 취소) 
    *
    * @param   OrdrValueObject 
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/cusDahamgaeOrdrList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<OrdrValueObject> cusDahamgaeOrdrList(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);
		System.out.println(ordrValueObject.getCustmrSeq());
		System.out.println(ordrValueObject.getOrdrStatSeq());
		List<OrdrValueObject> cusOrdrList = appMyPgService.cusDahamgaeOrdrList(ordrValueObject);
		return cusOrdrList;
	}
	
	
	
	/**
    * 상품 상세보기에서 주문 정보를 조회하는 메서드
    *
    * @param   OrdrValueObject 
    * @return  OrdrValueObject
    * @exception  Exception
    */
	@RequestMapping(value = "/cusOrdrRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public OrdrValueObject cusOrdrRead(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);
		System.out.println(ordrValueObject.getCustmrSeq());
		System.out.println(ordrValueObject.getOrdrStatSeq());
		OrdrValueObject ordrRead = appMyPgService.cusOrdrRead(ordrValueObject);
		return ordrRead;
	}
	
	
	/**
    * 일반상품 주문 상세보기에서 상품 리스트를 조회
    *
    * @param   OrdrValueObject 
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/ordrReadProdctList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<OrdrValueObject> ordrReadProdctList(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);
		System.out.println(ordrValueObject.getCustmrSeq());
		System.out.println(ordrValueObject.getOrdrStatSeq());
		List<OrdrValueObject> cusOrdrList = appMyPgService.ordrReadProdctList(ordrValueObject);
		return cusOrdrList;
	}
	
	/**
    * 공동구매 주문 상세보기에서 공동구매 상품 리스트를 조회
    *
    * @param   OrdrValueObject 
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/ordrReadDahamgaeList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<OrdrValueObject> ordrReadDahamgaeList(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);
		System.out.println(ordrValueObject.getCustmrSeq());
		System.out.println(ordrValueObject.getOrdrStatSeq());
		List<OrdrValueObject> cusOrdrList = appMyPgService.ordrReadDahamgaeList(ordrValueObject);
		return cusOrdrList;
	}
	
	
	
	/**
    * 주문 상세보기에서 주문 취소를 했을 경우, 주문취소
    *
    * @param   OrdrValueObject 
    * @return  void
    * @exception  Exception
    */
	@RequestMapping(value = "/cusOrdrCancel.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void cusOrdrCancel(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);
		appMyPgService.cusOrdrCancel(ordrValueObject);
	}
	
	
	
	
	/**
    * 장바구니 에서 주문하기할때 상품명 가져가기 ex. 물망초  외 3종
    *
    * @param   OrdrValueObject 
    * @return  OrdrValueObject
    * @exception  Exception
    */
	@RequestMapping(value = "/cartBuyProdctNme.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public OrdrValueObject cartBuyProdctNme(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);
		OrdrValueObject cartBuyProdctNme = appMyPgService.cartBuyProdctNme(ordrValueObject);
		return cartBuyProdctNme;		
	}

	
	
	/**
    * 푸시알림설정 수정
    *
    * @param   CustmrValueObject 
    * @return  void
    * @exception  Exception
    */
	@RequestMapping(value = "/pushCheckUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void pushCheckUpdate(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		appMyPgService.pushCheckUpdate(custmrValueObject);		
	}

	
	
	/**
    * 히스토리 조회
    *
    * @param   ProdctValueObject 
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/historyList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<ProdctValueObject> historyList(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		List<ProdctValueObject> historyList = appMyPgService.historyList(prodctValueObject);
		return historyList;
	}
	
	
	
	
	/**
    * 고객이 직원 추천
    * 
    * @param   CustmrValueObject
    * @return  void
    * @exception  Exception
    */
	@RequestMapping(value = "/custmrRecmndCreate.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void custmrRecmndCreate(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		appMyPgService.custmrRecmndCreate(custmrValueObject);
	}
	
	
	
	/**
    * 고객이 직원 추천을 했는지 체크 (이번달에)
    * 
    * @param   CustmrValueObject
    * @return  CustmrValueObject
    * @exception  Exception
    */
	@RequestMapping(value = "/custmrRecmndCheck.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public CustmrValueObject custmrRecmndCheck(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		CustmrValueObject custmrRecmndCheck = appMyPgService.custmrRecmndCheck(custmrValueObject);
		return custmrRecmndCheck;
	}
	
	
	/**
    * 테스트
    * 
    * @param   
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/test.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String test() throws Exception {
		Logger.info(null);
		return "pay/testPopup";
	}
	
	
	/**
    * 테스트
    * 
    * @param   
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/testClose.do", method = {RequestMethod.GET, RequestMethod.POST})
	public void testClose() throws Exception {
		Logger.info(null);
		
	}
	
	
	

	/** 카테고리 중분류 검색
    * 
    *
    * @param   CatgrValueObject
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/appProdctMidCatgrSearch.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<ProdctValueObject> appProdctMidCatgrSearch(CatgrValueObject catgrValueObject) throws Exception {
		Logger.info(null);
		List<ProdctValueObject> ProdctList = appMyPgService.appProdctMidCatgrSearch(catgrValueObject);
		return ProdctList;	
	}
	
	
	
	
	/**
    * 배송 완료에서 배달한 직원 추천
    * 
    * @param   @RequestParam("ordrRecmndUpdate")
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/ordrRecmndUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void ordrRecmndUpdate(@RequestParam("ordrNumbrSeq") int ordrNumbrSeq) throws Exception {
		Logger.info(null);
		appMyPgService.ordrRecmndUpdate(ordrNumbrSeq);
	}
	
}

