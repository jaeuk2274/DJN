/**
 * 
 */
function yearChng(tag) {
	var ordQty = $(tag).val();
	$(tag).siblings('label').text(ordQty);
};
function Chng(tag) {
	var ordQty = $(tag).val();
	// 		$(tag).parent().siblings('.item_info').find("input[name=ordQty]").val(ordQty);
	$(tag).siblings('label').text(ordQty);
};

$(document).on("pageshow","#joinPage",function(){
	var cusToken = sessionStorage.getItem("cusToken");
	$('#cusToken').val(cusToken);
	$('#cusJoinId').val(sessionStorage.getItem('cusJoinId'));
	$('#cusJoinPw').val(sessionStorage.getItem('cusJoinPw'));
	$('#reCusJoinPw').val(sessionStorage.getItem('reCusJoinPw'));
	$('#cusNme').val(sessionStorage.getItem('cusNme'));
	$('#cusMobile').val(sessionStorage.getItem('cusMobile'));
	$('#postNum').val(sessionStorage.getItem('sendZipNoHidn'));
	$('#loadNmeAdds').val(sessionStorage.getItem('sendRoadAddrHidn'));
	$('#loadNmeAddsDetl').val(sessionStorage.getItem('postDetail'));
	$('#cusEml').val(sessionStorage.getItem('cusEml'));
	
	$('#birthdayYear').click(function() {
		var now = new Date();
	    var year= now.getFullYear();
		for(var i=1950; i<year+1; i++){
			$("#birthdayYear").append("<option value="+i+">"+i+"</option>");
		}
		$("#birthdayYear").selectmenu("refresh");
	});
	$("#cusJoinId").focusout(function() {
		if($("#cusJoinId").val()==""){
			$("#idMsg").html("");
			$("#loadtext").html( "필수정보입니다.");
		}
		else if($("#cusJoinId").val().length <= 3){
			$("#idMsg").html("");
			$("#loadtext").html( "아이디를 4글자 이상  11글자 이하로 입력하세요.");
		}
		else if($("#cusJoinId").val().length > 12){
			$("#idMsg").html("");
			$("#loadtext").html( "아이디를 4글자 이상  11글자 이하로 입력하세요.");
		}
		else if($("#cusJoinId").val().length > 3 && $("#cusJoinId").val().length <= 11){
			var formData = $("#insertCusJoinFrm").serialize();
			$.ajax({
				type: "POST",
				url:"http://sebm.iptime.org:9124/idCheck.do", //이페이지에서 중복체크를 한다
				data : formData,
				dataType : "JSON",
				
				success: function(data){
				var idCheck = data["idCheck"];
				if(idCheck != 0){
					$("#idMsg").html("");
					$("#loadtext").html("아이디가 존재합니다."); //해당 내용을 보여준다
				}
				else{
					$("#loadtext").html("");
					$("#idMsg").html("사용가능한 아이디입니다."); //해당 내용을 보여준다
				}
				
				},
			    error:function(xhr,status,error){ //ajax 오류인경우  
			    	navigator.notification.alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
				}
			});
		}
		else{
			$("#loadtext").html("");
			$("#idMsg").html("");
		}
	});
	$("#cusJoinPw").focusout(function() {
		if($("#cusJoinPw").val()==""){
			$("#pwCheck").text( "");
			$("#labelPw").text( "필수정보입니다.");
		}
		else if(!($('#cusJoinPw').val()).match(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/)){
			$("#pwCheck").text( "");
			$("#labelPw").text( "비밀번호는 문자, 숫자, 특수문자의 조합으로 6~16자리로 입력해주세요.");
		}
		else{
			$("#labelPw").text( "");
			$("#pwCheck").text( "사용가능합니다.");
		}
	});
	$("#reCusJoinPw").focusout(function() {
		if($('#cusJoinPw').val() != $('#reCusJoinPw').val()){
			$("#rePwCheck").text( "");
			$("#labelRePw").text( "비밀번호와 일치하지 않습니다.");
		}
		else if($('#cusJoinPw').val()==""){
			$("#rePwCheck").text( "");
			$("#labelRePw").text( "필수정보입니다.");
		}
		else{
			$("#labelRePw").text( "");
			$("#rePwCheck").text( "비밀번호와 일치합니다.");
		}
	});
	$("#cusEml").focusout(function() {
		if(!($('#cusEml').val()).match(/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i)){
			$("#cusEmlCheck").text( "");
			$("#labelcusEml").text( "이메일을 바르게 입력하세요.");
		}
		else if($('#cusEml').val()==""){
			$("#cusEmlCheck").text( "");
			$("#labelcusEml").text( "필수정보입니다.");
		}
		else{
			$("#labelcusEml").text( "");
			$("#cusEmlCheck").text( "");
		}
	});
	$("#cusMobile").focusout(function() {
		if($('#cusMobile').val().match(/[-]/gi)){
			$("#labelCusMobile").text( "- 를 빼고 들으세요.");
		}
		else if(!($('#cusMobile').val()).match(/^[0-9]{11}$/)){
			$("#labelCusMobile").text( "휴대폰 번호를 제대로 입력하세요.");
		}
		else if(($('#cusMobile').val().length < 12)){
			$("#labelCusMobile").text("");
		}
		else if(($('#cusMobile').val()=="")){
			$("#labelCusMobile").text( "필수정보입니다.");
		}
		else{
			$("#labelCusMobile").text("");
		}
	});
	$("#cusEmail").focusout(function() {
		if($('#cusEmail').val().match(/[-]/gi)){
			$("#labelCusMobile").text( "- 를 빼고 들으세요.");
		}
		else if(!($('#cusMobile').val()).match(/^[0-9]{11}$/)){
			$("#labelCusMobile").text( "휴대폰 번호를 제대로 입력하세요.");
		}
		else if(($('#cusMobile').val().length < 12)){
			$("#labelCusMobile").text("");
		}
		else if(($('#cusMobile').val()=="")){
			$("#labelCusMobile").text( "필수정보입니다.");
		}
		else{
			$("#labelCusMobile").text("");
		}
	});
	$("#loadNmeAddsDetl").focusout(function() {
		if($('#loadNmeAddsDetl').val()==""){
			$("#labelCusMobile").text( "필수정보입니다.");
		}
	});
});
//회원가입
function insertCus(){
	if($("#cusJoinId").val()!=""&&$("#cusJoinId").val().length > 3 && $("#cusJoinId").val().length <= 11 && ($('#cusJoinPw').val()).match(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/)&&$('#cusJoinPw').val() == $('#reCusJoinPw').val() && $('#cusMobile').val().match(/^[0-9]{11}$/) && $('#loadNmeAddsDetl').val()!=""){
		sessionStorage.removeItem('cusJoinId');
		sessionStorage.removeItem('cusJoinPw');
		sessionStorage.removeItem('reCusJoinPw');
		sessionStorage.removeItem('cusNme');
		sessionStorage.removeItem('cusMobile');
		sessionStorage.removeItem('cusEml');
		sessionStorage.removeItem('sendZipNoHidn');
		sessionStorage.removeItem('sendRoadAddrHidn');
		sessionStorage.removeItem('postDetail');
		var formData = $("#insertCusJoinFrm").serialize();
		$.ajax({
			type:"POST",
			data:formData,
			url:"http://sebm.iptime.org:9124/insertCus.do",
			success:function(data){
				$.mobile.changePage("LoginFrame.html");
			},
			error:function(request,status,error){
				navigator.notification.alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
	else{
		navigator.notification.alert("정보를 확인하세요.",null,"알림","확인");
	}
	
}
function addrsPage(){
	sessionStorage.setItem('cusJoinId', $("#cusJoinId").val());
	sessionStorage.setItem('cusJoinPw', $("#cusJoinPw").val());
	sessionStorage.setItem('reCusJoinPw', $("#reCusJoinPw").val());
	sessionStorage.setItem('cusNme', $("#cusNme").val());
	sessionStorage.setItem('cusMobile', $("#cusMobile").val());
	sessionStorage.setItem('cusEml', $("#cusEml").val());
	
	$.mobile.changePage("LoginPost.html");
}
$(document).on("pageshow","#postPage",function(){
	$("#postJoinid").text(sessionStorage.getItem('cusJoinId'));
	$("#postJoinpw").text(sessionStorage.getItem('cusJoinPw'));
	$("#postReJoinPw").text(sessionStorage.getItem('reCusJoinPw'));
	$("#postJoinnme").text(sessionStorage.getItem('cusNme'));
	$("#postJoinMobile").text(sessionStorage.getItem('cusMobile'));
	$("#postJoinCusEml").text(sessionStorage.getItem('cusEml'));
	sessionStorage.setItem('', $("#").val());
});

//주소
function getAddr(){
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
				navigator.notification.alert(errCode+"="+errDesc);
			}else{
				if(xmlStr!= null){
					makeList(xmlStr);								// 결과 XML 데이터 파싱 및 출력
				}
			}
		}
		,error: function(xhr,status, error){
			navigator.notification.alert("에러발생");										// AJAX 호출 에러
		}
	});
}

