<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调度任务触发器</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/scripts/pages/cronValidate.js" ></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/scheduler/trigger/">调度任务触发器列表</a></li>
		<li class="active"><a href="${ctx}/scheduler/trigger/form?id=${schedulerTrigger.id}">${not empty schedulerTrigger.id?'调度任务触发器修改':'调度任务触发器添加' }</a></li>
	</ul>
	<form:form id="triggerForm" modelAttribute="schedulerTrigger" action="#" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="triggerSleep" id="triggerSleep"/>
		<div class="breadcrumb form-search">
			<shiro:hasPermission name="scheduler:trigger:edit"><input class="btn btn-primary" type="button" value="保 存" onclick="sumbitTrigger()"/>&nbsp;</shiro:hasPermission>
			<input class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		
		<fieldset class="bs-docs-example2 css-pMessage">
			<h4>基本信息 <span style="margin-left: 40px;font-size: 10px;color: red;">配置完成后将会刷新任务调度时间</span></h4>

			<table>
				<tr>
					<td><label>任务名称：</label></td>
					<td>
						<form:select path="jobId" style="width:220px;">
							<form:options items="${jobs}" itemLabel="jobName" itemValue="id" htmlEscape="false" />
						</form:select>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td><label>触发类型：</label></td>
					<td>
						<form:select path="triggerType" style="width:220px;" onclick="typeChange(this[selectedIndex].value)">
							<form:options items="${triggerTypes}"/>
						</form:select>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td><label>触发器状态：</label></td>
					<td>
						<form:radiobuttons path="triggerStatus" items="${fns:getDictItemListL('yes_no','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
						<font color="red">*</font>
					</td>
				</tr>
				<tr class="type_hidden" id="forCron" style="display:${schedulerTrigger.triggerType == 'forCron'?'table-row':'none'}">
					<td><label>cron表达式：</label></td>
					<td>
						<div >
							<form:input path="triggerCron" htmlEscape="false"/>
						</div>
					</td>
				</tr>
				<tr class="type_hidden" id="forDate" style="display:${schedulerTrigger.triggerType == 'forDate'?'table-row':'none'}">
					<td><label>固定日期：</label></td>
					<td>
						<div >
							<input name="triggerDate" type="text" readonly="readonly" maxlength="20" class="Wdate"
							value="<fmt:formatDate value="${schedulerTrigger.triggerDate}" pattern="yyyy-MM-dd hh:mm:ss"/>"
							onclick="WdatePicker({maxDate:new Date(),dateFmt:'yyyy-MM-dd hh:mm:ss',isShowClear:false});"/>
						</div>
					</td>
				</tr>
				<tr class="type_hidden" id="forSleep" style="display:${schedulerTrigger.triggerType == 'forSleep'?'table-row':'none'}">
					<td><label>轮询周期：</label></td>
					<td>
						<div >
							<input type="text" value="${empty schedulerTrigger.triggerSleep?0:schedulerTrigger.triggerSleep/1000}">
							<select id="_time">
								<option value="shi">时</option>
								<option value="fen">分</option>
								<option value="miao" selected="selected">秒</option>
							</select>
						</div>
					</td>
				</tr>
			</table>
		</fieldset>
	</form:form>
	<script type="text/javascript">
		function typeChange(val) {
			$('.type_hidden').hide();
			$('#'+ val).show();
		}
		
		function _time() {
			switch ($('#_time').val()){
				case 'shi':
					return $('#forSleep input').val()*3600000;
				case 'fen':
					return $('#forSleep input').val()*60000;
				case 'miao':
					return $('#forSleep input').val()*1000;
			}
		}
		
	function sumbitTrigger() {
		$("#triggerSleep").val(_time());
		if ($("#triggerType").val() == 'forCron')
			if(!CronExpressionValidator.validateCronExpression($("#triggerCron").val())){
				$.jBox.alert("Cron格式不正确 例:0 0/1 * * * ?");
				return null;
			}
		loading('处理中，请稍等...');
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"${ctx}/scheduler/trigger/save",
	        data:$('#triggerForm').serialize(),
	        async: false,
	        success: function(data) {
	        	closeLoading();
	        	if(!data.success)
		 			$.jBox.alert(data.error);
				else {
					location.href = "${ctx}/scheduler/trigger";
				}
	        }
	    });
	}
	</script>
</body>
</html>