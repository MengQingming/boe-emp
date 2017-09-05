/**
 * 
 */
	
$(document).ready(function()
{
	// input只有输入数字
	$("input.number-only").keydown(function(e)
	{
		
		// 只能输入数量和.,ESC,ENTER,BACKSPACE,DELETE,LEFT,RIGHT,negative 等功能键
		if ((e.keyCode >= 48 && e.keyCode <= 57) || (e.keyCode>= 96 && e.keyCode <= 105)) // 小键盘数字96~105
			return true;

		if (e.keyCode == 190 || e.keyCode == 110)
		{
			var value = $(this).val();
			if (value.indexOf(".") > -1)
				return false;
			else
				return true;
		}
		
		if (e.keyCode == 189 || e.keyCode == 109)
		{
			var value = $(this).val(); 
			if (!value.startsWith("-"))
				$(this).val("-" + value);
							
			return false;	
		}
		
		if(!(e.keyCode == 8 || e.keyCode == 13 || e.keyCode == 27 || e.keyCode == 46 || e.keyCode == 37 || e.keyCode== 39))
		{
			return false;
		}
		
	});
	
	// 负数标红的控件change事件
	$(".negative_red").change(function()
	{
		var val = $(this).val();
		if (Number(val) < 0)
			negativeRedColor($(this));
		else
			positiveNormalColor($(this));
	});
	
	// 所有负数要变红的统一变红
	allNegativeRed();
	
}); // End of document ready function.

/**
 * 所有负数要变红的统一变红
 */
function allNegativeRed()
{
	$(".negative_red").each(function(i, o)
	{
		var val = 0;
		if ($(o).is("input"))
			val = $(o).val();
		else
			val = $(o).text();
		
		if (val != "")
		{
			if (Number(val) < 0)
				negativeRedColor($(o));
			else
				positiveNormalColor($(o));
		}
	});
}

/**
 * 负数变红
 * @param $obj
 */
function negativeRedColor($obj)
{
	$obj.css("color", "red");
}

/**
 * 正数的颜色变为正常
 * @param $obj
 */
function positiveNormalColor($obj)
{
	$obj.css("color", "");
}





