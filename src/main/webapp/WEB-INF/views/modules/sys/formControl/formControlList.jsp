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
				<th>应用名称</th>
				<th>配置名称</th>
				<th>表单代码</th>
				<th>是否启用</th>
				<shiro:hasPermission name="sys:dict:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" varStatus="list" var="formControl">
				<%--<tr onclick="location.href='${ctx}/fssc/abmBill/form?query=query&id=${abmBill.id}';"> --%>
				<tr>
					<td>${list.index+1 }</td>
					<td>${formControl.companyName}</td>
					<td>${formControl.appName}</td>
					<td><a href="${ctx}/sys/formControl/view?id=${formControl.id}">${formControl.configName}</a></td>
					<td>${formControl.formCode}</td>
					<td>${fns:getDictItemValueL(formControl.status,'yes_no','T','未知')}</td>
					<shiro:hasPermission name="sys:formControl:edit">
						<td>
<!-- 							<a href="${ctx}/sys/formControl/form?query=query&id=${formControl.id}">查看</a> -->
							<a href="${ctx}/sys/formControl/form?id=${formControl.id}"><img src="${ctxStatic}/images/02.png" onMouseOver="this.src='${ctxStatic}/images/2.png'" onMouseOut="this.src='${ctxStatic}/images/02.png'" title="修改"></a>
							<a href="${ctx}/sys/formControl/delete?id=${formControl.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)"><img src="${ctxStatic}/images/03.png" onMouseOver="this.src='${ctxStatic}/images/3.png'"onMouseOut="this.src='${ctxStatic}/images/03.png'" title="删除" style="height:80%;"></a>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>