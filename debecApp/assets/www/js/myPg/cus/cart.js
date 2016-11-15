// 장바구니 (cart) 페이지가 실행되었을 때
$(document).on('pageshow', function(event, data){ //"CartPage" 라는 page가 로딩됐을 때 실행이 되는 함수	
	if ($.mobile.activePage.attr('id') === "CartPage") {	
		cartProdctList(); // 장바구니  list 조회
		
		// 체크박스 전체 선택 / 해제
		$("#cartAllCheck").click(function(){
		    //클릭되었으면
		    if($("#cartAllCheck").prop("checked")){
		    	//input태그의 name이 cartCheck인 태그들을 찾아서 checked옵션을 true로 정의
		    	$("input[name=cartCheck]").prop("checked",true).checkboxradio("refresh");
		    	$("input[name=cartPackgCheck]").prop("checked",true).checkboxradio("refresh");
		    	predictSumPriceRead(); 
		    }
		    //클릭이 안되있으면
		    else{
		    	//input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
		    	$("input[name=cartCheck]").prop("checked",false).checkboxradio("refresh");
		    	$("input[name=cartPackgCheck]").prop("checked",false).checkboxradio("refresh");
		    	$("#predictSumPrice").html("0원");
		    }
		});
	}
});


// 장바구니 상품 list 조회(상품만)
function cartProdctList(){
	var predictSumPrice = 0;
//	var formData = $("#cartFrm").serialize();
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	if(custmrSeq!=null){
		custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		custmrSeq = localStorage.getItem('custmrSeq');
	}
	
	
	formData = "cusSeq="+custmrSeq;
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/cartList.do",
		success:function(data){
			$.each(data, function(i, vo){
				prodctSalePric = vo.prodctSalePric;
				cartBridgSeq = vo.cartBridgSeq;
				prodctNme = vo.prodctNme;
				prodctPurchsPric = vo.prodctPurchsPric;
				prodctAmont = vo.prodctAmont;
				prodctMainImage = vo.prodctMainImage;
				
				$("#cartContent").append("" +
					"<div id='cartContent-"+cartBridgSeq+"' data-role='content' style='border:1px solid gray; padding-left:10px; padding-right:10px; padding-bottom:10px; margin-bottom:15px'> " +
						"<fieldset data-role='controlgroup' style='padding-top:7px; width:30%'> " +
							"<input type='checkbox' name='cartCheck' value='"+cartBridgSeq+"' id='checkbox-"+i+"' checked onchange='cartCheckChg("+cartBridgSeq+","+i+")'> " +
							"<label for='checkbox-"+i+"'> &nbsp;	</label> " +
						"</fieldset> " +
						"<div class='row'> " +
							"<div class='col-xs-4'> " +
								"<img src='http://sebm.iptime.org:9124/"+prodctMainImage+"' width='100%' height='100%'>" +
							"</div>" +
							"<div class='col-xs-8'>" +
								"<div class='box'>" +
									"<font class='font-md'>"+prodctNme+"</font><br>" +
									"<b id='price-"+i+"' class='font-lg'>"+numberWithCommas(prodctPurchsPric)+"원</b>" +
									"<div class='ui-select'>" +
										"<div id='' class='ui-btn ui-icon-carat-d ui-btn-icon-right ui-corner-all ui-shadow'>" +
											"<label>"+prodctAmont+"</label>" +
											"<select name='select-1' id='select-"+i+"' data-mini='true' data-inline='true' onchange='javascript:cartProdctAmontChg("+cartBridgSeq+",this);'>" +
												"<option>1</option> <option>2</option> <option>3</option> <option>4</option> <option>5</option>" +
												"<option>6</option> <option>7</option> <option>8</option> <option>9</option> <option>10</option>" +
												"<option>11</option> <option>12</option> <option>13</option> <option>14</option> <option>15</option>" +
												"<option>16</option> <option>17</option> <option>18</option> <option>19</option> <option>20</option>" +
											"</select>" +
										"</div>" +
									"</div>" +
								"</div>" +
							"</div>" +
						"</div>" +
						"<hr>" +
						"<a href='#' class='ui-btn' style='background-color:#F3F3F3; border:1px solid #D5D5D5' onclick='cartProdctDelete("+cartBridgSeq+")'><b>삭제</b></a>" +
					"</div>" +
				"").trigger("create");
				
				if(prodctSalePric != 0 && prodctSalePric != prodctPurchsPric) {
					priceId = "price-"+i;	
					$($("#"+priceId)).html("" +
						"<span class='font-md' style='text-decoration:line-through;'>" +
						numberWithCommas(prodctPurchsPric) + "원</span><br>" +
						numberWithCommas(prodctSalePric) + "원" +
							"");
					
				}
				
				// select-id로 수량 select 해주기(처음에 무조건 1, append 안에서 for, if 못씀)
				selectId = "select-"+i;	
				$($("#"+selectId)).val(prodctAmont);
			});
			cartPackgList(); // 장바구니 페키지 list 조회(패키지만)
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
}


//패키지  list 조회(패키지만)
function cartPackgList(){
	var predictSumPrice = 0;
//	var formData = $("#cartFrm").serialize();
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	if(custmrSeq!=null){
		custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		custmrSeq = localStorage.getItem('custmrSeq');
	}
	
	
	formData = "cusSeq="+sessionStorage.getItem('custmrSeq');
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/cartPackgList.do",
		success:function(data){
			$("#cartContent").append("<div class='font-md'><strong>패키지 상품</strong></div><hr>");
			$.each(data, function(i, vo){
				prodctSalePric = vo.prodctSalePric;
				packgBridgCartSeq = vo.packgBridgCartSeq;
				prodctNme = vo.prodctNme;
				prodctPurchsPric = vo.prodctPurchsPric;
				prodctAmont = vo.prodctAmont;
				prodctMainImage = vo.prodctMainImage;				
				$("#cartContent").append("" +
					"<div id='cartPackgContent-"+packgBridgCartSeq+"' data-role='content' style='border:1px solid gray; padding-left:10px; padding-right:10px; padding-bottom:10px; margin-bottom:15px'> " +
						"<fieldset data-role='controlgroup' style='padding-top:7px; width:30%'> " +
							"<input type='checkbox' name='cartPackgCheck' value='"+packgBridgCartSeq+"' id='checkboxPackg-"+i+"' checked onchange='cartCheckChg("+packgBridgCartSeq+","+i+")'> " +
							"<label for='checkboxPackg-"+i+"'> &nbsp;	</label> " +
						"</fieldset> " +
						"<div class='row'> " +
							"<div class='col-xs-4'> " +
								"<img src='../../패키지.img' width='100%' height='100%'>" +
							"</div>" +
							"<div class='col-xs-8'>" +
								"<div class='box'>" +
									"<font class='font-md'>"+prodctNme+"</font><br>" +
									"<b id='pricePackg-"+i+"' class='font-lg'>"+numberWithCommas(prodctPurchsPric)+"원</b>" +
									"<div class='ui-select'>" +
										"<div id='' class='ui-btn ui-icon-carat-d ui-btn-icon-right ui-corner-all ui-shadow'>" +
											"<label>"+prodctAmont+"</label>" +
											"<select name='select-1' id='selectPackg-"+i+"' data-mini='true' data-inline='true' onchange='javascript:cartPackgAmontChg("+packgBridgCartSeq+",this);'>" +
												"<option>1</option> <option>2</option> <option>3</option> <option>4</option> <option>5</option>" +
												"<option>6</option> <option>7</option> <option>8</option> <option>9</option> <option>10</option>" +
												"<option>11</option> <option>12</option> <option>13</option> <option>14</option> <option>15</option>" +
												"<option>16</option> <option>17</option> <option>18</option> <option>19</option> <option>20</option>" +
											"</select>" +
										"</div>" +
									"</div>" +
								"</div>" +
							"</div>" +
						"</div>" +
						"<hr>" +
						"<a href='#' class='ui-btn' style='background-color:#F3F3F3; border:1px solid #D5D5D5' onclick='cartPackgDelete("+packgBridgCartSeq+")'><b>삭제</b></a>" +
					"</div>" +
				"").trigger("create");
				
				if(prodctSalePric != prodctPurchsPric) {
					pricePackgId = "pricePackg-"+i;	
					$($("#"+pricePackgId)).html("" +
						"<span class='font-md' style='text-decoration:line-through;'>" +
						numberWithCommas(prodctPurchsPric) + "원</span><br>" +
						numberWithCommas(prodctSalePric) + "원" +
							"");
				}
				
				// select-id로 수량 select 해주기(처음에 무조건 1, append 안에서 for, if 못씀)
				selectPackgId = "selectPackg-"+i;	
				$($("#"+selectPackgId)).val(prodctAmont);
			});
			
			predictSumPriceRead(); 
			$("#cartContent").listview("refresh");
			
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}


// 장바구니 안의 상품 수량 수정했을때 -> 총 결제 예상 금액 변경
function cartProdctAmontChg(cartBridgSeq, tag) {
	var	originAmont = $(tag).val();
	$(tag).siblings('label').text(originAmont);
	var newAmont = $(tag).val();
//	 var formData = $("#cartForm").serialize();
	formData = "cartBridgSeq="+cartBridgSeq+"&prodctAmont="+newAmont;
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/cartProdctAmontUpdate.do",
		success:function(data){
			predictSumPriceRead();
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
};

//장바구니 안의 상품 수량 수정했을때 -> 총 결제 예상 금액 변경
function cartPackgAmontChg(packgBridgCartSeq, tag) {
	var	originAmont = $(tag).val();
	$(tag).siblings('label').text(originAmont);
	var newAmont = $(tag).val();
//	 var formData = $("#cartForm").serialize();
	formData = "packgBridgCartSeq="+packgBridgCartSeq+"&prodctAmont="+newAmont;
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/cartPackgAmontUpdate.do",
		success:function(data){
			predictSumPriceRead();
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
};


// 예상 합계 금액을 구하는 메서드
function predictSumPriceRead() {
	predictSumPrice = 0;
	var cartChkGroup = checkedChkGroup();
	var cartChkPackgGroup = checkedChkPackgGroup();
	if(cartChkGroup == "" && cartChkPackgGroup == "") {
		$("#predictSumPrice").html("0원");
	}
	else{
		formData = "cartChkGroup="+cartChkGroup + "&cartPackgChkGroup="+cartChkPackgGroup;
		$.ajax({
			type:"POST",
			data:formData,
			url:"http://sebm.iptime.org:9124/predictSumPriceRead.do",
			success:function(data){
				$("#predictSumPrice").html(numberWithCommas(data)+"원");
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}	
}
//
//
// 개별 체크박스 선택 / 해제
function cartCheckChg(cartBridgSeq, id) {
//	checkboxId = "checkbox-"+id;
//    if($("#"+checkboxId).prop("checked")){
//    	alert("체크");    	
//    }
//    //클릭이 안되있으면
//    else{
//    	alert("해제");
//    }
    predictSumPriceRead();
}

// 선택된 체크박스들만 구하기  - return : 선택된 체크박스 cart bridge seq 그룹
function checkedChkGroup() {
	var chk = document.getElementsByName("cartCheck"); // name이 cartCheck인 체크박스 객체들을 담는다
	var len = chk.length;    //체크박스의 전체 개수
	var checkRow = '';      //체크된 체크박스의 value를 담기위한 변수
	var checkCnt = 0;        //체크된 체크박스의 개수
	var checkLast = '';      //체크된 체크박스 중 마지막 체크박스의 인덱스를 담기위한 변수
	var rowid = '';             //체크된 체크박스의 모든 value 값을 담는다
	var cnt = 0;    
	
	for(var i=0; i<len; i++){
		if(chk[i].checked == true){
			checkCnt++;        //체크된 체크박스의 개수
			checkLast = i;     //체크된 체크박스의 인덱스
		}
	} 
	
	for(var i=0; i<len; i++){
		if(chk[i].checked == true){  //체크가 되어있는 값 구분
			checkRow = chk[i].value;         	
			if(checkCnt == 1){                            //체크된 체크박스의 개수가 한 개 일때,
				rowid += ""+checkRow+"";        //'value'의 형태 (뒤에 ,(콤마)가 붙지않게)
			}
			else
			{                                            //체크된 체크박스의 개수가 여러 개 일때,
				if(i == checkLast){                     //체크된 체크박스 중 마지막 체크박스일 때,
					rowid += ""+checkRow+"";  //'value'의 형태 (뒤에 ,(콤마)가 붙지않게)
				}
				else{
					rowid += ""+checkRow+",";	 //'value',의 형태 (뒤에 ,(콤마)가 붙게)         			
				}				
			}
			cnt++;
			checkRow = '';    //checkRow초기화.
		}	
	}
	return rowid;
}

//선택된 체크박스들만 구하기  - return : 선택된 체크박스 패키지c art bridge seq 그룹
function checkedChkPackgGroup() {
	var chk = document.getElementsByName("cartPackgCheck"); // name이 cartCheck인 체크박스 객체들을 담는다
	var len = chk.length;    //체크박스의 전체 개수
	var checkRow = '';      //체크된 체크박스의 value를 담기위한 변수
	var checkCnt = 0;        //체크된 체크박스의 개수
	var checkLast = '';      //체크된 체크박스 중 마지막 체크박스의 인덱스를 담기위한 변수
	var rowid = '';             //체크된 체크박스의 모든 value 값을 담는다
	var cnt = 0;    
	
	for(var i=0; i<len; i++){
		if(chk[i].checked == true){
			checkCnt++;        //체크된 체크박스의 개수
			checkLast = i;     //체크된 체크박스의 인덱스
		}
	} 
	
	for(var i=0; i<len; i++){
		if(chk[i].checked == true){  //체크가 되어있는 값 구분
			checkRow = chk[i].value;         	
			if(checkCnt == 1){                            //체크된 체크박스의 개수가 한 개 일때,
				rowid += ""+checkRow+"";        //'value'의 형태 (뒤에 ,(콤마)가 붙지않게)
			}
			else
			{                                            //체크된 체크박스의 개수가 여러 개 일때,
				if(i == checkLast){                     //체크된 체크박스 중 마지막 체크박스일 때,
					rowid += ""+checkRow+"";  //'value'의 형태 (뒤에 ,(콤마)가 붙지않게)
				}
				else{
					rowid += ""+checkRow+",";	 //'value',의 형태 (뒤에 ,(콤마)가 붙게)         			
				}				
			}
			cnt++;
			checkRow = '';    //checkRow초기화.
		}	
	}
	return rowid;
}



// 장바구니 상품 삭제
function cartProdctDelete(cartBridgSeq){
	formData = "cartBridgSeq="+cartBridgSeq;
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/cartProdctDelete.do",
		success:function(data){	
			cartContentId = "cartContent-"+cartBridgSeq;
			$("#"+cartContentId).remove();
			predictSumPriceRead();
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}


//장바구니 상품 삭제(패키지)
function cartPackgDelete(packgBridgCartSeq){
	formData = "packgBridgCartSeq="+packgBridgCartSeq;
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/cartPackgDelete.do",
		success:function(data){	
			cartContentId = "cartPackgContent-"+packgBridgCartSeq;
			$("#"+cartContentId).remove();
			predictSumPriceRead();
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}


//장바구니 체크한 상품 전체 삭제
function allCartProdctDelete(){
	var cartChkGroup = checkedChkGroup();
	var cartChkPackgGroup = checkedChkPackgGroup();

	if(cartChkGroup == "" && cartChkPackgGroup == "") {
		navigator.notification.alert("상품을 선택해주세요.",null,"상품 삭제","확인");
	}	
	else{
		formData = "cartChkGroup="+cartChkGroup + "&cartPackgChkGroup="+cartChkPackgGroup;
		$.ajax({
			type:"POST",
			data:formData,
			url:"http://sebm.iptime.org:9124/cartProdctGroupDelete.do",
			success:function(data){
				$("#cartContent").empty();
				cartProdctList(); // 삭제 안된 상품 리스트 다시 뿌림
				$("input[name=cartCheck]").prop("checked",false).checkboxradio("refresh"); // 나는 자동 check 로 했으니 체크된건 다삭제되었어야 하니 uncheck로 바꿈 
		    	$("input[name=cartPackgCheck]").prop("checked",false).checkboxradio("refresh"); // 나는 자동 check 로 했으니 체크된건 다삭제되었어야 하니 uncheck로 바꿈 
				$("#predictSumPrice").html("0원");
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
}



//주문하기
function cartOrdr() {	
	var cartChkGroup = checkedChkGroup();
	var cartChkPackgGroup = checkedChkPackgGroup();
	sessionStorage.setItem('prodctSeqGroup', cartChkGroup);
	sessionStorage.setItem('packgSeqGroup', cartChkPackgGroup);
	if(cartChkGroup == "" && cartChkPackgGroup == "") {
		navigator.notification.alert("상품을 선택해주세요.",null,"상품 선택","확인");
	}
	else {
		formData = "cartChkGroup="+cartChkGroup + "&cartPackgChkGroup="+cartChkPackgGroup;
		$.ajax({
			type:"POST",
			data:formData,
			url:"http://sebm.iptime.org:9124/predictSumPriceRead.do",
			success:function(data){
				sessionStorage.setItem('predictSumPrice', data);
				$.mobile.changePage("MarketOrdrCret.html");
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}		
}


