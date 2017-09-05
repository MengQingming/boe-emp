<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区域管理</title>
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
	<style>
		body{
			overflow-x: hidden;
		}
		.css-top{
			padding-top: 60px;
		}
	</style>
</head>
<body>
<ul class="nav nav-tabs">
	<li><a href="${ctx}/sys/area/list?id=${area.parent.id}">区域列表</a></li>
	<li class="active">
		<c:choose>
			<c:when test="${not empty area.id }">
				<a>
					<shiro:hasPermission name="sys:area:view">区域详情</shiro:hasPermission>
				</a>
			</c:when>
			<c:otherwise>
				<a href="form?id=${area.id}&parent.id=${area.parent.id}">
					<shiro:hasPermission name="sys:area:edit">区域添加</shiro:hasPermission>
				</a>
			</c:otherwise>
		</c:choose>
		<%-- <a href="form?id=${area.id}&parent.id=${area.parent.id}">区域
			<shiro:hasPermission name="sys:area:edit">${not empty area.id?'修改':'添加'}</shiro:hasPermission>
			<shiro:lacksPermission name="sys:area:edit">查看</shiro:lacksPermission> </a> --%>

	</li>
</ul><br/>
<form:form id="inputForm" modelAttribute="area" action="${ctx}/sys/area/save" method="post" class="form-horizontal">
	<div id="fixedTop" class="breadcrumb form-search">
			<%-- <shiro:hasPermission name="sys:dict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission> --%>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
	<form:hidden path="id"/>
	<sys:message content="${message}"/>
	<div class="css-top"></div>
	<div class="control-group">
		<label class="control-label">上级区域:</label>
		<div class="controls">
			<sys:treeselect id="area" name="parent.id"  value="${area.parent.id}" disabled="true" labelName="parent.name" labelValue="${area.parent.name}"
							title="区域" url="/sys/area/treeData1" extId="${area.id }" cssClass="" allowClear="true"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">区域名称:</label>
		<div class="controls">
			<form:input path="name" htmlEscape="false" maxlength="50" disabled="true" class="required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">区域编号:</label>
		<div class="controls">
			<form:input path="code" htmlEscape="false" maxlength="50" disabled="true" class="required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">排序:</label>
		<div class="controls">
			<form:input path="displayOrder" htmlEscape="false" maxlength="50" disabled="true" class="required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">区域类型:</label>
		<div class="controls">
			<form:select path="type"  disabled="true" class="input-xlarge required" style="width:220px">
				<form:options items="${fns:getDictItemListL('sys_area_type','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">城市级别:</label>
		<div class="controls">
			<form:select path="level" class="input-xlarge required" disabled="true" style="width:220px" >
				<form:options items="${fns:getDictItemListL('sys_area_level','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">城市分类:</label>
		<div class="controls">
			<form:select path="category" class="input-xlarge required" disabled="true" style="width:220px">
				<form:options items="${fns:getDictItemListL('sys_area_category','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">区域自定义标签:</label>
		<div class="controls">
			<form:input path="areaTag" htmlEscape="false" maxlength="50" disabled="true" class="required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">是否启用:</label>
		<div class="controls">
			<form:radiobuttons path="status"
							   items="${fns:getDictItemListL('yes_no','T')}"
							   itemLabel="itemValue" itemValue="itemCode" disabled="true" htmlEscape="false"
							   class="required" />
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">备注:</label>
		<div class="controls">
			<form:textarea path="remarks" htmlEscape="false" style="width:220px" rows="3" maxlength="200" disabled="true" class="input-xlarge"/>
		</div>
	</div>
</form:form>
</body>
</html>