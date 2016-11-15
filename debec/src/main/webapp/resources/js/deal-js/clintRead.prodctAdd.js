$(document).ready(function(){
	// 상품추가 ( '+' 버튼 )
	$('#plus').click(function(){
		var chked_length = $("input[name=realProdctCheck]:checked").length;		// 체크된 박스 개수
		if(chked_length == 0){
			alert("추가할 상품을 선택하십시오.");
		}
		else{
			var chked_val="";
			$(":checkbox[name='realProdctCheck']:checked").each(function(pi,po){	// each는 반복문, pi는 pi만큼 반복하고, po는 checkbox의 value값
				chked_val += ","+po.value;
			});
			if(chked_val!="")chked_val = chked_val.substring(1);
			$.ajax({
				type:"POST",
				url:"/ProdctAddTempTableCreate.do",		// 상품추가 닷두 (쿼리 미작성), 왼쪽테이블에서 체크한 상품을 임시테이블로 insert하는 닷두
				data:chked_val,		// 체크박스에서 체크한 항목을 데이타로 보냄
				success:function(){
					$.ajax({
						type:"POST",
						url:'/SelectProdctAddList.do',	// 상품테이블(왼쪽테이블)에 임시테이블에 없는 상품을 조회함(체크한 상품을 임시테이블로 insert하고 바로 select함)
						success: function(msg){
							document.getElementById('prodctTable').innerHTML = msg;
						}
					});
					$.ajax({
						type:"POST",
						url: '/SelectProdctAddTempList.do',		// 상품테이블(왼쪽테이블) select하고 임시테이블(오른쪽테이블) select 함
						success:function(msg){
							document.getElementById('tempTable').innerHTML = msg;
						}
					});
				},
				error:function(request,status,error){
					alert("code : " + request.status+"\n"+"message : "+request.responseText+"\n"+"error : "+error);
				}
			});
		}
	});
	
	// 상품 삭제 ( '-' 버튼 )
	$('#minus').click(function(){
		var chked_length = $("input[name=tempProdctCheck]:checked").length;		// 체크된 박스 개수
		if(chked_length == 0){
			alert("삭제할 상품을 선택하십시오.");
		}
		else{
			var chked_val="";
			$(":checkbox[name='tempProdctCheck']:checked").each(function(pi,po){	//each는 반복문, pi는 pi만큼 반복하고, po는 checkbox의 value값
				chked_val += ","+po.value;
			});
			if(chked_val != "") chked_val = chked_val.substring(1);
			$.ajax({
				type:"POST",
				url:"/ProdctAddTempTableDelete.do",		// 임시테이블(오른쪽테이블)에서 체크박스 체크하고 '-' 버튼 누를 시 임시테이블에 있는 값을 삭제 하는 닷두
				data:chked_val,
				success:function(){
					$.ajax({
						type:"POST",
						url: '/SelectProdctAddList.do',		// 임시테이블(오른쪽테이블)에서 삭제한 닷두 돌리고 상품테이블(왼쪽테이블)다시 조회하는 닷두
						success: function(msg){
							document.getElementById('prodctTable').innerHTML = msg;
						}
					});
					$.ajax({
						type : "POST",
						url : '/SelectProdctAddTempList.do',		// 임시테이블 조회(임시테이블에 체크한 상품 삭제하고 다시 조회)
						success: function(msg){
							document.getElementById('tempTable').innerHTML = msg;
						}
					});
				},
				error:function(request,status,error){
					alert("code:"+request.status+"\n"+"message : "+request.responseText+"\n"+"error : "+error);
				}
			});
		}
	});
});

// 상품 추가 테이블 체크박스
function allPChecking(){
	var aBox = prodctForm.realProdctCheck;
	if(aBox.length){	// 체크한 항목이 여러개일경우
		for(var i = 0; i<aBox.length; i++){
			aBox[i].checked = prodctForm.prodctAllCheck.checked;		// 스트링 배열에 체크항목을 만들어서 체크한 값들을 넣음
		}
	}
	else {		// 체크한 항목이 한개일경우
		aBox.checked = prodctForm.prodctAllCheck.checked;
	}
}

// 임시 테이블 체크박스
function allTChecking(){
	var bBox = tempForm.tempProdctCheck;		// 폼안에 tempProdctCheck는 각각 체크박스 항목의 name 값
	if(bBox.length){		//체크한 항목이 여러개일경우
		for(var i = 0; i<bBox.length; i++){
			bBox[i].checked = tempForm.tempAllCheck.checked;
		}
	}
	else {		// 체크한 항목이 한개일경우
		bBox.checked = tempForm.tempAllCheck.checked;
	}
}



