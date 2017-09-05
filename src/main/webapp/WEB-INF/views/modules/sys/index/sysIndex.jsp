<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')}</title>
	<meta name="decorator" content="blank"/><c:set var="tabmode" value="${empty cookie.tabmode.value ? '0' : cookie.tabmode.value}"/>
    <c:if test="${tabmode eq '1'}"><link rel="Stylesheet" href="${ctxStatic}/jerichotab/css/jquery.jerichotab.css" />
    <script type="text/javascript" src="${ctxStatic}/jerichotab/js/jquery.jerichotab.js"></script></c:if>
    <script src="${ctxStatic}/weather/jquery.leoweather.min.js"></script>
<%--	<script src="http://localhost/bundle.js"></script>
  	<link rel="stylesheet" href="http://localhost/bundle.css">--%>
	<script src="${ctxStatic}/scripts/components/SystemPreferences.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/styles/components/SystemPreferences.css">
 	<script src="${ctxStatic}/scripts/modules/css-header.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/styles/modules/css-header.css">
	<style type="text/css">
		#footer {margin:8px 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;}
		#footer, #footer a {color:#999;} #left{overflow-x:hidden;overflow-y:auto;} #left .collapse{position:static;}
		#userControl>li>a{text-shadow:none;} #userControl>li>a:hover, #user #userControl>li.open>a{background:transparent;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#weather').leoweather({format:'<span id="colock">{年}.{月}.{日}/星期{周}</span> '});
			//切换语言
			$("#languageSct").change(function(){
				$.ajax({ 
		           type: "post", 
		           url: "${ctx}/parameter/sysParameter/changeLan",
		           data: {"language":$(this).val()},
		           dataType: "json", 
		           success: function (data) { 
		        	   if(data.status==true){
		        		   window.location.href="${ctx}";
		        	   }
		           }
		   		});
			});
			//切换组织机构
			$("#groupSct").change(function(){
				$.ajax({ 
		           type: "post", 
		           url: "${ctx}/parameter/sysParameter/changeGroup",
		           data: {"groupId":$(this).val()},
		           dataType: "json", 
		           success: function (data) { 
		        	   if(data.status==true){
		        		   window.location.href="${ctx}";
		        	   }
		           }
		   		});
			});
			// <c:if test="${tabmode eq '1'}"> 初始化页签
			$.fn.initJerichoTab({
                renderTo: '#right', uniqueId: 'jerichotab',
                contentCss: { 'height': $('#right').height() - tabTitleHeight },
                tabs: [], loadOnce: true, tabWidth: 110, titleHeight: tabTitleHeight
            });//</c:if>
			// 绑定菜单单击事件
			$("#menu a.menu").click(function(){
				$(".css-menuListTitle").text($(this).text());
				// 一级菜单焦点
				$("#menu li.menu").removeClass("active");
				$(this).parent().addClass("active");
				// 左侧区域隐藏
				//&new=+Math.random()
				/* if ($(this).attr("target") == "mainFrame"){
					$("#mainFrame").attr("src",$(this).attr("data-href"));
					$("#left,#openClose").hide();
					wSizeWidth();
					// <c:if test="${tabmode eq '1'}"> 隐藏页签
					$(".jericho_tab").hide();
					$("#mainFrame").show();//</c:if>
					return true;
				} */
				$("#left,#openClose").hide();
				wSizeWidth();
				// <c:if test="${tabmode eq '1'}"> 隐藏页签
				$(".jericho_tab").hide();
				$("#mainFrame").show();//</c:if>
				// 左侧区域显示
				$("#left,#openClose").show();
				if(!$("#openClose").hasClass("close")){
					$("#openClose").click();
				}
				// 显示二级菜单
				
				var menuId = "#menu-" + $(this).attr("data-id");
				if ($(menuId).length > 0){
					$.get($(this).attr("data-href"), function(data){
						
					});
					$("#left .accordion").hide();
					$(menuId).show();
					// 初始化点击第一个二级菜单
					if (!$(menuId + " .accordion-body:first").hasClass('in')){
						$(menuId + " .accordion-heading:first a").click();
					}
					if (!$(menuId + " .accordion-body li:first ul:first").is(":visible")){
						$(menuId + " .accordion-body a:first i").click();
					}
					// 初始化点击第一个三级菜单
					$(menuId + " .accordion-body li:first li:first a:first i").click();
				}else{
					// 获取二级菜单数据
					$.get($(this).attr("data-href"), function(data){
						if (data.indexOf("id=\"loginForm\"") != -1){
							alert('未登录或登录超时。请重新登录，谢谢！');
							top.location = "${ctx}";
							return false;
						}
						$("#left .accordion").hide();
						$("#left").append(data);
						// 链接去掉虚框
						$(menuId + " a").bind("focus",function() {
							if(this.blur) {this.blur()};
						});
						// 二级标题
						$(menuId + " .accordion-heading a").click(function(){
							changeAppNo($(this).attr("id"));
								$(menuId + " .accordion-toggle i").removeClass('icon-chevron-down').addClass('icon-chevron-right');
							if(!$($(this).attr('data-href')).hasClass('in')){
								$(this).children("i").removeClass('icon-chevron-right').addClass('icon-chevron-down');
							}
						});
						// 二级内容
						$(menuId + " .accordion-body a").click(function(){
							changeAppNo($(this).attr("id"));
							$(menuId + " li").removeClass("active");
							$(menuId + " li i").removeClass("icon-white");
							$(this).parent().addClass("active");
							$(this).children("i").addClass("icon-white");
						});
						// 展现三级
						$(menuId + " .accordion-inner a").click(function(){
							changeAppNo($(this).attr("id"));
							var href = $(this).attr("data-href");
							if($(href).length > 0){
								$(href).toggle().parent().toggle();
								return false;
							}
							// <c:if test="${tabmode eq '1'}"> 打开显示页签
							return addTab($(this)); // </c:if>
						});
						// 默认选中第一个菜单
						$(menuId + " .accordion-body a:first i").click();
						$(menuId + " .accordion-body li:first li:first a:first i").click();
					});
				} 
				
				// 大小宽度调整
				wSizeWidth();
				return false;
			});
			// 初始化点击第一个一级菜单
			$("#menu a.menu:first span").click();
			// <c:if test="${tabmode eq '1'}"> 下拉菜单以选项卡方式打开
			$("#userInfo .dropdown-menu a").mouseup(function(){
				return addTab($(this), true);
			});// </c:if>
			// 鼠标移动到边界自动弹出左侧菜单
			$("#openClose").mouseover(function(){
				if($(this).hasClass("open")){
					$(this).click();
				}
			});
		    // 获取通知数目  <c:set var="oaNotifyRemindInterval" value="${fns:getConfig('oa.notify.remind.interval')}"/>
			/* function getNotifyNum(){
				$.get("${ctx}/oa/oaNotify/self/count?updateSession=0&t="+new Date().getTime(),function(data){
					var num = parseFloat(data);
					if (num > 0){
						$("#notifyNum,#notifyNum2").show().html("("+num+")");
					}else{
						$("#notifyNum,#notifyNum2").hide()
					}
				});
			}
			getNotifyNum(); //<c:if test="${oaNotifyRemindInterval ne '' && oaNotifyRemindInterval ne '0'}">
			setInterval(getNotifyNum, ${oaNotifyRemindInterval}); //</c:if> */
		}); 
		// <c:if test="${tabmode eq '1'}"> 添加一个页签
		function addTab($this, refresh){
			$(".jericho_tab").show();
			$("#mainFrame").hide();
			$.fn.jerichoTab.addTab({
                tabFirer: $this,
                title: $this.text(),
                closeable: true,
                data: {
                    dataType: 'iframe',
                    dataLink: $this.attr('href')
                }
            }).loadData(refresh);
			return false;
		}// </c:if>
		
		function changeAppNo(appNo){
			$.ajax({ 
		           type: "post", 
		           url: "${ctx}/sys/menu/changeAppNo",
		           data: {"appNo":appNo},
		           dataType: "json", 
		           success: function (data) { 
		           }
		   		});
		}
	</script>
