package net.su.app.appMyPg.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import net.su.app.appMyPg.dataAccessObject.AppCusMyPgDataAccessObject;
import net.su.app.appMyPg.dataAccessObject.AppEmpMyPgDataAccessObject;
import net.su.custmr.valueObject.CustmrValueObject;
import net.su.custmr.valueObject.OrdrValueObject;
import net.su.logger.Logger;
import net.su.market.valueObject.EmpValueObject;
import net.su.prodct.valueObject.ProdctValueObject;
import net.su.security.Base64Utils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service
public class AppEmpMyPgServiceImpl implements AppEmpMyPgService {

	@Resource
	private AppEmpMyPgDataAccessObject appMyPgDao;

	Base64Utils base64 = new Base64Utils();
	String encryptKey = "temp11111111111111111111";// key 선언
	String decryptKey = "temp11111111111111111111"; //

	// 키값은 무조건 24자리이여야 한듯.
	// Base64Utils.java 파일에서 키값의 길이를 수정하면 될것도 같으나,
	// seed 암호화에서 key값이 원래 24자리가 필요할지도 몰라서 그냥 1로 채움
	// seed + base64 암호화, 복호화

	/**
    * 직원 푸시수신 여부 조회하는 메서드
    *
    * @param   int
    * @return  String
    * @exception  Exception
    */
	public String selectEmpPushCheck(int empSeq) throws Exception{	
		Logger.info(null);
		String pushCheck = appMyPgDao.selectEmpPushCheck(empSeq);
		return pushCheck;
	}
	
	/**
    * 직원 푸시수신 여부 수정하는 메서드
    *
    * @param   int
    * @return  void
    * @exception  Exception
    */
	public void updateEmpPushCheck(int empSeq) throws Exception{	
		Logger.info(null);
		appMyPgDao.updateEmpPushCheck(empSeq);	
	}
	
	/**
    * 배송 상태 변경 시 고객 정보 조회하는 메서드
    *
    * @param   int
    * @return  EmpValueObject
    * @exception  Exception
    */
	public CustmrValueObject selectCustmrInfo(int ordrNumbrSeq) throws Exception{
		Logger.info(null);
		CustmrValueObject custmrInfo = appMyPgDao.selectCustmrInfo(ordrNumbrSeq);
		return custmrInfo;
	}

	/**
	 * 배송 상태 변경 하는 메서드
	 *
	 * @param int
	 * @return void
	 * @exception Exception
	 */
	public void updateOrdrStat(int ordrNumbrSeq, int empSeq) throws Exception {
		Logger.info(null);
		appMyPgDao.updateOrdrStat(ordrNumbrSeq, empSeq);
	}

	/**
	 * 직원 추천을 하는 메서드
	 *
	 * @param int, int
	 * @return void
	 * @exception Exception
	 */
	public void insertEmpRecmnd(HashMap<String, Integer> empSeqMap)
			throws Exception {
		Logger.info(null);
		appMyPgDao.insertEmpRecmnd(empSeqMap);
	}

	/**
	 * 직원 추천 목록을 조회하는 메서드
	 *
	 * @param int
	 * @return List<EmpValueObject>
	 * @exception Exception
	 */
	public List<EmpValueObject> selectEmpRecmndList(int empSeq)
			throws Exception {
		Logger.info(null);
		List<EmpValueObject> empRecmndList = appMyPgDao
				.selectEmpRecmndList(empSeq);
		return empRecmndList;
	}

	/**
	 * 관리자 이름 조회하는 메서드
	 *
	 * @param int
	 * @return String
	 * @exception Exception
	 */
	public String selectEmpNme(int empSeq) throws Exception {
		Logger.info(null);
		String empNme = appMyPgDao.selectEmpNme(empSeq);
		return empNme;
	}

	/**
	 * 배송상태별 주문건수를 조회하는 메서드
	 *
	 * @param int
	 * @return List<OrdrValueObject>
	 * @exception Exception
	 */
	public List<OrdrValueObject> selectOrdrCountList() throws Exception {
		Logger.info(null);
		List<OrdrValueObject> ordrCountList = appMyPgDao.selectOrdrCountList();
		return ordrCountList;
	}

	/**
	 * 실시간 주문 목록을 조회하는 메서드
	 *
	 * @param void
	 * @return List<OrdrValueObject>
	 * @exception Exception
	 */
	public List<OrdrValueObject> selectEmpRealTimeOrdrList() throws Exception {
		Logger.info(null);
		List<OrdrValueObject> empRealTimeOrdrList = appMyPgDao
				.selectEmpRealTimeOrdrList();
		return empRealTimeOrdrList;
	}

