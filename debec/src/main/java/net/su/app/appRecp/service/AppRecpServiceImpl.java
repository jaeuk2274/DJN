/**
 * 앱 레시피 관리 서비스입니다.
 * 
 * @see   net.su.app.appRecp.service.AppRecpServiceImpl
 * @version  1.0 
 * @ author 하원식, 2016/10/08
 */
package net.su.app.appRecp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.su.app.appRecp.dataAccessObject.AppRecpDataAccessObject;
import net.su.app.appRecp.valueObject.AppRecpValueObject;
import net.su.logger.Logger;
import net.su.prodct.service.ProdctServiceImpl;
import net.su.prodct.valueObject.ProdctValueObject;
@Service 
public class AppRecpServiceImpl implements AppRecpService {
	@Resource
	AppRecpDataAccessObject appRecpDataAccessObject;
	
	/**
    * 레시피 리스트를 조회해주는 메서드 입니다.
    *
    * @param   AppRecpValueObject appRecpValueObject
    * @return  List<AppRecpValueObject>
    * @exception  Exception
    */	
	public List<AppRecpValueObject> recpList(AppRecpValueObject appRecpValueObject) throws Exception
	{
		Logger.info(null);
		List<AppRecpValueObject> recpList =  appRecpDataAccessObject.recpList(appRecpValueObject);

		return recpList;
	}
	
	/**
    * 레시피의 좋아요를 처리해주는 메서드 입니다.
    *
    * @param   AppRecpValueObject appRecpValueObject
    * @return  void
    * @exception  Exception
    */
	public void recpLikeProcessing(AppRecpValueObject appRecpValueObject) throws Exception
	{
		Logger.info(null);
		if(appRecpValueObject.getLikeState()==0)
		appRecpDataAccessObject.recpLikeInsert(appRecpValueObject);
		else if(appRecpValueObject.getLikeState()==1)
		appRecpDataAccessObject.recpLikeDelete(appRecpValueObject);
	}
	
	/**
    * 레시피를 상세보기 해주는 메서드 입니다.
    *
    * @param   AppRecpValueObject appRecpValueObject
    * @return  AppRecpValueObject
    * @exception  Exception
    */
	public AppRecpValueObject recpRead(AppRecpValueObject appRecpValueObject) throws Exception
	{
		Logger.info(null);
		appRecpValueObject = appRecpDataAccessObject.recpRead(appRecpValueObject);
		return appRecpValueObject;
	}
	
	/**
	* 레시피의 댓글을 등록 시켜주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  void
	* @exception  Exception
	*/
	public void recpReplyInsert(AppRecpValueObject appRecpValueObject) throws Exception
	{
		Logger.info(null);
		appRecpDataAccessObject.recpReplyInsert(appRecpValueObject);
		
	}
	
	/**
	* 레시피의 관련 상품 리스트를 조회 해주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  List<AppRecpValueObject>
	* @exception  Exception
	*/
	public List<AppRecpValueObject> recpProdctList(AppRecpValueObject appRecpValueObject) throws Exception
	{
		List<AppRecpValueObject> recpProdctList = appRecpDataAccessObject.recpProdctList(appRecpValueObject);
				
		return recpProdctList;
	}
	
	/**
	* 레시피의 댓글 리스트를 조회 해주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  List<AppRecpValueObject>
	* @exception  Exception
	*/
	public List<AppRecpValueObject> recpReplyList(AppRecpValueObject appRecpValueObject) throws Exception
	{
		Logger.info(null);
		List<AppRecpValueObject> recpReplyList = appRecpDataAccessObject.recpReplyList(appRecpValueObject);

		return recpReplyList;
	}
	
	/**
	* 레시피 연관 상품을 검색해주는 메서드 입니다..
	*
	* @param   String recpSechText
	* @return  List<AppRecpValueObject>
	* @exception  Exception
	*/
	public List<AppRecpValueObject> appRecpProdctSearch(String recpSechText) throws Exception{
		List<AppRecpValueObject> appRecpProdctSearch = appRecpDataAccessObject.appRecpProdctSearch(recpSechText);
		return appRecpProdctSearch;
	}
	
	/**
	* 레시피 연관 상품 리스트를 조회해주는 메서드 입니다.
	*
	* @param   long[] recpProdct
	* @return  List<AppRecpValueObject>
	* @exception  Exception
	*/
	public List<AppRecpValueObject> appRecpProdctList(long[] recpProdct) throws Exception{
		List<AppRecpValueObject> appRecpProdctList = appRecpDataAccessObject.appRecpProdctList(recpProdct);
		return appRecpProdctList;
	}
	
