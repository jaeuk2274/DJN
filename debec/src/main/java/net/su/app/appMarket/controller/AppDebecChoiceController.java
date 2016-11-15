package net.su.app.appMarket.controller;

import java.util.List;

import javax.annotation.Resource;

import net.su.app.appMarket.service.AppDebecChoiceService;
import net.su.logger.Logger;
import net.su.prodct.display.service.FreshService;
import net.su.prodct.display.service.HotdlService;
import net.su.prodct.display.service.PackgService;
import net.su.prodct.display.valueObject.FreshValueObject;
import net.su.prodct.display.valueObject.HotdlValueObject;
import net.su.prodct.display.valueObject.PackgValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 앱 마켓 대백초이스 관련 컨트롤러입니다.
 * 
 * @see   net.su.app.appMarket.controller.AppDebecChoiceController
 * @version  1.0 
 * @ author 시상일, 2016/09/19
 */
@Controller
public class AppDebecChoiceController {
	@Resource
	private HotdlService hotdlService;
	@Resource
	private FreshService freshService;
	@Resource
	private PackgService packgService;
	@Resource
	private AppDebecChoiceService appDebecChoiceService;
	
	/**
	* 앱 신선식품 상품 갯수 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/appFreshtCount.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public int appFreshtCount() throws Exception {
		Logger.info("앱 신선식품 상품 진열 관리 갯수 조회 컨트롤러 가기 전 입니다.");
		int appFreshtCount = appDebecChoiceService.selectAppFreshtCount();
		return appFreshtCount;
	}
	
	/**
	* 앱 핫딜 상품 갯수 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/appHotdltCount.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public int appHotdltCount() throws Exception {
		Logger.info("앱 핫딜 상품 진열 관리 갯수 조회 컨트롤러 가기 전 입니다.");
		int appHotdltCount = appDebecChoiceService.selectAppHotdltCount();
		return appHotdltCount;
	}
	
	/**
	* 앱 패키지 상품 갯수 조회를 하는 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/appPackgCount.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public int appPackgCount() throws Exception {
		Logger.info("앱 패키지 상품 진열 관리 갯수 조회 컨트롤러 가기 전 입니다.");
		int appPackgCount = appDebecChoiceService.selectAppPackgCount();
		return appPackgCount;
	}

	/**
	* 앱 신선식품 상품 목록 조회를 하는 메서드입니다.
	*
	* @param  
	* @return   List<FreshValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/appFreshList.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<FreshValueObject> appFreshList() throws Exception {
		Logger.info("앱 신선식품 상품 목록 조회");
		FreshValueObject freshValueObject = new FreshValueObject();
		List<FreshValueObject> appFreshList = appDebecChoiceService.selectAppFreshList(freshValueObject);
		return appFreshList;
	}

	/**
	* 앱 핫딜 상품 목록 조회를 하는 메서드입니다.
	*
	* @param  
	* @return   List<HotdlValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "appHotdlList.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<HotdlValueObject> appHotdlList() throws Exception {
		Logger.info(null);
		HotdlValueObject hotdlValueObject = new HotdlValueObject();
		List<HotdlValueObject> appHotdlList = appDebecChoiceService.selectAppHotdlList(hotdlValueObject);
		return appHotdlList;
	}
	
	/**
	* 앱 패키지 상품 목록 조회를 하는 메서드입니다.
	*
	* @param  
	* @return  List<PackgValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/appPackgList.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<PackgValueObject> appPackgList() throws Exception {
		Logger.info(null);
		List<PackgValueObject> appPackgList = appDebecChoiceService.selectAppPackgList();
		PackgValueObject packgValueObject = appPackgList.get(0);
		String seeSangIll = packgValueObject.getProdctSeqSet();
		System.out.println("seeSangIll"+seeSangIll);
		return appPackgList;
	}	
	
	/**
	* 앱 패키지 상세보기 조회를 하는 메서드입니다.
	*
	* @param  @RequestParam(value= "packgSeq")
	* @return  PackgValueObject
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value = "/appPackgRead.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public PackgValueObject appPackgRead(@RequestParam(value= "packgSeq") int packgSeq) throws Exception {
		Logger.info("앱 패키지 상세보기 조회");
		 PackgValueObject appPackgRead = appDebecChoiceService.selectAppPackgRead(packgSeq);
		return appPackgRead;
	}	
}
