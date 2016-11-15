package net.su.custmr.controller;

import java.util.List;

import javax.annotation.Resource;

import net.su.custmr.service.CustmrService;
import net.su.custmr.valueObject.CustmrValueObject;
import net.su.logger.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 고객관리 메뉴 관련 컨트롤러입니다.
 * 
 * @see   net.su.custmr.controller.CustmrController
 * @version  1.0 
 * @ author 최재욱, 2016/08/09
 */
@Controller
public class CustmrController {
	
	@Resource
	private CustmrService custmrService;
	/**
		* 고객관리 고객조회(휴먼x)로 이동하는 메서드입니다.
		*
		* @param   schedlValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/custmrList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String custmrList(Model model, CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		custmrValueObject.setCustmrQuscncCheck("N");
		List<CustmrValueObject> custmrList = custmrService.selectCustmrList(custmrValueObject);	// 고객 리스트			
		model.addAttribute("CustmrValueObject", custmrValueObject);	// 검색 조건, 페이지 정보들			
		model.addAttribute("CustmrList", custmrList); // 고객 리스트
			
		return "custmr/custmr/custmrList";
	}
	
	/**
		* 체크박스로 선택한 고객들의 카드여부를 Y로 바꿔주는 메서드 (일반 고객 목록)
		*
		* @param   schedlValueObject
		* @return  StringchangeCustmrCardChkGroup
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/changeCustmrCardChkGroup.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String changeCustmrCardChkGroup(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		custmrService.changeCustmrCardChkGroup(custmrValueObject.getCustmrChkGroup());	
		return "forward:/custmrList.do";
	}
	
	/**
		* 고객 상세보기 화면으로 이동하는 메서드(팝업)
		*
		* @param   schedlValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/custmrReadPopup.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String custmrReadPopup(Model model, CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		CustmrValueObject custmrReadVO = custmrService.custmrRead(custmrValueObject); // 상세보기			
		model.addAttribute("CustmrReadVO", custmrReadVO);	// 고객 상세보기  vo			
		return "custmr/custmr/custmrReadPopup";
	}
	
	/**
		* 고객 상세정보 수정화면으로 이동하는 메서드(팝업)
		*
		* @param   schedlValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/custmrUpdatePopup.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String custmrUpdatePopup(Model model, CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		CustmrValueObject custmrReadVO = custmrService.custmrRead(custmrValueObject); // 상세보기		
		model.addAttribute("CustmrReadVO", custmrReadVO);	// 고객 상세보기  vo		
		return "custmr/custmr/custmrUpdatePopup";
	}
	
	/**
		* 고객 상세보를 수정하는 메서드(팝업)
		*
		* @param   schedlValueObject 
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/custmrUpdate.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String custmrUpdate(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		custmrService.custmrUpdate(custmrValueObject); // 고객 상세정보 수정				
		return "forward:/custmrReadPopup.do";
	}
	
	/**
		* 고객 정보를 삭제하는 메서드(팝업)
		*
		* @param   schedlValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/custmrDelete.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String custmrDelete(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		custmrService.custmrDelete(custmrValueObject); // 고객 상세정보 삭제
		return "forward:/custmrReadPopup.do";
	}
	
	/**
		* 고객관리 고객조회(휴먼 고객)로 이동하는 메서드입니다.
		*
		* @param   schedlValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	//@RequestMapping(value = "/custmrList.do", method = RequestMethod.POST)
	@RequestMapping(value = "/quscncCustmrList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String quscncCustmrList(Model model, CustmrValueObject custmrValueObject)  throws Exception{
		Logger.info(null);
		custmrValueObject.setCustmrQuscncCheck("Y");
		List<CustmrValueObject> custmrList = custmrService.selectCustmrList(custmrValueObject);	// 고객 리스트		
		model.addAttribute("CustmrValueObject", custmrValueObject);	// 검색 조건, 페이지 정보들			
		model.addAttribute("CustmrList", custmrList); // 고객 리스트			
		return "custmr/custmr/quscncCustmrList";
	}
		
	/**
		* 체크박스로 선택한 휴면 고객들을 일반 고객으로 바꿔주는 메서드 
		*
		* @param   schedlValueObject 
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/changeQuscncCustmrGroup.do", method = RequestMethod.POST)
	public String changeQuscncCustmrGroup(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		custmrService.changeQuscncCustmrGroup(custmrValueObject.getCustmrChkGroup());	
		return "forward:/quscncCustmrList.do";
	}
	
	/**
		* 체크박스로 선택한 휴면 고객들을 일괄 삭제하는 메서드 
		*
		* @param   schedlValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/deleteQuscncCustmrGroup.do", method = RequestMethod.POST)
	public String deleteQuscncCustmrGroup(CustmrValueObject custmrValueObject) throws Exception {
		Logger.info(null);
		custmrService.deleteQuscncCustmrGroup(custmrValueObject.getCustmrChkGroup());	
		return "forward:/quscncCustmrList.do";
	}
}