function makeList(xmlStr){
   // 페이지 처리 (주소정보 리스트 makeList(xmlData); 다음에서 호출) 
   var total = $(xmlStr).find("totalCount").text(); // 총건수
   var pageNum = document.form.currentPage.value;// 현재페이지
   var paggingStr = "";
   if(total < 1){
   }else{
      var PAGEBLOCK=document.form.countPerPage.value;;
      var pageSize=document.form.countPerPage.value;;
      var totalPages = Math.floor((total-1)/pageSize) + 1;
      var firstPage = Math.floor((pageNum-1)/PAGEBLOCK) * PAGEBLOCK + 1;      
      if( firstPage <= 0 ) firstPage = 1;      
      var lastPage = firstPage-1 + PAGEBLOCK;
      if( lastPage > totalPages ) lastPage = totalPages;      
      var nextPage = lastPage+1 ;
      var prePage = firstPage-5 ;      
      if( totalPages > 50){
    	  navigator.notification.alert("검색한 주소가 50개가 넘어 갑니다. 주소를 상세히 적으세요");
      }
      else{
         var htmlStr = "";
         htmlStr += "<table>";
         // jquery를 이용한 XML 결과 데이터 파싱
         $(xmlStr).find("juso").each(function(){
            htmlStr += "<tr onclick='postCoice(this)'>";
            htmlStr += "<td><input type='hidden' id='zipNo' value='"+$(this).find('zipNo').text() +"'>"+$(this).find('zipNo').text() +"</td>";
            htmlStr += "<td><input type='hidden' id='roadAddr' value='"+$(this).find('roadAddr').text() +"'>"+$(this).find('roadAddr').text() +"</td>";
//	            htmlStr += "<td><input type='hidden' id='roadAddrPart1' value='"+$(this).find('roadAddrPart1').text() +"'>"+$(this).find('roadAddrPart1').text() +"</td>";
//	            htmlStr += "<td><input type='hidden' id='roadAddrPart2' value='"+$(this).find('roadAddrPart2').text() +"'>"+$(this).find('roadAddrPart2').text() +"</td>";
//	            htmlStr += "<td><input type='hidden' id='jibunAddr' value='"+$(this).find('jibunAddr').text() +"'>"+$(this).find('jibunAddr').text() +"</td>";
//	            htmlStr += "<td><input type='hidden' id='engAddr' value='"+$(this).find('engAddr').text() +"'>"+$(this).find('engAddr').text() +"</td>";
//	            htmlStr += "<td><input type='hidden' id='admCd' value='"+$(this).find('admCd').text() +"'>"+$(this).find('admCd').text() +"</td>";
//	            htmlStr += "<td><input type='hidden' id='rnMgtSn' value='"+$(this).find('rnMgtSn').text() +"'>"+$(this).find('rnMgtSn').text() +"</td>";
//	            htmlStr += "<td><input type='hidden' id='bdMgtSn' value='"+$(this).find('bdMgtSn').text() +"'>"+$(this).find('bdMgtSn').text() +"</td>";
//	            htmlStr += "<td><input type='hidden' id='detBdNmList' value='"+$(this).find('detBdNmList').text() +"'>"+$(this).find('detBdNmList').text() +"</td>";
            htmlStr += "</tr>";
         });
         htmlStr += "</table>";
         // 결과 HTML을 FORM의 결과 출력 DIV에 삽입
         $('#list').show();
         $('#pageApi').show();
         $('#postDetailDisplay').hide();
         $("#list").html(htmlStr);
         if( firstPage > PAGEBLOCK ){
            paggingStr +=  "<a href='javascript:goPage("+prePage+");'>◁</a>  " ;
         }      
         for( i=firstPage; i<=lastPage; i++ ){
            if( pageNum == i )
               paggingStr += "<a style='font-weight:bold;color:blue;font-size:15px;' href='javascript:goPage("+i+");'>" + i + "</a>  ";
            else
               paggingStr += "<a href='javascript:goPage("+i+");'>" + i + "</a>  ";
         }      
         $("#pageApi").html(paggingStr);
      }
   }   
}

