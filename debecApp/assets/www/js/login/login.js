/**
 * 
 */
//$( document ).ready(function() {
function start(){
	id = localStorage.getItem('empId');
	pw = localStorage.getItem('empPw');
	id2 = localStorage.getItem('custmrId');
	pw2 = localStorage.getItem('custmrPw');
    if(id != null && pw != null){
    	$.mobile.changePage("MainEmpFrame.html");
    }
    else if(id2 != null && pw2 != null){
    	$.mobile.changePage("MainCusFrame.html");
    }
    else{
    	$("#cusId").val(localStorage.getItem('custmrId'));
    	if(localStorage.getItem('custmrId') != null){
    		$("input[id=chk_save_id]").attr("checked","checked");
    		$("input[name=chk_save_id]").prop("checked",true).checkboxradio("refresh");
    	}
    	$.mobile.changePage("LoginFrame.html");
    }
};
$(document).on("pageshow","#loginForm",function(){
	$("#loginForm")[0].reset();
	sessionStorage.removeItem('cusJoinId');
	sessionStorage.removeItem('cusJoinPw');
	sessionStorage.removeItem('reCusJoinPw');
	sessionStorage.removeItem('cusNme');
	sessionStorage.removeItem('cusMobile');
	sessionStorage.removeItem('zipNo');
	sessionStorage.removeItem('roadAddr');
	sessionStorage.removeItem('postDetail');
	if($("input[id=chk_save_id]").prop("checked") == true){
		$("#custmrId").val(localStorage.getItem('custmrId'));
		$("#custmrId").val(localStorage.getItem('empId'));
	}
});
//index화면에서 아이디 비밀번호 입력하면 main화면으로 이동
 function login(){
	loginChecked = $(":input:radio[name=selectLogin]:checked").val();
 	sessionStorage.setItem('type', loginChecked);
 	var formData = $("#loginForm").serialize();
 	formData = formData + "&type="+sessionStorage.getItem('type');
	$.ajax({
 		type:"POST",
 		data:formData,
 		url:"http://sebm.iptime.org:9124/mobileLogin.do",		
 		success:function(data){
 			if(data.idCount==1){
 				if(data.selectLogin == 1){
 					if(data.token != $("#loginHtmlToken").val()){
 						var empSeq = data.empSeq;
 						var token = $("#loginHtmlToken").val();
 						var type = data.selectLogin;
 						tokenUpdate(token, empSeq, type);
 					}
 					
 					if($('input:checkbox[id="chk_save_id"]').is(":checked") == true){
 						localStorage.setItem('empId', data.empId);
 					}
 					else if($('input:checkbox[id="chk_save_id"]').is(":checked") == false){
 						localStorage.removeItem('empId');
 					}
 					if($('input:checkbox[id="chk_auto_login"]').is(":checked") == true){
 		 				localStorage.setItem('empId', data.empId);
 		 				localStorage.setItem('empPw', data.empPw);
 		 	 			localStorage.setItem('empSeq', data.empSeq);
 		 	 			sessionStorage.setItem('empId', data.empId);
 						sessionStorage.setItem('empPw', data.empPw);
 						sessionStorage.setItem('empSeq', data.empSeq);
 		 		 	}
 					else{
 						sessionStorage.setItem('empId', data.empId);
 						sessionStorage.setItem('empPw', data.empPw);
 						sessionStorage.setItem('empSeq', data.empSeq);
 					}
 					
 					localStorage.setItem("Token", $("#loginHtmlToken").val());
 					$.mobile.changePage("MainEmpFrame.html");
 				}
 				else {
 					if(data.token != $("#loginHtmlToken").val()){
 						var custmrSeq = data.custmrSeq;
 						var token = $("#loginHtmlToken").val();
 						var type = data.selectLogin;
 						tokenUpdate(token, custmrSeq, type);
 					}
 					if($('input:checkbox[id="chk_save_id"]').is(":checked") == true){
 						localStorage.setItem('custmrId', data.custmrId);
 					}
 					else if($('input:checkbox[id="chk_save_id"]').is(":checked") == false){
 						localStorage.removeItem('custmrId');
 					}
 					if($('input:checkbox[id="chk_auto_login"]').is(":checked") == true){
 		 				localStorage.setItem('custmrId', data.custmrId);
 		 				localStorage.setItem('custmrPw', data.custmrPw);
 		 	 			localStorage.setItem('custmrSeq', data.custmrSeq);
 		 		 	}
 					else{
 						sessionStorage.setItem('custmrId', data.custmrId);
 						sessionStorage.setItem('custmrPw', data.custmrPw);
 						sessionStorage.setItem('custmrSeq', data.custmrSeq);
 					}
 					localStorage.setItem("Token", $("#loginHtmlToken").val());
					$.mobile.changePage("MainCusFrame.html");
 				}
			}
 			else{
 				navigator.notification.alert("입력하신 아이디 또는 비밀번호가 일치하지 않습니다.",null,"알림","확인");
 			}
 		},
 		error:function(request,status,error){
 	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
 		}
 	});
}
function tokenUpdate(token, custmrSeq, type){
 	if(type == 1){
 		var formData = "token="+token+"&empSeq="+custmrSeq+"&selectLogin="+type;
 	}
 	else{
 		var formData = "token="+token+"&custmrSeq="+custmrSeq+"&selectLogin="+type;
 	}
	$.ajax({
 		type:"POST",
 		data:formData,
 		url:"http://sebm.iptime.org:9124/tokenUpdate.do",
 		success:function(){
 		},
 		error:function(request,status,error){
 	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
 		}
 	});
}
function logout(){
	localStorage.removeItem('empId');
	localStorage.removeItem('empPw');
	localStorage.removeItem('empSeq');
	localStorage.removeItem('custmrId');
	localStorage.removeItem('custmrPw');
	localStorage.removeItem('custmrSeq');
	$.mobile.changePage("LoginFrame.html");
}
$(document).on("pageshow","#searchCusPage",function(){
	$("#searchCustmrNme").focusout(function() {
		if($("#searchCustmrNme").val() == ""){
			$("#custmrNmeCheck").text("이름을 제대로 입력하세요.");
		}
		else if(!($('#searchCustmrNme').val()).match(/^[가-힝]{2,4}$/)){
			$("#custmrNmeCheck").text("한글 2글자 이상 ~ 4글자 이하로 입력하세요.");
		}
		else{
			$("#custmrNmeCheck").text("");
		}
	});
	$("#searchCustmrMobl").focusout(function() {
		if($('#searchCustmrMobl').val().match(/[-]/gi)){
			$("#custmrMoblCheck").text( "- 를 빼고 들으세요.");
		}
		else if(!($('#searchCustmrMobl').val()).match(/^[0-9]{11}$/)){
			$("#custmrMoblCheck").text( "휴대폰 번호를 제대로 입력하세요.");
		}
		else if(($('#searchCustmrMobl').val().length < 12)){
			$("#custmrMoblCheck").text("");
		}
		else if(($('#searchCustmrMobl').val()=="")){
			$("#custmrMoblCheck").text( "번호를 적어주세요.");
		}
		else{
			$("#custmrMoblCheck").text("");
		}
	});
});
//index화면에서 아이디 비밀번호 입력하면 main화면으로 이동
function idSearch(){	
	if($("#searchCustmrNme").val() == "" || (!($('#searchCustmrNme').val()).match(/^[가-힝]{2,4}$/))){
		navigator.notification.alert("이름을 제대로 입력하세요.",null,"알림","확인");
	}
	else if($('#searchCustmrMobl').val().match(/[-]/gi) || (!($('#searchCustmrMobl').val()).match(/^[0-9]{11}$/)) || ($('#searchCustmrMobl').val()=="")){
		navigator.notification.alert("번호를 제대로 입력하세요.",null,"알림","확인");
	}
	else{
	 	var formData = $("#idSearchFrm").serialize();
	// 	formData = formData + "&type="+sessionStorage.getItem('type');
	// 	formData = formData + "&loginChecked="+loginChecked;
	 	$.ajax({
	 		type:"POST",
	 		data:formData,
	 		url:"http://sebm.iptime.org:9124/idSearch.do",		
	 		success:function(data){
	 			sessionStorage.setItem('custmrId', data.custmrId);
	 		
	 			$.mobile.changePage("LoginSearchId.html");
	 		},
	 		error:function(request,status,error){
	 	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	 		}
	 	});
	}
}
$(document).on("pageshow","#searchCusId",function(){
	$("#SearchId").text(sessionStorage.getItem('custmrId'));
});

