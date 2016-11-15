/**
 * 앱 레시피 관리 컨트롤러입니다.
 * 
 * @see   net.su.app.appRecp.controller.AppRecpController
 * @version  1.0 
 * @ author 하원식, 2016/10/08
 */
package net.su.app.appRecp.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.su.app.appRecp.service.AppRecpService;
import net.su.app.appRecp.valueObject.AppRecpValueObject;
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
public class AppRecpController {
	


	@Resource
	private  AppRecpService appRecpService;
	
	// AppRecpServiceImpl 바로가기

	/**
    * 레시피 리스트를 조회해주는 메서드 입니다.
    *
    * @param   AppRecpValueObject, @RequestParam(value="memSeq") int memSeq, @RequestParam(value="memType") int memType
    * @return  List<AppRecpValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/appRecpList.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<AppRecpValueObject> recpList(AppRecpValueObject appRecpValueObject, @RequestParam(value="memSeq") int memSeq, @RequestParam(value="memType") int memType) throws Exception {
		Logger.info(null);
		appRecpValueObject.setMemSeq(memSeq);
		appRecpValueObject.setMemType(memType);
		List<AppRecpValueObject> recpList = appRecpService.recpList(appRecpValueObject);
	    return recpList;
	}
	
	/**
    * 레시피의 좋아요를 처리해주는 메서드 입니다.
    *
    * @param   AppRecpValueObject appRecpValueObject, @RequestParam(value="recpSeq") int recpSeq,  @RequestParam(value="memSeq") int memSeq, @RequestParam(value="memType"
    * @return  AppRecpValueObject
    * @exception  Exception
    */
	@RequestMapping(value = "/appRecpLikeProcessing.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public AppRecpValueObject recpLikeProcessing(AppRecpValueObject appRecpValueObject, @RequestParam(value="recpSeq") int recpSeq,  @RequestParam(value="memSeq") int memSeq, @RequestParam(value="memType") int memType) throws Exception {
		Logger.info(null);
		appRecpValueObject.setMemSeq(memSeq);
		appRecpValueObject.setMemType(memType);
		appRecpValueObject.setRecpSeq(recpSeq);
		appRecpService.recpLikeProcessing(appRecpValueObject);
		appRecpValueObject = appRecpService.recpRead(appRecpValueObject);
	    return appRecpValueObject;
	}
	
	/**
    * 레시피를 등록해주는 메서드 입니다.
    *
    * @param   @RequestParam(value="recpSeq") int recpSeq,@RequestParam(value="replyCnt") String replyCnt, @RequestParam(value="memSeq") int memSeq, @RequestParam(value="memType") int memType
    * @return  Map<String, Object>
    * @exception  Exception
    */
	@RequestMapping(value = "/appRecpReplyInsert.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> recpReplyInsert(@RequestParam(value="recpSeq") int recpSeq,@RequestParam(value="replyCnt") String replyCnt, @RequestParam(value="memSeq") int memSeq, @RequestParam(value="memType") int memType) throws Exception {
		Logger.info(null);
		AppRecpValueObject appRecpValueObject = new AppRecpValueObject();
		appRecpValueObject.setMemSeq(memSeq);
		appRecpValueObject.setMemType(memType);
		appRecpValueObject.setRecpSeq(recpSeq);
		appRecpValueObject.setReplyCnt(replyCnt);
		appRecpService.recpReplyInsert(appRecpValueObject);
		Map<String, Object> recpReadMap = new HashMap <String, Object>();
		AppRecpValueObject recpRead = appRecpService.recpRead(appRecpValueObject);
		List<AppRecpValueObject> recpReplyList = appRecpService.recpReplyList(appRecpValueObject);

		recpReadMap.put("recpRead", recpRead);
		recpReadMap.put("recpReplyList", recpReplyList);
	    return recpReadMap;
	}
	
