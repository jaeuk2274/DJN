<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
* 메인 화면 JSP 입니다.
* 
* history : 김민아, 1.0, 2016/08/08 - 초기 작성
* author : 김민아
* version : 1.0, 2016/08/08  - 초기 작성
* see : 메인 화면 
//-->
<!DOCTYPE html>
	<html>
		<head> 
			<title>대신 장봐주는 남자</title>
			<meta charset="UTF-8">
			<meta name="Generator" content="EditPlus®">
			<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
			<meta name="viewport" content="width=device-width, initial-scale=1"> 
			<meta name="appple-mobile-web-app-capable" content="yes">
			<meta name="subject" content="대신 장봐주는 남자" />
			<meta name="keywords" content="대신 장봐주는 남자" />
			<meta name="description" content="대신 장봐주는 남자" /> 
			
			<link rel="stylesheet" type="text/css" href="../../../resources/common/css/style.css">
			
			<script type="text/javascript" src="../../../resources/common/js/jquery-1.10.2.min.js"></script>
			<script type="text/javascript" src="../../../resources/common/js/jquery-ui.min.js"></script>
<!-- 			<script type="text/javascript" src="../../../resources/common/js/onlypub.js"></script> -->
			<script type="text/javascript" src="../../../resources/common/js/jquery.fullPage.js"></script>
			<script type="text/javascript" src="../../../resources/common/js/jquery.slimscroll.min.js"></script>
			<script type="text/javascript" src="../../../resources/common/js/base.js"></script>
			<script type="text/javascript" src="../../../resources/common/js/commonFnc.js"></script>
		</head> 
		<body id="subContent"> 
			<div id="skipToContent"> 
				<a href="#containerM">메인콘텐츠 바로가기</a> 
			</div>
			<div id="wrap">   
				<!-- 상단 바 -->	
				 <header>
					<script type="text/javascript">
						
						function logout() {
							location.href="/logout.do";
						}
						
					</script>

					<div class="topGroup"> 
						<div> 
							<div class="mobileBtn"><button type="button">대백마트 파호점</button></div>
							<ul class="leftMenu">
								<li class="on"><a href="/MainFrame.do" style="padding-left:20px;">대백마트 파호점</a></li> 
							</ul>
							<ul class="rightMenu">
								<li><a href="" title="로그인">안녕하세요 ♡</a></li>
								<li><a href="" target="_blank" title="새창열림">${loginUserInfo.empNme}님</a></li> 
								<li><a href="" target="_blank" title="새창열림">(${loginUserInfo.empId})</a></li> 
								<li class="last on"><a href="javascript:logout();">로그아웃</a></li>
							</ul> 
							<p><button type="button" class="topAd">열기</button></p>
						</div>
					</div> 
					<div class="gnbWrap" id="gnb_">
						<span class="gnbBtnLine">gnb라인</span>
						<span class="allMenuBtnLine">전체메뉴라인</span> 
						<div class="gnbArea">
							<span class="gnbOverLine">gnb메뉴라인</span>
							<h1><a href="/MainFrame.do"><img src="../../resources/image/common/debec.png" alt="대백마트 파호점" width="80%"></a></h1>
							<nav>
								<ul> 
									<li class="menu01 "><a href="#gnb" >매장관리</a>
										<div class="innerWrap">
											<div class="inner">
												<ul>
													<li class="baseOn" ><a href=""  class="depth2y">직원 관리</a>
														<ul>
															<li class="baseOn"><a href="/empList.do">직원 목록</a></li>
															<li class=""><a href="/DJNAdmin.do">이달의 대장남 관리</a></li>
														</ul>
													</li>
													<li class="" ><a href=""  class="depth2y">휴가 관리</a>
														<ul>
															<li class="baseOn"><a href="/vactnList.do">휴가 조회</a></li>
														</ul>
													</li>  
													<li class="" ><a href=""  class="depth2y">일정 관리</a>
														<ul>
															<li class="baseOn"><a href="/SchedlList.do">월간 일정</a></li>
														</ul>
													</li>  
													<li class="" ><a href=""  class="depth2y">이용약관 관리</a>
														<ul>
															<li class="baseOn"><a href="/UseAgremtList.do">이용약관 설정</a></li>
															<li class=""><a href="/EtcAgremtList.do">기타 이용약관 설정</a></li>
														</ul>
												
													</li>  
													<li class="" ><a href=""  class="depth2y">대신 장봐주는 남자</a>
														<ul>
															<li class="baseOn"><a href="/DJN.do">이달의 대장남</a></li>
															<li class=""><a href="/empRecmnd.do">칭찬합시다</a></li>
														</ul>
													</li>  
												</ul>			
												<div class="bannerAreaL">
													<a href="" ><img src="" alt=""></a>
												</div>
												<div class="bannerAreaR">
													<a href="" ><img src="" alt=""></a>
												</div>
												<p><button type="button" class="gnbClose">닫기</button></p>
											</div>
										</div>  
									</li>
									<li class="menu02 "><a href="#gnb" >상품관리</a>
										<div class="innerWrap">
											<div class="inner">
												<ul>
													<li class="baseOn" ><a href=""  class="depth2y">상품 관리 </a>
														<ul>
															<li class="baseOn"><a href="/selProdctList.do">판매 상품 목록</a></li>
															<li class=""><a href="/selStopProdctList.do">판매 중지 상품 목록</a></li>
														</ul>
													</li>  
													<li class="" ><a href=""  class="depth2y">대백제 관리</a>
														<ul>
															<li class="baseOn"><a href="/debecFestivalList.do">대백제 목록</a></li>
														</ul>
													</li>  
													<li class="" ><a href=""  class="depth2y">진열 관리</a>
														<ul>
															<li class="baseOn"><a href="/mainDisplayList.do">메인 상품 진열 목록</a></li>
															<li class=""><a href="/hotdlList.do">핫딜 상품 목록</a></li>
															<li class=""><a href="/freshList.do">신선 식품 목록</a></li>
															<li class=""><a href="/packgList.do">패키지 목록</a></li>
														</ul>
													</li>  
													<li class="" ><a href=""  class="depth2y">다함께 관리</a>
														<ul>
															<li class="baseOn"><a href="/togthrProdctList.do">다함께 상품 진열 목록</a></li>
														</ul>
													</li>  
												</ul>
												<div class="bannerAreaL">
													<a href="" ><img src="" alt=""></a>
												</div>
												<div class="bannerAreaR">
													<a href="" ><img src="" alt=""></a>
												</div>
												<p><button type="button" class="gnbClose">닫기</button></p>
											</div>
										</div>  
									</li>
									<li class="menu03 "><a href="#gnb" >거래관리</a>
										<div class="innerWrap">
											<div class="inner">
												<ul>
													<li class="baseOn" ><a href=""  class="depth2y">거래처 관리</a>
														<ul>
															<li class="baseOn"><a href="/ClintList.do" >거래처 목록</a></li>
														</ul> 
													</li>  
													<li class="" ><a href=""  class="depth2y">입고 거래장 관리</a>
														<ul>
															<li class="baseOn"><a href="/InstckExchngFlorList.do" >입고 거래장 목록</a></li>
														</ul> 
													</li>  
													<li class="" ><a href=" "  class="depth2y">재고 관리</a>
														<ul>
															<li class="baseOn"><a href="/stckList.do" >재고 목록</a></li>
														</ul> 
													</li>  
												</ul>
												<div class="bannerAreaL">
													<a href="" ><img src="" alt=""></a>
												</div>
												<div class="bannerAreaR">
													<a href="" ><img src="" alt=""></a>
												</div>
												<p><button type="button" class="gnbClose">닫기</button></p>
											</div>
										</div>  
									</li>
									<li class="menu04 "><a href="#gnb" >고객관리</a>
										<div class="innerWrap">
											<div class="inner">
												<ul>
													<li class="baseOn" ><a href=".jsp"  class="depth2y">고객 관리</a>
														<ul>
															<li class="baseOn"><a href="/custmrList.do" >고객 목록</a></li>
															<li class=""><a href="/quscncCustmrList.do" >휴먼 고객 목록</a></li>
														</ul> 
													</li>  
													<li class="" ><a href=".jsp"  class="depth2y">주문 내역 관리</a>
														<ul>
															<li class="baseOn"><a href="/callOrderCreateRead.do" >전화 주문 등록</a></li>
															<li class=""><a href="/realTimeOrdrRecrdList.do" >실시간 주문 내역</a></li>
															<li class=""><a href="/ordrRecrdList.do" >주문 내역</a></li>
															<li class=""><a href="/ordrCanclRecrdList.do" >주문 취소 내역</a></li>
														</ul> 
													</li>  
													<li class="" ><a href=".jsp"  class="depth2y">푸시 알림 관리</a>
														<ul>
															<li class="baseOn"><a href="/pushNotfcatnMain.do" >푸시 알림</a></li>
															<li class=""><a href="/pushNotfcatnDespSelectList.do" >푸시 알림 발송</a></li>
															<li class=""><a href="/pushNotfcatnDespRecrdList.do" >푸시 알림 발송 내역</a></li>
															<li class=""><a href="/pushNotfcatnTempltSelectList.do" >푸시 알림 템플릿</a></li>
														</ul> 
													</li>  
												</ul>
												<div class="bannerAreaL">
													<a href="" ><img src=""></a>
												</div>
												<div class="bannerAreaR">
													<a href="" ><img src=""></a>
												</div>
												<p><button type="button" class="gnbClose">닫기</button></p>
											</div>
										</div>  
									</li>
									<li class="menu05 "><a href="#gnb" >마감관리</a>
										<div class="innerWrap">
											<div class="inner">
												<ul>
													<li class="baseOn" ><a href="/TodyAnalList.do"  class="depth2n">TODAY 리포트</a>
													</li>  
													<li class="" ><a href=""  class="depth2y">상품 분석</a>
														<ul>
															<li class="baseOn"><a href="/SelProdctAnalList.do" >판매 상품 분석</a></li>
															<li class=""><a href="/SelCatgrAnal.do" >판매 분류 분석</a></li>
															<li class=""><a href="/ExchngRefndAnalList.do" >교환/환불 분석</a></li>
														</ul> 
													</li>  
													<li class="" ><a href=""  class="depth2y">주문 분석</a>
														<ul>
															<li class="baseOn"><a href="/DayOrdrAnal.do" >일별 주문 분석</a></li>
															<li class=""><a href="/MonthOrdrAnal.do" >월별 주문 분석</a></li>
															<li class=""><a href="/ProdctOrdrAnal.do" >상품별 주문 분석</a></li>
														</ul> 
													</li>  
													<li class="" ><a href=""  class="depth2y">매출 분석</a>
														<ul>
															<li class="baseOn"><a href="/DaySalsAnal.do" >일별 매출 분석</a></li>
															<li class=""><a href="/MonthSalsAnal.do" >월별 매출 분석</a></li>
														</ul> 
													</li>  
												</ul>
												<div class="bannerAreaL">
													<a href="" ><img src="" alt=""></a>
												</div>
												<div class="bannerAreaR">
													<a href="" ><img src="" alt=""></a>
												</div>
												<p><button type="button" class="gnbClose">닫기</button></p>
											</div>
										</div>  
									</li>
									<li class="menu06 "><a href="#gnb" >개인 정보 관리</a>
										<div class="innerWrap">
											<div class="inner">
												<ul>
													<li class="baseOn" ><a href=""  class="depth2y">개인 정보 관리</a>
														<ul>
															<li class="baseOn"><a href="/pwCheckRead.do" >개인 정보 조회</a></li>
															<li class=""><a href="/persnlVactnList.do" >휴가 조회</a></li>
														</ul> 
													</li>  
												</ul>
												<div class="bannerAreaL">
													<a href="" ><img src="" alt=""></a>
												</div>
												<div class="bannerAreaR">
													<a href="" ><img src="" alt=""></a>
												</div>
												<p><button type="button" class="gnbClose">닫기</button></p>
											</div>
										</div>  
									</li>
								</ul>	
							</nav>
							<span><button type="button">검색</button></span><!-- 닫기 클래스  class="close"-->
							<p><button type="button">전체메뉴</button></p> 
						</div>  
					</div><!-- This page is generated by CMS -->
				
				
					<!--전체메뉴-->
					<div class="allMenuWrap">
						<div class="allMenu">
							<div>
								 <ul> 
									<li class="menu01"><a href="">매장관리</a> 
										<ul>
											<li><a href="" >직원관리</a>
												<ul>
													<li><a href="/empList.do" >- 직원 목록</a></li> 
													<li><a href="/DJNAdmin.do">이달의 대장남 관리</a></li> 
												</ul> 
											</li>  
											<li><a href="" >휴가관리</a>
												<ul>
													<li><a href="/vactnList.do" >- 휴가 조회</a></li> 
												</ul> 
											</li>  
											<li><a href="" >일정관리</a>
												<ul>
													<li><a href="/SchedlList.do" >- 월간 일정</a></li> 
												</ul>
											</li>  
											<li><a href="" >이용약관 관리</a>
												<ul>
													<li><a href="/UseAgremtList.do" >- 이용약관 설정</a></li> 
													<li><a href="/EtcAgremtList.do" >- 기타 이용약관 설정</a></li> 
												</ul>
											</li>  
											<li><a href="" >대신 장봐주는 남자</a>
												<ul>
													<li><a href="/DJNAdmin.do">이달의 대장남 관리</a></li> 
													<li><a href="/empRecmnd.do">칭찬합시다</a></li> 
												</ul>
											</li>  
										</ul>  
									</li> 
									<li class="menu02"><a href="">상품관리</a> 
										<ul>
											<li><a href= >상품 관리</a>
												<ul>
													<li><a href="/selProdctList.do" >- 판매 상품 목록</a></li> 
													<li><a href="/selStopProdctList.do" >- 판매 중지 상품 목록</a></li> 
												</ul> 
											</li>  
											<li><a href="" >대백제 관리</a>
												<ul>
													<li><a href="/debecFestivalList.do" >- 대백제 목록</a></li> 
												</ul> 
											</li>  
											<li><a href="" >진열 관리</a>
												<ul>
													<li><a href="/mainDisplayList.do">메인 상품 진열 목록</a></li>
													<li><a href="/hotdlList.do">핫딜 상품 목록</a></li>
													<li><a href="/freshList.do">신선 식품 목록</a></li>
													<li><a href="/packgList.do">패키지 목록</a></li> 
												</ul> 
											</li>  
											<li><a href="" >다함께 관리</a>
												<ul>
													<li><a href="/togthrProdctList.do" >- 다함께 상품 진열 목록</a></li> 
												</ul> 
											</li>  
										</ul>  
									</li> 
									<li class="menu03"><a href="">거래관리</a> 
										<ul>
											<li><a href="" >거래처 관리</a>
												<ul>
													<li><a href="/ClintList.do" >- 거래처 목록</a></li> 
												</ul> 
											</li>  
											<li><a href="" >입고 거래장 관리</a>
												<ul>
													<li><a href="/InstckExchngFlorList.do" >- 입고 거래장 목록</a></li> 
												</ul> 
											</li>  
											<li><a href=" " >재고 관리</a>
												<ul>
													<li><a href="/stckList.do" >- 재고 목록</a></li> 
												</ul> 
											</li>  
										</ul>  
									</li> 
									<li class="menu04"><a href="">고객관리</a> 
										<ul>
											<li><a href="" >회원 관리</a>
												<ul>
													<li><a href="/custmrList.do" >- 고객 목록</a></li> 
													<li><a href="/quscncCustmrList.do" >- 휴먼 고객 목록</a></li> 
												</ul> 
											</li>  
											<li><a href="" >주문 내역 관리</a>
												<ul>
													<li><a href="/callOrderCreateRead.do" >- 전화 주문 등록</a></li> 
													<li><a href="/realTimeOrdrRecrdList.do" >- 실시간 주문 내역</a></li> 
													<li><a href="/ordrRecrdList.do" >- 주문 내역</a></li> 
													<li><a href="/ordrCanclRecrdList.do" >- 주문 취소 내역</a></li> 
												</ul> 
											</li>  
											<li><a href="" >푸시 알림 관리</a>
												<ul>
													<li><a href="" >- 푸시 알림</a></li> 
													<li><a href="" >- 푸시 알림 발송</a></li> 
													<li><a href="" >- 푸시 알림 발송 내역</a></li> 
													<li><a href="" >- 푸시 알림 템플릿</a></li> 
												</ul> 
											</li>  
										</ul>  
									</li> 
									<li class="menu05"><a href="">마감관리</a> 
										<ul>
											<li><a href="/TodyAnalList.do" >TODAY 리포트</a>
											</li>
											<li><a href="/SelProdctAnalList.do" >상품 분석</a>
												<ul>
													<li><a href="/SelProdctAnalList.do" >- 판매 상품 분석</a></li> 
													<li><a href="" >- 판매 분류 분석</a></li> 
													<li><a href="/ExchngRefndAnalList.do" >- 교환/환불 분석</a></li> 
												</ul> 
											</li> 
											<li><a href="" >주문 분석</a>
												<ul>
													<li><a href="" >- 일별 주문 분석</a></li> 
													<li><a href="" >- 월별 주문 분석</a></li> 
													<li><a href="" >- 상품별 주문 분석</a></li> 
												</ul> 
											</li>  
											<li><a href="" >매출 분석</a>
												<ul>
													<li><a href="/DaySalsAnal.do" >- 일별 매출 분석</a></li> 
													<li><a href="/MonthSalsAnal.do" >- 월별 매출 분석</a></li> 
												</ul> 
											</li>   
										</ul>  
									</li> 
									<li class="menu06"><a href="">개인 정보</a> 
										<ul>
											<li><a href="" >개인 정보 관리</a>
												<ul>
													<li><a href="/pwCheckRead.do" >- 개인 정보 조회</a></li> 
													<li><a href="/persnlVactnList.do" >- 휴가 조회</a></li> 
												</ul> 
											</li>  
										</ul>  
									</li>
								</ul>
								<p><button type="button">닫기</button></p>	
							</div>  
						</div>  
					</div><!-- This page is generated by CMS -->
					<!--// 전체메뉴-->
				</header>
    			<!-- //header -->
     
				
				<div id="containerM"> 
				<!-- contents -->
					<section id="contAreaM"> 
					<!-- cneter 부분 start -->
						<div class="visualArea"> 
						<!-- 빨갱이 부분 -->
							<div class="vSection01">
								<p>
								<span>빠른 주문확인을 위한<strong>실시간 주문현황</strong></span> 
								 </p>
								<a  style="margin-left:40px" href="/realTimeOrdrRecrdList.do"><img title="" style="VERTICAL-ALIGN: baseline" src="../../resources/image/common/button01.png"></a>
							</div>
							<!-- 중간 배너 부분 -->
							<div class="vBanner" style="max-width: 100%;  max-height:100%;" >
								<ul style="max-width: 100%;  max-height:100%; width: 100%;  height:100%;" >
									<li class="ban01" style="width: 100%;  height:100%;" >
										<p class="size1280"><img src="../../resources/image/common/632.png" style="width: 100%;  height:100%;"  alt="" usemap="#Map1_1">
											<map name="Map1_1">
											</map>
										</p>
										<p class="size1024"><img src="../../resources/image/common/708.png" style="width: 100%;  height:100%;"  alt="" usemap="#Map1_2">
											<map name="Map1_2">
											</map>
										</p>
										<p class="size768"><img src="../../resources/image/common/720.png" style="width: 100%;  height:100%;"  alt="" usemap="#Map1_3">
											<map name="Map1_3">
											</map>
										</p>
										<p class="size320"><img src="../../resources/image/common/920.png" style="width: 100%;  height:100%;" alt="" usemap="#Map1_4">
											<map name="Map1_4">
											</map> 
										</p>
									</li>
								</ul>
								<p class="page">
									<a href="#" class="num on">1</a>
									<a href="#" class="stop" id="playCtrl">stop</a>
								</p>
							</div>
							<div class="vSection02">
								<p><span><strong></strong></span> 
								<a href=""></a> 
								</p>
								<a  style="margin-left:40px" href="/TodyAnalList.do"><img title="" style="VERTICAL-ALIGN: baseline" src="../../resources/image/common/button02.png"></a>
							</div> 
							<div class="vSection03">
								<p><span><strong></strong></span> 
									<a href=""></a> 
								</p>
								<a  style="margin-left:40px" href="/SchedlList.do"><img title="" style="VERTICAL-ALIGN: baseline" src="../../resources/image/common/button03.png"></a>
							</div>
							<div class="vSection04">
								<p><span><strong></strong></span> 
									<a href=""></a> 
								</p>
								<a  style="margin-left:40px" href="/pushNotfcatnMain.do"><img title="" style="VERTICAL-ALIGN: baseline" src="../../resources/image/common/button04.png"></a>
							</div>
						</div>
						<!-- section1 end -->
				</section> 
				<!-- contents --> 
			</div>
			<!-- //container -->  
	
	 		<!-- footer -->  
			<footer>
				<div class="footer">
					<div class="sectionN01">
