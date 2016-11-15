function catgrChoice(size,value)
{

	var cartgArray = new Array();
	var catgrData = { "catgrSize" : size, "catgrNme" : value};
	if(value=="대분류"){
		
		$("#midCatgrNme").find("option").remove();
		$("#midCatgrNme").append("<option>중분류</option>");
		$("#botCatgrNme").find("option").remove();
		$("#botCatgrNme").append("<option>소분류</option>");
		$("input[name=selectedBotCatgrNme]").val("소분류");
	}
	if(size == "대" && value=="전체"){
		
		$("#midCatgrNme").find("option").remove();
		$("#midCatgrNme").append("<option>전체</option>");
		$("#botCatgrNme").find("option").remove();
		$("#botCatgrNme").append("<option>전체</option>");
		$("input[name=selectedBotCatgrNme]").val("소분류");
	}
	else
	{
		$.ajax({
			type: "get",
			url: "/catgrList.do", //이페이지에서 중복체크를 한다
			data : catgrData,
			dataType : "JSON",
			success: function(data){
				var catgrArray = data["catgrArray"];
				if(size=="대")
				{
					$("#midCatgrNme").find("option").remove();
					$("#midCatgrNme").append("<option selected>중분류</option>");
					for(var i=0;i<catgrArray.length;i++)
					$("#midCatgrNme").append("<option>"+catgrArray[i]+"</option>");
					$("input[name=topCatgrNme]").val(value);
					
					catgrArray = data["botCatgrArray"];
					
					$("#botCatgrNme").find("option").remove();
					$("#botCatgrNme").append("<option selected>소분류</option>");
					for(var i=0;i<catgrArray.length;i++)
					$("#botCatgrNme").append("<option>"+catgrArray[i]+"</option>");
					$("input[name=midCatgrNme]").val($("#midCatgrNme").val());
				}
				else if(size=="중")
				{
					$("#botCatgrNme").find("option").remove();
					$("#botCatgrNme").append("<option selected>소분류</option>");
					for(var i=0;i<catgrArray.length;i++)
					$("#botCatgrNme").append("<option>"+catgrArray[i]+"</option>");
					$("input[name=midCatgrNme]").val(value);
				}
				$("input[name=selectedTopCatgrNme]").val($("#topCatgrNme").val());
				$("input[name=selectedMidCatgrNme]").val($("#midCatgrNme").val());
				$("input[name=selectedBotCatgrNme]").val($("#botCatgrNme").val());
			
			},
		    error:function(xhr,status,error)
		    { //ajax 오류인경우  
		    	alert("$");
	            alert("error\nxhr : " + xhr + ", status : " + status + ", error : " + error);       
	   		}
		});
	}
	
}	