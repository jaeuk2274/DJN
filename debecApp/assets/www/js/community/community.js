var url;
var boardSeq;
var boardTitl;
var boardImg;
var boardIntrodun;
var boardPw;
var boardEntryCount;
var hotlistBoardSeq; 

//리스트 모음
	$(document).on('pageshow', function(event, data){ //"insertPage" 라는 page가 로딩됐을 때 실행이 되는 함수
	   var formData = "custmrSeq="+custmrSeq; 
	   // 담소방 리스트
	   if ($.mobile.activePage.attr('id') === "communityListPage") {   	      
	      $("#communityList").empty();
	      $.ajax({
	         type:"POST",
	         data:formData,
	         url:"http://sebm.iptime.org:9124/appCommunityList.do",
	         success:function(data){
	            
	            $.each(data, function(i, vo){
	               boardSeq = vo.boardSeq;
	               boardTitl = vo.boardTitl;
	               boardImg = vo.boardImg;
	                boardIntrodun = vo.boardIntrodun;
	                boardPw = vo.boardPw;
	                boardEntryCount = vo.boardEntryCount;
	                hotlistBoardSeq = vo.hotlistBoardSeq;
	                
	               // 공개 담소방
	               if(boardPw==0){      
	                  $("#communityList").append("<div class='nd2-card card-media-right card-media-medium' style='background-color:#FFFFFF'>"
	                        + "<ul data-role='listview' class='ui-listview'>"
	                        + "<li name='"+boardSeq+"' class='ui-li-has-thumb ui-first-child ui-last-child'>"
	                        + "<a href='javascript:communityReadOne("+boardSeq+")' class='ui-btn waves-effect waves-button waves-effect waves-button waves-effect waves-button'>"
	                        + "<img src='../img/communiOpen.png' class='ui-thumbnail ui-thumbnail-circular' style='width:120px; height:105px; left:10px; max-height: 20em;  max-width:20em;'/>"
	                        + "<h2 style='height:20%; margin-left:30px;'>"+boardTitl+"</h2>"
	                        + "<p style='margin-top: 3px; margin-left: 30px; margin-bottom: 0px;'>"+boardIntrodun+"</p>"
	                        + "</a>"
	                        + "<div class='box' style='padding-bottom:0px; padding-left:110px;'>"
	                        +"   <input type='hidden' name='loveState"+boardSeq+"'>"
	                        +"<div style='display:inline-block' id='noLove"+boardSeq+"'>"
	                        +"<a href='javascript:clickLike("+boardSeq+")' class='ui-btn ui-btn-inline'>"
	                        +"<img name='img"+boardSeq+"' src='../img/noLove.png' style='width: 25px;'>"
	                        +"</a></div>"   
	                        +"<div name='lockState"+boardSeq+"' style='display:inline-block;'></div>"
	                        + "<img src='../img/people.png' style='width: 20px; padding-left:6px; padding-right:0px; display:inline-block;'>"
	                        + "<font class='font-sm'>"+boardEntryCount+"</font>"
	                        + "</div></li></ul></div>");
	               }
	               // 비공개 담소방
	               else{
	                  $("#communityList").append("<div class='nd2-card card-media-right card-media-medium' style='background-color:#FFFFFF'>"
	                        + "<ul data-role='listview' class='ui-listview'>"
	                        + "<li name='"+boardSeq+"' class='ui-li-has-thumb ui-first-child ui-last-child'>"
	                        + "<a onclick='javascript:lockPopUp("+boardSeq+")' class='ui-btn ui-btn-right'  data-rel='popup' data-position-to='window' data-role='button' data-inline='true' data-transition='pop' class='ui-btn'>"
	                        + "<img src='../img/communiSecret.png' class='ui-thumbnail ui-thumbnail-circular' style='width: 120px; height:105px; left: 10px; max-height: 20em; max-width: 20em;'/>"
	                        + "<h2 style='height:20%; margin-left:30px;'>"+boardTitl+"</h2>"
	                        + "<p style='margin-top: 3px; margin-left: 30px; margin-bottom: 0px;'>"+boardIntrodun+"</p>"
	                        + "</a>"
	                        + "<div class='box' style='padding-bottom:0px; padding-left:110px;'>"
	                        + "<input type='hidden' name='loveState"+boardSeq+"'>"
	                        + "<div style='display:inline-block' id='noLove"+boardSeq+"'>"
	                        + "<a href='javascript:clickLike("+boardSeq+")' class='ui-btn ui-btn-inline'>"
	                        + "<img name='img"+boardSeq+"' src='../img/noLove.png' style='width: 25px;'>"
	                        + "</a></div>"   
	                        + "<div name='lockState"+boardSeq+"' style='display:inline-block;'></div>"
	                        + "<img src='../img/people.png' style='width: 20px; padding-left:6px; padding-right:0px; display:inline-block;'>"
	                        + "<font class='font-sm'>"+boardEntryCount+"</font>"
	                        + "</div></li></ul></div>");
	
	                  $("div[name='lockState"+boardSeq+"']").append("<img src='../img/security.png' style='width:25px;'>");
	               }
	               
	               // 즐겨찾기가 되어있지 않은 담소방이라면               
	               if(hotlistBoardSeq==0){
	                   $("input[name='loveState"+boardSeq+"']").val(0); 
	               }
	               else {
	                  $("img[name='img"+boardSeq+"']").attr("src", "../img/love.png");
	                   $("input[name='loveState"+boardSeq+"']").val(1);
	               }               
	            });
	            $("#communityList").listview("refresh");
	         },
	         error:function(request,status,error){
	              alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	         }
	      });
	   };
	   
	   // 즐겨찾는 담소방 리스트
	   if ($.mobile.activePage.attr('id') === "communityListPage") {   
	      $("#communityHotList").empty();
	      $.ajax({
	         type:"POST",
	         data:formData,
	         url:"http://sebm.iptime.org:9124/appCommunityHotList.do",
	         success:function(data){
	            
	            $.each(data, function(i, vo){                       
	               boardSeq = vo.boardSeq;
	               boardTitl = vo.boardTitl;
	               boardImg = vo.boardImg;
	                boardIntrodun = vo.boardIntrodun;
	                boardPw = vo.boardPw;
	                boardEntryCount = vo.boardEntryCount;
	                               
	               // 공개 담소방
	               if(boardPw==0){      
	                  $("#communityHotList").append("<div class='nd2-card card-media-right card-media-medium' style='background-color:#FFFFFF'>"
	                        + "<ul data-role='listview' class='ui-listview'>"
	                        + "<li name='"+boardSeq+"' class='ui-li-has-thumb ui-first-child ui-last-child'>"
	                        + "<a href='javascript:communityReadOne("+boardSeq+")' class='ui-btn waves-effect waves-button waves-effect waves-button waves-effect waves-button'>"
	                        + "<img src='../img/communiOpen.png' class='ui-thumbnail ui-thumbnail-circular' style='width: 120px; height:105px; left: 10px; max-height: 20em; max-width: 20em;'/>"
	                        + "<h2 style='height:20%; margin-left:30px;'>"+boardTitl+"</h2>"
	                        + "<p style='margin-top: 3px; margin-left: 30px; margin-bottom: 0px;'>"+boardIntrodun+"</p>"
	                        + "</a>"
	                        + "<div class='box' style='padding-bottom:0px; padding-left:110px;'>"
	                        + "<input type='hidden' name='loveState"+boardSeq+"'>"
	                        + "<div style='display:inline-block' id='noLove"+boardSeq+"'>"
	                        + "<a href='javascript:clickLike("+boardSeq+")' class='ui-btn ui-btn-inline'>"
	                        + "<img name='img"+boardSeq+"' src='../img/love.png' style='width: 25px;'>"
	                        + "</a></div>"   
	                        + "<div name='lockState"+boardSeq+"' style='display:inline-block;'></div>"
	                        + "<img src='../img/people.png' style='width: 20px; padding-left:6px; padding-right:0px; display:inline-block;'>"
	                        + "<font class='font-sm'>"+boardEntryCount+"</font>"
	                        + "</div></li></ul></div>");
	               }
	               // 비공개 담소방
	               else{
	                  $("#communityHotList").append("<div class='nd2-card card-media-right card-media-medium' style='background-color:#FFFFFF'>"
	                        + "<ul data-role='listview' class='ui-listview'>"
	                        + "<li name='"+boardSeq+"' class='ui-li-has-thumb ui-first-child ui-last-child'>"
	                        + "<a onclick='javascript:lockPopUp("+boardSeq+")' class='ui-btn ui-btn-right'  data-rel='popup' data-position-to='window' data-role='button' data-inline='true' data-transition='pop' class='ui-btn'>"
	                        + "<img src='../img/communiSecret.png' class='ui-thumbnail ui-thumbnail-circular' style='width: 120px; height:105px; left: 10px; max-height: 20em; max-width: 20em;'/>"
	                        + "<h2 style='height:20%; margin-left:30px;'>"+boardTitl+"</h2>"
	                        + "<p style='margin-top: 3px; margin-left: 30px; margin-bottom: 0px;'>"+boardIntrodun+"</p>"
	                        + "</a>"
	                        + "<div class='box' style='padding-bottom:0px; padding-left:110px;'>"
	                        + "<input type='hidden' name='loveState"+boardSeq+"'>"
	                        + "<div style='display:inline-block' id='noLove"+boardSeq+"'>"
	                        + "<a href='javascript:clickLike("+boardSeq+")' class='ui-btn ui-btn-inline'>"
	                        + "<img name='img"+boardSeq+"' src='../img/love.png' style='width: 25px;'>"
	                        + "</a></div>"   
	                        + "<div name='lockState"+boardSeq+"' style='display:inline-block;'></div>"
	                        + "<img src='../img/people.png' style='width: 20px; padding-left:6px; padding-right:0px; display:inline-block;'>"
	                        + "<font class='font-sm'>"+boardEntryCount+"</font>"
	                        + "</div></li></ul></div>");
	               }               
	            });
	            $("#communityHotList").listview("refresh");
	         },
	         error:function(request,status,error){
	              alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	         }
	      });
	   };
	   
	   // 나의 담소방 관리 리스트
	   if ($.mobile.activePage.attr('id') === "communityMyListPage") {   
	      $("#communityMyList").empty();
	      $.ajax({
	         type:"POST",
	         data:formData,
	         url:"http://sebm.iptime.org:9124/appCommunityMyList.do",
	         success:function(data){
	            
	            $.each(data, function(i, vo){      
	               boardSeq = vo.boardSeq;
	               boardTitl = vo.boardTitl;
	               boardImg = vo.boardImg;
	                boardIntrodun = vo.boardIntrodun;
	                boardPw = vo.boardPw;
	                boardEntryCount = vo.boardEntryCount;
	                
	               $("#communityMyList").append("<div class='nd2-card card-media-right card-media-medium' style='background-color:#FFFFFF'>"
	                  + "<ul data-role='listview' class='ui-listview'>"
	                  + "<li id='"+boardSeq+"' class='ui-li-has-thumb ui-first-child ui-last-child'>"
	                  + "<a href='javascript:communityReadOne("+boardSeq+")' class='ui-btn waves-effect waves-button waves-effect waves-button waves-effect waves-button'>"
	                  + "<img src='../img/communiMain.jpg' class='ui-thumbnail ui-thumbnail-circular' style='width: 120px; height:105px; left: 10px; max-height: 20em; max-width: 20em;'/>"
	                  + "<h2 style='height:20%; margin-left:30px;'>"+boardTitl+"</h2>"
	                  + "<p style='margin-top: 3px; margin-left: 30px; margin-bottom: 0px;'>"+boardIntrodun+"</p>"
	                  + "</a>"
	                  + "<div class='box' style='padding-bottom:0px; padding-left:110px;'>"
	                  + "<a href='javascript:comntyMyUpdateRead("+boardSeq+")' class='ui-btn ui-btn-inline waves-effect waves-button waves-effect waves-button' style='padding-right: 8px;'>"
	                  + "<img src='http://image.fileslink.com/36e672d3e14324/set.png' style='width: 18px;'>"
	                  + "</a>"
	                  + "<img src='http://image.fileslink.com/36b82de624929b/lock.png' style='width:20px'>"
	                  + "<img src='http://image.fileslink.com/36b82f0b730287/person.png' style='width: 20px; padding-left:6px; padding-right:0px;'>"
	                  + "<font class='font-sm'>"+boardEntryCount+"</font>"
	                  + "</div></li></ul></div>");               
	            });
	            $("#appCommunityMyList").listview("refresh");
	         },
	         error:function(request,status,error){
	              alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	         }
	      });
	   };
	   
	   // 담소방 상세보기
	   if ($.mobile.activePage.attr('id') === "communityReadPage") {   
			   var boardSeq =sessionStorage.getItem('boardSeq');
			   $('#boardSeq').val(boardSeq);
			   $('#custmrSeq').val(custmrSeq);
			   
			   var formData = $("#communityForm").serialize();
			   $.ajax({
			      type:"POST",
			      data:formData,
			      url:"http://sebm.iptime.org:9124/appComntyEntryCreate.do",
			      // <성공>
			      success:function(data){
			         navigator.notification.alert("환영합니다",null,"참가자 등록","확인");
			         $("#communityReadList").listview("refresh");
			         communityReadTwo(boardSeq); // 담소방 상세보기시 STEP 2 톡 리스트
			      },
			      // <실패>
			      error:function(request,status,error){
					$.ajax({
						  type:"POST",
						  data:formData,
						  url:"http://sebm.iptime.org:9124/appComntyEntryLeave.do",
						  success:function(data){
						     // 삭제한 뒤 참가자 새로 등록
						     $.ajax({
						        type:"POST",
						        data:formData,
						        url:"http://sebm.iptime.org:9124/appComntyEntryCreate.do",
						        success:function(data){
						           communityReadTwo(boardSeq); // 담소방 상세보기시 STEP 2 톡 리스트
						        },
						        error:function(request,status,error){
						             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
						        }
						     });                
						  },
						  error:function(request,status,error){
				    			       alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
						  }
						});
		             
			      }
			   });   	   
	   };
	});

