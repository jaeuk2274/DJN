package net.su.prodct.display.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.su.logger.Logger;
import net.su.prodct.display.dataAccessObject.HotdlDataAccessObject;
import net.su.prodct.display.valueObject.HotdlValueObject;

@Service
public class HotdlServiceImpl implements HotdlService {
	@Resource
	private HotdlDataAccessObject hotdlDao;
	
	//핫딜 상품 리스트 조회
	public List<HotdlValueObject> selectHotdlList(HotdlValueObject hotdlValueObject) throws Exception {
		Logger.info(null);
		int selectHotdlCount = hotdlDao.selectHotdlCount(hotdlValueObject);
		hotdlValueObject.setTotalRecordCount(selectHotdlCount);
		List<HotdlValueObject> hotdlList = hotdlDao.selectHotdlList(hotdlValueObject);	
		
		return hotdlList;
	}
	 
	//핫딜 상품 리스트 전체 수
	public int selectHotdlCount(HotdlValueObject hotdlValueObject) throws Exception {
		Logger.info(null);
		int selectHotdlCount = hotdlDao.selectHotdlCount(hotdlValueObject);	
		return selectHotdlCount;
	}
	
	// 핫딜 상품의 삭제
	public void deleteHotdlProdct(String[] data) throws Exception {
		Logger.info(null);
		hotdlDao.deleteHotdlProdct(data);
	}		
	
	//핫딜 상품 리스트 조회
	public HotdlValueObject selectHotdlRead(long pordctSeq) throws Exception {
		Logger.info(null);		
		HotdlValueObject hotdlValueObject = hotdlDao.selectHotdlRead(pordctSeq); 
		return hotdlValueObject;
	}
	
	//핫딜 상품 수정
	public void updateHotdl(HotdlValueObject hotdlValueObject) throws Exception {
		Logger.info(null);		
		hotdlDao.updateHotdl(hotdlValueObject);	
	}
	
	//핫딜 상품 추가
	public void insertHotdl(HotdlValueObject hotdlValueObject) throws Exception {
		Logger.info(null);		
		hotdlDao.insertHotdl(hotdlValueObject);	
	}
	
	//핫딜 품의 상품 추가 리스트 조회
	public List<HotdlValueObject> selectHotdlAdList(HotdlValueObject hotdlValueObject) throws Exception {
		Logger.info(null);
		int selectHotdlAdListCount = hotdlDao.selectHotdlAdListCount(hotdlValueObject);
		hotdlValueObject.setTotalRecordCount(selectHotdlAdListCount);
		List<HotdlValueObject> prodctAdList = hotdlDao.selectHotdlAdList(hotdlValueObject);
		return prodctAdList;
	}
}
