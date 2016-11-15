package net.su.custmr.dataAccessObject;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Logger;

import net.su.custmr.valueObject.CustmrValueObject;
import net.su.custmr.valueObject.OrdrValueObject;
import net.su.custmr.valueObject.PushValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class PushDataAccessObject extends SqlSessionDaoSupport{

	//푸시 메인 일일 통계
	public PushValueObject pushNotfcatnDayStatstcs()throws Exception{
		return getSqlSession().selectOne("pushMapper.pushNotfcatnDayStatstcs");
	}
	//푸시 메인 주 통계
	public PushValueObject pushNotfcatnWekStatstcs()throws Exception{
		return getSqlSession().selectOne("pushMapper.pushNotfcatnWekStatstcs");
	}
	//푸시 메인 달 통계
	public PushValueObject pushNotfcatnMonthStatstcs()throws Exception{
		return getSqlSession().selectOne("pushMapper.pushNotfcatnMonthStatstcs");
	}
	//푸시 메인 그래프
	public List<PushValueObject> pushNotfcatnStatstcsGraph(int dateOption)throws Exception{
		return getSqlSession().selectList("pushMapper.pushNotfcatnStatstcsGraph",dateOption);
	}
	//푸시 메인 그래프 통계 합계
	public PushValueObject pushNotfcatnStatstcsGraphSum(int dateOption)throws Exception{
		return getSqlSession().selectOne("pushMapper.pushNotfcatnStatstcsGraphSum",dateOption);
	}
	//푸시알람 템플릿 조회
	public List<PushValueObject> pushNotfcatnList(PushValueObject pushVo)throws Exception{
		System.out.println("푸시알림 텟플릿 조회 검색 DAO"+pushVo.getSearchWrd());
		System.out.println("푸시알림 텟플릿 조회 검색 DAO"+pushVo.getSearchCnd());
		return getSqlSession().selectList("pushMapper.pushNotfcatnList", pushVo);
	}
	//
	public int selectpushTempltCount(PushValueObject pushVo)throws Exception{
		int selectpushTempltCount = getSqlSession().selectOne("pushMapper.selectpushTempltCount", pushVo);	
		return selectpushTempltCount;
	}
	//푸시알림 템플릿 추가
	public void pushNotfcatTempltInsert(PushValueObject pushVo)throws Exception{
		System.out.println("푸시알림 템플릿 등록 DAO");
		
		getSqlSession().insert("pushMapper.pushNotfcatTempltInsert", pushVo);
	}
	//푸시알림 템플릿 상세조회
	public PushValueObject pushNotfcatnTempltSelectDetl(int getPushNotfcatnSeq)throws Exception{
		System.out.println("푸시알림 템플릿 조쇠"+getPushNotfcatnSeq);
		return getSqlSession().selectOne("pushMapper.pushNotfcatnTempltSelectDetl", getPushNotfcatnSeq);
	}
	//푸시알림 템플릿 삭제
	public void pushTempltDelete(String[] data)throws Exception{
		System.out.println("푸시알림 템플릿 삭제");
		for (int i=0; i<data.length; i++){
			long gropPurchsSeq = Long.parseLong(data[i]);
			getSqlSession().delete("pushMapper.pushTempltDelete", gropPurchsSeq);	
		}	
	}
	//푸시 템플릿 수정
	public void pushNotfcatnTempltUpdate(PushValueObject pushVo)throws Exception{
		System.out.println("템플릿 수정 Dao"+pushVo.getPushTitl());
		getSqlSession().update("pushMapper.pushNotfcatnTempltUpdate", pushVo);
	}
	//푸시 수신자 고객 전체 수
	public int pushRecpntAllCount(PushValueObject pushVo)throws Exception{
		return getSqlSession().selectOne("pushMapper.pushRecpntAllCount", pushVo);
	}
	//푸시 수신자 전체 조회
	public List<OrdrValueObject> pushRecpntAll(PushValueObject pushVo)throws Exception{
		
		return getSqlSession().selectList("pushMapper.pushRecpntAll", pushVo);
	}
	//푸시 상품 수신자 고객 전체 수
	public int pushRecpntProdctAllCount(PushValueObject pushVo)throws Exception{
		return getSqlSession().selectOne("pushMapper.pushRecpntProdctAllCount", pushVo);
	}
	//푸시 상품 수신자 전체 조회
	public List<OrdrValueObject> pushRecpntProdctAll(PushValueObject pushVo)throws Exception{
		
		return getSqlSession().selectList("pushMapper.pushRecpntProdctAll", pushVo);
	}
	//푸시 수신자 임시테이블 전체 조회
	public List<OrdrValueObject> selectPushRecpntAllTemp(PushValueObject pushVo2)throws Exception{
		System.out.println(pushVo2.getTotalRecordCount()+"@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(pushVo2.getRecordCountPerPage()+"!!!!!!!!!!!!!!!!!");
		System.out.println(pushVo2.getFirstRecordIndex()+"!!!!!!!!!!!!!!!!!");
		 
		return getSqlSession().selectList("pushMapper.selectPushRecpntAllTemp", pushVo2);
	}
	//푸시 수신자 임시테이블 전체 조회
	public int selectPushRecpntAllTempCount()throws Exception{
		int custmrCount = getSqlSession().selectOne("pushMapper.selectPushRecpntAllTempCount");
		return custmrCount;
	}
	//푸시 수신자 임시테이블 추가
	public void insertPushRecpntAllTemp(String[] data)throws Exception{
		for (int i=0; i<data.length; i++){
			int custmrSeq = Integer.parseInt(data[i]);
			getSqlSession().insert("pushMapper.insertPushRecpntAllTemp", custmrSeq);	
		}		
	}
	//푸시 수신자 임시테이블 삭제
	public void deletePushRecpntTemp(String[] data)throws Exception{
		for (int i=0; i<data.length; i++){
			int custmrSeq = Integer.parseInt(data[i]);
			getSqlSession().delete("pushMapper.deletePushRecpntTemp", custmrSeq);	
		}		
	}
	//푸시 수신자 임시테이블 전체삭제
	public void deletePushRecpntAllTemp()throws Exception{
		getSqlSession().delete("pushMapper.deletePushRecpntAllTemp");	
	}
	public List<PushValueObject> pushNotfcatnDespRecrdList(PushValueObject pushVo)throws Exception{
		System.out.println("페이지 갯수 확인"+pushVo.getTotalRecordCount());
		System.out.println("검색값뜨는지확인"+pushVo.getSearchWrd());
		List<PushValueObject> pushNotfcatnDespRecrdList = getSqlSession().selectList("pushMapper.pushNotfcatnDespRecrdList", pushVo);
		return pushNotfcatnDespRecrdList;
	}
	// 푸시 알림 발송 내역 전체 갯수 조회
	public int selectPushNotfcatnDespRecrdCount(PushValueObject pushVo) throws Exception {
		System.out.println("CustmrDateAccessObject의  selectCustmrCount() 작동");		
		int selectPushNotfcatnDespRecrdCount = getSqlSession().selectOne("pushMapper.selectPushNotfcatnDespRecrdCount", pushVo);	
		return selectPushNotfcatnDespRecrdCount;
	}
	//푸시 알림 발송 내역 합계 조회
	public PushValueObject pushNotfcatnDespRecrdListSum()throws Exception{
		return getSqlSession().selectOne("pushMapper.pushNotfcatnDespRecrdListSum");
	}
	//푸시 알림 발송 내역 다시보내기
	public PushValueObject pushNotfcatnReDesp(int despRecrdSeq)throws Exception{
		return getSqlSession().selectOne("pushMapper.pushNotfcatnReDesp", despRecrdSeq);
	}
	//푸시알림 템플릿 삭제
	public void pushNotfcatnDespRecrdDelete(String[] data) throws Exception {
		System.out.println("PushDateAccessObject의  pushNotfcatnDespRecrdDelete() 작동");	
		for (int i=0; i<data.length; i++){
			long gropPurchsSeq = Long.parseLong(data[i]);
			getSqlSession().delete("pushMapper.pushNotfcatnDespRecrdDelete", gropPurchsSeq);	
		}
	}
	//푸시 알림 최근 내역 추가
	public void insertPushDesp(PushValueObject pushVo)throws Exception{
		getSqlSession().insert("pushMapper.insertPushDesp", pushVo);
	}
	//푸시 알림 최근 내역 성공,실패값 추가
	public void updatePushDespCont(PushValueObject pushVo)throws Exception{
		getSqlSession().update("pushMapper.updatePushDesp", pushVo);
	}
	//푸시 알림 최근 내역 열어본 횟수 추가
	public void updateOpenPushDespCont(PushValueObject pushVo)throws Exception{
		getSqlSession().update("pushMapper.updateOpenPushDesp", pushVo);
	}
	//푸시 알림 최근 내역 조회
	public List<PushValueObject> selectPushHistory()throws Exception{
		return getSqlSession().selectList("pushMapper.selectPushHistory");
	}
	//푸시 알림 최근 내역 추가
	public void insertPushHistory(PushValueObject pushVo)throws Exception{
		getSqlSession().insert("pushMapper.insertPushHistory", pushVo);
	}
	//푸시 알림 최근 내역 상세조회
	public PushValueObject selectPushHistoryDetl(int pushSeq) throws Exception{
		return getSqlSession().selectOne("pushMapper.selectPushHistoryDetl", pushSeq);
	}
}