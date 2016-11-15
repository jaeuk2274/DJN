package net.su.market.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import net.su.logger.Logger;
import net.su.market.dataAccessObject.EmpDataAccessObject;
import net.su.market.valueObject.EmpValueObject;
import net.su.security.Base64Utils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 직원관리 메뉴 관련 서비스 인터페이스입니다.
 * 
 * @see   net.su.market.service.EmpServiceImpl
 * @version  1.0 
 * @ author 이인호, 2016/08/09
 */

@Service
public class EmpServiceImpl implements EmpService {

	@Resource
	private EmpDataAccessObject EmpDAO;
	
	Base64Utils base64 = new Base64Utils();
	String encryptKey = "temp11111111111111111111";
	
	/**
	 * 직원관리 직원조회로 이동하는 메서드입니다.
	 *
	 * @param   EmpValueObject  
	 * @return  List<EmpValueObject>
	 * @exception  Exception
	 */
	
	public List<EmpValueObject> selectEmpList(EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		int selectEmpCount = EmpDAO.selectEmpCount(empValueObject);	// 직원 리스트 전체 수
		empValueObject.setTotalRecordCount(selectEmpCount);
		
		List<EmpValueObject> selectEmpList = EmpDAO.selectEmpList(empValueObject);
		return selectEmpList;
	}
	
	/**
	 * 직원관리 직원 상세조회로 이동하는 메서드입니다.
	 *
	 * @param   int 
	 * @return  List<EmpValueObject>
	 * @exception  Exception
	 */
	
	public List<EmpValueObject> selectEmpRead(int empSeq) throws Exception {
		Logger.info(null);
		List<EmpValueObject> selectEmpRead = EmpDAO.selectEmpRead(empSeq);
		return selectEmpRead;
	}
	
	/**
	 * 직원관리 직원 등록하는 메서드입니다.
	 *
	 * @param   MultipartHttpServletRequest, EmpValueObject 
	 * @return  void
	 * @exception  Exception
	 */
	
	@Transactional
	public void empCreate(MultipartHttpServletRequest multiRequest, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
				
		String EN_ORG_FG = base64.encrypt(empValueObject.getPw(), encryptKey);
		empValueObject.setPw(EN_ORG_FG);
		
		EmpServiceImpl empServiceImpl = new EmpServiceImpl();

		
		Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		CommonsMultipartFile getMainCmf = (CommonsMultipartFile) files.get("empImg");
		
		ServletContext context = multiRequest.getSession().getServletContext();
		
		int empSeq= EmpDAO.maxEmpSeq();
		
		if(getMainCmf.getSize()!=0)
		{
			String filePath ="resources/image/emp/"+empSeq+"/empImg.jpg";
			String dirPath ="resources/image/emp/"+empSeq;
			empServiceImpl.empImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), getMainCmf);
			empValueObject.setImgRot(filePath);
		}	
		EmpDAO.empCreate(empValueObject);
	}
	
	public void empImgSave(String filePath, String dirPath, CommonsMultipartFile cmf) throws Exception
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
	 * 직원관리 직원정보 수정하는 메서드입니다.
	 *
	 * @param   EmpValueObject 
	 * @return  void
	 * @exception  Exception
	 */
	
	public void empUpdate(MultipartHttpServletRequest multiRequest, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		
		EmpServiceImpl empServiceImpl = new EmpServiceImpl();
		
		Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		CommonsMultipartFile getMainCmf = (CommonsMultipartFile) files.get("empImg");
		
		ServletContext context = multiRequest.getSession().getServletContext();
		
		if(getMainCmf.getSize()!=0)
		{
			String filePath ="resources/image/emp/"+empValueObject.getEmpSeq()+"/empImg.jpg";
			String dirPath ="resources/image/emp/"+empValueObject.getEmpSeq();
			empServiceImpl.empImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), getMainCmf);
			empValueObject.setImgRot(filePath);
		}	
		
		EmpDAO.empUpdate(empValueObject);
	}
	
	/**
	 * 직원관리 직원 개인정보 수정하는 메서드입니다.
	 *
	 * @param   EmpValueObject 
	 * @return  void
	 * @exception  Exception
	 */
	
	public void empPersnlUpdate(MultipartHttpServletRequest multiRequest, EmpValueObject empValueObject) throws Exception{
		Logger.info(null);
		
		EmpServiceImpl empServiceImpl = new EmpServiceImpl();
		
		Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		CommonsMultipartFile getMainCmf = (CommonsMultipartFile) files.get("empImg");
		
		ServletContext context = multiRequest.getSession().getServletContext();
		
		if(getMainCmf.getSize()!=0)
		{
			int empSeq= EmpDAO.maxEmpSeq();
			String filePath ="resources/image/emp/"+empSeq+"/empImg.jpg";
			String dirPath ="resources/image/emp/"+empSeq;
			empServiceImpl.empImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), getMainCmf);
			empValueObject.setImgRot(filePath);
		}	
			
		EmpDAO.empPersnlUpdate(empValueObject);
	}
	
	/**
	 * 직원관리 직원 정보 삭제하는 메서드입니다.
	 *
	 * @param   EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	public void empDelete(EmpValueObject empValueObject) throws Exception{
		Logger.info(null);
		EmpDAO.empDelete(empValueObject);
	}
	
	/**
	 * 직원관리 직원 비밀번호를 수정하는 메서드입니다.
	 *
	 * @param   EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */
	
	public void empPWUpdate(EmpValueObject empValueObject) throws Exception{
		Logger.info(null);
		
		String EN_ORG_FG = base64.encrypt(empValueObject.getPw(), encryptKey);
		empValueObject.setPw(EN_ORG_FG);
		EmpDAO.empPWUpdate(empValueObject);
	}
} 
