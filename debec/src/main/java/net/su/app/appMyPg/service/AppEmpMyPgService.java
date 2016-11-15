package net.su.app.appMyPg.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.su.custmr.valueObject.CustmrValueObject;
import net.su.custmr.valueObject.OrdrValueObject;
import net.su.logger.Logger;
import net.su.market.valueObject.EmpValueObject;
import net.su.prodct.valueObject.ProdctValueObject;
import net.su.security.Base64Utils;

public interface AppEmpMyPgService {
	/**
    * 직원 푸시수신 여부 수정하는 메서드
    *
    * @param   int
    * @return  void
    * @exception  Exception
    */
	public void updateEmpPushCheck(int empSeq) throws Exception;
	
	/**
    * 직원 푸시수신 여부 조회하는 메서드
    *
    * @param   int
    * @return  String
    * @exception  Exception
    */
	public String selectEmpPushCheck(int empSeq) throws Exception;
	
	/**
    * 배송 상태 변경 시 고객 정보 조회하는 메서드
    *
    * @param   int
    * @return  EmpValueObject
    * @exception  Exception
    */
	public CustmrValueObject selectCustmrInfo(int ordrNumbrSeq) throws Exception;
	
	/**
    * 배송 상태 변경 하는 메서드
    *
    * @param   int
    * @return  void
    * @exception  Exception
    */
	public void updateOrdrStat(int ordrNumbrSeq, int empSeq) throws Exception;
	
	/**
    * 직원 추천을 하는 메서드
    *
    * @param   int, int
    * @return  void
    * @exception  Exception
    */
	public void insertEmpRecmnd(HashMap<String, Integer> empSeqMap) throws Exception;
	
	/**
    * 직원 추천 목록을 조회하는 메서드
    *
    * @param   int
    * @return  List<EmpValueObject>
    * @exception  Exception
    */
	public List<EmpValueObject> selectEmpRecmndList(int empSeq) throws Exception;
	
	/**
    * 관리자 이름 조회하는 메서드
    *
    * @param   int
    * @return  String
    * @exception  Exception
    */
	public String selectEmpNme(int empSeq) throws Exception;
	
	/**
    * 배송상태별 주문건수를 조회하는 메서드
    *
    * @param   int
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectOrdrCountList() throws Exception;
	
	/**
    * 배송 종류별 목록을 조회하는 메서드
    *
    * @param   int
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectEmpShipngList(int ordrStatSeq) throws Exception;
	
	/**
    * 공동구매 배송 목록을 조회하는 메서드
    *
    * @param   int
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectEmpDahamgaeShipngList(int ordrStatSeq) throws Exception;
	
	/**
    * 주문 취소 목록을 조회하는 메서드
    *
    * @param   void
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectOrdrCancelList() throws Exception;
	
	/**
    * 공동구매 주문 취소 목록을 조회하는 메서드
    *
    * @param   void
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectDahamgaeOrdrCancelList() throws Exception;
	
	/**
    * 주문 상세 조회하는 메서드
    *
    * @param   int
    * @return  OrdrValueObject
    * @exception  Exception
    */
	public OrdrValueObject selectEmpOrdrRead(int ordrNumbrSeq, int ordrType) throws Exception;
		
	/**
    * 주문 취소 상세 조회하는 메서드
    *
    * @param   int
    * @return  OrdrValueObject
    * @exception  Exception
    */
	public OrdrValueObject selectEmpOrdrCancelRead(int ordrNumbrSeq) throws Exception;
	
	/**
    * 주문 취소 상세 조회의 상품 리스트 조회하는 메서드
    *
    * @param   int
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectOrdrCancelProdctList(int ordrNumbrSeq) throws Exception;
	
	/**
    * 공동구매 주문 취소 상세 조회의 상품 리스트 조회하는 메서드
    *
    * @param   int
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectDahamgaeOrdrCancelProdctList(int ordrNumbrSeq) throws Exception;
	
	
	/**
    * 공동구매 주문 취소 상세 조회하는 메서드
    *
    * @param   int
    * @return  OrdrValueObject
    * @exception  Exception
    */
	public OrdrValueObject selectDahamgaeOrdrCancelRead(int ordrNumbrSeq) throws Exception;
	
	/**
    * 실시간 주문 목록을 조회하는 메서드
    *
    * @param   void
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectEmpRealTimeOrdrList() throws Exception;
	
	/**
    * 실시간 공동구매 주문 목록을 조회하는 메서드
    *
    * @param   void
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectEmpDahamgaeRealTimeOrdrList() throws Exception;
	
	/**
    * 회원정보 상세 조회하는 메서드
    *
    * @param   int
    * @return  EmpValueObject
    * @exception  Exception
    */
	public EmpValueObject selectEmpInfo(int empSeq) throws Exception;
	
	/**
    * 회원정보 확인 값 조회하는 메서드
    *
    * @param   EmpValueObject
    * @return  int
    * @exception  Exception
    */
	public EmpValueObject selectEmpInfoCount(EmpValueObject empVo) throws Exception;
	
	/**
    * 회원정보 수정하는 메서드
    *
    * @param   EmpValueObject
    * @return  void
    * @exception  Exception
    */
	public void updateEmpInfo(EmpValueObject empVo) throws Exception;
	
	/**
    * 회원정보 비밀번호 수정하는 메서드
    *
    * @param   EmpValueObject
    * @return  void
    * @exception  Exception
    */
	public void updateEmpPw(EmpValueObject empVo) throws Exception;

	public String ordrImgUpload(MultipartHttpServletRequest request, OrdrValueObject ordrVo) throws Exception;
	
	public void ordrImgDelete(OrdrValueObject ordrVo) throws Exception;
}
