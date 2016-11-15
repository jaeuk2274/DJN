package net.su.prodct.display.dataAccessObject;

import java.util.List;

import net.su.logger.Logger;
import net.su.prodct.display.valueObject.MainDisplayValueObject;
import net.su.prodct.valueObject.CatgrValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class MainDisplayDataAccessObject extends SqlSessionDaoSupport {
	
	// 메인 진열 상품 리스트 조회
	public List<MainDisplayValueObject> selectMainDisplayList(MainDisplayValueObject mainDisplayValueObject) throws Exception {
		Logger.info(null);		
		List<MainDisplayValueObject> mainDisplayList = getSqlSession().selectList("mainDisplayMapper.selectMainDisplayList", mainDisplayValueObject);	
		return mainDisplayList;
	}
	
	// 메인 진열 상품 리스트 전체 수
	public int selectMainDisplayCount(MainDisplayValueObject mainDisplayValueObject) throws Exception {
		Logger.info(null);
		int selectMainDisplayCount = getSqlSession().selectOne("mainDisplayMapper.selectMainDisplayCount", mainDisplayValueObject);	
		return selectMainDisplayCount;
	} 
	
	// 메인 진열 상품의 삭제
	public void deleteMainDisplayProdct(String[] data) throws Exception {
		Logger.info(null);
		for (int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			getSqlSession().delete("mainDisplayMapper.deleteMainDisplayProdct", prodctSeq);	
			getSqlSession().update("mainDisplayMapper.updateDeleteMainDisplayProdctType", prodctSeq);	
		}	
		int select = getSqlSession().selectOne("mainDisplayMapper.updateMainDisplayOrderAfterDelete1");
		getSqlSession().update("mainDisplayMapper.updateMainDisplayOrderAfterDelete2");
	}	
	
	// 메인 진열 상품의 진열순서변경
	public void updateMainDisplayOrder(String[] data) throws Exception {
		Logger.info(null);
		for (int i=0; i<data.length; i++){
			MainDisplayValueObject mainVo = new MainDisplayValueObject();
			mainVo.setProdctSeq(Long.parseLong((data[i])));
			mainVo.setSeqnc(i+1);
			System.out.println("prodctSeq = " + mainVo.getProdctSeq() +"seqnc = " + mainVo.getSeqnc());
			getSqlSession().update("mainDisplayMapper.updateMainDisplayOrder", mainVo);	
		}	
	}	
	
	// 메인 진열 상품의 카테고리 리스트 조회
	public List<CatgrValueObject> selectCatgrList() throws Exception {
		Logger.info(null);
		List<CatgrValueObject> catgrList = getSqlSession().selectList("mainDisplayMapper.selectCatgrList");	
		return catgrList;
	}
	
	// 메인 진열 상품의 상품 추가 리스트 조회
	public List<MainDisplayValueObject> selectProdctAdList(MainDisplayValueObject mainDisplayValueObject2) throws Exception {
		Logger.info(null);
		List<MainDisplayValueObject> prodctAdList = getSqlSession().selectList("mainDisplayMapper.selectProdctAdList", mainDisplayValueObject2);	
		return prodctAdList;
	}
	
	//상품 테이블 리스트 전체 수
	public int selectProdctAdCount(MainDisplayValueObject mainDisplayValueObject) throws Exception {
		Logger.info(null);
		int selectProdctAdCount = getSqlSession().selectOne("mainDisplayMapper.selectProdctAdCount", mainDisplayValueObject);	
		return selectProdctAdCount;
	}
	
	// 메인 진열 상품의 상품 추가 임시 리스트 조회
	public List<MainDisplayValueObject> selectProdctAdTempList(MainDisplayValueObject mainDisplayValueObject3) throws Exception {
		Logger.info(null);
		List<MainDisplayValueObject> prodctAdTempList = getSqlSession().selectList("mainDisplayMapper.selectProdctAdTempList", mainDisplayValueObject3);	
		return prodctAdTempList;
	}
	
	//임시 테이블 리스트 전체 수
	public int selectProdctAdTempCount(MainDisplayValueObject mainDisplayValueObject) throws Exception {
		Logger.info(null);
		int selectProdctAdTempCount = getSqlSession().selectOne("mainDisplayMapper.selectProdctAdTempCount", mainDisplayValueObject);	
		return selectProdctAdTempCount;
	}
	
	// 메인 진열 상품의 상품 추가 임시 리스트 처음 추가
	public void insertProdctTempList() throws Exception {
		Logger.info(null);
		getSqlSession().insert("mainDisplayMapper.insertProdctTempList");	
	}
	
	// 메인 진열 상품의 상품 추가 임시 리스트  추가
	public void insertProdctAdTempList(String[] data) throws Exception {
		Logger.info(null);
		for (int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			getSqlSession().insert("mainDisplayMapper.insertProdctAdTempList", prodctSeq);
		}	
	}

	// 메인 진열 상품의 상품 추가 임시 리스트 선택 삭제
	public void deleteProdctAdTempList(String[] data) throws Exception {
		Logger.info(null);
		for (int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			getSqlSession().delete("mainDisplayMapper.deleteProdctAdTempList", prodctSeq);	
		}	
	}	
	
	// 메인 진열 상품의 상품 추가 임시 리스트 삭제
	public void deleteProdctTempList() throws Exception {
		Logger.info(null);
		getSqlSession().delete("mainDisplayMapper.deleteProdctTempList");	
	}
	
	// 임시 테이블에서 상품 최종 추가
	public void insertProdct(String[] data) throws Exception {
		Logger.info(null);
		getSqlSession().delete("mainDisplayMapper.deleteAllMainDisplayProdct");
		for (int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			getSqlSession().insert("mainDisplayMapper.insertProdct", prodctSeq);
			getSqlSession().update("mainDisplayMapper.updateInsertMainDisplayProdctType", prodctSeq);
		}	
	}
}
