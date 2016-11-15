package net.su.market.service;

import net.su.market.dataAccessObject.SchedlDataAccessObject;
import net.su.market.valueObject.SchedlValueObject;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class SchedlServiceImpl implements SchedlService{
	
	@Resource
	private SchedlDataAccessObject schedlDAO;
	
	//전체 일정 조회
	public List<SchedlValueObject> SchedlListRead() throws Exception {
		System.out.println("SchedlServiceImpl의 SchedlListRead()");
		List<SchedlValueObject> allCalList = schedlDAO.SchedlListRead();
		return allCalList;
	}
	
	// 일정 등록
	public void SchedlCreate(SchedlValueObject schedlValueObject) throws Exception {
		System.out.println("SchedlServiceImpl의 SchedlCreate()");
		schedlDAO.SchedlCreate(schedlValueObject);
	}
		
	// 일정 상세 조회(팝업)
	public SchedlValueObject SchedlReadPopup(SchedlValueObject schedlValueObject) throws Exception {
		System.out.println("SchedlServiceImpl의 SchedlReadPopup()");
		SchedlValueObject schedlReadVO = schedlDAO.SchedlReadPopup(schedlValueObject);
		return schedlReadVO;
	}
	
	// 일정 삭제
	public void SchedlDelete(SchedlValueObject schedlValueObject) throws Exception {
		System.out.println("SchedlServiceImpl의 SchedlDelete()");
		schedlDAO.SchedlDelete(schedlValueObject);
		
	}
		
	// 일정 수정
	public void SchedlUpdate(SchedlValueObject schedlValueObject) throws Exception {
		System.out.println("SchedlServiceImpl의 SchedlUpdate()");
		schedlDAO.SchedlUpdate(schedlValueObject);
		
	}
}
