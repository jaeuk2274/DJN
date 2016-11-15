package net.su.app.appMyPg.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.su.market.service.DJNService;
import net.su.app.appMyPg.service.AppEmpMyPgService;
import net.su.custmr.valueObject.OrdrValueObject;
import net.su.logger.Logger;
import net.su.login.valueObject.LoginValueObject;
import net.su.market.valueObject.DJNPointValueObject;
import net.su.market.valueObject.DJNValueObject;
import net.su.market.valueObject.EmpValueObject;
import net.su.market.valueObject.RecmndResnValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class AppEmpMyPgController {

	@Resource
	private AppEmpMyPgService appMyPgService;
	
	@Resource
	private DJNService djnService;
	
	@RequestMapping(value = "/empPushCheck.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateEmpPushCheck(@RequestParam(value="empSeq") int empSeq) throws Exception {
		Logger.info(null);
		appMyPgService.updateEmpPushCheck(empSeq);
		String pushCheck = appMyPgService.selectEmpPushCheck(empSeq);
		return pushCheck;
	}
	
	@RequestMapping(value = "/empDjnRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public HashMap<String, Object> selectDjnRead(@RequestParam(value="selectMon") String selectMon) throws Exception {
		Logger.info(null);
		DJNValueObject djnVo = new DJNValueObject();
		DJNValueObject djnVo2 = new DJNValueObject();
		djnVo.setSelectMon(selectMon);
		djnVo2 = djnService.selectDJN(djnVo); //이달의 대장남 정보조회
		System.out.println("selectMon = "+selectMon);
		DJNPointValueObject djnPointPerct =djnService.selectDjnPointPerct(); //대장남 포인트 선정기준 조회
		
		HashMap<String, Object> djnMap = new HashMap<String, Object>();
		djnMap.put("djnVo2", djnVo2);
		djnMap.put("djnPointPerct", djnPointPerct);
		System.out.println("@@@@@@@@@@@@@@@@");
		return djnMap;
	}
	
	@RequestMapping(value = "/recmndResn.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<RecmndResnValueObject> selectRecmndResn(@RequestParam(value="selectMon") String selectMon, @RequestParam(value="empSeq") int empSeq) throws Exception {
		Logger.info(null);
		DJNValueObject djnVo = new DJNValueObject();
		djnVo.setSelectMon(selectMon);
		djnVo.setEmpSeq(empSeq);
		List<RecmndResnValueObject> djnRecmndResn = djnService.selectDjnRecmndResn(djnVo); //이달의 대장남 고객 추천사유 조회
			
		return djnRecmndResn;
	}
	
	@RequestMapping(value = "/ordrStatUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void updateOrdrStat(@RequestParam(value="ordrNumbrSeq") int ordrNumbrSeq, @RequestParam(value="empSeq") int empSeq) throws Exception {
		Logger.info(null);
		appMyPgService.updateOrdrStat(ordrNumbrSeq, empSeq);
	}
	
	@RequestMapping(value = "/empRecmndCreate.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public int insertEmpRecmnd(@RequestParam(value="getEmpSeq") int getEmpSeq, @RequestParam(value="giveEmpSeq") int giveEmpSeq) throws Exception {
		Logger.info(null);
		LoginValueObject loginUser = new LoginValueObject();
		loginUser.setEmpSeq(giveEmpSeq);
		int memRecmnChck = djnService.memRecmnChck(loginUser);
		if (memRecmnChck == 1){
			return 1;
		}
		else{
			HashMap<String, Integer> empSeqMap = new HashMap<String, Integer>();
			empSeqMap.put("getEmpSeq", getEmpSeq);
			empSeqMap.put("giveEmpSeq", giveEmpSeq);
			appMyPgService.insertEmpRecmnd(empSeqMap);
			return 0;
		}		
	}
	
	@RequestMapping(value = "/empRecmndList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<EmpValueObject> selectEmpRecmndList(@RequestParam(value="empSeq") int empSeq) throws Exception {
		Logger.info(null);
		List<EmpValueObject> empRecmndList = appMyPgService.selectEmpRecmndList(empSeq);
		return empRecmndList;
	}
	
	@RequestMapping(value = "/ordrCountList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public HashMap<String, Object> selectOrdrCountList(@RequestParam(value="empSeq") int empSeq) throws Exception {
		Logger.info(null);
		List<OrdrValueObject> ordrCountList = appMyPgService.selectOrdrCountList();
		String empNme = appMyPgService.selectEmpNme(empSeq);
		String pushCheck = appMyPgService.selectEmpPushCheck(empSeq);
		HashMap<String, Object> ordrMap = new HashMap<String, Object>();
		ordrMap.put("ordrCountList", ordrCountList);
		ordrMap.put("empNme", empNme);
		ordrMap.put("pushCheck", pushCheck);
		return ordrMap;
	}
	
	@RequestMapping(value = "/empShipngList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<OrdrValueObject> selectEmpShipngList(@RequestParam(value="ordrStatSeq") int ordrStatSeq) throws Exception {
		Logger.info(null);
		System.out.println("ordrStatSeq = " + ordrStatSeq);
		List<OrdrValueObject> empShipngList = appMyPgService.selectEmpShipngList(ordrStatSeq);
		return empShipngList;
	}
	
	@RequestMapping(value = "/empDahamgaeShipngList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<OrdrValueObject> selectEmpDahamgaeShipngList(@RequestParam(value="ordrStatSeq") int ordrStatSeq) throws Exception {
		Logger.info(null);
		System.out.println("ordrStatSeq = " + ordrStatSeq);
		List<OrdrValueObject> empDahamgaeShipngList = appMyPgService.selectEmpDahamgaeShipngList(ordrStatSeq);
		return empDahamgaeShipngList;
	}
	
	@RequestMapping(value = "/ordrCancelList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<OrdrValueObject> selectOrdrCancelList() throws Exception {
		Logger.info(null);
		List<OrdrValueObject> ordrCancelList = appMyPgService.selectOrdrCancelList();
		return ordrCancelList;
	}
	
	@RequestMapping(value = "/dahamgaeOrdrCancelList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<OrdrValueObject> selectDahamgaeOrdrCancelList() throws Exception {
		Logger.info(null);
		List<OrdrValueObject> ordrCancelList = appMyPgService.selectDahamgaeOrdrCancelList();
		return ordrCancelList;
	}
	
	@RequestMapping(value = "/empRealTimeOrdrList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<OrdrValueObject> selectEmpRealTimeOrdrList() throws Exception {
		Logger.info(null);
		List<OrdrValueObject> empRealTimeOrdrList = appMyPgService.selectEmpRealTimeOrdrList();
		return empRealTimeOrdrList;
	}
	
	@RequestMapping(value = "/empDahamgaeRealTimeOrdrList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<OrdrValueObject> selectEmpDahamgaeRealTimeOrdrList() throws Exception {
		Logger.info(null);
		List<OrdrValueObject> empRealTimeOrdrList = appMyPgService.selectEmpDahamgaeRealTimeOrdrList();
		return empRealTimeOrdrList;
	}
	
	@RequestMapping(value = "/empOrdrRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public HashMap<String, Object> selectEmpOrdrRead(@RequestParam(value="ordrNumbrSeq") int ordrNumbrSeq, @RequestParam(value="ordrType") int ordrType) throws Exception {
		Logger.info(null);
		OrdrValueObject empOrdrVo = appMyPgService.selectEmpOrdrRead(ordrNumbrSeq, ordrType);
		List<OrdrValueObject> ordrProdctList = appMyPgService.selectOrdrCancelProdctList(ordrNumbrSeq);
		
		HashMap<String, Object> ordrMap = new HashMap<String, Object>();
		ordrMap.put("empOrdrVo", empOrdrVo);
		ordrMap.put("ordrProdctList", ordrProdctList);
		return ordrMap;
	}
	
	@RequestMapping(value = "/empOrdrCancelRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public HashMap<String, Object> selectEmpOrdrCancelRead(@RequestParam(value="ordrNumbrSeq") int ordrNumbrSeq) throws Exception {
		Logger.info(null);
		OrdrValueObject empOrdrVo = appMyPgService.selectEmpOrdrCancelRead(ordrNumbrSeq);
		List<OrdrValueObject> ordrCancelProdctList = appMyPgService.selectOrdrCancelProdctList(ordrNumbrSeq);
		HashMap<String, Object> ordrMap = new HashMap<String, Object>();
		ordrMap.put("empOrdrVo", empOrdrVo);
		ordrMap.put("ordrCancelProdctList", ordrCancelProdctList);
		return ordrMap;
	}

	@RequestMapping(value = "/empDahamgaeOrdrCancelRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public HashMap<String, Object> selectEmpDahamgaeOrdrCancelRead(@RequestParam(value="ordrNumbrSeq") int ordrNumbrSeq) throws Exception {
		Logger.info(null);
		OrdrValueObject empOrdrVo = appMyPgService.selectDahamgaeOrdrCancelRead(ordrNumbrSeq);
		List<OrdrValueObject> ordrCancelProdctList = appMyPgService.selectDahamgaeOrdrCancelProdctList(ordrNumbrSeq);
		HashMap<String, Object> ordrMap = new HashMap<String, Object>();
		ordrMap.put("empOrdrVo", empOrdrVo);
		ordrMap.put("ordrCancelProdctList", ordrCancelProdctList);
		return ordrMap;
	}
	
	@RequestMapping(value = "/empInfoRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public EmpValueObject selectEmpInfo(@RequestParam(value="empSeq") int empSeq) throws Exception {
		Logger.info(null);
		EmpValueObject selectEmpInfo = appMyPgService.selectEmpInfo(empSeq);
		return selectEmpInfo;
	}
	
	@RequestMapping(value = "/empInfoConfirmRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public EmpValueObject selectEmpInfoCount(EmpValueObject empVo) throws Exception {
		Logger.info(null);
		EmpValueObject selectEmpInfo = appMyPgService.selectEmpInfoCount(empVo);
		return selectEmpInfo;
	}
	
	@RequestMapping(value = "/empInfoUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void updateEmpInfo(EmpValueObject empVo) throws Exception {
		Logger.info(null);
		appMyPgService.updateEmpInfo(empVo);
	}

	@RequestMapping(value = "/empPwUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void updateEmpPw(EmpValueObject empVo) throws Exception {
		Logger.info(null);
		appMyPgService.updateEmpPw(empVo);
	}
	
	@RequestMapping(value = "/ordrImgUpload.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String ordrImgUpload(OrdrValueObject ordrVo, MultipartHttpServletRequest request) throws Exception {
		Logger.info(null);
		String ordrImg = appMyPgService.ordrImgUpload(request, ordrVo);
		
		return ordrImg;
	}
}
