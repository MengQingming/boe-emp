<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>国际化管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/i18n/resource/">国际化配置列表</a></li>
		<shiro:hasPermission name="i18n:resource:edit">
			<li><a href="${ctx}/i18n/resource/form">新增国际化配置</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="resource"
		 method="post" class="breadcrumb form-search css-searchForm">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
			<ul class="ul-form">
				<c:if test="${user.currentUser.admin }">
					<li>
						<label class="control-label">公司名称:</label>
						<form:select id="companyId" path="companyId" style = "width:220px;" onchange="queryCompany();">
							<form:option value="">全部</form:option>
							<form:options items="${fns:getAllCompany()}" itemLabel="companyName" itemValue="id" htmlEscape="false"/>
						</form:select>
					</li>
				</c:if>
				<c:if test="${!user.currentUser.admin }">
					<li>
						<label class="control-label">公司名称:</label>
						<form:hidden path="companyId" value="${user.currentUser.company.id }"/>
						<form:input path="companyName" readonly="true" value="${user.currentUser.company.companyName }"/>
					</li>
				</c:if>
				<li>
					<label class="control-label">语言:</label>
					<form:select path="language" style = "width:220px;">
						<form:option value="">全部</form:option>
						<form:options items="${fns:getDictItemListL('sys_language','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
					</form:select>
				</li>
				<li>
					<label class="control-label">资源编号:</label>
					<form:input path="code" htmlEscape="false" maxlength="50"/>
				</li>
				<li class="clearfix"></li>
				<li>
					<label class="control-label">资源类型:</label>
					<form:select path="type" style = "width:220px;">
						<form:option value=""></form:option>
						<form:options items="${fns:getDictItemListL('sys_i18n_type','F')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
					</form:select>
				</li>
				<li style="margin-left:60px;">
					<input id="btnSubmit" class="btn btn-primary" type="button" onclick="query()" value="查询" />
				</li>
			</ul>
	</form:form>
	<div id="content" style="width: 100%;overflow-x : scroll;">
		<sys:message content="${message}"/>
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>序号</th>
					<th>公司名称</th>
					<th>语言</th>
					<th>资源编号</th>
					<th>值</th>
					<th>类型</th>
					<shiro:hasPermission name="i18n:resource:edit">
						<th>操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</div>
<script type="text/javascript">
$(function(){ 
	queryList(30,1);
}); 

function queryList(pageSize,pageNo){
		$.ajax({ 
			url: "${ctx}/i18n/resource/list", 
			data:{
				pageSize : pageSize,
				pageNO : pageNo,
				companyId : $("#companyId").val(),
				language : $("#language").val(),
				code : $("#code").val(),
				type : $("#type").val()
			},
			type:"post",
			cache:false,
			context: document.body, 
			success: function(html){
				//写这个一直刷新页面测试ok在提交，ok？$("#css-back").click();
	        	$("#content").replaceWith(html);
	        	$('.table img').height(parseInt($('.table').css('line-height')) * 0.8 + 'px');
      		}
      	});
	}
function query(){
	queryList(30,1);
}
</script>
</body>
</html>