// 담소방 등록
function communityCret(){
    if ($("#boardTitl").val() == ""){
      navigator.notification.alert("담소방 이름을 정해주세요.",null,"담소방 등록","확인");
      $("#boardTitl").focus();
       return;
   }
   else if ($("#boardIntrodun").val() == ""){
      navigator.notification.alert("담소방을 소개해주세요.",null,"담소방 등록","확인");
      $("#boardIntrodun").focus();
       return;
   }
    
   var formData = $("#communityCretForm").serialize();
   $.ajax({
      type:"POST",
      data:formData,
      url:"http://sebm.iptime.org:9124/appCommtyCreate.do",
      success:function(){
         navigator.notification.alert("담소방이 열렸습니다.",null,"담소방 등록","확인");
         $.mobile.changePage("CommunityList.html");         
      },
      error:function(request,status,error){
           alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}

// 담소방 상세보기시 STEP 1 참가자 등록
function communityReadOne(boardSeq){
	sessionStorage.setItem('boardSeq', boardSeq);
	$.mobile.changePage("CommunityRead.html");
}

// 담소방 상세보기시 STEP 2 톡 리스트 
function communityReadTwo(boardSeq){
   $('#boardSeq').val(boardSeq);
   
	var formData = $("#communityForm").serialize();
	$("#communityReadList").empty();

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
			communityPanel(); //담소방 상세보기시 STEP 3 오른쪽 패널
			$("#communityReadList").listview("refresh");
			document.body.scrollTop = document.body.scrollHeight;
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});    
}

//담소방 상세보기시 STEP 3 오른쪽 패널
function communityPanel() {
   var boardSeq =sessionStorage.getItem('boardSeq');  
   $('#boardSeq').val(boardSeq);

   var formData = $("#communityForm").serialize();
   $("#entryHotList").empty();
   $.ajax({
      type:"POST",
      data:formData,
      url:"http://sebm.iptime.org:9124/appComntyReadPanel.do",
      success:function(data){   
         $.each(data, function(i, vo){
                       
            boardSeq = vo.boardSeq;
            titl = vo.boardTitl;
            boardIntrodun = vo.boardIntrodun;
            custmrSeq = vo.custmrSeq;
            custmrNme = vo.custmrNme;
            custmrId = vo.custmrId;
            hotlistCheck = vo.hotlistCheck;
            
            $( '#titl' ).text(titl);
            $( '#boardIntrodun' ).text(boardIntrodun);
               
            $("#entryHotList").append("<li style='padding-top: 0px; margin-bottom: 0px;'><h6>"+custmrNme+"("+custmrId+")</h6>"
               +"<hr style='width:120%; margin-top: 0px; margin-bottom: 0px;'></li>");   
         });
         $("#entryHotList").listview("refresh");
         
         $("#communityUpdate").html("<font color='#B5B5B5' class='font-sm'><b>설정하기</b></font>"
               +"<a href='javascript:comntyMyUpdateRead("+boardSeq+")'><img src='../img/set.png' style='height:18px; float:right; padding-right:8px'></a>");   
         
         
         // 즐겨찾기가 되어있지 않은 담소방이라면               
         if(hotlistCheck==0){
             $('#loveState').val(0);
         }
         else {
            $("#img").attr("src", "../img/love.png");
             $('#loveState').val(1);
         }   
      },
      error:function(request,status,error){
           alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });   
}   

//담소방 글 등록
function talkCreate(){
    if ($("#talk").val() == ""){
      navigator.notification.alert("대화를 써주세요.",null,"담소방 글 등록","확인");
      $("#talk").focus();
       return;
   }  
   var boardSeq = sessionStorage.getItem('boardSeq');
   
   $('#boardSeq').val(boardSeq);
   $('#custmrSeq').val(custmrSeq);
   var formData = $("#communityForm").serialize();

   $.ajax({
      type:"POST",
      data:formData,
      url:"http://sebm.iptime.org:9124/appTalkCreate.do",
      success:function(){
         $("#communityReadList").listview("refresh");
      },
      error:function(request,status,error){
           alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
   communityReadTwo(boardSeq);
}

// 담소방 나가기
function communityReadBack(){
   var boardSeq =sessionStorage.getItem('boardSeq');
   
   $('#boardSeq').val(boardSeq);
   $('#custmrSeq').val(custmrSeq);
   var formData = $("#communityForm").serialize();

   $.ajax({
      type:"POST",
      data:formData,
      url:"http://sebm.iptime.org:9124/appComntyEntryLeave.do",
      success:function(data){
         sessionStorage.removeItem('boardSeq');
         $.mobile.changePage("CommunityList.html");   
      },
      error:function(request,status,error){
           alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });   
}

// 담소방 수정화면 전환
function comntyMyUpdateRead(boardSeq) {
   
   $.mobile.changePage("CommunityUpdate.html");   
      $.ajax({
         type:"POST",
         data:{"boardSeq": boardSeq},
         url:"http://sebm.iptime.org:9124/appComntyMyUpdateRead.do",
         success:function(data){
            $('#boardSeq').val(data.boardSeq);
            $('#boardImg').attr("src", data.boardImg);
            $('#boardTitl').val(data.boardTitl);
            $('#boardIntrodun').val(data.boardIntrodun);
            $('#boardPw').val(data.boardPw);
         },
         error:function(request,status,error){
              alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
         }
      });
   }

//담소방 수정
function communityUpdate(){
    if ($("#boardTitl").val() == ""){
      navigator.notification.alert("담소방 이름을 정해주세요.",null,"담소방 수정","확인");
      $("#boardTitl").focus();
       return;
   }
   else if ($("#boardIntrodun").val() == ""){
      navigator.notification.alert("담소방을 소개해주세요.",null,"담소방 수정","확인");
      $("#boardIntrodun").focus();
       return;
   }
    
   var formData = $("#communityUpdateForm").serialize();
   $.ajax({
      type:"POST",
      data:formData,
      url:"http://sebm.iptime.org:9124/appCommtyUpdate.do",
      success:function(){
         navigator.notification.alert("담소방이 변경되었습니다.",null,"담소방 수정","확인");
         $.mobile.changePage("CommunityMyList.html");         
      },
      error:function(request,status,error){
           alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}

// 리스트 즐겨찾기 
function clickLike(boardSeq) {
   
   var loveState = $("input[name='loveState"+boardSeq+"']").val();
   
   //빈 하트-> 꽉찬 하트 (즐겨찾기 등록)
    if(loveState==0){
       $("input[name='loveState"+boardSeq+"']").val(1);
       $("img[name='img"+boardSeq+"']").attr("src", "../img/love.png");
       url="http://sebm.iptime.org:9124/appHotlistCreate.do"
    }
   //꽉찬 하트-> 빈 하트 (즐겨찾기 취소)
    else{
       $("input[name='loveState"+boardSeq+"']").val(0);
       $("img[name='img"+boardSeq+"']").attr("src", "../img/noLove.png");
       url="http://sebm.iptime.org:9124/appHotlistCancel.do"
    }
    
   $('#boardSeq').val(boardSeq);
   $('#custmrSeq').val(custmrSeq);
   
   var formData = $("#communityForm").serialize();
   $.ajax({
      type:"POST",
      data:formData,
      url:url,
      success:function(){
         
      },
      error:function(request,status,error){
           alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}

// 오른쪽 패널 즐겨찾기
function panelClickLike() {
   var loveState = $('#loveState').val();
   
   //빈 하트-> 꽉찬 하트 (즐겨찾기 등록)
    if(loveState==0){
       $('#loveState').val(1);
       $("#img").attr("src", "../img/love.png");
       url="http://sebm.iptime.org:9124/appHotlistCreate.do"
    }   
   //꽉찬 하트-> 빈 하트 (즐겨찾기 취소)
    else{
       $('#loveState'+boardSeq).val(0);
       $("#img").attr("src", "../img/noLove.png");
       url="http://sebm.iptime.org:9124/appHotlistCancel.do"
    }
   var formData = $("#communityForm").serialize();
   $.ajax({
      type:"POST",
      data:formData,
      url:url,
      success:function(){
         
      },
      error:function(request,status,error){
           alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}

// 비공개 담소방 비밀번호 팝업
function lockPopUp(boardSeq){
   $("#popupRead-screen").attr("class", "ui-popup-screen ui-overlay-inherit in");
   $("#popupRead-popup").attr("class", "ui-popup-container pop in ui-popup-active");
   $("#popupRead-popup").attr("style", "max-width: 330px; top: 206.5px; left: 52px;");
   $('#boardSeq').val(boardSeq);   
}

//비공개 담소방 비밀번호 팝업 닫기
function lockPopUpCancel(){
   $("#popupRead-screen").attr("class", "ui-popup-container pop ui-popup-hidden ui-popup-truncate");
   $("#popupRead-popup").attr("class", "ui-popup-screen ui-overlay-inherit ui-screen-hidden");
   $("#popupRead-popup").attr("style", "");
}

// 비공개 담소방 비밀번호 클리어 시 상세보기 전환
function lockPopupClear(boardPw){

   $('#boardPw').val(boardPw);   
   var formData = $("#communityForm").serialize();

   $.ajax({
      type:"POST",
      data:formData,
      url:"http://sebm.iptime.org:9124/appComntyPasswordCheck.do",
      success:function(data){
         var boardPwCheck = data.boardPwCheck;
     
         if(boardPwCheck!=0){
            communityReadOne($('#boardSeq').val());
         }
         else{
            navigator.notification.alert("비밀번호가 일치하지 않습니다.",null,"알림","확인");
         }
      },
      error:function(request,status,error){
           alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}

function communityCustmrSearch(searchText){
      $.mobile.changePage("CommunitySearchList.html");    
      sessionStorage.setItem('communitySearchText', searchText);
};

$(document).on("pageshow","#communitySearchListPage",function(){ // When entering pagetwo
	var searchText = sessionStorage.getItem('communitySearchText');
	$("#communitySechList").empty();
	$.ajax({
	    type:"POST",
	    url:"http://sebm.iptime.org:9124/appCommunityList.do",
	    data:{"searchText":searchText},
	    success:function(data){    
	       $.each(data, function(i, vo){
	          boardSeq = vo.boardSeq;
	          boardTitl = vo.boardTitl;
	          boardImg = vo.boardImg;
	           boardIntrodun = vo.boardIntrodun;
	           boardPw = vo.boardPw;
	           boardEntryCount = vo.boardEntryCount;
	           hotlistBoardSeq = vo.hotlistBoardSeq;
	          $('#boardSeq').val(boardSeq);   
	             
	       // 공개 담소방
              if(boardPw==0){      
                 $("#communitySechList").append("<div class='nd2-card card-media-right card-media-medium' style='background-color:#FFFFFF'>"
                       + "<ul data-role='listview' class='ui-listview'>"
                       + "<li name='"+boardSeq+"' class='ui-li-has-thumb ui-first-child ui-last-child'>"
                       + "<a href='javascript:communityReadOne("+boardSeq+")' class='ui-btn waves-effect waves-button waves-effect waves-button waves-effect waves-button'>"
                       + "<img src='../img/communiOpen.png' class='ui-thumbnail ui-thumbnail-circular' style='width: 120px; height:105px; left: 10px; max-height: 20em; max-width: 20em;'/>"
                       + "<h2 style='height:20%; margin-left:30px;'>"+boardTitl+"</h2>"
                       + "<p style='margin-top: 3px; margin-left: 30px; margin-bottom: 0px;'>"+boardIntrodun+"</p>"
                       + "</a>"
                       + "<div class='box' style='padding-bottom:0px; padding-left:110px;'>"
                       +"   <input type='hidden' name='loveState"+boardSeq+"'>"
                       +"<div style='display:inline-block' id='noLove"+boardSeq+"'>"
                       +"<a href='javascript:clickLike("+boardSeq+")' class='ui-btn ui-btn-inline'>"
                       +"<img name='img"+boardSeq+"' src='../img/noLove.png' style='width: 25px;'>"
                       +"</a></div>"   
                       +"<div name='lockState"+boardSeq+"' style='display:inline-block;'></div>"
                       + "<img src='../img/people.png' style='width: 20px; padding-left:6px; padding-right:0px; display:inline-block;'>"
                       + "<font class='font-sm'>"+boardEntryCount+"</font>"
                       + "</div></li></ul></div>");
              }
              // 비공개 담소방
              else{
                 $("#communitySechList").append("<div class='nd2-card card-media-right card-media-medium' style='background-color:#FFFFFF'>"
                       + "<ul data-role='listview' class='ui-listview'>"
                       + "<li name='"+boardSeq+"' class='ui-li-has-thumb ui-first-child ui-last-child'>"
                       + "<a onclick='javascript:lockPopUp("+boardSeq+")' class='ui-btn ui-btn-right'  data-rel='popup' data-position-to='window' data-role='button' data-inline='true' data-transition='pop' class='ui-btn'>"
                       + "<img src='../img/communiSecret.png' class='ui-thumbnail ui-thumbnail-circular' style='width: 120px; height:105px; left: 10px; max-height: 20em; max-width: 20em;'/>"
                       + "<h2 style='height:20%; margin-left:30px;'>"+boardTitl+"</h2>"
                       + "<p style='margin-top: 3px; margin-left: 30px; margin-bottom: 0px;'>"+boardIntrodun+"</p>"
                       + "</a>"
                       + "<div class='box' style='padding-bottom:0px; padding-left:110px;'>"
                       +"   <input type='hidden' name='loveState"+boardSeq+"'>"
                       +"<div style='display:inline-block' id='noLove"+boardSeq+"'>"
                       +"<a href='javascript:clickLike("+boardSeq+")' class='ui-btn ui-btn-inline'>"
                       +"<img name='img"+boardSeq+"' src='../img/noLove.png' style='width: 25px;'>"
                       +"</a></div>"   
                       +"<div name='lockState"+boardSeq+"' style='display:inline-block;'></div>"
                       + "<img src='../img/people.png' style='width: 20px; padding-left:6px; padding-right:0px; display:inline-block;'>"
                       + "<font class='font-sm'>"+boardEntryCount+"</font>"
                       + "</div></li></ul></div>");

	             $("div[name='lockState"+boardSeq+"']").append("<img src='../img/security.png' style='width:25px;'>");
	          }
	                   
	          // 즐겨찾기가 되어있지 않은 담소방이라면               
	          if(hotlistBoardSeq==0){
	              $("input[name='loveState"+boardSeq+"']").val(0); 
	          }
	          else {
	             $("img[name='img"+boardSeq+"']").attr("src", "../img/love.png");
	              $("input[name='loveState"+boardSeq+"']").val(1);
	          }               
	       });
	    },
	    error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	 });
	sessionStorage.removeItem('communitySearchText');
});
