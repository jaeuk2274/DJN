var cnt1 = 0; //아이디 정렬값
var cnt2 = 0; //이름 정렬값
var cnt3 = 0; //성별 정렬값
var cnt4 = 0; //주문횟수 정렬값
var cnt5 = 0; //주문금액 정렬값

$(document).ready(function() {
    $('#tabs').tab();
    
    //카테고리 리스트 조회
	$.ajax({
		type: "POST",
		dataType: "JSON",
		url: "/selectCatgrList.do",
		error: function(){
		},
		success: function(data){
			$('#tree').jstree({
				'plugins': ["wholerow"],
				'core' : {
					"multiple" : false,
				    "animation" : 0,
					'data' : data,
						'themes' : {
							'name' : 'proton',
							'responsive' : true
						}
				}
			});
		}
	});
	
	//카테고리 선택 시 조회
	$('#tree').on("changed.jstree", function (e, data) {
		var catgrSeq = "catgrSeq="+data.selected;
		$.ajax({
			type: "GET",
			data : catgrSeq,
			url: "/selectProdctAdList2.do",
			success:function(msg)
			{
				document.getElementById("prodctTable").innerHTML = msg;
			},
            error:function(xhr,status,error)
            { //ajax 오류인경우  
               alert("$");
                 alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
            }
		});
	});
});

function rangeRecpnt(searchAry) {
	//cnt값이 0이면 오름차순(기본값), 1이면 내림차순 정렬
	switch(searchAry){
		case 1 :
			if(cnt1 == 0){
				cnt1++;
			}
			else{
				cnt1--;
			}
			$('#searchCnt').val(cnt1);
			break;
		case 2 :
			if(cnt2 == 0){
				cnt2++;
			}
			else{
				cnt2--;	
			}
			$('#searchCnt').val(cnt2);
			break;
		case 3 :
			if(cnt3 == 0){
				cnt3++;
			}
			else{
				cnt3--;	
			}
			$('#searchCnt').val(cnt3);
			break;
		case 4 :
			if(cnt4 == 0){
				cnt4++;
			}
			else{
				cnt4--;
			}
			$('#searchCnt').val(cnt4);
			break;
		case 5 :
			if(cnt5 == 0){
				cnt5++;
			}
			else{
				cnt5--;	
			}
			$('#searchCnt').val(cnt5);
			break;
	}
	$('#searchAry').val(searchAry);

	var searchWrd = document.getElementById("searchWrd").value;
	var searchCnd = document.getElementById("searchCnd").value;
	var currentPageNo = document.getElementById("currentPageNo").value;
	
	if(currentPageNo == ''){
		document.pushRecpntAllInfoFrm.currentPageNo.value = 1;	
	}
	else{
		document.pushRecpntAllInfoFrm.currentPageNo.value = currentPageNo;
	}
			
	document.pushRecpntAllInfoFrm.searchCnd.value = searchCnd;
	document.pushRecpntAllInfoFrm.searchWrd.value = searchWrd;
	
	var formData = $('#pushRecpntAllInfoFrm').serialize();
	$.ajax({
		type : "POST",
		url : "/pushNotfcatnRecpntAllPopupTable.do",
		data : formData,
		success: function(msg) {
			document.getElementById('recpntTable').innerHTML = msg;
		}
	});
}

function pushRecpntSearch(page){
	var searchWrd = document.getElementById("searchWrd").value;
	var searchCnd = document.getElementById("searchCnd").value;
	
	document.pushRecpntAllInfoFrm.currentPageNo.value = page;
	document.pushRecpntAllInfoFrm.searchCnd.value = searchCnd;
	document.pushRecpntAllInfoFrm.searchWrd.value = searchWrd;
	
	var formData = $('#pushRecpntAllInfoFrm').serialize();
	$.ajax({
		type : "POST",
		url : "/pushNotfcatnRecpntAllPopupTable.do",
		data : formData,
		success: function(msg) {
			document.getElementById('recpntTable').innerHTML = msg;
		}
	});
}

