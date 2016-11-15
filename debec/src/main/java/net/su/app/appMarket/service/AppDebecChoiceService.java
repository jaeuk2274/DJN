package net.su.app.appMarket.service;

import java.util.List;

import net.su.prodct.display.valueObject.FreshValueObject;
import net.su.prodct.display.valueObject.HotdlValueObject;
import net.su.prodct.display.valueObject.PackgValueObject;

/**
 * 앱 마켓 대백초이스 관련 서비스입니다.
 * 
 * @see    net.su.app.appMarket.service
 * @version  1.0 
 * @ author 시상일, 2016/09/29
 */
public interface AppDebecChoiceService {
	/**
	* 앱 신선식품 상품 갯수 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	public int selectAppFreshtCount() throws Exception;
	
	/**
	* 앱 핫딜 상품 갯수 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	public int selectAppHotdltCount() throws Exception;
	/**
	* 앱 패키지 상품 갯수 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	public int selectAppPackgCount() throws Exception;
	/**
	* 앱 신선 식품 리스트 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  List<FreshValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	public List<FreshValueObject> selectAppFreshList(FreshValueObject freshValueObject) throws Exception;
	/**
	* 앱 핫딜 상품 리스트 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  List<HotdlValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	public List<HotdlValueObject> selectAppHotdlList(HotdlValueObject hotdlValueObject) throws Exception;
	/**
	* 앱 패키지 상품 리스트 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  List<PackgValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	public List<PackgValueObject> selectAppPackgList()throws Exception;
	/**
	* 앱 패키지 상품 상세보기 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  PackgValueObject
	* @exception  예외처리 상황을 적어주세요
	*/
	public PackgValueObject selectAppPackgRead(int packgSeq)throws Exception;
	
}
