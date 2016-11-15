/**
 * 
 */
//공동구매 검색
function grorPurchsSearch(){
	document.gropPurchsSearch.action="/togthrProdctList.do";
	document.gropPurchsSearch.submit();
}
//상품추가창 팝업
	function prodctAdd(){
		var popUrl = "togthrPurchsInsertPopup.do";
		var popupName= "togthrPurchsInsertPopup";
		var width = 1200;
		var height = 700;
		var leftPosition = (screen.width/2) - (width/2);
			leftPosition += window.screenLeft;//듀얼 모니터일때
		var topPosition = (screen.height/2) - (height/2);
		
		var win = window.open(popUrl, popupName, "left="+leftPosition+", top="+topPosition+", toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, width="+width+", height="+height);
		
		document.togthrProdctFrm.target = popupName;
		document.togthrProdctFrm.action = popUrl;
		document.togthrProdctFrm.submit();
	};
//다함께 공동구매 상품 상세조회
function togthrDetail(seq){
	var popUrl= "togthrDetail.do";
	var popupName= "TogthrDetail";
	var width = 750;
	var height = 570;
	var leftPosition = (screen.width/2) - (width/2);
		leftPosition += window.screenLeft;//듀얼 모니터일때
	var topPosition = (screen.height/2) - (height/2);
	
	var win = window.open(popUrl, popupName, "left="+leftPosition+", top="+topPosition+", toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, width="+width+", height="+height);
	$('#gropPurchsProdctSeq').val(seq);
	document.togthrDetailFrm.target = popupName;
	document.togthrDetailFrm.action = "/togthrDetail.do";
	document.togthrDetailFrm.submit();
}
//카테고리 리스트 조회
$.ajax({
	type: "POST",
	dataType: "JSON",
	url: "selectCatgrList.do",
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

function prodctAddDetail(id){
	var trId = '#' + id;
	var prodctNme = $(trId).children().html();
	var selPric = $(trId).children().next().html();
	var prodctMainImage = $(trId).next().val();
	var prodctDetlImageOne = $(trId).next().next().val();
	var prodctDetlImageTwo = $(trId).next().next().next().val();
	var prodctDetlImageThree = $(trId).next().next().next().next().val();
	
	$('#GropPurchsprodctSeq').attr("value",id);
	$('#prodctNme').attr("value",prodctNme);
	$('#existPric').attr("value",selPric);
	
	$('#togthProdctMainImage').attr("value",prodctMainImage);
	$('#togthProdctDetlImageOne').attr("value",prodctDetlImageOne);
	$('#togthProdctDetlImageTwo').attr("value",prodctDetlImageTwo);
	$('#togthProdctDetlImageThree').attr("value",prodctDetlImageThree);
}

//메인 페이징
function togthrPaging(page) {
	document.togthrPagingFrm.currentPageNo.value = page;
	document.togthrPagingFrm.action ="/togthrProdctList.do";
	document.togthrPagingFrm.submit();
}
$(document).ready(function() {

	//카테고리 선택 시 조회
	$('#tree').on("changed.jstree", function (e, data) {
		alert("ㅌ세ㅡ트");
		var catgrSeq = {"selectedCatgrSeq" : data.selected}
		$.ajax({
			type: "POST",
			data : catgrSeq,
			url: "selectTogthrProdctAdList.do",
			error: function(){
			},
			success: function(data){	
		       	document.getElementById('prodctTable').innerHTML = data;   
			}
		});
	});
	
	//체크박스 전체클릭
	$("#allCheck").click(function(){
	    //클릭되었으면
	    if($("#allCheck").prop("checked")){
	        //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
	        $("input[name=togthrChk]").prop("checked",true);
	        //클릭이 안되있으면
	    }else{
	        //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
	        $("input[name=togthrChk]").prop("checked",false);
	    }
	})
	//체크박스 삭제
    $('#togthrDelete').click(function(){
    	var chked_length = $("input[name=togthrChk]:checked").length; //체크된 박스 개수
    	if (confirm("총 " + chked_length + "개의 항목을 삭제하시겠습니까?") == true){    //확인
    		var chked_val = "";
			$(":checkbox[name='togthrChk']:checked").each(function(pi,po){
				chked_val += ","+po.value;
			});
			if(chked_val!="")chked_val = chked_val.substring(1);
			if(chked_val == ""){
				alert("삭제할 상품을 선택해주십시오.")
			}
			else{
				$.ajax({
					type:"POST",
					url:"/deleteToghrProdct.do",
					data:chked_val,
					success:function(data){
						location.reload();
					},
					error:function(request,status,error){
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
				});
			}		
    	}
    	else{
    		alert("삭제가 취소되었습니다.");	
    	}
				
    });
	
	//다함게 상품 등록
	$('#togthrProdctInsert').click(function(){
		if($("#togthInsertTitl").val() == "" && $("#togthInsertOrdrStarDat").val() == "" && $("#togthInsertOrdrEndDat").val() == "" && $("#togthInsertMinOrdrAmont").val() == "" && $("#togthInsertMaxOrdrAmont").val() == "" && $("#togthInsertSelPric").val() == "") {
			alert("정보를 입력하세요.");
		}
		else{
			var formData = $("#purchsProdctInsert").serialize();
			$.ajax({
		      type: "POST",
		      url: "/purchsProdctInsert.do",
		      data: formData ,
		      success: function(data){//이페이지에서 중복체크를 한다
		    	  opener.parent.location.reload();
		    	  self.close();
		      },
		       error:function(xhr,status,error)
		       { 
		          alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
		       }
		  });
		}
	});
	//다함게 상품 팝업 취소
	$('#togthrProdctCancle').click(function(){
		window.close();
	});
});
//더함께 상품 수정화면
function togthrUpdateFrm(){
	document.purchsProdctDetail.action="/togthrUpdateFrm.do";
	document.purchsProdctDetail.submit();
}
//더함께 상품 수정
function togthrUpdate() {
	document.purchsProdctUpdate.action="/togthrUpdate.do";
	document.purchsProdctUpdate.submit();
}
//더함께 업데이트 화면에서 삭제
function togthrUpdateFrmDelete() {
	var gropPurchsProdctSeq = "gropPurchsProdctSeq="+$('#gropPurchsProdctSeq').val();
	$.ajax({
      type: "POST",
      url: "/togthrUpdateFrmDelete.do",
      data: gropPurchsProdctSeq ,
      success: function(data){//이페이지에서 중복체크를 한다
    	  opener.parent.location.reload();
    	  self.close();
      },
       error:function(xhr,status,error)
       { 
          alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
       }
  });
}
function cancle(){
	document.purchsProdctUpdate.action="/togthrDetail.do";
	document.purchsProdctUpdate.submit();
}

//메인 페이징
function togthProdctPaging(page) {
	document.TogthProdctPagingFrm.currentPageNo.value = page;
//	$("#TogthCurrentPageNo").val(page);
	var formData = $("#TogthProdctPagingFrm").serialize();
	$.ajax({
	      type: "POST",
	      url: "/togthrProdctAdList.do",
	      data: formData ,
	      success: function(data){//이페이지에서 중복체크를 한다
	    	  document.getElementById('togthProdctManagementList').innerHTML = data;
	      },
	       error:function(xhr,status,error)
	       { 
	          alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
	       }
	  });
}

function togthSearch(){
	var formData = $("#prodctSechFrm").serialize();
	$.ajax({
      type: "POST",
      url: "/togthrProdctAdList.do",
      data: formData ,
      success: function(data){//이페이지에서 중복체크를 한다
    	  document.getElementById('togthProdctManagementList').innerHTML = data;
      },
       error:function(xhr,status,error)
       { 
          alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
       }
  });
}