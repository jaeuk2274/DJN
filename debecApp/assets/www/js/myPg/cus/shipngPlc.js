/**
 * 
 */
function ShipngPlcCreate(){
	sessionStorage.removeItem('postShipngPlcName');
	sessionStorage.removeItem('postRecvrName');
	sessionStorage.removeItem('postRecvrTel_hp');
	sessionStorage.removeItem('sendShipngPlcZipNoHidn');
	sessionStorage.removeItem('sendShipngPlcRoadAddrHidn');
	sessionStorage.removeItem('postShipngPlcDetail');
	$.mobile.changePage("MyPageCusShipngPlcCreate.html");
}
$(document).on("pageshow","#shipngPlcInsertPage",function(){
	$("#shipngPlcName").val(sessionStorage.getItem('postShipngPlcName'));
	$("#shipngRecvrName").val(sessionStorage.getItem('postRecvrName'));
	$("#shipngRecvrTel_hp").val(sessionStorage.getItem('postRecvrTel_hp'));
	$("#shipngPlcPostNum").val(sessionStorage.getItem('sendShipngPlcZipNoHidn'));
	$("#shipngPlcPostAdrs").val(sessionStorage.getItem('sendShipngPlcRoadAddrHidn'));
	$("#shipngPlcPostDetlAdrs").val(sessionStorage.getItem('postShipngPlcDetail'));
});

function shipngAddrsPage(){
	sessionStorage.setItem('shipngPlcName', $("#shipngPlcName").val());
	sessionStorage.setItem('recvrName', $("#shipngRecvrName").val());
	sessionStorage.setItem('recvrTel_hp', $("#shipngRecvrTel_hp").val());
	$.mobile.changePage("MyPageCusShipngPlcPost.html");
}
$(document).on("pageshow","#shipngPlcPostPage",function(){
	$("#postShipngPlcName").text(sessionStorage.getItem('shipngPlcName'));
	$("#postRecvrName").text(sessionStorage.getItem('recvrName'));
	$("#postRecvrTel_hp").text(sessionStorage.getItem('recvrTel_hp'));
});
//주소
function getShipngPlcAddr(){
	// AJAX 주소 검색 요청
	var adds = $("#shipngPlcForm").serialize();
	$.ajax({
		url:"http://sebm.iptime.org:9124/getAddrApi.do"				// 고객사 API 호출할 Controller URL
		,type:"post"
		,data: adds					 								// 요청 변수 설정
		,dataType:"xml"												// 데이터 결과 : XML
		,success:function(xmlStr){									// xmlStr : 주소 검색 결과 XML 데이터
			$("#shipngPlcPostList").html("");									// 결과 출력 영역 초기화
			var errCode= $(xmlStr).find("errorCode").text();		// 응답코드
			var errDesc= $(xmlStr).find("errorMessage").text();		// 응답메시지
			if(errCode != "0"){ 									// 응답에러시 처리
				navigator.notification.alert(errCode+"="+errDesc);
			}else{
				if(xmlStr!= null){
					makeShipngPlcList(xmlStr);								// 결과 XML 데이터 파싱 및 출력
				}
			}
		}
		,error: function(xhr,status, error){
			navigator.notification.alert("에러발생");										// AJAX 호출 에러
		}
	});
}

