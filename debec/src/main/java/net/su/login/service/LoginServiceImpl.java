package net.su.login.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.su.logger.Logger;
import net.su.login.dataAccessObject.LoginDataAccessObject;
import net.su.login.valueObject.LoginValueObject;
import net.su.security.Base64Utils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService{

	@Resource
	LoginDataAccessObject LoginDataAccessObject;
	
	Base64Utils base64 = new Base64Utils();
	String encryptKey = "temp11111111111111111111";//key 선언 
	//키값은 무조건 24자리이여야 한듯.
	//Base64Utils.java 파일에서 키값의 길이를 수정하면 될것도 같으나,
	//seed 암호화에서 key값이 원래 24자리가 필요할지도 몰라서 그냥 1로 채움
	//seed + base64 암호화, 복호화
	
	
	// 로그인
	public int login(LoginValueObject loginValueObject) throws Exception {
		
		System.out.println("LoginServiceImpl의 login() 작동");
		
		//기본 pw
		String W_ORG_FG = loginValueObject.getEmpPw(); //암호화할 문자열
		//암호화 pw
		String EN_ORG_FG = base64.encrypt(W_ORG_FG, encryptKey);
		loginValueObject.setEmpPw(EN_ORG_FG);
		int login = LoginDataAccessObject.login(loginValueObject);
		
		return login;
	}
	
	// 로그인 세션
	public LoginValueObject empInfoRead(LoginValueObject loginValueObject) throws Exception {
		System.out.println("LoginServiceImpl의 empInfoRead() 작동");
		loginValueObject = LoginDataAccessObject.empInfoRead(loginValueObject);
		
		return loginValueObject;
	}
	
	/**
	 * 비밀번호 재확인하는 기능입니다.
	 *
	 * @param  LoginValueObject 
	 * @return  String
	 * @exception  Exception
	 */
	public int pwCheckDataRead(LoginValueObject loginValueObject) throws Exception {
		Logger.info(null);
		
		String EN_ORG_FG = base64.encrypt(loginValueObject.getEmpPw(), encryptKey);
		loginValueObject.setEmpPw(EN_ORG_FG);
		
		int pwCheck = LoginDataAccessObject.pwCheckDataRead(loginValueObject);
				
		return pwCheck;
	}
	
	/**
	 * 아이디 중복확인하는 기능입니다.
	 *
	 * @param  String 
	 * @return  Map<String, Object>
	 * @exception  Exception
	 */
	@Transactional
	public Map<String, Object> idCheck (String memId)throws Exception{
		Logger.info(null);
		
		int idCheck =  LoginDataAccessObject.idCheck(memId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("idCheck", idCheck);
		
		return map;
	}
}
