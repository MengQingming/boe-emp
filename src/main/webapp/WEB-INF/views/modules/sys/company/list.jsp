<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<script type="text/javascript">
	function page(n, s) {
		queryList(s,n);
		return false;
	}
</script>
<%--
<script src="http://localhost/bundle.js"></script>
<link rel="stylesheet" href="http://localhost/bundle.css">
--%>
<script src="${ctxStatic}/common/table.js"></script>
<link rel="stylesheet" href="${ctxStatic}/common/table.css">
<div id="content" style="width: 100%;">
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed css-contentTable">
		<thead><tr><th>公司名称</th><th>公司简称</th><th>公司编号</th><th>是否启用</th><th>创建日期</th><shiro:hasPermission name="sys:company:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="company">
			<tr>
				<td><a href="${ctx}/sys/company/form?id=${company.id}&query=details">${company.companyName}</a></td>
				<td>${company.companyShortName}</td>
				<td>${company.companyNo}</td>
				<td>${fns:getDictItemValueL(company.status,'yes_no','T','未知')}</td>
				<td><fmt:formatDate value="${company.creationDate}" type="date"/></td>
				<shiro:hasPermission name="sys:company:edit"><td>
    				<a href="${ctx}/sys/company/form?id=${company.id}"><img src="${ctxStatic}/images/02.png" onMouseOver="this.src='${ctxStatic}/images/2.png'" onMouseOut="this.src='${ctxStatic}/images/02.png'" title="修改"></a>
					<a href="${ctx}/sys/company/delete?id=${company.id}" onclick="return confirmx('确认要删除该应用吗？', this.href)"><img src="${ctxStatic}/images/03.png" onMouseOver="this.src='${ctxStatic}/images/3.png'"onMouseOut="this.src='${ctxStatic}/images/03.png'" title="删除"></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>