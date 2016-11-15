function ordrAddrsPage(){
	sessionStorage.setItem('recvrNme', $("#recvrNme").val());
	sessionStorage.setItem('recvrMobl', $("#recvrMobl").val());
	sessionStorage.setItem('recvrPostcd', $("#recvrPostcd").val());
	sessionStorage.setItem('recvrAddrss', $("#recvrAddrss").val());
	sessionStorage.setItem('recvrDetlAddress', $("#recvrDetlAddress").val());
	sessionStorage.setItem('reqmnt', $("#reqmnt").val());
	sessionStorage.setItem('shippingPlaceSelect', $("#shippingPlaceSelect").val());
	navigator.notification.alert($("#shippingPlaceSelect").val(),null,"알림","확인");
	$.mobile.changePage("MarketOrdrCretPost.html");
}
$(document).on("pageshow","#marketOrdrCretPostPage",function(){
	sessionStorage.setItem('ordrCretShippingType', 1);
});

//주소
function ordrGetAddr(){
	// AJAX 주소 검색 요청
	var adds = $("#form").serialize();
	$.ajax({
		url:"http://sebm.iptime.org:9124/getAddrApi.do"				// 고객사 API 호출할 Controller URL
		,type:"post"
		,data: adds					 								// 요청 변수 설정
		,dataType:"xml"												// 데이터 결과 : XML
		,success:function(xmlStr){									// xmlStr : 주소 검색 결과 XML 데이터
			$("#list").html("");									// 결과 출력 영역 초기화
			var errCode= $(xmlStr).find("errorCode").text();		// 응답코드
			var errDesc= $(xmlStr).find("errorMessage").text();		// 응답메시지
			if(errCode != "0"){ 									// 응답에러시 처리
				alert(errCode+"="+errDesc);
			}else{
				if(xmlStr!= null){
					makeList(xmlStr);								// 결과 XML 데이터 파싱 및 출력
				}
			}
		}
		,error: function(xhr,status, error){
			alert("에러발생");										// AJAX 호출 에러
		}
	});
}

function makeList(xmlStr){
	var htmlStr = "";
	htmlStr += "<table>";
	// jquery를 이용한 XML 결과 데이터 파싱
	$(xmlStr).find("juso").each(function(){
		htmlStr += "<tr onclick='postCoice(this)'>";
		htmlStr += "<td><input type='hidden' id='zipNo' value='"+$(this).find('zipNo').text() +"'>"+$(this).find('zipNo').text() +"</td>";
		htmlStr += "<td><input type='hidden' id='roadAddr' value='"+$(this).find('roadAddr').text() +"'>"+$(this).find('roadAddr').text() +"</td>";
		htmlStr += "</tr>";
	});
	htmlStr += "</table>";
	// 결과 HTML을 FORM의 결과 출력 DIV에 삽입
	$('#list').show();
	$('#postDetailDisplay').hide();
	$("#list").html(htmlStr);
}
function postCoice(tag){
	var zipNo = $(tag).first().children().find("input[id=zipNo]").val();
	var roadAddr = $(tag).first().children().find("input[id=roadAddr]").val();
	$('#list').hide();
	$('#postDetailDisplay').show();
    $("#sendZipNo").text(zipNo);
    $("#sendRoadAddr").text(roadAddr);
}
function ordrPostAdrrsDetail(){
	sessionStorage.setItem('sendZipNoHidn', $("#sendZipNo").text());
    sessionStorage.setItem('sendRoadAddrHidn', $("#sendRoadAddr").text());
    sessionStorage.setItem('postDetail', $("#postDetail").val());
	$.mobile.changePage("MarketOrdrCret.html");

}



