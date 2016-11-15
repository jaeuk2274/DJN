/**
 * 앱 레시피 관리 서비스입니다.
 * 
 * @see   net.su.app.appRecp.dataAccessObject.AppRecpDataAccessObject
 * @version  1.0 
 * @ author 하원식, 2016/10/08
 */
package net.su.app.appRecp.dataAccessObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.su.app.appRecp.valueObject.AppRecpValueObject;
import net.su.logger.Logger;
import net.su.prodct.valueObject.ProdctValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
@Repository
public class AppRecpDataAccessObject extends SqlSessionDaoSupport{
	
	/**
	* 레시피 리스트를 조회해주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  List<AppRecpValueObject>
	* @exception  Exception
	*/
	public List<AppRecpValueObject> recpList(AppRecpValueObject appRecpValueObject) throws Exception{
		Logger.info(null);
		
		List<AppRecpValueObject> recpList = getSqlSession().selectList("appRecpMapper.appRecpList", appRecpValueObject);
		return recpList;
	}
	
	/**
	* 레시피의 좋아요를 등록해주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  void
	* @exception  Exception
	*/
	public void recpLikeInsert(AppRecpValueObject appRecpValueObject) throws Exception
	{
		Logger.info(null);
		getSqlSession().insert("appRecpMapper.appRecpLikeInsert", appRecpValueObject);
	}
	
	/**
	* 레시피의 좋아요를 취소해주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  void
	* @exception  Exception
	*/
	public void recpLikeDelete(AppRecpValueObject appRecpValueObject) throws Exception
	{
		Logger.info(null);
		getSqlSession().delete("appRecpMapper.appRecpLikeDelete", appRecpValueObject);
	}
	
	/**
	* 레시피를 상세보기 해주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  AppRecpValueObject
	* @exception  Exception
	*/
	public AppRecpValueObject recpRead(AppRecpValueObject appRecpValueObject) throws Exception
	{
		Logger.info(null);
		appRecpValueObject = getSqlSession().selectOne("appRecpMapper.appRecpRead", appRecpValueObject);
		return appRecpValueObject;
	}
	
	/**
	* 레시피의 댓글을 등록 시켜주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  void
	* @exception  Exception
	*/
	public void recpReplyInsert(AppRecpValueObject appRecpValueObject) throws Exception
	{
		Logger.info(null);
		getSqlSession().insert("appRecpMapper.appRecpReplyInsert", appRecpValueObject);
	}
	
	/**
	* 레시피의 관련 상품 리스트를 조회 해주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  List<AppRecpValueObject>
	* @exception  Exception
	*/
	public List<AppRecpValueObject> recpProdctList(AppRecpValueObject appRecpValueObject) throws Exception
	{
		List<AppRecpValueObject> recpProdctList = getSqlSession().selectList("appRecpMapper.appRecpProdctListRead", appRecpValueObject);
				
		return recpProdctList;
	}
	
	/**
	* 레시피의 댓글 리스트를 조회 해주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  List<AppRecpValueObject>
	* @exception  Exception
	*/
	public List<AppRecpValueObject> recpReplyList(AppRecpValueObject appRecpValueObject) throws Exception
	{
		Logger.info(null);
		List<AppRecpValueObject> recpReplyList = getSqlSession().selectList("appRecpMapper.appRecpReplyList", appRecpValueObject);
		
		return recpReplyList;
	}

	/**
	* 레시피 연관 상품을 검색해주는 메서드 입니다..
	*
	* @param   String recpSechText
	* @return  List<AppRecpValueObject>
	* @exception  Exception
	*/
	public List<AppRecpValueObject> appRecpProdctSearch(String recpSechText) throws Exception{
		List<AppRecpValueObject> appRecpProdctSearch = getSqlSession().selectList("appRecpMapper.appRecpProdctSearch", recpSechText);
		return appRecpProdctSearch;
	}

