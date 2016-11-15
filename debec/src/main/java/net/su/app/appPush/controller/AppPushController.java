package net.su.app.appPush.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.su.app.appMyPg.service.AppEmpMyPgService;
import net.su.app.appPush.service.AppPushService;
import net.su.app.appPush.valueObject.AppPushValueObject;
import net.su.custmr.service.PushService;
import net.su.custmr.valueObject.CustmrValueObject;
import net.su.custmr.valueObject.PushValueObject;
import net.su.market.valueObject.EmpValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

@Controller
public class AppPushController {
	@Resource
	private AppEmpMyPgService appMyPgService;
	
	@Resource
	private AppPushService appPushService;
	
	@Resource
	private PushService pushService;
	
	@RequestMapping(value = "/pushOrdrStat.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void pushOrdrStat(AppPushValueObject appPushVo, @RequestParam(value="ordrNumbrSeq") int ordrNumbrSeq, @RequestParam(value="ordrType") String ordrType) throws Exception {
		try {
			// TODO Auto-generated method stub
			CustmrValueObject custmrInfo = appMyPgService.selectCustmrInfo(ordrNumbrSeq);
			if(custmrInfo.getPushCheck() == 'Y'){
				int custmrSeq = custmrInfo.getCustmrSeq();
				
				Sender sender = new Sender("AIzaSyDkvL2tbeYw-3wdVDCHd_1lRJJF6_9ld30"); // 서버 API Key 입력
				String regId =  appPushService.selectCusmtrToken(custmrSeq); // 단말기 RegID 입력
				CustmrValueObject custmrVo = appPushService.selectCustmrRead(custmrSeq); //고객 id, pw 조회
				String custmrId = custmrVo.getCustmrId();
				String custmrPw = custmrVo.getCustmrPw();
				
				System.out.println(regId);
				String type = appPushVo.getType();
				String sendTlt = appPushVo.getSendTitl();
		        String sendMsg = appPushVo.getSendMesg();
				
		        Message message = new Message.Builder()
		        .collapseKey("collapseKey")
				.timeToLive(3000) //메시지가 살아있는 시간(초단위)
				.delayWhileIdle(false) //true면 화면이 꺼진 상태일 때 수신이 안됨
				.addData("title", sendTlt)
		        .addData("message", sendMsg)
		        .addData("ordrNumbrSeq", String.valueOf(ordrNumbrSeq))
		        .addData("ordrType", ordrType)
		        .addData("custmrSeq", String.valueOf(custmrSeq))
		        .addData("type",type)
		        .addData("custmrSeq", String.valueOf(custmrSeq))
		        .addData("custmrId", custmrId)
		        .addData("custmrPw", custmrPw)
		        .build();
				List<String> list = new ArrayList<String>();
				list.add(regId);
				MulticastResult multiResult;
			
				multiResult = sender.send(message, list, 5);
				if (multiResult != null) {
						List<Result> resultList = multiResult.getResults();
						for (Result result : resultList) {
							System.out.println(result.getMessageId());
						}
				}
			}
			else{
				return;
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/pushRealTiemOrdrList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void pushRealTiemOrdrList(AppPushValueObject appPushVo) throws Exception {
		try {
			// TODO Auto-generated method stub			
			Sender sender = new Sender("AIzaSyDkvL2tbeYw-3wdVDCHd_1lRJJF6_9ld30"); // 서버 API Key 입력

			String type = appPushVo.getType();
			String sendTlt = appPushVo.getSendTitl();
	        String sendMsg = appPushVo.getSendMesg();
			
	        Message message = new Message.Builder()
//	        .collapseKey("collapseKey")
			.timeToLive(3000) //메시지가 살아있는 시간(초단위)
			.delayWhileIdle(false) //true면 화면이 꺼진 상태일 때 수신이 안됨
			.addData("title", sendTlt)
	        .addData("message", sendMsg)
	        .addData("type",type)
	        .build();
	        List<String> regIdList = new ArrayList<String>();
			regIdList = appPushService.selectEmpToken(); // 관리자 단말기 RegID 리스트

			for(int i=0; i<regIdList.size(); i++)
				System.out.println(regIdList.get(i));
			
			
			MulticastResult multiResult;
		
			multiResult = sender.send(message, regIdList, 5);
			if (multiResult != null) {
					List<Result> resultList = multiResult.getResults();
					for (Result result : resultList) {
						System.out.println(result.getMessageId());
					}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/pushOrdrImgUpload.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void pushOrdrImgUpload(AppPushValueObject appPushVo, @RequestParam(value="ordrNumbrSeq") int ordrNumbrSeq, @RequestParam(value="ordrType") String ordrType) throws Exception {
		try {
			// TODO Auto-generated method stub
			CustmrValueObject custmrInfo = appMyPgService.selectCustmrInfo(ordrNumbrSeq);
			if (custmrInfo.getPushCheck() == 'Y'){
				int custmrSeq = custmrInfo.getCustmrSeq();
				
				Sender sender = new Sender("AIzaSyDkvL2tbeYw-3wdVDCHd_1lRJJF6_9ld30"); // 서버 API Key 입력
				String regId =  appPushService.selectCusmtrToken(custmrSeq); // 단말기 RegID 입력
				CustmrValueObject custmrVo = appPushService.selectCustmrRead(custmrSeq); //고객 id, pw 조회
				String custmrId = custmrVo.getCustmrId();
				String custmrPw = custmrVo.getCustmrPw();
				
				System.out.println(regId);
				String type = appPushVo.getType();
				String sendTlt = appPushVo.getSendTitl();
		        String sendMsg = appPushVo.getSendMesg();
				
		        Message message = new Message.Builder()
		        .collapseKey("collapseKey")
				.timeToLive(3000) //메시지가 살아있는 시간(초단위)
				.delayWhileIdle(false) //true면 화면이 꺼진 상태일 때 수신이 안됨
				.addData("title", sendTlt)
		        .addData("message", sendMsg)
		        .addData("ordrNumbrSeq", String.valueOf(ordrNumbrSeq))
		        .addData("ordrType", ordrType)
		        .addData("custmrSeq", String.valueOf(custmrSeq))
		        .addData("type",type)
		        .addData("custmrSeq", String.valueOf(custmrSeq))
		        .addData("custmrId", custmrId)
		        .addData("custmrPw", custmrPw)
		        .build();
				List<String> list = new ArrayList<String>();
				list.add(regId);
				MulticastResult multiResult;
			
				multiResult = sender.send(message, list, 5);
				if (multiResult != null) {
						List<Result> resultList = multiResult.getResults();
						for (Result result : resultList) {
							System.out.println(result.getMessageId());
						}
				}
			}
			else{
				return;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//푸시알림 웹전송
	@RequestMapping(value = "/pushDespList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String insertPushDespList(AppPushValueObject appPushVo, PushValueObject pushVo, HttpSession session) throws Exception {
		try {
			// TODO Auto-generated method stub			
			Sender sender = new Sender("AIzaSyDkvL2tbeYw-3wdVDCHd_1lRJJF6_9ld30"); // 서버 API Key 입력

			String type = appPushVo.getType();
			String sendTlt = appPushVo.getSendTitl();
	        String sendMsg = appPushVo.getSendMesg();
	        String sendUrl = appPushVo.getSendUrl();
	        int despRecrdSeq = (Integer)session.getAttribute("despRecrdSeq");
	        pushVo.setDespRecrdSeq(despRecrdSeq);
	        
	        Message message = new Message.Builder()
//	        .collapseKey("collapseKey")
			.timeToLive(3000) //메시지가 살아있는 시간(초단위)
			.delayWhileIdle(false) //true면 화면이 꺼진 상태일 때 수신이 안됨
			.addData("title", sendTlt)
	        .addData("message", sendMsg)
	        .addData("url", sendUrl)
	        .addData("type",type)
	        .addData("despRecrdSeq",String.valueOf(despRecrdSeq))
	        .build();
	        List<String> regIdList = new ArrayList<String>();
			regIdList = appPushService.selectWebCusmtrToken(); // 관리자 단말기 RegID 리스트

			for(int i=0; i<regIdList.size(); i++)
				System.out.println(regIdList.get(i));
			
			
			MulticastResult multiResult;
		
			multiResult = sender.send(message, regIdList, 5);
			if (multiResult != null) {
					List<Result> resultList = multiResult.getResults();
					for (Result result : resultList) {
						System.out.println(result.getMessageId());
					}
					System.out.println(multiResult.getSuccess());
					System.out.println(multiResult.getFailure());
					pushVo.setSucsCont(multiResult.getSuccess());
					pushVo.setFalCont(multiResult.getFailure());
					
					System.out.println(pushVo.getDespRecrdSeq()+"app에서 값");
					pushService.updatePushDespCont(pushVo);
					session.removeAttribute("despRecrdSeq");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/pushNotfcatnDespSelectList.do";
	}
	//푸시알림 재전송
	@RequestMapping(value = "/rePushDespList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String insertRePushDespList(AppPushValueObject appPushVo, PushValueObject pushVo, HttpSession session) throws Exception {
		try {
			// TODO Auto-generated method stub			
			Sender sender = new Sender("AIzaSyDkvL2tbeYw-3wdVDCHd_1lRJJF6_9ld30"); // 서버 API Key 입력

			String type = appPushVo.getType();
			String sendTlt = appPushVo.getSendTitl();
	        String sendMsg = appPushVo.getSendMesg();
	        String sendUrl = appPushVo.getSendUrl();
			
	        Message message = new Message.Builder()
//	        .collapseKey("collapseKey")
			.timeToLive(3000) //메시지가 살아있는 시간(초단위)
			.delayWhileIdle(false) //true면 화면이 꺼진 상태일 때 수신이 안됨
			.addData("title", sendTlt)
	        .addData("message", sendMsg)
	        .addData("url", sendUrl)
	        .addData("type",type)
	        .build();
	        List<String> regIdList = new ArrayList<String>();
			regIdList = appPushService.selectWebCusmtrToken(); // 관리자 단말기 RegID 리스트

			for(int i=0; i<regIdList.size(); i++)
				System.out.println(regIdList.get(i));
			
			
			MulticastResult multiResult;
		
			multiResult = sender.send(message, regIdList, 5);
			if (multiResult != null) {
					List<Result> resultList = multiResult.getResults();
					for (Result result : resultList) {
						System.out.println(result.getMessageId());
					}
					System.out.println(multiResult.getSuccess());
					System.out.println(multiResult.getFailure());
					pushVo.setSucsCont(multiResult.getSuccess());
					pushVo.setFalCont(multiResult.getFailure());
					pushVo.setDespRecrdSeq((Integer)session.getAttribute("despRecrdSeq"));
					System.out.println(pushVo.getDespRecrdSeq()+"app에서 값");
					pushService.updatePushDespCont(pushVo);
					session.removeAttribute("despRecrdSeq");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "flag";
	}
}