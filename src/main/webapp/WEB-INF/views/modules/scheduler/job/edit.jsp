<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调度任务配置</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/scripts/components/selectController.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/styles/pages/css-job.css">
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
			initOuSelect();
			changeBillType();
			select2Controller('#jobName');
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/scheduler/job/">调度任务列表</a></li>
		<li class="active"><a href="${ctx}/scheduler/job/form">${not empty schedulerJob.id?'调度任务修改':'调度任务添加' }</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="schedulerJob" action="${ctx}/scheduler/job/save" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
			<shiro:hasPermission name="scheduler:job:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		
		<div style="margin-left: 5%;padding-top: 60px;">
			<sys:message content="${message}"/>
			<form:hidden path="id" />
			<div class="control-group">
				<label class="control-label">公司:</label>
				<div class="controls">
					<form:select id="company_id" path="companyId">
						<form:options items="${fns:getAllCompany()}" itemLabel="companyName" itemValue="id" htmlEscape="false" />
					</form:select>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">App名称:</label>
				<div class="controls">
					<form:select id="appId" path="appId">
						<form:options items="${fns:getAllApp()}" itemLabel="appName" itemValue="id" htmlEscape="false" />
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">任务名称:</label>
				<div class="controls">
					<form:input path="jobName" htmlEscape="false" maxlength="150" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">任务编码:</label>
				<div class="controls">
					<form:input path="code" htmlEscape="false" maxlength="50" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">任务IP:</label>
				<div class="controls">
					<form:input path="ip" htmlEscape="false" maxlength="50" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">任务端口:</label>
				<div class="controls">
					<form:input path="port" htmlEscape="false" maxlength="50" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">任务地址:</label>
				<div class="controls">
					<form:input path="jobUrl" htmlEscape="false" maxlength="50" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div> 
			<div class="control-group">
				<label class="control-label">任务参数:</label>
				<div class="controls">
					<form:input path="jobParams" htmlEscape="false" maxlength="50" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div> 
			<div class="control-group">
				<label class="control-label">是否启用:</label>
				<div class="controls">
					<form:radiobuttons path="status" items="${fns:getDictItemListL('yes_no','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>