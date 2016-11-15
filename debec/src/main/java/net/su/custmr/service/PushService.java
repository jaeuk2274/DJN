package net.su.custmr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.su.custmr.valueObject.OrdrValueObject;
import net.su.custmr.valueObject.PushValueObject;

public interface PushService {
	//푸시 메인
	public Map<String, Object> pushNotfcatnMain(int dateOption) throws Exception;
	//푸시 템플릿 리스트 조회
	public List<PushValueObject> pushNotfcatnList(PushValueObject pushVo) throws Exception;
	//푸시 템플릿 등록
	public void pushNotfcatTempltInsert(PushValueObject pushVo)throws Exception;
	//푸시 템플릿 상세 조회
	public PushValueObject pushNotfcatnTempltSelectDetl(int PushNotfcatnSeq)throws Exception;
	//푸시 템플릿 삭제
	public void pushTempltDelete(String[] data) throws Exception;
	//푸시 수정
	public void pushNotfcatnTempltUpdate(PushValueObject pushVo)throws Exception;
	//푸시 전체 수신자
	public List<OrdrValueObject> pushRecpntAll(PushValueObject pushVo)throws Exception;
	//푸시 상품 전체 수신자
	public List<OrdrValueObject> pushRecpntProdctAll(PushValueObject pushVo)throws Exception;
	//푸시 수신자 임시테이블 전체 조회
	public List<OrdrValueObject> selectPushRecpntAllTemp(PushValueObject pushVo2)throws Exception;
	//푸시 수신자 임시테이블 전체 조회
	public int selectPushRecpntAllTempCount()throws Exception;
	//푸시 수신자 임시테이블 추가
	public void insertPushRecpntAllTemp(String[] data)throws Exception;
	//푸시 수신자 임시테이블 삭제
	public void deletePushRecpntTemp(String[] data)throws Exception;
	//푸시 수신자 임시테이블 전체삭제
	public void deletePushRecpntAllTemp()throws Exception;
	//푸시알림 발송내역 리스트
	public List<PushValueObject> pushNotfcatnDespRecrdList(PushValueObject pushVo)throws Exception;
	//푸시 알림 발송 내역 합계
	public PushValueObject pushNotfcatnDespRecrdListSum()throws Exception;
	//푸시 알림 발송 내역 다시보내기
	public PushValueObject pushNotfcatnReDesp(PushValueObject pushVo)throws Exception;
	//푸시 알림 최근 내역 추가
	public void insertPushDesp(PushValueObject pushVo)throws Exception;
	//푸시 알림 최근 내역 성공,실패값 추가
	public void updatePushDespCont(PushValueObject pushVo)throws Exception;
	//푸시 알림 최근 내역 열어본 횟수 추가
	public void updateOpenPushDespCont(PushValueObject pushVo)throws Exception;
	//푸시 템플릿 삭제
	public void pushNotfcatnDespRecrdDelete(String[] data) throws Exception; // 휴면고객 일괄 삭제
	//푸시 알림 최근 내역 조회
	public List<PushValueObject> selectPushHistory()throws Exception;
	//푸시 알림 최근 내역 추가
	public void insertPushHistory(PushValueObject pushVo)throws Exception;
	//푸시 알림 최근 내역 상세조회
	public PushValueObject selectPushHistoryDetl(int pushSeq) throws Exception;
}