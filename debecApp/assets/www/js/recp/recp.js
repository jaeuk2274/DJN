var recpSeq;
var	recpTitl;
var	recpCnt;
var	recpImg;
var	likeCount;
var	likeState;
var	replyCount;
var	likeState;
var	memType;
var replyMark=0;
var replyListVar; // 댓글 리스트를 저장하는 var
var recpProdctListVar; // 댓글 리스트를 저장하는 var
var MyRecpListVar; // 댓글 리스트를 저장하는 var
var recpSechText = "";
var recpProdct = new Array();
var recpProdctCnt = 0; 
var recpProdctFlag = 0; // 레시피 상품 선택 후 등록으로 갈지 수정으로 갈지 정해주는 var
var tempNum;
var cretSeq=0;
var replyClickFlag=0;
var readType = 0;
sessionStorage.setItem('recpImg', "../img/noImg.jpg");
sessionStorage.setItem('tempNum', "0");
sessionStorage.setItem('MyRecpListVar', MyRecpListVar);
/*

sender
0: recpList
1: recpRead

likeState
0:좋앙요 중
*/
// read 위한 likeprocessing ㄱㄱ


//로그인한 사람 Seq로 파라미터 안에 값 바꿔야 함

$(document).on('pageshow', function(event, data){ //"insertPage" 라는 page가 로딩됐을 때 실행이 되는 함수
	// ../img/love.png
	//레시피 리스트
	if ($.mobile.activePage.attr('id') == "recpListPage") {
		recpFooterSelector();
		var memData = getLoginMemData();
		var data = "memSeq="+memData.memSeq+"&memType="+memData.memType
		$.ajax({
			type:"POST",
			data:data,
			url:"http://sebm.iptime.org:9124/appRecpList.do",
			success:function(data){
	            console.log(data);
	            $.each(data, function(i, vo){
					
					if(vo.likeState == 0)
					{
						$("#newestRecpCont").append("<div class='nd2-card'>"
												+   "<div class='card-title has-avatar'>"
												+   "<h3 class='card-primary-title font-sm'>"+vo.recpTitl+"</h3>"
												+   "</div>"
												+   "<div class='card-media'>"
												+	"<a href='javascript:recpRead("+vo.recpSeq+",0)'>"
												+   "<img src='http://sebm.iptime.org:9124/"+vo.recpImg+"' style='width:100%;height:250px;'>"
												+	"</a>"
												+   "</div>"
												+   "<div class='card-supporting-text has-action'>"
												+   "<font class='font-md'>"+vo.recpCnt+"</font>"
												+   "</div>"
												+   "<div class='card-action'>"
												+   "<div class='col-xs-6w'>"
												+   "<font class='font-sm' id='stat"+vo.recpSeq+"'> 좋아요  "+vo.likeCount+" 댓글 "+vo.replyCount+"</font><br>"
												+   "<a id='likeMark"+vo.recpSeq+"' href='javascript:recpLikeProcessing("+vo.recpSeq+","+vo.likeState+",0)' class='ui-btn ui-btn-inline ui-btn-fab waves-effect waves-button waves-effect waves-button'>"
												+   "<img id='img"+ vo.recpSeq+"' src='../img/noLove.png' width='32px' height='32px'>"
												+   "</a>"
												+   "<a id='replyMark' href='javascript:replyClick("+vo.recpSeq+");' class= 'ui-btn ui-btn-inline ui-btn-fab waves-effect waves-button waves-effect waves-button'>"
												+   "<i class='zmdi zmdi-comment zmd-2x'></i></a></div></div></div>").trigger('create');
					}
					if(vo.likeState == 1)
					{
						$("#newestRecpCont").append("<div class='nd2-card'>"
												+   "<div class='card-title has-avatar'>"
												+   "<h3 class='card-primary-title font-sm'>"+vo.recpTitl+"</h3>"
												+   "</div>"
												+   "<div class='card-media'>"
												+	"<a href='javascript:recpRead("+vo.recpSeq+",0)'>"
												+   "<img src='http://sebm.iptime.org:9124/"+vo.recpImg+"' style='width:100%;height:250px;'>"
												+	"</a>"
												+   "</div>"
												+   "<div class='card-supporting-text has-action'>"
												+   "<font class='font-md'>"+vo.recpCnt+"</font>"
												+   "</div>"
												+   "<div class='card-action'>"
												+   "<div class='col-xs-6w'>"
												+   "<font class='font-sm' id='stat"+vo.recpSeq+"'> 좋아요  "+vo.likeCount+" 댓글  "+vo.replyCount+"</font><br>"
												+   "<a id='likeMark"+vo.recpSeq+"' href='javascript:recpLikeProcessing("+vo.recpSeq+","+vo.likeState+",0)' class='ui-btn ui-btn-inline ui-btn-fab waves-effect waves-button waves-effect waves-button'>"
												+   "<img id='img"+ vo.recpSeq+"' src='../img/love.png' width='32px' height='32px'>"
												+   "</a>"
												+   "<a id='replyMark' href='javascript:replyClick("+vo.recpSeq+");' class= 'ui-btn ui-btn-inline ui-btn-fab waves-effect waves-button waves-effect waves-button'>"
												+   "<i class='zmdi zmdi-comment zmd-2x'></i></a></div></div></div>").trigger('create');
					}

					
	            });
         },
         error:function(request,status,error){
              alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
         }
      }); 
		recpFamousList(data)
		recpLikeList(data);
		if(memData.memType == 0)
		{
			$("#recpDialogBtn").hide();
		}
		recpSeq = 0;
		recpTitl = "";
		recpCnt = "";
		recpImg = "";
		tempNum = 0;
		recpProdct = new Array();
	}

	if ($.mobile.activePage.attr('id') == "recpReadPage") {//////

		recpFooterSelector();
		$("#recpBtn").attr("onclick","javascript:recpReplyInsert("+recpSeq+")");
		$("#recpTitl").html(recpTitl);
		$("#recpImg").attr("src","http://sebm.iptime.org:9124/" +recpImg);
		sessionStorage.setItem('recpImg', recpImg);
		$("#recpCnt").html(recpCnt);
		$("#stat").html("좋아요 " + likeCount + " 댓글 "+replyCount);
		if(likeState==0)
		{
	       	 $("#likeMark").attr("href","javascript:recpLikeProcessing("+recpSeq+",0,1)");
	       	 $("#likeImg").attr("src","../img/noLove.png");
		}
        else if(likeState==1)
		{
	       	 $("#likeMark").attr("href","javascript:recpLikeProcessing("+recpSeq+",1,1)");
	       	 $("#likeImg").attr("src","../img/love.png");
		}
		if(readType==0)
		{
			$("#recpReadBack").attr("href","RecpList.html");
		}
		else if(readType==1)
		{
			$("#recpReadBack").attr("href","RecpMyRecpList.html");
		}
		$("#replyTb").html("");
		var memData = getLoginMemData();
		$.each(replyListVar, function(i, vo){
			if(vo.memType==0)
			memNme = vo.empNme;
        	else if(vo.memType==1)
        	memNme = vo.custmrNme;
			
			if(memData.memType==0||(memData.memType==vo.memType&&memData.memSeq==vo.memSeq))
			{
	        	$("#replyTb").append("<tr style='border-bottom:1px;'><td rowspan='2' class='font-md' style='width:20%'>"
	        						+ memNme+"</td>"
	        						+ "<td class='font-sm' style='word-wrap:break-word;width:75%'>" 
	        						+ vo.replyCnt+"</td>" 
	        						+ "<td rowspan='2' style='width:5%'><a href='javascript:recpReplyDelete("+vo.recpReplySeq+","+vo.recpSeq+")'><img src = '../img/delete.png'></a></td></tr>"
	        						+ "<tr><td class='font-sm'>"
	        						+ vo.dat+"</td></tr><tr><td colspan='3' style='height:10px;'><hr></td></tr>").trigger('create');
			}
			else
			{
				$("#replyTb").append("<tr style='border-bottom:1px;'><td rowspan='2' class='font-md' style='width:20%'>"
					+ memNme+"</td>"
					+ "<td class='font-sm' style='word-wrap:break-word;width:75%'>" 
					+ vo.replyCnt+"</td>" 
					+ "<td rowspan='2' style='width:5%'></td></tr>"
					+ "<tr><td class='font-sm'>"
					+ vo.dat+"</td></tr><tr><td colspan='3' style='height:10px;'><hr></td></tr>").trigger('create');	
			}
			
        	
        });
		var eachFlag = 0;
		$.each(recpProdctListVar, function(i, vo){
			if(i==0)
			{

				 $("#recpProdctList").append("<li data-role='list-divider' role='heading' class='ui-li-divider ui-bar-inherit ui-first-child font-size-middle'>식재료</li>");
	
		         $("#recpProdctList").append("<fieldset data-role='controlgroup' style='width:50%' class='ui-controlgroup ui-controlgroup-vertical ui-corner-all'>"
						            		+   "<div class='ui-controlgroup-controls '><div class='ui-checkbox'>"
											+   "<label for='recpProdctAllCheck' class='font-md ui-btn ui-corner-all ui-btn-inherit ui-btn-icon-left ui-checkbox-on ui-first-child ui-last-child' style='margin-left:10px;'>전체 선택</label>"
											+   "<input type='checkbox' id='recpProdctAllCheck' onclick='javascript:recpProdctAllCheckFnc()' onchange='javascript:recpProdctPriceCheck()'/>"
											+	"</div></div></fieldset><hr>").trigger('create') ;
			}
			$("#recpProdctList").append("<fieldset data-role='controlgroup' style='padding-top:7px;width:30%' class='ui-controlgroup ui-controlgroup-vertical ui-corner-all'>"
									+   "<div class='ui-controlgroup-controls '><div class='ui-checkbox'>"
									+   "<label for='recpProdct"+vo.prodctSeq+"' class='font-md ui-btn ui-corner-all ui-btn-inherit ui-btn-icon-left ui-checkbox-on ui-first-child ui-last-child' style='height:7px;margin-left:10px;'>&nbsp;</label>"
									+   "<input type='checkbox' name='recpProdctCheck' id='recpProdct"+vo.prodctSeq+"' value='"+vo.prodctSeq+"' data-cacheval='true' onchange='javascript:recpProdctPriceCheck()'>"
									+   "</div><div></fieldset>").trigger('create');
			$("#recpProdctList").append("<li class='ui-li-has-thumb ui-li-static ui-body-inherit' id='"+vo.recpProdctSeq+"'>"
	        						+   "<img src='http://sebm.iptime.org:9124/" +vo.mainImg+"' class='ui-thumbnail ui-thumbnail-circular'>"
	        						+   "<font class='font-sm'>" + vo.prodctNme+"</font>" 
	        						+   "<p class='font-sm' id='selPric"+vo.prodctSeq+"'>"+vo.selPric+"원</p></li><hr>").trigger('create');			
			eachFlag = 1;
		});
		if(eachFlag ==1)
		{
			$("#cartButton").append("<a class='ui-btn clr-btn-amber waves-effect waves-button waves-effect waves-button font-sm' onclick='javascript:recpProdctCartInsert()'>장바구니담기</a>").trigger('create') ;	
			$("#recpProdctList").append("<li class='font-md ui-li-static ui-body-inherit'><b class='font-md' style='float:left;'>총 금액</b><b id='resultPric' class='font-md' style='float:right; color:red'>0원</b>").trigger('create');
		}
		replyCntFocus(0);
		var memData = getLoginMemData();
		if(memSeq == memData.memSeq && memData.memType == 1)
		{
			$("#recpPopupDialog").append("<a href='RecpCret.html' data-role='button' data-inline='true' class='ui-btn ui-btn-primary'><i class='zmdi zmdi-check'></i>레시피등록하기</a>").trigger('create');
		    $("#recpPopupDialog").append("<a href='RecpMyRecpList.html' data-role='button' data-inline='true' class='ui-btn ui-btn-primary'><i class='zmdi zmdi-check'></i>내가올린레시피</a>").trigger('create');
		    $("#recpPopupDialog").append("<a href='javascript:recpUpdateRead()' data-role='button' data-inline='true' class='ui-btn ui-btn-primary'><i class='zmdi zmdi-check'></i>수정</a>").trigger('create');
		    $("#recpPopupDialog").append("<a href='javascript:recpDelete()' data-role='button' data-inline='true' class='ui-btn ui-btn-primary'><i class='zmdi zmdi-check'></i>삭제</a>").trigger('create');
		}
		else if(memData.memType == 1)
		{
			$("#recpPopupDialog").append("<a href='RecpCret.html' data-role='button' data-inline='true' class='ui-btn ui-btn-primary'><i class='zmdi zmdi-check'></i>레시피등록하기</a></li>").trigger('create');
		    $("#recpPopupDialog").append("<a href='RecpMyRecpList.html' data-role='button' data-inline='true' class='ui-btn ui-btn-primary'><i class='zmdi zmdi-check'></i>내가올린레시피</a></li>").trigger('create');
		}
		else if(memData.memType == 0)
		{
		    $("#recpPopupDialog").append("<a href='javascript:recpUpdateRead()' data-role='button' data-inline='true' class='ui-btn ui-btn-primary'><i class='zmdi zmdi-check'></i>수정</a>").trigger('create');
		    $("#recpPopupDialog").append("<a href='javascript:recpDelete()' data-role='button' data-inline='true' class='ui-btn ui-btn-primary'><i class='zmdi zmdi-check'></i>삭제</a>").trigger('create');
		}
		
	}
	if ($.mobile.activePage.attr('id') == "recpCretPage") {
		if (recpProdct!="")
		{
			
			////###
			 $("#recpTitl").val(recpTitl);
			 $("#recpCnt").val(recpCnt);
			 $("#recpImg").attr("src","http://sebm.iptime.org:9124/"+sessionStorage.getItem('recpImg'));
			 $("#tempNum").val(sessionStorage.getItem('tempNum'));
			recpCretProdctRead();
			
		}
	}
	if ($.mobile.activePage.attr('id') == "recptTitlSearchPage") {
		recpFooterSelector();
		$("#pageType").val(1);
		recpTitlSearchFn()
		
		var memData = getLoginMemData();
		if(memData.memType == 0)
		{
			$("#recpDialogBtn").hide();
		}	
			
	}

	if ($.mobile.activePage.attr('id') == "recpUpdatePage") {
		
		$("#recpSeq").val(sessionStorage.getItem('recpSeq'));
		$("#recpTitl").val(recpTitl);
		$("#recpImg").attr("src","http://sebm.iptime.org:9124/"+sessionStorage.getItem('recpImg'));
		$("#recpCnt").val(recpCnt);
		recpCretProdctRead()
		var recpUpdateFn = 
		$("#recpUpdateBack").attr("href","javascript:recpRead("+recpSeq+","+readType+")");

	}
	if ($.mobile.activePage.attr('id') == "myRecpListPage") {

		recpFooterSelector();
		myRecpSearch()
		var memData = getLoginMemData();
		if(memData.memType == 0)
		{
			$("#recpDialogBtn").hide();
		}
	}
	
});
function recpFamousList(data)
{
	$.ajax({
		type:"POST",
		data:data,
		url:"http://sebm.iptime.org:9124/appRecpList.do?pageType=2",
		success:function(data){
            console.log(data);
        	$("#popularRecpCont").html("");
            $.each(data, function(i, vo){
				$("#popularRecpCont").append("<a onclick='javascript:recpRead("+vo.recpSeq+",0)'>"
										 + " <img style='width: 33%; height: 110px; float: left'"
										 + "src='http://sebm.iptime.org:9124/"
										 + vo.recpImg 
										 +"'></a> ").trigger('create');
				
            });
     },
     error:function(request,status,error){
          alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
     }
	});	
}
function recpLikeList(data){
	$("#likeRecpCont").html("");
	$.ajax({
		type:"POST",
		data:data,
		url:"http://sebm.iptime.org:9124/appRecpList.do?pageType=3",
		success:function(data){
            console.log(data);
        	$("#likeRecpCont").html("");
            $.each(data, function(i, vo){
				$("#likeRecpCont").append("<a onclick='javascript:recpRead("+vo.recpSeq+",0)'>"
									  + " <img style='width: 33%; height: 110px; float: left'"
									  + "src='http://sebm.iptime.org:9124/"
									  + vo.recpImg 
									  +"'></a> ").trigger('create');
							
            });
     },
     error:function(request,status,error){
          alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
     }
  }); 
}
function recpCretProdctRead()
{
	if(recpProdct[0]!=null||recpProdct[0]!="")
	{
		formData = "recpProdct="+recpProdct;
		$.ajax({
			type:"POST",
			data:formData,
			url:"http://sebm.iptime.org:9124/appRecpProdctList.do",
			success:function(data){
				var resultPric = 0;//////////
				$("#recpListSize").val(data["recpListSize"]);
	            console.log(data);
	            $("#recpProdctList").html("");
	            $("#recpProdctList").append("<fieldset data-role='controlgroup' style='width:50%' class='ui-controlgroup ui-controlgroup-vertical ui-corner-all'>"
	            		+   "<div class='ui-controlgroup-controls '><div class='ui-checkbox'>"
						+   "<label for='recpProdctAllCheck' class='font-md ui-btn ui-corner-all ui-btn-inherit ui-btn-icon-left ui-checkbox-on ui-first-child ui-last-child'>전체 선택</label>"
						+   "<input type='checkbox' id='recpProdctAllCheck' onclick='javascript:recpProdctAllCheckFnc()'/>"
						+	"</div></div></fieldset><hr>").trigger('create') ;
	            $.each(data["appRecpProdctList"], function(i, vo){
					recpSeq = vo.recpSeq;
					recpImg = vo.recpImg;
					$("#recpProdctList").append("<fieldset data-role='controlgroup' style='padding-top:7px;width:30%' class='ui-controlgroup ui-controlgroup-vertical ui-corner-all'>"
											+   "<div class='ui-controlgroup-controls '><div class='ui-checkbox'>"
											+   "<label for='recpProdct"+vo.prodctSeq+"' class='font-md ui-btn ui-corner-all ui-btn-inherit ui-btn-icon-left ui-checkbox-on ui-first-child ui-last-child'>&nbsp;</label>"
											+   "<input type='checkbox' name='recpProdctCheck' id='recpProdct"+vo.prodctSeq+"' value='"+vo.prodctSeq+"' data-cacheval='true'>"
											+   "</div><div></fieldset>"
											+   "<div class = 'row'>"
											+   "<div class = 'col-xs-4'>"
											+   "<img src='http://sebm.iptime.org:9124/"+vo.mainImg+"' width='100%' height='100%'>"
											+   "</div>"
											+   "<div class = 'col-xs-8'>"
											+   "<div class = 'box'>"
											+   "<br>"
											+   "<font class = 'font-sm'>"+vo.prodctNme+"</font>"
											+   "<h3 class = 'font-md'>"+vo.selPric+"원</h3>"
											+   "</div></div></div><hr>").trigger('create') ;
					resultPric+=parseInt(vo.selPric);
	
		        });
				$("#recpProdctList").append("<a href='javascript:recpCretProdctDelete();' class='ui-btn clr-btn-light-green'>삭제</a>").trigger('create') ;
				$("#resultPricLi").html("");
				$("#resultPricLi").append("<b class='font-md' style='float:left;'>총 금액</b><b id='resultPric' class='font-md' style='float:right; color:red'>"+resultPric+"원</b>").trigger('create');
		     },
		     error:function(request,status,error){
		          alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		     }
		});
	}
}
function recpTitlSearchFn()
{
	var memData = getLoginMemData();
	var data = "memSeq="+memData.memSeq+"&memType="+memData.memType
	$.ajax({
		type:"POST",
		url:"http://sebm.iptime.org:9124/appRecpList.do?recpSechText="+recpSechText+"&pageType=4",
		data:data,
		success:function(data){
			$("#RecptTitlSearchList").html("");
			if(data.length==0)
			{
				recpSearchResltPop();
			}
			else
			{
				$.each(data, function(i, vo){
					$("#RecptTitlSearchList").append("<a onclick='javascript:recpRead("+vo.recpSeq+")'>"
							 +   "<img style='width:33%;height:110px;float:left' src='http://sebm.iptime.org:9124/"+vo.recpImg+"'></a>");
					});	
			}
	     },
	     error:function(request,status,error){
	          alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	     }
	});
}

