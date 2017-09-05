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
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/i18n/resource/">国际化配置列表</a></li>
		<li class="active"><a href="${ctx}/i18n/resource/form">国际化配置详情</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="resource" action="${ctx}/i18n/resource/save" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
			<%-- <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission> --%>
			<shiro:hasPermission name="i18n:resource:view">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</shiro:hasPermission>
		</div>
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
			<div class="control-group" style="padding-top:60px;">
				<label class="control-label">公司名称:</label>
				<div class="controls">
					<form:hidden id="companyName" path="companyName"/>
					<form:select id="companyId" path="companyId" style="width:220px;" disabled="true" onchange="queryCompany();">
						<form:option value=""></form:option>
						<form:options items="${fns:getAllCompany()}" itemLabel="companyName" itemValue="id" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">公司编号:</label>
				<div class="controls">
					<form:input path="companyNo" value="${resource.companyNo}" readonly="true"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">语言:</label>
				<div class="controls">
					<form:select path="language" style="width:220px;" disabled="true">
						<form:options items="${fns:getDictItemListL('sys_language','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">资源类型:</label>
				<div class="controls">
					<form:select path="type" style="width:220px;" disabled="true">
						<form:options items="${fns:getDictItemListL('sys_i18n_type','F')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">资源编号:</label>
				<div class="controls">
					<form:input path="code" value="${resource.code}" disabled="true" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">资源值:</label>
				<div class="controls">
					<form:input path="value" value="${resource.value}" disabled="true" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">备注:</label>
				<div class="controls">
					<form:textarea path="remarks" disabled="true" value="${resource.remarks}"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			
	</form:form>
</body>
</html>