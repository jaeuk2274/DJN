package net.su.end.controller;

import java.util.List;

import javax.annotation.Resource;

import net.su.end.service.OrdrAnalService;
import net.su.end.valueObject.OrdrAnalValueObject;
import net.su.end.valueObject.SalsValueObject;
import net.su.logger.Logger;
import net.su.prodct.service.ProdctService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 주문분석 관련 컨트롤러입니다.
 * 
 * @see   net.su.end.controller.OrdrAnalController
 * @version  1.0 
 * @ author 시상일, 2016/08/25
 */
@Controller
public class OrdrAnalController {

	@Resource
	private ProdctService prodctService;
	@Resource
	private OrdrAnalService ordrAnalService;
	
	/**
		* 일별주문 통계 화면으로 이동하는 메서드입니다.
		*
		* @param   Model,SalsValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/DayOrdrAnal.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String dayOrdrAnal(Model model, SalsValueObject salsValueObject) throws Exception {		
		Logger.info(null);		
		System.out.println("재욱이 바보"+salsValueObject.getBtnDatSech());
		List<OrdrAnalValueObject> ordrList = ordrAnalService.dayOrdrAnal(salsValueObject); 
			
		model.addAttribute("ordrList",ordrList); // 일별주문 현황 리스트
		model.addAttribute("SalsValueObject", salsValueObject);
		
		return "end/ordrAnal/DayOrdrAnal";
	}
	

	/**
		* 월별주문 화면으로 이동하는 메서드입니다.
		*
		* @param   Model,SalsValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/MonthOrdrAnal.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String monthOrdrAnal(Model model, SalsValueObject salsValueObject) throws Exception {		
		Logger.info(null);
		System.out.println("재욱이 바보"+salsValueObject.getBtnDatSech());
		List<OrdrAnalValueObject> ordrList = ordrAnalService.monthOrdrAnal(salsValueObject);
		model.addAttribute("ordrList",ordrList); 
		model.addAttribute("SalsValueObject", salsValueObject); // 검색 정보들
		return "end/ordrAnal/MonthOrdrAnal";
	}	
	
	/**
		* 상품별 주문 통계 화면으로 이동하는 메서드입니다.
		*
		* @param   Model,SalsValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/ProdctOrdrAnal.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String prodctOrdrAnal(Model model, SalsValueObject salsValueObject) throws Exception {		
		Logger.info(salsValueObject.getOrdrType());	
		List<OrdrAnalValueObject>ordrTypeList=ordrAnalService.ordrTypeList(salsValueObject); //주문종류리스트
		List<OrdrAnalValueObject> prodctOrdrList = ordrAnalService.prodctOrdrAnal(salsValueObject); 
		String[] topCatgrArray= prodctService.catgrList("기본",""); 
		String[] midCatgrArray=prodctService.catgrList("대",salsValueObject.getTopCatgrNme());
		String[] botCatgrArray=prodctService.catgrList("중",salsValueObject.getMidCatgrNme());
			
		model.addAttribute("ordrTypeList",ordrTypeList);
		model.addAttribute("prodctOrdrList",prodctOrdrList); // 상품별주문 현황 리스트
		model.addAttribute("topCatgrArray",topCatgrArray);
		model.addAttribute("midCatgrArray",midCatgrArray);
		model.addAttribute("botCatgrArray",botCatgrArray);
		model.addAttribute("SalsValueObject", salsValueObject);
		
		return "end/ordrAnal/ProdctOrdrAnal";
	}
	
	/**
		* 일별주문 엑셀 다운을 위한 화면으로 이동하는 메서드입니다.
		*
		* @param   Model,SalsValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/DayOrdrAnalXls.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String dayOrdrAnalXls(Model model, SalsValueObject salsValueObject) throws Exception {		
		Logger.info(null);			
		List<OrdrAnalValueObject> ordrList = ordrAnalService.dayOrdrAnal(salsValueObject); // 일별주문 현황 리스트 
		model.addAttribute("ordrList",ordrList); // 일별주문 현황 리스트 
		return "end/ordrAnal/DayOrdrAnalXls";
	}
	
	
	/**
		* 월별주문 엑셀 다운을 위한 화면으로 이동하는 메서드입니다.
		*
		* @param   Model,SalsValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/MonthOrdrAnalXls.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String monthOrdrAnalXls(Model model, SalsValueObject salsValueObject) throws Exception {		
		Logger.info(null);			
		List<OrdrAnalValueObject> ordrList = ordrAnalService.monthOrdrAnal(salsValueObject); // 월별주문 현황 리스트
		model.addAttribute("ordrList",ordrList); // 월별주문 현황 리스트
		return "end/ordrAnal/MonthOrdrAnalXls";
	}
	
	/**
		* 상품별 주문 엑셀 다운을 위한 화면으로 이동하는 메서드입니다.
		*
		* @param   Model,SalsValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/ProdctOrdrAnalXls.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String prodctOrdrAnalXls(Model model, SalsValueObject salsValueObject) throws Exception {		
		Logger.info(null);		
		salsValueObject.setTopCatgrNme(salsValueObject.getSelectedTopCatgrNme());
		salsValueObject.setMidCatgrNme(salsValueObject.getSelectedMidCatgrNme());
		salsValueObject.setBotCatgrNme(salsValueObject.getSelectedBotCatgrNme());	
		List<OrdrAnalValueObject> prodctOrdrList = ordrAnalService.prodctOrdrAnal(salsValueObject);		
		model.addAttribute("prodctOrdrList",prodctOrdrList); // 상품별주문 현황 리스트
		return "end/ordrAnal/ProdctOrdrAnalXls";
	}
	
	
}
