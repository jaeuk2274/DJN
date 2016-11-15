package net.su.app.appMarket.service;

import javax.annotation.Resource;

import java.util.List;

import org.springframework.stereotype.Service;

import net.su.app.appMarket.dataAccessObject.AppDebecChoiceDataAccessObject;
import net.su.logger.Logger;
import net.su.prodct.display.valueObject.FreshValueObject;
import net.su.prodct.display.valueObject.HotdlValueObject;
import net.su.prodct.display.valueObject.PackgValueObject;


/**
 * 앱 마켓 대백초이스 관련 인터페이스입니다.
 * 
 * @see   net.su.app.appMarket.service
 * @version  1.0 
 * @ author 시상일, 2016/10/03
 */

@Service
public class AppDebecChoiceServiceImpl  implements AppDebecChoiceService{

	@Resource
	private AppDebecChoiceDataAccessObject appDebecChoiceDAO;
	
	/**
	* 앱 신선식품 상품 갯수 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	public int selectAppFreshtCount() throws Exception{
		int appFreshtCount = appDebecChoiceDAO.selectAppFreshtCount();
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
		int appHotdltCount = appDebecChoiceDAO.selectAppHotdltCount();
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
		int appPackgCount = appDebecChoiceDAO.selectAppPackgCount();
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
		List<FreshValueObject> appFreshList = appDebecChoiceDAO.selectAppFreshList(freshValueObject);
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
//		int selectHotdlCount = hotdlDao.selectHotdlCount(hotdlValueObject);
//		hotdlValueObject.setTotalRecordCount(selectHotdlCount);
		List<HotdlValueObject> appHotdlList = appDebecChoiceDAO.selectAppHotdlList(hotdlValueObject);	
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
		List<PackgValueObject> appPackgList = appDebecChoiceDAO.selectAppPackgList();
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
		PackgValueObject appPackgRead = appDebecChoiceDAO.selectAppPackgRead(packgSeq);
		return appPackgRead;
	}
}
