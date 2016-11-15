var selProdctImg = new Array()
var selProdctImgNum = 0;
var noImagePath = "resources/image/noImg.jpg";
$(document).ready(function() {
	$("#selProdctImgCheck").click(function(){
		var imgFrm = new FormData(document.getElementById('selProdctFileFrm'));
		var prodctSeqVar= $("#prodctSeq").val();
		var dupliCheckSeqVar= $("#dupliCheckSeq").val();
		
		if(prodctSeqVar=="")
		{
			alert("사진 확인 전에 바코드를 입력해주세요.");
			$("#prodctSeq").focus();
		}
		else if(dupliCheckSeqVar=="")
		{
			alert("사진 확인 전에 바코드 중복 여부를 검사해주세요.");
			$("#prodctSeq").focus();	
		}
		else if(prodctSeqVar!=dupliCheckSeqVar)
		{
			alert("중복 검사 하셨던 바코드와 지금의 바코드가 다릅니다.");
			$("#prodctSeq").focus();	
		}
		else
		{	
			$("#prodctFileSeq").val($("#prodctSeq").val())
			$.ajax({
				url: "/tempProdctImgSave.do",
				data: imgFrm,
				processData: false,
				contentType: false,
				type: 'POST',
				success:function(data){
					var imgPath = data["prodctValueObject"];
					imgNullCheck(imgPath.prodctMainImage, imgPath.prodctDetlImageOne, imgPath.prodctDetlImageTwo,imgPath.prodctDetlImageThree);
					
					$("#selProdctTempImg").attr("src",selProdctImg[0]);
					$("#imgRight").show();
					$("#imgLeft").hide();
					selProdctImgNum=0;
				},
				error:function(xhr,status,error)
				{ //ajax 오류인경우  
					alert("$");
					alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
				}
			});
		}
	});
	$("#imgRight").click(function(){
		if(selProdctImgNum==selProdctImg.length-2)
		{
			selProdctImgNum++;
			$("#selProdctTempImg").attr("src",selProdctImg[selProdctImgNum]);
			$("#imgRight").hide();
		}
		else
		{
			selProdctImgNum++;
			$("#selProdctTempImg").attr("src",selProdctImg[selProdctImgNum]);
			$("#imgLeft").show();
		}
	});
	$("#imgLeft").click(function(){
		if(selProdctImgNum-1==0)
		{
			selProdctImgNum--;
			$("#selProdctTempImg").attr("src",selProdctImg[selProdctImgNum]);
			$("#imgLeft").hide();
		}
		else if(selProdctImgNum>=1)
		{
			selProdctImgNum--;
			$("#selProdctTempImg").attr("src",selProdctImg[selProdctImgNum]);
			$("#imgRight").show();
		}
	});
	
	$("#selProdctTempImg").click(function(){
		var popUrl = "/selProdctImgExpnd.do";
		var popOption = "width=605, height=605,  toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, ";    //공유 팝업창 옵션(optoin)
		imgExpandPop = window.open(popUrl,"selProdctImg",popOption);

	});
});
function imgLoad()
{

	document.all.selProdctImgs.src = opener.document.getElementById("selProdctTempImg").src;
}
function imgExpandPopUp() {
	var popUrl = "/selProdctImgExpnd.do";
	var popOption = "width=600, height=600,  toolbar=no, location=no, directories=no, scrollbars=no, resizable=no, ";    //공유 팝업창 옵션(optoin)
	imgExpandPop = window.open(popUrl,"selProdctImg",popOption);
	imgExpandPop.document.all.selProdctImgs.src = selProdctImg[selProdctImgNum];
};
function imgNullCheck(main, detl1, detl2, detl3)
{
	var i = 0;
	if(main!=null)
	{
		selProdctImg[i] = main;
		$("#mainFile").val(main)
		i++
	}
	if(detl1!=null)
	{
		selProdctImg[i] = detl1;
		$("#detlFile1").val(detl1)
		i++
	}
	if(detl2!=null)
	{
		selProdctImg[i] = detl2;
		$("#detlFile2").val(detl2)
		i++
	}
	if(detl3!=null)
	{
		selProdctImg[i] = detl3;
		$("#detlFile3").val(detl3)
		i++
	}
	if(i>=1)
	{
		$("#selProdctTempImg").attr("src",selProdctImg[0]);
		$("#imgRight").show();
	}
}

function imgChangeChck()
{	
	var chck = 0;
	if($("#mainProdctImgFile").val()!="")
		chck++;
	if($("#detlProdctImgOneFile").val()!="")
		chck++;
	if($("#detlProdctImgTwoFile").val()!="")
		chck++;
	if($("#detlProdctImgThreeFile").val()!="")
		chck++;
	return chck;
}
	