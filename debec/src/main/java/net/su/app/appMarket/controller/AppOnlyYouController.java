package net.su.app.appMarket.controller;

import java.util.List;

import javax.annotation.Resource;

import net.su.app.appMarket.service.AppOnlyYouService;
import net.su.logger.Logger;
import net.su.prodct.valueObject.ProdctValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 앱 마켓 메뉴 너.만.상 기능 컨트롤러입니다.
 * 
 * @see   net.su.app.appMarket.controller
 * @version  1.0 
 * @ author 이인호, 2016/09/22
 */

@Controller
public class AppOnlyYouController {
	
	@Resource
	private AppOnlyYouService appOnlyYouService;

	/**
    * 너.만.상 준비 상태를 조회하는 메서드
    *
    * @param   int 
    * @return  int
    * @exception  Exception
    */
	@RequestMapping(value = "/appOnlyYou.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public int appOnlyYou(@RequestParam(value="custmrSeq") int custmrSeq) throws Exception {
		Logger.info(null);
		
		int onlyYouCount = appOnlyYouService.appOnlyYou(custmrSeq);
		return onlyYouCount;
	}
	
	/**
    * 너.만.상 상품 리스트를 조회하는 메서드
    *
    * @param   int
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/appOnlyYouRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<ProdctValueObject> appOnlyYouRead(@RequestParam(value="custmrSeq") int custmrSeq) throws Exception {
		Logger.info(null);
		
		List<ProdctValueObject> appOnlyYouList = appOnlyYouService.appOnlyYouRead(custmrSeq);
		return appOnlyYouList;
	}
	
}
