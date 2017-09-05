<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公司管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/company/">公司列表</a></li>
		<shiro:hasPermission name="sys:company:edit"><li><a href="${ctx}/sys/company/form?sort=10">公司添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="company" action="${ctx}/sys/company/" method="post" class="breadcrumb form-search css-searchForm">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		  <li>
				<label>是否启用：</label>
				<form:select id="status" path="status" cssStyle="width:62px;">
					<form:options
						items="${fns:getDictItemListL('yes_no','T')}"
						itemLabel="itemValue" itemValue="itemCode" htmlEscape="false" />
				</form:select>
		   </li>
		   <li style="margin-left: 20px">
				<input id="btnSubmit" class="btn btn-primary" type="button" onclick="query()" value="查询" />
		   </li>
		  </ul>
	</form:form>
	<div id="content" style="width: 100%;">
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed css-contentTable">
		<thead>
			<tr>
				<th>公司名称</th>
				<th>公司简称</th>
				<th>公司编号</th>
				<th>是否启用</th>
				<th>创建日期</th>
				<shiro:hasPermission name="sys:company:edit">
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
			url: "${ctx}/sys/company/list", 
			data:{
				pageSize:pageSize,
				pageNo:pageNo,
				status:$("#status").val()
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