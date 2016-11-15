<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 
* 거래처정보를 상세조회하는 화면을 보여주는 JSP입니다.
* 
* history :
*        김대현, 1.0, 2016/08/11 – 초기 작성
* author : 김대현
* version : 1.0, 2016/08/11  - 초기 작성
* see : 관련된 모듈을 기술한다.
// -->
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>대신 장봐주는 남자 - 대.장.남</title>
   
      <!-- 합쳐지고 최소화된 최신 CSS -->
      <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
      
      <!-- 부가적인 테마 -->
      <link href="/resources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
      
      <!-- JQuery 관련 파일들 -->
      <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
      <script src="http://code.jquery.com/jquery-latest.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>   
      
      <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
      <script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
      
      <!-- ClintRead 관련 JS -->
      <script src="../../../../resources/js/deal-js/clintRead.js?ver=1" type="text/javascript" charset="utf-8"></script>
      
      <!-- Table 행 바꾸는 JS -->
      <script src="../../../../resources/jquery.tablednd.js"></script>
      
	<!-- 이미지 썸네일 CSS -->
	<link href="/resources/common/css/thumbnail.css" rel="stylesheet" type="text/css"/>
   </head>
   <body>
		<div style="margin-bottom: 130px;">
			<c:import url="/TopFrame.do"/>
		</div>
      
      <div class="container">
         <form name="clintReadFrm" method="post">
            <input type="hidden" value="${clintRead.clintSeq}" name="clintSeq" id="clintSeq">
            <input type="hidden" value="${clintRead.clintNme}" name="clintNme">
            <input type="hidden" value="${clintRead.clintRepresntatv}" name="clintRepresntatv">
            <input type="hidden" value="${clintRead.clintMangr}" name="clintMangr">
            <input type="hidden" value="${clintRead.clintNum}" name="clintNum">
            <input type="hidden" value="${clintRead.clintMangrNum}" name="clintMangrNum">
            <input type="hidden" value="${clintRead.clintFax}" name="clintFax">
            <input type="hidden" value="${clintRead.clintBankAcout}" name="clintBankAcout">
            <input type="hidden" value="${clintRead.clintAcoutNum}" name="clintAcoutNum">
            <input type="hidden" value="${clintRead.clintSurtaxCheck}" name="clintSurtaxCheck">
            <input type="hidden" value="${clintRead.clintPostcd}" name="clintPostcd">
            <input type="hidden" value="${clintRead.clintAdrs}" name="clintAdrs">
            <input type="hidden" value="${clintRead.clintDetl}" name="clintDetl">
            <input type="hidden" name="currentPageNo" value="${clintVo.currentPageNo}"> <!-- 현재 페이지 -->
         </form>
            <input type="hidden" name="checkedList" id="checkedList">
         
         <div class="container">
            <h3>거래처 정보</h3>
            <div style="display:inline-block; margin-top:1%; float:right">
               <h5>
                 <img src="/resources/image/common/home.png"> > 거래처관리 > 
                 <a href="/ClintList.do" style="text-decoration:none"><strong>거래처 목록</strong></a>
               </h5>
            </div>
         </div>
         <div class="container form-group">
            <table class="table table-bordered">
               <tr>
                  <td class="active">
                        <h5 class="text-center"><b>거래처명</b></h5>
                     </td>
                  <td class="text-center">${clintRead.clintNme}</td>
                  <td class="active">
                        <h5 class="text-center"><b>대표자</b></h5>
                     </td>
                  <td class="text-center">${clintRead.clintRepresntatv}</td>
                  <td class="active">
                        <h5 class="text-center"><b>담당자</b></h5>
                     </td>
                  <td class="text-center">${clintRead.clintMangr}</td>
               </tr>
               <tr>
                  <td class="active">
                        <h5 class="text-center"><b>거래처 번호</b></h5>
                     </td>
                  <td class="text-center">${clintRead.clintNum}</td>
                  <td class="active">
                        <h5 class="text-center"><b>담당자 번호</b></h5>
                     </td>
                  <td class="text-center">${clintRead.clintMangrNum}</td>
                  <td class="active">
                        <h5 class="text-center"><b>팩스</b></h5>
                     </td>
                  <td class="text-center">${clintRead.clintFax}</td>
               </tr>
               <tr>
                  <td class="active">
                        <h5 class="text-center"><b>계좌 은행</b></h5>
                     </td>
                  <td class="text-center">${clintRead.clintBankAcout}</td>
                  <td class="active">
                        <h5 class="text-center"><b>계좌 번호</b></h5>
                     </td>
                  <td class="text-center">${clintRead.clintAcoutNum}</td>
                  <td class="active">
                        <h5 class="text-center"><b>부가세</b></h5>
                     </td>
                  <td class="text-center">${clintRead.clintSurtaxCheck}</td>
               </tr>
               <tr>
                  <td class="active">
                        <h5 class="text-center"><b>주  소</b></h5>
                     </td>
                  <td colspan="5">
                     ${clintRead.clintPostcd} &nbsp;${clintRead.clintAdrs} &nbsp;${clintRead.clintDetl}
                  </td>
               </tr>
            </table>
            <div class="form-inline">
               <div class="form-group pull-left">
                  <input type="button" class="btn btn-default"  value="입고거래장" onclick="javascript:instckExchngFlorPopup(${clintRead.clintSeq})">
                  <input type="button" class="btn btn-default"  value="상품 입고내역" onclick="javascript:instckRecrdListPopup(${clintRead.clintSeq})">
               </div>
               <div class="form-group pull-right">
                  <input type="button" class="btn btn-default"  value="수정" onclick="javascript:clintReadUpdate()">
                  <input type="button" class="btn btn-default"  value="삭제" onclick="javascript:clintReadDelete(${clintRead.clintSeq})">
               </div>
            </div>
         </div>
         
         <div class="container">
            <h3>상품리스트</h3>
         </div>
         
         <!-- 대현아 추가한거다~~~~(시작)~~~~(시작)~~~(시작)~~~(시작)~~~(시작) -->
         <script>
         function productAdd(){
            var clintSeq = $("#clintSeq").val();
            var defH, defW, sTop, sLeft, url;
            defW = 1200;
            defH = 700;
            sTop = (screen.height - defH)/2;
            sLeft= (screen.width  - defW)/2;
            url = "/ProductAddFrame.do?clintSeq="+clintSeq;
            popWin = window.open(url, "거래처 상품추가 팝업창", "width="+defW+", height="+defH+", top="+sTop+",left="+sLeft+", scrollbars=yes, marginwidth=0, marginheight=0");
            popWin.focus();      
         }
         </script>         
         
         <div class="container text-right">
            <input type="button" class="btn btn-warning" value="상품추가" onclick="productAdd()">   
            <input type="button" class="btn btn-default" value="거래처 변경" onclick="clintChange()">
            <input type="button" class="btn btn-default" value="상품삭제" onclick="productDelete()">
         </div>
         
         <div class="form-group"></div>
         
         <div class="container">
            <table class="table table-bordered">
               <colgroup>
					<col class="col-md-1 col-sm-1"/>                
                    <col class="col-md-2 col-sm-2"/>          
                    <col class="col-md-4 col-sm-4"/>           
                    <col class="col-md-1 col-sm-1"/>
                    <col class="col-md-1 col-sm-1"/>          
                    <col class="col-md-1 col-sm-1"/>
                    <col class="col-md-1 col-sm-1"/>
               </colgroup>
               <tr class="active">
                  <td class="text-center">                        
                     <input type="checkbox" id="allProdctCheck">
                  </td>
                  <td class="text-center">   
                     <b>이미지</b>
                  </td>
                  <td class="text-center">   
                     <b>상품명</b>
                  </td>
                  <td class="text-center">   
                     <b>재고량</b>
                  </td>
                  <td class="text-center">   
                     <b>매입가</b>
                  </td>
                  <td class="text-center">   
                     <b>판매가</b>
                  </td>
                  <td class="text-center">   
                     <b>진열여부</b>
                  </td>
               </tr>
               <c:choose>
                  <c:when test="${fn:length(prodctList) == 0}">
                     <tr class="text-center">
                        <td colspan="7">
                           <h4>등록된 상품이 없습니다</h4>
                        </td>
                     </tr>
                  </c:when>
                  <c:when test="${fn:length(prodctList) != 0}">
                     <c:forEach items="${prodctList}" var="prodctList" varStatus="status">
                        <tr onmouseover="javascript:changeTrColor(this, '#FFFFFF', '#F4FFFD')" style="cursor:hand">
                           <td class="text-center">
                              <input type="checkbox" name="prodctCheck" value="${prodctList.clintBridgSeq}">
                           </td>
                           <td class="text-center">
                              <img src="${prodctList.mainImg}" class="thumbnail">
                           </td>
                           <td class="text-center">
                              ${prodctList.prodctNme}
                           </td>
                           <td class="text-center">
                         	  <fmt:formatNumber value="${prodctList.qunt}" type="number"/>
                           </td>
                           <td class="text-center">
                           	  <fmt:formatNumber value="${prodctList.purchsPric}" type="number"/>
                           </td>
                           <td class="text-center">
                          	 <fmt:formatNumber value="${prodctList.selPric}" type="number"/>
                           </td>
                           <td class="text-center">
                              ${prodctList.displyCheck}
                           </td>
                        </tr>
                     </c:forEach>
                  </c:when>
               </c:choose>
            </table>
         </div>
         <!-- 페이징 -->
         <div class="container text-center">
            <nav>
               <ul class="pagination">
                  <!-- 무조건 1페이지로 << 버튼 -->
                  <c:if test = "${clintVo.currentPageNo>20}">
                     <li>
                        <a href="javascript:clintReadPaging(1)" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span></a>
                     </li>
                  </c:if>  
                  <!-- 한 단위 앞으로 < 버튼 -->
                  <c:if test = "${clintVo.currentPageNo>10}">
                     <li>
                        <a href="javascript:clintReadPaging(${clintVo.firstPageNoOnPageList-1})" aria-label="Previous">
                        <span aria-hidden="true">&lsaquo;</span></a>
                     </li>
                  </c:if>    
                                
                  <c:forEach varStatus="status" begin="${clintVo.firstPageNoOnPageList}" end="${clintVo.lastPageNoOnPageList}">
                  
                     <c:if test="${clintVo.currentPageNo==status.current}">
                        <li class="active">
                           <a href="javascript:clintReadPaging(${status.current})">${status.current}</a>
                        </li>
                     </c:if>
                     
                     <c:if test="${clintVo.currentPageNo!=status.current}">
                        <li>
                           <a href="javascript:clintReadPaging(${status.current})">${status.current}</a>
                        </li>
                     </c:if>   
                                                                
                  </c:forEach>   
                  
                  <!-- 한 단위 뒤로 > 버튼 -->
                  <c:if test = "${clintVo.firstPageNoOnPageList + 10 < clintVo.totalPageCount}">
                     <li>
                        <a href="javascript:clintReadPaging(${clintVo.lastPageNoOnPageList+1})" aria-label="Next">
                        <span aria-hidden="true">&rsaquo;</span></a>
                     </li>
                  </c:if>  
                  <!-- 무조건 마지막페이지로 >> 버튼 -->
                  <c:if test = "${clintVo.firstPageNoOnPageList + 20 < clintVo.totalPageCount}">
                     <li>
                        <a href="javascript:clintReadPaging(${clintVo.totalPageCount})" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span></a>
                     </li>
                  </c:if>  
               </ul>
            </nav>
         </div>   
      </div>
      <script>
         // 상품추가 페이징
         function clintReadPaging(page){
            document.clintReadFrm.currentPageNo.value = page;
            document.clintReadFrm.action ="/ClintRead.do";
            document.clintReadFrm.submit();
         }
      
         function changeTrColor(trObj, oldColor, newColor) {
            trObj.style.backgroundColor = newColor;
            trObj.onmouseout = function(){
               trObj.style.backgroundColor = oldColor;
            };
         };
         
         // 전체 선택, 전체 선택 해제
         $(function(){
             //전체선택 체크박스 클릭
            $("#allProdctCheck").click(function(){
               //만약 전체 선택 체크박스가 체크된상태일경우
               if($("#allProdctCheck").prop("checked")) {
                  //해당화면에 전체 checkbox들을 체크해준다
                  $("input[type=checkbox]").prop("checked",true);
               // 전체선택 체크박스가 해제된 경우
               } else {
                  //해당화면에 모든 checkbox들의 체크를해제시킨다.
                  $("input[type=checkbox]").prop("checked",false);
               }
            })
         });
         
         // 거래처 상품들 일괄 거래처 변경
         function clintChange(){
            var checkedList =[];
            $("input[name='prodctCheck']:checkbox:checked").each(function() {
               checkedList.push($(this).val());
               
            });
            if(checkedList == ""){
               alert("상품을 선택해주세요.");
               return;
            }
            
            var chkChange = confirm("선택한 "+checkedList.length+"개의 상품의 거래처를 변경 하시겠습니까?"); 
            if(chkChange == true) {
               $('#checkedList').value = checkedList;   
               var defH, defW, sTop, sLeft, url;
               defW = document.body.scrollWidth*0.3;
               defH = document.body.scrollHeight*0.3;
               sTop = (screen.height - defH)/4;
               sLeft= (screen.width  - defW)/4;
               url = "/ClintProductUpdatePopup.do?clintSeq="+$('#clintSeq').val()+"&clintBridgChkGroup="+checkedList;
               popWin = window.open(url, "상품 거래처 변경 팝업창", "width="+defW+", height="+defH+", top="+sTop+",left="+sLeft+", scrollbars=yes, marginwidth=0, marginheight=0");
               popWin.focus();      
            }
            
            else {
               return;
            }
            
         }
         
         // 거래처 상품들 일괄 삭제
         function productDelete(){
            var checkedList =[];
            $("input[name='prodctCheck']:checkbox:checked").each(function() {
               checkedList.push($(this).val());
               
            });
            if(checkedList == ""){
               alert("상품을 선택해주세요.");
               return;
            }
            
            var chkChange = confirm("선택한 "+checkedList.length+"개의 상품을 삭제하시겠습니까?"); 
            if(chkChange == true) {
//             	var url="/ClintProductDelete.do?clintBridgChkGroup="+checkedList+"&clintSeq="+$('#clintSeq').val();
//                window.location.href = url;
				$.ajax({
					type:"POST",
					url:"/ClintProductDelete.do?clintBridgChkGroup="+checkedList+"&clintSeq="+$('#clintSeq').val(),
					success:function(){
// 						alert("석세스 펑션에 data 매개변수받아보기, 리턴 지워보기, 리로드해보기");
						location.reload();
					},
					error:function(request,status,error){
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					}
				});
            }
        };
		$(document).ready(function() {
		   //이미지 섬네일
		    var xOffset = 10;
		    var yOffset = 30;
		    $(document).on("mouseover",".thumbnail",function(e){ //마우스 오버시
		        $("body").append("<p id='preview'><img src='"+ $(this).attr("src") +"' width='200px' /></p>"); //보여줄 이미지를 선언                       
		        $("#preview")
		            .css("top",(e.pageY - xOffset) + "px")
		            .css("left",(e.pageX + yOffset) + "px")
		            .fadeIn("fast"); //미리보기 화면 설정 셋팅
		    });     
		    $(document).on("mousemove",".thumbnail",function(e){ //마우스 이동시
		        $("#preview")
		            .css("top",(e.pageY - xOffset) + "px")
		            .css("left",(e.pageX + yOffset) + "px");
		    });     
		    $(document).on("mouseout",".thumbnail",function(){ //마우스 아웃시
		        $("#preview").remove();
		    });
		});
		</script>
	</body>
</html>