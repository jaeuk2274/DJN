package net.su.prodct.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.su.prodct.valueObject.DebecFestivalValueObject;
import net.su.prodct.valueObject.ProdctFestivalValueObject;

public interface DebecFestivalService {
	
	/**
    * 대백제 목록 조회화면 메서드입니다.
    *
    * @param   @RequestParam DebecFestivalValueObject
    * @return  debecFestivalList
    * @exception  Exception
    */
	public List<DebecFestivalValueObject> debecFestivalList(DebecFestivalValueObject debecFestivalValueObject) throws Exception; 
	
	/**
    * 대백제 목록 일괄삭제 메서드입니다.
    *
    * @param   @RequestParam String[]
    * @return  
    * @exception  Exception
    */	
	public void debecFestivalDelete(String[] data) throws Exception;
	
	/**
    * 대백제 목록 단일 삭제 메서드입니다.
    *
    * @param   @RequestParam int
    * @return  
    * @exception  Exception
    */
	public void debecFestivalSigleDelete(int schedlSeq) throws Exception;
	
	/**
    * 대백제 상세보기 Top 행사를 가져오는 메서드입니다.
    *
    * @param   @RequestParam DebecFestivalValueObject
    * @return  debecFestivalValueObject
    * @exception  Exception
    */
	public DebecFestivalValueObject debecFestivalRead(int schedlSeq) throws Exception;
	
	/**
    * 대백제 상세보기 Bottom 상품 리스트를 가져오는 메서드입니다.
    *
    * @param   @RequestParam int
    * @return  debecFestivalProdctList
    * @exception  Exception
    */
	public List<ProdctFestivalValueObject> debecFestivalProdctList(int schedlSeq) throws Exception;
	
	/**
    * 대백제 목록 등록 메서드입니다.
    *
    * @param   @RequestParam DebecFestivalValueObject
    * @return  
    * @exception  Exception
    */
	public void debecFestivalCreate(MultipartHttpServletRequest request, DebecFestivalValueObject debecFestivalValueObject) throws Exception;
	
	/**
	* 대백제 수정화면 전환시 상품리스트를 임시테이블에 추가하는 메서드입니다.
	*
	* @param   MultipartHttpServletRequest, ProdctFestivalValueObject
	* @return  ResponseBody Map<String, Object>
	* @exception  Exception
	*/	
	public void debecFestivalUpdateProdctList(int schedlSeq) throws Exception;
	
	/**
    * 대백제 수정 기능 메서드입니다.
    *
    * @param   @RequestParam MultipartHttpServletRequest, DebecFestivalValueObject
    * @return  
    * @exception  Exception
    */
	public void debecFestivalUpdate(ProdctFestivalValueObject prodctFestivalValueObject, MultipartHttpServletRequest request) throws Exception;
	
	/**
    * 대백제 수정 상품수정 기능 메서드입니다.
    *
    * @param   @RequestParam MultipartHttpServletRequest, DebecFestivalValueObject
    * @return  
    * @exception  Exception
    */
	public void debecFestivaProdctlUpdate(int schedlSeq) throws Exception;
		
	/**
    * 대백제 수정 상품리스트 일괄삭제 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void debecFestivalUpdateDelete(String[] data) throws Exception;

	/**
    * 대백제 상품추가 테이블1 생성 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public List<ProdctFestivalValueObject> prodctAddList(ProdctFestivalValueObject prodctFestivalValueObject) throws Exception;

	/**
    * 대백제 상품추가 테이블2 생성 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public List<ProdctFestivalValueObject> prodctAddTempList(ProdctFestivalValueObject prodctFestivalValueObject) throws Exception;

	/**
    * 상품 선택 추가 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void prodctAdTempCreate(String[] data) throws Exception;
	
	/**
    * 상품 선택 제거 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void prodctAddTempDelete(String[] data) throws Exception;
	
	/**
    * 상품 선택 임시테이블 전체 삭제 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void prodctTempAllDelete() throws Exception;

	/**
    * 상품 선택 최종 등록 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void prodctCreate(ProdctFestivalValueObject prodctFestivalValueObject) throws Exception;

	/**
    * 대백제 등록화면 상품 리스트(임시 테이블 리스트) 가져오는 메서드입니다. 
    *
    * @param   @RequestParam 
    * @return  
    * @exception  Exception
    */
	public List<ProdctFestivalValueObject> prodctTempList() throws Exception;

	/**
    * 대백제 목록 일괄 삭제 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void prodctDelete(String[] data) throws Exception;

	/**
	* 상품 정보로된 엑셀의 데이터를 읽어 일괄적으로 등록하는 메서드 입니다.
	*
	* @param   MultipartHttpServletRequest, ProdctFestivalValueObject
	* @return  ResponseBody Map<String, Object>
	* @exception  Exception
	*/	
	public void createXlsxUpload(MultipartHttpServletRequest request, ProdctFestivalValueObject prodctFestivalValueObject) throws Exception;
	




}
