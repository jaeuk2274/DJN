package net.su.end.dataAccessObject;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.su.end.valueObject.TodyAnalValueObject;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 투데이리포트 Dao입니다.
 * 
 * @see   net.su.end.dataAccessObject.TodyAnalDataAccessObject
 * @version  1.0 
 * @ author 김대현, 2016/08/24
 */
@Repository
public class TodyAnalDataAccessObject extends SqlSessionDaoSupport {

	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 매출액을 조회 메서드입니다.
	*
	* @param   
	* @return  TodyAnalValueObject
	* @exception  예외처리 상황을 적어주세요
	*/
	// 매출액
	public TodyAnalValueObject selectTodyResult() throws Exception{
		TodyAnalValueObject selectTodyResult = getSqlSession().selectOne("todyAnalMapper.selectTodyResult");
		return selectTodyResult;
	}
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 전화주문 건수를 조회 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	// 전화주문건수
	public int telOrdrCount() throws Exception{
		int telOrdrCount = getSqlSession().selectOne("todyAnalMapper.telOrdrCount");
		return telOrdrCount;
	}
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 어플주문 건수를 조회 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	// 어플주문건수
	public int appOrdrCount() throws Exception{
		int appOrdrCount = getSqlSession().selectOne("todyAnalMapper.appOrdrCount");
		return appOrdrCount;
	}
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 주문취소 건수를 조회 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	// 주문취소건수
	public int ordrCancelCount() throws Exception{
		int ordrCancelCount = getSqlSession().selectOne("todyAnalMapper.ordrCancelCount");
		return ordrCancelCount;
	}
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 교환환불 건수를 조회 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	// 교환환불 건수
	public String exchngRefndCount() throws Exception{
		String exchngRefndCount = getSqlSession().selectOne("todyAnalMapper.exchngRefndCount");
		return exchngRefndCount;
		
	}
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 오늘 상품 판매량순위 top10을 조회 메서드입니다.
	*
	* @param   
	* @return  List<TodyAnalValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	// 오늘 상품 판매량순위 top 10
	public List<TodyAnalValueObject> todySalesList() throws Exception{
		List<TodyAnalValueObject> todySalesList = getSqlSession().selectList("todyAnalMapper.todySalesList");
		return todySalesList;
	}
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 급상승 판매상품순위 top10을 조회 메서드입니다.
	*
	* @param   
	* @return  List<TodyAnalValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	// 급상승 판매상품순위 top10
	public List<TodyAnalValueObject> surgeSelProdctList() throws Exception{
		List<TodyAnalValueObject> surgeSelProdctList = getSqlSession().selectList("todyAnalMapper.surgeSelProdctList");
		return surgeSelProdctList;
	}
	
	// 일일매출 그래프 (시간)
	public List<TodyAnalValueObject> salsTime() throws Exception{
		List<TodyAnalValueObject> salsTime = getSqlSession().selectList("todyAnalMapper.salsTime");
		return salsTime;
	}
	
