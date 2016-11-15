var realTimRefresh; //실시간 주문목록 새로고침 관련 변수

$(document).on("pagehide","#empMainPage",function(){
	document.removeEventListener("backbutton", onBackKeyDown, false);
})

//마이페이지 관리자 환경설정
$(document).on("pageshow","#empSetupPage",function(){
    if(sessionStorage.getItem('empPushCheck')=='Y'){
    	$("#pushCheck option:eq(1)").attr("selected", "selected");
    }
    else{
    	$("#pushCheck option:eq(0)").attr("selected", "selected");
    }
});

//이달의 대장남 페이지
$(document).on("pageshow","#empDjnPage",function(){
    djnRead("init",0);
});

//개인정보 확인 페이지
$(document).on("pageshow","#empSetupConfirmPage",function(){
	setupConfirm();
});

//마에피이지 관리자 메인 페이지
$(document).on("pageshow","#empMyPageMainPage",function(){
	shipngTypeList();
});

//주문 취소 목록 페이지
$(document).on("pageshow","#empOrdrCancelListPage",function(){
	ordrCancelList();
});

//배송 목록 페이지
$(document).on("pageshow","#empShipngListPage",function(){
	shipngList();
});

//주문 취소 상세 페이지
$(document).on("pageshow","#empOrdrCancelReadPage",function(){
	ordrCancelRead();
});


//주문 상세 조회 페이지
$(document).on("pageshow","#empOrdrReadPage",function(){
	empOrdrRead2();
});

//실시간 주문목록 페이지
$(document).on("pageshow","#empRealTimeOrdrListPage",function(){
	realTimeOrdr();
});


//개인정보 관리 상세 조회 페이지
$(document).on("pageshow","#empSetupReadPage",function(){
	setupRead();
});

//직원추천 목록조회 페이지
$(document).on("pageshow","#empRecmndListPage",function(){
	recmndList();
})

function empLogout(){
	localStorage.removeItem('empId');
	localStorage.removeItem('empPw');
	localStorage.removeItem('empSeq');
	sessionStorage.removeItem('empId');
	sessionStorage.removeItem('empPw');
	sessionStorage.removeItem('empSeq');
	$.mobile.changePage("LoginFrame.html");
}