</head>
<body>
	<div id="main">
		<div id="header" class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="brand"><img src="static/images/NewHope.png" class="logo"><%--<span id="productName"> --%><%--${fns:getConfig('productName')}--%></span></div>



<%--

				<ul id="userControl" class="nav pull-right">
					<li><a href="${pageContext.request.contextPath}${fns:getFrontPath()}/index-${fnc:getCurrentSiteId()}.html" target="_blank" title="访问网站主页"><i class="icon-home"></i></a></li>
					<li id="themeSwitch" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" title="主题切换"><i class="icon-th-large"></i></a>
						<ul class="dropdown-menu">
							<c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
							<li><a href="javascript:cookie('tabmode','${tabmode eq '1' ? '0' : '1'}');location=location.href">${tabmode eq '1' ? '关闭' : '开启'}页签模式</a></li>
						</ul>
						<!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
					</li>
					<li id="userInfo" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" title="个人信息">您好, ${fns:getUser().fullName}&nbsp;<span id="notifyNum" class="label label-info hide"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/sys/user/info" target="mainFrame"><i class="icon-user"></i>&nbsp; 个人信息</a></li>
							<li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame"><i class="icon-lock"></i>&nbsp;  修改密码</a></li>
							<li><a href="${ctx}/oa/oaNotify/self" target="mainFrame"><i class="icon-bell"></i>&nbsp;  我的通知 <span id="notifyNum2" class="label label-info hide"></span></a></li>
						</ul>
					</li>
					<li><a href="${ctx}/logout" title="退出登录">退出</a></li>
					<li>&nbsp;</li>
				</ul>
