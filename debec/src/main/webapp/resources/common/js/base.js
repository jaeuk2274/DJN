var $topAdHeight, $gnbAreaW, $winWidth,$topBannW,   innerMenuLen,innerMenuH,
	gnbAreaH = [403,403,403,403,403,403], 
	innerWrapH = [320,320,320,320,320,320],
	innerH = [280,280,280,280,280,280],
	timer = 500, easeInOut = "easeInOutExpo", easeOut = "easeOutExpo",
	fnVbannerW;

$(document).ready(function(){

	var $allMenuWrapH = 310, refFocusEl = null, index, nowNum,
		LineW0,LineW1,LineW2,LineW3,LineW4,
		LineL0,LineL1,LineL2,LineL3,LineL4,
		LineW, LineL,
		bannZRolling, bannZNum, pageCnt,
		bannerRolling, bannerRolling2,topAdDownSlide, wsTimer=500,
		bannZNum = 0, pageCnt=0,
		toggleVal = "off";
	var broserStr = navigator.userAgent.toLowerCase();

	if ($(document).height() > $(window).height()){
		$winWidth = $(window).width()+17;
	} else {
		$winWidth = $(window).width();
	};

	$topAdHeight = $("#topAd").height();
	$gnbAreaW = $(".gnbArea").width();
	$topBannW = $("#topAd > .banner").width();

	$("#topAd").css("top",-$topAdHeight);

	function firstSet(){

		if ($winWidth > 1179) {
			if ($("#containerM").length){
				fnVbannerL(460);
				fnProList(240);
			};
		} else if ($winWidth >= 851 && $winWidth <= 1179) {
			if ($("#containerM").length){
				fnVbannerL(920);
				fnProList(460);
				fnVsec01n02();
			} else {

			};
		} else if ($winWidth >= 728 && $winWidth <= 850) {
			if ($("#containerM").length){
				fnVbannerL(708);
				fnProList(354);
				fnVsec01n02();
			};
		} else  {
			if ($("#containerM").length){
				fnVbannerL(359);
				fnProList(320);
				fnVsec01n02();
			};
		};
		fnTopAdSize();
		if ($("#containerM").length){
			mainVisualSize();
			popSize();
			banSize();
		};
	};
	firstSet();


	//디바이스 넓이 변경시
	$(window).resize(function(){

		if ($(document).height() > $(window).height()){
			$winWidth = $(window).width()+17;
		} else {
			$winWidth = $(window).width();
		};
		$topAdHeight = $("#topAd").height();
		$gnbAreaW = $(".gnbArea").width();
		$topBannW = $("#topAd > .banner").width();

		if ($winWidth > 1179) {
			fnHeader();
		} else if ($winWidth >= 851 && $winWidth <= 1179) {
			fnHeader();
		} else if ($winWidth >= 728 && $winWidth <= 850) {
			fnHeader();
		} else  {
			fnWMin();
			if($(".allMenuWrap").css("display")=="block"){
				$("html").css("overflow","auto");
			};
			$(".smartSearch").css("display","none");
			$("#containerM").css("top",0);
			$("#container").css("top",0);
		};
		
		if ($(".mypageBanner").length) {
			if ($winWidth < 728) { return false };
			fnMypageBannL();
		};

		if ($(".layerWrap").length){
			fnPoplayerPos();
		};

		if ($(".barometerArea").length){
			if ($winWidth > 1179) {
				$(".areaL").css({width:'357px'});
				$(".areaR").css({width:'493px'});
			} else if ($winWidth >= 851 && $winWidth <= 1179) {
				$(".areaL").css({width:'350px'});
				$(".areaR").css({width:'500px'});
			} else if ($winWidth >= 728 && $winWidth <= 850) {
				$(".areaL").css({width:'297px'});
				$(".areaR").css({width:'411px'});
			};
		};
		if ($("#container").length){
			fnGnbOverLine();
		};
	
		firstSet();
	});

	function fnHeader(){
		if ( $("#topAd").hasClass("open") ) {
			$("#topAd").css("top",0);
			$("header").css({'top':$topAdHeight});
			$("#containerM").css("margin-top",$topAdHeight+115);
			$("#container").css("margin-top",$topAdHeight+165);
		} else {
			$("#topAd").css("top",-$topAdHeight);
			$("header").css({'top':0});
			$("#containerM").css("margin-top",115);
			$("#container").css("margin-top",165);
		};
		$("nav").css({'display':'block'});

		if ($(".innerWrap.nowOn").length){
			nowOn = parseInt($(".innerWrap.nowOn").attr("data-index"));
			$(".innerWrap").each(function(index){
				$(this).css("left", index*$gnbAreaW-(nowOn*$gnbAreaW));
			});
		} else {
			fnInnerWrapL();
		};

		$(".gnbOverLine").css({'display':'inline-block'});
		$(".mobileMenu").css({'display':'none'});

		$(".vBanner").css("position","absolute");
		$(".visualArea .vBanner ul").css("left",0);
		$(".iconMenuWrap > ul").css("left",0);
		$(".popupZone .popList ul").css("left",0);
		$(".BannerZone ul.banner").css("left",0);
		if($(".allMenuWrap").css("display")=="block" ){
			if( $("#topAd").hasClass("open")){
				$(".allMenu p").css({'position':'fixed', 'top':$topAdHeight+155});
			};
		}
	};

	function fnWMin(){
		$("header").css({'top':0});
		$(".gnbWrap").css({'height':40});
		$("#containerM").css("margin-top",0);
		$("#container").css("margin-top",0);

		$("nav").css({'display':'none', 'height':''});
		$(".innerWrap").each(function(index){
			$(this).css("left",index*$gnbAreaW);
		});
		$(".innerWrap").css({'height':'', 'display':'none'});
		$(".innerWrap").removeClass("nowOn");
		$(".gnbOverLine").css({'display':'none','width':0});
		$(".gnbBtnLine").removeClass("over");
		$(".allMenuWrap").css({'display':'none'});
		$(".allMenuBtnLine").removeClass("over");

		$(".vBanner").css({"position":"relative","margin":"0 auto" });
		$(".visualArea .vBanner ul").css("left",0);
		$(".popupZone .popList ul").css("left",0);
		$(".BannerZone ul.banner").css("left",0);
	};

	function fnVsec01n02(){
		$(".vSection01 .menuOn").css({"display":"none", "width":0});
		$(".vSection01 .menuOn > div").css({"left":-460});
		$(".vSection02 .menuOn").css({"display":"none", "width":0});
		$(".vSection02 .menuOn > div").css({"left":-700});
		$(".vSection03 .menuOn").css({"display":"none", "width":0});
		$(".vSection03 .menuOn > div").css({"left":-940});
		$(".vSection04 .menuOn").css({"display":"none", "width":0});
		$(".vSection04 .menuOn > div").css({"left":-940});
	};

	function fnProList(val){
		$(".proList, .proWrap").css({"width":val});
		
		$(".vSection04 .proList  ul li").each(function(index){
			$(".vSection04 .proList  ul li").eq(index).css("left", index*val);
		});
		$(".proList ul li").removeClass("active");
		$(".vSection04 .proList  ul li").eq(0).addClass("active");
	};

	function fnVbannerL(val){
//		$(".vBanner").css({"width":val});
		$(".vBanner > ul").css("left",0);
		$(".vBanner ul li").each(function(index){
			$(".vBanner ul li").eq(index).css("left", index*val);
		});
	};

	function mainVisualSize(){
		var vBsize = $(".vBanner").width();
//		$(".vBanner li").css({"width":vBsize});
		$(".vBanner .page > a").removeClass("on");
		$(".vBanner .page > a").eq(0).addClass("on");
		$("#playCtrl").removeClass("play");
		$("#playCtrl").addClass("stop").html('stop');
		clearTimeout(bannerRolling2);
		clearInterval(bannerRolling);
		if ($winWidth < 728) {
			var vBannPagdLen = $(".vBanner .page a.num").length;
//			$(".vBanner > ul").css("width",359*vBannPagdLen);
		};	
	};

	function fnMypageBannL(){
		var myBannL = $(".mypageBanner .banner li img").width();
		$(".mypageBanner .banner li").removeClass("active");
		$(".mypageBanner .banner li").eq(0).addClass("active");
		$(".mypageBanner .banner .bn").removeClass("on");
		$(".mypageBanner .banner .bn01").addClass("on");
		$(".mypageBanner .banner .img01 img").css("left", 0);
		$(".mypageBanner .banner .img02 img").css("left", myBannL);
	};
	if ($(".mypageBanner").length) {
		fnMypageBannL();
	};

	function fnPoplayerPos(){
		if ($winWidth < 728) {
			$(".layerWrap").css('position','absolute');
		} else {
			$(".layerWrap").css('position','fixed');
			var browserH = $(window).height() / 2;
			var poplayerH = 200;
			$(".layerContent").css("top",browserH-poplayerH);
		};
	};

//gnbOverline output ===============

	function fnGnbOverLineOut(lineIdx, dp ,beforeOn){
		if ($winWidth < 728) return false;
		var lineWVal, lineLVal, getNowOn, getLineVal;

		if (lineIdx == "none"){
			LineL0 = parseInt($(".menu01 > a").css("left"));
			$(".gnbOverLine").stop(true,true).animate({width:0, left:LineL0}, timer, easeInOut);
		} else {
			LineW0 = parseInt($(".menu01 > a").css("width"));
			LineW1 = parseInt($(".menu02 > a").css("width"));
			LineW2 = parseInt($(".menu03 > a").css("width"));
			LineW3 = parseInt($(".menu04 > a").css("width"));
			LineW4 = parseInt($(".menu05 > a").css("width"));
			LineW5 = parseInt($(".menu06 > a").css("width"));
			LineL0 = parseInt($(".menu01 > a").css("left"));
			LineL1 = parseInt($(".menu02 > a").css("left"));
			LineL2 = parseInt($(".menu03 > a").css("left"));
			LineL3 = parseInt($(".menu04 > a").css("left"));
			LineL4 = parseInt($(".menu05 > a").css("left"));
			LineL5 = parseInt($(".menu06 > a").css("left"));

			if (lineIdx == 0){
				getLineVal = [LineW0,LineL0];
			} else if (lineIdx == 1){
				getLineVal = [LineW1,LineL1];
			} else if (lineIdx == 2){
				getLineVal = [LineW2,LineL2];
			} else if (lineIdx == 3){
				getLineVal = [LineW3,LineL3];
			} else if (lineIdx == 4){
				getLineVal = [LineW4,LineL4];
			} else if (lineIdx == 5){
				getLineVal = [LineW5,LineL5];
			};

			lineWVal = getLineVal[0];
			lineLVal = getLineVal[1];
			getNowOn = parseInt($("nav > ul > li > a").eq(beforeOn).css("left"));

			if (dp=="dpCss"){
				$(".gnbOverLine").css({'width':lineWVal, 'left':lineLVal, "display":"inline-block"});
					
			} else if (dp=="dpAni"){
				$(".gnbOverLine").css({'display':'inline-block'}).stop(true,true).animate({'width':lineWVal, 'left':lineLVal}, timer, easeInOut);
				
			} else if (dp=="dpSlide"){
				$(".gnbOverLine").css({"left":getNowOn, "display":"inline-block"}).stop(true,true).animate({width:lineWVal, left:lineLVal}, timer, easeInOut);
				
			};
		};
	};

	function fnGnbOverLine(val){
		if ($winWidth < 728) return false;
		if($(".gnbWrap").attr("id")=="gnb_13754"){	
			lineVal = 0;
		} else if ($(".gnbWrap").attr("id")=="gnb_13755"){	
			lineVal = 1;
		} else if ($(".gnbWrap").attr("id")=="gnb_13756"){	
			lineVal = 2;
		} else if ($(".gnbWrap").attr("id")=="gnb_13757"){	
			lineVal = 3;
		} else if ($(".gnbWrap").attr("id")=="gnb_13733"){	
			lineVal = 4;
		} else if ($(".gnbWrap").attr("id")=="gnb_13758"){	
			lineVal = 5;
		} else {
			lineVal ="none";
		};
		
		if (val == "gnbClose"){	
			fnGnbOverLineOut(lineVal, 'dpAni', lineVal);			
		} else {
			fnGnbOverLineOut(lineVal, 'dpCss', lineVal);
		};
	};
	
	if ($("#container").length){
		fnGnbOverLine(); //red Line
	};

//GNB ========================

	//gnbAreaW
	function fnInnerWrapL(){
		$(".innerWrap").each(function(index){
			$(this).css("left",index*$gnbAreaW);
		});
	};
	fnInnerWrapL();

	//gnb menu 속성할당
	$(".innerWrap").each(function(index){
		$(this).attr("data-index", index);
	});
	$("nav > ul > li > a").each(function(index){
		$(this).attr("data-index", index);
	});

	//gnb 실행
	$("nav > ul > li > a").on("click", function(){

		$(".innerWrap").css('display','block');
		$(".innerWrap a").css('display','block');

		fnAllMenuClose();
		fnTopqueryClose();
		nowNum = $(this).attr("data-index");
		$(this).attr('tabindex','0').focus();
		refFocusEl = this;

		baseOff();
		baseOn();

		slideThis(nowNum);
	});



	//gnb sliding
	function slideThis(nowNum) {

		if (!($(".nowOn").length)) { 	//서브영역 비활성화 상태
			var chgLeft = parseInt(nowNum) * $gnbAreaW;
			$(".innerWrap").each(function(index){
				$(this).css({'left': "-=" + chgLeft });
			});

			$(".innerWrap").eq(nowNum).addClass("nowOn");	//선택된 inner에 클래스 추가

			$(".innerWrap a").css("display","none");	//접근성
			$("nav .innerWrap").eq(nowNum).find("a").css("display","block");//접근성

			$("nav .gnbClose").attr("disabled",true);//접근성
			$("nav .innerWrap").eq(nowNum).find(".gnbClose").attr("disabled",false);//접근성

			openHight(nowNum);
			fnGnbOverLineOut(nowNum, "dpAni", nowNum); //red line

		} else {	//서브영역 활성화 상태

			var nowOn = parseInt($(".nowOn").attr("data-index"));
			var resultIdx = parseInt(nowOn - nowNum);

			$(".innerWrap").find("a").css("display","block"); //추가141114

			if (resultIdx != 0) {
				var moveLeft_result = resultIdx * $gnbAreaW;

				$(".gnbWrap, nav").stop(true,true).animate({height:gnbAreaH[nowNum]}, timer, easeInOut);

				$(".innerWrap").each(function(index){
					$(this).stop(true,true).animate({
						left: "+="+moveLeft_result,
						height:innerWrapH[index] //baseHeight
						}, 500, easeInOut, function(){
							if ($(".innerWrap").hasClass("nowOn")){ //접근성
								$(this).find("a").css("display","block");
							} else {
								$(this).find("a").css("display","none");
							};
					});
				});
				$("nav .gnbClose").attr("disabled",true);	//접근성
				$("nav .innerWrap").eq(nowNum).find(".gnbClose").attr("disabled",false); //접근성
			};
			fnGnbOverLineOut(nowNum, "dpSlide", nowOn); // red line

		};
		$('.innerWrap[data-index!='+nowNum+']').removeClass("nowOn");
		$('.innerWrap[data-index='+nowNum+']').addClass("nowOn");
	};

	//gnb close
	$(".gnbClose").click(function() {
		fnGnbClose();
		return false;
	});

	function fnGnbClose(){
		closeHight();
		if(refFocusEl) {
			refFocusEl.focus();
			refFocusEl = null;
		};
		setTimeout(function(){
			fnInnerWrapL();
		}, timer);
	};


	//3depth sliding
	$(".innerWrap .depth2y").on("click", function(event){
		event.preventDefault();
		var dp2Index = parseInt($(this).parent().index());
		var dp3Index = parseInt($(this).parent().find("li").size());
		var dpTotIndex = dp2Index + dp3Index;
		var chgHight = (dpTotIndex*28) + 40,
			chgHightWrap = chgHight + 82;

		baseOff();

		$(this).parent().addClass("active");
		$(this).next().addClass("disBlock");

		var chkBase = $(this).next().find("li").hasClass("active");
		if (!chkBase) {
			$(this).next().find("li").first().addClass("active");
		};

		nowOn = parseInt($("nav .nowOn").attr("data-index"));

		if (chgHightWrap > gnbAreaH[nowOn]) {
			$(".gnbWrap").stop(true,false).animate({height:chgHightWrap+1}, timer, easeInOut);
			$("nav").stop(true,false).animate({height:chgHightWrap}, timer, easeInOut);
			$(".innerWrap").stop(true,false).animate({height:chgHight},timer, easeInOut);
		} else {
			$(".gnbWrap").stop(true,false).animate({height:gnbAreaH[nowOn]}, timer, easeInOut);
			$("nav").stop(true,false).animate({height:gnbAreaH[nowOn]+2}, timer, easeInOut);
			$(".innerWrap").stop(true,false).animate({height:innerWrapH[nowOn]},timer, easeInOut);
		};
	});


	function openHight(nowNum){ // open
		$(".innerWrap").css("display","block");
		$(".gnbBtnLine").css("display","block").addClass("over");
		$(".gnbWrap").stop(true,true).animate({height:gnbAreaH[nowNum]}, timer, easeInOut);
		$("nav").stop(true,true).animate({height:gnbAreaH[nowNum]}, timer, easeInOut);
		$(".innerWrap .inner").css("top",-innerH[nowNum]).stop(true,true).animate({'top':0},timer, easeInOut);
		$(".innerWrap").stop(true,true).animate({height:innerWrapH[nowNum]},timer, easeInOut);
	};

	function closeHight(){ // close
		if ($("#container").length){
			fnGnbOverLine("gnbClose");
		} else {
			$(".gnbOverLine").stop().animate({"width":0, "left":LineL0}, timer, easeInOut);
		};
		$(".gnbWrap").stop(true,true).animate({height:82}, timer, easeInOut).delay(timer,function(){$(".gnbBtnLine").removeClass('over')});
		$("nav").stop(true,true).animate({height:82}, timer, easeInOut);
		$(".innerWrap .inner").stop(true,true).animate({'top':-innerH[nowNum]},timer, easeInOut);
		$(".innerWrap").removeClass("nowOn").stop(true,true).animate({height:0},timer, easeInOut,function(){$(".innerWrap").css('display','none')});
	};

	$(".innerWrap .inner .depth2n").on("click",function(){
		$(".innerWrap li > a.depth2n").parent().removeClass("on");
		$(".innerWrap li > a.depth2y").parent().removeClass("active");
		$(".innerWrap li > a.depth2y").next().removeClass("disBlock");
		$(this).parent().addClass("on");
	});

	$(".innerWrap .inner ul ul li").on("mouseover focus", function(){
		$(this).parent().find("li").first().removeClass("active");
		$(this).addClass("active");
	});

	$(".innerWrap .inner ul ul li").on("mouseleave", function(){
		$(this).removeClass("active");
		$(this).parent().find("li").first().addClass("active");
	});

	function baseOn() {
		$(".innerWrap li.baseOn > a.depth2n").parent().addClass("on");
		$(".innerWrap li.baseOn > a.depth2y").parent().addClass("active");
		$(".innerWrap li.baseOn > ul").addClass("disBlock");
	};
	function baseOff() {
		$(".innerWrap li > a.depth2n").parent().removeClass("on");
		$(".innerWrap li > a.depth2y").parent().removeClass("active");
		$(".innerWrap li > a.depth2y").next().removeClass("disBlock");
	};

//sub fixed menu :  =================

	$(window).scroll(function(){

		if ($winWidth < 1180) {
			$(".contents > .tab01").css({"position":"relative"});
			return false
		};

		if ( ($(".gnbWrap").attr("id")=="gnb_13754") || ($(".gnbWrap").attr("id")=="gnb_13755") ){

			var wST = $(window).scrollTop();
			var lnbH = parseInt($("#lnb").css("height"))+100;

			if ($(".goodsQuick").length){
				if (lnbH <= wST ) {
					$(".goodsQuick").fadeIn();
					$(".goodsQuick").css({"position":"fixed"});
				} else {
					$(".goodsQuick").hide();
				};
			};
			if ($(".contents > .tab01").length){
				if ( wST > 510 ) {
					$(".contents > .tab01").css({"position":"fixed", "top":80});
				} else {
					$(".contents > .tab01").css({"position":"relative", "top":''});
				};
			};
		};

	});


//통합검색======================

	$(".faqSearch > #query").keyup(function(event){
		if(window.event.keyCode==9){ //tab키
			fnAllMenuClose();
			fnGnbClose();
			$(".faqSearch .dropList").css("display","block");
		};
		return false;
	});
	$(".faqSearch > #query").on("click", function(){
		fnAllMenuClose();
		fnGnbClose();
		$(".faqSearch .dropList").css("display","block");
		return false;
	});
	$(".faqQueryClose").on("click", function(){
		$("#faqQuery").val("");
		$("#faqQuery").focus();
		return false;
	});


//스마트검색 ========================

	//모바일 : 검색폼 열기
	$(".mSrchBtn").on("click", function(){
		if ($(".mSrchForm").css("display")=="none"){
			if ($winWidth > 727 ) {
				fnAllMenuClose();
				GnbCloseForSrch();
				$(".mSrchForm").css({"display":"block"});
				$(".smartSearch").css("display","block");
			} else {
				$(this).parent().addClass("on");
				$(".mSrchForm").css({"display":"block"});
				$(".smartSearch").css("display","none");
			};
			$(".gnbArea >.search p input").focus();
		} else {
			$(".mSrchForm").css({"display":"none"});
			$(".smartSearch").css("display","none");
			if ($winWidth < 728 ) {
				$(this).parent().removeClass("on");
			};
		}
	});
	//스마트검색 열기
	$("#topquery").keyup(function(event){
		if ($winWidth < 728) return false;
		if(window.event.keyCode==9){	//tab키
			fnAllMenuClose();
			GnbCloseForSrch();
			$(".smartSearch").css("display","block");
		};
	});
	$("#topquery").on("click", function(event){
		if ($winWidth < 728) return false;
		fnAllMenuClose();
		GnbCloseForSrch();
		$(".smartSearch").css("display","block");
		
	});
	//스마트검색
	$("#topquery").keyup(function(event){
		if ($winWidth < 728) return false;
		if(window.event.keyCode==8){ //back space키
			if ($(this).val().length === 0){
				$(".smartSearch").css("display","none");
			};
		};
	});
	
	function GnbCloseForSrch(){
		$(".gnbWrap").stop(true,true).animate({height:82}, timer, easeInOut).delay(timer,function(){$(".gnbBtnLine").removeClass('over')});
		$("nav").stop(true,true).animate({height:82}, timer, easeInOut);
		$(".innerWrap .inner").stop(true,true).animate({'top':-innerH[nowNum]},timer, easeInOut);
		$(".innerWrap").removeClass("nowOn").stop(true,true).animate({height:0},timer, easeInOut,function(){$(".innerWrap").css('display','none')});
	};
	
	//스마트검색 닫기
	function fnTopqueryClose(){
		$(".smartSearch").css("display","none");
		if ($winWidth >= 728 && $winWidth <= 850) {
			$(".mSrchForm").css({"display":"none"});
		};
	};
	$(".smartSeCont > button").on("click", function(){
		fnTopqueryClose();
		$(".search input[type=button]").focus();
	});

	//모바일 : 사이트 바로가기
	$(".mobileBtn").on("click", function(event){
		if (toggleVal=="off") {
			$(".leftMenu").slideDown(300);
			$(this).addClass("on");
			toggleVal = "on";
		} else {
			$(".leftMenu").slideUp(300);
			$(this).removeClass("on");
			toggleVal = "off";
		};
	});

	//모바일 : top으로 가기
	$(window).scroll(function(){
		if ($winWidth > 727){ return false };
		if ($(window).scrollTop() > 400){
			$(".topBtnMobile").fadeIn(300);
		} else {
			$(".topBtnMobile").fadeOut(300);
		};
	});
	$(".topBtnMobile").on("click", function(){
		if ($winWidth > 727){ return false };
		$("html, body").animate({scrollTop:0}, 500, easeInOut);
	});

//통합검색 자주찾는 검색어 ===========
	$(".searchWord .prev").on("click", function(){
		$(".searchWord > div").stop().animate({'top':-4}, 300, easeInOut);
	});
	$(".searchWord .next").on("click", function(){
		$(".searchWord > div").stop().animate({'top':-32}, 300, easeInOut);
	});	

//메뉴바로가기 & 자주하는 질문 ===========
	$(".seListCont > ul > li > a").on("click", function(){
		$(".seListCont > ul > li").removeClass("on");
		$(".seListCont > ul > li > ul").removeClass("disBlock");
		$(this).parent().addClass("on");
		$(this).next().addClass("disBlock");
		return false;
	});

	
//전체메뉴 =============

	var allMenuTimer = 500;

	$(".gnbArea> p > button").on("click", function(){
		$(".smartSearch").css("display","none");
		if ($winWidth < 728) {
			fnMobileMenuOpen();
		} else {
			fnAllMenuOpen();
		};
	});

	//전체메뉴열기
	function fnAllMenuOpen(){
		
		if ($(".allMenuWrap").css("display") == "none"){
			fnGnbClose();
			fnTopqueryClose();
			
			$("#topAd").stop().animate({'top':-$topAdHeight}, wsTimer, easeInOut);
			contMarginMin();			
			
			$("header").stop().animate({'top':0}, wsTimer, easeInOut);
			$(".allMenu p").css({'position':'fixed','top':155});
			
			if ($("#containerM").length){
				$("#containerM").stop().animate({'marginTop':115}, wsTimer, easeInOut);
			} else {
				$("#container").stop().animate({'marginTop':165}, wsTimer, easeInOut);
				$(".quick").stop().animate({'top':165}, wsTimer, easeInOut);
			};
			//----추가//
			$(".allMenuBtnLine").addClass("over");
			$(".allMenuWrap").css({'display':'block','height':0}).stop(true,true).animate({'height':$allMenuWrapH},allMenuTimer,easeInOut);
			$(".allMenu").css({'top':-$allMenuWrapH}).stop(true,true).animate({'top':0}, allMenuTimer, easeInOut);
			$("header .allMenu p button").css({'filter':'alpha(opacity=0)'}).stop(true,true).animate({opacity:1},500,easeInOut);
			$(".allMenu div > ul > li").eq(0).find("a").first().attr('tabindex','0').focus();
			$(".allMenu div > ul > li").eq(0).find("a").first().focus();
			refFocusEl = this;
			$("html").css("overflow","hidden");
		} else {
			fnAllMenuClose();
			if (refFocusEl) {
				$(".gnbArea > p button").focus();
				refFocusEl = null; 
			};
		};
	};
	
	//전체메뉴닫기
	$(".allMenu p button").on("click", function(){
		fnAllMenuClose();
		if (refFocusEl) {
			$(".gnbArea > p button").focus();
			refFocusEl = null;
		};
		return false;
	});
	function fnAllMenuClose() {
		$(".allMenu").stop(true,true).animate({'top':-270}, allMenuTimer, easeInOut);
		$(".allMenu p button").css({'filter':'alpha(opacity=100)'}).stop(true,true).animate({opacity:0}, 300, easeInOut, function(){
			$(".allMenu p").css({'position':'relative','top':0});
		});
		$(".allMenuWrap").slideUp(allMenuTimer, easeInOut, function(){
			$(".allMenuBtnLine").removeClass("over");

			if ($("#container").length){
				fnGnbOverLine(); // over line of sub page
			}

			$("html").css("overflow","auto");
		});
	};


//모바일 전체메뉴 ==========================

	function fnMobileMenuOpen(){
		var mobileBgH = $(document).height();
		if ($(".mobileMenu").css("display")=="none"){
			$(".mobileMenu").before("<div class='mobileBg'><img src='/image/common/bg/mobileBg.png' alt='모바일 전체메뉴 배경이미지' /></div>");
			$(".mobileBg").css({"height":mobileBgH, "display":"block"}).stop().animate({"opacity":0.7, "left":-160}, 500, easeInOut);
			$(".mobileBg img").css({"height":mobileBgH});
			$(".mobileMenu > ul > li").removeClass("active");
			$(".mobileMenu > ul ul").hide();
			$("li.disPlus").removeClass("on");
			$("li.plus").removeClass("active");
			$("#wrap").stop(true,true).animate({left:-160}, allMenuTimer, easeInOut);
			$(".mobileMenu").css({"right":-160, "display":"block"}).stop(true,true).animate({right:0}, allMenuTimer, easeInOut, function(){
				$(".mobileMenu > div").find("a").first(0).attr('tabindex','0').focus();	// 포커스 접근성
				$(".mobileMenu > div").find("a").first(0).focus();
				refFocusEl = this;
			});
		};
	};
	
	
	$(".mobileMenu > ul > li > a").on("click", function(){
		if ($(this).next().css("display")=="none"){
			$(".mobileMenu > ul > li").removeClass("active");
			$(".mobileMenu > ul ul").hide();
			$(".mobileMenu > ul ul ul").hide();
			$(".mobileMenu li.disPlus").removeClass("on");
			$(".mobileMenu li.plus").removeClass("active");

			$(this).parent().addClass("active"); //depth_1
			$(this).next().slideDown(300, easeOut);

			if ($(this).next().find("li").eq(0).hasClass("plus")){ // 3depth 존재
				$(this).next().find("li").eq(0).addClass("active");
				$(this).next().find("li").eq(0).find("ul").slideDown(300, easeOut);
//			} else {
//				$(this).next().find("li").eq(0).addClass("on"); //2depth 첫번째메뉴 흰색활성화
			};
		} else {
			$(this).parent().removeClass("active");
			$(this).next().slideUp(300, easeOut);
		};
		return false;
	});

	$(".mobileMenu li.plus > a").on("click", function(){ //2depth
		if ( $(this).next().css("display") == "none"){
			$(".mobileMenu li.disPlus").removeClass("on");
			$(".mobileMenu li.plus").removeClass("active");
			$(".mobileMenu li.plus > ul").slideUp(300, easeOut);
			$(this).next().slideDown(300, easeOut);
			$(this).parent().addClass("active");
		} else {
			$(this).next().slideUp(300, easeOut);
			$(this).parent().removeClass("active");
		};
		return false;
	});

	$(".mobileMenu li.disPlus > a").on("click", function(){ //2depth
		$(".mobileMenu li.disPlus").removeClass("on");
		$(".mobileMenu li.plus").removeClass("active");
		$(".mobileMenu li.plus > ul").slideUp(300, easeOut);
		$(this).parent().addClass("on");
	});

	//모바일전체메뉴 닫기
	$(".mobileMenu > div button").on("click", function(){
		$("#wrap").stop(true,true).animate({left:0}, allMenuTimer, easeInOut);
		$(".mobileBg").stop(true,true).animate({'left':0,'opacity':0}, allMenuTimer, easeInOut, function(){
			$(".mobileBg").remove();
		});		
		$(".mobileMenu").stop(true,true).animate({right:-160}, allMenuTimer, easeInOut, function(){
			$(this).css({"display":"none"});
		});
		if (refFocusEl) {
			$(".gnbArea > p button").focus();
			refFocusEl = null;
		};
	});
	$('body').on('click','.mobileBg img',function(){
		$("#wrap").stop(true,true).animate({left:0}, allMenuTimer, easeInOut);
		$(".mobileBg").stop(true,true).animate({'left':0,'opacity':0}, allMenuTimer, easeInOut, function(){
			$(".mobileBg").remove();
		});
		$(".mobileMenu").stop(true,true).animate({right:-160}, allMenuTimer, easeInOut, function(){
			$(this).css({"display":"none"});
		});
		if (refFocusEl) {
			$(".gnbArea > p button").focus();
			refFocusEl = null;
		};
	});

//마이페이지 : ================
	$(".mypageBanner .banner li .bn").click(function(){
		var mypageBanW = $(".mypageBanner .banner").css("width");
		var nowImgOn = parseInt($(".mypageBanner .banner .active").index());
		var nowImgNum = parseInt($(this).parent().index());
		var resultImgIdx = nowImgOn - nowImgNum;
		if (resultImgIdx !=0){
			var imgW = parseInt($(".mypageBanner .banner").css("width"));
			if (resultImgIdx < 0) { //순차
				$(".mypageBanner .banner .img01 img").stop(true,true).animate({left: -imgW}, 500, easeOut);
				$(".mypageBanner .banner .img02 img").stop(true,true).animate({left: 0}, 500, easeOut);
			} else { //역차
				$(".mypageBanner .banner .img01 img").stop(true,true).animate({left: 0}, 500, easeOut);
				$(".mypageBanner .banner .img02 img").stop(true,true).animate({left: imgW}, 500, easeOut);
			};
			$(".mypageBanner .banner li").removeClass("active");
			$(".mypageBanner .banner li").eq(nowImgNum).addClass("active");
			$(".mypageBanner .banner li").find(".bn").removeClass("on");
			$(".mypageBanner .banner li").eq(nowImgNum).find(".bn").addClass("on");
		};
		return false;
	});


//모바일 서브페이지 : =============

	$(".mhgroupW > h2 > a").on("click", function(){

		if ($(".mhgroup").css("display")=="none"){
			$(".mhgroupW h2").css({"background":"url(/image/common/icon/icon_arrowUp.png) no-repeat 99% 50%", "background-size":"12px 12px"});
			//$(".mhgroup > li.active a.active").removeClass("active");
			$(".mhgroup > li.active > ul").hide();
			$(".mhgroup > li.active > a.active").next().show(); 
			//$(".mhgroup > li.on").removeClass("on");  
			$(".mhgroup").slideDown(300, easeOut);
		} else {
			$(".mhgroupW h2").css({"background":"url(/image/common/icon/icon_arrowDown.png) no-repeat 99% 50%", "background-size":"12px 12px"});
			$(".mhgroup").slideUp(300, easeOut);
		};
		return false; 
	});

//var mclick = 0;
	$(".mhgroup > li > a").on("click", function(){
		
		
		//alert("mclick : "+mclick);
		
		if ($(this).next().length){ //서브메뉴 있을때
			
			//$(".mhgroup > li.an > a.on").addClass("flag");
			//$(".mhgroup > li.active > a.active").addClass("flag2");
			
			if(!$(this).hasClass("active")) { toggleVal = "off"; }
			
			if (toggleVal == "off"){	//토글
				
				
				//$(".mhgroup > li.an > a.on").addClass("flag");
				//$(".mhgroup > li.active > a.active").addClass("flag2");
				
				$(".mhgroup > li.an > a.on").removeClass("on");
				$(".mhgroup > li.active > a").removeClass("active");
				$(".mhgroup > li.active > ul").slideUp(300, easeOut);
				$(".mhgroup > li.active li").removeClass("active");
				$(this).addClass("active");
				$(this).next().slideDown(300, easeOut);
				//$(".mhgroup > li.active li  :nth-child("+mclick+")").find("a").addClass("active");
				//alert("mclick2 : "+$(".mhgroup > li.active li  :nth-child("+mclick+")").attr("class"));
				toggleVal = "on";
								 
			} else {
				
				//$(".mhgroup > li.an > a.flag").addClass("on");
				//$(".mhgroup > li.an > a.flag").removeClass("flag");
				
				//$(".mhgroup > li.active > a.flag2").addClass("active");
				//$(".mhgroup > li.active > a.flag2").next().slideDown(300, easeOut);
				//$(".mhgroup > li.active > a.flag2").removeClass("flag2");
				
				$(this).parent().removeClass("on");
				$(this).next().slideUp(300, easeOut);
				$(this).removeClass("active");
				
				toggleVal = "off";
			};
			
			return false;

		} else {  //서브메뉴 없을때
			$(".mhgroup > li").removeClass("on");
			$(".mhgroup > li.active > a").removeClass("active");
			$(".mhgroup > li.active > ul").slideUp(300, easeOut);
			$(this).addClass("on");
			//parent()
		};
	});

	$(".mhgroup > li.active li a").on("click", function(){
		$(".mhgroup > li.active li").removeClass("active"); 
		//mclick = $(this).index(".mhgroup > li.active li ");
		$(this).parent().addClass("active");
	});


//quick 메뉴 ================

	var quickTimer = 400;
	var opacityTimer = 300;

	//툴팁 ==================
	$(".quick > ul > li > a").each(function(index){
		$(this).attr("data-index",index);
	});

	$(".quick > ul > li > a").on("mousemove", function(e){
		if ( (broserStr.indexOf('ipad') != -1) || (broserStr.indexOf('iphone') != -1) || (broserStr.indexOf('ipod') != -1) || (broserStr.indexOf('android') != -1)) { //apple
			return false;
		};
		var headerT;
		var tootipW = [75,75,80,25];
		var tootipTxt = ["즐겨찾는 메뉴","보증 가이드북","본사/지사 안내","TOP"];
		var tootipHide0 = [0, 36],
			tootipHide1 = [36, 72],
			tootipHide2 = [72, 110],
			tootipHide3 = [110, 147];

		var qInx = $(this).attr("data-index");
		if  ($(window).scrollTop() > 82 ){
			headerT = 35;
		} else {
			headerT = 0;
		};
		var quickMarginT = parseInt($("#container").css("margin-top")) - headerT;

		var quickPos = e.pageY - quickMarginT - $(window).scrollTop();
		
		if (quickPos<=15){
			for (var i=0; i<=quickPos; i++){
				$(".tooltip").css({"top":quickPos+(18-i), "display":"block"}).stop().animate({"opacity":1},100);
				$(".tooltip > span").css("display","block").stop().animate({"min-width":tootipW[qInx]},100);
				$(".tooltip > span").text(tootipTxt[qInx]);
			};

		} else if (quickPos>17 && quickPos<124) {
			$(".tooltip").css({"top":quickPos, "display":"block"}).stop().animate({"opacity":1},200);
			$(".tooltip > span").css("display","block").stop().animate({"min-width":tootipW[qInx]},100);
			$(".tooltip > span").text(tootipTxt[qInx]);

		} else if (quickPos>=127){
			for (var i=1; i<=147-quickPos; i++){
				$(".tooltip").css({"top":quickPos-(17-i), "display":"block"}).stop().animate({"opacity":1},200);
				$(".tooltip > span").css("display","block").stop().animate({"min-width":tootipW[qInx]},100);
				$(".tooltip > span").text(tootipTxt[qInx]);
			};
		} else {
			$(".tooltip").hide();
			$(".tooltip > span").hide();
		};
		
		if ($(this).parent().hasClass("on")){
			if (qInx==0){
				if (quickPos>=tootipHide0[0] && quickPos<=tootipHide0[1]){
					$(".tooltip").hide();
					$(".tooltip > span").hide();
				}
			} else if (qInx==1) {
				if (quickPos>=tootipHide1[0] && quickPos<=tootipHide1[1]){
					$(".tooltip").hide();
					$(".tooltip > span").hide();
				}
			} else if (qInx==2) {
				if (quickPos>=tootipHide2[0] && quickPos<=tootipHide2[1]){
					$(".tooltip").hide();
					$(".tooltip > span").hide();
				}
			} else if (qInx==3) {
				if (quickPos>=tootipHide3[0] && quickPos<=tootipHide3[1]){
					$(".tooltip").hide();
					$(".tooltip > span").hide();
				};
			};
		};
	});

	$(".quick > ul > li > a").on("mouseleave", function(){
		$(".tooltip").stop(true,true).animate({"opacity":0},opacityTimer,easeOut,function(){
			$(".tooltip").hide();
			$(".tooltip > span").hide();
		});
	});

	
	$(".m01 > a").on("click", function(){
		var qInx = $(this).attr("data-index");
		fnM02Close();
		$(".tooltipSub").eq(qInx).css({"display":"none"});
		$(".quick > ul > li > a").removeClass("over");
		$(this).addClass("over");
		$(".quick > ul > li").removeClass("on");
		$(this).parent().addClass("on");

		$(".qMenuWrap01").css({"display":"block"}).stop(true,true).animate({width:477}, quickTimer, easeOut);
		$(".menuC01").stop(true,true).animate({left:0}, quickTimer, easeOut);
		$(".m01 .close").stop(true,true).animate({"right":18}, quickTimer, easeOut);
		
		$(".tooltip").hide();
		$(".tooltip > span").hide();
	});

	
	$(".qMenuWrap01 .menu").on("click", function(){
		$(".qMenuWrap01").stop(true,true).animate({height:410}, quickTimer, easeOut);
		$(".qMenuWrap01 .menu").stop(true,true).animate({marginTop:257}, quickTimer, easeOut);
		$(".menuC02").css({"display":"block","height":0, "right":0}).stop(true,true).animate({height:236}, 300, easeOut);
		$(".menuC02 > div").css({"top":-236}).stop(true,true).animate({"top":0}, quickTimer, easeOut);
		return false;
	});

	
	$(".m01 .close").on("click", function(){
		fnM01Close();
		$(".m01 > a").focus();
		return false;
	});
	function fnM01Close(){
		$(".m01").removeClass("on");
		$(".qMenuWrap01").stop(true,true).animate({width:0,"height":175}, quickTimer, easeOut, function(){
			$(".qMenuWrap01").css({"display":"none"});
			$(".m01 > a").removeClass("over");
		});
		$(".qMenuWrap01 .menu").stop(true,true).animate({marginTop:0}, quickTimer, easeOut);
		$(".menuC01").stop(true,true).animate({"right":36}, quickTimer, easeOut);
		$(".m01 .close").stop(true,true).animate({"right":-459}, quickTimer, easeOut);
		$(".menuC02").stop(true,true).animate({height:236, "right":-477}, quickTimer, easeOut);
		$(".menuC02 > div").stop(true,true).animate({"top":-236}, quickTimer, easeOut, function(){
			$(".menuC02").css({"display":"none"});
		});
	};
	
	
	$(".m02 > a").on("click", function(){
		var qInx = $(this).attr("data-index");
		fnM01Close();
		$(".tooltipSub").eq(qInx).css({"display":"none"});
		$(".quick > ul > li > a").removeClass("over");
		$(this).addClass("over");
		$(".quick > ul > li").removeClass("on");
		$(this).parent().addClass("on");

		$(".qMenuWrap02").css({"display":"block","right":36}).stop(true,true).animate({width:179}, quickTimer, easeOut);
		$(".m02 .close").stop(true,true).animate({"right":18}, quickTimer, easeOut);

		$(".tooltip").hide();
		$(".tooltip > span").hide();
	});

	
	$(".m02 .close").on("click", function(){
		fnM02Close();
		$(".m02 > a").focus();
		return false;
	});
	function fnM02Close(){
		$(".m02").removeClass("on");
		$(".qMenuWrap02").stop(true,true).animate({width:0}, quickTimer, easeOut, function(){
			$(".qMenuWrap02").css({"display":"none"});
			$(".m02 > a").removeClass("over");
		});
		$(".m02 .close").stop(true,true).animate({"right":-179}, quickTimer, easeOut);
	};

	
	$(".m03 > a").on("click", function(){
		fnM01Close();
		fnM02Close();
		$(".quick > ul > li").removeClass("on");
		$(this).parent().addClass("on");
		
		$(".tooltip").hide();
		$(".tooltip > span").hide();
	});

	
	$(".m04 > a").on("click", function(){
		fnM01Close();
		fnM02Close();
		$(".quick > ul > li").removeClass("on");
		$(this).parent().addClass("on");
		$("html, body").stop(true,true).animate({scrollTop:0}, 700, easeOut, function(){
			$(".quick .m04").removeClass("on");
		});

		$(".tooltip").hide();
		$(".tooltip > span").hide();
	});


//barometer ================

	HashMap = function() {
		this.map = new Array();
	};
	HashMap.prototype = {
		put : function(key, value){
			this.map[key] = value;
		},
		get : function(key){
			return this.map[key];
		},
		clear : function(){
			this.map = new Array();
		},
		getKeys : function(){
			var key = new Array();
			for(i in this.map){
				key.push(i);
			}
			return keys;
		}
	};

	function fnBarometer(areaIdx, sidoNm){
		var map = new HashMap();
		map.put("02", ['left',70,110]);
		map.put("031", ['right',10,80]);
		map.put("032", ['left',55,110]); 
		map.put("033", ['left',150,70]);
		map.put("041", ['left',50,170]); 
		map.put("042", ['right',0,195]);
		map.put("043", ['right',40,160]);
		map.put("044", ['left',85,180]);
		map.put("051", ['right',110,275]);
		map.put("052", ['right',120,260]);
		map.put("053", ['left',170,230]);
		map.put("054", ['left',200,180]);
		map.put("055", ['left',160,270]);
		map.put("061", ['left',60,310]);
		map.put("062", ['left',60,290]);
		map.put("063", ['right',10,240]);
		map.put("064", ['left',70,360]);

		var mtpV0 = map.get(areaIdx)[0]; //툴팁 방향
		var mtpV1 = map.get(areaIdx)[1]; //툴팁 위치 left
		var mtpV2 = map.get(areaIdx)[2]; //툴팁 위치 top

		$(".mapImg > img").attr({"src":"/image/gp/img_barometerL_"+areaIdx+".png"});
		$(".mapTip").addClass(mtpV0).css({"left":mtpV1, "top":mtpV2});
		$(".mapTip > em").text(sidoNm);

		
		$("#sigungu"+areaIdx +"> img").attr({"src":"/image/gp/img_barometer_"+areaIdx+".png"});
		$("#sigungu"+areaIdx +"> img").attr({"alt":sidoNm});
	};


	
	$("#barometerAll > area").on("click", function(){
		var areaIdx = $(this).attr("data-index");
		var sidoNm  = $(this).attr("alt"); //시도명
		$(this).parent().parent().find("span").removeClass("left, right");

		fnBarometer(areaIdx, sidoNm);

		if ($winWidth > 1179) {
			$(".areaL").stop(true,true).animate({width:'357px'}, 300);
			$(".areaR").stop(true,true).animate({width:'493px'}, 300);
		} else if ($winWidth >= 851 && $winWidth <= 1179) {
			$(".areaL").stop(true,true).animate({width:'350px'}, 300);
			$(".areaR").stop(true,true).animate({width:'500px'}, 300);
		} else if ($winWidth >= 728 && $winWidth <= 850) {
			$(".areaL").stop(true,true).animate({width:'297px'}, 300);
			$(".areaR").stop(true,true).animate({width:'411px'}, 300);
		};

		$(".p0, .p1").css({'display':'none'});
		$(".p2").css({'display':'block'});
		$(".areaL").removeClass("on");
		$(".areaR").addClass("on");
		$(".sigungu").css({'display':'none'});
		$("#sigungu"+areaIdx).css({'display':'block'});

		return false;
	});

	$(".sigungu > map area").on("click", function(){
		var indexR = $(this).attr("data-index");
		var sigunguNm = $(this).attr("alt"); //시군구명
		$(this).parent().prev().attr({"src":"/image/gp/img_barometer_"+indexR+".png"});
		$(this).parent().prev().attr({"alt":sigunguNm});
		return false;


	});

//lnb ======================================


//	$.fn.hasAttr = function(name){
//		return this.attr(name) !== undefined;
//	};
	var lnbTimer = 300;
	$("#lnb > ul > li.active > a").on("click",function(){
		if ($(this).next().css("display")=="none"){
			$("#lnb > ul > li.active > a").removeClass("active");
			$("#lnb > ul > li.active > ul").slideUp(lnbTimer,easeOut);
			$("#lnb > ul > li.an > a").removeClass("on");
			$(this).addClass("active");
			$(this).next().slideDown(lnbTimer,easeOut);
			return false;
		};
	});
	$("#lnb > ul > li.active > ul > li").on("click",function(){
		$(this).parent().find("li").removeClass("active");
		$(this).addClass("active");
	});
	$("#lnb > ul > li.an > a").on("click",function(){
		$("#lnb > ul > li.active > ul").slideUp(lnbTimer,easeOut);
		$("#lnb > ul > li.active > a").removeClass("active");
		$("#lnb > ul > li.an > a").removeClass("on");
		if ($(this).attr("target") != "_blank"){
			$(this).addClass("on");
		};
	});



	$(".sectionN02 .select > p > a").click(function(){
		if (toggleVal=="off"){
			$(".sectionN02 .select > ul").css({"display":"block"});
			toggleVal="on";
		} else {
			$(".sectionN02 .select > ul").css({"display":"none"});
			toggleVal = "off";
		};
		return false;
	});



	var mainTimer = 500;
	var vTimer = 400;
	var fadeTimer = 200;

	function fnvSection01Close(){
		$(".vSection01 .menuOn").stop(true,true).animate({width:0}, vTimer, easeOut, function(){
			$(".vSection01 .menuOn").css({"display":"none"});
		});
		$(".vSection01 .menuOn > div").css({"left":0}).stop(true,true).animate({left:-460}, vTimer, easeOut);
	};

	function fnvSection02Close(){
		$(".vSection02 .menuOn").stop(true,true).animate({width:0}, vTimer, easeOut, function(){
			$(".vSection02 .menuOn").css({"display":"none"});
		});
		$(".vSection02 .menuOn > div").css({"left":0}).stop(true,true).animate({left:-700}, vTimer, easeOut);
	};
	

	function fnvSection03Close(){
		$(".vSection03 .menuOn").stop(true,true).animate({width:0}, vTimer, easeOut, function(){
			$(".vSection03 .menuOn").css({"display":"none"});
		});
		$(".vSection03 .menuOn > div").css({"left":0}).stop(true,true).animate({left:-940}, vTimer, easeOut);
	};


	function fnvSection04Close(){
		$(".vSection04 .menuOn").stop(true,true).animate({width:0}, vTimer, easeOut, function(){
			$(".vSection04 .menuOn").css({"display":"none"});
		});
		$(".vSection04 .menuOn > div").css({"left":0}).stop(true,true).animate({left:-940}, vTimer, easeOut);
	};



	$(".vSection01 p a").on("mouseover focus", function(){
		$(this).find("img").fadeOut(fadeTimer,easeOut);
	});
	$(".vSection01 p a").on("mouseleave", function(){
		$(this).find("img").fadeIn(fadeTimer,easeOut);
	});
	$(".vSection01 > p > a").on("", function(){	//개인보증 열기
		if ($winWidth > 1179) {
			if ($(".vSection01 > .menuOn").css("display") != "block"){
				$(".vSection01 .menuOn").css({"display":"block", "width":0}).stop(true,true).animate({width:460}, vTimer, easeOut);
				$(".vSection01 .menuOn > div").css({"left":-460}).stop(true,true).animate({left:0}, vTimer, easeOut);
				fnvSection02Close();
			};
			return false;
		};
	});

	$(".vSection01 .close").on("click", function(){ //사전정보공개 닫기
		fnvSection01Close();
		return false;
	});
	$(".vSection01 .product01 li a").on("mouseover focus", function(){
		$(this).find("img").fadeOut(fadeTimer,easeOut);
	});
	$(".vSection01 .product01 li a").on("mouseleave", function(){
		$(this).find("img").fadeIn(fadeTimer,easeOut);
	});


	$(".vSection02 p a").on("mouseover focus", function(){
		$(this).find("img").fadeOut(fadeTimer,easeOut);
	});
	$(".vSection02 p a").on("mouseleave", function(){
		$(this).find("img").fadeIn(fadeTimer,easeOut);
	});

	$(".vSection02 > p > a").on("", function(){ //오픈API 서비스 열기
		if ($winWidth > 1179) {
			if ($(".vSection02 > .menuOn").css("display") != "block"){
				$(".vSection02 .menuOn").css({"display":"block", "width":0}).stop(true,true).animate({width:460}, vTimer, easeOut);
				$(".vSection02 .menuOn > div").css({"left":-700}).stop(true,true).animate({left:0}, vTimer, easeOut);
				fnvSection02Close();
			};
			return false;
		};
	});

	$(".vSection02 .close").on("click", function(){ //오픈API 서비스  닫기
		fnvSection02Close();
		return false;
	});
	$(".vSection02 .product01 li a").on("mouseover focus", function(){
		$(this).find("img").fadeOut(fadeTimer,easeOut);
		return false;
	});
	$(".vSection02 .product01 li a").on("mouseleave", function(){
		$(this).find("img").fadeIn(fadeTimer,easeOut);
		return false;
	});
	

	$(".vSection03 p a").on("mouseover focus", function(){
		$(this).find("img").fadeOut(fadeTimer,easeOut);
	});
	$(".vSection03 p a").on("mouseleave", function(){
		$(this).find("img").fadeIn(fadeTimer,easeOut);
	});

	$(".vSection03 > p > a").on("", function(){ //공공데이터개방안내 열기
		if ($winWidth > 1179) {
			if ($(".vSection03 > .menuOn").css("display") != "block"){
				$(".vSection03 .menuOn").css({"display":"block", "width":0}).stop(true,true).animate({width:460}, vTimer, easeOut);
				fnvSection03Close();
			};
			return false;
		};
	});

	$(".vSection03 .close").on("click", function(){ //공공데이터개방안내 닫기
		fnvSection02Close();
		return false;
	});
	$(".vSection03 .product01 li a").on("mouseover focus", function(){
		$(this).find("img").fadeOut(fadeTimer,easeOut);
		return false;
	});
	$(".vSection03 .product01 li a").on("mouseleave", function(){
		$(this).find("img").fadeIn(fadeTimer,easeOut);
		return false;
	});
	
	

	$(".vSection04 p a").on("mouseover focus", function(){
		$(this).find("img").fadeOut(fadeTimer,easeOut);
	});
	$(".vSection04 p a").on("mouseleave", function(){
		$(this).find("img").fadeIn(fadeTimer,easeOut);
	});

	$(".vSection04 > p > a").on("", function(){ //공공데이터개방안내 열기
		if ($winWidth > 1179) {
			if ($(".vSection04 > .menuOn").css("display") != "block"){
				$(".vSection04 .menuOn").css({"display":"block", "width":0}).stop(true,true).animate({width:460}, vTimer, easeOut);
				fnvSection03Close();
			};
			return false;
		};
	});

	$(".vSection04 .close").on("click", function(){ //공공데이터개방안내 닫기
		fnvSection02Close();
		return false;
	});
	$(".vSection04 .product01 li a").on("mouseover focus", function(){
		$(this).find("img").fadeOut(fadeTimer,easeOut);
		return false;
	});
	$(".vSection04 .product01 li a").on("mouseleave", function(){
		$(this).find("img").fadeIn(fadeTimer,easeOut);
		return false;
	});
	
	

	var vSec04BannNum;
	$(".vSection04").on("swipeleft", function(){
		var v04pagdLen = $(".vSection04 .page a").length;
		var mainSec4W = $(".vSection04 .proList").width();
		vSec04BannNum = $(".vSection04 .proList li.active").index() + 1;
		if(vSec04BannNum >= v04pagdLen) return false;
		$(".vSection04 .proList ul li").removeClass("active");
		$(".vSection04 .proList ul li").eq(vSec04BannNum).addClass("active");
		$(".vSection04 .page .on").removeClass("on");
		$(".vSection04 .page > a").eq(vSec04BannNum).addClass("on");
		$(".vSection04 .proList ul li").each(function(index){
			$(this).stop().animate({"left":"-="+mainSec4W}, mainTimer, easeOut);
		});
		vSec04BannNum++;
	});
	$(".vSection04").on("swiperight", function(){
		var mainSec4W = $(".vSection04 .proList").width();
		vSec04BannNum = $(".vSection04 .proList li.active").index()-1;
		if(vSec04BannNum < 0) return false;
		$(".vSection04 .proList ul li").removeClass("active");
		$(".vSection04 .proList ul li").eq(vSec04BannNum).addClass("active");
		$(".vSection04 .page .on").removeClass("on");
		$(".vSection04 .page > a").eq(vSec04BannNum).addClass("on");
		$(".vSection04 .proList ul li").each(function(index){
			$(this).stop().animate({"left":"+="+mainSec4W}, mainTimer, easeOut);
		});
		vSec04BannNum--;
	});





	if ($("#containerM").length){movieBannerFirst()};

    var firstmove = true;
    var obj = -1, movieNum = 0;
    var bannerRolling, bannerRolling2;
    var allIdx = $(".vBanner > ul > li").length - 1;

    function movieBanner(obj, onClassIdx){
    	var mainBannW = $(".vBanner").width();
		movieNum =  $(".vBanner .page a.on").index()+1;
    	if (obj > -1) { movieNum = obj } else { movieNum = movieNum};
    	if (movieNum > allIdx) { movieNum = 0 };

		$(".vBanner .page > a.num").removeClass("on");
		$(".vBanner .page > a").eq(movieNum).addClass("on");

		$(".vBanner > ul").stop(true,true).animate({"left":-movieNum*mainBannW}, 800, "easeInOutExpo");
		movieNum++;
    };

	$(".vBanner .page a.num").on("click", function(){
		clearTimeout(bannerRolling2);
		clearInterval(bannerRolling);
		var onClassIdx = $(".vBanner .page a.on").index(); //이전 선택된 버튼
		var obj = $(this).index();
		if ($("#playCtrl").hasClass("stop")){
			$("#playCtrl").removeClass("stop");
			$("#playCtrl").addClass("play").html('play');
		};
		movieBanner(obj, onClassIdx);
	});

	var vBannNum;
	$(".vBanner").on("swipeleft", function(){
		clearTimeout(bannerRolling2);
		clearInterval(bannerRolling);
		var mainBannW = $(".vBanner").width();
		var vBannPagdLen = $(".vBanner .page a.num").length;
		vBannNum =  $(".vBanner .page a.on").index()+1;
		if(vBannNum >= vBannPagdLen) { return false };
		if ($winWidth < 728) {
//			$(".vBanner > ul").css("width",mainBannW*vBannPagdLen);
		};
		$(".vBanner > ul").stop().animate({'left': -mainBannW*vBannNum}, timer, easeInOut);
		$(".vBanner .page a.num").removeClass("on");
		$(".vBanner .page a.num").eq(vBannNum).addClass("on");
		$("#playCtrl").removeClass("stop");
		$("#playCtrl").addClass("play").html('play');
		vBannNum++;
	});
	$(".vBanner").on("swiperight", function(){
		clearTimeout(bannerRolling2);
		clearInterval(bannerRolling);
		var mainBannW = $(".vBanner").width();
		vBannNum =$(".vBanner .page a.on").index()-1;
		if(vBannNum < 0) { return false };
		$(".vBanner > ul").stop().animate({'left': -mainBannW*vBannNum}, timer, easeInOut);
		$(".vBanner .page a.num").removeClass("on");
		$(".vBanner .page a.num").eq(vBannNum).addClass("on");
		$("#playCtrl").removeClass("stop");
		$("#playCtrl").addClass("play").html('play');
		vBannNum--;
	});


    function movieBannerFirst() {
    	clearTimeout(bannerRolling2);
    	bannerRolling2 = setTimeout(movieBanner, 3000);
    	bannerRolling = setInterval(movieBanner, 6000);
	};


    function movieBannerStart() {
    	clearInterval(bannerRolling);
    	bannerRolling = setInterval(movieBanner, 6000);
	};

    function movieBannerOne() {
    	clearInterval(bannerRolling);
    	bannerRolling = setTimeout(movieBanner, 100);
    	bannerRolling = setInterval(movieBanner, 6000);
    	return false;
	};


	$("#playCtrl").on("click", function(){
		clearInterval(bannerRolling);
		if ($(this).hasClass("stop")){
			$(this).removeClass("stop");
			$(this).addClass("play").html('play');
		} else {
			$(this).removeClass("play");
			$(this).addClass("stop").html('stop');
			movieBannerOne();
		};
		return false;
	});



	$(".infoZone > h3 > a").on("click", function(){
		$(".infoZone > h3").removeClass("on");
		$(".infoZone > h3").next().css("display","none");
		$(this).parent().addClass("on");
		$(this).parent().next().css("display","block");
		return false;
	});

	$(".quickService .prev").on("click", function(){
		iconMenuOut("prev");
		return false;
	});
	$(".quickService .next").on("click", function(){
		iconMenuOut("next");
		return false;
	});

	function iconMenuOut(btnKind){
		var iconWrapL = parseInt($(".iconMenu ul").css("left"));
		var iconW = parseInt($(".iconMenu ul li").outerWidth(true));
		var iconCnt;
		if ($winWidth > 1179) {
			iconCnt = 3;
		} else if ($winWidth >= 851 && $winWidth <= 1179) {
			iconCnt = 4;
		} else {
			iconCnt = 5;
		};

		if ($winWidth > 1179) {
			if (btnKind=="prev"){
				$(".iconMenuWrap > ul").stop(true,true).animate({left:0}, mainTimer, easeOut);
			};

			if (btnKind=="next"){
				$(".iconMenuWrap > ul").stop(true,true).animate({left:-584}, mainTimer, easeOut);
			};

		} else if ($winWidth >= 728 && $winWidth <= 1179){
			if (btnKind=="prev"){
				if (iconWrapL <= -iconW ){
					$(".iconMenuWrap > ul").stop(true,true).animate({left:"+="+iconW}, 300, easeOut);
				};
			};
			if (btnKind=="next"){
				if (iconWrapL >= -iconW*iconCnt){
					$(".iconMenuWrap > ul").stop(true,true).animate({left:"-="+iconW}, 300, easeOut);
				};
			};
		};
	};


	function popSize() {
		clearInterval(popupZRolling); //자동롤링 멈춤
		var popBannW = $(".popupZone .popList").width();
		var popBannLen = $(".popupZone .popList ul li").length;
		$(".popupZone .popList ul").css("width", popBannW*popBannLen);
		$(".popupZone .popList ul li").css("width", popBannW);
		$("#popPlayCtrl").removeClass("play");
		$("#popPlayCtrl").addClass("stop").html('stop');
		popupZStart();
	};

	$(".popupZone > span button.prev").on("click", function(){
		popupZOut("prev");
		return false;
	});
	$(".popupZone > span button.next").on("click", function(){
		popupZOut("next");
		return false;
	});
	function popupZOut(btnKind){
		var popBannWrapL = parseInt($(".popupZone .popList ul").css("left"));
		var popBannW = $(".popupZone .popList ul li").width();
		var popBannLen = $(".popupZone .popList ul li").length - 2;
		clearInterval(popupZRolling);
		if (btnKind=="prev"){
			if (popBannWrapL <= -popBannW ){
				$(".popupZone .popList ul").stop(true,true).animate({left:"+="+popBannW}, 300, easeOut);
			};
		};
		if (btnKind=="next"){
			if (popBannWrapL >= -popBannW*popBannLen){
				$(".popupZone .popList ul").stop(true,true).animate({left:"-="+popBannW}, 300, easeOut);
			};
		};
		$("#popPlayCtrl").removeClass("stop");
		$("#popPlayCtrl").addClass("play").html('play');
	};

	$(".popupZone .popList").on("swipeleft", function(){
		$(".popupZone > span button.next").trigger("click");
	});
	$(".popupZone .popList").on("swiperight", function(){
		$(".popupZone > span button.prev").trigger("click");
	});


	var popupZRolling, popupZNum = 0;
	function moviePopupZ(){
		var popBannW = parseInt($(".popupZone .popList ul li").css("width"));
		var pageCnt = $(".popupZone .popList ul li").length - 1;
		if (popupZNum > pageCnt) { popupZNum = 0 };
		$(".popupZone .popList ul").stop(true,true).animate({left:-popupZNum*popBannW}, mainTimer, easeOut);
    	popupZNum++;
	};

    function popupZStart() {
    	clearInterval(popupZRolling);
    	popupZRolling = setInterval(moviePopupZ, 2000);
	};


    function moviePopupZOne() {
    	clearInterval(popupZRolling);
    	popupZRolling = setTimeout(moviePopupZ, 200);
    	popupZRolling = setInterval(moviePopupZ, 3000);
	};

	$("#popPlayCtrl").on("click", function(){
		clearInterval(popupZRolling);
		if ($(this).hasClass("stop")){
			$(this).removeClass("stop");
			$(this).addClass("play").html('play');
		} else {
			$(this).removeClass("play");
			$(this).addClass("stop").html('stop');
			moviePopupZOne();
		};
	});

	var bannzoneW, bannWrapL, bannW;

	function banSize() {
		bannLiLen = $(".BannerZone .banner li").length;
		bannALen = $(".BannerZone .banner li a").length;
		bannzoneW = $(".BannerZone > div").width();

		$("#bannPlayCtrl").removeClass("play");
		$("#bannPlayCtrl").addClass("stop").html('stop');

		if ((bannzoneW / 2) != 0){
			bannzoneW = bannzoneW - 1;
		}

		if ($winWidth > 1179) {
			pageCnt = $(".BannerZone ul.banner li").length - 1;
			bannW = 180;
			$(".BannerZone ul.banner").css("width",bannW*bannLiLen);
			$(".BannerZone ul.banner li").css("width",bannW);

		} else if ($winWidth >= 851 && $winWidth <= 1179) {
			pageCnt = $(".BannerZone ul.banner li").length - 1;
			bannW = 175;
			$(".BannerZone ul.banner").css("width", bannW*bannALen);
			$(".BannerZone ul.banner li").css("width", bannW*2);

		} else if ($winWidth >= 728 && $winWidth <= 850) {
			pageCnt = $(".BannerZone ul.banner li > a").length - 2;
			bannW = 169;
			$(".BannerZone ul.banner").css("width", bannW*bannALen);
			$(".BannerZone ul.banner li").css("width", bannW*2);

		} else if ($winWidth < 728) {
			bannW = bannzoneW / 2;
			$(".BannerZone ul.banner").css("width",bannW*bannALen);
			$(".BannerZone ul.banner li").css("width",bannW*2);
		};
		clearInterval(bannZRolling); //자동롤링 멈춤
		bannZStart();
	};

	var bannZNum = 1;
	function bannZoneMove(){
		if ($winWidth > 1179) {
			pageCnt = $(".BannerZone ul.banner li").length - 1;
			bannW = 180;

		} else if ($winWidth >= 851 && $winWidth <= 1179) {
			pageCnt = $(".BannerZone ul.banner li a").length - 4;
			bannW = 175;

		} else if ($winWidth >= 728 && $winWidth <= 850) {
			pageCnt = $(".BannerZone ul.banner li > a").length - 2;
			bannW = 169;

		} else if ($winWidth < 728) {
			pageCnt = $(".BannerZone ul.banner li > a").length - 2;
			bannzoneW = parseInt($(".BannerZone > div").css("width"));
			bannW = bannzoneW / 2;
			$(".BannerZone ul.banner").css("width",bannW*bannALen);
			$(".BannerZone ul.banner li").css("width",bannzoneW);
		};
		if (bannZNum > pageCnt) { bannZNum = 0 };
		$(".BannerZone ul.banner").stop(true,true).animate({left:-bannZNum*bannW}, mainTimer, easeOut);
    	bannZNum++;
	};
	$(".BannerZone > div > span button.prev").on("click", function(){
		fnBannzoneBtn("prev");
		return false;
	});
	$(".BannerZone > div > span button.next").on("click", function(){
		fnBannzoneBtn("next");
		return false;
	});

	var bannZCnt = 1;
	if ($winWidth < 728) {
		$(".bannStyle").bind("swipeleft", function(){

			clearInterval(bannZRolling); //자동롤링 멈춤
			$("#bannPlayCtrl").removeClass("stop");
			$("#bannPlayCtrl").addClass("play").html('play');

			var bannZStyleW = $(".bannStyle").width();
			var bannZLen =  $(".bannStyle > ul > li").length-1;
			$(".bannStyle > ul").css("width",bannZStyleW*bannZLen);
			$(".bannStyle > ul > li").css("width",bannZStyleW);

			if (bannZCnt >= bannZLen) return false;
			$(".bannStyle > ul").stop(true,true).animate({left:-bannZStyleW*bannZCnt}, mainTimer, easeOut);
			bannZCnt++;
		});
		$(".bannStyle").bind("swiperight", function(){
			clearInterval(bannZRolling); //자동롤링 멈춤

			$("#bannPlayCtrl").removeClass("stop");
			$("#bannPlayCtrl").addClass("play").html('play');

			var bannZStyleW = $(".bannStyle").width();
			var bannZLen =  $(".bannStyle > ul > li").length;
			$(".bannStyle > ul").css("width",bannZStyleW*bannZLen);
			$(".bannStyle > ul > li").css("width",bannZStyleW);

			if (bannZCnt <= 1) return false;
			$(".bannStyle > ul").stop(true,true).animate({left:"+="+bannZStyleW}, mainTimer, easeOut);
			bannZCnt--;
		});
	};

	function fnBannzoneBtn(btnKind){
		clearInterval(bannZRolling); //자동롤링 멈춤
		var bannzTimer = 300, nextMax;
		bannLiLen = $(".BannerZone .banner li").length;
		bannALen = $(".BannerZone .banner li a").length;
		bannWrapL = parseInt($(".BannerZone ul.banner").css("left"));

		if ($winWidth > 1179) {
			bannW = 180;
			bannLiLen = bannLiLen-2;
			nextMax = -(bannLiLen*bannW);

		} else if ($winWidth >= 851 && $winWidth <= 1179) {
			bannW = 175;
			bannALen = bannALen - 5;
			nextMax = -(bannALen*bannW);

		} else if ($winWidth >= 728 && $winWidth <= 850) {
			bannW = 169;
			bannALen = bannALen - 3;
			nextMax = -(bannALen*bannW);

		} else if ($winWidth < 728){
			bannALen = bannALen - 3;
			nextMax = -(bannALen*bannW);
			bannzoneW = parseInt($(".BannerZone > div").css("width"));
			bannW = bannzoneW / 2;
			$(".BannerZone ul.banner li").css("width",bannzoneW);
			$(".BannerZone ul.banner").css("width", bannzoneW*bannLiLen);
		};

		if (btnKind=="prev"){
			if (bannWrapL <= -bannW){
				$(".BannerZone .banner").stop(true,true).animate({left:"+="+bannW}, bannzTimer, easeOut);
			};
		};
		if (btnKind=="next"){
			if  (bannWrapL >= nextMax){
				$(".BannerZone .banner").stop(true,true).animate({left:"-="+bannW}, bannzTimer, easeOut);
			};
		};
		$("#bannPlayCtrl").removeClass("stop");
		$("#bannPlayCtrl").addClass("play").html('play');
	};

	//롤링 시작
    function bannZStart() {
    	clearInterval(bannZRolling);
    	bannZRolling = setInterval(bannZoneMove, 2000);
	};

    function bannZoneMoveOne() {
    	clearInterval(bannZRolling);
    	bannZRolling = setTimeout(bannZoneMove, 100);
    	bannZRolling = setInterval(bannZoneMove, 2000);
	};

	$("#bannPlayCtrl").on("click", function(){
		clearInterval(bannZRolling);
		if ($(this).hasClass("stop")){
			$(this).removeClass("stop");
			$(this).addClass("play").html('play');
		} else {
			$(this).removeClass("play");
			$(this).addClass("stop").html('stop');
			bannZoneMoveOne();
		};
	});

	$(".boardTb > ul > li > p").on("click", function(){
		if( $(this).parent().hasClass("on") == false){
			$(".boardTb > ul > li").removeClass("on");
			$(this).parent().addClass("on");
		} else {
			$(this).parent().removeClass("on");
		};
		return false;
	});

	$(".tabSite a").on("click", function() { //ul.tab01 > li > a
		var fromTop = 82+117;
		href = $(this).attr("href");
		if(href && href.indexOf("#") != -1 && href.indexOf("#") != href.length - 1) {
			href = href.substring(href.indexOf("#"));
			if($(href).length > 0) {
				$('html, body').stop(true,true).animate({scrollTop: $(href).offset().top - fromTop}, 0);
				return false;
			};
		};
	});

	$(".infoAccess li a").on("click", function() { //ul.tab01 > li > a
		var fromTop = 82+117;
		href = $(this).attr("href");
		if(href && href.indexOf("#") != -1 && href.indexOf("#") != href.length - 1) {
			href = href.substring(href.indexOf("#"));
			if($(href).length > 0) {
				$('html, body').stop(true,true).animate({scrollTop: $(href).offset().top - fromTop}, 0);
				return false;
			};
		};
	});

	if ($("#topAd").children().hasClass("banner")==false){
		$(".topGroup > div > p").html('');
	};
	function fnTopAdSize(){
		clearInterval(topBannerRolling);
		var topBannIdx = $(".banner > div > ul > li").length;
		$(".banner > div > ul").css({"left":0,  "width":$topBannW*topBannIdx});
		$(".banner > div > ul > li").css({"width":$topBannW});
		$("#topAd .page > a.num").removeClass("on");
		$("#topAd .page > a.num").eq(0).addClass("on");
	};
	
	var topBannObj = -1;
	var topBannerRolling;
	var topBannAllIdx = $(".banner > div > ul > li").length - 1 ;

	function topBannerFirst() {
    	clearInterval(topBannerRolling);
    	topBannerRolling = setInterval(movieTopBanner, 4000);
	};
	function topBannerPlay() {
    	clearInterval(topBannerRolling);
    	topBannerRolling = setTimeout(movieTopBanner, 200);
    	topBannerRolling = setInterval(movieTopBanner, 4000);
	};	
	function movieTopBanner(topBannObj, onClassIdx){
		var topBannNum =  $("#topAd .page .num.on").index() + 1;		
		if (topBannObj > -1) { topBannNum = topBannObj } else { topBannNum = topBannNum};
		if (topBannNum > topBannAllIdx) { topBannNum = 0 };
		$("#topAd .page > a.num").removeClass("on");
		$("#topAd .page > a.num").eq(topBannNum).addClass("on");
		$("#topAd > .banner > div > ul").stop(true,true).animate({'left': -$topBannW*topBannNum}, timer, easeInOut);
		topBannNum++;
	};
	$("#topAdPlayCtrl").on("click", function(){
		clearInterval(topBannerRolling);
		if ($(this).hasClass("stop")){
			$(this).removeClass("stop");
			$(this).addClass("play").html('play');
		} else {
			$(this).removeClass("play");
			$(this).addClass("stop").html('stop');
			topBannerPlay();
		};
	});
	$("#topAd .banner > button.next").on("click", function(){
		clearInterval(topBannerRolling);		
		$("#topAdPlayCtrl").removeClass("stop");
		$("#topAdPlayCtrl").addClass("play").html('play');
		var topAdOn = $("#topAd .page .num.on").index();
		var topAdLen = $("#topAd .page .num").length - 1;
		if (topAdOn >= topAdLen) return false;
		$("#topAd > .banner > div > ul").stop(true,true).animate({'left': "-="+$topBannW}, timer, easeInOut);
		$("#topAd .page > .num").removeClass("on");
		$("#topAd .page > .num").eq(topAdOn+1).addClass("on");		
		topAdOn++;
	});
	$("#topAd .banner > button.prev").on("click", function(){
		clearInterval(topBannerRolling);		
		$("#topAdPlayCtrl").removeClass("stop");
		$("#topAdPlayCtrl").addClass("play").html('play');
		var topAdOn = $("#topAd .page .num.on").index();		
		if (topAdOn <= 0) return false;
		$("#topAd > .banner > div > ul").stop(true,true).animate({'left': "+="+$topBannW}, timer, easeInOut);
		$("#topAd .page > .num").removeClass("on");
		$("#topAd .page > .num").eq(topAdOn-1).addClass("on");		
		topAdOn--;
	});
	
	$("#topAd .page a.num").on("click", function(event){
		event.preventDefault();
		clearInterval(topBannerRolling);
		$("#topAdPlayCtrl").removeClass("stop");
		$("#topAdPlayCtrl").addClass("play").html('play');
		var topAdNum = $(this).index();
		$("#topAd .page > a.num").removeClass("on");
		$(this).addClass("on");
		$("#topAd > .banner > div > ul").stop().animate({'left': -$topBannW*topAdNum}, timer, easeInOut);
		return false;
	});

	var topAdBannNum;
	$(".banner > div").on("swipeleft", function(){
		var pagdLen = $("#topAd .page a.num").length;
		topAdBannNum =  $("#topAd > .banner > .page a.on").index()+1;
		if(topAdBannNum >= pagdLen) { return false };
		$("#topAd > .banner > div > ul").stop().animate({'left': -$topBannW*topAdBannNum}, timer, easeInOut);
		$("#topAd > .banner > .page a.num").removeClass("on");
		$("#topAd > .banner > .page a.num").eq(topAdBannNum).addClass("on");
		topAdBannNum++;
	});
	$(".banner > div").on("swiperight", function(){
		topAdBannNum = $("#topAd > .banner > .page a.on").index()-1;
		if(topAdBannNum < 0) { return false };
		$("#topAd > .banner > div > ul").stop().animate({'left': $topBannW*topAdBannNum}, timer, easeInOut);
		$("#topAd > .banner > .page a.num").removeClass("on");
		$("#topAd > .banner > .page a.num").eq(topAdBannNum).addClass("on");
		topAdBannNum--;
	});

	var topAdDownSlide;
	function topAdDown(){
		if ($winWidth < 728) { return false };
		if ($("#container").length){ return false };
		var topAdDownTimer = 700;
		if ($("#topAd").children().hasClass("banner")) {
			$("#topAd").css({'top':-$topAdHeight}).stop(true,true).animate({'top':0}, topAdDownTimer, easeInOut);
			$("header").css('top',0).stop(true,true).animate({'top':$topAdHeight}, topAdDownTimer, easeInOut);
			$("#containerM").css('margin-top',115).stop(true,true).animate({'marginTop':$topAdHeight+115}, topAdDownTimer, easeInOut, function(){
				$("#topAd").addClass("open");
				$(".topAd").css({'background-image':'url(/image/common/bul/bul_closeArrow.png)'});
				$(".topAd").text("닫기");
				if($(".allMenuWrap").css("display")=="block" ){
					if( $("#topAd").hasClass("open")){
						$(".allMenu p").css({'position':'fixed', 'top':$topAdHeight+155});
					};
				};
				topBannerFirst();
			});
		};
	};
	var topAdDownTimer;
	if ($("#containerM").length){
		topAdDownSlide = setTimeout(topAdDown, 1000);
	};
	
	function contMarginMin(){
		$(".topAd").text("열기");
		$(".topAd").css({'background-image':'url(/image/common/bul/bul_openArrow.png)'});
		$("#topAd").removeClass("open");
	};
	function contMarginMax(){
		$(".topAd").text("닫기");
		$(".topAd").css({'background-image':'url(/image/common/bul/bul_closeArrow.png)'});
		$("#topAd").addClass("open");
	}
	
	$(window).scroll(function(){
		if ($winWidth < 728){ return false };
		if ($("#topAd").hasClass("open")){ //광고창 열려있을때

			if ($(window).scrollTop() > 82){
				$("#topAd").stop().animate({'top':-$topAdHeight-35}, wsTimer, easeInOut);
				$("header").stop().animate({'top':-35}, wsTimer, easeInOut);
				$("#containerM").css({'margin-top':115}).stop().animate({'top':-35}, wsTimer, easeInOut);
				$("#container").css({'margin-top':165}).stop().animate({'top':-35}, wsTimer, easeInOut);
				$(".quick").stop().animate({'top':165-35}, wsTimer, easeInOut);
				contMarginMin();
			};
			
		} else { //광고창 닫혀있을때
			
			if ($(window).scrollTop() > 82){
				$("#topAd").stop().animate({'top':-$topAdHeight-35}, wsTimer, easeInOut);
				$("header").stop().animate({'top':-35}, wsTimer, easeInOut);
				$("#containerM").css({'margin-top':115}).stop().animate({'top':-35}, wsTimer, easeInOut);
				$("#container").css({'margin-top':165}).stop().animate({'top':-35}, wsTimer, easeInOut);
				$(".quick").stop().animate({'top':165-35}, wsTimer, easeInOut);
				contMarginMin();
			} else {
				$("#topAd").stop().animate({'top':-$topAdHeight}, wsTimer, easeInOut);
				$("header").stop().animate({'top':0}, wsTimer, easeInOut);
				$("#containerM").stop().animate({'top':0}, wsTimer, easeInOut);
				$("#container").stop().animate({'top':0}, wsTimer, easeInOut);
				$(".quick").stop().animate({'top':165}, wsTimer, easeInOut);
				contMarginMin();
			};
		};
	});

	$(".topAd").on("click", function(){
		clearInterval(topBannerRolling);		
		if ($winWidth < 728) { return false };
		
		if ($("#topAd").hasClass("open")){

			$("#topAd").stop().animate({'top':-$topAdHeight}, wsTimer, easeInOut, function(){
				$(".topAd").text("열기");
				$(".topAd").css({'background-image':'url(/image/common/bul/bul_openArrow.png)'});
				$("#topAd").removeClass("open");
			});
			$("header").stop().animate({'top':0}, wsTimer, easeInOut);

			if ($("#containerM").length) {
				$("#containerM").stop().animate({'marginTop':115}, wsTimer, easeInOut);
			} else {
				$("#container").stop().animate({'marginTop':165}, wsTimer, easeInOut);
				$(".quick").stop().animate({'top':165}, wsTimer, easeInOut);
			};

			if( $(".allMenuWrap").css("display")=="block"){
				$(".allMenu p").stop().animate({'top':155}, wsTimer, easeInOut);
			};


		} else { //광고 닫힌 상태
			
			$("#topAd button.prev").focus();

			$("#topAd").stop().animate({'top':0}, wsTimer, easeInOut, function(){
				$("#topAd").addClass("open");
				$(".topAd").text("닫기");
				$(".topAd").css({'background-image':'url(/image/common/bul/bul_closeArrow.png)'});
				topBannerFirst();				
			});
			$("header").stop().animate({'top':$topAdHeight}, wsTimer, easeInOut);
			
			if ($("#containerM").length) {
				$("#containerM").stop().animate({'marginTop':$topAdHeight+115}, wsTimer, easeInOut);
			} else {
				$("#container").stop().animate({'marginTop':$topAdHeight+165}, wsTimer, easeInOut);
				$(".quick").stop().animate({'top':$topAdHeight+165}, wsTimer, easeInOut);
			};

			if( $(".allMenuWrap").css("display")=="block"){
				$(".allMenu p").stop().animate({'top':$topAdHeight+155}, wsTimer, easeInOut);
			}
		};
	});
});
	