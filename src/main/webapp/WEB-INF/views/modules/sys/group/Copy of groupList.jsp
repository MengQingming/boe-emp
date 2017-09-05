<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, rootId = "${group.id}";
			addRow("#treeTableList", tpl, data, rootId, true);
			$("#treeTable").treeTable({expandLevel : 1});
		});
		function addRow(list, tpl, data, pid, root){
			var language = '${language}';
			var s_appNo = '${s_appNo}';
			for (var i=0; i<data.length; i++){
				var row = data[i];
				var parentId = (${fns:jsGetVal('row.parentId')});
				if (parentId == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
							type: getDictItemLabel(${fns:toJson(fns:getDictItemListL(s_appNo,'sys_group_type',language))}, row.groupType),
							grade: getDictItemLabel(${fns:toJson(fns:getDictItemListL(s_appNo,'sys_group_grade',language))}, row.groupLayer),
						}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/group/list?id=${group.id}&parentIds=${group.parentIds}">机构列表</a></li>
		<shiro:hasPermission name="sys:group:edit"><li><a href="${ctx}/sys/group/form?parent.id=${group.id}">机构添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>机构名称</th><th>机构简称</th><th>归属区域</th><th>机构编码</th><th>机构类型</th><th>机构层次</th><th>备注</th><shiro:hasPermission name="sys:group:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/sys/group/form?id={{row.id}}">{{row.groupName}}</a></td>
			<td>{{row.groupShortName}}</td>
			<td>{{row.area.name}}</td>
			<td>{{row.groupNo}}</td>
			<td>{{dict.type}}</td>
			<td>{{dict.grade}}</td>
			<td>{{row.remarks}}</td>
			<shiro:hasPermission name="sys:group:edit"><td>
				<a href="${ctx}/sys/group/form?id={{row.id}}">修改</a>
				<a href="${ctx}/sys/group/delete?id={{row.id}}" onclick="return confirmx('要删除该机构及所有子机构项吗？', this.href)">删除</a>
				<a href="${ctx}/sys/group/form?parent.id={{row.id}}">添加下级机构</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>