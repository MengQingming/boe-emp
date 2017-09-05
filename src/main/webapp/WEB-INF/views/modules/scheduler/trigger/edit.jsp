<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调度任务触发器</title>
	<meta name="decorator" content="default"/>
<%--	<script src="http://localhost/bundle.js"></script>
	<link rel="stylesheet" href="http://localhost/bundle.css">--%>
	<script src="${ctxStatic}/scripts/pages/css-trigger.js"></script>
    <link rel="stylesheet" href="${ctxStatic}/styles/pages/css-trigger.css">
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
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/scheduler/trigger/">调度任务触发器列表</a></li>
		<li class="active"><a href="${ctx}/scheduler/trigger/form">${not empty schedulerTrigger.id?'调度任务触发器修改':'调度任务触发器添加' }</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="schedulerTrigger" action="${ctx}/scheduler/trigger/save" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
			<shiro:hasPermission name="scheduler:trigger:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		
		<div style="margin-left: 5%;padding-top: 60px;">
			<sys:message content="${message}"/>
			<form:hidden path="id" />
			<div class="control-group">
				<label class="control-label">任务:</label>
				<div class="controls">
					<form:select id="job_id" path="jobId" class="input-medium">
						<form:options items="${fns:getAllSchedulerJob()}" itemLabel="jobName" itemValue="id" htmlEscape="false" />
					</form:select>                 
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">触发类型:</label>
				<div class="controls">
					<form:input path="triggerType" htmlEscape="false" maxlength="50" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">触发参数:</label>
				<div class="controls">
					<form:input path="triggerParam" htmlEscape="false" maxlength="50" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			
		</div>
	</form:form>
</body>
</html>