	/**
    * 레시피를 상세보기 해주는 메서드 입니다.
    *
    * @param   @RequestParam(value="recpSeq") int recpSeq,@RequestParam(value="replyCnt") String replyCnt, @RequestParam(value="memSeq") int memSeq, @RequestParam(value="memType") int memType
    * @return  Map<String, Object>
    * @exception  Exception
    */
	@RequestMapping(value = "/appRecpRead.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> recpRead(@RequestParam(value="recpSeq") int recpSeq, AppRecpValueObject appRecpValueObject, @RequestParam(value="memSeq") int memSeq, @RequestParam(value="memType") int memType) throws Exception {
		Logger.info(null);
		appRecpValueObject.setMemSeq(memSeq);
		appRecpValueObject.setMemType(memType);
		
		Map<String, Object> recpReadMap = new HashMap <String, Object>();


		AppRecpValueObject recpRead = appRecpService.recpRead(appRecpValueObject);
		List<AppRecpValueObject> recpReplyList = appRecpService.recpReplyList(appRecpValueObject);
		List<AppRecpValueObject> recpProdctList = appRecpService.recpProdctList(appRecpValueObject);

		recpReadMap.put("recpRead", recpRead);
		recpReadMap.put("recpReplyList", recpReplyList);
		recpReadMap.put("recpProdctList", recpProdctList);
	    return recpReadMap;
	}
	
	/**
    * 레시피의 연관 상품을 검색해주는 메서드 입니다.
    *
    * @param   @RequestParam(value="recpSechText") String recpSechText) int memType
    * @return  List<AppRecpValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/appRecpProdctSearch.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<AppRecpValueObject> appRecpProdctSearch(@RequestParam(value="recpSechText") String recpSechText) throws Exception {
		Logger.info(null);

		List<AppRecpValueObject> appRecpProdctSearch = appRecpService.appRecpProdctSearch(recpSechText);

	    return appRecpProdctSearch;
	}
	
	/**
    * 레시피의 연관 상품 리스트를 조회해주는 메서드 입니다.
    *
    * @param   @RequestParam(value="recpProdct") long[] recpProdct
    * @return  Map<String, Object>
    * @exception  Exception
    */
	@RequestMapping(value = "/appRecpProdctList.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> appRecpProdctList(@RequestParam(value="recpProdct") long[] recpProdct) throws Exception {
		Logger.info(null);
		
		Map<String, Object> recpReadMap = new HashMap <String, Object>();
		
		AppRecpValueObject appRecpValueObject = new AppRecpValueObject();
		
		List<AppRecpValueObject> recpList = appRecpService.recpList(appRecpValueObject);
		List<AppRecpValueObject> appRecpProdctList = appRecpService.appRecpProdctList(recpProdct);

		recpReadMap.put("recpListSize", recpList.size());
		recpReadMap.put("appRecpProdctList", appRecpProdctList);
	    return recpReadMap;
	}

	/**
    * 레시피의 사진을 업로드 해주는 메서드 입니다.
    *
    * @param   MultipartHttpServletRequest request, AppRecpValueObject appRecpValueObject
    * @return  Map<String, Object>
    * @exception  Exception
    */
	@RequestMapping(value = "/appRecpFileUpload.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> recpTempFileUpload(MultipartHttpServletRequest request, AppRecpValueObject appRecpValueObject) throws Exception {
		Logger.info(null);
		Map<String, Object> recpMap = new HashMap <String, Object>();
		System.out.println(appRecpValueObject.getTempNum());
		String num = appRecpService.recpTempFileUpload(request, appRecpValueObject);

		String imgPath ="resources/image/recp/temp/"+num+"/main.jpg";

		recpMap.put("imgPath", imgPath);
		recpMap.put("num", num);
	    return recpMap;
	}
	
	/**
    * 레시피를 등록 시켜주는 메서드 입니다.
    *
    * @param   @RequestParam(value="recpTitl") String recpTitl, @RequestParam(value="recpCnt") String recpCnt , @RequestParam(value="recpProdct") long[] recpProdct, @RequestParam(value="memSeq") int memSeq, @RequestParam(value="memType") int memType
    * @return  int
    * @exception  Exception
    */
	@RequestMapping(value = "/appRecpInsert.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public int appRecpInsert(@RequestParam(value="recpTitl") String recpTitl, @RequestParam(value="recpCnt") String recpCnt , @RequestParam(value="recpProdct") long[] recpProdct, @RequestParam(value="memSeq") int memSeq, @RequestParam(value="memType") int memType) throws Exception {
		Logger.info(null);
		AppRecpValueObject appRecpValueObject = new AppRecpValueObject();
		appRecpValueObject.setRecpTitl(recpTitl);
		appRecpValueObject.setRecpCnt(recpCnt);
		appRecpValueObject.setMemSeq(memSeq);
		appRecpValueObject.setMemType(memType);
		System.out.println(appRecpValueObject.getTempNum());

		int recpSeq = appRecpService.appRecpInsert(appRecpValueObject, recpProdct);

	    return recpSeq;
	}
	
