/**
 * 거래처관리 dao입니다.
 * 
 * @see   net.su.deal.dataAccessObject.ClintDataAccessObject
 * @version  1.0 
 * @ author 김대현, 2016/08/09
 */
package net.su.deal.dataAccessObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.su.deal.valueObject.ClintValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class ClintDataAccessObject extends SqlSessionDaoSupport{
	
	/**
	* 거래처관리 거래처 목록 조회를 하는 메서드입니다.
	*
	* @param   ClintValueObject clintVo
	* @return  List
	* @exception  예외처리 상황을 적어주세요
	*/
	// 거래처 목록을 조회하는 메소드
	public List<ClintValueObject> clintList(ClintValueObject clintVo) throws Exception{
		List<ClintValueObject> clintList = getSqlSession().selectList("clintMapper.clintList", clintVo);
		return clintList;
	}
	
	/**
	* 거래처관리 거래처 리스트 조회를 하는 메서드입니다.
	*
	* @param   ClintValueObject clintVo
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	// 거래처 리스트 전체 갯수 (페이징)
	public int selectClintCount(ClintValueObject clintVo) throws Exception{

		int selectClintCount = getSqlSession().selectOne("clintMapper.selectClintCount", clintVo);
		
		return selectClintCount;
	}
	
	/**
	* 거래처관리 거래처 리스트 조회를 하는 메서드입니다.
	*
	* @param   ClintValueObject clintVo
	* @return  ClintValueObject
	* @exception  예외처리 상황을 적어주세요
	*/
	// 거래처 정보 상세조회 메소드
	public ClintValueObject clintRead(int clintSeq) throws Exception{
		ClintValueObject clintVo = getSqlSession().selectOne("clintMapper.clintRead", clintSeq);
		return clintVo;
	}
	
	/**
	* 거래처관리 거래처 리스트 조회를 하는 메서드입니다.
	*
	* @param   int clintSeq
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/
	// 거래처 상세조회에서 해당 거래처 삭제하는 메소드
	public void clintReadDelete(int clintSeq) throws Exception{
		getSqlSession().delete("clintMapper.clintReadDelete", clintSeq);
		getSqlSession().delete("clintMapper.clintProductDelete", clintSeq);
	}
	
	/**
	* 거래처관리 거래처 수정화면에서 거래처를 수정하는 메서드입니다.
	*
	* @param   ClintValueObject clintVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/
	public void clintUpdate(ClintValueObject clintVo) throws Exception{
		
		getSqlSession().update("clintMapper.clintUpdate", clintVo);
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
		
		List<ClintValueObject> selectProdctAdList = getSqlSession().selectList("clintMapper.SelectProdctAdList", clintVo);
		return selectProdctAdList;
	}
	
	/**
	* 거래처관리 상품추가모달에서 거래처가 미등록된 상품의 갯수를 조회하는 메서드입니다.
	*
	* @param 
	* @return  selectProdctCount
	* @exception  예외처리 상황을 적어주세요
	*/	
	public int selectProdctCount(ClintValueObject clintVo) throws Exception{
		
		int selectProdctCount = getSqlSession().selectOne("clintMapper.selectProdctCount", clintVo);
		return selectProdctCount;
	}
	
	/**
	* 거래처관리 상품추가모달에서 임시테이블에 있는 상품을 조회하는 메서드입니다.
	*
	* @param 
	* @return  selectProdctAdTempList
	* @exception  예외처리 상황을 적어주세요
	*/
	public List<ClintValueObject> selectProdctAdTempList(ClintValueObject clintVo) throws Exception{
		
		
		List<ClintValueObject> selectProdctAdTempList = getSqlSession().selectList("clintMapper.selectProdctAdTempList",clintVo);
		return selectProdctAdTempList;
	}
	
	/**
	* 거래처관리 상품추가모달에서 임시테이블에 있는 상품의 갯수를 조회하는 메서드입니다.
	*
	* @param 
	* @return  selectProdctTempCount
	* @exception  예외처리 상황을 적어주세요
	*/
	public int selectProdctTempCount(ClintValueObject clintVo) throws Exception{
		
		int selectProdctTempCount = getSqlSession().selectOne("clintMapper.selectProdctTempCount", clintVo);
		return selectProdctTempCount;
	}
	
	/**
	* 거래처관리 상품추가모달에서 임시테이블에 상품을 추가하는 메서드입니다.
	*
	* @param String[] data
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/
	public void prodctAddTempTableCreate(String[] data) throws Exception{
		System.out.println("dao 왔다");
		for(int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);			// 배열안의 값들을 for문돌려 long형태로 변환하여 할당한다.
			System.out.println("상품 seq :" +prodctSeq);
			getSqlSession().insert("clintMapper.prodctAddTempTableCreate", prodctSeq);
		}
	}
	
	/**
	* 거래처관리 상품추가모달에서 임시테이블에 상품을 삭제하는 메서드입니다.
	*
	* @param String[] data
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/
	// 임시테이블에 체크한 상품 마이너스 눌렀을 때 삭제
	public void prodctAddTempTableDelete(String[] data) throws Exception{
		
		for(int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			getSqlSession().delete("clintMapper.prodctAddTempTableDelete", prodctSeq);
		}
	}
	
	/**
	* 임시테이블 상품 삭제 메서드
	*
	* @param 
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 임시테이블 상품 삭제 메서드 (임시테이블에 있는 데이터를 삭제시킴)
	public void deleteTempTable() throws Exception{
		getSqlSession().delete("clintMapper.deleteTempTable");
		
	}
	
	/**
	* 임시테이블 상품을 DB에 최종 등록
	*
	* @param String[] data
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 임시테이블 상품을 DB에 최종 등록
	public void insertProdct(String[] data, int clintSeq) throws Exception{
		
//		getSqlSession().delete("clintMapper.deleteTempTable");
		ClintValueObject clintVo = new ClintValueObject();
		clintVo.setClintSeq(clintSeq);
		for(int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			clintVo.setProdctSeq(prodctSeq);
			getSqlSession().insert("clintMapper.insertProdct", clintVo);
		}
		
	}
	
	/**
	* 거래처 상세보기에 상품을 추가하는 메서드입니다.
	*
	* @param ClintValueObject clintVo
	* @return  List<ClintValueObject> prodctList
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 거래처 상세보기에 상품 추가
	public List<ClintValueObject> prodctList(ClintValueObject clintVo) throws Exception{
		List<ClintValueObject> prodctList = getSqlSession().selectList("clintMapper.prodctList", clintVo);
		return prodctList;
	}
	
	/**
	* 거래처 상세보기에 상품리스트를 페이징하는 메서드입니다.
	*
	* @param ClintValueObject clintVo
	* @return  int selectProdctCountPaging
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 상품리스트 페이징(거래처 상세보기)
	public int selectProdctCountPaging(ClintValueObject clintVo) throws Exception{
		int selectProdctCountPaging = getSqlSession().selectOne("clintMapper.selectProdctCountPaging", clintVo);
		return selectProdctCountPaging;
	}
	
	/**
	* 거래처를 등록하는 메서드입니다.
	*
	* @param ClintValueObject clintVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 거래처 등록 메서드
	public int insertClint(ClintValueObject clintVo) throws Exception{
		getSqlSession().insert("clintMapper.insertClint", clintVo);
		int insertClintSeq = getSqlSession().selectOne("clintMapper.insertClintSeq");
		
		return insertClintSeq;
	}
	
	/**
	* 거래처를 체크해서 삭제하는 메서드입니다.
	*
	* @param ClintValueObject clintVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	public void deleteClint(List<Integer> groupSeq) throws Exception{
		
		getSqlSession().delete("clintMapper.deleteClint", groupSeq);
	}

	/**
	* 상품추가 팝업창에서 완료를 눌렀을 때, 임시테이블에 있는 상품들의 seq를 가져오는 메소드.
	*
	* @param   ClintValueObject clintVo
	* @return  String[]
	* @exception  예외처리 상황을 적어주세요
	*/	
	public String[] selectTempProdct(ClintValueObject clintVo) throws Exception{
		logger.info(null);
		List<Long> prodctList = getSqlSession().selectList("clintMapper.selectTempProdct", clintVo);
		String[] data = new String[prodctList.size()];
		for(int i=0; i<prodctList.size(); i++){
			data[i] = Long.toString(prodctList.get(i));
			System.out.println(i+"번째~~~~:" +data[i]);
		}
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
		logger.info(null);
		
		int ClintBridgChk[] = clintVo.getClintBridgChkGroup();
				
		List<Integer> chkList = new ArrayList<Integer>(); 
		for(int i=0; i<ClintBridgChk.length; i++) {
			chkList.add(ClintBridgChk[i]);
		}	
		clintVo.setChkList(chkList);	
				
		// map으로 보내는 이유. .list를 같이 보내기 위해서, 반복되는 일정 등록할때 반복 횟수만큼 고정된 값은 고정되어서 들어가고 .list 반복이 돌면서 변할 값들은 변하면서 같이 insert
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("clintVo", clintVo);
		map.put("chkList", chkList);
		
		getSqlSession().update("clintMapper.ClintProductUpdate", map);
	}
	
	/**
	* 거래처 상세보기에서 거래처 상품들 일괄 삭제하는 메소드
	*
	* @param   ClintValueObject clintVo
	* @return  void
	* @exception  예외처리 상황을 적어주세요
	*/	
	public void ClintProductDelete(ClintValueObject clintVo) throws Exception{
		int ClintBridgChk[] = clintVo.getClintBridgChkGroup();
		
		List<Integer> chkList = new ArrayList<Integer>(); 
		for(int i=0; i<ClintBridgChk.length; i++) {
			chkList.add(ClintBridgChk[i]);
		}	
			
		getSqlSession().delete("clintMapper.ClintProductDelete", chkList);
	}
}
