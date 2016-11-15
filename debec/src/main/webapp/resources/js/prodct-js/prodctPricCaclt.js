function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
function pointZeroZero(x)
{
	return x.toFixed(2);
}
$(document).ready(function() {

	// 마진울, 순이익 계산 함수

	// 판매가  입력시 실행
	$("#prodctSelPric").keyup(function(){ //판매가
		var prodctDiscntRat = $("#discntRat").val()
    	var prodctSelPric = $("#prodctSelPric").val();
    	var prodctPurchsPric = $("#prodctPurchsPric").val();

    	if($("#prodctSelPric").val()>=0&&$("#prodctPurchsPric").val()>=0)
    	{
    		var netincPric = numberWithCommas(prodctSelPric-prodctPurchsPric);
    		var margnPric = pointZeroZero(((prodctSelPric/prodctPurchsPric)*100)-100);
	    	$("#prodctNetincPric").val(netincPric+" 원");
	    	$("#prodctMargnPric").val(margnPric+" %");
    	}
    	
    	if($("#discntRat").val()>=0&&$("#prodctSelPric").val()>=0)
    	{
    		var schedlSalePric = numberWithCommas((prodctSelPric*(100-prodctDiscntRat))/100)
    		
	    	var schedlSalePric= (prodctSelPric*(100-prodctDiscntRat))/100;
	    	var schedlMargnRat = pointZeroZero(((schedlSalePric/prodctPurchsPric)*100)-100);
	    	$("#schedlSalePric").val(schedlSalePric +" 원");
	    	$("#schedlMargnRat").val(schedlMargnRat+" %");
    	}
	});
	
	//입고가격 입력시 실행
	$("#prodctPurchsPric").keyup(function(){
		var prodctDiscntRat = $("#discntRat").val()
    	var prodctSelPric = $("#prodctSelPric").val();
    	var prodctPurchsPric = $("#prodctPurchsPric").val();

    	if($("#prodctSelPric").val()>=0&&$("#prodctPurchsPric").val()>=0)
    	{
    		var netincPric = numberWithCommas(prodctSelPric-prodctPurchsPric);
    		var margnPric = pointZeroZero(((prodctSelPric/prodctPurchsPric)*100)-100);
	    	$("#prodctNetincPric").val(netincPric+" 원");
	    	$("#prodctMargnPric").val(margnPric+" %");
    	}
    	
    	if($("#discntRat").val()>=0&&$("#prodctSelPric").val()>=0)
    	{
    		var schedlSalePric = numberWithCommas((prodctSelPric*(100-prodctDiscntRat))/100)
    		
	    	var schedlSalePric= (prodctSelPric*(100-prodctDiscntRat))/100;
	    	var schedlMargnRat = pointZeroZero(((schedlSalePric/prodctPurchsPric)*100)-100);
	    	$("#schedlSalePric").val(schedlSalePric +" 원");
	    	$("#schedlMargnRat").val(schedlMargnRat+" %");
    	}
	});

	//할인율 입력시 실행
	$("#discntRat").keyup(function(){
		var prodctDiscntRat = $("#discntRat").val()
    	var prodctSelPric = $("#prodctSelPric").val();
    	var prodctPurchsPric = $("#prodctPurchsPric").val();

    	if($("#prodctSelPric").val()>=0&&$("#prodctPurchsPric").val()>=0)
    	{
    		var netincPric = numberWithCommas(prodctSelPric-prodctPurchsPric);
    		var margnPric = pointZeroZero(((prodctSelPric/prodctPurchsPric)*100)-100);
	    	$("#prodctNetincPric").val(netincPric+" 원");
	    	$("#prodctMargnPric").val(margnPric+" %");
    	}
    	
    	if($("#discntRat").val()>=0&&$("#prodctSelPric").val()>=0)
    	{
    		var schedlSalePric = numberWithCommas((prodctSelPric*(100-prodctDiscntRat))/100)
    		
	    	var schedlSalePric= (prodctSelPric*(100-prodctDiscntRat))/100;
	    	var schedlMargnRat = pointZeroZero(((schedlSalePric/prodctPurchsPric)*100)-100);
	    	$("#schedlSalePric").val(schedlSalePric +" 원");
	    	$("#schedlMargnRat").val(schedlMargnRat+" %");
    	}
    });
});