	/**
	 * 실시간 공동구매 주문 목록을 조회하는 메서드
	 *
	 * @param void
	 * @return List<OrdrValueObject>
	 * @exception Exception
	 */
	public List<OrdrValueObject> selectEmpDahamgaeRealTimeOrdrList()
			throws Exception {
		Logger.info(null);
		List<OrdrValueObject> empRealTimeOrdrList = appMyPgDao
				.selectEmpDahamgaeRealTimeOrdrList();
		return empRealTimeOrdrList;
	}

	/**
	 * 배송 목록을 조회하는 메서드
	 *
	 * @param String
	 * @return List<OrdrValueObject>
	 * @exception Exception
	 */
	public List<OrdrValueObject> selectEmpShipngList(int ordrStatSeq)
			throws Exception {
		Logger.info(null);
		List<OrdrValueObject> empShipngList = appMyPgDao.selectEmpShipngList(ordrStatSeq);
		return empShipngList;
	}

	/**
	 * 공동구매 배송 목록을 조회하는 메서드
	 *
	 * @param int
	 * @return List<OrdrValueObject>
	 * @exception Exception
	 */
	public List<OrdrValueObject> selectEmpDahamgaeShipngList(int ordrStatSeq)
			throws Exception {
		Logger.info(null);
		List<OrdrValueObject> empDahamgaeShipngList = appMyPgDao.selectEmpDahamgaeShipngList(ordrStatSeq);
		return empDahamgaeShipngList;
	}

	/**
	 * 주문 취소 목록을 조회하는 메서드
	 *
	 * @param void
	 * @return List<OrdrValueObject>
	 * @exception Exception
	 */
	public List<OrdrValueObject> selectOrdrCancelList() throws Exception {
		Logger.info(null);
		List<OrdrValueObject> ordrCancelList = appMyPgDao.selectOrdrCancelList();
		return ordrCancelList;
	}

	/**
	 * 공동구매 주문 취소 목록을 조회하는 메서드
	 *
	 * @param void
	 * @return List<OrdrValueObject>
	 * @exception Exception
	 */
	public List<OrdrValueObject> selectDahamgaeOrdrCancelList()
			throws Exception {
		Logger.info(null);
		List<OrdrValueObject> ordrCancelList = appMyPgDao.selectDahamgaeOrdrCancelList();
		return ordrCancelList;
	}

	/**
	 * 주문 취소 상세 조회하는 메서드
	 *
	 * @param int
	 * @return OrdrValueObject
	 * @exception Exception
	 */
	public OrdrValueObject selectEmpOrdrCancelRead(int ordrNumbrSeq)
			throws Exception {
		Logger.info(null);
		OrdrValueObject empOrdrVo = appMyPgDao.selectEmpOrdrCancelRead(ordrNumbrSeq);
		return empOrdrVo;
	}

	/**
	 * 주문 취소 상세 조회의 상품 리스트 조회하는 메서드
	 *
	 * @param int
	 * @return List<OrdrValueObject>
	 * @exception Exception
	 */
	public List<OrdrValueObject> selectOrdrCancelProdctList(int ordrNumbrSeq)
			throws Exception {
		Logger.info(null);
		List<OrdrValueObject> ordrCancelProdctList = appMyPgDao.selectOrdrCancelProdctList(ordrNumbrSeq);
		return ordrCancelProdctList;
	}

	/**
	 * 공동구매 주문 취소 상세 조회의 상품 리스트 조회하는 메서드
	 *
	 * @param int
	 * @return List<OrdrValueObject>
	 * @exception Exception
	 */
	public List<OrdrValueObject> selectDahamgaeOrdrCancelProdctList(
			int ordrNumbrSeq) throws Exception {
		Logger.info(null);
		List<OrdrValueObject> ordrCancelProdctList = appMyPgDao
				.selectDahamgaeOrdrCancelProdctList(ordrNumbrSeq);
		return ordrCancelProdctList;
	}

	/**
	 * 공동구매 주문 취소 상세 조회하는 메서드
	 *
	 * @param int
	 * @return OrdrValueObject
	 * @exception Exception
	 */
	public OrdrValueObject selectDahamgaeOrdrCancelRead(int ordrNumbrSeq)
			throws Exception {
		Logger.info(null);
		OrdrValueObject empOrdrVo = appMyPgDao
				.selectDahamgaeOrdrCancelRead(ordrNumbrSeq);
		return empOrdrVo;
	}

