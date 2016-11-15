package net.su.prodct.display.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.su.logger.Logger;
import net.su.prodct.display.service.MainDisplayService;
import net.su.prodct.display.valueObject.MainDisplayValueObject;
import net.su.prodct.valueObject.CatgrValueObject;



import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 메인진열 메뉴 관련 컨트롤러입니다.
 * 
 * @see   net.su.prodct.display.controller.MainDisplayController
 * @version  1.0 
 * @ author 김동욱, 2016/08/09
 */

@Controller
public class MainDisplayController {

	@Resource
	private MainDisplayService mainDisplayService;
	
	//메인 진열 상품 리스트
	@RequestMapping(value = "/mainDisplayList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String mainDisplayList(Model model, MainDisplayValueObject mainDisplayValueObject) throws Exception {
		Logger.info(null);
		mainDisplayService.deleteProdctTempList();
		mainDisplayService.insertProdctTempList();
		List<MainDisplayValueObject> mainDisplayList = mainDisplayService.selectMainDisplayList(mainDisplayValueObject);
		int mainDisplayCount = mainDisplayService.selectMainDisplayCount(mainDisplayValueObject);
		model.addAttribute("MainDisplayValueObject", mainDisplayValueObject);	// 검색 조건, 페이지 정보들			
		model.addAttribute("MainDisplayList", mainDisplayList); // 고객 리스트
		model.addAttribute("MainDisplayCount", mainDisplayCount); // 메인 상품 개수
		
		return "prodct/display/mainDisplay/MainDisplayList";
	}
	
	//메인 진열 상품 삭제
	@RequestMapping(value = "/deleteMainDisplayProdct.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void deleteMainDisplayProdct(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		System.out.println(chkedVal+"@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		mainDisplayService.deleteMainDisplayProdct(data);
	}
	
	//진열 순서 변경
	@RequestMapping(value = "/updateMainDisplayOrder.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void updateMainDisplayOrder(@RequestBody Map<String, Object> map, Model model, MainDisplayValueObject mainDisplayValueObject) throws Exception {
		Logger.info(null);
		System.out.println(map.get("tableId")+"@@@@@@@");
		String[] data = map.get("tableId").toString().split(",");
		for(int i=0; i<data.length; i++){
			System.out.println(data[i]);
			data[i] = data[i].replace(" ", "");
			if(data[i].contains("["))
			data[i] = data[i].replace("[","");
			else if(data[i].contains("]"))
			data[i] = data[i].replace("]","");
		}
		mainDisplayService.updateMainDisplayOrder(data);
		
	}
	
	//카테고리 전체 조회
	@RequestMapping(value = "/selectCatgrList.do")
	@ResponseBody
	public Object selectCatgrList() throws Exception{
		Logger.info(null);
		List<CatgrValueObject> catgrList = mainDisplayService.selectCatgrList();	
		ObjectMapper mapper = new ObjectMapper();		
		return mapper.writeValueAsString(catgrList);
	}
	
	//상품 추가 테이블 생성
	@RequestMapping(value = "/selectProdctAdList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String selectProdctAdList(HttpServletRequest request, Model model, MainDisplayValueObject mainDisplayValueObject2) throws Exception{
		Logger.info(null);
		String catgrSeq = request.getParameter("catgrSeq") == null?"0":request.getParameter("catgrSeq");
		String prodctSechText = request.getParameter("prodctSechText") == null?"":request.getParameter("prodctSechText");
		mainDisplayValueObject2.setProdctSechText(prodctSechText);
		mainDisplayValueObject2.setCatgrSeq(Integer.parseInt(catgrSeq));
		List<MainDisplayValueObject> prodctAdList = mainDisplayService.selectProdctAdList(mainDisplayValueObject2);
		model.addAttribute("MainDisplayValueObject2", mainDisplayValueObject2);	// 검색 조건, 페이지 정보들			
		model.addAttribute("ProdctAdList", prodctAdList);
		
		return "prodct/display/mainDisplay/MainDisplayProdctTable";
	}
	
	//상품 추가 테이블 생성
	@RequestMapping(value = "/selectProdctAdList2.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String selectProdctAdList2(HttpServletRequest request, Model model, MainDisplayValueObject mainDisplayValueObject2) throws Exception{
		Logger.info(null);
		String catgrSeq = request.getParameter("catgrSeq") == null?"0":request.getParameter("catgrSeq");
		String prodctSechText = request.getParameter("prodctSechText") == null?"":request.getParameter("prodctSechText");
		mainDisplayValueObject2.setProdctSechText(prodctSechText);
		mainDisplayValueObject2.setCatgrSeq(Integer.parseInt(catgrSeq));
		List<MainDisplayValueObject> prodctAdList = mainDisplayService.selectProdctAdList(mainDisplayValueObject2);
		model.addAttribute("MainDisplayValueObject2", mainDisplayValueObject2);	// 검색 조건, 페이지 정보들			
		model.addAttribute("ProdctAdList", prodctAdList);
		
		return "custmr/push/ProdctTable";
	}
	
	//임시 테이블  생성
	@RequestMapping(value = "/selectProdctAdTempList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String selectProdctAdTempList(HttpServletRequest request, Model model, MainDisplayValueObject mainDisplayValueObject3) throws Exception{
		Logger.info(null);
		String tempSechText = request.getParameter("tempSechText") == null?"":request.getParameter("tempSechText");
		mainDisplayValueObject3.setTempSechText(tempSechText);
		List<MainDisplayValueObject> prodctAdTempList = mainDisplayService.selectProdctAdTempList(mainDisplayValueObject3);
		model.addAttribute("MainDisplayValueObject3", mainDisplayValueObject3);	// 검색 조건, 페이지 정보들			
		model.addAttribute("ProdctAdTempList", prodctAdTempList);
		
		return "prodct/display/mainDisplay/MainDisplayTempTable";
	}
	
	//임시 테이블 상품추가
	@RequestMapping(value = "/insertProdctAdTempList.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void insertProdctAdTempList(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		System.out.println(chkedVal+"@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		mainDisplayService.insertProdctAdTempList(data);
	}
	
	//임시 테이블 상품삭제
	@RequestMapping(value = "/deleteProdctAdTempList.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void deleteProdctAdTempList(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		System.out.println(chkedVal+"@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		mainDisplayService.deleteProdctAdTempList(data);
	}
	
	//임시테이블에서 상품 테이블 최종추가
	@RequestMapping(value = "/insertProdct.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void insertProdct(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		System.out.println(chkedVal+"@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		mainDisplayService.deleteProdctTempList();
		mainDisplayService.insertProdct(data);
	}
	
	//상품 추가 화면
	@RequestMapping(value = "/mainDisplayCreateRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String mainDisplayCreateRead() throws Exception{
		Logger.info(null);
		
		return "prodct/display/mainDisplay/MainDisplayCreate";
	}
}
