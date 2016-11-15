package net.su.custmr.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.su.custmr.dataAccessObject.CustmrDataAccessObject;
import net.su.custmr.valueObject.CustmrValueObject;

import org.springframework.stereotype.Service;

@Service
public class CustmrServiceImpl implements CustmrService{

	@Resource
	private CustmrDataAccessObject custmrDAO;
	
	// 고객 리스트
	public List<CustmrValueObject> selectCustmrList(CustmrValueObject custmrValueObject) throws Exception {
		System.out.println("CustmrServiceImpl의 selectCustmrList() 작동");		
		int selectCustmrCount = custmrDAO.selectCustmrCount(custmrValueObject);	// 고객 리스트 전체 수 (페이징)
		custmrValueObject.setTotalRecordCount(selectCustmrCount); // 고객 리스트 전체 수 셋팅 (페이징)
		List<CustmrValueObject> custmrList = custmrDAO.selectCustmrList(custmrValueObject);	 // 고객 리스트 조회
		return custmrList;
	}

	// 고객 카드 여부 일괄 변경
	public void changeCustmrCardChkGroup(int custmrCardChkGroup[]) throws Exception {
		System.out.println("CustmrServiceImpl의 changeCustmrCardChkGroup() 작동");	
		List<Integer> groupSeq = new ArrayList<Integer>(); // 맵퍼에  .list로 한번에 보내서 맵퍼에서 한번에 처리하기 위해 위해 배열을 list에 삽입
		// db에 커넥트하는게 1번이라서 반복 돌리면서 계속 커넥트하는 것 보다 훨씬 효율적이다.
		for(int i=0; i<custmrCardChkGroup.length; i++) {
			groupSeq.add(custmrCardChkGroup[i]);
		}				
		custmrDAO.changeCustmrCardChkGroup(groupSeq);
	}
	
	// 고객 상세보기
	public CustmrValueObject custmrRead(CustmrValueObject custmrValueObject) throws Exception {
		System.out.println("CustmrServiceImpl의 custmrRead() 작동");			
		CustmrValueObject custmrReadVO= custmrDAO.custmrRead(custmrValueObject);
		return custmrReadVO;
	}
	
	// 고객 정보 변경
	public void custmrUpdate(CustmrValueObject custmrValueObject) throws Exception {
		System.out.println("CustmrServiceImpl의 custmrUpdate() 작동");			
		custmrDAO.custmrUpdate(custmrValueObject);
	}
	
	// 고객 정보 삭제
	public void custmrDelete(CustmrValueObject custmrValueObject) throws Exception {
		System.out.println("CustmrServiceImpl의 custmrDelete() 작동");			
		custmrDAO.custmrDelete(custmrValueObject);
	}
	
	
	// 휴면고객 일반회원 일괄 변경
	public void changeQuscncCustmrGroup(int custmrChkGroup[]) throws Exception {
		System.out.println("CustmrServiceImpl의 changeQuscncCustmrGroup() 작동");	
		List<Integer> groupSeq = new ArrayList<Integer>(); 
		for(int i=0; i<custmrChkGroup.length; i++) {
			groupSeq.add(custmrChkGroup[i]);
		}				
		custmrDAO.changeQuscncCustmrGroup(groupSeq);
	}
	
	// 휴면고객 일반회원 일괄 변경
	public void deleteQuscncCustmrGroup(int custmrChkGroup[]) throws Exception {
		System.out.println("CustmrServiceImpl의 deleteQuscncCustmrGroup() 작동");	
		List<Integer> groupSeq = new ArrayList<Integer>(); 
		for(int i=0; i<custmrChkGroup.length; i++) {
			groupSeq.add(custmrChkGroup[i]);
		}				
		custmrDAO.changeQuscncCustmrGroup(groupSeq);
	}

}
