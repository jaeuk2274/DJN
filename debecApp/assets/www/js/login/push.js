document.addEventListener("deviceready", function(){
	console.log(device.platform);
	if(device.platform.toUpperCase() == 'ANDROID'){
		window.plugins.pushNotification.register(successHandler,errorHandler, {
			"senderID" : "584575521448", // Google GCM 서비스에서 생성한 Project Number를 입력한다.
			"ecb" : "onNotificationGCM", // 디바이스로 푸시가 오면 onNotificationGCM 함수를 실행할 수 있도록 ecb(event callback)에 등록한다.
		});
	}
	else 
	{
    	// PushPlugin을 설치했다면 window.plugins.pushNotification.register를 이용해서 iOS 푸시 서비스를 등록한다.
   		window.plugins.pushNotification.register(tokenHandler, errorHandler, {
		"badge":"true", // 뱃지 기능을 사용한다.
		"sound":"true", // 사운드를 사용한다.
		"alert":"true", // alert를 사용한다.
		"ecb": "onNotificationAPN" // 디바이스로 푸시가 오면 onNotificationAPN 함수를 실행할 수 있도록 ecb(event callback)에 등록한다.
		});
  	}
});

/**
 * tokenHandler
 *
 * @param result
 *
 * 디바이스 토큰핸들러 콜백함수.
 * 푸시 서비스를 활성화 하였을 때, window.plugins.pushNotification.register 메소드가 실행되면서 디바이스 토큰을 가져와서 출력한다.
 * 만약에 푸시 서버로 디바이스 토큰을 보내야할 경우 이 함수 안에서 서버로 디바이스 토큰을 전송하면 된다.
 */
function tokenHandler(result){
  console.log('deviceToken:' + result);
}

/**
 * errorHandler
 *
 * @param err
 *
 * 에러 핸들러 콜백 함수.
 */
function errorHandler(err){
  console.log('error:' + err);
}

/**
 * successHandler
 *
 * @param result
 *
 * 디바이스로 푸시 메세지를 받았을 때 뱃지처리 이후 호출하는 콜백함수
 */
function successHandler(result){
  console.log('result:'+result);
}
/**
 * onNotificationGCM
 *
 * @param e
 *
 * 안드로이드 디바이스로 푸시 메세지를 받을 때 호출되는 함수, window.plugins.pushNotification.register 옵션에 설정에서 ecb의 이름에 매칭된다.
 */
		 
//주문내역 상세조회 이동
function cusOrdrRead2(ordrNumbrSeq, ordrType){
	sessionStorage.setItem('ordrNumbrSeq', ordrNumbrSeq);
	sessionStorage.setItem('ordrType', ordrType); //0은 일반, 1은 공동구매
	$.mobile.changePage("MyPageCusOrdrRead.html");
}

