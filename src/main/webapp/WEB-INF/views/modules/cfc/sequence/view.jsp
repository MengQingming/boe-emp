<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务编码</title>
	<meta name="decorator" content="default"/>
	<style>
		body{
			overflow-x: hidden;
		}
	</style>
	<script>
		$(document).ready(function() {
			$('.select2-container').width($('#seqName').width() + 12 + 'px');
		})
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cfc/seq/">编码列表</a></li>
		<li class="active"><a href="#">编码查看</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="sysSeq" action="${ctx}/cfc/seq/save" method="post" class="form-horizontal">
        <div id="fixedTop" class="breadcrumb form-search">
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
		<sys:message content="${message}"/>
		<div style="margin-left: 5%;">
			<form:hidden path="id"/>
			<div class="control-group" style="padding-top:60px;">
				<label class="control-label">业务编码名称:</label>
				<div class="controls">
					<form:input path="seqName" htmlEscape="false" readonly="true"  maxlength="50" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">业务编码编号:</label>
				<div class="controls">
					<form:input path="seqCode" htmlEscape="false" readonly="true"  maxlength="50" class="required specialCharFilter"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">分隔符:</label>
				<div class="controls">
					<form:input path="delimiter" readonly="true"  htmlEscape="false" maxlength="50"/>
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">步长:</label>
				<div class="controls">
					<form:input path="step" htmlEscape="false"  readonly="true"  maxlength="50" class="required specialCharFilter number"/>
					<span class="help-inline"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">重置规则:</label>
				<div class="controls">
					<form:select path="resetRule" class="input-medium" disabled="true" >
						<form:options items="${fns:getDictItemListL('seq_reset_rule','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">创建时间:</label>
				<div class="controls">
					<input type="text" readonly="readonly" value="<fmt:formatDate value="${sysSeq.createDate}" pattern="yyyy-MM-dd"/>" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">最后修改时间:</label>
				<div class="controls">
					<input type="text" readonly="readonly" value="<fmt:formatDate value="${sysSeq.lastUpdateDate}" pattern="yyyy-MM-dd"/>" />
				</div>
			</div>
		</div>

	</form:form>
</body>
</html>