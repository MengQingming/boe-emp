<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>record管理</title>
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
		<li class="active"><a href="${ctx}/record/empRecordInfo/">record列表</a></li>
		<shiro:hasPermission name="record:empRecordInfo:edit"><li><a href="${ctx}/record/empRecordInfo/form">record添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="empRecordInfo" action="${ctx}/record/empRecordInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>备案名称：</label>
				<form:input path="recordName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>主责部门：</label>
				<form:input path="responsibilityDept" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>备案类别：</label>
				<form:input path="recordTpye" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>备案编号：</label>
				<form:input path="recordCode" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>状态编码：</label>
				<form:input path="status" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>备案名称</th>
				<th>包含需求</th>
				<th>主责部门</th>
				<th>备案类别</th>
				<th>项目类别</th>
				<th>备案编号</th>
				<th>状态名</th>
				<shiro:hasPermission name="record:empRecordInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="empRecordInfo">
			<tr>
				<td><a href="${ctx}/record/empRecordInfo/form?id=${empRecordInfo.id}">
					${empRecordInfo.recordName}
				</a></td>
				<td>
					${empRecordInfo.includeDemand}
				</td>
				<td>
					${empRecordInfo.responsibilityDept}
				</td>
				<td>
					${empRecordInfo.recordTpye}
				</td>
				<td>
					${empRecordInfo.projectType}
				</td>
				<td>
					${empRecordInfo.recordCode}
				</td>
				<td>
					${empRecordInfo.statusName}
				</td>
				<shiro:hasPermission name="record:empRecordInfo:edit"><td>
    				<a href="${ctx}/record/empRecordInfo/form?id=${empRecordInfo.id}">修改</a>
					<a href="${ctx}/record/empRecordInfo/delete?id=${empRecordInfo.id}" onclick="return confirmx('确认要删除该record吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>