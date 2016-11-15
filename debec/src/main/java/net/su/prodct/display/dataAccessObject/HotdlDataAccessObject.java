package net.su.prodct.display.dataAccessObject;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import net.su.logger.Logger;
import net.su.prodct.display.valueObject.HotdlValueObject;

@Repository
public class HotdlDataAccessObject extends SqlSessionDaoSupport {
	//핫딜 상품 리스트 조회
	public List<HotdlValueObject> selectHotdlList(HotdlValueObject hotdlValueObject) throws Exception {
		Logger.info(null);		
		List<HotdlValueObject> hotdlList = getSqlSession().selectList("hotdlMapper.selectHotdlList", hotdlValueObject);	
		return hotdlList;
	}
	
	//핫딜 상품 리스트 전체 수
	public int selectHotdlCount(HotdlValueObject hotdlValueObject) throws Exception {
		Logger.info(null);
		int selectHotdlCount = getSqlSession().selectOne("hotdlMapper.selectHotdlCount", hotdlValueObject);	
		return selectHotdlCount;
	} 
	
	// 핫딜 상품의 삭제
	public void deleteHotdlProdct(String[] data) throws Exception {
		Logger.info(null);
		for (int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			getSqlSession().delete("hotdlMapper.deleteHotdlProdct", prodctSeq);
			getSqlSession().update("hotdlMapper.updateDeleteHotdlProdctType", prodctSeq);
		}
	}
	
	//핫딜 상품 리스트 조회
	public HotdlValueObject selectHotdlRead(long pordctSeq) throws Exception {
		Logger.info(null);		
		HotdlValueObject hotdlValueObject = getSqlSession().selectOne("hotdlMapper.selectHotdlRead", pordctSeq);	
		return hotdlValueObject;
	}

	//핫딜 상품 수정
	public void updateHotdl(HotdlValueObject hotdlValueObject) throws Exception {
		Logger.info(null);		
		getSqlSession().update("hotdlMapper.updateHotdl", hotdlValueObject);	
	}
	
	//핫딜 상품 추가
	public void insertHotdl(HotdlValueObject hotdlValueObject) throws Exception {
		Logger.info(null);		
		getSqlSession().insert("hotdlMapper.insertHotdl", hotdlValueObject);
		getSqlSession().update("hotdlMapper.updateInsertHotdlProdctType", hotdlValueObject.getProdctSeq());	
	}
	
	//핫딜 품의 상품 추가 리스트 조회
	public List<HotdlValueObject> selectHotdlAdList(HotdlValueObject hotdlValueObject) throws Exception {
		Logger.info(null);
		List<HotdlValueObject> prodctAdList = getSqlSession().selectList("hotdlMapper.selectHotdlAdList", hotdlValueObject);	
		return prodctAdList;
	}
	
	//핫딜 상품 테이블 리스트 전체 수
	public int selectHotdlAdListCount(HotdlValueObject hotdlValueObject) throws Exception {
		Logger.info(null);
		int prodctAdCount = getSqlSession().selectOne("hotdlMapper.selectHotdlAdListCount", hotdlValueObject);	
		return prodctAdCount;
	}
}
