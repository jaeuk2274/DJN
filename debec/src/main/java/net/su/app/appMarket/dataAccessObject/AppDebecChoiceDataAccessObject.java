package net.su.app.appMarket.dataAccessObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import net.su.logger.Logger;
import net.su.prodct.display.valueObject.FreshValueObject;
import net.su.prodct.display.valueObject.HotdlValueObject;
import net.su.prodct.display.valueObject.PackgValueObject;

import java.util.List;

/**
 * 앱 마켓 메뉴 대백초이스 기능 DAO입니다.
 * 
 * @see   net.su.app.appMarket.dataAccessObject
 * @version  1.0 
 * @ author 시상일, 2016/10/03
 */

@Repository
public class AppDebecChoiceDataAccessObject extends SqlSessionDaoSupport{

	/**
	* 앱 신선식품 상품 갯수 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	public int selectAppFreshtCount() throws Exception{
		int appFreshtCount = getSqlSession().selectOne("appDebecChoiceMapper.selectAppFreshtCount");	
		return appFreshtCount;
	}
	
	/**
	* 앱 핫딜 상품 갯수 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	public int selectAppHotdltCount() throws Exception{
		int appHotdltCount = getSqlSession().selectOne("appDebecChoiceMapper.selectAppHotdltCount");	
		return appHotdltCount;
	}
	
	/**
	* 앱 패키지 상품 갯수 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	public int selectAppPackgCount() throws Exception{
		int appPackgCount = getSqlSession().selectOne("appDebecChoiceMapper.selectAppPackgCount");	
		return appPackgCount;
	}
	
	/**
	* 앱 신선식품 상품 진열 관리 목록 조회를 하는 메서드입니다.
	*
	* @param  
	* @return   List<FreshValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	public List<FreshValueObject> selectAppFreshList(FreshValueObject freshValueObject) throws Exception {
		Logger.info(null);		
		List<FreshValueObject> appFreshList = getSqlSession().selectList("appDebecChoiceMapper.selectAppFreshList", freshValueObject);	
		return appFreshList;
	}
	
	/**
	* 앱 핫딜 상품 진열 관리 목록 조회를 하는 메서드입니다.
	*
	* @param  
	* @return   List<HotdlValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	public List<HotdlValueObject> selectAppHotdlList(HotdlValueObject hotdlValueObject) throws Exception {
		Logger.info(null);		
		List<HotdlValueObject> appHotdlList = getSqlSession().selectList("appDebecChoiceMapper.selectAppHotdlList", hotdlValueObject);	
		return appHotdlList;
	}
	
	/**
	* 앱 패키지 상품 진열 관리 목록 조회를 하는 메서드입니다.
	*
	* @param  
	* @return  List<PackgValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	public List<PackgValueObject> selectAppPackgList() throws Exception{
		List<PackgValueObject> appPackgList = getSqlSession().selectList("appDebecChoiceMapper.selectAppPackgList");
		return appPackgList;
	}
	
	/**
	* 앱 패키지 상세보기 조회를 하는 메서드입니다.
	*
	* @param  @RequestParam(value= "packgSeq")
	* @return  PackgValueObject
	* @exception  예외처리 상황을 적어주세요
	*/
	public PackgValueObject selectAppPackgRead(int packgSeq) throws Exception{
		PackgValueObject appPackgRead = getSqlSession().selectOne("appDebecChoiceMapper.selectAppPackgRead",packgSeq);
		return appPackgRead;
	}
}
