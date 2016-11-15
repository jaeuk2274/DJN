package net.su.app.appMyPg.service;

import java.util.List;

import javax.annotation.Resource;

import net.su.app.appMyPg.dataAccessObject.AppCusMyPgDataAccessObject;
import net.su.custmr.valueObject.CustmrValueObject;
import net.su.custmr.valueObject.OrdrValueObject;
import net.su.logger.Logger;
import net.su.market.valueObject.EmpValueObject;
import net.su.prodct.valueObject.CatgrValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class AppCusMyPgServiceImpl implements AppCusMyPgService{

	@Resource
	private AppCusMyPgDataAccessObject appMyPgDAO;
	
	/**
    * 장바구니 목록들을 조회하는 메서드
    *
    * @param   int
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> cartList(int cusSeq) throws Exception{	
		List<ProdctValueObject> cartList = appMyPgDAO.cartList(cusSeq);
		return cartList;
	}
	
	
	/**
    * 장바구니 패키지 목록들을 조회하는 메서드
    *
    * @param   int
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> cartPackgList(int cusSeq) throws Exception{	
		List<ProdctValueObject> cartPackgList = appMyPgDAO.cartPackgList(cusSeq);
		return cartPackgList;
	}
	

	
	/**
    * 장바구니 상품의 수량을 수정하는 메서드
    *
    * @param   prodctValueObject 
    * @return  void
    * @exception  Exception
    */
	public void cartProdctAmontUpdate(ProdctValueObject prodctValueObject) throws Exception{
		appMyPgDAO.cartProdctAmontUpdate(prodctValueObject);
	}
	
	
	/**
    * 장바구니 상품의 수량을 수정하는 메서드(패키지)
    *
    * @param   prodctValueObject 
    * @return  void
    * @exception  Exception
    */
	public void cartPackgAmontUpdate(ProdctValueObject prodctValueObject) throws Exception{
		appMyPgDAO.cartPackgAmontUpdate(prodctValueObject);
	}
	
	
	/**
    * 체크된 장바구니 상품들 예상 합계 구하기
    *
    * @param   ProdctValueObject
    * @return  int
    * @exception  Exception
    */
	public int predictSumPriceRead(ProdctValueObject prodctValueObject) throws Exception {
		int predictSumPrice = appMyPgDAO.predictSumPriceRead(prodctValueObject);
		return predictSumPrice;
	}
	
	
	/**
    * 장바구니 안의 상품을 삭제하는 메서드
    *
    * @param   ProdctValueObject
    * @return  void
    * @exception  Exception
    */
	public void cartProdctDelete(ProdctValueObject prodctValueObject) throws Exception{
		appMyPgDAO.cartProdctDelete(prodctValueObject);
	}
	
	
	
	/**
    * 장바구니 안의 패키지를 삭제하는 메서드
    *
    * @param   ProdctValueObject
    * @return  void
    * @exception  Exception
    */
	public void cartPackgDelete(ProdctValueObject prodctValueObject) throws Exception{
		appMyPgDAO.cartPackgDelete(prodctValueObject);
	}
	
	
	
	/**
    * 장바구니 안의 선택된 상품을 삭제하는 메서드(일괄 삭제)
    *
    * @param   ProdctValueObject 
    * @return  void
    * @exception  Exception
    */
	
	public void cartProdctGroupDelete(ProdctValueObject prodctValueObject) throws Exception{
		appMyPgDAO.cartProdctGroupDelete(prodctValueObject);
	}
	
	
	/**
    * 장바구니 -> 주문하기 할 때 기본 배송지를 조회하는 메서드 
    *
    * @param   int
    * @return  OrdrValueObject
    * @exception  Exception
    */
	public OrdrValueObject existShippingPlaceRead(int cusSeq) throws Exception {
		OrdrValueObject ordrValueObject = appMyPgDAO.existShippingPlaceRead(cusSeq);
		return ordrValueObject;		
	}
	
	
	/**
    * 배송지 리스트를 조회하는 메서드
    *
    * @param   int
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> shippingPlaceList(int cusSeq) throws Exception{
		List<OrdrValueObject> ordrVOList = appMyPgDAO.shippingPlaceList(cusSeq);
		return ordrVOList;
	}
	
	
	/**
    * 배송지를 변경할때 변경한 배송지를 상세조회하는 메서드
    *
    * @param   int
    * @return  OrdrValueObject
    * @exception  Exception
    */
	public OrdrValueObject shippingPlaceRead(int shipngPlcSeq) throws Exception{
		OrdrValueObject ordrValueObject = appMyPgDAO.shippingPlaceRead(shipngPlcSeq);
		return ordrValueObject;	
	}
	
	
	/**
    * 고객 오른쪽 패널  - 쇼핑정보 조회 (주문 취소 제외 가장 최신 주문 정보 1건)
    * ex. 주문 완료   / 한입 사과 외 3건
    * @param   int
    * @return  OrdrValueObject
    * @exception  Exception
    */
	public OrdrValueObject cusRightPanelOrdrInfo(int cusSeq) throws Exception {
		OrdrValueObject ordrValueObject = appMyPgDAO.cusRightPanelOrdrInfo(cusSeq);
		return ordrValueObject;	
	}
	
	
	
	/**
    * 고객 오른쪽 패널  - 장바구니 상품 리스트 조회 (5개 까지만)
    * 
    * @param   int
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> cusRightPanelCartList(int cusSeq) throws Exception{
		List<OrdrValueObject> ordrVOList = appMyPgDAO.cusRightPanelCartList(cusSeq);
		return ordrVOList;
	}
	
	
	/**
    * 고객 오른쪽 패널  - 히스토리 상품 리스트 조회 (5개 까지만)
    * 
    * @param   int
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> cusRightPanelHistoryList(int cusSeq) throws Exception{
		List<OrdrValueObject> ordrVOList = appMyPgDAO.cusRightPanelHistoryList(cusSeq);
		return ordrVOList;
	}
	
	
	
	/**
    * 고객 오른쪽 패널  - 직원품 리스트 조회 
    * 
    * @param   void
    * @return  List<EmpValueObject>
    * @exception  Exception
    */
	public List<EmpValueObject> cusRightPanelEmpList() throws Exception {
		List<EmpValueObject> empVOList = appMyPgDAO.cusRightPanelEmpList();
		return empVOList;
	}
	
	
	/**
    * 고객 마이페이지 메인 , 주문상태별 횟수 조회
    * 
    * @param   int
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> cusOrdrCountList(int cusSeq) throws Exception {
		List<OrdrValueObject> ordrCountList = appMyPgDAO.cusOrdrCountList(cusSeq);
		return ordrCountList;
	}
	
	
	/**
    * 고객 정보 조회, 장바구니 개수, 히스토리 개수
    * 
    * @param   int
    * @return  CustmrValueObject
    * @exception  Exception
    */
	public CustmrValueObject cusInfoCount(int cusSeq) throws Exception {
		CustmrValueObject custmrValueObject = appMyPgDAO.cusInfoCount(cusSeq);
		return custmrValueObject;			
	}
	
	
	/**
    * 주문 목록 리스트를 조회하는 메서드(전체, 주문완료, 배송중, 배송완료, 취소) 
    *
    * @param   OrdrValueObject 
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> cusOrdrList(OrdrValueObject ordrValueObject) throws Exception{
		List<OrdrValueObject> ordrList = appMyPgDAO.cusOrdrList(ordrValueObject);
		return ordrList;
	}
	
	
	
	/**
    * 공동구매 주문 목록 리스트를 조회하는 메서드(전체, 주문완료, 배송중, 배송완료, 취소) 
    *
    * @param   OrdrValueObject 
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> cusDahamgaeOrdrList(OrdrValueObject ordrValueObject) throws Exception{
		List<OrdrValueObject> ordrList = appMyPgDAO.cusDahamgaeOrdrList(ordrValueObject);
		return ordrList;
	}
	
	
	/**
    * 상품 상세보기에서 주문 정보를 조회하는 메서드
    *
    * @param   OrdrValueObject 
    * @return  OrdrValueObject
    * @exception  Exception
    */
	public OrdrValueObject cusOrdrRead(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);
		OrdrValueObject ordrRead = appMyPgDAO.cusOrdrRead(ordrValueObject);
		return ordrRead;
	}
	
	
	
	/**
    * 일반상품 주문 상세보기에서 상품 리스트를 조회
    *
    * @param   OrdrValueObject 
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> ordrReadProdctList(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);
		System.out.println(ordrValueObject.getCustmrSeq());
		System.out.println(ordrValueObject.getOrdrStatSeq());
		List<OrdrValueObject> cusOrdrList = appMyPgDAO.ordrReadProdctList(ordrValueObject);
		return cusOrdrList;
	}
	
	
	
	/**
    * 공동구매 주문 상세보기에서 공동구매 상품 리스트를 조회
    *
    * @param   OrdrValueObject 
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> ordrReadDahamgaeList(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);
		List<OrdrValueObject> cusOrdrList = appMyPgDAO.ordrReadDahamgaeList(ordrValueObject);
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
		appMyPgDAO.cusOrdrCancel(ordrValueObject);
	}
	
	
	/**
    * 주문하기에서 결제를 하는 메서드
    *
    * @param   ProdctValueObject 
    * @return  void
    * @exception  Exception
    */
	public void ordrCretInsert(OrdrValueObject ordrValueObject) throws Exception {
		appMyPgDAO.ordrCretInsert(ordrValueObject);
	}
	
	
	
	
	/**
    * 개인정보 확인, 값 조회
    *
    * @param   CustmrValueObject 
    * @return  CustmrValueObject
    * @exception  Exception
    */
	public CustmrValueObject selectCusInfoCount(CustmrValueObject custmrValueObject) throws Exception{
		CustmrValueObject custmrInfo = appMyPgDAO.selectCusInfoCount(custmrValueObject);
		return custmrInfo;
	}
	
	
	
	/**
    * 고객 pw 조회
    *
    * @param   CustmrValueObject 
    * @return  CustmrValueObject
    * @exception  Exception
    */
	public CustmrValueObject cusPwRead(CustmrValueObject custmrValueObject) throws Exception{
		CustmrValueObject custmrInfo = appMyPgDAO.cusPwRead(custmrValueObject);
		return custmrInfo;
	}
	
	
	
	/**
    * 고객 정보 삭제
    *
    * @param   CustmrValueObject 
    * @return  void
    * @exception  Exception
    */
	public void cusDelete(CustmrValueObject custmrValueObject) throws Exception {
		appMyPgDAO.cusDelete(custmrValueObject);		
	}
	
	
	
	/**
    * 고객 정보 수정
    *
    * @param   CustmrValueObject 
    * @return  void
    * @exception  Exception
    */
	public void cusInfoUpdate(CustmrValueObject custmrValueObject) throws Exception{
		appMyPgDAO.cusInfoUpdate(custmrValueObject);
	}
	
	
	
	/**
    * 고객 비밀번호 수정
    *
    * @param   CustmrValueObject 
    * @return  void
    * @exception  Exception
    */
	public void cusPwUpdate(CustmrValueObject custmrValueObject) throws Exception{
		appMyPgDAO.cusPwUpdate(custmrValueObject);
	}
	
	
	
	/**
    * 행사 상품 리스트 조회
    *
    * @param   void
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> cusLeftPanelEventProdctList() throws Exception {
		Logger.info(null);
		List<OrdrValueObject> ordrVOList = appMyPgDAO.cusLeftPanelEventProdctList();
		return ordrVOList;
	}
	
	
	
	/** 고객 왼쪽 패널 대분류 카테고리 리스트 조회
    * 
    *
    * @param   void
    * @return  List<CatgrValueObject>
    * @exception  Exception
    */
	public List<CatgrValueObject> cusLeftPanelTopCatgrList() throws Exception {
		List<CatgrValueObject> catgrList = appMyPgDAO.cusLeftPanelTopCatgrList();
		return catgrList;		
	}
	
	
	
	/** 고객 왼쪽 패널 중분류 카테고리 리스트 조회
    * 
    *
    * @param   void
    * @return  List<CatgrValueObject>
    * @exception  Exception
    */
	public List<CatgrValueObject> cusLeftPanelMidCatgrList() throws Exception{
		List<CatgrValueObject> catgrList = appMyPgDAO.cusLeftPanelMidCatgrList();
		return catgrList;
	}
	
	
	/** 고객 왼쪽 패널 중분류 카테고리 선택 -> 상품 리스트 소분류 카테고리 리스트 조회
    * 
    *
    * @param   void
    * @return  List<CatgrValueObject>
    * @exception  Exception
    */
	public List<CatgrValueObject> prodctListBotCatgrList(CatgrValueObject catgrValueObject) throws Exception{
		Logger.info(null);
		List<CatgrValueObject> catgrList = appMyPgDAO.prodctListBotCatgrList(catgrValueObject);
		return catgrList;
	}
	
	
	
	/**
    * 장바구니 에서 주문하기할때 상품명 가져가기 ex. 물망초  외 3종
    *
    * @param   OrdrValueObject 
    * @return  OrdrValueObject
    * @exception  Exception
    */
	public OrdrValueObject cartBuyProdctNme(OrdrValueObject ordrValueObject) throws Exception{
		Logger.info(null);
		OrdrValueObject cartBuyProdctNme = appMyPgDAO.cartBuyProdctNme(ordrValueObject);
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
		appMyPgDAO.pushCheckUpdate(custmrValueObject);	
	}
	
	
	
	/**
    * 히스토리 조회
    *
    * @param   ProdctValueObject 
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> historyList(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		List<ProdctValueObject> historyList = appMyPgDAO.historyList(prodctValueObject);	
		return historyList;
	}
	
	
	/**
    * 고객이 직원 추천
    * 
    * @param   CustmrValueObject
    * @return  void
    * @exception  Exception
    */
	public void custmrRecmndCreate(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		appMyPgDAO.custmrRecmndCreate(custmrValueObject);
	}
	
	
	
	/**
    * 고객이 직원 추천을 했는지 체크 (이번달에)
    * 
    * @param   CustmrValueObject
    * @return  CustmrValueObject
    * @exception  Exception
    */
	public CustmrValueObject custmrRecmndCheck(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		CustmrValueObject custmrRecmndCheck = appMyPgDAO.custmrRecmndCheck(custmrValueObject);
		return custmrRecmndCheck;
	}
	
	
	/** 카테고리 중분류 검색
    * 
    *
    * @param   CatgrValueObject
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> appProdctMidCatgrSearch(CatgrValueObject catgrValueObject) throws Exception{
		Logger.info(null);
		List<ProdctValueObject> ProdctList = appMyPgDAO.appProdctMidCatgrSearch(catgrValueObject);
		return ProdctList;	
	}
	
	
	/**
    * 배송 완료에서 배달한 직원 추천
    * 
    * @param   @RequestParam("ordrRecmndUpdate")
    * @return  
    * @exception  Exception
    */
	public void ordrRecmndUpdate(int ordrNumbrSeq) throws Exception {
		Logger.info(null);
		appMyPgDAO.ordrRecmndUpdate(ordrNumbrSeq);
	}
}
