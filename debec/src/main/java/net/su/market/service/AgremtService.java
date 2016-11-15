package net.su.market.service;

import net.su.market.valueObject.AgremtValueObject;

public interface AgremtService {
	
	// 이용약관 내용 조회
	public AgremtValueObject selectAgremt(AgremtValueObject agremtValueObject) throws Exception;
	
	// 이용약관 내용 수정
	public void updateAgremt(AgremtValueObject agremtValueObject) throws Exception;
}
