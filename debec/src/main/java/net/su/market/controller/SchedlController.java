package net.su.market.controller;

import java.util.List;
import javax.annotation.Resource;

import net.su.market.service.SchedlService;
import net.su.market.valueObject.SchedlValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.codehaus.jackson.map.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 일정관리 메뉴 관련 컨트롤러입니다.
 * 
 * @see   net.su.market.controller.SchedlController
 * @version  1.0 
 * @ author 최재욱, 2016/08/15
 */
@Controller
public class SchedlController {

	@Resource
	private SchedlService schedlService;
	/**
		* 일정관리 월간일정으로 이동하는 메서드입니다.
		*
		* @param   SchedlValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/SchedlList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String SchedlList(Model model, SchedlValueObject schedlValueObject) throws Exception {
		System.out.println("schedlController의 /SchedlList.do 작동");
		model.addAttribute("SchedlSechVO", schedlValueObject);
		return "market/schedl/SchedlList";
	}
		
	/**
		* 일정관리 월간일정을 조회하는 메서드입니다.
		*
		* @param   SchedlValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/SchedlListRead.do")
	@ResponseBody
	public Object SchedlListRead(SchedlValueObject schedlValueObject) throws Exception {
		System.out.println("schedlController의 /SchedlListRead.do 작동");
		List<SchedlValueObject> calList = schedlService.SchedlListRead(); 
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(calList);
	}	
	
	/**
	* 일정관리 월간일정을 등록화면으로 이동하는 메서드입니다.(팝업)
		*
		* @param   x
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/SchedlCreatePopup.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String SchedlCreatePopup() throws Exception {
		System.out.println("schedlController의 /SchedlCreatePopup.do 작동");
		return "market/schedl/SchedlCreatePopup";
	}		

	/**
	* 일정관리 월간일정을 등록하는 메서드입니다.
		*
		* @param   SchedlValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/SchedlCreate.do", method = {RequestMethod.POST, RequestMethod.GET})
	public void SchedlCreate(SchedlValueObject schedlValueObject) throws Exception {
		System.out.println("schedlController의 /SchedlCreate.do 작동");		
		schedlService.SchedlCreate(schedlValueObject); 
//		return "market/schedl/SchedlCreatePopup";
	}
	
	/**
	* 월간일정 클릭시 상세보기화면을 보여주는 메서드입니다.(팝업)
		*
		* @param   SchedlValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/SchedlReadPopup.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String SchedlReadPopup(Model model, SchedlValueObject schedlValueObject) throws Exception {
		System.out.println("schedlController의 /SchedlReadPopup.do 작동");
		System.out.println("id : " + schedlValueObject.getId());	
		SchedlValueObject schedlReadVO = schedlService.SchedlReadPopup(schedlValueObject);
		
		model.addAttribute("SchedlReadVO", schedlReadVO);
		return "market/schedl/SchedlReadPopup";
	}

	/**
		* 일정 수정화면으로 이동하는 메서드입니다.
		*
		* @param   SchedlValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/SchedlUpdatePopup.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String SchedlUpdatePopup(Model model, SchedlValueObject schedlValueObject) throws Exception {
		System.out.println("schedlController의 /SchedlUpdatePopup.do 작동");
		System.out.println("id : " + schedlValueObject.getId());	
		SchedlValueObject schedlReadVO = schedlService.SchedlReadPopup(schedlValueObject);		
		model.addAttribute("SchedlReadVO", schedlReadVO);
		return "market/schedl/SchedlUpdatePopup";
	}
	
	/**
		* 일정을 삭제하는 메서드입니다.
		*
		* @param   SchedlValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/SchedlDelete.do", method = {RequestMethod.POST, RequestMethod.GET})
	public void SchedlDelete(Model model, SchedlValueObject schedlValueObject) throws Exception {
		System.out.println("schedlController의 /SchedlDelete.do 작동");
		System.out.println("id : " + schedlValueObject.getId());	
		schedlService.SchedlDelete(schedlValueObject);		
	}
	
	
	/**
		* 일정을 수정하는 메서드입니다.
		*
		* @param   SchedlValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/SchedlUpdate.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String SchedlUpdate(SchedlValueObject schedlValueObject) throws Exception {
		System.out.println("schedlController의 /SchedlUpdate.do 작동");
		System.out.println("id : " + schedlValueObject.getId());	
		schedlService.SchedlUpdate(schedlValueObject);		
		
		return "forward:/SchedlReadPopup.do";
	}

	
}