//웹 푸시전송 알림
function pushDespList(e){
	//자동 로그인 설정
    if(localStorage.getItem('custmrId') != null && localStorage.getItem('custmrPw') != null){
    	if (e.foreground){ // 푸시 메세지가 왔을 때 앱이 실행되고 있을 경우
    		navigator.notification.confirm(
		        e.payload.message, // message
		        function(button) {
		            if ( button == 1 ) {
		            	if(e.playload.url == ''){
		            		$.mobile.changePage("LoginFrame.html");// callback to invoke with index of button pressed
		            	}
		            	else{
		            		$.mobile.changePage(e.payload.url);// callback to invoke with index of button pressed
		            	}
		            }
		        }, 
				e.payload.title,     // title
		        ['확인','취소']         // buttonLabels
		    );
		}
		else { // 푸시 메세지가 왔을 때 앱이 백그라운드로 실행되거나 실행되지 않을 경우
			if (e.coldstart) { // 푸시 메세지가 왔을 때 푸시를 선택하여 앱이 열렸을 경우
				if(e.playload.url == ''){
            		$.mobile.changePage("LoginFrame.html");// callback to invoke with index of button pressed
            	}
            	else{
            		$.mobile.changePage(e.payload.url);// callback to invoke with index of button pressed
            	}
			}
			else { // 푸시 메세지가 왔을 때 앱이 백그라운드로 사용되고 있을 경우
				if(e.playload.url == ''){
            		$.mobile.changePage("LoginFrame.html");// callback to invoke with index of button pressed
            	}
            	else{
            		$.mobile.changePage(e.payload.url);// callback to invoke with index of button pressed
            	}
			}
		}
    }
	//자동 로그인 미설정(직접 DB에서 정보 조회 후 세션에 넣어줌)
    else{
    	if (e.foreground){ // 푸시 메세지가 왔을 때 앱이 실행되고 있을 경우
    		navigator.notification.confirm(
		        '푸시 알림 확인은 로그인 후에 확인가능합니다.', // message
		        function(button) {
		            if ( button == 1 ) {
		            	$.mobile.changePage("LoginFrame.html");// callback to invoke with index of button pressed
		            }
		        }, 
				'푸시 알림 확인',           // title
		        ['로그인','취소']         // buttonLabels
		    );
		}
		else { // 푸시 메세지가 왔을 때 앱이 백그라운드로 실행되거나 실행되지 않을 경우
			if (e.coldstart) { // 푸시 메세지가 왔을 때 푸시를 선택하여 앱이 열렸을 경우
				navigator.notification.confirm(
			        '푸시 알림 확인은 로그인 후에 확인가능합니다.', // message
			        function(button) {
			            if ( button == 1 ) {
			            	$.mobile.changePage("LoginFrame.html");// callback to invoke with index of button pressed
			            }
			        }, 
					'푸시 알림 확인',           // title
			        ['로그인','취소']         // buttonLabels
			    );
			}
			else { // 푸시 메세지가 왔을 때 앱이 백그라운드로 사용되고 있을 경우
				navigator.notification.confirm(
			        '푸시 알림 확인은 로그인 후에 확인가능합니다.', // message
			        function(button) {
			            if ( button == 1 ) {
			            	$.mobile.changePage("LoginFrame.html");// callback to invoke with index of button pressed
			            }
			        }, 
					'푸시 알림 확인',           // title
			        ['로그인','취소']         // buttonLabels
			    );
			}
		}
    }
}

//배송상태변경 푸시알림
function pushOrdrImgUpload(e){
	//자동 로그인 설정
    if(localStorage.getItem('custmrId') != null && localStorage.getItem('custmrPw') != null){
    	if (e.foreground){ // 푸시 메세지가 왔을 때 앱이 실행되고 있을 경우
    		navigator.notification.confirm(
		        '주문 상품의 장바구니 사진이 등록되었습니다. 확인하시겠습니까?', // message
		        function(button) {
		            if ( button == 1 ) {
		            	cusOrdrRead2(e.payload.ordrNumbrSeq, e.payload.ordrType);// callback to invoke with index of button pressed
		            }
		        }, 
				'장바구니 사진 등록 알림',     // title
		        ['확인','취소']         // buttonLabels
		    );
		}
		else { // 푸시 메세지가 왔을 때 앱이 백그라운드로 실행되거나 실행되지 않을 경우
			if (e.coldstart) { // 푸시 메세지가 왔을 때 푸시를 선택하여 앱이 열렸을 경우
				cusOrdrRead2(e.payload.ordrNumbrSeq, e.payload.ordrType);
			}
			else { // 푸시 메세지가 왔을 때 앱이 백그라운드로 사용되고 있을 경우
				cusOrdrRead2(e.payload.ordrNumbrSeq, e.payload.ordrType);
			}
		}
    }
	//자동 로그인 미설정(직접 DB에서 정보 조회 후 세션에 넣어줌)
    else{
    	if (e.foreground){ // 푸시 메세지가 왔을 때 앱이 실행되고 있을 경우
    		navigator.notification.confirm(
		        '장바구니 사진 확인은 로그인 후에 확인가능합니다.', // message
		        function(button) {
		            if ( button == 1 ) {
		            	$.mobile.changePage("LoginFrame.html");// callback to invoke with index of button pressed
		            }
		        }, 
				'장바구니 사진 확인',           // title
		        ['로그인','취소']         // buttonLabels
		    );
		}
		else { // 푸시 메세지가 왔을 때 앱이 백그라운드로 실행되거나 실행되지 않을 경우
			if (e.coldstart) { // 푸시 메세지가 왔을 때 푸시를 선택하여 앱이 열렸을 경우
				navigator.notification.confirm(
			        '장바구니 사진 확인은 로그인 후에 확인가능합니다.', // message
			        function(button) {
			            if ( button == 1 ) {
			            	$.mobile.changePage("LoginFrame.html");// callback to invoke with index of button pressed
			            }
			        }, 
					'장바구니 사진 확인',           // title
			        ['로그인','취소']         // buttonLabels
			    );
			}
			else { // 푸시 메세지가 왔을 때 앱이 백그라운드로 사용되고 있을 경우
				navigator.notification.confirm(
			        '장바구니 사진 확인은 로그인 후에 확인가능합니다.', // message
			        function(button) {
			            if ( button == 1 ) {
			            	$.mobile.changePage("LoginFrame.html");// callback to invoke with index of button pressed
			            }
			        }, 
					'장바구니 사진 확인',           // title
			        ['로그인','취소']         // buttonLabels
			    );
			}
		}
    }
}

