package net.su.app.appMyPg.dataAccessObject;

import java.util.List;

import net.su.custmr.valueObject.CustmrValueObject;
import net.su.custmr.valueObject.OrdrValueObject;
import net.su.logger.Logger;
import net.su.login.valueObject.LoginValueObject;
import net.su.market.valueObject.EmpValueObject;
import net.su.prodct.valueObject.CatgrValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.su.security.Base64Utils;

@Repository
public class AppCusMyPgDataAccessObject extends SqlSessionDaoSupport{
	
	Base64Utils base64 = new Base64Utils();
	String encryptKey = "temp11111111111111111111";//key 선언 
	/**
    * 장바구니 목록들을 조회하는 메서드
    *
    * @param   int
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> cartList(int cusSeq) throws Exception{	
		Logger.info(null);
		List<ProdctValueObject> cartList = getSqlSession().selectList("appMyPgMapper.cartList", cusSeq);	
		return cartList;
	}
	
	
	/**
    * 장바구니 공동구매 목록들을 조회하는 메서드
    *
    * @param   int
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> cartPackgList(int cusSeq) throws Exception{	
		Logger.info(null);
		List<ProdctValueObject> cartGropPurchsList = getSqlSession().selectList("appMyPgMapper.cartPackgList", cusSeq);	
		return cartGropPurchsList;
	}
	
	/**
    * 장바구니 상품의 수량을 수정하는 메서드
    *
    * @param   prodctValueObject 
    * @return  void
    * @exception  Exception
    */
	public void cartProdctAmontUpdate(ProdctValueObject prodctValueObject) throws Exception{
		Logger.info(null);
		getSqlSession().update("appMyPgMapper.cartProdctAmontUpdate", prodctValueObject);		
	}
	
	
	/**
    * 장바구니 상품의 수량을 수정하는 메서드(패키지)
    *
    * @param   prodctValueObject 
    * @return  void
    * @exception  Exception
    */
	public void cartPackgAmontUpdate(ProdctValueObject prodctValueObject) throws Exception{
		Logger.info(null);
		getSqlSession().update("appMyPgMapper.cartPackgAmontUpdate", prodctValueObject);		
	}	
	
	
	
	
	
	/**
    * 체크된 장바구니 상품들 예상 합계 구하기
    *
    * @param   ProdctValueObject 
    * @return  int
    * @exception  Exception
    */
	public int predictSumPriceRead(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		List<Integer> cartChkGroup = prodctValueObject.getCartChkGroup();
		List<Integer> cartPackgChkGroup = prodctValueObject.getCartPackgChkGroup();
		System.out.println(cartChkGroup);
		System.out.println(cartPackgChkGroup);
		int predictSumPrice=0;
		if(cartChkGroup.isEmpty()) {
			predictSumPrice = getSqlSession().selectOne("appMyPgMapper.predictPackgSumPriceRead", cartPackgChkGroup);	
		}
		else if(cartPackgChkGroup.isEmpty()) {
			predictSumPrice = getSqlSession().selectOne("appMyPgMapper.predictSumPriceRead", cartChkGroup);	
		}
		else {
			predictSumPrice = getSqlSession().selectOne("appMyPgMapper.predictSumPriceRead", cartChkGroup);
			int packgPredictSumPrice = getSqlSession().selectOne("appMyPgMapper.predictPackgSumPriceRead", cartPackgChkGroup);	
			predictSumPrice = predictSumPrice + packgPredictSumPrice;
			
		}
		System.out.println("합계 : " + predictSumPrice );
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
		Logger.info(null);
		getSqlSession().delete("appMyPgMapper.cartProdctDelete", prodctValueObject);	
	}
	
	
	/**
    * 장바구니 안의 패키지를 삭제하는 메서드
    *
    * @param   ProdctValueObject
    * @return  void
    * @exception  Exception
    */
	public void cartPackgDelete(ProdctValueObject prodctValueObject) throws Exception{
		Logger.info(null);
		getSqlSession().delete("appMyPgMapper.cartPackgDelete", prodctValueObject);	
	}
	
	
	/**
    * 장바구니 안의 선택된 상품을 삭제하는 메서드(일괄 삭제)
    *
    * @param   ProdctValueObject 
    * @return  void
    * @exception  Exception
    */
	
