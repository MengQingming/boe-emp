<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调度任务配置</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/scheduler/job/">调度任务列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="schedulerJob" action="#" method="post" class="breadcrumb form-search css-searchForm">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<ul class="ul-form">
		  <li>
			<label class="control-label">　任务名称:</label>
			<form:input path="jobName" htmlEscape="false" maxlength="150"/>
		  </li>
		  <li>
			<label class="control-label">　任务编码:</label>
			<form:input path="jobCode" htmlEscape="false" maxlength="50"/>
		  </li>
		  <li>
			<label>是否启用：</label>
			<select name="jobStatus" style="width: 220px">
                <option value="">请选择</option>
				<c:forEach items="${fns:getDictItemListL('yes_no','T')}" var="item">
					<option value="${item.itemCode}">${item.itemValue}</option>
				</c:forEach>
			</select>
		  </li>
		  <li style="margin-left: 20px;">
			<input id="btnSubmit" class="btn btn-primary" type="button" onclick="queryList()" value="查询" />
			<shiro:hasPermission name="scheduler:job:edit">
				<a href="${ctx}/scheduler/job/form" class="btn btn-primary">新增</a>
				<a href="${ctx}/scheduler/job/taskResult" class="btn btn-primary">任务重置</a>
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
			url: "${ctx}/scheduler/job/list", 
			data: $("#searchForm").serialize(),
			type:"post",
			cache:false,
			context: document.body, 
			success: function(html){
				closeLoading();
	        	$("#content").replaceWith(html);
      		}
      	});
	}
</script>
</body>
</html>