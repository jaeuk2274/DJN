package net.su.prodct.display.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.su.logger.Logger;
import net.su.prodct.display.service.HotdlService;
import net.su.prodct.display.service.MainDisplayService;
import net.su.prodct.display.valueObject.HotdlValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 핫딜 상품 메뉴 관련 컨트롤러입니다.
 * 
 * @see   net.su.prodct.display.controller.HotdlController
 * @version  1.0 
 * @ author 김동욱, 2016/08/12
 */

@Controller
public class HotdlController {
	@Resource
	private HotdlService hotdlService;
	
	@Resource
	private MainDisplayService mainDisplayService;
	
	//핫딜 상품 리스트
	@RequestMapping(value = "hotdlList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String selectHotdlList(Model model, HotdlValueObject hotdlValueObject) throws Exception {
		Logger.info(null);
		List<HotdlValueObject> hotdlList = hotdlService.selectHotdlList(hotdlValueObject);
		int hotdlCount = hotdlService.selectHotdlCount(hotdlValueObject);
		model.addAttribute("HotdlValueObject", hotdlValueObject);	// 검색 조건, 페이지 정보들			
		model.addAttribute("HotdlList", hotdlList); // 상품 리스트
		model.addAttribute("HotdlCount", hotdlCount); // 메인 상품 개수
		
		return "prodct/display/hotdl/HotdlList";
	}
		
	//핫딜 상품 삭제
	@RequestMapping(value = "/hotdlDelete.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void deleteHotdlProdct(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		System.out.println(chkedVal+"@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		hotdlService.deleteHotdlProdct(data);
	}
	
	//핫딜 상품 수정화면 조회
	@RequestMapping(value = "/hotdlUpdateRead.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String selectHotdlUpdateRead(Long prodctSeq, Model model) throws Exception {
		Logger.info(null);
		HotdlValueObject hotdlValueObject = hotdlService.selectHotdlRead(prodctSeq);
		model.addAttribute("HotdlVo", hotdlValueObject);
		return "prodct/display/hotdl/HotdlUpdate";
	}
	
	//핫딜 상품 수정
	@RequestMapping(value = "/hotdlUpdate.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void updateHotdl(HttpServletRequest request, Model model, HotdlValueObject hotdlValueObject) throws Exception {
		Logger.info(null);
		Long prodctSeq = Long.parseLong(request.getParameter("prodctSeq") == null?"":request.getParameter("prodctSeq"));
		int discntPric = Integer.parseInt(request.getParameter("discntPric") == null?"":request.getParameter("discntPric"));
		int amont = Integer.parseInt(request.getParameter("amont") == null?"":request.getParameter("amont"));
		hotdlValueObject.setProdctSeq(prodctSeq);
		hotdlValueObject.setDiscntPric(discntPric);
		hotdlValueObject.setAmont(amont);
		
		hotdlService.updateHotdl(hotdlValueObject);
	}
	
	//핫딜 상품 추가
	@RequestMapping(value = "/hotdlCreate.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void insertHotdl(HttpServletRequest request, HotdlValueObject hotdlValueObject) throws Exception {
		Logger.info(null);
		Long prodctSeq = Long.parseLong(request.getParameter("prodctSeq") == null?"":request.getParameter("prodctSeq"));
		int discntPric = Integer.parseInt(request.getParameter("discntPric") == null?"":request.getParameter("discntPric"));
		int amont = Integer.parseInt(request.getParameter("amont") == null?"":request.getParameter("amont"));
		
		hotdlValueObject.setProdctSeq(prodctSeq);
		hotdlValueObject.setDiscntPric(discntPric);
		hotdlValueObject.setAmont(amont);
		
		hotdlService.insertHotdl(hotdlValueObject);
	}
	
	//상품 추가 화면
	@RequestMapping(value = "/hotdlCreateRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String freshCreateRead() throws Exception{
		Logger.info(null);
		
		return "prodct/display/hotdl/HotdlCreate";
	}
	
	//상품 추가 테이블 생성
	@RequestMapping(value = "/hotdlAdList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String selectHotdlAdList(HttpServletRequest request, Model model, HotdlValueObject hotdlValueObject) throws Exception{
		Logger.info(null);
		String catgrSeq = request.getParameter("catgrSeq") == null?"0":request.getParameter("catgrSeq");
		String prodctSechText = request.getParameter("prodctSechText") == null?"":request.getParameter("prodctSechText");
		hotdlValueObject.setProdctSechText(prodctSechText);
		hotdlValueObject.setCatgrSeq(Integer.parseInt(catgrSeq));
		List<HotdlValueObject> prodctAdList = hotdlService.selectHotdlAdList(hotdlValueObject);
		model.addAttribute("HotdlValueObject", hotdlValueObject);	// 검색 조건, 페이지 정보들			
		model.addAttribute("ProdctAdList", prodctAdList);
		
		return "prodct/display/hotdl/HotdlProdctTable";
	}
}
