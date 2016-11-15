//상품테이블 검색
function prodctSech() {
	var formData = $("#prodctSechFrm").serialize();
	$.ajax({
		type : "POST",
		url : "/selectProdctAdList.do",
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
		url : "/selectProdctAdList.do",
		data : formData,
		success: function(msg) {
			document.getElementById('prodctTable').innerHTML = msg;	
		}
	});
}

