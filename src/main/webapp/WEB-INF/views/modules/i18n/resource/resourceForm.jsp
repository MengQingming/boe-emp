<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>国际化配置管理</title>
	<meta name="decorator" content="default"/>
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
		});
		//查询公司编号
		function queryCompany(){
			var id = $("#companyId").val();
			$.ajax({
				url:"${ctx}/sys/company/queryCompanyNo",
				data:{
					id:id
				},
				type:"post",
				datatype:"json",
				success:function(data){
					var json = $.parseJSON(data);
					var companyNo = json.companyNo;
					var companyName = json.companyName;
					$("#companyNo").val(companyNo);
					$("#companyName").val(companyName);
				}
			});
		}
	</script>
	<style>
		body{overflow-x: hidden}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/i18n/resource/">国际化配置列表</a></li>
		<li class="active"><a href="${ctx}/i18n/resource/form">${not empty resource.id?'修改国际化配置':'新增国际化配置' }</a></li>
	</ul>
	
	<form:form id="inputForm" modelAttribute="resource" action="${ctx}/i18n/resource/save" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
			<shiro:hasPermission name="i18n:resource:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
			<c:if test="${user.currentUser.admin }">
				<div class="control-group" style="padding-top:60px;">
					<label class="control-label"><m:message code="sysmgr-resource-companyName"/>:</label>
					<div class="controls">
						<form:hidden id="companyName" path="companyName"/>
						<form:select id="companyId" path="companyId" style="width:220px;" class="required specialCharFilter" onchange="queryCompany();">
							<form:option value=""></form:option>
							<form:options items="${fns:getAllCompany()}" itemLabel="companyName" itemValue="id" htmlEscape="false"/>
						</form:select>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><m:message code="sysmgr-resource-companyNo"/>:</label>
					<div class="controls">
						<form:input path="companyNo" value="${resource.companyNo}" readonly="true" class="required specialCharFilter"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
			</c:if>
			<c:if test="${!user.currentUser.admin }">
				<div class="control-group" style="padding-top:60px;">
					<label class="control-label"><m:message code="sysmgr-resource-companyName"/>:</label>
					<div class="controls">
						<form:hidden id="companyId" path="companyId" value="${user.currentUser.company.id }"/>
						<form:input id="companyName" path="companyName" readonly="true" value="${user.currentUser.company.companyName }"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
					</div>
					<div class="control-group">
						<label class="control-label"><m:message code="sysmgr-resource-companyNo"/>:</label>
						<div class="controls">
							<form:input path="companyNo" value="${user.currentUser.company.companyNo }" readonly="true" class="required specialCharFilter"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					</c:if>
			<div class="control-group">
				<label class="control-label"><m:message code="sysmgr-resource-language"/>:</label>
				<div class="controls">
					<form:select path="language" style="width:220px;" class="required specialCharFilter">
						<form:option value=""></form:option>
						<form:options items="${fns:getDictItemListL('sys_language','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><m:message code="sysmgr-resource-type"/>:</label>
				<div class="controls">
					<form:select path="type" style="width:220px;" class="required specialCharFilter">
						<form:option value=""></form:option>
						<form:options items="${fns:getDictItemListL('sys_i18n_type','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><m:message code="sysmgr-resource-code"/>:</label>
				<div class="controls">
					<form:input path="code" value="${resource.code}" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
					<span class="help-inline">编号规则：表单，appNo-表名,例如,承兑汇票台账：fssc-abm-bill。表单项：表名-字段名，例如，abm-bill-company-No</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><m:message code="sysmgr-resource-value"/>:</label>
				<div class="controls">
					<form:input path="value" value="${resource.value}" class="required specialCharFilter"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><m:message code="sysmgr-resource-remarks"/>:</label>
				<div class="controls">
					<form:textarea path="remarks" value="${resource.remarks}"/>
				</div>
			</div>
			
	</form:form>
</body>
</html>