
$(document).ready(function() {
	document.getElementById("rtcInput").value = getTimeStamp();
	setTimeout("realtimeClock()", 1000);
});

//상품테이블 검색
function prodctSech() {
	var formData = $("#prodctSechFrm").serialize();
	alert(formData);
	$.ajax({
		type : "POST",
		url : "/freshAdList.do",
		data : formData,
		success: function(msg) {
			alert(msg);
			document.getElementById('prodctTable').innerHTML = msg;	
			alert("@@@@@@@@@");
		} 
	});
}

//상품테이블 페이징
function prodctPaging(page) {
	document.prodctPagingFrm.currentPageNo.value = page;
	var formData = $("#prodctPagingFrm").serialize();
	alert(formData);
	$.ajax({
		type : "POST",
		url : "/freshAdList.do",
		data : formData,
		success: function(msg) {
			alert(msg);
			document.getElementById('prodctTable').innerHTML = msg;	
			alert("@@@@@@@@@");
		}
	});
}


//상품 추가창에서 상품 정보 입력
function insertInfo(id){
	var trId = '#' + id;
	var prodctNme = $(trId).children().html();
	var selPric = $(trId).children().next().html();
	var stat = $(trId).children().next().next().html();
	$('#prodctSeq').val(id);
	$('#prodctSeq2').html(id);
	$('#prodctNme').html(prodctNme);
	$('#selPric').html(selPric);
}

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