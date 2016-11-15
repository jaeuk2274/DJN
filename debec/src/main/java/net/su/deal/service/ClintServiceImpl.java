
package net.su.deal.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.su.deal.dataAccessObject.ClintDataAccessObject;
import net.su.deal.valueObject.ClintValueObject;

/**
 * 거래처관리 서비스임플입니다.
 * 
 * @see   net.su.deal.service.ClintServiceImpl
 * @version  1.0 
 * @ author 김대현, 2016/08/09
 */
@Service
public class ClintServiceImpl implements ClintService{
	@Resource
	private ClintDataAccessObject clintDao;
	
	/**
	* 거래처관리 거래처 목록 조회를 하는 메서드입니다.
	*
	* @param   ClintValueObject clintVo
	* @return  List
	* @exception  예외처리 상황을 적어주세요
	*/
	// 거래처 목록을 조회하는 메소드
	public List<ClintValueObject> clintList(ClintValueObject clintVo) throws Exception{
		
		int selectClintCount = clintDao.selectClintCount(clintVo);	// 거래처 리스트 전체 수
		
		System.out.println("selectCustmrCount : " + selectClintCount);
		
		clintVo.setTotalRecordCount(selectClintCount); // 거래처 리스트 전체 수 셋팅
		
		List<ClintValueObject> clintList = clintDao.clintList(clintVo);
		
		return clintList;
	}
	
	/**
	* 거래처관리 거래처 상세보기를 하는 메서드입니다.
	*
	* @param   ClintValueObject clintVo
	* @return  ClintValueObject
	* @exception  예외처리 상황을 적어주세요
	*/
	// 거래처 정보를 상세조회하는 메소드
	public ClintValueObject clintRead(int clintSeq) throws Exception{
		
//		int selectClintCount = clintDao.selectClintCount(clintVo);	// 거래처 리스트 전체 수
//		
//		System.out.println("selectCustmrCount : " + selectClintCount);
//		
//		clintVo.setTotalRecordCount(selectClintCount); // 거래처 리스트 전체 수 셋팅
		
		ClintValueObject clintVo=clintDao.clintRead(clintSeq);
		return clintVo;
	}
	
