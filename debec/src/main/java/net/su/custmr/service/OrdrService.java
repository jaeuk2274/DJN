package net.su.custmr.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.su.custmr.valueObject.OrdrValueObject;

public interface OrdrService {

	/**
		* 실시간 주문내역 리스트를 조회하는 메서드입니다.
		*
		* @param   OrdrValueObject ordrValueObject
		* @return  List<OrdrValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrValueObject> selectRealTimeOrdrRecrdList(OrdrValueObject ordrValueObject) throws Exception; 
	
	
	/**
		* 주문내역 정보를 상세조회하는 메서드입니다.
		*
		* @param   OrdrValueObject ordrValueObject
		* @return  OrdrValueObject
		* @exception  예외처리 상황을 적어주세요
	*/
	public OrdrValueObject ordrRecrdRead(OrdrValueObject ordrValueObject) throws Exception; 
	
	/**
		* 주문내역의 상품 리스트를 조회하는 메서드입니다.
		*
		* @param   OrdrValueObject ordrValueObject
		* @return  List<OrdrValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrValueObject> ordrRecrdProdctRead(OrdrValueObject ordrValueObject) throws Exception ; 
	
	
	/**
		* 주문내역 리스트를 조회하는 메서드입니다.
		*
		* @param   OrdrValueObject ordrValueObject
		* @return  List<OrdrValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrValueObject> ordrRecrdList(OrdrValueObject ordrValueObject) throws Exception; 
	
	/**
		* 주문내역 리스트에서 일괄 배달 접수를 하는 메서드입니다.(주문정보에 로그인한 직원정보가 배달원으로 업데이트)
		* (주문접수 -> 배달중만 가능)
		* @param   OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/	
	public void changeOrdrStatGroup(OrdrValueObject ordrValueObject) throws Exception; 
	
	/**
		* 주문내역 상세보기에서 배달 상태변경을 하는 메서드입니다.
		* 
		* @param   OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void changeOrdrStat(OrdrValueObject ordrValueObject) throws Exception; // 배달 상태 변경
		
	/**
		* 주문내역 상세보기에서 이미지 등록을 하는 메서드입니다.
		* 
		* @param   MultipartHttpServletRequest request, OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void imgInsert(MultipartHttpServletRequest request, OrdrValueObject ordrValueObject) throws Exception;
	
	
	/**
		* 주문내역 상세보기에서 체크박스 선택한 이미지 삭제를 하는 메서드입니다.
		* 
		* @param   OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void imgDelete(OrdrValueObject ordrValueObject) throws Exception;
		
	
	
}
