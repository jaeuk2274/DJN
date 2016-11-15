package net.su.custmr.dataAccessObject;

import java.util.List;

import net.su.custmr.valueObject.CustmrValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class CustmrDataAccessObject extends SqlSessionDaoSupport{

	// 고객 리스트 조회
	public List<CustmrValueObject> selectCustmrList(CustmrValueObject custmrValueObject) throws Exception {
		System.out.println("CustmrDateAccessObject의  selectCustmrList() 작동");		
		List<CustmrValueObject> custmrList = getSqlSession().selectList("custmrMapper.selectCustmrList", custmrValueObject);	
		return custmrList;
	}
	
	// 고객 리스트 전체 수
	public int selectCustmrCount(CustmrValueObject custmrValueObject) throws Exception {
		System.out.println("CustmrDateAccessObject의  selectCustmrCount() 작동");		
		int selectCustmrCount = getSqlSession().selectOne("custmrMapper.selectCustmrCount", custmrValueObject);	
		return selectCustmrCount;
	}
	
	// 고객 카드 여부 일괄 변경 -> Y
	public void changeCustmrCardChkGroup(List<Integer> custmrCardChkGroup) throws Exception {
		System.out.println("CustmrDateAccessObject의  changeCustmrCardChkGroup() 작동");	
		getSqlSession().update("custmrMapper.changeCustmrCardChkGroup", custmrCardChkGroup);
	}
	
	// 고객 상세보기
	public CustmrValueObject custmrRead(CustmrValueObject custmrValueObject) throws Exception {
		System.out.println("CustmrDateAccessObject의  custmrRead() 작동");		
		CustmrValueObject custmrReadVO= getSqlSession().selectOne("custmrMapper.custmrRead", custmrValueObject);	
		return custmrReadVO;
	}
	
	// 고객 정보 변경
	public void custmrUpdate(CustmrValueObject custmrValueObject) throws Exception {
		System.out.println("CustmrDateAccessObject의  custmrUpdate() 작동");	
		getSqlSession().update("custmrMapper.custmrUpdate", custmrValueObject);
	}

	// 고객 정보 삭제
	public void custmrDelete(CustmrValueObject custmrValueObject) throws Exception {
		System.out.println("CustmrDateAccessObject의  custmrDelete() 작동");	
		getSqlSession().delete("custmrMapper.custmrDelete", custmrValueObject);
	}
	
	// 휴면고객 일반회원 일괄 변경
	public void changeQuscncCustmrGroup(List<Integer> custmrChkGroup) throws Exception {
		System.out.println("CustmrDateAccessObject의  changeQuscncCustmrGroup() 작동");	
		getSqlSession().update("custmrMapper.changeQuscncCustmrGroup", custmrChkGroup);
	}
	
	// 휴면고객 일괄 삭제
	public void deleteQuscncCustmrGroup(List<Integer> custmrChkGroup) throws Exception {
		System.out.println("CustmrDateAccessObject의  deleteQuscncCustmrGroup() 작동");	
		getSqlSession().delete("custmrMapper.deleteQuscncCustmrGroup", custmrChkGroup);
	}
		
	
	
	
}
