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
			url: "/hotdlAdList.do",
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

//상품 추가창에서 상품 정보 입력
function insertInfo(id){
	var trId = '#' + id;
	var prodctNme = $(trId).children().html();
	var selPric = $(trId).children().next().html();
	var img = $(trId).next().val();
	$('#prodctSeq').val(id);
	$('#prodctSeq2').html(id);
	$('#prodctNme').html(prodctNme);
	$('#selPric').html(selPric);
	$('#prodctImg').attr('src',img);
};

//상품추가 등록 최종완료
function insertProdct(){
	if($('#prodctSeq').val()==""){
		alert("선택된 상품이 없습니다.");
	}
	else{
		var formData = $("#hotdlAdForm").serialize();
		$.ajax({
			type : "POST",
			url : "/hotdlCreate.do",
			data : formData,
			success: function(msg) {
				alert("등록이 완료되었습니다.");
				$('#prodctSeq').val("");
				$('#prodctSeq2').html("");
				$('#prodctNme').html("");
				$('#selPric').html("");
				$('#discntPric').val("");
				$('#amont').val("");
				$('#prodctImg').attr('src','');
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
};

//상품추가 취소
function cancelProdct(){
    window.opener.location.reload();
    self.close();
};

//상품테이블 검색
function prodctSech() {
	var formData = $("#prodctSechFrm").serialize();
	$.ajax({ 
		type : "POST",
		url : "/hotdlAdList.do",
		data : formData,
		success: function(msg) {
			document.getElementById('prodctTable').innerHTML = msg;	
		}
	});
};

//상품테이블 페이징
function prodctPaging(page) {
	document.prodctPagingFrm.currentPageNo.value = page;
	var formData = $("#prodctPagingFrm").serialize();
	$.ajax({
		type : "POST",
		url : "/hotdlAdList.do",
		data : formData,
		success: function(msg) {
			document.getElementById('prodctTable').innerHTML = msg;	
		}
	});
};