//직원 추천
function djnRead(type,num){
	var now = new Date();
    var year = $('#year').val();
    var mon = $('#mon').val();
    var selectMon;
    var exam = /^[0]/;
    
	if(type=='init'){
		year= now.getFullYear();
		mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
		$('#year').val(year);
		$('#mon').val(mon);
		selectMon = year + "-" + mon;
	    $('#curntMon').text(year+"년 "+mon+"월");
	}
	else{
		if(type=='year'){
			if(num=='-1'){
				year = parseInt(year)-1;
				$('#year').val(year);
			}
			else{
				year = parseInt(year)+1;
				$('#year').val(year);
			}
		}
		else{
			if((mon=='01'&&num=='-1') || (mon=='12'&&num=='1')){
				navigator.notification.alert("월 선택은 1월에서 12월까지 가능합니다.",null,"알림","확인");
				return false;
			}
			else{
				mon = exam.test(mon) ? mon.replace('0','') : mon;
				if(num=='-1'){
					mon = parseInt(mon)-1;
					mon = mon>9 ? mon : '0'+mon;
					$('#mon').val(mon);
				}
				else{
					mon = parseInt(mon)+1;
					mon = mon>9 ? mon : '0'+mon;
					$('#mon').val(mon);
				}
			}
		}
		selectMon = year + "-" + mon;
		$('#curntMon').text(year+"년 "+mon+"월");
	}	
	sessionStorage.setItem('selectMon', selectMon);
	
    $.ajax({
      type: "POST",
      url:"http://sebm.iptime.org:9124/empDjnRead.do",   
      data:{"selectMon":selectMon},
      success:function(data){
		  var djnVo = data.djnVo2;
		  var djnPointPerct = data.djnPointPerct;
		  
		  if(djnVo.empNme == null){
			  $('#empNme').html("없음");
			  $('#empDuty').html("없음");
			  $('#djnPointPerct').html("없음");
			  $('#empImg').attr('src','');
			  $('#empImg').attr('alt','없음');
		  }
		  else{
			  var empSeq = djnVo.empSeq;
			  var empNme = djnVo.empNme;
			  var empDuty = djnVo.empDuty;
			  var empTask = djnVo.empTask;
			  var imgRot = "http://sebm.iptime.org:9124/" + djnVo.empImg;
			  
			  var empRecmnd = djnPointPerct.empRecmnd;
			  var custmrRecmnd = djnPointPerct.custmrRecmnd;
			  var custmrPrais = djnPointPerct.custmrPrais;
			  var deliCont = djnPointPerct.deliCont;		 
			  
			  sessionStorage.setItem('chartEmpSeq', empSeq);
			  		  
			  $('#empImg').attr('src',imgRot);
			  $('#empNme').html(empNme);
			  $('#empDuty').html(empDuty + " / " + empTask);
			  $('#djnPointPerct').html("칭찬합시다 " + custmrPrais + "% / 고객추천 " + custmrRecmnd + "%<br>배송횟수 " + deliCont + "% / 직원추천 " + empRecmnd + "%");  
		  }
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
	google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);
}

// 차트를 그리는 메서드
function drawChart() {
	
	var empSeq = sessionStorage.getItem('chartEmpSeq');
	var selectMon = sessionStorage.getItem('selectMon');
	
	$.ajax({
      type: "POST",
      url:"http://sebm.iptime.org:9124/recmndResn.do",   
      data:{"empSeq":empSeq, "selectMon":selectMon},
      success:function(data){
    	  var arr = new Array(data.length+1); // 매개변수는 배열의 크기
    	  for (var i=0; i<data.length+1; i++) {
    		if(i==0){
    			arr[i] = new Array("기준","비율");
    		}
    		else{
    			arr[i] = new Array(2); // 매개변수는 배열의 크기
    		}
    	  }
    	  $.each(data, function(i, vo){
	    	  var recmndResnCont = vo.recmndResnCont;
	    	  var recmndResn = vo.recmndResn;
	    	  
	    	  if(recmndResnCont!='undefined'){
	    		  arr[i+1][0] = recmndResn;
		    	  arr[i+1][1] = recmndResnCont;
	    	  }
    	  })
		var w = window.innerWidth*0.85;
		var h = window.innerHeight*0.25;
		var option = {width:w, height:h,pieSliceText: 'label', legend:{position:'bottom'}};
		var data = google.visualization.arrayToDataTable(arr);
		var chart = new google.visualization.PieChart(document.getElementById('empDjnChart'));
		chart.draw(data, option);
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}

//실시간 주문 목록 자동 새로고침
function refreshRealTimeOrdrList(){
	$.ajax({
        type: "POST",
        url:"http://sebm.iptime.org:9124/empRealTimeOrdrList.do",  
        success:function(data){
        	$('#empRealTimeOrdrListFrame').empty();
        	$.each(data, function(i, vo){
      		  var ordrNumbrSeq = vo.ordrNumbrSeq;
      		  var ordrDat = vo.ordrDat;
      		  var ordrStat = vo.ordrStat;
      		  var prodctNme = vo.prodctNme;
      		  if(vo.cartImg1 == null){
    			  var cartImg1 = "../img/no_image.gif";
    		  }
    		  else{
    			  var cartImg1 = "http://sebm.iptime.org:9124/" + vo.cartImg1;  
    		  }       
      		  var recvrMobl = vo.recvrMobl;
      		  var ordrProdctAmont = vo.ordrProdctAmont;
      		  var allPric = numberWithCommas(vo.allPric);
      		  if(ordrStat=='주문접수'){
	      		  $('#empRealTimeOrdrListFrame').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%'>"
	      				  					+ "<table>"
	      				  					+ "<tr>"
	      				  					+ "<td>"+ordrDat+"</td>"
	      				  					+ "<td style='float:right'><a onclick='empOrdrRead("+ordrNumbrSeq+",0)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>주문상세보기 ></a>"
	      				  					+ "</td>"
	      				  					+ "</tr>"
	      				  					+ "</table>"
	      				  					+ "<div data-role='content' style='border-top:1px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
	      				  					+ "<div class='row'>"
	      				  					+ "<div class='col-xs-5'>"
	      				  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
	      				  					+ "</div>"
	      				  					+ "<div class='col-xs-7'>"
	      				  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
	      				  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + " 외 " + ordrProdctAmont + "건 </div>"
	      				  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
	      				  					+ "</div>"
	      				  					+ "</div>"
	      				  					+ "</div>"
	      				  					+ "</div>"
	      				  					+ "<div class='ui-grid-a ui-responsive'>"
	      				  					+ "<div style='width:50%; float:left' class='ui-block-a'><a href='tel:" + recvrMobl + "' rel='external' class='ui-btn ui-btn-raised font-btn clr-primary'>전화 연결</a></div>"
	      				  					+ "<div style='width:50%; float:left' class='ui-block-b'><a onclick='updateOrdrStat(2,"+ordrNumbrSeq+",0)' class='ui-btn ui-btn-raised font-btn clr-primary'>배송 실시</a></div>"	  
	      				  					+ "</div>"
	      		  )	
      		  }
      		  else{
      			$('#empRealTimeOrdrListFrame').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%'>"
		  					+ "<table>"
		  					+ "<tr>"
		  					+ "<td>"+ordrDat+"</td>"
		  					+ "<td style='float:right'><a onclick='empOrdrCancelRead("+ordrNumbrSeq+",0)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>취소상세보기 ></a>"
		  					+ "</td>"
		  					+ "</tr>"
		  					+ "</table>"
		  					+ "<div data-role='content' style='border-top:1px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
		  					+ "<div class='row'>"
		  					+ "<div class='col-xs-5'>"
		  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
		  					+ "</div>"
		  					+ "<div class='col-xs-7'>"
		  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
		  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:center'>"+ prodctNme + " 외 " + ordrProdctAmont + "건 </div>"
		  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
		  					+ "</div>"
		  					+ "</div>"
		  					+ "</div>"
		  					+ "</div>"
      			)	
      		  }
      	  });
  		  $("#empRealTimeOrdrListFrame").listview("refresh");
        },
        error:function(request,status,error){
           alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
     });
	//실시간 공동구매 조회
	$.ajax({
        type: "POST",
        url:"http://sebm.iptime.org:9124/empDahamgaeRealTimeOrdrList.do",  
        success:function(data){
        	$('#empRealTimeOrdrListFrame2').empty();
        	$.each(data, function(i, vo){
      		  var ordrNumbrSeq = vo.ordrNumbrSeq;
      		  var ordrDat = vo.ordrDat;
      		  var ordrStat = vo.ordrStat;
      		  var prodctNme = vo.prodctNme;
      		  if(vo.cartImg1 == null){
    			  var cartImg1 = "../img/no_image.gif";
    		  }
    		  else{
    			  var cartImg1 = "http://sebm.iptime.org:9124/" + vo.cartImg1;  
    		  }       
      		  var recvrMobl = vo.recvrMobl;
      		  var allPric = numberWithCommas(vo.allPric);
      		  if(ordrStat=='주문접수'){
	      		  $('#empRealTimeOrdrListFrame2').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%'>"
	      				  					+ "<table>"
	      				  					+ "<tr>"
	      				  					+ "<td>"+ordrDat+"</td>"
	      				  					+ "<td style='float:right'><a onclick='empOrdrRead("+ordrNumbrSeq+",1)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>주문상세보기 ></a>"
	      				  					+ "</td>"
	      				  					+ "</tr>"
	      				  					+ "</table>"
	      				  					+ "<div data-role='content' style='border-top:1px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
	      				  					+ "<div class='row'>"
	      				  					+ "<div class='col-xs-5'>"
	      				  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
	      				  					+ "</div>"
	      				  					+ "<div class='col-xs-7'>"
	      				  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
	      				  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + "</div>"
	      				  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
	      				  					+ "</div>"
	      				  					+ "</div>"
	      				  					+ "</div>"
	      				  					+ "</div>"
	      				  					+ "<div class='ui-grid-a ui-responsive'>"
	      				  					+ "<div style='width:50%; float:left' class='ui-block-a'><a href='tel:" + recvrMobl + "' rel='external' class='ui-btn ui-btn-raised font-btn clr-primary'>전화 연결</a></div>"
	      				  					+ "<div style='width:50%; float:left' class='ui-block-b'><a onclick='updateOrdrStat(2,"+ordrNumbrSeq+",1)' class='ui-btn ui-btn-raised font-btn clr-primary'>배송 실시</a></div>"
	      				  					+ "</div>"
	      		  )	
      		  }
      		  else{
      			$('#empRealTimeOrdrListFrame2').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%'>"
		  					+ "<table>"
		  					+ "<tr>"
		  					+ "<td>"+ordrDat+"</td>"
		  					+ "<td style='float:right'><a onclick='empOrdrCancelRead("+ordrNumbrSeq+",1)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>취소상세보기 ></a>"
		  					+ "</td>"
		  					+ "</tr>"
		  					+ "</table>"
		  					+ "<div data-role='content' style='border-top:1px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
		  					+ "<div class='row'>"
		  					+ "<div class='col-xs-5'>"
		  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
		  					+ "</div>"
		  					+ "<div class='col-xs-7'>"
		  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
		  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + "</div>"
		  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
		  					+ "</div>"
		  					+ "</div>"
		  					+ "</div>"
		  					+ "</div>"
      			)	
      		  }
      	  });
  		  $("#empRealTimeOrdrListFrame2").listview("refresh");
        },
        error:function(request,status,error){
           alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
     });
}


//숫자 세자리마다 콤마 넣기
function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

//주문내역 상세조회 이동
function empOrdrRead(ordrNumbrSeq,ordrType){
	sessionStorage.setItem('ordrNumbrSeq', ordrNumbrSeq);
	sessionStorage.setItem('ordrType', ordrType); //0은 일반, 1은 공동구매
	$.mobile.changePage("MyPageEmpOrdrRead.html");
}

//주문상세조회
function empOrdrRead2(){
	var ordrNumbrSeq = sessionStorage.getItem('ordrNumbrSeq');
	var ordrType = sessionStorage.getItem('ordrType');
	if(ordrType == 0){
		$.ajax({
	      type: "POST",
	      url:"http://sebm.iptime.org:9124/empOrdrRead.do",   
	      data:{"ordrNumbrSeq":ordrNumbrSeq, "ordrType":ordrType},
	      success:function(ordrMap){
	    	  var vo = ordrMap.empOrdrVo;
	    	  var list = ordrMap.ordrProdctList;
	    	  
			  var ordrNumbrSeq = vo.ordrNumbrSeq;
			  var ordrDat = vo.ordrDat;
			  var recvrNme = vo.recvrNme;
			  var nme = vo.empNme;
			  var empMobl = vo.empMobl;
			  var recvrMobl = vo.recvrMobl;
			  var recvrAddrss = vo.recvrAddrss;
			  var recvrDetlAddrss = vo.recvrDetlAddrss;
			  var recvrAddrssURI = "http://local.daum.net/map/index.jsp?q="+encodeURI(recvrAddrss + " " + recvrDetlAddrss);
			  var allPric = numberWithCommas(vo.allPric);
			  var hopDelvrDat = vo.hopDelvrDat;
			  var ordrStatSeq = vo.ordrStatSeq;
			  if(vo.cartImg1 == null){
      			  var cartImg1 = "../img/no_image.gif";
      		  }
      		  else{
      			  var cartImg1 = "http://sebm.iptime.org:9124/" + vo.cartImg1;  
      		  }    
			  $('#ordrDat').text(ordrDat);
			  $('#ordrNumbrSeq').text("주문번호 "+ordrNumbrSeq);
			  switch (ordrStatSeq) {
				case 1:
					$('#ordrStatSeq').text("주문완료");
					break;
				case 2:
					$('#ordrStatSeq').text("배송중");
					break;
				case 3:
					$('#ordrStatSeq').text("배송완료");
				break;
			  }
			  var source = "";
			  $.each(list, function(i, vo){
				  var prodctNme = vo.prodctNme;
				  var ordrAmont = vo.ordrAmont;
				  var selPric = vo.selPric;
				  var mainImg = "http://sebm.iptime.org:9124/" +  vo.mainImg;
				  source = source + "<li class='li-border'><div class='row'><div class='col-xs-4'><img class='img-center' width='100%' height='100%' src='" + mainImg + "'></div><div class='col-xs-8'><p>" + prodctNme + "</p><p>수량 " + ordrAmont + "개 / " + selPric + "원</p></div></div></li>";				  				    		  
			  });
			  $('#empOrdrReadList').append(source);
			  $('#empOrdrReadList').listview("refresh");
			  $('#recvrNme').val(recvrNme);
			  $('#map').attr("onclick", "window.open('"+recvrAddrssURI+"', '_system');");
			  $('#recvrAddrss').val(recvrAddrss);
			  $('#recvrDetlAddrss').val(recvrDetlAddrss);
			  $('#hopDelvrDat').val(hopDelvrDat);
			  $('#allPric').val(allPric+"원");
			  $('#recvrMobl').val(recvrMobl);
			  $('#nme').val(nme);
			  $('#empMobl').val(empMobl);
			  $('#cartImg1').attr('src',cartImg1);
			  $('#ordrSeq').val(ordrNumbrSeq);
			  sessionStorage.removeItem('ordrNumbrSeq');
	      },
	      error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	      }
	   });
	}
	else{
		$.ajax({
	      type: "POST",
	      url:"http://sebm.iptime.org:9124/empOrdrRead.do",   
	      data:{"ordrNumbrSeq":ordrNumbrSeq, "ordrType":ordrType},
	      success:function(ordrMap){
	    	  var vo = ordrMap.empOrdrVo;
	    	  var list = ordrMap.ordrProdctList;
	    	  
			  var ordrNumbrSeq = vo.ordrNumbrSeq;
			  var ordrDat = vo.ordrDat;
			  var recvrNme = vo.recvrNme;
			  var nme = vo.empNme;
			  var empMobl = vo.empMobl;
			  var recvrMobl = vo.recvrMobl;
			  var recvrAddrss = vo.recvrAddrss;
			  var recvrAddrssURI = "http://local.daum.net/map/index.jsp?q="+encodeURI(recvrAddrss);
			  var allPric = numberWithCommas(vo.allPric);
			  var hopDelvrDat = vo.hopDelvrDat;
			  var ordrStatSeq = vo.ordrStatSeq;
			  if(vo.cartImg1 == null){
      			  var cartImg1 = "../img/no_image.gif";
      		  }
      		  else{
      			  var cartImg1 = "http://sebm.iptime.org:9124/" + vo.cartImg1;  
      		  }    
			  $('#ordrDat').text(ordrDat);
			  $('#ordrNumbrSeq').text("주문번호 "+ordrNumbrSeq);
			  switch (ordrStatSeq) {
				case 1:
					$('#ordrStatSeq').text("주문완료");
					break;
				case 2:
					$('#ordrStatSeq').text("배송중");
					break;
				case 3:
					$('#ordrStatSeq').text("배송완료");
				break;
			  }
			  var source = "";
			  $.each(list, function(i, vo){
				  var prodctNme = vo.prodctNme;
				  var ordrAmont = vo.ordrAmont;
				  var selPric = vo.selPric;
				  var mainImg = "http://sebm.iptime.org:9124/" +  vo.mainImg;
				  source = source + "<li class='li-border'><div class='row'><div class='col-xs-4'><img class='img-center' width='100%' height='100%' src='" + mainImg + "'></div><div class='col-xs-8'><p>" + prodctNme + "</p><p>수량 " + ordrAmont + "개 / " + selPric + "원</p></div></div></li>";				  				    		  
			  });
			  $('#empOrdrReadList').append(source);
			  $('#empOrdrReadList').listview("refresh");
			  $('#recvrNme').val(recvrNme);
			  $('#map').attr("onclick", "window.open('"+recvrAddrssURI+"', '_system');");
			  $('#recvrAddrss').val(recvrAddrss);
			  $('#hopDelvrDat').val(hopDelvrDat);
			  $('#allPric').val(allPric+"원");
			  $('#recvrMobl').val(recvrMobl);
			  $('#nme').val(nme);
			  $('#empMobl').val(empMobl);
			  $('#cartImg1').attr('src',cartImg1);
			  sessionStorage.removeItem('ordrNumbrSeq');
	      },
	      error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	      }
	   });
	}
}

//실시간 주문목록 조회
function realTimeOrdr(){
	realTimRefresh = setInterval(function(){refreshRealTimeOrdrList()},10000); //10초마다 새로고침
	$.ajax({
        type: "POST",
        url:"http://sebm.iptime.org:9124/empRealTimeOrdrList.do",  
        success:function(data){
        	$.each(data, function(i, vo){
      		  var ordrNumbrSeq = vo.ordrNumbrSeq;
      		  var ordrDat = vo.ordrDat;
      		  var ordrStat = vo.ordrStat;
      		  var prodctNme = vo.prodctNme;
      		  if(vo.cartImg1 == null){
    			  var cartImg1 = "../img/no_image.gif";
    		  }
    		  else{
    			  var cartImg1 = "http://sebm.iptime.org:9124/" + vo.cartImg1;  
    		  }       
      		  var recvrMobl = vo.recvrMobl;
      		  var ordrProdctAmont = vo.ordrProdctAmont;
      		  var allPric = numberWithCommas(vo.allPric);
      		  if(ordrStat=='주문접수'){
	      		  $('#empRealTimeOrdrListFrame').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%'>"
	      				  					+ "<table>"
	      				  					+ "<tr>"
	      				  					+ "<td>"+ordrDat+"</td>"
	      				  					+ "<td style='float:right'><a onclick='empOrdrRead("+ordrNumbrSeq+",0)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>주문상세보기 ></a>"
	      				  					+ "</td>"
	      				  					+ "</tr>"
	      				  					+ "</table>"
	      				  					+ "<div data-role='content' style='border-top:1px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
	      				  					+ "<div class='row'>"
	      				  					+ "<div class='col-xs-5'>"
	      				  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
	      				  					+ "</div>"
	      				  					+ "<div class='col-xs-7'>"
	      				  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
	      				  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + " 외 " + ordrProdctAmont + "건 </div>"
	      				  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
	      				  					+ "</div>"
	      				  					+ "</div>"
	      				  					+ "</div>"
	      				  					+ "</div>"
	      				  					+ "<div class='ui-grid-a ui-responsive'>"
	      				  					+ "<div style='width:50%; float:left' class='ui-block-a'><a href='tel:" + recvrMobl + "' rel='external' class='ui-btn ui-btn-raised font-btn clr-primary'>전화 연결</a></div>"
	      				  					+ "<div style='width:50%; float:left' class='ui-block-b'><a onclick='updateOrdrStat(2,"+ordrNumbrSeq+",0)' class='ui-btn ui-btn-raised font-btn clr-primary'>배송 실시</a></div>"	  
	      				  					+ "</div>"
	      		  )	
      		  }
      		  else{
      			$('#empRealTimeOrdrListFrame').append("<div data-role='content' style='padding-left:0px; padding-right:0px;'>"
		  					+ "<table>"
		  					+ "<tr>"
		  					+ "<td>"+ordrDat+"</td>"
		  					+ "<td style='float:right'><a onclick='empOrdrCancelRead("+ordrNumbrSeq+",0)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>취소상세보기 ></a>"
		  					+ "</td>"
		  					+ "</tr>"
		  					+ "</table>"
		  					+ "<div data-role='content' style='border-top:1px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
		  					+ "<div class='row'>"
		  					+ "<div class='col-xs-5'>"
		  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
		  					+ "</div>"
		  					+ "<div class='col-xs-7'>"
		  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary'>"+ordrStat+"</a></p>"
		  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + " 외 " + ordrProdctAmont + "건 </div>"
		  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
		  					+ "</div>"
		  					+ "</div>"
		  					+ "</div>"
		  					+ "</div>"
      			)	
      		  }
      	  });
  		  $("#empRealTimeOrdrListFrame").listview("refresh");
        },
        error:function(request,status,error){
           alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
     });
	//실시간 공동구매 조회
	$.ajax({
        type: "POST",
        url:"http://sebm.iptime.org:9124/empDahamgaeRealTimeOrdrList.do",  
        success:function(data){
        	$.each(data, function(i, vo){
      		  var ordrNumbrSeq = vo.ordrNumbrSeq;
      		  var ordrDat = vo.ordrDat;
      		  var ordrStat = vo.ordrStat;
      		  var prodctNme = vo.prodctNme;
      		  if(vo.cartImg1 == null){
    			  var cartImg1 = "../img/no_image.gif";
    		  }
    		  else{
    			  var cartImg1 = "http://sebm.iptime.org:9124/" + vo.cartImg1;  
    		  }       
      		  var recvrMobl = vo.recvrMobl;
      		  var allPric = numberWithCommas(vo.allPric);
      		  if(ordrStat=='주문접수'){
	      		  $('#empRealTimeOrdrListFrame2').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%'>"
	      				  					+ "<table>"
	      				  					+ "<tr>"
	      				  					+ "<td>"+ordrDat+"</td>"
	      				  					+ "<td style='float:right'><a onclick='empOrdrRead("+ordrNumbrSeq+",1)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>주문상세보기 ></a>"
	      				  					+ "</td>"
	      				  					+ "</tr>"
	      				  					+ "</table>"
	      				  					+ "<div data-role='content' style='border-top:1px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
	      				  					+ "<div class='row'>"
	      				  					+ "<div class='col-xs-5'>"
	      				  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
	      				  					+ "</div>"
	      				  					+ "<div class='col-xs-7'>"
	      				  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
	      				  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + "</div>"
	      				  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
	      				  					+ "</div>"
	      				  					+ "</div>"
	      				  					+ "</div>"
	      				  					+ "</div>"
	      				  					+ "<div class='ui-grid-a ui-responsive'>"
	      				  					+ "<div style='width:50%; float:left' class='ui-block-a'><a href='tel:" + recvrMobl + "' rel='external' class='ui-btn ui-btn-raised font-btn clr-primary'>전화 연결</a></div>"
	      				  					+ "<div style='width:50%; float:left' class='ui-block-b'><a onclick='updateOrdrStat(2,"+ordrNumbrSeq+",1)' class='ui-btn ui-btn-raised font-btn clr-primary'>배송 실시</a></div>"
	      				  					+ "</div>"
	      		  )	
      		  }
      		  else{
      			$('#empRealTimeOrdrListFrame2').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%'>"
		  					+ "<table>"
		  					+ "<tr>"
		  					+ "<td>"+ordrDat+"</td>"
		  					+ "<td style='float:right'><a onclick='empOrdrCancelRead("+ordrNumbrSeq+",1)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>취소상세보기 ></a>"
		  					+ "</td>"
		  					+ "</tr>"
		  					+ "</table>"
		  					+ "<div data-role='content' style='border-top:1px solid #F6F6F6; border-bottom:1px solid #F6F6F6'ㄴ>"
		  					+ "<div class='row'>"
		  					+ "<div class='col-xs-5'>"
		  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
		  					+ "</div>"
		  					+ "<div class='col-xs-7'>"
		  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
		  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + "</div>"
		  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
		  					+ "</div>"
		  					+ "</div>"
		  					+ "</div>"
		  					+ "</div>"
      			)	
      		  }
      	  });
  		  $("#empRealTimeOrdrListFrame2").listview("refresh");
        },
        error:function(request,status,error){
           alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
     });
}

