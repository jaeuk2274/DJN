
//콤마추가하기
function addComma(val,flen){ 
	var returndata;
	var fLen=0;
	if(flen==null || flen=="undefined" || typeof flen =="undefined"){
		flen=0;
	}else{
		fLen = flen;
	}
	if(val==null){
		return "";
	}
	if(typeof val !="string"){
		returndata=val.toString().replace(/,|\s+/g,'');
	}else{
		returndata=val.replace(/,|\s+/g,'');
	}
	if(isNaN(returndata)){
		return returndata;
	}
	

	returndata=(parseFloat(returndata)).toFixed(fLen).toString();

	var buho="";
	if(returndata.substring(0,1)=="+" || returndata.substring(0,1)=="-"){
		buho=returndata.substring(0,1);
		returndata=returndata.substring(1,returndata.length);
	}
	var strValue = returndata;
 	var strBeforeValue = (strValue.indexOf('.') != -1)? strValue.substring(0,strValue.indexOf('.')) :strValue ;
 	var strAfterValue = (strValue.indexOf('.') != -1)? strValue.substr(strValue.indexOf('.'),fLen+1) : '' ;
	if(isNaN(strValue)){
 		return val;
 		
 	}
 	var intLast = strBeforeValue.length-1;
	var arrValue = new Array;
 	var strComma = '';
 	for(var i=intLast,j=0; i >= 0; i--,j++){ 
		if( j !=0 && j%3 == 0){ 
			strComma = ',';
 		}else{
 			strComma = '';
 		}
		arrValue[arrValue.length] = strBeforeValue.charAt(i) + strComma ;
	}

	return buho+arrValue.reverse().join('') + (fLen>0? strAfterValue:''); 
}

function replaceAll(inputString, targetString, replacement){
	var v_ret = null;
	var v_regExp = new RegExp(targetString,"g");
	v_ret = inputString.replace(v_regExp, replacement);
	return v_ret;
}

function getMonHan(inStr){
	var str="";
	if(typeof inStr =="number"){
		str=inStr.toString();
	}else if(typeof inStr=="object"){
		str=inStr.toString();
	}else{
	
		str=inStr;
	}
	
	if(str==""){
		return inStr;
	}
	
	if($.trim(str).length==6){
		return str.substring(0,4) + "." + str.substring(4,6)+"월";
	}
	return inStr;
}
function getBunHan(inStr){
	
	var str="";
	if(typeof inStr=="number"){
		str=inStr.toString();
	}else if(typeof instr=="object"){
		str=inStr.toString();
		
	}else{
		str=inStr;
	}
	
	if(str==""){
		return str;
	}
	
	if($.trim(str).length==6){
		//alert(str);
		return  str.substring(0,4) + "." + str.substring(4,6)+"분기";
	}
	return str; 
}
function getYYMM(inStr){
	
	var str="";
	if(typeof inStr=="number"){
		str=inStr.toString();
	}else if(typeof instr=="object"){
		str=inStr.toString();
		
	}else{
		str=inStr;
	}
	
	if(str==""){
		return str;
	}
	str=$.trim(str).replace(/\./gi,"");
	if(str.length==6){
		//alert(str);
		return  str.substring(2,4) + "." + str.substring(4,6);
	}
	if(str.length==4){
		return  str;
	}
	return str; 
}


function getYYYYMM(inStr){
	
	var str="";
	if(typeof inStr=="number"){
		str=inStr.toString();
	}else if(typeof instr=="object"){
		str=inStr.toString();
		
	}else{
		str=inStr;
	}
	
	if(str==""){
		return str;
	}
	str=$.trim(str).replace(/\./gi,"");
	if(str.length==6){
		//alert(str);
		return  str.substring(0,4) + "." + str.substring(4,6);
	}
	if(str.length==4){
		return  str;
	}
	return str; 
}


function numberFormat(num) {
	if(num==null || num==""){
		return "-";
	}
	 var pattern = /(-?[0-9]+)([0-9]{3})/;
	 while(pattern.test(num)) {
		 num = num.replace(pattern,"$1,$2");
	 }
	 return num;
 }


function getYMD(inStr,deli){ 
	
	var str="";
	if(typeof inStr=="number"){
		str=inStr.toString();
	}else if(typeof instr=="object"){
		str=inStr.toString();
		
	}else{
		str=inStr;
	}
	
	if(str==""){
		return str;
	}
	str=$.trim(str).replace(/\./gi,"");
	if(str.length==6){
		//alert(str);
		return  str.substring(0,4) + deli + str.substring(4,6);
	}
	if(str.length==4){
		return  str;
	}
	if(str.length==8){
		return  str.substring(0,4) + deli + str.substring(4,6)+deli+str.substring(6,8);
	}
	return str; 
}

/*****************************************************************************
 * 모달창을 open 한다.
 * param str : sUrl, sParam, sWithd, sHeight
 * author	: 김선국
 *****************************************************************************/
function fncShowModal(sUrl, sParam, sWithd, sHeight) {
	var returnVal = new Array();

	var winleft = (screen.width - sWithd) / 2;
	var wintop = (screen.height - sHeight ) / 2 ;

	returnVal = window.showModalDialog(sUrl, sParam,"dialogWidth:" + sWithd + "px; dialogHeight:" + sHeight + "px; resizable:no; help:no; status:yes; scroll:yes; edge:sunken;");

	return returnVal;
}


/*****************************************************************************
 * 모달창을 open 한다.
 * param str : sUrl, sParam, sWithd, sHeight
 * author	: 김선국
 *****************************************************************************/
function fncShowModalOp(sUrl, sParam, sWithd, sHeight) {
	//var returnVal = new Array();

	//var winleft = (screen.width - sWithd) / 2;
	//var wintop = (screen.height - sHeight ) / 2 ;

//	returnVal = window.showModalDialog(sUrl, sParam,"dialogWidth:" + sWithd + "px; dialogHeight:" + sHeight + "px; resizable:no; help:no; status:yes; scroll:yes; edge:sunken;");

	// var modal = window.open (sUrl, sParam, "width="+sWithd+",height="+sHeight+",left=300,modal=yes,scrollbars=yes,alwaysRaised=yes",null);
	   
	var posi = 'top=' + ((screen.height - sHeight) / 2) + ',left=' + ((screen.width-sWithd) / 2 ) + ',scrollbars=yes';
	posi = posi + ',width=' + sWithd + ',height=' + sHeight ;

	var win  = window.open(sUrl, sParam, posi);
	if (win.focus){
		win.focus();
	}
	return win;
	//return returnVal;
}


