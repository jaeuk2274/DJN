package net.su.prodct.service;

import java.util.List;

import javax.annotation.Resource;

import net.su.logger.Logger;
import net.su.prodct.dataAccessObject.TogthrDataAccessObject;
import net.su.prodct.display.valueObject.MainDisplayValueObject;
import net.su.prodct.valueObject.TogthrValueObject;

import org.springframework.stereotype.Service;

@Service
public class TogthrServiceImpl implements TogthrService {
	@Resource TogthrDataAccessObject togthrDao;
	//다함꼐 상품 관리 리스트
	public List<TogthrValueObject> togthrProdctList(TogthrValueObject togthrValueObject)throws Exception{
		int togthrProdctListCount = togthrDao.togthrProdctListCount();
		togthrValueObject.setTotalRecordCount(togthrProdctListCount);
		List<TogthrValueObject> togthrProdctList = togthrDao.togthrProdctList(togthrValueObject);
		
		return togthrProdctList;
	}
	//다함께 상품 관리 삭제
	public void deleteToghrProdct(String[] data) throws Exception {
		Logger.info(null);
		togthrDao.deleteToghrProdct(data);
	}	
	//다함께 상품 관리 등록
	public void purchsProdctInsert(TogthrValueObject togthrValueObject)throws Exception{
		System.out.println("공동구매 등록" + togthrValueObject.getProdctSeq());
		System.out.println("공동구매 등록" + togthrValueObject.getProdctNme());
		int prodctSeqLength = togthrDao.prodctLength(togthrValueObject.getProdctSeq());
		System.out.println("공동구매 등록" + togthrValueObject.getOrdrStarDat());
		togthrValueObject.setProdctSeqLength(prodctSeqLength);
		togthrValueObject.setStat("모집중");
		togthrDao.purchsProdctInsert(togthrValueObject);
	}
	//다함께 상품 관리 상세조회
	public TogthrValueObject togthrDetail(TogthrValueObject togthrValueObject)throws Exception{
		int gropPurchsProdctSeq  = togthrValueObject.getGropPurchsProdctSeq();
		TogthrValueObject togthrDetail = togthrDao.togthrDetail(gropPurchsProdctSeq);
		
		return togthrDetail;
	}
	//다함께 상품 관리 수정
	public void togthrUpdate(TogthrValueObject togthrValueObject)throws Exception{
		Logger.info("다함께 상품 수정 서비스 입니다.");
		togthrDao.togthrUpdate(togthrValueObject);
	}
	//다함께 상품 관리 수정화면에서 삭제
	public void togthrUpdateFrmDelete(int gropPurchsProdctSeq)throws Exception{
		togthrDao.togthrUpdateFrmDelete(gropPurchsProdctSeq);
	}
}