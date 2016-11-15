/**
 * 입고거래관리 dao입니다.
 * 
 * @see   net.su.deal.dataAccessObject.InstckDataAccessObject
 * @version  1.0 
 * @ author 김대현, 2016/08/15
 */
package net.su.deal.dataAccessObject;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import net.su.deal.valueObject.InstckValueObject;

@Repository
public class InstckDataAccessObject extends SqlSessionDaoSupport{


	/**
	* 거래처 입고거래장을 조회하는 메서드입니다.
	*
	* @param  int clintSeq, InstckValueObject instckVo
	* @return  List
	* @exception  예외처리 상황을 적어주세요
	*/
// 거래처 상세조회에서 해당 거래처의 입고거래장을 팝업으로 조회하는 메소드
	public List<InstckValueObject> instckList(int clintSeq, InstckValueObject instckVo) throws Exception{
		System.out.println(clintSeq+"   dao1");
		instckVo.setClintSeq(clintSeq);
		System.out.println(clintSeq+"   dao2");

		List<InstckValueObject> instckList = getSqlSession().selectList("instckMapper.instckList", instckVo);
		System.out.println(clintSeq+"   dao3");

		return instckList;
	}
	
	/**
	* 거래처 입고거래장을 페이징하는 메소드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
// 거래처 상세조회에 입고거래장(팝업) 페이징
	public int selectInstckCount(InstckValueObject instckVo) throws Exception{
		
		System.out.println("   dao4");

		int selectInstckCount = getSqlSession().selectOne("instckMapper.selectInstckCount", instckVo);
		System.out.println("   dao5");

		return selectInstckCount;
	}
	
	/**
	* 거래처 입고거래장을 상세조회하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  InstckValueObject
	* @exception  예외처리 상황을 적어주세요
	*/
// 거래처 입고거래장 상세조회하는 메서드
	public InstckValueObject instckExchngFlorReadPopup(InstckValueObject instckVo) throws Exception{
		
		instckVo = getSqlSession().selectOne("instckMapper.instckExchngFlorReadPopup", instckVo);
		return instckVo;
	}
	
	/**
	* 거래처 입고내역을 조회하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  List
	* @exception  예외처리 상황을 적어주세요
	*/
// 거래처 입고내역을 조회하는 메서드
	public List<InstckValueObject> instckRecrdList(InstckValueObject instckVo) throws Exception{
		
		List<InstckValueObject> instckRecrdList = getSqlSession().selectList("instckMapper.instckRecrdList", instckVo);
		return instckRecrdList;
	}
	
	/**
	* 거래처 입고내역을 조회하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
// 거래처 입고내역(팝업) 페이징
	public int selectInstckRecrdCount(InstckValueObject instckVo) throws Exception{
		
		int selectInstckRecrdCount = getSqlSession().selectOne("instckMapper.selectInstckRecrdCount", instckVo);
		return selectInstckRecrdCount;
	}
	
	/**
	* 입고거래관리 - 입고거래장 목록을 조회하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  List<InstckValueObject> instckList
	* @exception  예외처리 상황을 적어주세요
	*/
// 입고거래장 목록 조회
	public List<InstckValueObject> selectInstckExchngFlor(InstckValueObject instckVo) throws Exception{
		
		List<InstckValueObject> instckList = getSqlSession().selectList("instckMapper.selectInstckExchngFlor", instckVo);
		return instckList;
	}
	
	/**
	* 입고거래관리 - 입고거래장 목록의 갯수를 조회하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  int selectInstckListCount
	* @exception  예외처리 상황을 적어주세요
	*/
// 입고거래장 리스트 갯수(카운트)
	public int selectInstckListCount(InstckValueObject instckVo) throws Exception{
		
		int selectInstckListCount = getSqlSession().selectOne("instckMapper.selectInstckListCount", instckVo);
		return selectInstckListCount;
	}

	/**
	* 입고거래관리 - 입고거래장등록(팝업)에 거래처명을 조회하는 메서드입니다.
	*
	* @param  
	* @return  List<InstckValueObject> clintNmeList
	* @exception  예외처리 상황을 적어주세요
	*/
// 입고거래장 등록 팝업에 거래처명 조회하는 메서드
	public List<InstckValueObject> selectClintNme() throws Exception{
		List<InstckValueObject> clintNmeList = getSqlSession().selectList("instckMapper.selectClintNme");
		return clintNmeList;
	}

	/**
	* 입고거래관리 - 입고거래장등록(팝업)을 하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/
// 입고거래장 등록하는 메서드
	public void insertInstckExchngFlor(InstckValueObject instckVo) throws Exception{
		getSqlSession().insert("instckMapper.insertInstckExchngFlor", instckVo);
	}
	
	/**
	* 입고거래장(팝업)을 수정하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/
	public void updateInstckExchngFlor(InstckValueObject instckVo) throws Exception{
		getSqlSession().update("instckMapper.updateInstckExchngFlor", instckVo);
		
	}
	
	/**
	* 입고거래장(팝업)을 삭제하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/
	public void deleteInstckExchngFlorPopup(InstckValueObject instckVo) throws Exception{
		
		getSqlSession().delete("instckMapper.deleteInstckExchngFlorPopup", instckVo);
	}
	
	/**
	* 입고거래장을 체크해서 삭제하는 메서드입니다.
	*
	* @param  InstckValueObject instckVo
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/
	public void deleteInstckExchngFlor(List<Integer> groupSeq) throws Exception{
		getSqlSession().delete("instckMapper.deleteInstckExchngFlor", groupSeq);
		
	}
}
