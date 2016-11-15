$(document).on('pageshow', "#empMainPage", function (event, data) {
	empLeftPanelEventProdctList();
	empLeftPanelTopCatgrList();
	
	empRightPanelOrdrInfo();
	empRightPanelEmpList();
	empLeftPanelNme();
}); 

//고객 왼쪽 패널 이름
function empLeftPanelNme(){
	var sessionEmpSeq = sessionStorage.getItem('empSeq');

	if(sessionEmpSeq!=null){
		var empSeq = sessionStorage.getItem('empSeq');
	}
	else{
		var empSeq = localStorage.getItem('empSeq');
	}
	$.ajax({
      type: "POST",
      url:"http://sebm.iptime.org:9124/ordrCountList.do",  
      data:{"empSeq":empSeq},
      success:function(data){
    	  var empNme = data.empNme;
    	  $('#empNme').append("<strong>"+empNme+" 님</strong>");
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}


//고객 왼쪽 패널 - 행사 상품 리스트 조회 
function empLeftPanelEventProdctList() {
   $.ajax({
      type:"POST",
      url:"http://sebm.iptime.org:9124/cusLeftPanelEventProdctList.do",
      success:function(data){
         $.each(data, function(i, vo){
            $("#empLeftPanelEventProdct").append("" +
                  "<tr>" +
                     "<td onclick='appProdcrDtlRead("+vo.prodctSeq+")' rowspan='2' width='35%'>" +
                        "<img src='"+ "http://sebm.iptime.org:9124/" + vo.cartImg+"' width='75' height='75'>" +
                     "</td>" +
                     "<td onclick='appProdcrDtlRead("+vo.prodctSeq+")' width='45%'>" +
                        "<div style='display: -webkit-box; height: auto; padding-right: 15px; line-height: 20px; color: #666; -webkit-line-clamp: 2; -webkit-box-orient: vertical; word-break: break-all; word-wrap: break-word; overflow:hidden; white-space:nowrap; text-overflow:ellipsis;'>" +
                           "<span>"+vo.prodctNme+"</span>" +
                        "</div>" +
                     "</td>" +
                  "</tr>" +
                  "<tr>" +
                     "<td>" +
                        "<div onclick='appProdcrDtlRead("+vo.prodctSeq+")' style='margin: 0 0 -4px; line-height: 27px; color: #262f33;'>" +
                           "<em style='font-style: normal; font-family: 'emm_bol'; font-size: 20px; font-weight: bold; letter-spacing: -1px; color: #262f33;'>" +
                           "<b class='font-md' id='panelEventProdct-"+i+"'>" + vo.selPric + "원</b>" +
                           "</em>" +
                        "</div>" +
                     "</td>" +
                  "</tr>" +
                   "");   

            
            if(vo.purchsPric != 0 && vo.purchsPric != vo.selPric) {
               panelEventProdctId = "panelEventProdct-"+i;   
               $($("#"+panelEventProdctId)).html("" +
                  "<span class='font-md' style='text-decoration:line-through;'>" +
                     vo.selPric + "원</span> <br>" +
                     vo.purchsPric + "원" +
                     "");
            }
            
         
            
         });
         $("#empLeftPanelEventProdct").listview("refresh");
         
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });   
}



//고객 왼쪽 패널 - 대분류 카테고리 리스트 조회
function empLeftPanelTopCatgrList() {
   $.ajax({
      type:"POST",
      url:"http://sebm.iptime.org:9124/cusLeftPanelTopCatgrList.do",
      success:function(data){
         $.each(data, function(i, vo){
               $("#empLeftPanelCatgr").append("" +
                     "<div data-role='collapsible' data-inset='false'  data-collapsed-icon='carat-d' data-expanded-icon='carat-d' data-iconpos='right'>" +
                        "<h3>"+vo.text+"</h3>" +
                        "<ul id='TopCatgr"+vo.id+"' data-role='listview' data-inset='false' data-icon='false'>" +
                        "</ul>" +
                     "</div>" +
                     "").trigger("create");               
         });
         
         empLeftPanelMidCatgrList();
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });   
}



//고객 왼쪽 패널 - 중분류 카테고리 리스트 조회
function empLeftPanelMidCatgrList() {
   $.ajax({
      type:"POST",
      url:"http://sebm.iptime.org:9124/cusLeftPanelMidCatgrList.do",
      success:function(data){
         $.each(data, function(i, vo){            
            $("#TopCatgr"+vo.parent).append("" +
                  "<li style='height:40px' onclick='midCatgrSelect2("+vo.id+","+'"'+vo.text+'"'+")'><a class='font-md' href='#' data-ajax='false' data-icon='false'>"+vo.text+"</a></li>" +
                  "").trigger("create");               
         });
         
         $("#empLeftPanelCatgr").listview("refresh");
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });   
}


// 카테고리 중분류 눌렀을 때
function midCatgrSelect2(midCatgrSeq, midCatgrText) {
	var midCatgrSeq = midCatgrSeq;
	sessionStorage.setItem('midCatgrSeq', midCatgrSeq);
	var midCatgrText = midCatgrText;
	sessionStorage.setItem('midCatgrText', midCatgrText);
	$.mobile.changePage("MarketEmpSearchProdctList.html");	
}



// 고객 오른쪽 패널 - 쇼핑 정보 조회
function empRightPanelOrdrInfo() {  
	$.ajax({
        type: "POST",
        url:"http://sebm.iptime.org:9124/empShipngList.do",   
        data:{"ordrStatSeq":10},
        success:function(data){
        	$.each(data, function(i, vo){
      		  var ordrNumbrSeq = vo.ordrNumbrSeq;
      		  var ordrStat = vo.ordrStat;
      		  var prodctNme = vo.prodctNme;
      		  var ordrProdctAmont = vo.ordrProdctAmont;
       		  
	      		var prodctCotent;
	            if(ordrProdctAmont == 1) {
	               prodctCotent = prodctNme;
	            }
	            else{
	               prodctCotent = prodctNme + " 외 " + ordrProdctAmont + " 종 ";   
	            }
	            
	            $("#ordrInfoCotent").append("" +
	                  "<li onclick='empOrdrRead("+ordrNumbrSeq+",0)'class='ui-field-contain'>" +
	                     "<div class='row' style='height:18px'>" +
	                        "<div class='col-xs-3' >" +
	                           "<b>"+ordrStat+"</b>" +
	                        "</div>" +
	                        "<div id='empRightPanelOrdrProdct"+ordrNumbrSeq+"' class='col-xs-9' style='overflow:hidden; white-space:nowrap; text-overflow:ellipsis;'>" +
	                        "</div>" +
	                     "</div>" +
	                  " </li>" +
	                  "");
	            $("#ordrInfoCotent").listview("refresh");
	            $("#empRightPanelOrdrProdct"+ordrNumbrSeq).append(prodctCotent);         
      	  });
  		  sessionStorage.removeItem('ordrStatSeq');
        },
        error:function(request,status,error){
           alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
     });
}

//고객 오른쪽 패널 - 직원 리스트 조회 추천 하기
function empRightPanelEmpList() {
	var sessionEmpSeq = sessionStorage.getItem('empSeq');
	if(sessionEmpSeq!=null){
		var empSeq = sessionStorage.getItem('empSeq');
	}
	else{
		var empSeq = localStorage.getItem('empSeq');
	}
    $.ajax({
        type: "POST",
        url:"http://sebm.iptime.org:9124/empRecmndList.do",   
        data:{"empSeq":empSeq},
        success:function(data){
  		  $.each(data, function(i, vo){
  			  var nme = vo.nme;
    		  var empSeq = vo.empSeq;
    		  var duty = vo.duty;
    		  var task = vo.task;
    		  var imgRot =  "http://sebm.iptime.org:9124/" + vo.imgRot;
	            if(i%2==0) {
	               $("#empRightPanelEmpCotent").append("" +
	                  "<div id='row-"+i+"' class='row' style='height:80px; padding:0px; margin-left: 1%'>" +
	                     "<div onclick='empRecmnd("+empSeq+")' style='width:30%;'>" +
	                        "<img src='"+imgRot+"' width='98%' height='78px;'>   " +
	                     "</div>" +
	                     "<div class='col-xs' style='width:15%;'>" +
	                        nme+"<br>" + duty +
	                     "</div>" +
	                  "</div>" +
	               "");               
	            }
	            else {
	               var id = "row-"+(i-1);
	               $("#"+id).append("" +
	                     "<div onclick='empRecmnd("+empSeq+")' style='width:30%;'>" +
	                        "<img src='"+imgRot+"' width='98%' height='78px;'> " +
	                     "</div>" +
	                     "<div class='col-xs' style='width:25%;'>" +
	                        nme+"<br>" + duty +
	                     "</div>" +
	                  "");
	                  
	            }         
	         });
	         $("#empRightPanelEmpCotent").listview("refresh");
	      },
	      error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	      }
	   });   
}

//직원추천
function empRecmnd(empSeq, empNme){
	var getEmpSeq = empSeq;
	if(sessionStorage.getItem('empSeq')!=null){
		var giveEmpSeq = sessionStorage.getItem('empSeq');
	}
	else{
		var giveEmpSeq = localStorage.getItem('empSeq');
	}
	
	navigator.notification.confirm(
        '추천하시겠습니까?', // message
        function(button) {
            if ( button == 1 ) {
            	$.ajax({
        	        type: "POST",
        	        url:"http://sebm.iptime.org:9124/empRecmndCreate.do",   
        	        data:{"getEmpSeq":empSeq, "giveEmpSeq":giveEmpSeq},
        	        success:function(data){
        	        	if(data==1){
        	        		navigator.notification.alert("이미 추천을 하셨습니다.",null,"알림","확인");
        	        	}
        	        	else{
        	        		navigator.notification.alert("직원 추천이 완료되었습니다.",null,"알림","확인");
        	        	}	        	
        	        },
        	        error:function(request,status,error){
        	           alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        	        }
        	     });
            }
            else{
            	navigator.notification.alert("추천이 취소되었습니다.",null,"알림","확인");
            }
        }, 
		'직원 추천',     // title
        ['추천','취소']         // buttonLabels
    );
}