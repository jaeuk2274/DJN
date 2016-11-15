package net.su.market.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.su.logger.Logger;
import net.su.login.valueObject.LoginValueObject;
import net.su.market.service.EmpService;
import net.su.market.service.VactnService;
import net.su.market.valueObject.EmpValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 직원관리 메뉴 관련 컨트롤러입니다.
 * 
 * @see   net.su.market.controller.EmpController
 * @version  1.0 
 * @ author 이인호, 2016/08/09
 */

@Controller
public class EmpController {
	
	@Resource
	private EmpService EmpService;
	
	@Resource
	private VactnService vactnService;
	 
	/**
	 * 직원관리 직원조회로 이동하는 메서드입니다.
	 *
	 * @param   ModelMap, EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	@RequestMapping(value = "/empList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String empList(ModelMap model, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		List<EmpValueObject> selectEmpList = EmpService.selectEmpList(empValueObject);
		model.addAttribute("selectEmpList", selectEmpList);
		return "market/emp/EmpList";
	}
	
	/**
	 * 직원관리 직원 상세조회하는 메서드입니다.
	 *
	 * @param   ModelMap, EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	@RequestMapping(value = "/empRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String empRead(ModelMap model, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		
		List<EmpValueObject> selectEmpRead = EmpService.selectEmpRead(empValueObject.getEmpSeq());
		model.addAttribute("selectEmpRead", selectEmpRead);
		
		List<EmpValueObject> selectVactnList = vactnService.selectPersnlVactnList(empValueObject);
		model.addAttribute("selectVactnList", selectVactnList);
		
		return "market/emp/EmpRead";
	}
	
	/**
	 * 직원관리 직원 등록으로 이동하는 메서드입니다.
	 *
	 * @param   - 
	 * @return  String
	 * @exception  Exception
	 */

	@RequestMapping(value = "/empCreateRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String empCreateRead() throws Exception {
		Logger.info(null);
		return "market/emp/EmpCreate";
	}
	
	/**
	 * 직원관리 직원 등록하는 메서드입니다.
	 *
	 * @param   MultipartHttpServletRequest, EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */
	
	@RequestMapping(value = "/empCreate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String empCreate(MultipartHttpServletRequest multiRequest, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);

//		ServletContext context = request.getSession().getServletContext();
//		String realFolder = context.getRealPath("/resources/bear/bear.jpg");
		
		EmpService.empCreate(multiRequest, empValueObject);
		return "redirect:empList.do";
	}
	
	/**
	 * 직원관리 직원 정보 수정화면으로 이동하는 메서드입니다.
	 *
	 * @param   ModelMap, EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	@RequestMapping(value = "/empUpdateRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String empUpdateRead(ModelMap model, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		List<EmpValueObject> selectEmpRead = EmpService.selectEmpRead(empValueObject.getEmpSeq());
		model.addAttribute("selectEmpRead", selectEmpRead);
		return "market/emp/EmpUpdate";
	}
	
	/**
	 * 직원관리 직원 정보 수정하는 메서드입니다.
	 *
	 * @param   ModelMap, MultipartHttpServletRequest, EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	@RequestMapping(value = "/empUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String empUpdate(ModelMap model, MultipartHttpServletRequest multiRequest, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		EmpService.empUpdate(multiRequest, empValueObject);
		
		List<EmpValueObject> selectEmpRead = EmpService.selectEmpRead(empValueObject.getEmpSeq());
		model.addAttribute("selectEmpRead", selectEmpRead);
		return "market/emp/EmpRead";
	}

	
	/**
	 * 직원관리 직원 정보 삭제하는 메서드입니다.
	 *
	 * @param   EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	@RequestMapping(value = "/empDelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String empDelete(EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		EmpService.empDelete(empValueObject);
		
		return "redirect:empList.do";
	}
	
	/**
	 * 직원관리 직원 정보 수정화면으로 이동하는 메서드입니다.
	 *
	 * @param   ModelMap, EmpValueObject 
	 * @return  String
	 * @exception  Exception
	 */

	@RequestMapping(value = "/empPWUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String empPWUpdate(ModelMap model, HttpSession session, HttpServletRequest request, EmpValueObject empValueObject) throws Exception {
		Logger.info(null);
		
		String pw = request.getParameter("pw") == null?"":request.getParameter("pw");
		empValueObject.setPw(pw);
		
		session.getAttribute("loginUserInfo");
		LoginValueObject userLoginInfo = (LoginValueObject)session.getAttribute("loginUserInfo");
		
		empValueObject.setEmpSeq(userLoginInfo.getEmpSeq());
		EmpService.empPWUpdate(empValueObject);
		
		List<EmpValueObject> selectEmpRead = EmpService.selectEmpRead(empValueObject.getEmpSeq());
		model.addAttribute("selectEmpRead", selectEmpRead);
		return "market/emp/EmpUpdate";
	}
}
