  package net.su.prodct.display.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.su.deal.service.ClintService;
import net.su.deal.valueObject.ClintValueObject;
import net.su.logger.Logger;
import net.su.prodct.display.service.PackgService;
import net.su.prodct.display.valueObject.PackgValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 패키지 메뉴 관련 컨트롤러입니다.
 * 
 * @see   net.su.prodct.display.controller.PackgController
 * @version  1.0 
 * @ author 김동욱, 2016/08/09
 */


@Controller
public class PackgController {
	@Resource
	PackgService packgService;
	
	@Resource
	ClintService clintService;
	
	//패키지 상품 리스트
	@RequestMapping(value = "/packgList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String selectPackgList(Model model, PackgValueObject packgValueObject) throws Exception {
		Logger.info(null);
		List<PackgValueObject> packgList = packgService.selectPackgList(packgValueObject);
		int packgCount = packgService.selectPackgCount(packgValueObject);
		model.addAttribute("PackgValueObject", packgValueObject);	// 검색 조건, 페이지 정보들			
		model.addAttribute("PackgList", packgList); // 패키지 리스트
		model.addAttribute("PackgCount", packgCount); // 패키지 개수
		
		return "prodct/display/packg/PackgList";
	}	
	
	//패키지의 거래처 리스트
	@RequestMapping(value = "/packgClintList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String selectPackgClintList(Model model, ClintValueObject clintValueObject) throws Exception {
		Logger.info(null);
		List<ClintValueObject> clintList = clintService.clintList(clintValueObject);	// 거래처 목록 조회,검색 리스트
		
		model.addAttribute("ClintList", clintList);		// 거래처 목록 조회 리스트
		model.addAttribute("ClintValueObject", clintValueObject);
		
		return "prodct/display/packg/PackgClintList";
	}
	
	//패키지의 거래처 리스트 상세 조회
	@RequestMapping(value = "/packgClintRead.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ClintValueObject selectPackgClintList(int clintSeq) throws Exception {
		Logger.info(null);
		ClintValueObject clintVo = clintService.clintRead(clintSeq);
		return clintVo;
	}
	