	/**
	* 레시피 연관 상품 리스트를 조회해주는 메서드 입니다.
	*
	* @param   long[] recpProdct
	* @return  List<AppRecpValueObject>
	* @exception  Exception
	*/
	public List<AppRecpValueObject> appRecpProdctList(long[] recpProdct) throws Exception{
		List<AppRecpValueObject> appRecpProdctList = null;
		if(recpProdct[0]!=0)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("recpProdct",recpProdct);
			appRecpProdctList = getSqlSession().selectList("appRecpMapper.appRecpProdctList", map);
		}
		return appRecpProdctList;
	}
	
	/**
	* 레시피를 등록시켜주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject, long[] recpProdct
	* @return  int
	* @exception  Exception
	*/
	public int appRecpInsert(AppRecpValueObject appRecpValueObject, long[] recpProdct)throws Exception
	{
		getSqlSession().insert("appRecpMapper.appRecpInsert", appRecpValueObject);
		int recpSeq = getSqlSession().selectOne("appRecpMapper.appRecpMaxSeq");
		appRecpValueObject.setRecpSeq(recpSeq);
		if(recpProdct[0] != 0)
		{
			for(int i = 0 ; i < recpProdct.length ; i++)
			{
				appRecpValueObject.setProdctSeq(recpProdct[i]);
				getSqlSession().insert("appRecpMapper.appRecpProdctInsert", appRecpValueObject);
			}
		}
		recpSeq = getSqlSession().selectOne("appRecpMapper.appRecpMaxSeq");
		
		return recpSeq;
	}
	
	/**
	* 레시피의 이미지 경로를 수정시켜주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  void
	* @exception  Exception
	*/
	public void appRecpImgSrcUpdate(AppRecpValueObject appRecpValueObject)throws Exception
	{
		getSqlSession().update("appRecpMapper.appRecpImgSrcUpdate", appRecpValueObject);
	}
	
	/**
	* 레시피를 삭제 시켜주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  void
	* @exception  Exception
	*/
	public void appRecpDelete(AppRecpValueObject appRecpValueObject)throws Exception
	{
		getSqlSession().delete("appRecpMapper.appRecpDelete", appRecpValueObject);
		getSqlSession().delete("appRecpMapper.appRecpLikeDelete", appRecpValueObject);
		getSqlSession().delete("appRecpMapper.appRecpProdctDelete", appRecpValueObject);
		getSqlSession().delete("appRecpMapper.appRecpReplyDelete", appRecpValueObject);
	}
	
	/**
	* 레시피를 수정 시켜주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  void
	* @exception  Exception
	*/
	public void appRecpUpdate(AppRecpValueObject appRecpValueObject, long[] recpProdct)throws Exception
	{
		getSqlSession().insert("appRecpMapper.appRecpUpdate", appRecpValueObject);
	
		
		getSqlSession().delete("appRecpMapper.appRecpProdctDelete", appRecpValueObject);
		
		if(recpProdct[0] != 0)
		{

			for(int i = 0 ; i < recpProdct.length ; i++)
			{
				appRecpValueObject.setProdctSeq(recpProdct[i]);
				getSqlSession().insert("appRecpMapper.appRecpProdctInsert", appRecpValueObject);
			}
		}
	}

	/**
	* 레시피의 관련 상품을 카트에 등록 시켜주는 메서드 입니다.
	*
	* @param   int memSeq, long[] prodctSeqArray
	* @return  void
	* @exception  Exception
	*/
	public void appRecpProdctCartInsert(int memSeq, long[] prodctSeqArray) throws Exception
	{
		for(int i = 0 ; i < prodctSeqArray.length ; i++)
		{
			AppRecpValueObject appRecpValueObject = new AppRecpValueObject();
			
			appRecpValueObject.setMemSeq(memSeq);
			appRecpValueObject.setProdctSeq(prodctSeqArray[i]);
			int check = getSqlSession().selectOne("appRecpMapper.appRecpProdctCartCheck", appRecpValueObject);
			if(check == 0)
			{
				getSqlSession().insert("appRecpMapper.appRecpProdctCartInsert", appRecpValueObject);
			}
		}
	}
	

	/**
	* 레시피의 댓글을 삭제시켜주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  void
	* @exception  Exception
	*/
	public void appRecpReplyDelete(AppRecpValueObject appRecpValueObject) throws Exception
	{
		getSqlSession().delete("appRecpMapper.appRecpReplyDeleteTwo", appRecpValueObject);
	}
}
