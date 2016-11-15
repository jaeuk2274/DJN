package net.su.prodct.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.su.logger.Logger;
import net.su.prodct.dataAccessObject.DebecFestivalDataAccessObject;
import net.su.prodct.valueObject.DebecFestivalValueObject;
import net.su.prodct.valueObject.ProdctFestivalValueObject;

@Service
public class DebecFestivalServiceImpl implements DebecFestivalService {
	
	@Resource
	 DebecFestivalDataAccessObject  DebecFestivalcDataAccessObject;

	/**
    * 대백제 목록 조회 메서드입니다.
    *
    * @param   @RequestParam DebecFestivalValueObject
    * @return  debecFestivalList
    * @exception  Exception
    */
	public List<DebecFestivalValueObject> debecFestivalList(DebecFestivalValueObject debecFestivalValueObject) throws Exception {
		Logger.info(null);
		int debecFestivalCountRead = DebecFestivalcDataAccessObject.debecFestivalCountRead(debecFestivalValueObject);	// 행사 리스트 전체 수 (페이징)
		debecFestivalValueObject.setTotalRecordCount(debecFestivalCountRead); // 행사 리스트 전체 수 셋팅 (페이징)
		List<DebecFestivalValueObject> debecFestivalList = DebecFestivalcDataAccessObject.debecFestivalList(debecFestivalValueObject);
		
		return debecFestivalList;
	}
	
	/**
	* 대백제 목록 일괄 삭제 기능 메서드입니다.
    *
    * @param   @RequestParam String[]
    * @return  
    * @exception  Exception
    */
	public void debecFestivalDelete(String[] data) throws Exception {
		Logger.info(null);
		DebecFestivalcDataAccessObject.debecFestivalDelete(data);
	}
	
	/**
    * 대백제 목록 단일 삭제 기능 메서드입니다.
    *
    * @param   @RequestParam int
    * @return  
    * @exception  Exception
    */
	public void debecFestivalSigleDelete(int schedlSeq) throws Exception {
		Logger.info(null);
		DebecFestivalcDataAccessObject.debecFestivalSigleDelete(schedlSeq);
	}
	
	/**
    * 대백제 상세보기 (상단 행사부분) 메서드입니다.
    *
    * @param   @RequestParam DebecFestivalValueObject
    * @return  debecFestivalValueObject
    * @exception  Exception
    */
	public DebecFestivalValueObject debecFestivalRead(int schedlSeq) throws Exception {
		Logger.info(null);
		DebecFestivalValueObject debecFestivalValueObject = DebecFestivalcDataAccessObject.debecFestivalRead(schedlSeq);
		
		return debecFestivalValueObject;
	}
		
	/**
    * 대백제 상세보기 (하단 상품부분) 메서드입니다.
    *
    * @param   @RequestParam int
    * @return  debecFestivalProdctList
    * @exception  Exception
    */
	public List<ProdctFestivalValueObject> debecFestivalProdctList(int schedlSeq) throws Exception {
		Logger.info(null);
		List<ProdctFestivalValueObject> debecFestivalProdctList = DebecFestivalcDataAccessObject.debecFestivalProdctList(schedlSeq);
		return debecFestivalProdctList;
	}
	
