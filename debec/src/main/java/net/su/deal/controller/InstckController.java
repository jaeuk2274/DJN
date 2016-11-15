package net.su.deal.controller;

import java.util.List;

import javax.annotation.Resource;

import net.su.deal.service.InstckService;
import net.su.deal.valueObject.InstckValueObject;
import net.su.logger.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 입고거래장관리 컨트롤러입니다.
 * 
 * @see   net.su.deal.controller.ClintController
 * @version  1.0 
 * @ author 김대현, 2016/08/14
 */


@Controller
public class InstckController {
	@Resource
	private InstckService instckService;
	
	
/**
* 거래처 입고거래장을 조회하는 메서드입니다.
*
* @param   int clintSeq, ModelMap model, InstckValueObject instckVo
* @return  String
* @exception  예외처리 상황을 적어주세요
*/
// 입고거래장 팝업 뛰우는 메소드
	@RequestMapping(value="/InstckExchngFlorPopup.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String instckExchngFlorPopup(@RequestParam("clintSeq") int clintSeq, ModelMap model, InstckValueObject instckVo) throws Exception{

		System.out.println(instckVo.getBeginDatepicker()+ "     1111111111111111111111");
		System.out.println(instckVo.getEndDatepicker()+ "     2222222222222222222222222222222");
		
		List<InstckValueObject> instckList = instckService.instckList(clintSeq, instckVo);
		
		instckVo.setClintSeq(clintSeq);
		
		model.addAttribute("instckList", instckList);
		
		model.addAttribute("instckVo", instckVo);

		return "deal/instck/InstckExchngFlorListPopup";
	}

/**
* 거래처 입고거래장을  상세조회하는 메서드입니다.
*
* @param   int clintSeq, ModelMap model, InstckValueObject instckVo
* @return  String
* @exception  예외처리 상황을 적어주세요
*/
	// 거래처 입고거래장 상세조회 메서드
	@RequestMapping(value="/InstckExchngFlorReadPopup.do", method = RequestMethod.POST)
	public String instckExchngFlorReadPopup(ModelMap model, InstckValueObject instckVo) throws Exception{
		System.out.println(instckVo.getInstckExchngFlorSeq() + "  111111111111111111");
		System.out.println(instckVo.getClintSeq()+  "  222222222222222");
		
		instckVo=instckService.instckExchngFlorReadPopup(instckVo);
		model.addAttribute("instckVo", instckVo);
		return "deal/instck/InstckExchngFlorReadPopup";
	}


/**
* 거래처 입고내역을  조회하는 메서드입니다.
*
* @param   ModelMap model, InstckValueObject instckVo
* @return  String
* @exception  예외처리 상황을 적어주세요
*/
	// 입고내역 리스트 조회 메서드
	@RequestMapping(value="/instckRecrdListPopup.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String instckRecrdListPopup(ModelMap model, InstckValueObject instckVo) throws Exception{
		
		List<InstckValueObject> instckRecrdList=instckService.instckRecrdList(instckVo);
		model.addAttribute("instckVo", instckVo);
		model.addAttribute("instckRecrdList", instckRecrdList);
		return "deal/instck/InstckRecrdListPopup";
	}
	
