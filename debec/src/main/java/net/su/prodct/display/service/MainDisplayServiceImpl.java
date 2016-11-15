package net.su.prodct.display.service;

import java.util.List;

import javax.annotation.Resource;

import net.su.logger.Logger;
import net.su.prodct.display.dataAccessObject.MainDisplayDataAccessObject;
import net.su.prodct.display.valueObject.MainDisplayValueObject;
import net.su.prodct.valueObject.CatgrValueObject;

import org.springframework.stereotype.Service;


@Service
public class MainDisplayServiceImpl implements MainDisplayService {
	@Resource
	private MainDisplayDataAccessObject mainDisplayDao;
	
	//메인 진열 상품 리스트 조회
	public List<MainDisplayValueObject> selectMainDisplayList(MainDisplayValueObject mainDisplayValueObject) throws Exception {
		Logger.info(null);
		int selectMainDisplyCount = mainDisplayDao.selectMainDisplayCount(mainDisplayValueObject);
		mainDisplayValueObject.setTotalRecordCount(selectMainDisplyCount);
		List<MainDisplayValueObject> mainDisplayList = mainDisplayDao.selectMainDisplayList(mainDisplayValueObject);	
		
		return mainDisplayList;
	} 
	 
	// 메인 진열 상품 리스트 전체 수
	public int selectMainDisplayCount(MainDisplayValueObject mainDisplayValueObject) throws Exception {
		Logger.info(null);
		int selectMainDisplayCount = mainDisplayDao.selectMainDisplayCount(mainDisplayValueObject);	
		return selectMainDisplayCount;
	}
	
	// 메인 진열 상품의 삭제
	public void deleteMainDisplayProdct(String[] data) throws Exception {
		Logger.info(null);
		mainDisplayDao.deleteMainDisplayProdct(data);
	}	
	
	// 메인 진열 상품의 진열순서변경
	public void updateMainDisplayOrder(String[] data) throws Exception {
		Logger.info(null);
		mainDisplayDao.updateMainDisplayOrder(data);
	}
	
	// 메인 진열 상품의 카테고리 리스트 조회
	public List<CatgrValueObject> selectCatgrList() throws Exception {
		Logger.info(null);
		List<CatgrValueObject> catgrList = mainDisplayDao.selectCatgrList();
		return catgrList;
	}
	
	// 메인 진열 상품의 상품 추가 리스트 조회
	public List<MainDisplayValueObject> selectProdctAdList(MainDisplayValueObject mainDisplayValueObject2) throws Exception {
		Logger.info(null);
		int selectProdctAdCount = mainDisplayDao.selectProdctAdCount(mainDisplayValueObject2);
		mainDisplayValueObject2.setTotalRecordCount(selectProdctAdCount);
		List<MainDisplayValueObject> prodctAdList = mainDisplayDao.selectProdctAdList(mainDisplayValueObject2);
		return prodctAdList;
	}
	
	// 메인 진열 상품의 상품 추가 임시 리스트 조회
	public List<MainDisplayValueObject> selectProdctAdTempList(MainDisplayValueObject mainDisplayValueObject3) throws Exception {
		Logger.info(null);
		int selectProdctAdTempCount = mainDisplayDao.selectProdctAdTempCount(mainDisplayValueObject3);
		System.out.println("임시테이블 상품 수 = " + selectProdctAdTempCount);
		mainDisplayValueObject3.setTotalRecordCount(selectProdctAdTempCount);
		List<MainDisplayValueObject> prodctAdTempList = mainDisplayDao.selectProdctAdTempList(mainDisplayValueObject3);
		return prodctAdTempList;
	}
		
	// 메인 진열 상품의 상품 추가 임시 리스트 처음 추가
	public void insertProdctTempList() throws Exception {
		Logger.info(null);
		mainDisplayDao.insertProdctTempList();
	}
	
	// 메인 진열 상품의 상품 추가 임시 리스트 추가
	public void insertProdctAdTempList(String[] data) throws Exception {
		Logger.info(null);
		mainDisplayDao.insertProdctAdTempList(data);
	}
	
	// 메인 진열 상품의 상품 추가 임시 리스트 삭제
	public void deleteProdctAdTempList(String[] data) throws Exception {
		Logger.info(null);
		mainDisplayDao.deleteProdctAdTempList(data);
	}	
	
	// 메인 진열 상품의 상품 추가 임시 리스트 전체 삭제
	public void deleteProdctTempList() throws Exception {
		Logger.info(null);
		mainDisplayDao.deleteProdctTempList();
	}	
	
	// 임시 테이블에서 상품 최종 추가
	public void insertProdct(String[] data) throws Exception {
		Logger.info(null);
		mainDisplayDao.insertProdct(data);
	}
}
