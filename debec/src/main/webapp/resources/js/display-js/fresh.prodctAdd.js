$(document).ready(function() {
	//현재 시간 보여주는 함수
	document.getElementById("rtcInput").value = getTimeStamp();
	setTimeout("realtimeClock()", 1000);
	
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
			url: "/freshAdList.do",
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

//현재 시간 표시
function realtimeClock() {
  document.getElementById("rtcInput").value = getTimeStamp();
  setTimeout("realtimeClock()", 1000);
}


function getTimeStamp() { // 24시간제
  var d = new Date();

  var s =
    leadingZeros(d.getFullYear(), 4) + '-' +
    leadingZeros(d.getMonth() + 1, 2) + '-' +
    leadingZeros(d.getDate(), 2) + ' ' +

    leadingZeros(d.getHours(), 2) + ':' +
    leadingZeros(d.getMinutes(), 2) + ':' +
    leadingZeros(d.getSeconds(), 2);

  return s;
}


function leadingZeros(n, digits) {
  var zero = '';
  n = n.toString();

  if (n.length < digits) {
    for (i = 0; i < digits - n.length; i++)
      zero += '0';
  }
  return zero + n;
}

//상품 추가창에서 상품 정보 입력
function insertInfo(id){
	var trId = '#' + id;
	var prodctNme = $(trId).children().html();
	var selPric = $(trId).children().next().html();
	var stat = $(trId).children().next().next().html();
	var img = $(trId).next().val();
	$('#prodctSeq').val(id);
	$('#prodctSeq2').html(id);
	$('#prodctNme').html(prodctNme);
	$('#selPric').html(selPric);
	$('#prodctImg').attr('src',img);

}
//상품추가
function insertProdct(){
	var formData = $("#freshAdForm").serialize();
	$.ajax({
		type : "POST",
		url : "/freshCreate.do",
		data : formData,
		success: function(msg) {
			alert("등록이 완료되었습니다.");
			$('#prodctSeq').val("");
			$('#freshAdListImg').html("");
			$('#prodctSeq2').html("");
			$('#prodctNme').html("");
			$('#selPric').html("");
			$('#stat').val("판매중");
			$('#prodctImg').attr('src','');
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	}); 
}

//상품 추가 취소
function cancelProdct(){
    window.opener.location.reload();
    self.close();
}

function closeIt(){
    window.opener.$("#FadeIn").remove();
    self.close();
}

//상품테이블 검색
function prodctSech() {
	var formData = $("#prodctSechFrm").serialize();
	$.ajax({
		type : "POST",
		url : "/freshAdList.do",
		data : formData,
		success: function(msg) {
			document.getElementById('prodctTable').innerHTML = msg;	
		}
	});
}

//상품테이블 페이징
function prodctPaging(page) {
	document.prodctPagingFrm.currentPageNo.value = page;
	var formData = $("#prodctPagingFrm").serialize();
	$.ajax({
		type : "POST",
		url : "/freshAdList.do",
		data : formData,
		success: function(msg) {
			document.getElementById('prodctTable').innerHTML = msg;	
		}
	});
}