package net.su.prodct.dataAccessObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.su.logger.Logger;
import net.su.prodct.valueObject.DebecFestivalValueObject;
import net.su.prodct.valueObject.ProdctFestivalValueObject;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Repository
public class DebecFestivalDataAccessObject extends SqlSessionDaoSupport {

	/**
    * 대백제 목록 조회 메서드입니다.
    *
    * @param   @RequestParam DebecFestivalValueObject
    * @return  debecFestivalList
    * @exception  Exception
    */
	public List<DebecFestivalValueObject> debecFestivalList(DebecFestivalValueObject debecFestivalValueObject) throws Exception {
		Logger.info(null);
		List<DebecFestivalValueObject> debecFestivalList = getSqlSession().selectList("debecFestivalMapper.debecFestivalList", debecFestivalValueObject);
		
		return debecFestivalList;
	}
	
	/**
    * 대백제 목록 카운트 조회 메서드입니다.
    *
    * @param   @RequestParam DebecFestivalValueObject
    * @return  selectCustmrCount
    * @exception  Exception
    */
	public int debecFestivalCountRead(DebecFestivalValueObject debecFestivalValueObject) throws Exception {
		Logger.info(null);
		int selectCustmrCount = getSqlSession().selectOne("debecFestivalMapper.debecFestivalCount", debecFestivalValueObject);	
		return selectCustmrCount;
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
		for (int i=0; i<data.length; i++){
			long schedlSeq = Long.parseLong(data[i]);
			getSqlSession().update("debecFestivalMapper.debecFestivalDelete", schedlSeq); //대백제 삭제1 (행사 테이블 삭제)
			getSqlSession().update("debecFestivalMapper.prodctTypeUpdate", schedlSeq); //대백제 삭제3 (상품 테이블 상품TYPE=0 수정)
			getSqlSession().delete("debecFestivalMapper.debecFestivalProdctDelete", schedlSeq); //대백제 삭제2 (행사브릿지 테이블 삭제)	

		}	
	}

	/**
    * 대백제 목록 단일 삭제 기능 메서드입니다.
    *
    * @param   @RequestParam int
    * @return  
    * @exception  Exception
    */
	public void debecFestivalSigleDelete(int schedlSeq) {
		Logger.info(null);
		getSqlSession().update("debecFestivalMapper.debecFestivalDelete", schedlSeq);	 //대백제 삭제1 (행사 테이블 삭제)
		getSqlSession().update("debecFestivalMapper.prodctTypeUpdate", schedlSeq); //대백제 삭제3 (상품 테이블 상품TYPE=0 수정)
		getSqlSession().delete("debecFestivalMapper.debecFestivalProdctDelete", schedlSeq); //대백제 삭제2 (행사브릿지 테이블 삭제)

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
		DebecFestivalValueObject debecFestivalValueObject = getSqlSession().selectOne("debecFestivalMapper.debecFestivalRead", schedlSeq);
		
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
		List<ProdctFestivalValueObject> debecFestivalProdctList = getSqlSession().selectList("debecFestivalMapper.debecFestivalProdctList", schedlSeq);
		
		return debecFestivalProdctList;
	}

	/**
    * 대백제 등록 STEP1 등록 기능 메서드입니다.
    *
    * @param   @RequestParam DebecFestivalValueObject
    * @return  
    * @exception  Exception
    */
	public void debecFestivalCreate(DebecFestivalValueObject debecFestivalValueObject) throws Exception {
		Logger.info(null);
		getSqlSession().insert("debecFestivalSchedlCreate", debecFestivalValueObject);
		getSqlSession().insert("debecFestivalEvntCreate", debecFestivalValueObject);
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
		List<ProdctFestivalValueObject> prodctTempList = getSqlSession().selectList("debecFestivalMapper.prodctTempList");	
		for (int i=0; i<prodctTempList.size(); i++){
			long prodctSeq = prodctTempList.get(i).getProdctSeq();
			prodctFestivalValueObject.setProdctSeq(prodctSeq);
			prodctFestivalValueObject.setProdctType(5);
			System.out.println(prodctSeq);
			System.out.println(prodctFestivalValueObject.getProdctType());
			getSqlSession().insert("debecFestivalMapper.debecFestivalProdctCreate", prodctSeq); //상품 등록
			getSqlSession().update("debecFestivalMapper.prodctTypeCreate", prodctFestivalValueObject); //상품 타입 수정
		}	
		
		int[] prodctSeq = prodctFestivalValueObject.getProdctSeq_arry();
		int[] discntRat = prodctFestivalValueObject.getDiscntRat_arry();
		for (int i=0; i<prodctSeq.length; i++){
			prodctFestivalValueObject.setProdctSeq(prodctSeq[i]);
			prodctFestivalValueObject.setEvntDiscntRat(discntRat[i]);
			
			getSqlSession().update("debecFestivalMapper.debecFestivalDiscntRatCreate", prodctFestivalValueObject); //상품 할인율 수정
		}
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
		for (int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			getSqlSession().delete("debecFestivalMapper.prodctDelete", prodctSeq);	
		}	
	}
	
