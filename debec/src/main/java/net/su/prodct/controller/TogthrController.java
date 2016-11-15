package net.su.prodct.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.su.logger.Logger;
import net.su.prodct.display.controller.HotdlController;
import net.su.prodct.display.service.MainDisplayService;
import net.su.prodct.display.valueObject.MainDisplayValueObject;
import net.su.prodct.service.ProdctService;
import net.su.prodct.service.TogthrService;
import net.su.prodct.valueObject.CatgrValueObject;
import net.su.prodct.valueObject.ProdctValueObject;
import net.su.prodct.valueObject.TogthrValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 핫딜 상품 메뉴 관련 컨트롤러입니다.
 * 
 * @see   net.su.prodct.display.controller.HotdlController
 * @version  1.0 
 * @ author 이정호, 2016/09/02
 */

@Controller
public class TogthrController {
	
	@Resource
	private MainDisplayService mainDisplayService;
	@Resource
	private TogthrService togthrService;
	@Resource
	private  ProdctService prodctService;

	/**
	* 다함께 상품 진열 관리 목록 조회를 하는 메서드입니다.
	*
	* @param   ModelMap model, TogthrValueObject togthrValueObject
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/togthrProdctList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String togthrProdctList(Model model, TogthrValueObject togthrValueObject) throws Exception {
		Logger.info("다함께 상품 진열 관리 목록 조회 컨트롤러 입니다.");
		List<TogthrValueObject> togthrProdctList = togthrService.togthrProdctList(togthrValueObject);
		model.addAttribute("togthrProdctList", togthrProdctList);
		model.addAttribute("togthrValueObject", togthrValueObject);
		
		return "prodct/together/TogthrProdctList";
	}
	/**
	* 다함께 상품 진열 관리 목록 상품 삭제를 하는 메서드입니다.
	*
	* @param   ModelMap model, String chkedVal
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/deleteToghrProdct.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void deleteToghrProdct(@RequestBody String chkedVal) throws Exception {
		Logger.info("다함께 상품 진열 관리 목록 상품 삭제 컨트롤러 입니다.");
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		togthrService.deleteToghrProdct(data);
	}
	/**
	* 다함께 상품 진열 관리 상품 상세조회 팝업을 하는 메서드입니다.
	*
	* @param   ModelMap model, TogthrValueObject togthrValueObject
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/togthrDetail.do", method={RequestMethod.POST, RequestMethod.GET})
	public String togthrDetail(Model model, TogthrValueObject togthrValueObject)throws Exception{
		Logger.info("다함께 상품 진열 관리 상세조회 팝업 컨트롤러 입니다.");
		TogthrValueObject togthrDetail = togthrService.togthrDetail(togthrValueObject);
		
		model.addAttribute("togthrDetail", togthrDetail);
		
		return "prodct/together/TogthrReadPopup";
	}
	/**
	* 다함께 상품 진열 관리 상품 카테고리 리스트 팝업을 하는 메서드입니다.
	*
	* @param
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/togthrPurchsInsertPopup.do", method={RequestMethod.POST, RequestMethod.GET})
	public String togthrPurchsInsertPopup()throws Exception{
		Logger.info("다함께 상품 진열 관리 카테고리 리스트 팝업 컨트롤러 입니다.");
		
		return "prodct/together/TogthrProdctListPopup";
	}
	/**
	* 다함께 상품 진열 관리 상품 등록화면 이동 팝업을 하는 메서드입니다.
	*
	* @param
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/togthrInsert.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String togthrInsert() throws Exception {
		Logger.info("다함께 상품 진열 관리 등록화면 팝업 컨트롤러 입니다.");
		
		return "prodct/together/TogthrProdctCreatePopup";
	}
	/**
	* 다함께 상품 진열 관리 상품 팝업에서 다함께 상품을 등록 하는 메서드입니다.
	*
	* @param   HttpServletRequest request, Model model, MainDisplayValueObject mainDisplayValueObject2
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/purchsProdctInsert.do", method={RequestMethod.GET, RequestMethod.POST})
	public String purchsProdctInsert(HttpServletRequest request, Model model, TogthrValueObject togthrValueObject)throws Exception{
		Logger.info("다함께 상품 진열 관리 상품 등록 컨트롤러 입니다.");
		
		togthrService.purchsProdctInsert(togthrValueObject);
		
		return "redirect:/togthrProdctList.do";
	}
	/**
	* 다함께 상품 진열 관리 상품 팝업에서 수정화면 이동 하는 메서드입니다.
	*
	* @param   Model model, MainDisplayValueObject mainDisplayValueObject2
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/togthrUpdateFrm.do", method={RequestMethod.GET, RequestMethod.POST})
	public String togthrUpdateFrm(Model model, TogthrValueObject togthrValueObject)throws Exception{
		Logger.info("다함께 상품 진열 관리 상품 수정화면 컨트롤러 입니다.");
		TogthrValueObject togthrUpdage = togthrService.togthrDetail(togthrValueObject);
		
		model.addAttribute("togthrUpdage", togthrUpdage);
		
		return "prodct/together/TogthrUpdatePopup";
	}
	/**
	* 다함께 상품 진열 관리 상품 팝업에서 수정하는 메서드입니다.
	*
	* @param   Model model, MainDisplayValueObject mainDisplayValueObject2
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/togthrUpdate.do", method={RequestMethod.GET, RequestMethod.POST})
	public String togthrUpdate(HttpServletRequest request, Model model, TogthrValueObject togthrValueObject)throws Exception{
		Logger.info("다함께 상품 진열 관리 상품 수정 컨트롤러 입니다.");
		togthrService.togthrUpdate(togthrValueObject);
		
		TogthrValueObject togthrDetail = togthrService.togthrDetail(togthrValueObject);
		
		model.addAttribute("togthrDetail", togthrDetail);
		
		return "prodct/together/TogthrReadPopup";
	}
	/**
	* 다함께 상품 진열 관리 상품 팝업에서 삭제하는 메서드입니다.
	*
	* @param   Model model, MainDisplayValueObject mainDisplayValueObject2
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/togthrUpdateFrmDelete.do", method={RequestMethod.GET, RequestMethod.POST})
	public String togthrUpdateFrmDelete(int gropPurchsProdctSeq, Model model)throws Exception{
		Logger.info("다함께 상품 진열 관리 상품 삭제 컨트롤러 입니다.");
		
		togthrService.togthrUpdateFrmDelete(gropPurchsProdctSeq);
		
		return "prodct/together/TogthrProdctList";
	}
	
	/**
    * 카테고리 변경에 따른 내역을 세션에 저장하는 메서드다.
    *
    * @param   HttpSession, @RequestParam(value="selectedCatgrSeq[]") String[] catgrSeq
    * @return  String
    * @exception  Exception
    */	
	@RequestMapping(value = "/selectTogthrProdctAdList.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String catgrChange(HttpSession togthrProdctSession, @RequestParam(value="selectedCatgrSeq[]") String[] catgrSeq) throws Exception {
		System.out.println("ProdctController의  catgrChange() 작동");
		String temp = catgrSeq[0];
		togthrProdctSession.setAttribute("catgrSeq",temp);
		return "redirect:/togthrProdctAdList.do";
	}
	

