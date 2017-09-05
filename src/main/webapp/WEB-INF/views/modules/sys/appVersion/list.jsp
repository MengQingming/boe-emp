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
		<thead><tr><th>版本名称</th><th>App名称</th><th>版本号</th><th>版本日期</th><th>版本说明</th><shiro:hasPermission name="sys:appVersion:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="appVersion">
			<tr>
				<td><a href="${ctx}/sys/appVersion/form?id=${appVersion.id}&query=details">${appVersion.versionName}</a></td>
				<td>${APPMAPS[appVersion.appNo]}</td>
				<td>${appVersion.versionNum}</td>
				<td><fmt:formatDate value="${appVersion.versionDate}"  pattern="yyyy-MM-dd"/></td>
				<td>${appVersion.remarks}</td>
				<shiro:hasPermission name="sys:appVersion:edit"><td>
    				<a href="${ctx}/sys/appVersion/form?id=${appVersion.id}"><img src="${ctxStatic}/images/02.png" onMouseOver="this.src='${ctxStatic}/images/2.png'" onMouseOut="this.src='${ctxStatic}/images/02.png'" title="修改"></a>
					<a href="${ctx}/sys/appVersion/delete?id=${appVersion.id}&type=${appVersion.appNo}" onclick="return confirmx('确认要删除该版本吗？', this.href)"><img src="${ctxStatic}/images/03.png" onMouseOver="this.src='${ctxStatic}/images/3.png'"onMouseOut="this.src='${ctxStatic}/images/03.png'" title="删除"></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>