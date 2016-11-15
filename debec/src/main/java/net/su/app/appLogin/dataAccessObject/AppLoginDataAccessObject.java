package net.su.app.appLogin.dataAccessObject;



import java.util.List;

import net.su.app.appLogin.valueObject.ShippingPlaceValueObject;
import net.su.custmr.valueObject.CustmrValueObject;
import net.su.logger.Logger;
import net.su.login.valueObject.LoginValueObject;
import net.su.market.valueObject.AgremtValueObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class AppLoginDataAccessObject extends SqlSessionDaoSupport {
	
	public LoginValueObject mobileLogin(LoginValueObject loginVo)throws Exception{
//		logger.info("앱 로그인 DAO"+loginVo.getId());
//		System.out.println("랩 로그인 DAO"+loginVo.getId());
//		System.out.println("랩 로그인 DAO"+loginVo.getPw());
		System.out.println("랩 로그인 DAO"+loginVo.getSelectLogin()
		); 
		LoginValueObject login = getSqlSession().selectOne("appLoginMapper.mobileLogin", loginVo);
		return login;
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
	/**
	 * 고객 회원가입 하는 기능입니다.
	 *
	 * @param  void 
	 * @exception  Exception
	 */
	public void insertCus(CustmrValueObject cusVo) throws Exception{
		getSqlSession().insert("appLoginMapper.insertCus", cusVo);
	}
	public int custmrSeqSelect(CustmrValueObject cusVo)throws Exception{
		return getSqlSession().selectOne("appLoginMapper.custmrSeqSelect", cusVo);
	} 
	public void shipngPlcInsert(CustmrValueObject cusVo)throws Exception{
		getSqlSession().insert("appLoginMapper.shipngPlcInsert", cusVo);
	}
	
	public String idSearch(LoginValueObject loginVo)throws Exception{
		return getSqlSession().selectOne("appLoginMapper.idSearch", loginVo);
	}
	
	public int cusSeqSelect(LoginValueObject loginVo)throws Exception{
		return getSqlSession().selectOne("appLoginMapper.cusSeqSelect", loginVo);
	}
	
	public void pwUpdate(LoginValueObject loginVo)throws Exception{
		getSqlSession().update("appLoginMapper.pwUpdate", loginVo);
	}
	
	public LoginValueObject pwSelect(LoginValueObject loginVo)throws Exception{
		return getSqlSession().selectOne("appLoginMapper.pwSelect", loginVo);
	}
	
	public List<AgremtValueObject> joinAgremtList()throws Exception{
		return getSqlSession().selectList("appLoginMapper.joinAgremtList");
	}
	public void shipngPlcCret(ShippingPlaceValueObject shippingPlaceValueObject)throws Exception{
		System.out.println("배송관리 테스트 DAO"+shippingPlaceValueObject.getShipngPlcAdrs());
		getSqlSession().insert("appLoginMapper.shipngPlcCret", shippingPlaceValueObject);
	}
	public List<ShippingPlaceValueObject> shipngPlcList(ShippingPlaceValueObject shippingPlaceValueObject)throws Exception{
		return getSqlSession().selectList("appLoginMapper.shipngPlcList", shippingPlaceValueObject.getCustmrSeq());
	}
	public void empTokenUpdate(LoginValueObject loginVo)throws Exception{
		getSqlSession().update("appLoginMapper.empTokenUpdate", loginVo);
	}
	
	public void custmrTokenUpdate(LoginValueObject loginVo)throws Exception{
		getSqlSession().update("appLoginMapper.custmrTokenUpdate", loginVo);
	}
	
	public void shipngUpdate(ShippingPlaceValueObject shipngPlcVo)throws Exception{
		getSqlSession().update("appLoginMapper.shipngUpdate", shipngPlcVo);
	}
	
	public void shipngCheck(ShippingPlaceValueObject shipngPlcVo)throws Exception{
		getSqlSession().update("appLoginMapper.shipngCheck", shipngPlcVo);
	}
	public void shipngDelete(ShippingPlaceValueObject shipingPlcVo)throws Exception{
		getSqlSession().delete("appLoginMapper.shipngDelete", shipingPlcVo);
	}
	
	public ShippingPlaceValueObject shipngPlcUpdateFrm(ShippingPlaceValueObject shipingPlcVo)throws Exception{
		return getSqlSession().selectOne("appLoginMapper.shipngPlcUpdateFrm", shipingPlcVo.getShipngPlcSeq());
	}
	
	public void LoginDate(LoginValueObject loginVo)throws Exception{
		getSqlSession().update("appLoginMapper.LoginDate", loginVo);
	}
}