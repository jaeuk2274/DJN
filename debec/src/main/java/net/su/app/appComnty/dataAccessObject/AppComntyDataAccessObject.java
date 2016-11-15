package net.su.app.appComnty.dataAccessObject;

import java.util.List;

import net.su.app.appComnty.valueObject.ComntyValueObject;
import net.su.custmr.valueObject.CallOrderValueObject;
import net.su.logger.Logger;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class AppComntyDataAccessObject extends SqlSessionDaoSupport {

	/**
    * 담소방 등록 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public void communityCreate(ComntyValueObject commtyValueObject) throws Exception {
		getSqlSession().insert("appComntyMapper.communityCreate", commtyValueObject);	
	}

	/**
    * 담소방 담소방리스트 조회 메서드입니다.
    *
    * @param   @RequestParam 
    * @return  
    * @exception  Exception
    */
	public List<ComntyValueObject> communityList(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		List<ComntyValueObject> communityList = getSqlSession().selectList("appComntyMapper.communityList", commtyValueObject);

		return communityList;
	}

	/**
    * 담소방 즐겨찾는 담소방 리스트 조회 메서드입니다.
    *
    * @param   @RequestParam 
    * @return  
    * @exception  Exception
    */
	public List<ComntyValueObject> communityHotList(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		List<ComntyValueObject> communityHotList = getSqlSession().selectList("appComntyMapper.communityHotList", commtyValueObject);

		return communityHotList;
	}

	/**
    * 담소방 나의 담소방 리스트 조회 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public List<ComntyValueObject> communityMyList(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		List<ComntyValueObject> communityMyList = getSqlSession().selectList("appComntyMapper.communityMyList", commtyValueObject);

		return communityMyList;
	}

	/**
	* 담소방 나의 담소방 수정화면 전환 메서드입니다.
	*
	* @param   @RequestParam CommtyValueObject
	* @return  
	* @exception  Exception
	*/
	public ComntyValueObject comntyMyUpdateRead(int boardSeq) throws Exception {
		Logger.info(null);
		ComntyValueObject commtyValueObject = getSqlSession().selectOne("appComntyMapper.comntyMyUpdateRead", boardSeq);

		return commtyValueObject;
	}

	/**
    * 담소방 수정 기능 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public void communityUpdate(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		getSqlSession().update("appComntyMapper.communityUpdate", commtyValueObject);	
		
	}

	/**
    * 담소방 즐겨찾기 등록 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public void hotlistCreate(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		getSqlSession().insert("appComntyMapper.hotlistCreate", commtyValueObject);	
		
	}


	
	/**
    * 담소방 즐겨찾기 취소 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public void hotlistCancel(ComntyValueObject commtyValueObject) throws Exception {
		getSqlSession().delete("appComntyMapper.hotlistCancel", commtyValueObject);	
		
	}

	/**
    * 담소방 상세보기 화면 전환 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public List<ComntyValueObject> comntyReadList(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		List<ComntyValueObject> comntyReadList = getSqlSession().selectList("appComntyMapper.comntyReadList", commtyValueObject);
		
		return comntyReadList;
	}

	/**
    * 담소방 상세보기 오른쪽 패널 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public List<ComntyValueObject> comntyReadPanelList(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		List<ComntyValueObject> comntyReadPanelList = getSqlSession().selectList("appComntyMapper.comntyReadPanelList", commtyValueObject);
		ComntyValueObject hotlistSelect = getSqlSession().selectOne("appComntyMapper.hotlistSelect", commtyValueObject);
		for(int i=0; i<comntyReadPanelList.size(); i++){
		ComntyValueObject comntyValueObject = comntyReadPanelList.get(i);
		System.out.println(hotlistSelect.getHotlistCheck()+"즐겨찾기인지 아닌지 0/1");
		comntyValueObject.setHotlistCheck(hotlistSelect.getHotlistCheck());
		System.out.println(comntyValueObject.getHotlistCheck()+i+"번째 즐겨찾기가 잘 들어갔는지 확인");
		}
		
		return comntyReadPanelList;
	}
		
	/**
    * 담소방 참가자 등록 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public void comntyEntryCreate(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		getSqlSession().insert("appComntyMapper.comntyEntryCreate", commtyValueObject);	
	}
	
	/**
    * 담소방 글 등록 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public void talkCreate(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		getSqlSession().insert("appComntyMapper.talkCreate", commtyValueObject);	
	}

	/**
    * 담소방 나가기 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public void comntyEntryLeave(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		getSqlSession().insert("appComntyMapper.comntyEntryLeave", commtyValueObject);	
	}

	/**
	* 담소방 비밀번호 체크 메서드입니다.
	*
	* @param   @RequestParam CommtyValueObject
	* @return  
	* @exception  Exception
	*/
	public ComntyValueObject comntyPasswordCheck(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		commtyValueObject = getSqlSession().selectOne("appComntyMapper.comntyPwSelect", commtyValueObject);

		return commtyValueObject;
	}

	/**
    * 담소방 관리자 담소방리스트 조회 메서드입니다.
    *
    * @param   @RequestParam CommtyValueObject
    * @return  
    * @exception  Exception
    */
	public List<ComntyValueObject> empCommunityList(ComntyValueObject commtyValueObject) throws Exception {
		Logger.info(null);
		List<ComntyValueObject> communityList = getSqlSession().selectList("appComntyMapper.empCommunityList", commtyValueObject);

		return communityList;
	}



		

}