//배송상태변경 푸시알림
function pushOrdrStat(e){
	//자동 로그인 설정
    if(localStorage.getItem('custmrId') != null && localStorage.getItem('custmrPw') != null){
    	if (e.foreground){ // 푸시 메세지가 왔을 때 앱이 실행되고 있을 경우
    		navigator.notification.confirm(
		        '주문 상품의 배송이 시작되었습니다. 확인하시겠습니까?', // message
		        function(button) {
		            if ( button == 1 ) {
		            	cusOrdrRead2(e.payload.ordrNumbrSeq, e.payload.ordrType);// callback to invoke with index of button pressed
		            }
		        }, 
				'배송 시작 알림',           // title
		        ['확인','취소']         // buttonLabels
		    );
		}
		else { // 푸시 메세지가 왔을 때 앱이 백그라운드로 실행되거나 실행되지 않을 경우
			if (e.coldstart) { // 푸시 메세지가 왔을 때 푸시를 선택하여 앱이 열렸을 경우
				cusOrdrRead2(e.payload.ordrNumbrSeq, e.payload.ordrType);
			}
			else { // 푸시 메세지가 왔을 때 앱이 백그라운드로 사용되고 있을 경우
				cusOrdrRead2(e.payload.ordrNumbrSeq, e.payload.ordrType);
			}
		}
    }
	//자동 로그인 미설정(직접 DB에서 정보 조회 후 세션에 넣어줌)
    else{
    	if (e.foreground){ // 푸시 메세지가 왔을 때 앱이 실행되고 있을 경우
    		navigator.notification.confirm(
		        '주문 확인은 로그인 후에 확인가능합니다.', // message
		        function(button) {
		            if ( button == 1 ) {
		            	$.mobile.changePage("LoginFrame.html");// callback to invoke with index of button pressed
		            }
		        }, 
				'주문 확인',           // title
		        ['로그인','취소']         // buttonLabels
		    );
		}
		else { // 푸시 메세지가 왔을 때 앱이 백그라운드로 실행되거나 실행되지 않을 경우
			if (e.coldstart) { // 푸시 메세지가 왔을 때 푸시를 선택하여 앱이 열렸을 경우
				navigator.notification.confirm(
			        '주문 확인은 로그인 후에 확인가능합니다.', // message
			        function(button) {
			            if ( button == 1 ) {
			            	$.mobile.changePage("LoginFrame.html");// callback to invoke with index of button pressed
			            }
			        }, 
					'주문 확인',           // title
			        ['로그인','취소']         // buttonLabels
			    );
			}
			else { // 푸시 메세지가 왔을 때 앱이 백그라운드로 사용되고 있을 경우
				navigator.notification.confirm(
			        '주문 확인은 로그인 후에 확인가능합니다.', // message
			        function(button) {
			            if ( button == 1 ) {
			            	$.mobile.changePage("LoginFrame.html");// callback to invoke with index of button pressed
			            }
			        }, 
					'주문 확인',           // title
			        ['로그인','취소']         // buttonLabels
			    );
			}
		}
    }
}

