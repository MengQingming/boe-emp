/**
 * 
 */

// 如果不是框架合同，必须输入大于0的数字
$.validator.addMethod(
    		"ammount-v", //验证方法名称
    		function(value, element, param) 
    		{
    			//验证规则
    			var flag = getFrameworkFlag();
    			if ("N" == flag)
				{
    				if (value == "")
    					return false;
    				if (isNaN(value))
    					return false;
    				if (Number(value) <= 0)
    					return false;
				}
    			return true;
    		}, 
    		'请输入大于0的数字'//验证提示信息
    		);


/**
* 失去焦点的时候，小数据点保留几位
*/
function toFixed(obj, count)
{
	if (obj.value != "")
	{
		var v = Number(obj.value);
		$(obj).val(v.toFixed(count));
	}
	
}