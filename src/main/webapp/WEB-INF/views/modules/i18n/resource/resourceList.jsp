<%@ page contentType="text/html;charset=UTF-8"%>
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
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed css-contentTable">
		<thead>
			<tr>
				<th>序号</th>
				<th>公司名称</th>
				<th>语言</th>
				<th>资源编号</th>
				<th>值</th>
				<th>资源类型</th>
				<shiro:hasPermission name="sys:dict:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" varStatus="list" var="resource">
				<%--<tr onclick="location.href='${ctx}/fssc/abmBill/form?query=query&id=${abmBill.id}';"> --%>
				<tr>
					<td>${list.index+1 }</td>
					<td>${resource.companyName}</td>
					<td>${fns:getDictItemValueL(resource.language,'sys_language','T','未知')}</td>
					<td><a href="${ctx}/i18n/resource/view?id=${resource.id}">${resource.code}</a></td>
					<td>${resource.value}</td>
					<td>${fns:getDictItemValueL(resource.type,'sys_i18n_type','F','未知')}</td>
					<shiro:hasPermission name="i18n:resource:edit">
						<td>
<!-- 							<a href="${ctx}/i18n/resource/form?query=query&id=${resource.id}">查看</a> -->
							<a href="${ctx}/i18n/resource/form?id=${resource.id}"><img src="${ctxStatic}/images/02.png" onMouseOver="this.src='${ctxStatic}/images/2.png'" onMouseOut="this.src='${ctxStatic}/images/02.png'" title="修改"></a>
							<a href="${ctx}/i18n/resource/delete?id=${resource.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)"><img src="${ctxStatic}/images/03.png" onMouseOver="this.src='${ctxStatic}/images/3.png'"onMouseOut="this.src='${ctxStatic}/images/03.png'" title="删除" style="height:80%;"></a>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>