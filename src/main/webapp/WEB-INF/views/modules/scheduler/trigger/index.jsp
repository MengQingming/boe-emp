<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调度任务触发器</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/scheduler/trigger/">调度任务触发器列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="schedulerTrigger" action="#" method="post" class="breadcrumb form-search css-searchForm">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<ul class="ul-form">
		  <li>
			<label class="control-label">任务名称:</label>
			<form:input path="jobName" htmlEscape="false" maxlength="150"/>
		  </li>
		  <li>
			<label class="control-label">任务编码:</label>
			<form:input path="jobCode" htmlEscape="false" maxlength="50"/>
		  </li>
		  <li>
			<label class="control-label">触发类型:</label>
			<form:select path="triggerType" style="width:220px;">
				<form:option value="" label=" "/>
				<form:options items="${triggerTypes}" />
			</form:select>
		  </li>
		  <li class="clearfix"></li>
		  <li>
			<label>是否启用：</label>
			<select name="triggerStatus" style="width: 220px">
				<option value="">请选择</option>
				<c:forEach items="${fns:getDictItemListL('yes_no','T')}" var="item">
					<option value="${item.itemCode}">${item.itemValue}</option>
				</c:forEach>
			</select>
		  </li>
		  <li style="margin-left: 20px;">
			<input id="btnSubmit" class="btn btn-primary" type="button" onclick="queryList()" value="查询" />
			<shiro:hasPermission name="scheduler:trigger:edit">
				<a href="${ctx}/scheduler/trigger/form" class="btn btn-primary">新增</a>
			</shiro:hasPermission>
		  </li>
		 </ul>
	</form:form>

	<div id="content" style="width: 100%;"></div>
	
<script type="text/javascript">
	$(function(){ 
		queryList();
	}); 

	function queryList(){
		loading('加载中，请稍等...');
		$.ajax({ 
			url: "${ctx}/scheduler/trigger/list", 
			data: $("#searchForm").serialize(),
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
</script>
</body>
</html>