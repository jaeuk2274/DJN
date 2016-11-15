package net.su.custmr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;

import net.su.custmr.dataAccessObject.PushDataAccessObject;
import net.su.custmr.valueObject.OrdrValueObject;
import net.su.custmr.valueObject.PushValueObject;

import org.springframework.stereotype.Service;

@Service
public class PushServiceImpl implements PushService {
	@Resource
	private PushDataAccessObject pushDataAccessObject;
	//푸시 메인
	public Map<String, Object> pushNotfcatnMain(int dateOption)throws Exception{
		net.su.logger.Logger.info("메인 서비스");
		Map<String, Object> pushNotfcatnMainMap = new HashMap<String, Object>();
		PushValueObject pushNotfcatnDayStatstcs = pushDataAccessObject.pushNotfcatnDayStatstcs();
		PushValueObject pushNotfcatnWekStatstcs = pushDataAccessObject.pushNotfcatnWekStatstcs();
		PushValueObject pushNotfcatnMonthStatstcs = pushDataAccessObject.pushNotfcatnMonthStatstcs();
		List<PushValueObject> pushNotfcatnStatstcsGraph = pushDataAccessObject.pushNotfcatnStatstcsGraph(dateOption);
		PushValueObject pushNotfcatnStatstcsGraphSum = pushDataAccessObject.pushNotfcatnStatstcsGraphSum(dateOption);
		
		pushNotfcatnMainMap.put("pushNotfcatnDayStatstcs", pushNotfcatnDayStatstcs);
		pushNotfcatnMainMap.put("pushNotfcatnWekStatstcs", pushNotfcatnWekStatstcs);
		pushNotfcatnMainMap.put("pushNotfcatnMonthStatstcs", pushNotfcatnMonthStatstcs);
		pushNotfcatnMainMap.put("pushNotfcatnStatstcsGraph", pushNotfcatnStatstcsGraph);
		pushNotfcatnMainMap.put("pushNotfcatnStatstcsGraphSum", pushNotfcatnStatstcsGraphSum);
		
		return pushNotfcatnMainMap;
	}
	//푸시알림 조회
	public List<PushValueObject> pushNotfcatnList(PushValueObject pushVo)throws Exception{
		int selectpushTempltCount = pushDataAccessObject.selectpushTempltCount(pushVo);	
		pushVo.setTotalRecordCount(selectpushTempltCount);
		List<PushValueObject> pushNotfcatnList	= pushDataAccessObject.pushNotfcatnList(pushVo);
		
		return pushNotfcatnList;
	}
	public void pushNotfcatTempltInsert(PushValueObject pushVo)throws Exception{
		System.out.println("푸시알람 템플릿 등록 서비스");
		pushDataAccessObject.pushNotfcatTempltInsert(pushVo);
	}
	//푸시 템플릿 상세조회
	public PushValueObject pushNotfcatnTempltSelectDetl(int getPushNotfcatnSeq)throws Exception{
		System.out.println("푸시알람 템플릿 상세조회"+getPushNotfcatnSeq);
		
		PushValueObject pushNotfcatnTempltSelectDetl = pushDataAccessObject.pushNotfcatnTempltSelectDetl(getPushNotfcatnSeq);
		
		return pushNotfcatnTempltSelectDetl;
	}
	//푸시 템플릿 삭제
	public void pushTempltDelete(String[] data)throws Exception{
		System.out.println(data+"삭제 서비스");
		pushDataAccessObject.pushTempltDelete(data);
	}
	//푸시 템플릿 수정
	public void pushNotfcatnTempltUpdate(PushValueObject pushVo)throws Exception{
		System.out.println("템플릿 수정 서비스인플"+pushVo.getPushTitl());
		pushDataAccessObject.pushNotfcatnTempltUpdate(pushVo);
	}
	//푸시 수신자 전체 조회
	public List<OrdrValueObject> pushRecpntAll(PushValueObject pushVo)throws Exception{
		Logger.getLogger("푸시 수신자 고객 전체 조회");
		int pushRecpntAllCount = pushDataAccessObject.pushRecpntAllCount(pushVo);
		pushVo.setTotalRecordCount(pushRecpntAllCount);
		List<OrdrValueObject> pushRecpntAll = pushDataAccessObject.pushRecpntAll(pushVo);
		
		return pushRecpntAll;
	}
	//푸시 상품 수신자 전체 조회
	public List<OrdrValueObject> pushRecpntProdctAll(PushValueObject pushVo)throws Exception{
		Logger.getLogger("푸시 상품 수신자 고객 전체 조회");
		int pushRecpntProdctAllCount = pushDataAccessObject.pushRecpntProdctAllCount(pushVo);
		pushVo.setTotalRecordCount(pushRecpntProdctAllCount);
		List<OrdrValueObject> pushRecpntProdctAll = pushDataAccessObject.pushRecpntProdctAll(pushVo);
		
		return pushRecpntProdctAll;
	}
	//푸시 수신자 임시테이블 전체 조회
	public List<OrdrValueObject> selectPushRecpntAllTemp(PushValueObject pushVo2)throws Exception{
		Logger.getLogger("푸시 수신자 고객 전체 조회");
		int pushRecpntAllCount = pushDataAccessObject.selectPushRecpntAllTempCount();
		pushVo2.setTotalRecordCount(pushRecpntAllCount);
		System.out.println(pushVo2.getTotalRecordCount()+"!!!@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(pushVo2.getRecordCountPerPage()+"!@@@@!!!!!!!!!!!!!!!!");
		System.out.println(pushVo2.getFirstRecordIndex()+"!@@@!!!!!!!!!!!!!!!!");
		List<OrdrValueObject> pushRecpntAllTemp = pushDataAccessObject.selectPushRecpntAllTemp(pushVo2);
		return pushRecpntAllTemp;
	}
	//푸시 수신자 임시테이블 전체 조회
	public int selectPushRecpntAllTempCount()throws Exception{
		int custmrCount = pushDataAccessObject.selectPushRecpntAllTempCount();
		return custmrCount;
	}
	//푸시 수신자 임시테이블 추가
	public void insertPushRecpntAllTemp(String[] data)throws Exception{
		pushDataAccessObject.insertPushRecpntAllTemp(data);		
	}
	//푸시 수신자 임시테이블 삭제
	public void deletePushRecpntTemp(String[] data)throws Exception{
		pushDataAccessObject.deletePushRecpntTemp(data);;	
	}
	//푸시 수신자 임시테이블 전체삭제
	public void deletePushRecpntAllTemp()throws Exception{
		pushDataAccessObject.deletePushRecpntAllTemp();
	}
	//푸시 알림 발송 내역 조회
	public List<PushValueObject> pushNotfcatnDespRecrdList(PushValueObject pushVo)throws Exception{
		int selectPushNotfcatnDespRecrdCount = pushDataAccessObject.selectPushNotfcatnDespRecrdCount(pushVo);	// 푸시 발송 내역 총 갯수 (페이징)
		System.out.println("제발찍혀라"+selectPushNotfcatnDespRecrdCount);
		pushVo.setTotalRecordCount(selectPushNotfcatnDespRecrdCount); // 푸시 발송 내역 총 갯수 셋팅 (페이징)
		List<PushValueObject> pushNotfcatnDespRecrdList = pushDataAccessObject.pushNotfcatnDespRecrdList(pushVo);
		
		return pushNotfcatnDespRecrdList;
	}
	//푸시 알림 발송 내역 합계 조회
	public PushValueObject pushNotfcatnDespRecrdListSum()throws Exception{
		PushValueObject pushNotfcatnDespRecrdListSum = pushDataAccessObject.pushNotfcatnDespRecrdListSum();
		return pushNotfcatnDespRecrdListSum;
	}
	//푸시 알림 발송 내역 다시보내기
	public PushValueObject pushNotfcatnReDesp(PushValueObject pushVo)throws Exception{
		int despRecrdSeq = pushVo.getDespRecrdSeq();
		PushValueObject pushNotfcatnReDesp = pushDataAccessObject.pushNotfcatnReDesp(despRecrdSeq);
		return pushNotfcatnReDesp;
	}
	//푸시 알림 발송 내역 삭제
	public void pushNotfcatnDespRecrdDelete(String[] data) throws Exception {
		System.out.println(data+"삭제 서비스");		
		pushDataAccessObject.pushNotfcatnDespRecrdDelete(data);
	}
	//푸시 알림 최근 내역 추가
	public void insertPushDesp(PushValueObject pushVo)throws Exception{
		pushDataAccessObject.insertPushDesp(pushVo);
	}
	//푸시 알림 최근 내역 성공,실패값 추가
	public void updatePushDespCont(PushValueObject pushVo)throws Exception{
		pushDataAccessObject.updatePushDespCont(pushVo);
	}
	//푸시 알림 최근 내역 열어본 횟수 추가
	public void updateOpenPushDespCont(PushValueObject pushVo)throws Exception{
		pushDataAccessObject.updateOpenPushDespCont(pushVo);
	}
	//푸시 알림 최근 내역 조회
	public List<PushValueObject> selectPushHistory()throws Exception{
		return pushDataAccessObject.selectPushHistory();
	}
	//푸시 알림 최근 내역 추가
	public void insertPushHistory(PushValueObject pushVo)throws Exception{
		pushDataAccessObject.insertPushHistory(pushVo);
	}
	//푸시 알림 최근 내역 상세조회
	public PushValueObject selectPushHistoryDetl(int pushSeq) throws Exception{
		return pushDataAccessObject.selectPushHistoryDetl(pushSeq);
	}
}