function makeShipngPlcList(xmlStr){
   // 페이지 처리 (주소정보 리스트 makeList(xmlData); 다음에서 호출) 
   var total = $(xmlStr).find("totalCount").text(); // 총건수
   var pageNum = document.shipngPlcForm.currentPage.value;// 현재페이지
   var paggingStr = "";
   if(total < 1){
   }else{
      var PAGEBLOCK=document.shipngPlcForm.countPerPage.value;;
      var pageSize=document.shipngPlcForm.countPerPage.value;;
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
				htmlStr += "<tr onclick='shipngPlcPostCoice(this)'>";
				htmlStr += "<td><input type='hidden' id='shipngPlcZipNo' value='"+$(this).find('zipNo').text() +"'>"+$(this).find('zipNo').text() +"</td>";
				htmlStr += "<td><input type='hidden' id='shipngPlcRoadAddr' value='"+$(this).find('roadAddr').text() +"'>"+$(this).find('roadAddr').text() +"</td>";
				htmlStr += "</tr>";
			});
			htmlStr += "</table>";
			// 결과 HTML을 FORM의 결과 출력 DIV에 삽입
			$('#shipngPlcPostList').show();
			$('#shipngPlcPostpageApi').show();
			$('#shipngPlcPostDetail').hide();
			$("#shipngPlcPostList").html(htmlStr);
         if( firstPage > PAGEBLOCK ){
            paggingStr +=  "<a href='javascript:shipngPlcgoPage("+prePage+");'>◁</a>  " ;
         }      
         for( i=firstPage; i<=lastPage; i++ ){
            if( pageNum == i )
               paggingStr += "<a style='font-weight:bold;color:blue;font-size:15px;' href='javascript:shipngPlcgoPage("+i+");'>" + i + "</a>  ";
            else
               paggingStr += "<a href='javascript:shipngPlcgoPage("+i+");'>" + i + "</a>  ";
         }      
         $("#shipngPlcPostpageApi").html(paggingStr);
      }
   }   
}

//페이지 이동
function shipngPlcgoPage(pageNum){
   document.shipngPlcForm.currentPage.value=pageNum;
   getShipngPlcAddr();
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
   if (!checkSearchedWord(document.shipngPlcForm.keyword)) {
      return ;
   }
}

function shipngPlcPostCoice(tag){
	var shipngPlcZipNo = $(tag).first().children().find("input[id=shipngPlcZipNo]").val();
	var shipngPlcRoadAddr = $(tag).first().children().find("input[id=shipngPlcRoadAddr]").val();
	$('#shipngPlcPostList').hide();
	$('#shipngPlcPostpageApi').hide();
	$('#shipngPlcPostDetail').show();
    $("#sendShipngPlcZipNo").text(shipngPlcZipNo);
    $("#sendShipngPlcRoadAddr").text(shipngPlcRoadAddr);
}

