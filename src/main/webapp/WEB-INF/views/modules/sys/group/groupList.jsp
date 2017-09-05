<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/group/list?id=${group.id}&group.parent.id=0">机构列表</a></li>
		<shiro:hasPermission name="sys:group:edit"><li><a href="${ctx}/sys/group/form?parent.id=${group.id}">机构添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="margin-top: 0px;">
		<thead><tr><th>机构名称</th><th>机构简称</th><th>归属区域</th><th>机构编码</th><th>机构类型</th><th>是否启用</th><th>备注</th><shiro:hasPermission name="sys:group:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
			<c:forEach items="${groups}" var="group">
				<tr>
					<td><a href="${ctx}/sys/group/form?id=${group.id}&query=details">${group.groupName}</a></td>
					<td>${group.groupShortName}</td>
					<td>${group.area.name}</td>
					<td>${group.groupNo}</td>
					<td>${fns:getDictItemValueL(group.groupType,'sys_group_type','F','未知')}</td>
					<td>${fns:getDictItemValueL(group.status,'yes_no','T','未知')}</td>
					<td>${group.remarks}</td>
					<shiro:hasPermission name="sys:group:edit">
						<td><a href="${ctx}/sys/group/form?id=${group.id}"><img src="${ctxStatic}/images/02.png" onMouseOver="this.src='${ctxStatic}/images/2.png'" onMouseOut="this.src='${ctxStatic}/images/02.png'" title="修改" /></a>
							<a href="#" onclick="deleteCompany('${group.id}')"><img src="${ctxStatic}/images/03.png" onMouseOver="this.src='${ctxStatic}/images/3.png'"onMouseOut="this.src='${ctxStatic}/images/03.png'" title="删除" /></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript">
		function deleteCompany(id){
			$.ajax({ 
				url: "${ctx}/sys/group/validateGroupDel", 
				data:{
					id:id
				},
				type:"get",
				cache:false,
				context: document.body, 
				success: function(result){
					if("false" == result){
						if(confirmx("确定要删除该机构吗？","${ctx}/sys/group/delete?id="+id))
						{	
						}else{
							return false;
						}
					}else{
						alertx("该机构有子机构，不允许删除!");
					}
				}
	      	});
		}
	</script>
</body>
</html>