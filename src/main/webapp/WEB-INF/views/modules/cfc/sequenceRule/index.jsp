<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>规则配置</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cfc/seqRule/">规则列表</a></li>
		<shiro:hasPermission name="cfc:seq:edit">
			<li><a href="${ctx}/cfc/seqRule/form">规则新增</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysSeqRule" action="${ctx}/cfc/seqRule/" method="post" class="breadcrumb form-search css-searchForm">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<ul class="ul-form">
			<li>
				<label style="width: 100px">业务编码名称：</label>
				<form:input path="seqName" htmlEscape="false" maxlength="50"/>
			</li>
			<li style="margin-left: 50px;">
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
					<th>公司名称</th>
					<th>规则排序</th>
					<th>规则类型</th>
					<th>规则值</th>
					<th>当前编码</th>
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
			url: "${ctx}/cfc/seqRule/list",
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