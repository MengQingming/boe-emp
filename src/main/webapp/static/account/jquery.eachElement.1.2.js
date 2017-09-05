/* 
* tableUI
* ------------------------------------
* 
* version: 1.1
* author: prosper
* Date: 2011-2-16
* 使用each可以将传入的元素集合中的奇偶元素加上不同的、交替的CSS样式，并在鼠标悬浮时提供不同的CSS样式 
* jQuery version: 1.3
* ------------------------------------
*
* version: 1.2
* author: prosper
* Date: 2013-1-23
* 当点击选中某一行时，样式变为选中样式
* jQuery version: 1.3
*/ 
(function($)
{ 
	$.fn.eachElement = function(obj)
	{ 
		var defaults = 
		{ 
			evenClass:"evenClass", 
			oddClass:"oddClass", 
			activeClass:"activeClass" ,
			clickClass:"clickClass"
		} 
		var opts = $.extend(defaults, obj); 
		this.each(function(index)
		{ 
			//添加奇偶行颜色 
			if(index % 2 ==0)
				$(this).addClass(opts.evenClass); 
			else
				$(this).addClass(opts.oddClass); 
				
			//添加活动行颜色 
			$(this).not("."+opts.clickClass).on("mouseenter mouseleave", function() 
			{
			  	$(this).toggleClass(opts.activeClass);
			}); 
			
			//点击时颜色固定
			$(this).on("click", function()
			{
				//去掉之前点击选中的行的选中样式
				$("."+opts.clickClass).removeClass(opts.clickClass);
				//给当前行添加选中样式
				$(this).addClass(opts.clickClass);
			});
		}); 
	}; 
})(jQuery); 
