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
	<table id="contentTable" class="table table-striped table-bordered table-condensed css-contentTable">
		<tr>
			<th>业务编码名称</th>
			<th>公司名称</th>
			<th>规则排序</th>
			<th>规则类型</th>
			<th>规则值</th>
			<th>创建日期</th>
			<shiro:hasPermission name="cfc:seq:edit">
				<th>操作</th>
			</shiro:hasPermission>
		</tr>

		<tbody>
		<c:forEach items="${page.list}" var="seqRule">
			<tr>
				<td><a href="${ctx}/cfc/seqRule/form?query=query&id=${seqRule.id}">${seqRule.seqName}</a></td>
				<td>${seqRule.companyName}</td>
				<td>${seqRule.ruleOrder}</td>
				<td>${fns:getDictItemValueL(seqRule.ruleCode,'seq_rule_type','T','未知')}</td>
				<td>${seqRule.ruleValue}</td>
				<td><fmt:formatDate value="${seqRule.createDate}" type="both"/></td>
				<shiro:hasPermission name="cfc:seq:edit">
					<td>
						<a href="${ctx}/cfc/seqRule/form?id=${seqRule.id}"><img src="${ctxStatic}/images/02.png" onMouseOver="this.src='${ctxStatic}/images/2.png'" onMouseOut="this.src='${ctxStatic}/images/02.png'" title="修改"></a>
						<a href="${ctx}/cfc/seqRule/delete?id=${seqRule.id}" onclick="return confirmx('确认要删除该应用吗？', this.href)"><img src="${ctxStatic}/images/03.png" onMouseOver="this.src='${ctxStatic}/images/3.png'"onMouseOut="this.src='${ctxStatic}/images/03.png'" title="删除" style="height:80%;"></a>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>