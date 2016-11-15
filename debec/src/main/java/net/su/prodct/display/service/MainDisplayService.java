package net.su.prodct.display.service;

import java.util.List;

import net.su.prodct.display.valueObject.MainDisplayValueObject;
import net.su.prodct.valueObject.CatgrValueObject;

public interface MainDisplayService {
	public List<MainDisplayValueObject> selectMainDisplayList(MainDisplayValueObject mainDisplayValueObject) throws Exception;
	public List<CatgrValueObject> selectCatgrList() throws Exception;
	public void updateMainDisplayOrder(String[] data) throws Exception;
	public int selectMainDisplayCount(MainDisplayValueObject mainDisplayValueObject) throws Exception;
	public void deleteMainDisplayProdct(String[] data) throws Exception;
	
	//상품 추가 테이블
	public List<MainDisplayValueObject> selectProdctAdList(MainDisplayValueObject mainDisplayValueObject2) throws Exception;
	
	//임시 테이블    
	public List<MainDisplayValueObject> selectProdctAdTempList(MainDisplayValueObject mainDisplayValueObject3) throws Exception;
	public void insertProdctTempList() throws Exception;
	public void insertProdctAdTempList(String[] data) throws Exception;
	public void deleteProdctAdTempList(String[] data) throws Exception;
	public void deleteProdctTempList() throws Exception;
	public void insertProdct(String[] data) throws Exception;
}
