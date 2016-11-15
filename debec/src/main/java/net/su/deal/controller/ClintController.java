package net.su.deal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.su.deal.service.ClintService;
import net.su.deal.service.InstckService;
import net.su.deal.valueObject.ClintValueObject;
import net.su.deal.valueObject.InstckValueObject;
import net.su.logger.Logger;
import net.su.prodct.display.service.MainDisplayService;
import net.su.prodct.valueObject.CatgrValueObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 거래처관리 컨트롤러입니다.
 * 
 * @see   net.su.deal.controller.ClintController
 * @version  1.0 
 * @ author 김대현, 2016/08/09
 */

@Controller
public class ClintController {
	
	@Resource
	private ClintService clintService;
	@Resource
	private InstckService instckService;
	@Resource
	private MainDisplayService mainDisplayService;
	
/**
* 거래처관리 거래처 목록 조회를 하는 메서드입니다.  (+검색)
*
* @param   ModelMap model, ClintValueObject clintVo
* @return  String
* @exception  예외처리 상황을 적어주세요
*/
	@RequestMapping(value="/ClintList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String clintList(ModelMap model, ClintValueObject clintVo) throws Exception{
		List<ClintValueObject> clintList = clintService.clintList(clintVo);	// 거래처 목록 조회,검색 리스트
		model.addAttribute("clintList", clintList);		// 거래처 목록 조회 리스트
		model.addAttribute("clintVo", clintVo);
		return "deal/clint/ClintList";
	}
	
/**
* 거래처관리 거래처 상세보기를 하는 메서드입니다.
*
* @param   ModelMap model, ClintValueObject clintVo
* @return  String
* @exception  예외처리 상황을 적어주세요
*/
	// 거래처 상세보기 메소드
	@RequestMapping(value="/ClintRead.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String clintRead(ModelMap model, ClintValueObject clintVo) throws Exception{
		Logger.info(null);
		int clintSeq = clintVo.getClintSeq();
		List<ClintValueObject> prodctList = clintService.prodctList(clintVo);
		ClintValueObject clintVo_read = clintService.clintRead(clintSeq);
		
		model.addAttribute("clintVo", clintVo);
		model.addAttribute("prodctList",prodctList);	// 상품 리스트
		model.addAttribute("clintRead", clintVo_read);
		return "deal/clint/ClintRead";
	}
	
