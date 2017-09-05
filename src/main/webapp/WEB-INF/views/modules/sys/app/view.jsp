<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>应用管理</title>
	<meta name="decorator" content="default"/>
	<style>
		body{
			overflow-x: hidden;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/app/">App列表</a></li>
		<li class="active"><a href="${ctx}/sys/app/form?id=${app.id}&query=details">App详情</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="app" action="${ctx}/sys/app/save" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<div style="padding-top: 60px;">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">App编号:</label>
			<div class="controls">
				<form:input path="appNo" htmlEscape="false" maxlength="50" readonly="true" class="required abc"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">App名称:</label>
			<div class="controls">
				<form:input path="appName" htmlEscape="false" maxlength="50" readonly="true" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间:</label>
			<div class="controls">
				<input type="text" readonly="readonly" value="<fmt:formatDate value="${app.creationDate}" pattern="yyyy-MM-dd"/>" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改时间:</label>
			<div class="controls">
				<input type="text" readonly="readonly" value="<fmt:formatDate value="${app.lastUpdateDate}" pattern="yyyy-MM-dd"/>" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否启用:</label>
			<div class="controls">
				<form:radiobuttons path="status" disabled="true" items="${fns:getDictItemListL('yes_no','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
			</div>
		</div>
		</div>
	</form:form>
</body>
</html>