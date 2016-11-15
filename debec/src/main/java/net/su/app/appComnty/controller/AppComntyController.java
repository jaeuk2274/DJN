package net.su.app.appComnty.controller;

import java.util.List;

import javax.annotation.Resource;

import net.su.app.appComnty.service.AppComntyService;
import net.su.app.appComnty.valueObject.ComntyValueObject;
import net.su.custmr.valueObject.CallOrderValueObject;
import net.su.logger.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppComntyController {

	@Resource 
	private AppComntyService AppComntyService;
	
	/**
    * 담소방 등록 기능 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/appCommtyCreate.do", method = {RequestMethod.GET, RequestMethod.POST} )
	@ResponseBody
	public void communityCreate(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		commtyValueObject.setBoardImg("ddddd");
		commtyValueObject.setCustmrSeq(2);
		
		System.out.println(commtyValueObject.getBoardImg());
		System.out.println(commtyValueObject.getBoardTitl());
		System.out.println(commtyValueObject.getBoardIntrodun());
		System.out.println(commtyValueObject.getBoardPw());
		System.out.println(commtyValueObject.getCustmrSeq());
		
		AppComntyService.communityCreate(commtyValueObject);		
	}

	/**
    * 담소방 담소방리스트 조회 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/appCommunityList.do", method = {RequestMethod.GET, RequestMethod.POST} ) 
	@ResponseBody
	public List<ComntyValueObject> communityList(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		
		List<ComntyValueObject> communityList = AppComntyService.communityList(commtyValueObject);
		
		return communityList;
	}
	
	/**
    * 담소방 즐겨찾는 담소방 리스트 조회 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/appCommunityHotList.do", method = {RequestMethod.GET, RequestMethod.POST} ) 
	@ResponseBody
	public List<ComntyValueObject> communityHotList(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		List<ComntyValueObject> communityHotList = AppComntyService.communityHotList(commtyValueObject);
		
		
		return communityHotList;
	}
	
	/**
    * 담소방 나의 담소방 리스트 조회 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/appCommunityMyList.do", method = {RequestMethod.GET, RequestMethod.POST} ) 
	@ResponseBody
	public List<ComntyValueObject> communityMyList(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		List<ComntyValueObject> communityMyList = AppComntyService.communityMyList(commtyValueObject);
		
		return communityMyList;
	}
	
	/**
    * 담소방 나의 담소방 수정화면 전환 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/appComntyMyUpdateRead.do", method = {RequestMethod.GET, RequestMethod.POST} ) 
	@ResponseBody
	public ComntyValueObject comntyMyUpdateRead(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		int boardSeq=commtyValueObject.getBoardSeq();
		commtyValueObject = AppComntyService.comntyMyUpdateRead(boardSeq);
		
		return commtyValueObject;	
	}
	
	/**
    * 담소방 수정 기능 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/appCommtyUpdate.do", method = {RequestMethod.GET, RequestMethod.POST} )
	@ResponseBody
	public void communityUpdate(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		commtyValueObject.setBoardImg("ddddd");
		
		System.out.println(commtyValueObject.getBoardImg());
		System.out.println(commtyValueObject.getBoardTitl());
		System.out.println(commtyValueObject.getBoardIntrodun());
		System.out.println(commtyValueObject.getBoardPw());
		System.out.println(commtyValueObject.getCustmrSeq());
		
		AppComntyService.communityUpdate(commtyValueObject);		
	}
	
	/**
    * 담소방 즐겨찾기 등록 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/appHotlistCreate.do", method = {RequestMethod.GET, RequestMethod.POST} )
	@ResponseBody
	public void hotlistCreate(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		
		System.out.println(commtyValueObject.getBoardSeq());
		System.out.println(commtyValueObject.getCustmrSeq());
		
		AppComntyService.hotlistCreate(commtyValueObject);		
	}
	
	/**
    * 담소방 즐겨찾기 취소 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/appHotlistCancel.do", method = {RequestMethod.GET, RequestMethod.POST} )
	@ResponseBody
	public void hotlistCancel(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		
		System.out.println(commtyValueObject.getBoardSeq());
		System.out.println(commtyValueObject.getCustmrSeq());
		
		AppComntyService.hotlistCancel(commtyValueObject);		
	}
	
	/**
    * 담소방 상세보기 화면 전환 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/appCommtyRead.do", method = {RequestMethod.GET, RequestMethod.POST} )
	@ResponseBody
	public List<ComntyValueObject> comntyRead(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		
		System.out.println(commtyValueObject.getBoardSeq());
		System.out.println(commtyValueObject.getCustmrSeq());
		
		List<ComntyValueObject> comntyReadList = AppComntyService.comntyReadList(commtyValueObject);
		
		return comntyReadList;
	}

	/**
    * 담소방 상세보기 오른쪽 패널 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/appComntyReadPanel.do", method = {RequestMethod.GET, RequestMethod.POST} )
	@ResponseBody
	public List<ComntyValueObject> comntyReadPanel(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		
		System.out.println(commtyValueObject.getBoardSeq());
		System.out.println(commtyValueObject.getCustmrSeq());
		
		List<ComntyValueObject> comntyReadPanelList = AppComntyService.comntyReadPanelList(commtyValueObject);
		
		return comntyReadPanelList;
	}
		
	/**
    * 담소방 참가자 등록 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/appComntyEntryCreate.do", method = {RequestMethod.GET, RequestMethod.POST} )
	@ResponseBody
	public void comntyEntryCreate(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		
		System.out.println(commtyValueObject.getBoardSeq());
		System.out.println(commtyValueObject.getCustmrSeq());
		AppComntyService.comntyEntryCreate(commtyValueObject);		
	}
		
	/**
    * 담소방 글 등록 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/appTalkCreate.do", method = {RequestMethod.GET, RequestMethod.POST} )
	@ResponseBody
	public void talkCreate(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		
		System.out.println(commtyValueObject.getBoardSeq());
		System.out.println(commtyValueObject.getCustmrSeq());
		System.out.println(commtyValueObject.getTalk());
		AppComntyService.talkCreate(commtyValueObject);		
	}
	
	/**
    * 담소방 나가기 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/appComntyEntryLeave.do", method = {RequestMethod.GET, RequestMethod.POST} )
	@ResponseBody
	public void comntyEntryLeave(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		
		System.out.println(commtyValueObject.getBoardSeq());
		System.out.println(commtyValueObject.getCustmrSeq());
		System.out.println(commtyValueObject.getTalk());
		AppComntyService.comntyEntryLeave(commtyValueObject);		
	}

	/**
    * 담소방 비밀번호 체크하는 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/appComntyPasswordCheck.do", method = {RequestMethod.GET, RequestMethod.POST} ) 
	@ResponseBody
	public ComntyValueObject comntyPasswordCheck(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		commtyValueObject = AppComntyService.comntyPasswordCheck(commtyValueObject);
		System.out.println(commtyValueObject.getBoardPwCheck()+"체크값!!!!!!!!!!");
		return commtyValueObject;	
	}
	
	/**
    * 담소방 관리자 담소방리스트 조회 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	@RequestMapping(value = "/appEmpCommunityList.do", method = {RequestMethod.GET, RequestMethod.POST} ) 
	@ResponseBody
	public List<ComntyValueObject> empCommunityList(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		System.out.println(commtyValueObject.getEmpSeq());
		List<ComntyValueObject> communityList = AppComntyService.empCommunityList(commtyValueObject);
		System.out.println(communityList.get(0).getBoardEntryCount()+"참가자 몇명인지 알아볼수잇슴");
		System.out.println(communityList.get(0).getBoardTitl()+"제목 알아볼수잇슴");
		System.out.println(communityList.get(0).getBoardIntrodun()+"제목 알아볼수잇슴");
		
		return communityList;
	}
		

		
}
