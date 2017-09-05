<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规则配置</title>
	<meta name="decorator" content="default"/>
	<style>
		body{
			overflow-x: hidden;
		}
	</style>
	<script>
		$(document).ready(function() {
			$('.select2-container').width($('#ruleOrder').width() + 12 + 'px');
		})
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cfc/seqRule/">规则列表</a></li>
		<li class="active"><a href="#">规则查看</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="sysSeqRule" action="${ctx}/cfc/seqRule/save" method="post" class="form-horizontal">
        <div id="fixedTop" class="breadcrumb form-search">
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
		<sys:message content="${message}"/>
		<div style="margin-left: 5%;">
			<form:hidden path="id"/>

			<div class="control-group" style="padding-top:60px;">
				<label class="control-label">业务编码:</label>
				<div class="controls">
					<input type="text" readonly="readonly" value="${sysSeqRule.seqName}"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">规则类型:</label>
				<div class="controls">
					<form:select path="ruleCode" class="input-medium"  disabled="true">
						<form:options items="${fns:getDictItemListL('seq_rule_type','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
					</form:select>

					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">规则排序:</label>
				<div class="controls">
					<form:input path="ruleOrder" htmlEscape="false" readonly="true"  maxlength="5" class="required number" placeholder="请输入纯数字"/>
						<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">规则值:</label>
				<div class="controls">
					<form:input path="ruleValue" htmlEscape="false" readonly="true"  maxlength="50" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>

			<div class="control-group" id="paddingSideDiv">
				<label class="control-label">补齐方向:</label>
				<div class="controls">
					<form:select path="paddingSide" class="input-medium" disabled="true">
						<form:options items="${fns:getDictItemListL('padding_left_right','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"></span>
				</div>
			</div>

			<div class="control-group" id="paddingWideDiv">
				<label class="control-label">补齐宽度:</label>
				<div class="controls">
					<form:input path="paddingWide" htmlEscape="false" readonly="true" maxlength="10" class="number" placeholder="请输入纯数字"/>
					<span class="help-inline"></span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">创建时间:</label>
				<div class="controls">
					<input type="text" readonly="readonly" value="<fmt:formatDate value="${sysSeqRule.createDate}" pattern="yyyy-MM-dd"/>" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">最后修改时间:</label>
				<div class="controls">
					<input type="text" readonly="readonly" value="<fmt:formatDate value="${sysSeqRule.lastUpdateDate}" pattern="yyyy-MM-dd"/>" />
				</div>
			</div>


		</div>

	</form:form>
</body>
</html>