<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区域管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		function deleteArea(id,parentId){debugger
			$.ajax({ 
				url: "${ctx}/sys/area/validateAreaDel", 
				data:{
					id:id
				},
				type:"get",
				cache:false,
				context: document.body, 
				success: function(result){
					if("false" == result){
						if(confirmx("确定要删除该吗？","${ctx}/sys/area/delete?id="+id+"&parent.id="+parentId))
						{	
						}else{
							return false;
						}
					}else{
						alertx("该区域有下级区域，不允许删除!");
					}
				}
	      	});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/area/list?id=${area.id }&area.parent.id=0">区域列表</a></li>
		<shiro:hasPermission name="sys:area:edit"><li><a href="${ctx}/sys/area/form?parent.id=${area.id}">区域添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
		<thead>
			<tr>
				<th>序号</th>
				<th>区域名称</th>
				<th>区域编码</th>
				<th>区域类型</th>
				<th>城市级别</th>
				<th>城市类型</th>
				<th>排序</th>
				<th>是否启用</th>
				<th>备注</th>
				<shiro:hasPermission name="sys:area:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${areas}" varStatus="status" var="area">
				<tr>
					<td>${status.index+1}</td>
					<td><a href="${ctx}/sys/area/view?id=${area.id}">${area.name}</a></td>
					<td>${area.code}</td>
					<td>${fns:getDictItemValueL(area.type,'sys_area_type','T','未知')}</td>
					<td>${fns:getDictItemValueL(area.level,'sys_area_level','T','未知')}</td>
					<td>${fns:getDictItemValueL(area.category,'sys_area_category','T','未知')}</td>
					<td>${area.displayOrder}</td>
					<td>${fns:getDictItemValueL(area.status,'yes_no','T','未知')}</td>
					<td>${area.remarks}</td>
					<shiro:hasPermission name="sys:area:edit">
						<td><a href="${ctx}/sys/area/form?id=${area.id}"><img src="${ctxStatic}/images/02.png" onMouseOver="this.src='${ctxStatic}/images/2.png'" onMouseOut="this.src='${ctxStatic}/images/02.png'" title="修改"></a> 
<!-- 					<a href="${ctx}/sys/area/delete?id=${area.id}&parent.id" onclick="return confirmx('确认要删除该区域吗？', this.href)">删除</a></td> -->
					<a href="#" onclick="deleteArea('${area.id}','${area.parent.id }')"><img src="${ctxStatic}/images/03.png" onMouseOver="this.src='${ctxStatic}/images/3.png'"onMouseOut="this.src='${ctxStatic}/images/03.png'" title="删除" style="height:80%;"></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>