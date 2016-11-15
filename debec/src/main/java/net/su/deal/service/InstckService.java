/**
 * 입고거래관리 서비스입니다.
 * 
 * @see   net.su.deal.service.InstckService
 * @version  1.0 
 * @ author 김대현, 2016/08/15
 */
package net.su.deal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.su.deal.valueObject.InstckValueObject;

public interface InstckService {

	
	/**
	* 거래처 입고거래장을 조회하는 메서드입니다.
	*
	* @param  int clintSeq, InstckValueObject instckVo
	* @return  List
	* @exception  예외처리 상황을 적어주세요
	*/
// 입고거래장 목록 조회하는 메소드
	public List<InstckValueObject> instckList(int clintSeq, InstckValueObject instckVo) throws Exception;
	
	/**
	* 거래처 입고거래장을 상세조회하는 메서드입니다.
	*
	* @param   InstckValueObject instckVo
	* @return  InstckValueObject
	* @exception  예외처리 상황을 적어주세요
	*/
// 입고거래장 상세보기 메소드
	public InstckValueObject instckExchngFlorReadPopup(InstckValueObject instckVo) throws Exception;
	
	/**
	* 거래처 입고내역을 조회하는 메서드입니다.
	*
	* @param   InstckValueObject instckVo
	* @return  List
	* @exception  예외처리 상황을 적어주세요
	*/
// 입고내역 조회 메소드
	public List<InstckValueObject> instckRecrdList(InstckValueObject instckVo) throws Exception;
	
	/**
	* 입고거래장 목록을 조회하는 메서드입니다.
	*
	* @param   InstckValueObject instckVo
	* @return  List
	* @exception  예외처리 상황을 적어주세요
	*/
// 입고거래장 목록 조회 메소드
	public List<InstckValueObject> selectInstckExchngFlor(InstckValueObject instckVo) throws Exception;
	
	/**
	* 입고거래장등록(팝업)에 거래처명을 조회하는 메서드입니다.
	*
	* @param   
	* @return   List<InstckValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
// 입고거래장 등록팝업에 거래처명 조회 메소드
	public List<InstckValueObject> selectClintNme() throws Exception;

	/**
	* 입고거래장을 등록하는 메서드입니다.
	*
	* @param   InstckValueObject instckVo
	* @return 
	* @exception  예외처리 상황을 적어주세요
	*/
// 입고거래장(팝업) 등록하는 메소드
	public void insertInstckExchngFlor(InstckValueObject instckVo) throws Exception;
	
	/**
	* 입고거래장을 수정하는 메서드입니다.
	*
	* @param   InstckValueObject instckVo
	* @return 
	* @exception  예외처리 상황을 적어주세요
	*/
// 입고거래장(팝업) 수정하는 메서드
	public void updateInstckExchngFlor(InstckValueObject instckVo) throws Exception;
	
	/**
	* 입고거래장(팝업)을 삭제하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/
// 입고거래장(팝업) 삭제하는 메서드
	public void deleteInstckExchngFlorPopup(InstckValueObject instckVo) throws Exception;
	
	/**
	* 입고거래장을 체크해서 삭제하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/
// 입고거래장 체크해서 삭제
	public void deleteInstckExchngFlor(InstckValueObject instckVo) throws Exception;
	
	
	/**
		* 입고거래장 등록에서 (이미지 등록)을 하는 메서드입니다.
		* 
		* @param   MultipartHttpServletRequest request, OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void imgInsert(MultipartHttpServletRequest request, InstckValueObject instckVo) throws Exception;
	
	/**
		* 입고거래장 수정에서 (이미지 등록)을 하는 메서드입니다.
		* 
		* @param   MultipartHttpServletRequest request, OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void imgUpdate(MultipartHttpServletRequest request, InstckValueObject instckVo) throws Exception;

	/**
		* 이미지 저장을 하는 메서드입니다.
		* 
		* @param   MultipartHttpServletRequest request, OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void selProdctImgSave(String filePath, String dirPath, CommonsMultipartFile cmf) throws Exception;
}
