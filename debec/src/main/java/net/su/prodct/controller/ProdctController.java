/**
 * 판매 상품 관리 컨트롤러입니다.
 * 
 * @see   net.su.prodct.controller.ProdctController
 * @version  1.0 
 * @ author 하원식, 2016/08/27
 */

package net.su.prodct.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.su.deal.service.ClintService;
import net.su.deal.valueObject.ClintValueObject;
import net.su.logger.Logger;
import net.su.prodct.service.DebecFestivalService;
import net.su.prodct.service.ProdctService;
import net.su.prodct.valueObject.CatgrValueObject;
import net.su.prodct.valueObject.DebecFestivalValueObject;
import net.su.prodct.valueObject.ProdctValueObject;









import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class ProdctController {

	@Resource
	private  ProdctService prodctService;
	@Resource
	private  ClintService clintService;
	@Resource
	private  DebecFestivalService debecFestivalService;
	
	// ProdctServiceImpl 바로가기
	
	/**
    * 판매 상품 리스트를 조회하는 메서드다..
    *
    * @param   Session, Model, ProdctValueObject
    * @return  String
    * @exception  Exception
    */
	@RequestMapping(value = "/selProdctList.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String selProdctList(HttpSession session, Model model,ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);

		prodctValueObject.setPageType(1);
		List<ProdctValueObject> selProdctList = prodctService.selProdctList(prodctValueObject);
		String[] topCatgrArray= prodctService.catgrList("기본","");
		String[] midCatgrArray=prodctService.catgrList("대",prodctValueObject.getTopCatgrNme());
		String[] botCatgrArray=prodctService.catgrList("중",prodctValueObject.getMidCatgrNme());
		model.addAttribute("selProdctList",selProdctList);
		model.addAttribute("prodctValueObject",prodctValueObject);
		model.addAttribute("topCatgrArray",topCatgrArray);
		model.addAttribute("midCatgrArray",midCatgrArray);
		model.addAttribute("botCatgrArray",botCatgrArray);
		
		return "prodct/prodct/SelProdctList";
	}
	
	/**
    * 반응형 카테고리 리스트 입니다.
    *
    * @param   @RequestParam(value="catgrSize") String catgrSize, @RequestParam(value="catgrNme") String catgrNme
    * @return  @ResponseBody Map<String, Object>
    * @exception  Exception
    */
	@RequestMapping(value = "/catgrList.do", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> catgrList(@RequestParam(value="catgrSize") String catgrSize, @RequestParam(value="catgrNme") String catgrNme) throws Exception {
		Logger.info(null);
		
		Map<String, Object> catgrListCrueMap = new HashMap<String, Object>();
		System.out.println(catgrSize+"#사이즈"+catgrNme+"#이름");
		String[] catgrArray= prodctService.catgrList(catgrSize,catgrNme);
		String[] botCatgrArray; 
		if(catgrSize.equals("대"))
		{
			botCatgrArray =  prodctService.catgrList("중",catgrArray[0]);
			catgrListCrueMap.put("botCatgrArray",botCatgrArray);
		}
		catgrListCrueMap.put("catgrArray",catgrArray);
		return catgrListCrueMap;
	}
	
	
	/**
    * 판매 상품 등록 화면 이동 메서드 입니다.
    *
    * @param   Model, ProdctValueObject, HttpSession
    * @return  String
    * @exception  Exception
    */
	@RequestMapping(value = "/selProdctCreateRead.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String SelProdctCreateRead(Model model,ProdctValueObject prodctValueObject, HttpSession selProdctSession) throws Exception {
		Logger.info(null);
		ClintValueObject clintVo = new ClintValueObject();
		clintVo.setProdctSeq(prodctValueObject.getProdctSeq());
		DebecFestivalValueObject debecFestivalValueObject = new DebecFestivalValueObject();
		String[] topCatgrArray= prodctService.catgrList("기본","");
		String[] midCatgrArray=prodctService.catgrList("대",prodctValueObject.getTopCatgrNme());
		String[] botCatgrArray=prodctService.catgrList("중",prodctValueObject.getMidCatgrNme());
		List<ClintValueObject> clintList = clintService.clintList(clintVo);
		List<DebecFestivalValueObject> debecFestivalList = debecFestivalService.debecFestivalList(debecFestivalValueObject);
		selProdctSession.setAttribute("catgrSeq","no"); // prodctSession 임시값 저장
		
		model.addAttribute("prodctValueObject",prodctValueObject);
		model.addAttribute("topCatgrArray",topCatgrArray);
		model.addAttribute("midCatgrArray",midCatgrArray);
		model.addAttribute("botCatgrArray",botCatgrArray);
		model.addAttribute("clintList", clintList);
		model.addAttribute("debecFestivalList", debecFestivalList);
		
		return "prodct/prodct/SelProdctCreate";
	}

	/**
    * 판매 상품을 등록하는 메서드 입니다.
    *
    * @param   ProdctValueObject, HttpSession
    * @return  String
    * @exception  Exception
    */
	@RequestMapping(value = "/selProdctCreate.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String selProdctCreate(HttpSession selProdctSession, ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);

			prodctService.selProdctCreate(prodctValueObject, selProdctSession);

		return "redirect:/selProdctList.do";
	}
	
	
	/**
    * 판매 상품의 이미지를  등록하는 메서드다.
    *
    * @param   MultipartHttpServletRequest, ProdctValueObject, HttpSession
    * @return  String
    * @exception  Exception
    */
	@RequestMapping(value = "/selProdctImgCreate.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String selProdctImgCreate(MultipartHttpServletRequest request, ProdctValueObject prodctValueObject,HttpSession selProdctSession) throws Exception {
		Logger.info(null);

			prodctService.selProdctImgCreate(request, prodctValueObject,selProdctSession);
	
		return "dummy";
	}
	
	
	/**
    * 판매 상품 바코드의 중복 여부를 검사하는 메서드다.
    *
    * @param   @RequestParam(value="prodctSeq"), long dupliCheck
    * @return  @ResponseBody Map<String, Object>
    * @exception  Exception
    */
	@RequestMapping(value = "/prodctSeqDuliCheck.do", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> prodctSeqDuliCheck(@RequestParam(value="prodctSeq") long dupliCheck) throws Exception {
		Logger.info(null);
		Map<String, Object> prodctSeqDuliCheck = new HashMap<String, Object>();
		
		dupliCheck = prodctService.prodctSeqDuliCheck(dupliCheck);
		prodctSeqDuliCheck.put("dupliCheck",dupliCheck);
		
		return prodctSeqDuliCheck;

	}

	
	/**
    * 미리보기를 위한 이미지 파일을 임시로 저장해주는 메서드다.
    *
    * @param   HttpSession, MultipartHttpServletRequest, ProdctValueObject
    * @return  @ResponseBody Map<String, Object>
    * @exception  Exception
    */		
	@RequestMapping(value = "/tempProdctImgSave.do", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> tempProdctImgSave(HttpSession selProdctSession, MultipartHttpServletRequest request, ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		prodctValueObject.setProdctSeq(prodctValueObject.getProdctFileSeq());
		prodctValueObject = prodctService.tempProdctImgSave(selProdctSession, request, prodctValueObject);
		
		
		
		Map<String, Object> prodctImgPathMap = new HashMap<String, Object>();
		
		
		prodctImgPathMap.put("prodctValueObject",prodctValueObject);
		return prodctImgPathMap;
		
	}

	/**
    * 카테고리 리스트를 조회하는 메서드 입니다.
    *
    * @param   Model
    * @return  Object
    * @exception  Exception
    */	
	@RequestMapping(value = "/catgrProdctList.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody	
	public Object catgrProdctList(Model model) throws Exception {
		Logger.info(null);
		List<CatgrValueObject> catgrProdctList = prodctService.catgrProdctList();
		ObjectMapper catgrProdctMapper = new ObjectMapper();
		return catgrProdctMapper.writeValueAsString(catgrProdctList);
	}
	

	/**
    * 카테고리 변경에 따른 내역을 세션에 저장하는 메서드다.
    *
    * @param   HttpSession, @RequestParam(value="selectedCatgrSeq[]") String[] catgrSeq
    * @return  String
    * @exception  Exception
    */	
	@RequestMapping(value = "/catgrChange.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String catgrChange(HttpSession selProdctSession, @RequestParam(value="selectedCatgrSeq[]") String[] catgrSeq) throws Exception {
		Logger.info(null);
		String temp = catgrSeq[0];
		selProdctSession.setAttribute("catgrSeq",temp);
		return "redirect:/reltnProdctAdList.do";
	}
	

	/**
    * 연관 상품 등록 팝업에서 게시물을 조회하는 메서드다.
    *
    * @param   Model, HttpSession, ProdctValueObject
    * @return  String
    * @exception  Exception
    */
	@RequestMapping(value = "/reltnProdctAdList.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String ReltnProdctAdList(Model model, HttpSession selProdctSession,ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		String catgrSeq ="0";
		if(selProdctSession.getAttribute("catgrSeq")!=null)
		{
			catgrSeq = (String) selProdctSession.getAttribute("catgrSeq");
		}
		prodctValueObject.setCatgrSeq(catgrSeq);
		List<ProdctValueObject> selProdctList = prodctService.selProdctList(prodctValueObject);
		model.addAttribute("prodctValueObject",prodctValueObject);
		model.addAttribute("selProdctList", selProdctList);
		return "prodct/prodct/ProdctTable";
	}
	

	/**
    *  관련 상품의 상세 내역을 조회해주는 메서드 다.
    *
    * @param   Model, @RequestParam(value="prodctSeq") long prodctSeq
    * @return  @ResponseBody Map<String, Object>
    * @exception  Exception
    */	
	@RequestMapping(value = "/reltnProdctDetlRead.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public @ResponseBody Map<String, Object> reltnProdctDetlRead(Model model, @RequestParam(value="prodctSeq") long prodctSeq) throws Exception {
		Logger.info(null);
		ProdctValueObject prodctValueObject = new ProdctValueObject();
		prodctValueObject.setProdctSeq(prodctSeq);
		List<ProdctValueObject> reltnProdctList = prodctService.selProdctList(prodctValueObject);
		Map<String, Object>  reltnProdctMapper = new HashMap<String, Object>();
		reltnProdctMapper.put("reltnProdctList",reltnProdctList);
		return reltnProdctMapper;
	}
	
	/**
    *  판매 중인 상품을 판매 중지하는 메서드 입니다.
    *
    * @param   @RequestBody String chkedVal
    * @return  void
    * @exception  Exception
    */	
	@RequestMapping(value = "/selProdctDelete.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public void selProdctDelete(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		String[] data = chkedVal.split("%2C");
		long[] selProdctArray = new long[data.length];
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
			data[i] = data[i].replace("=","");
			System.out.println(data[i]);
			selProdctArray[i] = Long.parseLong(data[i]);
		}
		prodctService.selProdctDelete(selProdctArray);
	
	}
	

	/**
    *  관련 상품의 상세 내역을 조회해주는 메서드 입니다.
    *
    * @param   Model, @RequestParam(value="prodctSeq") long prodctSeq
    * @return  @ResponseBody Map<String, Object>
    * @exception  Exception
    */	
	@RequestMapping(value = "/schedlProdctDetl.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public @ResponseBody Map<String, Object> schedlProdctDetl(@RequestParam(value="schedlSeq") int schedlSeq) throws Exception {
		Logger.info(null);
		DebecFestivalValueObject debecFestivalValueObject = new DebecFestivalValueObject();
		debecFestivalValueObject.setSchedlSeq(schedlSeq);
		List<DebecFestivalValueObject> debecFestivalList = debecFestivalService.debecFestivalList(debecFestivalValueObject);
		System.out.println(debecFestivalList.get(0).getEvntStat()+"#######");
		Map<String, Object>  schedlProdctDetl = new HashMap<String, Object>();
		schedlProdctDetl.put("schedlStartDat",debecFestivalList.get(0).getSchedlStartDat());
		schedlProdctDetl.put("evntStat",debecFestivalList.get(0).getEvntStat());
		schedlProdctDetl.put("schedlEndtDat",debecFestivalList.get(0).getSchedlEndDat());
		return schedlProdctDetl;
	}
	

	/**
    *  판매 상품을 상세보기하는 메서드 입니다.
    *
    * @param   ProdctValueObject, Model, HttpSession
    * @return  String
    * @exception  Exception
    */	
	@RequestMapping(value = "/selProdctRead.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String selProdctRead(ProdctValueObject prodctValueObject, Model model, HttpSession selProdctSession) throws Exception {
		Logger.info(null);
		
		if(prodctValueObject.getProdctSeq()!=0)
		{
			selProdctSession.setAttribute("prodctSeq", prodctValueObject.getProdctSeq());
		}
		else 
		{
			prodctValueObject.setProdctSeq((Long) selProdctSession.getAttribute("prodctSeq"));
		}
		
		if(prodctValueObject.getPageType()!=0)
		{
			selProdctSession.setAttribute("pageType", prodctValueObject.getPageType());
		}
		else
		{
			prodctValueObject.setPageType((Integer) selProdctSession.getAttribute("pageType"));
		}
		ArrayList<ProdctValueObject> selProdctReads = prodctService.selProdctRead(prodctValueObject.getProdctSeq());
		model.addAttribute("selProdctRead", selProdctReads.get(0));
		model.addAttribute("reltnProdctRead1", selProdctReads.get(1));
		model.addAttribute("reltnProdctRead2", selProdctReads.get(2));
		model.addAttribute("reltnProdctRead3", selProdctReads.get(3));
		model.addAttribute("evntInfoRead", selProdctReads.get(4));
		model.addAttribute("catgrProdctRead", selProdctReads.get(5));
		model.addAttribute("ProdctValueObject", prodctValueObject);
		return "prodct/prodct/SelProdctRead";
	}

	/**
    *  판매 중인 상품의 정보를 판매 중지 사앹로 바꿔주는 메서드다.
    *
    * @param   ProdctValueObject
    * @return  String
    * @exception  Exception
    */	
	@RequestMapping(value = "/selProdctReadDelete.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String selProdctReadDelete(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		
		long[] prodctSeq = new long[1];
		prodctSeq[0] = prodctValueObject.getProdctSeq();
		prodctService.selProdctDelete(prodctSeq);
	
		return "redirect:selProdctList.do";
	}

	/**
    *  판매 중인 상품의 정보를 판매 중지 사앹로 바꿔주는 메서드다.
    *
    * @param   ProdctValueObject
    * @return  String
    * @exception  Exception
    */	
	@RequestMapping(value = "/selStopProdctReadDelete.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String selStopProdctReadDelete(ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		
		long[] prodctSeq = new long[1];
		prodctSeq[0] = prodctValueObject.getProdctSeq();
		prodctService.selStopProdctDelete(prodctSeq);
	
		return "redirect:selStopProdctList.do";
	}
	
	
	
	/**
    *  판매 상품의 정보를 수정하기 위한 페이지로 이동시켜주는 메서드다.
    *
    * @param   ProdctValueObject, Model, HttpSession
    * @return  String
    * @exception  Exception
    */	
	@RequestMapping(value = "/selProdctUpdateRead.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String selProdctUpdateRead(ProdctValueObject prodctValueObject, Model model,HttpSession selProdctSession) throws Exception {
		Logger.info(null);
		
		ClintValueObject clintVo = new ClintValueObject();
		DebecFestivalValueObject debecFestivalValueObject = new DebecFestivalValueObject();
		debecFestivalValueObject.setRecordCountPerPage(9999);
		ArrayList<ProdctValueObject> selProdctReads = prodctService.selProdctRead(prodctValueObject.getProdctSeq());	
		String[] topCatgrArray= prodctService.catgrList("기본","");
		String[] midCatgrArray=prodctService.catgrList("대",prodctValueObject.getTopCatgrNme());
		String[] botCatgrArray=prodctService.catgrList("중",prodctValueObject.getMidCatgrNme());
		List<ClintValueObject> clintList = clintService.clintList(clintVo);
		List<DebecFestivalValueObject> debecFestivalList = debecFestivalService.debecFestivalList(debecFestivalValueObject);
		
		selProdctSession.setAttribute("catgrSeq","no");
		
		model.addAttribute("topCatgrArray",topCatgrArray);
		model.addAttribute("midCatgrArray",midCatgrArray);
		model.addAttribute("botCatgrArray",botCatgrArray);
		model.addAttribute("clintList", clintList);
		model.addAttribute("debecFestivalList", debecFestivalList);
		model.addAttribute("prodctValueObject", prodctValueObject);
		model.addAttribute("selProdctRead", selProdctReads.get(0));
		model.addAttribute("reltnProdctRead1", selProdctReads.get(1));
		model.addAttribute("reltnProdctRead2", selProdctReads.get(2));
		model.addAttribute("reltnProdctRead3", selProdctReads.get(3));
		model.addAttribute("evntInfoRead", selProdctReads.get(4));
		model.addAttribute("catgrProdctRead", selProdctReads.get(5));
	
		return "prodct/prodct/SelProdctUpdate";
	}
	
	
	
	/**
    *  판매 중인 상품의 정보를 수정하는 메서드다.
    *
    * @param   ProdctValueObject, HttpSession
    * @return  String
    * @exception  Exception
    */	
	@RequestMapping(value = "/selProdctUpdate.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String selProdctUpdate(ProdctValueObject prodctValueObject, HttpSession selProdctSession) throws Exception 
	{
		Logger.info(null);
		
		prodctService.selProdctUpdate(prodctValueObject, selProdctSession);	
		
		return "redirect:/selProdctRead.do";
	}
	
	

	/**
    *  이미지를 크게 볼 수 있게 해주는 메서드다.
    *
    * @param   HttpSession, Model
    * @return  String
    * @exception  Exception
    */	
	@RequestMapping(value = "/selProdctImgExpnd.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String selProdctImgExpnd(HttpSession selProdctSession, Model model) throws Exception 
	{
		Logger.info(null);
	
		return "prodct/prodct/SelProdctImgExpnd";
	}

	/**
    *  판매 상품의 이미지 경로를 저장하는 메서드 입니다.
    *
    * @param   Model, @RequestParam(value="prodctSeq") long prodctSeq
    * @return  @ResponseBody Map<String, Object>
    * @exception  Exception
    */	
	@RequestMapping(value = "/selProdctImgPathSession.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public @ResponseBody Map<String, Object> selProdctImgPathSession(MultipartHttpServletRequest request, ProdctValueObject prodctValueObject, HttpSession selProdctSession) throws Exception 
	{
		Logger.info(null);
		
		prodctService.selProdctImgPathSession(prodctValueObject, selProdctSession);	
		
		Map<String, Object> dummy = new HashMap<String, Object>();
		
		return dummy;
	}

	
	
	/**
    * 판매 중지 상품 리스트를 조회하는 매서드 입니다.
    *
    * @param   HttpSession, Model, ProdctValueObject
    * @return  String
    * @exception  Exception
    */
	@RequestMapping(value = "/selStopProdctList.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String selStopProdctList(HttpSession session, Model model,ProdctValueObject prodctValueObject) throws Exception {
		Logger.info(null);
		
		List<ProdctValueObject> SelStopProdctList = prodctService.selStopProdctList(prodctValueObject);
		String[] topCatgrArray= prodctService.catgrList("기본","");
		String[] midCatgrArray=prodctService.catgrList("대",prodctValueObject.getTopCatgrNme());
		String[] botCatgrArray=prodctService.catgrList("중",prodctValueObject.getMidCatgrNme());
		prodctValueObject.setPageType(2);
		model.addAttribute("selStopProdctList",SelStopProdctList);
		model.addAttribute("prodctValueObject",prodctValueObject);
		model.addAttribute("topCatgrArray",topCatgrArray);
		model.addAttribute("midCatgrArray",midCatgrArray);
		model.addAttribute("botCatgrArray",botCatgrArray);
		
		return "prodct/prodct/SelProdctList";
	}

	

	/**
    * 판매 중지 상품 을 삭제 하는 메서드다.
    *
    * @param   @RequestBody String chkedVal
    * @return  String
    * @exception  Exception
    */
	@RequestMapping(value = "/selStopProdctDelete.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String selStopProdctDelete(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		String[] data = chkedVal.split("%2C");
		long[] selStopProdctArray = new long[data.length];
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
			data[i] = data[i].replace("=","");
			selStopProdctArray[i] = Long.parseLong(data[i]);
		}
		prodctService.selStopProdctDelete(selStopProdctArray);
		
		return "redirect:/selStopProdctList.do";
	}
	
	
	/**
    * 판매 중지 상품의 상태를 판매중으로 바꿔주는 메서드다.
    *
    * @param   @RequestBody String chkedVal
    * @return  String
    * @exception  Exception
    */
	@RequestMapping(value = "/selStopProdctReSelStrt.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String selStopProdctSelng(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		String[] data = chkedVal.split("%2C");
		long[] selStopProdctArray = new long[data.length];
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
			data[i] = data[i].replace("=","");
			System.out.println(data[i]);
			selStopProdctArray[i] = Long.parseLong(data[i]);
		}
		prodctService.selStopProdctReSelStrt(selStopProdctArray);
		
		return "redirect:/selStopProdctList.do";
	}
	
	
	
	/**
    * 연관 상품 등록을 위한 팝업을 띄우는 메서드다.
    *
    * @param   Model, ProdctValueObject, HttpSession
    * @return  String
    * @exception  Exception
    */
	@RequestMapping(value = "/reltnProdctAdListPopUp.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String reltnProdctAdListPopUp(Model model,ProdctValueObject prodctValueObject, HttpSession selProdctSession) throws Exception {
		Logger.info(null);

		selProdctSession.setAttribute("catgrSeq","0");
		return "prodct/prodct/SelProdctPopUp";
	}
	

	/**
    *  관련 상품의 상세 내역을 조회해주는 메서드 입니다.
    *
    * @param   HttpSession, @RequestParam(value="prodctSeq") long prodctSeq
    * @return  @ResponseBody String
    * @exception  Exception
    */	
	@RequestMapping(value = "/tempImgPathCleaner.do", method = {RequestMethod.GET,RequestMethod.POST})	
	@ResponseBody
	public String tempImgPathCleaner(HttpSession selProdctSession) throws Exception {
		Logger.info(null);
		prodctService.directoryCleaner((String)selProdctSession.getAttribute("tempImgPath"));
		
		return "dummy";
	}
	
			
}
