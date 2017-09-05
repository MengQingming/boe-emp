<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统参数管理</title>
	<meta name="decorator" content="default"/>
    <script>
        $(document).ready(function() {
            $('.select2-container').width($('#companyName').width() + 12 + 'px');
        })
    </script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/parameter/sysParameter/">系统参数列表</a></li>
		<li class="active"><a href="${ctx}/parameter/sysParameter/form?id=${sysParameter.id}">系统参数<shiro:hasPermission name="parameter:sysParameter:edit">${not empty sysParameter.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="parameter:sysParameter:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysParameter" action="${ctx}/parameter/sysParameter/save" method="post" class="form-horizontal">
		<div style="position:fixed;top:50px;width: 100%;z-index: 999" class="breadcrumb form-search">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<form:hidden path="companyNo" id="companyNo" htmlEscape="false" maxlength="64" class="input-xlarge" readonly="true"/>
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group"  style="margin-top: 50px;">
			<label class="control-label">App编号：</label>
			<div class="controls">
				<%-- <form:input path="appNo" htmlEscape="false" maxlength="64" class="input-xlarge required"/> --%>
				<select id="appNo" name="appNo" class="input-xlarge" onchange="()" disabled="disabled">
					<c:forEach items="${apps}" var="app">
						<option value="${app.appNo}"<c:if test='${sysParameter.appNo == app.appNo}'>selected = "selected"</c:if>>${app.appName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">单位ID：</label>
			<div class="controls">
				<form:input path="companyId" htmlEscape="false" maxlength="64" class="input-xlarge"/>
			</div>
		</div> --%>
		<div class="control-group">
			   <label class="control-label">所属公司：</label>
			  <div class="controls">
					<c:if test="${sysParameter.currentUser.admin}">
						<form:select id="companyId" path="companyId" class="input-xlarge">
							<form:options items="${fns:getAllCompany()}" itemLabel="companyName" class="input-xlarge" itemValue="id" htmlEscape="false"/>
						</form:select>
					</c:if>
					<c:if test="${!sysParameter.currentUser.admin}">
						<form:input type="text" path="companyName" readonly="true" value="${sysParameter.currentUser.company.companyName}" class="input-xlarge"/>
						<form:input type="text" path="companyId"  cssStyle="display:none;"  value="${sysParameter.currentUser.company.id}" />
						<form:input type="text" path="companyNo"  cssStyle="display:none;" value="${sysParameter.currentUser.company.companyNo}" />
					</c:if>
				</div>
		</div>
		<div class="control-group">
			<label class="control-label">参数分组：</label>
			<div class="controls">
				<form:select  path="paramGroup" class="input-xlarge required" disabled="true">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictItemListL('sys_parameter_type','F')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参数名称：</label>
			<div class="controls">
				<form:input path="paramName" htmlEscape="false" maxlength="64" class="input-xlarge required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参数值：</label>
			<div class="controls">
				<form:input path="paramValue" htmlEscape="false" maxlength="64" class="input-xlarge required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参数格式：</label>
			<div class="controls">
				<form:input path="paramFormat" htmlEscape="false" maxlength="64" class="input-xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否可用：</label>
			<div class="controls">
				<form:radiobuttons disabled="true" path="status" items="${fns:getDictItemListL('yes_no','T')}" itemLabel="itemValue" itemValue="itemCode"  htmlEscape="false" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="64" class="input-xxlarge required" readonly="true"/>
			</div>
		</div>
		
	</form:form>
</body>
</html>