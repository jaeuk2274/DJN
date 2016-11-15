package net.su.app.appMain.dataAccessObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.su.app.appRecp.valueObject.AppRecpValueObject;
import net.su.market.valueObject.DJNValueObject;
import net.su.market.valueObject.EmpValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class AppMainDataAccessObject extends SqlSessionDaoSupport{

	// 직원 목록 조회(대장남 투표)
	public List<EmpValueObject> appSelectEmpList() throws Exception{
		List<EmpValueObject> empList = getSqlSession().selectList("appMainMapper.appSelectEmpList");
		return empList;
	}
	
	// 대장남 투표(고객)
	public void appCustmrDJNVoting(Map votingMap) throws Exception{
		getSqlSession().insert("appMainMapper.appCustmrDJNVoting", votingMap);
	}
	
	// 대장남 투표(직원)
	public void appEmpDJNVoting(Map votingMap) throws Exception{
		getSqlSession().insert("appMainMapper.appEmpDJNVoting", votingMap);
	}
	
	// 대장남 투표 권한(고객)
	public int appSelectCustmrDJNVotingAthrty(int custmrSeq) throws Exception{
		System.out.println("디에이오 1");
		int thisMonthCount = getSqlSession().selectOne("appMainMapper.appCustmrDjnVotingAthrty", custmrSeq);
		System.out.println("디에이오 2");
		return thisMonthCount;
	}
	
	// 대장남 투표 권한 (직원)
	public int appSelectEmpDJNVotingAthrty(int empSeq) throws Exception{
		int thisMonthCount = getSqlSession().selectOne("appMainMapper.appEmpDjnVotingAthrty", empSeq);
		return thisMonthCount;
	}
	
	// 랜덤 레시피 조회
	public List<AppRecpValueObject> appSelectRecpList() throws Exception{
		List<AppRecpValueObject> recpList = getSqlSession().selectList("appMainMapper.appSelectRecpList");
		return recpList;
	}
	
	// 역대 대장남
//	public List<DJNValueObject> selectDJN(DJNValueObject djnVo) throws Exception{
//		List<DJNValueObject> everDJNList = getSqlSession().selectList("DJNMapper.selectDJN", djnVo);
//		return everDJNList;
//	}
	
	// 레시피 상세보기
//	public AppRecpValueObject appSelectRecpRead() throws Exception{
//		getSqlSession().selectOne("appMainMapper.appSelectRecpRead");
//		return "";
//	}
}
