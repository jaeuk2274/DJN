/**
 * 판매 상품 관리 서비스임플입니다.
 * 
 * @see   net.su.prodct.service.ProdctServiceImpl
 * @version  1.0 
 * @ author 하원식, 2016/08/27
 */
package net.su.prodct.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.su.logger.Logger;
import net.su.prodct.dataAccessObject.ProdctDataAccessObject;
import net.su.prodct.valueObject.CatgrValueObject;
import net.su.prodct.valueObject.ProdctValueObject;
@Service
public class ProdctServiceImpl implements ProdctService{
	//ProdctController
	@Resource
	private ProdctDataAccessObject prodctDAO;
	
	/**
    * 판매 상품 리스트를 조회하는 메서드다..
    *
    * @param   ProdctValueObject
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> selProdctList(ProdctValueObject prodctValueObject) throws Exception{
		Logger.info(null);

		int selProdctCount = prodctDAO.selProdctCount(prodctValueObject);	// 고객 리스트 전체 수
		
		System.out.println("selectCustmrCount : " + selProdctCount);
		prodctValueObject.setTotalRecordCount(selProdctCount); // 고객 리스트 전체 수 셋팅
		List<ProdctValueObject> selProdctList = prodctDAO.selProdctList(prodctValueObject);
		
		return selProdctList;
	}
	

	/**
    * 반응형 카테고리 리스트 입니다.
    *
    * @param   String catgrSize, String catgrNme
    * @return  String[]
    * @exception  Exception
    */
	public String[] catgrList(String catgrSize, String catgrNme) throws Exception{
		Logger.info(null);
		
		String[] catgrArray = null;
		List<ProdctValueObject> catgrList = prodctDAO.catgrList(catgrSize,catgrNme);
		if(catgrList!=null){
			catgrArray = new String[catgrList.size()];
			
			for(int i=0; i < catgrList.size(); i++)
			{
				catgrArray[i] = catgrList.get(i).getCatgrNme();
			}
		}
		return catgrArray;
	}
	

	/**
    * 판매 상품을 등록하는 메서드 입니다.
    *
    * @param   ProdctValueObject, HttpSession
    * @return  void
    * @exception  Exception
    */
	public void selProdctCreate(ProdctValueObject prodctValueObject, HttpSession selProdctSession) throws Exception
	{
		Logger.info(null);

		prodctValueObject.setProdctMainImage((String) selProdctSession.getAttribute("mainImg"));
		prodctValueObject.setProdctDetlImageOne((String) selProdctSession.getAttribute("detlImg1"));
		prodctValueObject.setProdctDetlImageTwo((String) selProdctSession.getAttribute("detlImg2"));
		prodctValueObject.setProdctDetlImageThree((String) selProdctSession.getAttribute("detlImg3"));

		prodctDAO.selProdctCreate(prodctValueObject);
	}
	

