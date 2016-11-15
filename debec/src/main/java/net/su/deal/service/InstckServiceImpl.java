/**
 * 입고거래관리 서비스임플입니다.
 * 
 * @see   net.su.deal.service.InstckServiceImpl
 * @version  1.0 
 * @ author 김대현, 2016/08/15
 */

package net.su.deal.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.su.deal.dataAccessObject.InstckDataAccessObject;
import net.su.deal.valueObject.InstckValueObject;
import net.su.logger.Logger;

@Service
public class InstckServiceImpl implements InstckService{
	@Resource
	private InstckDataAccessObject instckDao;
	
	
	/**
	* 거래처 입고거래장을 조회하는 메서드입니다.
	*
	* @param  int clintSeq, InstckValueObject instckVo
	* @return  List
	* @exception  예외처리 상황을 적어주세요
	*/
	// 거래처 상세조회에서 해당 거래처 입고거래장 팝업을 조회하는 메소드
	public List<InstckValueObject> instckList(int clintSeq, InstckValueObject instckVo) throws Exception{
		int selectInstckCount = instckDao.selectInstckCount(instckVo);	// 입고거래장 리스트 전체 수
		instckVo.setTotalRecordCount(selectInstckCount); // 입고거래장 리스트 전체 수 셋팅
		List<InstckValueObject> instckList = instckDao.instckList(clintSeq, instckVo);
		return instckList;
	}
	
	/**
	* 거래처 입고거래장을 상세조회하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  InstckValueObject
	* @exception  예외처리 상황을 적어주세요
	*/
	// 거래처 입고거래장 상세보기 메소드
	public InstckValueObject instckExchngFlorReadPopup(InstckValueObject instckVo) throws Exception{
		instckVo=instckDao.instckExchngFlorReadPopup(instckVo);
		return instckVo;
	}
	
	/**
	* 거래처 입고내역을 조회하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  InstckValueObject
	* @exception  예외처리 상황을 적어주세요
	*/
	// 거래처 입고내역 조회하는 메소드
	public List<InstckValueObject> instckRecrdList(InstckValueObject instckVo) throws Exception{		
		int selectInstckRecrdCount = instckDao.selectInstckRecrdCount(instckVo);	// 입고내역 리스트 전체 수
		instckVo.setTotalRecordCount(selectInstckRecrdCount); //  입고내역 전체 수 셋팅	
		List<InstckValueObject> instckRecrdList = instckDao.instckRecrdList(instckVo);		
		return instckRecrdList;
	}
	
	/**
	* 입고거래장 목록을 조회하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/
	// 입고거래장 목록 조회하는 메소드
	public List<InstckValueObject> selectInstckExchngFlor(InstckValueObject instckVo) throws Exception{	
		int selectInstckCount = instckDao.selectInstckListCount(instckVo);	// 입고거래장 리스트 전체 수
		instckVo.setTotalRecordCount(selectInstckCount); // 입고거래장 리스트 전체 수 셋팅
		List<InstckValueObject> instckList = instckDao.selectInstckExchngFlor(instckVo);
		return instckList;
	}
	
	/**
	* 입고거래장등록(팝업)에 거래처명을 조회하는 메서드입니다.
	*
	* @param  
	* @return List<InstckValueObject> clintNmeList 
	* @exception  예외처리 상황을 적어주세요
	*/
	// 입고거래장 등록 팝업에 거래처명 조회하는 메서드
	public List<InstckValueObject> selectClintNme() throws Exception{	
		List<InstckValueObject> clintNmeList = instckDao.selectClintNme();
		return clintNmeList;
	}
	
	/**
	* 입고거래장을(팝업) 등록하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return 
	* @exception  예외처리 상황을 적어주세요
	*/
	// 입고거래장(팝업) 등록하는 메서드
	public void insertInstckExchngFlor(InstckValueObject instckVo) throws Exception{
		instckDao.insertInstckExchngFlor(instckVo);
	}
	
