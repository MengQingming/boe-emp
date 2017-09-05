<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
<%-- 	<script src="http://localhost/bundle.js"></script>
  	<link rel="stylesheet" href="http://localhost/bundle.css">--%>
	<script src="${ctxStatic}/common/table.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/common/table.css">
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnImport").click(function(){	
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/sys/user/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnExport").click(function(){
				$.jBox($("#importBox").html(), {
					title:"导入数据", 
					buttons:{"关闭":true}, 
					top:"100px",
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
				});
			});
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/user/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
	<style>
		#groupButton{
			margin-left: 0;
		}
	</style>
</head>
<body>
	<div id="importBox" class="hide" style="padding-top: 10px;">
		<form id="importForm" action="${ctx}/sys/user/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/sys/user/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/list">用户列表</a></li>
		<shiro:hasPermission name="sys:user:edit"><li><a href="${ctx}/sys/user/form">用户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/list" method="post" class="breadcrumb form-search css-searchForm" style="margin-bottom:0">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li>
			<label>公司名称：</label>
			<c:if test="${user.currentUser.admin}">
				<form:select id="companyIdSelect" path="company.id" style = "width:220px;">
					<option value="0">请选择您要查询的公司</option>
					<form:options items="${fns:getAllCompany()}" itemLabel="companyName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</c:if>
			<c:if test="${!user.currentUser.admin}">
				<input type="text" disabled="disabled" value="${user.currentUser.company.companyName}"/>
			</c:if>
			</li>
			<li><label>登录名：</label><form:input path="userName" htmlEscape="false"/></li>
			<!-- <li class="clearfix"></li> -->
			<li>
				<label>组织机构：</label>
				<c:if test="${user.currentUser.admin}">
					<sys:treeselect id="group" name="group.id" cssStyle="width:160px;" value="${user.group.id}" labelName="group.groupName" labelValue="${user.group.groupName}" 
					title="机构" url="/sys/group/treeData1" cssClass="input-small" allowClear="true"/>
				</c:if>
				<c:if test="${!user.currentUser.admin}">
					<sys:treeselect id="group" name="group.id" cssStyle="width:160px;" value="${user.group.id}" labelName="group.groupName" labelValue="${user.group.groupName}" 
					title="机构" url="/sys/group/treeData1?companyId=${user.currentUser.company.id}" cssClass="input-small" allowClear="true"/>
				</c:if>
			</li>
			<li class="clearfix"></li>
			<li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="fullName" htmlEscape="false" maxlength="50"/></li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input id="css-back" class="btn btn-primary" type="submit" value="返回"/> -->
<!-- 				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/> -->
				</li>
			<li style="margin-left: 44px;">
				<shiro:hasPermission name="sys:user:edit"><input id="btnExport" class="btn btn-primary" type="button" value="导入">
				<input id="btnImport" class="btn btn-primary" type="button" value="导出"></shiro:hasPermission>
				<input class="btn btn-primary css-search" type="button" value="查询" onclick="return page();">
			</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-condensed table-bordered css-contentTable">
		<thead><tr><th>组织机构</th><th class="sort-column full_name">姓名</th><th class="sort-column user_name">登录名</th><th>电话</th><th>手机</th><shiro:hasPermission name="sys:user:edit"><th>上级领导</th><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr onmouseover="style.backgroundColor='#f3f8fc'" onmouseout="style.backgroundColor='white'">
				<td>${user.group.groupName}</td>
				<td><a href="${ctx}/sys/user/formView?id=${user.id}">${user.fullName}</a></td>
				<td>${user.userName}</td>
				<td>${user.phone}</td>
				<td>${user.mobile}</td>
				<td>${user.group.leaderName}</td>
				<shiro:hasPermission name="sys:user:edit"><td>
					<a href="${ctx}/sys/user/partTime?id=${user.id}"><img src="${ctxStatic}/images/01.png" onMouseOver="this.src='${ctxStatic}/images/1.png'" onMouseOut="this.src='${ctxStatic}/images/01.png'" title="兼职" style="height:80%;"></a>
    				<a href="${ctx}/sys/user/form?id=${user.id}"><img src="${ctxStatic}/images/02.png" onMouseOver="this.src='${ctxStatic}/images/2.png'" onMouseOut="this.src='${ctxStatic}/images/02.png'" title="修改" style="height:80%;"></a>
					<a href="${ctx}/sys/user/delete?id=${user.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)"><img src="${ctxStatic}/images/03.png" onMouseOver="this.src='${ctxStatic}/images/3.png'"onMouseOut="this.src='${ctxStatic}/images/03.png'" title="删除"></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
<div class="pagination">${page}</div>
</body>
</html>