	// 엑셀 업로드
	public void insertSalsPresnt(TodyAnalValueObject todyAnalVo, CommonsMultipartFile cmf) throws Exception{
		
//		FileInputStream fis = new FileInputStream(todyAnalVo.getExcelFile());// 엑셀파일 경로를 통해 해당 파일을 읽을 수 있도록 한다.
//		cmf.getInputStream();
		
		XSSFWorkbook workbook = new XSSFWorkbook(cmf.getInputStream());// xlsx파일을 출력하기 위함
		int rowindex = 0;// 행의 값을 담기위해 생성
		int columnindex = 0;// 컬럼 값을 담기위해 생성
//		long prodctSeq = 0;
		int a=0;// sysout해서 값 찍어보기위함
		XSSFSheet sheet = workbook.getSheetAt(0);// workbook에 있는 엑셀파일의 첫번째 시트를 가져옴


		//ArrayList<String> relsCellList = new ArrayList();// for문 돌려서 나온 value들을 배열에 담아 mapper에서 반복문 돌려 인설트 함

		int rows = sheet.getPhysicalNumberOfRows();// 엑셀 시트에 행의 갯수를 읽어 할당한다.

		System.out.println(rows + "   111111111111111111 rows값(행의 갯수, 매출현황은 201개)");// 총 행의 갯수 8개,  201개(매출현황)

		for(rowindex=1; rowindex<=rows-1; rowindex++){// 행의 갯수만큼 반복문을 돌린다. 총 201번 돌림, 0부터돌리면 엑셀에서 1행부터
			XSSFRow row = sheet.getRow(rowindex);// 시트에서 첫번째 행부터 마지막 행까지 반복해서 row에 담는다.
	
			ArrayList<String> relsCellList = new ArrayList();// 행마다 리스트에 담기위해 행마다 리스트를 생성함(출고 테이블에 인설트하는 list)
			ArrayList<String> retrnCellList = new ArrayList();	// 반품 테이블에 인설트하는 list
			ArrayList<String> stckCellList = new ArrayList();	// 재고 테이블 업데이트 list
			
//			if(row != null){// 조건 : 행에 널이 아닐 때
//				int cells = row.getPhysicalNumberOfCells();// 한개 행에 몇개의 셀에 값이 있는가?(널인거 제외 시킴)  4개, 매출현황은 30개
		
//				System.out.println(cells + "    1111111111111111111  cells 값(매출현황 30개)");
		
		

				for(columnindex=0; columnindex<=15; columnindex++){// 셀값을 읽는다.5번 돌림, 30번 돌림
//	출고테이블에 인설트할 데이터 추출
					if(columnindex==0 || columnindex==3 || columnindex==4 || columnindex==15){	// 컬럼인덱스가 등록할 엑셀파일의 컬럼번호를 뜻함 (출고 테이블에 인설트)
						System.out.println(columnindex+ " : 출고테이블 columnindex값");
						XSSFCell cell = row.getCell(columnindex);// 행의 특정 셀값을 가져와 담는다.
						String value = "";// 셀이 빈값일경우를 위한 널 체크, value에 각 셀의 값을 담는다.
						if(cell==null){// 조건 : cell에 값이 널일 때
							System.out.println(cell + "@@ cell 값이 널임");
							value = "0";// 아래 코드로 계속 진행됨
						}
						else{// 타입별로 내용 읽기
							switch(cell.getCellType()){// 엑셀에 셀의 타입이 있을 때
							case XSSFCell.CELL_TYPE_FORMULA:// 셀의 타입이 수식일경우와 수식으로 반환값이 문자, 숫자이면
								value = cell.getCellFormula();// 수식자체를 가져와서 value에 담는다.
								break;
							case XSSFCell.CELL_TYPE_NUMERIC:// 셀의 타입이 numeric일 때
								if(DateUtil.isCellDateFormatted(cell)){// 조건 : cell의 타입이 날짜이면
									Date date = cell.getDateCellValue();// date형태로 담는다.
									value = new SimpleDateFormat("yyyy-MM-dd").format(date);// date를 형식에 맞게 수정하여 value에 할당한다.
								}
								else{// 셀의 타입이 날짜가 아닐 때
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
						}
						if(!(value.equals("false")))// 조건 : value에 값이 false가 아닐때
						{
//							if(rowindex!=0){// 조건 : 위에서 for문을 돌릴 때 첫번째로 돌리는게 아닐 때  (컬럼부분은 DB에 등록을 안하기 위해서인듯)
								System.out.println("value : " + value + "     a의 값 : "+a);
								a++;// sysout해보기 위함
//	if()
								relsCellList.add(value);// 리스트에 value 값을 담는다.
								//System.out.println(relsCellList.get(columnindex) +  "                       relsCellList값");
//							}
				
				
					
							//System.out.println("0 : " + excelList.get(a));
							//a++;
					
				
							//getSqlSession().insert("mapper.excelInsert", a);
							
						}
						//System.out.println("셀 내용 : " + value);
						//getSqlSession().insert("mapper.excelInsert", value);
					}
					
				
//	반품테이블에 인설트할 데이터 추출
					if(columnindex==0 || columnindex==7){		// 컬럼인덱스가 등록할 엑셀파일의 컬럼번호를 뜻함 (반품 테이블에 인설트)
						System.out.println(columnindex+ " 반품 테이블 columnindex값");
						XSSFCell cell = row.getCell(columnindex);// 행의 특정 셀값을 가져와 담는다.
						String value = "";// 셀이 빈값일경우를 위한 널 체크, value에 각 셀의 값을 담는다.
						if(cell==null){// 조건 : cell에 값이 널일 때
							System.out.println(cell + "@@ cell 값이 널임");
							value = "0";// 아래 코드로 계속 진행됨
						}
						else{// 타입별로 내용 읽기
							switch(cell.getCellType()){// 엑셀에 셀의 타입이 있을 때
							case XSSFCell.CELL_TYPE_FORMULA:// 셀의 타입이 수식일경우와 수식으로 반환값이 문자, 숫자이면
								value = cell.getCellFormula();// 수식자체를 가져와서 value에 담는다.
								break;
							case XSSFCell.CELL_TYPE_NUMERIC:// 셀의 타입이 numeric일 때
								if(DateUtil.isCellDateFormatted(cell)){// 조건 : cell의 타입이 날짜이면
									Date date = cell.getDateCellValue();// date형태로 담는다.
									value = new SimpleDateFormat("yyyy-MM-dd").format(date);// date를 형식에 맞게 수정하여 value에 할당한다.
								}
								else{// 셀의 타입이 날짜가 아닐 때
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
						}
						if(!(value.equals("false")))// 조건 : value에 값이 false가 아닐때
						{
//							if(rowindex!=0){// 조건 : 위에서 for문을 돌릴 때 첫번째로 돌리는게 아닐 때  (컬럼부분은 DB에 등록을 안하기 위해서인듯)
								System.out.println("반품 value : " + value + "     a의 값 : "+a);
								a++;// sysout해보기 위함
//	if()
								retrnCellList.add(value);// 리스트에 value 값을 담는다.
								//System.out.println(relsCellList.get(columnindex) +  "                       relsCellList값");
//							}
				
				
					
							//System.out.println("0 : " + excelList.get(a));
							//a++;
					
				
							//getSqlSession().insert("mapper.excelInsert", a);
						}
//						if(columnindex==0)
//						{
//							prodctSeq= (long)Double.parseDouble(value);
//						}
						
					}
					
//	재고 테이블에 수량 업데이트					
					if(columnindex==0 || columnindex==4 || columnindex==7){		// 컬럼인덱스가 등록할 엑셀파일의 컬럼번호를 뜻함 (반품 테이블에 인설트)
						System.out.println(columnindex+ " 재고 테이블 columnindex값");
						XSSFCell cell = row.getCell(columnindex);// 행의 특정 셀값을 가져와 담는다.
						String value = "";// 셀이 빈값일경우를 위한 널 체크, value에 각 셀의 값을 담는다.
						if(cell==null){// 조건 : cell에 값이 널일 때
							System.out.println(cell + "재고 @@ cell 값이 널임");
							value = "0";// 아래 코드로 계속 진행됨
						}
						else{// 타입별로 내용 읽기
							switch(cell.getCellType()){// 엑셀에 셀의 타입이 있을 때
							case XSSFCell.CELL_TYPE_FORMULA:// 셀의 타입이 수식일경우와 수식으로 반환값이 문자, 숫자이면
								value = cell.getCellFormula();// 수식자체를 가져와서 value에 담는다.
								break;
							case XSSFCell.CELL_TYPE_NUMERIC:// 셀의 타입이 numeric일 때
								if(DateUtil.isCellDateFormatted(cell)){// 조건 : cell의 타입이 날짜이면
									Date date = cell.getDateCellValue();// date형태로 담는다.
									value = new SimpleDateFormat("yyyy-MM-dd").format(date);// date를 형식에 맞게 수정하여 value에 할당한다.
								}
								else{// 셀의 타입이 날짜가 아닐 때
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
						}
						if(!(value.equals("false")))// 조건 : value에 값이 false가 아닐때
						{
								System.out.println("재고 value : " + value + "     a의 값 : "+a);
								a++;	// sysout해보기 위함
								stckCellList.add(value);// 리스트에 value 값을 담는다.
								
				
				
						}

						
					}
					
				}// 두번째 (안에) for문 끝
		
		
//			}
//			if(rowindex!=0){// 조건 : 위에서 for문을 돌릴 때 첫번째로 돌리는게 아닐 때 (컬럼부분은 DB에 등록을 안하기 위해서인듯)
				System.out.println("11111111111111111111");
				Map excelMap = new HashMap();// 리스트를 담을 맵을 생성한다.
				excelMap.put("relsCellList", relsCellList);// relsCellList는 엑셀파일의 각 셀의 값들을(출고테이블 데이터) arrayList에 담은것이고 그것을 맵에 넣는다.
				excelMap.put("retrnCellList", retrnCellList);	// retrnCellList는 반품테이블 데이터를 담은것
//				excelMap.put("stckCellList", stckCellList);		// stckCellList는 재고테이블에 업데이트(반품수량, 출고수량을 재고에서 감소)
				System.out.println("@2222222222222222222");
				

				
				
				todyAnalVo.setProdctSeq(Long.parseLong(stckCellList.get(0)));
				todyAnalVo.setQunt(Integer.parseInt(stckCellList.get(1).replace(".0", "")));
				todyAnalVo.setRetrnQunt(Integer.parseInt(stckCellList.get(2).replace(".0", "")));
			
				System.out.println(todyAnalVo.getProdctSeq());
				System.out.println(todyAnalVo.getQunt());
				System.out.println(todyAnalVo.getRetrnQunt());

//				prodctSeq= (long)Double.parseDouble(value);
//				String e=stckCellList.get(1);
				
				
				
				getSqlSession().insert("todyAnalMapper.insertRels", excelMap);
				getSqlSession().insert("todyAnalMapper.insertRetrn", excelMap);
				getSqlSession().update("todyAnalMapper.updateStck", todyAnalVo);
//			}

		}// 첫번째 (밖에) for문 끝
		System.out.println("행 의 갯수                       "+rows);

		//Map excelMap = new HashMap();
		//excelMap.put("rows", rows);// rows는 엑셀파일의 행의 갯수(int형)
		//excelMap.put("relsCellList", relsCellList);// relsCellList는 엑셀파일의 각 셀의 값들을 arrayList에 담은것
		System.out.println("rowindex값         : "+rowindex);
		System.out.println("columnindex값            : "+columnindex);
		//for(int i=0; i<=rows-2; i++){
		//getSqlSession().insert("mapper.excelInsert", excelMap);
		//
		//}




		
		
		
		
		
		
		
		
		
	}
}
