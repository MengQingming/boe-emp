<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
<!-- 	<script src="http://localhost/bundle.js"></script>
  	<link rel="stylesheet" href="http://localhost/bundle.css">  -->
	<style>
		body{
			overflow-x: hidden;
		}
		.css-head{
			padding-top: 60px;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/user/list">用户列表</a></li>
		<li class="active"><a href="javascript:void(0)">用户详情</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/save" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
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
					<sys:treeselect id="group" name="group.id" cssStyle="width:210px;" value="${user.group.id}" labelName="group.groupName" labelValue="${user.group.groupName}" 
					title="机构" url="/sys/group/treeData1" cssClass="input-small" allowClear="true"/>
				</c:if>
				<c:if test="${!user.currentUser.admin}">
					<sys:treeselect id="group" name="group.id" cssStyle="width:210px;" value="${user.group.id}" labelName="group.groupName" labelValue="${user.group.groupName}" 
					title="机构" url="/sys/group/treeData1?companyId=${user.currentUser.company.id}" cssClass="input-small" allowClear="true"/>
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">员工编号:</label>
			<div class="controls">
				<form:input path="userNum" htmlEscape="false" maxlength="50" class="required specialCharFilter" disabled="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">登录名:</label>
			<div class="controls">
				<%-- <input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}"> --%>
				<form:input path="userName" htmlEscape="false" maxlength="50" class="required userName specialCharFilter" disabled="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名:</label>
			<div class="controls">
				<form:input path="fullName" htmlEscape="false" maxlength="50" class="required specialCharFilter" disabled="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="100" class="email" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电话:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="100" class="phone specialCharFilter" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机:</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="100" class="phone specialCharFilter" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">传真:</label>
			<div class="controls">
				<form:input path="fax" htmlEscape="false" maxlength="100" class="specialCharFilter checkFax" disabled="true"/>
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
				<form:select path="rankCode" disabled="true">
					<form:options items="${fns:getDictItemListL('sys_user_level','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">岗位级别:</label>
			<div class="controls">
				<form:select path="positionCode" disabled="true">
					<form:options items="${fns:getDictItemListL('sys_user_position','fssc_rmbs')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户类型:</label>
			<div class="controls">
				<form:select path="userTypeCode" disabled="true">
					<form:options items="${fns:getDictItemListL('sys_user_category','fssc_rmbs')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">补助级别:</label>
			<div class="controls">
				<form:select path="allowanceLevelCode" disabled="true">
					<form:options items="${fns:getDictItemListL('sys_user_allowance_level','fssc_rmbs')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">补助级别:</label>
			<div class="controls">
				<form:select path="allowanceLevel">
					<form:options items="${fns:getDictItemListL('allowance_level','fssc_rmbs')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">语言:</label>
			<div class="controls">
				<form:select path="lang" disabled="true">
					<form:options items="${fns:getDictItemListL('sys_language','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否允许登录:</label>
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
			<label class="control-label">用户角色:</label>
			<div class="controls css-controls">
				<form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="roleName" itemValue="id" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge" disabled="true"/>
			</div>
		</div>
	</form:form>
</body>
</html>