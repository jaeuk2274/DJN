function Number_Format(val)
{
	if(val)
	{
		val = toNumber(val).toString();
		var len = val.length;
		var str = "";
		if(len > 3){
		    var in_c = len % 3;
		    if(!in_c) in_c = 3;
    
		    for(n = 0; n < len; n++){
				if(n == in_c){
				str += ",";
				in_c = 3 + in_c;
				}
			str += val.charAt(n);
		    }
			return str;
		}
		else
		{
		    return val;
		}
	}
}
// 파라미터에 쉼표 찍고 싶은 숫자를 넣으면 됩니다. 참 쉽죠?

// var bear = 19.99999
// bear = bear.toFixed(2)
// alert(bear) -> 19.99
// 소수점이 긴  var를 줄여줍니다.