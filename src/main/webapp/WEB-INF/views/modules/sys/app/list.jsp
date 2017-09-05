<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
	function page(n, s) {
		queryList(s,n);
		return false;
	}
</script>
<script src="${ctxStatic}/common/table.js"></script>
<link rel="stylesheet" href="${ctxStatic}/common/table.css">
<div id="content" style="width: 100%;">
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed css-contentTable">
		<thead><tr><th>App名称</th><th>App编号</th><th>当前版本号</th><th>是否启用</th><th>创建日期</th><shiro:hasPermission name="sys:app:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="app">
			<tr>
				<td><a href="${ctx}/sys/app/form?id=${app.id}&query=details">${app.appName}</a></td>
				<td>${app.appNo}</td>
				<td>${app.versionNum}</td>
				<td>${fns:getDictItemValueL(app.status,'yes_no','T','未知')}</td>
				<td><fmt:formatDate value="${app.creationDate}" pattern="yyyy-MM-dd"/></td>
				<shiro:hasPermission name="sys:app:edit"><td>
    				<a href="${ctx}/sys/app/form?id=${app.id}"><img src="${ctxStatic}/images/02.png" onMouseOver="this.src='${ctxStatic}/images/2.png'" onMouseOut="this.src='${ctxStatic}/images/02.png'" title="修改"></a>
					<a href="${ctx}/sys/app/delete?id=${app.id}" onclick="return confirmx('确认要删除该应用吗？', this.href)"><img src="${ctxStatic}/images/03.png" onMouseOver="this.src='${ctxStatic}/images/3.png'"onMouseOut="this.src='${ctxStatic}/images/03.png'" title="删除"></a>
					<a href="${ctx}/sys/appVersion?appNo=${app.appNo}"><img src="${ctxStatic}/images/history.png" onMouseOver="this.src='${ctxStatic}/images/history1.png'"onMouseOut="this.src='${ctxStatic}/images/history.png'" title="历史版本"></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>