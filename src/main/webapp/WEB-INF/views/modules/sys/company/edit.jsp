<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公司管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate({
				rules: {
					companyNo: {remote: "${ctx}/sys/company/validateCompany?companyNo=" + encodeURIComponent('${company.companyNo}') +"&id="+encodeURIComponent('${company.id}')}
				},
				messages: {
					companyNo: {remote: "该公司编号已存在"}
				},
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
		});
	</script>
	<style>
		body{overflow-x: hidden}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/company/">公司列表</a></li>
		<li class="active"><a href="${ctx}/sys/company/form?id=${company.id}">公司<shiro:hasPermission name="sys:company:edit">${not empty company.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:company:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="company" action="${ctx}/sys/company/save" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
			<shiro:hasPermission name="sys:company:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<div style="margin-left: 5%;padding-top: 50px;">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">公司编号:</label>
			<div class="controls">
				<form:input path="companyNo" htmlEscape="false" maxlength="50" class="required abc"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司名称:</label>
			<div class="controls">
				<form:input path="companyName" htmlEscape="false" maxlength="50" class="required specialCharFilter"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司简称:</label>
			<div class="controls">
				<form:input path="companyShortName" htmlEscape="false" maxlength="50" class="specialCharFilter"/>
			</div>
			<span class="help-inline"></span>
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