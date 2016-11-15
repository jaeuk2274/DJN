package net.su.app.appLogin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.su.app.appLogin.dataAccessObject.AppLoginDataAccessObject;
import net.su.app.appLogin.valueObject.ShippingPlaceValueObject;
import net.su.custmr.valueObject.CustmrValueObject;
import net.su.logger.Logger;
import net.su.login.valueObject.LoginValueObject;
import net.su.market.valueObject.AgremtValueObject;
import net.su.security.Base64Utils;

@Service
public class AppLoginServiceImpl implements AppLoginService {

	@Resource
	private AppLoginDataAccessObject appLoginDao;
	
	Base64Utils base64 = new Base64Utils();
	String encryptKey = "temp11111111111111111111";//key 선언 
	String decryptKey = "temp11111111111111111111"; //
	
	public LoginValueObject mobileLogin(LoginValueObject loginVo)throws Exception{
		Logger.info("모바일 로그인 서비스인플"+loginVo.getEmpId());
//		//기본 pw
		String W_ORG_FG = loginVo.getEmpPw(); //암호화할 문자열
//		//암호화 pw
		String EN_ORG_FG = base64.encrypt(W_ORG_FG, encryptKey);
		loginVo.setEmpPw(EN_ORG_FG);
		LoginValueObject login = appLoginDao.mobileLogin(loginVo);
		
		return login;
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
		
		int idCheck =  appLoginDao.idCheck(memId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("idCheck", idCheck);
		
		return map;
	}
	/**
	 * 고객 회원가입 기능입니다.
	 *
	 * @param  void 
	 * @exception  Exception
	 */
	public void insertCus(CustmrValueObject cusVo) throws Exception{
		System.out.println("회원가입 서비스"+cusVo.getBirthdayMonth());
		//기본 pw
		String W_ORG_FG = cusVo.getCustmrPw();
		//암호화 pw
		String EN_ORG_FG = base64.encrypt(W_ORG_FG,encryptKey);
		cusVo.setCustmrPw(EN_ORG_FG);
		cusVo.setCustmrBirth(cusVo.getBirthdayYear()+"-"+cusVo.getBirthdayMonth()+"-"+cusVo.getBirthdayDay());
		System.out.println("생년월일일일일일"+cusVo.getCustmrBirth());
		appLoginDao.insertCus(cusVo);
		int custmrSeq = appLoginDao.custmrSeqSelect(cusVo);
		cusVo.setCustmrSeq(custmrSeq);
		
		appLoginDao.shipngPlcInsert(cusVo); 
	}
	public String idSearch(LoginValueObject loginVo)throws Exception{
		String idSearch =  appLoginDao.idSearch(loginVo);
		System.out.println("결과값 서비스"+idSearch);
		return idSearch;
	}

	public LoginValueObject pwSearch(LoginValueObject loginVo)throws Exception{
		int custmrSeq = appLoginDao.cusSeqSelect(loginVo);
		Random rnd =new Random(); 
		StringBuffer buf =new StringBuffer();
		 
		for(int i=0;i<8;i++){
		    if(rnd.nextBoolean()){
		        buf.append((char)((int)(rnd.nextInt(26))+97));
		    }else{
		        buf.append((rnd.nextInt(10))); 
		    }
		}
		loginVo.setCustmrSeq(custmrSeq);
		loginVo.setCustmrPw(buf.toString());
		//기본 pw
		String W_ORG_FG = loginVo.getCustmrPw();
		//암호화 pw
		String EN_ORG_FG = base64.encrypt(W_ORG_FG,encryptKey);
		loginVo.setCustmrPw(EN_ORG_FG);
		Logger.info("비밀번호 찾기 비밀번호 변경 서비스"+loginVo.getCustmrPw());
		
		appLoginDao.pwUpdate(loginVo);
		
		LoginValueObject pwSearch = appLoginDao.pwSelect(loginVo);
		
		String pw = base64.decrypt(pwSearch.getCustmrPw(), decryptKey);
		pwSearch.setCustmrPw(pw);
		
		return pwSearch;
	}
	public List<AgremtValueObject>joinAgremtList()throws Exception{
		List<AgremtValueObject> joinAgremtList = appLoginDao.joinAgremtList();
		
		return joinAgremtList;
	}
	public void shipngPlcCret(ShippingPlaceValueObject shippingPlaceValueObject)throws Exception{
		System.out.println("배송관리 테스트 서비스"+shippingPlaceValueObject.getShipngPlcAdrs());
		System.out.println("배송관리 "+shippingPlaceValueObject.getExistCheck());
		if(shippingPlaceValueObject.getExistCheck().equals("Y")){
			appLoginDao.shipngCheck(shippingPlaceValueObject);
		}
		appLoginDao.shipngPlcCret(shippingPlaceValueObject);
	}
	public List<ShippingPlaceValueObject> shipngPlcList(ShippingPlaceValueObject shippingPlaceValueObject)throws Exception{
		System.out.println("배송관리 테스트  리스트 서비스"+shippingPlaceValueObject.getCustmrSeq());
		List<ShippingPlaceValueObject> shipngPlcList = appLoginDao.shipngPlcList(shippingPlaceValueObject);
		return shipngPlcList;
	}
	public void tokenUpdate(LoginValueObject loginVo)throws Exception{
		if(loginVo.getSelectLogin() == 1){
			appLoginDao.empTokenUpdate(loginVo);
		}
		else{
			appLoginDao.custmrTokenUpdate(loginVo);
		}
	}
	
	public void shipngUpdate(ShippingPlaceValueObject shipingPlcVo)throws Exception{
		if(shipingPlcVo.getExistCheck().equals("Y")){
			appLoginDao.shipngCheck(shipingPlcVo);
		}
		appLoginDao.shipngUpdate(shipingPlcVo);
	}

	public void shipngDelete(ShippingPlaceValueObject shipingPlcVo)throws Exception{
		appLoginDao.shipngDelete(shipingPlcVo);
	}
	
	public ShippingPlaceValueObject shipngPlcUpdateFrm(ShippingPlaceValueObject shipingPlcVo)throws Exception{
		ShippingPlaceValueObject shipngPlcUpdateFrm = appLoginDao.shipngPlcUpdateFrm(shipingPlcVo);
		return shipngPlcUpdateFrm;
	}
	public void LoginDate(LoginValueObject loginVo)throws Exception{
		appLoginDao.LoginDate(loginVo);
	}
}