	/**
	 * 주문 상세 조회하는 메서드
	 *
	 * @param int
	 * @return OrdrValueObject
	 * @exception Exception
	 */
	public OrdrValueObject selectEmpOrdrRead(int ordrNumbrSeq, int ordrType)
			throws Exception {
		Logger.info(null);
		OrdrValueObject empOrdrVo = appMyPgDao.selectEmpOrdrRead(ordrNumbrSeq,
				ordrType);
		return empOrdrVo;
	}

	/**
	 * 회원정보 상세 조회하는 메서드
	 *
	 * @param int
	 * @return EmpValueObject
	 * @exception Exception
	 */
	public EmpValueObject selectEmpInfo(int empSeq) throws Exception {
		Logger.info(null);
		EmpValueObject selectEmpInfo = appMyPgDao.selectEmpInfo(empSeq);
		return selectEmpInfo;
	}

	/**
	 * 회원정보 확인 값 조회하는 메서드
	 *
	 * @param EmpValueObject
	 * @return int
	 * @exception Exception
	 */
	public EmpValueObject selectEmpInfoCount(EmpValueObject empVo)
			throws Exception {
		Logger.info(null);
		// 기본 pw
		String W_ORG_FG = empVo.getPw(); // 암호화할 문자열
		// 암호화 pw
		String EN_ORG_FG = base64.encrypt(W_ORG_FG, encryptKey);
		empVo.setPw(EN_ORG_FG);
		EmpValueObject selectEmpInfo = appMyPgDao.selectEmpInfoCount(empVo);
		// 복호화 pw
		String pw = base64.decrypt(selectEmpInfo.getPw(), decryptKey);
		selectEmpInfo.setPw(pw);
		System.out.println("pw 는 " + pw);
		return selectEmpInfo;
	}

	/**
	 * 회원정보 수정하는 메서드
	 *
	 * @param EmpValueObject
	 * @return void
	 * @exception Exception
	 */
	public void updateEmpInfo(EmpValueObject empVo) throws Exception {
		Logger.info(null);
		appMyPgDao.updateEmpInfo(empVo);
	}

	/**
	 * 회원정보 비밀번호 수정하는 메서드
	 *
	 * @param EmpValueObject
	 * @return void
	 * @exception Exception
	 */
	public void updateEmpPw(EmpValueObject empVo) throws Exception {
		Logger.info(null);
		// 기본 pw
		String W_ORG_FG = empVo.getPw(); // 암호화할 문자열
		// 암호화 pw
		String EN_ORG_FG = base64.encrypt(W_ORG_FG, encryptKey);
		empVo.setPw(EN_ORG_FG);
		appMyPgDao.updateEmpPw(empVo);
	}

	public String ordrImgUpload(MultipartHttpServletRequest request, OrdrValueObject ordrVo) throws Exception {
		Map<String, MultipartFile> files = request.getFileMap();
		ServletContext context = request.getSession().getServletContext();
		int random = (int) (Math.random() * 100000000);
		Date d = new Date();
        
        String s = d.toString();
        System.out.println("현재날짜 : "+ s);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("현재날짜 : "+ sdf.format(d));
		
		String fileNme = ordrVo.getOrdrNumbrSeq() + "_" + random + "_" + sdf.format(d); 
		String filePath = "";
		String dirPath = "";

		CommonsMultipartFile cmf = (CommonsMultipartFile) files
				.get("ordrImgFile");

		filePath = "resources/image/cart/" + ordrVo.getOrdrNumbrSeq() + "/" + fileNme + ".jpg";
		dirPath = "resources/image/cart/" + ordrVo.getOrdrNumbrSeq();
		ordrVo.setCartImg1(filePath);

		File fileSaver = new File(context.getRealPath(filePath));
		File dirSaver = new File(context.getRealPath(dirPath));
		System.out.println(context.getRealPath(filePath));
		// 파일 업로드 처리 완료.
		try {
			if (!fileSaver.exists()) {
				dirSaver.mkdir();
			}
			cmf.transferTo(fileSaver);
			// insert method
			System.out.println("성공");
		} catch (Exception e) {
			System.out.println("실패: " + e);
		}
		ordrVo.setCartImg1(filePath);
		appMyPgDao.updateOrdrImg(ordrVo); //DB의 이미지 경로 수정
		return filePath;
	}

	public void ordrImgDelete(OrdrValueObject ordrVo) throws Exception {
		if (ordrVo.getCartImg1() != null) {
			File file = new File(ordrVo.getCartImg1());
			System.out.println(ordrVo.getCartImg1());
			if (!file.exists()) {
				return;
			}
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				files[i].delete();
			}
			file.delete();
		}
	}
}
