\<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>菜单管理</title>
<meta name="decorator" content="default" />
	<script>
		$(document).ready(function() {
			$('.select2-container').width($('#menuName').width() + 12 + 'px');
		})	</script>
	<style>
		body{
			overflow-x: hidden;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/menu/">菜单列表</a></li>
		<li class="active"><a href="javascript:void(0)">菜单详情</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="menu" action="${ctx}/sys/menu/save" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group" style="padding-top: 50px;">
			<label class="control-label">上级菜单:</label>
			<div class="controls">
				<sys:treeselect id="menu" name="parent.id" value="${menu.parent.id}"
					labelName="parent.name" labelValue="${menu.parent.menuName}"
					title="菜单" url="/sys/menu/treeData" extId="${menu.id}"
					cssClass="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称:</label>
			<div class="controls">
				<form:input path="menuName" htmlEscape="false" maxlength="50"
					class="required" disabled="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">菜单编号:</label>
			<div class="controls">
				<form:input path="menuNo" htmlEscape="false" maxlength="50"
					class="required" disabled="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">编号:</label>
			<div class="controls">
				<form:input path="menuNo" htmlEscape="false" maxlength="50"
					class="required input-xlarge" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型:</label>
			<div class="controls">
				<form:input path="menuType" htmlEscape="false" maxlength="50"
					class="required input-xlarge" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">路径:</label>
			<div class="controls">
				<form:input path="menuPath" htmlEscape="false" maxlength="50"
					class="required input-xlarge" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">App:</label>
			<div class="controls">
				<form:select id="appNo" path="appNo">
					<form:option value="" label="" />
					<form:options items="${fns:getAllApp()}" itemLabel="appName"
						itemValue="appNo" htmlEscape="false" />
				</form:select>

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">链接:</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="2000"
					disabled="true"/>
				<span class="help-inline">点击菜单跳转的页面</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">目标:</label>
			<div class="controls">
				<form:input path="target" htmlEscape="false" maxlength="10"
					disabled="true"/>
				<span class="help-inline">链接地址打开的目标窗口，默认：mainFrame</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图标:</label>
			<div class="controls">
				<sys:iconselect id="icon" name="icon" value="${menu.icon}" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<form:input path="displayOrder" htmlEscape="false" maxlength="50"
					class="required digits" disabled="true"/>
				<span class="help-inline">排列顺序，升序。</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">可见:</label>
			<div class="controls">
				<form:radiobuttons path="displayFlag"
					items="${fns:getDictItemListL('show_hide','T')}"
					itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"
					class="required" />
				<span class="help-inline">该菜单或操作是否显示到系统菜单中</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">权限标识:</label>
			<div class="controls">
				<form:input path="permission" htmlEscape="false" maxlength="100"
					class="input-xxlarge" disabled="true"/>
				<span class="help-inline">控制器中定义的权限标识，如：@RequiresPermissions("权限标识") 提示! 查看:sys:menu:view 修改:sys:menu:eidt</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3"
					maxlength="200" class="input-xxlarge" disabled="true"/>
			</div>
		</div>
	</form:form>
</body>
</html>