function pushRecpntPaging(page) {
	var searchWrd = document.getElementById("searchWrd").value;
	var searchCnd = document.getElementById("searchCnd").value;
	
	document.pushRecpntAllInfoFrm.currentPageNo.value = page;
	document.pushRecpntAllInfoFrm.searchCnd.value = searchCnd;
	document.pushRecpntAllInfoFrm.searchWrd.value = searchWrd;
	
	var formData = $("#pushRecpntAllInfoFrm").serialize();
	$.ajax({
		type : "POST",
		url : "/pushNotfcatnRecpntAllPopupTable.do",
		data : formData,
		success: function(msg) {
			document.getElementById('recpntTable').innerHTML = msg;	
		}
	});
}

function pushRecpntSearch2(page){
	var searchWrd = document.getElementById("searchWrd2").value;
	var searchCnd = document.getElementById("searchCnd2").value;
	
	document.pushRecpntAllInfoFrm2.currentPageNo.value = page;
	document.pushRecpntAllInfoFrm2.searchCnd.value = searchCnd;
	document.pushRecpntAllInfoFrm2.searchWrd.value = searchWrd;
	
	var formData = $('#pushRecpntAllInfoFrm2').serialize();
	$.ajax({
		type : "POST",
		url : "/pushNotfcatnRecpntAllPopupProdctTable.do",
		data : formData,
		success: function(msg) {
			document.getElementById('recpntProdctTable').innerHTML = msg;
		}
	});
}

function pushRecpntPaging2(page) {
	var searchWrd = document.getElementById("searchWrd2").value;
	var searchCnd = document.getElementById("searchCnd2").value;
	
	document.pushRecpntAllInfoFrm2.currentPageNo.value = page;
	document.pushRecpntAllInfoFrm2.searchCnd.value = searchCnd;
	document.pushRecpntAllInfoFrm2.searchWrd.value = searchWrd;
	
	var formData = $("#pushRecpntAllInfoFrm2").serialize();
	$.ajax({
		type : "POST",
		url : "/pushNotfcatnRecpntAllPopupTable.do",
		data : formData,
		success: function(msg) {
			document.getElementById('recpntProdctTable').innerHTML = msg;	
		}
	});
}


function pushRecpntTempPaging(page) {
	document.custmrTempFrm.currentPageNo.value = page;
	
	var formData = $("#custmrTempFrm").serialize();
	$.ajax({
		type : "POST",
		url : "/pushNotfcatnRecpntAllPopupTempTable.do",
		data : formData,
		success: function(msg) {
			document.getElementById('tempTable').innerHTML = msg;	
		}
	});
}


function pushRecpntTempPaging2(page) {
	document.custmrTempFrm.currentPageNo.value = page;
	
	var formData = $("#custmrTempFrm2").serialize();
	$.ajax({
		type : "POST",
		url : "/pushNotfcatnRecpntAllPopupProdctTempTable.do",
		data : formData,
		success: function(msg) {
			document.getElementById('tempTable2').innerHTML = msg;	
		}
	});
}