--%>


				<ul id="userControl" class="nav pull-right">
					<%-- <li id="themeSwitch" class="dropdown">
						<select id="languageSct" style="margin-top: 10px;">
							<c:forEach items="${fns:getDictItemListL('sys_language','T')}" var="v">
								<c:if test="${v.selected==true}">
									<option selected="selected" value="${v.itemCode}">${v.itemValue}</option>
								</c:if>
								<c:if test="${v.selected==false}">
									<option value="${v.itemCode}">${v.itemValue}</option>
								</c:if>
							</c:forEach>
						</select>
					</li> --%>
					<li class="dropdown">
					<a class="dropdown-toggle" id="css-userMes" data-toggle="dropdown" href="#" title="个人信息">
							<span class="css-user">您好, ${fns:getUser().fullName}</span>
							<c:if test="${fns:getUser().id!=1}">
								<span class="css-zuZhi">
									<c:forEach items="${fns:getUser().groupList}" var="v">
										<c:if test="${v.selected==true}">
											<span value="${v.id}">${v.groupShortName}</span> 
										</c:if>
									</c:forEach>
								</span>
							</c:if>
							<c:if test="${fns:getUser().id==1}">
								<span id="groupSct" class="css-zuZhi">
									<span>营运管理系统</span>
								</span>
							</c:if>
							<span id="notifyNum" class="label label-info hide"></span>
						</a>

						<ul class="dropdown-menu" role="menu" id="css-memu">
						<li><a href="${ctx}/sys/user/info" target="mainFrame"><i class="icon-user"></i>&nbsp; 个人信息</a></li>
						<li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame"><i class="icon-lock"></i>&nbsp;  修改密码</a></li>
						<li class="css-set"><a href="javascript:void(0)"><i class=" icon-asterisk"></i>&nbsp;  偏好设置</a></li>
							<%-- <li><a href="${ctx}/oa/oaNotify/self" target="mainFrame"><i class="icon-bell"></i>&nbsp;  我的通知 <span id="notifyNum2" class="label label-info hide"></span></a></li> --%>
						</ul>
					</li>


					<li><a href="${ctx}/logout" id="css-exit" title="退出登录"><img src="data:image/png;base64,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" class="images"></a></li>
					<li>&nbsp;</li>
				</ul>
				<%-- <c:if test="${cookie.theme.value eq 'cerulean'}">
					<div id="user" style="position:absolute;top:0;right:0;"></div>
					<div id="logo" style="background:url(${ctxStatic}/images/logo_bg.jpg) right repeat-x;width:100%;">
						<div style="background:url(${ctxStatic}/images/logo.jpg) left no-repeat;width:100%;height:70px;"></div>
					</div>
					<script type="text/javascript">
						$("#productName").hide();$("#user").html($("#userControl"));$("#header").prepend($("#user, #logo"));
					</script>
				</c:if> --%>
				<div class="nav-collapse">
					<ul id="menu" class="nav" style="*white-space:nowrap;float:none;">
						<c:set var="firstMenu" value="true"/>
						<c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
							<c:if test="${menu.parent.id eq '1'&&menu.displayFlag eq '1'}">
								<li class="menu ${not empty firstMenu && firstMenu ? ' active' : ''}">
									<c:if test="${empty menu.url}">
										<a class="menu" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}"><span>${menu.menuName}</span></a>
									</c:if>
									<c:if test="${not empty menu.url}">
										<a class="menu" href="${fn:indexOf(menu.url, '://') eq -1 ? ctx : ''}${menu.url}?parentId=${menu.parent.id}" data-id="${menu.id}" target="mainFrame"><span>${menu.menuName}</span></a>
									</c:if>
								</li>
								<c:if test="${firstMenu}">
									<c:set var="firstMenuId" value="${menu.id}"/>
								</c:if>
								<c:set var="firstMenu" value="false"/>
							</c:if>
						</c:forEach><%--
						<shiro:hasPermission name="cms:site:select">
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#">${fnc:getSite(fnc:getCurrentSiteId()).name}<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<c:forEach items="${fnc:getSiteList()}" var="site"><li><a href="${ctx}/cms/site/select?id=${site.id}&flag=1">${site.name}</a></li></c:forEach>
							</ul>
						</li>
						</shiro:hasPermission> --%>
					</ul>
				</div><!--/.nav-collapse -->
			</div>
	    </div>
	    <div class="container-fluid">
			<div id="content" class="row-fluid">
				<div id="left"><%-- 
					<iframe id="menuFrame" name="menuFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe> --%>
