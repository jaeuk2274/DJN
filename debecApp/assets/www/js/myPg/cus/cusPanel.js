$(document).on("pagehide","#cusMainPage",function(){
   document.removeEventListener("backbutton", onBackKeyDown, false);
})

$(document).on('pageshow', "#cusMainPage", function (event, data) {
   // 고객 오른쪽 패널
   cusRightPanelOrdrInfo();
   cusRightPanelCartList();
   cusRightPanelHistoryList();
   cusRightPanelEmpList();
   
   cusLeftPanelEventProdctList();
   cusLeftPanelTopCatgrList();
   cusLeftPanelNme();
}); 


//고객 왼쪽 패널 이름
function cusLeftPanelNme(){
	var custmrId = sessionStorage.getItem('custmrId');
	if(custmrId!=null){
		custmrId = sessionStorage.getItem('custmrId');
	}
	else{
		custmrId = localStorage.getItem('custmrId');
	}
	$.ajax({
      type: "POST",
      url:"http://sebm.iptime.org:9124/cusPwRead.do",  
      data:{"custmrId":custmrId},
      success:function(data){
    	  var custmrNme = data.custmrNme;
    	  $('#custmrNme').append("<strong>"+custmrNme+" 님</strong>");
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}

// 고객 왼쪽 패널 장바구니버튼 클릭, 장바구니 담기
function cusLeftPanelFormCart(seq, type) {
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	if(custmrSeq!=null){
		custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		custmrSeq = localStorage.getItem('custmrSeq');
	}
	var prodctSeq =  seq;
	var prodctAmont =  1;
	var prodctType =  type;
	formData = "prodctSeq="+prodctSeq+"&prodctAmont="+prodctAmont+"&custmrSeq="+custmrSeq+"&prodctType="+prodctType;
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/appCartItemCreat.do",
		success:function(data){
			navigator.notification.alert("장바구니에 상품을 담았습니다.",null,"알림","확인");
			 cusRightPanelCartList();
			
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
};



//고객 왼쪽 패널 - 행사 상품 리스트 조회 
function cusLeftPanelEventProdctList() {
   $.ajax({
      type:"POST",
      url:"http://sebm.iptime.org:9124/cusLeftPanelEventProdctList.do",
      success:function(data){
         $.each(data, function(i, vo){
            $("#cusLeftPanelEventProdct").append("" +
                  "<tr>" +
                     "<td onclick='appProdcrDtlRead("+vo.prodctSeq+")' rowspan='2' width='35%'>" +
                        "<img src='http://sebm.iptime.org:9124/"+vo.cartImg+"' width='75' height='75'>" +
                     "</td>" +
                     "<td onclick='appProdcrDtlRead("+vo.prodctSeq+")' width='45%'>" +
                        "<div style='display: -webkit-box; height: auto; padding-right: 15px; line-height: 20px; color: #666; -webkit-line-clamp: 2; -webkit-box-orient: vertical; word-break: break-all; word-wrap: break-word; overflow:hidden; white-space:nowrap; text-overflow:ellipsis;'>" +
                           "<span>"+vo.prodctNme+"</span>" +
                        "</div>" +
                     "</td>" +
                     "<td rowspan='2' width='20%'>" +
                        "<button onclick='cusLeftPanelFormCart("+vo.prodctSeq+","+vo.prodctType+");return false;' class='ui-btn' style='padding: 0; width: 53px; height: 53px; background-position: 0 -400px;'>" +
                        "<i><img src='../img/cart.png'/></i>" +
                        "</button>" +
                     "</td>" +
                  "</tr>" +
                  "<tr>" +
                     "<td>" +
                        "<div onclick='appProdcrDtlRead("+vo.prodctSeq+")' style='margin: 0 0 -4px; line-height: 27px; color: #262f33;'>" +
                           "<em style='font-style: normal; font-family: 'emm_bol'; font-size: 20px; font-weight: bold; letter-spacing: -1px; color: #262f33;'>" +
                           "<b class='font-md' id='panelEventProdct-"+i+"'>" + numberWithCommas(vo.selPric) + "원</b>" +
                           "</em>" +
                        "</div>" +
                     "</td>" +
                  "</tr>" +
                   "");   

            
            if(vo.purchsPric != 0 && vo.purchsPric != vo.selPric) {
               panelEventProdctId = "panelEventProdct-"+i;   
               $($("#"+panelEventProdctId)).html("" +
                  "<span class='font-md' style='text-decoration:line-through;'>" +
                  numberWithCommas(vo.selPric) + "원</span> <br>" +
                  numberWithCommas(vo.purchsPric) + "원" +
                     "");
            }
            
         
            
         });
         $("#cusLeftPanelEventProdct").listview("refresh");
         
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });   
}



//고객 왼쪽 패널 - 대분류 카테고리 리스트 조회
function cusLeftPanelTopCatgrList() {
   $.ajax({
      type:"POST",
      url:"http://sebm.iptime.org:9124/cusLeftPanelTopCatgrList.do",
      success:function(data){
         $.each(data, function(i, vo){
               $("#cusLeftPanelCatgr").append("" +
                     "<div data-role='collapsible' data-inset='false'  data-collapsed-icon='carat-d' data-expanded-icon='carat-d' data-iconpos='right'>" +
                        "<h3>"+vo.text+"</h3>" +
                        "<ul id='TopCatgr"+vo.id+"' data-role='listview' data-inset='false' data-icon='false'>" +
                        "</ul>" +
                     "</div>" +
                     "").trigger("create");               
         });
         
         cusLeftPanelMidCatgrList();
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });   
}



//고객 왼쪽 패널 - 중분류 카테고리 리스트 조회
function cusLeftPanelMidCatgrList() {
   $.ajax({
      type:"POST",
      url:"http://sebm.iptime.org:9124/cusLeftPanelMidCatgrList.do",
      success:function(data){
         $.each(data, function(i, vo){            
            $("#TopCatgr"+vo.parent).append("" +
                  "<li style='height:40px' onclick='midCatgrSelect("+vo.id+","+'"'+vo.text+'"'+")'><a class='font-md' href='#' data-ajax='false' data-icon='false'>"+vo.text+"</a></li>" +
                  "").trigger("create");               
         });
         
         $("#cusLeftPanelCatgr").listview("refresh");
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });   
}


// 카테고리 중분류 눌렀을 때
function midCatgrSelect(midCatgrSeq, midCatgrText) {
	var midCatgrSeq = midCatgrSeq;
	sessionStorage.setItem('midCatgrSeq', midCatgrSeq);
	var midCatgrText = midCatgrText;
	sessionStorage.setItem('midCatgrText', midCatgrText);
	$.mobile.changePage("MarketCusSearchProdctList.html");

	
}



// 고객 오른쪽 패널 - 쇼핑 정보 조회
function cusRightPanelOrdrInfo() {
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	if(custmrSeq!=null){
		custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		custmrSeq = localStorage.getItem('custmrSeq');
	}
	
   formData = "cusSeq="+custmrSeq;
   $.ajax({
      type:"POST",
      data:formData,
      url:"http://sebm.iptime.org:9124/cusRightPanelOrdrInfo.do",
      success:function(data){
         var prodctCotent;
         if(data.prodctCount == 1) {
            prodctCotent = data.firstProdctNme;
         }
         else{
            prodctCotent = data.firstProdctNme + " 외 " + data.prodctCount + " 종 ";   
         }
         
         $("#ordrInfoCotent").append("" +
               "<li onclick=' cusOrdrRead("+data.ordrNumbrSeq+",0)'class='ui-field-contain'>" +
                  "<div class='row' style='height:18px'>" +
                     "<div class='col-xs-3' >" +
                        "<b>"+data.ordrStat+"</b>" +
                     "</div>" +
                     "<div id='cusRightPanelOrdrProdct' class='col-xs-9' style='overflow:hidden; white-space:nowrap; text-overflow:ellipsis;'>" +
                     "</div>" +
                  "</div>" +
               " </li>" +
               "");
         $("#ordrInfoCotent").listview("refresh");
         $("#cusRightPanelOrdrProdct").append(prodctCotent);         
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });   
}


//고객 오른쪽 패널 - 장바구니 리스트 조회 (5개 까지만)
function cusRightPanelCartList() {
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	if(custmrSeq!=null){
		custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		custmrSeq = localStorage.getItem('custmrSeq');
	}
   formData = "cusSeq="+custmrSeq;
   $.ajax({
      type:"POST",
      data:formData,
      url:"http://sebm.iptime.org:9124/cusRightPanelCartList.do",
      success:function(data){
    	 $("#cusRightPanelCartCotent").empty();
         $.each(data, function(i, vo){
            $("#cusRightPanelCartCotent").append("" +
               "<li onclick='appProdcrDtlRead("+vo.prodctSeq+")' class='ui-field-contain'>" +
                  "<div class='row' style='height:45px'>" +
                     "<div class='col-xs-10' style='overflow:hidden; white-space:nowrap; text-overflow:ellipsis;'>" +
                        vo.prodctNme + "<br>" +
                        "<b class='font-md' id='panelCartProdct-"+i+"'>" + numberWithCommas(vo.selPric) + "원</b>" +
                     "</div>" +
                     "<div class='col-xs-2'>" +
                        "<h4>" + vo.ordrAmont + "개</h4>" +
                     "</div>" +
                  "</div>" +
               "</li>" +
               "<hr style='margin:0px'>" +
             "");
            
            if(vo.purchsPric != 0 && vo.purchsPric != vo.selPric) {
               panelCartProdctId = "panelCartProdct-"+i;   
               $($("#"+panelCartProdctId)).html("" +
                  "<span class='font-md' style='text-decoration:line-through;'>" +
                  numberWithCommas(vo.selPric) + "원</span> -> &nbsp;" +
                  numberWithCommas(vo.purchsPric) + "원" +
                     "");
            }
            
         });
         $("#cusRightPanelCartCotent").listview("refresh");
         
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });   
}


//고객 오른쪽 패널 - 히스토리 리스트 조회 (5개 까지만)
function cusRightPanelHistoryList() {
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	if(custmrSeq!=null){
		custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		custmrSeq = localStorage.getItem('custmrSeq');
	}
   formData = "cusSeq="+custmrSeq;
   $.ajax({
      type:"POST",
      data:formData,
      url:"http://sebm.iptime.org:9124/cusRightPanelHistoryList.do",
      success:function(data){
         $.each(data, function(i, vo){
            $("#cusRightPanelHistoryCotent").append("" +
               "<li onclick='appProdcrDtlRead("+vo.prodctSeq+")' class='ui-field-contain'>" +
                  "<div class='row' style='height:80px'>" +
                     "<div class='col-xs-4'>" +
                        "<img src='http://sebm.iptime.org:9124/" + vo.cartImg + "' width='100%' height='80px;'>" +
                     "</div>" +
                     "<div class='col-xs-8' style='white-space:normal;'>" +
                        vo.prodctNme+"<br>" +
                        "<b class='font-md' id='panelHistoryProdct-"+i+"'>"+numberWithCommas(vo.selPric)+"원</b>" +
                     "</div>" +
                  "</div>" +
               "</li>" +
               "<hr style='margin:0px'>" +
             "");
             
            if(vo.purchsPric != 0) {
               panelHistoryProdctId = "panelHistoryProdct-"+i;   
               $($("#"+panelHistoryProdctId)).html("" +
                  "<span class='font-md' style='text-decoration:line-through;'>" +
                  numberWithCommas(vo.selPric) + "원</span> <br>" +
                  numberWithCommas(vo.purchsPric) + "원" +
                     "");
            }
         });
         $("#cusRightPanelHistoryCotent").listview("refresh");      
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });   
}



//고객 오른쪽 패널 - 직원 리스트 조회 
function cusRightPanelEmpList() {
   $.ajax({
      type:"POST",
      url:"http://sebm.iptime.org:9124/cusRightPanelEmpList.do",
      success:function(data){
         $.each(data, function(i, vo){
            if(i%2==0) {
               $("#cusRightPanelEmpCotent").append("" +
                  "<div id='row-"+i+"' class='row' style='height:80px; padding:0px; margin-left: 1%'>" +
                     "<div onclick='custmrRecmnd("+vo.empSeq+")'  data-rel='popup' data-position-to='window' data-role='button' data-inline='true' data-transition='pop'  style='width:30%;'>" +
                        "<img src='http://sebm.iptime.org:9124/"+vo.imgRot+"' width='98%' height='78px;'>   " +
                     "</div>" +
                     "<div class='col-xs' style='width:15%;'>" +
                        vo.nme+"<br>" + vo.duty +
                     "</div>" +
                  "</div>" +
               "").trigger("create");               
            }
            else {
               var id = "row-"+(i-1);
               $("#"+id).append("" +
                     "<div onclick='custmrRecmnd("+vo.empSeq+")'  data-rel='popup' data-position-to='window' data-role='button' data-inline='true' data-transition='pop'  style='width:30%;'>" +
                        "<img src='http://sebm.iptime.org:9124/"+vo.imgRot+"' width='98%' height='78px;'> " +
                     "</div>" +
                     "<div class='col-xs' style='width:25%;'>" +
                        vo.nme+"<br>" + vo.duty +
                     "</div>" +
                  "").trigger("create");     
            }         
         });
//         $("#cusRightPanelEmpCotent").listview("refresh");
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });   
}


// 직원 추천 눌렀을 때 다이얼로그 띄움
function custmrRecmnd(empSeq){
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	if(custmrSeq!=null){
		custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		custmrSeq = localStorage.getItem('custmrSeq');
	}
	
	$.ajax({
	      type:"POST",
	      data:{"custmrSeq":custmrSeq},
	      url:"http://sebm.iptime.org:9124/custmrRecmndCheck.do",
	      success:function(data){
	    	  if(data.cusCount == 0){
	    		    sessionStorage.setItem('recmndEmpSeq', empSeq);
	    			$("#popupRead-screen").attr("class", "ui-popup-screen ui-overlay-inherit in");
	    			$("#popupRead-screen").attr("style", "z-index:3000");
	    			$("#popupRead-popup").attr("class", "ui-popup-container pop in ui-popup-active");
	    			$("#popupRead-popup").attr("style", "max-width: 330px; top: 206.5px; left: 52px; z-index:3000");
	    	  }
	    	  else{
	    		  navigator.notification.alert("직원 추천은 한달에 한번만 가능합니다.",null,"직원 추천","확인");
	    	  }
	      },
	      error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	      }
	 });  
}


// 고객이 직원 추천
function custmrRecmndCreate(recmndResnCodeSeq){
	var custmrSeq = sessionStorage.getItem('custmrSeq');
	if(custmrSeq!=null){
		custmrSeq = sessionStorage.getItem('custmrSeq');
	}
	else{
		custmrSeq = localStorage.getItem('custmrSeq');
	}
	var recmndEmpSeq = sessionStorage.getItem('recmndEmpSeq');
	var recmndResnCodeSeq = recmndResnCodeSeq;
	
	$.ajax({
	      type:"POST",
	      data:{"custmrSeq":custmrSeq, "recmndEmpSeq":recmndEmpSeq, "recmndResnCodeSeq":recmndResnCodeSeq},
	      url:"http://sebm.iptime.org:9124/custmrRecmndCreate.do",
	      success:function(data){
	    	  navigator.notification.alert("선택한 직원을 추천하였습니다.",null,"직원 추천","확인");
	      },
	      error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	      }
	 });   

	$("#popupRead-screen").attr("class", "ui-popup-container pop ui-popup-hidden ui-popup-truncate");
	$("#popupRead-screen").attr("style", "");
	$("#popupRead-popup").attr("class", "ui-popup-screen ui-overlay-inherit ui-screen-hidden");
	$("#popupRead-popup").attr("style", "");
}

function popupClose() {	
	$("#popupRead-screen").attr("class", "ui-popup-container pop ui-popup-hidden ui-popup-truncate");
	$("#popupRead-screen").attr("style", "");
	$("#popupRead-popup").attr("class", "ui-popup-screen ui-overlay-inherit ui-screen-hidden");
	$("#popupRead-popup").attr("style", "");
}
