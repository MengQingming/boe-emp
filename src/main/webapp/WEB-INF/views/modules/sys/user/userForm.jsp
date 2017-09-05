<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
<%--
 	<script src="http://localhost/bundle.js"></script>
  	<link rel="stylesheet" href="http://localhost/bundle.css">
--%>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#inputForm").validate({
				rules: {
					userName: {remote: "${ctx}/sys/user/checkLoginName?userName=" + encodeURIComponent('${user.userName}')+"&id="+encodeURIComponent('${user.id}')}
				},
				messages: {
					userName: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
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
			$("#btnSubmit").click(function(){
				if($("#groupId").val()==""){
					top.$.jBox.tip("请选择组织机构", 'info');
					return false;
				}
				$("#inputForm").submit();
			});
		});
	</script>
    <script src="${ctxStatic}/scripts/pages/css-userForm.js"></script>
    <link rel="stylesheet" href="${ctxStatic}/styles/pages/css-userForm.css">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/user/list">用户列表</a></li>
		<li class="active"><a href="${ctx}/sys/user/form?id=${user.id}">用户<shiro:hasPermission name="sys:user:edit">${not empty user.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:user:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/save" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
			<shiro:hasPermission name="sys:user:edit"><input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/></shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group css-head">
			<label class="control-label">头像:</label>
			<div class="controls">
				<form:hidden id="nameImage" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">组织机构:</label>
			<div class="controls">
				<c:if test="${user.currentUser.admin}">
					<sys:treeselect id="group" name="group.id" value="${user.group.id}" labelName="group.groupName" labelValue="${user.group.groupName}"
					title="机构" url="/sys/group/treeData1" allowClear="true"/>
				</c:if>
				<c:if test="${!user.currentUser.admin}">
					<sys:treeselect id="group" name="group.id" value="${user.group.id}" labelName="group.groupName" labelValue="${user.group.groupName}"
					title="机构" url="/sys/group/treeData1?companyId=${user.currentUser.company.id}" allowClear="true"/>
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">员工编号:</label>
			<div class="controls">
				<form:input path="userNum" htmlEscape="false" maxlength="50" class="required specialCharFilter"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">登录名:</label>
			<div class="controls">
				<%-- <input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}"> --%>
				<form:input path="userName" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名:</label>
			<div class="controls">
				<form:input path="fullName" htmlEscape="false" maxlength="50" class="required specialCharFilter"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码:</label>
			<div class="controls">
				<input id="password" name="password" type="password" value="" maxlength="50" minlength="3" class="${empty user.id?'required':''}"/>
				<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
				<c:if test="${not empty user.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">确认密码:</label>
			<div class="controls">
				<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#password"/>
				<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="100" class="email"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电话:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="100" class="phone specialCharFilter"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机:</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="100" class="phone specialCharFilter"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">传真:</label>
			<div class="controls">
				<form:input path="fax" htmlEscape="false" maxlength="100" class="specialCharFilter phone"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">家庭所在城市:</label>
			<div class="controls">
				<sys:treeselect id="homeCityCode" name="homeCityCode"  value="${user.homeCityCode}" labelName="homeCityName" labelValue="${user.homeCityName}"
					title="区域" url="/sys/area/treeData1" extId="3" notAllowSelectParent="true" cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作所在城市:</label>
			<div class="controls">
				<sys:treeselect id="workingCityCode" name="workingCityCode"  value="${user.workingCityCode}" labelName="workingCityName" labelValue="${user.workingCityName}"
					title="区域" url="/sys/area/treeData1" extId="3" notAllowSelectParent="true" cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">派驻地城市:</label>
			<div class="controls">
				<sys:treeselect id="postedCityCode" name="postedCityCode"  value="${user.postedCityCode}" labelName="postedCityName" labelValue="${user.postedCityName}"
					title="区域" url="/sys/area/treeData1" extId="3" notAllowSelectParent="true" cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职级代码:</label>
			<div class="controls">
				<form:select path="rankCode">
					<form:options items="${fns:getDictItemListL('sys_user_level','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">岗位级别:</label>
			<div class="controls">
				<form:select path="positionCode">
					<form:options items="${fns:getDictItemListL('sys_user_position','fssc_rmbs')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户类型:</label>
			<div class="controls">
				<form:select path="userTypeCode">
					<form:options items="${fns:getDictItemListL('sys_user_category','fssc_rmbs')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">补助级别:</label>
			<div class="controls">
				<form:select path="allowanceLevelCode">
					<form:options items="${fns:getDictItemListL('sys_user_allowance_level','fssc_rmbs')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">语言:</label>
			<div class="controls">
				<form:select path="lang">
					<form:options items="${fns:getDictItemListL('sys_language','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label css-radio">是否允许登录:</label>
			<div class="controls">
				<form:radiobuttons path="status" items="${fns:getDictItemListL('yes_no','T')}" itemLabel="itemValue" itemValue="itemCode"  htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> “是”代表此账号允许登录，“否”则表示此账号不允许登录</span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">用户类型:</label>
			<div class="controls">
				<form:select path="userType" class="input-xlarge">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('sys_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label css-radio">用户角色:</label>
			<div class="controls css-controls">
				<form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="roleName" itemValue="id" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
	</form:form>
</body>
</html>