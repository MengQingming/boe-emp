<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>角色管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#inputForm").validate({
				submitHandler: function(form){
					var ids2 = [], nodes2 = tree2.getCheckedNodes(true);
					for(var i=0; i<nodes2.length; i++) {
						ids2.push(nodes2[i].id);
					}
					$("#groupIds").val(ids2);
					loading('正在提交，请稍等...');
					form.submit();
				}
			});
		});
		
		
		function conf(obj,groupId){
			var userId = $("#userId").val();
			var submit = function (v, h, f) {
				if(v=="ok"){
					$.ajax({ 
			           type: "post", 
			           url: "${ctx}/sys/user/deletePartTime",
			           data: {"groupId":groupId,"userId":userId},
			           dataType: "json", 
			           success: function (data) { 
			        	   if(data.status==true){
			        		   $(obj).parent().parent().remove();
			        	   }
			        	   top.$.jBox.tip(data.msg); 
			           }
			   		});
				}
			}
			$.jBox.confirm("确认要删除此兼职吗？", "系统提示", submit);
		}
		function addPT(){
			var userId = $("#userId").val();
			var groupId = $("#groupId").val();
			var groupName = $("#groupName").val();
			if($("#groupId").val()==""){
				top.$.jBox.tip("请先选择您要兼职的组织机构");
				return false;
			}
			
			$.ajax({ 
	           type: "post", 
	           url: "${ctx}/sys/user/savePartTime",
	           data: {"groupId":groupId,"userId":userId},
	           dataType: "json", 
	           success: function (data) { 
	        	   if(data.status==true){
	        		   $("#partTimeTable").append("<tr><td>"+groupName+"</td><td><a href='javascript:void(0)' onclick='conf(this,"+groupId+")'>删除</a></td></tr>");
	        	   }
	        	   top.$.jBox.tip(data.msg); 
	           }
	   		});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/user/">用户列表</a></li>
		<li class="active"><a href="javascript:void(0)">兼职机构</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/saveUserGroup" method="post" class="form-horizontal">
		<form:hidden id="userId" path="id"/>
		<sys:message content="${message}"/>
		<table>
			<tr>
				<td>
					<sys:treeselect id="group" name="group.id" cssStyle="width:190px;" value="" labelName="group.groupName" labelValue="" 
					title="机构" url="/sys/group/treeData1?companyId=${user.currentUser.company.id}" cssClass="input-small" allowClear="true"/>
				</td>
				<td>
					<input id="btnSubmit" class="btn btn-primary" type="button" onclick="addPT()" value="添加" />
				</td>
			</tr>
		</table>
	
		<table id="partTimeTable" style="width: 300px;margin-top: 20px;" class="table table-striped table-bordered table-condensed">
			<thead><tr><th>已兼职的组织机构</th><shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission></tr></thead>
			<tbody>
				<c:forEach items="${groups}" var="gps">
				<tr>
					<td>${gps.groupName}</td>
					<td>
						<a href="javascript:void(0)" onclick="conf(this,${gps.id})">删除</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</form:form>
</body>
</html>