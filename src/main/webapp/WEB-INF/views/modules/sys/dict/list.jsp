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
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed css-contentTable">
		<thead>
			<tr>
				<th>字典名称</th>
				<th>字典编码</th>
				<th>App</th>
				<th>语言</th>
				<th>描述</th>
				<th>是否启用</th>
				<shiro:hasPermission name="sys:dict:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="dict">
				<tr>
					<td><a href="${ctx}/sys/dict/form?id=${dict.id}&query=details">${dict.dictName}</a></td>
					<td>${dict.dictCode}</td>
					<td>${APPMAP[dict.appNo]}</td>
					<td>${fns:getDictItemValueL(dict.language,'sys_language','T','未知')}</td>
					<td>${dict.remarks}</td>
					<td>${fns:getDictItemValueL(dict.status,'yes_no','T','未知')}</td>
					<shiro:hasPermission name="sys:dict:edit">
						<td>
							<a href="${ctx}/sys/dict/form?id=${dict.id}"><img src="${ctxStatic}/images/02.png" onMouseOver="this.src='${ctxStatic}/images/2.png'" onMouseOut="this.src='${ctxStatic}/images/02.png'" title="修改"></a> 
							<a href="${ctx}/sys/dict/delete?id=${dict.id}&type=${dict.appNo}" onclick="return confirmx('确认要删除该字典吗？', this.href)"><img src="${ctxStatic}/images/03.png" onMouseOver="this.src='${ctxStatic}/images/3.png'"onMouseOut="this.src='${ctxStatic}/images/03.png'" title="删除"></a>
							<a href="${ctx}/sys/dictItem?dictCode=${dict.dictCode}&appNo=${dict.appNo}"><img src="${ctxStatic}/images/data.png" onMouseOver="this.src='${ctxStatic}/images/data1.png'"onMouseOut="this.src='${ctxStatic}/images/data.png'" title="字典项"></a>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>