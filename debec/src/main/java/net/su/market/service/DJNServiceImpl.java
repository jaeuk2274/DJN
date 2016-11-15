package net.su.market.service;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import net.su.login.valueObject.LoginValueObject;
import net.su.market.dataAccessObject.DJNDataAccessObject;
import net.su.market.valueObject.DJNPointValueObject;
import net.su.market.valueObject.DJNValueObject;
import net.su.market.valueObject.RecmndResnValueObject;
import net.su.market.valueObject.RecmndValueObject;

import org.springframework.stereotype.Service;

@Service
public class DJNServiceImpl implements DJNService{
	@Resource DJNDataAccessObject djnDao;
	
	/**
		* 오늘의 년월(YY-MM)을 조회하는 메서드입니다.
		*
		* @param   
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	public String selectTYearMonth() throws Exception{
		
		Calendar oCalendar = Calendar.getInstance( );  // 현재 날짜/시간 등의 각종 정보 얻기
		System.out.println("현재 년: " +  oCalendar.get(Calendar.YEAR));
		int tMonth = oCalendar.get(Calendar.MONTH); //이전 월 조회()
		String tMonth2 = null;
		if (("" + tMonth).length() == 1) { // 월자리가 한 자리 숫자의 경우 앞에 "0"붙히기 
			tMonth2 = "0" + tMonth; } 
		System.out.println("현재 년월: " +  oCalendar.get(Calendar.YEAR)+ "-" + tMonth2);
		String tYearMonth = oCalendar.get(Calendar.YEAR)+ "-" + tMonth2;// 조회할 년월
		
		return tYearMonth;
	}
	
	/**
		* 이달의 대장남 정보를 조회하는 메서드입니다.
		*
		* @param   DJNValueObject
		* @return  DJNValueObject
		* @exception  예외처리 상황을 적어주세요
	*/
	public DJNValueObject selectDJN(DJNValueObject djnVo) throws Exception {
		DJNValueObject djnVo2 = new DJNValueObject();
		 int DJNPointDivision = djnDao.selectDJNPointDivision(djnVo); // 대장남 포인트 구분
		 if(DJNPointDivision >0) {
			 djnVo2 = djnDao.selectDJN(djnVo);
			 return djnVo2;
		 }
		 else {
			 return djnVo2;
		 }
	}
	
	/**
		* 대장남 포인트 구분(포인트 값 유무)을 조회하는 메서드입니다.
		*
		* @param   DJNValueObject
		* @return  DJNValueObject
		* @exception  예외처리 상황을 적어주세요
	*/
	public int selectDJNPointDivision(DJNValueObject djnVo) throws Exception{
		 int DJNPointDivision = djnDao.selectDJNPointDivision(djnVo); // 대장남 포인트 구분
		return DJNPointDivision;
	}
	
	/**
		* 이달의 대장남 고객 추천사유 조회하는 메서드입니다.
		*
		* @param   DJNValueObject
		* @return  List<RecmndResnValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<RecmndResnValueObject> selectDjnRecmndResn(DJNValueObject djnVo2) throws Exception{
		System.out.println("이달의 대장남 고객 추천사유 조회");
		List<RecmndResnValueObject> djnRecmndResn = djnDao.selectDjnRecmndResn(djnVo2);
		return djnRecmndResn;
	}
	
	/**
		* 직원별 대장남 포인트 조회하는 메서드입니다.
		*
		* @param   DJNValueObject
		* @return  List<DJNValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<DJNValueObject> selectDJNPointList(DJNValueObject djnVo) throws Exception{
		List<DJNValueObject> djnPointList = djnDao.selectDJNPointList(djnVo);
		return djnPointList;
	}
	
	/**
		* 대장남 포인트 선정기준을 조회하는 메서드입니다.
		*
		* @param   
		* @return  DJNPointValueObject
		* @exception  예외처리 상황을 적어주세요
	*/
	public DJNPointValueObject selectDjnPointPerct() throws Exception{
		DJNPointValueObject djnPointPerct = djnDao.selectDjnPointPerct();
		return djnPointPerct;
	}
	
	/**
		* 직원별 고객 추천사유를 조회하는 메서드입니다.
		*
		* @param   DJNValueObject
		* @return  List<RecmndResnValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<RecmndResnValueObject> selectRecmndResn(DJNValueObject djnVo) throws Exception{
		List<RecmndResnValueObject> recmndResn = djnDao.selectRecmndResn(djnVo);
		return recmndResn;
	}
	
	/**
		*대장남 포인트 선정기준을 수정하는 메서드입니다.
		*
		* @param   DJNPointValueObject
		* @return  updateDjnPointPerct
		* @exception  예외처리 상황을 적어주세요
	*/
	public void updateDjnPointPerct(DJNPointValueObject djnPointVo) throws Exception{
		djnDao.updateDjnPointPerct(djnPointVo);
	}
	
	/**
		*모든직원 리스트를 조회하는 메서드입니다.
		*
		* @param   
		* @return  List<DJNValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<DJNValueObject> selectEmpList() throws Exception{
		List<DJNValueObject> empList = djnDao.selectEmpList();
		return empList;
	}
	
	/**
		*(수상자 제외)직원 리스트를 조회하는 메서드입니다.
		*
		* @param   int
		* @return  List<DJNValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<DJNValueObject> selectExceptAwrdEmpList(int empSeq) throws Exception{
		List<DJNValueObject> exceptAwrdEmpList = djnDao.selectExceptAwrdEmpList(empSeq);
		return exceptAwrdEmpList;
	}
	
	
	/**
		*직원 추천 메서드입니다.
		*
		* @param   RecmndValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void recmndEmp(RecmndValueObject recmndVo) throws Exception{
		djnDao.recmndEmp(recmndVo);
	}

	/**
		*직원 추천 여부 확인 메서드 입니다.
		*
		* @param   LoginValueObject
		* @return  int
		* @exception  예외처리 상황을 적어주세요
	*/
	public int memRecmnChck(LoginValueObject loginUser) throws Exception{
		int memRecmnChck = djnDao.memRecmnChck(loginUser);
		return memRecmnChck;
	}
}
