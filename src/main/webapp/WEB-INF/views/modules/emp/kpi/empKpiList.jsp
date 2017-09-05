<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>kpi管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
	</script>
</head>
<body>
<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/kpi/empKpi/">kpi列表</a></li>
	<li><a href="${ctx}/kpi/empKpi/form">kpi添加</a></li>
</ul>
<form:form id="searchForm" modelAttribute="empKpi" action="${ctx}/kpi/empKpi/" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<ul class="ul-form">
		<li><label>年度：</label>
			<input name="kpiYear" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
				   value="<fmt:formatDate value="${empKpi.kpiYear}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
		</li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		<li class="clearfix"></li>
	</ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>ID</th>
		<th>级别（1级、2级）</th>
		<th>年度</th>
		<th>KPI名称</th>
		<th>维度</th>
		<th>指标类型</th>
		<th>KPI</th>
		<th>年度指标</th>
		<th>权重</th>
		<th>关键节点</th>
		<th>评分标准</th>
		<th>数据来源</th>
		<th>是否在二级显示</th>
		<th>二级是否可编制</th>
		<th>是否可用</th>
		<th>创建日期</th>
		<th>创建人</th>
		<th>最后更新日期</th>
		<th>最后更新人</th>
		<shiro:hasPermission name="kpi:empKpi:edit"><th>操作</th></shiro:hasPermission>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="empKpi">
		<tr>
			<td><a href="${ctx}/kpi/empKpi/form?id=${empKpi.id}">
					${empKpi.id}
			</a></td>
			<td>
					${empKpi.kpiLevel}
			</td>
			<td>
					${empKpi.kpiYear}
			</td>
			<td>
					${empKpi.kpiName}
			</td>
			<td>
					${empKpi.dimension}
			</td>
			<td>
					${empKpi.targetType}
			</td>
			<td>
					${empKpi.kpi}
			</td>
			<td>
					${empKpi.yearTarget}
			</td>
			<td>
					${empKpi.weight}
			</td>
			<td>
					${empKpi.keyNode}
			</td>
			<td>
					${empKpi.scoreStandard}
			</td>
			<td>
					${empKpi.dateSource}
			</td>
			<td>
					${empKpi.showStage2}
			</td>
			<td>
					${empKpi.editStage2}
			</td>
			<td>
					${empKpi.yn}
			</td>
			<td>
				<fmt:formatDate value="${empKpi.creationDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>
					${empKpi.createdBy}
			</td>
			<td>
				<fmt:formatDate value="${empKpi.lastUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>
					${empKpi.lastUpdatedBy}
			</td>
			<shiro:hasPermission name="kpi:empKpi:edit"><td>
				<a href="${ctx}/kpi/empKpi/form?id=${empKpi.id}">修改</a>
				<a href="${ctx}/kpi/empKpi/delete?id=${empKpi.id}" onclick="return confirmx('确认要删除该kpi吗？', this.href)">删除</a>
			</td></shiro:hasPermission>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>