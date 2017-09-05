<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			$("#btnSubmit").click(function(){
				var re="[";
				$("input[name='leaderhidden']").each(function(){
					var groupId = $(this).attr("id");
					var leaderId = $(this).val();
					if(leaderId!=''){
						if(re=="["){
							re+=",{groupId:"+groupId+",leaderId:"+leaderId+"}";
						}else{
							re+="{groupId:"+groupId+",leaderId:"+leaderId+"}";
						}
					}
				}); 
				re+="]";
				$("#leaderinfo").val(re);
				$("#inputForm").submit();
			});
		});
		
		function setLeader(tid,obj){
			top.$.jBox.open("iframe:${ctx}/sys/user/selectUserParent?id=${user.id}", "绑定上级",810,$(top.document).height()-240,{
				buttons:{"确定":"ok", "清除":"clear", "关闭":true}, bottomText:"通过选择部门，然后为列出的人员绑定上级。",submit:function(v, h, f){
					var parentId = h.find("iframe")[0].contentWindow.parentId;
					var parentName = h.find("iframe")[0].contentWindow.parentName;
					if (v=="ok"){
						
						if(parentId==""){
							top.$.jBox.tip("未选择上级！", 'info');
							return false;
						};
				    	// 执行保存
				    	$(obj).parent().parent().children('td').eq(2).html(parentName);
				    	$('#'+tid).val(parentId);
					} else if (v=="clear"){
						h.find("iframe")[0].contentWindow.clearAssign();
						return false;
	                }
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/info">个人信息</a></li>
		<li><a href="${ctx}/sys/user/modifyPwd">修改密码</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/info" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>
		</div>
		<div class="control-group" style="padding-top: 50px;">
			<input type="hidden" name="leaderinfo" id="leaderinfo"/>
			<sys:message content="${message}"/>
			<label class="control-label">头像:</label>
			<div class="controls">
				<form:hidden id="nameImage" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">组织机构:</label>
			<div class="controls">
				<label class="lbl">
				
				<c:if test="${user.group==null || user.group.groupName==null || user.group.groupName==''}">
					超级管理员
				</c:if>
				<c:if test="${user.group!=null && user.group.groupName!=null && user.group.groupName!=''}">
					${user.group.groupName}
				</c:if>
				</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">登录名:</label>
			<div class="controls">
				<form:input path="userName" htmlEscape="false" maxlength="50" class="required" readonly="true" cssStyle="margin-top: 0px;" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名:</label>
			<div class="controls">
				<form:input path="fullName" htmlEscape="false" maxlength="50" class="required" readonly="true" cssStyle="margin-top: 0px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">员工编号:</label>
			<div class="controls">
				<form:input path="userNum" htmlEscape="false" maxlength="50" class="required" readonly="true" cssStyle="margin-top: 0px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职务级别:</label>
			<div class="controls">
				<c:forEach items="${fns:getDictItemListL('sys_user_level','T')}" var="v">
					<c:if test="${v.itemCode==user.rankCode}">
						<input type="text" value="${v.itemValue }" disabled="disabled" style="margin-top: 0px;"/>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">补助级别:</label>
			<div class="controls">
				<c:forEach items="${fns:getDictItemListL('allowance_level','fssc_rmbs')}" var="v">
					<c:if test="${v.itemCode==user.allowanceLevel}">
						<input type="text" value="${v.itemValue }" disabled="disabled" style="margin-top: 0px;"/>
					</c:if>
				</c:forEach>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="50" class="email"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电话:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机:</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">家庭所在地:</label>
			<div class="controls">
				<form:input path="homeCityName" htmlEscape="false" maxlength="50" readonly="true" cssStyle="margin-top: 0px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作所在地:</label>
			<div class="controls">
				<form:input path="workingCityName" htmlEscape="false" maxlength="50" readonly="true" cssStyle="margin-top: 0px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">派驻地:</label>
			<div class="controls">
				<form:input path="postedCityName" htmlEscape="false" maxlength="50" readonly="true" cssStyle="margin-top: 0px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge" />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">用户类型:</label>
			<div class="controls">
				<label class="lbl">${fns:getDictLabel(user.userType, 'sys_user_type', '无')}</label>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">用户角色:</label>
			<div class="controls">
				<label class="lbl">${user.roleNames}</label>
			</div>
		</div> --%>
		<c:if test="${fns:getUser().id!=1}">
		<div class="control-group">
			<table id="groupTable" style="width: 600px;margin-left: 180px;" class="table table-striped table-bordered table-condensed">
				<thead><tr><th>组织机构名称</th><th>职业性质</th><th>上级领导</th><th>操作</th></thead>
				<tbody>
				<c:forEach items="${fns:getUser().groupList}" var="v">
					<tr>
						<td>
							${v.groupName}
						</td>
						<td> 
							<c:if test="${v.relationType=='F'}">全职</c:if>
							<c:if test="${v.relationType=='P'}">兼职</c:if>
						</td>
						<td>
							<c:if test="${v.leaderName == null}">
								暂无
							</c:if>
							<c:if test="${v.leaderName != null}">
								${v.leaderName}
							</c:if>
						</td>
						<td>
							<input type="hidden" name="leaderhidden" id="${v.id}" value="${v.leaderId}"/>
							<a href="javascript:void(0);" onclick="setLeader('${v.id}',this)">绑定上级</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">上次登录:</label>
			<div class="controls">
				<label class="lbl">IP: ${user.lastLoginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${user.lastLoginDate}" type="both" dateStyle="full"/></label>
			</div>
		</div>
	</form:form>
</body>
</html>