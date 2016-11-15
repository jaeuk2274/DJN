package net.su.end.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.su.end.dataAccessObject.TodyAnalDataAccessObject;
import net.su.end.valueObject.TodyAnalValueObject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 투데이리포트 서비스임플입니다.
 * 
 * @see   net.su.end.service.TodyAnalServiceImpl
 * @version  1.0 
 * @ author 김대현, 2016/08/24
 */
@Service
public class TodyAnalServiceImpl implements TodyAnalService{

	@Resource
	private TodyAnalDataAccessObject todyAnalDao;
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 매출액을 조회 메서드입니다.
	*
	* @param   
	* @return  TodyAnalValueObject
	* @exception  예외처리 상황을 적어주세요
	*/
	// 매출액
	public TodyAnalValueObject selectTodyResult() throws Exception{
		TodyAnalValueObject selectTodyResult = todyAnalDao.selectTodyResult();
		return selectTodyResult;
	}
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 전화주문 건수를 조회 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	// 전화주문 건수
	public int telOrdrCount() throws Exception{
		int telOrdrCount = todyAnalDao.telOrdrCount();
		return telOrdrCount;
	}
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 어플주문 건수를 조회 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	// 어플주문 건수
	public int appOrdrCount() throws Exception{
		int appOrdrCount = todyAnalDao.appOrdrCount();
		return appOrdrCount;
	}
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 주문취소 건수를 조회 메서드입니다.
	*
	* @param   
	* @return  int
	* @exception  예외처리 상황을 적어주세요
	*/
	// 주문취소 건수
	public int ordrCancelCount() throws Exception{
		int ordrCancelCount = todyAnalDao.ordrCancelCount();
		return ordrCancelCount;
	}
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 교환환불 건수를 조회 메서드입니다.
	*
	* @param   
	* @return  String
	* @exception  예외처리 상황을 적어주세요
	*/
	// 교환환불 건수
	public String exchngRefndCount() throws Exception{
		String exchngRefndCount = todyAnalDao.exchngRefndCount();
		return exchngRefndCount;
	}
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 오늘 상품 판매량순위 top10을 조회 메서드입니다.
	*
	* @param   
	* @return  List<TodyAnalValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	// 오늘 상품 판매량순위 top10
	public List<TodyAnalValueObject> todySalesList() throws Exception{
		List<TodyAnalValueObject> todySalesList = todyAnalDao.todySalesList();
		return todySalesList;
	}
	
	/**
	* 통계관리 - 투데이리포트 - 투데이 실적 - 급상승 판매상품순위 top10을 조회 메서드입니다.
	*
	* @param   
	* @return  List<TodyAnalValueObject>
	* @exception  예외처리 상황을 적어주세요
	*/
	// 급상승 판매상품 순위 top10
	public List<TodyAnalValueObject> surgeSelProdctList() throws Exception{
		List<TodyAnalValueObject> surgeSelProdctList = todyAnalDao.surgeSelProdctList();
		return surgeSelProdctList;
	}
	
	// 일일매출 그래프 (시간)
	public List<TodyAnalValueObject> salsTime() throws Exception{
		List<TodyAnalValueObject> salsTime = todyAnalDao.salsTime();
		return salsTime;
	}
	
	// 엑셀 업로드
	public void insertSalsPresnt(MultipartHttpServletRequest request, TodyAnalValueObject todyAnalVo) throws Exception{
		Map<String, MultipartFile> files = request.getFileMap();					// 크롬, 익스플로러에서도 파일 경로를 잡을 수 있게 해줌
		CommonsMultipartFile cmf = (CommonsMultipartFile) files.get("fileName");	// 크롬, 익스플로러에서도 파일 경로를 잡을 수 있게 해줌
		
		
		todyAnalDao.insertSalsPresnt(todyAnalVo, cmf);
		
	}

}
