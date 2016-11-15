// ** oneCard이랑 twoCard 변수 2개를 한개로 해서 바꾸기
var oneCard = true;	// 역대 대장남(true면 역대대장남 화면 생성, false면 이달의 대장남 화면 생성)
var twoCard = true;	// 대장남 투표(true면 대장남 투표화면 생성, false면 역대 대장남 화면 생성)	
var thisMonthDJNSource;	// 이달의 대장남 소스

var empSeq;
var empId;
var empPw;
var custmrSeq;
var custmrId;
var custmrPw;


$(document).on('pageshow', function(event, data){ //"cusMainPage" 라는 page가 로딩됐을 때 실행이 되는 함수
	
	
	if ($.mobile.activePage.attr('id') == "cusMainPage") {	
		thisMonthDJN();
		if(localStorage.getItem('custmrSeq')!=null){
			custmrSeq = localStorage.getItem('custmrSeq');
			custmrId = localStorage.getItem('custmrId');
			custmrPw = localStorage.getItem('custmrPw');
		}
		else if(sessionStorage.getItem('custmrSeq')!=null){
			custmrSeq = sessionStorage.getItem('custmrSeq');
			custmrId = sessionStorage.getItem('custmrId');
			custmrPw = sessionStorage.getItem('custmrPw');
		}
	}
	if ($.mobile.activePage.attr('id') == "empMainPage") {	
		thisMonthDJN();
		if(localStorage.getItem('empSeq')!=null){
			empSeq = localStorage.getItem('empSeq');
			empId = localStorage.getItem('empId');
			empPw = localStorage.getItem('empPw');
		}
		else if(sessionStorage.getItem('empSeq')!=null){
			empSeq = sessionStorage.getItem('empSeq');
			empId = sessionStorage.getItem('empId');
			empPw = sessionStorage.getItem('empPw');
		}
	}
});

