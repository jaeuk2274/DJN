package net.su.custmr.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.su.custmr.dataAccessObject.OrdrDataAccessObject;
import net.su.custmr.valueObject.OrdrValueObject;
import net.su.logger.Logger;

@Service
public class OrdrServiceImpl implements OrdrService {

	@Resource
	private OrdrDataAccessObject ordrDAO;
	
	/**
		* 실시간 주문내역 리스트를 조회하는 메서드입니다.
		*
		* @param   OrdrValueObject ordrValueObject
		* @return  List<OrdrValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrValueObject> selectRealTimeOrdrRecrdList(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);					
		List<OrdrValueObject> realTimeOrdrRecrdList = ordrDAO.selectRealTimeOrdrRecrdList(ordrValueObject);	 // 고객 리스트 조회
		return realTimeOrdrRecrdList;
	}	
	
	/**
		* 주문내역을 상세조회하는 메서드입니다.
		*
		* @param   OrdrValueObject ordrValueObject
		* @return  OrdrValueObject
		* @exception  예외처리 상황을 적어주세요
	*/
	public OrdrValueObject ordrRecrdRead(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);			
		OrdrValueObject ordrRecrdVO = ordrDAO.ordrRecrdRead(ordrValueObject);
		return ordrRecrdVO;
	}
	
	/**
		* 주문내역의 상품 리스트를 조회하는 메서드입니다.
		*
		* @param   OrdrValueObject ordrValueObject
		* @return  List<OrdrValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrValueObject> ordrRecrdProdctRead(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);			
		List<OrdrValueObject> ordrRecrdProdctList = ordrDAO.ordrRecrdProdctRead(ordrValueObject);
		return ordrRecrdProdctList;
	}
	
	/**
		* 주문내역 리스트를 조회하는 메서드입니다.
		*
		* @param   OrdrValueObject ordrValueObject
		* @return  List<OrdrValueObject>
		* @exception  예외처리 상황을 적어주세요
	*/
	public List<OrdrValueObject> ordrRecrdList(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);	
		int selectOrdrCount = ordrDAO.selectOrdrCount(ordrValueObject);	// 주문 리스트 전체 수 (페이징)
		ordrValueObject.setTotalRecordCount(selectOrdrCount); // 주문 리스트 전체 수 셋팅 (페이징)
		List<OrdrValueObject> ordrRecrdtList = ordrDAO.ordrRecrdList(ordrValueObject);
		return ordrRecrdtList;
	}
	
	/**
		* 주문내역 리스트에서 일괄 배달 접수를 하는 메서드입니다.(주문정보에 로그인한 직원정보가 배달원으로 업데이트)
		* (주문접수 -> 배달중만 가능)
		* @param   OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/	
	public void changeOrdrStatGroup(OrdrValueObject ordrValueObject) throws Exception {
		Logger.info(null);
		int[] ordrGroup = ordrValueObject.getOrdrChkGroup();
		List<Integer> ordrStatChgGrop = new ArrayList<Integer>(); // 맵퍼에  .list로 한번에 보내서 맵퍼에서 한번에 처리하기 위해 위해 배열을 list에 삽입
		// db에 커넥트하는게 1번이라서 반복 돌리면서 계속 커넥트하는 것 보다 훨씬 효율적이다.
		for(int i=0; i<ordrGroup.length; i++) {
			ordrStatChgGrop.add(ordrGroup[i]);
		}					
		// map으로 보내는 이유. .list를 같이 보내기 위해서, 고정된 값은 VO, 유동적인 값 반복
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("OrdrVO", ordrValueObject);
		map.put("OrdrStatChgGrop", ordrStatChgGrop);
		ordrDAO.changeOrdrStatGroup(map);	
	}
	
	/**
		* 주문내역 상세보기에서 배달 상태변경을 하는 메서드입니다.
		* 
		* @param   OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void changeOrdrStat(OrdrValueObject ordrValueObject) throws Exception{
		Logger.info(null);	
		ordrDAO.changeOrdrStat(ordrValueObject);
	}
	

	
	/**
		* 주문내역 상세보기에서 이미지 등록을 하는 메서드입니다.
		* 
		* @param   MultipartHttpServletRequest request, OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void imgInsert(MultipartHttpServletRequest request, OrdrValueObject ordrValueObject) throws Exception  
	{
		Logger.info(null);	
		OrdrServiceImpl ordrServiceImpl = new OrdrServiceImpl();
		Map<String, MultipartFile> files = request.getFileMap();
		CommonsMultipartFile cart1 = (CommonsMultipartFile) files.get("cart1");
		ServletContext context = request.getSession().getServletContext();
		int random = (int) (Math.random() * 100);
		if(cart1.getSize()!=0)
		{
			String filePath ="resources/image/prodct/ordrNum_"+ordrValueObject.getOrdrNumbrSeq()+"_"+random+"/main.jpg";
			String dirPath ="resources/image/prodct/ordrNum_"+ordrValueObject.getOrdrNumbrSeq()+"_"+random;
			System.out.println("사진 저장 절대경로 : "+context.getRealPath(filePath));
			ordrServiceImpl.selProdctImgSave(context.getRealPath(filePath), context.getRealPath(dirPath), cart1);
			ordrValueObject.setCartImg(filePath);
			ordrDAO.imgUpdate(ordrValueObject);
		}
		else // 이미지가 선택되지 않음
		{
			System.out.println("이미지를 선택해주세요.");
		}	
				
	}
	
	/**
		* 이미지 저장을 하는 메서드입니다.
		* 
		* @param   MultipartHttpServletRequest request, OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void selProdctImgSave(String filePath, String dirPath, CommonsMultipartFile cmf) throws Exception
	{
		Logger.info(null);	
		File fileSaver = new File(filePath);
		File dirSaver = new File(dirPath);
		// 파일 업로드 처리 완료.
		try {
			if(!fileSaver.exists())
			{
				dirSaver.mkdir();
			}
			cmf.transferTo(fileSaver);
			// insert method
			System.out.println("성공");			
		} 
		catch (Exception e) {
			System.out.println("실패: "+ e);
		}
	}
	
	
	/**
		* 주문내역 상세보기에서 체크박스 선택한 이미지 삭제를 하는 메서드입니다.
		* 
		* @param   OrdrValueObject ordrValueObject
		* @return  void
		* @exception  예외처리 상황을 적어주세요
	*/
	public void imgDelete(OrdrValueObject ordrValueObject) throws Exception{
		ordrDAO.imgDelete(ordrValueObject);		
	}

}
