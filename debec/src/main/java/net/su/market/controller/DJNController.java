package net.su.market.controller;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.su.login.valueObject.LoginValueObject;
import net.su.market.service.DJNService;
import net.su.market.valueObject.DJNPointValueObject;
import net.su.market.valueObject.DJNValueObject;
import net.su.market.valueObject.RecmndResnValueObject;
import net.su.market.valueObject.RecmndValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 대장남 관련 컨트롤러입니다.
 * 
 * @see   net.su.market.controller.DJNController
 * @version  1.0 
 * @ author 시상일, 2016/08/24
 */
	@Controller
	public class DJNController {
		
	@Resource	
	private DJNService djnService;

	/**
		* 이달의 대장남 관리 화면으로 이동하는 메서드입니다.
		*
		* @param   Model,DJNValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/DJNAdmin.do")
	public String DJNAdmin(Locale locale, Model model, DJNValueObject djnVo) throws Exception {
	   
	      System.out.println("DJNController 클래스  DJNAdmin 메서드 실행");

	      String tYearMonth = djnService.selectTYearMonth(); //오늘의 년월 조회
	      djnVo.setSelectMon(tYearMonth);
	      List<DJNValueObject> djnPointList =djnService.selectDJNPointList(djnVo); // 직원별 대장남 포인트 조회
	      
	      djnVo=djnService.selectDJN(djnVo); //이달의 대장남 정보조회
	      djnVo.setSelectMon(tYearMonth);
	      List<RecmndResnValueObject> djnRecmndResn = djnService.selectDjnRecmndResn(djnVo); //이달의 대장남 고객 추천사유 조회
	      
	      
	      DJNPointValueObject djnPointPerct =djnService.selectDjnPointPerct(); //대장남 포인트 선정기준 조회
	      
	      model.addAttribute("djnRecmndResn", djnRecmndResn);
	      model.addAttribute("tYearMonth", tYearMonth);
	      model.addAttribute("djnVo", djnVo);
	      model.addAttribute("djnPointList", djnPointList);
	      model.addAttribute("djnPointPerct", djnPointPerct);
	      
		return "market/DJN/DJNAdmin";
	}
	
	/**
		* 직원별 고객 추천사유 조회팝업으로 이동하는 메서드입니다.
		*
		* @param  Model,int
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/DJNAdminPopup.do", method = RequestMethod.GET)
	public String DJNAdminPopup(Locale locale, Model model, @RequestParam("empSeq") int empSeq, @RequestParam("selectMon") String selectMon) throws Exception {
		System.out.println("DJNController 클래스  DJNAdminPopup 메서드 실행");
		DJNValueObject djnVo = new DJNValueObject();
		djnVo.setEmpSeq(empSeq);
		djnVo.setSelectMon(selectMon);
		List<RecmndResnValueObject> recmndResn = djnService.selectRecmndResn(djnVo); //고객 추천사유 조회
		model.addAttribute("recmndResn", recmndResn);
		return "market/DJN/DJNAdminPopup";
	}
	
	/**
		* 이달의 대장남 관리 월별 조회화면  메서드입니다.
		*
		* @param   Model,DJNValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/monDJNAdmin.do")
	public String monDJNAdmin(Locale locale, Model model, DJNValueObject djnVo) throws Exception {
		
		String tYearMonth = djnVo.getSelectMon();// 조회 년월
		djnVo.setSelectMon(tYearMonth);
		djnVo =djnService.selectDJN(djnVo); //이달의 대장남 정보조회
		djnVo.setSelectMon(tYearMonth);
		
		List<RecmndResnValueObject> djnRecmndResn = djnService.selectDjnRecmndResn(djnVo); //이달의 대장남 고객 추천사유 조회
		List<DJNValueObject> djnPointList =djnService.selectDJNPointList(djnVo); // 직원별 대장남 포인트 조회
		DJNPointValueObject djnPointPerct =djnService.selectDjnPointPerct(); //대장남 포인트 선정기준 조회
		
		model.addAttribute("djnRecmndResn", djnRecmndResn);
		model.addAttribute("tYearMonth", tYearMonth);
		model.addAttribute("djnVo", djnVo);
		model.addAttribute("djnPointList", djnPointList);
		model.addAttribute("djnPointPerct", djnPointPerct);
		return "market/DJN/DJNAdmin";
	}
	
	/**
		* 대장남 포인트 선정기준 수정  메서드입니다.
		*
		* @param   Model,int
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/updateDjnPointPerct.do")
	public String updateDjnPointPerct(Locale locale, Model model,DJNPointValueObject djnPointVo)throws Exception {
		djnService.updateDjnPointPerct(djnPointVo);
		return "redirect:/DJNAdmin.do";
	}
	
	/**
		* 이달의 대장남 화면으로 이동하는  메서드입니다.
		*
		* @param   Model,DJNValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/DJN.do")
	public String DJN(Locale locale, Model model, DJNValueObject djnVo) throws Exception {
		System.out.println("DJNController 클래스  DJN 메서드 실행");

	      String tYearMonth = djnService.selectTYearMonth(); //오늘의 년월 조회
	      djnVo.setSelectMon(tYearMonth);
	      
	      djnVo=djnService.selectDJN(djnVo); //이달의 대장남 정보조회
	      djnVo.setSelectMon(tYearMonth);      
	      List<RecmndResnValueObject> djnRecmndResn = djnService.selectDjnRecmndResn(djnVo); //이달의 대장남 고객 추천사유 조회
	      
	      DJNPointValueObject djnPointPerct =djnService.selectDjnPointPerct(); //대장남 포인트 선정기준 조회
	      
	      model.addAttribute("djnRecmndResn", djnRecmndResn);
	      model.addAttribute("tYearMonth", tYearMonth);
	      model.addAttribute("djnVo", djnVo);
	      model.addAttribute("djnPointPerct", djnPointPerct);
		return "market/DJN/DJN";
	}
	
	/**
		* 이달의 대장남  월별 조회화면  메서드입니다.
		*
		* @param   Model,DJNValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/monDJN.do")
	public String monDJN(Locale locale, Model model, DJNValueObject djnVo) throws Exception {
		
		String tYearMonth = djnVo.getSelectMon();// 조회 년월
		djnVo.setSelectMon(tYearMonth);
		
		djnVo =djnService.selectDJN(djnVo); //이달의 대장남 정보조회
		djnVo.setSelectMon(tYearMonth);
		List<RecmndResnValueObject> djnRecmndResn = djnService.selectDjnRecmndResn(djnVo); //이달의 대장남 고객 추천사유 조회
		DJNPointValueObject djnPointPerct =djnService.selectDjnPointPerct(); //대장남 포인트 선정기준 조회
		
		model.addAttribute("djnRecmndResn", djnRecmndResn);
		model.addAttribute("tYearMonth", tYearMonth);
		model.addAttribute("djnVo", djnVo);
		model.addAttribute("djnPointPerct", djnPointPerct);
		
		return "market/DJN/DJN";
	}

	/**
		* 직원추천 화면으로 이동하는  메서드입니다.
		*
		* @param  Model,DJNValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/empRecmnd.do")
	public String empRecmnd(Model model,DJNValueObject djnVo) throws Exception {
		System.out.println("DJNController 클래스  empRecmnd 메서드 실행");
		
		List<DJNValueObject> empList =djnService.selectEmpList(); // 직원 리스트 조회
		model.addAttribute("empList",empList);
		return "market/DJN/empRecmnd";
	}
		
	/**
		* 직원 추천 메서드입니다.
		*
		* @param   Model,int,HttpSession
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/recmndEmp.do")
	public String recmndEmp(Model model,@RequestParam("empSeq") int getRecmndEmp,HttpSession session) throws Exception {
		System.out.println("DJNController 클래스  recmndEmp 메서드 실행");
		RecmndValueObject recmndVo = new RecmndValueObject();
		recmndVo.setGetRecmndEmp(getRecmndEmp);
		System.out.println(recmndVo.getGetRecmndEmp()+"받은이");
		LoginValueObject loginUser = (LoginValueObject) session.getAttribute("loginUserInfo");
		int giveRecmndEmp = loginUser.getEmpSeq();
		recmndVo.setGiveRecmndEmp(giveRecmndEmp);
		System.out.println(recmndVo.getGiveRecmndEmp()+"주는이");
		
		djnService.recmndEmp(recmndVo); //직원추천하기
		List<DJNValueObject> empList =djnService.selectEmpList(); // 직원 리스트 조회
		model.addAttribute("empList",empList);
		
		return "market/DJN/empRecmnd";
	}
	
	/**
		* 직원 추천 여부 확인 메서드 입니다.
		*
		* @param   HttpSession
		* @return  int
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/memRecmnChck.do")
	@ResponseBody
	public int memRecmnChck(HttpSession session) throws Exception {
		System.out.println("DJNController 클래스  memRecmnChck 메서드 실행");
		LoginValueObject loginUser = (LoginValueObject) session.getAttribute("loginUserInfo");
		int memRecmnChck = djnService.memRecmnChck(loginUser);
		
		return memRecmnChck;
	}
	
	
}