	public void cartProdctGroupDelete(ProdctValueObject prodctValueObject) throws Exception{
		Logger.info(null);
		List<Integer> cartChkGroup = prodctValueObject.getCartChkGroup();
		List<Integer> cartPackgChkGroup = prodctValueObject.getCartPackgChkGroup();
		System.out.println(cartChkGroup);
		System.out.println(cartPackgChkGroup);
		if(cartChkGroup.isEmpty()) {
			getSqlSession().delete("appMyPgMapper.cartPackgGroupDelete", cartPackgChkGroup);
		}
		else if(cartPackgChkGroup.isEmpty()) {
			getSqlSession().delete("appMyPgMapper.cartProdctGroupDelete", cartChkGroup);
		}
		else {
			
			getSqlSession().delete("appMyPgMapper.cartProdctGroupDelete", cartChkGroup);
			getSqlSession().delete("appMyPgMapper.cartPackgGroupDelete", cartPackgChkGroup);
			
		}	
	}
	
	
	/**
    * 장바구니 -> 주문하기 할 때 기본 배송지를 조회하는 메서드 
    *
    * @param   int
    * @return  OrdrValueObject
    * @exception  Exception
    */
	public OrdrValueObject existShippingPlaceRead(int cusSeq) throws Exception {
		Logger.info(null);
		OrdrValueObject ordrValueObject = getSqlSession().selectOne("appMyPgMapper.existShippingPlaceRead", cusSeq);	
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
		Logger.info(null);
		List<OrdrValueObject> ordrVOList = getSqlSession().selectList("appMyPgMapper.shippingPlaceList", cusSeq);
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
		Logger.info(null);
		OrdrValueObject ordrValueObject = getSqlSession().selectOne("appMyPgMapper.shippingPlaceRead", shipngPlcSeq);	
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
		Logger.info(null);
		OrdrValueObject ordrValueObject = getSqlSession().selectOne("appMyPgMapper.cusRightPanelOrdrInfo", cusSeq);	
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
		Logger.info(null);
		List<OrdrValueObject> ordrVOList = getSqlSession().selectList("appMyPgMapper.cusRightPanelCartList", cusSeq);
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
		Logger.info(null);
		List<OrdrValueObject> ordrVOList = getSqlSession().selectList("appMyPgMapper.cusRightPanelHistoryList", cusSeq);
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
		Logger.info(null);
		List<EmpValueObject> empVOList = getSqlSession().selectList("appMyPgMapper.cusRightPanelEmpList");
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
		Logger.info(null);
		List<OrdrValueObject> ordrVOList = getSqlSession().selectList("appMyPgMapper.cusOrdrCountList", cusSeq);
		return ordrVOList;
	}
	
	
	/**
    * 고객 정보 조회, 장바구니 개수, 히스토리 개수
    * 
    * @param   int
    * @return  CustmrValueObject
    * @exception  Exception
    */
	public CustmrValueObject cusInfoCount(int cusSeq) throws Exception {
		Logger.info(null);
		CustmrValueObject custmrValueObject = getSqlSession().selectOne("appMyPgMapper.cusInfoCount", cusSeq);
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
		Logger.info(null);
		List<OrdrValueObject> ordrVOList = getSqlSession().selectList("appMyPgMapper.cusOrdrList", ordrValueObject);
		return ordrVOList;
	}
	
	
	/**
    * 공동구매 주문 목록 리스트를 조회하는 메서드(전체, 주문완료, 배송중, 배송완료, 취소) 
    *
    * @param   OrdrValueObject 
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> cusDahamgaeOrdrList(OrdrValueObject ordrValueObject) throws Exception{
		List<OrdrValueObject> ordrList = getSqlSession().selectList("appMyPgMapper.cusDahamgaeOrdrList", ordrValueObject);
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
		OrdrValueObject ordrRead = getSqlSession().selectOne("appMyPgMapper.cusOrdrRead", ordrValueObject);
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
		List<OrdrValueObject> cusOrdrList = getSqlSession().selectList("appMyPgMapper.ordrReadProdctList", ordrValueObject);
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
		List<OrdrValueObject> cusOrdrList = getSqlSession().selectList("appMyPgMapper.ordrReadDahamgaeList", ordrValueObject);
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
		getSqlSession().update("appMyPgMapper.cusOrdrCancel", ordrValueObject);
	}
	
	
	/**
    * 주문하기에서 결제를 하는 메서드
    *
    * @param   ProdctValueObject 
    * @return  void
    * @exception  Exception
    */
	@Transactional
	public void ordrCretInsert(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);
		// 주문 번호 생성 및 주문자,수령자 정보 등록
		getSqlSession().insert("appMyPgMapper.ordrCretInsert", ordrValueObject);
		