<!-- 						<ul> -->
<!-- 							<li><a href="">파호점 소개</a> </li> -->
<!-- 							<li><a href="">오시는 길</a> </li> -->
<!-- 							<li><a href="">대신 장봐주는 남자</a> </li> -->
<!-- 						</ul> -->
					</div>
					<div class="sectionN02">
						<div class="copy">
							<address><span>대백마트 파호점 | </span> <span>대표자 : 안혜숙, 김대용 | </span> <span>주소 : 대구광역시 달서구 달구벌대로 199길 7 (파호동)</span> </address>
							<p>Copyright ⓒ KOREA DEBEC PAHO</p>
						</div>
					</div>
				</div>
			</footer>
			<!-- //footer -->  
		</div>
		<!-- //wrap -->
		
		<!-- 모바일 메뉴 -->
		<div class="mobileMenu">
			<div>
				<button type="button">닫기</button>
			</div> 
			<ul> 
				<li class="menu01"><a href="">매장관리</a> 
					<ul>
						<li  class="disPlus"><a href="/empList.do" >직원 관리</a></li>  
						<li  class="disPlus"><a href="/vactnList.do" >휴가 관리</a></li>  
						<li  class="disPlus"><a href="/SchedlList.do" >일정 관리</a></li>  
						<li  class="disPlus"><a href="/UseAgremtList.do" >이용약관 관리</a></li>  
						<li  class="disPlus"><a href="" >대신 장봐주는 남자</a></li>    
					</ul>  
				</li> 
				<li class="menu02"><a href="">상품관리</a> 
					<ul>
						<li  class="disPlus"><a href="" >상품 관리</a></li>  
						<li  class="disPlus"><a href="/debecFestivalList.do" >대백제 관리</a></li>  
						<li  class="disPlus"><a href="" >진열 관리</a></li>  
						<li  class="disPlus"><a href="/togthrProdctList.do" >다함께 관리</a></li> 
					</ul>  
				</li> 
				<li class="menu03"><a href="">거래관리</a> 
					<ul>
						<li  class="disPlus"><a href="/ClintList.do" >거래처 관리</a></li>  
						<li  class="disPlus"><a href="/InstckExchngFlorList.do" >입고 거래장 관리</a></li> 
						<li  class="disPlus"><a href="/stckList.do" >재고 관리</a></li>  
					</ul>  
				</li> 
				<li class="menu04"><a href="">고객관리</a> 
					<ul>
						<li  class="disPlus"><a href="/custmrList.do" >고객 관리</a></li>  
						<li  class="disPlus"><a href="/ordrRecrdList.do" >주문 내역 관리</a></li>  
						<li  class="disPlus"><a href="/pushNotfcatnMain.do" >푸시 알림 관리</a></li> 
					</ul>  
				</li> 
				<li class="menu05"><a href="">마감관리</a> 
					<ul>
						<li  class="disPlus"><a href="" >TODAY 리포드</a></li>  
						<li  class="disPlus"><a href="" >상품 분석</a></li>
						<li  class="disPlus"><a href="" >주문 분석</a></li>  
						<li  class="disPlus"><a href="" >매출 분석</a></li>  
					</ul>  
				</li> 
				<li class="menu05"><a href="">개인 정보</a> 
					<ul>
						<li  class="disPlus"><a href="/pwCheckRead.do" >개인 정보 관리</a></li>  
					</ul>  
				</li> 
			</ul>
		</div><!-- This page is generated by CMS -->
		<!-- //모바일 메뉴 -->
	</body>
</html>