/*****************************************************************************
 * 공통 팝업
 * param str : opn_url, wth, hgt, scr, opt
 * author	: 김선국
 *****************************************************************************/
function fncPopup(opn_url, wth, hgt, scr, opt){
	var posi = 'top=' + ((screen.height - hgt) / 2 - 50) + ',left=' + ((screen.width-wth) / 2 - 190) + ',scrollbars=' + scr;
	posi = posi + ',width=' + wth + ',height=' + hgt + opt;

	var win  = window.open(opn_url, '', posi);
	if (win.focus)
		win.focus();

	return win;
}


/*****************************************************************************
 * String 앞뒤의 공백을 제거한다.
 * param str : 문자열
 * author	: No Name
 *****************************************************************************/
function fncTrim(str) {
	var temp = null;

	temp = fncLTtrim(str);
	str = fncRTrim(temp);
	return str;
}


/*****************************************************************************
 * String 왼쪽의 공백을 제거한다.
 * param str : 문자열
 * author	: No Name
 *****************************************************************************/
function fncLTtrim(str) {
	var len = str.length;
	var i;

	for(i=0; i < len; i++) {
		if( str.charAt(i) != ' ' )
			break;
	}
	return str.substring(i, len);
}


/*****************************************************************************
 * String 오른쪽의 공백을 제거한다.
 * param str : 문자열
 * author	: No Name
 *****************************************************************************/
function fncRTrim(str) {
	var len = str.length;
	var i;

	for(i=len-1; i >= 0; i--) {
		if( str.charAt(i) != ' ' )
			break;
	}
	return str.substring(0, i+1);
}


/*****************************************************************************
 * String 입력값이 알파벳, 숫자, _, @ 로 되어 있는지 체크.
 * param str : 문자열
 * author	: No Name
 *****************************************************************************/
function fncChkId(str) {
	var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_@-";
	var flag = fncContainsCharsOnly(str,chars);

	return flag;
}


/*****************************************************************************
 * String 입력값이 알파벳, 숫자, _, @ 로 되어 있는지 체크.
 * param input : 문자열
 * param chars : 문자열
 * author	: No Name
 *****************************************************************************/
function fncContainsCharsOnly(input,chars) {

	for (var inx = 0; inx < input.length; inx++) {
		if (chars.indexOf(input.charAt(inx)) == -1)
			return false;
	}

	return true;
}


/*****************************************************************************
 * String
 * param str : 문자열
 * author	: No Name
 *****************************************************************************/
function fncPassOnlyNum(str){
	var ret;

	for (var i = 0; i < str.length; i++) {
		ret = str.charCodeAt(i);
		if (!((ret > 47) && (ret < 58))) {
			return true;
		}
	}

	return false;
}



/*****************************************************************************
 * String
 * param str : 문자열
 * author	: No Name
 *****************************************************************************/
function fncPassOnlyEng(str) {
	var ret;
	var j = 0;
	var alpha_num_Str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	for (var i = 0; i < str.length; i++) {
		var substr = str.substring(i,i+1);
		if (alpha_num_Str.indexOf(substr) < 0) {
			//영문자가 아닌값
		}else{
			j = j + 1;	//영문자
		}
	}

	if (j == str.length) {
		return false;	//영문자만 있는경우
	}else{
		return true;	//영문자와 다른 문자가 있는경우
	}
}


/*****************************************************************************
 * String 길이 체크(length)
 * param formName : 폼이름
 * param fieldName : 필드명
 * param min : 최소 길이
 * param max : 최대 길이
 * param str : 뿌려질 메세지
 * author	: No Name
 *****************************************************************************/
function fncChkLength(formName, fieldName, min, max, str) {
	var f = document.forms[formName];
	var obj = f[fieldName];

	tmpStr = obj.value;
	len = tmpStr.length;

	if(min==max){
		if(len<min || len>max){
			if(obj.value!=null){
				if(str!=null){
					alert(str + " 길이가 잘못되었습니다. \r\n" + min +"자로 입력하세요");
				}
				obj.focus();
			}

			return false;
		}
	}
	else if(len<min){
		if(obj.value!=null){
			if(str!=null){
				alert(str + " 길이가 너무 짧습니다. \r\n" + min +"자 이상으로 입력하세요");
			}
			obj.focus();
		}
		return false;
	}
	else if(len>max){
		if(obj.value!=null){
			if(str!=null){
				alert(str + " 길이가 초과되었습니다.\r\n" + max +"자 이하로 입력하세요");
			}
			obj.focus();
		}
		return false;
	}

	return true;
}


/*****************************************************************************
 * String 길이 체크(바이트)
 * param formName : 폼이름
 * param fieldName : 필드명
 * param min : 최소 길이
 * param max : 최대 길이
 * param str : 뿌려질 메세지
 * author	: No Name
 *****************************************************************************/
function fncChkBytes(formName,fieldName,min,max,str){
	var f = document.forms[formName];
	var obj = f[fieldName];

	tmpStr = obj.value;
	len = fncCalBytes(tmpStr);

	if(min==max){
		if(len<min || len>max){
			if(str!=null){
				alert(str + " 길이가 잘못되었습니다. \r\n" + (min/2) + "자, 영문(숫자) " + min +"자로 입력하세요");
			}
			obj.focus();

			return false;
		}
	}
	else if(len<min && min!=0){
		if(str!=null){
			alert(str + " 길이가 너무 짧습니다. \r\n한글 " + (min/2) + "자, 영문(숫자) " + min +"자 이상으로 입력하세요");
		}
		obj.focus();

		return false;
	}
	else if(len>max){
		if(str!=null){
			alert(str + " 길이가 초과되었습니다. \r\n한글 " + (max/2) + "자, 영문(숫자) " + max +"자 이하로 입력하세요");
		}
		obj.focus();

		return false;
	}

	return true;
}


/*****************************************************************************
 * String byte 변환
 * param str :
 * author	: No Name
 *****************************************************************************/
function fncCalBytes(str){
	tmpStr = str;
	strLength = tmpStr.length;
	var one_char;
	var bytes = 0;

	for (k=0;k<strLength;k++){
		one_char = tmpStr.charAt(k);

		if(escape(one_char).length>4){
			bytes += 2;
		}
		else if(one_char!='\r'){
			bytes++;
		}
	}

	return bytes;
}


/*****************************************************************************
 * String 숫자체크
 * param formName : 폼이름
 * param fieldName : 필드명
 * param str : 뿌려질 메세지
 * author	: No Name
 *****************************************************************************/
