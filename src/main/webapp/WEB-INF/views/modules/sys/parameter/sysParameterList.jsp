<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统参数管理</title>
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
	<style>
		#appNo{z-index:1000000;}
	</style>
	<script src="${ctxStatic}/common/table.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/common/table.css">
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/parameter/sysParameter/">系统参数列表</a></li>
		<shiro:hasPermission name="parameter:sysParameter:edit"><li><a href="${ctx}/parameter/sysParameter/form">系统参数添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysParameter" action="${ctx}/parameter/sysParameter/" method="post" class="breadcrumb form-search css-searchForm">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>App名称：</label>
				<select id="appNo" name="appNo" class="input-medium">
					<option value="" ></option>
					<c:forEach items="${apps}" var="app">
						<option value="${app.appNo}" <c:if test='${sysParameter.appNo == app.appNo}'>selected = "selected"</c:if>>${app.appName}</option>
					</c:forEach>
			</select>
			</li>
			<li><label>参数分组：</label>
				<form:select path="paramGroup" class="input-medium">
					<form:option value="" label=""></form:option>
					<form:options items="${fns:getDictItemListL('sys_parameter_type','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>

		</ul>
	</form:form>
	<sys:message content="${message}"/>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed css-contentTable">
		<thead>
			<tr>
				<th>参数名称</th>
				<th>App名称</th>
				<!-- <th>单位编号</th> -->
				<th>所属公司</th>
				<th>参数分组</th>
				
				<th>参数值</th>
				<th>参数格式</th>
				<th>描述</th>
				<shiro:hasPermission name="parameter:sysParameter:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysParameter">
			<tr>
				<td>
					<a href="${ctx}/parameter/sysParameter/form?query=query&id=${sysParameter.id}">
						${sysParameter.paramName}
					</a>
				</td>
				<td>
					${sysParameter.appNo}
				</td>
				<%-- <td>
					${sysParameter.companyNo}
				</td> --%>
				<td>
					${sysParameter.companyName}
				</td>
				<td>
					${fns:getDictItemValueL(sysParameter.paramGroup,'sys_parameter_type','F','未知')}
				</td>
				<td>
					${sysParameter.paramValue}
				</td>
				<td>
					${sysParameter.paramFormat}
				</td>
				<td>
					${sysParameter.remarks}
				</td>
				<shiro:hasPermission name="parameter:sysParameter:edit"><td>
    				<a href="${ctx}/parameter/sysParameter/form?id=${sysParameter.id}"><img src="${ctxStatic}/images/02.png" onMouseOver="this.src='${ctxStatic}/images/2.png'" onMouseOut="this.src='${ctxStatic}/images/02.png'" title="修改" ></a>
					<a href="${ctx}/parameter/sysParameter/delete?id=${sysParameter.id}" onclick="return confirmx('确认要删除该系统参数吗？', this.href)"><img src="${ctxStatic}/images/03.png" onMouseOver="this.src='${ctxStatic}/images/3.png'"onMouseOut="this.src='${ctxStatic}/images/03.png'" title="删除"></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>