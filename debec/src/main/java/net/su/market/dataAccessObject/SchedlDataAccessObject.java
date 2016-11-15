package net.su.market.dataAccessObject;

import net.su.market.valueObject.SchedlValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Repository
public class SchedlDataAccessObject extends SqlSessionDaoSupport {

	//전체 일정 조회
	public List<SchedlValueObject> SchedlListRead() throws Exception {
		System.out.println("SchedlDataAccessObject의 SchedlListRead()");
		List<SchedlValueObject> SchedlListRead = getSqlSession().selectList("schedlMapper.SchedlListRead");
		return SchedlListRead;
	}
	
	// 일정 등록 (반복 설정 가능)
	public void SchedlCreate(SchedlValueObject schedlValueObject) throws Exception {
		System.out.println("SchedlDataAccessObject의 SchedlCreate()");
		System.out.println("반복 뭐야? :"+schedlValueObject.getSchedlRept());
		int cont=1;
		
		// 반복 하는 휫수 카운트 해오기 (안함, 일,주,월 별)
		if(schedlValueObject.getSchedlRept().equals("Not")){
			getSqlSession().insert("schedlMapper.notSchedlCreate", schedlValueObject);
			System.out.println("리턴");
			return;
		}
		else if(schedlValueObject.getSchedlRept().equals("Day")){			
			cont = getSqlSession().selectOne("schedlMapper.reptDayCont", schedlValueObject);
		}
		else if(schedlValueObject.getSchedlRept().equals("Week")){
			cont = getSqlSession().selectOne("schedlMapper.reptWeekCont", schedlValueObject);
		}
		else if(schedlValueObject.getSchedlRept().equals("Month")){
			cont = getSqlSession().selectOne("schedlMapper.reptMonthCont", schedlValueObject);
		}	

		System.out.println("반복횟수는? : "+ cont);		
		List<Integer> reptCont = new ArrayList<Integer>(); // 맵퍼에  .list로 한번에 보내서 맵퍼에서 한번에 처리하기 위해 위해 배열을 list에 삽입
		// db에 커넥트하는게 1번이라서 반복 돌리면서 계속 커넥트하는 것 보다 훨씬 효율적이다.
		for(int i=0; i<cont; i++) {
			reptCont.add(i);
		}				
		
		// map으로 보내는 이유. .list를 같이 보내기 위해서, 반복되는 일정 등록할때 반복 횟수만큼 고정된 값은 고정되어서 들어가고 .list 반복이 돌면서 변할 값들은 변하면서 같이 insert
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("schedlVO", schedlValueObject);
		map.put("reptCont", reptCont);
		
		getSqlSession().insert("schedlMapper.SchedlCreate", map);
		
		
	}
	
	
	// 일정 상세 조회(팝업)
	public SchedlValueObject SchedlReadPopup(SchedlValueObject schedlValueObject) throws Exception {
		System.out.println("SchedlDataAccessObject의 SchedlReadPopup()");
		SchedlValueObject schedlReadVO = getSqlSession().selectOne("schedlMapper.SchedlReadPopup", schedlValueObject);
		return schedlReadVO;
	}
	
	
	// 일정 삭제
	public void SchedlDelete(SchedlValueObject schedlValueObject) throws Exception {
		System.out.println("SchedlDataAccessObject의 SchedlDelete()");
		
		getSqlSession().delete("schedlMapper.EvntDelete", schedlValueObject);	// 행사 tb 삭제
		getSqlSession().delete("schedlMapper.VactnDelete", schedlValueObject);	// 휴가 tb 삭제
		getSqlSession().delete("schedlMapper.SchedlDelete", schedlValueObject);	// 일정 tb 삭제
	}
	
	// 일정 수정
	public void SchedlUpdate(SchedlValueObject schedlValueObject) throws Exception {
		System.out.println("SchedlDataAccessObject의 SchedlUpdate()");
		System.out.println("반복 뭐야? :"+schedlValueObject.getSchedlRept());
		getSqlSession().update("schedlMapper.SchedlUpdate", schedlValueObject);
	}
	
	
	
}
