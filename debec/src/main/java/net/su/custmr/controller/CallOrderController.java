package net.su.custmr.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.su.app.appComnty.valueObject.ComntyValueObject;
import net.su.custmr.service.CallOrderService;
import net.su.custmr.valueObject.CallOrderValueObject;
import net.su.custmr.valueObject.CustmrValueObject;
import net.su.logger.Logger;
import net.su.prodct.service.DebecFestivalService;
import net.su.prodct.valueObject.DebecFestivalValueObject;
import net.su.prodct.valueObject.ProdctFestivalValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class CallOrderController {

	@Resource
	private CallOrderService CallOrderService;
	
	@Resource
	private DebecFestivalService DebecFestivalService;
	
	/**
    * (조회) 전화주문 등록 전환 메서드입니다.
    *
    * @param   @RequestParam Model
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/callOrderCreateRead.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public String callOrderCreateRead(Model model, @RequestParam(value="custmrClassify", required=false, defaultValue="미지정") String custmrClassify, CallOrderValueObject callOrderValueObject)  throws Exception {
		Logger.info(null);
	
		int custmrSeq=callOrderValueObject.getCustmrSeq();

		if(custmrClassify.equals("회원")){
			callOrderValueObject.setCustmrClassify("회원");
		}
		else if(custmrClassify.equals("비회원")){
			callOrderValueObject.setCustmrClassify("비회원");
		}
		
		if(custmrSeq!=0){
			CustmrValueObject custmrValueObject = CallOrderService.custmrInfo(custmrSeq); //고객 정보
			List<CallOrderValueObject> shippingPlaceList = CallOrderService.shippingPlaceList(custmrSeq); //배송지 리스트
			
			System.out.println(custmrValueObject.getCustmrId()+"돌았을떄 손님");
			
			model.addAttribute("shippingPlaceList", shippingPlaceList);
			model.addAttribute("custmrValueObject", custmrValueObject);
		}
		
		List<ProdctValueObject> prodctTempList = CallOrderService.prodctTempList();	
		model.addAttribute("prodctTempList", prodctTempList);
		model.addAttribute("callOrderValueObject", callOrderValueObject);

		return "custmr/ordr/CallOrderCreate";
	}
	
	/**
    * (조회) 전화주문 등록 전환 메서드입니다.
    *
    * @param   @RequestParam Model
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/callOrderCreateProdctRead.do", method = {RequestMethod.GET, RequestMethod.POST} )
	@ResponseBody
	public  List<ProdctValueObject> callOrderCreateProdctRead(Model model, CallOrderValueObject callOrderValueObject)  throws Exception {
		Logger.info(null);
		
		List<ProdctValueObject> prodctTempList = CallOrderService.prodctTempList();	
		model.addAttribute("prodctTempList", prodctTempList);

		return prodctTempList;
	}
		
	/**
    * (조회) 고객 선택 팝업창 전환 메서드입니다.
    *
    * @param   @RequestParam Model
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/custmrChoicePopup.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public String custmrChoicePopup(Model model, CustmrValueObject custmrValueObject)  throws Exception {
		Logger.info(null);
		List<CustmrValueObject> custmrList = CallOrderService.custmrList(custmrValueObject);
		
		model.addAttribute("custmrList", custmrList);
		model.addAttribute("custmrValueObject", custmrValueObject);

		return "custmr/ordr/CallOrderCustmrChoicePopup";
	}
	/**
    * (조회) 배송지 선택시 배송지 정보 조회 메서드입니다.
    *
    * @param   @RequestParam Model, CallOrderValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/shipngPlcRead.do", method = {RequestMethod.GET, RequestMethod.POST} )
	@ResponseBody
	public CallOrderValueObject shipngPlcRead(CallOrderValueObject callOrderValueObject)  throws Exception {
		Logger.info(null);
		int shipngPlcSeq = callOrderValueObject.getShipngPlcSeq();
		callOrderValueObject = CallOrderService.shipngPlcInfo(shipngPlcSeq);
		System.out.println(callOrderValueObject.getShipngPlcAdrs()+"돌았을떄 배송지 주소");

		return callOrderValueObject;
	}
		
	/**
    * 상품추가 팝업 띄우는 메서드입니다.
    *
    * @param   @RequestParam Model
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/callOrdrProdctCreatePopup.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public String callOrdrProdctCreatePopup(Model model) throws Exception {
		Logger.info(null);

		return "custmr/ordr/ProdctCreatePopup";
	}	
	
	/**
    * 상품추가 테이블1 생성 메서드입니다.
    *
    * @param   @RequestParam Model, HttpServletRequest, ProdctValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/callOrderProdctAddList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String prodctAddList(HttpServletRequest request, Model model, ProdctValueObject prodctValueObject) throws Exception{
		Logger.info(null);
		String prodctSechText = request.getParameter("prodctSechText") == null?"":request.getParameter("prodctSechText");
		prodctValueObject.setProdctSechText(prodctSechText);
		List<ProdctValueObject> prodctAdList = CallOrderService.prodctAddList(prodctValueObject);
		model.addAttribute("prodctValueObject", prodctValueObject);	// 검색 조건, 페이지 정보들			
		model.addAttribute("prodctAdList", prodctAdList);
		
		return "custmr/ordr/ProdctTable";
	}
	
	/**
    * 상품추가 테이블2 생성 메서드입니다.
    *
    * @param   @RequestParam Model, HttpServletRequest, ProdctValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/callOrderProdctAdTempList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String callOrderProdctAdTempList(HttpServletRequest request, Model model, ProdctValueObject prodctValueObject) throws Exception{
		Logger.info(null);
		String tempSechText = request.getParameter("tempSechText") == null?"":request.getParameter("tempSechText");
		prodctValueObject.setTempSechText(tempSechText);
		List<ProdctValueObject> prodctAdTempList = CallOrderService.prodctAddTempList(prodctValueObject);
		model.addAttribute("prodctValueObject", prodctValueObject);	// 검색 조건, 페이지 정보들			
		model.addAttribute("prodctAdTempList", prodctAdTempList);
		
		return "custmr/ordr/TempTable";
	}
	
	/**
    * 상품 선택 추가 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/callOrderProdctAdTempCreate.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void callOrderProdctAdTempCreate(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		System.out.println(chkedVal+"@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		CallOrderService.prodctAdTempCreate(data);
	}
	
	/**
    * 상품 선택 제거 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/callOrderProdctAddTempDelete.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void callOrderProdctAddTempDelete(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		System.out.println(chkedVal+"@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		CallOrderService.prodctAddTempDelete(data);
	}
	
	/**
    * 상품 선택 삭제 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/callOrderProdctDelete.do", method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String prodctDelete(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
	
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
		}
		DebecFestivalService.prodctDelete(data);

		return "foward:/callOrderCreateRead.do";
	}
	
	/**
    * 전화주문 최종 등록 메서드입니다.
    *
    * @param   @RequestParam CallOrderValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/callOrderCreate.do", method = {RequestMethod.GET, RequestMethod.POST} )
	@ResponseBody
	public void callOrderCreate(CallOrderValueObject callOrderValueObject) throws Exception {
		Logger.info(null);
		System.out.println(callOrderValueObject.getRecvrNme()+"주문자 ");
		System.out.println(callOrderValueObject.getRecvrMobl()+"주문자 번호");
		CallOrderService.callOrderCreate(callOrderValueObject);
		
	}
		
	/**
    * 최종 등록 시 상품 리스트 임시테이블 수량 수정 메서드입니다.
    *
    * @param   @RequestParam CallOrderValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/callOrderProdctTempUpdate.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public String callOrderProdctTempUpdate(CallOrderValueObject callOrderValueObject) throws Exception {
		Logger.info(null);
		CallOrderService.callOrderProdctTempUpdate(callOrderValueObject);
		
		return "custmr/ordr/CallOrderCreate"; 
		
	}
}