function recpCretProdctDelete()
{
	
	var chked_length = $("input[name=recpProdctCheck]:checked").length; //체크된 박스 개수
	if (chked_length > 0 )
	{
		if (confirm("총 " + chked_length + "개의 항목을 다시 진열 중 상태로 변경하시겠습니까?") == true){    //확인
			recpProdct = new Array();
			$(":checkbox[name='recpProdctCheck'][data-cacheval='true']").each(function(pi,po){
				
				recpProdct.push(po.value);
					
			});
			if(recpProdct!="")
			{

				$("#recpProdctList").html("");
				recpCretProdctRead();
			}
			else if (recpProdct=="")
			{
				$("#recpProdctList").html("");
				$("#resultPricLi").html("");
			}
		}
		else{
			navigator.notification.alert("상품 상태 변경 요청이 취소되었습니다.",null,"알림","확인");
		}
	}
	else
	{
		navigator.notification.alert("삭제할 상품을 1개 이상 선택해주세요.",null,"알림","확인");
	}
}
//좋아요
function recpLikeProcessing(recpSeq,likeState, sender)
{

	var memData = getLoginMemData();
	var urlAddress;
	if(likeState==0)
	{
		urlAddress = "http://sebm.iptime.org:9124/appRecpLikeProcessing.do?likeState=0";
	}
	else if(likeState==1)
	{
		urlAddress = "http://sebm.iptime.org:9124/appRecpLikeProcessing.do?likeState=1"

	}

	var memData = getLoginMemData();
	$.ajax({
		type:"POST",
	    url:urlAddress,
	    data:{"recpSeq":recpSeq
	    	 ,"memSeq" :memData.memSeq
	    	 ,"memType":memData.memType},
	    success:function(data){
	    	 if(sender==0)
	         {
	              if(data.likeState==0)
	             {
	                  $("#likeMark"+recpSeq).attr("href","javascript:recpLikeProcessing("+recpSeq+",0,0)");
	                  $("#img"+recpSeq).attr("src","../img/noLove.png");
	             }
	              else if(data.likeState==1)
	              {
	                  $("#likeMark"+recpSeq).attr("href","javascript:recpLikeProcessing("+recpSeq+",1,0)");
	                  $("#img"+recpSeq).attr("src","../img/love.png");
	             }
	               $("#stat"+recpSeq).html("좋아요 " +data.likeCount+ " 댓글 "+data.replyCount);
		        var memData = getLoginMemData();
		        var data = "memSeq="+memData.memSeq+"&memType="+memData.memType
		        recpLikeList(data);
		        recpFamousList(data)
	           }
	           else if(sender==1)
	          {	              
	             $("#stat").html("좋아요 " + data.likeCount + " 댓글 "+ data.replyCount);
	             if(data.likeState==0)
	             {
	                    $("#likeMark").attr("href","javascript:recpLikeProcessing("+recpSeq+",0,1)");
	                    $("#likeImg").attr("src","../img/noLove.png");
	             }
	             else if(data.likeState==1)
	             {
	                    $("#likeMark").attr("href","javascript:recpLikeProcessing("+recpSeq+",1,1)");
	                    $("#likeImg").attr("src","../img/love.png");
	             }
	   
	             $("#statDiv").listview("refresh");
	          }

         	
//	    	$("#statDiv").listview("refresh");
	    },
	    error:function(request,status,error){
    		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	});
 
}


////상세보기
function recpRead(seq,readTypeNum)
{
	var memData = getLoginMemData();
	$.ajax({
		type:"POST",
		data:{"recpSeq":seq
			, "memSeq":memData.memSeq
			, "memType":memData.memType},
		url:"http://sebm.iptime.org:9124/appRecpRead.do",
		success:function(data){
			var vo = data["recpRead"];
			recpSeq = vo.recpSeq;
			recpTitl = vo.recpTitl;
			recpCnt = vo.recpCnt;
			recpImg = vo.recpImg;
			likeCount = vo.likeCount;
			likeState = vo.likeState;
			replyCount = vo.replyCount;
			memSeq = vo.memSeq;
			memType = vo.memType;
			readType = readTypeNum;
			replyListVar = data["recpReplyList"];
			recpProdctListVar = data["recpProdctList"];
	
			$.mobile.changePage("RecpRead.html");
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
}
////등록
function recpInsert()
{
	var memData = getLoginMemData();
	var formData;
	if(recpProdct=="")
	{
		recpProdct.push(0);
		formData = "recpTitl="+$("#recpTitl").val()+"&recpCnt="+$("#recpCnt").val()+"&recpProdct="+recpProdct+"&memSeq="+memData.memSeq+"&memType="+memData.memType;
	}
	else
	{
		formData = "recpTitl="+$("#recpTitl").val()+"&recpCnt="+$("#recpCnt").val()+"&recpProdct="+recpProdct+"&memSeq="+memData.memSeq+"&memType="+memData.memType;
	}
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appRecpInsert.do",
		success:function(data){
			recpImgUpload(1,data);
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
}

//상세보기 댓글
function recpReplyInsert(recpSeq)
{
	var memData = getLoginMemData();
	$.ajax({
		type:"POST",
		data:{"recpSeq":recpSeq
			, "replyCnt":$("#replyCnt").val()
			, "memSeq":memData.memSeq
			, "memType":memData.memType},
		url:"http://sebm.iptime.org:9124/appRecpReplyInsert.do",
		success:function(data){
			var memData = getLoginMemData();
			console.log(data);
			$("#replyTb").html("");
			$.each(data["recpReplyList"], function(i, vo){
	        	if(vo.memType==0)
	        	memNme = vo.empNme;
	        	else if(vo.memType==1)
	        	memNme = vo.custmrNme;
	        	
        		if(memData.memType==0||(memData.memType==vo.memType&&memData.memSeq==vo.memSeq))
    			{
    	        	$("#replyTb").append("<tr style='border-bottom:1px;'><td rowspan='2' class='font-md' style='width:20%'>"
    	        						+ memNme+"</td>"
    	        						+ "<td class='font-sm' style='word-wrap:break-word;width:75%'>" 
    	        						+ vo.replyCnt+"</td>" 
    	        						+ "<td rowspan='2' style='width:5%'><a href='javascript:recpReplyDelete("+vo.recpReplySeq+","+vo.recpSeq+")'><img src = '../img/delete.png'></a></td></tr>"
    	        						+ "<tr><td class='font-sm'>"
    	        						+ vo.dat+"</td></tr><tr><td colspan='3' style='height:10px;'><hr></td></tr>").trigger('create');
    			}
    			else
    			{
    				$("#replyTb").append("<tr style='border-bottom:1px;'><td rowspan='2' class='font-md' style='width:20%'>"
    					+ memNme+"</td>"
    					+ "<td class='font-sm' style='word-wrap:break-word;width:75%'>" 
    					+ vo.replyCnt+"</td>" 
    					+ "<td rowspan='2' style='width:5%'></td></tr>"
    					+ "<tr><td class='font-sm'>"
    					+ vo.dat+"</td></tr><tr><td colspan='3' style='height:10px;'><hr></td></tr>").trigger('create');	
    			}
		
	         });
	         countVo = data["recpRead"];
	         $("#stat").html("좋아요 " + countVo.likeCount + " 댓글 "+countVo.replyCount);
	         $("#replyCntDiv").html("");
	         $("#replyCntDiv").html("<input type='text' name='replyCnt' id='replyCnt' vdata-clear-btn='true' placeholder='댓글쓰기' style='width:80%' onload='javascript:replyCntFocus()'>").trigger('create');
			navigator.notification.alert("댓글이 등록되었습니다.",null,"알림","확인");
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
}
function replyClick(recpSeq)
{
		replyClickFlag=1;
		recpRead(recpSeq);
}
function recpProdctSearchRead(flag)
{/////
	recpTitl = $("#recpTitl").val();
	recpCnt = $("#recpCnt").val();
	recpProdctFlag = flag;
	$.mobile.changePage("RecpSearchProdct.html");
}
function recpProdctSearch()
{
	var data = {"recpSechText" : $("#recpSechText").val()}
	$.ajax({
		type:"POST",
		data: data,
		url:"http://sebm.iptime.org:9124/appRecpProdctSearch.do",
		success:function(data){
			console.log(data);
			$("#recpProdctList").html("");
			if(data.length==0)
			{
				recpSearchResltPop();
			}
			else
			{
				$.each(data, function(i, vo){
		        	$("#recpProdctList").append("<li style='padding-top: 0px; padding-bottom: 0px;'><hr>"
							+   "<div class='row' style='position: relative; padding-left: 5px; height: 110px; padding-right: 5px;'>"
							+   "<div  class='col-xs-4' style='display: table-cell; position: relative; width:110px; height:110px; vertical-align: top;'>"
							+	"<a href='javascript:recpProdctChoice("+vo.prodctSeq+")' style='color: #636566; text-decoration: none;'>"
							+	"<img src='http://sebm.iptime.org:9124/"+vo.mainImg+"' style='height:100%'/>"
							+	"</a></div>"+vo.prodctSeq
							+	"<div class='col-xs-6' style='display: table-cell; padding: 4px 0 0 15px; vertical-align: top;'>"
							+	"<a href='MarketProdctRead.html' style='color: #636566; text-decoration: none;'>"
							+	"<div>"
							+	"<div style='display: -webkit-box; overflow: hidden; height: auto; padding-right: 74px; line-height: 20px; color: #666; -webkit-line-clamp: 2; -webkit-box-orient: vertical; word-break: break-all; word-wrap: break-word;'>"
							+	"<span class='font-sm'>"+vo.prodctNme+"</span>"
							+	"</div>"
							+	"<div style='margin: 0 0 -4px; line-height: 27px; color: #262f33;'>"
							+	"<em class='font-sm' style='font-style: normal; font-family: emm_bol; font-weight: bold; letter-spacing: -1px; color: #262f33;'>"+vo.selPric+"원</em>"
							+	"</div></div></a></div>"
							+	"<div class='col-xs-2' style='position: absolute; top: 0; right: 5px;'>"
							+	"<a class='ui-btn' style='padding: 0; width: 63px; height: 53px; background-position: 0 -400px;'>"
							+   "<i><img src='../img/cart.png' width='100%' height='100%'></i></a></div></div></li>").trigger('create');

				});
			}
	         $.mobile.changePage("RecpSearchProdct.html");
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
	
}
function recpProdctChoice(prodctSeq)
{
	if(recpProdct[0] == "")
	{
		recpProdct = new Array();
	}
	recpProdct.push(prodctSeq);
	if(recpProdctFlag==0)
	{
		$.mobile.changePage("RecpCret.html");
	}
	if(recpProdctFlag==1)
	{
		$.mobile.changePage("RecpUpdate.html");
	}
}

function recpProdctAllCheckFnc() 
{
    //클릭되었으면
    if($("#recpProdctAllCheck").prop("checked")){
    	//input태그의 name이 cartCheck인 태그들을 찾아서 checked옵션을 true로 정의
    	$("input[name=recpProdctCheck]").prop("checked",true).checkboxradio("refresh");
    	$("input[name=recpProdctCheck]").attr("data-cacheval","false").checkboxradio("refresh");;
    }
    //클릭이 안되있으면
    else{
    	//input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
    	$("input[name=recpProdctCheck]").prop("checked",false).checkboxradio("refresh");
    	$("input[name=recpProdctCheck]").attr("data-cacheval","true").checkboxradio("refresh");;
    }
  
}
function recpImgUpload(flag,paramSeq)
{
	var memData = getLoginMemData();
	$("#memSeq").val(memData.memSeq)
	$("#memType").val(memData.memType)
	var imgFrm = new FormData(document.getElementById('recpFileFrm'));
	var url = "";
	var uploadType = 0;
	if(flag == 0)
	{
		url = "http://sebm.iptime.org:9124/appRecpFileUpload.do?pageType=0";

	}
	else if(flag == 1)
	{
		url = "http://sebm.iptime.org:9124/appRecpFileUpload.do?pageType=1&recpSeq="+paramSeq+"&tempNum="+$("#tempNum").val();

	}
    $.ajax({
		url: url,
		data: imgFrm,
		processData: false,
		contentType: false,
		type: 'POST',
		success:function(data){
			if(flag==0)
			{
				$("#recpImg").attr("src","http://sebm.iptime.org:9124/"+data["imgPath"]);
				$("#tempNum").val(data["num"]);
				sessionStorage.setItem('recpImg', data["imgPath"]);
				sessionStorage.setItem('tempNum', data["num"]);
			}
			else
			{
				recpRead(paramSeq);
			}
			
		},
		error:function(xhr,status,error)
		{ //ajax 오류인경우  
			alert("$");
			alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
		}
    });
}
function recpTitlSearch()
{
	
	recpSechText = $("#recpSechText").val();
	if($("#pageType").val() == 0)
	{
		$.mobile.changePage("RecptTitlSearch.html");
	}
	else if($("#pageType").val() == 1)
	{	
		$.mobile.changePage(
		  window.location.href,
		  {
		    allowSamePageTransition : true,
		    transition              : document.location.href,
		    showLoadMsg             : false,
		    reloadPage              : true
		  }
		);
	}
}
function recpDelete()
{
	
	$.ajax({
		type:"POST",
		data:{"recpSeq":recpSeq},
		url:"http://sebm.iptime.org:9124/appRecpDelete.do",
		success:function(data){
			$.mobile.changePage("RecpList.html");
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
}
function recpUpdateRead()
{
	var memData = getLoginMemData();
	var data = "recpSeq="+recpSeq+"&memSeq="+memData.memSeq+"&memType="+memData.memType;
	$.ajax({
		type:"POST",
		data:data,
		url:"http://sebm.iptime.org:9124/appRecpRead.do",
		success:function(data){
			var vo = data["recpRead"];
			recpSeq = vo.recpSeq;
			recpTitl = vo.recpTitl;
			recpCnt = vo.recpCnt;
			recpImg = vo.recpImg;
			sessionStorage.setItem('recpImg', vo.recpImg);
			sessionStorage.setItem('recpSeq', vo.recpSeq);
			likeCount = vo.likeCount;
			likeState = vo.likeCount;
			replyCount = vo.replyCount;
			memSeq = vo.memSeq;
			recpProdct = new Array();
			recpProdctListVar = data["recpProdctList"];
			if(recpProdctListVar!="")
			{
				$.each(recpProdctListVar, function(i, vo){
					recpProdct.push(vo.prodctSeq);
				});
			}
			else
			{
				recpProdct.push(0);
			}
			
			$.mobile.changePage("RecpUpdate.html");
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
}

function recpUpdate()
{
	var formData;
	console.log(recpProdct+"############");
	var memData = getLoginMemData();
	if(recpProdct[0]==""||recpProdct[0]==null)
	{
		
		
		formData = "recpSeq="+$("#recpSeq").val()+"&recpTitl="+$("#recpTitl").val()+"&recpCnt="+$("#recpCnt").val()+"&recpProdct="+0+"&memSeq="+memData.memSeq+"&memType="+memData.memType;
	}
	else
	{
		formData = "recpSeq="+$("#recpSeq").val()+"&recpTitl="+$("#recpTitl").val()+"&recpCnt="+$("#recpCnt").val()+"&recpProdct="+recpProdct+"&memSeq="+memData.memSeq+"&memType="+memData.memType;
	}
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appRecpUpdate.do",
		success:function(data){
			if(recpImg != $("#recpImg").attr("src"))
			{
				recpImgUpload(1,$("#recpSeq").val());
			}
			else
			{
				$.mobile.changePage("RecpList.html");
			}
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
}
function myRecpSearch()
{
	var memData = getLoginMemData();
	formData = "recpSechText="+$("#recpSechText").val()+"&pageType="+5+"&memSeq="+memData.memSeq+"&memType="+memData.memType;
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appRecpList.do",
		success:function(data){
			$("#myRecpList").html("");
            $.each(data, function(i, vo){
				$("#myRecpList").append("<a onclick='javascript:recpRead("+vo.recpSeq+",1)'>"
											 +   "<img style='width:33%;height:110px;float:left' src='http://sebm.iptime.org:9124/"+vo.recpImg+"'></a>").trigger('create');;
	        });
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}
function recpProdctCartInsert()
{
	var chked_length = $("input[name=recpProdctCheck]:checked").length; //체크된 박스 개수
	var chkedVar = new Array();
	if (chked_length > 0 )
	{
		if (confirm("총 " + chked_length + "개의 항목을 장바구니에 추가하시겠습니까?") == true){    //확인
			
			$(":checkbox[name='recpProdctCheck'][data-cacheval='false']").each(function(pi,po){
				
				
				chkedVar.push(po.value);
				
					
			});
			if(chkedVar!="")
			{
				var memData = getLoginMemData();
				formData = "chkedVar="+chkedVar+"&memSeq="+memData.memSeq+"&memType="+memData.memType;
				$.ajax({
					type:"POST",
					data:formData,
					url:"http://sebm.iptime.org:9124/appRecpProdctCartInsert.do",
					success:function(data){
						navigator.notification.alert("장바구니에 상품이 추가되었습니다.",null,"알림","확인");
					},
					error:function(request,status,error){
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
				});
			}
		}
		else{
			navigator.notification.alert("장바구니 상품 추가가 취소되었습니다.",null,"알림","확인");
		}
	}
	else
	{
		navigator.notification.alert("장바구니에 추가할 상품을 1개 이상 선택해주세요.",null,"알림","확인");
	}
}
function recpProdctPriceCheck()
{
			
	var resultPric = 0 ;
	
	$(":checkbox[name='recpProdctCheck'][data-cacheval='false']").each(function(pi,po){
		
		
		resultPric+=parseInt($("#selPric"+po.value).html().substring(0,$("#selPric"+po.value).html().length-1));
		
			
	});
	$("#resultPric").html(resultPric+"원");
}
//로그인 데이터 추출 함수
function getLoginMemData()
{
	var loginMemInfo;
	if(localStorage.getItem('empSeq')!= null && localStorage.getItem('empSeq')!= "")
	{
		var seq = localStorage.getItem('empSeq');
		loginMemInfo = new memInfo(seq,0);
	}
	else if(sessionStorage.getItem('empSeq')!= null && sessionStorage.getItem('empSeq')!= "")
	{
		var seq = sessionStorage.getItem('empSeq');
		loginMemInfo = new memInfo(seq,0);
	}
	else if(localStorage.getItem('custmrSeq')!= null && localStorage.getItem('custmrSeq')!= "")
	{
		var seq = localStorage.getItem('custmrSeq');
		loginMemInfo = new memInfo(seq,1);
		
	}
	else if(sessionStorage.getItem('custmrSeq')!= null && sessionStorage.getItem('custmrSeq')!= "")
	{
		var seq = sessionStorage.getItem('custmrSeq');
		loginMemInfo = new memInfo(seq,1);
		
	}
	return loginMemInfo;
}
function recpReplyDelete(recpReplySeq,recpSeq)
{
	$.ajax({
		type:"POST",
		data:{"recpReplySeq":recpReplySeq,"recpSeq":recpSeq},
		url:"http://sebm.iptime.org:9124/appRecpReplyDelete.do",
		success:function(data){
			
			var memData = getLoginMemData();
			console.log(data);
			$("#replyTb").html("");
			$.each(data["recpReplyList"], function(i, vo){
				if(vo.memType==0)
				memNme = vo.empNme;
				else if(vo.memType==1)
				memNme = vo.custmrNme;
				
				if(memData.memType==0||(memData.memType==vo.memType&&memData.memSeq==vo.memSeq))
				{
			    	$("#replyTb").append("<tr style='border-bottom:1px;'><td rowspan='2' class='font-md' style='width:20%'>"
			    						+ memNme+"</td>"
			    						+ "<td class='font-sm' style='word-wrap:break-word;width:75%'>" 
			    						+ vo.replyCnt+"</td>" 
			    						+ "<td rowspan='2' style='width:5%'><a href='javascript:recpReplyDelete("+vo.recpReplySeq+","+vo.recpSeq+")'><img src = '../img/delete.png'></a></td></tr>"
			    						+ "<tr><td class='font-sm'>"
			    						+ vo.dat+"</td></tr><tr><td colspan='3' style='height:10px;'><hr></td></tr>").trigger('create');
				}
				else
				{
					$("#replyTb").append("<tr style='border-bottom:1px;'><td rowspan='2' class='font-md' style='width:20%'>"
						+ memNme+"</td>"
						+ "<td class='font-sm' style='word-wrap:break-word;width:75%'>" 
						+ vo.replyCnt+"</td>" 
						+ "<td rowspan='2' style='width:5%'></td></tr>"
						+ "<tr><td class='font-sm'>"
						+ vo.dat+"</td></tr><tr><td colspan='3' style='height:10px;'><hr></td></tr>").trigger('create');	
				}

			});
			countVo = data["recpRead"];
			$("#stat").html("좋아요 " + countVo.likeCount + " 댓글 "+countVo.replyCount);
			$("#replyCntDiv").html("");
			$("#replyCntDiv").html("<input type='text' name='replyCnt' id='replyCnt' vdata-clear-btn='true' placeholder='댓글쓰기' style='width:80%' onload='javascript:replyCntFocus()'>").trigger('create');

			navigator.notification.alert("댓글이 삭제되었습니다.",null,"알림","확인");
			
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}


// 사용자 정보를 담는 객체
function memInfo(seq, type) {
    this.memSeq = seq;
    this.memType = type;
}
function replyCntFocus(num)/////////////////////////
{
	var offset = $("#replyCnt").offset();
	if(replyClickFlag==1)
	{
	    $('html, body').animate({scrollTop : offset.top-200}, 400);
		replyClickFlag==0;
	}
	if(num==1)
	{
	    $('html, body').animate({scrollTop : offset.top-200}, 400);
	}
	
}
function recpSearchResltPop()
{
	navigator.notification.alert("검색 결과가 없습니다!",null,"알림","확인");
}
function recpFooterSelector()
{
	var memData = getLoginMemData();
	if(memData.memType==0)
	{
		$("#recpFooter").html("<div class='row' style='text-align:center'>"
							+"	<div class='col-xs' style='width:20%'>"
							+"	<div class='box'>"
							+"			<a href='MainEmpFrame.html' class='ui-btn ui-bottom-sheet-link nd2-btn-icon-block waves-effect waves-button'><i class='zmdi zmdi-home'></i>홈</a>"
							+"</div>"
							+"	</div>"
							+"	<div class='col-xs' style='width:20%'>"
							+"		<div class='box'>"
							+"			<a href='MarketEmpDebecChoiceMain.html' class='ui-btn ui-bottom-sheet-link nd2-btn-icon-block waves-effect waves-button'><i class='zmdi zmdi-local-store'></i>마켓</a>"
							+"		</div>"
							+"	</div>"
							+"	<div class='col-xs' style='width:20%'>"
							+"		<div class='box'>"
							+"			<a href='RecpList.html' class='ui-btn ui-bottom-sheet-link nd2-btn-icon-block waves-effect waves-button'><i class='zmdi zmdi-local-dining'></i>레시피</a>"
							+"		</div>"
							+"	</div>"
							+"	<div class='col-xs' style='width:20%'>"
							+"		<div class='box'>"
							+"			<a href='CommunityEmpList.html' class='ui-btn ui-bottom-sheet-link nd2-btn-icon-block waves-effect waves-button'><i class='zmdi zmdi-accounts'></i>담소방</a>"
							+"		</div>"
							+"	</div>"
							+"	<div class='col-xs' style='width:20%'>"
							+"	    <div class='box'>"
							+"	    	<a href='MyPageEmpMain.html' class='ui-btn ui-bottom-sheet-link nd2-btn-icon-block waves-effect waves-button'><i class='zmdi zmdi-account-box'></i>내정보</a>"
							+"	    </div>"
							+"	</div>"
							+"</div>");
	}
	else
	{
		$("#recpFooter").html("<div class='row' style='text-align:center'>"
							+"	<div class='col-xs' style='width:20%'>"
							+"	<div class='box'>"
							+"			<a href='MainCusFrame.html' class='ui-btn ui-bottom-sheet-link nd2-btn-icon-block waves-effect waves-button'><i class='zmdi zmdi-home'></i>홈</a>"
							+"</div>"
							+"	</div>"
							+"	<div class='col-xs' style='width:20%'>"
							+"		<div class='box'>"
							+"			<a href='MarketCusDebecChoiceMain.html' class='ui-btn ui-bottom-sheet-link nd2-btn-icon-block waves-effect waves-button'><i class='zmdi zmdi-local-store'></i>마켓</a>"
							+"		</div>"
							+"	</div>"
							+"	<div class='col-xs' style='width:20%'>"
							+"		<div class='box'>"
							+"			<a href='RecpList.html' class='ui-btn ui-bottom-sheet-link nd2-btn-icon-block waves-effect waves-button'><i class='zmdi zmdi-local-dining'></i>레시피</a>"
							+"		</div>"
							+"	</div>"
							+"	<div class='col-xs' style='width:20%'>"
							+"		<div class='box'>"
							+"			<a href='CommunityList.html' class='ui-btn ui-bottom-sheet-link nd2-btn-icon-block waves-effect waves-button'><i class='zmdi zmdi-accounts'></i>담소방</a>"
							+"		</div>"
							+"	</div>"
							+"	<div class='col-xs' style='width:20%'>"
							+"	    <div class='box'>"
							+"	    	<a href='MyPageCusMain.html' class='ui-btn ui-bottom-sheet-link nd2-btn-icon-block waves-effect waves-button'><i class='zmdi zmdi-account-box'></i>내정보</a>"
							+"	    </div>"
							+"	</div>"
							+"</div>");
	}
}