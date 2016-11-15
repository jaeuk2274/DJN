package net.su.app.appMarket.service;

import java.util.List;

import net.su.prodct.valueObject.DebecFestivalValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

/**
 * 앱 마켓 메뉴 대백제 기능 서비스입니다.
 * 
 * @see   net.su.app.appMarket.service
 * @version  1.0 
 * @ author 이인호, 2016/09/20
 */
public interface AppDebecFestivalService {
	
	public int appDebecFestival() throws Exception;
	
	public DebecFestivalValueObject appDebecFestivalRead() throws Exception;
	
	public List<ProdctValueObject> appDebecFestivalProdctList(DebecFestivalValueObject debecFestivalValueObject) throws Exception;

}
