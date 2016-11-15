package net.su.end.controller;

import java.util.List;

import javax.annotation.Resource;

import net.su.end.service.ProdctAnalService;
import net.su.end.valueObject.ProdctAnalValueObject;
import net.su.end.valueObject.SalsValueObject;
import net.su.logger.Logger;
import net.su.prodct.service.ProdctService;
import net.su.prodct.valueObject.ProdctValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 상품분석 관련 컨트롤러입니다.
 * 
 * @see   net.su.end.controller.ProdctAnalController
 * @version  1.0 
 * @ author 김대현, 2016/09/07
 */

@Controller
public class ProdctAnalController {

	@Resource
	private ProdctAnalService prodctAnalService;
	@Resource
	private ProdctService prodctService;
	
	/**
	* 판매상품순위 화면을 보여주는 메서드입니다.
	*
	* @param   
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/SelProdctAnalList.do", method={RequestMethod.POST, RequestMethod.GET})
	public String selectSelProdctAnal(ModelMap model, SalsValueObject salsValueObject, ProdctAnalValueObject prodctAnalVo) throws Exception{
		System.out.println(prodctAnalVo.getBeginDatepicker()+"   데이트피커 시작일");
		System.out.println(prodctAnalVo.getEndDatepicker()+"   데이트피커 종료일");
		System.out.println(prodctAnalVo.getSelectedTopCatgrNme()+"   대분류");
		System.out.println(prodctAnalVo.getSelectedMidCatgrNme()+"   중분류");
		System.out.println(prodctAnalVo.getSelectedBotCatgrNme()+"   소분류");
		System.out.println(prodctAnalVo.getProdctNmeSech()+"   상품명");
		System.out.println(prodctAnalVo.getBeginSelPrice()+"   상품 판매 시작가격");
		System.out.println(prodctAnalVo.getEndSelPrice()+"   상품 판매 종료가격");

		
		
		List<ProdctAnalValueObject> prodctSelQuntList = prodctAnalService.selectSelProdctQunt(prodctAnalVo);	// TOP 10 상품 판매수량
		List<ProdctAnalValueObject> prodctSelSumList = prodctAnalService.selectSelProdctSum(prodctAnalVo);		// TOP 10 상품 판매합계
		List<ProdctAnalValueObject> prodctRankingList = prodctAnalService.selectProdctRanking(prodctAnalVo);		// 판매 상품 순위 내역(테이블)
		String[] topCatgrArray=prodctService.catgrList("기본",""); 									// 카테고리
		String[] midCatgrArray=prodctService.catgrList("대",salsValueObject.getTopCatgrNme());		// 카테고리
		String[] botCatgrArray=prodctService.catgrList("중",salsValueObject.getMidCatgrNme());		// 카테고리

		model.addAttribute("prodctSelQuntList", prodctSelQuntList);	// TOP 10 상품 판매수량 차트
		model.addAttribute("prodctSelSumList", prodctSelSumList);	// TOP 10 상품 판매합계 차트
		model.addAttribute("prodctRankingList",prodctRankingList);	// Top 10 상품 판매 순위 테이블
		model.addAttribute("topCatgrArray",topCatgrArray);	// 카테고리
		model.addAttribute("midCatgrArray",midCatgrArray);	// 카테고리
		model.addAttribute("botCatgrArray",botCatgrArray);	// 카테고리
		model.addAttribute("prodctAnalVo", prodctAnalVo);
		return "end/prodctAnal/SelProdctRanking";
	}
	
	// 엑셀다운로드(판매상품 )
	@RequestMapping(value = "/SelProdct.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String selProdctXls(ModelMap model, ProdctAnalValueObject prodctAnalVo) throws Exception {		
		Logger.info(null);		
		prodctAnalVo.setTopCatgrNme(prodctAnalVo.getSelectedTopCatgrNme());
		prodctAnalVo.setMidCatgrNme(prodctAnalVo.getSelectedMidCatgrNme());
		prodctAnalVo.setBotCatgrNme(prodctAnalVo.getSelectedBotCatgrNme());	
		List<ProdctAnalValueObject> selProdctList = prodctAnalService.selectProdctRanking(prodctAnalVo); // 일별매출 현황 리스트		
		model.addAttribute("selProdctList",selProdctList); // 환불상품 리스트
		return "end/prodctAnal/SelProdctRankingXls";
	}

	/**
	* 판매상품순위 화면을 보여주는 메서드입니다.
	*
	* @param   ModelMap, 
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/SelCatgrAnal.do", method={RequestMethod.POST, RequestMethod.GET})
	public String SelCatgrAnal(ModelMap model, ProdctAnalValueObject prodctAnalValueObject, SalsValueObject salsValueObject) throws Exception{
		if (prodctAnalValueObject.getBtnDatSech()==null&&prodctAnalValueObject.getEndDatSech()==null&&prodctAnalValueObject.getStartDatSech()==null)
		{
			prodctAnalValueObject.setBtnDatSech("3일");
		}
		List<ProdctAnalValueObject> catgrSelQuntList = prodctAnalService.catgrSelQuntList(prodctAnalValueObject);	// TOP 10 상품 판매수량
		List<ProdctAnalValueObject> catgrSelSumList = prodctAnalService.catgrSelSumList(prodctAnalValueObject);		// TOP 10 상품 판매합계
		
		String[] topCatgrArray=prodctService.catgrList("기본",""); 									// 카테고리
		String[] midCatgrArray=prodctService.catgrList("대",salsValueObject.getTopCatgrNme());		// 카테고리
		String[] botCatgrArray=prodctService.catgrList("중",salsValueObject.getMidCatgrNme());		// 카테고리
		
		model.addAttribute("catgrSelListSize",catgrSelQuntList.size());
		model.addAttribute("catgrSelQuntList", catgrSelQuntList);	// TOP 10 상품 판매수량
		model.addAttribute("catgrSelSumList", catgrSelSumList);	// TOP 10 상품 판매합계
		model.addAttribute("prodctAnalValueObject", prodctAnalValueObject); 
		model.addAttribute("topCatgrArray",topCatgrArray);	// 카테고리
		model.addAttribute("midCatgrArray",midCatgrArray);	// 카테고리
		model.addAttribute("botCatgrArray",botCatgrArray);	// 카테고리
		return "end/prodctAnal/SelCatgrAnal";
	}

	// 엑셀다운로드(판매상품 )
	@RequestMapping(value = "/SelCatgrXls.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String SelCatgrXls(ModelMap model, ProdctAnalValueObject prodctAnalValueObject) throws Exception {
		if (prodctAnalValueObject.getBtnDatSech()==null)
		{
			prodctAnalValueObject.setBtnDatSech("3일");
		}		
		Logger.info(null);		
		prodctAnalValueObject.setTopCatgrNme(prodctAnalValueObject.getSelectedTopCatgrNme());
		prodctAnalValueObject.setMidCatgrNme(prodctAnalValueObject.getSelectedMidCatgrNme());
		prodctAnalValueObject.setBotCatgrNme(prodctAnalValueObject.getSelectedBotCatgrNme());	
		List<ProdctAnalValueObject> catgrSelSumList = prodctAnalService.catgrSelSumList(prodctAnalValueObject);		// TOP 10 상품 판매합계	
		model.addAttribute("catgrSelSumList",catgrSelSumList); // 환불상품 리스트
		return "end/prodctAnal/SelCatgrAnalXls";
	}
	
	/**
	* 교환환불순위 화면을 보여주는 메서드입니다.
	*
	* @param   
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/ExchngRefndAnalList.do", method={RequestMethod.POST, RequestMethod.GET})
	public String selectExchngRefndAnal(ModelMap model, ProdctAnalValueObject prodctAnalVo) throws Exception{
		System.out.println(prodctAnalVo.getBeginDatepicker()+"   데이트피커 시작일");
		System.out.println(prodctAnalVo.getEndDatepicker()+"   데이트피커 종료일");
		System.out.println(prodctAnalVo.getProdctNmeSech()+"   상품명");
		System.out.println(prodctAnalVo.getBtnDatSech() + "  버튼검색일");
		
		List<ProdctAnalValueObject> refndQuntList = prodctAnalService.selectRefndQunt(prodctAnalVo);	// 교환환불 top10 환불수량 차트
		List<ProdctAnalValueObject> refndRatList = prodctAnalService.selectRefndRat(prodctAnalVo);		// 교환환불 top10 환불비율 차트
		List<ProdctAnalValueObject> refndRankingList = prodctAnalService.selectRefndRanking(prodctAnalVo);	// 환불 상품 순위 내역(테이블)
		model.addAttribute("refndQuntList", refndQuntList);
		model.addAttribute("refndRatList", refndRatList);
		model.addAttribute("refndRankingList", refndRankingList);
		
		return "end/prodctAnal/ExchngRefndRanking";
	}
	
// 엑셀다운로드(교환환불)
	@RequestMapping(value = "/RefndXls.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String refndXls(ModelMap model, ProdctAnalValueObject prodctAnalVo) throws Exception {		
		Logger.info(null);		
//		salsValueObject.setTopCatgrNme(salsValueObject.getSelectedTopCatgrNme());
//		salsValueObject.setMidCatgrNme(salsValueObject.getSelectedMidCatgrNme());
//		salsValueObject.setBotCatgrNme(salsValueObject.getSelectedBotCatgrNme());	
		List<ProdctAnalValueObject> refndList = prodctAnalService.selectRefndRanking(prodctAnalVo); // 일별매출 현황 리스트		
		model.addAttribute("refndList",refndList); // 환불상품 리스트
		return "end/prodctAnal/ExchngRefndRankingXls";
	}
}
