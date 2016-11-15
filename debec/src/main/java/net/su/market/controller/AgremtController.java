package net.su.market.controller;

import javax.annotation.Resource;

import net.su.market.service.AgremtService;
import net.su.market.valueObject.AgremtValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 이용약관 관리 관련 컨트롤러입니다.
 * 
 * @see   net.su.market.controller.AgremtController
 * @version  1.0 
 * @ author 최재욱, 2016/08/15
 */
@Controller
public class AgremtController {

	@Resource
	private AgremtService agremtService;
	
	/**
		* 이용약관 설정으로 이동하는 메서드입니다.
		*
		* @param   AgremtValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/UseAgremtList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String UseAgremtList(Model model, AgremtValueObject agremtValueObject) throws Exception {
		
		System.out.println("AgremtController의 /UseAgremtList.do 작동");		
		agremtValueObject = agremtService.selectAgremt(agremtValueObject);
		agremtValueObject.setShowStd("N");
		System.out.println("표준약관 보여준다 : " + agremtValueObject.getShowStd());
		model.addAttribute("AgremtVO", agremtValueObject);
		return "market/agremt/UseAgremtList";
	}
		
	/**
		* 이용약관(이용약관 설정, 기타이용약관설정 둘 다)을 수정하는 메서드입니다.
		*
		* @param   AgremtValueObject
		* @return  String
		* @throws Exception 
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/agremtUpdate.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String agremtUpdate(AgremtValueObject agremtValueObject) throws Exception{
		System.out.println("AgremtController의 /agremtUpdate.do 작동");
		System.out.println("seq : " + agremtValueObject.getUseAgremtSeq());
		System.out.println("usecheck : " + agremtValueObject.getUseCheck());
		agremtService.updateAgremt(agremtValueObject);
		return "forward:/UseAgremtList.do";
	}
	
	/**
	* 이용약관 설정(표준을 보여주는)으로 이동하는 메서드입니다.
		*
		* @param   AgremtValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/UseStdAgremtList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String UseStdAgremtList(Model model, AgremtValueObject agremtValueObject) throws Exception {
		System.out.println("AgremtController의 /UseAgremtList.do 작동");		
		agremtValueObject = agremtService.selectAgremt(agremtValueObject);	
		agremtValueObject.setShowStd("Y");
		System.out.println("표준약관 보여준다 : " + agremtValueObject.getShowStd());
		model.addAttribute("AgremtVO", agremtValueObject);
		return "market/agremt/UseAgremtList";
	}
	
	/**
		* 기타이용약관 설정 으로 이동하는 메서드입니다.
		*
		* @param   AgremtValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/EtcAgremtList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String EtcAgremtList(Model model, AgremtValueObject agremtValueObject) throws Exception {	
		System.out.println("AgremtController의 /EtcAgremtList.do 작동");		
		if(agremtValueObject.getUseAgremtSeq()==1) {
			agremtValueObject.setUseAgremtSeq(7);
		}
		agremtValueObject = agremtService.selectAgremt(agremtValueObject);
		agremtValueObject.setShowStd("N");
		model.addAttribute("AgremtVO", agremtValueObject);
		return "market/agremt/EtcAgremtList";
	}
	
	/**
		* 기타 이용약관(기타 이용약관 설정)을 수정하는 메서드입니다.
		*
		* @param   AgremtValueObject
		* @return  String
		* @throws Exception 
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/etcAgremtUpdate.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String etcAgremtUpdate(AgremtValueObject agremtValueObject) throws Exception{
		System.out.println("AgremtController의 /etcAgremtUpdate.do 작동");
		System.out.println("seq : " + agremtValueObject.getUseAgremtSeq());
		System.out.println("usecheck : " + agremtValueObject.getUseCheck());
		agremtService.updateAgremt(agremtValueObject);
		return "forward:/EtcAgremtList.do";
	}
	
	/**
	* 기타 이용약관 설정(표준을 보여주는)으로 이동하는 메서드입니다.
		*
		* @param   AgremtValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/EtcStdAgremtList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String EtcStdAgremtList(Model model, AgremtValueObject agremtValueObject) throws Exception {
		System.out.println("AgremtController의 /UseAgremtList.do 작동");
		if(agremtValueObject.getUseAgremtSeq()==1) {
			agremtValueObject.setUseAgremtSeq(7);
		}
		agremtValueObject = agremtService.selectAgremt(agremtValueObject);	
		agremtValueObject.setShowStd("Y");
		model.addAttribute("AgremtVO", agremtValueObject);
		return "market/agremt/EtcAgremtList";
	}
	
}
