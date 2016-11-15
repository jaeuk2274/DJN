$(document).on("pageshow","#cusDahamgaePage",function(){
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
	$.ajax({
		type:"POST",
		url:"http://sebm.iptime.org:9124/appTogthrProdctCount.do",
		success:function(data){
			dahamgaeCount = data;
			if(dahamgaeCount == 0) {
				dahamgaeNotInfo();
			}
			else {
				cusDahamgaeList();
			}
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
});

function cusDahamgaeList() {
	$.ajax({
	    type:"POST",
	    url:"http://sebm.iptime.org:9124/appTogthrProdctList.do",
	    success:function(data){
	       $.each(data, function(i, vo){
	       	// 다함께 상품 목록 리스트
	    	var gropPurchsProdctSeq = vo.gropPurchsProdctSeq //공동상품seq
	       	var gropPurchsSeq = vo.gropPurchsSeq; // 공동구매seq
	       	var prodctMainImage = vo.prodctMainImage; //메인 이미지
	       	var titl = vo.titl; //공동구매 제목
	       	var prodctNme = vo.prodctNme; //상품명
	       	var selPric = numberWithCommas(vo.selPric);//판매가
	       	var joinPersns =vo.joinPersns; //참여인원
				$("#cusDahamgaeList").append("<li style='padding-top: 0px; padding-bottom: 10px;'>"+
												"<hr style='margin-top: 0px;'>"+
												"<div style='position: relative; padding-left: 10px; padding-right: 10px; height: 200px;'>"+
													"<a onclick='appCusDahamgaeDtlRead("+gropPurchsProdctSeq+")' align='center' style='width: 100%'>"+
														"<div style='display: table-cell; height: 140px; vertical-align: top;' align='center'>"+
															"<img src='http://sebm.iptime.org:9124/"+prodctMainImage+"' style='width:80%; height:100%'/>"+
														"</div>"+
													"</a>"+
										            "<div class='col-xs-6' style='display: table-cell; padding: 4px 0px 0  15px; vertical-align: top;'>"+
										            	"<a onclick='appCusDahamgaeDtlRead("+gropPurchsProdctSeq+")' style='color: #636566; text-decoration: none;'>"+
															"<div style='white-space:normal; display: -webkit-box; overflow: hidden; width: 240px;height: 20px; padding-right: 15px; line-height: 20px; color: #666; -webkit-line-clamp: 2; -webkit-box-orient: vertical; word-break: break-all; word-wrap: break-word;'>"+
																"<span>"+titl+"</span>"+
															"</div>"+
															"<div style='margin: 0 0 -4px; line-height: 27px; color: #262f33;'>"+
																"<em class='font-xlg' style='font-style: normal; font-family:emm_bol; font-weight: bold; letter-spacing: -1px; color: #262f33;'>"+selPric+"원</em>"+
															"</div> "+
										               "</a>"+
										           "</div>"+
										           "<div style='position: absolute; top: 170px; right: 10px;'>"+
														"<span style='padding: 0; width: 53px; height: 53px; background-position: 0 -400px;'>"+
															"참여인원 <span class='font-lg' style='color:red'><strong>"+joinPersns+"명</strong></span>"+
														"</span>"+
										            "</div>"+
												"</div>"+
											"</li>");
	       });
			$("#cusDahamgaeList").listview("refresh");
	    },
	    error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	});
}

//다함께 행사가 없을 경우
function dahamgaeNotInfo(){
	$("#dahamgaeContent").append("" + 
		"<div class='nd2-card'>" +
			"<div class='card-media'>" +
				"<img src='../img/thank_you.jpg' style='width:100%'/>" +
			"</div>" +
			"<div class='card-supporting-text'>" + 
				"<h4 class='nd2-headline font-lg' style='text-align:center'>" + 
					" 더 <span class='font-xlg' style='color:#8BC34A'><b>싸고</b></span>" +
					" 더 <span class='font-xlg' style='color:#8BC34A'><b>좋은</b></span> 상품" +
					"<br>" +
					"<br>" + 
					" 새로운 <span class='font-xlg' style='color:#8BC34A'><b>다함께</b></span>로 찾아 뵙겠습니다." + 
				"</h4>" +
			"</div>" +
		"</div>" + 
	"").trigger("create");
};

//고객 다함께 상품 상세 조회
function appCusDahamgaeDtlRead(gropPurchsProdctSeq) {
	var gropPurchsProdctSeq = gropPurchsProdctSeq;
	sessionStorage.setItem('gropPurchsProdctSeq', gropPurchsProdctSeq);
	$.mobile.changePage("MarketCusDahamgaeRead.html");
};

//고객 다함께 상품 상세 조회
$(document).on('pageshow', "#cusDahamgaeReadPage", function (event, data) {
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
	var gropPurchsProdctSeq = sessionStorage.getItem('gropPurchsProdctSeq');

	formData = {"gropPurchsProdctSeq":gropPurchsProdctSeq};
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appCusTogthrDetail.do",
		success:function(data){
        	// 다함께 상품 상세보기
			var prodctSeq = data.gropPurchsProdctSeq; //공동구매 상품 SEQ
			var prodctMainImage = data.prodctMainImage; //메인 이미지
			var prodctDetlImageOne = data.prodctDetlImageOne; //상세이미지1
			var prodctDetlImageTwo = data.prodctDetlImageTwo; //상세이미지2
			var prodctDetlImageThree = data.prodctDetlImageThree; //상세이미지3
			var prodctNme = data.titl; //공동구매 제목
			var prodctSelPric = numberWithCommas(data.selPric); //공동구매 판매가
			var prodctSalePric = 0;
			var selStrtTime = data.ordrStarDat; //공동구매 판매시작 시간
			var selEndTime = data.ordrEndDat; //공동구매 판매종료 시간
			var stat = data.stat;// 판매상태
        	var minOrdrAmont = data.minOrdrAmont;// 주문 최소 수량
        	var maxOrdrAmont = data.maxOrdrAmont;// 주문 최대 수량
			var joinPersns =data.joinPersns; //공동구매 참여인원
			var percent =joinPersns/maxOrdrAmont*100 //공동구매 참여 퍼센트
			var percent = percent+"%";
			
			var prodctType = 0; //공동구매 타입 정해야 함!!!!
			
			//공동구매 타이머 작동 기능 시작
			var selEndTime = selEndTime.replace( /-/gi, "/");
			dahamgaeCountDownTimer(""+selEndTime+" 23:59", 'countdown');
			//공동구매 타이머 작동 기능 끝
			$("#prodctCount").append("<div class='chd_count_obj' align='center'>" +
						"<strong>남은 시간 :  <span id='daysCounter'></span>일 <span id='hoursCounter'></span>시간 <span id='minutesCounter' ></span>분  남음</strong>" +
							"</div>").trigger("create");
			
			$("#prodctTitle").append("" +
					"<h3 class='font-md'>"+prodctNme+"</h3>" +
					"<dl id='price'>" +
						"<dd>" +
							"<span class='sale font-xlg'>" + prodctSelPric +"</span><span>원</span>" +
						"</dd>" +
					"</dl>" +
			"").trigger("create");
			
			$("#prodctMainImg").append("" +
					"<img src='http://sebm.iptime.org:9124/"+prodctMainImage+"' style='width:100%; height:300px;'/>" +
			"").trigger("create");
			
			$("#progressBar").append("" +
					"<div class='col-xs-12'>"+
						"<div class='progress'>"+
							"<div align='center' class='one primary-color'><br><br>"+minOrdrAmont+"</div> <div align='center' class='two danger-color'><br><br>"+maxOrdrAmont+"</div>"+
							"<div id='progressPercentBar' class='progress-bar' style='width:"+percent+"'></div>"+
						"</div>"+
						"<div class='bx_prd2'>"+
							"<br> <img src='../img/bluePoint.png'  style='width:30px; height:30px'/> 제품 주문에 필요한 최소 주문수량"+
							"<br> <img src='../img/blackPoint.png'  style='width:30px; height:30px'/> 제품 주문 가능한 최대 주문수량"+
						"</div>"+
					"</div>"+
				"").trigger("create");
			
			$("#prodctCart").append("" +
					"<div class='bx_prd'>" +
						"<div class='prd_amount'>" +
							"<a href='#' class='b_minus ui-link waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button' onclick='javascript:unitDown(this);'>빼기</a>" +
							"<span class='opa_area'>" +
								"<label class='opa_tx'>1</label>" +
								"<span class='opa_select'>" +
									"<select onchange='javascript:unitSelected($(this));'>" +
										"<option>1</option>" +
										"<option>2</option>" +
										"<option>3</option>" +
										"<option>4</option>" +
										"<option>5</option>" +
										"<option>6</option>" +
										"<option>7</option>" +
										"<option>8</option>" +
										"<option>9</option>" +
										"<option>10</option>" +
									"</select>" +
								"</span>" +
							"</span>" +
							"<a href='#' class='sp_com b_plus ui-link waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button' onclick='javascript:unitUp(this);'>더하기</a>" +
						"</div>" +
					"</div>" +
				"").trigger("create");
			
			$("#prodctPric").append("" +
				"<div class='bx_prd2'>" +
					"<h4 class='m_mid_total'>" +
						"<strong class='label font-md'>합계</strong>" +
						"<strong class='totalPrice' id='totalPrice' style='margin-left: 10px;'>" +
							"<em class='font-xlg' style='font-family: emm_bol; font-weight: bold;'>"+prodctSelPric+"</em>" +
							"<span>원</span>" +
						"</strong>" +
					"</h4>" +
				"</div>" +
			"").trigger("create");
			
			$("#prodctBuy").append("" +
					"<ul>" +
						"<li>" +
							"<a href='#' onclick='javascript:buyNow(this);' class='btn_ty1 font-btn ui-link waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button'>구매하기</a>" +
							"<span id='item_info' class='item_info' style='display:none'>" +
				                "<input type='hidden' name='prodctSeq' value='"+prodctSeq+"'>" +
				                "<input type='hidden' name='prodctNme' value='"+prodctNme+"'>" +
				                "<input type='hidden' name='prodctSelPric' value='"+data.selPric+"'>" +
				                "<input type='hidden' name='prodctSalePric' value='"+prodctSalePric+"'>" +
				                "<input type='hidden' name='prodctType' value='"+prodctType+"'>" +
				                "<input type='hidden' name='ordrQty' value='1'>" +
				            "</span>" +
						"</li>" +
					"</ul>" +
				"").trigger("create");
			
			$("#prodctDetailImg").append("" +
				"<div class='dtl_capture_img'>" +
					"<img src='http://sebm.iptime.org:9124/"+prodctMainImage+"'/>" +
				"</div>" +
				"<div class='dtl_capture_img'>" +
					"<img src='http://sebm.iptime.org:9124/"+prodctDetlImageOne+"'/>" +
				"</div>" +
				"<div class='dtl_capture_img'>" +
					"<img src='http://sebm.iptime.org:9124/"+prodctDetlImageTwo+"'/>" +
				"</div>" +		
				"<div class='dtl_capture_img'>" +
					"<img src='http://sebm.iptime.org:9124/"+prodctDetlImageThree+"'/>" +
				"</div>" +
			"").trigger("create");
			
			appDeliveryAgremtRead();
			appExchngAgremtRead();
			
			if(prodctSalePric != 0 && prodctSalePric != prodctSelPric) {
				$($("#price")).html("" +
					"<dd>" +
						"<span class='price-u font-md' style='text-decoration:line-through;'>" + prodctSelPric + "원</span>" +
					"</dd>" +
					"<dd>" +
						"<span id='price' class='sale font-xlg'>" + prodctSalePric +"</span><span>원</span>" +
					"</dd>" +
				"");
				
				$($("#totalPrice")).html("" +
					"<em class='font-xlg' style='font-family: emm_bol; font-weight: bold;'>"+prodctSalePric+"</em>" +
					"<span>원</span>" +
				"");
			}
			
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
});

//공동구매 바로구매
function buyNow(tag) {
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	var gropPurchsProdctSeq =  $(tag).siblings('.item_info').find("input[name=prodctSeq]").val();
	var gropPurchsProdctProdctAmont =  $(tag).siblings('.item_info').find("input[name=ordrQty]").val();
	var predictSumPrice =  $(tag).siblings('.item_info').find("input[name=prodctSelPric]").val();
	var gropPurchsProdctNme =  $(tag).siblings('.item_info').find("input[name=prodctNme]").val();
	
	gropPurchsProdctNme = gropPurchsProdctNme + gropPurchsProdctProdctAmont + "개";
	predictSumPrice = predictSumPrice * gropPurchsProdctProdctAmont;
		
	sessionStorage.setItem('custmrSeq', custmrSeq);
	sessionStorage.setItem('gropPurchsProdctSeq', gropPurchsProdctSeq);
	sessionStorage.setItem('gropPurchsProdctProdctAmont', gropPurchsProdctProdctAmont);
	sessionStorage.setItem('predictSumPrice', predictSumPrice);
	sessionStorage.setItem('gropPurchsProdctNme', gropPurchsProdctNme);
	
	$.mobile.changePage("MarketOrdrCret.html");
};

$(document).on("pageshow","#empDahamgaePage",function(){
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
	$.ajax({
		type:"POST",
		url:"http://sebm.iptime.org:9124/appTogthrProdctCount.do",
		success:function(data){
			dahamgaeCount = data;
			if(dahamgaeCount == 0) {
				dahamgaeNotInfo();
			}
			else {
				empDahamgaeList();
			}
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
});

function empDahamgaeList(){
	$.ajax({
		type:"POST",
		url:"http://sebm.iptime.org:9124/appTogthrProdctList.do",
		success:function(data){
		   $.each(data, function(i, vo){
		   	// 다함께 상품 목록 리스트
		   	var gropPurchsSeq = vo.gropPurchsSeq; // 공동구매seq
		   	var mainImg = vo.prodctMainImage; //메인 이미지
		   	var prodctNme = vo.prodctNme; //상품명
		   	var selPric = numberWithCommas(vo.selPric);//판매가
		   	var joinPersns =vo.joinPersns; //참여인원
			$("#cusDahamgaeList").append("<li style='padding-top: 0px; padding-bottom: 10px;'>"+
											"<hr style='margin-top: 0px;'>"+
											"<div style='position: relative; padding-left: 10px; padding-right: 10px; height: 200px;'>"+
												"<a onclick='appEmpDahamgaeDtlRead("+gropPurchsSeq+")' align='center' style='width: 100%'>"+
													"<div style='display: table-cell; height: 140px; vertical-align: top;' align='center'>"+
														"<img src='http://sebm.iptime.org:9124/"+mainImg+"' style='width:80%; height:100%'/>"+
													"</div>"+
												"</a>"+
									            "<div class='col-xs-6' style='display: table-cell; padding: 4px 0px 0  15px; vertical-align: top;'>"+
									            	"<a onclick='appEmpDahamgaeDtlRead("+gropPurchsSeq+")' style='color: #636566; text-decoration: none;'>"+
														"<div style='white-space:normal; display: -webkit-box; overflow: hidden; width: 240px;height: 20px; padding-right: 15px; line-height: 20px; color: #666; -webkit-line-clamp: 2; -webkit-box-orient: vertical; word-break: break-all; word-wrap: break-word;'>"+
															"<span>"+prodctNme+"</span>"+
														"</div>"+
														"<div style='margin: 0 0 -4px; line-height: 27px; color: #262f33;'>"+
															"<em class='font-xlg' style='font-style: normal; font-family:emm_bol; font-weight: bold; letter-spacing: -1px; color: #262f33;'>"+selPric+"원</em>"+
														"</div> "+
									               "</a>"+
									           "</div>"+
									           "<div style='position: absolute; top: 170px; right: 10px;'>"+
													"<span style='padding: 0; width: 53px; height: 53px; background-position: 0 -400px;'>"+
														"참여인원 <span class='font-lg' style='color:red'><strong>"+joinPersns+"명</strong></span>"+
													"</span>"+
									            "</div>"+
											"</div>"+
										"</li>");
		   });
		   $("#cusDahamgaeList").listview("refresh");
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

//관리자 다함께 상품 상세 조회
function appEmpDahamgaeDtlRead(gropPurchsSeq) {
	var gropPurchsSeq = gropPurchsSeq;
	sessionStorage.setItem('gropPurchsSeq', gropPurchsSeq);
	$.mobile.changePage("MarketEmpDahamgaeRead.html");
};

//관리자 다함께 상품 상세 조회
$(document).on('pageshow', "#empDahamgaeReadPage", function (event, data) {
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
	var gropPurchsSeq = sessionStorage.getItem('gropPurchsSeq');
	formData = {"gropPurchsSeq":gropPurchsSeq};
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appEmpTogthrDetail.do",
		success:function(data){
        	// 다함께 상품 상세보기
			var prodctSeq = data.gropPurchsProdctSeq; //공동구매 상품 SEQ
			var prodctMainImage = data.prodctMainImage; //메인 이미지
			var prodctDetlImageOne = data.prodctDetlImageOne; //상세이미지1
			var prodctDetlImageTwo = data.prodctDetlImageTwo; //상세이미지2
			var prodctDetlImageThree = data.prodctDetlImageThree; //상세이미지3
			var prodctNme = data.titl; //공동구매 제목
			var prodctSelPric = numberWithCommas(data.selPric); //공동구매 판매가
			var prodctSalePric = 0;
			var selStrtTime = data.ordrStarDat; //공동구매 판매시작 시간
			var selEndTime = data.ordrEndDat; //공동구매 판매종료 시간
			var stat = data.stat;// 판매상태
        	var minOrdrAmont = data.minOrdrAmont;// 주문 최소 수량
        	var maxOrdrAmont = data.maxOrdrAmont;// 주문 최대 수량
			var joinPersns =data.joinPersns; //공동구매 참여인원
			var percent = joinPersns/maxOrdrAmont*100 //공동구매 참여 퍼센트
			var percent = percent+"%";
			
			var prodctType = 0; //공동구매 타입 정해야 함!!!!
			
			//공동구매 타이머 작동 기능 시작
			var selEndTime = selEndTime.replace( /-/gi, "/");
			dahamgaeCountDownTimer(""+selEndTime+" 23:59", 'countdown');
			//공동구매 타이머 작동 기능 끝
			$("#prodctCount").append("<div class='chd_count_obj' align='center'>" +
						"<strong>남은 시간 :  <span id='daysCounter'></span>일 <span id='hoursCounter'></span>시간 <span id='minutesCounter' ></span>분  남음</strong>" +
							"</div>").trigger("create");
			
			$("#prodctTitle").append("" +
					"<h3 class='font-md'>"+prodctNme+"</h3>" +
					"<dl id='price'>" +
						"<dd>" +
							"<span class='sale font-xlg'>" + prodctSelPric +"</span><span>원</span>" +
						"</dd>" +
					"</dl>" +
			"").trigger("create");
			
			$("#prodctMainImg").append("" +
					"<img src='http://sebm.iptime.org:9124/"+prodctMainImage+"' style='width:100%; height:300px;'/>" +
			"").trigger("create");
			
			$("#progressBar").append("" +
					"<div class='col-xs-12'>"+
						"<div class='progress'>"+
							"<div align='center' class='one primary-color'><br><br>"+minOrdrAmont+"</div> <div align='center' class='two danger-color'><br><br>"+maxOrdrAmont+"</div>"+
							"<div id='progressPercentBar' class='progress-bar' style='width:"+percent+"'></div>"+
						"</div>"+
						"<div class='bx_prd2'>"+
							"<br> <img src='../img/bluePoint.png'  style='width:30px; height:30px'/> 제품 주문에 필요한 최소 주문수량"+
							"<br> <img src='../img/blackPoint.png'  style='width:30px; height:30px'/> 제품 주문 가능한 최대 주문수량"+
						"</div>"+
					"</div>"+
				"").trigger("create");
			
			$("#prodctPric").append("" +
				"<div class='bx_prd2'>" +
					"<h4 class='m_mid_total'>" +
						"<strong class='label font-md'>합계</strong>" +
						"<strong class='totalPrice' id='totalPrice' style='margin-left: 10px;'>" +
							"<em class='font-xlg' style='font-family: emm_bol; font-weight: bold;'>"+prodctSelPric+"</em>" +
							"<span>원</span>" +
						"</strong>" +
					"</h4>" +
				"</div>" +
			"").trigger("create");
			
			$("#prodctDetailImg").append("" +
				"<div class='dtl_capture_img'>" +
					"<img src='http://sebm.iptime.org:9124/"+prodctMainImage+"'/>" +
				"</div>" +
				"<div class='dtl_capture_img'>" +
					"<img src='http://sebm.iptime.org:9124/"+prodctDetlImageOne+"'/>" +
				"</div>" +
				"<div class='dtl_capture_img'>" +
					"<img src='http://sebm.iptime.org:9124/"+prodctDetlImageTwo+"'/>" +
				"</div>" +		
				"<div class='dtl_capture_img'>" +
					"<img src='http://sebm.iptime.org:9124/"+prodctDetlImageThree+"'/>" +
				"</div>" +
			"").trigger("create");
			
			appDeliveryAgremtRead();
			appExchngAgremtRead();
			
			if(prodctSalePric != 0 && prodctSalePric != prodctSelPric) {
				$($("#price")).html("" +
					"<dd>" +
						"<span class='price-u font-md' style='text-decoration:line-through;'>" + prodctSelPric + "원</span>" +
					"</dd>" +
					"<dd>" +
						"<span id='price' class='sale font-xlg'>" + prodctSalePric +"</span><span>원</span>" +
					"</dd>" +
				"");
				
				$($("#totalPrice")).html("" +
					"<em class='font-xlg' style='font-family: emm_bol; font-weight: bold;'>"+prodctSalePric+"</em>" +
					"<span>원</span>" +
				"");
			}
			
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
});

function dahamgaeCountDownTimer(dt, id){
	var end = new Date(dt);

	var _second = 1000;
	var _minute = _second * 60;
	var _hour = _minute * 60;
	var _day = _hour * 24;
	var timer;

	function showRemaining() {
		var now = new Date();
		var distance = end - now;
		if (distance < 0) {

			clearInterval(timer);
			document.getElementById(id).innerHTML = 'EXPIRED!';

			return;
		};

		var days = Math.floor(distance / _day);
		var hours = Math.floor((distance % _day) / _hour);
		var minutes = Math.floor((distance % _hour) / _minute);
		var seconds = Math.floor((distance % _minute) / _second);
		
		if(days != null){
			document.getElementById("daysCounter").innerHTML = days;
			document.getElementById("hoursCounter").innerHTML = hours; 
			document.getElementById("minutesCounter").innerHTML = minutes; 
			//document.getElementById("counter4").innerHTML = seconds; 
		}
	}

	timer = setInterval(showRemaining, 1000);
}