	/**
	* 입고거래관리 - 입고거래장 목록을 조회하는 메서드입니다.
	*
	* @param   ModelMap model, InstckValueObject instckVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	// 입고거래관리 - 입고거래장 목록 리스트 조회
	@RequestMapping(value="/InstckExchngFlorList.do", method={RequestMethod.POST, RequestMethod.GET})
	public String selectInstckExchngFlor(ModelMap model, InstckValueObject instckVo) throws Exception{
		System.out.println("1111111111");
		List<InstckValueObject> instckList = instckService.selectInstckExchngFlor(instckVo);
		model.addAttribute("instckList", instckList);
		model.addAttribute("instckVo", instckVo);
		return "deal/instck/InstckExchngFlorList";
	}
	
	/**
	* 입고거래관리 - 입고거래장을 등록 팝업창을 생성하는 메서드입니다.
	*
	* @param   ModelMap model, InstckValueObject instckVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/InstckExchngFlorCreateReadPopup.do", method=RequestMethod.GET)
	public String insertInstckExchngFlorReadPopup(ModelMap model) throws Exception{
		
		List<InstckValueObject> clintNmeList = instckService.selectClintNme();		// 거래처명 셀렉타이즈
		model.addAttribute("clintNmeList",clintNmeList);
		return "deal/instck/InstckExchngFlorCreatePopup";
	}
	
	
	/**
	* 입고거래관리 - 입고거래장 등록 팝업창에 입력한 것을 등록하는 메서드입니다.
	* 
	* @param   ModelMap model, InstckValueObject instckVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/InstckExchngFlorCreate.do", method={RequestMethod.POST, RequestMethod.GET})
	public String insertInstckExchngFlor(MultipartHttpServletRequest request, InstckValueObject instckVo) throws Exception{
		Logger.info(null);
		instckService.imgInsert(request, instckVo);
		
//		instckService.insertInstckExchngFlor(instckVo);
		return "redirect:/InstckExchngFlorList.do";
	}
	
	/**
	* 입고거래장을 팝업으로 상세보기하는 메서드입니다.
	*
	* @param   ModelMap model, InstckValueObject instckVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/InstckExchngFlor_ReadPopup.do", method={RequestMethod.POST, RequestMethod.GET})
	public String InstckExchngFlorReadPopup(ModelMap model, InstckValueObject instckVo) throws Exception{
		System.out.println(" !!!!!!!!!!!!!!!!!!!"+instckVo.getInstckExchngFlorSeq());
		instckVo=instckService.instckExchngFlorReadPopup(instckVo);
		model.addAttribute("instckVo", instckVo);
		return "deal/instck/InstckExchngFlor_ReadPopup";
	}
	
	/**
	* 입고거래장상세보기에서 입고거래장 수정화면으로 이동하는 메서드입니다.
	*
	* @param   ModelMap model, InstckValueObject instckVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/InstckExchngFlorUpdateRead.do", method=RequestMethod.POST)
	public String updateInstckExchngFlorRead(ModelMap model, InstckValueObject instckVo) throws Exception{
		
		List<InstckValueObject> clintNmeList = instckService.selectClintNme();		// 거래처명 셀렉타이즈
		model.addAttribute("clintNmeList",clintNmeList);
		
		instckVo=instckService.instckExchngFlorReadPopup(instckVo);
		model.addAttribute("instckVo", instckVo);
		
		return "deal/instck/InstckExchngFlorUpdatePopup";
	}
	
	/**
	* 입고거래장(팝업)을 수정하는 메서드입니다.
	*
	* @param   ModelMap model, InstckValueObject instckVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/InstckExchngFlorUpdate.do", method={RequestMethod.POST, RequestMethod.GET})
	public String updateInstckExchngFlor(MultipartHttpServletRequest request, ModelMap model, InstckValueObject instckVo) throws Exception{
		
		instckService.imgUpdate(request, instckVo);

//		instckService.updateInstckExchngFlor(instckVo);
		
		
		
		instckVo=instckService.instckExchngFlorReadPopup(instckVo);
		model.addAttribute("instckVo", instckVo);
		return "redirect:/InstckExchngFlorList.do";
	}
	
	/**
	* 입고거래장(팝업)을 삭제하는 메서드입니다.
	*
	* @param   ModelMap model, InstckValueObject instckVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/InstckExchngFlorDelete.do", method={RequestMethod.POST, RequestMethod.GET})
	public void deleteInstckExchngFlorPopup(InstckValueObject instckVo) throws Exception{
		System.out.println(instckVo.getInstckExchngFlorSeq()+ "   @@@@@@@@@@@@@");
		instckService.deleteInstckExchngFlorPopup(instckVo);
		System.out.println(instckVo.getInstckExchngFlorSeq()+ "   !!!!!!!!!!!!!");
	}
	
	/**
	* 입고거래장을 체크해서 삭제하는 메서드입니다.
	*
	* @param   ModelMap model, InstckValueObject instckVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/InstcExchngFlorkDelete.do", method={RequestMethod.POST, RequestMethod.GET})
	public String deleteInstckExchngFlor(InstckValueObject instckVo) throws Exception{
		System.out.println(instckVo.getChangeGroup()+ "        ggggggggggggggggggggggggggg");
		instckService.deleteInstckExchngFlor(instckVo);
		return "redirect:/InstckExchngFlorList.do";
	}
}




