package net.su.app.appMarket.controller;

import java.util.List;

import javax.annotation.Resource;

import net.su.app.appMarket.service.AppDebecFestivalService;
import net.su.logger.Logger;
import net.su.prodct.valueObject.DebecFestivalValueObject;
import net.su.prodct.valueObject.ProdctValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 앱 마켓 메뉴 대백제 기능 컨트롤러입니다.
 * 
 * @see   net.su.app.appMarket.controller
 * @version  1.0 
 * @ author 이인호, 2016/09/20
 */

@Controller
public class AppDebecFestivalController {
	
	@Resource
	private AppDebecFestivalService appDebecFestivalService;
	
	/**
    * 대백제 진행 상탸를 조회하는 메서드
    *
    * @param    
    * @return  int
    * @exception  Exception
    */
	@RequestMapping(value = "/appDebecFestival.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public int appDebecFestival() throws Exception {
		Logger.info(null);
		
		int schedlCount = appDebecFestivalService.appDebecFestival();
		return schedlCount;
	}
	
	
	/**
    * 대백제 행사 정보를 조회하는 메서드
    *
    * @param    
    * @return  DebecFestivalValueObject
    * @exception  Exception
    */
	@RequestMapping(value = "/appDebecFestivalRead.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public DebecFestivalValueObject appDebecFestivalRead() throws Exception {
		Logger.info(null);
		
		DebecFestivalValueObject debecFestivalInfo = appDebecFestivalService.appDebecFestivalRead();
		return debecFestivalInfo;
	}
	
	/**
    * 대백제 행사 상품 리스트를 조회하는 메서드
    *
    * @param   DebecFestivalValueObject 
    * @return  List<ProdctValueObject>
    * @exception  Exception
    */
	@RequestMapping(value = "/appDebecFestivalProdctList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<ProdctValueObject> appDebecFestivalProdctList(DebecFestivalValueObject debecFestivalValueObject) throws Exception {
		Logger.info(null);
		
		List<ProdctValueObject> appDebecFestivalProdctList = appDebecFestivalService.appDebecFestivalProdctList(debecFestivalValueObject);
		return appDebecFestivalProdctList;
	}
}
