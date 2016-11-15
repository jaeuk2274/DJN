//  제이쿼리 최신버전 임폴트 해야 잘 돌아가요~
//  제작자 하원식

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}


var width;
var height;
var catgrSeq;
$(document).ready(function() {	
		
		//상품추가창 모달  
	$( "[name='reltnProdctChoice']").click(function(){
//		var width = 700//document.body.scrollWidth*0.8//(문서 전체의 크기)
//		var height = document.body.scrollHeight*0.55
		var popUrl = "/reltnProdctAdListPopUp.do";	//팝업창에 출력될 페이지 URL
		var popOption = "width=750, height=630, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)

			window.open(popUrl,"",popOption);

	});

	   
	//카테고리 리스트 조회
	$.ajax({
		type: "POST",
		dataType: "JSON",
		url: "catgrProdctList.do",
		error: function(){
			alert("Select failed.");
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
		catgrSeq = {"selectedCatgrSeq" : data.selected}
		$.ajax({
			type: "POST",
			data : catgrSeq,
			url: "/catgrChange.do",
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
//상품테이블 검색
function prodctSech() {
	var formData = $("#prodctSechFrm").serialize();
	$.ajax({
		type : "POST",
		url : "/reltnProdctAdList.do",
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
		url : "/reltnProdctAdList.do",
		data : formData,
		success: function(msg) {
			document.getElementById('prodctTable').innerHTML = msg;	
		}
	});
}
//팝업 닫기
function prodctCancel()
{
	catgrSeq="";
	self.close();
}
function prodctChoice(prodctSeq)
{
	var prodctData = {"prodctSeq":prodctSeq};
	$.ajax({
		type: "get",
		url: "/reltnProdctDetlRead.do", //이페이지에서 중복체크를 한다
		data : prodctData,
		dataType : "JSON",
		success: function(data){
			var reltnProdct = data["reltnProdctList"];
			var newProdctSelPric = numberWithCommas(reltnProdct[0].prodctSelPric)+" 원";
			if($(opener.document).find("#selectedReltnProdct").val()==1)
			{
				$(opener.document).find("#reltnProdctSeqOne").val(reltnProdct[0].prodctSeq);
				$(opener.document).find("#reltnProdctNmeOne").val(reltnProdct[0].prodctNme);
				$(opener.document).find("#reltnProdctSelPricOne").val(newProdctSelPric);
				if(reltnProdct[0].prodctMainImage==null)
				{
					$(opener.document).find("#reltnProdctImgOne").attr("src","/resources/image/common/noImg.png");
				}
				else
				{
					$(opener.document).find("#reltnProdctImgOne").attr("src",reltnProdct[0].prodctMainImage);
				}
			}
			else if($(opener.document).find("#selectedReltnProdct").val()==2)
			{
				$(opener.document).find("#reltnProdctSeqTwo").val(reltnProdct[0].prodctSeq);
				$(opener.document).find("#reltnProdctNmeTwo").val(reltnProdct[0].prodctNme);
				$(opener.document).find("#reltnProdctSelPricTwo").val(newProdctSelPric);
				if(reltnProdct[0].prodctMainImage==null)
				{
					$(opener.document).find("#reltnProdctImgTwo").attr("src","/resources/image/common/noImg.png");
				}
				else
				{
					$(opener.document).find("#reltnProdctImgTwo").attr("src",reltnProdct[0].prodctMainImage);
				}
			}
			else if($(opener.document).find("#selectedReltnProdct").val()==3)
			{
				$(opener.document).find("#reltnProdctSeqThree").val(reltnProdct[0].prodctSeq);
				$(opener.document).find("#reltnProdctNmeThree").val(reltnProdct[0].prodctNme);
				$(opener.document).find("#reltnProdctSelPricThree").val(newProdctSelPric);
				if(reltnProdct[0].prodctMainImage == null)
				{
					$(opener.document).find("#reltnProdctImgThree").attr("src","/resources/image/common/noImg.png");
				}
				else 
				{
					$(opener.document).find("#reltnProdctImgThree").attr("src",reltnProdct[0].prodctMainImage);
				}
			}
			self.close();
			catgrSeq="";
			
		}
	});
}