//실시간 주문 등록 및 취소 푸시알림
function pushRealTiemOrdrList(e){
	//로그인 했을 때
	if((localStorage.getItem('empId') != null && localStorage.getItem('empPw') != null) || (sessionStorage.getItem('empId') != null && sessionStorage.getItem('empPw') != null)){
       	if (e.foreground){ // 푸시 메세지가 왔을 때 앱이 실행되고 있을 경우
       		
		}
		else { // 푸시 메세지가 왔을 때 앱이 백그라운드로 실행되거나 실행되지 않을 경우
			if (e.coldstart) { // 푸시 메세지가 왔을 때 푸시를 선택하여 앱이 열렸을 경우
				$.mobile.changePage("MyPageEmpRealTimeOrdrList.html");// callback to invoke with index of button pressed
			}
			else { // 푸시 메세지가 왔을 때 앱이 백그라운드로 사용되고 있을 경우
				$.mobile.changePage("MyPageEmpRealTimeOrdrList.html");// callback to invoke with index of button pressed
			}
		}
    }
	//자동 로그인 미설정
    else{
    	if (e.foreground){ // 푸시 메세지가 왔을 때 앱이 실행되고 있을 경우
    		navigator.notification.confirm(
		        '실시간 주문 목록은 로그인 후에 확인가능합니다.', // message
		        function(button) {
		            if ( button == 1 ) {
		            	$.mobile.changePage("LoginFrame.html");// callback to invoke with index of button pressed
		            }
		        }, 
				'실시간 주문 목록 확인',           // title
		        ['로그인','취소']         // buttonLabels
		    );
		}
		else { // 푸시 메세지가 왔을 때 앱이 백그라운드로 실행되거나 실행되지 않을 경우
			if (e.coldstart) { // 푸시 메세지가 왔을 때 푸시를 선택하여 앱이 열렸을 경우
				navigator.notification.confirm(
			        '실시간 주문 목록은 로그인 후에 확인가능합니다.', // message
			        function(button) {
			            if ( button == 1 ) {
			            	$.mobile.changePage("LoginFrame.html");// callback to invoke with index of button pressed
			            }
			        }, 
					'실시간 주문 목록 확인',           // title
			        ['로그인','취소']         // buttonLabels
			    );
			}
			else { // 푸시 메세지가 왔을 때 앱이 백그라운드로 사용되고 있을 경우
				navigator.notification.confirm(
			        '실시간 주문 목록은 로그인 후에 확인가능합니다.', // message
			        function(button) {
			            if ( button == 1 ) {
			            	$.mobile.changePage("LoginFrame.html");// callback to invoke with index of button pressed
			            }
			        }, 
					'실시간 주문 목록 확인',           // title
			        ['로그인','취소']         // buttonLabels
			    );
			}
		}
    }
}

//푸시알림 받아주는 메인함수
function onNotificationGCM (e){
	switch (e.event) {
		case 'registered': // 안드로이드 디바이스의 registerID를 획득하는 event 중 registerd 일 경우 호출된다.
		{	
			console.log(e.regid);
			sessionStorage.setItem("cusToken", e.regid);
			$('#token').val(e.regid);
			$("#loginHtmlToken").val(sessionStorage.getItem("cusToken"));
			break;
		}
		case 'message': // 안드로이드 디바이스에 푸시 메세지가 오면 호출된다.
		{			
			switch (e.payload.type) {
				case "ordrStat":
					$.ajax({
				      type: "POST",
				      url:"http://sebm.iptime.org:9124/pushOpenDespContUpdate.do",  
				      data:{"despRecrdSeq":e.payload.despRecrdSeq},
				      success:function(data){
				      },
				      error:function(request,status,error){
				         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				      }
				   });
					pushOrdrStat(e);
					break;
				
				case "realTimeOrdrList":
					$.ajax({
				      type: "POST",
				      url:"http://sebm.iptime.org:9124/pushOpenDespContUpdate.do",  
				      data:{"despRecrdSeq":e.payload.despRecrdSeq},
				      success:function(data){
				      },
				      error:function(request,status,error){
				         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				      }
				   });
					pushRealTiemOrdrList(e);
					break;
				
				case "ordrImgUpload":
					$.ajax({
				      type: "POST",
				      url:"http://sebm.iptime.org:9124/pushOpenDespContUpdate.do",  
				      data:{"despRecrdSeq":e.payload.despRecrdSeq},
				      success:function(data){
				      },
				      error:function(request,status,error){
				         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				      }
				   });
					pushOrdrImgUpload(e);
					break;
	
				case "despList":
					$.ajax({
				      type: "POST",
				      url:"http://sebm.iptime.org:9124/pushOpenDespContUpdate.do",  
				      data:{"despRecrdSeq":e.payload.despRecrdSeq},
				      success:function(data){
				      },
				      error:function(request,status,error){
				         alert("code11:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				      }
				   });
					pushDespList(e);
					break;
			}
		}
		break;
		case 'error': // 푸시 메세지 처리에 에러가 발생하면 호출한다.
			console.log('error:' + e.msg);
			break;
		case 'default':
			console.log('알수 없는 이벤트');
			break;
	}
}