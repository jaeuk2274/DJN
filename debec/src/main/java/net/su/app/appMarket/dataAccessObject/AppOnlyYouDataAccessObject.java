package net.su.app.appMarket.dataAccessObject;

import java.util.List;

import net.su.logger.Logger;
import net.su.prodct.valueObject.ProdctValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 앱 마켓 메뉴 너.만.상 기능 DAO입니다.
 * 
 * @see   net.su.app.appMarket.controller
 * @version  1.0 
 * @ author 이인호, 2016/09/22
 */
@Repository
public class AppOnlyYouDataAccessObject extends SqlSessionDaoSupport{
	
	/**
    * 너.만.상 준비 상태를 조회하는 메서드
    *
    * @param    
    * @return  int
    * @exception  Exception
    */
	public int appOnlyYou(int custmrSeq) throws Exception {
		Logger.info(null);
		
		int onlyYouCount = getSqlSession().selectOne("appOnlyYouMapper.appOnlyYou", custmrSeq);
		return onlyYouCount;
	}
	
	/**
    * 너.만.상 상품 리스트를 조회하는 메서드
    *
    * @param   int
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> appOnlyYouRead(@RequestParam(value="custmrSeq") int custmrSeq) throws Exception {
		Logger.info(null);
		
		List<ProdctValueObject> appOnlyYouList = getSqlSession().selectList("appOnlyYouMapper.appOnlyYouRead", custmrSeq);
		return appOnlyYouList;
	}
}