	/**
    * 대백제 수정 화면 (하단 상품부분) 메서드입니다.
    *
    * @param   @RequestParam int
    * @return  debecFestivalProdctList
    * @exception  Exception
    */
	public void debecFestivalUpdateProdctList(int schedlSeq) throws Exception {
		Logger.info(null);
		getSqlSession().delete("debecFestivalMapper.prodctTempAllDelete");	
		List<ProdctFestivalValueObject> debecFestivalProdctList = getSqlSession().selectList("debecFestivalMapper.debecFestivalProdctList", schedlSeq); //등록되어있는 상품리스트 불러오기
		for (int i=0; i<debecFestivalProdctList.size(); i++){
			long prodctSeq = debecFestivalProdctList.get(i).getProdctSeq();
			getSqlSession().insert("debecFestivalMapper.prodctAdTempCreate", prodctSeq);	
		}	
	}
		
	/**
    * 대백제 수정 기능 메서드입니다.
    *
    * @param   @RequestParam DebecFestivalValueObject
    * @return  
    * @exception  Exception
    */
	public void debecFestivalUpdate(ProdctFestivalValueObject prodctFestivalValueObject) throws Exception {
		Logger.info(null);
		getSqlSession().update("debecFestivalMapper.debecFestivalSchedlUpdate", prodctFestivalValueObject); //일정 테이블 수정
		getSqlSession().update("debecFestivalMapper.debecFestivalEvntUpdate", prodctFestivalValueObject); // 행사 테이블 수정
		
		int[] prodctSeq = prodctFestivalValueObject.getProdctSeq_arry();
		int[] discntRat = prodctFestivalValueObject.getDiscntRat_arry();
		for (int i=0; i<prodctSeq.length; i++){
			prodctFestivalValueObject.setProdctSeq(prodctSeq[i]);
			prodctFestivalValueObject.setEvntDiscntRat(discntRat[i]);
			
			getSqlSession().update("debecFestivalMapper.debecFestivalProdctUpdate", prodctFestivalValueObject); // 상품 리스트 수정
		}
	}
	
	/**
    * 대백제 수정 상품수정 기능 메서드입니다.
    *
    * @param   @RequestParam MultipartHttpServletRequest, DebecFestivalValueObject
    * @return  
    * @exception  Exception
    */
	public void debecFestivaProdctlUpdate(int schedlSeq) throws Exception {
		ProdctFestivalValueObject prodctFestivalValueObject = new ProdctFestivalValueObject() ;

		List<ProdctFestivalValueObject> prodctTempList = getSqlSession().selectList("debecFestivalMapper.prodctTempList");	
		for (int i=0; i<prodctTempList.size(); i++){

			long prodctSeq = prodctTempList.get(i).getProdctSeq();
			prodctFestivalValueObject.setSchedlSeq(schedlSeq); //행사 SEQ 등록
			prodctFestivalValueObject.setProdctSeq(prodctSeq); //상품 SEQ 등록
			
			prodctFestivalValueObject.setProdctType(5); //상품 타입 등록
			System.out.println(prodctSeq);
			System.out.println(schedlSeq);
			System.out.println(prodctFestivalValueObject.getProdctType());
			getSqlSession().insert("debecFestivalMapper.debecFestivaProdctlUpdate", prodctFestivalValueObject); //상품 등록
			getSqlSession().update("debecFestivalMapper.prodctTypeCreate", prodctFestivalValueObject); //상품 타입 수정
			

		}
		prodctTempAllDelete();
		
	}
		
