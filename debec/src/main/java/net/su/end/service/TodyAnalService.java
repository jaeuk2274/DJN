package net.su.end.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.su.end.valueObject.TodyAnalValueObject;

/**
 * 투데이리포트 서비스입니다.
 * 
 * @see   net.su.end.service.TodyAnalService
 * @version  1.0 
 * @ author 김대현, 2016/08/24
 */
public interface TodyAnalService {
	// TodyAnalServiceImpl
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 매출액을 조회 메서드입니다.
	*
	* @param   
	* @return  TodyAnalValueObject
	* @exception  예외처리 상황을 적어주세요
	*/
	// 투데이 리포트 - 매출액 조회
	public TodyAnalValueObject selectTodyResult() throws Exception;
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 전화주문 건수를 조회 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	// 전화주문 건수 조회
	public int telOrdrCount() throws Exception;
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 어플주문 건수를 조회 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	// 어플주문 건수 조회
	public int appOrdrCount() throws Exception;
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 주문취소 건수를 조회 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	// 주문취소 건수
	public int ordrCancelCount() throws Exception;
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 교환환불 건수를 조회 메서드입니다.
	*
	* @param   
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	// 교환환불 건수
	public String exchngRefndCount() throws Exception;
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 오늘 상품 판매량순위 top10을 조회 메서드입니다.
	*
	* @param   
	* @return  List<TodyAnalValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	// 오늘 상품 판매량순위 top10
	public List<TodyAnalValueObject> todySalesList() throws Exception;
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 급상승 판매상품순위 top10을 조회 메서드입니다.
	*
	* @param   
	* @return  List<TodyAnalValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	// 급상승 판매상품순위  top10
	public List<TodyAnalValueObject> surgeSelProdctList() throws Exception;
	
	// 일일매출 그래프 시간
	public List<TodyAnalValueObject> salsTime() throws Exception;
	
	// 엑셀 업로드
	public void insertSalsPresnt(MultipartHttpServletRequest request, TodyAnalValueObject todyAnalVo) throws Exception;

}
