<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典项管理</title>
	<meta name="decorator" content="default"/>
	<style>
		body{
			overflow-x: hidden;
		}
	</style>
	<script>
		$(document).ready(function() {
			$('.select2-container').width($('#companyName').width() + 12 + 'px');
		})
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/dictItem/">字典项列表</a></li>
		<li class="active"><a href="${ctx}/sys/dictItem/form?id=${dictItem.id}&query=details">字典项详情</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="dictItem" action="${ctx}/sys/dictItem/save" method="post" class="form-horizontal" style="margin:0px;">
		<div id="fixedTop" class="breadcrumb form-search">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<div style="padding-top: 60px;">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">所属公司:</label>
			<div class="controls">
					<form:input type="text" path="companyName" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属字典组:</label>
			<div class="controls">
				<form:select path="dict.id" class="input-medium" disabled="true">
					<form:options disabled="return" items="${fns:getAllDict()}" itemLabel="dictName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">字典项编码:</label>
			<div class="controls">
				<form:input path="itemCode" readonly="true" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">字典项名称:</label>
			<div class="controls">
				<form:input path="itemValue" readonly="true" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<form:input path="displayOrder" readonly="true" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">语言</label>
			<div class="controls">
				<select name="language" id="language" disabled="true">
					<option value="zh_CN" selected="selected">中文</option>
					<option value="en_US">英文</option>
				</select>
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
				<input type="text" readonly="readonly" value="<fmt:formatDate value="${dictItem.creationDate}" pattern="yyyy-MM-dd"/>" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改时间:</label>
			<div class="controls">
				<input type="text" readonly="readonly" value="<fmt:formatDate value="${dictItem.lastUpdateDate}" pattern="yyyy-MM-dd"/>" />
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