		int gropPurchsProdctSeq = ordrValueObject.getGropPurchsProdctSeq();
		if(gropPurchsProdctSeq != 0){
			getSqlSession().insert("appMyPgMapper.insertGropPurchsOrdrRecrd", ordrValueObject);
		}
		
		List<Integer> cartChkGroup = ordrValueObject.getProdctSeqGroup();
		List<Integer> cartPackgChkGroup = ordrValueObject.getPackgSeqGroup();
		System.out.println(cartChkGroup);
		System.out.println(cartPackgChkGroup);
		if(cartChkGroup.isEmpty()) {
			getSqlSession().insert("appMyPgMapper.ordrPackgRecordInsert", ordrValueObject);
			getSqlSession().delete("appMyPgMapper.cartPackgGroupDelete", cartPackgChkGroup);
		}
		else if(cartPackgChkGroup.isEmpty()) {
			getSqlSession().insert("appMyPgMapper.ordrProductRecordInsert", ordrValueObject);
			// 매출 테이블에 등록 (rels_tb)
			getSqlSession().insert("appMyPgMapper.ordrProductRecordRelsTbInsert", ordrValueObject);
			getSqlSession().delete("appMyPgMapper.cartProdctGroupDelete", cartChkGroup);
		}
		else {			
			// 주문 기록 등록(상품)
			getSqlSession().insert("appMyPgMapper.ordrProductRecordInsert", ordrValueObject);
			// 주문 기록 등록(패키지)
			getSqlSession().insert("appMyPgMapper.ordrPackgRecordInsert", ordrValueObject);
			// 매출 테이블에 등록 (rels_tb)
			getSqlSession().insert("appMyPgMapper.ordrProductRecordRelsTbInsert", ordrValueObject);
			// 테스트한다고 삭제는 주석처리 해놓음 풀어야함
			// 장바구니 삭제 (상품)
			getSqlSession().delete("appMyPgMapper.cartProdctGroupDelete", cartChkGroup);
			// 장바구니 삭제 (패키지)
			getSqlSession().delete("appMyPgMapper.cartPackgGroupDelete", cartPackgChkGroup);	
		}
	}
	
	
	
	
	/**
    * 개인정보 확인, 값 조회
    *
    * @param   CustmrValueObject 
    * @return  CustmrValueObject
    * @exception  Exception
    */
	public CustmrValueObject selectCusInfoCount(CustmrValueObject custmrValueObject) throws Exception{
		Logger.info(null);
		Logger.info("모바일 로그인 서비스인플"+custmrValueObject.getCustmrPw());
//		//기본 pw
		String W_ORG_FG = custmrValueObject.getCustmrPw(); //암호화할 문자열
//		//암호화 pw
		String EN_ORG_FG = base64.encrypt(W_ORG_FG, encryptKey);
		
		custmrValueObject.setCustmrPw(EN_ORG_FG);

		
		CustmrValueObject custmrInfo = getSqlSession().selectOne("appMyPgMapper.selectCusInfoCount", custmrValueObject);
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
		Logger.info(null);
		CustmrValueObject custmrInfo = getSqlSession().selectOne("appMyPgMapper.cusPwRead", custmrValueObject);
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
		Logger.info(null);
		System.out.println(custmrValueObject.getCustmrSeq());
		System.out.println(custmrValueObject.getCustmrSeq());
		getSqlSession().delete("appMyPgMapper.cusDelete", custmrValueObject);		
	}
	
	
	
	/**
    * 고객 정보 수정
    *
    * @param   CustmrValueObject 
    * @return  void
    * @exception  Exception
    */
	public void cusInfoUpdate(CustmrValueObject custmrValueObject) throws Exception{
		getSqlSession().update("appMyPgMapper.cusInfoUpdate", custmrValueObject);		
	}
	
	
	
	/**
    * 고객 비밀번호 수정
    *
    * @param   CustmrValueObject 
    * @return  void
    * @exception  Exception
    */
	public void cusPwUpdate(CustmrValueObject custmrValueObject) throws Exception{
		getSqlSession().update("appMyPgMapper.cusPwUpdate", custmrValueObject);
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
		List<OrdrValueObject> ordrVOList = getSqlSession().selectList("appMyPgMapper.cusLeftPanelEventProdctList");
		return ordrVOList;
	}
	
	
	
	/** 고객 왼쪽 패널 카테고리 리스트 조회
    * 
    *
    * @param   void
    * @return  List<CatgrValueObject>
    * @exception  Exception
    */
	public List<CatgrValueObject> cusLeftPanelTopCatgrList() throws Exception {
		List<CatgrValueObject> catgrList = getSqlSession().selectList("appMyPgMapper.cusLeftPanelTopCatgrList");
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
		Logger.info(null);
		List<CatgrValueObject> catgrList = getSqlSession().selectList("appMyPgMapper.cusLeftPanelMidCatgrList");
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
		List<CatgrValueObject> catgrList = getSqlSession().selectList("appMyPgMapper.prodctListBotCatgrList", catgrValueObject);
		return catgrList;	
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
		getSqlSession().update("appMyPgMapper.pushCheckUpdate", custmrValueObject);
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
		List<ProdctValueObject> historyList = getSqlSession().selectList("appMyPgMapper.historyList", prodctValueObject);		
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
		getSqlSession().insert("appMyPgMapper.custmrRecmndCreate", custmrValueObject);		
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
		CustmrValueObject custmrRecmndCheck = getSqlSession().selectOne("appMyPgMapper.custmrRecmndCheck", custmrValueObject);		
		return custmrRecmndCheck;
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
		List<Integer> prodctSeqGroup = ordrValueObject.getProdctSeqGroup();
		List<Integer> packgSeqGroup = ordrValueObject.getPackgSeqGroup();
		System.out.println(prodctSeqGroup);
		System.out.println(packgSeqGroup);
		OrdrValueObject cartBuyProdctNme = new OrdrValueObject();
		if(prodctSeqGroup.isEmpty()) {
			cartBuyProdctNme = getSqlSession().selectOne("appMyPgMapper.onlyDahamgaeCartBuyProdctNme", ordrValueObject);	
		}
		else if(packgSeqGroup.isEmpty()) {
			cartBuyProdctNme = getSqlSession().selectOne("appMyPgMapper.onlyNormalCartBuyProdctNme", ordrValueObject);	
		}
		else {
			cartBuyProdctNme = getSqlSession().selectOne("appMyPgMapper.cartBuyProdctNme", ordrValueObject);			
		}

//		OrdrValueObject cartBuyProdctNme = getSqlSession().selectOne("appMyPgMapper.cartBuyProdctNme", ordrValueObject);
		return cartBuyProdctNme;		
	}
	
	/** 카테고리 중분류 검색
    * 
    *
    * @param   CatgrValueObject
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> appProdctMidCatgrSearch(CatgrValueObject catgrValueObject) throws Exception {
		Logger.info(null);
		List<ProdctValueObject> ProdctList = getSqlSession().selectList("appMyPgMapper.appProdctMidCatgrSearch", catgrValueObject);
		return ProdctList;	
	}
	
	
	/**
    * 배송 완료에서 배달한 직원 추천
    * 
    * @param   int
    * @return  
    * @exception  Exception
    */
	public void ordrRecmndUpdate(int ordrNumbrSeq) throws Exception {
		Logger.info(null);
		getSqlSession().update("appMyPgMapper.ordrRecmndUpdate", ordrNumbrSeq);
	}
}
