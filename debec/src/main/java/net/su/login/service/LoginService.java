package net.su.login.service;

import java.util.Map;

import net.su.login.valueObject.LoginValueObject;

public interface LoginService {
	
	// 로그인
	public int login(LoginValueObject loginValueObject) throws Exception;
	
	//로그인 세션
	public LoginValueObject empInfoRead(LoginValueObject loginValueObject) throws Exception;

	/**
	 * 비밀번호 재확인하는 기능입니다.
	 *
	 * @param  LoginValueObject 
	 * @return  String
	 * @exception  Exception
	 */
	public int pwCheckDataRead(LoginValueObject loginValueObject) throws Exception;
	
	/**
	 * 아이디 중복확인하는 기능입니다.
	 *
	 * @param  String 
	 * @return  Map<String, Object>
	 * @exception  Exception
	 */
	public Map<String, Object> idCheck (String memId)throws Exception;
}