//페이지 이동
function goPage(pageNum){
   document.form.currentPage.value=pageNum;
   getAddr();
}

function checkSearchedWord(obj){
   if(obj.value.length >0){
      //특수문자 제거
      var expText = /[%=><]/ ;
      if(expText.test(obj.value) == true){
    	  navigator.notification.alert("특수문자를 입력 할수 없습니다.") ;
         obj.value = obj.value.split(expText).join(""); 
         return false;
      }
      
      //특정문자열(sql예약어의 앞뒤공백포함) 제거
      var sqlArray = new Array(
         //sql 예약어
         "OR", "SELECT", "INSERT", "DELETE", "UPDATE", "CREATE", "DROP", "EXEC",
                    "UNION",  "FETCH", "DECLARE", "TRUNCATE" 
      );
      
      var regex ;
      var regex_plus ;
      for(var i=0; i<sqlArray.length; i++){
         regex = new RegExp("\\s" + sqlArray[i] + "\\s","gi") ;
         if (regex.test(obj.value)) {
        	 navigator.notification.alert("\"" + sqlArray[i]+"\"와(과) 같은 특정문자로 검색할 수 없습니다.");
            obj.value =obj.value.replace(regex, "");
            return false;
         }
         
         regex_plus = new RegExp( "\\+" + sqlArray[i] + "\\+","gi") ;
         if (regex_plus.test(obj.value)) {
        	 navigator.notification.alert("\"" + sqlArray[i]+"\"와(과) 같은 특정문자로 검색할 수 없습니다.");
            obj.value =obj.value.replace(regex_plus, "");
            return false;
         }
      }
   }
   return true ;
}

