<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/scripts/components/selectController.js"></script>
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
			select2Controller('#companyName');
		});
	</script>
	<link rel="stylesheet" href="${ctxStatic}/styles/pages/css-group.css">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/group/list?id=${group.parent.id}">机构列表</a></li>
		<li class="active"><a href="${ctx}/sys/group/form?id=${group.id}&parent.id=${group.parent.id}">机构<shiro:hasPermission name="sys:group:edit">${not empty group.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:group:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="group" action="${ctx}/sys/group/save" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
			<shiro:hasPermission name="sys:group:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<div style="margin-left: 5%;padding-top:60px;">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">上级机构:</label>
			<div class="controls">
                <sys:treeselect id="group" name="parent.id" value="${group.parent.id}" labelName="parent.groupName" labelValue="${group.parent.groupName}"
					title="机构" url="/sys/group/treeData1" extId="${group.id}" allowClear="${group.currentUser.admin}" cssClass=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属区域:</label>
			<div class="controls">
                <sys:treeselect id="area" name="area.id" value="${group.area.id}" labelName="area.name" labelValue="${group.area.name}"
					title="区域" url="/sys/area/treeData1" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属公司:</label>
			<div class="controls">
				<c:if test="${group.currentUser.admin}">
					<form:select id="companyId" path="companyId" style = "width:266px;">
						<form:options items="${fns:getAllCompany()}" itemLabel="companyName" itemValue="id" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!group.currentUser.admin}">
					<form:input type="text" path="companyName" readonly="true" value="${group.currentUser.company.companyName}" />
					<form:input type="text" path="companyId" cssStyle="display:none;" value="${group.currentUser.company.id}" />
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构名称:</label>
			<div class="controls">
				<form:input path="groupName" htmlEscape="false" maxlength="50" class="required specialCharFilter"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构简称:</label>
			<div class="controls">
				<form:input path="groupShortName" htmlEscape="false" maxlength="50" class="required specialCharFilter"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构编码:</label>
			<div class="controls">
				<form:input path="groupNo" id="groupNo" htmlEscape="false" maxlength="50" class="required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
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
				<form:select path="groupType">
					<form:options items="${fns:getDictItemListL('sys_group_type','F')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构级别:</label>
			<div class="controls">
				<form:select path="groupLayer">
					<form:options items="${fns:getDictItemListL('sys_group_grade','F')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否启用:</label>
			<div class="controls">
				<%-- <form:select path="status" class="input-medium">
					<form:options items="${fns:getDictItemListL('yes_no','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select> --%>
				<form:radiobuttons path="status" items="${fns:getDictItemListL('yes_no','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		</div>
	</form:form>
</body>
</html>