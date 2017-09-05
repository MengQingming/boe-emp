<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公司管理</title>
	<meta name="decorator" content="default"/>
	<style>
		body{
			overflow-x: hidden;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/company/">公司列表</a></li>
		<li class="active"><a href="${ctx}/sys/company/form?id=${company.id}&query=details">公司详情</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="company" action="${ctx}/sys/company/save" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<div style="padding-top: 50px;">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">公司编号:</label>
			<div class="controls">
				<form:input path="companyNo" htmlEscape="false" maxlength="50" readonly="true" class="required abc"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司名称:</label>
			<div class="controls">
				<form:input path="companyName" htmlEscape="false" maxlength="50" readonly="true" class="required specialCharFilter"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司简称:</label>
			<div class="controls">
				<form:input path="companyShortName" htmlEscape="false" maxlength="50" readonly="true" class="specialCharFilter"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否启用:</label>
			<div class="controls">
				<form:radiobuttons path="status" items="${fns:getDictItemListL('yes_no','T')}" disabled="true" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间:</label>
			<div class="controls">
				<input type="text" readonly="readonly" value="<fmt:formatDate value="${company.creationDate}" pattern="yyyy-MM-dd"/>" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改时间:</label>
			<div class="controls">
				<input type="text" readonly="readonly" value="<fmt:formatDate value="${company.lastUpdateDate}" pattern="yyyy-MM-dd"/>" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" readonly="true" class="input-xlarge"/>
			</div>
		</div>
		</div>
	</form:form>
</body>
</html>