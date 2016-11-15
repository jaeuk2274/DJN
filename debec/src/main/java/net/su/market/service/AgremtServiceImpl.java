package net.su.market.service;

import net.su.market.dataAccessObject.AgremtDataAccessObject;
import net.su.market.valueObject.AgremtValueObject;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AgremtServiceImpl implements AgremtService{
	
	@Resource
	private AgremtDataAccessObject AgremtDAO;
	
	// 이용약관 내용 조회
	public AgremtValueObject selectAgremt(AgremtValueObject agremtValueObject) throws Exception {
		System.out.println("AgremtServiceImpl의 selectAgremt()");
		AgremtValueObject agremtVO = AgremtDAO.selectAgremt(agremtValueObject);
		return agremtVO;
	}

	// 이용약관 내용 수정
	public void updateAgremt(AgremtValueObject agremtValueObject) throws Exception {
		System.out.println("AgremtServiceImpl의 updateAgremt()");
		AgremtDAO.updateAgremt(agremtValueObject);
	}
}
