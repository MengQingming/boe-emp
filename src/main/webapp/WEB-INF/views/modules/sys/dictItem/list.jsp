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
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed css-contentTable">
		<thead>
			<tr>
				<th>字典项名称</th>
				<th>字典项编码</th>
				<th>App</th>
				<th>所属字典组</th>
				<th>所属字典编码</th>
				<th>是否启用</th>
				<th>语言</th>
				<th>所属公司</th>
				<th>描述</th>
				<shiro:hasPermission name="sys:dictItem:edit">
					<th style="width: 100px;">操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="dictItem">
				<tr>
					<td><a href="${ctx}/sys/dictItem/form?id=${dictItem.id}&query=details">${dictItem.itemValue}</a></td>
					<td>${dictItem.itemCode}</td>
					<td>${APPMAP[dictItem.appNo]}</td>
					<td>${fns:findDictNameById(dictItem.dict.id)}</td>
					<td>${dictItem.dict.dictCode}</td>
					<td>${fns:getDictItemValueL(dictItem.status,'yes_no','T','未知')}</td>
					<td>${fns:getDictItemValueL(dictItem.language,'sys_language','T','未知')}</td>
					<td>${dictItem.companyName}</td>
					<td>${dictItem.remarks}</td>
					<shiro:hasPermission name="sys:dictItem:edit">
						<td>
							<a href="${ctx}/sys/dictItem/form?id=${dictItem.id}"><img src="${ctxStatic}/images/02.png" onMouseOver="this.src='${ctxStatic}/images/2.png'" onMouseOut="this.src='${ctxStatic}/images/02.png'" title="修改"/></a>
							
							<a href="${ctx}/sys/dictItem/delete?id=${dictItem.id}&type=${dictItem.appNo}"
							onclick="return confirmx('确认要删除该字典吗？', this.href)"><img src="${ctxStatic}/images/03.png" onMouseOver="this.src='${ctxStatic}/images/3.png'"onMouseOut="this.src='${ctxStatic}/images/03.png'" title="删除"/></a>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>