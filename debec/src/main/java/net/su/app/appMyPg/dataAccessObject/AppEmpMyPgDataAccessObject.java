package net.su.app.appMyPg.dataAccessObject;

import java.util.HashMap;
import java.util.List;

import net.su.custmr.valueObject.CustmrValueObject;
import net.su.custmr.valueObject.OrdrValueObject;
import net.su.logger.Logger;
import net.su.market.valueObject.EmpValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class AppEmpMyPgDataAccessObject extends SqlSessionDaoSupport{	
	/**
    * 직원 푸시수신 여부 조회하는 메서드
    *
    * @param   int
    * @return  String
    * @exception  Exception
    */
	public String selectEmpPushCheck(int empSeq) throws Exception{	
		Logger.info(null);
		String pushCheck = getSqlSession().selectOne("appEmpMyPgMapper.selectEmpPushCheck", empSeq);
		return pushCheck;
	}

	/**
    * 직원 푸시수신 여부 수정하는 메서드
    *
    * @param   int
    * @return  void
    * @exception  Exception
    */
	public void updateEmpPushCheck(int empSeq) throws Exception{	
		Logger.info(null);
		getSqlSession().update("appEmpMyPgMapper.updateEmpPushCheck", empSeq);	
	}
	
	/**
    * 장바구니 사진 업데이트 하는 메서드
    *
    * @param   OrdrValueObject
    * @return  void
    * @exception  Exception
    */
	public void updateOrdrImg(OrdrValueObject ordrVo) throws Exception{	
		Logger.info(null);
		getSqlSession().update("appEmpMyPgMapper.updateOrdrImg", ordrVo);	
	}
	
	/**
    * 배송 상태 변경 하는 메서드
    *
    * @param   int
    * @return  void
    * @exception  Exception
    */
	public void updateOrdrStat(int ordrNumbrSeq, int empSeq) throws Exception{	
		Logger.info(null);
		System.out.println("empSeq="+empSeq);
		HashMap<String, Integer> empMap = new HashMap<String, Integer>();
		empMap.put("ordrNumbrSeq", ordrNumbrSeq);
		empMap.put("empSeq", empSeq);
		getSqlSession().update("appEmpMyPgMapper.updateOrdrStat", empMap);	
	}
	
	/**
    * 직원 추천을 하는 메서드
    *
    * @param   int, int
    * @return  void
    * @exception  Exception
    */
	public void insertEmpRecmnd(HashMap<String, Integer> empSeqMap) throws Exception{	
		Logger.info(null);
		getSqlSession().insert("appEmpMyPgMapper.insertEmpRecmnd", empSeqMap);	
	}
	
	/**
    * 직원 추천 목록을 조회하는 메서드
    *
    * @param   int
    * @return  List<EmpValueObject>
    * @exception  Exception
    */
	public List<EmpValueObject> selectEmpRecmndList(int empSeq) throws Exception{	
		Logger.info(null);
		List<EmpValueObject> empRecmndList = getSqlSession().selectList("appEmpMyPgMapper.selectEmpRecmndList", empSeq);	
		return empRecmndList;
	}	
	
	/**
    * 배송 상태 변경 시 고객 정보 조회하는 메서드
    *
    * @param   int
    * @return  EmpValueObject
    * @exception  Exception
    */
	public CustmrValueObject selectCustmrInfo(int ordrNumbrSeq) throws Exception{
		Logger.info(null);
		CustmrValueObject custmrInfo = getSqlSession().selectOne("appEmpMyPgMapper.selectCustmrInfo", ordrNumbrSeq);
		return custmrInfo;
	}
	
	/**
    * 관리자 이름 조회하는 메서드
    *
    * @param   int
    * @return  String
    * @exception  Exception
    */
	public String selectEmpNme(int empSeq) throws Exception{
		Logger.info(null);
		String empNme = getSqlSession().selectOne("appEmpMyPgMapper.selectEmpNme", empSeq);
		return empNme;
	}

	/**
    * 배송상태별 주문건수를 조회하는 메서드
    *
    * @param   int
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectOrdrCountList() throws Exception{	
		Logger.info(null);
		List<OrdrValueObject> ordrCountList = getSqlSession().selectList("appEmpMyPgMapper.selectOrdrCountList");	
		return ordrCountList;
	}
	
	/**
    * 주문 취소 목록을 조회하는 메서드
    *
    * @param   void
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectOrdrCancelList() throws Exception{	
		Logger.info(null);
		List<OrdrValueObject> ordrCancelList = getSqlSession().selectList("appEmpMyPgMapper.selectOrdrCancelList");	
		return ordrCancelList;
	}
	
	/**
    * 공동구매 주문 취소 목록을 조회하는 메서드
    *
    * @param   void
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectDahamgaeOrdrCancelList() throws Exception{	
		Logger.info(null);
		List<OrdrValueObject> ordrCancelList = getSqlSession().selectList("appEmpMyPgMapper.selectDahamgaeOrdrCancelList");	
		return ordrCancelList;
	}
	
	/**
    * 실시간 주문 목록을 조회하는 메서드
    *
    * @param   void
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectEmpRealTimeOrdrList() throws Exception{	
		Logger.info(null);
		List<OrdrValueObject> empRealTimeOrdrList = getSqlSession().selectList("appEmpMyPgMapper.selectEmpRealTimeOrdrList");	
		return empRealTimeOrdrList;
	}
	
	/**
    * 실시간 공동구매 주문 목록을 조회하는 메서드
    *
    * @param   void
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectEmpDahamgaeRealTimeOrdrList() throws Exception{	
		Logger.info(null);
		List<OrdrValueObject> empRealTimeOrdrList = getSqlSession().selectList("appEmpMyPgMapper.selectEmpDahamgaeRealTimeOrdrList");	
		return empRealTimeOrdrList;
	}
	
	/**
    * 배송 목록을 조회하는 메서드
    *
    * @param   int
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectEmpShipngList(int ordrStatSeq) throws Exception{	
		Logger.info(null);
		if(ordrStatSeq == 0){
			List<OrdrValueObject> empShipngList = getSqlSession().selectList("appEmpMyPgMapper.selectEmpShipngAllList");
			return empShipngList;
		}
		else if(ordrStatSeq == 10){
			List<OrdrValueObject> empShipngList = getSqlSession().selectList("appEmpMyPgMapper.selectEmpShipngPanelList");
			return empShipngList;
		}
		else{
			List<OrdrValueObject> empShipngList = getSqlSession().selectList("appEmpMyPgMapper.selectEmpShipngList", ordrStatSeq);
			return empShipngList;
		}		
	}
	
	/**
    * 공동구매 배송 목록을 조회하는 메서드
    *
    * @param   int
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectEmpDahamgaeShipngList(int ordrStatSeq) throws Exception{	
		Logger.info(null);
		if(ordrStatSeq == 0){
			List<OrdrValueObject> empDahamgaeShipngList = getSqlSession().selectList("appEmpMyPgMapper.selectEmpDahamgaeShipngAllList");
			return empDahamgaeShipngList;
		}
		else{
			List<OrdrValueObject> empDahamgaeShipngList = getSqlSession().selectList("appEmpMyPgMapper.selectEmpDahamgaeShipngList", ordrStatSeq);
			return empDahamgaeShipngList;
		}		
	}

	/**
    * 주문 상세 조회하는 메서드
    *
    * @param   int
    * @return  OrdrValueObject
    * @exception  Exception
    */
	public OrdrValueObject selectEmpOrdrRead(int ordrNumbrSeq, int ordrType) throws Exception{
		Logger.info(null);
		OrdrValueObject empOrdrVo = new OrdrValueObject();
		if(ordrType == 0){
			empOrdrVo = getSqlSession().selectOne("appEmpMyPgMapper.selectEmpOrdrRead", ordrNumbrSeq);
		}
		else{
			empOrdrVo = getSqlSession().selectOne("appEmpMyPgMapper.selectEmpDahamgaeOrdrRead", ordrNumbrSeq);
		}
		return empOrdrVo;
	}
	
	/**
    * 주문 취소 상세 조회의 상품 리스트 조회하는 메서드
    *
    * @param   int
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectOrdrCancelProdctList(int ordrNumbrSeq) throws Exception{	
		Logger.info(null);
		List<OrdrValueObject> ordrCancelProdctList = getSqlSession().selectList("appEmpMyPgMapper.selectOrdrCancelProdctList", ordrNumbrSeq);	
		return ordrCancelProdctList;
	}
	
	
	
	/**
    * 주문 취소 상세 조회하는 메서드
    *
    * @param   int
    * @return  OrdrValueObject
    * @exception  Exception
    */
	public OrdrValueObject selectEmpOrdrCancelRead(int ordrNumbrSeq) throws Exception{
		Logger.info(null);
		OrdrValueObject empOrdrVo = getSqlSession().selectOne("appEmpMyPgMapper.selectOrdrCancelRead", ordrNumbrSeq);
		return empOrdrVo;
	}
	
	/**
    * 공동구매 주문 취소 상세 조회의 상품 리스트 조회하는 메서드
    *
    * @param   int
    * @return  List<OrdrValueObject>
    * @exception  Exception
    */
	public List<OrdrValueObject> selectDahamgaeOrdrCancelProdctList(int ordrNumbrSeq) throws Exception{	
		Logger.info(null);
		List<OrdrValueObject> ordrCancelProdctList = getSqlSession().selectList("appEmpMyPgMapper.selectDahamgaeOrdrCancelProdctList", ordrNumbrSeq);	
		return ordrCancelProdctList;
	}
	
	
	
	/**
    * 공동구매 주문 취소 상세 조회하는 메서드
    *
    * @param   int
    * @return  OrdrValueObject
    * @exception  Exception
    */
	public OrdrValueObject selectDahamgaeOrdrCancelRead(int ordrNumbrSeq) throws Exception{
		Logger.info(null);
		OrdrValueObject empOrdrVo = getSqlSession().selectOne("appEmpMyPgMapper.selectDahamgaeOrdrCancelRead", ordrNumbrSeq);
		return empOrdrVo;
	}
	
	/**
    * 회원정보 상세 조회하는 메서드
    *
    * @param   int
    * @return  EmpValueObject
    * @exception  Exception
    */
	public EmpValueObject selectEmpInfo(int empSeq) throws Exception{	
		Logger.info(null);
		EmpValueObject selectEmpInfo = getSqlSession().selectOne("appEmpMyPgMapper.selectEmpInfo", empSeq);	
		return selectEmpInfo;
	}
	
	/**
    * 회원정보 확인 값 조회하는 메서드
    *
    * @param   EmpValueObject
    * @return  EmpValueObject
    * @exception  Exception
    */
	public EmpValueObject selectEmpInfoCount(EmpValueObject empVo) throws Exception{	
		Logger.info(null);
		EmpValueObject selectEmpInfo = getSqlSession().selectOne("appEmpMyPgMapper.selectEmpInfoCount", empVo);	
		return selectEmpInfo;
	}
	
	/**
    * 회원정보 수정하는 메서드
    *
    * @param   EmpValueObject
    * @return  void
    * @exception  Exception
    */
	public void updateEmpInfo(EmpValueObject empVo) throws Exception{	
		Logger.info(null);
		getSqlSession().update("appEmpMyPgMapper.updateEmpInfo", empVo);	
	}
	
	/**
    * 회원정보 비밀번호 수정하는 메서드
    *
    * @param   EmpValueObject
    * @return  void
    * @exception  Exception
    */
	public void updateEmpPw(EmpValueObject empVo) throws Exception{	
		Logger.info(null);
		getSqlSession().update("appEmpMyPgMapper.updateEmpPw", empVo);	
	}
}
