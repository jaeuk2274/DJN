package net.su.prodct.dataAccessObject;

import java.util.List;

import net.su.logger.Logger;
import net.su.prodct.display.valueObject.MainDisplayValueObject;
import net.su.prodct.valueObject.TogthrValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class TogthrDataAccessObject extends SqlSessionDaoSupport{
	
	//다함께 상품 진열 관리 총 갯수
	public int togthrProdctListCount()throws Exception{
		return getSqlSession().selectOne("togthrMapper.togthrProdctListCount");
	}
	//다함께 상품 진열 관리 리스트 조회
	public List<TogthrValueObject> togthrProdctList(TogthrValueObject togthrValueObject)throws Exception{
		return getSqlSession().selectList("togthrMapper.togthrProdctList", togthrValueObject);
	}
	//다함께 상품 진열 관리 삭제
	public void deleteToghrProdct(String[] data) throws Exception {
		Logger.info(null);
		for (int i=0; i<data.length; i++){
			int gropPurchsProdctSeq = Integer.parseInt(data[i]);
			getSqlSession().delete("togthrMapper.deleteToghrProdct", gropPurchsProdctSeq);	
		}	
	}	
	//상품seq length 조회
	public int prodctLength(long prodctSeq)throws Exception{
		
		return getSqlSession().selectOne("togthrMapper.prodctLength", prodctSeq); 
	}
	//다함께 상품 진열 관리 등록
	public void purchsProdctInsert(TogthrValueObject togthrValueObject)throws Exception{
		logger.info("공동구매 등록 DAO"+togthrValueObject.getProdctSeq());
		logger.info("공동구매 등록 DAO"+togthrValueObject.getProdctNme());
		logger.info("공동구매 등록 DAO"+togthrValueObject.getSelPric());
		
		getSqlSession().insert("togthrMapper.purchsProdctInsert", togthrValueObject);
	}
	//다함께 상품 진열 관리 상품 상세조회
	public TogthrValueObject togthrDetail(int gropPurchsProdctSeq)throws Exception{
		logger.info("더함께 상품 상세조회 DAO"+gropPurchsProdctSeq);
		return getSqlSession().selectOne("togthrMapper.togthrDetail",gropPurchsProdctSeq);
	}
	//다함께 상품 진열 관리 상품 수정
	public void togthrUpdate(TogthrValueObject togthrValueObject)throws Exception{
		logger.info("다함께 상품 수정 DAO");
		getSqlSession().update("togthrMapper.togthrUpdate",togthrValueObject);
	}
	//다함께 상품 진열 관리 상품 수정화면에서 삭제
	public void togthrUpdateFrmDelete(int gropPurchsProdctSeq)throws Exception{
		getSqlSession().delete("togthrMapper.deleteToghrProdct", gropPurchsProdctSeq);
	}
}