var permNum = "0123456789";
function fncChkNumber(formName, fieldName, str) {
	var f = document.forms[formName];
	var obj = f[fieldName];

	tmpStr = obj.value;

	for(i=0 ; i<tmpStr.length ; i++){
		if((permNum).indexOf(tmpStr.charAt(i))<0){
			if(str!=null){
				alert(str + " 잘못 입력하였습니다.\r\n숫자만 입력이 가능합니다.");
			}
			obj.value = '';
			obj.focus();
			return false;
		}
	}
	return true;
}


/*****************************************************************************
 * String 숫자체크
 * param formName : 폼이름
 * param fieldName : 필드명
 * param str : 뿌려질 메세지
 * author	: No Name
 *****************************************************************************/
var permNum1 = "0123456789.";
function fncChkNumberPoint(formName, fieldName, str) {
	var f = document.forms[formName];
	var obj = f[fieldName];

	tmpStr = obj.value;

	for(i=0 ; i<tmpStr.length ; i++){
		if((permNum1).indexOf(tmpStr.charAt(i))<0){
			if(str!=null){
				alert(str + " 잘못 입력하였습니다.\r\n숫자만 입력이 가능합니다.");
			}
			obj.value = '';
			obj.focus();
			return false;
		}
	}
	return true;
}



/*****************************************************************************
 * String 이메일 유효성 검사
 * param TextBoxID : 이메일 아이디
 * param TextBoxDomain : 이메일 도메인
 * param ComboBoxDomain :
 * param disableNullAlert :
 * author	: No Name
 *****************************************************************************/
function fncChkEmail(TextBoxID, TextBoxDomain, ComboBoxDomain) {
	emailID = TextBoxID.value.replace(/ /g, "");

	if(emailID.length ==0) {
		alert("이메일 아이디를 입력하세요");
		TextBoxID.focus();
		return false;
	}

	emailID = TextBoxID.value;
/*
	if(ComboBoxDomain.value.length == 0) {
		alert("이메일 도메인을 선택하세요");
		ComboBoxDomain.focus();
		return false;
	}
*/
	if(emailID.indexOf(".")==0 || emailID.indexOf(";")>-1 || emailID.indexOf(" ")>-1 || emailID.replace(/[A-Za-z0-9._\-]/g,"").length!=0) {
		alert("이메일 형식이 올바르지 않습니다.");
		TextBoxID.focus();
		return false;
	}

	emailDomain=TextBoxDomain.value;

	if(emailDomain.indexOf(".")<1 || emailDomain.indexOf(".")>= emailDomain.length-1 || emailDomain.indexOf(";")>-1
		|| emailDomain.indexOf(" ") >-1
		|| emailDomain.charAt(emailDomain.length-1) == "."
		|| emailDomain.replace(/[A-Za-z0-9._\-]/g,"").length!=0)
	{
		alert("이메일 형식이 올바르지 않습니다.");
		TextBoxDomain.focus();
		return false;
	}

	return true;
}


/*****************************************************************************
 * String 포커스 이동
 * param formName : 폼이름
 * param fieldName1 : 필드명(현재)
 * param fieldName2 : 필드명(이동)
 * param size : 텍스트 필드에 입력되는 길이값
 * author	: No Name
 *****************************************************************************/
function fncChkFocus(formName, fieldName1, fieldName2, size){
	var f = document.forms[formName];
	var obj1 = f[fieldName1];
	var obj2 = f[fieldName2];

	var tmpobj1 = obj1.value.length;

	if (tmpobj1 == size) {
		obj2.focus();
	}
}


/*****************************************************************************
 * String 우편번호 검색
 * param formName : 폼이름
 * param val01 : 우편번호 1~3자리
 * param val02 : 우편번호 4~6자리
 * param val03 : 주소
 * param val04 : 상세주소 focus field
 * author	: No Name
 *****************************************************************************/
function fncChkAddress(v){
	winWidth = "516px";
	winHeight = "466px";
	winLeft = screen.width / 2 - winWidth / 2;
	winTop = screen.height / 2 - winHeight / 2;

	elements = "width=" + winWidth;
	elements += ",height=" + winHeight;
	elements += ",top=" + winTop;
	elements += ",left=" + winLeft;

	//var f = document.forms[formName];
	var picWin = window.open(v,"addcheck","scrollbars=no,"+elements);

	//f.target = "addcheck";
	//f.action = "/khgchp/common/jsp/popup/p0004.jsp";
	//f.submit();
}

/*****************************************************************************
 * TextBox에 blur되었을때 작동해야하는 부분을 정의
 * ex) <input type="text" onBlur="onBlurTextBox(this,'DATE');" value='' title=''>
 *	 <input type="text" onBlur="onBlurTextBox(this,'MONEY');" value='' title=''>
 * param str : pObj, pType
 * author	: 김선국
 *****************************************************************************/
function fncOnBlurTextBox(pObj, pType) {
	if (pType == "DATE") {
		if (pObj.value != null && pObj.value != "") {
			// 마스크 적용
			fncAutoMaskEdit(pObj, '####-##-##');

			if (!fncIsValidDate(pObj.value, '-') || pObj.value.length != 10) {
				alert("날짜 형식을 확인하여 주세요.");
				pObj.value="";
				pObj.focus();
			}
		}
	}
	else if(pType=="MONTH") {
		if(pObj.value != null && pObj.value != "") {
			// 마스크 적용
			fncAutoMaskEdit(pObj,'####-##');

			if(!fncIsValidMonth(pObj.value.substring(pObj.value.length -2,pObj.value.length)) || pObj.value.length != 7) {
				alert("날짜 형식을 확인하여 주세요.");
				pObj.value="";
				pObj.focus();
			}
		}
	}
}

/*****************************************************************************
 * 마스크 적용
 * param str : pObj, patternText
 * author	: 김선국
 *****************************************************************************/
function fncAutoMaskEdit(pObj, patternText) {
	if(event) {
		if (event.keyCode != 8) {
			var sourceValue = pObj.value;
			var mixedValue = '';

			for (var i = 0; i < patternText.length; i++) {
				if (patternText.charAt(i) != '#') {
				sourceValue = sourceValue.replace(patternText.charAt(i), '');
			}
		}

		var cutIDX = 0;
		var skipIDX = 0;

		for (var i = 0; i < patternText.length; i++) {
			if (patternText.charAt(i) == '#') {
				if (sourceValue.length > cutIDX) {
					mixedValue = mixedValue + sourceValue.charAt(cutIDX);
					cutIDX++;
				}
			}
			else {
				if (sourceValue.length > cutIDX)
					mixedValue = mixedValue + patternText.charAt(i);
			}
		}

		if (mixedValue != '')
			pObj.value = mixedValue;
		else
			pObj.value = pObj.value;
		}
	}
}

