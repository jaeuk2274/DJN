package net.su.prodct.display.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.su.logger.Logger;
import net.su.prodct.display.service.FreshService;
import net.su.prodct.display.valueObject.FreshValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 신선 식품 메뉴 관련 컨트롤러입니다.
 * 
 * @see   net.su.prodct.display.controller.FreshController
 * @version  1.0 
 * @ author 김동욱, 2016/08/18
 */

@Controller
public class FreshController {
	@Resource
	private FreshService freshService;
	
	//신선 식품 리스트
	@RequestMapping(value = "/freshList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String freshList(Model model, FreshValueObject freshValueObject) throws Exception {
		Logger.info(null);
		List<FreshValueObject> freshList = freshService.selectFreshList(freshValueObject);
		int freshCount = freshService.selectFreshCount(freshValueObject);
		model.addAttribute("FreshValueObject", freshValueObject);	// 검색 조건, 페이지 정보들			
		model.addAttribute("FreshList", freshList); // 상품 리스트
		model.addAttribute("FreshCount", freshCount); // 메인 상품 개수
		
		return "prodct/display/fresh/FreshList";
	}
	
	//신선 식품 삭제
	@RequestMapping(value = "/freshDelete.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void deleteFreshProdct(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		System.out.println(chkedVal+"@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		freshService.deleteFresh(data);
	}
	
	//신선 식품 수정화면 조회
	@RequestMapping(value = "/freshUpdateRead.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String selectFreshUpdateRead(long prodctSeq, Model model) throws Exception {
		Logger.info(null);
		FreshValueObject freshValueObject = freshService.selectFreshRead(prodctSeq);
		model.addAttribute("FreshVo", freshValueObject);
		return "prodct/display/fresh/FreshUpdate";
	}
	
	//신선 식품 수정
	@RequestMapping(value = "/freshUpdate.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void updateFresh(HttpServletRequest request, Model model, FreshValueObject freshValueObject) throws Exception {
		Logger.info(null);
		Long prodctSeq = Long.parseLong(request.getParameter("prodctSeq") == null?"":request.getParameter("prodctSeq"));
		String stat = request.getParameter("stat") == null?"":request.getParameter("stat");
		
		freshValueObject.setProdctSeq(prodctSeq);
		freshValueObject.setStat(stat);
		
		freshService.updateFresh(freshValueObject);
	}
	
	//신선 식품 추가
	@RequestMapping(value = "/freshCreate.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void insertFresh(HttpServletRequest request, FreshValueObject freshValueObject) throws Exception {
		Logger.info(null);
		Long prodctSeq = Long.parseLong(request.getParameter("prodctSeq") == null?"":request.getParameter("prodctSeq"));
		String stat = request.getParameter("stat") == null?"":request.getParameter("stat");
		
		freshValueObject.setProdctSeq(prodctSeq);
		freshValueObject.setStat(stat);
		
		freshService.insertFresh(freshValueObject);
	}
	
	//상품 추가 테이블 생성
	@RequestMapping(value = "/freshAdList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String selectFreshAdList(HttpServletRequest request, Model model, FreshValueObject freshValueObject) throws Exception{
		Logger.info(null);
		String catgrSeq = request.getParameter("catgrSeq") == null?"0":request.getParameter("catgrSeq");
		String prodctSechText = request.getParameter("prodctSechText") == null?"":request.getParameter("prodctSechText");
		freshValueObject.setProdctSechText(prodctSechText);
		freshValueObject.setCatgrSeq(Integer.parseInt(catgrSeq));
		List<FreshValueObject> prodctAdList = freshService.selectFreshAdList(freshValueObject);
		model.addAttribute("FreshValueObject", freshValueObject);	// 검색 조건, 페이지 정보들			
		model.addAttribute("ProdctAdList", prodctAdList);
		
		return "prodct/display/fresh/FreshProdctTable";
	}
	
	//상품 추가 화면
	@RequestMapping(value = "/freshCreateRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String freshCreateRead() throws Exception{
		Logger.info(null);
		
		return "prodct/display/fresh/FreshCreate";
	}
}
