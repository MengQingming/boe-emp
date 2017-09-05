<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>版本管理</title>
	<meta name="decorator" content="default"/>
	<style>
		body{
			overflow-x: hidden;
		}
	</style>
	<script>
		$(document).ready(function() {
			$('.select2-container').width($('#versionNum').width() + 12 + 'px');
		})
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/appVersion/">版本列表</a></li>
		<li class="active"><a href="${ctx}/sys/appVersion/form?id=${appVersion.id}&query=details">版本详情</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="appVersion" action="${ctx}/sys/appVersion/save" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<div style="margin-left: 5%;padding-top: 60px;">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">App:</label>
			<div class="controls">
				<form:select id="appNo" path="appNo" disabled="true" class="input-medium">
					<form:options items="${fns:getAllApp()}" itemLabel="appName"
						itemValue="appNo" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本号:</label>
			<div class="controls">
				<form:input path="versionNum" htmlEscape="false" maxlength="50" readonly="true" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本名称:</label>
			<div class="controls">
				<form:input path="versionName" htmlEscape="false" maxlength="50" readonly="true" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本日期:</label>
			<div class="controls">
				<input id="versionDate" name="versionDate" type="text" readonly="readonly" class="Wdate required"
				value="<fmt:formatDate value="${appVersion.versionDate}" pattern="yyyy-MM-dd"/>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间:</label>
			<div class="controls">
				<input type="text" readonly="readonly" value="<fmt:formatDate value="${appVersion.creationDate}" pattern="yyyy-MM-dd"/>" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改时间:</label>
			<div class="controls">
				<input type="text" readonly="readonly" value="<fmt:formatDate value="${appVersion.lastUpdateDate}" pattern="yyyy-MM-dd"/>" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">版本说明:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" disabled="true" class="input-xlarge"/>
			</div>
		</div>
		</div>
	</form:form>
</body>
</html>