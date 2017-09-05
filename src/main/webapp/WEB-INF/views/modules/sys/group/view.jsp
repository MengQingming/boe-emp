<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					groupNo: {
						remote: {
							url: "${ctx}/sys/group/validateGroup",
                        	type: "get",
                        	data: {
	                            groupNo: function () { return $("#groupNo").val(); },
	                            companyId:function(){return $("#companyId").val();},
	                            id:'${group.id}'
                        	}
						}
						/* "${ctx}/sys/group/validateGroup?groupNo=" + encodeURIComponent('${group.groupNo}') +"&id="+encodeURIComponent('${group.id}')+"&companyId="+encodeURIComponent('${group.companyId}') */
					}
				},
				messages: {
					groupNo: {remote: "该公司组织机构编号已存在"}
				},
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
			$('.select2-container').width($('#companyName').width() + 12 + 'px');
		});
	</script>
	<style>
		body{
			overflow-x: hidden;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/group/list?id=${group.parent.id}">机构列表</a></li>
		<li class="active"><a href="${ctx}/sys/group/form?id=${group.id}&query=details">机构详情</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="group" action="${ctx}/sys/group/save" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<div style="padding-top:60px;">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">上级机构:</label>
			<div class="controls">
					<form:input path="parent.groupName" htmlEscape="false" maxlength="64" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属区域:</label>
			<div class="controls">
				<form:input path="area.name" htmlEscape="false" maxlength="64" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属公司:</label>
			<div class="controls">
				<form:input path="companyName" htmlEscape="false" maxlength="64" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构名称:</label>
			<div class="controls">
				<form:input path="groupName" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构简称:</label>
			<div class="controls">
				<form:input path="groupShortName" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构编码:</label>
			<div class="controls">
				<form:input path="groupNo" id="groupNo" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构全路径:</label>
			<div class="controls">
				<form:input path="groupPath" htmlEscape="false" maxlength="50" readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构类型:</label>
			<div class="controls">
				<form:select path="groupType" class="input-medium" disabled="true">
					<form:options items="${fns:getDictItemListL('sys_group_type','F')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构级别:</label>
			<div class="controls">
				<form:select path="groupLayer" class="input-medium" disabled="true">
					<form:options items="${fns:getDictItemListL('sys_group_grade','F')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否启用:</label>
			<div class="controls">
				<form:radiobuttons path="status" items="${fns:getDictItemListL('yes_no','T')}" itemLabel="itemValue" itemValue="itemCode" disabled="true" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间:</label>
			<div class="controls">
				<input type="text" readonly="readonly" value="<fmt:formatDate value="${group.creationDate}" pattern="yyyy-MM-dd"/>" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改时间:</label>
			<div class="controls">
				<input type="text" readonly="readonly" value="<fmt:formatDate value="${group.lastUpdateDate}" pattern="yyyy-MM-dd"/>" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" disabled="true" class="input-xlarge"/>
			</div>
		</div>
		</div>
	</form:form>
</body>
</html>