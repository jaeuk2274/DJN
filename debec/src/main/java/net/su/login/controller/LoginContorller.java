package net.su.login.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.su.logger.Logger;
import net.su.login.service.LoginService;
import net.su.login.valueObject.LoginValueObject;
import net.su.market.service.EmpService;
import net.su.market.valueObject.EmpValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class LoginContorller {
	
	@Resource
	private LoginService LoginService;
	
	@Resource
	private EmpService EmpService;

	// 로그인 화면
	@RequestMapping(value = "/loginFrame.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginFrame(Model model) {
		Logger.info(null);
		return "login/LoginFrame";
	}

	// 로그인
	@RequestMapping(value = "/login.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(LoginValueObject loginValueObject, HttpSession session, Model model) throws Exception {
		
		Logger.info(loginValueObject.getEmpId()+"???"+loginValueObject.getEmpPw());
		int login = LoginService.login(loginValueObject);
		String jsp = "login/LoginFrame";
		if(login == 1) {
			LoginValueObject loginUser = LoginService.empInfoRead(loginValueObject);
			session.setAttribute("loginUserInfo", loginUser);
			jsp = "redirect:/MainFrame.do";
		}
		else {
			model.addAttribute("falseMsg", "ID와 PW를 확인해 주세요.");
		}
			
		return jsp;
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) throws Exception {
		session.setAttribute("loginUserInfo", null);
		
		return "redirect:/loginFrame.do";
	}
	
	/**
	 * 비밀번호 재확인화면 전환하는 메서드입니다.
	 *
	 * @param   HttpSession, LoginValueObject 
	 * @return  String
	 * @exception  Exception
	 */
	@RequestMapping(value = "/pwCheckRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String pwCheckRead(HttpSession session, LoginValueObject loginValueObject) throws Exception {
		Logger.info(null);

		return "login/PwCheckRead";
		
	}
	 
	/**
	 * 비밀번호 재확인하는  매서드입니다.
	 *
	 * @param   ModelMap, HttpSession, LoginValueObject 
	 * @return  String
	 * @exception  Exception
	 */
	@RequestMapping(value = "/pwCheckDataRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String pwCheckDataRead(ModelMap model, HttpSession session, LoginValueObject loginValueObject) throws Exception {
		Logger.info(null);

		session.getAttribute("loginUserInfo");
		LoginValueObject userLoginInfo = (LoginValueObject)session.getAttribute("loginUserInfo");
		
		userLoginInfo.setEmpPw(loginValueObject.getEmpPw());
		
		int pwCheck = LoginService.pwCheckDataRead(userLoginInfo);
		
		if(pwCheck==1){
			List<EmpValueObject> selectEmpRead = EmpService.selectEmpRead(userLoginInfo.getEmpSeq());
			model.addAttribute("selectEmpRead", selectEmpRead);
			return "login/PersnlDataRead";
		}
		
		else{
			return "redirect:/pwCheckRead.do";
		}
	}
	
	/**
	 * 개인정보 수정화면 전환하는 메서드입니다.
	 *
	 * @param   HttpSession, LoginValueObject 
	 * @return  String
	 * @exception  Exception
	 */
	@RequestMapping(value = "/persnlDataRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String persnlDataRead(ModelMap model, HttpSession session, LoginValueObject loginValueObject) throws Exception {
		Logger.info(null);
		
		session.getAttribute("loginUserInfo");
		LoginValueObject userLoginInfo = (LoginValueObject)session.getAttribute("loginUserInfo");
		
		List<EmpValueObject> selectEmpRead = EmpService.selectEmpRead(userLoginInfo.getEmpSeq());
		model.addAttribute("selectEmpRead", selectEmpRead);
		return "login/PersnlUpdate";
		
	}
	
	/**
	 * 개인정보 수정하는 메서드입니다.
	 *
	 * @param   ModelMap, MultipartHttpServletRequest, HttpSession, LoginValueObject, LoginValueObject
	 * @return  String
	 * @exception  Exception
	 */
	@RequestMapping(value = "/persnlDataUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String persnlDataUpdate(ModelMap model, MultipartHttpServletRequest multiRequest, HttpSession session, EmpValueObject empValueObject, LoginValueObject loginValueObject) throws Exception {
		Logger.info(null);
				
		EmpService.empPersnlUpdate(multiRequest, empValueObject);		
		
		session.getAttribute("loginUserInfo");
		LoginValueObject userLoginInfo = (LoginValueObject)session.getAttribute("loginUserInfo");
		
		userLoginInfo.setEmpPw(loginValueObject.getEmpPw());
		
		List<EmpValueObject> selectEmpRead = EmpService.selectEmpRead(userLoginInfo.getEmpSeq());
		model.addAttribute("selectEmpRead", selectEmpRead);
		return "login/PersnlDataRead";
		
	}
	
	/**
	 * 비밀번호 수정하는 메서드입니다.
	 *
	 * @param   ModelMap, HttpSession, HttpServletRequest, LoginValueObject, EmpValueObject
	 * @return  String
	 * @exception  Exception
	 */
	@RequestMapping(value = "/pwUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String pwUpdate(ModelMap model, HttpSession session, HttpServletRequest request, LoginValueObject loginValueObject, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);

		String pw = request.getParameter("pw") == null?"":request.getParameter("pw");
		empValueObject.setPw(pw);
		
		session.getAttribute("loginUserInfo");
		LoginValueObject userLoginInfo = (LoginValueObject)session.getAttribute("loginUserInfo");
		
		empValueObject.setEmpSeq(userLoginInfo.getEmpSeq());
		EmpService.empPWUpdate(empValueObject);
		
		List<EmpValueObject> selectEmpRead = EmpService.selectEmpRead(userLoginInfo.getEmpSeq());
		model.addAttribute("selectEmpRead", selectEmpRead);
		
		return "redirect:/persnlDataRead.do";
		
	}
	
	/**
	 * 아이디 중복 체크하는 메서드입니다.
	 *
	 * @param   HttpServletRequest, Model, EmpValueObject
	 * @return  Map<String, Object>
	 * @exception  Exception
	 */
	@RequestMapping(value = "/idCheck.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> idcheck(HttpServletRequest request, Model model, LoginValueObject loginValueObject) throws Exception {
		Logger.info(null);
		
		String memId = request.getParameter("id") == null?"":request.getParameter("id");
		System.out.println(memId);
		
	    Map<String, Object> map = LoginService.idCheck(memId);
	    Map<String, Object> idCheck = new HashMap<String, Object>();
	    
	    idCheck.put("idCheck", map.get("idCheck"));

		return idCheck;
		
	}
}