	/**
    * 판매 상품의 이미지를  등록하는 메서드다.
    *
    * @param   MultipartHttpServletRequest, ProdctValueObject, HttpSession
    * @return  void
    * @exception  Exception
    */
	public void selProdctImgCreate(MultipartHttpServletRequest request, ProdctValueObject prodctValueObject, HttpSession selProdctSession) throws Exception
	{
		Logger.info(null);
		ProdctServiceImpl prodctServiceImpl = new ProdctServiceImpl();

		Map<String, MultipartFile> files = request.getFileMap();
		
		CommonsMultipartFile getMainCmf = (CommonsMultipartFile) files.get("mainProdctImgFile");
		CommonsMultipartFile getDetlCmfOne = (CommonsMultipartFile) files.get("detlProdctImgOneFile");
		CommonsMultipartFile getDetlCmfTwo = (CommonsMultipartFile) files.get("detlProdctImgTwoFile");
		CommonsMultipartFile getDetlCmfThree = (CommonsMultipartFile) files.get("detlProdctImgThreeFile");
		ServletContext context = request.getSession().getServletContext();
		if(getMainCmf.getSize()!=0)
		{
			String filePath ="resources/image/prodct/"+prodctValueObject.getProdctFileSeq()+"/main.jpg";
			String dirPath ="resources/image/prodct/"+prodctValueObject.getProdctFileSeq();
			System.out.println("###"+context.getRealPath(filePath));
			selProdctSession.setAttribute("mainImg", filePath);
			prodctServiceImpl.selProdctImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), getMainCmf);
		}
		else
		{
			selProdctSession.setAttribute("mainImg", "resources/image/common/noImg.png");
		}
		
		if(getDetlCmfOne.getSize()!=0)
		{
			String filePath ="resources/image/prodct/"+prodctValueObject.getProdctFileSeq()+"/detlOne.jpg";
			String dirPath ="resources/image/prodct/"+prodctValueObject.getProdctFileSeq();
			System.out.println("###"+context.getRealPath(filePath));
			selProdctSession.setAttribute("detlImg1", filePath);
			prodctServiceImpl.selProdctImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), getDetlCmfOne);
		}
		else
		{
			selProdctSession.setAttribute("detlImg1", "resources/image/common/noImg.png");
		}
		
		if(getDetlCmfTwo.getSize()!=0)
		{
			String filePath ="resources/image/prodct/"+prodctValueObject.getProdctFileSeq()+"/detlTwo.jpg";
			String dirPath ="resources/image/prodct/"+prodctValueObject.getProdctFileSeq();
			System.out.println("###"+context.getRealPath(filePath));
			selProdctSession.setAttribute("detlImg2", filePath);
			prodctServiceImpl.selProdctImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), getDetlCmfTwo);
		}
		else
		{
			selProdctSession.setAttribute("detlImg2", "resources/image/common/noImg.png");
		}
		
		if(getDetlCmfThree.getSize()!=0)
		{
			String filePath ="resources/image/prodct/"+prodctValueObject.getProdctFileSeq()+"/detlThree.jpg";
			String dirPath ="resources/image/prodct/"+prodctValueObject.getProdctFileSeq();
			System.out.println("###"+context.getRealPath(filePath));
			selProdctSession.setAttribute("detlImg3", filePath);
			prodctServiceImpl.selProdctImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), getDetlCmfThree);
		}
		else
		{
			selProdctSession.setAttribute("detlImg3", "resources/image/common/noImg.png");
		}
		
	}
	

	/**
    * 판매 상품 바코드의 중복 여부를 검사하는 메서드다.
    *
    * @param   long dupliCheck
    * @return  long
    * @exception  Exception
    */
	public long prodctSeqDuliCheck(long dupliCheck) throws Exception
	{
		Logger.info(null);

		dupliCheck = prodctDAO.prodctSeqDuliCheck(dupliCheck);
		
		return dupliCheck;
	}


	/**
    * 미리보기를 위한 이미지 파일을 임시로 저장해주는 메서드다.
    *
    * @param   HttpSession, MultipartHttpServletRequest, ProdctValueObject
    * @return  ProdctValueObject
    * @exception  Exception
    */	
	public ProdctValueObject tempProdctImgSave(HttpSession selProdctSession, MultipartHttpServletRequest request, ProdctValueObject prodctValueObject) throws Exception
	{
		Logger.info(null);
		ProdctServiceImpl prodctServiceImpl = new ProdctServiceImpl();
		System.out.println("#####"+prodctValueObject.getProdctFileSeq());
		Map<String, MultipartFile> files = request.getFileMap();
		
		CommonsMultipartFile getMainCmf = (CommonsMultipartFile) files.get("mainProdctImgFile");
		CommonsMultipartFile getDetlCmfOne = (CommonsMultipartFile) files.get("detlProdctImgOneFile");
		CommonsMultipartFile getDetlCmfTwo = (CommonsMultipartFile) files.get("detlProdctImgTwoFile");
		CommonsMultipartFile getDetlCmfThree = (CommonsMultipartFile) files.get("detlProdctImgThreeFile");
		ServletContext context = request.getSession().getServletContext();
		// 파일 생성 이전에 디렉토리 청소
		String dirCleaner ="resources/image/prodct/temp/"+prodctValueObject.getProdctFileSeq();
		File fileDirCleaner = new File(context.getRealPath(dirCleaner));
		prodctServiceImpl.directoryCleaner(fileDirCleaner);
		
		int random = (int) (Math.random() * 100);
		if(getMainCmf.getSize()!=0)
		{
			String filePath ="resources/image/prodct/temp/"+prodctValueObject.getProdctFileSeq()+"/"+random+"main.jpg";
			String dirPath ="resources/image/prodct/temp/"+prodctValueObject.getProdctFileSeq();
			System.out.println("###"+context.getRealPath(filePath));
			
			prodctValueObject.setProdctMainImage(filePath);
			prodctServiceImpl.selProdctImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), getMainCmf);
		}
		else
		{
			prodctValueObject.setProdctMainImage("resources/image/common/noImg.png");
		}
		if(getDetlCmfOne.getSize()!=0)
		{
			String filePath ="resources/image/prodct/temp/"+prodctValueObject.getProdctFileSeq()+"/"+random+"detlOne.jpg";
			String dirPath ="resources/image/prodct/temp/"+prodctValueObject.getProdctFileSeq();
			prodctValueObject.setProdctDetlImageOne(filePath);
			prodctServiceImpl.selProdctImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), getDetlCmfOne);
		}
		else
		{
			prodctValueObject.setProdctDetlImageOne("resources/image/common/noImg.png");
		}
		if(getDetlCmfTwo.getSize()!=0)
		{
			String filePath ="resources/image/prodct/temp/"+prodctValueObject.getProdctFileSeq()+"/"+random+"detlTwo.jpg";
			String dirPath ="resources/image/prodct/temp/"+prodctValueObject.getProdctFileSeq();
			prodctValueObject.setProdctDetlImageTwo(filePath);
			prodctServiceImpl.selProdctImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), getDetlCmfTwo);
		}
		else
		{
			prodctValueObject.setProdctDetlImageTwo("resources/image/common/noImg.png");
		}
		if(getDetlCmfThree.getSize()!=0)
		{
			String filePath ="resources/image/prodct/temp/"+prodctValueObject.getProdctFileSeq()+"/"+random+"detlThree.jpg";
			String dirPath ="resources/image/prodct/temp/"+prodctValueObject.getProdctFileSeq();
			prodctValueObject.setProdctDetlImageThree(filePath);
			prodctServiceImpl.selProdctImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), getDetlCmfThree);
		}
		else
		{
			prodctValueObject.setProdctDetlImageThree("resources/image/common/noImg.png");
		}
		selProdctSession.setAttribute("tempImgPath", context.getRealPath("resources/image/prodct/temp/"+prodctValueObject.getProdctFileSeq()));
		
		return prodctValueObject;
	}
	

	/**
    * 특정 경로 상에 사용되지 않는 파일들을 청소해주는 메서드다.
    *
    * @param   File file
    * @return  void
    * @exception  Exception
    */	
	public void directoryCleaner(File file) throws Exception
	{
		Logger.info(null);
		
		if (!file.exists())
		{
			return;
		}
		File[] files = file.listFiles();
		for(int i = 0 ; i < files.length;i++)
		{	  
			files[i].delete();
		}
		file.delete();
	}
	/**
    * 특정 경로 상에 사용되지 않는 파일들을 청소해주는 메서드다.
    *
    * @param   File file
    * @return  void
    * @exception  Exception
    */	
	public void directoryCleaner(String prodctImgPath) throws Exception
	{
		Logger.info(null);
		if(prodctImgPath!=null)
		{
			File file = new File(prodctImgPath);
			System.out.println(prodctImgPath);
			if (!file.exists())
			{
				return;
			}
			File[] files = file.listFiles();
			for(int i = 0 ; i < files.length;i++)
			{
				files[i].delete();
				System.out.println("돌고돌고");
			}
			file.delete();
		}
	}
	

	/**
    * 사진을 저장 시켜주는 역할을 하는 메서드
    *
    * @param   String filePath, String dirPath, CommonsMultipartFile cmf 
    * @return  void
    * @exception  Exception
    */
	public void selProdctImgSave(String filePath, String dirPath, CommonsMultipartFile cmf) throws Exception
	{
		Logger.info(null);
		File fileSaver = new File(filePath);
		File dirSaver = new File(dirPath);
		// 파일 업로드 처리 완료.
		try {
			if(!fileSaver.exists())
			{
				dirSaver.mkdir();
			}
			cmf.transferTo(fileSaver);
			// insert method
			System.out.println("성공");
		} catch (Exception e) {
			System.out.println("실패: "+ e);
		}
	}

	
	/**
    * 카테고리 리스트를 조회하는 메서드 입니다.
    *
    * @param   
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */	
	public List<CatgrValueObject> catgrProdctList() throws Exception{
		Logger.info(null);
		
		List<CatgrValueObject> catgrProdctList = prodctDAO.catgrProdctList();
		
		return catgrProdctList;
	}
	

	/**
    *  판매 중인 상품을 판매 중지하는 메서드 입니다.
    *
    * @param   long[] selProdctArray
    * @return  void
    * @exception  Exception
    */	
	public void selProdctDelete(long[] selProdctArray) throws Exception
	{
		Logger.info(null);
		for(int i=0; i<selProdctArray.length; i++)
		{
			prodctDAO.selProdctDelete(selProdctArray[i]);
		}
	}
	

	/**
    *  판매 상품을 상세보기하는 메서드 입니다.
    *
    * @param   long prodctSeq
    * @return  ArrayList<ProdctValueObject>
    * @exception  Exception
    */	
	public ArrayList<ProdctValueObject> selProdctRead(long prodctSeq) throws Exception
	{
		Logger.info(null);
		
		ArrayList<ProdctValueObject> selProdctReads = new ArrayList<ProdctValueObject>();
		ProdctValueObject selProdctRead = prodctDAO.selProdctRead(prodctSeq); // 상세보기 하는 상품
		ProdctValueObject reltnProdctRead1 = prodctDAO.selProdctRead(selProdctRead.getReltnProdctSeqOne());  // 관련 상품  #1
		ProdctValueObject reltnProdctRead2 = prodctDAO.selProdctRead(selProdctRead.getReltnProdctSeqTwo());  // 관련 상품  #2
		ProdctValueObject reltnProdctRead3 = prodctDAO.selProdctRead(selProdctRead.getReltnProdctSeqThree());  // 관련 상품  #3
		ProdctValueObject evntInfoRead = prodctDAO.evntInfoRead(prodctSeq);  // 상품의 행사 정보 조회
		ProdctValueObject catgrProdctRead = prodctDAO.catgrProdctRead(prodctSeq);  // 상품의 행사 정보 조회

		selProdctReads.add(selProdctRead);
		selProdctReads.add(reltnProdctRead1);
		selProdctReads.add(reltnProdctRead2);
		selProdctReads.add(reltnProdctRead3);
		selProdctReads.add(evntInfoRead);
		selProdctReads.add(catgrProdctRead);
		return selProdctReads;
	}
	
	
	/**
    *  판매 중인 상품의 정보를 수정하는 메서드다.
    *
    * @param   ProdctValueObject, HttpSession
    * @return  void
    * @exception  Exception
    */
	public void selProdctUpdate(ProdctValueObject prodctValueObject, HttpSession selProdctSession) throws Exception{
		Logger.info(null);
		
		prodctValueObject.setProdctMainImage((String) selProdctSession.getAttribute("mainImg"));
		System.out.println("###"+selProdctSession.getAttribute("mainImg"));
		prodctValueObject.setProdctDetlImageOne((String) selProdctSession.getAttribute("detlImg1"));
		System.out.println("###"+selProdctSession.getAttribute("detlImg1"));
		prodctValueObject.setProdctDetlImageTwo((String) selProdctSession.getAttribute("detlImg2"));
		System.out.println("###"+selProdctSession.getAttribute("detlImg2"));
		prodctValueObject.setProdctDetlImageThree((String) selProdctSession.getAttribute("detlImg3"));
		System.out.println("###"+selProdctSession.getAttribute("detlImg3"));
		prodctDAO.selProdctUpdate(prodctValueObject);
	}


	/**
    *  판매 상품의 이미지 경로를 저장하는 메서드 입니다.
    *
    * @param   ProdctValueObject, HttpSession
    * @return  void
    * @exception  Exception
    */	
	public void selProdctImgPathSession(ProdctValueObject prodctValueObject, HttpSession selProdctSession)
	{
		Logger.info(null);
		if(prodctValueObject.getProdctMainImage()!=null)
		{
			selProdctSession.setAttribute("prodctMainImage", prodctValueObject.getProdctMainImage());
		}
		else
		{
			selProdctSession.setAttribute("prodctMainImage", "resources/image/common/noImg.png");
		}
		
		
		if(prodctValueObject.getProdctDetlImageOne()!=null)
		{
			selProdctSession.setAttribute("prodctDetlImageOne", prodctValueObject.getProdctDetlImageOne());
		}
		else
		{
			selProdctSession.setAttribute("prodctDetlImageOne", "resources/image/common/noImg.png");		
		}
		
		
		if(prodctValueObject.getProdctDetlImageTwo()!=null)
		{
			selProdctSession.setAttribute("prodctDetlImageTwo", prodctValueObject.getProdctDetlImageTwo());
		}
		else
		{
			selProdctSession.setAttribute("getProdctDetlImageTwo", "resources/image/common/noImg.png");		
		}
		
		
		if(prodctValueObject.getProdctDetlImageThree()!=null)
		{
			selProdctSession.setAttribute("prodctDetlImageThree", prodctValueObject.getProdctDetlImageThree());
		}
		else
		{
			selProdctSession.setAttribute("prodctDetlImageThree", "resources/image/common/noImg.png");		
		}
	}

	
	/**
    * 판매 중지 상품 리스트를 조회하는 매서드 입니다.
    *
    * @param   ProdctValueObject
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> selStopProdctList(ProdctValueObject prodctValueObject) throws Exception
	{
		Logger.info(null);

		int selStopProdctCount = prodctDAO.selStopProdctCount(prodctValueObject);	// 고객 리스트 전체 수
		
		System.out.println("sellStopProdctCount : " + selStopProdctCount);
		prodctValueObject.setTotalRecordCount(selStopProdctCount); // 고객 리스트 전체 수 셋팅
		List<ProdctValueObject> selStopProdctList = prodctDAO.selStopProdctList(prodctValueObject);
		
		return selStopProdctList;
	}
	

	/**
    * 판매 중지 상품 을 삭제 하는 메서드다.
    *
    * @param   long[] selStopProdctArray
    * @return  void
    * @exception  Exception
    */
	public void selStopProdctDelete(long[] selStopProdctArray) throws Exception
	{
		Logger.info(null);
		for(int i=0; i<selStopProdctArray.length; i++)
		{
			prodctDAO.selStopProdctDelete(selStopProdctArray[i]);
		}
	}
	

	/**
    * 판매 중지 상품의 상태를 판매중으로 바꿔주는 메서드다.
    *
    * @param   long[] selStopProdctArray
    * @return  void
    * @exception  Exception
    */
	public void selStopProdctReSelStrt(long[] selStopProdctArray) throws Exception
	{
		Logger.info(null);
		for(int i=0; i<selStopProdctArray.length; i++)
		{
			prodctDAO.selStopProdctReSelStrt(selStopProdctArray[i]);
		}
	}

	
	/**
    * 재고 반품을 과정에서 필요한 리스트 출력 메서드
    *
    * @param   long[] prodctSeq
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	public List<ProdctValueObject> selectedSelProdctList(long[] prodctSeq) throws Exception
	{
		Logger.info(null);
		List<ProdctValueObject> selectedSelProdctList = prodctDAO.selectedSelProdctList(prodctSeq);
		
		return selectedSelProdctList;
	}
	

	/**
    * 카테고리만 조회하는 메서드
    *
    * @param   long prodctSeq
    * @return  ProdctValueObject
    * @exception  Exception
    */
	public ProdctValueObject catgrProdctRead(long prodctSeq)throws Exception
	{
		Logger.info(null);
	
		ProdctValueObject catgrProdctRead = prodctDAO.catgrProdctRead(prodctSeq);  // 상품의 행사 정보 조회

		return catgrProdctRead;
	}
}
