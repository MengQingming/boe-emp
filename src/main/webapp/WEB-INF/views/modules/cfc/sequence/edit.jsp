<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务编码</title>
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
	</script>
<%--	<script src="http://localhost/bundle.js"></script>
  	<link rel="stylesheet" href="http://localhost/bundle.css">--%>
	<script src="${ctxStatic}/scripts/pages/css-sequence.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/styles/pages/css-sequence.css">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cfc/seq/">编码列表</a></li>
		<li class="active"><a href="${ctx}/cfc/seq/form">${not empty sysSeq.id?'编码修改':'编码新增' }</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="sysSeq" action="${ctx}/cfc/seq/save" method="post" class="form-horizontal">
        <div id="fixedTop" class="breadcrumb form-search">
            <shiro:hasPermission name="cfc:seq:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
		<sys:message content="${message}"/>
		<div style="margin-left: 5%;">
			<form:hidden path="id"/>
			<div class="control-group" style="padding-top:60px;">
				<label class="control-label">业务编码名称:</label>
				<div class="controls">
					<form:input path="seqName" htmlEscape="false" maxlength="50" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">业务编码编号:</label>
				<div class="controls">
					<form:input path="seqCode" htmlEscape="false" maxlength="50" class="required specialCharFilter"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">分隔符:</label>
				<div class="controls">
					<form:input path="delimiter" htmlEscape="false" maxlength="50"/>
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">步长:</label>
				<div class="controls">
					<form:input path="step" htmlEscape="false" maxlength="50" class="required specialCharFilter number"/>
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">重置规则:</label>
				<div class="controls">
					<form:select path="resetRule" class="input-medium">
						<form:options items="${fns:getDictItemListL('seq_reset_rule','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
					</form:select>
				</div>
			</div>
		</div>

	</form:form>
</body>
</html>