/*****************************************************************************
 * 날짜 유효성 검사
 * ex) if(!fncIsValidDate('2004-05-06','-'))
 *		alert("올바르지 않은 날짜 형식 입니다.");
 *	 if(!fncIsValidDate('2004','05','06'))
 *		alert("올바르지 않은 날짜 형식 입니다.");
 * param str : yyyy, mm, dd
 * author	: 김선국
 *****************************************************************************/
function fncIsValidDate(yyyy, mm, dd) {
	if (fncIsValidDate.arguments.length == 2) {
		fncIsValidDay(yyyy);

		var arrValue;
		arrValue = yyyy.split(mm);
		if (arrValue.length == 3) {
			yyyy = arrValue[0];
			mm = arrValue[1];
			dd = arrValue[2];
		}
		else
			return false;
	}

	yyyy = Number(yyyy);
	if (isNaN(yyyy)) return false;

	mm = Number(mm);
	if (isNaN(mm)) return false;

	dd = Number(dd);
	if (isNaN(dd)) return false;

	return (fncIsValidMonth(mm) && fncIsValidDay(yyyy, mm, dd));
}

/*****************************************************************************
 * 유효한(존재하는) 월인지 체크
 * param str : mm
 * author	: 김선국
 *****************************************************************************/
function fncIsValidMonth(mm) {
	var m = parseInt(mm, 10);
	return (m >= 1 && m <= 12);
}

/*****************************************************************************
 * 유효한(존재하는) 일인지 체크
 * param str : yyyy, mm, dd
 * author	: 김선국
 *****************************************************************************/
function fncIsValidDay(yyyy, mm, dd) {
	var m = parseInt(mm, 10) - 1;
	var d = parseInt(dd, 10);

	var end = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	if ((yyyy % 4 == 0 && yyyy % 100 != 0) || yyyy % 400 == 0) {
		end[1] = 29;
	}

	return (d >= 1 && d <= end[m]);
}


/*****************************************************************************
 * 자릿수 콤마 제거
 * param str : formName, fieldName
 * author	: 김선국
 *****************************************************************************/
function fncNonCipherV(obj){
	number = obj.value ;
	data   = number + "" ;
	while(data.indexOf(",")>=0){
		idx = data.indexOf(",") ;
		data = data.substring(0,idx) + data.substring(idx+1) ;
	}
	return data ;
}

/*****************************************************************************
 * 숫자체크(value)
 * param str :value
 * author	: 김선국
 *****************************************************************************/
function fncChkNumberV(value){
	tmpStr = value ;

	for(i=0 ; i<tmpStr.length ; i++){
		if((permNum).indexOf(tmpStr.charAt(i))<0)
			return false ;
	}
	return true ;
}

/*******************************************************************************
 * 공백 체크(formName, fieldName, str)
 * param formName : 폼이름
 * param fieldName : 필드명
 * param str : 에러메시지
 * author	: 임종세
 *
*******************************************************************************/
function fncChkSpace(formName, fieldName, str){
 var f   = document.forms[formName] ;
	var obj = f[fieldName] ;


	tmpStr = obj.value ;

	for(i=0 ; i<tmpStr.length ; i++){
		if(tmpStr.charAt(i)!=" ")
			return true ;
	}

	if(str!=null){
		alert(str + " 입력하세요") ;
	}
	obj.focus() ;

	return false ;
}
/*******************************************************************************
* 주민등록번호 체크
*	- formName   : 폼이름
*  - fieldName1 : 주민번호 앞자리
*  - fieldName2 : 주민번호 뒷자리
*	- str  : 뿌려질 메세지 ex) "주민번호를"
*	author	: 박유혁
*******************************************************************************/
function fncChkRegNum(formName, fieldName1, fieldName2, str){
	var f    = document.forms[formName] ;
	var obj1 = f[fieldName1] ;
	var obj2 = f[fieldName2] ;

	if(!fncChkSpace(formName, fieldName1, str)) return false ;
	if(!fncChkNumber(formName, fieldName1, str)) return false ;
	if(!fncChkLength(formName, fieldName1, 6, 6, str)) return false ;

	if(!fncChkSpace(formName, fieldName2, str)) return false ;
	if(!fncChkNumber(formName, fieldName2, str)) return false ;
	if(!fncChkLength(formName, fieldName2, 7, 7, str)) return false ;

	sIdno = obj1.value + "-" + obj2.value ;

	a1 = sIdno.substring(0, 1);
	a2 = sIdno.substring(1, 2);
	a3 = sIdno.substring(2, 3);
	a4 = sIdno.substring(3, 4);
	a5 = sIdno.substring(4, 5);
	a6 = sIdno.substring(5, 6);
	a7 = sIdno.substring(7, 8);
	a8 = sIdno.substring(8, 9);
	a9 = sIdno.substring(9, 10);
	a10 = sIdno.substring(10, 11);
	a11 = sIdno.substring(11, 12);
	a12 = sIdno.substring(12, 13);
	a = sIdno.substring(13, 14);

	x = a1*2 + a2*3 + a3*4 + a4*5 + a5*6 + a6*7 + a7*8 + a8*9 + a9*2 + a10*3 + a11*4 + a12*5;

	xx = x % 11;

	if (xx == 10) xx = 0;
	a = 11 - a;

	if (a == 11) a = 1;
	else if (a == 10) a = 0;

	if (xx == a)
		return true;
	else {
		if(str!=null){
			alert(str + " 잘못 입력하였습니다. \r\n형식에 맞게 입력하세요") ;
		}
		obj1.focus() ;

		return false;
	}
}

/*******************************************************************************
* 문자열 바꾸기
*  - str    : 원본 문자열
*  - oldStr : 바꿀 문자열
*  - newStr : 새 문자열
*	author	: 박유혁
*******************************************************************************/
function fncReplace(str, oldStr, newStr){

	tmpStr    = str ;
	ret_str   = ""
	left_str  = "" ;
	right_str = "" ;

	if(str.indexOf(oldStr)==-1){
		return str ;
	}

	while(tmpStr.indexOf(oldStr)>=0){
		left_str  = tmpStr.substring(0,tmpStr.indexOf(oldStr)) ;
		right_str = tmpStr.substring(tmpStr.indexOf(oldStr)+oldStr.length) ;

		ret_str += left_str + newStr;
		tmpStr = right_str ;
	}

	return ret_str + right_str ;
}

/*******************************************************************************
* 다운로드링크
*  - path : 파일경로
*  - file   : 파일명
*  - author	: 박유혁
*******************************************************************************/
function down(path, save, orcy){
	top.location.href = "/khgchp/common/jsp/downLoad.jsp?d="+path+"&f="+save+"&o="+orcy;
}


