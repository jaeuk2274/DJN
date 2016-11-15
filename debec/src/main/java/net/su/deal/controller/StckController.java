/**
 * 재고 관리 컨트롤러입니다.
 * 
 * @see   net.su.deal.controller.StckController
 * @version  1.0 
 * @ author 하원식, 2016/08/27
 */

package net.su.deal.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.su.deal.service.InstckService;
import net.su.deal.service.StckService;
import net.su.deal.valueObject.InstckValueObject;
import net.su.deal.valueObject.StckValueObject;
import net.su.logger.Logger;
import net.su.prodct.service.ProdctService;
import net.su.prodct.valueObject.ProdctValueObject;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Controller
public class StckController {
	@Resource
	private StckService stckService;
	@Resource
	private ProdctService prodctService;
	
	
/**
* 상품의 재고 내역을 조회하는 메서드입니다.
*
* @param   ModelMap model, StckValueObject stckValueObject, ProdctValueObject prodctValueObject
* @return  String
* @exception  Exception
*/
//  재고 목록 띄우는 메서드
	@RequestMapping(value="/stckList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String stckList(ModelMap model, StckValueObject stckValueObject, ProdctValueObject prodctValueObject) throws Exception{
		Logger.info(null);
		
		List<StckValueObject> stckList = stckService.stckList(stckValueObject);
		String[] topCatgrArray= prodctService.catgrList("기본","");
		String[] midCatgrArray=prodctService.catgrList("대",prodctValueObject.getTopCatgrNme());
		String[] botCatgrArray=prodctService.catgrList("중",prodctValueObject.getMidCatgrNme());
				
		model.addAttribute("stckList",stckList);
		model.addAttribute("ProdctValueObject",prodctValueObject);
		model.addAttribute("stckValueObject",stckValueObject);
		model.addAttribute("topCatgrArray",topCatgrArray);
		model.addAttribute("midCatgrArray",midCatgrArray);
		model.addAttribute("botCatgrArray",botCatgrArray);
		
		return "deal/stck/StckList";
	}
	
	
/**
* 상품의 재고량을 수정하는 메서드입니다.
*
* @param   @RequestParam(value="prodctSeq[]") long[] prodctSeq, @RequestParam(value="qunt[]") int[] qunt
* @return  @ResponseBody Map<String, Object>
* @exception  Exception
*/
	@RequestMapping(value = "/stckUpdate.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public @ResponseBody Map<String, Object> stckUpdate(@RequestParam(value="prodctSeq[]") long[] prodctSeq, @RequestParam(value="qunt[]") int[] qunt) throws Exception {
		Logger.info(null);
		
		Map<String, Object> dummy = new HashMap<String, Object>();
		
		
		stckService.stckUpdate(prodctSeq, qunt);
		return dummy;
	}
	
	
/**
* 상품 반품을 위한 팝업을 띄우기 전에 선택한 상품의 바코드를 세션에 저장 시켜주는 메서드 입니다.
*
* @param   HttpSession stckSession, @RequestParam(value="prodctSeq[]") long[] prodctSeq
* @return  @ResponseBody Map<String, Object>
* @exception  Exception
*/
	@RequestMapping(value = "stckRetrnSeqSaver.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public @ResponseBody Map<String, Object> stckRetrnSeqSaver(HttpSession stckSession, @RequestParam(value="prodctSeq[]") long[] prodctSeq) throws Exception {
		Logger.info(null);
		
		Map<String, Object> dummy = new HashMap<String, Object>();
		stckSession.setAttribute("prodctSeq",prodctSeq);
		return dummy;
	}
	
/**
* 상품 반품을 위한 팝업을 불러오는 메서드 입니다.
*
* @param   HttpSession stckSession, Model model
* @return  String
* @exception  Exception
*/	
	@RequestMapping(value = "/stckRetrnPopup.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String stckRetrnPopup(HttpSession stckSession, Model model) throws Exception {
		Logger.info(null);

		long[] prodctSeq = (long[]) stckSession.getAttribute("prodctSeq");
		List<ProdctValueObject> selectedSelProdctList = prodctService.selectedSelProdctList(prodctSeq);

		model.addAttribute("selProdctList", selectedSelProdctList);
		return "deal/stck/StckRetrnPopup";
	}
	
/**
* 상품 반품 팝업에 입력된 정보로 수정을 하는 메서드 입니다.
*
* @param   @RequestParam(value="prodctSeq[]") long[] prodctSeq,@RequestParam(value="qunt[]") int[] qunt
* @return  ResponseBody Map<String, Object>
* @exception  Exception
*/
	@RequestMapping(value = "/stckRetrnUpdate.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public @ResponseBody Map<String, Object> stckRetrn(@RequestParam(value="prodctSeq[]") long[] prodctSeq,@RequestParam(value="qunt[]") int[] qunt) throws Exception {
		Logger.info(null);
		stckService.stckRetrnUpdate(prodctSeq, qunt);
		Map<String, Object> dummy = new HashMap<String, Object>();
		return dummy;
	}
	
	
/**
* 특정 상품의 상세 정보 및 입고 내역을 상세보기 하는 메서드 입니다.
*
* @param   Model model, ProdctValueObject prodctValueObject,StckValueObject stckValueObject
* @return  ResponseBody Map<String, Object>
* @exception  Exception
*/
	@RequestMapping(value = "/stckRead.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String stckCreateRead(Model model, ProdctValueObject prodctValueObject,StckValueObject stckValueObject) throws Exception {
		Logger.info(null);
		
		//상품 정보 상세보기를 위한 VO

		prodctValueObject.setPageType(3);  // prodctMapper 안에 있는  쿼리를 활용하는 과정에서 조건절을 적절하게 출력되게 하기 위해 pageType을 추가함
		
		List<ProdctValueObject> selProdctRead = prodctService.selProdctList(prodctValueObject); // 상품 정보 상세보기
		ProdctValueObject catgrProdctRead = prodctService.catgrProdctRead(prodctValueObject.getProdctSeq());  // 상품 카테고리 조회
		
		int instckRecrdCount = stckService.instckRecrdCount(stckValueObject); // 입고 내역 리스트

		stckValueObject.setTotalRecordCount(instckRecrdCount); // 고객 리스트 전체 수 셋팅
		
		List<StckValueObject> instckRecrdList = stckService.instckRecrdList(stckValueObject); // 입고 내역 리스트
		
		model.addAttribute("selProdctRead",selProdctRead.get(0));
		model.addAttribute("catgrProdctRead",catgrProdctRead);
		model.addAttribute("instckRecrdCount",instckRecrdCount);
		model.addAttribute("instckRecrdList",instckRecrdList);
		model.addAttribute("prodctValueObject",prodctValueObject);
		model.addAttribute("stckValueObject",stckValueObject);
		return "deal/stck/StckRead";
	}
	
	
/**
* 상품의 재고 정보를 엑셀의 데이터를 읽어 일괄적으로 등록시켜주는 메서드 입니다.
*
* @param   MultipartHttpServletRequest request, StckValueObject stckValueObject
* @return  ResponseBody Map<String, Object>
* @exception  Exception
*/	
	@RequestMapping(value = "/stckCreate.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String stckCreate(MultipartHttpServletRequest request, StckValueObject stckValueObject, Model model) throws Exception {
		Logger.info(null);
		stckService.stckCreate(request, stckValueObject);
		model.addAttribute("stckValueObject",stckValueObject);
		return "deal/stck/StckCreatePopUp";
	}
	
	
/**
* 선택된 상품의 정보를 엑셀 파일(.xlx)로 다운로드 하는 메서드입니다.
*
* @param   String model, StckValueObject stckValueObject
* @return  ResponseBody Map<String, Object>
* @exception  Exception
*/	
	 @RequestMapping(value = "/stckXlxDown.do", method = {RequestMethod.GET,RequestMethod.POST}) 
	 public String stckXlxDown(Model model, StckValueObject stckValueObject) throws Exception 
	 {
		 Logger.info(null);
		List<StckValueObject> stckList = stckService.stckXlxList(stckValueObject.getProdctCheck());  
		model.addAttribute("stckList", stckList);
		
		return "deal/stck/StckListXlsx";
	 }
 /**
 * 상품 등록을 위한 화면으로 이동시켜주는 메서드 입니다.
 *
 * @param   Model model, StckValueObject stckValueObject
 * @return  String
 * @exception  Exception
 */	 
	 @RequestMapping(value = "/stckCreateRead.do", method = {RequestMethod.GET,RequestMethod.POST}) 
	 public String stckCreateRead(Model model, StckValueObject stckValueObject) throws Exception 
	 {
		Logger.info(null);
		
		return "deal/stck/StckCreatePopUp";
	 }
}

