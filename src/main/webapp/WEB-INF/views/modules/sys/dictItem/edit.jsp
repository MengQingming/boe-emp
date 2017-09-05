<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典项管理</title>
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
			select2Controller('#itemCode');
		});
	</script>
	<link rel="stylesheet" href="${ctxStatic}/styles/pages/css-dictItem.css">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/dictItem/">字典项列表</a></li>
		<li class="active"><a href="${ctx}/sys/dictItem/form?id=${dict.id}">字典项<shiro:hasPermission name="sys:dictItem:edit">${not empty dictItem.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:dictItem:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="dictItem" action="${ctx}/sys/dictItem/save" method="post" class="form-horizontal" style="margin:0px;">
		<div id="fixedTop" class="breadcrumb form-search">
			<shiro:hasPermission name="sys:dictItem:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<div style="margin-left: 5%;padding-top: 60px;">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">所属公司:</label>
			<div class="controls">
				<c:if test="${dictItem.currentUser.admin}">
					<form:select id="companyId" path="companyId" style = "width:266px;">
						<form:options items="${fns:getAllCompany()}" itemLabel="companyName" itemValue="id" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!dictItem.currentUser.admin}">
					<form:input type="text" path="companyName" readonly="true" value="${dictItem.currentUser.company.companyName}" />
					<form:input type="text" path="companyId" cssStyle="display:none;" value="${dictItem.currentUser.company.id}" />
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属字典组:</label>
			<div class="controls">
				<form:select path="dict.id" class="input-medium">
					<form:options items="${fns:getAllDict()}" itemLabel="dictName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">字典项编码:</label>
			<div class="controls">
				<form:input path="itemCode" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">字典项名称:</label>
			<div class="controls">
				<form:input path="itemValue" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<form:input path="displayOrder" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">语言</label>
			<div class="controls">
				<select name="language" id="language">
					<option value="zh_CN" selected="selected">中文</option>
					<option value="en_US">英文</option>
				</select>
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