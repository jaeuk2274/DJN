package net.su.prodct.display.service;

import java.util.List; 

import net.su.prodct.display.valueObject.PackgValueObject;

public interface PackgService {
	public List<PackgValueObject> selectPackgList(PackgValueObject packgValueObject) throws Exception;
	public int selectPackgCount(PackgValueObject packgValueObject) throws Exception;
	public void updatePackgDisplayCheck(String[] data) throws Exception;
	public void deletePackg(String[] data) throws Exception;
	public List<PackgValueObject> selectClintProdctList(int clintSeq) throws Exception;
	public List<PackgValueObject> selectPackgProdctAdList(PackgValueObject packgVo) throws Exception;
	public List<PackgValueObject> selectPackgProdctAdTempList(PackgValueObject packgVo) throws Exception;
	public void insertPackgProdctTempList(int clintSeq) throws Exception;
	public void insertPackgProdctAdTempList(String[] data) throws Exception;
	public void deletePackgProdctAdTempList(String[] data) throws Exception;
	public void deletePackgProdctTempList() throws Exception;
	public List<PackgValueObject> selectPackgProdctAdTempList() throws Exception;
	public void insertPackg(PackgValueObject packgVo) throws Exception;
	public void insertPackgProdct(String[] data) throws Exception;
	public PackgValueObject selectPackgRead(int packgSeq) throws Exception;
	public List<PackgValueObject> selectPackgProdctRead(int packgSeq) throws Exception;
	public List<PackgValueObject> selectPackgProdctReadAdList(PackgValueObject packgVo) throws Exception;
	public void insertPackgProdctReadTempList(int packgSeq) throws Exception;
	public void updatePackg(PackgValueObject packgVo) throws Exception;
	public void deletePackgBridg(int packgSeq) throws Exception;
	public void insertUpdatePackgProdct(int packgSeq, String[] data) throws Exception;
}