function postAdrrsShipngPlcDetail(){
	sessionStorage.setItem('sendShipngPlcZipNoHidn', $("#sendShipngPlcZipNo").text());
    sessionStorage.setItem('sendShipngPlcRoadAddrHidn', $("#sendShipngPlcRoadAddr").text());
    sessionStorage.setItem('postShipngPlcDetail', $("#postShipngPlcDetail").val());
    sessionStorage.setItem('postShipngPlcName', $("#postShipngPlcName").text());
	sessionStorage.setItem('postRecvrName', $("#postRecvrName").text());
	sessionStorage.setItem('postRecvrTel_hp', $("#postRecvrTel_hp").text());
	$.mobile.changePage("MyPageCusShipngPlcCreate.html");
}
//배송지 등록
function shipngPlcCret(){
	sessionStorage.removeItem('postShipngPlcName');
	sessionStorage.removeItem('postRecvrName');
	sessionStorage.removeItem('postRecvrTel_hp');
	sessionStorage.removeItem('sendShipngPlcZipNoHidn');
	sessionStorage.removeItem('sendShipngPlcRoadAddrHidn');
	sessionStorage.removeItem('postShipngPlcDetail');
	if(localStorage.getItem('custmrSeq') != null){
		$("#shipngCusSeq").val(localStorage.getItem('custmrSeq'));
	}
	else{
		$("#shipngCusSeq").val(sessionStorage.getItem('custmrSeq'));
	}
	if($('#shipngCheckInsert').is(":checked") == false){
		$("#insertShipngCheck").val('N');
	}
	else{
		$("#insertShipngCheck").val('Y');
	}
	var formData = $("#shipngPlcFrm").serialize();
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/shipngPlcCret.do",
		success:function(data){
			$.mobile.changePage("MyPageCusShipngPlcList.html");
		},
		error:function(request,status,error){
			navigator.notification.alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

$(document).on("pageshow","#shipngPlcListPage",function(){
	if(localStorage.getItem('custmrSeq') != null){
		var formData = "custmrSeq="+localStorage.getItem('custmrSeq');
	}
	else{
		var formData = "custmrSeq="+sessionStorage.getItem('custmrSeq');
	}
	$.ajax({
		url:"http://sebm.iptime.org:9124/shipngPlcList.do"				
		,type:"post"
		,data : formData
		,dataType : "JSON"
		,success:function(data){
			$.each(data, function(i, vo){
				shipngPlcSeq = vo.shipngPlcSeq;
				shipngPlcNme = vo.shipngPlcNme;
				shipngPlcPostcod = vo.shipngPlcPostcod;
				shipngPlcAdrs = vo.shipngPlcAdrs;
				shipngPlcDetalAdrs = vo.shipngPlcDetalAdrs;
				custmrSeq = vo.custmrSeq;
				recvrNme = vo.recvrNme;
				recvrMobl = vo.recvrMobl;
				existCheck = vo.existCheck;
	
				$("#shipngList").append("" +
					"<div data-role='content' style='border:1px solid gray; padding:5px'> " +
						"<input type='hidden' value='"+shipngPlcSeq+"' name='shipngPlcSeq' id='shipngPlcSeqList'>" +
						"<input type='hidden' value='"+shipngPlcNme+"' id='shipngPlcNmeList'>" +
						"<input type='hidden' value='"+shipngPlcPostcod+"' id='shipngPlcPostcodList'>" +
						"<input type='hidden' value='"+shipngPlcAdrs+"' id='shipngPlcAdrsList'>" +
						"<input type='hidden' value='"+shipngPlcDetalAdrs+"' id='shipngPlcDetalAdrsList'>" +
						"<input type='hidden' value='"+recvrNme+"' id='recvrNmeList'>" +
						"<input type='hidden' value='"+recvrMobl+"' id='recvrMoblList'>" +
						"<input type='hidden' value='"+custmrSeq+"' name= 'custmrSeq' id='shipngListCustmrSeq'>" +
						"<p class='font-md'><b>"+shipngPlcNme+"</b></p><div id='checkDisplay'></div> " +
						"<table width='100%'> " +
							"<tr style='height:45px'> " +
								"<th width='25%'>이름 </th> " +
								"<td width='75%'>"+recvrNme+"</td> " +
							"</tr> " +
							"<tr style='height:45px'> " +
							"	<th width='25%'>주소 </th> " +
							"	<td width='75%'>"+shipngPlcPostcod+"<br>"+shipngPlcAdrs+"<br>"+shipngPlcDetalAdrs+"</td> " +
							"</tr> " +
							"<tr style='height:45px'> " +
							"	<th width='25%'>전화번호 </th> " +
							"	<td width='75%'>"+recvrMobl+"</td> " +
							"</tr> " +
						"</table> " +
						"<table width='100%' > " +
							"<tr> " +
							"	<th width='50%' align='center'>" +
							"		<a onclick='shipngPlcUpdateFrm("+shipngPlcSeq+")' class='ui-btn ui-btn-inline clr-btn-light-green font-btn' style='width:80%'><b>수정</b></a></th> " +
							"	<td width='50%' align='center'><a onclick='shipngDelete()' class='ui-btn font-btn' style='background-color:#F3F3F3; width:80%; border:1px solid #D5D5D5'><b>삭제</b></a></td> " +
							"</tr> " +
						"</table> " +
					"</div> " +
				"");
				if(vo.existCheck=="Y"){
		            $('#checkDisplay').text('(기본배송지)');
				}
			});
		}
		,error: function(xhr,status, error){
			navigator.notification.alert("에러발생");										
		}
	});
});
var shipngPlcSeqList;
var shipngPlcNmeList;
var recvrNmeList;
var shipngPlcPostcodList;
var shipngPlcAdrsList;
var shipngPlcDetalAdrsList;
var recvrMoblList;
var custmrSeqList;

function shipngPlcUpdateFrm(shipngPlcSeq){
	sessionStorage.setItem('shipngPlcSeq', shipngPlcSeq);
	$.mobile.changePage("MyPageCusShipngPlcUpdate.html");
//	shipngPlcSeqList = $("#shipngPlcSeqList").val();
//	shipngPlcNmeList = $("#shipngPlcNmeList").val();
//	recvrNmeList = $("#recvrNmeList").val();
//	shipngPlcPostcodList = $("#shipngPlcPostcodList").val();
//	shipngPlcAdrsList = $("#shipngPlcAdrsList").val();
//	shipngPlcDetalAdrsList = $("#shipngPlcDetalAdrsList").val();
//	recvrMoblList = $("#recvrMoblList").val();
//	custmrSeqList = $("#shipngListCustmrSeq").val();
}

$(document).on("pageshow","#shipngPlcUpdatePage",function(){
	var formData = "shipngPlcSeq="+sessionStorage.getItem('shipngPlcSeq');
	$.ajax({
		url:"http://sebm.iptime.org:9124/shipngPlcUpdateFrm.do"				
		,type:"post"
		,data: formData					 								
		,dataType : "JSON"								
		,success:function(data){
		 $("#shipngUpdateFrm").append("" +
				 "<input type='hidden' name='shipngPlcSeq' value='"+data.shipngPlcSeq+"' id='shipngPlcSeqUpdate'>" +
				 "<input type='hidden' name='custmrSeq' id='custmrSeqUpdate' value='"+data.custmrSeq+"'>" +
				 "<input type='hidden' name='existCheck' id='existCheck' value='"+data.existCheck+"'>" +
				 "<ul data-role='listview' data-inset='true'>" +
				 	"<li class='ui-field-contain'>" +
				 		"<label for='name'>배송지 이름</label>" +
				 		"<input type='text' name='shipngPlcNme' id='shipngPlcNmeUpdate' value='"+data.shipngPlcNme+"'>" +
			 		"</li>" +
					"<li class='ui-field-contain'>" +
						"<label for='name'>수령자</label>" +
						"<input type='text' name='recvrNme' id='recvrNmeUpdate' value='"+data.recvrNme+"'>" +
					"</li>" +
					"<li class='ui-field-contain font-btn'>" +
				   		"<a onClick='shipngAddrsUpdatePage()' class='ui-btn ui-btn-raised' class='font-sm' style='font-size: 16px text-align: center'>우편번호찾기</a>" +
				   "</li>" +
				   "<li class='ui-field-contain'>" +
					    "<label for='name'>우편번호</label>" +
					    "<input type='text' name='shipngPlcPostcod' id='shipngPlcPostcodUpdate' value='"+data.shipngPlcPostcod+"' readonly>" +
				   "</li>" +
				   "<li class='ui-field-contain'>" +
						"<label for='name'>기본주소</label>" +
						"<input type='text' name='shipngPlcAdrs' id='shipngPlcAdrsUpdate' value='"+data.shipngPlcAdrs+"' readonly>" +
					"</li>" +
					"<li class='ui-field-contain'>" +
						"<label for='name'>상세주소</label>" +
						"<input type='text' name='shipngPlcDetalAdrs' id='shipngPlcDetalAdrsUpdate' value='"+data.shipngPlcDetalAdrs+"'>" +
					"</li>" +
					"<li class='ui-field-contain'>" +
						"<label for='name'>연락처</label>" +
						"<input type='text' name='recvrMobl' id='recvrMoblUpdate' value='"+data.recvrMobl+"'>" +
				   "</li>" +
				   "<li class='ui-field-contain'>" +
				    	"<fieldset data-role='controlgroup' style='width:50%'>" +
							"<input type='checkbox' id='shipngCheck'/>" +
							"<label for='shipngCheck'>기본 배송지로 선택</label>" +
						"</fieldset>" +	
				    "</li>" +
			     "</ul>" +
	             "").trigger("create");
		}
		,error: function(xhr,status, error){
			navigator.notification.alert("에러발생");										// AJAX 호출 에러
		}
	});
//	$("#shipngPlcSeqUpdate").val(shipngPlcSeqList);
//	$("#shipngPlcNmeUpdate").val(shipngPlcNmeList);
//	$("#recvrNmeUpdate").val(recvrNmeList);
//	$("#shipngPlcPostcodUpdate").val(shipngPlcPostcodList);
//	$("#shipngPlcAdrsUpdate").val(shipngPlcAdrsList);
//	$("#shipngPlcDetalAdrsUpdate").val(shipngPlcDetalAdrsList);
//	$("#recvrMoblUpdate").val(recvrMoblList);
//	$("#custmrSeqUpdate").val(custmrSeqList);
});

function shipngUpdate(){
	if($('#shipngCheck').is(":checked") == false){
		$("#existCheck").val('N');
	}
	else{
		$("#existCheck").val('Y');
	}
	var formData = $('#shipngPlcUpdate').serialize();
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/shipngUpdate.do",
		success:function(){
			$.mobile.changePage("MyPageCusShipngPlcList.html");				
		},
		error:function(request,status,error){
			navigator.notification.alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

function shipngDelete(){
	var formData = $("#shipngPlcList").serialize();
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/shipngDelete.do",
		success:function(){
			$.mobile.changePage(
			        window.location.href,
			        {
			          allowSamePageTransition : true,
			          transition              : document.location.href,
			          showLoadMsg             : false,
			          reloadPage              : true
			        }
			      );
		},
		error:function(request,status,error){
			navigator.notification.alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

function shipngAddrsUpdatePage(){
	sessionStorage.removeItem('postShipngPlcUpdateName');
	sessionStorage.removeItem('postRecvrUpdateName');
	sessionStorage.removeItem('postRecvrUpdateTel_hp');
	sessionStorage.removeItem('sendShipngPlcZipNoUpdateHidn');
	sessionStorage.removeItem('sendShipngPlcRoadAddrUpdateHidn');
	sessionStorage.removeItem('postShipngPlcUpdateDetail');
	
	sessionStorage.setItem('shipngPlcUpdateName', $("#shipngPlcUpdateName").val());
	sessionStorage.setItem('recvrUpdateName', $("#shipngRecvrUpdateName").val());
	sessionStorage.setItem('recvrUpdateTel_hp', $("#shipngRecvrUpdateTel_hp").val());
	$.mobile.changePage("MyPageCusShipngPlcUpdatePost.html");
}
$(document).on("pageshow","#shipngPlcPostUpdatePage",function(){
	$("#postShipngPlcUpdateName").text(sessionStorage.getItem('shipngPlcUpdateName'));
	$("#postRecvrUpdateName").text(sessionStorage.getItem('recvrUpdateName'));
	$("#postRecvrUpdateTel_hp").text(sessionStorage.getItem('recvrUpdateTel_hp'));
});
//주소
function getShipngPlcUpdateAddr(){
	// AJAX 주소 검색 요청
	var adds = $("#shipngUpdatePlcForm").serialize();
	$.ajax({
		url:"http://sebm.iptime.org:9124/getAddrApi.do"				// 고객사 API 호출할 Controller URL
		,type:"post"
		,data: adds					 								// 요청 변수 설정
		,dataType:"xml"												// 데이터 결과 : XML
		,success:function(xmlStr){									// xmlStr : 주소 검색 결과 XML 데이터
			$("#shipngPlcPostUpdateList").html("");									// 결과 출력 영역 초기화
			var errCode= $(xmlStr).find("errorCode").text();		// 응답코드
			var errDesc= $(xmlStr).find("errorMessage").text();		// 응답메시지
			if(errCode != "0"){ 									// 응답에러시 처리
				navigator.notification.alert(errCode+"="+errDesc);
			}else{
				if(xmlStr!= null){
					makeShipngPlcUpdateList(xmlStr);								// 결과 XML 데이터 파싱 및 출력
				}
			}
		}
		,error: function(xhr,status, error){
			navigator.notification.alert("에러발생");										// AJAX 호출 에러
		}
	});
}

function makeShipngPlcUpdateList(xmlStr){
   // 페이지 처리 (주소정보 리스트 makeList(xmlData); 다음에서 호출) 
   var total = $(xmlStr).find("totalCount").text(); // 총건수
   var pageNum = document.shipngUpdatePlcForm.currentPage.value;// 현재페이지
   var paggingStr = "";
   if(total < 1){
   }else{
      var PAGEBLOCK=document.shipngUpdatePlcForm.countPerPage.value;;
      var pageSize=document.shipngUpdatePlcForm.countPerPage.value;;
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
    			htmlStr += "<tr onclick='shipngPlcPostUpdateCoice(this)' id='postparent'>";
    			htmlStr += "<td><input type='hidden' name='shipngPlcUpdateZipNo' id='shipngPlcUpdateZipNo' value='"+$(this).find('zipNo').text() +"'>"+$(this).find('zipNo').text() +"</td>";
    			htmlStr += "<td><input type='hidden' name='shipngPlcUpdateRoadAddr' id='shipngPlcUpdateRoadAddr' value='"+$(this).find('roadAddr').text() +"'>"+$(this).find('roadAddr').text() +"</td>";
    			htmlStr += "</tr>";
    		});
    		htmlStr += "</table>";
    		// 결과 HTML을 FORM의 결과 출력 DIV에 삽입
    		$('#shipngPlcPostUpdateList').show();
    		$('#shipngPlcPostUpdatepageApi').show();
    		$('#shipngPlcPostUpdateDetail').hide();
    		$("#shipngPlcPostUpdateList").html(htmlStr);
         if( firstPage > PAGEBLOCK ){
            paggingStr +=  "<a href='javascript:shipngUpdatePlcFormgoPage("+prePage+");'>◁</a>  " ;
         }      
         for( i=firstPage; i<=lastPage; i++ ){
            if( pageNum == i )
               paggingStr += "<a style='font-weight:bold;color:blue;font-size:15px;' href='javascript:shipngUpdatePlcFormgoPage("+i+");'>" + i + "</a>  ";
            else
               paggingStr += "<a href='javascript:shipngUpdatePlcFormgoPage("+i+");'>" + i + "</a>  ";
         }      
         $("#shipngPlcPostUpdatepageApi").html(paggingStr);
      }
   }   
}

//페이지 이동
function shipngUpdatePlcFormgoPage(pageNum){
   document.shipngUpdatePlcForm.currentPage.value=pageNum;
   getShipngPlcUpdateAddr();
}

function checkSearchedWord(obj){
   if(obj.value.length >0){
      //특수문자 제거
      var expText = /[%=><]/ ;
      if(expText.test(obj.value) == true){
         navigator.notification.alert("특수문자를 입력 할수 없습니다.");
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
   if (!checkSearchedWord(document.shipngUpdatePlcForm.keyword)) {
      return ;
   }
}

function shipngPlcPostUpdateCoice(tag){
	var shipngPlcUpdateZipNo = $(tag).first().children().find("input[id=shipngPlcUpdateZipNo]").val();
	var shipngPlcUpdateRoadAddr = $(tag).first().children().find("input[id=shipngPlcUpdateRoadAddr]").val();
	$('#shipngPlcPostUpdateList').hide();
	$('#shipngPlcPostUpdatepageApi').hide();
	$('#shipngPlcPostUpdateDetail').show();
    $("#sendShipngPlcUpdateZipNo").text(shipngPlcUpdateZipNo);
    $("#sendShipngPlcUpdateRoadAddr").text(shipngPlcUpdateRoadAddr);
}

function postAdrrsShipngPlcUpdateDetail(){
	sessionStorage.setItem('sendShipngPlcZipNoUpdateHidn', $("#sendShipngPlcUpdateZipNo").text());
    sessionStorage.setItem('sendShipngPlcRoadAddrUpdateHidn', $("#sendShipngPlcUpdateRoadAddr").text());
    sessionStorage.setItem('postShipngPlcUpdateDetail', $("#postShipngPlcUpdateDetail").val());
    sessionStorage.setItem('postShipngPlcUpdateName', $("#postShipngPlcUpdateName").text());
	sessionStorage.setItem('postRecvrUpdateName', $("#postRecvrUpdateName").text());
	sessionStorage.setItem('postRecvrUpdateTel_hp', $("#postRecvrUpdateTel_hp").text());
	$.mobile.changePage("MyPageCusShipngPlcUpdate.html");
}