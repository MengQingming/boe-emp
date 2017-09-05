<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>业务编码</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cfc/seq/">编码列表</a></li>
		<shiro:hasPermission name="cfc:seq:edit">
			<li><a href="${ctx}/cfc/seq/form">编码新增</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysSeq" action="${ctx}/cfc/seq/" method="post" class="breadcrumb form-search css-searchForm">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<ul class="ul-form">
			<li>
				<label style="width: 100px">业务编码名称：</label>
				<form:input path="seqName" htmlEscape="false" maxlength="50"/>
			</li>
			<li style="margin-left: 20px;">
				<input id="btnSubmit" class="btn btn-primary " type="button" onclick="query()" value="查询" />
			</li>
		</ul>
	</form:form>
	<div id="content" style="width: 100%;">
		<sys:message content="${message}"/>
		<table id="contentTable" class="table table-striped table-bordered table-condensed css-contentTable">

			<thead>
				<tr>
					<th>业务编码名称</th>
					<th>业务编码编号</th>
					<th>公司名称</th>
					<th>重置规则</th>
					<th>创建日期</th>
					<shiro:hasPermission name="cfc:seq:edit">
						<th>操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>

			<tbody></tbody>
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
			url: "${ctx}/cfc/seq/list",
			data:{
				pageSize:pageSize,
				pageNo:pageNo,
                seqName:$("#seqName").val()
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