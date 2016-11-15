package net.su.prodct.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.su.deal.valueObject.StckValueObject;
import net.su.logger.Logger;
import net.su.prodct.service.DebecFestivalService;
import net.su.prodct.valueObject.DebecFestivalValueObject;
import net.su.prodct.valueObject.ProdctFestivalValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DebecFestivalController {
	
	@Resource
	private DebecFestivalService DebecFestivalService;

	/**
    * 대백제 목록 조회 화면 메서드입니다.
    *
    * @param   @RequestParam Model, DebecFestivalValueObject
    * @return  debecFestivalValueObject, debecFestivalList
    * @exception  Exception
    */
	@RequestMapping(value = "/debecFestivalList.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public String debecFestivalList(Model model, DebecFestivalValueObject debecFestivalValueObject) throws Exception {
		Logger.info(null);
		List<DebecFestivalValueObject> debecFestivalList = DebecFestivalService.debecFestivalList(debecFestivalValueObject);
		
		model.addAttribute("debecFestivalList", debecFestivalList);
		model.addAttribute("debecFestivalValueObject", debecFestivalValueObject);

		return "prodct/debecJae/DebecFestivalList";
	}
	
	/**
    * 대백제 목록 일괄 삭제 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/debecFestivalDelete.do", method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String debecFestivalDelete(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
	
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
		}
		DebecFestivalService.debecFestivalDelete(data);

		return "forward:/debecFestivalList.do";
	}
	
	/**
    * 대백제 목록 단일 삭제 메서드입니다.
    *
    * @param   @RequestParam DebecFestivalValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/debecFestivalSigleDelete.do", method ={RequestMethod.GET, RequestMethod.POST})
	public String debecFestivalSigleDelete(DebecFestivalValueObject debecFestivalValueObject) throws Exception {
		Logger.info(null);
		int schedlSeq = debecFestivalValueObject.getSchedlSeq();
		DebecFestivalService.debecFestivalSigleDelete(schedlSeq);
		
		return "forward:/debecFestivalList.do";
	}
	
	/**
    * 대백제 상세보기 화면 메서드입니다.
    *
    * @param   @RequestParam Model, DebecFestivalValueObject
    * @return  DebecFestivalValueObject
    * @exception  Exception
    */
	@RequestMapping(value = "/debecFestivalRead.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public String debecFestivalRead(Model model, DebecFestivalValueObject debecFestivalValueObject) throws Exception {
		Logger.info(null);

		int schedlSeq = debecFestivalValueObject.getSchedlSeq();
		debecFestivalValueObject = DebecFestivalService.debecFestivalRead(schedlSeq);
		List<ProdctFestivalValueObject> debecFestivalProdctList = DebecFestivalService.debecFestivalProdctList(schedlSeq);
		
		model.addAttribute("debecFestivalValueObject", debecFestivalValueObject);
		model.addAttribute("debecFestivalProdctList", debecFestivalProdctList);
		
		return "prodct/debecJae/DebecFestivalRead";
	}
	
	/**
    * 대백제 등록 STEP1 화면 전환 메서드입니다.
    *
    * @param   @RequestParam Model, DebecFestivalValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/debecFestivalCreateRead.do", method ={RequestMethod.GET, RequestMethod.POST})
	public String debecFestivalCreateRead(Model model) throws Exception {
		Logger.info(null);
		return "prodct/debecJae/DebecFestivalCreateOne";
	}
	
	/**
    * 대백제 등록 STEP1 등록 기능 메서드입니다.
    *
    * @param   @RequestParam MultfipartHttpServletRequest, DebecFestivalValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/debecFestivalCreate.do", method ={RequestMethod.GET, RequestMethod.POST})
	public String debecFestivalCreate(MultipartHttpServletRequest request, DebecFestivalValueObject debecFestivalValueObject) throws Exception {
		Logger.info(null);
		DebecFestivalService.debecFestivalCreate(request, debecFestivalValueObject);
		
		return "redirect:/debecFestivalCreateProdctRead.do";
	}
	
	/**
    * 대백제 등록 STEP2 화면 전환 / 상품 추가 후 수정 화면 전환 메서드입니다.
    *
    * @param   @RequestParam Model, DebecFestivalValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/debecFestivalCreateProdctRead.do", method ={RequestMethod.GET, RequestMethod.POST})
	public String debecFestivalCreateProdctRead(Model model, DebecFestivalValueObject debecFestivalValueObject) throws Exception {
		Logger.info(null);
		String jsp="prodct/debecJae/DebecFestivalCreateTwo";
		if(debecFestivalValueObject.getDivision().equalsIgnoreCase("u")){
			jsp= "prodct/debecJae/DebecFestivalUpdateRead";
		}
		List<ProdctFestivalValueObject> debecFestivalProdctList = DebecFestivalService.prodctTempList();	
		model.addAttribute("debecFestivalProdctList", debecFestivalProdctList);
		
		return jsp;
	}
	
	/**
    * 대백제 등록 STEP2 등록 기능 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/debecFestivalCreateProdct.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String prodctCreate(ProdctFestivalValueObject prodctFestivalValueObject) throws Exception {
		Logger.info(null);
		System.out.println(prodctFestivalValueObject.getProdctSeq_arry());
		DebecFestivalService.prodctCreate(prodctFestivalValueObject); //대백제 해당 상품리스트 등록 -> 상품리스트 할인율 수정
		DebecFestivalService.prodctTempAllDelete(); //임시테이블 삭제
		
		Logger.info("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
		return "redirect:/debecFestivalList.do";
		
	}
	
	/**
    * 대백제 등록 STEP2 상품리스트 일괄삭제 기능 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/prodctDelete.do", method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String prodctDelete(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
	
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
		}
		DebecFestivalService.prodctDelete(data);

		return "redirect:/debecFestivalCreateProdctRead.do";
	}
		
	/**
    * 대백제 등록 STEP2 등록취소 기능 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/prodctCreateCancel.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String prodctCreateCancel() throws Exception {
		Logger.info(null);
		DebecFestivalService.prodctTempAllDelete();

		return "redirect:/debecFestivalList.do";
		
	}
		
	/**
    * 대백제 수정 화면 전환 메서드입니다.
    *
    * @param   @RequestParam Model, DebecFestivalValueObject
    * 
    * @return  DebecFestivalValueObject
    * @exception  Exception
    */
	@RequestMapping(value = "/debecFestivalUpdateRead.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public String debecFestivalUpdateRead(Model model, DebecFestivalValueObject debecFestivalValueObject) throws Exception {
		Logger.info(null);

		int schedlSeq = debecFestivalValueObject.getSchedlSeq();
		debecFestivalValueObject = DebecFestivalService.debecFestivalRead(schedlSeq); //상단에 행사 조회
		List<ProdctFestivalValueObject> debecFestivalProdctList = DebecFestivalService.debecFestivalProdctList(schedlSeq); //하단에 상품리스트 조회
		
		model.addAttribute("debecFestivalValueObject", debecFestivalValueObject);
		model.addAttribute("debecFestivalProdctList", debecFestivalProdctList);
		
		return "prodct/debecJae/DebecFestivalUpdateRead";
	}
	
	/**
    * 대백제 수정 기능 메서드입니다.
    *
    * @param   @RequestParam MultipartHttpServletRequest, DebecFestivalValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/debecFestivalUpdate.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public String debecFestivalUpdate(ProdctFestivalValueObject prodctFestivalValueObject, MultipartHttpServletRequest request) throws Exception {
		Logger.info(null);
		DebecFestivalService.debecFestivalUpdate(prodctFestivalValueObject, request);
		
		return "forward:/debecFestivalRead.do";
	}
	
	/**
    * 대백제 수정 상품수정 기능 메서드입니다.
    *
    * @param   @RequestParam MultipartHttpServletRequest, DebecFestivalValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/debecFestivaProdctlUpdate.do", method = {RequestMethod.GET, RequestMethod.POST} )
	@ResponseBody
	public void debecFestivaProdctlUpdate(DebecFestivalValueObject debecFestivalValueObject) throws Exception {
		Logger.info(null);
		int schedlSeq = debecFestivalValueObject.getSchedlSeq();
		DebecFestivalService.debecFestivaProdctlUpdate(schedlSeq);

	}
		
			
	/**
    * 대백제 수정 하단 상품리스트 일괄삭제 기능 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/debecFestivalUpdateDelete.do", method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String debecFestivalUpdateDelete(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
		}
		DebecFestivalService.debecFestivalUpdateDelete(data);

		return "forward:/debecFestivalRead.do";
	}
	
	/**
    * 대백제 상품추가 팝업 띄우는 메서드입니다.
    *
    * @param   @RequestParam Model
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/prodctCreatePopupRead.do", method = {RequestMethod.GET, RequestMethod.POST} )
	public String prodctCreatePopupRead(Model model, @RequestParam(value="schedlSeq", defaultValue = "0") int schedlSeq, @RequestParam(value="division", defaultValue = "C") String division) throws Exception {
		Logger.info(null);																																			
		model.addAttribute("schedlSeq", schedlSeq);
		model.addAttribute("divison", division);
		return "prodct/debecJae/ProdctCreatePopup";
	}	
	
	/**
    * 대백제 상품추가 테이블1 생성 메서드입니다.
    *
    * @param   @RequestParam Model, HttpServletRequest, ProdctFestivalValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/prodctAddList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String prodctAddList(HttpServletRequest request, Model model, ProdctFestivalValueObject prodctFestivalValueObject) throws Exception{
		Logger.info(null);
		String prodctSechText = request.getParameter("prodctSechText") == null?"":request.getParameter("prodctSechText");
		prodctFestivalValueObject.setProdctSechText(prodctSechText);
		List<ProdctFestivalValueObject> prodctAdList = DebecFestivalService.prodctAddList(prodctFestivalValueObject);
		model.addAttribute("prodctFestivalValueObject", prodctFestivalValueObject);	// 검색 조건, 페이지 정보들			
		model.addAttribute("prodctAdList", prodctAdList);
		
		return "prodct/debecJae/ProdctTable";
	}
	
	/**
    * 대백제 상품추가 테이블2 생성 메서드입니다.
    *
    * @param   @RequestParam Model, HttpServletRequest, ProdctFestivalValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/prodctAdTempList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String prodctAddTempList(HttpServletRequest request, Model model, ProdctFestivalValueObject prodctFestivalValueObject) throws Exception{
		Logger.info(null);
		String tempSechText = request.getParameter("tempSechText") == null?"":request.getParameter("tempSechText");
		prodctFestivalValueObject.setTempSechText(tempSechText);
		List<ProdctFestivalValueObject> prodctAdTempList = DebecFestivalService.prodctAddTempList(prodctFestivalValueObject);
		model.addAttribute("prodctFestivalValueObject", prodctFestivalValueObject);	// 검색 조건, 페이지 정보들			
		model.addAttribute("prodctAdTempList", prodctAdTempList);
		
		return "prodct/debecJae/TempTable";
	}
	
	/**
    * 상품 선택 팝업창 추가 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/prodctAdTempCreate.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void prodctAdTempCreate(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		DebecFestivalService.prodctAdTempCreate(data);
	}
	
	/**
    * 상품 선택 팝업창 제거 메서드입니다.
    *
    * @param   @RequestParam String
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/prodctAddTempDelete.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void prodctAddTempDelete(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		DebecFestivalService.prodctAddTempDelete(data);
	}
	
	/**
    * 대백제 등록화면 엑셀업로드 팝업창 전환 메서드입니다.
    *
    * @param   @RequestParam Model
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/createXlsxPopup.do", method ={RequestMethod.GET, RequestMethod.POST})
	public String createXlsxPopup(Model model, @RequestParam(value="schedlSeq", defaultValue = "0") int schedlSeq, @RequestParam(value="division", defaultValue = "C") String division) throws Exception {
		Logger.info(null);
		model.addAttribute("schedlSeq", schedlSeq);
		model.addAttribute("divison", division);
		return "prodct/debecJae/CreateXlsxPopup";
	}
	
	/**
	* 대백제 등록화면 상품 정보로된 엑셀의 데이터를 읽어 일괄적으로 등록하는 메서드 입니다.
	*
	* @param   MultipartHttpServletRequest, ProdctFestivalValueObject
	* @return  ResponseBody Map<String, Object>
	* @exception  Exception
	*/	
	@RequestMapping(value = "/xlsxUploadCreate.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String xlsxUploadCreate(MultipartHttpServletRequest request, ProdctFestivalValueObject prodctFestivalValueObject) throws Exception {
		Logger.info(null);
		DebecFestivalService.createXlsxUpload(request, prodctFestivalValueObject);
		return "prodct/debecJae/CreateXlsxPopup";
	}
	
	/**
	* 대백제 수정화면 상품 정보로된 엑셀의 데이터를 읽어 일괄적으로 등록하는 메서드 입니다.
	*
	* @param   MultipartHttpServletRequest, ProdctFestivalValueObject
	* @return  ResponseBody Map<String, Object>
	* @exception  Exception
	*/	
	@RequestMapping(value = "/xlsxUploadUpdate.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String xlsxUploadUpdate(MultipartHttpServletRequest request, ProdctFestivalValueObject prodctFestivalValueObject) throws Exception {
		Logger.info(null);
		int schedlSeq = prodctFestivalValueObject.getSchedlSeq();
		DebecFestivalService.createXlsxUpload(request, prodctFestivalValueObject);
		DebecFestivalService.debecFestivaProdctlUpdate(schedlSeq);
		return "prodct/debecJae/CreateXlsxPopup";
	}
}
