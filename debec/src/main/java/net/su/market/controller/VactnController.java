package net.su.market.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.su.logger.Logger;
import net.su.login.valueObject.LoginValueObject;
import net.su.market.service.VactnService;
import net.su.market.valueObject.EmpValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 휴가관리 메뉴 관련 컨트롤러입니다.
 * 
 * @see   net.su.market.controller.VactnController
 * @version  1.0 
 * @ author 이인호, 2016/08/16
 */

@Controller
public class VactnController {
	
	@Resource
	private VactnService vactnService;
	 
	/**
	 * 휴가관리 휴가조회로 이동하는 메서드입니다.
	 *
	 * @param   ModelMap, EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	@RequestMapping(value = "/vactnList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String selectVactnList(ModelMap model, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		
		List<EmpValueObject> selectVactnList = vactnService.selectVactnList(empValueObject);
		model.addAttribute("selectVactnList", selectVactnList);
		return "market/vactn/VactnList";
	}
	
	/**
	 * 개인 휴가조회로 이동하는 메서드입니다.
	 *
	 * @param   ModelMap, HttpSession, EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	@RequestMapping(value = "/persnlVactnList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String selectPersnlVactnList(ModelMap model, HttpSession session, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		
		session.getAttribute("loginUserInfo");
		LoginValueObject userLoginInfo = (LoginValueObject)session.getAttribute("loginUserInfo");

		empValueObject.setEmpSeq(userLoginInfo.getEmpSeq());
		
		List<EmpValueObject> selectVactnList = vactnService.selectPersnlVactnList(empValueObject);
		model.addAttribute("selectVactnList", selectVactnList);
		
		return "market/vactn/PersnlVactnList";
	}
	
	/**
	 * 휴가관리 사용 연가조회하는 메서드입니다.
	 *
	 * @param   ModelMap, EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	@RequestMapping(value = "/anlVactnListPopup.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String selectAnlVactnListPopup(ModelMap model, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		
		List<EmpValueObject> selectAnlVactnList = vactnService.selectAnlVactnListPopup(empValueObject);
		model.addAttribute("selectAnlVactnList", selectAnlVactnList);
		
		return "market/vactn/AnlVactnListPopup";
	}
	
	/**
	 * 휴가관리 휴가 상세조회하는 메서드입니다.
	 *
	 * @param   ModelMap, EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	@RequestMapping(value = "/vactnReadPopup.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String selectVactnReadPopup(ModelMap model, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		
		List<EmpValueObject> selectVactnRead = vactnService.selectVactnReadPopup(empValueObject);
		model.addAttribute("selectVactnRead", selectVactnRead);
		
		return "market/vactn/VactnReadPopup";
	}
	
	/**
	 * 휴가관리 휴가 등록 화면 이동하는 메서드입니다.
	 *
	 * @param   ModelMap, EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	@RequestMapping(value = "/vactnInsertPopup.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String insertVactnPopup(ModelMap model, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
			
		return "market/vactn/VactnInsertPopup";
	}
	
	/**
	 * 휴가관리 휴가 등록하는 메서드입니다.
	 *
	 * @param   request, HttpSession, EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	@RequestMapping(value = "/vactnInsert.do", method = {RequestMethod.GET, RequestMethod.POST})
	public void insertVactn(HttpServletRequest request, HttpSession session, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		
		session.getAttribute("loginUserInfo");
		LoginValueObject userLoginInfo = (LoginValueObject)session.getAttribute("loginUserInfo");
		
		String vactnType = request.getParameter("vactnType") == null?"":request.getParameter("vactnType");
		String schedlStartDat = request.getParameter("schedlStartDat") == null?"":request.getParameter("schedlStartDat");
		String schedlEndDat = request.getParameter("schedlEndDat") == null?"":request.getParameter("schedlEndDat");
		String schedlResn = request.getParameter("schedlResn") == null?"":request.getParameter("schedlResn");

		empValueObject.setEmpSeq(userLoginInfo.getEmpSeq());	
		empValueObject.setVactnType(vactnType);
		empValueObject.setSchedlStartDat(schedlStartDat);
		empValueObject.setSchedlEndDat(schedlEndDat);
		empValueObject.setSchedlResn(schedlResn);
		
		vactnService.insertVactn(empValueObject);
	}
	
	/**
	 * 휴가관리휴가 수정화면 조회하는 메서드입니다.
	 *
	 * @param   HttpServletRequest, EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	@RequestMapping(value = "/vactnUpdateRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String vactnUpdateRead(ModelMap model, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		
		List<EmpValueObject> selectVactnRead = vactnService.selectVactnReadPopup(empValueObject);
		model.addAttribute("selectVactnRead", selectVactnRead);
		
		return "market/vactn/VactnUpdatePopup";
	}
	
	/**
	 * 휴가관리휴가 수정하는 메서드입니다.
	 *
	 * @param   HttpServletRequest, EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	@RequestMapping(value = "/vactnUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public void updateVactn(HttpServletRequest request, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		
		String vactnType = request.getParameter("vactnType") == null?"":request.getParameter("vactnType");
		String schedlStartDat = request.getParameter("schedlStartDat") == null?"":request.getParameter("schedlStartDat");
		String schedlEndDat = request.getParameter("schedlEndDat") == null?"":request.getParameter("schedlEndDat");
		String schedlResn = request.getParameter("schedlResn") == null?"":request.getParameter("schedlResn");
		
		empValueObject.setVactnType(vactnType);
		empValueObject.setSchedlStartDat(schedlStartDat);
		empValueObject.setSchedlEndDat(schedlEndDat);
		empValueObject.setSchedlResn(schedlResn);
		
		
		vactnService.updateVactn(empValueObject);
	}
	
	/**
	 * 휴가관리휴가 삭제하는 메서드입니다.
	 *
	 * @param   HttpServletRequest, EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	@RequestMapping(value = "/vactnDelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public void deleteVactn(HttpServletRequest request, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		
		String schedlSeq = request.getParameter("schedlSeq") == null?"":request.getParameter("schedlSeq");
		empValueObject.setSchedlSeq(Integer.parseInt(schedlSeq));
	    
		vactnService.deleteVactn(empValueObject);
		
	}
	
	/**
	 * 휴가관리 휴가 다중 삭제하는 메서드입니다.
	 *
	 * @param   String 
	 * @return  String
	 * @exception  Exception
	 */
	
	@RequestMapping(value = "/vactnListDelete.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String deleteVactnList(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
		}
		vactnService.deleteVactnList(data);
		
		return "redirect:/VactnList.do";
	}	   
}
