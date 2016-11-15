package net.su.login.dataAccessObject;

import net.su.logger.Logger;
import net.su.login.valueObject.LoginValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDataAccessObject extends SqlSessionDaoSupport {
	
	// 로그인
	public int login(LoginValueObject loginValueObject) throws Exception {
		
		System.out.println("LoginDataAccessObject의 login() 작동");
		int login = getSqlSession().selectOne("loginMapper.login", loginValueObject);
		
		return login;
	}
	
	// 로그인 세션
	public LoginValueObject empInfoRead(LoginValueObject loginValueObject) throws Exception {
		System.out.println("LoginDataAccessObject의 empInfoRead() 작동");
		loginValueObject = getSqlSession().selectOne("loginMapper.empInfoRead", loginValueObject);
		
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
		
		int pwCheck = getSqlSession().selectOne("loginMapper.pwCheckDataRead", loginValueObject);
				
		return pwCheck;
	}
	
	/**
	 * 아이디 중복확인하는 기능입니다.
	 *
	 * @param  String 
	 * @return  Map<String, Object>
	 * @exception  Exception
	 */
	public int idCheck (String memId)throws Exception{
		Logger.info(null);

		int idCheck_1 = getSqlSession().selectOne("loginMapper.idCheck_1", memId);
		int idCheck_2 = getSqlSession().selectOne("loginMapper.idCheck_2", memId);
		
		int idCheck = idCheck_1 + idCheck_2;
		
		return idCheck;
	}
}
