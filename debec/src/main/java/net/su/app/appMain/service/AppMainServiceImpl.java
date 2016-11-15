package net.su.app.appMain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.su.app.appMain.dataAccessObject.AppMainDataAccessObject;
import net.su.app.appRecp.valueObject.AppRecpValueObject;
import net.su.market.valueObject.DJNValueObject;
import net.su.market.valueObject.EmpValueObject;

import org.springframework.stereotype.Service;

@Service
public class AppMainServiceImpl implements AppMainService {
	
	@Resource
	private AppMainDataAccessObject appMainDao;
	
	// 직원 목록 조회
	public List<EmpValueObject> appSelectEmpList() throws Exception{
		List<EmpValueObject> empList = appMainDao.appSelectEmpList();
		return empList;
	}
	
	// 대장남 투표(고객)
	public void appCustmrDJNVoting(Map votingMap) throws Exception{
		appMainDao.appCustmrDJNVoting(votingMap);
	}
	
	// 대장남 투표(직원)
	public void appEmpDJNVoting(Map votingMap) throws Exception{
		appMainDao.appEmpDJNVoting(votingMap);
	}
	
	// 대장남 투표 권한(고객)
	public int appSelectCustmrDJNVotingAthrty(int custmrSeq) throws Exception{
		int thisMonthCount = appMainDao.appSelectCustmrDJNVotingAthrty(custmrSeq);
		return thisMonthCount;
	}
	
	// 대장남 투표 권한(직원)
	public int appSelectEmpDJNVotingAthrty(int empSeq) throws Exception{
		int thisMonthCount = appMainDao.appSelectEmpDJNVotingAthrty(empSeq);
		return thisMonthCount;
	}
	
	// 랜덤 레시피 조회
	public List<AppRecpValueObject> appSelectRecpList() throws Exception{
		List<AppRecpValueObject> recpList = appMainDao.appSelectRecpList();
		return recpList;
	}
	
	// 역대 대장남
//	public List<DJNValueObject> selectDJN(DJNValueObject djnVo) throws Exception{
//		List<DJNValueObject> everDJNList = appMainDao.selectDJN(djnVo);
//		return everDJNList;
//	}
	
	// 레시피 상세조회
//	public AppRecpValueObject appSelectRecpRead() throws Exception{
//		appMainDao.appSelectRecpRead();
//	}
}
