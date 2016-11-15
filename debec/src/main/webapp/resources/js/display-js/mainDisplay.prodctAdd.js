$(document).ready(function() {	
	//F5키 막아놓기
	window.onkeydown = function() {
		var kcode = event.keyCode;
		if(kcode == 116) event.returnValue = false;
	}
	
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
			url: "/selectProdctAdList.do",
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
	
	//상품 추가
	$('#plus').click(function(){
	var chked_length = $("input[name=realProdctCheck]:checked").length; //체크된 박스 개수
	if(chked_length == 0){
		alert("추가할 상품을 선택하십시오.")
	}
	else{
		var chked_val = "";
		$(":checkbox[name='realProdctCheck']:checked").each(function(pi,po){
			chked_val += ","+po.value;
		});
		if(chked_val!="")chked_val = chked_val.substring(1);
		$.ajax({
			type:"POST",
			url:"/insertProdctAdTempList.do",
			data:chked_val,
			success:function(){ 
				$.ajax({
					type: "POST",
					url: '/selectProdctAdList.do',
					success: function(msg) {
						document.getElementById('prodctTable').innerHTML = msg;	
					}	
				});	    
				$.ajax({
					type: "POST",
					url: '/selectProdctAdTempList.do',
					success: function(msg) {
						document.getElementById('tempTable').innerHTML = msg;	
					}	
				});	   
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});		
		}
	});
	
	//상품 삭제
	$('#minus').click(function(){
	var chked_length = $("input[name=tempProdctCheck]:checked").length; //체크된 박스 개수
	if(chked_length == 0){
		alert("삭제할 상품을 선택하십시오");
	}
	else{	
		var chked_val = "";
		$(":checkbox[name='tempProdctCheck']:checked").each(function(pi,po){
			chked_val += ","+po.value;
		});
		if(chked_val!="")chked_val = chked_val.substring(1);
		$.ajax({
			type:"POST",
			url:"/deleteProdctAdTempList.do",
			data:chked_val,
			success:function(){
				$.ajax({
					type: "POST",
					url: '/selectProdctAdList.do',
					success: function(msg) {
						document.getElementById('prodctTable').innerHTML = msg;	
					}	
				});	    
				$.ajax({
					type: "POST",
					url: '/selectProdctAdTempList.do',
					success: function(msg) {
						document.getElementById('tempTable').innerHTML = msg;	
					}	
				});	   
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});		
		}
	});
});

//상품 추가 테이블 체크박스
function allPChecking(){
    var aBox = prodctForm.realProdctCheck;
    if(aBox.length) {  // 여러 개일 경우
        for(var i = 0; i<aBox.length;i++) {
        	aBox[i].checked=prodctForm.prodctAllCheck.checked;
        }
    } 
    else { // 한 개일 경우
    	aBox.checked=prodctForm.prodctAllCheck.checked;
	}
}

//임시 테이블 체크박스
function allTChecking(){
	var bBox = tempForm.tempProdctCheck;
	if(bBox.length) {  // 여러 개일 경우
    	for(var i = 0; i<bBox.length;i++) {
    		bBox[i].checked=tempForm.tempAllCheck.checked;
    	}
	}
	else { // 한 개일 경우
		bBox.checked=tempForm.tempAllCheck.checked;
	}
}

//상품테이블 검색
function prodctSech() {
	var formData = $("#prodctSechFrm").serialize();
	$.ajax({
		type : "POST",
		url : "/selectProdctAdList.do",
		data : formData,
		success: function(msg) {
			document.getElementById('prodctTable').innerHTML = msg;			}
	});
}

//상품테이블 페이징
function prodctPaging(page) {
	document.prodctPagingFrm.currentPageNo.value = page;
	var formData = $("#prodctPagingFrm").serialize();
	$.ajax({
		type : "POST",
		url : "/selectProdctAdList.do",
		data : formData,
		success: function(msg) {
			document.getElementById('prodctTable').innerHTML = msg;	
		}
	});
}

//임시테이블 검색
function tempSech() {
	var formData = $("#tempSechFrm").serialize();
	$.ajax({
		type : "POST",
		url : "/selectProdctAdTempList.do",
		data : formData,
		success: function(msg) {
			document.getElementById('tempTable').innerHTML = msg;
		}
	});
}

//임시테이블 페이징
function tempPaging(page) {
	document.tempPagingFrm.currentPageNo.value = page;
	var formData = $("#tempPagingFrm").serialize();
	$.ajax({
		type : "POST",
		url : "/selectProdctAdTempList.do",
		data : formData,
		success: function(msg) {
			document.getElementById('tempTable').innerHTML = msg;	
		}
	});
}

//상품추가 완료
function insertProdct(){
	var chked_val = "";
	$(":checkbox[name='tempProdctCheck']").each(function(pi,po){
		chked_val += ","+po.value;
	});
	if(chked_val!="")chked_val = chked_val.substring(1);
	$.ajax({
		type:"POST",
		url:"/insertProdct.do",
		data:chked_val,
		success:function(data){
			self.close();
			opener.parent.location.reload();
		},
		error:function(request,status,error){								
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
};

function cancelProdct(){
    window.opener.location.reload();
    self.close();
};

function closeIt(){
    window.opener.$("#FadeIn").remove();
    self.close();
};