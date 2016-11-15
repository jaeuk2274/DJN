
package net.su.deal.service;

import java.util.List;

import net.su.deal.valueObject.ClintValueObject;

/**
 * 거래처관리 서비스입니다.
 * 
 * @see   net.su.deal.service.ClintService
 * @version  1.0 
 * @ author 김대현, 2016/08/09
 */
public interface ClintService {

	/**
	* 거래처관리 거래처 목록 조회를 하는 메서드입니다.
	*
	* @param   ClintValueObject clintVo
	* @return  List
	* @exception  예외처리 상황을 적어주세요
	*/
	public List<ClintValueObject> clintList(ClintValueObject clintVo) throws Exception;
	
	/**
	* 거래처관리 거래처 상세보기를 하는 메서드입니다.
	*
	* @param   ClintValueObject clintVo
	* @return  ClintValueObject
	* @exception  예외처리 상황을 적어주세요
	*/
	// 거래처 정보 상세조회하는 메소드
	public ClintValueObject clintRead(int clintSeq) throws Exception;
	
	/**
	* 거래처관리 거래처 상세보기에서 거래처를 삭제하는 메서드입니다.
	*
	* @param   int clintSeq
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 거래처 상세조회에서 해당 거래처 삭제하는 메소드
	public void clintReadDelete(int clintSeq) throws Exception;

	/**
	* 거래처관리 거래처 수정화면에서 거래처를 수정하는 메서드입니다.
	*
	* @param  ClintValueObject clintVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 거래처 수정
	public void clintUpdate(ClintValueObject clintVo) throws Exception;
	
	/**
	* 거래처관리 상품추가모달에서 거래처가 미등록된 상품을 조회하는 메서드입니다.
	*
	* @param 
	* @return  SelectProdctAdList
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 상품추가 모달에서 거래처가 미등록된 상품 조회(왼쪽 테이블)
	public List<ClintValueObject> selectProdctAdList(ClintValueObject clintVo) throws Exception;
	
	/**
	* 거래처관리 상품추가모달에서 임시테이블 상품을 조회하는 메서드입니다.
	*
	* @param 
	* @return  selectProdctAdTempList
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 상품추가 모달에서 임시테이블 상품 조회(오른쪽 테이블)
	public List<ClintValueObject> selectProdctAdTempList(ClintValueObject clintVo) throws Exception;
	
	/**
	* 거래처관리 상품추가모달에서 임시테이블 상품을 조회하는 메서드입니다.
	*
	* @param String[] data
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 상품추가 모달에서 임시테이블에 상품 추가(오른쪽테이블)
	public void prodctAddTempTableCreate(String[] data) throws Exception;
	
	/**
	* 거래처관리 상품추가모달에서 임시테이블 상품을 삭제하는 메서드입니다.
	*
	* @param String[] data
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 상품추가 모달에서 임시테이블에 상품 삭제(오른쪽 테이블)
	public void prodctAddTempTableDelete(String[] data) throws Exception;
	
	/**
	* 임시테이블 상품 삭제 메서드
	*
	* @param 
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 임시테이블 상품 삭제 메서드
	public void deleteTempTable() throws Exception;

	/**
	* 임시테이블 상품을 DB에 최종 등록
	*
	* @param String[] data
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 임시테이블 상품을 DB에 최종 등록
	public void insertProdct(String[] data, int clintSeq) throws Exception;
	
	/**
	* 거래처관리 상세보기에 상품을 조회하는 메서드입니다.
	*
	* @param   ClintValueObject clintVo
	* @return  List<ClintValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 거래처 상세보기에 상품 조회
	public List<ClintValueObject> prodctList(ClintValueObject clintVo) throws Exception;
	
	/**
	* 거래처관리 거래처를 등록하는 메서드입니다.
	*
	* @param   ClintValueObject clintVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 거래처 등록 메서드
	public int insertClint(ClintValueObject clintVo) throws Exception;
	
	/**
	* 거래처관리 거래처를 체크해서 삭제하는 메서드입니다.
	*
	* @param   ClintValueObject clintVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 거래처 체크해서 삭제하는 메서드
	public void deleteClint(ClintValueObject clintVo) throws Exception;

	
	/**
	* 상품추가 팝업창에서 완료를 눌렀을 때, 임시테이블에 있는 상품들의 seq를 가져오는 메소드.
	*
	* @param   ClintValueObject clintVo
	* @return  String[]
	* @exception  예외처리 상황을 적어주세요
	*/	
	public String[] selectTempProdct(ClintValueObject clintVo) throws Exception;
	
	
	/**
	* 거래처 상세보기에서 거래처 상품들 거래처 일괄 변경 하는 메소드
	*
	* @param   ClintValueObject clintVo
	* @return  void
	* @exception  예외처리 상황을 적어주세요
	*/	
	public void ClintProductUpdate(ClintValueObject clintVo) throws Exception;
	
	/**
	* 거래처 상세보기에서 거래처 상품들 일괄 삭제하는 메소드
	*
	* @param   ClintValueObject clintVo
	* @return  void
	* @exception  예외처리 상황을 적어주세요
	*/	
	public void ClintProductDelete(ClintValueObject clintVo) throws Exception;
	
	
	
	
	
	
	
}
