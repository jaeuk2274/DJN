package net.su.prodct.display.service;

import java.util.List;

import net.su.prodct.display.valueObject.FreshValueObject;

public interface FreshService {
	public List<FreshValueObject> selectFreshList(FreshValueObject freshValueObject) throws Exception;
	public int selectFreshCount(FreshValueObject freshValueObject) throws Exception;
	public void deleteFresh(String[] data) throws Exception;
	public void updateFresh(FreshValueObject freshValueObject) throws Exception;
	public void insertFresh(FreshValueObject freshValueObject) throws Exception;
	public List<FreshValueObject> selectFreshAdList(FreshValueObject freshValueObject) throws Exception;
	public int selectFreshAdListCount(FreshValueObject freshValueObject) throws Exception;
	public FreshValueObject selectFreshRead(long pordctSeq) throws Exception;
}
 