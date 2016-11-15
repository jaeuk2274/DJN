/**
 * 재고 관리 서비스입니다.
 * 
 * @see   net.su.deal.controller.StckService
 * @version  1.0 
 * @ author 하원식, 2016/08/27
 */
package net.su.deal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.su.deal.valueObject.InstckValueObject;
import net.su.deal.valueObject.StckValueObject;



public interface StckService {

	//StckServiceImpl 
	
	/**
	* 상품의 재고 정보를  조회하는 메서드입니다.
	*
	* @param  StckValueObject stckValueObject
	* @return  List<StckValueObject>
	* @exception  Exception
	*/
// 재고 목록
	public List<StckValueObject> stckList(StckValueObject stckValueObject) throws Exception;
	
	/**
	* 특정 상품들의 재고 량을 수정하는 메서드 입니다.
	*
	* @param  long[] prodctSeq, int[] qunt
	* @return  void
	* @exception  Exception
	*/
// 재고 수정
	public void stckUpdate(long[] prodctSeq, int[] qunt) throws Exception;

	/**
	* 특정 상품들의 반품 정보를 등록하고 반품된 상품 수만큼 재고량을 줄여주는 메서드입니다.
	*
	* @param  long[] prodctSeq, int[] qunt
	* @return  void
	* @exception  Exception
	*/
// 재고 반품
	public void stckRetrnUpdate(long[] prodctSeq, int[] qunt) throws Exception;
	
	
	/**
	* 재고 정보를 일괄적으로 등록하는 메서드입니다.
	*
	* @param  long[] prodctSeq, int[] qunt
	* @return  void
	* @exception  Exception
	*/
// 재고 반품
	public void stckCreate(MultipartHttpServletRequest request, StckValueObject stckValueObject) throws Exception;
	
	
	/**
	* 특정 상품의 재고 정보를 다운로드하기 위해 조회하는 메서드입니다.
	*
	* @param  long[] prodctSeq
	* @return  void
	* @exception  Exception
	*/
// 재고 반품
	public List<StckValueObject> stckXlxList(long[] prodctSeq) throws Exception;
	
	/**
	* 특정 상품의 입고 내역 정보의 행의 수를 조회하는 메서드 입니다.
	*
	* @param  StckValueObject stckValueObject
	* @return  int
	* @exception  Exception
	*/
	public int instckRecrdCount(StckValueObject stckValueObject) throws Exception;
	
	
	/**
	* 특정 상품의 입고 내역 정보를 조회하는 메서드 입니다.
	*
	* @param  StckValueObject stckValueObject
	* @return  List<StckValueObject>
	* @exception  Exception
	*/	
	public List<StckValueObject> instckRecrdList(StckValueObject stckValueObject) throws Exception;
	
}
