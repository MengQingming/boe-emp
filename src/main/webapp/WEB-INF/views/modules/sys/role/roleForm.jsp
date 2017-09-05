<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>角色管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					name: {remote: "${ctx}/sys/role/checkName?oldName=" + encodeURIComponent("${role.roleName}")},
				},
				messages: {
					name: {remote: "角色名已存在"},
				},
				submitHandler: function(form){
					var ids = [], nodes = tree.getCheckedNodes(true);
					for(var i=0; i<nodes.length; i++) {
						ids.push(nodes[i].id);
					}
					$("#menuIds").val(ids);
					var ids2 = [], nodes2 = tree2.getCheckedNodes(true);
					for(var i=0; i<nodes2.length; i++) {
						ids2.push(nodes2[i].id);
					}
					$("#officeIds").val(ids2);
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

			var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:false},
					data:{simpleData:{enable:true}},callback:{beforeClick:function(id, node){
						tree.checkNode(node, !node.checked, true, true);
						return false;
					}}};
			
			// 用户-菜单
			var zNodes=[
					<c:forEach items="${menuList}" var="menu">{id:"${menu.id}", pId:"${not empty menu.parent.id?menu.parent.id:0}", name:"${not empty menu.parent.id?menu.menuName:'权限列表'}"},
		            </c:forEach>];
			// 初始化树结构
			var tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);
			// 不选择父节点
			tree.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
			// 默认选择节点
			var ids = "${role.menuIds}".split(",");
			for(var i=0; i<ids.length; i++) {
				var node = tree.getNodeByParam("id", ids[i]);
				try{tree.checkNode(node, true, false);}catch(e){}
			}
			// 默认展开全部节点
			tree.expandAll(true);
			
			// 用户-机构
			var zNodes2=[
					<c:forEach items="${groupList}" var="group">{id:"${group.id}", pId:"${not empty group.parent?group.parent.id:0}", name:"${group.groupName}"},
		            </c:forEach>];
			// 初始化树结构
			var tree2 = $.fn.zTree.init($("#groupTree"), setting, zNodes2);
			// 不选择父节点
			tree2.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
			// 默认选择节点
			var ids2 = "${role.groupIds}".split(",");
			for(var i=0; i<ids2.length; i++) {
				var node = tree2.getNodeByParam("id", ids2[i]);
				try{tree2.checkNode(node, true, false);}catch(e){}
			}
			// 默认展开全部节点
			tree2.expandAll(true);
			// 刷新（显示/隐藏）机构
			refreshOfficeTree();
			$("#dataScope").change(function(){
				refreshOfficeTree();
			});
			
			if($("#roleTypeSel").val()=="assignment"){
				$("#giveRigthsDiv").hide();
			}else{
				$("#giveRigthsDiv").show();
			}
			$("#roleTypeSel").change(function(){
				var rt = $(this).val();
				if("assignment"==rt){
					$("#giveRigthsDiv").hide();
				}else if("security-role"==rt){
					$("#giveRigthsDiv").show();
				}
			});
		});
		function refreshOfficeTree(){
			if($("#dataScope").val()==9){
				$("#groupTree").show();
			}else{
				$("#groupTree").hide();
			}
		}
	</script>
<%--	<script src="http://localhost/bundle.js"></script>
  	<link rel="stylesheet" href="http://localhost/bundle.css">--%>
	<script src="${ctxStatic}/scripts/pages/css-role.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/styles/pages/css-role.css">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/role/">角色列表</a></li>
		<li class="active"><a href="${ctx}/sys/role/form?id=${role.id}">角色<shiro:hasPermission name="sys:role:edit">${not empty role.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:role:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="role" action="${ctx}/sys/role/save" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
			<shiro:hasPermission name="sys:dict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group" style="padding-top: 50px;">
			<label class="control-label">公司名称:</label>
			<div class="controls">
				<c:if test="${role.currentUser.admin}">
					<form:select id="companyIdSelect" path="company.id" style ="width:220px;">
						<option value="0">请选择您要查询的公司</option>
						<form:options items="${fns:getAllCompany()}" itemLabel="companyName" itemValue="id" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!role.currentUser.admin}">
					<input type="text" disabled="disabled" value="${role.currentUser.company.companyName}"/>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">组织机构:</label>
			<div class="controls">
			
			<c:if test="${role.currentUser.admin}">
					<sys:treeselect id="group" name="group.id" cssStyle="width:210px;" value="${role.group.id}" labelName="group.groupName" labelValue="${role.group.groupName}" 
					title="机构" url="/sys/group/treeData1" allowClear="true"/>
			</c:if>
			<c:if test="${!role.currentUser.admin}">
				<sys:treeselect id="group" name="group.id" value="${role.group.id}" labelName="group.groupName" labelValue="${role.group.groupName}"
				title="机构" url="/sys/group/treeData1?companyId=${role.currentUser.company.id}" allowClear="true"/>
			</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">角色名称:</label>
			<div class="controls">
				<input id="oldName" name="oldName" type="hidden" value="${role.roleName}">
				<form:input path="roleName" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">角色编号:</label>
			<div class="controls">
				<form:input path="roleNo" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> 工作流用户组标识</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">显示顺序:</label>
			<div class="controls">
				<form:input path="displayOrder" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">角色类型:</label>
			<div class="controls"><%--
				<form:input path="roleType" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline" title="activiti有3种预定义的组类型：security-role、assignment、user 如果使用Activiti Explorer，需要security-role才能看到manage页签，需要assignment才能claim任务">
					工作流组用户组类型（security-role：管理员、assignment：可进行任务分配、user：普通用户）</span> --%>
				<form:select path="roleType" id="roleTypeSel">
					<form:options items="${fns:getDictItemListL('role_type','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
<!-- 				<span class="help-inline" title="activiti有3种预定义的组类型：security-role、assignment、user 如果使用Activiti Explorer，需要security-role才能看到manage页签，需要assignment才能claim任务">
					工作流组用户组类型（流程角色：assignment、功能角色：security-role、超级管理员：super-admin）</span>
 -->			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">是否系统数据:</label>
			<div class="controls">
				<form:select path="sysData">
					<form:options items="${fns:getDictList('group_yes_no')}" itemLabel="dictName" itemValue="dictCode" htmlEscape="false"/>
				</form:select>
				<span class="help-inline">“是”代表此数据只有超级管理员能进行修改，“否”则表示拥有角色修改人员的权限都能进行修改</span>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">是否可用</label>
			<div class="controls">
				<form:radiobuttons path="status" items="${fns:getDictItemListL('yes_no','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				<span class="help-inline">“是”代表此数据可用，“否”则表示此数据不可用</span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">数据范围:</label>
			<div class="controls">
				<form:select path="dataScope" class="input-medium">
					<form:options items="${fns:getDictItemListL(s_appNo,'sys_data_scope',language)}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
				<span class="help-inline">特殊情况下，设置为“按明细设置”，可进行跨机构授权</span>
			</div>
		</div> --%>
		<div class="control-group" id="giveRigthsDiv">
			<label class="control-label">角色授权:</label>
			<div class="controls">
				<div id="menuTree" class="ztree" style="margin-top:3px;float:left;"></div>
				<form:hidden path="menuIds"/>
				<div id="groupTree" class="ztree" style="margin-left:100px;margin-top:3px;float:left;"></div>
				<form:hidden path="groupIds"/>
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