	/**
    * 대백제 수정 하단 상품리스트 일괄삭제 기능 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void debecFestivalUpdateDelete(String[] data) throws Exception {
		Logger.info(null);
		for (int i=0; i<data.length; i++){
			long evntBridgSeq = Long.parseLong(data[i]);
			getSqlSession().delete("debecFestivalMapper.debecFestivalUpdateDelete", evntBridgSeq);	
		}	
	}

	/**
    * 대백제 상품추가 테이블1 카운트 메서드입니다.
    *
    * @param   @RequestParam Model, HttpServletRequest, ProdctFestivalValueObject
    * @return  
    * @exception  Exception
    */
	public int prodctAddListCount(ProdctFestivalValueObject prodctFestivalValueObject) throws Exception {
		Logger.info(null);
		int prodctAddListCount = getSqlSession().selectOne("debecFestivalMapper.prodctAddListCount", prodctFestivalValueObject);	
		return prodctAddListCount;
	}

	/**
    * 대백제 상품추가 테이블1 생성 메서드입니다.
    *
    * @param   @RequestParam Model, HttpServletRequest, ProdctFestivalValueObject
    * @return  
    * @exception  Exception
    */
	public List<ProdctFestivalValueObject> prodctAddList(ProdctFestivalValueObject prodctFestivalValueObject) throws Exception{
		Logger.info(null);
		List<ProdctFestivalValueObject> prodctAdList = getSqlSession().selectList("debecFestivalMapper.prodctAddList", prodctFestivalValueObject);	
		return prodctAdList;
	}

	/**
    * 대백제 상품추가 테이블2 카운트 메서드입니다.
    *
    * @param   @RequestParam Model, HttpServletRequest, ProdctFestivalValueObject
    * @return  
    * @exception  Exception
    */
	public int prodctAdTempCount(ProdctFestivalValueObject prodctFestivalValueObject) throws Exception {
		Logger.info(null);
		int prodctAdTempCount = getSqlSession().selectOne("debecFestivalMapper.prodctAdTempCount", prodctFestivalValueObject);	
		return prodctAdTempCount;
	}
	
	/**
    * 대백제 상품추가 테이블2 생성 메서드입니다.
    *
    * @param   @RequestParam Model, HttpServletRequest, ProdctFestivalValueObject
    * @return  
    * @exception  Exception
    */
	public List<ProdctFestivalValueObject> prodctAdTempList(ProdctFestivalValueObject prodctFestivalValueObject) throws Exception {
		Logger.info(null);
		List<ProdctFestivalValueObject> prodctAdTempList = getSqlSession().selectList("debecFestivalMapper.prodctAdTempList", prodctFestivalValueObject);	
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
		List<ProdctFestivalValueObject> prodctTempList = getSqlSession().selectList("debecFestivalMapper.prodctTempList");	
		
		return prodctTempList;
	}
		
	/**
    * 상품 선택 추가 기능 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void prodctAdTempCreate(String[] data) throws Exception {
		Logger.info(null);
		for (int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			getSqlSession().insert("debecFestivalMapper.prodctAdTempCreate", prodctSeq);
		}	
	}
	
	/**
    * 상품 선택 제거 기능 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	public void prodctAddTempDelete(String[] data) throws Exception {
		Logger.info(null);
		for (int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			getSqlSession().delete("debecFestivalMapper.prodctAddTempDelete", prodctSeq);	
		}	
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
		getSqlSession().delete("debecFestivalMapper.prodctTempAllDelete");	
	}

	/**
	* 상품 정보로된 엑셀의 데이터를 읽어 일괄적으로 등록하는 메서드 입니다.
	*
	* @param   MultipartHttpServletRequest, ProdctFestivalValueObject
	* @return  ResponseBody Map<String, Object>
	* @exception  Exception
	*/	
	public void createXlsxUpload(ProdctFestivalValueObject prodctFestivalValueObject) throws Exception {
		Logger.info(null);
		getSqlSession().insert("debecFestivalMapper.createXlsxUpload", prodctFestivalValueObject);
		
	}

}
