<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调度任务配置</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/scheduler/job/">调度任务列表</a></li>
		<li class="active"><a href="${ctx}/scheduler/job/form?id=${schedulerJob.id}">${not empty schedulerJob.id?'调度任务修改':'调度任务添加' }</a></li>
	</ul>
	<form:form id="jobForm" modelAttribute="schedulerJob" action="#" method="post" class="form-horizontal">
		<form:hidden path="id" />
		
		<div class="breadcrumb form-search">
			<shiro:hasPermission name="scheduler:job:edit">
				<input class="btn btn-primary" type="button" value="保 存" onclick="sumbitJob()"/>&nbsp;
			</shiro:hasPermission>
			<input class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		
		<fieldset class="bs-docs-example2 css-pMessage">
			<h4>调度任务信息<span style="margin-left: 40px;font-size: 10px;color: red;">修改或保存后，请做手动调度一次，测试是否成功调用。</span></h4>

			<table>
				<tr>
					<td><label>任务名称：</label></td>
					<td>
						<div >
						<form:input path="jobName" htmlEscape="false" class="required"/><font color="red">*</font>
						</div>
					</td>
				</tr>
				<tr>
					<td><label>任务编码：</label></td>
					<td>
						<div >
							<form:input path="jobCode" htmlEscape="false" class="required"/><font color="red">*</font>
						</div>
					</td>
				</tr>
				<tr>
					<td><label>调度任务项目路径：</label></td>
					<td>
						<div >
							<form:input path="jobPath" htmlEscape="false" class="required"/><font color="red">*</font>
							<span style="font-size: 10px;color:red;">调度任务完整地址 例：com.tark.scheduler.scheduling.SapTest</span>
						</div>
					</td>
				</tr>
				<tr>
					<td><label>调度任务执行方法名：</label></td>
					<td>
						<div >
							<form:input path="jobMethod" htmlEscape="false" class="required"/><font color="red">*</font>
							<span style="font-size: 10px;color:red;">调度执行的方法,方法不带有参数 例：runTask</span>
						</div>
					</td>
				</tr>
				<tr>
					<td><label>IP白名单：</label></td>
					<td>
						<div >
							<form:input path="jobIp" htmlEscape="false" class="required"/><font color="red">*</font>
							<span style="font-size: 10px;color:red;">用“,”分割，为空则不限制 例：10.9.17.169,199.99.92.99</span>
						</div>
					</td>
				</tr>
				<tr>
					<td><label>授权服务：</label></td>
					<td>
						<div >
							<form:input path="jobService" htmlEscape="false" class="required"/><font color="red">*</font>
							<span style="font-size: 10px;color:red;">用“,”分割，为空则不限制 例：service-1,service-2</span>
						</div>
					</td>
				</tr>
				<tr>
					<td><label>任务描述：</label></td>
					<td>
						<div >
							<form:input path="jobMessage" htmlEscape="false"/>
						</div>
					</td>
				</tr>
				<tr>
					<td><label>调度任务状态：</label></td>
					<td>
						<form:radiobuttons path="jobStatus" items="${fns:getDictItemListL('yes_no','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
					</td>
				</tr>
			</table>
		</fieldset>
	</form:form>
	<script type="text/javascript">
	function sumbitJob() {
		loading('处理中，请稍等...');
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"${ctx}/scheduler/job/save",
	        data:$('#jobForm').serialize(),
	        async: false,
	        success: function(data) {
	        	closeLoading();
	        	if(!data.success)
		 			$.jBox.alert(data.error);
				else {
					location.href = "${ctx}/scheduler/job";
				}
	        }
	    });
	}
	</script>
</body>
</html>