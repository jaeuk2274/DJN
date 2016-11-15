package net.su.app.appMarket.service;

import java.util.List;

import javax.annotation.Resource;

import net.su.app.appMarket.dataAccessObject.AppTogthrDataAccessObject;
import net.su.logger.Logger;
import net.su.prodct.valueObject.TogthrValueObject;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 앱 마켓 메뉴 다함께 기능 서비스 인터페이스입니다.
 * 
 * @see   net.su.app.appMarket.service
 * @version  1.0 
 * @ author 시상일, 2016/10/06
 */
@Service
public class AppTogthrServiceImpl implements AppTogthrService {
	
	@Resource
	private AppTogthrDataAccessObject appTogthrDataAccessObject;
	
	/**
	* 앱 다함께 상품 갯수 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/appTogthrProdctCount.do", method = {RequestMethod.POST, RequestMethod.GET})
	public int selectAppTogthrProdctCount() throws Exception{
		int appTogthrProdctCount = appTogthrDataAccessObject.selectAppTogthrProdctCount();
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
		List<TogthrValueObject> appTogthrProdctList = appTogthrDataAccessObject.selectAppTogthrProdctList();
		
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
			TogthrValueObject appTogthrDetail = appTogthrDataAccessObject.selectAppTogthrDetail(gropPurchsProdctSeq);
			
		return appTogthrDetail;
	}
}