//실시간 주문목록 페이지 숨겨지면 새로고침 함수 제거
$(document).on("pagehide","#empRealTimeOrdrListPage",function(){
	clearInterval(realTimRefresh);
});

//직원추천
function recmndEmp(empSeq){
	var empNme = $('#empSeq'+empSeq).val();
	var getEmpSeq = empSeq;
	var sessionEmpSeq = sessionStorage.getItem('empSeq');
	if(sessionEmpSeq!=null){
		var giveEmpSeq = sessionStorage.getItem('empSeq');
	}
	else{
		var giveEmpSeq = localStorage.getItem('empSeq');
	}
	
	navigator.notification.confirm(
		empNme+" 직원을 추천하시겠습니까?", // message
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

//직원추천 목록조회
function recmndList(){
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
        	var length = data.length;
        	var source = "<li><div class='font-sm center-xs'><strong>이번 달 추천하고 싶은 동료를 뽑아주세요!<br>투표결과는 '이달의 대장남' 선정에 반영됩니다.</strong></div></li>";
        	$.each(data, function(i, vo){
      		  var nme = vo.nme;
      		  var empSeq = vo.empSeq;
      		  var duty = vo.duty;
      		  var task = vo.task;
      		  var imgRot = "http://sebm.iptime.org:9124/" + vo.imgRot;
      		  if (i%2 == 0){
      			  source = source + "<li><div class='row'>";
      		  }
      		  source = source + "<div class='col-xs-6 center-xs'>"
      		  			+ "<input type='hidden' id='empSeq"+empSeq+"' value='"+nme+"'>"
      		  			+ "<div onclick='javascript:recmndEmp("+empSeq+")'><img src='" + imgRot + "' width='145px' height='195px'></div>"
      		  			+ "<p><strong>" + nme + "</strong></p>"
      		  			+ "<p>" + duty + " / " + task + "</p></div>";
      		  if (i%2 != 0 || (length-1) == i){
      			  source = source + "</div></li>";
      		  }
        	});
        	$('#empRecmndListFrame').append(source);
        	$('#empRecmndListFrame').listview("refresh");
        },
        error:function(request,status,error){
           alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
     });
}

