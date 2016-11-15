package net.su.prodct.service;

import java.util.List;

import net.su.prodct.display.valueObject.MainDisplayValueObject;
import net.su.prodct.valueObject.TogthrValueObject;

public interface TogthrService {
	//다함께 상품 진열 관리 상품 삭제
	public void deleteToghrProdct(String[] data) throws Exception;
	//다함께 상품 진열 관리 상품 등록
	public void purchsProdctInsert(TogthrValueObject togthrValueObject)throws Exception;
	//다함께 상품 잔열 관리 리스트 조회
	public List<TogthrValueObject> togthrProdctList(TogthrValueObject togthrValueObject)throws Exception;
	//다함께 상품 진열 관리 상세조회
	public TogthrValueObject togthrDetail(TogthrValueObject togthrValueObject)throws Exception;
	//다함께 상품 진열 관리  수정
	public void togthrUpdate(TogthrValueObject togthrValueObject)throws Exception;
	//다함께 상품 진열 관리  수정화면에서 삭제
	public void togthrUpdateFrmDelete(int gropPurchsProdctSeq)throws Exception;
}