/*****************************************************************************
 * String 팝업창으로 나오는 SendMail
 * author	: No Name
 *****************************************************************************/
function fncChkSendMail(name, empno){
	winWidth  = "516";
	winHeight = "466";
	winLeft = screen.width  / 2 - winWidth  / 2 ;
	winTop = screen.height / 2 - winHeight / 2 ;

	elements  = "width="   + winWidth ;
	elements += ",height=" + winHeight ;
	elements += ",top="    + winTop ;
	elements += ",left="   + winLeft ;

	var f = document.frm ;
}

/*****************************************************************************
 * String 팝업창으로 나오는 탈퇴 SendMail
 * author	: No Name
 *****************************************************************************/
function fncChkContractSendMail(name, id, eml_addr){
	winWidth  = "516";
	winHeight = "466";
	winLeft = screen.width  / 2 - winWidth  / 2 ;
	winTop = screen.height / 2 - winHeight / 2 ;

	elements  = "width="   + winWidth ;
	elements += ",height=" + winHeight ;
	elements += ",top="    + winTop ;
	elements += ",left="   + winLeft ;

	var f = document.frm ;
}

/*****************************************************************************
 * String 관리자에게 SendMail 하는 팝업창 띄우기
 * author	: 김소영
 *****************************************************************************/
function fncAdminSendMail(){
	winWidth  = "516";
	winHeight = "466";
	winLeft = screen.width  / 2 - winWidth  / 2 ;
	winTop = screen.height / 2 - winHeight / 2 ;

	elements  = "width="   + winWidth ;
	elements += ",height=" + winHeight ;
	elements += ",top="    + winTop ;
	elements += ",left="   + winLeft ;

	var f = document.frm ;
}

/*******************************************************************************
 * 입력값에 ' ? 입력 불가능하게 만들기(' = 39, ? = 63)
 * author	:
*******************************************************************************/
function fncCheckKeyCode(e) {
	if(e.keyCode == 39 || e.keyCode == 63)
		e.returnValue = false;
}



/* ----------------------------------------------------------------------------------------------------
 *	iCross Develope.Team Publishing.Div
 * Author	: 2003-04-05 by 2dea
 * Descript	: 2dea Common Script
 * Edit+	: 2011-07-20 by 2dea
 ---------------------------------------------------------------------------------------------------- */

function objE(param) {
    return document.getElementById(param);
}

function getUrl(url) {
    return window.location.href=url;
}

function getGo(value) {
    return window.history.go(value);
}

function getBack() {
    return window.history.back();
}

function getForward() {
    return window.history.forward();
}

function getRefresh() {
    return window.location.reload(true);
}

function getReload() {
    return location.reload();
}

function getClose() {
    return window.close();
}

function getPrint() {
    return window.print();
}

function newWindow(url,target,w,h,s,p) {
    var win;

    if (p == 1) {
        if (self.innerHeight) {
            windowWidth = self.innerWidth;
            windowHeight = self.innerHeight;
        }
        else if (document.documentElement && document.documentElement.clientHeight) {
            windowWidth = document.documentElement.clientWidth;
            windowHeight = document.documentElement.clientHeight;
        }
        else if (document.body) {
            windowWidth = document.body.clientWidth;
            windowHeight = document.body.clientHeight;
        }
        var x = (windowWidth-w)/2;
        var y = (windowHeight-h)/2;

        win = window.open(url,target,"left="+x+",top="+y+",width="+w+",height="+h+",scrollbars="+s+",resizable=0,status=1");
    } else {
        win = window.open(url,target,"width="+w+",height="+h+",scrollbars="+s+",resizable=0,status=1");
    }
    win.focus();

    return win;
}

function newFullWindow(url) {
    window.open(url,"_blank","fullscreen");
}

function embedSwf(url,id,w,h,wm) {
    var wmode = (wm == 1) ? 'window' : 'transparent';
    var embed = "";
    embed = '<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" id="'+id+'" width="'+w+'" height="'+h+'">';
    embed += '  <param name="movie" value="'+url+'" \/>';
    embed += '  <param name="quality" value="high" \/>';
    embed += '  <param name="bgcolor" value="#FFFFFF" \/>';
    embed += '  <param name="wmode" value="'+wmode+'" \/>';
    embed += '  <param name="menu" value="false" \/>';
    embed += '  <param name="allowscriptaccess" value="always" \/>';
    embed += '  <param name="allowfullscreen" value="true" \/>';
    embed += '  <embed type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" name="'+id+'" src="'+url+'" width="'+w+'" height="'+h+'" quality="high" bgcolor="#FFFFFF" wmode="'+wmode+'" menu="false" allowscriptaccess="always" allowfullscreen="true"><\/embed>';
    embed += '<\/object>';

    document.write(embed);
}

function embedWmp(url,id,w,h,loop,autostart) {
    var embed = "";
    embed = '<object type="application/x-oleobject" classid="clsid:22D6f312-B0F6-11D0-94AB-0080C74C7E95" codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=6,4,7,1112" id="'+id+'" name="'+id+'" width="'+w+'" height="'+h+'" standby="Loading Windows Media Player components...">';
    embed += '  <param name="filename" value="'+url+'" \/>';
    embed += '  <param name="autostart" value="'+autostart+'" \/>';
    embed += '  <param name="autosize" value="0" \/>';
    embed += '  <param name="loop" value="'+loop+'" \/>';
    embed += '  <embed type="application/x-mplayer2" name="'+id+'" id="'+id+'" src="'+url+'" width="'+w+'" height="'+h+'" autostart="'+autostart+'" autosize="0" playcount="infinite"><\/embed>';
    embed += '<\/object>';

    document.write(embed);
}

function imgSwap(obj) {
    if (obj != "undefined" && obj.tagName == "A") {
        var childObj = obj.childNodes;
        if (childObj[0].tagName == "IMG") {
            var strOn = "_on.";
            var strOr = "_or.";
            var tempSrc = childObj[0].src;
            if (tempSrc.indexOf(strOr) != -1) {
                childObj[0].src = tempSrc.replace(strOr, strOn);
            }
            else {
                childObj[0].src = tempSrc.replace(strOn, strOr);
            }
        }
    }
}

function labelCheck(obj,id) {
    if (navigator.appVersion.indexOf('MSIE') != -1) {
        if (obj && obj.tagName == "LABEL") {
            var childObj = obj.childNodes;
            if (childObj[0].tagName == "IMG") {
                if (document.getElementById(id).type == "checkbox" || document.getElementById(id).type == "radio" || document.getElementById(id).type == "file") {
                    document.getElementById(id).click();
                }
                else {
                    document.getElementById(id).focus();
                }
            }
        }
    }
}

