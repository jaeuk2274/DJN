// 고객 마이페이지 메인 page show
$(document).on("pageshow","#cusMyPageMainPage",function(){

	cusOrdrCountList();
	cusInfoCount();
});


//배송상태별 건수조회
function cusOrdrCountList(){
	var cusSeq = sessionStorage.getItem('custmrSeq');
	if(cusSeq!=null){
		cusSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		cusSeq = localStorage.getItem('custmrSeq');
	}
	
	
	$.ajax({
      type: "POST",
      url:"http://sebm.iptime.org:9124/cusOrdrCountList.do",   
      data: {"cusSeq":cusSeq},
      success:function(data){
    	  $.each(data, function(i, vo){
    		 if(i==0){
    			 $('#cusOrdrFinish').text(vo.ordrNumbrCount);
    		 } 
    		 if(i==1){
    			 $('#cusShipng').text(vo.ordrNumbrCount);
    		 }
    		 if(i==2){
    			 $('#cusShipngFinish').text(vo.ordrNumbrCount);
    		 }
    	  });
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}


// 고객 정보 조회, 장바구니 개수, 히스토리 개수
function cusInfoCount(){
	var cusSeq = sessionStorage.getItem('custmrSeq');
	if(cusSeq!=null){
		cusSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		cusSeq = localStorage.getItem('custmrSeq');
	}
	$.ajax({
      type: "POST",
      url:"http://sebm.iptime.org:9124/cusInfoCount.do",   
      data: {"cusSeq":cusSeq},
      success:function(data){   	  
    	  $('#myPageCusNme').text(data.custmrNme+" 님, 환영합니다!");
    	  $('#cartCount').text(data.cartCount+"건");
    	  $('#historyCount').text(data.historyCount+"건");
    	  sessionStorage.setItem('pushCheck', data.pushCheck);
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}






//환경설정 화면 뜰 때
$(document).on("pageshow","#cusMyPageSetupPage",function(){
	var pushCheck = sessionStorage.getItem('pushCheck');	
	if(sessionStorage.getItem('pushCheck')=='Y'){
    	$("#pushCheckToggle option:eq(1)").attr("selected", "selected");
    }
    else{
    	$("#pushCheckToggle option:eq(0)").attr("selected", "selected");
    }
	
});

//푸시 알림을 온,오프 했을때 푸시 알림 설정 수정
function pushCheckUpdate(){
	var pushCheck = $("#pushCheckToggle").val();
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	if(custmrSeq!=null){
		custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		custmrSeq = localStorage.getItem('custmrSeq');
	}
	$.ajax({
	      type: "POST",
	      url:"http://sebm.iptime.org:9124/pushCheckUpdate.do",   
	      data:{"pushCheck":pushCheck, "custmrSeq":custmrSeq},
	      success:function(){
	    	  sessionStorage.setItem('pushCheck', pushCheck);
//	    	  if(pushCheck == 'N'){
//	    		  navigator.notification.alert("푸시 알림을 off 하셨습니다.",null,"푸시 알림 설정","확인");
//	    	  }
//	    	  else{
//	    		  navigator.notification.alert("푸시 알림을 on 하셨습니다.",null,"푸시 알림 설정","확인");
//	    	  }	    	 
	      },
	      error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	      }
	   });	
}



//개인정보 수정 확인 화면 뜰때 
$(document).on("pageshow","#cusMyPageSetupConfirmPage",function(){
	var custmrId = sessionStorage.getItem('custmrId');
	if(custmrId!=null){
		custmrId = sessionStorage.getItem('custmrId');
	}
	else{
		custmrId = localStorage.getItem('custmrId');
	}
	
	$("#custmrId").val(custmrId);

});


//개인정보 확인값 조회
function selectCusMyPageInfoCount(){
//	custmrId
//	custmrPw
	var formData = $('#cusMyPageSetupConfirmFrm').serialize();
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/cusInfoConfirmRead.do",
		success:function(data){
			if(data.cusCount==1){
				$.mobile.changePage("MyPageCusSetupRead.html");				
				sessionStorage.setItem('custmrId',data.custmrId);
				sessionStorage.setItem('custmrNme',data.custmrNme);
				sessionStorage.setItem('custmrMobl',data.custmrMobl);
				sessionStorage.setItem('custmrEml',data.custmrEml);
				sessionStorage.setItem('custmrPw',data.custmrPw);				
			}
			else{
				navigator.notification.alert("비밀번호를 다시 확인해주세요.",null,"알림","확인");
			}
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}	




//개인정보확인 후 조회 및 수정 화면 뜰때 
$(document).on("pageshow","#cusMyPageSetupReadPage",function(){
	var custmrId = sessionStorage.getItem('custmrId');
	var custmrNme = sessionStorage.getItem('custmrNme');
	var custmrMobl = sessionStorage.getItem('custmrMobl');
	var custmrEml = sessionStorage.getItem('custmrEml');
	
	$("#custmrId").val(custmrId);
	$("#custmrNme").val(custmrNme);
	$("#custmrMobl").val(custmrMobl);
	$("#custmrEml").val(custmrEml);

});


//마이페이지 관리자 - 환경설정 - 수정,완료버튼 관련 함수
function changeCusMyPageInfo(){ 
	$('#custmrId').attr('readonly',false);
	$('#custmrNme').attr('readonly',false);
	$('#custmrMobl').attr('readonly',false);
	$('#custmrEml').attr('readonly',false);
	$('#updateBtnCus').css('display','none');
	$('#finishBtnCus').css('display','block');
}

//마이페이지 관리자 - 환경설정 - 수정,완료버튼 관련 함수
function finishCusMyPageInfo(){ 
	$('#custmrId').attr('readonly',true);
	$('#custmrNme').attr('readonly',true);
	$('#custmrMobl').attr('readonly',true);
	$('#custmrEml').attr('readonly',true);
	$('#updateBtnCus').css('display','block');
	$('#finishBtnCus').css('display','none');
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	if(custmrSeq!=null){
		custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		custmrSeq = localStorage.getItem('custmrSeq');
	}
	
	var formData = $("#custmrInfoUpdateFrm").serialize();
	formData = formData + "&custmrSeq="+custmrSeq;
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/cusInfoUpdate.do",
		success:function(data){
			navigator.notification.alert("개인정보 수정이 완료되었습니다.",null,"알림","확인");
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}


function updateCusPw(){
	var pw = sessionStorage.getItem('custmrPw');
	var curntPw = $('#curntPw').val();
	var newPw = $('#pw').val();
	var newPw2 = $('#pw2').val();
     
	//기존 비밀번호 입력 여부
	if (curntPw == "") {
		navigator.notification.alert("현재 비밀번호를 입력해주세요.",null,"알림","확인");
		return false;
	}
	
	//새 비밀번호 입력 여부
	if (newPw == "" || newPw2 == ""){
		navigator.notification.alert("새 비밀번호를 입력해주세요.",null,"알림","확인");
		return false;
	}
	
	// 길이
    if(!/^[a-zA-Z0-9!@#$%^&*()?_~]{5,20}$/.test(newPw))
    { 
        navigator.notification.alert("비밀번호는 숫자, 영문, 특수문자 조합으로 5~20자리를 사용해야 합니다.",null,"알림","확인");
        $('#pw').val("");
        $('#pw2').val("");
        return false;
    }
     
    // 영문, 숫자, 특수문자 2종 이상 혼용
    var chk = 0;
    if(newPw.search(/[0-9]/g) != -1 ) chk ++;
    if(newPw.search(/[a-z]/ig)  != -1 ) chk ++;
    if(newPw.search(/[!@#$%^&*()?_~]/g)  != -1  ) chk ++;
    if(chk < 2)
    { 
        navigator.notification.alert("비밀번호는 숫자, 영문, 특수문자를 두가지이상 혼용하여야 합니다.",null,"알림","확인");
        $('#pw').val("");
        $('#pw2').val("");
        return false;
    }
     
    // 동일한 문자/숫자 4이상, 연속된 문자
    if(/(\w)\1\1\1/.test(newPw) || isContinuedValue(newPw))
    {
        navigator.notification.alert("비밀번호에 4자 이상의 연속 또는 반복 문자 및 숫자를 사용하실 수 없습니다.",null,"알림","확인");
        $('#pw').val("");
        $('#pw2').val("");
        return false;
    }
    
	// 기존 비밀번호 일치 여부
    if (pw != curntPw){
    	navigator.notification.alert("현재 비밀번호가 맞지 않습니다.",null,"알림","확인");
    	return false;
    }
	
	// 기존 비밀번호와 새 비밀번호 일치 여부
    if (curntPw == newPw2) {
        navigator.notification.alert("현재 비밀번호와 새 비밀번호는 다르게 설정해야 합니다.",null,"알림","확인");
        return false;
    }    
    
    // 재입력 일치 여부
    if (newPw != newPw2) {
        navigator.notification.alert("입력한 두 개의 비밀번호가 서로 일치하지 않습니다.",null,"알림","확인");
        $('#pw').val("");
        $('#pw2').val("");
        return false;
    }
    
    var custmrSeq = sessionStorage.getItem('custmrSeq');
    if(custmrSeq!=null){
    	custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		custmrSeq = localStorage.getItem('custmrSeq');
	}
    
	formData = "custmrPw="+newPw+"&custmrSeq="+custmrSeq;
    
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/cusPwUpdate.do",
		success:function(data){
			navigator.notification.alert("비밀번호 수정이 완료되었습니다.",null,"알림","확인");
			sessionStorage.setItem('custmrPw',newPw);
			$.mobile.changePage("MyPageCusMain.html");
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}


//회원 탈퇴 화면
$(document).on("pageshow","#cusDeletePage",function(){
	var custmrId = sessionStorage.getItem('custmrId');
	if(custmrId!=null){
		custmrId = sessionStorage.getItem('custmrId');
	}
	else{
		custmrId = localStorage.getItem('custmrId');
	}
	$("#custmrId").val(custmrId);
});



// 회원 탈퇴
function cusDelete(){
	var custmrId = sessionStorage.getItem('custmrId');
	if(custmrId!=null){
		custmrId = sessionStorage.getItem('custmrId');
	}
	else{
		custmrId = localStorage.getItem('custmrId');
	}
	formData = "custmrId="+custmrId;
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/cusPwRead.do",
		success:function(data){						
			sessionStorage.setItem('custmrPw',data.custmrPw);				
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
	var custmrPw = sessionStorage.getItem('custmrPw');	
	
	if($("#custmrPw").val() == custmrPw){
		if (confirm('정말로 회원 탈퇴를 하시겠습니까?')) {
			var custmrSeq = sessionStorage.getItem('custmrSeq');
			if(custmrSeq!=null){
				custmrSeq = sessionStorage.getItem('custmrSeq');
			}
			else{
				custmrSeq = localStorage.getItem('custmrSeq');
			}
			formData = "custmrSeq="+custmrSeq;
			$.ajax({
				type:"POST",
				data:formData,
				url:"http://sebm.iptime.org:9124/cusDelete.do",
				success:function(data){						
					navigator.notification.alert("회원 탈퇴가 되었습니다.",null,"알림","확인");
					navigator.notification.alert("앱을 종료합니다.",null,"알림","확인");
			        navigator.app.exitApp();				
				},
				error:function(request,status,error){
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
	    }
		else{
			return;
		}			
	}
	else{
		navigator.notification.alert("비밀번호를 확인해주세요.",null,"알림","확인");
	}	
}




//마이페이지 고객 - 배송조회 각각 조회
function selectCusOrdrList(ordrStatSeq){
	switch (ordrStatSeq) {
	case 0: //전체조회
		sessionStorage.setItem('ordrStatSeq', 0);
		$.mobile.changePage("MyPageCusOrdrList.html");
		break;
	case 1: //주문완료
		sessionStorage.setItem('ordrStatSeq', 1);
		$.mobile.changePage("MyPageCusOrdrList.html");
		break;
	case 2: //배송중
		sessionStorage.setItem('ordrStatSeq', 2);
		$.mobile.changePage("MyPageCusOrdrList.html");
		break;
	case 3: //배송완료
		sessionStorage.setItem('ordrStatSeq', 3);
		$.mobile.changePage("MyPageCusOrdrList.html");
		break;
	case 4: //주문취소
		sessionStorage.setItem('ordrStatSeq', 4);
		$.mobile.changePage("MyPageCusOrdrCancelList.html");
		break;
	}
}

//페이지 처음 조회 버튼 클릭되어 있게
function cusOrdrListButtonChecked(ordrStatSeq){
	switch (ordrStatSeq) {
	case '0':
		$("#ordrStatAll").attr('checked',true).checkboxradio('refresh');
		$("#ordrStatAll2").attr('checked',true).checkboxradio('refresh');
		break;
	case '1':
		$("#ordrStatAccept").attr('checked',true).checkboxradio('refresh');
		$("#ordrStatAccept2").attr('checked',true).checkboxradio('refresh');
		break;
	case '2':
		$("#ordrStatShipng").attr('checked',true).checkboxradio('refresh');
		$("#ordrStatShipng2").attr('checked',true).checkboxradio('refresh');
		break;
	case '3':
		$("#ordrStatFinish").attr('checked',true).checkboxradio('refresh');
		$("#ordrStatFinish2").attr('checked',true).checkboxradio('refresh');
		break;
	}
}


// 주문목록 조회 (전체, 상태별 포함)
function cusStatOrdrList(ordrStatSeq, custmrSeq){
	 $('#cusOrdrFrame').empty();
	//일반 배송목록 조회
	$.ajax({
      type: "POST",
      url:"http://sebm.iptime.org:9124/cusOrdrList.do",   
      data:{"ordrStatSeq":ordrStatSeq, "custmrSeq":custmrSeq},
      success:function(data){
    	  $.each(data, function(i, vo){
    		  var ordrNumbrSeq = vo.ordrNumbrSeq;
    		  var ordrDat = vo.ordrDat;
    		  var ordrStat = vo.ordrStat;
    		  var prodctNme = vo.prodctNme;
    		  if(vo.cartImg1 == null){
      			  var cartImg1 = "../img/no_image.gif";
      		  }
      		  else{
      			  var cartImg1 = "http://sebm.iptime.org:9124/" + vo.cartImg1;  
      		  }  
    		 
    		  var recvrMobl = vo.recvrMobl;
    		  var ordrProdctAmont = vo.ordrProdctAmont;
    		  var allPric = numberWithCommas(vo.allPric);
    		  if(i!=0){
    			  $('#cusOrdrFrame').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%; margin-top:10%'>"
		  					+ "<table>"
		  					+ "<tr>"
		  					+ "<td>"+ordrDat+"</td>"
		  					+ "<td style='float:right'><a onclick='cusOrdrRead("+ordrNumbrSeq+",0)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>주문상세보기 ></a>"
		  					+ "</td>"
		  					+ "</tr>"
		  					+ "</table>"
		  					+ "<div data-role='content' style='border-top:3px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
		  					+ "<div class='row'>"
		  					+ "<div class='col-xs-5'>"
		  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
		  					+ "</div>"
		  					+ "<div class='col-xs-7'>"
		  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
		  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + " 외 " + ordrProdctAmont + "건 </div>"
		  					+ "<h5 style='text-align:right'>"+ numberWithCommas(allPric) + "원</h5>"
		  					+ "</div>"
		  					+ "</div>"
		  					+ "</div>"
		  					+ "</div>"
    			  );
    		  }
    		  else{
    			  $('#cusOrdrFrame').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%;'>"
		  					+ "<table>"
		  					+ "<tr>"
		  					+ "<td>"+ordrDat+"</td>"
		  					+ "<td style='float:right'><a onclick='cusOrdrRead("+ordrNumbrSeq+",0)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>주문상세보기 ></a>"
		  					+ "</td>"
		  					+ "</tr>"
		  					+ "</table>"
		  					+ "<div data-role='content' style='border-top:3px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
		  					+ "<div class='row'>"
		  					+ "<div class='col-xs-5'>"
		  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
		  					+ "</div>"
		  					+ "<div class='col-xs-7'>"
		  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
		  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + " 외 " + ordrProdctAmont + "건 </div>"
		  					+ "<h5 style='text-align:right'>"+ numberWithCommas(allPric) + "원</h5>"
		  					+ "</div>"
		  					+ "</div>"
		  					+ "</div>"
		  					+ "</div>"
    			  );
    		  }    		  	
    	  });
		  $("#cusOrdrFrame").listview("refresh");
		  sessionStorage.removeItem('ordrStatSeq');
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}

//공동구매 조회
function cusDahamgaeStatOrdrList(ordrStatSeq, custmrSeq){
	//공동구매 배송목록 조회
	$('#cusOrdrFrame2').empty();
	$.ajax({
      type: "POST",
      url:"http://sebm.iptime.org:9124/cusDahamgaeOrdrList.do",   
      data:{"ordrStatSeq":ordrStatSeq, "custmrSeq":custmrSeq},
      success:function(data){
    	  $.each(data, function(i, vo){
    		  var ordrNumbrSeq = vo.ordrNumbrSeq;
    		  var ordrDat = vo.ordrDat;
    		  var ordrStat = vo.ordrStat;
    		  var prodctNme = vo.prodctNme;
    		  if(vo.cartImg1 == null){
      			  var cartImg1 = "../img/no_image.gif";
      		  }
      		  else{
      			  var cartImg1 = "http://sebm.iptime.org:9124/" + vo.cartImg1;  
      		  }   
    		  var recvrMobl = vo.recvrMobl;
    		  var allPric = numberWithCommas(vo.allPric);
    		  if(i!=0){
    			  $('#cusOrdrFrame2').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%; margin-top:10%'>"
		  					+ "<table>"
		  					+ "<tr>"
		  					+ "<td>"+ordrDat+"</td>"
		  					+ "<td style='float:right'><a onclick='cusOrdrRead("+ordrNumbrSeq+",1)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>주문상세보기 ></a>"
		  					+ "</td>"
		  					+ "</tr>"
		  					+ "</table>"
		  					+ "<div data-role='content' style='border-top:3px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
		  					+ "<div class='row'>"
		  					+ "<div class='col-xs-5'>"
		  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
		  					+ "</div>"
		  					+ "<div class='col-xs-7'>"
		  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
		  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + "</div>"
		  					+ "<h5 style='text-align:right'>"+ numberWithCommas(allPric) + "원</h5>"
		  					+ "</div>"
		  					+ "</div>"
		  					+ "</div>"
		  					+ "</div>"
    			  )
    		  }
    		  else{
    			  $('#cusOrdrFrame2').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%'>"
		  					+ "<table>"
		  					+ "<tr>"
		  					+ "<td>"+ordrDat+"</td>"
		  					+ "<td style='float:right'><a onclick='cusOrdrRead("+ordrNumbrSeq+",1)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>주문상세보기 ></a>"
		  					+ "</td>"
		  					+ "</tr>"
		  					+ "</table>"
		  					+ "<div data-role='content' style='border-top:3px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
		  					+ "<div class='row'>"
		  					+ "<div class='col-xs-5'>"
		  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
		  					+ "</div>"
		  					+ "<div class='col-xs-7'>"
		  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
		  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + "</div>"
		  					+ "<h5 style='text-align:right'>"+ numberWithCommas(allPric) + "원</h5>"
		  					+ "</div>"
		  					+ "</div>"
		  					+ "</div>"
		  					+ "</div>"
    			  )	
    		  }    		  
    	  });
		  $("#cusOrdrFrame2").listview("refresh");
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}

$(document).on("pagehide","#cusOrdrListPage",function(){
	sessionStorage.removeItem('ordrStatSeq');
})


//주문조회(주문접수~배송중)
$(document).on("pagebeforeshow","#cusOrdrListPage",function(){
	var ordrStatSeq = sessionStorage.getItem('ordrStatSeq');
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	if(custmrSeq!=null){
		custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		custmrSeq = localStorage.getItem('custmrSeq');
	}
	if(ordrStatSeq == null){
		ordrStatSeq = 0;	
		$("#ordrStatAll").attr('checked',true).checkboxradio('refresh');
		$("#ordrStatAll2").attr('checked',true).checkboxradio('refresh');
	}
	cusOrdrListButtonChecked(ordrStatSeq); //페이지 처음 조회 버튼 클릭되어 있게
	cusStatOrdrList(ordrStatSeq, custmrSeq); // 일반 주문목록 조회 (전체, 상태별 포함)	
	cusDahamgaeStatOrdrList(ordrStatSeq, custmrSeq); // 공동구매 주문목록 조회 (전체, 상태별 포함)	
	
	// 버튼별 주문 목록 리스트(일반 주문)
	$("input[name='ordrStatSeq']").on("change", function() {
        var ordrStatSeq = $("input[name='ordrStatSeq']:checked").val();
        cusStatOrdrList(ordrStatSeq, custmrSeq); // 주문목록 조회 (전체, 상태별 포함)
	});
	// 버튼별 주문 목록 리스트(공동구매 주문)
	$("input[name='ordrStatSeq2']").on("change", function() {
        var ordrStatSeq = $("input[name='ordrStatSeq2']:checked").val();
        cusDahamgaeStatOrdrList(ordrStatSeq, custmrSeq); // 공동구매 주문목록 조회 (전체, 상태별 포함)
	});	
});


//주문내역 상세조회 이동
function cusOrdrRead(ordrNumbrSeq, ordrType){
	sessionStorage.setItem('ordrNumbrSeq', ordrNumbrSeq);
	sessionStorage.setItem('ordrType', ordrType); //0은 일반, 1은 공동구매
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
	$.mobile.changePage("MyPageCusOrdrRead.html");
	
//	$.mobile.changePage("#cusOrdrReadPage");
//	$.mobile.pageContainer.pagecontainer("change", "#cusOrdrReadPage", { options });
	
	
}


// 주문 상세보기에서 주문 취소 버튼을 눌렀을 때, 주문취소
function cusOrdrCancel(){
	var ordrNumbrSeq = sessionStorage.getItem('ordrNumbrSeq');
	$.ajax({
		type: "POST",
		url:"http://sebm.iptime.org:9124/cusOrdrCancel.do",   
		data:{"ordrNumbrSeq":ordrNumbrSeq},
		success:function(data){	
			//주문 후 푸시 알림
//	  		sendTlt = "주문 취소 알림";
//	  		sendMsg = time + "에 주문이 취소되었습니다.";
//	  		type = "realTimeOrdrList";
//	  		$.ajax({
//	  			type: "POST",
//	  			data:{"sendTlt":sendTlt, "sendMsg":sendMsg, "type":type},
//	  			url:"http://sebm.iptime.org:9124/pushRealTiemOrdrList.do",   
//	  			success:function(data){
//	  				
//	  			},
//	  			error:function(request,status,error){
//	  				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
//	  			}
//	  		});
	  		navigator.notification.alert("주문 취소 하였습니다.",null,"주문취소","확인");
			window.history.back();
			
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}


// 주문 취소 목록 리스트 조회
$(document).on("pageshow","#cusOrdrCancelListPage",function(){
//	var ordrStatSeq = sessionStorage.getItem('ordrStatSeq');
	var ordrStatSeq = 4;
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	if(custmrSeq!=null){
		custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		custmrSeq = localStorage.getItem('custmrSeq');
	}
	cusStatOrdrList(ordrStatSeq, custmrSeq); // 일반 주문목록 조회 (전체, 상태별 포함)	
	cusDahamgaeStatOrdrList(ordrStatSeq, custmrSeq); // 공동구매 주문목록 조회 (전체, 상태별 포함)
});

//주문내역 상세조회 페이지 쇼
$(document).on("pageshow","#cusOrdrReadPage",function(){
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	if(custmrSeq!=null){
		custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		custmrSeq = localStorage.getItem('custmrSeq');
	}
	var ordrNumbrSeq = sessionStorage.getItem('ordrNumbrSeq');
	var ordrType = sessionStorage.getItem('ordrType');
	
	ordrRead(ordrNumbrSeq); //주문상세조회
	if(ordrType == 0) {
		ordrReadProdctList(ordrNumbrSeq); //  일반상품  리스트 조회
	}
	else { 
		ordrReadDahamgaeList(ordrNumbrSeq); //  공동구매 상품 리스트 조회
	}
	
	
});

//주문내역 상세조회 (상품 리스트)
function ordrReadProdctList(ordrNumbrSeq){
	$.ajax({
	      type: "POST",
	      url:"http://sebm.iptime.org:9124/ordrReadProdctList.do",   
	      data:{"ordrNumbrSeq":ordrNumbrSeq},
	      success:function(list){
	    	  var source = "";
	    	  $.each(list, function(i, vo){
		    	  var prodctNme = vo.prodctNme;
				  var ordrAmont = vo.ordrAmont;
				  var selPric = vo.selPric;
				  var mainImg = "http://sebm.iptime.org:9124/" + vo.cartImg;
				  source = source + "<li class='li-border'><div class='row'><div class='col-xs-4'><img class='img-center' width='100%' height='100%' src='" + mainImg + "'></div><div class='col-xs-8'><p>" + prodctNme + "</p><p>수량 " + ordrAmont + "개 / " + numberWithCommas(selPric) + "원</p></div></div></li>";	  				    		  
	    	  });
	    	  $("#ordrReadProdctList").append(source);
	    	  $("#ordrReadProdctList").listview("refresh");
	      },
	      error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	      }
	   });
}

//주문내역 상세조회 (공동구매 리스트)
function ordrReadDahamgaeList(ordrNumbrSeq){
	$.ajax({
	      type: "POST",
	      url:"http://sebm.iptime.org:9124/ordrReadDahamgaeList.do",   
	      data:{"ordrNumbrSeq":ordrNumbrSeq},
	      success:function(list){
	    	  var source = "";
	    	  $.each(list, function(i, vo){
		    	  var prodctNme = vo.prodctNme;
				  var ordrAmont = vo.ordrAmont;
				  var selPric = vo.selPric;
				  var mainImg = "http://sebm.iptime.org:9124/" + vo.cartImg;
				  source = source + "<li class='li-border'><div class='row'><div class='col-xs-4'><img class='img-center' width='100%' height='100%' src='" + mainImg + "'></div><div class='col-xs-8'><p>" + prodctNme + "</p><p>수량 " + ordrAmont + "개 / " + numberWithCommas(selPric) + "원</p></div></div></li>";	  				    		  
	    	  });
	    	  $("#ordrReadProdctList").append(source);
	    	  $("#ordrReadProdctList").listview("refresh");
	      },
	      error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	      }
	   });
}




//주문내역 상세조회 (주문 정보들)
function ordrRead(ordrNumbrSeq) {
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	if(custmrSeq!=null){
		custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		custmrSeq = localStorage.getItem('custmrSeq');
	}
	
	
	
	$.ajax({
      type: "POST",
      url:"http://sebm.iptime.org:9124/cusOrdrRead.do",   
      data:{"ordrNumbrSeq":ordrNumbrSeq, "custmrSeq":custmrSeq},
      success:function(vo){
		  var ordrNumbrSeq = vo.ordrNumbrSeq;
		  var ordrDat = vo.ordrDat;
		  var allPric = numberWithCommas(vo.allPric);
		  			  
		  var recvrNme = vo.recvrNme;
		  var hopDelvrDat = vo.hopDelvrDat;
		  var recvrMobl = vo.recvrMobl;
		  var recvrAddrss = vo.recvrAddrss;
		  
		  var empNme = vo.empNme;
		  var empMobl = vo.empMobl;
		  var empImg = vo.empImg;
		  var cartImg1 = vo.cartImg1;
		    
		  var ordrStatSeq = vo.ordrStatSeq;
		  var cartImg1 = vo.cartImg1;
		  
		  var recmndCheck = vo.recmndCheck;
		 
		  $('#ordrDat').text(ordrDat);
		  $('#ordrNumbrSeq').text("주문번호 : "+ordrNumbrSeq);
		  $('#allPric').text(allPric+"원");
		  
		  $('#recvrNme').text(recvrNme);
		  $('#hopDelvrDat').text(hopDelvrDat);
		  $('#recvrMobl').text(recvrMobl);
		  $('#recvrAddrss').text(recvrAddrss);
		  		  
		  $('#empImg').attr('src',"http://sebm.iptime.org:9124/"+empImg);
		  $('#empNme').text(empNme);
		  $('#empMobl').text(empMobl);
		  $('#cartImg1').attr('src',"http://sebm.iptime.org:9124/"+cartImg1);

		  switch (ordrStatSeq) {
			case 1:
				$('#ordrStatSeq').text("주문완료");
				$('#empCotent').remove();
				break;
			case 2:
				$('#ordrStatSeq').text("배송중");
				break;
			case 3:
				$('#ordrStatSeq').text("배송완료");
				$('#cusOrdrCancelButton').remove();	
				if(recmndCheck == 'Y'){
					$("#recmndDiv").append("" +
				 		"<a class='ui-btn ui-btn-raised ui-disabled'>배달 직원 추천</a>" +
				 		"").trigger("create");
				}
				else{
					$("#recmndDiv").append("" +
				 		"<a id='recmndButton' href='javascript:ordrRecmnd("+ordrNumbrSeq+")' class='ui-btn clr-btn-light-green'>배달 직원 추천</a>" +
				 		"").trigger("create");
				}
				break;
			case 4:
				$('#ordrStatSeq').remove();
				$('#empCotent').remove();
				$('#cusOrdrCancelButton').remove();	
				break;
		  }

      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}

// 주문완료에서 배달한 직원 추천
function ordrRecmnd(ordrNumbrSeq){
	$.ajax({
	      type: "POST",
	      url:"http://sebm.iptime.org:9124/ordrRecmndUpdate.do",    
	      data:{"ordrNumbrSeq":ordrNumbrSeq},
	      success:function(){
	    	  navigator.notification.alert("배달한 직원을 추천하였습니다.",null,"배달 직원 추천","확인");
	    	  $("#recmndButton").remove();
	    	  $("#recmndDiv").append("" +
	    	  		"<a class='ui-btn ui-btn-raised ui-disabled'>배달 직원 추천</a>").trigger("create");	  				    		  
	      },
	      error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	      }
	   });
}

// 히스토리 화면 뜰 때
$(document).on("pageshow","#historyPage",function(){
	historyList(0);
	historyList(1);
	historyList(2);
});


//appProdcrDtlRead

//히스토리 리스트 조회 (1일전 2일전 3일전)
function historyList(pageType){
	var cusSeq = sessionStorage.getItem('custmrSeq');
	if(custmrSeq!=null){
		cusSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		cusSeq = localStorage.getItem('custmrSeq');
	}
	var pageType = pageType;
	$.ajax({
	      type: "POST",
	      url:"http://sebm.iptime.org:9124/historyList.do",   
	      data:{"cusSeq":cusSeq, "pageType":pageType},
	      success:function(data){
	          $.each(data, function(i, vo){  
	        	  if(i==0){
	        		  $("#historyContent"+pageType).append("" +
	        		  		"<div data-role='collapsible' data-inset='false' style='margin:0' class='ui-collapsible ui-collapsible-themed-content'>" +
		        		  		"<h4 class='ui-collapsible-heading'> "+vo.historyDat+" </h4>" +
		        		  		"<div style='padding:0px' class='ui-collapsible-content ui-body-inherit' aria-hidden='false'> " +
			        		  		"<ul id='historyUl"+pageType+"' data-role='listview' class='ui-listview'>" +
			        		  		"</ul>" +
		        		  		"</div>" +
	        		  		"</div>" +
	        		  		"").trigger("create");
	        	  } 	  
	             if(i%2==0) {
	                $("#historyUl"+pageType).append("" +
	                		"<li class='ui-li-static ui-body-inherit ui-last-child'>" +
		                		"<div id='"+pageType+"FirstDiv"+i+"' class='row' style='height:150px; align:center;'>" +
			                		"<div onclick='appProdcrDtlRead("+vo.prodctSeq+")' class='col-xs-6'>" +
				                		"<a href='#' class='ui-link waves-effect waves-button'>" +
				                			"<img src='http://sebm.iptime.org:9124/"+vo.prodctMainImage+"' width='100%' height='95%'>" +
				                		"</a>" +
				                	"</div>" +
				                "</div>" +
				                "<div id='"+pageType+"SecondDiv"+i+"' class='row' style='align:center;'>" +
				                	"<div onclick='alert(1)' class='col-xs-6'>" +
				                		"<p>"+vo.prodctNme +"</p>" +
				                		"<b class='font-md' id='"+pageType+"prodctPrice"+i+"'>"+numberWithCommas(vo.prodctSelPric)+"원</b>" +
			                		"</div>" +
		                		"</div>" +
	                		" </li>" +
	                		"").trigger("create"); 
	             }
	             else {
	            	 var firstId = "#"+pageType+"FirstDiv"+(i-1);
	            	 $(firstId).append("" +
	            	 		"<div onclick='appProdcrDtlRead("+vo.prodctSeq+")' class='col-xs-6'>" +
		            	 		"<a href='#' class='ui-link waves-effect waves-button'>" +
		            	 			"<img src='http://sebm.iptime.org:9124/"+vo.prodctMainImage+"' width='100%' height='95%'>" +
		            	 		"</a>" +
	            	 		"</div>").trigger("create");
	            	 
	            	 var secondId = "#"+pageType+"SecondDiv"+(i-1);
	            	 $(secondId).append("" +
	            	 		"<div onclick='alert(2)' class='col-xs-6'>" +
		            	 		"<p>"+vo.prodctNme +"</p>" +
		            	 		"<b class='font-md' id='"+pageType+"prodctPrice"+i+"'>"+numberWithCommas(vo.prodctSelPric)+"원</b>" +
	            	 		"</div>" +
	            	 		"").trigger("create"); 	 
	             }   

	             if(vo.prodctSalePric != 0) {
	            	 var priceId = "#"+pageType+"prodctPrice"+i;  
	                 $(priceId).html("" +
	                    "<span class='font-md' style='text-decoration:line-through;'>" +
	                    numberWithCommas(vo.prodctSelPric) + "원</span> -> " +
	                    numberWithCommas(vo.prodctSalePric) + "원" +
	                       "");
	              }
	          });
	      },
	      error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	      }
	   });	
}



