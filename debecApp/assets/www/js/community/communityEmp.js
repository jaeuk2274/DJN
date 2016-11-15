	$(document).on('pageshow', function(event, data){ //"insertPage" 라는 page가 로딩됐을 때 실행이 되는 함수
		var formData = "empSeq="+empSeq;

	   // 담소방 리스트
		if ($.mobile.activePage.attr('id') === "communityEmpListPage") {  
			$("#communityEmpList").empty();
			$.ajax({
				type:"POST",
				data:formData,
				url:"http://sebm.iptime.org:9124/appEmpCommunityList.do",
				success:function(data){	            
					$.each(data, function(i, vo){
					boardSeq = vo.boardSeq;
					boardTitl = vo.boardTitl;
					boardImg = vo.boardImg;
					boardIntrodun = vo.boardIntrodun;
					boardEntryCount = vo.boardEntryCount;	                
					$("#communityEmpList").append("<div class='nd2-card card-media-right card-media-medium' style='background-color:#FFFFFF'>"
						+ "<ul data-role='listview' class='ui-listview'>"
						+ "<li name='"+boardSeq+"' class='ui-li-has-thumb ui-first-child ui-last-child'>"
						+ "<a href='javascript:communityEmpReadOne("+boardSeq+")' class='ui-btn waves-effect waves-button waves-effect waves-button waves-effect waves-button'>"
						+ "<img src='../img/communiOpen.png' class='ui-thumbnail ui-thumbnail-circular' style='width: 110px; left: 10px; max-height: 20em; max-width: 20em;'/>"
						+ "<h2 style='height:30px; margin-left:30px;'>"+boardTitl+"</h2>"
						+ "<p style='margin-top: 3px; margin-left: 30px; margin-bottom: 0px;'>"+boardIntrodun+"</p>"
						+ "</a>"
						+ "<div class='box' style='padding-bottom:0px; padding-left:110px;'>"                  
						+ "<img src='../img/people.png' style='width: 20px; padding-left:20px; padding-right:0px; display:inline-block;'>"
						+ "<font class='font-sm'>"+boardEntryCount+"</font>"
						+ "</div></li></ul></div>");            
					});
					$("#communityEmpList").listview("refresh");
				},
				error:function(request,status,error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});	   
		}
	   // 담소방 상세보기
		if ($.mobile.activePage.attr('id') === "communityEmpReadPage") {
			if(empSeq!=null){
				var boardSeq =sessionStorage.getItem('boardSeq');
				communityEmpPanel(boardSeq); //오른쪽 패널
				$("#communityReadList").empty();
				$('#boardSeq').val(boardSeq);
				var formData = $("#communityForm").serialize();
				$.ajax({
					type:"POST",
					data:formData,
					url:"http://sebm.iptime.org:9124/appCommtyRead.do",
					success:function(data){
						$.each(data, function(i, vo){
						boardTitl = vo.boardTitl;
						talk = vo.talk;
						talkRegstrtnDat = vo.talkRegstrtnDat;
						custmrNme = vo.custmrNme;			            
						$('#boardTitl').text(boardTitl);
						$("#communityReadList").append("<ul data-role='listview' data-icon='false' style='margin-top: 0px; margin-bottom: 0px;' class='ui-listview'>"
							+ "<li class='ui-first-child ui-last-child'>"
							+ "<a href='#' class='ui-btn waves-effect waves-button'>"               
							+ "<font class='font-md'>"+custmrNme+"</font><font class='font-sm' color='#8C8C8C' style='margin-left:5px'>"+talkRegstrtnDat+"</font>"
							+ "<p>"+talk+"</p>"
							+ "</a></li></ul>"
							+ "<hr style='width:100%; margin-top: 0px; margin-bottom: 2px;'>");
						});
						$("#communityReadList").listview("refresh");
						document.body.scrollTop = document.body.scrollHeight;
					},
					error:function(request,status,error){
						alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
				});  
			};	   
		}
	});

// 관리자 담소방 상세보기
function communityEmpReadOne(boardSeq){
	sessionStorage.setItem('boardSeq', boardSeq);
	$.mobile.changePage("CommunityEmpRead.html");
}
//관리자 담소방 상세보기 오른쪽 패널
function communityEmpPanel(boardSeq){
	$('#boardSeq').val(boardSeq);
	var formData = $("#communityForm").serialize();
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appComntyReadPanel.do",
		success:function(data){   
			$.each(data, function(i, vo){                   
				boardSeq = vo.boardSeq;
				titl = vo.boardTitl;
				boardIntrodun = vo.boardIntrodun;
				custmrNme = vo.custmrNme;
				custmrId = vo.custmrId;      
				$('#titl').text(titl);
				$('#boardIntrodun').text(boardIntrodun);             
				$("#entryHotList").append("<li style='padding-top: 0px; margin-bottom: 0px;'><h6>"+custmrNme+"("+custmrId+")</h6>"
						+"<hr style='width:120%; margin-top: 0px; margin-bottom: 0px;'></li>");   
				});
				$("#entryHotList").listview("refresh");  
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});   
}
//관리자 담소방 검색
function communityEmpSearch(searchText){
    $.mobile.changePage("CommunitySearchList.html");   
    $("#communitySechList").empty();
    $.ajax({
       type:"POST",
       url:"http://sebm.iptime.org:9124/appEmpCommunityList.do",
       data:{"searchText":searchText},
       success:function(data){         
          $.each(data, function(i, vo){
				boardSeq = vo.boardSeq;
				boardTitl = vo.boardTitl;
				boardImg = vo.boardImg;
				boardIntrodun = vo.boardIntrodun;
				boardEntryCount = vo.boardEntryCount;
				$('#boardSeq').val(boardSeq);   	            
				$("#communitySechList").append("<div class='nd2-card card-media-right card-media-medium' style='background-color:#FFFFFF'>"
					+ "<ul data-role='listview' class='ui-listview'>"
					+ "<li name='"+boardSeq+"' class='ui-li-has-thumb ui-first-child ui-last-child'>"
					+ "<a href='javascript:communityEmpReadOne("+boardSeq+")' class='ui-btn waves-effect waves-button waves-effect waves-button waves-effect waves-button'>"
					+ "<img src='../img/communiOpen.png' class='ui-thumbnail ui-thumbnail-circular' style='width: 110px; left: 10px; max-height: 20em; max-width: 20em;'/>"
					+ "<h2 style='height:30px; margin-left:30px;'>"+boardTitl+"</h2>"
					+ "<p style='margin-top: 3px; margin-left: 30px; margin-bottom: 0px;'>"+boardIntrodun+"</p>"
					+ "</a>"
					+ "<div class='box' style='padding-bottom:0px; padding-left:110px;'>"                  
					+ "<img src='../img/people.png' style='width: 20px; padding-left:20px; padding-right:0px; display:inline-block;'>"
					+ "<font class='font-sm'>"+boardEntryCount+"</font>"
					+ "</div></li></ul></div>");    
          });
//          $("#communitySechList").listview("refresh");
       },
       error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
       }
    });
 };