	/**
    * 대백제 등록 STEP1 등록 기능 메서드입니다.
    *
    * @param   @RequestParam DebecFestivalValueObject
    * @return  
    * @exception  Exception
    */
	public void debecFestivalCreate(MultipartHttpServletRequest request, DebecFestivalValueObject debecFestivalValueObject) throws Exception {
		Logger.info(null);
		DebecFestivalServiceImpl debecFestivalServiceImpl = new DebecFestivalServiceImpl();

		Map<String, MultipartFile> files = request.getFileMap();
		
		CommonsMultipartFile getEvntImgCmf = (CommonsMultipartFile) files.get("evntImgFile");
		ServletContext context = request.getSession().getServletContext();

		if(getEvntImgCmf!=null)
		{
			String filePath ="resources/image/evnt/"+debecFestivalValueObject.getSchedlSeq()+"/evntImg.jpg";
			String dirPath ="resources/image/evnt/"+debecFestivalValueObject.getSchedlSeq();
			debecFestivalValueObject.setEvntImg(filePath);
			debecFestivalServiceImpl.evntImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), getEvntImgCmf);
		}
		DebecFestivalcDataAccessObject.debecFestivalCreate(debecFestivalValueObject);
	}
	
	// 배너이미지 저장 메서드
	public void evntImgSave(String filePath, String dirPath, CommonsMultipartFile cmf) throws Exception
	{
		Logger.info(null);
		File fileSaver = new File(filePath);
		File dirSaver = new File(dirPath);
		// 파일 업로드 처리 완료.
		try {
			if(!fileSaver.exists())
			{
				System.out.println("가나요?");
				dirSaver.mkdir();
				System.out.println("가냐구여");
			}
			cmf.transferTo(fileSaver);
			System.out.println("성공");
		} catch (Exception e) {
			System.out.println("실패: "+ e);
		}
	}
	
	/**
    * 대백제 등록 STEP2 등록 기능 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void prodctCreate(ProdctFestivalValueObject prodctFestivalValueObject) throws Exception {
		Logger.info(null);
		DebecFestivalcDataAccessObject.prodctCreate(prodctFestivalValueObject);
	}
		
	/**
    * 대백제 등록 STEP2 상품리스트 일괄삭제 기능 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void prodctDelete(String[] data) throws Exception {
		Logger.info(null);
		DebecFestivalcDataAccessObject.prodctDelete(data);
	}
		
	/**
	* 대백제 수정화면 전환시 상품리스트를 임시테이블에 추가하는 메서드입니다.
	*
	* @param   MultipartHttpServletRequest, ProdctFestivalValueObject
	* @return  ResponseBody Map<String, Object>
	* @exception  Exception
	*/	
	public void debecFestivalUpdateProdctList(int schedlSeq) throws Exception {
		Logger.info(null);
		DebecFestivalcDataAccessObject.debecFestivalUpdateProdctList(schedlSeq);
	}
	
	/**
    * 대백제 수정 기능 메서드입니다.
    *
    * @param   @RequestParam MultipartHttpServletRequest, DebecFestivalValueObject
    * @return  
    * @exception  Exception
    */
	public void debecFestivalUpdate(ProdctFestivalValueObject prodctFestivalValueObject, MultipartHttpServletRequest request) throws Exception {
		Logger.info(null);
		DebecFestivalServiceImpl debecFestivalServiceImpl = new DebecFestivalServiceImpl();

		Map<String, MultipartFile> files = request.getFileMap();
		
		CommonsMultipartFile getEvntImgCmf = (CommonsMultipartFile) files.get("evntImgFile");
		ServletContext context = request.getSession().getServletContext();
		
		if(getEvntImgCmf!=null)
		{
			String filePath ="resources/image/evnt/"+prodctFestivalValueObject.getSchedlSeq()+"/evntImg.jpg";
			String dirPath ="resources/image/evnt/"+prodctFestivalValueObject.getSchedlSeq();
			prodctFestivalValueObject.setEvntImg(filePath);
			
			debecFestivalServiceImpl.evntImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), getEvntImgCmf);
		}	
		DebecFestivalcDataAccessObject.debecFestivalUpdate(prodctFestivalValueObject);
	};
	
	/**
    * 대백제 수정 상품수정 기능 메서드입니다.
    *
    * @param   @RequestParam MultipartHttpServletRequest, DebecFestivalValueObject
    * @return  
    * @exception  Exception
    */
	public void debecFestivaProdctlUpdate(int schedlSeq) throws Exception {
		Logger.info(null);
		DebecFestivalcDataAccessObject.debecFestivaProdctlUpdate(schedlSeq);
	}
	
	/**
    * 대백제 수정 하단 상품리스트 일괄삭제 기능 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void debecFestivalUpdateDelete(String[] data)throws Exception {
		Logger.info(null);
		DebecFestivalcDataAccessObject.debecFestivalUpdateDelete(data);
	}
	
	/**
    * 대백제 상품추가 테이블1 생성 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public List<ProdctFestivalValueObject> prodctAddList(ProdctFestivalValueObject prodctFestivalValueObject) throws Exception {
		Logger.info(null);
		int prodctAddListCount = DebecFestivalcDataAccessObject.prodctAddListCount(prodctFestivalValueObject); //임시테이블에 없고 판매중이고 재고 있는 상품카운트
		System.out.println("상품테이블 상품 수 = " + prodctAddListCount);
		prodctFestivalValueObject.setTotalRecordCount(prodctAddListCount);//VO 전체 카운트 담아줌
		List<ProdctFestivalValueObject> prodctAdList = DebecFestivalcDataAccessObject.prodctAddList(prodctFestivalValueObject);
		return prodctAdList;
	}
	
	/**
    * 대백제 상품추가 테이블2 생성 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public List<ProdctFestivalValueObject> prodctAddTempList(ProdctFestivalValueObject prodctFestivalValueObject) throws Exception {
		Logger.info(null);
		int prodctAdTempCount = DebecFestivalcDataAccessObject.prodctAdTempCount(prodctFestivalValueObject);
		System.out.println("임시테이블 상품 수 = " + prodctAdTempCount);
		prodctFestivalValueObject.setTotalRecordCount(prodctAdTempCount);
		List<ProdctFestivalValueObject> prodctAdTempList = DebecFestivalcDataAccessObject.prodctAdTempList(prodctFestivalValueObject);
		return prodctAdTempList;
	}

	/**
    * 대백제 상품추가 테이블2 조회 메서드입니다.
    *
    * @param   @RequestParam 
    * @return  
    * @exception  Exception
    */
	public List<ProdctFestivalValueObject> prodctTempList() throws Exception {
		Logger.info(null);
		List<ProdctFestivalValueObject> prodctTempList = DebecFestivalcDataAccessObject.prodctTempList();
		
		return prodctTempList;
	}
		
	/**
    * 상품 선택 추가 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void prodctAdTempCreate(String[] data) throws Exception {
		Logger.info(null);
		DebecFestivalcDataAccessObject.prodctAdTempCreate(data);
	}
	
	/**
    * 상품 선택 제거 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void prodctAddTempDelete(String[] data) throws Exception {
		Logger.info(null);
		DebecFestivalcDataAccessObject.prodctAddTempDelete(data);
	}
	
	/**
    * 대백제 상품추가 테이블2 전체 삭제 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void prodctTempAllDelete() throws Exception {
		Logger.info(null);
		DebecFestivalcDataAccessObject.prodctTempAllDelete();
	}
	
	/**
	* 상품 정보로된 엑셀의 데이터를 읽어 일괄적으로 등록하는 메서드 입니다.
	*
	* @param   MultipartHttpServletRequest, ProdctFestivalValueObject
	* @return  ResponseBody Map<String, Object>
	* @exception  Exception
	*/	
	public void createXlsxUpload(MultipartHttpServletRequest request, ProdctFestivalValueObject prodctFestivalValueObject) throws Exception {
		Logger.info(null);
		
		Map<String, MultipartFile> files = request.getFileMap();	
		CommonsMultipartFile xlsxFile = (CommonsMultipartFile) files.get("xlsxFile");
		XSSFWorkbook workbook = new XSSFWorkbook(xlsxFile.getInputStream());// xlsx파일을 출력하기 위함
		
		int rowindex = 0;// 행의 값을 담기위해 생성
		int columnindex = 0;// 컬럼 값을 담기위해 생성

		int a=0;// sysout해서 값 찍어보기위함
		XSSFSheet sheet = workbook.getSheetAt(0);// workbook에 있는 엑셀파일의 첫번째 시트를 가져옴
		int rows = sheet.getPhysicalNumberOfRows();// 엑셀 시트에 행의 갯수를 읽어 할당한다.

		System.out.println(rows + "   111111111111111111 rows값");// 총 행의 갯수 8개, power=6개
		int flag = 0;
		for(rowindex=1; rowindex<rows; rowindex++)
		{// 행의 갯수만큼 반복문을 돌린다. 총 8번 돌림
			XSSFRow row = sheet.getRow(rowindex);// 시트에서 첫번째 행부터 마지막 행까지 반복해서 row에 담는다.
	
	
			if(row != null){// 조건 : 행에 널이 아닐 때
				int cells = row.getPhysicalNumberOfCells();// 한개 행에 몇개의 셀에 값이 있는가?  4개, power=2개	
				System.out.println(cells + "    1111111111111111111  cells 값");
				
				for(columnindex=0; columnindex<=cells; columnindex++)
				{// 셀값을 읽는다.5번 돌림, power=3번 돌림
					XSSFCell cell = row.getCell(columnindex);// 행의 특정 셀값을 가져와 담는다.
					String value = "";// 셀이 빈값일경우를 위한 널 체크, value에 각 셀의 값을 담는다.
					if(cell==null) // 조건 : cell에 값이 널일 때
					{
						continue;// 아래 코드로 계속 진행됨
					}
					else
					{// 타입별로 내용 읽기
						switch(cell.getCellType())// 엑셀에 셀의 타입이 있을 때
						{
							case XSSFCell.CELL_TYPE_FORMULA:// 셀의 타입이 수식일경우와 수식으로 반환값이 문자, 숫자이면
								value = cell.getCellFormula();// 수식자체를 가져와서 value에 담는다.
							break;
							case XSSFCell.CELL_TYPE_NUMERIC:// 셀의 타입이 numeric일 때
								if(DateUtil.isCellDateFormatted(cell))// 조건 : cell의 타입이 날짜이면
								{
									Date date = cell.getDateCellValue();// date형태로 담는다.
									value = new SimpleDateFormat("yyyy-MM-dd").format(date);// date를 형식에 맞게 수정하여 value에 할당한다.
								}
								else// 셀의 타입이 날짜가 아닐 때
								{
									value = cell.getNumericCellValue()+"";// 셀에 적힌 숫자만 가져와 담는다.
								}
							break;
							case XSSFCell.CELL_TYPE_STRING:// 셀의 타입이 일반이면
							value = cell.getStringCellValue()+"";// 그대로 담는다.
							break;
							case XSSFCell.CELL_TYPE_BLANK:// 셀의 타입이 없으면
							value = cell.getBooleanCellValue()+"";// 논리값으로 담는다.
							break;
							case XSSFCell.CELL_TYPE_ERROR:// 셀의 타입이 에러 (수식결과가 에러일 때 인거 같음)
							value = cell.getErrorCellValue()+"";// 그대로 담는다.
							break;
							
						}
						System.out.println(value);
						prodctFestivalValueObject.setProdctSeq((long)Double.parseDouble(value));

					}
					
					if(!(value.equals("false")))// 조건 : value에 값이 false가 아닐때
					{	
							System.out.println("value : " + value + "     a의 값 : "+a);
							a++;// sysout해보기 위함
						continue;
					}
				}
			}
			DebecFestivalcDataAccessObject.createXlsxUpload(prodctFestivalValueObject);
		}// 첫번째 (밖에) for문 끝
		System.out.println("행 의 갯수                       "+rows);
		System.out.println("rowindex값         : "+rowindex);
		System.out.println("columnindex값            : "+columnindex);
	}
}
