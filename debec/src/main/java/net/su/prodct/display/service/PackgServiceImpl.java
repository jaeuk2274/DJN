package net.su.prodct.display.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.su.logger.Logger;
import net.su.prodct.display.dataAccessObject.PackgDataAccessObject;
import net.su.prodct.display.valueObject.PackgValueObject;


@Service
public class PackgServiceImpl implements PackgService{
	@Resource 
	private PackgDataAccessObject packgDao;
	
	//패키지 리스트 조회
	public List<PackgValueObject> selectPackgList(PackgValueObject packgValueObject) throws Exception {
		Logger.info(null);		
		List<PackgValueObject> packgList =packgDao.selectPackgList(packgValueObject);	
		return packgList;
	}
	
	//패키지 리스트 전체 수
	public int selectPackgCount(PackgValueObject packgValueObject) throws Exception {
		Logger.info(null);
		int selectPackgCount = packgDao.selectPackgCount(packgValueObject);	
		return selectPackgCount;
	}
	
	//패키지 진열여부 수정
	public void updatePackgDisplayCheck(String[] data) throws Exception {
		Logger.info(null);		
		packgDao.updatePackgDisplayCheck(data);
	}
	
	//패키지 삭제
	public void deletePackg(String[] data) throws Exception {
		Logger.info(null);		
		packgDao.deletePackg(data);
	}
	
	//거래처 상품 리스트 조회
	public List<PackgValueObject> selectClintProdctList(int clintSeq) throws Exception {
		Logger.info(null);		
		List<PackgValueObject> clintProdctList = packgDao.selectClintProdctList(clintSeq);	
		return clintProdctList;
	}
	
	//패키지 상품 선택 리스트 조회
	public List<PackgValueObject> selectPackgProdctAdList(PackgValueObject packgVo) throws Exception {
		Logger.info(null);		
		int selectPackgProdctAdCount = packgDao.selectPackgProdctAdCount(packgVo);
		packgVo.setTotalRecordCount(selectPackgProdctAdCount);
		List<PackgValueObject> packgProdctAdList = packgDao.selectPackgProdctAdList(packgVo);	
		return packgProdctAdList;
	}
	
	//패키지 상품 선택 임시 리스트 조회
	public List<PackgValueObject> selectPackgProdctAdTempList(PackgValueObject packgVo) throws Exception {
		Logger.info(null);		
		int selectPackgProdctAdTempCount = packgDao.selectPackgProdctAdTempCount(packgVo); 
		packgVo.setTotalRecordCount(selectPackgProdctAdTempCount);
		List<PackgValueObject> packgProdctAdTempList = packgDao.selectPackgProdctAdTempList(packgVo);	
		return packgProdctAdTempList;
	}
	
	//패키지 상품의 상품 추가 임시 리스트 처음 추가
	public void insertPackgProdctTempList(int clintSeq) throws Exception {
		Logger.info(null);
		packgDao.insertPackgProdctTempList(clintSeq);	
	}
	
	//패키지 상품의 상품 추가 임시 리스트  추가
	public void insertPackgProdctAdTempList(String[] data) throws Exception {
		Logger.info(null);
		packgDao.insertPackgProdctAdTempList(data);	
	}

	//패키지 상품의 상품 추가 임시 리스트 선택 삭제
	public void deletePackgProdctAdTempList(String[] data) throws Exception {
		Logger.info(null);
		packgDao.deletePackgProdctAdTempList(data);
	}	
	
	//패키지 상품의 상품 추가 임시 리스트 삭제
	public void deletePackgProdctTempList() throws Exception {
		Logger.info(null);
		packgDao.deletePackgProdctTempList();
	}
	
	//패키지 상품 선택 임시 리스트 조회
	public List<PackgValueObject> selectPackgProdctAdTempList() throws Exception {
		Logger.info(null);		
		List<PackgValueObject> packgProdctAdTempList = packgDao.selectPackgProdctAdTempList();	
		return packgProdctAdTempList;
	}
	
	//패키지 추가
	public void insertPackg(PackgValueObject packgVo) throws Exception {
		Logger.info(null);
		packgDao.insertPackg(packgVo);
	}
	
	//패키지 상품 추가
	public void insertPackgProdct(String[] data) throws Exception {
		Logger.info(null);
		packgDao.insertPackgProdct(data);
	}
	
	//패키지 상세조회
	public PackgValueObject selectPackgRead(int packgSeq) throws Exception {
		Logger.info(null);		
		PackgValueObject packgRead = packgDao.selectPackgRead(packgSeq);
		return packgRead;
	}
	
	//패키지 상세조회 상품 리스트
	public List<PackgValueObject> selectPackgProdctRead(int packgSeq) throws Exception {
		Logger.info(null);		
		List<PackgValueObject> packgProdctRead = packgDao.selectPackgProdctRead(packgSeq);	
		return packgProdctRead;
	}
	
	//패키지 상세 조회 상품 선택 리스트 
	public List<PackgValueObject> selectPackgProdctReadAdList(PackgValueObject packgVo) throws Exception {
		Logger.info(null);	
		int selectPackgProdctAdCount = packgDao.selectPackgProdctReadAdCount(packgVo);
		packgVo.setTotalRecordCount(selectPackgProdctAdCount);
		List<PackgValueObject> packgProdctReadAdList = packgDao.selectPackgProdctReadAdList(packgVo);	
		return packgProdctReadAdList;
	}
	
	//패키지 상세조회의 상품 추가 임시 리스트 처음 추가
	public void insertPackgProdctReadTempList(int packgSeq) throws Exception {
		Logger.info(null);
		packgDao.insertPackgProdctReadTempList(packgSeq);	
	}
	
	//패키지 수정
	public void updatePackg(PackgValueObject packgVo) throws Exception {
		Logger.info(null);
		packgDao.updatePackg(packgVo);
	}
	
	//패키지  삭제
	public void deletePackgBridg(int packgSeq) throws Exception {
		Logger.info(null);
		packgDao.deletePackgBridg(packgSeq);
	}
	
	//패키지 수정 상품 추가
	public void insertUpdatePackgProdct(int packgSeq, String[] data) throws Exception {
		Logger.info(null);
		packgDao.insertUpdatePackgProdct(packgSeq, data);
	}
}
