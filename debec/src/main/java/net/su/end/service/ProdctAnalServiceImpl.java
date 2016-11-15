package net.su.end.service;

/**
 * 상품분석 관련 서비스임플입니다.
 * 
 * @see   net.su.end.service.ProdctAnalServiceImpl
 * @version  1.0 
 * @ author 김대현, 2016/09/07
 */

import java.util.List;

import javax.annotation.Resource;

import net.su.end.dataAccessObject.ProdctAnalDataAccessObject;
import net.su.end.valueObject.ProdctAnalValueObject;

import org.springframework.stereotype.Service;

@Service
public class ProdctAnalServiceImpl implements ProdctAnalService{

	@Resource
	private ProdctAnalDataAccessObject prodctAnalDao;
	
	/**
	* 판매수량이 상위 top 10 상품을 조회하는 메서드입니다.
	*
	* @param   
	* @return  List<ProdctAnalValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	public List<ProdctAnalValueObject> selectSelProdctQunt(ProdctAnalValueObject prodctAnalVo) throws Exception{
		List<ProdctAnalValueObject> prodctSelQuntList = prodctAnalDao.selectSelProdctQunt(prodctAnalVo);
		return prodctSelQuntList;
	}
	
	/**
	* 판매금액합계가 상위 top 10 상품을 조회하는 메서드입니다.
	*
	* @param   
	* @return  List<ProdctAnalValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	public List<ProdctAnalValueObject> selectSelProdctSum(ProdctAnalValueObject prodctAnalVo) throws Exception{
		List<ProdctAnalValueObject> prodctSelSumList = prodctAnalDao.selectSelProdctSum(prodctAnalVo);
		return prodctSelSumList;
	}
	
	/**
	* 판매 상품 순위내역
	*
	* @param   
	* @return  List<ProdctAnalValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	public List<ProdctAnalValueObject> selectProdctRanking(ProdctAnalValueObject prodctAnalVo) throws Exception{
		List<ProdctAnalValueObject> prodctRankingList = prodctAnalDao.selectProdctRanking(prodctAnalVo);
		return prodctRankingList;
	}

	/**
	* 판매분류 별 판매량  상위 top 10 상품을 조회하는 메서드입니다.
	*
	* @param   
	* @return  List<ProdctAnalValueObject>
	* @exception  Exception
	*/	
	public List<ProdctAnalValueObject> catgrSelQuntList(ProdctAnalValueObject prodctAnalValueObject) throws Exception
	{
		List<ProdctAnalValueObject> catgrSelQuntList = prodctAnalDao.catgrSelQuntList(prodctAnalValueObject);
		return catgrSelQuntList;
	}
	
	/**
	* 판매분류 별 판매 합계 상위 top 10 상품을 조회하는 메서드입니다.
	*
	* @param   
	* @return  List<ProdctAnalValueObject>
	* @exception  Exception
	*/
	public List<ProdctAnalValueObject> catgrSelSumList(ProdctAnalValueObject prodctAnalValueObject) throws Exception
	{
		List<ProdctAnalValueObject> catgrSelSumList = prodctAnalDao.catgrSelSumList(prodctAnalValueObject);
		return catgrSelSumList;
	}
	
	// 교환환불 top10 차트(환불수량)
	public List<ProdctAnalValueObject> selectRefndQunt(ProdctAnalValueObject prodctAnalVo) throws Exception{
		List<ProdctAnalValueObject> refndQuntList = prodctAnalDao.selectRefndQunt(prodctAnalVo);
		return refndQuntList;
	}
	
	// 교환환불 top10 차트 (환불비율)
	public List<ProdctAnalValueObject> selectRefndRat(ProdctAnalValueObject prodctAnalVo) throws Exception{
		List<ProdctAnalValueObject> refndRatList = prodctAnalDao.selectRefndRat(prodctAnalVo);
		return refndRatList;
	}
	
	// 환불 상품 순위 내역 (테이블)
	public List<ProdctAnalValueObject> selectRefndRanking(ProdctAnalValueObject prodctAnalVo) throws Exception{
		List<ProdctAnalValueObject> refndRankingList = prodctAnalDao.selectRefndRanking(prodctAnalVo);
		return refndRankingList;
		
	}
}