	/**
	* 거래처관리 거래처 상세보기에서 거래처를 삭제하는 메서드입니다.
	*
	* @param   int clintSeq
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 거래처 상세조회에서 해당 거래처 삭제하는 메소드
	public void clintReadDelete(int clintSeq) throws Exception{
		
		clintDao.clintReadDelete(clintSeq);
	}
	
	/**
	* 거래처관리 거래처 수정화면에서 거래처를 수정하는 메서드입니다.
	*
	* @param   int clintSeq
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 거래처 수정
	public void clintUpdate(ClintValueObject clintVo) throws Exception{
		
		clintDao.clintUpdate(clintVo);
	}
	
	/**
	* 거래처관리 상품추가모달에서 거래처가 미등록된 상품을 조회하는 메서드입니다.
	*
	* @param 
	* @return  SelectProdctAdList
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 상품추가 모달에서 거래처가 미등록된 상품 조회
	public List<ClintValueObject> selectProdctAdList(ClintValueObject clintVo) throws Exception{
		
		int selectProdctCount = clintDao.selectProdctCount(clintVo);	// 거래처가 미등록된 상품 리스트 전체 수
		clintVo.setTotalRecordCount(selectProdctCount); // 거래처가 미등록된 상품 리스트 리스트 전체 수 셋팅

		List<ClintValueObject> selectProdctAdList = clintDao.selectProdctAdList(clintVo);
		return selectProdctAdList;
	}
	
	/**
	* 거래처관리 상품추가 모달에서 임시테이블 상품 조회하는 메서드입니다.
	*
	* @param 
	* @return  selectProdctAdTempList
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 상품추가 모달에서 임시테이블 상품 조회(오른쪽 테이블)
	public List<ClintValueObject> selectProdctAdTempList(ClintValueObject clintVo) throws Exception{
		
		int selectProdctTempCount = clintDao.selectProdctTempCount(clintVo);
		clintVo.setTotalRecordCount(selectProdctTempCount);
		
		List<ClintValueObject> selectProdctAdTempList = clintDao.selectProdctAdTempList(clintVo);
		return selectProdctAdTempList;
	}
	
	/**
	* 거래처관리 상품추가모달에서 임시테이블에 상품을 추가하는 메서드입니다.
	*
	* @param String[] data
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/
	// 상품추가 모달에서 임시테이블에 상품추가
	public void prodctAddTempTableCreate(String[] data) throws Exception{		
		clintDao.prodctAddTempTableCreate(data);
	}

	/**
	* 거래처관리 상품추가모달에서 임시테이블에 상품을 삭제하는 메서드입니다.
	*
	* @param String[] data
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/
	// 상품추가 모달에서 임시테이블에 상품삭제
	public void prodctAddTempTableDelete(String[] data) throws Exception{
		
		clintDao.prodctAddTempTableDelete(data);
	}
	
	
	/**
	* 임시테이블 상품 삭제 메서드
	*
	* @param 
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 임시테이블 상품 삭제 메서드
	public void deleteTempTable() throws Exception{
		
		clintDao.deleteTempTable();
	}

	/**
	* 임시테이블 상품을 DB에 최종 등록
	*
	* @param String[] data
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
//	 임시테이블 상품을 DB에 최종 등록
	public void insertProdct(String[] data, int clintSeq) throws Exception{
		clintDao.insertProdct(data, clintSeq);
	}
	
	/**
	* 거래처 상세보기에 상품을 추가하는 메서드입니다.
	*
	* @param ClintValueObject clintVo
	* @return  List<ClintValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/	
// 거래처 상세보기에 상품 추가
	public List<ClintValueObject> prodctList(ClintValueObject clintVo) throws Exception{
		
//		int selectClintCount = clintDao.selectClintCount(clintVo);	// 거래처 리스트 전체 수
//		
//		System.out.println("selectCustmrCount : " + selectClintCount);
//		
//		clintVo.setTotalRecordCount(selectClintCount); // 거래처 리스트 전체 수 셋팅	
		
		int selectProdctCount = clintDao.selectProdctCountPaging(clintVo);	// 상품 리스트 전체 수
		System.out.println("selectCustmrCount : " + selectProdctCount);
//		ClintValueObject clintProductVo = new ClintValueObject();
		clintVo.setTotalRecordCount(selectProdctCount); // 상품 리스트 전체 수 셋팅
		
		clintVo.setClintSeq(clintVo.getClintSeq());
		List<ClintValueObject> prodctList = clintDao.prodctList(clintVo);
		
		return prodctList;
	}
	
	/**
	* 거래처를 등록하는 메서드입니다.
	*
	* @param ClintValueObject clintVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	public int insertClint(ClintValueObject clintVo) throws Exception{
		int insertClintSeq = clintDao.insertClint(clintVo);
		return insertClintSeq;
	}
	
	/**
	* 거래처를 체크해서 삭제하는 메서드입니다.
	*
	* @param ClintValueObject clintVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	public void deleteClint(ClintValueObject clintVo) throws Exception{
		int checkGroup[] = clintVo.getChangeGroup();
		List<Integer> groupSeq = new ArrayList<Integer>();
		for(int i=0; i<checkGroup.length; i++){
			groupSeq.add(checkGroup[i]);
		}
		clintDao.deleteClint(groupSeq);
	}
	
	/**
	* 상품추가 팝업창에서 완료를 눌렀을 때, 임시테이블에 있는 상품들의 seq를 가져오는 메소드.
	*
	* @param   ClintValueObject clintVo
	* @return  String[]
	* @exception  예외처리 상황을 적어주세요
	*/	
	public String[] selectTempProdct(ClintValueObject clintVo) throws Exception{
		String[] data = clintDao.selectTempProdct(clintVo);
		return data;
	}
	

	/**
	* 거래처 상세보기에서 거래처 상품들 거래처 일괄 변경 하는 메소드
	*
	* @param   ClintValueObject clintVo
	* @return  void
	* @exception  예외처리 상황을 적어주세요
	*/	
	public void ClintProductUpdate(ClintValueObject clintVo) throws Exception{
		clintDao.ClintProductUpdate(clintVo);
	}
	
	
	/**
	* 거래처 상세보기에서 거래처 상품들 일괄 삭제하는 메소드
	*
	* @param   ClintValueObject clintVo
	* @return  void
	* @exception  예외처리 상황을 적어주세요
	*/	
	public void ClintProductDelete(ClintValueObject clintVo) throws Exception{
		clintDao.ClintProductDelete(clintVo);
	}
}