	/**
	* 레시피 이미지를 임시로 업로드 시켜주는 메서드 입니다.
	*
	* @param   MultipartHttpServletRequest request, AppRecpValueObject appRecpValueObject
	* @return  String
	* @exception  Exception
	*/	
	public String recpTempFileUpload(MultipartHttpServletRequest request, AppRecpValueObject appRecpValueObject) throws Exception
	{
		ProdctServiceImpl prodctServiceImpl = new ProdctServiceImpl();
		Map<String, MultipartFile> files = request.getFileMap();
		ServletContext context = request.getSession().getServletContext();
		
		int random = (int) (Math.random() * 100000000);
		String dirCleaner = "";
		String filePath ="";
		String dirPath ="";
		
		CommonsMultipartFile getCmf = (CommonsMultipartFile) files.get("recpImgFile");
		if(appRecpValueObject.getPageType()==0)
		{
			if(appRecpValueObject.getTempNum()!=0)
			{
				dirCleaner ="resources/image/recp/temp/"+appRecpValueObject.getTempNum()+"/";
				
				File fileDirCleaner = new File(context.getRealPath(dirCleaner));
				prodctServiceImpl.directoryCleaner(fileDirCleaner);
			}	
			filePath ="resources/image/recp/temp/"+random+"/main.jpg";
			dirPath ="resources/image/recp/temp/"+random+"/";
			
			prodctServiceImpl.selProdctImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), getCmf);
		}
		if(appRecpValueObject.getPageType()==1)
		{
			dirCleaner ="resources/image/recp/temp/"+appRecpValueObject.getTempNum()+"/";	
			filePath ="resources/image/recp/recpImg/"+appRecpValueObject.getRecpSeq()+"/main"+random+".jpg";
			dirPath ="resources/image/recp/recpImg/"+appRecpValueObject.getRecpSeq();

			appRecpValueObject.setRecpImg(filePath);

			prodctServiceImpl.directoryCleaner(context.getRealPath(dirPath));
			
			File dirSaver = new File(context.getRealPath(dirPath));
			dirSaver.mkdir();
			
			fileCopy(context.getRealPath(dirCleaner+"/main.jpg"), context.getRealPath(filePath));
			appRecpDataAccessObject.appRecpImgSrcUpdate(appRecpValueObject);
			
			File fileDirCleaner = new File(context.getRealPath(dirCleaner));
			prodctServiceImpl.directoryCleaner(fileDirCleaner);
		}
		
		return random+"";
	}
	
	/**
	* 레시피를 등록시켜주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject, long[] recpProdct
	* @return  int
	* @exception  Exception
	*/
	public int appRecpInsert(AppRecpValueObject appRecpValueObject, long[] recpProdct)throws Exception
	{
		int recpSeq = appRecpDataAccessObject.appRecpInsert(appRecpValueObject, recpProdct);
		
		return recpSeq;
	}
	
	/**
	* 파일을 복사해주는 메서드 입니다. 등록 혹은 수정 중이던 레시피의 임시 이미지를 실제 등록 및 수정 시에 이미지 이동에 사용됩니다. 
	*
	* @param   String inFileName, String outFileName
	* @return  void
	* @exception  Exception
	*/
	public void fileCopy(String inFileName, String outFileName) {
		try 
		{
		   FileInputStream fis = new FileInputStream(inFileName);
		   FileOutputStream fos = new FileOutputStream(outFileName);

		   int data = 0;
		   while((data=fis.read())!=-1) {
			   fos.write(data);
		   }
			fis.close();
			fos.close();
	   
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	* 레시피를 삭제 시켜주는 메서드 입니다,
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  void
	* @exception  Exception
	*/
	public void appRecpDelete(AppRecpValueObject appRecpValueObject)throws Exception
	{
		appRecpDataAccessObject.appRecpDelete(appRecpValueObject);
	}

	/**
	* 레시피를 수정 시켜주는 메서드 입니다,
	*
	* @param   AppRecpValueObject appRecpValueObject, long[] recpProdct
	* @return  void
	* @exception  Exception
	*/
	public void appRecpUpdate(AppRecpValueObject appRecpValueObject, long[] recpProdct)throws Exception
	{
		appRecpDataAccessObject.appRecpUpdate(appRecpValueObject, recpProdct);
	}

	/**
	* 레시피의 관련 상품을 카트에 등록 시켜주는 메서드 입니다.
	*
	* @param   int memSeq, long[] prodctSeqArray
	* @return  void
	* @exception  Exception
	*/
	public void appRecpProdctCartInsert(int memSeq, long[] prodctSeqArray) throws Exception
	{
		appRecpDataAccessObject.appRecpProdctCartInsert(memSeq, prodctSeqArray);
	}
	

	/**
	* 레시피의 댓글을 삭제시켜주는 메서드 입니다.
	*
	* @param   AppRecpValueObject appRecpValueObject
	* @return  void
	* @exception  Exception
	*/
	public void appRecpReplyDelete(AppRecpValueObject appRecpValueObject) throws Exception
	{
		appRecpDataAccessObject.appRecpReplyDelete(appRecpValueObject);
	}
}
