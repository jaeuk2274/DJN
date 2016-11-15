package net.su.app.appMarket.dataAccessObject;

import java.util.List;

import net.su.logger.Logger;
import net.su.prodct.valueObject.TogthrValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 앱 마켓 메뉴 다함께 기능 DAO입니다.
 * 
 * @see   net.su.app.appMarket.dataAccessObject
 * @version  1.0 
 * @ author 시상일, 2016/10/06
 */

@Repository
public class AppTogthrDataAccessObject extends SqlSessionDaoSupport{
	
	
	/**
	* 앱 다함께 상품 갯수 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/appTogthrProdctCount.do", method = {RequestMethod.POST, RequestMethod.GET})
	public int selectAppTogthrProdctCount() throws Exception{
		int appTogthrProdctCount = getSqlSession().selectOne("appTogthrMapper.selectAppTogthrProdctCount");
		return appTogthrProdctCount;
	}
	
	/**
    * 앱 다함께 리스트를 조회하는 메서드
    *
    * @param   
    * @return  List<TogthrValueObject>
    * @exception  Exception
    */
	public List<TogthrValueObject> selectAppTogthrProdctList() throws Exception{
		List<TogthrValueObject> appTogthrProdctList = getSqlSession().selectList("appTogthrMapper.selectAppTogthrProdctList");
		return appTogthrProdctList;
	}	
	
	/**
	* 앱 다함께 상세보기를 조회하는 메서드
	*
	* @param   long
	* @return  TogthrValueObject
	* @exception  Exception
	*/
	public TogthrValueObject selectAppTogthrDetail(int gropPurchsProdctSeq) throws Exception{
		TogthrValueObject appTogthrDetail = getSqlSession().selectOne("appTogthrMapper.selectAppTogthrDetail",gropPurchsProdctSeq);
		return appTogthrDetail;
	}
}