function insertRecpnt(){
	$.ajax({
		type : "POST",
		url : "/pushModalAllSearchPopupTempTableCount.do",
		success: function(data) {
			opener.insertRecpnt(data);
			self.close();
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

//고객 추가 테이블 체크박스
function allCChecking(){
    var aBox = custmrFrm.custmrCheck;
    if(aBox.length) {  // 여러 개일 경우
        for(var i = 0; i<aBox.length;i++) {
        	aBox[i].checked=custmrFrm.allCCheck.checked;
        }
    } 
    else { // 한 개일 경우
    	aBox.checked=custmrFrm.allCCheck.checked;
	}
}

//상품의 고객 추가 테이블 체크박스
function allCChecking2(){
    var aBox = custmrFrm2.custmrCheck2;
    if(aBox.length) {  // 여러 개일 경우
        for(var i = 0; i<aBox.length;i++) {
        	aBox[i].checked=custmrFrm2.allCCheck2.checked;
        }
    } 
    else { // 한 개일 경우
    	aBox.checked=custmrFrm2.allCCheck2.checked;
	}
}

//고객 삭제 테이블 체크박스
function allTChecking(){
    var aBox = custmrTempFrm.custmrTempCheck;
    if(aBox.length) {  // 여러 개일 경우
        for(var i = 0; i<aBox.length;i++) {
        	aBox[i].checked=custmrTempFrm.allTCheck.checked;
        }
    } 
    else { // 한 개일 경우
    	aBox.checked=custmrTempFrm.allTCheck.checked;
	}
}

//상품의 고객 삭제 테이블 체크박스
function allTChecking2(){
    var aBox = custmrTempFrm2.custmrTempCheck2;
    if(aBox.length) {  // 여러 개일 경우
        for(var i = 0; i<aBox.length;i++) {
        	aBox[i].checked=custmrTempFrm2.allTCheck2.checked;
        }
    } 
    else { // 한 개일 경우
    	aBox.checked=custmrTempFrm2.allTCheck2.checked;
	}
}

function tableRefresh(){
	$.ajax({
		type: "POST",
		url: '/pushNotfcatnRecpntAllPopupTable.do',
		success: function(msg) {
			document.getElementById('recpntTable').innerHTML = msg;	
		}	
	});	    
	$.ajax({
		type: "POST",
		url: '/pushNotfcatnRecpntAllPopupProdctTable.do',
		success: function(msg) {
			document.getElementById('recpntProdctTable').innerHTML = msg;	
		}	
	});	
	$.ajax({
		type: "POST",
		url: '/pushNotfcatnRecpntAllPopupTempTable.do',
		success: function(msg) {
			document.getElementById('tempTable').innerHTML = msg;
		}	
	});	 
	$.ajax({
		type: "POST",
		url: '/pushNotfcatnRecpntAllPopupProdctTempTable.do',
		success: function(msg) {
			document.getElementById('tempTable2').innerHTML = msg;
		}	
	});	
}

//고객 추가
function insertCustmr(){
	var chked_length = $("input[name=custmrCheck]:checked").length; //체크된 박스 개수
	if(chked_length == 0){
		alert("추가할 고객을 선택하십시오.")
	}
	else{
		var chked_val = "";
		$(":checkbox[name='custmrCheck']:checked").each(function(pi,po){
			chked_val += ","+po.value;
		});
		if(chked_val!="")chked_val = chked_val.substring(1);
		$.ajax({
			type:"POST",
			url:"/insertPushRecpntAllTemp.do",
			data:chked_val,
			success:function(){ 
				tableRefresh();
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});		
	}
}

//상품의 고객 추가
function insertCustmr2(){
	var chked_length = $("input[name=custmrCheck2]:checked").length; //체크된 박스 개수
	if(chked_length == 0){
		alert("추가할 고객을 선택하십시오.")
	}
	else{
		var chked_val = "";
		$(":checkbox[name='custmrCheck2']:checked").each(function(pi,po){
			chked_val += ","+po.value;
		});
		if(chked_val!="")chked_val = chked_val.substring(1);
		$.ajax({
			type:"POST",
			url:"/insertPushRecpntAllTemp.do",
			data:chked_val,
			success:function(){ 
				tableRefresh();
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});		
	}
}

//고객 삭제
function deleteCustmr(){
	var chked_length = $("input[name=custmrTempCheck]:checked").length; //체크된 박스 개수
	if(chked_length == 0){
		alert("삭제할 고객을 선택하십시오");
	}
	else{	
		var chked_val = "";
		$(":checkbox[name='custmrTempCheck']:checked").each(function(pi,po){
			chked_val += ","+po.value;
		});
		if(chked_val!="")chked_val = chked_val.substring(1);
		$.ajax({
			type:"POST",
			url:"/deletePushNotfcatnRecpntAllPopupTempTable.do",
			data:chked_val,
			success:function(){
				tableRefresh();
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});		
	}
}

//상품의 고객 삭제
function deleteCustmr2(){
	var chked_length = $("input[name=custmrTempCheck2]:checked").length; //체크된 박스 개수
	if(chked_length == 0){
		alert("삭제할 고객을 선택하십시오");
	}
	else{	
		var chked_val = "";
		$(":checkbox[name='custmrTempCheck2']:checked").each(function(pi,po){
			chked_val += ","+po.value;
		});
		if(chked_val!="")chked_val = chked_val.substring(1);
		$.ajax({
			type:"POST",
			url:"/deletePushNotfcatnRecpntAllPopupTempTable.do",
			data:chked_val,
			success:function(){
				tableRefresh();
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});		
	}
}

function closeIt(){
	$.ajax({
		type: "POST",
		url: '/pushRecpntAllTempDelete.do',
		success: function(msg) {
		}	
	});	

    window.opener.$("#FadeIn").remove();
    self.close();
}; 

$(document).ready(function() {
	//F5키 막아놓기
	window.onkeydown = function() {
		var kcode = event.keyCode;
		if(kcode == 116) event.returnValue = false;
	}
});