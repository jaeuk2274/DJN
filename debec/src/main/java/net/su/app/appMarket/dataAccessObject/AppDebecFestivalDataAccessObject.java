package net.su.app.appMarket.dataAccessObject;

import java.util.List;

import net.su.logger.Logger;
import net.su.prodct.valueObject.DebecFestivalValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * 앱 마켓 메뉴 대백제 기능 DAO입니다.
 * 
 * @see   net.su.app.appMarket.dataAccessObject
 * @version  1.0 
 * @ author 이인호, 2016/09/20
 */

@Repository
public class AppDebecFestivalDataAccessObject extends SqlSessionDaoSupport{
	
	
	/**
    * 대백제 진행 상탸를 조회하는 메서드
    *
    * @param    
    * @return  int
    * @exception  Exception
    */
	public int appDebecFestival() throws Exception {
		Logger.info(null);
		
		int schedlCount = getSqlSession().selectOne("appDebecFestivalMapper.appDebecFestival");
		return schedlCount;
	}	
	
	
	/**
    * 대백제 행사 정보를 조회하는 메서드
    *
    * @param    
    * @return  DebecFestivalValueObject
    * @exception  Exception
    */
	public DebecFestivalValueObject appDebecFestivalRead() throws Exception{
		Logger.info(null);
			
		DebecFestivalValueObject debecFestivalInfo = getSqlSession().selectOne("appDebecFestivalMapper.appDebecFestivalInfo");	
		return debecFestivalInfo;
	}
	
	
	/**
    * 대백제 행사 상품 리스트를 조회하는 메서드
    *
    * @param   DebecFestivalValueObject 
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> appDebecFestivalProdctList(DebecFestivalValueObject debecFestivalValueObject) throws Exception {
		Logger.info(null);
		
		List<ProdctValueObject> appDebecFestivalProdctList = getSqlSession().selectList("appDebecFestivalMapper.appDebecFestivalProdctList", debecFestivalValueObject);
		return appDebecFestivalProdctList;
	}	

}