	/**
    * 다함께 상품 등록 팝업에서 게시물을 조회하는 메서드다.
    *
    * @param   Model, HttpSession, ProdctValueObject
    * @return  String
    * @exception  Exception
    */
	@RequestMapping(value = "/togthrProdctAdList.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String togthrProdctAdList(Model model, HttpSession togthrProdctSession,ProdctValueObject prodctValueObject) throws Exception {
		System.out.println("ProdctController의  reltnProdctAdList() 작동"+prodctValueObject.getProdctSechText());
		
		String catgrSeq ="0";
		if(togthrProdctSession.getAttribute("catgrSeq")!=null)
		{
			catgrSeq = (String) togthrProdctSession.getAttribute("catgrSeq");
		}
		prodctValueObject.setCatgrSeq(catgrSeq);
		List<ProdctValueObject> togthrProdctList = prodctService.selProdctList(prodctValueObject);
		model.addAttribute("prodctValueObject",prodctValueObject);
		model.addAttribute("togthrProdctList", togthrProdctList);

		return "prodct/together/TogthrProdctChocTable";
	}
	/**
    * 카테고리 리스트를 조회하는 메서드 입니다.
    *
    * @param   Model
    * @return  Object
    * @exception  Exception
    */	
	@RequestMapping(value = "/selectCatgrList.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody	
	public Object catgrProdctList(Model model) throws Exception {
		System.out.println("ProdctController의  catgrProdctList() 작동");
		List<CatgrValueObject> catgrProdctList = prodctService.catgrProdctList();
		ObjectMapper catgrProdctMapper = new ObjectMapper();
		return catgrProdctMapper.writeValueAsString(catgrProdctList);
	}
}