	/**
	* 거래처관리 거래처 상세보기에서 거래처를 삭제하는 메서드입니다.
	* 상세조회된 거래처 삭제 메소드  ** 거래처 삭제 시 등록되어 있는 상품은 삭제안함
	* @param   int clintSeq
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/	
	@RequestMapping(value="/ClintReadDelete.do", method = RequestMethod.GET)
	public String clintReadDelete(@RequestParam("clintSeq") int clintSeq) throws Exception{
		clintService.clintReadDelete(clintSeq);	
		return "redirect:/ClintList.do";
	}
	
	/**
	* 거래처관리 거래처 상세보기에서 거래처 수정화면으로 이동하는 메서드입니다.
	*
	* @param   ModelMap model, ClintValueObject clintVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/	
	@RequestMapping(value="/ClintUpdateRead.do", method = RequestMethod.POST)
	public String clintUpdateRead(ModelMap model, ClintValueObject clintVo) throws Exception{
		model.addAttribute("clintVo", clintVo);
		return "deal/clint/ClintUpdate";
	}
	
	/**
	* 거래처관리 거래처 수정화면에서 거래처를 수정하는 메서드입니다.
	*
	* @param   ClintValueObject clintVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/	
	@RequestMapping(value="/ClintUpdate.do", method=RequestMethod.POST)
	public String clintUpdate(ClintValueObject clintVo) throws Exception{
		clintService.clintUpdate(clintVo);
		int clintSeq = clintVo.getClintSeq();
		return "redirect:/ClintRead.do?clintSeq="+clintSeq;
	}
	
	/**
	* 거래처관리 상품추가모달에서 거래처가 미등록된 상품을 조회, 검색하는 메서드입니다.
	* 상품추가(모달)에서 거래처가 미등록된 상품을 조회(왼쪽테이블)
	* @param   ModelMap model
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/	
	@RequestMapping(value="/SelectProdctAddList.do", method={RequestMethod.POST, RequestMethod.GET})
	public String selectProdctAdList(HttpServletRequest request, ModelMap model, ClintValueObject clintVo) throws Exception{
		String prodctSechText = request.getParameter("prodctSechText") == null?"":request.getParameter("prodctSechText");	
		// 삼항연산자, ajax로 받은 값이 null이면 공백을, null이 아니면 입력한 값을 담음
		clintVo.setProdctSechText(prodctSechText);				
		List<ClintValueObject> selectProdctAdList = clintService.selectProdctAdList(clintVo);
		model.addAttribute("selectProdctAdList", selectProdctAdList);
		model.addAttribute("clintVo", clintVo);
		return "deal/clint/ProdctTable";
	}
	
	/**
	* 거래처관리 상품추가모달에서 임시테이블 조회, 검색하는 메서드입니다.
	*
	* @param   ModelMap model
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/	
	// 상품추가(모달)에서 임시테이블 조회, 검색하는 메서드입니다.(오른쪽 테이블)
	@RequestMapping(value="/SelectProdctAddTempList.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String selectProdctAdTempList(HttpServletRequest request, ModelMap model, ClintValueObject clintVo) throws Exception{
		String tempSechText = request.getParameter("tempSechText") == null?"": request.getParameter("tempSechText");
		clintVo.setTempSechText(tempSechText);
		List<ClintValueObject> selectProdctAdTempList = clintService.selectProdctAdTempList(clintVo);
		model.addAttribute("selectProdctAdTempList", selectProdctAdTempList);
		model.addAttribute("clintVo", clintVo);
		return "deal/clint/TempTable";
	}
	
	/**
	* 거래처관리 상품추가모달에서 임시테이블에 상품을 추가하는 메서드입니다.
	* 임시테이블(오른쪽테이블)에 상품추가하는 메서드(체크해서 '+' 버튼)
	* @param   String chkedVal
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	@RequestMapping(value="/ProdctAddTempTableCreate.do", method={RequestMethod.POST, RequestMethod.GET})	// insertProdctAdTempList.do
	@ResponseBody		// ajax로 값 받을때  @ResponseBody를 적어줘야 ajax에서 보낸값을 인식한다.
	public void prodctAddTempTableCreate(@RequestBody String chkedVal) throws Exception{		// ajax로 값 받을때 @RequestBody를 적어야 받은 값을 인식한다.
		String[] data = chkedVal.split("%2C");
		for(int i =0; i<data.length; i++){		// 배열 data의 길이만큼 돌림 (size랑 length랑 차이)
			if(data[i].contains("="))		// 배열 data안에 "=" 들어있을 때 조건
			data[i] = data[i].replace("=", "");		// "=" 을 "" 으로 바꿔준다.
		}
		clintService.prodctAddTempTableCreate(data);
		
	}
	
	/**
	* 거래처관리 상품추가모달에서 임시테이블에 상품을 삭제하는 메서드입니다.
	* 임시테이블(오른쪽테이블)에 상품삭제하는 메서드(체크해서 '-' 버튼)
	* @param   String chkedVal
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	@RequestMapping(value="/ProdctAddTempTableDelete.do", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void prodctAddTempTableDelete(@RequestBody String chkedVal) throws Exception{
		String[] data = chkedVal.split("%2C");
		for (int i=0; i<data.length; i++){
			if(data[i].contains("="))
				data[i] = data[i].replace("=","");
		}
		clintService.prodctAddTempTableDelete(data);
	}
	
	/**
	* 거래처관리 상품추가모달에서 임시테이블에 상품을 최종적으로 추가하는 메서드
	*
	* @param   String chkedVal
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	@RequestMapping(value="/ProdctAddCreate.do", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void prodctAddCreate(ClintValueObject clintVo) throws Exception{
		Logger.info(null);
		String[] data = clintService.selectTempProdct(clintVo);
		clintService.insertProdct(data, clintVo.getClintSeq());
		clintService.deleteTempTable();
	}
	
	/**
	* 거래처관리 거래처 등록하는 화면으로 이동하는 메서드
	*
	* @param   
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/	
	@RequestMapping(value="/ClintCreateRead.do", method=RequestMethod.GET)
	public String clintCreateRead() throws Exception{
		return "deal/clint/ClintCreate";
	}
	
	/**
	* 거래처관리 거래처를 등록하는 메서드
	* @param   ClintValueObject clintVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/	
	@RequestMapping(value="/ClintCreate.do", method=RequestMethod.POST)
	public String insertClint(ClintValueObject clintVo) throws Exception{
		int insertClintSeq = clintService.insertClint(clintVo);
//		System.out.println("방금 등록횐 거래처 seq : "+insertClintSeq);
		clintVo.setClintSeq(insertClintSeq);
//		System.out.println(clintVo.getClintSeq());
		return "redirect:/ClintRead.do?clintSeq="+insertClintSeq;
	}
	
	/**
	* 거래처관리 체크한 거래처를 삭제하는 메서드
	* @param   ClintValueObject clintVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	@RequestMapping(value="/ClintDelete.do", method=RequestMethod.POST)
	public String deleteClint(ClintValueObject clintVo) throws Exception{
		clintService.deleteClint(clintVo);	
		return "redirect:/ClintList.do";
	}

	/**
	* 거래처관리 거래처 조회화면에서 상품추가 팝업창을 띄우는 메서드입니다.
	*
	* @param   ClintValueObject clintVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/	
	@RequestMapping(value="/ProductAddFrame.do", method={RequestMethod.POST, RequestMethod.GET})
	public String ProductAddFrame(ClintValueObject clintVo, ModelMap model) throws Exception{
		model.addAttribute("ClintVo", clintVo);
		return "deal/clint/ProductAddFrame";
	}
	
	/**
	* 거래처관리 상품추가모달에서 취소(껐을때) 임시테이블 제거
	*
	* @param   String chkedVal
	* @return  
	* @exception  예외처리 상황을 적어주세요
	*/	
	@RequestMapping(value="/deleteTempTable.do", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void deleteTempTable(ClintValueObject clintVo) throws Exception{
		Logger.info(null);
		clintService.deleteTempTable();
	}
	
	/**
	* 거래처 상세보기에서 거래처 상품들 거래처 일괄 변경 하는 화면으로 이동하는 메소드
	*
	* @param   ClintValueObject clintVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/	
	@RequestMapping(value="/ClintProductUpdatePopup.do", method={RequestMethod.POST, RequestMethod.GET})
	public String ClintProductUpdatePopup(Model model, ClintValueObject clintVo) throws Exception{
		Logger.info(null);
		int ClintBridgChk[] = clintVo.getClintBridgChkGroup();
		
		List<Integer> chkList = new ArrayList<Integer>(); 
		for(int i=0; i<ClintBridgChk.length; i++) {
			chkList.add(ClintBridgChk[i]);
		}	
		clintVo.setChkList(chkList);
		
		List<InstckValueObject> clintNmeList = instckService.selectClintNme();		// 거래처명 셀렉타이즈
		model.addAttribute("clintNmeList",clintNmeList);
		model.addAttribute("clintVo", clintVo);

		return "deal/clint/ClintProductUpdatePopup";
	}
	
	/**
	* 거래처 상세보기에서 거래처 상품들 거래처 일괄 변경 하는 메소드
	*
	* @param   ClintValueObject clintVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/	
	@RequestMapping(value="/ClintProductUpdate.do", method={RequestMethod.POST, RequestMethod.GET})
	public String ClintProductUpdate(ClintValueObject clintVo) throws Exception{
		Logger.info(null);
		clintService.ClintProductUpdate(clintVo);
		return "forward:/ClintProductUpdatePopup.do";
	}
	
	
	/**
	* 거래처 상세보기에서 거래처 상품들 일괄 삭제하는 메소드
	*
	* @param   ClintValueObject clintVo
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/	
	@RequestMapping(value="/ClintProductDelete.do", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public void ClintProductDelete(ClintValueObject clintVo) throws Exception{
		Logger.info(null);
		clintService.ClintProductDelete(clintVo);

//		return "redirect:/ClintRead.do";
	}
	
	
}






