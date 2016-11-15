/**
 * 앱 메인 컨트롤러입니다.
 * 
 * @see   net.su.app.appMain.controller
 * @version  1.0 
 * @ author 김대현, 2016/09/22
 */

package net.su.app.appMain.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.su.app.appMain.service.AppMainService;
import net.su.app.appRecp.valueObject.AppRecpValueObject;
import net.su.custmr.valueObject.CustmrValueObject;
import net.su.market.service.DJNService;
import net.su.market.valueObject.DJNValueObject;
import net.su.market.valueObject.EmpValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppMainController {

	@Resource
	private AppMainService appMainService;
	@Resource
	private DJNService djnService;
	
// 이달의 대장남
	@RequestMapping(value = "/AppThisDJNList.do", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public DJNValueObject appSelectThisDJN(DJNValueObject djnVo) throws Exception{
//		Calendar cal = Calendar.getInstance(); 					//객체 생성 및 현재 일시분초...셋팅
//		String ntime = String.valueOf(cal.get(Calendar.YEAR))+"-";		// 올해 년도
//		String month = String.valueOf(cal.get(Calendar.MONTH) + 1);		// 이번달
//		if(month.length() == 1 ){		// 이번달이 1~9월이면 두자리로 변경
//			 month = "0" + month; 
//		}
//		ntime += month;		// ntime은 "2016-09" 라는 값이 들어감
//		System.out.println(ntime);
//		djnVo.setSelectMon(ntime);
		System.out.println("1111111  이달의 대장남 "+djnVo.getSelectMon());
		djnVo = djnService.selectDJN(djnVo); //이달의 대장남 정보조회
		System.out.println("2222222  이달의 대장남 "+djnVo.getEmpSeq());
		System.out.println("2222222  이달의 대장남 "+djnVo.getEmpNme());
		System.out.println("2222222  이달의 대장남 "+djnVo.getEmpImg());
		System.out.println("2222222  이달의 대장남 "+djnVo.getDat());


		return djnVo;
	}
	
// 역대 대장남
//	@RequestMapping(value = "/AppEverDJNList.do", method=RequestMethod.POST)
//	@ResponseBody
//	public DJNValueObject appSelectEverDJN(DJNValueObject djnVo) throws Exception{
//		System.out.println("1111111 역대대장남 "+djnVo.getSelectMon());
//		List<DJNValueObject> everDJNList = appMainService.selectDJN(djnVo); //이달의 대장남 정보조회
//		djnVo = djnService.selectDJN(djnVo); //이달의 대장남 정보조회
//		System.out.println("22222222 역대대장남");
//		return djnVo;
//	}
	
// 직원 목록 조회
	@RequestMapping(value = "/AppEmpList.do", method=RequestMethod.POST)
	@ResponseBody
	public List<EmpValueObject> appSelectEmpList() throws Exception{
		List<EmpValueObject> empList = appMainService.appSelectEmpList();
		return empList;
	}
	
// 이달의 대장남 투표 권한(고객, 월 한번만 가능)
	@RequestMapping(value = "/AppCustmrDJNVotingAthrty.do", method=RequestMethod.POST)
	@ResponseBody
	public int appSelectCustmrDJNVotingAthrty(CustmrValueObject custmrVo) throws Exception{
		System.out.println("스프링왔다");
		int custmrSeq=custmrVo.getCustmrSeq();
		int thisMonthCount = appMainService.appSelectCustmrDJNVotingAthrty(custmrSeq);
		return thisMonthCount;
	}

// 이달의 대장남 투표권한(직원, 월 한번만 가능)
	@RequestMapping(value = "/AppEmpDJNVotingAthrty.do", method = RequestMethod.POST)
	@ResponseBody
	public int appSelectEmpDJNVotingAthrty(EmpValueObject empVo) throws Exception{
		System.out.println(empVo.getEmpSeq()+"  :  empSeq 값");
		int empSeq = empVo.getEmpSeq();
		int thisMonthCount = appMainService.appSelectEmpDJNVotingAthrty(empSeq);
		return thisMonthCount;
	}

// 이달의 대장남 투표(고객)
	@RequestMapping(value = "/AppCustmrDJNVoting.do", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void appCustmrDJNVoting(EmpValueObject empVo, CustmrValueObject custmrVo) throws Exception{
		System.out.println("직원seq: "+empVo.getEmpSeq());
		System.out.println("고객seq: "+custmrVo.getCustmrSeq());
		
		Map votingMap = new HashMap();
		votingMap.put("empSeq", empVo.getEmpSeq());
		votingMap.put("custmrSeq", custmrVo.getCustmrSeq());
		
		appMainService.appCustmrDJNVoting(votingMap);
	}
	
// 이달의 대장남 투표(직원)
	@RequestMapping(value = "/AppEmpDJNVoting.do", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void appEmpDJNVoting(EmpValueObject empVo) throws Exception{
		Map votingMap = new HashMap();
		votingMap.put("giveEmpSeq", empVo.getGiveEmpSeq());
		votingMap.put("getEmpSeq", empVo.getGetEmpSeq());
		
		appMainService.appEmpDJNVoting(votingMap);
	}
	
// 랜덤 레시피 조회
	@RequestMapping(value = "/AppRandomBoxDrawing.do", method=RequestMethod.POST)
	@ResponseBody
	public List<AppRecpValueObject> appSelectRecpList() throws Exception{
		System.out.println("111111111111111");
		List<AppRecpValueObject> recpList = appMainService.appSelectRecpList();
		return recpList;
	}
	
// 레시피 상세보기
//	@RequestMapping(value = "/AppRecpRead.do", method=RequestMethod.POST)
//	@ResponseBody
//	public AppRecpValueObject appSelectRecpRead() throws Exception{
//		appMainService.appSelectRecpRead();
//		return "";
//	}
}
