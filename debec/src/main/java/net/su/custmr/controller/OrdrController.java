package net.su.custmr.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.su.custmr.service.OrdrService;
import net.su.custmr.valueObject.OrdrValueObject;
import net.su.logger.Logger;
import net.su.login.valueObject.LoginValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
/**
 * 주문관리 관련 컨트롤러입니다.
 * 
 * @see   net.su.custmr.controller.CustmrController
 * @version  1.0 
 * @ author 최재욱, 2016/08/09
 */
@Controller
public class OrdrController {

	@Resource
	private OrdrService ordrService;
	
	/**
		* 전화 주문 등록을 하는 화면으로 이동하는 메서드입니다.
		*
		* @param   Model model, OrdrValueObject ordrValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/ordrRecrdCreate.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String ordrRecrdCreate(Model model, OrdrValueObject ordrValueObject) throws Exception {	
		Logger.info(null);		
		model.addAttribute("OrdrValueObject", ordrValueObject);	// 검색 조건	
		return "custmr/ordr/ordrRecrdCreate";
	}
																															
	/**
		* 실시간 주문내역 조회를 하는 화면으로 이동하는 메서드입니다.
		*
		* @param   Model model, OrdrValueObject ordrValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/realTimeOrdrRecrdList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String realTimeOrdrRecrdList(Model model, OrdrValueObject ordrValueObject) throws Exception {	
		Logger.info(null);																						
		List<OrdrValueObject> realTimeOrdrRecrdList = ordrService.selectRealTimeOrdrRecrdList(ordrValueObject);
		model.addAttribute("RealTimeOrdrRecrdList", realTimeOrdrRecrdList);	// 검색 조건	
		return "custmr/ordr/realTimeOrdrRecrdList";
	}
	
	/**
		* 주문내역을 상세조회하는 메서드입니다.
		*
		* @param   Model model, OrdrValueObject ordrValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/ordrRecrdRead.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String ordrRecrdRead(Model model, OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);		
		OrdrValueObject ordrRecrdVO = ordrService.ordrRecrdRead(ordrValueObject); // 주문 상세내역
		List<OrdrValueObject> ordrRecrdProdctList = ordrService.ordrRecrdProdctRead(ordrValueObject);	
		model.addAttribute("OrdrValueObject", ordrValueObject);	// 돌아가기 관련 정보들, 페이지, 검색조건 등
		model.addAttribute("OrdrRecrdVO", ordrRecrdVO);	// 주문 정보
		model.addAttribute("OrdrRecrdProdctList", ordrRecrdProdctList);	// 상품리스트		
		return "custmr/ordr/ordrRecrdRead";
	}

	/**
		* 주문내역 리스트(취소 제외)를 조회하는 메서드입니다.
		*
		* @param   Model model, OrdrValueObject ordrValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/ordrRecrdList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String ordrRecrdList(Model model, OrdrValueObject ordrValueObject) throws Exception {	
		Logger.info(null);	
		List<OrdrValueObject> ordrRecrdList = ordrService.ordrRecrdList(ordrValueObject);
		model.addAttribute("OrdrValueObject", ordrValueObject);	
		model.addAttribute("OrdrRecrdList", ordrRecrdList);	
		return "custmr/ordr/ordrRecrdList";
	}
	
	/**
		* 주문 취소 내역 리스트를 조회하는 메서드입니다.
		*
		* @param   Model model, OrdrValueObject ordrValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/ordrCanclRecrdList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String ordrCanclRecrdList(Model model, OrdrValueObject ordrValueObject) throws Exception {	
		Logger.info(null);	
		ordrValueObject.setOrdrStatSeq(4);
		List<OrdrValueObject> ordrRecrdList = ordrService.ordrRecrdList(ordrValueObject);		
		model.addAttribute("OrdrValueObject", ordrValueObject);	
		model.addAttribute("OrdrRecrdList", ordrRecrdList);	
		return "custmr/ordr/ordrCanclRecrdList";
	}

	/**
		* 주문내역 리스트에서 일괄 배달 접수를 하는 메서드입니다.(주문정보에 로그인한 직원정보가 배달원으로 업데이트)
		* (주문접수 -> 배달중만 가능)
		* @param   HttpSession session, Model model, OrdrValueObject ordrValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/changeOrdrStatGroup.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String changeOrdrStatGroup(HttpSession session, Model model, OrdrValueObject ordrValueObject) throws Exception {	
		Logger.info(null);	
		LoginValueObject loginUserInfo = (LoginValueObject)session.getAttribute("loginUserInfo");
		ordrValueObject.setEmpSeq(loginUserInfo.getEmpSeq());
		ordrService.changeOrdrStatGroup(ordrValueObject);
		return "forward:/ordrRecrdList.do";
	}
	
	/**
		* 주문내역 상세보기에서 배달 상태변경을 하는 메서드입니다.
		* 
		* @param   HttpSession session, Model model, OrdrValueObject ordrValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/changeOrdrStat.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String changeOrdrStat(HttpSession session, Model model, OrdrValueObject ordrValueObject) throws Exception {	
		Logger.info(null);	
		LoginValueObject loginUserInfo = (LoginValueObject)session.getAttribute("loginUserInfo");
		ordrValueObject.setEmpSeq(loginUserInfo.getEmpSeq());		
		ordrService.changeOrdrStat(ordrValueObject);	
		return "forward:/ordrRecrdRead.do";
	}
	
	
	/**
		* 주문내역 상세보기에서 이미지 등록을 하는 메서드입니다.
		* 
		* @param   MultipartHttpServletRequest request, OrdrValueObject ordrValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/imgInsert.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String imgInsert(MultipartHttpServletRequest request, OrdrValueObject ordrValueObject) throws Exception{		
		Logger.info(null);	
		ordrService.imgInsert(request, ordrValueObject);		
		return "forward:/ordrRecrdRead.do";
	}

	
	/**
		* 주문내역 상세보기에서 체크박스 선택한 이미지 삭제를 하는 메서드입니다.
		* 
		* @param   OrdrValueObject ordrValueObject
		* @return  String
		* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/imgDelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String imgDelete(OrdrValueObject ordrValueObject) throws Exception{		
		Logger.info(null);	
		ordrService.imgDelete(ordrValueObject);		
		return "forward:/ordrRecrdRead.do";
	}
	
	
	
}