function clearValue(obj) {
    obj.style.backgroundPosition='-10240px -10240px';
}

function iframeResize(obj) {
    targetObj=obj;
    //ih = targetObj.document ? targetObj.document.body.scrollHeight : targetObj.contentDocument.body.scrollHeight;
    ih = targetObj.contentWindow ? targetObj.contentWindow.document.body.scrollHeight : targetObj.contentDocument.body.scrollHeight;
    ih = ih+25;
    targetObj.style.height = ih + "px";
}

function getPosition(e) {
    e = e || window.event;
    var cursor = {x:0, y:0};
    if (e.pageX || e.pageY) {
        cursor.x = e.pageX;
        cursor.y = e.pageY;
    }
    else {
        var de = document.documentElement;
        var b = document.body;
        cursor.x = e.clientX +
            (de.scrollLeft || b.scrollLeft) - (de.clientLeft || 0);
        cursor.y = e.clientY +
            (de.scrollTop || b.scrollTop) - (de.clientTop || 0);
    }
    return cursor;
}

function deleteCookie(id) {
	var toDay = new Date();
	var expirDate = new Date();
	expirDate.setTime(toDay.getTime()-1);
	document.cookie = id + "=; path=/; expires=" + expirDate.toGMTString + ";";
}

function getCookie(id) {
	var arg = id + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while(i < clen) {
		var offset = i + alen;

		if(document.cookie.substring(i, offset) == arg) {
			var endstr = document.cookie.indexOf(";", offset);
			if(endstr == -1)
				endstr = document.cookie.length;
			return unescape(document.cookie.substring(offset, endstr));
		}

		i = document.cookie.indexOf(" ", i) + 1;

		if(i == 0)
			break;
	}
	return  null;
}

function setCookie(id, value, expirDate) {
	document.cookie = id + "=" + escape(value) + "; path=/; expires=" + expirDate.toGMTString + ";";
}

function floatLayer(target,position,topLimit,btmLimit) {
    if (!target)
        return false;
    var obj = document.getElementById(target);
    obj.initTop = position;
    obj.topLimit = topLimit;
    obj.bottomLimit = document.documentElement.scrollHeight - btmLimit;
    obj.style.position = "absolute";
    obj.top = obj.initTop;
    obj.left = obj.initLeft;
    if (typeof(window.pageYOffset) == "number") {
        obj.getTop = function() {
            return window.pageYOffset;
        }
    } else if (typeof(document.documentElement.scrollTop) == "number") {
        obj.getTop = function() {
            return document.documentElement.scrollTop;
        }
    } else {
        obj.getTop = function() {
            return 0;
        }
    }
    if (self.innerHeight) {
        obj.getHeight = function() {
            return self.innerHeight;
        }
    } else if(document.documentElement.clientHeight) {
        obj.getHeight = function() {
            return document.documentElement.clientHeight;
        }
    } else {
        obj.getHeight = function() {
            return 500;
        }
    }
    obj.move = setInterval(function() {
        if (obj.initTop > 0) {
            pos = obj.getTop() + obj.initTop;
        } else {
            pos = obj.getTop() + obj.getHeight() + obj.initTop;
            //pos = obj.getTop() + obj.getHeight() / 2 - 15;
        }
        if (pos > obj.bottomLimit)
            pos = obj.bottomLimit;
        if (pos < obj.topLimit)
            pos = obj.topLimit;
        interval = obj.top - pos;
        obj.top = obj.top - interval / 3;
        obj.style.top = obj.top + "px";
    }, 10)
}

function tabMain(dotabid,num) {
    var inum=parseInt(num)-1;
    var linkTab=document.getElementById(dotabid).getElementsByTagName("h3");
    for (i=0;i<linkTab.length;i++) {
        var tabimg = linkTab.item(i).getElementsByTagName("img").item(0);
        var tabContents= document.getElementById(dotabid+(1+i));
        if (i==inum) {
            if(tabContents.style.display!="block") {
                tabimg.src=tabimg.src.replace("_or.","_on.");
                tabContents.style.display="block";
            }
        } else {
            tabimg.src=tabimg.src.replace("_on.","_or.");
            tabContents.style.display="none";
            if(navigator.appVersion.indexOf('MSIE 8') >= 0) {
                document.body.style.border="none";
            }
        }
    }
}

function tabMenu(dotabid,num) {
    var inum=parseInt(num)-1;
    var linkTab=document.getElementById(dotabid).getElementsByTagName("li");
    for (i=0;i<linkTab.length;i++) {
        var tabimg = linkTab.item(i).getElementsByTagName("img").item(0);
        var tabContents= document.getElementById(dotabid+(1+i));
        if (i==inum) {
            if(tabContents.style.display!="block") {
                tabimg.src=tabimg.src.replace("_or.","_on.");
                tabContents.style.display="block";
            }
        } else {
            tabimg.src=tabimg.src.replace("_on.","_or.");
            tabContents.style.display="none";
            if(navigator.appVersion.indexOf('MSIE 8') >= 0) {
                document.body.style.border="none";
            }
        }
    }
}
function tabMenu2(dotabid,num) {
    var inum=parseInt(num)-1;
    var linkTab=document.getElementById(dotabid).getElementsByTagName("div");
	
    for (i=0;i<linkTab.length;i++) {
        var tabContents= document.getElementById(dotabid+(1+i));
        
        var tabimg = document.getElementById(dotabid+'_img'+i);
        if (i==inum) {
            if(tabContents.style.display!="block") {
                tabimg.src=tabimg.src.replace("_or.","_on.");
                //alert(tabimg.src);
                tabContents.style.display="block";
            }
        } else {
            tabimg.src=tabimg.src.replace("_on.","_or.");
            tabContents.style.display="none";
            if(navigator.appVersion.indexOf('MSIE 8') >= 0) {
                document.body.style.border="none";
            }
        }
    }
}

function tabText(dotabid,num) {
    var inum=parseInt(num)-1;
    var linkTab=document.getElementById(dotabid).getElementsByTagName("li");
    for (i=0;i<linkTab.length;i++) {
        var tabContents= document.getElementById(dotabid+(1+i));
        if (i==inum) {
            if(tabContents.style.display!="block") {
                linkTab.item(inum).className="focus";
                tabContents.style.display="block";
            }
        } else {
            linkTab.item(i).className="";
            tabContents.style.display="none";
            if(navigator.appVersion.indexOf('MSIE 8') >= 0) {
                document.body.style.border="none";
            }
        }
    }
}