function sessionDelete(){
	sessionStorage.remove('custmrId');
}
//index화면에서 아이디 비밀번호 입력하면 main화면으로 이동
function pwSearch(){	
	if($("#pwSearchCustmrNme").val() == "" || (!($('#pwSearchCustmrNme').val()).match(/^[가-힝]{2,4}$/))){
		navigator.notification.alert("이름을 제대로 입력하세요.",null,"알림","확인");
	}
	else if($("#pwSearchCustmrId").val().length <= 3 || $("#pwSearchCustmrId").val().length > 12 || $("#pwSearchCustmrId").val()==""){
		navigator.notification.alert("아이디를 제대로 입력하세요.",null,"알림","확인");
	}
	else if($('#pwSearchCustmrMobl').val().match(/[-]/gi) || (!($('#pwSearchCustmrMobl').val()).match(/^[0-9]{11}$/)) || ($('#pwSearchCustmrMobl').val()=="")){
		navigator.notification.alert("번호를 제대로 입력하세요.",null,"알림","확인");
	}
	else{
	 	var formData = $("#pwSearchFrm").serialize();
	// 	formData = formData + "&type="+sessionStorage.getItem('type');
	// 	formData = formData + "&loginChecked="+loginChecked;
	 	$.ajax({
	 		type:"POST",
	 		data:formData,
	 		url:"http://sebm.iptime.org:9124/pwSearch.do",		
	 		success:function(data){
	 			sessionStorage.setItem('custmrPw', data.custmrPw)
	 			$.mobile.changePage("LoginSearchPw.html");
	 		},
	 		error:function(request,status,error){
	 	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	 		}
	 	});
	}
}
$(document).on("pageshow","#searchCusPw",function(){
	$("#SearchPw").text(sessionStorage.getItem('custmrPw'));
});