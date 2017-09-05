<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>版本管理</title>
<meta name="decorator" content="default"/>
<script type="text/javascript">
	$(document).ready(function() {
      var obj = $("#left",window.parent.document).find("a");
      var index;
      for(var i=0;i<obj.length;i++){
        var str = obj[i].innerText;
        if($.trim(str)=="版本管理"){
          index=i;
        }
      }
      var className = obj[index-1].parentNode.className;
      if(className.indexOf("active")>=0)
		{
		   obj[index-1].parentNode.removeAttribute("class","active");
      	   obj[index].parentNode.setAttribute("class","menu3-40 hide hide active");
		}
    });
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/appVersion/">版本列表</a></li>
		<shiro:hasPermission name="sys:appVersion:edit"><li><a href="${ctx}/sys/appVersion/form?sort=10">版本添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dict" action="${ctx}/sys/appVersion/" method="post" class="breadcrumb form-search css-searchForm">
		<ul class="ul-form">
		  <li>
		  	<label>App：</label>
			<select id="appNo" name="appNo" class="input-medium">
				<option></option>
				<c:forEach items="${fns:getAllApp()}" var="app">
					<option value="${app.appNo}" <c:if test='${appquery == app.appNo}'>selected = "selected"</c:if>>${app.appName}</option>
				</c:forEach>
			</select>
		 <li style="margin-left: 20px;">
			<input id="btnSubmit" class="btn btn-primary" type="button" onclick="query()" value="查询" />
		  </li>
		 </ul>
	</form:form>
	<div id="content" style="width: 100%;">
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed css-contentTable">
		<thead>
		<tr>
			<th>版本名称</th>
			<th>App名称</th>
			<th>版本号</th>
			<th>版本日期</th>
			<th>版本说明</th>
			<shiro:hasPermission name="sys:appVersion:edit">
				<th>操作</th>
			</shiro:hasPermission>
		</tr>
		</thead>
	</table>
	<div class="pagination">${page}</div>
</div>
<script type="text/javascript">
$(function(){ 
	queryList(30,1);
}); 

function queryList(pageSize,pageNo){
		loading('加载中，请稍等...');
		$.ajax({ 
			url: "${ctx}/sys/appVersion/list", 
			data:{
				pageSize:pageSize,
				pageNo:pageNo,
				appNo:$("#appNo").val()
			},
			type:"post",
			cache:false,
			context: document.body, 
			success: function(html){
				closeLoading();
				$("#css-back").click();
	        	$("#content").replaceWith(html);
	        	$('.table img').height(parseInt($('.table').css('line-height')) * 0.8 + 'px');
      		}
      	});
	}
function query(){
	queryList(30,1);
}
</script>
</body>
</html>