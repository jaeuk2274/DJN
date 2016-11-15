// 대백제 페이지가 실행되었을 때
$(document).on('pageshow', "#onlyYouPage", function (event, data) {
	var sessionEmpSeq = sessionStorage.getItem('empSeq');
	if(sessionEmpSeq!=null){
		var empSeq = sessionStorage.getItem('empSeq');
	}
	else{
		var empSeq = localStorage.getItem('empSeq');
	}
	
	if(empSeq == null){
		var custmrSeq = sessionStorage.getItem('custmrSeq');
		$.ajax({
			type:"POST",
			data:{"custmrSeq":custmrSeq},
			url:"http://sebm.iptime.org:9124/appOnlyYou.do",
			success:function(data){
				onlyYouCount = data;
				if(onlyYouCount >= 3) {
					onlyYouInfo();
				}
				else {
					onlyYouNotInfo();
				}
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
	else{
		onlyYouNotInfo();
	}
}); 

//너만상 정보 조회
function onlyYouInfo(){
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	$.ajax({
		type:"POST",
		data:{"custmrSeq":custmrSeq},
		url:"http://sebm.iptime.org:9124/appOnlyYouRead.do",
		success:function(data){
			$("#onlyYouContent").append("" + 
				"<div class='row' data-inset='false'>" + 
					"<div>" + 
						"<img src='../img/OnlyYouBanner.png' style='width:100%'/>" + 
					"</div>" + 
				"</div>" + 
			"").trigger("create");
			
			$.each(data, function(i, vo){
				var prodctSeq = vo.prodctSeq;
				var prodctMainImage = vo.prodctMainImage;
				var prodctNme = vo.prodctNme;
				var prodctSelPric = vo.prodctSelPric;
				var prodctSalePric = vo.prodctSalePric;
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

//너만상 정보 없을 경우
function onlyYouNotInfo(){
	$("#onlyYouContent").append("" + 
		"<div class='nd2-card'>" +
			"<div class='card-media'>" +
				"<img src='../img/OnlyYouBanner.png' style='width:100%'/>" +
			"</div>" +
			"<div class='card-supporting-text'>" + 
				"<h4 class='nd2-headline font-lg' style='text-align:center'>" + 
					"<span class='font-xlg' style='color:#8BC34A'><b>당신만을 위한</b></span>" +
					" 더 <span class='font-xlg' style='color:#8BC34A'><b>좋은 상품</b></span>을" +
					"<br>" +
					"준비하고 있습니다." +
				"</h4>" +
			"</div>" +
		"</div>" + 
	"").trigger("create");
};