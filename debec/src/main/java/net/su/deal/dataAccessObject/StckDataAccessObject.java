/**
 * 재고 관리 DataAccessObject입니다.
 * 
 * @see   net.su.deal.controller.StckDataAccessObject
 * @version  1.0 
 * @ author 하원식, 2016/08/27
 */
package net.su.deal.dataAccessObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import net.su.deal.valueObject.InstckValueObject;
import net.su.deal.valueObject.StckValueObject;
import net.su.logger.Logger;
import net.su.prodct.valueObject.ProdctValueObject;

@Repository
public class StckDataAccessObject extends SqlSessionDaoSupport{


	/**
	* 재고 목록을 조회하는 메서드 입니다.
	*
	* @param  StckValueObject stckValueObject
	* @return  List
	* @exception  Exception
	*/
	// 재고 목록을 조회하는 메서드
	public List<StckValueObject> stckList(StckValueObject stckValueObject) throws Exception
	{
		Logger.info(null);
		List<StckValueObject> stckList = getSqlSession().selectList("stckMapper.stckList", stckValueObject);
		
		return stckList;
	}
	
	
	/**
	* 조회된 재고 목록의 총 갯수를 세어주는 메서드 입니다.
	*
	* @param  StckValueObject stckValueObject
	* @return  int
	* @exception  Exception
	*/
	public int stckCount(StckValueObject stckValueObject) throws Exception
	{
		Logger.info(null);
		int stckCount = getSqlSession().selectOne("stckMapper.stckCount", stckValueObject);
		
		return stckCount;
	}
	
	
	/**
	* 특정 상품들의 재고 량을 수정하는 메서드 입니다.
	*
	* @param  StckValueObject stckValueObject
	* @return  void
	* @exception  Exception
	*/
	public void stckUpdate(StckValueObject stckValueObject) throws Exception
	{
		Logger.info(null);
		
		getSqlSession().update("stckMapper.stckUpdate", stckValueObject);
		
	}
	
	/**
	* 반품 과정에서 반품 테이블에 데이터를 등록하는 메서드 입니다.
	*
	* @param  StckValueObject stckValueObject
	* @return  void
	* @exception  Exception
	*/
	public void retrnInsert(StckValueObject stckValueObject) throws Exception
	{
		Logger.info(null);
		
		getSqlSession().update("stckMapper.retrnInsert", stckValueObject);
		
	}
	
	/**
	* Excel에서 읽어낸 재고 정보를 등록하는 메서드입니다.
	*
	* @param  StckValueObject stckValueObject
	* @return  void
	* @exception  Exception
	*/	
	public void stckInsert(StckValueObject stckValueObject) throws Exception
	{
		Logger.info(null);
		
		// 재고를 등록하기 이전에 테이블에 재고 정보가 있는 지를 미리 확인해 봄.
		int prodctCount = getSqlSession().selectOne("stckMapper.stckRead", stckValueObject);
		if(prodctCount == 0) // SELECT COUNT 결과 등록되지 않는 상품의 경우 INSERT
		{
			getSqlSession().insert("stckMapper.stckInsert", stckValueObject);
		}
		else if(prodctCount == 1) // SELECT COUNT 결과 이미 등록된 상품의 경우 UPDATE
		{
			stckValueObject.setStckType(3);
			getSqlSession().update("stckMapper.stckUpdate", stckValueObject);
		}
	}
	
	/**
	* 특정 상품의 재고 정보를 다운로드하기 위해 조회하는 메서드입니다.
	*
	* @param  long[] prodctSeq
	* @return  List<StckValueObject> 
	* @exception  Exception
	*/
	public List<StckValueObject> stckXlxList(long[] prodctSeq) throws Exception
	{
		Logger.info(null);
		List<StckValueObject> stckXlxList = null;
		StckValueObject stckValueObject = new StckValueObject();
		stckValueObject.setProdctSeq(prodctSeq[0]);
		if(prodctSeq.length == 1)
			stckXlxList = getSqlSession().selectList("stckMapper.stckList", stckValueObject);
		else
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("prodctSeqArray", prodctSeq);
			
			stckXlxList = getSqlSession().selectList("stckMapper.stckXlxList", map);
		}
		return stckXlxList;
	}
	
	/**
	* 특정 상품의 입고 내역 정보를 조회하는 메서드 입니다.
	*
	* @param  StckValueObject stckValueObject
	* @return  List<StckValueObject> 
	* @exception  Exception
	*/
	public List<StckValueObject> instckRecrdList(StckValueObject stckValueObject) throws Exception
	{
		Logger.info(null);
		List<StckValueObject> instckRecrdList = getSqlSession().selectList("stckMapper.instckRecrdList", stckValueObject);
		
		return instckRecrdList;
	} 

	/**
	* 특정 상품의 입고 내역 정보의 Record 수를 조회하는 메서드 입니다.
	*
	* @param  StckValueObject stckValueObject
	* @return  int
	* @exception  Exception
	*/
	public int instckRecrdCount(StckValueObject stckValueObject) throws Exception
	{
		Logger.info(null);
		int prodctCount = getSqlSession().selectOne("stckMapper.instckRecrdCount", stckValueObject);
		
		return prodctCount;
	}

}
