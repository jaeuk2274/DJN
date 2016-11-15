/**
 * 판매 상품 관리 서비스입니다.
 * 
 * @see   net.su.prodct.service.ProdctService
 * @version  1.0 
 * @ author 하원식, 2016/08/27
 */
package net.su.prodct.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;





















import net.su.prodct.valueObject.CatgrValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

public interface ProdctService {
	
//  ProdctServiceImpl
//	ProdctController
	
	/**
    * 판매 상품 리스트를 조회하는 메서드다..
    *
    * @param   ProdctValueObject
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> selProdctList(ProdctValueObject prodctValueObject) throws Exception;
	
	
	/**
    * 반응형 카테고리 리스트 입니다.
    *
    * @param   String catgrSize, String catgrNme
    * @return  String[]
    * @exception  Exception
    */
	public String[] catgrList(String catgrSize, String catgrNme) throws Exception;
	
	
	/**
    * 판매 상품을 등록하는 메서드 입니다.
    *
    * @param   ProdctValueObject, HttpSession
    * @return  void
    * @exception  Exception
    */
	public void selProdctCreate(ProdctValueObject prodctValueObject, HttpSession selProdctSession) throws Exception;

	/**
    * 판매 상품의 이미지를  등록하는 메서드다.
    *
    * @param   MultipartHttpServletRequest, ProdctValueObject, HttpSession
    * @return  void
    * @exception  Exception
    */
	public void selProdctImgCreate(MultipartHttpServletRequest request, ProdctValueObject prodctValueObject, HttpSession selProdctSession) throws Exception;
	

	/**
    * 판매 상품 바코드의 중복 여부를 검사하는 메서드다.
    *
    * @param   long dupliCheck
    * @return  long
    * @exception  Exception
    */
	public long prodctSeqDuliCheck(long dupliCheck) throws Exception;
	

	/**
    * 미리보기를 위한 이미지 파일을 임시로 저장해주는 메서드다.
    *
    * @param   HttpSession, MultipartHttpServletRequest, ProdctValueObject
    * @return  ProdctValueObject
    * @exception  Exception
    */	
	public ProdctValueObject tempProdctImgSave(HttpSession session, MultipartHttpServletRequest request, ProdctValueObject prodctValueObject) throws Exception;
	

	/**
    * 카테고리 리스트를 조회하는 메서드 입니다.
    *
    * @param   
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */	
	public List<CatgrValueObject> catgrProdctList()throws Exception;
	

	/**
    *  판매 중인 상품을 판매 중지하는 메서드 입니다.
    *
    * @param   long[] selProdctArray
    * @return  void
    * @exception  Exception
    */	
	public void selProdctDelete(long[] selProdctArray) throws Exception;
	


	/**
    *  판매 상품을 상세보기하는 메서드 입니다.
    *
    * @param   long prodctSeq
    * @return  ArrayList<ProdctValueObject>
    * @exception  Exception
    */	
	public ArrayList<ProdctValueObject> selProdctRead(long prodctSeq) throws Exception;
	

	/**
    *  판매 중인 상품의 정보를 수정하는 메서드다.
    *
    * @param   ProdctValueObject, HttpSession
    * @return  void
    * @exception  Exception
    */	
	public void selProdctUpdate(ProdctValueObject prodctValueObject, HttpSession selProdctSession) throws Exception;
	

	/**
    *  판매 상품의 이미지 경로를 저장하는 메서드 입니다.
    *
    * @param   ProdctValueObject, HttpSession
    * @return  void
    * @exception  Exception
    */	
	public void selProdctImgPathSession(ProdctValueObject prodctValueObject, HttpSession selProdctSession);

	
	/**
    * 판매 중지 상품 리스트를 조회하는 매서드 입니다.
    *
    * @param   ProdctValueObject
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> selStopProdctList(ProdctValueObject prodctValueObject) throws Exception;
	

	/**
    * 판매 중지 상품 을 삭제 하는 메서드다.
    *
    * @param   long[] selStopProdctArray
    * @return  void
    * @exception  Exception
    */
	public void selStopProdctDelete(long[] selStopProdctArray) throws Exception;
	

	/**
    * 판매 중지 상품의 상태를 판매중으로 바꿔주는 메서드다.
    *
    * @param   long[] selStopProdctArray
    * @return  void
    * @exception  Exception
    */
	public void selStopProdctReSelStrt(long[] selStopProdctArray) throws Exception;

	/**
    * 재고 반품을 과정에서 필요한 리스트 출력 메서드
    *
    * @param   long[] prodctSeq
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> selectedSelProdctList(long[] prodctSeq) throws Exception;

	/**
    * 카테고리만 조회하는 메서드
    *
    * @param   long prodctSeq
    * @return  ProdctValueObject
    * @exception  Exception
    */
	public ProdctValueObject catgrProdctRead(long prodctSeq)throws Exception;
	/**
    * 특정 경로에 이미지를 지워주는 메서드
    *
    * @param   long prodctSeq
    * @return  void
    * @exception  Exception
    */
	public void directoryCleaner(String prodctSeq)throws Exception;
}