//배송목록조회(주문접수~배송중)
function shipngList(){
	//버튼별 배송조회
	$("input[name='ordrStatSeq']").on("change", function() {
        var ordrStatSeq = $("input[name='ordrStatSeq']:checked").val();
        $.ajax({
            type: "POST",
            url:"http://sebm.iptime.org:9124/empShipngList.do",   
            data:{"ordrStatSeq":ordrStatSeq},
            success:function(data){
          	  	$('#empShipngFrame').empty();
            	$.each(data, function(i, vo){
          		  var ordrNumbrSeq = vo.ordrNumbrSeq;
          		  var ordrDat = vo.ordrDat;
          		  var ordrStat = vo.ordrStat;
          		  var prodctNme = vo.prodctNme;
          		  if(vo.cartImg1 == null){
          			  var cartImg1 = "../img/no_image.gif";
          		  }
          		  else{
          			  var cartImg1 = "http://sebm.iptime.org:9124/" + vo.cartImg1;  
          		  }          		  
          		  var recvrMobl = vo.recvrMobl;
          		  var ordrProdctAmont = vo.ordrProdctAmont;
          		  var allPric = numberWithCommas(vo.allPric);
          		  var shipngStat = "배송 실시";
          		  var shipngCount = 2;
	      		  switch (ordrStat) {
	  				case '배송중':
	  					shipngStat = "배송 완료";
	  					shipngCount = 3;
	  					break;
	  				case '배송완료':
	  					shipngStat = "완료";
	  					shipngCount = 4;
	      		  }
	      		  if(i!=0){
	      			$('#empShipngFrame').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%; margin-top:10%'>"
			  					+ "<table>"
			  					+ "<tr>"
			  					+ "<td>"+ordrDat+"</td>"
			  					+ "<td style='float:right'><a onclick='empOrdrRead("+ordrNumbrSeq+",0)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>주문상세보기 ></a>"
			  					+ "</td>"
			  					+ "</tr>"
			  					+ "</table>"
			  					+ "<div data-role='content' style='border-top:3px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
			  					+ "<div class='row'>"
			  					+ "<div class='col-xs-5'>"
			  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
			  					+ "</div>"
			  					+ "<div class='col-xs-7'>"
			  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
			  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + " 외 " + ordrProdctAmont + "건 </div>"
			  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "<div class='ui-grid-a ui-responsive'>"
			  					+ "<div style='width:50%; float:left' class='ui-block-a'><a href='tel:" + recvrMobl + "' rel='external' class='ui-btn ui-btn-raised font-btn clr-primary'>전화 연결</a></div>"
			  					+ "<div style='width:50%; float:left' class='ui-block-b'><a onclick='updateOrdrStat("+shipngCount+","+ordrNumbrSeq+",0)' class='ui-btn ui-btn-raised font-btn clr-primary'>"+shipngStat+"</a></div>"	  
			  					+ "</div>"
	      				)
	      		  }
	      		  else{
	      			$('#empShipngFrame').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%'>"
			  					+ "<table>"
			  					+ "<tr>"
			  					+ "<td>"+ordrDat+"</td>"
			  					+ "<td style='float:right'><a onclick='empOrdrRead("+ordrNumbrSeq+",0)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>주문상세보기 ></a>"
			  					+ "</td>"
			  					+ "</tr>"
			  					+ "</table>"
			  					+ "<div data-role='content' style='border-top:3px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
			  					+ "<div class='row'>"
			  					+ "<div class='col-xs-5'>"
			  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
			  					+ "</div>"
			  					+ "<div class='col-xs-7'>"
			  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
			  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + " 외 " + ordrProdctAmont + "건 </div>"
			  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "<div class='ui-grid-a ui-responsive'>"
			  					+ "<div style='width:50%; float:left' class='ui-block-a'><a href='tel:" + recvrMobl + "' rel='external' class='ui-btn ui-btn-raised font-btn clr-primary'>전화 연결</a></div>"
			  					+ "<div style='width:50%; float:left' class='ui-block-b'><a onclick='updateOrdrStat("+shipngCount+","+ordrNumbrSeq+",0)' class='ui-btn ui-btn-raised font-btn clr-primary'>"+shipngStat+"</a></div>"	  
			  					+ "</div>"
	      				)
	      		  }          		  	
          	  });
      		  $("#empShipngFrame").listview("refresh");
      		  sessionStorage.removeItem('ordrStatSeq');
            },
            error:function(request,status,error){
               alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
         });
	});
	$("input[name='ordrStatSeq2']").on("change", function() {
        var ordrStatSeq = $("input[name='ordrStatSeq2']:checked").val();
        //버튼별 공동구매 배달목록 조회
        $.ajax({
            type: "POST",
            url:"http://sebm.iptime.org:9124/empDahamgaeShipngList.do",   
            data:{"ordrStatSeq":ordrStatSeq},
            success:function(data){
          	  	$('#empShipngFrame2').empty();
            	$.each(data, function(i, vo){
          		  var ordrNumbrSeq = vo.ordrNumbrSeq;
          		  var ordrDat = vo.ordrDat;
          		  var ordrStat = vo.ordrStat;
          		  var prodctNme = vo.prodctNme;
          		if(vo.cartImg1 == null){
        			  var cartImg1 = "../img/no_image.gif";
        		  }
        		  else{
        			  var cartImg1 = "http://sebm.iptime.org:9124/" + vo.cartImg1;  
        		  }       
          		  var recvrMobl = vo.recvrMobl;
          		  var allPric = numberWithCommas(vo.allPric);
          		  var shipngStat = "배송 실시";
          		  var shipngCount = 2;
	      		  switch (ordrStat) {
	  				case '배송중':
	  					shipngStat = "배송 완료";
	  					shipngCount = 3;
	  					break;
	  				case '배송완료':
	  					shipngStat = "완료";
	  					shipngCount = 4;
	      		  }
	      		  if(i!=0){
	          		  $('#empShipngFrame2').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%; margin-top:10%'>"
	          				  					+ "<table>"
	          				  					+ "<tr>"
	          				  					+ "<td>"+ordrDat+"</td>"
	          				  					+ "<td style='float:right'><a onclick='empOrdrRead("+ordrNumbrSeq+",1)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>주문상세보기 ></a>"
	          				  					+ "</td>"
	          				  					+ "</tr>"
	          				  					+ "</table>"
	          				  					+ "<div data-role='content' style='border-top:1px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
	          				  					+ "<div class='row'>"
	          				  					+ "<div class='col-xs-5'>"
	          				  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
	          				  					+ "</div>"
	          				  					+ "<div class='col-xs-7'>"
	          				  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
	          				  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + "</div>"
	          				  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
	          				  					+ "</div>"
	          				  					+ "</div>"
	          				  					+ "</div>"
	          				  					+ "</div>"
	          				  					+ "<div class='ui-grid-a ui-responsive'>"
	          				  					+ "<div style='width:50%; float:left' class='ui-block-a'><a href='tel:" + recvrMobl + "' rel='external' class='ui-btn ui-btn-raised font-btn clr-primary'>전화 연결</a></div>"
	          				  					+ "<div style='width:50%; float:left' class='ui-block-b'><a onclick='updateOrdrStat("+shipngCount+","+ordrNumbrSeq+",1)' class='ui-btn ui-btn-raised font-btn clr-primary'>"+shipngStat+"</a></div>"	  
	          				  					+ "</div>"
	          		  )	
	      		  }
	      		  else{
	      			  $('#empShipngFrame2').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%'>"
			  					+ "<table>"
			  					+ "<tr>"
			  					+ "<td>"+ordrDat+"</td>"
			  					+ "<td style='float:right'><a onclick='empOrdrRead("+ordrNumbrSeq+",1)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>주문상세보기 ></a>"
			  					+ "</td>"
			  					+ "</tr>"
			  					+ "</table>"
			  					+ "<div data-role='content' style='border-top:1px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
			  					+ "<div class='row'>"
			  					+ "<div class='col-xs-5'>"
			  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
			  					+ "</div>"
			  					+ "<div class='col-xs-7'>"
			  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
			  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + "</div>"
			  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "<div class='ui-grid-a ui-responsive'>"
			  					+ "<div style='width:50%; float:left' class='ui-block-a'><a href='tel:" + recvrMobl + "' rel='external' class='ui-btn ui-btn-raised font-btn clr-primary'>전화 연결</a></div>"
			  					+ "<div style='width:50%; float:left' class='ui-block-b'><a onclick='updateOrdrStat("+shipngCount+","+ordrNumbrSeq+",1)' class='ui-btn ui-btn-raised font-btn clr-primary'>"+shipngStat+"</a></div>"	  
			  					+ "</div>"
	      				)	
	      		  }
          	  });
      		  $("#empShipngFrame2").listview("refresh");
      		  sessionStorage.removeItem('ordrStatSeq');
            },
            error:function(request,status,error){
               alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
         });
	});
	//페이지 처음 조회
	var ordrStatSeq = sessionStorage.getItem('ordrStatSeq');
	switch (ordrStatSeq) {
	case '0':
		$("#ordrStatAll").attr('checked',true).checkboxradio('refresh');
		$("#ordrStatAll2").attr('checked',true).checkboxradio('refresh');
		break;
	case '1':
		$("#ordrStatAccept").attr('checked',true).checkboxradio('refresh');
		$("#ordrStatAccept2").attr('checked',true).checkboxradio('refresh');
		break;
	case '2':
		$("#ordrStatShipng").attr('checked',true).checkboxradio('refresh');
		$("#ordrStatShipng2").attr('checked',true).checkboxradio('refresh');
		break;
	case '3':
		$("#ordrStatFinish").attr('checked',true).checkboxradio('refresh');
		$("#ordrStatFinish2").attr('checked',true).checkboxradio('refresh');
		break;
	}
	//일반 배송목록 조회
	$.ajax({
      type: "POST",
      url:"http://sebm.iptime.org:9124/empShipngList.do",   
      data:{"ordrStatSeq":ordrStatSeq},
      success:function(data){
    	  $.each(data, function(i, vo){
    		  var ordrNumbrSeq = vo.ordrNumbrSeq;
    		  var ordrDat = vo.ordrDat;
    		  var ordrStat = vo.ordrStat;
    		  var prodctNme = vo.prodctNme;
    		  if(vo.cartImg1 == null){
      			  var cartImg1 = "../img/no_image.gif";
      		  }
      		  else{
      			  var cartImg1 = "http://sebm.iptime.org:9124/" + vo.cartImg1;  
      		  }       
    		  var recvrMobl = vo.recvrMobl;
    		  var ordrProdctAmont = vo.ordrProdctAmont;
    		  var allPric = numberWithCommas(vo.allPric);
    		  var shipngStat = "배송 실시";
      		  var shipngCount = 2;
      		  switch (ordrStat) {
  				case '배송중':
  					shipngStat = "배송 완료";
  					shipngCount = 3;
  					break;
  				case '배송완료':
  					shipngStat = "완료";
  					shipngCount = 4;
      		  }
      		  if(i!=0){
	    		  $('#empShipngFrame').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%; margin-top:10%'>"
	    				  					+ "<table>"
	    				  					+ "<tr>"
	    				  					+ "<td>"+ordrDat+"</td>"
	    				  					+ "<td style='float:right'><a onclick='empOrdrRead("+ordrNumbrSeq+",0)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>주문상세보기 ></a>"
	    				  					+ "</td>"
	    				  					+ "</tr>"
	    				  					+ "</table>"
	    				  					+ "<div data-role='content' style='border-top:1px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
	    				  					+ "<div class='row'>"
	    				  					+ "<div class='col-xs-5'>"
	    				  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
	    				  					+ "</div>"
	    				  					+ "<div class='col-xs-7'>"
	    				  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
	    				  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + " 외 " + ordrProdctAmont + "건 </div>"
	    				  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
	    				  					+ "</div>"
	    				  					+ "</div>"
	    				  					+ "</div>"
	    				  					+ "</div>"
	    				  					+ "<div class='ui-grid-a ui-responsive'>"
	    				  					+ "<div style='width:50%; float:left' class='ui-block-a'><a href='tel:" + recvrMobl + "' rel='external' class='ui-btn ui-btn-raised font-btn clr-primary'>전화 연결</a></div>"
	    				  					+ "<div style='width:50%; float:left' class='ui-block-b'><a onclick='updateOrdrStat("+shipngCount+","+ordrNumbrSeq+",0)' class='ui-btn ui-btn-raised font-btn clr-primary'>"+shipngStat+"</a></div>"	  
	    				  					+ "</div>"
	    		  )
      		  }
      		  else{
      			  $('#empShipngFrame').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%'>"
	  					+ "<table>"
	  					+ "<tr>"
	  					+ "<td>"+ordrDat+"</td>"
	  					+ "<td style='float:right'><a onclick='empOrdrRead("+ordrNumbrSeq+",0)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>주문상세보기 ></a>"
	  					+ "</td>"
	  					+ "</tr>"
	  					+ "</table>"
	  					+ "<div data-role='content' style='border-top:1px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
	  					+ "<div class='row'>"
	  					+ "<div class='col-xs-5'>"
	  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
	  					+ "</div>"
	  					+ "<div class='col-xs-7'>"
	  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
	  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + " 외 " + ordrProdctAmont + "건 </div>"
	  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
	  					+ "</div>"
	  					+ "</div>"
	  					+ "</div>"
	  					+ "</div>"
	  					+ "<div class='ui-grid-a ui-responsive'>"
	  					+ "<div style='width:50%; float:left' class='ui-block-a'><a href='tel:" + recvrMobl + "' rel='external' class='ui-btn ui-btn-raised font-btn clr-primary'>전화 연결</a></div>"
	  					+ "<div style='width:50%; float:left' class='ui-block-b'><a onclick='updateOrdrStat("+shipngCount+","+ordrNumbrSeq+",0)' class='ui-btn ui-btn-raised font-btn clr-primary'>"+shipngStat+"</a></div>"	  
	  					+ "</div>"
      			  )
      		  }
    	  });
		  $("#empShipngFrame").listview("refresh");
		  sessionStorage.removeItem('ordrStatSeq');
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
	//공동구매 배송목록 조회
	$.ajax({
      type: "POST",
      url:"http://sebm.iptime.org:9124/empDahamgaeShipngList.do",   
      data:{"ordrStatSeq":ordrStatSeq},
      success:function(data){
    	  $.each(data, function(i, vo){
    		  var ordrNumbrSeq = vo.ordrNumbrSeq;
    		  var ordrDat = vo.ordrDat;
    		  var ordrStat = vo.ordrStat;
    		  var prodctNme = vo.prodctNme;
    		  if(vo.cartImg1 == null){
      			  var cartImg1 = "../img/no_image.gif";
      		  }
      		  else{
      			  var cartImg1 = "http://sebm.iptime.org:9124/" + vo.cartImg1;  
      		  }       
    		  var recvrMobl = vo.recvrMobl;
    		  var allPric = numberWithCommas(vo.allPric);
    		  var shipngStat = "배송 실시";
      		  var shipngCount = 2;
      		  switch (ordrStat) {
  				case '배송중':
  					shipngStat = "배송 완료";
  					shipngCount = 3;
  					break;
  				case '배송완료':
  					shipngStat = "완료";
  					shipngCount = 4;
      		  }
      		  if(i!=0){
      			  $('#empShipngFrame2').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%; margin-top:10%'>"
	  					+ "<table>"
	  					+ "<tr>"
	  					+ "<td>"+ordrDat+"</td>"
	  					+ "<td style='float:right'><a onclick='empOrdrRead("+ordrNumbrSeq+",1)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>주문상세보기 ></a>"
	  					+ "</td>"
	  					+ "</tr>"
	  					+ "</table>"
	  					+ "<div data-role='content' style='border-top:1px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
	  					+ "<div class='row'>"
	  					+ "<div class='col-xs-5'>"
	  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
	  					+ "</div>"
	  					+ "<div class='col-xs-7'>"
	  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
	  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + "</div>"
	  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
	  					+ "</div>"
	  					+ "</div>"
	  					+ "</div>"
	  					+ "</div>"
	  					+ "<div class='ui-grid-a ui-responsive'>"
	  					+ "<div style='width:50%; float:left' class='ui-block-a'><a href='tel:" + recvrMobl + "' rel='external' class='ui-btn ui-btn-raised font-btn clr-primary'>전화 연결</a></div>"
	  					+ "<div style='width:50%; float:left' class='ui-block-b'><a onclick='updateOrdrStat("+shipngCount+","+ordrNumbrSeq+",1)' class='ui-btn ui-btn-raised font-btn clr-primary'>"+shipngStat+"</a></div>"	  
	  					+ "</div>"
  					)
      		  }
      		  else{
      			  $('#empShipngFrame2').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%'>"
	  					+ "<table>"
	  					+ "<tr>"
	  					+ "<td>"+ordrDat+"</td>"
	  					+ "<td style='float:right'><a onclick='empOrdrRead("+ordrNumbrSeq+",1)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>주문상세보기 ></a>"
	  					+ "</td>"
	  					+ "</tr>"
	  					+ "</table>"
	  					+ "<div data-role='content' style='border-top:1px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
	  					+ "<div class='row'>"
	  					+ "<div class='col-xs-5'>"
	  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
	  					+ "</div>"
	  					+ "<div class='col-xs-7'>"
	  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>"+ordrStat+"</a></p>"
	  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + "</div>"
	  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
	  					+ "</div>"
	  					+ "</div>"
	  					+ "</div>"
	  					+ "</div>"
	  					+ "<div class='ui-grid-a ui-responsive'>"
	  					+ "<div style='width:50%; float:left' class='ui-block-a'><a href='tel:" + recvrMobl + "' rel='external' class='ui-btn ui-btn-raised font-btn clr-primary'>전화 연결</a></div>"
	  					+ "<div style='width:50%; float:left' class='ui-block-b'><a onclick='updateOrdrStat("+shipngCount+","+ordrNumbrSeq+",1)' class='ui-btn ui-btn-raised font-btn clr-primary'>"+shipngStat+"</a></div>"	  
	  					+ "</div>"
      				)
      		  }
    	  });
		  $("#empShipngFrame2").listview("refresh");
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}

//마이페이지 관리자 - 배송조회 각각 조회
function selectShipngList(ordrStatSeq){
	switch (ordrStatSeq) {
	case 0: //전체조회
		sessionStorage.setItem('ordrStatSeq', 0);
		$.mobile.changePage("MyPageEmpShipngList.html");
		break;
	case 1: //주문완료
		sessionStorage.setItem('ordrStatSeq', 1);
		$.mobile.changePage("MyPageEmpShipngList.html");
		break;
	case 2: //배송중
		sessionStorage.setItem('ordrStatSeq', 2);
		$.mobile.changePage("MyPageEmpShipngList.html");
		break;
	case 3: //배송완료
		sessionStorage.setItem('ordrStatSeq', 3);
		$.mobile.changePage("MyPageEmpShipngList.html");
		break;
	case 4: //메인 패널에서 이동
		sessionStorage.setItem('ordrStatSeq', 0);
		$.mobile.changePage("MyPageEmpShipngList.html");
		break;
	}
}

//배송 상태 변경
function updateOrdrStat(shipngCount, ordrNumbrSeq, ordrType){
	var sessionEmpSeq = sessionStorage.getItem('empSeq');
	if(sessionEmpSeq!=null){
		var empSeq = sessionStorage.getItem('empSeq');
	}
	else{
		var empSeq = localStorage.getItem('empSeq');
	}
	var sendTlt;
	var sendMsg;
	var type;
	var d = new Date();
    var time = d.getFullYear() + "년 " + (d.getMonth() + 1) + "월 " + d.getDate() + "일 " + d.getHours() + "시 " + d.getMinutes() + "분";
   
	switch (shipngCount) {
	case 2:
		$.ajax({
	      type: "POST",
	      url:"http://sebm.iptime.org:9124/ordrStatUpdate.do",   
	      data:{"ordrNumbrSeq":ordrNumbrSeq, "empSeq":empSeq},
	      success:function(data){
	    	  sendTlt = "배송 시작 알림";
	    	  sendMsg = time + "에 배송이 시작되었습니다.";
	    	  type = "ordrStat";
	    	  
		  		$.ajax({
		  	      type: "POST",
		  	      data:{"ordrNumbrSeq":ordrNumbrSeq,"ordrType":ordrType, "sendTlt":sendTlt, "sendMsg":sendMsg, "type":type},
		  	      url:"http://sebm.iptime.org:9124/pushOrdrStat.do",   
		  	      success:function(data){
			    	  navigator.notification.alert("배송중으로 상태가 변경되었습니다.",null,"배송 상태 변경 알림","확인");
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
		$.mobile.changePage(window.location.href, {
	        allowSamePageTransition: true,
	        transition: 'none',
	        reloadPage: true
	    });
		break;
	case 3:
		$.ajax({
	      type: "POST",
	      url:"http://sebm.iptime.org:9124/ordrStatUpdate.do",   
	      data:{"ordrNumbrSeq":ordrNumbrSeq, "empSeq":0},
	      success:function(data){
	    	  navigator.notification.alert("배송 완료로 상태가 변경되었습니다.",null,"배송 상태 변경 알림","확인");
	      },
	      error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	      }
	   });
		$.mobile.changePage(window.location.href, {
	        allowSamePageTransition: true,
	        transition: 'none',
	        reloadPage: true
	    });
		break;
	case 4:
		navigator.notification.alert("모든 과정이 완료되었습니다.",null,"배송 상태 변경 알림","확인");
		break;
	}	
}

//주문 취소 상세조회 이동
function empOrdrCancelRead(ordrNumbrSeq, ordrType){
	sessionStorage.setItem('ordrNumbrSeq', ordrNumbrSeq);
	sessionStorage.setItem('ordrType', ordrType);
	$.mobile.changePage("MyPageEmpOrdrCancelRead.html");
}


//주문 취소 상세 조회
function ordrCancelRead(){
	var ordrNumbrSeq = sessionStorage.getItem('ordrNumbrSeq');
	var ordrType = sessionStorage.getItem('ordrType');
	if(ordrType==0){ //일반 상품
		$.ajax({
	      type: "POST",
	      url:"http://sebm.iptime.org:9124/empOrdrCancelRead.do",   
	      data:{"ordrNumbrSeq":ordrNumbrSeq},
	      success:function(ordrMap){
	    	  var vo = ordrMap.empOrdrVo;
	    	  var list = ordrMap.ordrCancelProdctList;
	    	  
			  var ordrNumbrSeq = vo.ordrNumbrSeq;
			  var ordrDat = vo.ordrDat;
			  var ordrPamntDat = vo.ordrPamntDat;
			  var recvrNme = vo.recvrNme;
			  var recvrMobl = vo.recvrMobl;
			  var recvrAddrss = vo.recvrAddrss;
			  var recvrDetlAddrss = vo.recvrDetlAddrss;
			  var allPric = numberWithCommas(vo.allPric);
			  var ordrCanlDat = vo.ordrCanlDat;
			  var source = "<li style='background:#F6F6F6'><font style='float:left' size='2'>"+ordrDat+"</font><font style='float:right' size='2'>주문번호 "+ordrNumbrSeq+"</font></li>";
			  $.each(list, function(i, vo){
				  var prodctNme = vo.prodctNme;
				  var ordrAmont = vo.ordrAmont;
				  var selPric = vo.selPric;
				  var mainImg = "http://sebm.iptime.org:9124/" +  vo.mainImg;
				  source = source + "<li class='li-border'><img src='" + mainImg + "'><p>" + prodctNme + "</p><p>수량 " + ordrAmont + "개 / " + selPric + "원</p></li>";				  				    		  
			  });
			  source = source + "<li><div data-role='content' style='border-bottom: 1px dashed'>"
			  			+ "<p>총 취소 금액 <font style='float:right' size='4'><strong>" + allPric + "원</strong></font></p></div></li>"
			  			+ "<li><p><strong>취소 날짜</strong><font style='float:right'>" + ordrCanlDat + "</font></p>"
			  			+ "<p><strong>받는 사람</strong><font style='float:right'>" + recvrNme + "</font></p>"
			  			+ "<p><strong>전화번호</strong><font style='float:right'>" + recvrMobl + "</font></p>" 
			  			+ "<p><strong>고객 주소</strong><font style='float:right'><marquee behavior='alternate' scrollamount='3' direction='right'>" + recvrAddrss + " " + recvrDetlAddrss + "</marquee></font></p></li>"
			  $('#empOrdrCancelReadList').append(source);	
			  $("#empOrdrCancelReadList").listview("refresh");	  
			  sessionStorage.removeItem('ordrNumbrSeq');
	      },
	      error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	      }
	   });
	}
	else{ //공동구매 상품
		$.ajax({
	      type: "POST",
	      url:"http://sebm.iptime.org:9124/empDahamgaeOrdrCancelRead.do",   
	      data:{"ordrNumbrSeq":ordrNumbrSeq},
	      success:function(ordrMap){
	    	  var vo = ordrMap.empOrdrVo;
	    	  var list = ordrMap.ordrCancelProdctList;
	    	  
			  var ordrNumbrSeq = vo.ordrNumbrSeq;
			  var ordrDat = vo.ordrDat;
			  var ordrPamntDat = vo.ordrPamntDat;
			  var recvrNme = vo.recvrNme;
			  var recvrMobl = vo.recvrMobl;
			  var recvrAddrss = vo.recvrAddrss;
			  var recvrDetlAddrss = vo.recvrDetlAddrss;
			  var allPric = numberWithCommas(vo.allPric);
			  var ordrCanlDat = vo.ordrCanlDat;
			  var source = "<li style='background:#F6F6F6'><font style='float:left' size='2'>"+ordrDat+"</font><font style='float:right' size='2'>주문번호 "+ordrNumbrSeq+"</font></li>";
			  $.each(list, function(i, vo){
				  var prodctNme = vo.prodctNme;
				  var ordrAmont = vo.ordrAmont;
				  var selPric = vo.selPric;
				  var mainImg = "http://sebm.iptime.org:9124/" + vo.mainImg;
				  source = source + "<li class='li-border'><img src='" + mainImg + "'><p>" + prodctNme + "</p><p>수량 " + ordrAmont + "개 / " + selPric + "원</p></li>";				  				    		  
			  });
			  source = source + "<li><div data-role='content' style='border-bottom: 1px dashed'>"
			  			+ "<p>총 취소 금액 <font style='float:right' size='4'><strong>" + allPric + "원</strong></font></p></div></li>"
			  			+ "<li><p><strong>취소 날짜</strong><font style='float:right'>" + ordrCanlDat + "</font></p>"
			  			+ "<p><strong>받는 사람</strong><font style='float:right'>" + recvrNme + "</font></p>"
			  			+ "<p><strong>전화번호</strong><font style='float:right'>" + recvrMobl + "</font></p>" 
			  			+ "<p><strong>고객 주소</strong><font style='float:right'><marquee behavior='alternate' scrollamount='3' direction='right'>" + recvrAddrss + " " + recvrDetlAddrss + "</marquee></font></p></li>"
			  $('#empOrdrCancelReadList').append(source);	
			  $("#empOrdrCancelReadList").listview("refresh");	  
			  sessionStorage.removeItem('ordrNumbrSeq');
	      },
	      error:function(request,status,error){
	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	      }
	   });
	}
}

//주문취소 목록조회
function ordrCancelList(){
	//일반 목록
	$.ajax({
      type: "POST",
      url:"http://sebm.iptime.org:9124/ordrCancelList.do",   
      success:function(data){
		  $.each(data, function(i, vo){
    		  var ordrNumbrSeq = vo.ordrNumbrSeq;
    		  var ordrDat = vo.ordrDat;
    		  var prodctNme = vo.prodctNme;
    		  var ordrProdctAmont = vo.ordrProdctAmont;
    		  var allPric = numberWithCommas(vo.allPric);
    		  var ordrCanlDat = vo.ordrCanlDat;
    		  if(vo.cartImg1 == null){
      			  var cartImg1 = "../img/no_image.gif";
      		  }
      		  else{
      			  var cartImg1 = "http://sebm.iptime.org:9124/" + vo.cartImg1;  
      		  }
    		  if(i!=0){
			  	$('#ordrCancelList').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%; margin-top:10%'>"
			  					+ "<table style='width:100%'>"
			  					+ "<tr>"
			  					+ "<td>"+ordrDat+"</td>"
			  					+ "<td style='float:right'><a onclick='empOrdrCancelRead("+ordrNumbrSeq+",0)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>취소상세보기 ></a>"
			  					+ "</td>"
			  					+ "</tr>"
			  					+ "</table>"
			  					+ "<div data-role='content' style='border-top:3px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
			  					+ "<div class='row'>"
			  					+ "<div class='col-xs-5'>"
			  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
			  					+ "</div>"
			  					+ "<div class='col-xs-7'>"
			  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>주문취소</a></p>"
			  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + " 외 " + ordrProdctAmont + "건 </div>"
			  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "</div>"
      				)
      		  }
      		  else{
      			$('#ordrCancelList').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%'>"
			  					+ "<table style='width:100%'>"
			  					+ "<tr>"
			  					+ "<td>"+ordrDat+"</td>"
			  					+ "<td style='float:right'><a onclick='empOrdrCancelRead("+ordrNumbrSeq+",0)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>취소상세보기 ></a>"
			  					+ "</td>"
			  					+ "</tr>"
			  					+ "</table>"
			  					+ "<div data-role='content' style='border-top:3px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
			  					+ "<div class='row'>"
			  					+ "<div class='col-xs-5'>"
			  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
			  					+ "</div>"
			  					+ "<div class='col-xs-7'>"
			  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>주문취소</a></p>"
			  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + " 외 " + ordrProdctAmont + "건 </div>"
			  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "</div>"
      				)
      		  }
    	  });
		  $("#ordrCancelList").listview("refresh");
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
	//공동구매 목록
	$.ajax({
      type: "POST",
      url:"http://sebm.iptime.org:9124/dahamgaeOrdrCancelList.do",   
      success:function(data){
    	  $.each(data, function(i, vo){
    		  var ordrNumbrSeq = vo.ordrNumbrSeq;
    		  var ordrDat = vo.ordrDat;
    		  var prodctNme = vo.prodctNme;
    		  var allPric = numberWithCommas(vo.allPric);
    		  var ordrCanlDat = vo.ordrCanlDat;
    		  if(vo.cartImg1 == null){
      			  var cartImg1 = "../img/no_image.gif";
      		  }
      		  else{
      			  var cartImg1 = "http://sebm.iptime.org:9124/" + vo.cartImg1;  
      		  }
    		  if(i!=0){
			  	$('#ordrCancelList2').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%; margin-top:10%'>"
			  					+ "<table style='width:100%'>"
			  					+ "<tr>"
			  					+ "<td>"+ordrDat+"</td>"
			  					+ "<td style='float:right'><a onclick='empOrdrCancelRead("+ordrNumbrSeq+",0)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>취소상세보기 ></a>"
			  					+ "</td>"
			  					+ "</tr>"
			  					+ "</table>"
			  					+ "<div data-role='content' style='border-top:3px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
			  					+ "<div class='row'>"
			  					+ "<div class='col-xs-5'>"
			  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
			  					+ "</div>"
			  					+ "<div class='col-xs-7'>"
			  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>주문취소</a></p>"
			  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + " 외 " + ordrProdctAmont + "건 </div>"
			  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "</div>"
      				)
      		  }
      		  else{
      			$('#ordrCancelList2').append("<div data-role='content' style='padding-left:0px; padding-right:0px; margin-bottom: 3%'>"
			  					+ "<table style='width:100%'>"
			  					+ "<tr>"
			  					+ "<td>"+ordrDat+"</td>"
			  					+ "<td style='float:right'><a onclick='empOrdrCancelRead("+ordrNumbrSeq+",0)' class='ui-btn ui-btn-inline clr-btn-accent-light-green'>취소상세보기 ></a>"
			  					+ "</td>"
			  					+ "</tr>"
			  					+ "</table>"
			  					+ "<div data-role='content' style='border-top:3px solid #F6F6F6; border-bottom:1px solid #F6F6F6'>"
			  					+ "<div class='row'>"
			  					+ "<div class='col-xs-5'>"
			  					+ "<img src='" + cartImg1 + "' style='width:100%;height:70%;margin-top: 15%;'>"
			  					+ "</div>"
			  					+ "<div class='col-xs-7'>"
			  					+ "<p><a href='#' class='ui-btn ui-btn-raised font-btn clr-primary' style='margin-top: 5%'>주문취소</a></p>"
			  					+ "<div style='text-overflow:ellipsis; overflow:hidden; white-space:nowrap; text-align:right'>"+ prodctNme + " 외 " + ordrProdctAmont + "건 </div>"
			  					+ "<h5 style='text-align:right'>"+ allPric + "원</h5>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "</div>"
			  					+ "</div>"
      				)
      		  }
    	  });
		  $("#ordrCancelList2").listview("refresh");
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}

//배송상태별 건수조회
function shipngTypeList(){
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
    	  var list = data.ordrCountList;
    	  var empNme = data.empNme;
    	  $.each(list, function(i, vo){
    		 if(i==0){
    			 $('#ordrFinish').text(vo.ordrNumbrCount);
    		 } 
    		 if(i==1){
    			 $('#shipng').text(vo.ordrNumbrCount);
    		 }
    		 if(i==2){
    			 $('#shipngFinish').text(vo.ordrNumbrCount);
    		 }
    	  });
    	  $('#empNme').text(empNme+" 님, 환영합니다!");
    	  sessionStorage.setItem('empPushCheck', data.pushCheck);
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}



//개인정보 관리 아이디 조회
function setupConfirm(){
	var sessionEmpSeq = sessionStorage.getItem('empSeq');
	if(sessionEmpSeq!=null){
		var empSeq = sessionStorage.getItem('empSeq');
	}
	else{
		var empSeq = localStorage.getItem('empSeq');
	}
	$.ajax({
      type: "POST",
      url:"http://sebm.iptime.org:9124/empInfoRead.do",   
      data: {"empSeq":empSeq},
      success:function(data){
    	  $('#empId').val(data.id);
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}

//개인정보 상세조회
function setupRead(){
	var sessionEmpSeq = sessionStorage.getItem('empSeq');
	if(sessionEmpSeq!=null){
		var empSeq = sessionStorage.getItem('empSeq');
	}
	else{
		var empSeq = localStorage.getItem('empSeq');
	}
	$.ajax({
      type: "POST",
      url:"http://sebm.iptime.org:9124/empInfoRead.do",   
      data: {"empSeq":empSeq},
      success:function(data){
    	  $('#nme').val(data.nme);
    	  $('#id').val(data.id);
    	  $('#mobl').val(data.mobl);
    	  $('#empSeq').val(empSeq);
    	  $('#empSeq2').val(empSeq);
      },
      error:function(request,status,error){
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
   });
}

//개인정보 확인값 조회
function selectEmpMyPageInfoCount(){
	var formData = $('#empMyPageSetupConfirmFrm').serialize();
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/empInfoConfirmRead.do",
		success:function(data){
			if(data.empCount==1){
				$.mobile.changePage("MyPageEmpSetupRead.html");
				var sessionEmpSeq = sessionStorage.getItem('empSeq');
				if(sessionEmpSeq!=null){
					sessionStorage.setItem('empPw',data.pw);
				}
				else{
					localStorage.setItem('empPw',data.pw);
				}
			}
			else{
				navigator.notification.alert("비밀번호를 다시 확인해주세요.",null,"알림","확인");
				
			}
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}	


//마이페이지 관리자 - 환경설정 - 수정,완료버튼 관련 함수
function changeEmpMyPageInfo(){ 
	$('#id').attr('readonly',false);
	$('#nme').attr('readonly',false);
	$('#mobl').attr('readonly',false);
	$('#updateBtn').css('display','none');
	$('#finishBtn').css('display','block');
}

//마이페이지 관리자 - 환경설정 - 수정,완료버튼 관련 함수
function finishEmpMyPageInfo(){ 
	$('#id').attr('readonly',true);
	$('#nme').attr('readonly',true);
	$('#mobl').attr('readonly',true);
	$('#updateBtn').css('display','block');
	$('#finishBtn').css('display','none');
	
	var formData = $('#empMyPageSetupReadFrm').serialize();
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/empInfoUpdate.do",
		success:function(data){
			if (confirm("개인 정보가 변경되었습니다. 홈 화면으로 이동하시겠습니까?") == true){    //확인
				$.mobile.changePage("MyPageEmpMain.html");
			}
			else{
				return false;
			}
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

//개인정보 최종 업데이트
function updateEmpMyPageInfo(){
	var formData = $('#empMyPageSetupReadFrm2').serialize();
	var sessionEmpPw = sessionStorage.getItem('empPw');
	if(sessionEmpPw!=null){
		var pw = sessionStorage.getItem('empPw');
	}
	else{
		var pw = localStorage.getItem('empPw');
	}
	var curntPw = $('#curntPw').val();
	var newPw = $('#pw').val();
	var newPw2 = $('#pw2').val();
     
	//기존 비밀번호 입력 여부
	if (curntPw == ""){
		navigator.notification.alert("현재 비밀번호를 입력해주세요.",null,"알림","확인");
		return false;
	}
	
	//새 비밀번호 입력 여부
	if (newPw == "" || newPw2 == ""){
		navigator.notification.alert("새 비밀번호를 입력해주세요.",null,"알림","확인");
		return false;
	}
	
	// 길이
    if(!/^[a-zA-Z0-9!@#$%^&*()?_~]{5,20}$/.test(newPw))
    { 
        navigator.notification.alert("비밀번호는 숫자, 영문, 특수문자 조합으로 5~20자리를 사용해야 합니다.",null,"알림","확인");
        $('#pw').val("");
        $('#pw2').val("");
        return false;
    }
     
    // 영문, 숫자, 특수문자 2종 이상 혼용
    var chk = 0;
    if(newPw.search(/[0-9]/g) != -1 ) chk ++;
    if(newPw.search(/[a-z]/ig)  != -1 ) chk ++;
    if(newPw.search(/[!@#$%^&*()?_~]/g)  != -1  ) chk ++;
    if(chk < 2)
    { 
        navigator.notification.alert("비밀번호는 숫자, 영문, 특수문자를 두가지이상 혼용하여야 합니다.",null,"알림","확인");
        $('#pw').val("");
        $('#pw2').val("");
        return false;
    }
     
    // 동일한 문자/숫자 4이상, 연속된 문자
    if(/(\w)\1\1\1/.test(newPw) || isContinuedValue(newPw))
    {
        navigator.notification.alert("비밀번호에 4자 이상의 연속 또는 반복 문자 및 숫자를 사용하실 수 없습니다.",null,"알림","확인");
        $('#pw').val("");
        $('#pw2').val("");
        return false;
    }
    
	// 기존 비밀번호 일치 여부
    if (pw != curntPw){
    	navigator.notification.alert("현재 비밀번호가 맞지 않습니다.",null,"알림","확인");
    	return false;
    }
	
	// 기존 비밀번호와 새 비밀번호 일치 여부
    if (curntPw == newPw2) {
        navigator.notification.alert("현재 비밀번호와 새 비밀번호는 다르게 설정해야 합니다.",null,"알림","확인");
        return false;
    }    
    
    // 재입력 일치 여부
    if (newPw != newPw2) {
        navigator.notification.alert("입력한 두 개의 비밀번호가 서로 일치하지 않습니다.",null,"알림","확인");
        $('#pw').val("");
        $('#pw2').val("");
        return false;
    }
    
	$.ajax({
		type:"POST",
		data:formData,
		url:"http://sebm.iptime.org:9124/empPwUpdate.do",
		success:function(data){
			navigator.notification.alert("비밀번호 수정이 완료되었습니다.",null,"알림","확인");
			if(localStorage.getItem('empPw') != null){
				localStorage.setItem('empPw',newPw);
			}
			else{
				sessionStorage.setItem('empPw',newPw);
			}
			$.mobile.changePage("MyPageEmpMain.html");
		},
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

function isContinuedValue(value) {
    console.log("value = " + value);
    var intCnt1 = 0;
    var intCnt2 = 0;
    var temp0 = "";
    var temp1 = "";
    var temp2 = "";
    var temp3 = "";

    for (var i = 0; i < value.length-3; i++) {
        console.log("=========================");
        temp0 = value.charAt(i);
        temp1 = value.charAt(i + 1);
        temp2 = value.charAt(i + 2);
        temp3 = value.charAt(i + 3);

        console.log(temp0)
        console.log(temp1)
        console.log(temp2)
        console.log(temp3)

        if (temp0.charCodeAt(0) - temp1.charCodeAt(0) == 1
                && temp1.charCodeAt(0) - temp2.charCodeAt(0) == 1
                && temp2.charCodeAt(0) - temp3.charCodeAt(0) == 1) {
            intCnt1 = intCnt1 + 1;
        }

        if (temp0.charCodeAt(0) - temp1.charCodeAt(0) == -1
                && temp1.charCodeAt(0) - temp2.charCodeAt(0) == -1
                && temp2.charCodeAt(0) - temp3.charCodeAt(0) == -1) {
            intCnt2 = intCnt2 + 1;
        }
        console.log("=========================");
    }

    console.log(intCnt1 > 0 || intCnt2 > 0);
    return (intCnt1 > 0 || intCnt2 > 0);
}

//장바구니 사진 업로드
function ordrImgUpload(){
	var sendTlt;
	var sendMsg;
	var type;
	var d = new Date();
    var time = d.getFullYear() + "년 " + (d.getMonth() + 1) + "월 " + d.getDate() + "일 " + d.getHours() + "시 " + d.getMinutes() + "분";
    var ordrType = sessionStorage.getItem('ordrType',ordrType); 
    var ordrNumbrSeq = $('#ordrSeq').val();
	var imgFrm = new FormData(document.getElementById('ordrFileFrm'));
	$.ajax({
		url: "http://sebm.iptime.org:9124/ordrImgUpload.do",
		data: imgFrm,
		processData: false,
		contentType: false,
		type: 'POST',
		success:function(data){
			$("#cartImg1").attr("src","http://sebm.iptime.org:9124/"+data);
			sendTlt = "장바구니 사진 등록 알림";
			sendMsg = time + "에 장바구니 사진이 등록되었습니다.";
			type = "ordrImgUpload";
    	  
	  		$.ajax({
	  	      type: "POST",
	  	      data:{"ordrNumbrSeq":ordrNumbrSeq,"ordrType":ordrType, "sendTlt":sendTlt, "sendMsg":sendMsg, "type":type},
	  	      url:"http://sebm.iptime.org:9124/pushOrdrImgUpload.do",   
	  	      success:function(data){
		    	  
	  	      },
	  	      error:function(request,status,error){
	  	         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	  	      }
	  	   });
		},
		error:function(xhr,status,error)
		{ //ajax 오류인경우  
			alert("$");
			alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
		}
    });
}

function empPushCheck(){
	var sessionEmpSeq = sessionStorage.getItem('empSeq');
	if(sessionEmpSeq!=null){
		var empSeq = sessionStorage.getItem('empSeq');
	}
	else{
		var empSeq = localStorage.getItem('empSeq');
	}
	$.ajax({
		type: "POST",
		data:{"empSeq":empSeq},
		url:"http://sebm.iptime.org:9124/empPushCheck.do",   
		success:function(data){
			if(data=='Y'){
				$("#pushCheck option:eq(1)").attr("selected", "selected").flipswitch("refresh");
			}
			else{
				$("#pushCheck option:eq(0)").attr("selected", "selected").flipswitch("refresh");
			}
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}