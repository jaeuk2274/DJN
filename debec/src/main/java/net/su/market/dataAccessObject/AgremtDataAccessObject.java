package net.su.market.dataAccessObject;

import net.su.market.valueObject.AgremtValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class AgremtDataAccessObject extends SqlSessionDaoSupport {

	// 이용약관 내용 조회
	public AgremtValueObject selectAgremt(AgremtValueObject agremtValueObject) throws Exception {
		System.out.println("AgremtDataAccessObject의 selectAgremt()");
		AgremtValueObject agremtVO = getSqlSession().selectOne("agremtMapper.selectAgremt", agremtValueObject);
		return agremtVO;
	}
	
	// 이용약관 내용 수정
	public void updateAgremt(AgremtValueObject agremtValueObject) throws Exception {
		System.out.println("AgremtDataAccessObject의 updateAgremt()");
		getSqlSession().update("agremtMapper.updateAgremt", agremtValueObject);	
	}
}
