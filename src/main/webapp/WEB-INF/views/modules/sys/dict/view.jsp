<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典组管理</title>
	<meta name="decorator" content="default"/>
	<style>
		body{
			overflow-x: hidden;
		}
	</style>
	<script>
		$(document).ready(function() {
			$('.select2-container').width($('#dictCode').width() + 12 + 'px');
		})

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/dict/">字典组列表</a></li>
		<li class="active"><a href="${ctx}/sys/dict/form?id=${dict.id}&query=details">字典组详情</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="dict" action="${ctx}/sys/dict/save" method="post" class="form-horizontal" style="margin:0px;">
		<div id="fixedTop" class="breadcrumb form-search">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<div style="padding-top: 60px;">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">字典编码:</label>
			<div class="controls">
				<form:input path="dictCode" htmlEscape="false" maxlength="50" readonly="true" class="required abc"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">字典名称:</label>
			<div class="controls">
				<form:input path="dictName" htmlEscape="false" maxlength="50" readonly="true" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">App:</label>
			<div class="controls">
				<select name="appNo" id="appNo" style="width: 130px;" disabled="true">
					<option value="T">通用</option>
					<c:forEach items="${apps}" var="app">
						<option value="${app.appNo}" <c:if test="${dict.appNo == app.appNo}"> selected= "selected"</c:if>>${app.appName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">语言</label>
			<div class="controls">
				<form:select path="language" disabled="true">
					<form:options items="${fns:getDictItemListL('sys_language','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否启用:</label>
			<div class="controls">
				<form:radiobuttons path="status" disabled="true" items="${fns:getDictItemListL('yes_no','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间:</label>
			<div class="controls">
				<input type="text" readonly="readonly" value="<fmt:formatDate value="${dict.creationDate}" pattern="yyyy-MM-dd"/>" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改时间:</label>
			<div class="controls">
				<input type="text" readonly="readonly" value="<fmt:formatDate value="${dict.lastUpdateDate}" pattern="yyyy-MM-dd"/>" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" disabled="true" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		</div>
	</form:form>
</body>
</html>