package net.su.app.appLogin.service;

import java.util.List;
import java.util.Map;

import net.su.app.appLogin.valueObject.ShippingPlaceValueObject;
import net.su.custmr.valueObject.CustmrValueObject;
import net.su.login.valueObject.LoginValueObject;
import net.su.market.valueObject.AgremtValueObject;

public interface AppLoginService {

public LoginValueObject mobileLogin(LoginValueObject loginVo)throws Exception;
	
	/**
	 * 아이디 중복확인하는 기능입니다.
	 *
	 * @param  String 
	 * @return  Map<String, Object>
	 * @exception  Exception
	 */
	public Map<String, Object> idCheck (String memId)throws Exception;
	/**
	 * 고객 회원가입하는 기능입니다.
	 * 
	 * @param  void 
	 * @exception  Exception
	 */
	public void insertCus(CustmrValueObject cusVo)throws Exception;
	/**
	 * 고객 아이디 조회하는 기능입니다.
	 *
	 * @param  String 
	 * @exception  Exception
	 */
	public String idSearch(LoginValueObject loginVo)throws Exception;
	
	public LoginValueObject pwSearch(LoginValueObject loginVo)throws Exception;
	
	public List<AgremtValueObject> joinAgremtList()throws Exception;
	
	public void shipngPlcCret(ShippingPlaceValueObject shippingPlaceValueObject)throws Exception;
	
	public List<ShippingPlaceValueObject> shipngPlcList(ShippingPlaceValueObject shippingPlaceValueObject)throws Exception;
	
	public void tokenUpdate(LoginValueObject loginVo)throws Exception; 
	
	public void shipngUpdate(ShippingPlaceValueObject shipingPlcVo)throws Exception;
	
	public void shipngDelete(ShippingPlaceValueObject shipingPlcVo)throws Exception;
	
	public ShippingPlaceValueObject shipngPlcUpdateFrm(ShippingPlaceValueObject shipingPlcVo)throws Exception;
	
	public void LoginDate(LoginValueObject loginVo)throws Exception;
}