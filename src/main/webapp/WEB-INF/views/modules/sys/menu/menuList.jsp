<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 3}).show();
            closeLoading();
		});
    	function updateSort() {
			loading('正在提交，请稍等...');
	    	$("#listForm").attr("action", "${ctx}/sys/menu/updateSort");
	    	$("#listForm").submit();
    	}
	</script>
	<script src="${ctxStatic}/common/table.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/common/table.css">
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/menu/">菜单列表</a></li>
		<shiro:hasPermission name="sys:menu:edit"><li><a href="${ctx}/sys/menu/form">菜单添加</a></li></shiro:hasPermission>
	</ul>
	<div id="fixedTop" class="breadcrumb form-search">
		<input id="btnSubmit" class="btn btn-primary" type="button" value="保存排序" onclick="updateSort();"/></li>
	</div>
	<div style="margin-top: 60px;"></div>
	<sys:message content="${message}" />
	<form id="listForm" method="post" >
		<table id="treeTable" class="table table-striped table-bordered table-condensed hide table-hover">
			<thead><tr><th>名称</th><th>编号</th><th>链接</th><th style="text-align:center;">排序</th><th>可见</th><th>权限标识</th><shiro:hasPermission name="sys:menu:edit"><th>操作</th></shiro:hasPermission></tr></thead>
			<tbody><c:forEach items="${list}" var="menu">
				<tr id="${menu.id}" pId="${menu.parent.id ne '1'?menu.parent.id:'0'}">
					<td nowrap><a href="${ctx}/sys/menu/formView?id=${menu.id}"><i class="icon-${not empty menu.icon?menu.icon:' hide'}"></i>${menu.menuName}</a> </td>
					<td >${menu.menuNo}</td>
					<td title="${menu.url}">${fns:abbr(menu.url,30)}</td>
					<td style="text-align:center;">
						<shiro:hasPermission name="sys:menu:edit">
							<input type="hidden" name="ids" value="${menu.id}"/>
							<input name="sorts" type="text" value="${menu.displayOrder}" style="width:50px;margin:0;padding:0;text-align:center;">
						</shiro:hasPermission><shiro:lacksPermission name="sys:menu:edit">
							${menu.displayOrder}
						</shiro:lacksPermission>
					</td>
					<td>${menu.displayFlag eq '1'?'显示':'隐藏'}</td>
					<td title="${menu.permission}">${fns:abbr(menu.permission,30)}</td>
					<shiro:hasPermission name="sys:menu:edit"><td nowrap>
						<a href="${ctx}/sys/menu/form?id=${menu.id}"><img src="${ctxStatic}/images/02.png" onMouseOver="this.src='${ctxStatic}/images/2.png'" onMouseOut="this.src='${ctxStatic}/images/02.png'" title="修改" style="height:80%;"></a>
						<a href="${ctx}/sys/menu/delete?id=${menu.id}" onclick="return confirmx('要删除该菜单及所有子菜单项吗？', this.href)"><img src="${ctxStatic}/images/03.png" onMouseOver="this.src='${ctxStatic}/images/3.png'"onMouseOut="this.src='${ctxStatic}/images/03.png'" title="删除" style="height:80%;"></a>
						<a href="${ctx}/sys/menu/form?parent.id=${menu.id}"><img src="${ctxStatic}/images/tianjia1.jpg" onMouseOver="this.src='${ctxStatic}/images/tianjia2.png'"onMouseOut="this.src='${ctxStatic}/images/tianjia1.jpg'" title="添加下级菜单" style="height:80%;"></a> 
					</td></shiro:hasPermission>
				</tr>
			</c:forEach></tbody>
		</table>
	 </form>
</body>
</html>