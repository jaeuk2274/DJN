/**
 * 앱 레시피 관리 서비스입니다.
 * 
 * @see   net.su.app.appRecp.service.AppRecpService
 * @version  1.0 
 * @ author 하원식, 2016/10/08
 */
package net.su.app.appRecp.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.su.app.appRecp.valueObject.AppRecpValueObject;
import net.su.logger.Logger;
import net.su.prodct.valueObject.ProdctValueObject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface AppRecpService {
	//AppRecpServiceImpl
	
	/**
    * 레시피 리스트를 조회해주는 메서드 입니다.
    *
    * @param   AppRecpValueObject appRecpValueObject
    * @return  List<AppRecpValueObject>
    * @exception  Exception
    */
	public List<AppRecpValueObject> recpList(AppRecpValueObject appRecpValueObject) throws Exception;
	
	/**
    * 레시피의 좋아요를 처리해주는 메서드 입니다.
    *
    * @param   AppRecpValueObject appRecpValueObject
    * @return  void
    * @exception  Exception
    */
	public void recpLikeProcessing(AppRecpValueObject appRecpValueObject) throws Exception;
	
	/**
    * 레시피를 상세보기 해주는 메서드 입니다.
    *
    * @param   AppRecpValueObject appRecpValueObject
    * @return  AppRecpValueObject
    * @exception  Exception
    */	
	public AppRecpValueObject recpRead(AppRecpValueObject appRecpValueObject) throws Exception;
	
	/**
    * 레시피의 댓글 리스트를 조회 해주는 메서드 입니다.
    *
    * @param   AppRecpValueObject appRecpValueObject
    * @return  List<AppRecpValueObject>
    * @exception  Exception
    */	
	public List<AppRecpValueObject> recpReplyList(AppRecpValueObject appRecpValueObject) throws Exception;
	
	/**
    * 레시피의 관련 상품 리스트를 조회 해주는 메서드 입니다.
    *
    * @param   AppRecpValueObject appRecpValueObject
    * @return  List<AppRecpValueObject>
    * @exception  Exception
    */	
	public List<AppRecpValueObject> recpProdctList(AppRecpValueObject appRecpValueObject) throws Exception;
	
	/**
    * 레시피의 댓글을 등록 시켜주는 메서드 입니다.
    *
    * @param   AppRecpValueObject appRecpValueObject
    * @return  void
    * @exception  Exception
    */	
	public void recpReplyInsert(AppRecpValueObject appRecpValueObject) throws Exception;
		
	/**
    * 레시피 연관 상품을 검색해주는 메서드 입니다..
    *
    * @param   String recpSechText
    * @return  List<AppRecpValueObject>
    * @exception  Exception
    */	
	public List<AppRecpValueObject> appRecpProdctSearch(String recpSechText) throws Exception;
	
	/**
	* 레시피 연관 상품 리스트를 조회해주는 메서드 입니다.
	*
	* @param   long[] recpProdct
	* @return  List<AppRecpValueObject>
	* @exception  Exception
	*/	
	public List<AppRecpValueObject> appRecpProdctList(long[] recpProdct) throws Exception;
	
	/**
	* 레시피 이미지를 임시로 업로드 시켜주는 메서드 입니다.
	*
	* @param   MultipartHttpServletRequest request, AppRecpValueObject appRecpValueObject
	* @return  String
	* @exception  Exception
	*/	
	public String recpTempFileUpload(MultipartHttpServletRequest request, AppRecpValueObject appRecpValueObject) throws Exception;
		
	/**
	* 레시피를 등록시켜주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject, long[] recpProdct
	* @return  int
	* @exception  Exception
	*/
	public int appRecpInsert(AppRecpValueObject appRecpValueObject, long[] recpProdct)throws Exception;
	
	/**
	* 레시피를 삭제 시켜주는 메서드 입니다,
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  void
	* @exception  Exception
	*/
	public void appRecpDelete(AppRecpValueObject appRecpValueObject)throws Exception;
	
	/**
	* 레시피를 수정 시켜주는 메서드 입니다,
	*
	* @param   AppRecpValueObject appRecpValueObject, long[] recpProdct
	* @return  void
	* @exception  Exception
	*/
	public void appRecpUpdate(AppRecpValueObject appRecpValueObject, long[] recpProdct)throws Exception;
	

	/**
	* 레시피의 관련 상품을 카트에 등록 시켜주는 메서드 입니다.
	*
	* @param   int memSeq, long[] prodctSeqArray
	* @return  void
	* @exception  Exception
	*/
	public void appRecpProdctCartInsert(int memSeq, long[] prodctSeqArray) throws Exception;
	

	/**
	* 레시피의 댓글을 삭제시켜주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  void
	* @exception  Exception
	*/
	public void appRecpReplyDelete(AppRecpValueObject appRecpValueObject) throws Exception;

}
