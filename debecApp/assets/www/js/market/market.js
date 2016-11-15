//고객상품 상세조회
$(document).on('pageshow', "#cusProdctReadPage", function (event, data) {
	var prodctSeq = sessionStorage.getItem('prodctSeq')
	
	if(localStorage.getItem('custmrSeq') != null){
		var custmrSeq = localStorage.getItem('custmrSeq');
	}
	else{
		var custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	
	formData = {"prodctSeq":prodctSeq,"custmrSeq":custmrSeq};
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appProdctRead.do",
		success:function(data){
			var prodctSeq = data.prodctSeq;
			var prodctMainImage = data.prodctMainImage;
			var prodctDetlImageOne = data.prodctDetlImageOne;
			var prodctDetlImageTwo = data.prodctDetlImageTwo;
			var prodctDetlImageThree = data.prodctDetlImageThree;
			var prodctNme = data.prodctNme;
			var prodctType = data.prodctType;
			var prodctSelPric = numberWithCommas(data.prodctSelPric);
			var prodctSalePric = numberWithCommas(data.prodctSalePric);
			var selStrtTime = data.selStrtTime;
			var selEndTime = data.selEndTime;
			var selStrtTimeCount = data.selStrtTimeCount;
			
			if(selStrtTimeCount!=0){
				//신선식품 타이머 작동 기능 시작
				var selEndTime = selEndTime.replace( /-/gi, "/");
				CountDownTimer(""+selEndTime+" 21:00", 'countdown');
				//신선식품 타이머 작동 기능 끝
				$("#prodctCount").append("<div class='chd_count_obj' align='center'>" +
							"<strong>남은 시간 :  <span id='counter1'></span>일 <span id='counter2'></span>시간 <span id='counter3' ></span>분  남음</strong>" +
								"</div>").trigger("create");
			}
			
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
			                "<input type='hidden' name='prodctSelPric' value='"+data.prodctSelPric+"'>" +
			                "<input type='hidden' name='prodctSalePric' value='"+data.prodctSalePric+"'>" +
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
			
			appReltnProdctRead()
			
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

//관리자 상품 상세조회
$(document).on('pageshow', "#empProdctReadPage", function (event, data) {
	var prodctSeq = sessionStorage.getItem('prodctSeq')
	
	if(localStorage.getItem('custmrSeq') != null){
		var custmrSeq = localStorage.getItem('custmrSeq');
	}
	else{
		var custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	
	formData = {"prodctSeq":prodctSeq,"custmrSeq":custmrSeq};
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appProdctRead.do",
		success:function(data){
			var prodctSeq = data.prodctSeq;
			var prodctMainImage = data.prodctMainImage;
			var prodctDetlImageOne = data.prodctDetlImageOne;
			var prodctDetlImageTwo = data.prodctDetlImageTwo;
			var prodctDetlImageThree = data.prodctDetlImageThree;
			var prodctNme = data.prodctNme;
			var prodctType = data.prodctType;
			var prodctSelPric = numberWithCommas(data.prodctSelPric);
			var prodctSalePric = numberWithCommas(data.prodctSalePric);
			var selStrtTime = data.selStrtTime;
			var selEndTime = data.selEndTime;
			var selStrtTimeCount = data.selStrtTimeCount;
			
			if(selStrtTimeCount!=0){
				//신선식품 타이머 작동 기능 시작
				var selEndTime = selEndTime.replace( /-/gi, "/");
				CountDownTimer(""+selEndTime+" 21:00", 'countdown');
				//신선식품 타이머 작동 기능 끝
				$("#prodctCount").append("<div class='chd_count_obj' align='center'>" +
							"<strong>남은 시간 :  <span id='counter1'></span>일 <span id='counter2'></span>시간 <span id='counter3' ></span>분  남음</strong>" +
								"</div>").trigger("create");
			}
			
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
			
			appReltnProdctRead()
			
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

function CountDownTimer(dt, id){
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
		
		document.getElementById("counter1").innerHTML = days;
		document.getElementById("counter2").innerHTML = hours; 
		document.getElementById("counter3").innerHTML = minutes; 
		//document.getElementById("counter4").innerHTML = seconds; 
	}

	timer = setInterval(showRemaining, 1000);
}

//연관 상품 상세 조회
function appReProdcrDtlRead(prodctSeq) {
	sessionStorage.setItem('prodctSeq', prodctSeq);
	var prodctSeq = sessionStorage.getItem('prodctSeq');
	formData = {"prodctSeq":prodctSeq};
	$('#prodctTitle').empty();
	$('#prodctMainImg').empty();
	$('#prodctCart').empty();
	$('#prodctPric').empty();
	$('#prodctBuy').empty();
	$('#prodctDetailImg').empty();
	$('#deliveryAgremt').empty();
	$('#exchngAgremt').empty();
	$('#appReltnProdctContent').empty();
	$('#appReltnProdct').empty();
	
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appProdctRead.do",
		success:function(data){
			var prodctSeq = data.prodctSeq;
			var prodctMainImage = data.prodctMainImage;
			var prodctDetlImageOne = data.prodctDetlImageOne;
			var prodctDetlImageTwo = data.prodctDetlImageTwo;
			var prodctDetlImageThree = data.prodctDetlImageThree;
			var prodctNme = data.prodctNme;
			var prodctType = data.prodctType;
			var prodctSelPric = numberWithCommas(data.prodctSelPric);
			var prodctSalePric = numberWithCommas(data.prodctSalePric);
			var selStrtTime = data.selStrtTime;
			
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
			                "<input type='hidden' name='prodctSelPric' value='"+data.prodctSelPric+"'>" +
			                "<input type='hidden' name='prodctSalePric' value='"+data.prodctSalePric+"'>" +
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
			
			appReltnProdctRead()
			
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
};


//합계 금액 변경
function changPrice(Qty) {
	var ordrQty = Qty;
	var prodctSelPric = parseInt($('#item_info').children("input[name=prodctSelPric]").val());
	var prodctSalePric = parseInt($('#item_info').children("input[name=prodctSalePric]").val());
	
	if(prodctSalePric != 0 && prodctSalePric != prodctSelPric) {
		
		prodctSelPric = numberWithCommas(prodctSalePric*parseInt(ordrQty));
		
		$($("#totalPrice")).html("" +
			"<em class='font-xlg' style='font-family: emm_bol; font-weight: bold;'>"+prodctSelPric+"</em>" +
			"<span>원</span>" +
		"");
	}
	else {
		prodctSelPric = numberWithCommas(prodctSelPric*parseInt(ordrQty));
		
		$($("#totalPrice")).html("" +
			"<em class='font-xlg' style='font-family: emm_bol; font-weight: bold;'>"+prodctSelPric+"</em>" +
			"<span>원</span>" +
		"");
	}
}

//연관 상품 조회
function appReltnProdctRead() {
	var prodctSeq = sessionStorage.getItem('prodctSeq');
	var reltnCount = 0;
	// 배열 생성
	var arr = new Array(3); 
	for(var i=0; i<3; i++) {
		arr[i] = new Array(5); 
	};
	
	formData = {"prodctSeq":prodctSeq};
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appReltnProdctRead.do",
		success:function(data){
			var reltnProdctOneSeq = data.reltnProdctOneSeq;
			var reltnProdctOneImg = data.reltnProdctOneImg;
			var reltnProdctOneNme = data.reltnProdctOneNme;
			var reltnProdctOneSelPric = data.reltnProdctOneSelPric;
			var reltnProdctOneDiscntPric = data.reltnProdctOneDiscntPric;
			
			var reltnProdctTwoSeq = data.reltnProdctTwoSeq;
			var reltnProdctTwoImg = data.reltnProdctTwoImg;
			var reltnProdctTwoNme = data.reltnProdctTwoNme;
			var reltnProdctTwoSelPric = data.reltnProdctTwoSelPric;
			var reltnProdctTwoDiscntPric = data.reltnProdctTwoDiscntPric;
			
			var reltnProdctThreeSeq = data.reltnProdctThreeSeq;
			var reltnProdctThreeImg = data.reltnProdctThreeImg;
			var reltnProdctThreeNme = data.reltnProdctThreeNme;
			var reltnProdctThreeSelPric = data.reltnProdctThreeSelPric;
			var reltnProdctThreeDiscntPric = data.reltnProdctThreeDiscntPric;
			
			arr[0][0] = reltnProdctOneSeq;
	        arr[0][1] = reltnProdctOneImg;
	        arr[0][2] = reltnProdctOneNme;
	        arr[0][3] = reltnProdctOneSelPric;
	        arr[0][4] = reltnProdctOneDiscntPric;
	        
	        arr[1][0] = reltnProdctTwoSeq;
	        arr[1][1] = reltnProdctTwoImg;
	        arr[1][2] = reltnProdctTwoNme;
	        arr[1][3] = reltnProdctTwoSelPric;
	        arr[1][4] = reltnProdctTwoDiscntPric;
	         
	        arr[2][0] = reltnProdctThreeSeq;
	        arr[2][1] = reltnProdctThreeImg;
	        arr[2][2] = reltnProdctThreeNme;
	        arr[2][3] = reltnProdctThreeSelPric;
	        arr[2][4] = reltnProdctThreeDiscntPric;
	       
	        $("#appReltnProdctContent").append("" +
    		 		"<p class='font-md'><b>이 상품과 같이 구매하면 좋은 상품</b></p>" +
    		 		"<hr/>" +
    		 "").trigger("create");
	        
           if(arr[0][0] > 0){
        	   reltnCount = parseInt(reltnCount)+1;
           }
           if(arr[1][0] > 0){
        	   reltnCount = parseInt(reltnCount)+1;
           }
           if(arr[2][0] > 0){
        	   reltnCount = parseInt(reltnCount)+1;
           }

            if(reltnCount == 3) {
            	 $.each(arr, function(i, item){
      	           if(item[0] != 0){
      	        	   $("#appReltnProdct").append("" +
		                 		 	"<div class='col-xs-4'>" +
		                 			"<div>" +
										"<a onclick='javascript:appReProdcrDtlRead("+item[0]+")' style='color: #636566; text-decoration: none;' class='ui-link waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button'>" +
			                 		 		"<img src='http://sebm.iptime.org:9124/"+item[1]+"' style='width:100%;height: 100px;'/>" +
			                 		 	"</a>" +
		                 		 	"</div>" +
		                 		 	"<div>" +
			        					"<a onclick='javascript:appReProdcrDtlRead("+item[0]+")' style='color: #636566; text-decoration: none;' class='ui-link waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button'>" +
			                 		 		"<span class='font-md'>" + item[2] +"</span>" +
				               		 		"<br>" +
				               		 		"<span id="+item[0]+" class='font-md'>" + item[3] +"원</span>" +
				               		 	"</a>" +
			               		 	"</div>" +
               		 		"</div>" +
               		 	"").trigger("create");
    	        	   if(item[4] != 0 && item[4] != item[3]) {
    	        		   $($("#"+item[0])).html("" +
		 						"<span style='text-decoration:line-through;'>" + item[3] + "원</span>" +
		 						"<span class='font-md'>" + item[4] +"원</span>" +
	            			 "");
    	        	   }
      	           }
  	  	        });
            }
            
            else if(reltnCount == 2) {
            	 $.each(arr, function(i, item){
		           if(item[0] != 0){
		        	   $("#appReltnProdct").append("" +
		       		 		"<div class='col-xs-6'>" +
				       		 	"<img src='http://sebm.iptime.org:9124/"+item[1]+"' style='width:100%;height: 100px;'/>" +
				       		 	"<span class='font-md'>" + item[2] +"</span>" +
		           		 		"<br>" +
		           		 		"<span id="+item[0]+" class='font-md'>" + item[3] +"원</span>" +
		       		 		"</div>" +
		       		 	"").trigger("create");
		        	   if(item[4] != 0 && item[4] != item[3]) {
		        		   $($("#"+item[0])).html("" +
		 						"<span style='text-decoration:line-through;'>" + item[3] + "원</span>" +
		 						"<span class='font-md'>" + item[4] +"원</span>" +
		        			 "");
		        	   }
		           }
			    });
            }
            
            else if(reltnCount == 1) {
	        	 $.each(arr, function(i, item){
    	           if(item[0] != 0){
    	        	   $("#appReltnProdct").append("" +
               		 		"<div class='col-xs-8'>" +
               		 			"<img src='http://sebm.iptime.org:9124/"+item[1]+"' style='width:100%;height: 100px;'/>" +
               		 			"<div style='word-break: break-all; word-wrap: break-word;'>" +
	               		 			"<span class='font-md'>" + item[2] +"</span>" +
       		 					"</div>" +
	               		 		"<br>" +
	               		 		"<span id="+item[0]+" class='font-md'>" + item[3] +"원</span>" +
	               		 	
               		 		"</div>" +
               		 	"").trigger("create");
    	        	   if(item[4] != 0 && item[4] != item[3]) {
    	        		   $($("#"+item[0])).html("" +
		 						"<span style='text-decoration:line-through;'>" + item[3] + "원</span>" +
		 						"<span class='font-md'>" + item[4] +"원</span>" +
	            			 "");
    	        	   }
    	           }
	  	        });
            }
            
            else {
            	$('#appReltnProdctContent').html("");
            	$('#appReltnProdct').html("");
           }
	         
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
};

//배송 약관 조회
function appDeliveryAgremtRead() {
	formData = "useAgremtSeq=10";
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appDeliveryAgremtRead.do",
		success:function(data){
			var cnt = data.cnt;
			
			$($("#deliveryAgremt")).append("" +cnt+"");
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
};

//교환/환불 약관 조회
function appExchngAgremtRead() {
	formData = "useAgremtSeq=11";
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appDeliveryAgremtRead.do",
		success:function(data){
			var cnt = data.cnt;
			
			$($("#exchngAgremt")).append("" +cnt+"");
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
};

//상품 상세  변경 (통일)
function unitSelected(tag) {
	var ordrQty = $(tag).val();
	$(tag).parent().siblings('label').text(ordrQty);
	$('#item_info').children("input[name=ordrQty]").val(ordrQty);
	changPrice(ordrQty)
};

//상품 상세 수량 변경 (빼기)
function unitDown(tag) {
	var ordrQty = $(tag).siblings('.opa_area').children('label').text();
	if(ordrQty == 1) {
		navigator.notification.alert("최소 주문 가능 수량은 1개 입니다.",null,"알림","확인");
		return;
	}
	else {
		ordrQty = parseInt(ordrQty)-1;
		$(tag).siblings('.opa_area').children('label').text(ordrQty);
		$('#item_info').children("input[name=ordrQty]").val(ordrQty);
		changPrice(ordrQty)
	}
};

//상품 상세 수량 변경 (더하기)
function unitUp(tag) {
	var ordrQty = $(tag).siblings('.opa_area').children('label').text();
	if(ordrQty == 10) {
		navigator.notification.alert("1회 최대 주문 가능 수량은 10개 입니다.",null,"알림","확인");
		return;
	}
	else {
		ordrQty = parseInt(ordrQty)+1;
		$(tag).siblings('.opa_area').children('label').text(ordrQty);
		$('#item_info').children("input[name=ordrQty]").val(ordrQty);
		changPrice(ordrQty)
	}
};

function changItemTab(item) {
	if(item == "desc") {
		$("#descEvnt").parent().attr('class','on');
		$("#deliveryEvnt").parent().attr('class','');
		
		$("#detailDescTab").attr('class','dtl_tabcont on');
		$("#deliveryInfoTab").attr('class','dtl_tabcont');
	}
	else if(item == "delivery") {
		$("#descEvnt").parent().attr('class','');
		$("#deliveryEvnt").parent().attr('class','on');
		
		$("#detailDescTab").attr('class','dtl_tabcont');
		$("#deliveryInfoTab").attr('class','dtl_tabcont on');
	}
};

//상세조회
function appProdcrDtlRead(prodctSeq) {
	
	//직원,회원 체크
	var sessionEmpSeq = sessionStorage.getItem('empSeq');
	if(sessionEmpSeq!=null){
		var empSeq = sessionStorage.getItem('empSeq');
	}
	else{
		var empSeq = localStorage.getItem('empSeq');
	}
	var prodctSeq = prodctSeq;
	sessionStorage.setItem('prodctSeq', prodctSeq);
	if(empSeq != null){
		$.mobile.changePage("MarketEmpProdctRead.html");
	}
	else{
		$.mobile.changePage("MarketCusProdctRead.html");
	}
};

//셀렉트박스 변경
function CartQtyChng(tag) {
	var ordrQty = $(tag).val();
	$(tag).parent().siblings('.item_info').find("input[name=ordrQty]").val(ordrQty);
	$(tag).siblings('label').text(ordrQty);
};

//숫자 세자리마다 콤마 넣기
function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function formCart(tag) {
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	var prodctSeq =  $(tag).siblings('.item_info').find("input[name=prodctSeq]").val();
	var prodctAmont =  $(tag).siblings('.item_info').find("input[name=ordrQty]").val();
	var prodctType =  $(tag).siblings('.item_info').find("input[name=prodctType]").val();
	formData = "prodctSeq="+prodctSeq+"&prodctAmont="+prodctAmont+"&custmrSeq="+custmrSeq+"&prodctType="+prodctType;
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appCartItemCreat.do",
		success:function(data){
			navigator.notification.alert("장바구니에 상품을 담았습니다.",null,"알림","확인");
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
};

//검색
function appProdctSearch(){
	//직원,회원 체크
	var sessionEmpSeq = sessionStorage.getItem('empSeq');
	if(sessionEmpSeq!=null){
		var empSeq = sessionStorage.getItem('empSeq');
	}
	else{
		var empSeq = localStorage.getItem('empSeq');
	}
	var prodctSechText = $("#prodctSechText").val();
	sessionStorage.setItem('prodctSechText', prodctSechText);
	if(empSeq != null){
		$.mobile.changePage("MarketEmpSearchProdctList.html");
	}
	else{
		$.mobile.changePage("MarketCusSearchProdctList.html");
	}
};

// 최초 검색 시
$(document).on('pageshow', "#SearchProdctListPage", function (event, data) {
	var midCatgrSeq = sessionStorage.getItem('midCatgrSeq');
	if(midCatgrSeq == null) {
		var prodctSechText = sessionStorage.getItem('prodctSechText')
		formData = {"prodctSechText":prodctSechText};
		$.ajax({
			type:"POST",
			data:formData,
			url:"http://sebm.iptime.org:9124/appProdctSearch.do",
			success:function(data){
				$("#prodctSechTitle").append("" +
						"<span class='font-lg'>'"+prodctSechText+"'</span> 검색" +
				"").trigger("create");
				
				$.each(data, function(i, vo){
					var prodctSeq = vo.prodctSeq;
					var prodctMainImage = vo.prodctMainImage;
					var prodctNme = vo.prodctNme;
					var prodctType = vo.prodctType;
					var prodctSelPric = numberWithCommas(vo.prodctSelPric);
					var prodctSalePric = numberWithCommas(vo.prodctSalePric);
					
					$("#prodctList").append("" +
							"<li style='padding-top: 0px; padding-bottom: 10px;' class='ui-li-static ui-body-inherit ui-first-child ui-last-child'>" +
							"<hr style='margin-top: 0px;'>" +
							"<div style='position: relative; padding-left: 10px; padding-right: 10px; height: 110px;'>" +
								"<div style='display: table-cell; position: relative; width: 90px; height: 100px; vertical-align: top;'>" +				
									"<a onclick='javascript:appProdcrDtlRead("+prodctSeq+")' style='color: #636566; text-decoration: none;' class='ui-link waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button'>" +
										"<img src='http://sebm.iptime.org:9124/"+prodctMainImage+"' style='width:100%; height:100%'/>" +
									"</a>" +
						        "</div>" +
								"<div class='col-xs-6' style='display: table-cell; padding: 4px 0px 0 15px; vertical-align: top;'>" +
									"<a onclick='javascript:appProdcrDtlRead("+prodctSeq+")' style='color: #636566; text-decoration: none;' class='ui-link waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button'>" +
										"<div>" +
											"<div style='white-space:normal; display: -webkit-box; overflow: hidden; width: 120px;height: 45px; padding-right: 15px; line-height: 20px; color: #666; -webkit-line-clamp: 2; -webkit-box-orient: vertical; word-break: break-all; word-wrap: break-word;'>" +
												"<span class='font-md'" +
												">" + prodctNme + "</span>" +
											"</div>" +
											"<div style='margin: 0 0 -4px; line-height: 27px; color: #262f33;'>" +
												"<br>" +
												"<em id='price-"+i+"' class='font-xlg' style='font-style: normal; font-family: emm_bol; font-weight: bold; letter-spacing: -1px; color: #262f33;'>" + prodctSelPric + "원 </em>" +
											"</div> " +
										"</div>" +
					               "</a>" +
					            "</div>" +
					            "<div style='position: absolute; top: 0; right: 10px;'>" +
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
						                "<input type='hidden' name='selPric' value='"+prodctSelPric+"'>" +
						                "<input type='hidden' name='salePric' value='"+prodctSalePric+"'>" +
						                "<input type='hidden' name='prodctType' value='"+prodctType+"'>" +
						                "<input type='hidden' name='ordrQty' value='1'>" +
						            "</span>" +
						        "</div>" +
					        "</div>" +
						"</li>" +
					"").trigger("create");
					
					if(prodctSalePric != 0 && prodctSalePric != prodctSelPric) {
						priceId = "price-"+i;	
						$($("#"+priceId)).html("" +
							"<span class='font-md' style='text-decoration:line-through;'>" +prodctSelPric + "원</span>" +
							"<em id='price-"+i+"' class='font-xlg' style='font-style: normal; font-family: emm_bol; font-weight: bold; letter-spacing: -1px; color: #FF0000; margin-left: 10px;'>" + prodctSalePric + "원 </em>" +
						"");
					}
				});
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});	
	}
	else{
		 var midCatgrText = sessionStorage.getItem('midCatgrText');	 
			formData = {"catgrSeq":midCatgrSeq};
			$.ajax({
				type:"POST",
				data:formData,
				url:"http://sebm.iptime.org:9124/appProdctMidCatgrSearch.do",
				success:function(data){
					$("#prodctSechTitle").append("" +
							"<span class='font-lg'>'"+midCatgrText+"'</span> 검색" +
					"").trigger("create");
					
					$.each(data, function(i, vo){
						var prodctSeq = vo.prodctSeq;
						var prodctMainImage = vo.prodctMainImage;
						var prodctNme = vo.prodctNme;
						var prodctType = vo.prodctType;
						var prodctSelPric = numberWithCommas(vo.prodctSelPric);
						var prodctSalePric = numberWithCommas(vo.prodctSalePric);
						
						$("#prodctList").append("" +
								"<li style='padding-top: 0px; padding-bottom: 10px;' class='ui-li-static ui-body-inherit ui-first-child ui-last-child'>" +
								"<hr style='margin-top: 0px;'>" +
								"<div style='position: relative; padding-left: 10px; padding-right: 10px; height: 110px;'>" +
									"<div style='display: table-cell; position: relative; width: 90px; height: 100px; vertical-align: top;'>" +				
										"<a onclick='javascript:appProdcrDtlRead("+prodctSeq+")' style='color: #636566; text-decoration: none;' class='ui-link waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button'>" +
											"<img src='http://sebm.iptime.org:9124/"+prodctMainImage+"' style='width:100%; height:100%'/>" +
										"</a>" +
							        "</div>" +
									"<div class='col-xs-6' style='display: table-cell; padding: 4px 0px 0 15px; vertical-align: top;'>" +
										"<a onclick='javascript:appProdcrDtlRead("+prodctSeq+")' style='color: #636566; text-decoration: none;' class='ui-link waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button'>" +
											"<div>" +
												"<div style='white-space:normal; display: -webkit-box; overflow: hidden; width: 120px;height: 45px; padding-right: 15px; line-height: 20px; color: #666; -webkit-line-clamp: 2; -webkit-box-orient: vertical; word-break: break-all; word-wrap: break-word;'>" +
													"<span class='font-md'" +
													">" + prodctNme + "</span>" +
												"</div>" +
												"<div style='margin: 0 0 -4px; line-height: 27px; color: #262f33;'>" +
													"<br>" +
													"<em id='price-"+i+"' class='font-xlg' style='font-style: normal; font-family: emm_bol; font-weight: bold; letter-spacing: -1px; color: #262f33;'>" + prodctSelPric + "원 </em>" +
												"</div> " +
											"</div>" +
						               "</a>" +
						            "</div>" +
						            "<div style='position: absolute; top: 0; right: 10px;'>" +
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
							                "<input type='hidden' name='selPric' value='"+prodctSelPric+"'>" +
							                "<input type='hidden' name='salePric' value='"+prodctSalePric+"'>" +
							                "<input type='hidden' name='prodctType' value='"+prodctType+"'>" +
							                "<input type='hidden' name='ordrQty' value='1'>" +
							            "</span>" +
							        "</div>" +
						        "</div>" +
							"</li>" +
						"").trigger("create");
						
						if(prodctSalePric != 0 && prodctSalePric != prodctSelPric) {
							priceId = "price-"+i;	
							$($("#"+priceId)).html("" +
								"<span class='font-md' style='text-decoration:line-through;'>" +prodctSelPric + "원</span>" +
								"<em id='price-"+i+"' class='font-xlg' style='font-style: normal; font-family: emm_bol; font-weight: bold; letter-spacing: -1px; color: #FF0000; margin-left: 10px;'>" + prodctSalePric + "원 </em>" +
							"");
						}
					});
				},
				error:function(request,status,error){
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});	
	}
	
	
	
}); 

//연속 검색 시
function appReProdctSearch() {
	var prodctSechText = $("#prodctSechText").val();
	formData = {"prodctSechText":prodctSechText};
	$('#prodctSechTitle').empty();
	$('#prodctList').empty();
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appProdctSearch.do",
		success:function(data){
			$("#prodctSechTitle").append("" +
					"<span class='font-lg'>'"+prodctSechText+"'</span> 검색" +
			"").trigger("create");
			
			$.each(data, function(i, vo){
				var prodctSeq = vo.prodctSeq;
				var prodctMainImage = vo.prodctMainImage;
				var prodctNme = vo.prodctNme;
				var prodctType = vo.prodctType;
				var prodctSelPric = numberWithCommas(vo.prodctSelPric);
				var prodctSalePric = numberWithCommas(vo.prodctSalePric);
				
				$("#prodctList").append("" +
						"<li style='padding-top: 0px; padding-bottom: 10px;' class='ui-li-static ui-body-inherit ui-first-child ui-last-child'>" +
						"<hr style='margin-top: 0px;'>" +
						"<div style='position: relative; padding-left: 10px; padding-right: 10px; height: 110px;'>" +
							"<div style='display: table-cell; position: relative; width: 90px; height: 100px; vertical-align: top;'>" +				
								"<a onclick='javascript:appProdcrDtlRead("+prodctSeq+")' style='color: #636566; text-decoration: none;' class='ui-link waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button'>" +
									"<img src='http://sebm.iptime.org:9124/"+prodctMainImage+"' style='width:100%; height:100%'/>" +
								"</a>" +
					        "</div>" +
							"<div class='col-xs-6' style='display: table-cell; padding: 4px 0px 0 15px; vertical-align: top;'>" +
								"<a onclick='javascript:appProdcrDtlRead("+prodctSeq+")' style='color: #636566; text-decoration: none;' class='ui-link waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button waves-effect waves-button'>" +
									"<div>" +
										"<div style='white-space:normal; display: -webkit-box; overflow: hidden; width: 120px;height: 45px; padding-right: 15px; line-height: 20px; color: #666; -webkit-line-clamp: 2; -webkit-box-orient: vertical; word-break: break-all; word-wrap: break-word;'>" +
											"<span class='font-md'>" + prodctNme + "</span>" +
										"</div>" +
										"<div style='margin: 0 0 -4px; line-height: 27px; color: #262f33;'>" +
											"<br>" +
											"<em id='price-"+i+"' class='font-xlg' style='font-style: normal; font-family: emm_bol; font-weight: bold; letter-spacing: -1px; color: #262f33;'>" + prodctSelPric + "원 </em>" +
										"</div> " +
									"</div>" +
				               "</a>" +
				            "</div>" +
				            "<div style='position: absolute; top: 0; right: 10px;'>" +
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
					                "<input type='hidden' name='prodctSelPric' value='"+prodctSelPric+"'>" +
					                "<input type='hidden' name='prodctSalePric' value='"+prodctSalePric+"'>" +
					                "<input type='hidden' name='prodctType' value='"+prodctType+"'>" +
					                "<input type='hidden' name='ordrQty' value='1'>" +
					            "</span>" +
					        "</div>" +
				        "</div>" +
					"</li>" +
				"").trigger("create");
				
				if(prodctSalePric != 0 && prodctSalePric != prodctSelPric) {
					priceId = "price-"+i;	
					$($("#"+priceId)).html("" +
						"<span class='font-md' style='text-decoration:line-through;'>" +prodctSelPric + "원</span>" +
						"<em id='price-"+i+"' class='font-xlg' style='font-style: normal; font-family: emm_bol; font-weight: bold; letter-spacing: -1px; color: #FF0000; margin-left: 10px;'>" + prodctSalePric + "원 </em>" +
					"");
				}
			});
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
};