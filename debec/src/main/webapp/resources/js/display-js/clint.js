//상품테이블 검색
function clintListSearch() {
	var formData = $("#clintSechFrm").serialize();
	$.ajax({
		type : "POST",
		url : "/packgClintList.do",
		data : formData,
		success: function(msg) {
			document.getElementById('clintTable').innerHTML = msg;	
		}
	});
}

//상품테이블 페이징
function clintPaging(page) {
	document.clintPagingFrm.currentPageNo.value = page;
	var formData = $("#clintPagingFrm").serialize();
	$.ajax({ 
		type : "POST",
		url : "/packgClintList.do",
		data : formData,
		success: function(msg) {
			document.getElementById('clintTable').innerHTML = msg;	
		}
	});
}

function clickClint(clintSeq){
	opener.parent.insertClintProdct(clintSeq);
	opener.parent.document.getElementById('clintSeq').value = clintSeq;
	self.close();
    window.opener.$("#FadeIn").remove();
};

function closeIt(){
    self.close();
    window.opener.$("#FadeIn").remove();
};

//리스트 색 변경
function changeTrColor(trObj, oldColor, newColor) {
	trObj.style.backgroundColor = newColor;
	trObj.onmouseout = function(){
		trObj.style.backgroundColor = oldColor;
	}
};