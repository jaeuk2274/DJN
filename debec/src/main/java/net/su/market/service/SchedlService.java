package net.su.market.service;

import java.util.List;

import net.su.market.valueObject.SchedlValueObject;

public interface SchedlService {
	//전체 일정 조회
	public List<SchedlValueObject> SchedlListRead() throws Exception;
	
	// 일정 등록
	public void SchedlCreate(SchedlValueObject schedlValueObject) throws Exception;
	
	// 일정 상세 조회(팝업)
	public SchedlValueObject SchedlReadPopup(SchedlValueObject schedlValueObject) throws Exception;
	
	// 일정 삭제
	public void SchedlDelete(SchedlValueObject schedlValueObject) throws Exception;
	
	// 일정 수정
	public void SchedlUpdate(SchedlValueObject schedlValueObject) throws Exception;
	
}
