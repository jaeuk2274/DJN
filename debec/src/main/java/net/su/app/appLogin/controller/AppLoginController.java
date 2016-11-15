package net.su.app.appLogin.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.su.app.appLogin.service.AppLoginService;
import net.su.app.appLogin.valueObject.ShippingPlaceValueObject;
import net.su.custmr.valueObject.CustmrValueObject;
import net.su.logger.Logger;
import net.su.login.valueObject.LoginValueObject;
import net.su.market.service.AgremtService;
import net.su.market.valueObject.AgremtValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppLoginController {

	@Resource
	private AppLoginService appLoginService;
	@Resource
	private AgremtService agremtService;
	 
	// 로그인
	@RequestMapping(value = "/mobileLogin.do", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public LoginValueObject mobileLogin(LoginValueObject loginVo, HttpSession session) throws Exception {
		Logger.info("앱 로그인 컨트롤러"+loginVo.getEmpId());
		Logger.info("앱 로그인 컨트롤러"+loginVo.getEmpPw());
		Logger.info("앱 로그인 컨트롤러"+loginVo.getSelectLogin());
		session.getAttribute("loginSession");
		LoginValueObject login = appLoginService.mobileLogin(loginVo);
		if(login.getIdCount() == 1){
		   if(login.getEmpSeq() != 0){
	    	   session.setAttribute("loginSession", login.getEmpSeq());
	    	   session.setAttribute("type", 1);
	    	   login.setSelectLogin(1);
	       }
	       else if(login.getCustmrSeq() != 0){
	    	   session.setAttribute("loginSession", login.getCustmrSeq());
	    	   session.setAttribute("type", 2);
	    	   login.setSelectLogin(2);
	    	   appLoginService.LoginDate(loginVo);
	       }
		}
		return login;
    }
	//고객 회원가입
	@RequestMapping(value = "/insertCus.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void insertCus(CustmrValueObject cusVo) throws Exception {
		System.out.println("회원가입");
		appLoginService.insertCus(cusVo);
	}  
	//아이디 찾기
	@RequestMapping(value = "/idSearch.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String idSearch(LoginValueObject loginVo) throws Exception {
		System.out.println("컨트롤러"+loginVo.getCustmrNme());
		System.out.println("컨트롤러"+loginVo.getCustmrMobl());
		String idSearch = appLoginService.idSearch(loginVo);
		System.out.println("결과값 컨트롤러"+idSearch);
		return idSearch;
	}
	@RequestMapping(value = "/pwSearch.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public LoginValueObject pwSearch(LoginValueObject loginVo) throws Exception {
		System.out.println("컨트롤러"+loginVo.getCustmrNme());
		System.out.println("컨트롤러"+loginVo.getCustmrMobl());
		LoginValueObject pwSearch = appLoginService.pwSearch(loginVo);
		System.out.println("결과값 컨트롤러"+pwSearch);
		return pwSearch;
	}
	@RequestMapping(value="/getAddrApi.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public void getAddrApi(HttpServletRequest req, ModelMap model, HttpServletResponse response) throws Exception {
		// 요청변수 설정
    	String currentPage = req.getParameter("currentPage");
		String countPerPage = req.getParameter("countPerPage");
		String confmKey = req.getParameter("confmKey");
		String keyword = req.getParameter("keyword");
		// OPEN API 호출 URL 정보 설정
		String apiUrl = "http://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage="+currentPage+"&countPerPage="+countPerPage+"&keyword="+URLEncoder.encode(keyword,"UTF-8")+"&confmKey="+confmKey;
		URL url = new URL(apiUrl);
    	BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
    	StringBuffer sb = new StringBuffer();
    	String tempStr = null;

    	while(true){
    		tempStr = br.readLine();
    		if(tempStr == null) break;
    		sb.append(tempStr);								// 응답결과 XML 저장
    	}
    	br.close();
    	response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		response.getWriter().write(sb.toString());			// 응답결과 반환
    }
	/**
	* 이용약관 로로 이동하는 메서드입니다.
	*
	* @param   AgremtValueObject
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/joinAgremt.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<AgremtValueObject> joinAgremtList(Model model, AgremtValueObject agremtValueObject) throws Exception {
		List<AgremtValueObject> joinAgremtList = appLoginService.joinAgremtList();
		System.out.println("AgremtController의 /UseAgremtList.do 작동");		
		System.out.println("표준약관 보여준다 : " + agremtValueObject.getShowStd());
		return joinAgremtList;
	}
	/**
	* 이용약관 로로 이동하는 메서드입니다.
	*
	* @param   AgremtValueObject
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/shipngPlcCret.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void shipngPlcCret(ShippingPlaceValueObject shippingPlaceValueObject) throws Exception {
		System.out.println("배송관리 테스트컨트롤러"+shippingPlaceValueObject.getShipngPlcAdrs());
		System.out.println("배송관리 테스트컨트롤러"+shippingPlaceValueObject.getExistCheck());
		appLoginService.shipngPlcCret(shippingPlaceValueObject);
	}
	/**
	* 이용약관 로로 이동하는 메서드입니다.
	*
	* @param   ShippingPlaceValueObject
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/shipngPlcList.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<ShippingPlaceValueObject> shipngPlcList(Model model, ShippingPlaceValueObject shippingPlaceValueObject) throws Exception {
		List<ShippingPlaceValueObject> shipngPlcList = appLoginService.shipngPlcList(shippingPlaceValueObject);
		System.out.println("배송관리 리스트 컨트롤러 작동" + shippingPlaceValueObject.getCustmrSeq());		
		return shipngPlcList;
	}
	/**
	* 이용약관 로로 이동하는 메서드입니다.
	*
	* @param   AgremtValueObject
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/tokenUpdate.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void tokenUpdate(LoginValueObject loginVo) throws Exception {
		System.out.println("배송관리 테스트컨트롤러"+loginVo.getToken());
		appLoginService.tokenUpdate(loginVo);
		System.out.println("배송관리 테스트컨트롤러"+loginVo.getToken());
	}
	/**
	* 이용약관 로로 이동하는 메서드입니다.
	*
	* @param   ShippingPlaceValueObject
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/shipngUpdate.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void shipngUpdate(ShippingPlaceValueObject shipngPlcVo) throws Exception {
		System.out.println("배송관리수정 테스트컨트롤러"+shipngPlcVo.getCustmrSeq());
		appLoginService.shipngUpdate(shipngPlcVo);
	}
	
	/**
	* 이용약관 로로 이동하는 메서드입니다.
	*
	* @param   ShippingPlaceValueObject
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/shipngDelete.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void shipngDelete(ShippingPlaceValueObject shipngPlcVo) throws Exception {
		System.out.println("배송관리삭제 테스트컨트롤러"+shipngPlcVo.getCustmrSeq());
		appLoginService.shipngDelete(shipngPlcVo);
	}
	/**
	* 이용약관 로로 이동하는 메서드입니다.
	*
	* @param   AgremtValueObject
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/shipngPlcUpdateFrm.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ShippingPlaceValueObject shipngPlcUpdateFrm(Model model, ShippingPlaceValueObject shipngPlcVo) throws Exception {
		ShippingPlaceValueObject shipngPlcUpdateFrm = appLoginService.shipngPlcUpdateFrm(shipngPlcVo);
		return shipngPlcUpdateFrm;
	}
}