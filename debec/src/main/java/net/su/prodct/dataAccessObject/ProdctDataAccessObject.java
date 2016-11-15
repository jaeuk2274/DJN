/**
 * 판매 상품 관리 서비스임플입니다.
 * 
 * @see   net.su.prodct.dataAcceessObject.ProdctDataAcceessObject
 * @version  1.0 
 * @ author 하원식, 2016/08/27
 */
package net.su.prodct.dataAccessObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import net.su.logger.Logger;
import net.su.prodct.valueObject.CatgrValueObject;
import net.su.prodct.valueObject.ProdctValueObject;
@Repository
public class ProdctDataAccessObject extends SqlSessionDaoSupport{

	/**
    * 판매 상품 리스트를 조회하는 메서드다..
    *
    * @param   ProdctValueObject
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> selProdctList(ProdctValueObject prodctValueObject) throws Exception{
		Logger.info(null);
		
		List<ProdctValueObject> selProdctList = getSqlSession().selectList("prodctMapper.selProdctList", prodctValueObject);
		return selProdctList;
	}

	/**
    * 판매 상품 리스트의 행 수를 세는 메서드다.
    *
    * @param   ProdctValueObject
    * @return  int
    * @exception  Exception
    */
	public int selProdctCount(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		
		int selProdctCount = getSqlSession().selectOne("prodctMapper.selProdctCount", prodctValueObject);	
		return selProdctCount;
	}

