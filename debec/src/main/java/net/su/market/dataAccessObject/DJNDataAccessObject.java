package net.su.market.dataAccessObject;

import java.util.List;

import net.su.login.valueObject.LoginValueObject;
import net.su.market.valueObject.DJNPointValueObject;
import net.su.market.valueObject.DJNValueObject;
import net.su.market.valueObject.RecmndResnValueObject;
import net.su.market.valueObject.RecmndValueObject;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DJNDataAccessObject extends SqlSessionDaoSupport{
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
		* 이달의 대장남 정보를 조회하는 메서드입니다.
		*
		* @param   DJNValueObject
		* @return  DJNValueObject
		* @exception  예외처리 상황을 적어주세요
	*/
	public DJNValueObject selectDJN(DJNValueObject djnVo) throws Exception {
		DJNValueObject djnVo2 = getSqlSession().selectOne("DJNMapper.selectDJN",djnVo);
		return djnVo2;
	}
	
	/**
		* 대장남 포인트 구분(포인트 값 유무)을 조회하는 메서드입니다.
		*
		* @param   DJNValueObject
		* @return  DJNValueObject
		* @exception  예외처리 상황을 적어주세요
	*/
	public int selectDJNPointDivision(DJNValueObject djnVo) throws Exception{
		 int DJNPointDivision = getSqlSession().selectOne("DJNMapper.selectDJNPointDivision",djnVo); 
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
		System.out.println("selectDjnRecmndResn 실행!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		List<RecmndResnValueObject> djnRecmndResn = getSqlSession().selectList("DJNMapper.selectDjnRecmndResn",djnVo2);
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
		List<DJNValueObject> djnPointList = getSqlSession().selectList("DJNMapper.selectDJNPointList",djnVo);
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
		DJNPointValueObject djnPointPerct = getSqlSession().selectOne("DJNMapper.selectDjnPointPerct");
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
		List<RecmndResnValueObject> recmndResn = getSqlSession().selectList("DJNMapper.selectRecmndResn",djnVo);
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
		getSqlSession().update("DJNMapper.updateDjnPointPerct",djnPointVo);
	}
	
	/**
		*모든직원 리스트를 조회하는 메서드입니다.
		*
		* @param   
		* @return  List<DJNValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<DJNValueObject> selectEmpList() throws Exception{
		List<DJNValueObject> empList = getSqlSession().selectList("DJNMapper.selectEmpList");
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
		List<DJNValueObject> exceptAwrdEmpList = getSqlSession().selectList("DJNMapper.selectExceptAwrdEmpList",empSeq);
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
		getSqlSession().insert("DJNMapper.recmndEmp",recmndVo);
	}
	

	/**
		*직원 추천 여부 확인 메서드 입니다.
		*
		* @param   LoginValueObject
		* @return  int
		* @exception  예외처리 상황을 적어주세요
	*/
	public int memRecmnChck(LoginValueObject loginUser) throws Exception
	{
		int userSeq = loginUser.getEmpSeq();
		int memRecmnChck = getSqlSession().selectOne("DJNMapper.memRecmnChck",userSeq);

		return memRecmnChck;
	}
}
