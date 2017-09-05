]<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>角色管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/role/">角色列表</a></li>
		<shiro:hasPermission name="sys:role:edit">
			<li><a href="${ctx}/sys/role/form">角色添加</a></li>
		</shiro:hasPermission>
	</ul>
	<sys:message content="${message}" />
	<table id="contentTable"class="table table-striped table-bordered table-condensed table-hover">
			<thead>
				<tr>
					<th>角色名称</th>
					<th>角色编号</th>
					<th>所属公司</th>
					<th>归属机构</th>
					<!-- <th>数据范围</th> -->
					<shiro:hasPermission name="sys:role:edit">
						<th>操作</th>
					</shiro:hasPermission>
				</tr>
			<thead/>
		<tbody>	
			<c:forEach items="${list}" var="role">
				<tr>
					<td><a href="${ctx}/sys/role/formView?id=${role.id}">${role.roleName}</a></td>
					<td>${role.roleNo}</td>
					<td>${role.company.companyName}</td>
					<td>${role.group.groupName}</td>
					<%-- <td>${fns:getDictItemValueL(s_appNo,role.dataScope,language,'sys_data_scope','未知')}</td> --%>
					<shiro:hasPermission name="sys:role:edit">
						<td>
							<a href="${ctx}/sys/role/assign?role.id=${role.id}"><img src="${ctxStatic}/images/01.png" onMouseOver="this.src='${ctxStatic}/images/1.png'" onMouseOut="this.src='${ctxStatic}/images/01.png'" title="分配" style="height:80%;"></a>
							<a href="${ctx}/sys/role/form?id=${role.id}"><img src="${ctxStatic}/images/02.png" onMouseOver="this.src='${ctxStatic}/images/2.png'" onMouseOut="this.src='${ctxStatic}/images/02.png'" title="修改" style="height:80%;"></a>
							<a href="${ctx}/sys/role/delete?id=${role.id}" onclick="return confirmx('确认要删除该角色吗？', this.href)"><img src="${ctxStatic}/images/03.png" onMouseOver="this.src='${ctxStatic}/images/3.png'"onMouseOut="this.src='${ctxStatic}/images/03.png'" title="删除" style="height:80%;"></a>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>