	/**
    * 분류 리스트를 뽑아내는 메서드다.
    *
    * @param   String catgrSize, String catgrNme
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> catgrList(String catgrSize, String catgrNme) throws Exception{
		Logger.info(null);
		
		List<ProdctValueObject> catgrList =null;
		if(catgrSize.equals("기본")){
			catgrList = getSqlSession().selectList("prodctMapper.topCatgrList");
		}
		if(catgrSize.equals("대")){
			catgrList = getSqlSession().selectList("prodctMapper.midCatgrList",catgrNme);
		}
		else if(catgrSize.equals("중")){
			catgrList = getSqlSession().selectList("prodctMapper.botCatgrList",catgrNme);
		}
		return catgrList;
	}

	
	/**
    * 판매 상품을 등록하는 메서드다.
    *
    * @param   ProdctValueObject
    * @return  void
    * @exception  Exception
    */
	public void selProdctCreate(ProdctValueObject prodctValueObject) throws Exception
	{
		Logger.info(null);
		getSqlSession().insert("prodctMapper.selProdctCreate",prodctValueObject);
		getSqlSession().insert("prodctMapper.reltnProdctCreate",prodctValueObject);
		getSqlSession().insert("prodctMapper.clintCreate",prodctValueObject);
		getSqlSession().insert("prodctMapper.stckCreate",prodctValueObject);
		if(prodctValueObject.getSchedlSeq()!=0)
		{
			getSqlSession().insert("prodctMapper.schedlCreate",prodctValueObject);
		}
	}

	
	/**
    * 판매 상품 바코드의 중복 여부를 검사하는 메서드다.
    *
    * @param   long dupliCheck
    * @return  long
    * @exception  Exception
    */
	public long prodctSeqDuliCheck(long dupliCheck) throws Exception
	{
		Logger.info(null);
		dupliCheck = getSqlSession().selectOne("prodctSeqDuliCheck",dupliCheck);
		return dupliCheck;
		
	}

	
	/**
    * 상품 카테고리의 리스트를 출력하는 메서드다.
    *
    * @param   
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<CatgrValueObject> catgrProdctList() throws Exception
	{
		Logger.info(null);
		List<CatgrValueObject> catgrProdctList = getSqlSession().selectList("catgrProdctList");
		
		return catgrProdctList;
	}

	
	/**
    * 판매 상품의 상태를 판매 중지로 바꿔주는 메서드다.
    *
    * @param   long prodctSeq
    * @return  void
    * @exception  Exception
    */
	public void selProdctDelete(long prodctSeq) throws Exception
	{
		Logger.info(null);
		getSqlSession().update("selProdctDelete", prodctSeq);
		getSqlSession().delete("prodctMapper.relsProdctDelete", prodctSeq);
		getSqlSession().delete("prodctMapper.prodctSchedlDelete", prodctSeq);
		getSqlSession().delete("prodctMapper.packgProdctDelete", prodctSeq);
	}

	
	/**
    * 판매 상품의 정보를 상세보기하는 메서드다.
    *
    * @param   long prodctSeq
    * @return  ProdctValueObject
    * @exception  Exception
    */
	public ProdctValueObject selProdctRead(long prodctSeq) throws Exception
	{
		Logger.info(null);
		ProdctValueObject selProdctRead = getSqlSession().selectOne("selProdctRead", prodctSeq);
		
		return selProdctRead;
	}

	
	/**
    * 행사 정보를 상세보기하는 메서드다.
    *
    * @param   long prodctSeq
    * @return  ProdctValueObject
    * @exception  Exception
    */
	public ProdctValueObject evntInfoRead(long prodctSeq) throws Exception
	{
		Logger.info(null);
		ProdctValueObject evntInfoRead = getSqlSession().selectOne("evntInfoRead", prodctSeq);
		
		return evntInfoRead;
	}
	

	
	/**
    * 판매 상품의 분류 정보를 상세보기하는 메서드다.
    *
    * @param   long prodctSeq
    * @return  ProdctValueObject
    * @exception  Exception
    */
	public ProdctValueObject catgrProdctRead(long prodctSeq) throws Exception
	{
		Logger.info(null);
		ProdctValueObject catgrProdctRead = getSqlSession().selectOne("catgrProdctRead", prodctSeq);
		
		return catgrProdctRead;
	}
	

	
	/**
    * 판매 상품의 정보를 수정하는 메서드다.
    *
    * @param   ProdctValueObject
    * @return  void
    * @exception  Exception
    */
	public void selProdctUpdate(ProdctValueObject prodctValueObject) throws Exception{
		Logger.info(null);

		getSqlSession().update("prodctMapper.selProdctUpdate", prodctValueObject);
		int reltnProdctCnt = getSqlSession().selectOne("prodctMapper.selProdctReltnCheck", prodctValueObject);
		if(reltnProdctCnt==0)
		{
			getSqlSession().insert("prodctMapper.reltnProdctCreate",prodctValueObject);	
		}
		else if(reltnProdctCnt == 1)
		{
			getSqlSession().update("prodctMapper.selProdctReltnUpdate", prodctValueObject);
		}
		getSqlSession().update("prodctMapper.selProdctClintUpdate", prodctValueObject);
		int clintCnt = getSqlSession().selectOne("prodctMapper.selProdctSchedlCheck", prodctValueObject);
		if(clintCnt == 0)
			getSqlSession().insert("prodctMapper.schedlCreate",prodctValueObject);
		if(clintCnt == 1)
			getSqlSession().update("prodctMapper.selProdctschedlUpdate", prodctValueObject);

	}
	

	
	/**
    * 판매  중지 상품의 리스트를 출력하는 메서드다.
    *
    * @param   ProdctValueObject
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> selStopProdctList(ProdctValueObject prodctValueObject) throws Exception
	{
		Logger.info(null);
		
		List<ProdctValueObject> selStopProdctList = getSqlSession().selectList("prodctMapper.selStopProdctList", prodctValueObject);
		return selStopProdctList;
	}
	
	
	/**
    * 판매  중지 상품 데이터의 숫자를 세는 메서드다.
    *
    * @param   ProdctValueObject
    * @return  int
    * @exception  Exception
    */
	public int selStopProdctCount(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		
		int selStopProdctCount = getSqlSession().selectOne("prodctMapper.selStopProdctCount", prodctValueObject);	
		return selStopProdctCount;
	}

	
	/**
    * 판매  중지 상품의 상태를 판매 종료로 바꿔주는 메서드다.
    *
    * @param   long prodctSeq
    * @return  void
    * @exception  Exception
    */
	public void selStopProdctDelete(long prodctSeq) throws Exception
	{
		Logger.info(null);
		getSqlSession().update("prodctMapper.selStopProdctDelete", prodctSeq);
		getSqlSession().delete("prodctMapper.prodctStckDelete", prodctSeq);
		getSqlSession().delete("prodctMapper.prodctClintDelete", prodctSeq);
	}
	
	
	/**
    * 판매  중지 상품의 상태를 다시 판매 중으로 바꿔주는 메서드다..
    *
    * @param   long prodctSeq
    * @return  void
    * @exception  Exception
    */
	public void selStopProdctReSelStrt(long prodctSeq) throws Exception
	{

		Logger.info(null);
		getSqlSession().update("prodctMapper.selStopProdctReSelStrt", prodctSeq);
	}
	

	
	/**
	    * 재고 반품을 과정에서 필요한 리스트 출력 메서드
	    *
	    * @param   long[] prodctSeqArray
	    * @return  List<ProdctValueObject>
	    * @exception  Exception
	    */
	public List<ProdctValueObject> selectedSelProdctList(long[] prodctSeqArray) throws Exception
	{
		Logger.info(null);
		List<ProdctValueObject> selectedSelProdctList = null;
		if(prodctSeqArray.length==1)
		{
			long prodctSeq = prodctSeqArray[0];
			selectedSelProdctList = getSqlSession().selectList("prodctMapper.selectedSelProdctView", prodctSeq);
		}
		else
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("prodctSeqArray", prodctSeqArray);
			map.put("length", prodctSeqArray.length);
			selectedSelProdctList = getSqlSession().selectList("prodctMapper.selectedSelProdctList", map);
		}
		return selectedSelProdctList;
	}
}