// 이달의 대장남 (탭 선택 시 띄움)
function thisMonthDJN(){
	var d = new Date();
	var year = d.getFullYear()+"-";
	var month = d.getMonth() + 1;
	var month2=/^[1-9]*$/;
	
	if(month2.test(month)){
		month = "0" + month;
	}
	
	var ntime = year+month;
	$.ajax({
		type:"POST",
		data:{selectMon: ntime},
		url:"http://sebm.iptime.org:9124/AppThisDJNList.do",
		success:function(vo){
			var djnNme = vo.empNme;		// 이달의 대장남 이름
			var djnImg = vo.empImg;		// 이달의 대장남 이미지
			
			if(djnNme == null){
				$('#thisMonthDJNImg').html("<img src='../img/basic_emp_img.png' style='width:120px; height:150px'/>");
				$('#thisMonthDJNNme').html("<font class='font-md' color='white' style='font-weight:bold'>대장남</font>");
			}
			else{
				$('#thisMonthDJNImg').html("<img src='http://sebm.iptime.org:9124/"+djnImg+"' style='width:120px; height:150px'/>");
				$('#thisMonthDJNNme').html("<font class='font-md' color='white' style='font-weight:bold'>"+djnNme+"</font>");
			}
			thisMonthDJNSource = $("#DJNMainPart").contents();	// 이달의 대장남 소스
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

// 역대 대장남 화면 생성(첫번째 카드)
function oneCardClick(mon){
	if(oneCard == true){
		var d = new Date();
		var month;
		var year = d.getFullYear();
		if(mon==false){
			month = d.getMonth() + 1;
		}
		else if(mon != false){
			 month = mon;
		}
		if(month == 0){
			year = year-1;
			month = 12;
		}
		else if(month == 13){
			year = year+1;
			month = 1;
		}
		
		var month2=/^[1-9]*$/;
		
		if(month2.test(month)){
			month = "0" + month;
		}
		var ntime = year+"-"+month;
		$.ajax({
			type:"POST",
			data:{selectMon: ntime},
			url:"http://sebm.iptime.org:9124/AppThisDJNList.do",
			success:function(vo){
				if(vo.empSeq!=0){
					$('#oneTitle').html("이달의 대장남");
					$("#oneCardImg").attr("src","../img/basic_emp_img.png");
					var djnSeq = vo.empSeq;
					var djnNme = vo.empNme;
					var djnImg = vo.empImg;
					var dat = vo.dat;
					dat = dat.replace("-", "년 ");
					dat += "월";
					var formDat = dat.substr(6,2);		// 왼쪽 오른쪽 버튼 클릭시 월을 수정해서 보냄
					var leftFormDat = parseInt(formDat)-1;
					var rightFormDat = parseInt(formDat)+1;
					$('#DJNMainPart').html(
											"<font class='font-md'><b>역대 대.장.남</b></font><br><br>"
											+"<img src='../img/ever_djn_left.png' style='width:15px; height:20px' onclick='javascript:everDJNChange("+leftFormDat+")'/>"
											+"<img src='http://sebm.iptime.org:9124/"+djnImg+"' style='overflow:hidden; border-radius:50%; background-color:#bdbdbd; width:30%; margin:5px'>"
											+"<img src='../img/ever_djn_right.png' style='width:15px; height:20px' onclick='javascript:everDJNChange("+rightFormDat+")'/><br>"
											+"<font class='font-sm'><b>"+dat+"</b></font><br>"
											+"<font class='font-sm'><b>"+djnNme+"</b></font>"
											);
				}
				else if(vo.empSeq==0){
					navigator.notification.alert("마지막입니다.",null,"역대 대장남","확인");
				}
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
		oneCard = false;
		twoCard = true;
	}
// 이달의 대장남 (첫번째 카드)
	else if(oneCard == false){
		var d = new Date();
		var year = d.getFullYear()+"-";
		var month = d.getMonth() + 1;
		var month2=/^[1-9]*$/;
		if(month2.test(month)){
			month = "0" + month;
		}
		var ntime = year+month;
		$.ajax({
			type:"POST",
			url:"http://sebm.iptime.org:9124/AppThisDJNList.do",
			data:{selectMon: ntime},
			success:function(vo){
				$('#oneTitle').html("역대 대.장.남");
				$("#oneCardImg").attr("src","../img/ever_djn_card.png");
				$('#twoTitle').html("투표");
				$("#twoCardImg").attr("src","../img/djn_voting_card.png");
				$('#DJNMainPart').css('height','200px');
				$('#DJNMainPart').html(thisMonthDJNSource);
				var djnNme = vo.empNme;		// 이달의 대장남 이름
				var djnImg = vo.empImg;		// 이달의 대장남 이미지
				if(djnNme == null){
					$('#thisMonthDJNImg').html("<img src='../img/basic_emp_img.png' style='width:120px; height:150px'/>");
					$('#thisMonthDJNNme').html("<font class='font-md' color='white' style='font-weight:bold'>대장남</font>");
				}
				else{
					$('#thisMonthDJNImg').html("<img src='http://sebm.iptime.org:9124/"+djnImg+"' style='width:120px; height:150px'/>");
					$('#thisMonthDJNNme').html("<font class='font-md' color='white' style='font-weight:bold'>"+djnNme+"</font>");
				}
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
		oneCard = true;
		twoCard = true;
	}
}

// 역대대장남 좌우 버튼
function everDJNChange(mon){
	var d = new Date();
	var year = d.getFullYear();
	var month=mon;
	if(month == 0){
		year = year-1;
		month = 12;
	}
	else if(month == 13){
		year = year+1;
		month = 1;
	}
	var ntime = year+"-"+month;
			
	$.ajax({
		type:"POST",
		data:{selectMon: ntime},
		url:"http://sebm.iptime.org:9124/AppThisDJNList.do",
		success:function(vo){
			if(vo.empSeq!=0){
				var djnSeq = vo.empSeq;
				var djnNme = vo.empNme;
				var djnImg = vo.empImg;
				var dat = vo.dat;
				dat = dat.replace("-", "년 ");
				dat += "월";
				var formDat = dat.substr(6,2);		// 왼쪽 오른쪽 버튼 클릭시 월을 수정해서 보냄
				var leftFormDat = parseInt(formDat)-1;
				var rightFormDat = parseInt(formDat)+1;
				$('#DJNMainPart').html(
										"<font class='font-md'><b>역대 대.장.남</b></font><br><br>"
										+"<img src='../img/ever_djn_left.png' style='width:15px; height:20px' onclick='javascript:everDJNChange("+leftFormDat+")'/>"
										+"<img src='http://sebm.iptime.org:9124/"+djnImg+"' style='overflow:hidden; border-radius:50%; background-color:#bdbdbd; width:30%; margin:5px'>"
										+"<img src='../img/ever_djn_right.png' style='width:15px; height:20px' onclick='javascript:everDJNChange("+rightFormDat+")'/><br>"
										+"<font class='font-sm'><b>"+dat+"</b></font><br>"
										+"<font class='font-sm'><b>"+djnNme+"</b></font>"
										);
			}
			else if(vo.empSeq==0){
				navigator.notification.alert("마지막입니다.",null,"역대 대장남","확인");
			}
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
			
}

// 대장남 투표 화면 생성(두번째 카드)
function twoCardClick(mon){
	var d = new Date();
	var month = d.getMonth() + 2;
	if(month >= 13){
		month = 1;
	}
	
	if(twoCard == true){
		$('#DJNMainPart').html("<font class='font-md'><b>"+month+"월의 대장남 투표</b></font>"
								+"<div id='empList' align='center' style='width:100%;'></div>");
		$('#DJNMainPart').css('height','100%');
		$.ajax({
			type:"POST",
			url:"http://sebm.iptime.org:9124/AppEmpList.do",
			success:function(data){
				$.each(data, function(i, vo){
					var djnSeq = vo.empSeq;
					var nme = vo.nme;
					var imgRot = vo.imgRot;
					if(imgRot!='NULL'){
						$('#empList').append("<a onclick='javascript:DJNVoting("+djnSeq+")'>" 
											+"<img src='http://sebm.iptime.org:9124/"+imgRot+"' style='width:35%; height:100px; margin:7px'>"
											+"</a>");
					}
					else if(imgRot=='NULL'){
						$('#empList').append("<img src='../img/basic_emp_img.png' style='width:35%; height:100px; margin:7px'>");
					}
				});
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
		$('#oneTitle').html("이달의 대장남");
		$("#oneCardImg").attr("src","../img/basic_emp_img.png");
		$('#twoTitle').html("역대 대.장.남");
		$("#twoCardImg").attr("src","../img/ever_djn_card.png");
		oneCard = false;
		twoCard = false;
	}
// 역대 대장남 (두번째 카드)
	else if(twoCard == false){
		var d = new Date();
		var month;
		var year = d.getFullYear();
		if(mon==false){
			month = d.getMonth() + 1;
		}
		else if(mon != false){
			 month = mon;
		}
		if(month == 0){
			year = year-1;
			month = 12;
		}
		else if(month == 13){
			year = year+1;
			month = 1;
		}
		var month2=/^[1-9]*$/;
		if(month2.test(month)){
			month = "0" + month;
		}
		var ntime = year+"-"+month;
		$.ajax({
			type:"POST",
			data:{selectMon: ntime},
			url:"http://sebm.iptime.org:9124/AppThisDJNList.do",
			success:function(vo){
				if(vo.empSeq!=0){
					$('#DJNMainPart').css('height','200px');
					$('#oneTitle').html("이달의 대장남");
					$("#oneCardImg").attr("src","../img/basic_emp_img.png");
					$('#twoTitle').html("투표");
					$("#twoCardImg").attr("src","../img/djn_voting_card.png");
					var djnSeq = vo.empSeq;
					var djnNme = vo.empNme;
					var djnImg = vo.empImg;
					var dat = vo.dat;
					dat = dat.replace("-", "년 ");
					dat += "월";
					var formDat = dat.substr(6,2);		// 왼쪽 오른쪽 버튼 클릭시 월을 수정해서 보냄
					var leftFormDat = parseInt(formDat)-1;
					var rightFormDat = parseInt(formDat)+1;
					$('#DJNMainPart').html(
											"<font class='font-md'><b>역대 대.장.남</b></font><br><br>"
											+"<img src='../img/ever_djn_left.png' style='width:15px; height:20px' onclick='javascript:everDJNChange("+leftFormDat+")'/>"
											+"<img src='http://sebm.iptime.org:9124/"+djnImg+"' style='overflow:hidden; border-radius:50%; background-color:#bdbdbd; width:30%; margin:5px'>"
											+"<img src='../img/ever_djn_right.png' style='width:15px; height:20px' onclick='javascript:everDJNChange("+rightFormDat+")'/><br>"
											+"<font class='font-sm'><b>"+dat+"</b></font><br>"
											+"<font class='font-sm'><b>"+djnNme+"</b></font>"
											);
				}
				else if(vo.empSeq==0){
					navigator.notification.alert("마지막입니다.",null,"역대 대장남","확인");
				}
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
		oneCard = false;
		twoCard = true;
	}
//});
}

// 대장남 투표하기
function DJNVoting(djnSeq){
	if(custmrSeq!=undefined){
		$.ajax({
			type:"POST",
			data:{custmrSeq: custmrSeq},
			url:"http://sebm.iptime.org:9124/AppCustmrDJNVotingAthrty.do",
			success:function(thisMonthCount){
				if(thisMonthCount==0){
					$.ajax({
						type:"POST",
						data:{empSeq: djnSeq, custmrSeq: custmrSeq},
						url:"http://sebm.iptime.org:9124/AppCustmrDJNVoting.do",
						success:function(){
							navigator.notification.alert("투표 해주셔서 감사합니다.",null,"투표","확인");
						},
						error:function(request,status,error){
							alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
						}
					});
				}
				else if(thisMonthCount!=0){
					navigator.notification.alert("이미 투표를 하셨습니다.\n투표는 월 1회만 할 수 있습니다.",null,"투표","확인");
				}
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
	else if(empSeq!=undefined){
		$.ajax({
			type:"POST",
			data:{empSeq: empSeq},
			url:"http://sebm.iptime.org:9124/AppEmpDJNVotingAthrty.do",
			success:function(thisMonthCount){
				if(thisMonthCount==0){
					$.ajax({
						type:"POST",
						data:{giveEmpSeq: djnSeq, getEmpSeq: empSeq},
						url:"http://sebm.iptime.org:9124/AppEmpDJNVoting.do",
						success:function(){
							navigator.notification.alert("투표 해주셔서 감사합니다.",null,"투표","확인");
						},
						error:function(request,status,error){
							alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
						}
					});
				}
				else if(thisMonthCount!=0){
					navigator.notification.alert("이미 투표를 하셨습니다.\n투표는 월 1회만 할 수 있습니다.",null,"투표","확인");
				}
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
}