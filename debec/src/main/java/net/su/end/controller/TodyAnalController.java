package net.su.end.controller;

import java.util.List;

import javax.annotation.Resource;

import net.su.end.service.TodyAnalService;
import net.su.end.valueObject.TodyAnalValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 투데이리포트 컨트롤러입니다.
 * 
 * @see   net.su.end.controller.TodyAnalController
 * @version  1.0 
 * @ author 김대현, 2016/08/24
 */
@Controller
public class TodyAnalController {
	@Resource 
	private TodyAnalService todyAnalService;

	/**
	* 통계관리 - 투데이리포트 - 투데이 실적을 조회 메서드입니다.
	*
	* @param   ModelMap model
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/TodyAnalList.do", method={RequestMethod.GET, RequestMethod.POST})
	public String selectTodyAnalList(ModelMap model) throws Exception{
		TodyAnalValueObject selectTodyResult = todyAnalService.selectTodyResult();	// 투데이실적 - 매출액
		int telOrdrCount = todyAnalService.telOrdrCount();	// 전화주문건수
		int appOrdrCount = todyAnalService.appOrdrCount();	// 어플주문건수
		int ordrCancelCount = todyAnalService.ordrCancelCount();	// 주문취소건수
		String exchngRefndCount = todyAnalService.exchngRefndCount();	// 교환환불건수
		System.out.println(exchngRefndCount+ "   교환환불건수 @@@@@@@@@@@@@@@@@@");
		List<TodyAnalValueObject> todySalesList = todyAnalService.todySalesList();	// 오늘 상품 판매량 순위 top 10
		List<TodyAnalValueObject> surgeSelProdctList = todyAnalService.surgeSelProdctList();	// 급상승 판매상품 순위 top10
		List<TodyAnalValueObject> salsTime = todyAnalService.salsTime();	// 일일매출 그래프 행(시간)
		
		model.addAttribute("surgeSelProdctList", surgeSelProdctList);
		model.addAttribute("selectTodyResult", selectTodyResult);
		model.addAttribute("telOrdrCount", telOrdrCount);
		model.addAttribute("appOrdrCount", appOrdrCount);
		model.addAttribute("ordrCancelCount", ordrCancelCount);
		model.addAttribute("exchngRefndCount", exchngRefndCount);
		model.addAttribute("todySalesList", todySalesList);
		model.addAttribute("salsTime", salsTime);
		return "end/todyAnal/TodyAnalList";
	}
	
	/**
	* 통계관리 - 투데이리포트 - 매출현황 등록 팝업 화면 메서드입니다.
	*
	* @param   ModelMap model
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/SalsPresntCreateReadPopup.do", method={RequestMethod.POST, RequestMethod.GET})
	public String SalsPresntInsertPopup() throws Exception{
		return "end/todyAnal/SalsPresntCreatePopup";
	}
	
	/**
	* 통계관리 - 투데이리포트 - 매출현황 등록 팝업 - 엑셀파일 업로드 메서드입니다.
	*
	* @param  
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/SalsPresntExcelUpload.do", method = RequestMethod.POST)
	public String insertSalsPresnt(MultipartHttpServletRequest request, TodyAnalValueObject todyAnalVo) throws Exception{
		todyAnalService.insertSalsPresnt(request, todyAnalVo);
		return "redirect:/TodyAnalList.do";
	}
}
