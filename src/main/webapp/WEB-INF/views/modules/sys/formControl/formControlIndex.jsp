<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>表单配置</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/formControl/">表单配置列表</a></li>
		<shiro:hasPermission name="sys:formControl:edit">
			<li><a href="${ctx}/sys/formControl/form">新增表单配置</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="formControl"
		 method="post" class="breadcrumb form-search css-searchForm">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
			<ul class="ul-form">
				<c:if test="${user.currentUser.admin }">
					<li>
						<label class="control-label">公司名称:</label>
						<form:select id="companyId" path="companyId" style = "width:220px;">
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
					<label class="control-label">应用名称:</label>
					<form:select id="appNo" path="appNo" class="input-medium" style = "width:220px;" onchange="changeDict()">
						<form:option value="">全部</form:option>
						<form:options items="${fns:getAllApp()}" itemLabel="appName" itemValue="appNo" htmlEscape="false" />
					</form:select>
				</li>
				<li>
					<label class="control-label">配置名称</label>
					<form:input id="configName" path="configName" htmlEscape="false" maxlength="50"/>
				</li>
				<li class="clearfix"></li>
				<li>
					<label class="control-label">表单代码</label>
					<form:input id="formCode" path="formCode" htmlEscape="false" maxlength="50"/>
				</li>
				<li>
					<label class="control-label">是否启用:</label>
					<form:radiobuttons path="status"
						items="${fns:getDictItemListL('yes_no','T')}"
						itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"
						class="required" />
				</li>
				<li style="margin-left: 50px;">
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
					<th>应用No</th>
					<th>配置名称</th>
					<th>表单代码</th>
					<th>是否启用</th>
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
	changeDict();
});

function queryList(pageSize,pageNo){
		var status = $("input[name='status']:checked").val();
		$.ajax({ 
			url: "${ctx}/sys/formControl/list",
			data:{
				pageSize : pageSize,
				pageNO : pageNo,
				companyId : $("#companyId").val(),
				appNo : $("#appNo").val(),
				configName : $("#configName").val(),
				formCode : $("#formCode").val(),
				status : status
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

function changeDict(){
	$("#dict").val("");
	$("#dict").empty();
	$.ajax({ 
		url: "${ctx}/sys/dictItem/getDictByAppNo", 
		data:{
			appNo:$("#appNo").val()
		},
		type:"get",
		cache:false,
		success: function(data){
			$("#dict").append("<option value=''>　</option>"); 
			for(var i=0;i<data.length;i++){
        		$("#dict").append("<option value='"+data[i].dictCode+"'>"+data[i].dictName+"</option>"); 
        	}
  		}
  	});
}
</script>
</body>
</html>