// 적용예 (api 호출 전에 검색어 체크) 
function searchJuso(){
   if (!checkSearchedWord(document.form.keyword)) {
      return ;
   }
}
	
function postCoice(tag){
	var zipNo = $(tag).first().children().find("input[id=zipNo]").val();
	var roadAddr = $(tag).first().children().find("input[id=roadAddr]").val();
	$('#list').hide();
	$('#pageApi').hide();
	$('#postDetailDisplay').show();
    $("#sendZipNo").text(zipNo);
    $("#sendRoadAddr").text(roadAddr);
//		$("#sendRoadAddrPart1").val($("#roadAddrPart1").val());
//		$("#sendRoadAddrPart2").val($("#roadAddrPart2").val());
//		$("#sendJibunAddr").val($("#jibunAddr").val()); //구 주소
//		$("#sendEngAddr").val($("#engAddr").val());
//		$("#sendAdmCd").val($("#admCd").val());
//		$("#sendRnMgtSn").val($("#rnMgtSn").val());
//		$("#sendBdMgtSn").val($("#bdMgtSn").val());
//		$("#sendDetBdNmList").val($("#detBdNmList").val());
}
function postAdrrsDetail(){
//	if($("#htmlType")val()==1){
	sessionStorage.setItem('sendZipNoHidn', $("#sendZipNo").text());
    sessionStorage.setItem('sendRoadAddrHidn', $("#sendRoadAddr").text());
    sessionStorage.setItem('postDetail', $("#postDetail").val());
    sessionStorage.setItem('cusJoinId', $("#postJoinid").text());
	sessionStorage.setItem('cusJoinPw', $("#postJoinpw").text());
	sessionStorage.setItem('reCusJoinPw', $("#postReJoinPw").text());
	sessionStorage.setItem('cusNme', $("#postJoinnme").text());
	sessionStorage.setItem('cusMobile', $("#postJoinMobile").text());
	sessionStorage.setItem('cusEml', $("#postJoinCusEml").text());
	
	$.mobile.changePage("LoginJoin.html");
//	}
//	if($("#htmlType")val()==2){
//		$.mobile.changePage("../myPage/custmr/ShipngPlcCreate.html");
//	}
}

$('#birthdayYear').click(function() {
	var now = new Date();
    var year= now.getFullYear();
	for(var i=1950; i<year+1; i++){
		$("#birthdayYear").append("<option value="+i+">"+i+"</option>");
	}
	$("#birthdayYear").selectmenu("refresh");
});

$(document).on("pageshow","#joinAgremt",function(){
	$.ajax({
		url:"http://sebm.iptime.org:9124/joinAgremt.do"				
		,type:"post"
		,dataType : "JSON"
		,success:function(data){		
			var html = data[1].cnt;
			$("#joinAgremtCnt").html(html);
		}
		,error: function(xhr,status, error){
			navigator.notification.alert("에러발생");										
		}
	});
});
$(document).on("pageshow","#joinAgremt1",function(){
	$.ajax({
		url:"http://sebm.iptime.org:9124/joinAgremt.do"				
		,type:"post"
		,dataType : "JSON"
		,success:function(data){		
			var html = data[2].cnt;
			$("#joinAgremtCnt1").html(html);
		}
		,error: function(xhr,status, error){
			navigator.notification.alert("에러발생");										
		}
	});
});

function agremtCheck(){
	if(!$("#agree").is(":checked")){
		$("#agremtLabel").text("동의해주세요");
	}
	else if(!$("#agree1").is(":checked")){
		$("#agremtLabel1").text("동의해주세요");
	}
	else{
		$.mobile.changePage("LoginJoin.html");
	}
}