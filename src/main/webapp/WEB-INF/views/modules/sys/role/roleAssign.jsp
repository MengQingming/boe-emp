<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分配角色</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/role/assign");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/role/">角色列表</a></li>
		<li class="active"><a href="${ctx}/sys/role/assign?role.id=${user.role.id}"><shiro:hasPermission name="sys:role:edit">角色分配</shiro:hasPermission></a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/role/assign" method="post" class="breadcrumb form-search css-searchForm">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<input type="hidden" name="role.id" value="${user.role.id}"/>
		<ul class="ul-form">
			<li>
			<label>公司名称：</label>
			<c:if test="${user.currentUser.admin}">
				<form:select id="companyIdSelect" path="company.id" style = "width:220px;">
					<option value="0">请选择您要查询的公司</option>
					<form:options items="${fns:getAllCompany()}" itemLabel="companyName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</c:if>
			<c:if test="${!user.currentUser.admin}">
				<input type="text" disabled="disabled" value="${user.currentUser.company.companyName}"/>
			</c:if>
			</li>
			<li><label>登录名：</label><form:input path="userName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="clearfix"></li>
			<li>
				<label>组织机构：</label>
				<c:if test="${user.currentUser.admin}">
					<sys:treeselect id="group" name="group.id" cssStyle="width:160px;" value="${user.group.id}" labelName="group.groupName" labelValue="${user.group.groupName}" 
					title="机构" url="/sys/group/treeData1" cssClass="input-small" allowClear="true"/>
				</c:if>
				<c:if test="${!user.currentUser.admin}">
					<sys:treeselect id="group" name="group.id" cssStyle="width:160px;" value="${user.group.id}" labelName="group.groupName" labelValue="${user.group.groupName}" 
					title="机构" url="/sys/group/treeData1?companyId=${user.currentUser.company.id}" cssClass="input-small" allowClear="true"/>
				</c:if>
			</li>
			<li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="fullName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input id="css-back" class="btn btn-primary" type="submit" value="返回"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div id="fixedTop" class="breadcrumb form-search">
		<form id="assignRoleForm" action="${ctx}/sys/role/assignrole" method="post" class="hide">
			<input type="hidden" name="id" value="${user.role.id}"/>
			<input id="idsArr" type="hidden" name="idsArr" value=""/>
		</form>
		<input id="assignButton" class="btn btn-primary" type="submit" value="分配角色"/>
		<script type="text/javascript">
			$("#assignButton").click(function(){
				top.$.jBox.open("iframe:${ctx}/sys/role/usertorole?id=${user.role.id}", "分配角色",810,$(top.document).height()-240,{
					buttons:{"确定分配":"ok", "清除已选":"clear", "关闭":true}, bottomText:"通过选择部门，然后为列出的人员分配角色。",submit:function(v, h, f){
						//var pre_ids = h.find("iframe")[0].contentWindow.pre_ids;
						var ids = h.find("iframe")[0].contentWindow.ids;
						//nodes = selectedTree.getSelectedNodes();
						if (v=="ok"){
							// 删除''的元素
							if(ids[0]==''){
								ids.shift();
								//pre_ids.shift();
							}
							if(ids.length<=0){
								top.$.jBox.tip("未给角色【${role.roleName}】分配新成员！", 'info');
								return false;
							};
					    	// 执行保存
					    	loading('正在提交，请稍等...');
					    	var idsArr = "";
					    	for (var i = 0; i<ids.length; i++) {
					    		idsArr = (idsArr + ids[i]) + (((i + 1)== ids.length) ? '':',');
					    	}
					    	$('#idsArr').val(idsArr);
					    	$('#assignRoleForm').submit();
					    	return true;
						} else if (v=="clear"){
							h.find("iframe")[0].contentWindow.clearAssign();
							return false;
		                }
					}, loaded:function(h){
						$(".jbox-content", top.document).css("overflow-y","hidden");
					}
				});
			});
		</script>
		<input class="btn btn-primary css-search" type="button" value="查询"></li>
	</div>
	<div class="container-fluid breadcrumb">
		<div class="row-fluid span12">
			<span class="span4">角色名称: <b>${user.role.roleName}</b></span>
			<span class="span4">归属机构:  <b>${user.role.group.groupName}</b></span>
			<span class="span4">角色类型:  
				<b>
					<c:if test="${user.role.roleType=='assignment'}">
						流程管理
					</c:if>
					<c:if test="${user.role.roleType=='security-role'}">
						功能管理
					</c:if>
				</b>
			</span>
		</div>
		<%-- <div class="row-fluid span8">
			<span class="span4">角色类型: ${role.roleType}</span>
			<c:set var="dictvalue" value="${role.dataScope}" scope="page" />
			<span class="span4">数据范围: ${fns:getDictItemValueL(s_appNo,role.dataScope,language,'sys_data_scope','未知')}</span>
		</div> --%>
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
		<thead><tr><th>归属公司</th><th>组织机构</th><th>登录名</th><th>姓名</th><th>电话</th><th>手机</th><shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="u">
			<tr>
				<td>${u.company.companyName}</td>
				<td>${u.group.groupName}</td>
				<td>${u.userName}</td>
				<td>${u.fullName}</td>
				<td>${u.phone}</td>
				<td>${u.mobile}</td>
				<shiro:hasPermission name="sys:role:edit"><td>
					<a href="${ctx}/sys/role/outrole?userId=${u.id}&roleId=${user.role.id}" onclick="return confirmx('确认要将用户<b>[${u.userName}]</b>从<b>[${user.role.roleName}]</b>角色中移除吗？', this.href)">
						<img src="${ctxStatic}/images/03.png" onMouseOver="this.src='${ctxStatic}/images/3.png'"onMouseOut="this.src='${ctxStatic}/images/03.png'" title="移除" style="height:80%;">
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
