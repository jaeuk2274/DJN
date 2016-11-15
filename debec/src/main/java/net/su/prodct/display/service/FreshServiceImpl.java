package net.su.prodct.display.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.su.logger.Logger;
import net.su.prodct.display.dataAccessObject.FreshDataAccessObject;
import net.su.prodct.display.valueObject.FreshValueObject;

@Service	
public class FreshServiceImpl implements FreshService{
	@Resource
	private FreshDataAccessObject freshDao;
	
	//신선 식품 리스트 조회
	public List<FreshValueObject> selectFreshList(FreshValueObject freshValueObject) throws Exception {
		Logger.info(null);		
		List<FreshValueObject> freshList = freshDao.selectFreshList(freshValueObject);
		return freshList;
	}
	
	//신선 식품 리스트 전체 수
	public int selectFreshCount(FreshValueObject freshValueObject) throws Exception {
		Logger.info(null);
		int freshCount = freshDao.selectFreshCount(freshValueObject);	
		return freshCount; 
	}
	
	//신선 식품 리스트 조회
	public FreshValueObject selectFreshRead(long pordctSeq) throws Exception {
		Logger.info(null);		
		FreshValueObject freshValueObject = freshDao.selectFreshRead(pordctSeq);
		return freshValueObject;
	}
	
	//신선 식품의 삭제
	public void deleteFresh(String[] data) throws Exception {
		Logger.info(null);
		freshDao.deleteFresh(data);
	}	

	//신선 식품 수정
	public void updateFresh(FreshValueObject freshValueObject) throws Exception {
		Logger.info(null);		
		freshDao.updateFresh(freshValueObject);
	}
	
	//신선 식품 추가
	public void insertFresh(FreshValueObject freshValueObject) throws Exception {
		Logger.info(null);		
		freshDao.insertFresh(freshValueObject);
	}
	
	//신선 식품의 상품 추가 리스트 조회
	public List<FreshValueObject> selectFreshAdList(FreshValueObject freshValueObject) throws Exception {
		Logger.info(null);
		List<FreshValueObject> prodctAdList = freshDao.selectFreshAdList(freshValueObject);
		return prodctAdList;
	}
	
	//신선 식품 테이블 리스트 전체 수
	public int selectFreshAdListCount(FreshValueObject freshValueObject) throws Exception {
		Logger.info(null);
		int prodctAdCount = freshDao.selectFreshAdListCount(freshValueObject);
		return prodctAdCount;
	}
}
