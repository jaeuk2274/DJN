/**
 * 재고 관리 ServiceImpl 입니다.
 * 
 * @see   net.su.deal.controller.StckServiceImpl
 * @version  1.0 
 * @ author 하원식, 2016/08/27
 */
package net.su.deal.service;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.su.deal.dataAccessObject.InstckDataAccessObject;
import net.su.deal.dataAccessObject.StckDataAccessObject;
import net.su.deal.valueObject.InstckValueObject;
import net.su.deal.valueObject.StckValueObject;
import net.su.logger.Logger;
import net.su.prodct.valueObject.ProdctValueObject;



@Service
public class StckServiceImpl implements StckService{
	@Resource
	private StckDataAccessObject stckDao;
	
	
	
	/**
	* 상품의 재고 정보를  조회하는 메서드입니다.
	*
	* @param  StckValueObject stckValueObject
	* @return  List<StckValueObject>
	* @exception  Exception
	*/
	// 재고 목록을 조회하는 메서드
	public List<StckValueObject> stckList(StckValueObject stckValueObject) throws Exception
	{
		Logger.info(null);

		int stckCount = stckDao.stckCount(stckValueObject);	// 고객 리스트 전체 수

		System.out.println("selectCustmrCount : " + stckCount);
		stckValueObject.setTotalRecordCount(stckCount); // 고객 리스트 전체 수 셋팅
		
		List<StckValueObject> stckList = stckDao.stckList(stckValueObject);
	
		
		return stckList;
	}
	
	/**
	* 특정 상품들의 재고 량을 수정하는 메서드 입니다.
	*
	* @param  long[] prodctSeq, int[] qunt
	* @return  void
	* @exception  Exception
	*/
	public void stckUpdate(long[] prodctSeq, int[] qunt) throws Exception
	{
		Logger.info(null);
		
		for(int i = 0; i<prodctSeq.length; i++)
		{
			StckValueObject stckValueObject = new StckValueObject();
			stckValueObject.setProdctSeq(prodctSeq[i]);
			stckValueObject.setQunt(qunt[i]);
			stckValueObject.setStckType(1);
			System.out.println(prodctSeq[i]+"######"+qunt[i]);
			stckDao.stckUpdate(stckValueObject);
		}
	}
	
	/**
	/**
	* 특정 상품들의 반품 정보를 등록하고 반품된 상품 수만큼 재고량을 줄여주는 메서드입니다.
	*
	* @param  long[] prodctSeq, int[] qunt
	* @return  void
	* @exception  Exception
	*/
	public void stckRetrnUpdate(long[] prodctSeq, int[] qunt) throws Exception
	{
		Logger.info(null);
		for(int i = 0; i<prodctSeq.length; i++)
		{
			StckValueObject stckValueObject = new StckValueObject();
			stckValueObject.setProdctSeq(prodctSeq[i]);
			stckValueObject.setQunt(qunt[i]);
			stckValueObject.setStckType(2);
			System.out.println(prodctSeq[i]+"######"+qunt[i]);
			stckDao.retrnInsert(stckValueObject);
			stckDao.stckUpdate(stckValueObject);
		}
	}
	
	
	/**
	* 재고 정보를 일괄적으로 등록하는 메서드입니다.
	*
	* @param  long[] prodctSeq, int[] qunt
	* @return  void
	* @exception  Exception
	*/
	public void stckCreate(MultipartHttpServletRequest request, StckValueObject stckValueObject) throws Exception
	{
		Logger.info(null);
		// 엑셀 파일 등록
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
						flag++;
						if(flag==1)
						{
							stckValueObject.setProdctSeq((long)Double.parseDouble(value));
						}
						else if(flag==2)
						{
							stckValueObject.setQunt((int)Double.parseDouble(value));
							
							flag=0;
						}
					}
					
					if(!(value.equals("false")))// 조건 : value에 값이 false가 아닐때
					{
						
							System.out.println("value : " + value + "     a의 값 : "+a);
							a++;// sysout해보기 위함
						continue;
					}
				}
			}
			stckDao.stckInsert(stckValueObject);
		}// 첫번째 (밖에) for문 끝
		System.out.println("행 의 갯수                       "+rows);
		System.out.println("rowindex값         : "+rowindex);
		System.out.println("columnindex값            : "+columnindex);
	}

	
	/**
	* 특정 상품의 재고 정보를 다운로드하기 위해 조회하는 메서드입니다.
	*
	* @param  long[] prodctSeq
	* @return  void
	* @exception  Exception
	*/
	public List<StckValueObject> stckXlxList(long[] prodctSeq) throws Exception
	{
		Logger.info(null);
		List<StckValueObject> stckXlxList = stckDao.stckXlxList(prodctSeq);
		
		return stckXlxList;
	}
	
	
	/**
	* 특정 상품의 입고 내역 정보의 행의 수를 조회하는 메서드 입니다.
	*
	* @param  StckValueObject stckValueObject
	* @return  int
	* @exception  Exception
	*/
	public int instckRecrdCount(StckValueObject stckValueObject) throws Exception
	{
		Logger.info(null);
		int prodctCount = stckDao.instckRecrdCount(stckValueObject);
		
		return prodctCount;
	}
	
	/**
	* 특정 상품의 입고 내역 정보를 조회하는 메서드 입니다.
	*
	* @param  StckValueObject stckValueObject
	* @return  List<StckValueObject>
	* @exception  Exception
	*/	
	public List<StckValueObject> instckRecrdList(StckValueObject stckValueObject) throws Exception
	{
		Logger.info(null);
		
		List<StckValueObject> instckRecrdList = stckDao.instckRecrdList(stckValueObject);
		
		return instckRecrdList;
	}
}

