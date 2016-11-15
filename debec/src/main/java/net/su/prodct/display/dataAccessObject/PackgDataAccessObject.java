package net.su.prodct.display.dataAccessObject;

import java.util.List;

import net.su.logger.Logger;
import net.su.prodct.display.valueObject.PackgValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class PackgDataAccessObject extends SqlSessionDaoSupport {
	//패키지 리스트 조회
	public List<PackgValueObject> selectPackgList(PackgValueObject packgValueObject) throws Exception {
		Logger.info(null);		
		List<PackgValueObject> packgList = getSqlSession().selectList("packgMapper.selectPackgList", packgValueObject);	
		return packgList;
	}
	
	//패키지 리스트 전체 수
	public int selectPackgCount(PackgValueObject packgValueObject) throws Exception {
		Logger.info(null);
		int selectPackgCount = getSqlSession().selectOne("packgMapper.selectPackgCount", packgValueObject);	
		return selectPackgCount;
	}
	 
	//패키지 진열여부 수정
	public void updatePackgDisplayCheck(String[] data) throws Exception {
		Logger.info(null);		
		for (int i=0; i<data.length; i++){
			int packgSeq = Integer.parseInt(data[i]);
			getSqlSession().update("packgMapper.updatePackgDisplayCheck", packgSeq);	
		}	
	}
	
	//패키지 삭제
	public void deletePackg(String[] data) throws Exception {
		Logger.info(null);		
		for (int i=0; i<data.length; i++){
			int packgSeq = Integer.parseInt(data[i]);
			getSqlSession().update("packgMapper.updateDeletePackgProdctType", packgSeq);
			getSqlSession().delete("packgMapper.deletePackg", packgSeq);
		}	
	}
	
	//거래처 상품 리스트 조회
	public List<PackgValueObject> selectClintProdctList(int clintSeq) throws Exception {
		Logger.info(null);		
		List<PackgValueObject> clintProdctList = getSqlSession().selectList("packgMapper.selectClintProdctList", clintSeq);	
		return clintProdctList;
	}
	
	//패키지 상품 선택 리스트 조회
	public List<PackgValueObject> selectPackgProdctAdList(PackgValueObject packgVo) throws Exception {
		Logger.info(null);		
		List<PackgValueObject> packgProdctAdList = getSqlSession().selectList("packgMapper.selectPackgProdctAdList", packgVo);	
		return packgProdctAdList;
	}
	
	//패키지 상품 선택 리스트 개수 조회
	public int selectPackgProdctAdCount(PackgValueObject packgVo) throws Exception {
		Logger.info(null);		
		int packgProdctAdCount = getSqlSession().selectOne("packgMapper.selectPackgProdctAdCount", packgVo);	
		return packgProdctAdCount;
	}
	
	//패키지 상품 선택 임시 리스트 조회
	public List<PackgValueObject> selectPackgProdctAdTempList(PackgValueObject packgVo) throws Exception {
		Logger.info(null);		
		List<PackgValueObject> packgProdctAdTempList = getSqlSession().selectList("packgMapper.selectPackgProdctAdTempList", packgVo);	
		return packgProdctAdTempList;
	}
	
	//패키지 상품 선택 임시 리스트 개수 조회
	public int selectPackgProdctAdTempCount(PackgValueObject packgVo) throws Exception {
		Logger.info(null);		
		int packgProdctAdTempCount = getSqlSession().selectOne("packgMapper.selectPackgProdctAdTempCount", packgVo);	
		return packgProdctAdTempCount;
	}
	
	//패키지 상품의 상품 추가 임시 리스트 처음 추가
	public void insertPackgProdctTempList(int clintSeq) throws Exception {
		Logger.info(null);
		getSqlSession().insert("packgMapper.insertPackgProdctTempList", clintSeq);	
	}
	
	//패키지 상품의 상품 추가 임시 리스트  추가
	public void insertPackgProdctAdTempList(String[] data) throws Exception {
		Logger.info(null);
		for (int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			getSqlSession().insert("packgMapper.insertPackgProdctAdTempList", prodctSeq);
		}	
	}

	//패키지 상품의 상품 추가 임시 리스트 선택 삭제
	public void deletePackgProdctAdTempList(String[] data) throws Exception {
		Logger.info(null);
		for (int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			getSqlSession().delete("packgMapper.deletePackgProdctAdTempList", prodctSeq);	
		}	
	}	
	
	//패키지 상품의 상품 추가 임시 리스트 삭제
	public void deletePackgProdctTempList() throws Exception {
		Logger.info(null);
		getSqlSession().delete("packgMapper.deletePackgProdctTempList");	
	}
	
	//패키지 상품 선택 최종 임시 리스트 조회
	public List<PackgValueObject> selectPackgProdctAdTempList() throws Exception {
		Logger.info(null);		
		List<PackgValueObject> packgProdctAdTempList = getSqlSession().selectList("packgMapper.selectPackgProdctAdTempList2");	
		return packgProdctAdTempList;
	}
	
	//패키지 추가
	public void insertPackg(PackgValueObject packgVo) throws Exception {
		Logger.info(null);
		getSqlSession().insert("packgMapper.insertPackg", packgVo);	
	}
	
	//패키지 상품 추가
	public void insertPackgProdct(String[] data) throws Exception {
		Logger.info(null);
		System.out.println(data+"############");
		for (int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			System.out.println(prodctSeq);
			PackgValueObject packgVo = new PackgValueObject();
			packgVo.setProdctSeq(prodctSeq);
			getSqlSession().insert("packgMapper.insertPackgProdct", packgVo);	
			getSqlSession().update("packgMapper.updateInsertPackgProdctType", prodctSeq);
		}
	}
	
	//패키지 상세조회
	public PackgValueObject selectPackgRead(int packgSeq) throws Exception {
		Logger.info(null);		
		PackgValueObject packgRead = getSqlSession().selectOne("packgMapper.selectPackgRead", packgSeq);	
		return packgRead;
	}
	
	//패키지 상세조회 상품 리스트
	public List<PackgValueObject> selectPackgProdctRead(int packgSeq) throws Exception {
		Logger.info(null);		
		List<PackgValueObject> packgProdctRead = getSqlSession().selectList("packgMapper.selectPackgProdctRead", packgSeq);	
		return packgProdctRead;
	}
	
	//패키지 상세 조회 상품 선택 리스트 
	public List<PackgValueObject> selectPackgProdctReadAdList(PackgValueObject packgVo) throws Exception {
		Logger.info(null);		
		List<PackgValueObject> packgProdctReadAdList = getSqlSession().selectList("packgMapper.selectPackgProdctReadAdList", packgVo);	
		return packgProdctReadAdList;
	}
	
	//패키지 상세조회 선택 리스트 개수 조회
	public int selectPackgProdctReadAdCount(PackgValueObject packgVo) throws Exception {
		Logger.info(null);		
		int packgProdctReadAdCount = getSqlSession().selectOne("packgMapper.selectPackgProdctReadAdCount", packgVo);	
		return packgProdctReadAdCount;
	}
	
	//패키지 상세조회의 상품 추가 임시 리스트 처음 추가
	public void insertPackgProdctReadTempList(int packgSeq) throws Exception {
		Logger.info(null);
		getSqlSession().insert("packgMapper.insertPackgProdctReadTempList", packgSeq);	
	}
	
	//패키지  수정
	public void updatePackg(PackgValueObject packgVo) throws Exception {
		Logger.info(null);
		getSqlSession().update("packgMapper.updatePackg", packgVo);	
	}
	
	//패키지  삭제
	public void deletePackgBridg(int packgSeq) throws Exception {
		Logger.info(null);
		getSqlSession().delete("packgMapper.deletePackgBridg", packgSeq);	
	}
	
	//패키지 수정 상품 추가
	public void insertUpdatePackgProdct(int packgSeq, String[] data) throws Exception {
		Logger.info(null);
		for (int i=0; i<data.length; i++){
			long prodctSeq = Long.parseLong(data[i]);
			PackgValueObject packgVo = new PackgValueObject();
			packgVo.setProdctSeq(prodctSeq);
			packgVo.setPackgSeq(packgSeq);
			getSqlSession().insert("packgMapper.insertUpdatePackgProdct", packgVo);
		}	
	}
}
