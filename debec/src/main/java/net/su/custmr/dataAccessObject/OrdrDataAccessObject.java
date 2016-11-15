package net.su.custmr.dataAccessObject;

import java.util.HashMap;
import java.util.List;

import net.su.custmr.valueObject.OrdrValueObject;
import net.su.logger.Logger;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class OrdrDataAccessObject extends SqlSessionDaoSupport {

	/**
		* 실시간 주문내역 리스트를 조회하는 메서드입니다.
		*
		* @param   OrdrValueObject ordrValueObject
		* @return  List<OrdrValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrValueObject> selectRealTimeOrdrRecrdList(OrdrValueObject ordrValueObject) throws Exception {
		System.out.println("OrdrDataAccessObject의  selectRealTimeOrdrRecrdList() 작동");		
		List<OrdrValueObject> realTimeOrdrRecrdList = getSqlSession().selectList("ordrMapper.selectRealTimeOrdrRecrdList", ordrValueObject);
		return realTimeOrdrRecrdList;
	}

	/**
		* 주문내역을 상세조회하는 메서드입니다.
		*
		* @param   OrdrValueObject ordrValueObject
		* @return  OrdrValueObject
		* @exception  예외처리 상황을 적어주세요
	*/
	public OrdrValueObject ordrRecrdRead(OrdrValueObject ordrValueObject) throws Exception {
		System.out.println("OrdrDataAccessObject의  ordrRecrdRead() 작동");		
		OrdrValueObject ordrRecrdVO = getSqlSession().selectOne("ordrMapper.ordrRecrdRead", ordrValueObject);
		return ordrRecrdVO;
	}
	
	/**
		* 주문내역의 상품 리스트를 조회하는 메서드입니다.
		*
		* @param   OrdrValueObject ordrValueObject
		* @return  List<OrdrValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrValueObject> ordrRecrdProdctRead(OrdrValueObject ordrValueObject) throws Exception {
		System.out.println("OrdrDataAccessObject의  ordrRecrdProdctRead() 작동");		
		List<OrdrValueObject> ordrRecrdProdctList = getSqlSession().selectList("ordrMapper.ordrRecrdProdctRead", ordrValueObject);
		return ordrRecrdProdctList;
	}
	
	
	/**
		* 주문내역 리스트를 조회하는 메서드입니다.
		*
		* @param   OrdrValueObject ordrValueObject
		* @return  List<OrdrValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrValueObject> ordrRecrdList(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);			
		List<OrdrValueObject> ordrRecrdList = getSqlSession().selectList("ordrMapper.ordrRecrdList", ordrValueObject);
		return ordrRecrdList;
	}
	
	/**
		* 주문의 총 개수를(검색조건 포함)조회하는 메서드입니다.(페이징 위한 수)
		*
		* @param   OrdrValueObject ordrValueObject
		* @return  int
		* @exception  예외처리 상황을 적어주세요
	*/
	public int selectOrdrCount(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);	
		int selectOrdrCount = getSqlSession().selectOne("ordrMapper.selectOrdrCount", ordrValueObject);	
		return selectOrdrCount;
	}
		
	/**
		* 주문내역 리스트에서 일괄 배달 접수를 하는 메서드입니다.(주문정보에 로그인한 직원정보가 배달원으로 업데이트)
		* (주문접수 -> 배달중만 가능)
		* @param   HashMap<String, Object> map
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/	
	public void changeOrdrStatGroup(HashMap<String, Object> map) throws Exception {
		Logger.info(null);
		getSqlSession().update("ordrMapper.changeOrdrStatGroup", map);	
	}
		
	
	/**
		* 주문내역 상세보기에서 배달 상태변경을 하는 메서드입니다.
		* 
		* @param   OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void changeOrdrStat(OrdrValueObject ordrValueObject) throws Exception{
		Logger.info(null);
		if(ordrValueObject.getOrdrStat().equals("주문접수")) {
			System.out.println("주문접수");
			getSqlSession().update("ordrMapper.changeOrdrStat1", ordrValueObject);	
		}
		else if(ordrValueObject.getOrdrStat().equals("배달중")) {
			System.out.println("배달중");
			getSqlSession().update("ordrMapper.changeOrdrStat2", ordrValueObject);	
		}
	}
	
	
	/**
		* 주문내역 상세보기에서 이미지 src를 수정하는 메서드입니다.
		* (사진 저장한 src 경로로 저장)
		* @param   OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void imgUpdate(OrdrValueObject ordrValueObject) throws Exception{
		OrdrValueObject imgVo = getSqlSession().selectOne("ordrMapper.selectImgSrc", ordrValueObject);	
		if(imgVo.getCartImg1().equals("Not")){
			ordrValueObject.setCartImg1(ordrValueObject.getCartImg());
			getSqlSession().update("ordrMapper.img1Update", ordrValueObject);	
		}
		else if(imgVo.getCartImg2().equals("Not")){
			ordrValueObject.setCartImg2(ordrValueObject.getCartImg());
			getSqlSession().update("ordrMapper.img2Update", ordrValueObject);
		}
		else if(imgVo.getCartImg3().equals("Not")){
			ordrValueObject.setCartImg3(ordrValueObject.getCartImg());
			getSqlSession().update("ordrMapper.img3Update", ordrValueObject);
		}
		else{ // 3개의 src에 사진이 모두 있을때.
			System.out.println("사진을 삭제해주세요.");
		}
	}	

	/**
		* 주문내역 상세보기에서 체크한 이미지 src를 수정하는 메서드입니다.
		* (null로 수정, 즉 사진 삭제)
		* @param   OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void imgDelete(OrdrValueObject ordrValueObject) throws Exception{
		getSqlSession().update("ordrMapper.imgDelete", ordrValueObject);
	}
	
	
	
	
}
