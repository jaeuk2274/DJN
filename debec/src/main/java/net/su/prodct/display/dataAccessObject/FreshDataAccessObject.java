package net.su.prodct.display.dataAccessObject;

import java.util.List;

import net.su.logger.Logger;
import net.su.prodct.display.valueObject.FreshValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class FreshDataAccessObject extends SqlSessionDaoSupport {
	//신선 식품 리스트 조회
	public List<FreshValueObject> selectFreshList(FreshValueObject freshValueObject) throws Exception {
		Logger.info(null);		
		List<FreshValueObject> freshList = getSqlSession().selectList("freshMapper.selectFreshList", freshValueObject);	
		return freshList;
	}
	
	//신선 식품 리스트 전체 수
	public int selectFreshCount(FreshValueObject freshValueObject) throws Exception {
		Logger.info(null);
		int selectFreshCount = getSqlSession().selectOne("freshMapper.selectFreshCount", freshValueObject);	
		return selectFreshCount;
	}
	 
	//신선 식품 리스트 조회
	public FreshValueObject selectFreshRead(long pordctSeq) throws Exception {
		Logger.info(null);		
		FreshValueObject freshValueObject = getSqlSession().selectOne("freshMapper.selectFreshRead", pordctSeq);	
		return freshValueObject;
	}
	
	//신선 식품의 삭제
	public void deleteFresh(String[] data) throws Exception {
		Logger.info(null);
		for (int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			getSqlSession().delete("freshMapper.deleteFresh", prodctSeq);
			getSqlSession().update("freshMapper.updateDeleteFreshProdctType", prodctSeq);
		}
	}	

	//신선 식품 수정
	public void updateFresh(FreshValueObject freshValueObject) throws Exception {
		Logger.info(null);		
		getSqlSession().update("freshMapper.updateFresh", freshValueObject);	
	}
	
	//신선 식품 추가
	public void insertFresh(FreshValueObject freshValueObject) throws Exception {
		Logger.info(null);		
		getSqlSession().insert("freshMapper.insertFresh", freshValueObject);	
		getSqlSession().update("freshMapper.updateInsertFreshProdctType", freshValueObject.getProdctSeq());
	}
	
	//신선 식품의 상품 추가 리스트 조회
	public List<FreshValueObject> selectFreshAdList(FreshValueObject freshValueObject) throws Exception {
		Logger.info(null);
		List<FreshValueObject> prodctAdList = getSqlSession().selectList("freshMapper.selectFreshAdList", freshValueObject);	
		return prodctAdList;
	}
	
	//신선 식품 테이블 리스트 전체 수
	public int selectFreshAdListCount(FreshValueObject freshValueObject) throws Exception {
		Logger.info(null);
		int prodctAdCount = getSqlSession().selectOne("freshMapper.selectFreshAdListCount", freshValueObject);	
		return prodctAdCount;
	}
}