var nowZoom = 100;
var maxZoom = 200;
var minZoom = 100;
var increaseNum = 10;
function zoomIn() {
    if (nowZoom < maxZoom) {
        nowZoom += increaseNum;
    } else {
        return;
    }

    document.getElementById('dobody').style.zoom = nowZoom + "%";
}

function zoomOut() {
    if (nowZoom > minZoom) {
        nowZoom -= increaseNum;
    } else {
        return;
    }

    document.getElementById('dobody').style.zoom = nowZoom + "%";
}

function zoomRenew() {
    nowZoom = minZoom;
    document.getElementById('dobody').style.zoom = nowZoom + "%";
}

var elem = "tr";
var rClick;
function rowHiligh() {
    if(document.getElementsByTagName) {
        var el = document.getElementsByTagName(elem);
        for(var i=0; i<el.length; i++) {
            if(el[i].childNodes[0].tagName != "th"
            && el[i].parentNode.parentNode.className.indexOf("list") != -1) {
                if (!(el[i].className == "notice" || el[i].className == "first-child notice")) {
                    if(i%2 == 1) {
                        el[i].className = "first";
                        el[i].oldClassName = "first";
                        el[i].onmouseout  = function() {
                            this.className = "first";
                        }
                    }
                    else {
                        el[i].className = "second";
                        el[i].oldClassName = "second";
                        el[i].onmouseout  = function() {
                            this.className = "second";
                        }
                    }
                }
                el[i].onmouseover = function() {
                    if(!(this.className == "notice" || this.className == "first-child notice")) {
                        if(this.className == this.oldClassName) {
                            this.className = "hover";
                        }
                        else if(this.onmouseout == null && this.className != "click") {
                            this.onmouseout = function() {
                                this.className = this.oldClassName;
                            }
                        }
                    }
                }
            }
        }
    }
}

	/*******************************************************************
	  	FUNCTION		:	isEmpty
	  	DESCRIPTION		:	입력값에 스페이스 이외의 의미있는 값이 있는지 체크
	  	PARAMETER		:
	*******************************************************************/
	function isEmpty(input) {

		if (input == null || trim(input) == "" ) {
	  		return true;
	  	}
	  	return false;
	}

	/*******************************************************************
	* FUNCTION		:	isNumberValue
	* DESCRIPTION	:	문자열이 숫자만으로 되어있는지 체크
	* PARAMETER		:
	*******************************************************************/
	function isNumberValue(input){

		var chars = "0123456789";

		if(isEmpty(input)) {
			return false;
		}

		for (var inx = 0; inx < input.length; inx++) {
		   if (chars.indexOf(input.charAt(inx)) == -1) {
			   return false;
		   }
		}
		return true;
	}

	/*******************************************************************
	* FUNCTION			:	chkDateFull
	* DESCRIPTION		:	년월일 유효성 검증
	* PARAMETER			:	datestr		검증할 날짜(YYYYMMDD)
	*******************************************************************/
	function chkDateFull(datestr, msg){

		if(isEmpty(datestr)){
			return false;
		}

		datestr = datestr.replace(/-/g, "");

		if(!isNumberValue(datestr)){
			alert(msg + " 숫자를 입력하셔야 합니다.");
			return false;
		}

		if(datestr.length != 8){
			alert(msg + " 년월일 8자리를 입력하셔야 합니다.");
			return false;
		}

		if(isEmpty(msg) || msg == "undefined"){
			msg = "";
		}

		var yy = Number(datestr.substring(0,4));
		var mm = Number(datestr.substring(4,6));
		var dd = Number(datestr.substring(6,8));

		if( !(mm > 0 && mm < 13) ){

			if(msg == "")
				alert(" 유효하지 않은 월입니다.\n 월은 1~12 까지만 입력할 수 있습니다.");
			else
				alert(msg + " 유효하지 않은 날자입니다.\n 월은 1~12 까지만 입력할 수 있습니다.");

			return false;
		}

		//윤년 검증
		var boundDay = "";
		if(mm != 2){
			var mon=new Array(31,28,31,30,31,30,31,31,30,31,30,31);
			boundDay = mon[mm-1];

		} else{
			if (yy%4 == 0 && yy%100 != 0 || yy%400 == 0){
				boundDay = 29;
			}
			else{
				boundDay = 28;
			}
		}

		//일 검증
		if( (dd <= 0) || (dd > boundDay) ){
			if(msg == "")
				alert("유효하지 않은 일자입니다.\n (" + yy + "년 " + mm + "월은 " + boundDay + "일 까지 있습니다.)");
			else
				alert(msg + " 유효하지 않은 날자입니다.\n (" + yy + "년 " + mm + "월은 " + boundDay + "일 까지 있습니다.)");


			return false;
		}

		return true;
	}
	

	function only(value){
	    var regExp =  /[\{\}\[\]\/?,;:|\)*~`!^\-_+<>\#$%&\\\=\(\'\"]/gi;
	    if(regExp.test(value)){

	       return false;
	    } else{
	    	return true;
	    }
	}
	
	

function pwdCheck(min, max, pwd,simpleChar, mmbrid){

	var minchk=true;
	var maxchk=true;

	var numchk=true;
	var charchk=true;
	var specchk=true;
	var samechk=true;
	var pcharchk=true;
	var mcharchk=true;
	var quartychk=true;
	var simplechk=true;
	var spchRegchk=true;
	var midchk=true;
	//var msg="";

	var textRegular=/[a-zA-Z]/g;
	var numberRegular=/[0-9]/g;
	var specailRegular=/[\W]/g;
	var sameChar=/(\w)\1\1/g;
	var quarty="qwertyuiopasdfghjklzxcvbnm";
	//var simpleChar="sky,hope,hello,bye,happy,jun";
	var spchReg=/[<>\(\)\#'\/|]/g; //해당특수문자 안됨.
	
	var plusCharCount=0;
	var minusCharCount=0;

	if(pwd.length<min){
		minchk=false;
		alert("패스워드는 " + min +"자이상 입력하셔야 합니다.");
		return false;
	}
	if(pwd.length>max){
		maxchk=false;
		alert("패스워드는 "+max +"자 이내로 입력하셔야 합니다.");
		return false;
	}
	charchk= textRegular.test(pwd);//텍스트
	numchk=numberRegular.test(pwd); // 숫자문자
	specchk=specailRegular.test(pwd);//special
	samechk=!sameChar.test(pwd); // 동일문자
	spchRegchk=!spchReg.test(pwd); //특수문자중 (등 문자
	
	if(!charchk){
		alert("패스워드에 영문자가 포함되어야 합니다." );
		return false;
	}
	if(!numchk){
		alert("패스워드에 숫자가 포함되어야 합니다.");
		return false;
	}
	if(!specchk){
		alert("패스워드에 특수문자가 포함되어야 합니다.");
		return false;
	}
	if(!spchRegchk){
		alert("패스워드에 특수문자 중 <, >, (, ), #, ', /, | 는 사용할 수 없습니다.");
		return false;
	}
	if(!samechk){
		alert("패스워드에 동일문자가 3회이상 포함되어 있습니다." );
		return false;
	}
	//연속문자(123,abc
	for(var i=0;i<pwd.length;i++){
		chr1=pwd.charAt(i);
		chr2=pwd.charAt(i+1);
		chr3=pwd.charAt(i+2);
		if(chr1.charCodeAt(0) - chr2.charCodeAt(0)==1 && chr2.charCodeAt(0)-chr3.charCodeAt(0)==1){
			plusCharCount++;
			pcharchk=false;
			alert("패스워드에 연속된 문자가 포함되어 있습니다.");
			return false;
		}
		if(chr1.charCodeAt(0) - chr2.charCodeAt(0)==-1 && chr2.charCodeAt(0)-chr3.charCodeAt(0)==-1){
			minusCharCount++;
			mcharchk=false;
			alert("패스워드에 연속된 문자가 포함되어 있습니다.");
			return false;
		}
	}
	
	if(mmbrid!="undefined"){
		if(mmbrid.length>0){
			for(var i=0;i<mmbrid.length-2;i++){
				var idchk=mmbrid.substring(i,i+3);
				if(pwd.indexOf(idchk)!=-1){
					midchk=false;
					alert("아이디와 3자리 이상 일치하는 비밀번호는 사용할 수 없습니다.");
					return false;
				}
			}
		}
	}
	//if(plusCharCount>=2||minusCharCount>=2){ // 123, abc .. >=3 : 1234,abcd
		//msg="연속문자가 포함되어 있습니다.";
	//}

	//quarty 쿼티 자판문자
	for(var i=0;i<quarty.length-3;i++){
		var qchk=quarty.substring(i,i+4);
		if(pwd.indexOf(qchk)!=-1){
			quartychk=false;
			alert("패스워드에 qwer 등 키보드 연속문자열이 포함되어 있습니다.");
			return false;
		}
	}
	//쉬운단어
	for(var i=0;i<simpleChar.split(",").length;i++){
		if(pwd.indexOf(simpleChar.split(",")[i])!=-1){
			simplechk=false;
			//$("#qt").append(simpleChar.split(",")[i]);
			alert("패스워드에 알기쉬운문자 " + simpleChar.split(",")[i] +"가 포함되어 있습니다.");
			return false;
		}
	}

	if(minchk && maxchk&&numchk && charchk && specchk && samechk && pcharchk && mcharchk && quartychk && simplechk){
		return true;
	}else{
		return false;
	}
}

/* --------------------------------------------------
알파벳인지 체크
------------------------------------------------*/
function isAlphabet(ch) {
 var numUnicode = ch.charCodeAt(0); // number of the decimal Unicode
 if ( 65 <= numUnicode && numUnicode <= 90 ) return true;            // 대문자
 if ( 97 <= numUnicode && numUnicode <= 122 ) return true;            // 소문자
 return false;
}

/* --------------------------------------------------
한글인지 체크
------------------------------------------------*/
function isKorean(ch) {

 var numUnicode =ch.charCodeAt(0); 
 //alert(numUnicode);
 if ( 44032 <= numUnicode && numUnicode <= 55203 || 12593 <= numUnicode && numUnicode <= 12643 ) return true;            
return false;
}

 /**
  * 한글또는 영문이 아니면 alert
  * */
function isKoreanAlphabet(formName, fieldName, str) {
	//alert(fieldName);
	var f = document.forms[formName];
	var obj = f[fieldName];

	var tmpStr = obj.value;
	for(var i=0 ; i<tmpStr.length ; i++){
		
		if(!isKorean(tmpStr.charAt(i)) && !isAlphabet(tmpStr.charAt(i))){
			alert(str + " 한글또는 영문 입력이 가능합니다.");
			obj.value = '';
			obj.focus();
			return false;
		}
	}
	return true;
}

/**
 *  field의 byte 체크.
 * */
function fncChkByteschk(formName,fieldName,min,max,str){
	var f = document.forms[formName];
	var obj = f[fieldName];
	var tmpStr = obj.value;
	var rbyte = 0;
	var rlen = 0;
	var one_char = "";
	var str2 = ""; 

	for(var i=0; i<tmpStr.length; i++){
	one_char = tmpStr.charAt(i);
		if(escape(one_char).length > 4){
		    rbyte += 2;                                         //한글2Byte
		}else{
		    rbyte++;                                            //영문 등 나머지 1Byte
		}
	
		if(rbyte <= max){
		    rlen = i+1;                                          //return할 문자열 갯수
		}
	}
	if(rbyte > max){
		alert(str+" " + max +" btye 까지 입력가능 합니다.");
	    str2 = tmpStr.substr(0,rlen);                                  //문자열 자르기
	    obj.value = str2;
	//    fnChkByte(obj, maxByte);
	return false;
	}
	return true;
}


function fncChkLenPwd(formName, fieldName, max) {
	var f = document.forms[formName];
	var obj = f[fieldName];

	tmpStr = obj.value;
	len = tmpStr.length;

	if(len>max){
			alert("비밀번호 입력 최대글자수가 초과되었습니다");
			obj.value=obj.value.substring(0,20);
			obj.focus();
			return false;
	}

	return true;
}

//textarea byte 제한
function fnChkByte(obj, maxByte){
	var str = obj.value;
	var str_len = str.length;

	var rbyte = 0;
	var rlen = 0;
	var one_char = "";
	var str2 = ""; 

	for(var i=0; i<str_len; i++){
	one_char = str.charAt(i);
	if(escape(one_char).length > 4){
	    rbyte += 2;                                         //한글2Byte
	}else{
	    rbyte++;                                            //영문 등 나머지 1Byte
	}

	if(rbyte <= maxByte){
	    rlen = i+1;                                          //return할 문자열 갯수
	}
	}

	if(rbyte > maxByte){
	    alert("한글 "+(maxByte/2)+"자 / 영문 "+maxByte+"자를 초과 입력할 수 없습니다.");
	    str2 = str.substr(0,rlen);                                  //문자열 자르기
	    obj.value = str2;
	    fnChkByte(obj, maxByte);
	}else{
	    document.getElementById('byteInfo').innerText = rbyte;
	}
}

