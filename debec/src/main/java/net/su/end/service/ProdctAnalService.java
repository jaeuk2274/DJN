package net.su.end.service;

import java.util.List;

import net.su.end.valueObject.ProdctAnalValueObject;

/**
 * 상품분석 관련 서비스입니다.
 * 
 * @see   net.su.end.service.ProdctAnalService
 * @version  1.0 
 * @ author 김대현, 2016/09/07
 */

public interface ProdctAnalService {

	/**
	* 판매수량이 상위 top 10 상품을 조회하는 메서드입니다.
	*
	* @param   
	* @return  List<ProdctAnalValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	public List<ProdctAnalValueObject> selectSelProdctQunt(ProdctAnalValueObject prodctAnalVo) throws Exception;
	
	/**
	* 판매금액합계가 상위 top 10 상품을 조회하는 메서드입니다.
	*
	* @param   
	* @return  List<ProdctAnalValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	public List<ProdctAnalValueObject> selectSelProdctSum(ProdctAnalValueObject prodctAnalVo) throws Exception;
	
	/**
	* 판매 상품 순위내역
	*
	* @param   
	* @return  List<ProdctAnalValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	public List<ProdctAnalValueObject> selectProdctRanking(ProdctAnalValueObject prodctAnalVo) throws Exception;
	
	/**
	* 판매분류 별 판매량  상위 top 10 상품을 조회하는 메서드입니다.
	*
	* @param   
	* @return  List<ProdctAnalValueObject>
	* @exception  Exception
	*/	
	public List<ProdctAnalValueObject> catgrSelQuntList(ProdctAnalValueObject prodctAnalValueObject) throws Exception;

	/**
	* 판매분류 별 판매 합계 상위 top 10 상품을 조회하는 메서드입니다.
	*
	* @param   
	* @return  List<ProdctAnalValueObject>
	* @exception  Exception
	*/
	public List<ProdctAnalValueObject> catgrSelSumList(ProdctAnalValueObject prodctAnalValueObject) throws Exception;
	
	// 교환환불 top10 차트(환불수량)
	public List<ProdctAnalValueObject> selectRefndQunt(ProdctAnalValueObject prodctAnalVo) throws Exception;
	
	// 교환환불 top10 차트 (환불비율)
	public List<ProdctAnalValueObject> selectRefndRat(ProdctAnalValueObject prodctAnalVo) throws Exception;
	
	// 환불 상품 순위 내역 (테이블)
	public List<ProdctAnalValueObject> selectRefndRanking(ProdctAnalValueObject prodctAnalVo) throws Exception;
	
}