	/**
    * 레시피를 삭제시켜주는 메서드 입니다.
    *
    * @param   @RequestParam(value="recpSeq") int recpSeq
    * @return  String
    * @exception  Exception
    */
	@RequestMapping(value = "/appRecpDelete.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String appRecpDelete(@RequestParam(value="recpSeq") int recpSeq) throws Exception {
		Logger.info(null);
		AppRecpValueObject appRecpValueObject = new AppRecpValueObject();
		appRecpValueObject.setRecpSeq(recpSeq);
		appRecpService.appRecpDelete(appRecpValueObject);

	    return "dummy";
	}
	
	/**
    * 레시피를 수정시켜주는 메서드 입니다.
    *
    * @param   @RequestParam(value="recpSeq") int recpSeq, @RequestParam(value="recpTitl") String recpTitl, @RequestParam(value="recpCnt") String recpCnt , @RequestParam(value="recpProdct") long[] recpProdct, @RequestParam(value="memSeq") int memSeq, @RequestParam(value="memType") int memType
    * @return  String
    * @exception  Exception
    */
	@RequestMapping(value = "/appRecpUpdate.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String appRecpUpdate(@RequestParam(value="recpSeq") int recpSeq, @RequestParam(value="recpTitl") String recpTitl, @RequestParam(value="recpCnt") String recpCnt , @RequestParam(value="recpProdct") long[] recpProdct, @RequestParam(value="memSeq") int memSeq, @RequestParam(value="memType") int memType) throws Exception {
		Logger.info(null);
		AppRecpValueObject appRecpValueObject = new AppRecpValueObject();
		appRecpValueObject.setRecpTitl(recpTitl);
		appRecpValueObject.setRecpCnt(recpCnt);
		appRecpValueObject.setRecpSeq(recpSeq);
		appRecpValueObject.setMemSeq(memSeq);
		appRecpValueObject.setMemType(memType);
		System.out.println(appRecpValueObject.getTempNum());

		appRecpService.appRecpUpdate(appRecpValueObject, recpProdct);

	    return "recpSeq";
	}
	
	/**
    * 레시피의 연관 상품을 카트에 등록시켜주는 메서드 입니다.
    *
    * @param   @RequestParam(value="chkedVar") long[] prodctSeqArray, @RequestParam(value="memSeq") int memSeq
    * @return  String
    * @exception  Exception
    */
	@RequestMapping(value = "/appRecpProdctCartInsert.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String appRecpProdctCartInsert(@RequestParam(value="chkedVar") long[] prodctSeqArray, @RequestParam(value="memSeq") int memSeq) throws Exception {
		Logger.info(null);

		appRecpService.appRecpProdctCartInsert(memSeq, prodctSeqArray);
		return "recpSeq";
	}
	

	/**
    * 레시피의 연관 상품을 카트에 등록시켜주는 메서드 입니다.
    *
    * @param   @RequestParam(value="recpReplySeq") int recpReplySeq, @RequestParam(value="recpSeq") int recpSeq
    * @return  Map<String, Object>
    * @exception  Exception
    */
	@RequestMapping(value = "/appRecpReplyDelete.do", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> appRecpReplyDelete(@RequestParam(value="recpReplySeq") int recpReplySeq, @RequestParam(value="recpSeq") int recpSeq) throws Exception {
		Logger.info(null);
		AppRecpValueObject appRecpValueObject = new AppRecpValueObject();
		appRecpValueObject.setRecpReplySeq(recpReplySeq);
		appRecpValueObject.setRecpSeq(recpSeq);

		appRecpService.appRecpReplyDelete(appRecpValueObject);
		
		Map<String, Object> recpReadMap = new HashMap <String, Object>();
		
		AppRecpValueObject recpRead = appRecpService.recpRead(appRecpValueObject);
		List<AppRecpValueObject> recpReplyList = appRecpService.recpReplyList(appRecpValueObject);
		
		recpReadMap.put("recpRead", recpRead);
		recpReadMap.put("recpReplyList", recpReplyList);
		return recpReadMap;
	}
}