	/**
	* 입고거래장을(팝업) 수정하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return 
	* @exception  예외처리 상황을 적어주세요
	*/
	// 입고거래장을(팝업) 수정하는 메서드
	public void updateInstckExchngFlor(InstckValueObject instckVo) throws Exception{
		instckDao.updateInstckExchngFlor(instckVo);
	}
	
	/**
	* 입고거래장(팝업)을 삭제하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/
	// 입고거래장(팝업) 삭제하는 메서드
	public void deleteInstckExchngFlorPopup(InstckValueObject instckVo) throws Exception{
		instckDao.deleteInstckExchngFlorPopup(instckVo);
	}
	
	/**
	* 입고거래장을 체크해서 삭제하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/
	// 입고거래장 체크해서 삭제하는 메서드
	public void deleteInstckExchngFlor(InstckValueObject instckVo) throws Exception{
		int checkGroup[] = instckVo.getChangeGroup();
		List<Integer> groupSeq = new ArrayList<Integer>();
		for(int i=0; i<checkGroup.length; i++){
			groupSeq.add(checkGroup[i]);
		}
		instckDao.deleteInstckExchngFlor(groupSeq);
	}
	
	
	
	
	/**
		* 입고거래장 등록에서 (이미지 등록)을 하는 메서드입니다.
		* 
		* @param   MultipartHttpServletRequest request, OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void imgInsert(MultipartHttpServletRequest request, InstckValueObject instckVo) throws Exception  
	{
		Logger.info(null);	
		InstckServiceImpl instckServiceImpl = new InstckServiceImpl();
		Map<String, MultipartFile> files = request.getFileMap();
		CommonsMultipartFile instckExchngFlorImg1 = (CommonsMultipartFile) files.get("img1");
		CommonsMultipartFile instckExchngFlorImg2 = (CommonsMultipartFile) files.get("img2");
		CommonsMultipartFile instckExchngFlorImg3 = (CommonsMultipartFile) files.get("img3");
		ServletContext context = request.getSession().getServletContext();
//		int random = (int) (Math.random() * 100);
		TimeZone jst = TimeZone.getTimeZone ("JST");
		Calendar cal = Calendar.getInstance ( jst ); // 주어진 시간대에 맞게 현재 시각으로 초기화된 GregorianCalender 객체를 반환.// 또는 Calendar now = Calendar.getInstance(Locale.KOREA);
		String date = cal.get ( Calendar.YEAR ) + "년 " + ( cal.get ( Calendar.MONTH ) + 1 ) + "월 " + cal.get ( Calendar.DATE ) + "일_";
		System.out.println(date);
		
		
		if(instckExchngFlorImg1.getSize()!=0)
		{
			String filePath ="resources/image/instck/"+date+instckVo.getInstckExchngFlorTitl()+"/img1.jpg";
			String dirPath ="resources/image/instck/"+date+instckVo.getInstckExchngFlorTitl();
			System.out.println("사진 저장 절대경로 : "+context.getRealPath(filePath));
			instckServiceImpl.selProdctImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), instckExchngFlorImg1);
			instckVo.setInstckExchngFlorImg1(filePath);
		}
		
		if(instckExchngFlorImg2.getSize()!=0)
		{ 
			String filePath ="resources/image/instck/"+date+instckVo.getInstckExchngFlorTitl()+"/img2.jpg";
			String dirPath ="resources/image/instck/"+date+instckVo.getInstckExchngFlorTitl();
			System.out.println("사진 저장 절대경로 : "+context.getRealPath(filePath));
			instckServiceImpl.selProdctImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), instckExchngFlorImg2);
			instckVo.setInstckExchngFlorImg2(filePath);
		}

		if(instckExchngFlorImg3.getSize()!=0)
		{
			String filePath ="resources/image/instck/"+date+instckVo.getInstckExchngFlorTitl()+"/img3.jpg";
			String dirPath ="resources/image/instck/"+date+instckVo.getInstckExchngFlorTitl();
			System.out.println("사진 저장 절대경로 : "+context.getRealPath(filePath));
			instckServiceImpl.selProdctImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), instckExchngFlorImg3);
			instckVo.setInstckExchngFlorImg3(filePath);
		}

		instckDao.insertInstckExchngFlor(instckVo);
	}
	
	/**
		* 입고거래장 수정에서 (이미지 등록)을 하는 메서드입니다.
		* 
		* @param   MultipartHttpServletRequest request, OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void imgUpdate(MultipartHttpServletRequest request, InstckValueObject instckVo) throws Exception{
		Logger.info(null);	
		InstckServiceImpl instckServiceImpl = new InstckServiceImpl();
		Map<String, MultipartFile> files = request.getFileMap();
		CommonsMultipartFile instckExchngFlorImg1 = (CommonsMultipartFile) files.get("img1");
		CommonsMultipartFile instckExchngFlorImg2 = (CommonsMultipartFile) files.get("img2");
		CommonsMultipartFile instckExchngFlorImg3 = (CommonsMultipartFile) files.get("img3");
		ServletContext context = request.getSession().getServletContext();
//		int random = (int) (Math.random() * 100);
		TimeZone jst = TimeZone.getTimeZone ("JST");
		Calendar cal = Calendar.getInstance ( jst ); // 주어진 시간대에 맞게 현재 시각으로 초기화된 GregorianCalender 객체를 반환.// 또는 Calendar now = Calendar.getInstance(Locale.KOREA);
		String date = cal.get ( Calendar.YEAR ) + "년 " + ( cal.get ( Calendar.MONTH ) + 1 ) + "월 " + cal.get ( Calendar.DATE ) + "일_";
		System.out.println(date);
		
		
		if(instckExchngFlorImg1.getSize()!=0)
		{
			System.out.println("111111111111");
			String filePath ="resources/image/instck/"+date+instckVo.getInstckExchngFlorTitl()+"/img1.jpg";
			String dirPath ="resources/image/instck/"+date+instckVo.getInstckExchngFlorTitl();
			System.out.println("사진 저장 절대경로 : "+context.getRealPath(filePath));
			instckServiceImpl.selProdctImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), instckExchngFlorImg1);
			instckVo.setInstckExchngFlorImg1(filePath);
		}
		
		if(instckExchngFlorImg2.getSize()!=0)
		{ 
			System.out.println("22222222222");
			String filePath ="resources/image/instck/"+date+instckVo.getInstckExchngFlorTitl()+"/img2.jpg";
			String dirPath ="resources/image/instck/"+date+instckVo.getInstckExchngFlorTitl();
			System.out.println("사진 저장 절대경로 : "+context.getRealPath(filePath));
			instckServiceImpl.selProdctImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), instckExchngFlorImg2);
			instckVo.setInstckExchngFlorImg2(filePath);
		}

		if(instckExchngFlorImg3.getSize()!=0)
		{
			System.out.println("333333333333");
			String filePath ="resources/image/instck/"+date+instckVo.getInstckExchngFlorTitl()+"/img3.jpg";
			String dirPath ="resources/image/instck/"+date+instckVo.getInstckExchngFlorTitl();
			System.out.println("사진 저장 절대경로 : "+context.getRealPath(filePath));
			instckServiceImpl.selProdctImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), instckExchngFlorImg3);
			instckVo.setInstckExchngFlorImg3(filePath);
		}
		System.out.println(instckVo.getInstckExchngFlorImg1());
		instckDao.updateInstckExchngFlor(instckVo);
	}
	
	
	
	/**
		* 이미지 저장을 하는 메서드입니다.
		* 
		* @param   MultipartHttpServletRequest request, OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
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
		} 
		catch (Exception e) {
			System.out.println("실패: "+ e);
		}
	}
}
