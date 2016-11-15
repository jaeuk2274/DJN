package net.su.app.appMarket.service;

import java.util.List;

import net.su.logger.Logger;
import net.su.prodct.valueObject.TogthrValueObject;


/**
 * 앱 마켓 메뉴 다함께 기능 서비스입니다.
 * 
 * @see   net.su.app.appMarket.service
 * @version  1.0 
 * @ author 시상일, 2016/10/06
 */
public interface AppTogthrService {

	/**
	* 앱 다함께 상품 갯수 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	public int selectAppTogthrProdctCount() throws Exception;
	
	/**
    * 앱 다함께 리스트를 조회하는 메서드
    *
    * @param   
    * @return  List<TogthrValueObject>
    * @exception  Exception
    */
	public List<TogthrValueObject> selectAppTogthrProdctList() throws Exception;

	/**
	 * 앱 다함께 상세보기를 조회하는 메서드
	 *
	 * @param   long
	 * @return  TogthrValueObject
	 * @exception  Exception
	 */
		public TogthrValueObject selectAppTogthrDetail(int gropPurchsProdctSeq) throws Exception;

}
