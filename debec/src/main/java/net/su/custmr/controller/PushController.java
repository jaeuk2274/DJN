package net.su.custmr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import net.su.custmr.service.PushService;
import net.su.custmr.valueObject.OrdrValueObject;
import net.su.custmr.valueObject.PushValueObject;
import net.su.logger.Logger;
import net.su.login.valueObject.LoginValueObject;
import net.su.prodct.service.ProdctService;
import net.su.prodct.valueObject.CatgrValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PushController {
	@Resource
	private PushService pushService;
	@Resource
	private  ProdctService prodctService;
	
	/**
	* 푸시 알림 메인 조회를 하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushNotfcatnMain.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushNotfcatnMain(Model model, PushValueObject pushVo)throws Exception{
		Logger.info("푸시알림  메인 조회 컨트롤러 입니다.");
		int dateOption = 1;
		dateOption = pushVo.getDateOption();
		Map<String, Object> map = pushService.pushNotfcatnMain(dateOption);
		model.addAttribute("pushVo", pushVo);
		model.addAttribute("pushNotfcatnDayStatstcs", map.get("pushNotfcatnDayStatstcs"));
		model.addAttribute("pushNotfcatnWekStatstcs", map.get("pushNotfcatnWekStatstcs"));
		model.addAttribute("pushNotfcatnMonthStatstcs", map.get("pushNotfcatnMonthStatstcs"));
		model.addAttribute("pushNotfcatnStatstcsGraph", map.get("pushNotfcatnStatstcsGraph"));
		model.addAttribute("pushNotfcatnStatstcsGraphSum", map.get("pushNotfcatnStatstcsGraphSum"));
		
		return "custmr/push/PushNotfcatnMain";
	}
	/**
	* 푸시 알림 발송 목록 조회의 수신자 임시 테이블 삭제를 하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushRecpntAllTempDelete.do", method={RequestMethod.GET,RequestMethod.POST})
	public void deletePushRecpntAllTemp()throws Exception{
		Logger.info("푸시알림  발송 목록 조회 수신자 삭제 컨트롤러 입니다.");
		pushService.deletePushRecpntAllTemp();
	}
	/**
	* 푸시 알림 발송 목록 조회를 하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushNotfcatnDespSelectList.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushNotfcatnDespList(Model model, PushValueObject pushVo)throws Exception{
		Logger.info("푸시알림  발송 목록 조회 컨트롤러 입니다.");
		pushService.deletePushRecpntAllTemp();
		List<PushValueObject> pushNotfcatnList= pushService.pushNotfcatnList(pushVo);
		List<PushValueObject> pushHistoryList = pushService.selectPushHistory();
		
		model.addAttribute("pushNotfcatnList", pushNotfcatnList);
		model.addAttribute("pushHistoryList", pushHistoryList);
		
		return "custmr/push/PushNotfcatnDespList";
	}
	/**
	* 푸시 알림 발송 불러오기 템플릿 목록 팝업 조회를 하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushImprtTempltListPopup.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushImprtTempltListPopup(Model model, PushValueObject pushVo, PushValueObject pushVo2)throws Exception{
		Logger.info("푸시알림  발송 불러오기 템플릿 목록 팝업 조회 컨트롤러 입니다.");
		List<PushValueObject> pushNotfcatnList = pushService.pushNotfcatnList(pushVo);
		List<PushValueObject> pushNotfcatnList2 = pushService.pushNotfcatnDespRecrdList(pushVo2);
		
		model.addAttribute("pushVo", pushVo);	// 검색 조건, 페이지 정보들
		model.addAttribute("pushVo2", pushVo2);	// 검색 조건, 페이지 정보들
		model.addAttribute("pushNotfcatnList", pushNotfcatnList);
		model.addAttribute("pushNotfcatnList2", pushNotfcatnList2);
		
		return "custmr/push/PushNotfcatnImprtTempltPopup";
	}
	/**
	* 푸시 알림 발송 불러오기 템플릿 목록 팝업의 템플릿 테이블 조회를 하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushImprtTempltTable.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushImprtTempltTable(Model model, PushValueObject pushVo)throws Exception{
		Logger.info("푸시알림  발송 불러오기 템플릿 목록 팝업 조회 컨트롤러 입니다.");
		List<PushValueObject> pushNotfcatnList = pushService.pushNotfcatnList(pushVo);
		
		model.addAttribute("pushVo", pushVo);	// 검색 조건, 페이지 정보들
		model.addAttribute("pushNotfcatnList", pushNotfcatnList);
		
		return "custmr/push/PushNotfcatnImprtTempltTable";
	}
	/**
	* 푸시 알림 발송 불러오기 템플릿 목록 팝업의 발송내역 테이블 조회를 하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushImprtTempltDespTable.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushImprtTempltDespTable(Model model, PushValueObject pushVo)throws Exception{
		Logger.info("푸시알림  발송 불러오기 템플릿 목록 팝업 조회 컨트롤러 입니다.");
		List<PushValueObject> pushNotfcatnList = pushService.pushNotfcatnDespRecrdList(pushVo);
		
		model.addAttribute("pushVo2", pushVo);	// 검색 조건, 페이지 정보들
		model.addAttribute("pushNotfcatnList2", pushNotfcatnList);
		
		return "custmr/push/PushNotfcatnImprtTempltDespTable";
	}
	/**
	* 푸시 알림 템플릿 목록 조회를 하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushNotfcatnTempltSelectList.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushNotfcatnTempltSelectList(Model model, PushValueObject pushVo)throws Exception{
		Logger.info("푸시알림  템플릿 목록 조회 컨트롤러 입니다.");
		List<PushValueObject> pushNotfcatnList= pushService.pushNotfcatnList(pushVo);
		
		model.addAttribute("pushVo", pushVo);	// 검색 조건, 페이지 정보들
		model.addAttribute("pushNotfcatnList", pushNotfcatnList);
		
		return "custmr/push/PushNotfcatnTempltList";
	}
	/**
	* 푸시 알림 템플릿 등록 화면 팝업 이동을 하는 메서드입니다.
	*
	* @param   Model model, @ModelAttribute("PushValueObject") PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	//푸시알립 템플릿 등록 팝업
	@RequestMapping(value = "/pushTempltInsertPopup.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushTempltInsertPopup(Model model, @ModelAttribute("PushValueObject") PushValueObject pushVo)throws Exception{
		Logger.info("푸시알림  템플릿 등록 화면 팝업 컨트롤러 입니다.");
		
		return "custmr/push/PushNotfcatnTempltInsertPopup";
	}
	/**
	* 푸시 알림 템플릿 등록를 하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/TempltInsert.do", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
	public String pushNotfcatnTempltInsert(PushValueObject pushVo, HttpSession session)throws Exception{
		Logger.info("푸시알림  템플릿 등록 컨트롤러 입니다.");
		LoginValueObject userLoginInfo = (LoginValueObject)session.getAttribute("loginUserInfo");
		System.out.println(userLoginInfo.getEmpNme()+"@@@@@@@@@@@@");
		pushVo.setRegstr(userLoginInfo.getEmpNme());		
		pushService.pushNotfcatTempltInsert(pushVo);
		return "flag";
	}
	/**
	* 푸시 알림 템플릿 상세 조회를 하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushNotfcatnTempltSelectDetlPopup.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushNotfcatnTempltSelectDetl(Model model, @RequestParam(value="pushTempltSeq") int pushTempltSeq)throws Exception{
		Logger.info("푸시알림  템플릿 상세 조회 컨트롤러 입니다.");
		PushValueObject pushNotfcatnTempltSelectDetl = pushService.pushNotfcatnTempltSelectDetl(pushTempltSeq);
		
		model.addAttribute("pushNotfcatnTempltSelectDetl", pushNotfcatnTempltSelectDetl);
		
		return "custmr/push/PushNotfcatnSelectDetlPopup";
	}
	/**
	 * 
	* 푸시 알림 템플릿 삭제를 하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo,@RequestParam(value="pushAllDelete[]") List<String> allCheck
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushTempltDelete.do", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public void pushTempltDelete(@RequestBody String chkedVal)throws Exception{
		Logger.info("푸시알림  템플릿 삭제 컨트롤러 입니다.");
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		pushService.pushTempltDelete(data);
	}
	/**
	* 푸시 알림 템플릿 수정화면으로 이동 하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushNotfcatnTempltUpdate.do", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String pushTempltUpdateFrm(Model model, PushValueObject pushVo)throws Exception{
		Logger.info("푸시알림  템플릿 수정화면으로 이동 컨트롤러 입니다.");
		pushService.pushNotfcatnTempltUpdate(pushVo);
		
		return "flag";
	}
	/**
	* 푸시 알림 수신자 전체 팝업 조회를 하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushNotfcatnRecpntAllPopup.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushModalAllSearchPopup(Model model)throws Exception{
		Logger.info(null);
		return "custmr/push/PushNotfcatnRecpntAllPopup";
	}
	/**
	* 푸시 알림 수신자 전체 팝업 조회의  임시 테이블을 조회하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushNotfcatnRecpntAllPopupTempTable.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushModalAllSearchPopupTempTable(Model model, PushValueObject pushVo2)throws Exception{
		Logger.info(null);
		List<OrdrValueObject> pushRecpntTempAll = pushService.selectPushRecpntAllTemp(pushVo2);
		
		model.addAttribute("PushRecpntTempAll", pushRecpntTempAll);
		model.addAttribute("pushVo2", pushVo2);
				
		return "custmr/push/PushNotfcatnRecpntAllPopupTempTable";
	}
	/**
	* 푸시 알림 수신자 전체 팝업 조회의  임시 테이블을 고객 수 조회하는 메서드입니다.
	*
	* @param   void
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushModalAllSearchPopupTempTableCount.do", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody 
	public int selectPushModalAllSearchPopupTempTableCount()throws Exception{
		Logger.info(null);

		int custmrCount = pushService.selectPushRecpntAllTempCount();
		return custmrCount;
	}
	/**
	* 푸시 알림 수신자 전체 팝업 조회의  임시 테이블에 추가하는 메서드입니다.
	*
	* @param   String
	* @return  void
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/insertPushRecpntAllTemp.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void insertPushRecpntAllTemp(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		pushService.insertPushRecpntAllTemp(data);
	}
	/**
	* 푸시 알림 수신자 전체 팝업 조회의  임시 테이블을 삭제하는 메서드입니다.
	*
	* @param   String
	* @return  void
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/deletePushNotfcatnRecpntAllPopupTempTable.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void deletePushNotfcatnRecpntAllPopupTempTable(@RequestBody String chkedVal) throws Exception {
		Logger.info(null);
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		pushService.deletePushRecpntTemp(data);
	}
	/**
	* 푸시 알림 수신자 전체 팝업 조회의 테이블을 조회하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushNotfcatnRecpntAllPopupTable.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushModalAllSearchPopupTable(Model model, PushValueObject pushVo)throws Exception{
		Logger.info(null);
		List<OrdrValueObject> pushRecpntAll = pushService.pushRecpntAll(pushVo);
		
		model.addAttribute("PushRecpntAll", pushRecpntAll);
		model.addAttribute("pushVo", pushVo);
				
		return "custmr/push/PushNotfcatnRecpntAllPopupCustmrTable";
	}
	/**
	* 푸시 알림 수신자 카테고리 리스트 팝업 조회를 하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushRecpntCatgrPopup.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushRecpntCatgrPopup(Model model, ProdctValueObject prodctValueObject)throws Exception{
		Logger.info("푸시알림  수신자 카테고리 리스트 팝업 조회 컨트롤러 입니다.");
		String[] topCatgrArray= prodctService.catgrList("기본","");
		String[] midCatgrArray=prodctService.catgrList("대",prodctValueObject.getTopCatgrNme());
		String[] botCatgrArray=prodctService.catgrList("중",prodctValueObject.getMidCatgrNme());
		
		model.addAttribute("ProdctValueObject",prodctValueObject);
		model.addAttribute("topCatgrArray",topCatgrArray);
		model.addAttribute("midCatgrArray",midCatgrArray);
		model.addAttribute("botCatgrArray",botCatgrArray);
		
		model.addAttribute("pageType",1);
		
		return "custmr/push/PushNotfcatnRecpntCatgrPopup";
	}
	/**
	* 푸시 알림 발송내역의 다시 보내기 팝업창을 조회하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushNotfcatnDespRecrdDetlPopup.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushNotfcatnDespRecrdDetlPopup(Model model, PushValueObject pushVo, @RequestParam(value="despRecrdSeq") int despRecrdSeq)throws Exception{
		Logger.info("푸시알림  템플릿 상세 조회 컨트롤러 입니다.");
		pushService.deletePushRecpntAllTemp();
		pushVo.setDespRecrdSeq(despRecrdSeq);
		PushValueObject pushNotfcatnReDesp = pushService.pushNotfcatnReDesp(pushVo);
		
		model.addAttribute("pushNotfcatnReDesp", pushNotfcatnReDesp);
		
		return "custmr/push/PushNotfcatnDespRecrdDetlPopup";
	}
	/**
	* 푸시 알림 발송 내역 조회를 하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushNotfcatnDespRecrdList.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushNotfcatnDespRecrdList(Model model, PushValueObject pushVo)throws Exception{
		Logger.info("푸시 발송 내역 조회 컨트롤러 입니다.");
		List<PushValueObject> pushNotfcatnDespRecrdList = pushService.pushNotfcatnDespRecrdList(pushVo);
		PushValueObject pushNotfcatnDespRecrdListSum = pushService.pushNotfcatnDespRecrdListSum();
		
		model.addAttribute("pushNotfcatnDespRecrdList", pushNotfcatnDespRecrdList);
		model.addAttribute("pushNotfcatnDespRecrdListSum", pushNotfcatnDespRecrdListSum);
		model.addAttribute("pushVo", pushVo);
		
		return "custmr/push/PushNotfcatnDespRecrdList";
	}
	/**
	* 푸시 알림 발송 내역 다시 보내기를 하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	//푸시 발송 내역 다시 보내기
	@RequestMapping(value = "/pushNotfcatnReDesp.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushNotfcatnReDesp(Model model, PushValueObject pushVo)throws Exception{
		Logger.info("푸시 발송 내역 조회 컨트롤러 입니다.");
		PushValueObject pushNotfcatnReDesp = pushService.pushNotfcatnReDesp(pushVo);

		model.addAttribute("pushNotfcatnReDesp", pushNotfcatnReDesp);
		
		return "forward:/pushNotfcatnDespSelectList.do";
	}
	/**
	* 푸시 알림 발송 내역 삭제를 하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushNotfcatnDespRecrdDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public String pushNotfcatnDespRecrdDelete(@RequestBody String chkedVal) throws Exception {
		Logger.info("푸시알림 발송 내역 삭제 컨트롤러 입니다.");
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
			System.out.println(data[i]);
		}
		pushService.pushNotfcatnDespRecrdDelete(data);
		
		return "forward:/pushNotfcatnDespRecrdList.do";
	}
	
	/**
    * 카테고리 변경에 따른 내역을 세션에 저장하는 메서드다.
    *
    * @param   HttpSession, @RequestParam(value="selectedCatgrSeq[]") String[] catgrSeq
    * @return  String
    * @exception  Exception
    */	
	@RequestMapping(value = "/selectPushProdctAdList.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String selectPushProdctAdList(HttpSession pushProdctSession, @RequestParam(value="selectedCatgrSeq[]") String[] catgrSeq) throws Exception {
		System.out.println("ProdctController의  catgrChange() 작동");
		String temp = catgrSeq[0];
		pushProdctSession.setAttribute("catgrSeq",temp);
		return "redirect:/pushProdctAdList.do";
	}
	

	/**
    * 다함께 상품 등록 팝업에서 게시물을 조회하는 메서드다.
    *
    * @param   Model, HttpSession, ProdctValueObject
    * @return  String
    * @exception  Exception
    */
	@RequestMapping(value = "/pushProdctAdList.do", method = {RequestMethod.GET,RequestMethod.POST})	
	public String pushProdctAdList(Model model, HttpSession pushProdctSession,ProdctValueObject prodctValueObject) throws Exception {
		System.out.println("ProdctController의  reltnProdctAdList() 작동"+prodctValueObject.getProdctSechText());
		
		String catgrSeq ="0";
		if(pushProdctSession.getAttribute("catgrSeq")!=null)
		{
			catgrSeq = (String) pushProdctSession.getAttribute("catgrSeq");
		}
		prodctValueObject.setCatgrSeq(catgrSeq);
		List<ProdctValueObject> pushProdctList = prodctService.selProdctList(prodctValueObject);
		model.addAttribute("prodctValueObject",prodctValueObject);
		model.addAttribute("pushProdctList", pushProdctList);

		return "custmr/push/PushChocTable";
	}
	/**
    * 카테고리 리스트를 조회하는 메서드 입니다.
    *
    * @param   Model
    * @return  Object
    * @exception  Exception
    */	
	@RequestMapping(value = "/selectPushCatgrList.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody	
	public Object selectPushCatgrList(Model model) throws Exception {
		System.out.println("ProdctController의  catgrProdctList() 작동");
		List<CatgrValueObject> catgrProdctList = prodctService.catgrProdctList();
		ObjectMapper catgrProdctMapper = new ObjectMapper();
		return catgrProdctMapper.writeValueAsString(catgrProdctList);
	}
	/**
    * 푸시 발송의 최근 내역 리스트를 상세조회하는 메서드 입니다.
    *
    * @param   PushValueObject
    * @return  PushValueObject
    * @exception  Exception
    */	
	@RequestMapping(value = "/pushHistoryDetlRead.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody	
	public PushValueObject selectPushHistoryDetl(PushValueObject pushVo) throws Exception {
		
		return pushService.selectPushHistoryDetl(pushVo.getPushSeq());
	}
	/**
    * 푸시 발송 후 푸시 테이블에 추가하는 메서드 입니다.
    *
    * @param   PushValueObject
    * @return  void
    * @exception  Exception
    */	
	@RequestMapping(value = "/pushHistoryCreate.do", method = {RequestMethod.GET,RequestMethod.POST})
	public void insertPushHistory(PushValueObject pushVo, HttpSession session) throws Exception {
		pushService.insertPushHistory(pushVo);
		LoginValueObject userLoginInfo = (LoginValueObject)session.getAttribute("loginUserInfo");
		pushVo.setEmpSeq(userLoginInfo.getEmpSeq());
		pushService.insertPushDesp(pushVo);
		session.setAttribute("despRecrdSeq", pushVo.getDespRecrdSeq());
	}
	/**
    * 푸시 발송 후 열어본 횟수를 업데이트 하는 메서드 입니다.
    *
    * @param   PushValueObject
    * @return  void
    * @exception  Exception
    */	
	@RequestMapping(value = "/pushOpenDespContUpdate.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public void updatePushOpenDespCont(PushValueObject pushVo) throws Exception {
		pushService.updateOpenPushDespCont(pushVo);
	}
	/**
	* 푸시 알림 수신자 상품 팝업 조회의 테이블을 조회하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushNotfcatnRecpntAllPopupProdctTable.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushRecpntProdctAll(Model model, PushValueObject pushVo)throws Exception{
		Logger.info(null);
		List<OrdrValueObject> pushRecpntProdctAll = pushService.pushRecpntProdctAll(pushVo);
		
		model.addAttribute("PushRecpntAllProdct", pushRecpntProdctAll);
		model.addAttribute("pushVo3", pushVo);
				
		return "custmr/push/PushNotfcatnRecpntAllPopupProdctCustmrTable";
	}
	/**
	* 푸시 알림 수신자 전체 팝업 조회의  임시 테이블을 조회하는 메서드입니다.
	*
	* @param   Model model, PushValueObject pushVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushNotfcatnRecpntAllPopupProdctTempTable.do", method={RequestMethod.GET,RequestMethod.POST})
	public String pushModalAllSearchPopupProdctTempTable(Model model, PushValueObject pushVo2)throws Exception{
		Logger.info(null);
		List<OrdrValueObject> pushRecpntTempAll = pushService.selectPushRecpntAllTemp(pushVo2);
		
		model.addAttribute("PushRecpntTempAll", pushRecpntTempAll);
		model.addAttribute("pushVo2", pushVo2);
				
		return "custmr/push/PushNotfcatnRecpntAllPopupProdctTempTable";
	}
	/**
	* 푸시 알림 수신자 전체 팝업 조회의  임시 테이블을 고객 수 조회하는 메서드입니다.
	*
	* @param   void
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/pushModalAllSearchPopupProdctTempTableCount.do", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody 
	public int selectPushModalAllSearchPopupProdctTempTableCount()throws Exception{
		Logger.info(null);

		int custmrCount = pushService.selectPushRecpntAllTempCount();
		return custmrCount;
	}
}