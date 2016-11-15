// 랜덤박스 뽑기
function randomBoxDrawing(){
	$.mobile.changePage('MainRandomBoxList.html');
}

//레시피 리스트(4개)
$(document).on('pageshow', function(event, data){ //"randomBoxList" 라는 page가 로딩됐을 때 실행이 되는 함수
	if ($.mobile.activePage.attr('id') == "randomBoxList") {	
		$.ajax({
			type:"POST",
			url:"http://sebm.iptime.org:9124/AppRandomBoxDrawing.do",
			success:function(data){
				$.each(data, function(i, vo){
					var recpSeq = vo.recpSeq;
					var recpTitl = vo.recpTitl;
					var recpImg = vo.recpImg;
					var likeCount = vo.likeCount;
					var dat = vo.dat;
					$('#recpListPart').append(
						"<div class='nd2-card' onclick='javascript:recpRead("+recpSeq+")' style='float:left; width:50%; margin:0px; padding:0px;'>"
							+"<div style='padding-top:10px;' align='center'>"
								+"<img src='http://sebm.iptime.org:9124/"+recpImg+"' style='width:150px; height:150px'/>"
							+"</div>"
							+"<div style='margin:5%; text-align:left; text-overflow:ellipsis; overflow:hidden; white-space:nowrap;'>"
								+"<font class='font-sm'><b>"+recpTitl+"</b></font>"
							+"</div>"
							+"<div style='width:100%; height:40px;'>"
								+"<font class='font-sm' style='float:right; margin-top:9px'>&nbsp;&nbsp;추천수&nbsp;"+likeCount+"&nbsp;</font>"
								+"<img src='../img/love.png' style='width:30px; height:30px; float:right;'/>"
							+"</div>"
						+"</div>"
					);
				});
				$("#recpListPart").listview("refresh");
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
});

