package net.su.app.appMain.service;

import java.util.List;
import java.util.Map;

import net.su.app.appRecp.valueObject.AppRecpValueObject;
import net.su.market.valueObject.DJNValueObject;
import net.su.market.valueObject.EmpValueObject;

public interface AppMainService {
	// AppMainServiceImpl
	
	// 직원 목록 조회
	public List<EmpValueObject> appSelectEmpList() throws Exception;
	
	// 대장남 투표(고객)
	public void appCustmrDJNVoting(Map votingMap) throws Exception;
	
	// 대장남 투표(직원)
	public void appEmpDJNVoting(Map votingMap) throws Exception;
	
	// 대장남 투표 권한(고객)
	public int appSelectCustmrDJNVotingAthrty(int custmrSeq) throws Exception;
	
	// 대장남 투표 권한 (직원)
	public int appSelectEmpDJNVotingAthrty(int empSeq) throws Exception;
	
	// 랜덤 레시피 조회
	public List<AppRecpValueObject> appSelectRecpList() throws Exception;
	
	// 역대 대장남
//	public List<DJNValueObject> selectDJN(DJNValueObject djnVo) throws Exception;
	// 레시피 상세보기
//	public AppRecpValueObject appSelectRecpRead() throws Exception;
}