	//패키지 진열여부 수정
	@RequestMapping(value = "/packgDisplayCheckUpdate.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void updatePackgDisplayCheck(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
		}
		packgService.updatePackgDisplayCheck(data);
	}
	
	//패키지 삭제
	@RequestMapping(value = "/deletePackg.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void deletePackg(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
		}
		packgService.deletePackg(data);
	}
	
	//패키지 수정
	@RequestMapping(value = "/packgUpdate.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void updatePackg(PackgValueObject packgVo, String chked_val) throws Exception {
		Logger.info(null);
		String[] data = chked_val.split(",");
		for (int i=0; i<data.length; i++){
			System.out.println(data[i]);
		}
		packgService.updatePackg(packgVo);
		packgService.deletePackgBridg(packgVo.getPackgSeq());
		packgService.insertUpdatePackgProdct(packgVo.getPackgSeq(), data);
	}

	//패키지 등록 화면
	@RequestMapping(value = "/packgCreateRead.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String packgCreateRead() throws Exception {
		Logger.info(null);
		
		return "prodct/display/packg/PackgCreate";
	}	
	
	//거래처 상품 리스트 조회
	@RequestMapping(value = "/clintProdctList.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<PackgValueObject> selectClintProdctList(int clintSeq) throws Exception {
		Logger.info(null);
		List<PackgValueObject> clintProdctList = packgService.selectClintProdctList(clintSeq);
		return clintProdctList;
	}	
	
	//패키지 등록 화면의 상품선택 테이블
	@RequestMapping(value = "/packgCreateTable.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String packgCreateTable(int clintSeq) throws Exception {
		Logger.info(null);
		packgService.deletePackgProdctTempList();
		packgService.insertPackgProdctTempList(clintSeq);
		return "prodct/display/packg/PackgCreateTable";
	}	

	//패키지 추가의 상품 리스트 조회
	@RequestMapping(value = "/selectPackgProdctAdList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String selectPackgProdctAdList(HttpServletRequest request, Model model, PackgValueObject packgVo) throws Exception {
		Logger.info(null);
		String catgrSeq = request.getParameter("catgrSeq") == null?"0":request.getParameter("catgrSeq");
		String prodctSechText = request.getParameter("prodctSechText") == null?"":request.getParameter("prodctSechText");
		packgVo.setProdctSechText(prodctSechText);
		packgVo.setCatgrSeq(Integer.parseInt(catgrSeq));
		List<PackgValueObject> packgProdctAdList = packgService.selectPackgProdctAdList(packgVo);
		model.addAttribute("PackgProdctAdList", packgProdctAdList);
		model.addAttribute("PackgValueObject", packgVo);
		return "prodct/display/packg/PackgProdctTable";
	}
	
	//거래처 상품 임시 리스트 조회
	@RequestMapping(value = "/selectPackgProdctAdTempList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String selectPackgProdctTempList(HttpServletRequest request, Model model, PackgValueObject packgVo) throws Exception {
		Logger.info(null);
		String tempSechText = request.getParameter("tempSechText") == null?"":request.getParameter("tempSechText");
		packgVo.setTempSechText(tempSechText);
		List<PackgValueObject> packgProdctAdTempList = packgService.selectPackgProdctAdTempList(packgVo);
		model.addAttribute("PackgProdctAdTempList", packgProdctAdTempList);
		model.addAttribute("PackgValueObject", packgVo);
		return "prodct/display/packg/PackgTempTable";
	}
	
	//임시 테이블 상품추가
	@RequestMapping(value = "/insertPackgProdctAdTempList.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void insertPackgProdctAdTempList(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		System.out.println(chkedVal+"@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		packgService.insertPackgProdctAdTempList(data);
	}
		
	//임시 테이블 상품삭제
	@RequestMapping(value = "/deletePackgProdctAdTempList.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void deletePackgProdctAdTempList(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		System.out.println(chkedVal+"@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		packgService.deletePackgProdctAdTempList(data);
	}
	
	//패키지 상품 최종 임시 리스트
	@RequestMapping(value = "/selectPackgProdctAdTempList2.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<PackgValueObject> selectPackgProdctAdTempList2() throws Exception {
		Logger.info(null);
		List<PackgValueObject> packgProdctAdTempList = packgService.selectPackgProdctAdTempList();
		
		return packgProdctAdTempList;
	}
	
	//패키지 등록
	@RequestMapping(value = "/packgCreate.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void insertPackg(PackgValueObject packgVo, String chked_val) throws Exception {
		Logger.info(null);
		String[] data = chked_val.split(",");
		for (int i=0; i<data.length; i++){
			System.out.println(data[i]);
		}
		packgService.insertPackg(packgVo);
		packgService.insertPackgProdct(data);
	}
	
	//패키지 상세조회
	@RequestMapping(value = "/packgRead.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String selectPackgRead(int packgSeq, Model model) throws Exception {
		Logger.info(null);
		PackgValueObject packgRead = packgService.selectPackgRead(packgSeq);
		List<PackgValueObject> packgProdctReadList = packgService.selectPackgProdctRead(packgSeq);
		model.addAttribute("PackgRead", packgRead);
		model.addAttribute("PackgProdctReadList", packgProdctReadList);
		
		return "prodct/display/packg/PackgRead";
	}
	
	//패키지 상세조회의 상품 리스트 조회
	@RequestMapping(value = "/selectPackgProdctReadAdList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String selectPackgProdctReadAdList(HttpServletRequest request, Model model, PackgValueObject packgVo) throws Exception {
		Logger.info(null);
		if(packgVo.getCatgrSeq()!=0){
			String catgrSeq = request.getParameter("catgrSeq") == null?"0":request.getParameter("catgrSeq");
			packgVo.setCatgrSeq(Integer.parseInt(catgrSeq));
		}
		List<PackgValueObject> packgProdctReadAdList = packgService.selectPackgProdctReadAdList(packgVo);
		model.addAttribute("PackgProdctAdList", packgProdctReadAdList);
		model.addAttribute("PackgValueObject", packgVo);
		return "prodct/display/packg/PackgProdctTable";
	}
	
	//패키지 등록 화면의 상품선택 테이블
	@RequestMapping(value = "/packgReadTable.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String packgReadCreateTable(int packgSeq) throws Exception {
		Logger.info(null);
		packgService.deletePackgProdctTempList();
		packgService.insertPackgProdctReadTempList(packgSeq);
		return "prodct/display/packg/PackgReadTable";
	}	
}