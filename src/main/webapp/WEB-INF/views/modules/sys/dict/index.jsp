<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>字典组管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/dict/">字典组列表</a></li>
		<shiro:hasPermission name="sys:dict:edit">
			<li><a href="${ctx}/sys/dict/form">字典组添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dict"
		action="${ctx}/sys/dict/" method="post" class="breadcrumb form-search css-searchForm">
		<ul class="ul-form">
		  <li>
			<label>App：</label>
			<form:select id="appNo" path="appNo" class="input-medium">
				<form:option value="" label="　" />
				<form:option value="T" label="通用" />
				<form:options items="${fns:getAllApp()}" itemLabel="appName"
					itemValue="appNo" htmlEscape="false" />
			</form:select>
		  </li>
		  <li>
			<label>是否启用：</label>
			<form:select id="status" path="status" class="input-medium" cssStyle="width:62px;">
				<form:options
					items="${fns:getDictItemListL('yes_no','T')}"
					itemLabel="itemValue" itemValue="itemCode" htmlEscape="false" />
			</form:select>
		  </li>
		  <li>
			<label>描述 ：</label>
			<form:input path="remarks" id="remarks" htmlEscape="false" maxlength="50"
				class="input-medium" />
			</li>
		   <li style="margin-left: 20px">
			<input id="btnSubmit" class="btn btn-primary" type="button" onclick="query()" value="查询" />
		   </li>
		  </ul>
	</form:form>
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
	</table>
	<div class="pagination">${page}</div>
	</div>
<script type="text/javascript">
$(function(){ 
	queryList(30,1);
}); 

function queryList(pageSize,pageNo){
		loading('加载中，请稍等...');
		$.ajax({ 
			url: "${ctx}/sys/dict/list", 
			data:{
				pageSize:pageSize,
				pageNo:pageNo,
				status:$("#status").val(),
				appNo :$("#appNo").val(),
				remarks : $("#remarks").val()
			},
			type:"post",
			cache:false,
			context: document.body, 
			success: function(html){
				closeLoading();
				$("#css-back").click();
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