$(document).on('pagebeforeshow', "#ordrCretPage", function (event, data) {
	// 장바구니 페이지에서 get 방식으로 보내준 값들
	// url 주소를 구하여 ?,& 등으로 잘라서 구한다.
//	var parameters = $(this).data("url").split("?")[1];
//	parameters1 = parameters.replace("chkGroup=","");
//	var pamntProdct = parameters1.split("&")[0];
//	parameters2 = parameters1.split("&")[1];
//	var pricSum = parameters2.replace("predictSumPrice=","");
//	$("#pamntProdct").val(pamntProdct);
//	$("#pricSum").val(pricSum);
	// 다른 방법 , 세션에 박아서 넣는다.
	var pricSum = sessionStorage.getItem('predictSumPrice', data);
	// 총 결제 예상 금액 변경
	$("#ordrCretPredictSumPrice").html("<font class='font-lg'><b>"+numberWithCommas(pricSum)+"원</b></font>");
	
	existShippingPlaceRead(); // 기본 배송지 조회
	ShippingPlaceList(); // 배송지 리스트 조회
	

	var ordrCretShippingType = sessionStorage.getItem('ordrCretShippingType');
	var shippingPlaceSelect = sessionStorage.getItem('shippingPlaceSelect');
	if(ordrCretShippingType == 1){
		var	shipngPlcSeq = sessionStorage.getItem('shippingPlaceSelect');
		
		if(shipngPlcSeq == 0) {
			$("#shippingPlaceSelect").val('0');
//			$("#shippingPlaceSelectNme").text('직접 입력');
			$("#shippingPlaceSelect").siblings('label').text('직접 입력');
			$("#recvrNme").val(sessionStorage.getItem('recvrNme'));
			$("#recvrMobl").val(sessionStorage.getItem('recvrMobl'));
			$("#recvrPostcd").val(sessionStorage.getItem('sendZipNoHidn'));
			$("#recvrAddrss").val(sessionStorage.getItem('sendRoadAddrHidn'));
			$("#recvrDetlAddress").val(sessionStorage.getItem('postDetail'));
			$("#reqmnt").val(sessionStorage.getItem('reqmnt'));
		}
		else {
			formData = "shipngPlcSeq="+shipngPlcSeq;
			$.ajax({
				type:"POST",
				data:formData,
				url:"http://sebm.iptime.org:9124/shippingPlaceRead.do",
				success:function(data){
					$("#shippingPlaceSelect").val(shippingPlaceSelect);
					$("#shippingPlaceSelect").siblings('label').text(data.shipngPlcNme);
//					$("#shippingPlaceSelectNme").text(data.shipngPlcNme);
					
					
					$("#recvrNme").val(sessionStorage.getItem('recvrNme'));
					$("#recvrMobl").val(sessionStorage.getItem('recvrMobl'));
					$("#recvrPostcd").val(sessionStorage.getItem('sendZipNoHidn'));
					$("#recvrAddrss").val(sessionStorage.getItem('sendRoadAddrHidn'));
					$("#recvrDetlAddress").val(sessionStorage.getItem('postDetail'));
					$("#reqmnt").val(sessionStorage.getItem('reqmnt'));

				},
				error:function(request,status,error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});	
		}

	}

	sessionStorage.removeItem('ordrCretShippingType');
	
	
	
}); 


//오후, 오전 셀렉시 희망 시간 변경(9~12 / 13~20)
function hopDelvrTimChange(tag) {
	var	originAmont = $(tag).val();
	$(tag).siblings('label').text(originAmont);	
	if(originAmont == "오전"){
		$("#hopDelvrHdiv").html("" +
				"<label>09시</label>" +
				"<select name='select-1' id='hopDelvrH' data-mini='true' data-inline='true' onchange='javascript:selectChange(this);'>" +
					"<option>9시</option>" +
					"<option>10시</option>" +
					"<option>11시</option>" +
					"<option>12시</option>" +
				"</select>" +
				"");
	}
	else{
		$("#hopDelvrHdiv").html("" +
				"<label>13시</label>" +
				"<select name='select-1' id='hopDelvrH' data-mini='true' data-inline='true' onchange='javascript:selectChange(this);'>" +
					"<option>13시</option>" +
					"<option>14시</option>" +
					"<option>15시</option>" +
					"<option>16시</option>" +
					"<option>17시</option>" +
					"<option>18시</option>" +
					"<option>19시</option>" +
					"<option>20시</option>" +
				"</select>" +
				"");
	}
	$("#hopDelvrHdiv").listview("refresh");
}



// 배송지 정보 상세조회 (기본 배송지)
function existShippingPlaceRead(){
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
		url:"http://sebm.iptime.org:9124/existShippingPlaceRead.do",
		success:function(data){
				$("#shippingPlaceSelectDiv").html("" +
						"<label>"+data.shipngPlcNme+"</label>" +
						"<select name='' id='shippingPlaceSelect' data-mini='true' data-inline='true' onchange='javascript:shippingPlaceChange(this)'>" +
//							"<option value='"+data.shipngPlcSeq+"'>"+data.shipngPlcNme+"</option>" +
						"</select>");
				$("#recvrNme").val(data.recvrNme);
				$("#recvrMobl").val(data.recvrMobl);
				$("#recvrPostcd").val(data.recvrPostcd);
				$("#recvrAddrss").val(data.recvrAddrss);
				$("#recvrDetlAddress").val(data.recvrDetlAddress);	
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
}


//배송지 리스트 조회
function ShippingPlaceList(){
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
		url:"http://sebm.iptime.org:9124/ShippingPlaceList.do",
		success:function(data){
			$.each(data, function(i, vo){
				$("#shippingPlaceSelect").append("" +
					"<option value='"+vo.shipngPlcSeq+"'>"+vo.shipngPlcNme+"</option>");
			});	
//			$("#shippingPlaceSelect").append("" +
//					"<option value='0'>직접 입력</option>");
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
}

// 배송지 변경
function shippingPlaceChange(tag){
	var	shipngPlcSeq = $(tag).val();
	
	if(shipngPlcSeq == 0) {
		$(tag).siblings('label').text('직접 입력');
		$("#recvrNme").val("");
		$("#recvrMobl").val("");
		$("#recvrPostcd").val("");
		$("#recvrAddrss").val("");
		$("#recvrDetlAddress").val("");	
	}
	else {
		formData = "shipngPlcSeq="+shipngPlcSeq;
		$.ajax({
			type:"POST",
			data:formData,
			url:"http://sebm.iptime.org:9124/shippingPlaceRead.do",
			success:function(data){
				$(tag).siblings('label').text(data.shipngPlcNme);
				$("#recvrNme").val(data.recvrNme);
				$("#recvrMobl").val(data.recvrMobl);
				$("#recvrPostcd").val(data.recvrPostcd);
				$("#recvrAddrss").val(data.recvrAddrss);
				$("#recvrDetlAddress").val(data.recvrDetlAddress);	
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});	
	}
}


// 주문하기, 결제	
function ordrCret(){
	var gropPurchsProdctSeq = sessionStorage.getItem('gropPurchsProdctSeq');
	
	if(gropPurchsProdctSeq != null){
		var formData = $('#ordrCretInsert').serialize();
		var hopH = $('#hopDelvrH').val().substring(0, 2);
		var hopM = $('#hopDelvrM').val().substring(0, 2);
		var hopDelvrTim = hopH+":"+hopM;
		var custmrSeq = sessionStorage.getItem('custmrSeq');
		var pricSum = sessionStorage.getItem('predictSumPrice');
		var ordrAmont = sessionStorage.getItem('gropPurchsProdctProdctAmont');
		formData += "&hopDelvrTim="+hopDelvrTim+"&pricSum="+pricSum +"&custmrSeq="+custmrSeq+"&gropPurchsProdctSeq="+gropPurchsProdctSeq+"&ordrAmont="+ordrAmont;
	
		
		var pamntMethd = document.ordrCretInsert.pamntMethd.value;
		if(pamntMethd == "간편결제") {
			
			$.ajax({
				type:"POST",
				data:formData,
				url:"http://sebm.iptime.org:9124/ordrCretInsert.do", 
				success:function(data){		
					sessionStorage.setItem('ordrStatSeq', 0);
					navigator.notification.alert("주문이 완료 되었습니다.",null,"알림","확인");
					
					//주문 후 푸시 알림
			  		sendTlt = "주문 접수 알림";
			  		sendMsg = time + "에 주문이 접수되었습니다.";
			  		type = "realTimeOrdrList";
			  		$.ajax({
				  		type: "POST",
				  		data:{"sendTlt":sendTlt, "sendMsg":sendMsg, "type":type},
				  		url:"http://sebm.iptime.org:9124/pushRealTiemOrdrList.do",   
				  		success:function(data){
				  	    },
				  		error:function(request,status,error){
				  			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				  		}
			  		});
					
					$.mobile.changePage("MyPageCusOrdrList.html");			
					},
				error:function(request,status,error){
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
			
			
			var custmrId = sessionStorage.getItem('custmrId');
	  		if(custmrId!=null){
	  			custmrId = sessionStorage.getItem('custmrId');
	  		}
	  		else{
	  			custmrId = localStorage.getItem('custmrId');
	  		}
			$.ajax({
			  type: "POST",
			  url:"http://sebm.iptime.org:9124/cusPwRead.do",   
			  data: {"custmrId":custmrId},
			  success:function(data){   	  
				  var custmrNme = data.custmrNme;
				  var custmrMobl = data.custmrMobl;
				  var custmrEml = data.custmrEml;
				  var buyProdctNmeCount = sessionStorage.getItem('gropPurchsProdctNme');
				 
			  	  location.href="http://sebm.iptime.org:9124/AGSMobile_start.do?pricSum="+pricSum+"&buyProdctNmeCount="+encodeURI(encodeURIComponent(buyProdctNmeCount))+"&custmrNme="+encodeURI(encodeURIComponent(custmrNme))+"&custmrMobl="+custmrMobl+"&custmrEml="+custmrEml;
			
				  sessionStorage.removeItem('predictSumPrice');
				  sessionStorage.removeItem('prodctSeqGroup');
				  sessionStorage.removeItem('packgSeqGroup');
				  
				  //주문 후 푸시 알림
				  sendTlt = "주문 접수 알림";
				  sendMsg = time + "에 주문이 접수되었습니다.";
				  type = "realTimeOrdrList";
				  $.ajax({
					  type: "POST",
					  data:{"sendTlt":sendTlt, "sendMsg":sendMsg, "type":type},
					  url:"http://sebm.iptime.org:9124/pushRealTiemOrdrList.do",   
					  success:function(data){
			    	  
					  },
					  error:function(request,status,error){
						  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					  }
				  });
				  
				  $.mobile.changePage("MyPageCusOrdrList.html");		  	    	  
			  },
			  error:function(request,status,error){
			     alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			      }
			   });  
		}
		else{
			navigator.notification.alert("공동구매의 경우 카드결제만 가능하며 \n 결제 시 취소가 불가능합니다.");
		}
		
		
	}
	else{
		var sendTlt; //푸시알림 제목
		var sendMsg; //푸시알림 내용
		var type; //푸시알림 타입
		var d = new Date();
	    var time = d.getFullYear() + "년 " + (d.getMonth() + 1) + "월 " + d.getDate() + "일 " + d.getHours() + "시 " + d.getMinutes() + "분";
	 
		var pricSum = sessionStorage.getItem('predictSumPrice');
		var recvMethd = document.ordrCretInsert.recvMethd.value;	
	
		if(pricSum < 20000 && recvMethd == "배달") {
			navigator.notification.alert("2만원 이상 주문건만 배달이 가능합니다.",null,"알림","확인");
			return;
		}
	
		var formData = $('#ordrCretInsert').serialize();
		var hopH = $('#hopDelvrH').val().substring(0, 2);
		var hopM = $('#hopDelvrM').val().substring(0, 2);
		var hopDelvrTim = hopH+":"+hopM;
		var custmrSeq = sessionStorage.getItem('custmrSeq');
		var prodctSeqGroup = sessionStorage.getItem('prodctSeqGroup');
		var packgSeqGroup = sessionStorage.getItem('packgSeqGroup');
		formData += "&hopDelvrTim="+hopDelvrTim+"&pricSum="+pricSum +"&prodctSeqGroup="+prodctSeqGroup +"&packgSeqGroup="+packgSeqGroup +"&custmrSeq="+custmrSeq;
	
		var pamntMethd = document.ordrCretInsert.pamntMethd.value;	
		if(pamntMethd == "간편결제") {
			$.ajax({
			      type: "POST",
			      url:"http://sebm.iptime.org:9124/cartBuyProdctNme.do",   
			      data: formData,
			      success:function(data){   	  
			    	var buyProdctNmeCount = data.firstProdctNme +" 외 "+data.prodctCount+"종";
			  		var custmrId = sessionStorage.getItem('custmrId');
			  		if(custmrId!=null){
			  			custmrId = sessionStorage.getItem('custmrId');
			  		}
			  		else{
			  			custmrId = localStorage.getItem('custmrId');
			  		}
			  		$.ajax({
			  	      type: "POST",
			  	      url:"http://sebm.iptime.org:9124/cusPwRead.do",   
			  	      data: {"custmrId":custmrId},
			  	      success:function(data){   	  
			  	    	  var custmrNme = data.custmrNme;
			  	    	  var custmrMobl = data.custmrMobl;
			  	    	  var custmrEml = data.custmrEml;
	
			  		  	  location.href="http://sebm.iptime.org:9124/AGSMobile_start.do?pricSum="+pricSum+"&buyProdctNmeCount="+encodeURI(encodeURIComponent(buyProdctNmeCount))+"&custmrNme="+encodeURI(encodeURIComponent(custmrNme))+"&custmrMobl="+custmrMobl+"&custmrEml="+custmrEml;
	
			  	  		  sessionStorage.removeItem('predictSumPrice');
			  	  		  sessionStorage.removeItem('prodctSeqGroup');
			  	  		  sessionStorage.removeItem('packgSeqGroup');
			  	  		  
			  	  		  //주문 후 푸시 알림
				  		  sendTlt = "주문 접수 알림";
				  		  sendMsg = time + "에 주문이 접수되었습니다.";
				  		  type = "realTimeOrdrList";
				  		  $.ajax({
				  			  type: "POST",
				  			  data:{"sendTlt":sendTlt, "sendMsg":sendMsg, "type":type},
				  			  url:"http://sebm.iptime.org:9124/pushRealTiemOrdrList.do",   
				  			  success:function(data){
				  	    	  
				  			  },
				  			  error:function(request,status,error){
				  				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				  			  }
				  		  });
			  	  		  
			  	  		  $.mobile.changePage("MyPageCusOrdrList.html");		  	    	  
			  	      },
			  	      error:function(request,status,error){
			  	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			  	      }
			  	   });  
			      },
			      error:function(request,status,error){
			         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			      }
			   });
		}
		else{
			$.ajax({
				type:"POST",
				data:formData,
				url:"http://sebm.iptime.org:9124/ordrCretInsert.do", 
				success:function(data){		
					sessionStorage.removeItem('predictSumPrice');
					sessionStorage.removeItem('prodctSeqGroup');
					sessionStorage.removeItem('packgSeqGroup');
					
					sessionStorage.setItem('ordrStatSeq', 0);
					navigator.notification.alert("주문이 완료 되었습니다.",null,"알림","확인");
					
					
					//주문 후 푸시 알림
			  		sendTlt = "주문 접수 알림";
			  		sendMsg = time + "에 주문이 접수되었습니다.";
			  		type = "realTimeOrdrList";
			  		$.ajax({
				  		type: "POST",
				  		data:{"sendTlt":sendTlt, "sendMsg":sendMsg, "type":type},
				  		url:"http://sebm.iptime.org:9124/pushRealTiemOrdrList.do",   
				  		success:function(data){
				  	    },
				  		error:function(request,status,error){
				  			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				  		}
			  		});
					
					$.mobile.changePage("MyPageCusOrdrList.html");			
					},
				error:function(request,status,error){
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		}
	}
}	