package net.su.end.controller;

import java.util.List;

import javax.annotation.Resource;

import net.su.end.service.SalsService;
import net.su.end.valueObject.SalsValueObject;
import net.su.logger.Logger;
import net.su.prodct.service.ProdctService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 매출분석 관련 컨트롤러입니다.
 * 
 * @see   net.su.end.controller.SalsController
 * @version  1.0 
 * @ author 최재욱, 2016/08/21
 */
@Controller
public class SalsController {

	@Resource
	private ProdctService prodctService;
	@Resource
	private SalsService salsService;
	
	/**
		* 일별매출 화면으로 이동하는 메서드입니다.
		*
		* @param   SalsValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/DaySalsAnal.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String DaySalsAnal(Model model, SalsValueObject salsValueObject) throws Exception {		
		Logger.info(null);		
		List<SalsValueObject> salsList = salsService.DaySalsAnal(salsValueObject); 
		String[] topCatgrArray= prodctService.catgrList("기본",""); 
		String[] midCatgrArray=prodctService.catgrList("대",salsValueObject.getTopCatgrNme());
		String[] botCatgrArray=prodctService.catgrList("중",salsValueObject.getMidCatgrNme());
			
		model.addAttribute("SalsList",salsList); // 일별매출 현황 리스트
		model.addAttribute("topCatgrArray",topCatgrArray);
		model.addAttribute("midCatgrArray",midCatgrArray);
		model.addAttribute("botCatgrArray",botCatgrArray);
		model.addAttribute("SalsValueObject", salsValueObject);
		
		return "end/salsAnal/DaySalsAnal";
	}
	

	/**
		* 월별매출 화면으로 이동하는 메서드입니다.
		*
		* @param   SalsValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/MonthSalsAnal.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String MonthSalsAnal(Model model, SalsValueObject salsValueObject) throws Exception {		
		Logger.info(null);
		List<SalsValueObject> salsList = salsService.monthSalsAnal(salsValueObject);
		String[] topCatgrArray= prodctService.catgrList("기본",""); 
		String[] midCatgrArray=prodctService.catgrList("대",salsValueObject.getTopCatgrNme());
		String[] botCatgrArray=prodctService.catgrList("중",salsValueObject.getMidCatgrNme());
			
		model.addAttribute("SalsList",salsList); 
		model.addAttribute("topCatgrArray",topCatgrArray);
		model.addAttribute("midCatgrArray",midCatgrArray);
		model.addAttribute("botCatgrArray",botCatgrArray);
		model.addAttribute("SalsValueObject", salsValueObject); // 검색 정보들
		
		return "end/salsAnal/MonthSalsAnal";
	}	
	
	
	
	/**
		* 일별매출 엑셀 다운을 위한 화면으로 이동하는 메서드입니다.
		*
		* @param   SalsValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/daySalsXls.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String daySalsXls(Model model, SalsValueObject salsValueObject) throws Exception {		
		Logger.info(null);		
		salsValueObject.setTopCatgrNme(salsValueObject.getSelectedTopCatgrNme());
		salsValueObject.setMidCatgrNme(salsValueObject.getSelectedMidCatgrNme());
		salsValueObject.setBotCatgrNme(salsValueObject.getSelectedBotCatgrNme());	
		List<SalsValueObject> salsList = salsService.DaySalsAnal(salsValueObject); // 일별매출 현황 리스트		
		model.addAttribute("SalsList",salsList); // 일별매출 현황 리스트
		return "end/salsAnal/DaySalsAnalXls";
	}
	
	
	/**
		* 일별매출 엑셀 다운을 위한 화면으로 이동하는 메서드입니다.
		*
		* @param   SalsValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/monthSalsXls.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String monthSalsXls(Model model, SalsValueObject salsValueObject) throws Exception {		
		Logger.info(null);		
		salsValueObject.setTopCatgrNme(salsValueObject.getSelectedTopCatgrNme());
		salsValueObject.setMidCatgrNme(salsValueObject.getSelectedMidCatgrNme());
		salsValueObject.setBotCatgrNme(salsValueObject.getSelectedBotCatgrNme());	
		List<SalsValueObject> salsList = salsService.monthSalsAnal(salsValueObject); // 일별매출 현황 리스트		
		model.addAttribute("SalsList",salsList); // 일별매출 현황 리스트
		return "end/salsAnal/MonthSalsAnalXls";
	}
	
	
}
