package net.su.app.appComnty.service;

import java.util.List;

import net.su.app.appComnty.valueObject.ComntyValueObject;

public interface AppComntyService {

	/**
    * 담소방 등록 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public void communityCreate(ComntyValueObject commtyValueObject) throws Exception;

	/**
    * 담소방 담소방리스트 조회 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public List<ComntyValueObject> communityList(ComntyValueObject commtyValueObject) throws Exception;

	/**
    * 담소방 즐겨찾는 담소방 리스트 조회 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public List<ComntyValueObject> communityHotList(ComntyValueObject commtyValueObject) throws Exception;

	/**
    * 담소방 나의 담소방 리스트 조회 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public List<ComntyValueObject> communityMyList(ComntyValueObject commtyValueObject) throws Exception;

	/**
    * 담소방 나의 담소방 수정화면 전환 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public ComntyValueObject comntyMyUpdateRead(int boardSeq) throws Exception;

	/**
    * 담소방 수정 기능 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public void communityUpdate(ComntyValueObject commtyValueObject) throws Exception;

	/**
    * 담소방 즐겨찾기 등록 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public void hotlistCreate(ComntyValueObject commtyValueObject) throws Exception;

	/**
    * 담소방 즐겨찾기 취소 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public void hotlistCancel(ComntyValueObject commtyValueObject) throws Exception;

	/**
    * 담소방 상세보기 화면 전환 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public List<ComntyValueObject> comntyReadList(ComntyValueObject commtyValueObject) throws Exception;

	/**
    * 담소방 상세보기 오른쪽 패널 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public List<ComntyValueObject> comntyReadPanelList(ComntyValueObject commtyValueObject)  throws Exception;
		
	/**
    * 담소방 참가자 등록 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public void comntyEntryCreate(ComntyValueObject commtyValueObject) throws Exception;
	
	/**
    * 담소방 글 등록 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public void talkCreate(ComntyValueObject commtyValueObject) throws Exception;

	/**
    * 담소방 나가기 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public void comntyEntryLeave(ComntyValueObject commtyValueObject) throws Exception;

	/**
    * 담소방 비밀번호 체크하는 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public ComntyValueObject comntyPasswordCheck(ComntyValueObject commtyValueObject) throws Exception;

	/**
    * 담소방 관리자 담소방리스트 조회 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public List<ComntyValueObject> empCommunityList(ComntyValueObject commtyValueObject) throws Exception;



}