<!-- 					<div class="css-menuListTitle">配置中心</div> -->
				</div>
				<div id="openClose" class="close">&nbsp;</div>
				<div id="right">
					<iframe id="mainFrame" name="mainFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
				</div>
			</div>
		    <div id="footer" class="row-fluid">
	           <%-- Copyright &copy; 2012-${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} Powered By <a href="http://www.t-ark.com" target="_blank">T-ark</a> ${fns:getConfig('version')}  --%>
	            Copyright &copy; 2012-${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} ${fns:getConfig('version')} 
			</div>
		</div>
		<div class="css-alter">
              <div class="header">
                  <ul class="nav nav-tabs alter-btn">
                      <li class="active"><a href="javascript:void(0)">语言</a></li>
                      <li><a href="javascript:void(0)">主题</a></li>
                      <li><a href="javascript:void(0)">组织机构</a></li>
                  </ul>
                  <div class="css-close">
                      <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAFMklEQVR4Xu3dTWoVQRSG4S86FjcjBJ2L4lRcgEF3oRgRN6GCGQpuQNGZILgenSuFtunc3NyuU3VO1fnL1Jvq7nqfVLd9/46QP6Fn4Cj00efBIwEER5AAEkDwGQh++LkCJIDgMxD88HMFSADBZyD44ecKkABEZuApgA8AfoqMHm/QmwAeAXjHfegSK8ArAM8AfAdwPxF0JyvxPwM4BnAK4GX3iKsBuAEs8ZdNJIK+Wuv4y0isCDgB7MZPBPzx2RFwAbgqfiJoQ7DvL393JJaVgAPAVvxEQENQE59tJegFUBs/EdQhoMRnQdADgBo/ERxG0BK/G0ErgNb4iWA/gp74XQhaAPTGTwQXEXDEb0ZABcAVPxH8nQHO+E0IKADK7d03ddcypEdFvVkkEX+Z+BMA72sqUADcAPAJwJ2agYmPiYZAMv43AA8A/KppQAFQxksENbMqd7W/tXVS/DIYFUAi2EpgKH4rgETQhkDNsr/e/ZYVYPn9PB3UQ1AZv2cFSAQO4nMAyNOBsXP+7u72nALWY+Xp4DIEtcs+1zXA7iEngvMZMRGf6xSQK8HFPwUz8SUARL8mMBVfCkBUBObiSwKIhsBkfGkAURCYjT8CgHcEpuOPAuAVgfn4IwF4Q+Ai/mgAXhC4iT8DgHUEruLPAmAVgbv4MwFYQ+Ay/mwAVhC4ja8BgHYEruNrAaAVgfv4mgBoQxAivjYAWhCEia8RwGwEoeJrBTALQbj4mgGMRhAyvnYAoxBcW30OX9km5w/5vXqcG68Zi+tl4TXban2M5KuNfwC4DuBW684d+D318S2sAMv8SiIQaA8T8S0BkD4dcCIwE98aAAsITMW3CEAzAnPxrQLQiMBkfMsANCEwG986AA0ITMf3AGAmAvPxvQCYgcBFfE8ARiJwE98bgAXBl3/fr8N5c2cZq3yg5b3aD2GU2AHuMS08F0A55vKs3lehe/tlP8pzB3c9fRGWJwCST+muEbr6WFsvAEbFX58KXHwlngcAo+O7QmAdwKz4bhBYBjA7vgsEVgFoiW8egUUA2uKbRmANgNb4ZhFYAqA9vkkEVgBYiW8OgQUAkvHLXb3ysvBjyv3myseauGOoHYBk/OVZvTIHYb8NTTOAEfGXr1aTfN+B6pVAK4CR8ZcVPSQCjQBmxA+LQBuAmfFDItAEQEP8cAi0ANAUPxQCDQA0xg+DYDYAzfFDIJgJwEJ89whmAbAU3zWCGQAsxneLYDQAy/FdIhgJwEN8dwhGAfAU3xWCEQA8xneDQBqA5/guEEgCiBDfPAIpAJHim0YgASBifLMIuAFEjm8SASeAjH/+cmEzLy/jApDxL79W3AQCDgAZ/+o3CqhH0Asg42+/S0Q1gh4AGX87vvoLw1YAGb8+vmoELQAyPj2+WgRUABm/Pb5KBBQAGb8/vjoEFABPALzlm4P/I7n67F3C/Ej97+A3gBMAZzX7QgFQxnsB4LRm4MrHRI0vtRKU+M8BvK6cf1ABcCKIHp8bATl+2YEWABwIMv7FP9He00FT/B4APQgy/v71uRVBc/xeAC0IMv7hkzMVQVd8DgAUBBm/7sqsFkF3fC4ANQgyfl382gtDlvicAA4hyPi0+FsI2OJzA9iHIOO3xb8KAWt8CQBrBBm/L/4ugtvUmzw1m2+9D7A19mMAHz19u9bWAQv/e7kwfFh7e5eyL1IAKPuQj504Awlg4uRr2HQC0FBh4j4kgImTr2HTCUBDhYn7kAAmTr6GTScADRUm7kMCmDj5GjadADRUmLgPCWDi5GvY9B/LJC6fN6v7PQAAAABJRU5ErkJggg==" alt="">
                  </div>
              </div>
              <div class="body">
                  <div id="language">
                    <label for="">选择语言：</label>
                    <select id="languageSct">
						<c:forEach items="${fns:getDictItemListL('sys_language','T')}" var="v">
							<c:if test="${v.selected==true}">
								<option selected="selected" value="${v.itemCode}">${v.itemValue}</option>
							</c:if>
							<c:if test="${v.selected==false}">
								<option value="${v.itemCode}">${v.itemValue}</option>
							</c:if>
						</c:forEach>
					</select>
                  </div>
                  <div id="theme">
                    <div>
                    	<li id="themeSwitch" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" title="主题切换"><i class="icon-th-large">切换系统主题</i></a>
						<ul class="dropdown-menu">
							<c:forEach items="${fns:getDictItemListL('theme','T')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.itemCode}?url='+location.href">${dict.itemValue}</a></li></c:forEach>
							<li><a href="javascript:cookie('tabmode','${tabmode eq '1' ? '0' : '1'}');location=location.href">${tabmode eq '1' ? '关闭' : '开启'}页签模式</a></li>
						</ul>
					</li>
                    </div>
                  </div>
                  <div id="organization">
                    <label for="">选择组织机构：</label>
                    <select id="groupSct" style="width: 250px;">
						<c:forEach items="${fns:getUser().groupList}" var="v">
							<c:if test="${v.selected==true}">
								<option selected="selected" value="${v.id}">${v.groupShortName}</option>
							</c:if>
							<c:if test="${v.selected==false}">
								<option value="${v.id}">${v.groupShortName}</option>
							</c:if>
						</c:forEach>
					</select>
                  </div>
              </div>
          </div>
	</div>
	<script type="text/javascript"> 
		var leftWidth = 160; // 左侧窗口大小
		var tabTitleHeight = 33; // 页签的高度
		var htmlObj = $("html"), mainObj = $("#main");
		var headerObj = $("#header"), footerObj = $("#footer");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize(){
			var minHeight = 500, minWidth = 980;
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({"overflow-x":strs[1] < minWidth ? "auto" : "hidden", "overflow-y":strs[0] < minHeight ? "auto" : "hidden"});
			mainObj.css("width",strs[1] < minWidth ? minWidth - 10 : "auto");
			frameObj.height((strs[0] < minHeight ? minHeight : strs[0]) - headerObj.height() - footerObj.height() - (strs[1] < minWidth ? 42 : 28));
			$("#openClose").height($("#openClose").height() - 5);// <c:if test="${tabmode eq '1'}"> 
			$(".jericho_tab iframe").height($("#right").height() - tabTitleHeight); // </c:if>
			wSizeWidth();
		}
		function wSizeWidth(){
			if (!$("#openClose").is(":hidden")){
				var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
				$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			}else{
				$("#right").width("100%");
			}
		}// <c:if test="${tabmode eq '1'}"> 
		function openCloseClickCallBack(b){
			$.fn.jerichoTab.resize();
		} // </c:if>
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>