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
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/area/list?id=${group.parent.id}&parentIds=${group.parentIds}">机构详情</a></li>
		<li><a href="${ctx}/sys/area/form?id=${group.id}&parent.id=${group.parent.id}">机构<shiro:hasPermission name="sys:area:edit">${not empty group.id?'添加':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:group:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="area" action="${ctx}/sys/area/save" method="post" class="form-horizontal" style="margin:0px;">
		<div style="position:fixed;width: 100%;z-index:999" class="breadcrumb form-search">
			<shiro:hasPermission name="sys:area:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<div style="margin-left: 5%;">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
<!-- 		<div class="control-group" style="padding-top:60px;"> -->
<!-- 			<label class="control-label">上级机构:</label> -->
<!-- 			<div class="controls"> -->
<!--                 <sys:treeselect id="group" name="parent.id" value="${group.parent.id}" labelName="parent.groupName" labelValue="${group.parent.groupName}" -->
<!-- 					title="机构" url="/sys/group/treeData1" extId="${group.id}" cssClass="" allowClear="${group.currentUser.admin}" disabled="disabled" /> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">归属区域:</label> -->
<!-- 			<div class="controls"> -->
<!--                 <sys:treeselect id="area" name="area.id" value="${group.area.id}" labelName="area.name" labelValue="${group.area.name}" -->
<!-- 					title="区域" url="/sys/area/treeData" cssClass="required"/> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">所属公司:</label> -->
<!-- 			<div class="controls"> -->
<!-- 				<form:select id="companyId" path="companyId" style = "width:266px;" disabled="true"> -->
<!-- 					<form:options items="${fns:getAllCompany()}" itemLabel="companyName" itemValue="id" htmlEscape="false"/> -->
<!-- 				</form:select> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">机构名称:</label> -->
<!-- 			<div class="controls"> -->
<!-- 				<form:input path="groupName" htmlEscape="false" maxlength="50" class="required"/> -->
<!-- 				<span class="help-inline"><font color="red">*</font> </span> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">机构简称:</label> -->
<!-- 			<div class="controls"> -->
<!-- 				<form:input path="groupShortName" htmlEscape="false" maxlength="50" class="required"/> -->
<!-- 				<span class="help-inline"><font color="red">*</font> </span> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">机构编码:</label> -->
<!-- 			<div class="controls"> -->
<!-- 				<form:input path="groupNo" htmlEscape="false" maxlength="50"/> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">机构全路径:</label> -->
<!-- 			<div class="controls"> -->
<!-- 				<form:input path="groupPath" htmlEscape="false" maxlength="50" readonly="true"/> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">机构类型:</label> -->
<!-- 			<div class="controls"> -->
<!-- 				<form:select path="groupType" class="input-medium"> -->
<!-- 					<form:options items="${fns:getDictItemListL(s_appNo,'sys_group_type',language)}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/> -->
<!-- 				</form:select> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">机构级别:</label> -->
<!-- 			<div class="controls"> -->
<!-- 				<form:select path="groupLayer" class="input-medium"> -->
<!-- 					<form:options items="${fns:getDictItemListL(s_appNo,'sys_group_grade',language)}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/> -->
<!-- 				</form:select> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">是否可用:</label> -->
<!-- 			<div class="controls"> -->
<!-- 				<form:select path="status" class="input-medium"> -->
<!-- 					<form:options items="${fns:getDictItemListL(s_appNo,'yes_no',language)}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/> -->
<!-- 				</form:select> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">备注:</label> -->
<!-- 			<div class="controls"> -->
<!-- 				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		</div> -->
	</form:form>
</body>
</html>