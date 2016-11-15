$(document).on('pageshow', function(event, data){ //"cusDebecChoiceMainPage" 라는 page가 로딩됐을 때 실행이 되는 함수
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
   if ($.mobile.activePage.attr('id') === "cusDebecChoiceMainPage") {
	   
		$.ajax({
			type:"POST",
			url:"http://sebm.iptime.org:9124/appFreshtCount.do",
			success:function(data){
				appFreshtCount = data;
				if(appFreshtCount == 0) {
					freshNotInfo();
				}
				else {
					cusFreshList();
				}
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
		
		$.ajax({
			type:"POST",
			url:"http://sebm.iptime.org:9124/appHotdltCount.do",
			success:function(data){
				appHotdltCount = data;
				if(appHotdltCount == 0) {
					hotdlNotInfo();
				}
				else {
					cusHotdlList();
				}
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
	});
	
	$.ajax({
		type:"POST",
		url:"http://sebm.iptime.org:9124/appPackgCount.do",
		success:function(data){
			appPackgCount = data;
			if(appPackgCount == 0) {
				packgNotInfo();
			}
			else {
				cusPackgList();
			}
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
   }
});
//고객 핫딜 리스트 조회
function cusHotdlList(){
	$.ajax({
	    type:"POST",
	    url:"http://sebm.iptime.org:9124/appHotdlList.do",
	    success:function(data){
	       $.each(data, function(i, vo){
	       	//핫딜 상품 목록 리스트
	       	var prodctSeq = vo.prodctSeq; //상품번호
	       	var img = vo.img; //이미지
	       	var prodctNme = vo.prodctNme; //상품명
	       	var remngAmont =vo.remngAmont; //남은수량
	       	var purchsPric =numberWithCommas(vo.purchsPric); //매입가
	       	var discntPric =numberWithCommas(vo.discntPric); //핫딜할인가
	       	var selPric =numberWithCommas(vo.selPric); //판매가
	       	var amont =vo.amont; //핫딜 수량
	       	var qunt =vo.qunt; //재고량
	       	var prodctType = 4;
	       	
				$("#cusHotdlList").append("<li style='padding-top: 0px; padding-bottom: 10px;'>"+
													"<hr style='margin-top: 0px;'>"+
													"<div style='position: relative; padding-left: 10px; padding-right: 10px; height: 110px;'>"+
														"<div style='display: table-cell; position: relative; width: 90px; height: 100px; vertical-align: top;'>"+
															"<a onclick='javascript:appProdcrDtlRead("+prodctSeq+")' style='color: #636566; text-decoration: none;'>"+
																"<img src='http://sebm.iptime.org:9124/"+img+"' style='width:100%; height:100%'>"+
															"</a>" +
														"</div>"+
														"<div class='col-xs-6' style='display: table-cell; padding: 4px 0px 0 15px; vertical-align: top;'>"+
															"<a onclick='javascript:appProdcrDtlRead("+prodctSeq+")' style='color: #636566; text-decoration: none;'>"+
																"<div style='white-space:normal; display: -webkit-box; overflow: hidden; width: 120px;height: 35px; padding-right: 15px; line-height: 20px; color: #666; -webkit-line-clamp: 2; -webkit-box-orient: vertical; word-break: break-all; word-wrap: break-word;'>"+
																	"<span>"+prodctNme+"</span>" +
																"</div>"+
																"<div style='margin: 0 0 -4px; line-height: 27px; color: #262f33;'><br>"+
																	"<span class='font-md' style='text-decoration:line-through;'>"+purchsPric+"</span>원<span class='font-xlg' style='color:red;font-family:emm_bol' ><strong>"+discntPric+"</strong>원</span>" +
																"</div>" +
															"</a>" +
														"</div> "+
				 										"<div style='position: absolute; top: 0; right: 10px;'>"+
					 						            "<div class='cart_rate' style='width: 53px; height: 48px;'>" +
					 					            		"<label>1</label>" +
					 										"<select onchange='javascript:CartQtyChng(this);'>" +
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
					 									"</div>" +
						 								"<button onclick='formCart(this);return false;' class='waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button ui-btn' style='padding: 0; width: 53px; height: 53px; background-position: 0 -400px;'>" +
						 									"<i><img src='../img/cart.png'/></i>" +
						 								"</button>" +
						 								"<span class='item_info' style='display:none'>" +
						 					                "<input type='hidden' name='prodctSeq' value='"+prodctSeq+"'>" +
						 					                "<input type='hidden' name='prodctSelPric' value='"+selPric+"'>" +
						 					                "<input type='hidden' name='prodctType' value='"+prodctType+"'>" +
						 					                "<input type='hidden' name='ordrQty' value='1'>" +
						 					            "</span>" +
					 									"</div>" +
			 										"</div>" +
												"</li>");
				$("#cusHotdlList").listview("refresh");
	       });
	    },
	    error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	 });
}
//고객 신선식품 리스트 조회
function cusFreshList(){
	$.ajax({
	    type:"POST",
	    url:"http://sebm.iptime.org:9124/appFreshList.do",
	    success:function(data){
	       $.each(data, function(i, vo){
	       	//신선 식품 목록 리스트
	       		var prodctSeq = vo.prodctSeq; //상품번호
				var img = vo.img; //이미지
				var prodctNme = vo.prodctNme; //상품명
				var selPric =numberWithCommas(vo.selPric); //판매가
				var selStrtTime = vo.selStrtTime; //판매시작시간
				var stat =vo.stat; //상태
				var qunt =vo.qunt; //재고량
				var prodctType = 3;
				
				$("#cusFreshList").append("<li style='padding-top: 0px; padding-bottom: 10px;'>"+
													"<hr style='margin-top: 0px;'>"+
													"<div style='position: relative; padding-left: 10px; padding-right: 10px; height: 110px;'>"+
		 												"<div style='display: table-cell; position: relative; width: 90px; height: 100px; vertical-align: top;'>"+
			 												"<a onclick='appProdcrDtlRead("+prodctSeq+")' style='color: #636566; text-decoration: none;'>"+
			 													"<img src='http://sebm.iptime.org:9124/"+img+"' style='width:100%; height:100%'>" +
			 												"</a>" +
			 											"</div>"+
				 										"<div class='col-xs-6' style='display: table-cell; padding: 4px 0px 0 15px; vertical-align: top;'>"+
			 												"<a onclick='appProdcrDtlRead("+prodctSeq+")' style='color: #636566; text-decoration: none;'>"+
				 												"<div style='white-space:normal; display: -webkit-box; overflow: hidden; width: 120px;height: 42px; padding-right: 15px; line-height: 20px; color: #666; -webkit-line-clamp: 2; -webkit-box-orient: vertical; word-break: break-all; word-wrap: break-word;'>"+
				 													"<span>"+prodctNme+"</span>" +
				 												"</div>"+
				 												"<em class='font-xlg' style='font-style: normal; font-family:emm_bol; font-weight: bold; letter-spacing: -1px; color: #262f33;'>"+selPric+"원</em></div>" +
			 												"</a>"+
					 										"<div style='position: absolute; top: 0; right: 10px;'>"+
							 						            "<div class='cart_rate' style='width: 53px; height: 48px;'>" +
							 					            		"<label>1</label>" +
							 										"<select onchange='javascript:CartQtyChng(this);'>" +
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
							 									"</div>" +
								 								"<button onclick='formCart(this);return false;' class='waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button ui-btn' style='padding: 0; width: 53px; height: 53px; background-position: 0 -400px;'>" +
								 									"<i><img src='../img/cart.png'/></i>" +
								 								"</button>" +
								 								"<span class='item_info' style='display:none'>" +
								 					                "<input type='hidden' name='prodctSeq' value='"+prodctSeq+"'>" +
								 					                "<input type='hidden' name='prodctSelPric' value='"+selPric+"'>" +
								 					                "<input type='hidden' name='prodctType' value='"+prodctType+"'>" +
								 					                "<input type='hidden' name='ordrQty' value='1'>" +
								 					            "</span>" +
							 									"</div>" +
					 										"</div>" +
													"</li>");
				$("#cusFreshList").listview("refresh");
	       });
	    },
	    error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	 });
}
//고객 패키지 리스트 조회
function cusPackgList(){
    $.ajax({
        type:"POST",
        url:"http://sebm.iptime.org:9124/appPackgList.do",
        success:function(data){
           $.each(data, function(i, vo){
	        	
				//패키지 목록 리스트
	        	var packgSeq =vo.packgSeq; //상품번호
	        	var packgNme =vo.packgNme; //패키지 명
	        	var allPric =numberWithCommas(vo.allPric); //총 가격
	        	var prodctType = 1;
	        	var prodctSeqSet = vo.prodctSeqSet; //상품 3개 seq
	        	prodctSeqSet = prodctSeqSet.split(',');
	        	
	        	var mainImg1 = prodctSeqSet[0]; //메인 이미지1
	        	var mainImg2 = prodctSeqSet[1]; //메인 이미지2
	        	var mainImg3 = prodctSeqSet[2]; //메인 이미지3
	        	

				$("#cusPackgList").append("<li style='padding-top: 0px; padding-bottom: 10px;'>"+
													"<hr style='margin-top: 0px;'>"+
													"<div style='position: relative; padding-left: 10px; padding-right: 10px; height: 200px;'>"+
														"<a onclick='appPackgDtlRead("+packgSeq+")' align='center' style='width: 100%'>"+
															"<div align='center' style='height:125px;'>"+
																"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg1+"/main.jpg' style='width:33%; height:120px;'/>" +
																"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg2+"/main.jpg' style='width:33%; height:120px;'/>" +
																"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg3+"/main.jpg' style='width:33%; height:120px;'/>" +
															"</div>"+
														"</a>"+
											           "<div class='col-xs-6' style='display: table-cell; padding: 4px 0px 0  15px; vertical-align: top;'>"+
											            	"<a onclick='appPackgDtlRead("+packgSeq+")' style='color: #636566; text-decoration: none;'>"+
																"<div style='white-space:normal; display: -webkit-box; overflow: hidden; width: 240px;height: 20px; padding-right: 15px; line-height: 20px; color: #666; -webkit-line-clamp: 2; -webkit-box-orient: vertical; word-break: break-all; word-wrap: break-word;'>"+
																	"<span>"+packgNme+"</span>"+
																"</div>"+
																"<div style='margin: 0 0 -4px; line-height: 27px; color: #262f33;'>"+
																	"<em class='font-xlg' style='font-style: normal; font-family:emm_bol; font-weight: bold; letter-spacing: -1px; color: #262f33;'>"+allPric+"원</em>"+
																"</div>"+
											              " </a>"+
											            "</div>"+
											            "<div style='position: absolute; top: 132px; right: 10px;'>"+
							 							"<button onclick='formCart(this);return false;' class='waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button ui-btn' style='padding: 0; width: 53px; height: 53px; background-position: 0 -400px;'>" +
						 									"<i><img src='../img/cart.png'/></i>" +
						 								"</button>" +
						 								"<span class='item_info' style='display:none'>" +
						 					                "<input type='hidden' name='prodctSeq' value='"+packgSeq+"'>" +
						 					                "<input type='hidden' name='prodctSelPric' value='"+allPric+"'>" +
						 					                "<input type='hidden' name='prodctType' value='"+prodctType+"'>" +
						 					                "<input type='hidden' name='ordrQty' value='1'>" +
						 					            "</span>" +
											            "</div>"+
													"</div>"+
												"</li>");
				$("#cusPackgList").listview("refresh");
           });
        },
        error:function(request,status,error){
             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
     });
}

//고객 패키지 상세조회
function appPackgDtlRead(packgSeq) {
	var packgSeq = packgSeq;
	sessionStorage.setItem('packgSeq', packgSeq);
	$.mobile.changePage("MarketCusDebecChoicePackgRead.html");
};

//고객 패키지 상세 조회
$(document).on('pageshow', "#cusPackgReadPage", function (event, data) {
	var packgSeq = sessionStorage.getItem('packgSeq')
	formData = {"packgSeq":packgSeq};
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appPackgRead.do",
		success:function(data){
			
			var prodctSeq = data.packgSeq;
			var prodctNme = data.packgNme;
			var prodctType =1;
			var prodctSelPric = numberWithCommas(data.allPric);
			var prodctSalePric = 0;
        	var prodctSeqSet = data.prodctSeqSet; //상품 3개 seq
        	prodctSeqSet = prodctSeqSet.split(',');
        	
        	var mainImg1 = prodctSeqSet[0]; //메인 이미지1
        	var mainImg2 = prodctSeqSet[1]; //메인 이미지2
        	var mainImg3 = prodctSeqSet[2]; //메인 이미지3
			
			$("#prodctTitle").append("" +
					"<h3 class='font-md'>"+prodctNme+"</h3>" +
					"<dl id='price'>" +
						"<dd>" +
							"<span class='sale font-xlg'>" + prodctSelPric +"</span><span>원</span>" +
						"</dd>" +
					"</dl>" +
			"").trigger("create");
			
			$("#prodctMainImg").append("" +
					"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg1+"/main.jpg' style='width:33%; height:120px;'/>" +
					"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg2+"/main.jpg' style='width:33%; height:120px;'/>" +
					"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg3+"/main.jpg' style='width:33%; height:120px;'/>" +
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
							"<a href='#' onclick='javascript:formCart(this);' class='btn_ty1 font-btn ui-link waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button'>장바구니</a>" +
							"<span id='item_info' class='item_info' style='display:none'>" +
				                "<input type='hidden' name='prodctSeq' value='"+prodctSeq+"'>" +
				                "<input type='hidden' name='prodctSelPric' value='"+data.allPric+"'>" +
				                "<input type='hidden' name='prodctSalePric' value='"+data.allPric+"'>" +
				                "<input type='hidden' name='prodctType' value='"+prodctType+"'>" +
				                "<input type='hidden' name='ordrQty' value='1'>" +
				            "</span>" +
						"</li>" +
					"</ul>" +
				"").trigger("create");
				
				$("#prodctDetailImg").append("" +
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg1+"/main.jpg'/>" +
					"</div>" +
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg1+"/detlOne.jpg'/>" +
					"</div>" +
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg1+"/detlTwo.jpg'/>" +
					"</div>" +		
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg1+"/detlThree.jpg'/>" +
					"</div>" +
					
					"<div class='dtl_capture_img'>" +
					"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg2+"/main.jpg'/>" +
					"</div>" +
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg2+"/detlOne.jpg'/>" +
					"</div>" +
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg2+"/detlTwo.jpg'/>" +
					"</div>" +		
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg2+"/detlThree.jpg'/>" +
					"</div>" +
					
					"<div class='dtl_capture_img'>" +
					"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg3+"/main.jpg'/>" +
					"</div>" +
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg3+"/detlOne.jpg'/>" +
					"</div>" +
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg3+"/detlTwo.jpg'/>" +
					"</div>" +		
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg3+"/detlThree.jpg'/>" +
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

$(document).on('pageshow', function(event, data){ //"empDebecChoiceMainPage" 라는 page가 로딩됐을 때 실행이 되는 함수
	if(!(typeof(componentHandler) == 'undefined')){
		componentHandler.upgradeAllRegistered();
	}
   if ($.mobile.activePage.attr('id') === "empDebecChoiceMainPage") {
	   
		$.ajax({
			type:"POST",
			url:"http://sebm.iptime.org:9124/appFreshtCount.do",
			success:function(data){
				appFreshtCount = data;
				if(appFreshtCount == 0) {
					freshNotInfo();
				}
				else {
					empFreshList();
				}
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
		
		$.ajax({
			type:"POST",
			url:"http://sebm.iptime.org:9124/appHotdltCount.do",
			success:function(data){
				appHotdltCount = data;
				if(appHotdltCount == 0) {
					hotdlNotInfo();
				}
				else {
					empHotdlList();
				}
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
	});
	
	$.ajax({
		type:"POST",
		url:"http://sebm.iptime.org:9124/appPackgCount.do",
		success:function(data){
			appPackgCount = data;
			if(appPackgCount == 0) {
				packgNotInfo();
			}
			else {
				empPackgList();
			}
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
   }
});

//관리자 신선식품 리스트 조회
function empFreshList(){
    $.ajax({
        type:"POST",
        url:"http://sebm.iptime.org:9124/appFreshList.do",
        success:function(data){
           $.each(data, function(i, vo){
	        	//신선 식품 목록 리스트
	        	var prodctSeq = vo.prodctSeq; //상품번호
				var img = vo.img; //이미지
				var prodctNme = vo.prodctNme; //상품명
				var selPric =numberWithCommas(vo.selPric); //판매가
				var selStrtTime = vo.selStrtTime; //판매시작시간
				var stat =vo.stat; //상태
				var qunt =vo.qunt; //재고량
				
				$("#empFreshList").append("<li style='padding-top: 0px; padding-bottom: 10px;'>"+
	 												"<hr style='margin-top: 0px;'>"+
	 												"<div style='position: relative; padding-left: 10px; padding-right: 10px; height: 110px;'>"+
		 												"<div style='display: table-cell; position: relative; width: 90px; height: 100px; vertical-align: top;'>"+
			 												"<a onclick='javascript:appProdcrDtlRead("+prodctSeq+")' style='color: #636566; text-decoration: none;'>"+
			 													"<img src='http://sebm.iptime.org:9124/"+img+"' style='width:100%; height:100%'>" +
			 												"</a>" +
			 											"</div>"+
				 										"<div class='col-xs-6' style='display: table-cell; padding: 4px 0px 0 15px; vertical-align: top;'>"+
			 												"<a onclick='javascript:prodcrDtlRead("+prodctSeq+")' style='color: #636566; text-decoration: none;'>"+
				 												"<div style='white-space:normal; display: -webkit-box; overflow: hidden; width: 120px;height: 42px; padding-right: 15px; line-height: 20px; color: #666; -webkit-line-clamp: 2; -webkit-box-orient: vertical; word-break: break-all; word-wrap: break-word;'>"+
				 													"<span>"+prodctNme+"</span>" +
				 												"</div>"+
				 												"<em class='font-xlg' style='font-style: normal; font-family:emm_bol; font-weight: bold; letter-spacing: -1px; color: #262f33;'>"+selPric+"원</em></div>" +
			 												"</a>"+
	 												"</li>");
				$("#empFreshList").listview("refresh");
           });
        },
        error:function(request,status,error){
             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
     });
}

//관리자 핫딜리스트 조회
function empHotdlList(){
	 $.ajax({
         type:"POST",
         url:"http://sebm.iptime.org:9124/appHotdlList.do",
         success:function(data){
            $.each(data, function(i, vo){
	        	//핫딜 상품 목록 리스트
	        	var prodctSeq = vo.prodctSeq; //상품번호
	        	var img = vo.img; //이미지
	        	var prodctNme = vo.prodctNme; //상품명
	        	var remngAmont =vo.remngAmont; //남은수량
	        	var purchsPric =numberWithCommas(vo.purchsPric); //매입가
	        	var discntPric =numberWithCommas(vo.discntPric); //핫딜할인가
	        	var selPric =numberWithCommas(vo.selPric); //판매가
	        	var amont =vo.amont; //핫딜 수량
	        	var qunt =vo.qunt; //재고량
				$("#empHotdlList").append("<li style='padding-top: 0px; padding-bottom: 10px;'>"+
													"<hr style='margin-top: 0px;'>"+
													"<div style='position: relative; padding-left: 10px; padding-right: 10px; height: 110px;'>"+
														"<div style='display: table-cell; position: relative; width: 90px; height: 100px; vertical-align: top;'>"+
															"<a onclick='javascript:appProdcrDtlRead("+prodctSeq+")' style='color: #636566; text-decoration: none;'>"+
																"<img src='http://sebm.iptime.org:9124/"+img+"' style='width:100%; height:100%'>"+
															"</a>" +
														"</div>"+
														"<div class='col-xs-6' style='display: table-cell; padding: 4px 0px 0 15px; vertical-align: top;'>"+
															"<a onclick='javascript:appProdcrDtlRead("+prodctSeq+")' style='color: #636566; text-decoration: none;'>"+
																"<div style='white-space:normal; display: -webkit-box; overflow: hidden; width: 120px;height: 35px; padding-right: 15px; line-height: 20px; color: #666; -webkit-line-clamp: 2; -webkit-box-orient: vertical; word-break: break-all; word-wrap: break-word;'>"+
																	"<span>"+prodctNme+"</span>" +
																"</div>"+
																"<div style='margin: 0 0 -4px; line-height: 27px; color: #262f33;'><br>"+
																	"<span class='font-md' style='text-decoration:line-through;'>"+purchsPric+"</span>원<span class='font-xlg' style='color:red;font-family:emm_bol' ><strong>"+discntPric+"</strong>원</span>" +
																"</div>" +
															"</a>" +
														"</div> "+
													"</div>" +
												"</li>");
				$("#empHotdlList").listview("refresh");
            });
         },
         error:function(request,status,error){
              alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
         }
      });
}

//관리자 패키지 리스트 조회
function empPackgList(){
    $.ajax({
        type:"POST",
        url:"http://sebm.iptime.org:9124/appPackgList.do",
        success:function(data){
           $.each(data, function(i, vo){
	        	
				//패키지 목록 리스트
	        	var packgSeq =vo.packgSeq; //상품번호
	        	var packgNme =vo.packgNme; //패키지 명
	        	var allPric =numberWithCommas(vo.allPric); //총 가격
	        	var mainImg = vo.mainImg; //메인 이미지
	        	var prodctSeqSet = vo.prodctSeqSet; //상품 3개 seq
	        	prodctSeqSet = prodctSeqSet.split(',');
	        	
	        	var mainImg1 = prodctSeqSet[0]; //메인 이미지1
	        	var mainImg2 = prodctSeqSet[1]; //메인 이미지2
	        	var mainImg3 = prodctSeqSet[2]; //메인 이미지3
	        	
				$("#empPackgList").append("<li style='padding-top: 0px; padding-bottom: 10px;'>"+
													"<hr style='margin-top: 0px;'>"+
													"<div style='position: relative; padding-left: 10px; padding-right: 10px; height: 200px;'>"+
														"<a onclick='empAppPackgDtlRead("+packgSeq+")' align='center' style='width: 100%'>"+
															"<div align='center' style='height:125px;'>"+
																"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg1+"/main.jpg' style='width:33%; height:120px;'/>" +
																"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg2+"/main.jpg' style='width:33%; height:120px;'/>" +
																"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg3+"/main.jpg' style='width:33%; height:120px;'/>" +
															"</div>"+
														"</a>"+
											           "<div class='col-xs-6' style='display: table-cell; padding: 4px 0px 0  15px; vertical-align: top;'>"+
											            	"<a onclick='empAppPackgDtlRead("+packgSeq+")' style='color: #636566; text-decoration: none;'>"+
																"<div style='white-space:normal; display: -webkit-box; overflow: hidden; width: 240px;height: 20px; padding-right: 15px; line-height: 20px; color: #666; -webkit-line-clamp: 2; -webkit-box-orient: vertical; word-break: break-all; word-wrap: break-word;'>"+
																	"<span>"+packgNme+"</span>"+
																"</div>"+
																"<div style='margin: 0 0 -4px; line-height: 27px; color: #262f33;'>"+
																	"<em class='font-xlg' style='font-style: normal; font-family:emm_bol; font-weight: bold; letter-spacing: -1px; color: #262f33;'>"+allPric+"원</em>"+
																"</div>"+
											              " </a>"+
											            "</div>"+
													"</div>"+
												"</li>");
				$("#empPackgList").listview("refresh");
           });
        },
        error:function(request,status,error){
             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
     });
}

//관리자 패키지 상세조회
function empAppPackgDtlRead(packgSeq) {
	var packgSeq = packgSeq;
	sessionStorage.setItem('packgSeq', packgSeq);
	$.mobile.changePage("MarketEmpDebecChoicePackgRead.html");
};

//관리자 패키지 상세 조회
$(document).on('pageshow', "#empPackgReadPage", function (event, data) {
	var packgSeq = sessionStorage.getItem('packgSeq')
	formData = {"packgSeq":packgSeq};
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appPackgRead.do",
		success:function(data){
			var prodctSeq = data.packgSeq;
			var prodctMainImage = data.mainImg;
			var prodctDetlImageOne = data.detlImg1;
			var prodctDetlImageTwo = data.detlImg2;
			var prodctDetlImageThree = data.detlImg3;
			var prodctNme = data.packgNme;
			var prodctSelPric = numberWithCommas(data.allPric);
        	var prodctSeqSet = data.prodctSeqSet; //상품 3개 seq
        	prodctSeqSet = prodctSeqSet.split(',');
        	
        	var mainImg1 = prodctSeqSet[0]; //메인 이미지1
        	var mainImg2 = prodctSeqSet[1]; //메인 이미지2
        	var mainImg3 = prodctSeqSet[2]; //메인 이미지3
			
			$("#prodctTitle").append("" +
					"<h3 class='font-md'>"+prodctNme+"</h3>" +
					"<dl id='price'>" +
						"<dd>" +
							"<span class='sale font-xlg'>" + prodctSelPric +"</span><span>원</span>" +
						"</dd>" +
					"</dl>" +
			"").trigger("create");
			
			$("#prodctMainImg").append("" +
					"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg1+"/main.jpg' style='width:33%; height:120px;'/>" +
					"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg2+"/main.jpg' style='width:33%; height:120px;'/>" +
					"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg3+"/main.jpg' style='width:33%; height:120px;'/>" +
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
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg1+"/main.jpg'/>" +
					"</div>" +
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg1+"/detlOne.jpg'/>" +
					"</div>" +
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg1+"/detlTwo.jpg'/>" +
					"</div>" +		
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg1+"/detlThree.jpg'/>" +
					"</div>" +
					
					"<div class='dtl_capture_img'>" +
					"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg2+"/main.jpg'/>" +
					"</div>" +
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg2+"/detlOne.jpg'/>" +
					"</div>" +
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg2+"/detlTwo.jpg'/>" +
					"</div>" +		
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg2+"/detlThree.jpg'/>" +
					"</div>" +
					
					"<div class='dtl_capture_img'>" +
					"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg3+"/main.jpg'/>" +
					"</div>" +
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg3+"/detlOne.jpg'/>" +
					"</div>" +
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg3+"/detlTwo.jpg'/>" +
					"</div>" +		
					"<div class='dtl_capture_img'>" +
						"<img src='http://sebm.iptime.org:9124/resources/image/prodct/"+mainImg3+"/detlThree.jpg'/>" +
					"</div>" +
				"").trigger("create");
			
			appDeliveryAgremtRead();
			appExchngAgremtRead();
			
			if(prodctSelPric != 0 && prodctSelPric != prodctSelPric) {
				$($("#price")).html("" +
					"<dd>" +
						"<span class='price-u font-md' style='text-decoration:line-through;'>" + prodctSelPric + "원</span>" +
					"</dd>" +
					"<dd>" +
						"<span id='price' class='sale font-xlg'>" + prodctSelPric +"</span><span>원</span>" +
					"</dd>" +
				"");
				
				$($("#totalPrice")).html("" +
					"<em class='font-xlg' style='font-family: emm_bol; font-weight: bold;'>"+prodctSelPric+"</em>" +
					"<span>원</span>" +
				"");
			}
			
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
});


//신선식품 상품이 없을 경우
function freshNotInfo(){
	$("#freshContent").append("" + 
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
					" 새로운 <span class='font-xlg' style='color:#8BC34A'><b>신선식품</b></span>으로 찾아 뵙겠습니다." + 
				"</h4>" +
			"</div>" +
		"</div>" + 
	"").trigger("create");
};

//핫딜 상품이 없을 경우
function hotdlNotInfo(){
	$("#hotdlContent").append("" + 
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
					" 새로운 <span class='font-xlg' style='color:#8BC34A'><b>핫딜</b></span>로 찾아 뵙겠습니다." + 
				"</h4>" +
			"</div>" +
		"</div>" + 
	"").trigger("create");
};

//패키지 상품이 없을 경우
function packgNotInfo(){
	$("#packgContent").append("" + 
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
					" 새로운 <span class='font-xlg' style='color:#8BC34A'><b>패키지</b></span>로 찾아 뵙겠습니다." + 
				"</h4>" +
			"</div>" +
		"</div>" + 
	"").trigger("create");
};