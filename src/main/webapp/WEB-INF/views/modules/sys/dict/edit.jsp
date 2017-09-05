<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典组管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/scripts/components/selectController.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$("#btnSubmit").attr("disabled",true);
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			select2Controller('#dictCode');
		});
	</script>
	<link rel="stylesheet" href="${ctxStatic}/styles/pages/css-dict.css">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/dict/">字典组列表</a></li>
		<li class="active"><a href="${ctx}/sys/dict/form?id=${dict.id}">字典组<shiro:hasPermission name="sys:dict:edit">${not empty dict.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:dict:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="dict" action="${ctx}/sys/dict/save" method="post" class="form-horizontal" style="margin:0px;">
		<div id="fixedTop" class="breadcrumb form-search">
			<shiro:hasPermission name="sys:dict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<div style="margin-left: 5%;padding-top: 60px;">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">字典编码:</label>
			<div class="controls">
				<form:input path="dictCode" htmlEscape="false" maxlength="50" class="required abc"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">字典名称:</label>
			<div class="controls">
				<form:input path="dictName" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">App:</label>
			<div class="controls">
				<select name="appNo" id="appNo" style="width: 130px;">
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
				<form:select path="language">
					<form:options items="${fns:getDictItemListL('sys_language','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否启用:</label>
			<div class="controls">
				<%-- <form:select path="status" class="input-medium">
					<form:options items="${fns:getDictItemListL('yes_no','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select> --%>
				<form:radiobuttons path="status" items="${fns:getDictItemListL('yes_no','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		</div>
	</form:form>
</body>
</html>