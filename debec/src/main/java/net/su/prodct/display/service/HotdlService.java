package net.su.prodct.display.service;

import java.util.List;

import net.su.prodct.display.valueObject.HotdlValueObject;

public interface HotdlService {
	public List<HotdlValueObject> selectHotdlList(HotdlValueObject hotdlValueObject) throws Exception;
	public int selectHotdlCount(HotdlValueObject hotdlValueObject) throws Exception;
	public void deleteHotdlProdct(String[] data) throws Exception;
	public HotdlValueObject selectHotdlRead(long pordctSeq) throws Exception;
	public void updateHotdl(HotdlValueObject hotdlValueObject) throws Exception;
	public void insertHotdl(HotdlValueObject hotdlValueObject) throws Exception;
	public List<HotdlValueObject> selectHotdlAdList(HotdlValueObject hotdlValueObject) throws Exception;
}
 