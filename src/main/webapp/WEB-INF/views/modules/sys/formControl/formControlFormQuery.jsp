<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="u" uri="/WEB-INF/tlds/container.tag.tld" %>
<html>
<head>
	<title>表单配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$("#btnSubmit").attr("disabled",true);
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
		});
		$(function(){
			changeDict();
		});
		
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
		//查询公司编号
		function queryCompany(){
			var id = $("#companyId").val();
			$.ajax({
				url:"${ctx}/sys/company/queryCompanyNo",
				data:{
					id:id
				},
				type:"post",
				datatype:"json",
				success:function(data){
					var json = $.parseJSON(data);
					var companyNo = json.companyNo;
					var companyName = json.companyName;
					$("#companyNo").val(companyNo);
					$("#companyName").val(companyName);
				}
			});
		}
	</script>
	<style>
		body{
		  position: relative;
		  overflow-x: hidden;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/formControl/">表单配置列表</a></li>
		<li class="active"><a href="${ctx}/sys/formControl/form">${not empty formControl.id?'表单配置详情':'表单配置新增' }</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="formControl" action="${ctx}/sys/formControl/save" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
			<%-- <shiro:hasPermission name="i18n:resource:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission> --%>
			<shiro:hasPermission name="sys:formControl:view">
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</shiro:hasPermission>
		</div>
		<%-- <u:container configName="${formControl.companyName }"/> --%>
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
			<div class="control-group" style="padding-top:60px;">
				<label class="control-label">公司名称:</label>
				<div class="controls">
					<form:hidden id="companyName" path="companyName"/>
					<form:select id="companyId" path="companyId" class="required" disabled="true" style="width:220px;" onchange="queryCompany();">
						<form:option value=""></form:option>
						<form:options items="${fns:getAllCompany()}" itemLabel="companyName" itemValue="id" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">公司编号:</label>
				<div class="controls">
					<form:input path="companyNo" readonly="true" class="required"/>
					<span class="help-inline"><font color="red">*</font></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">应用No:</label>
				<div class="controls">
					<form:select id="appNo" path="appNo" disabled="true" style="width:220px;" class="input-medium" onchange="changeDict()">
						<form:options items="${fns:getAllApp()}" itemLabel="appName"
						itemValue="appNo" htmlEscape="false" />
					</form:select>
					<span class="help-inline"><font color="red">*</font></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">表单编号:</label>
				<div class="controls">
					<form:input path="formCode" readOnly="true" class="required"/>
					<span class="help-inline"><font color="red">*</font></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">配置名称:</label>
				<div class="controls">
					<form:input path="configName" readOnly="true" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">配置描述:</label>
				<div class="controls">
					<form:input path="configDesp" readOnly="true" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">配置内容:</label>
				<div class="controls">
					<form:textarea path="configContent" readOnly="true" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">配置实例:</label>
				<div class="controls">
					<form:textarea path="configDemo" readOnly="true" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">是否启用:</label>
				<div class="controls">
					<form:radiobuttons path="status" disabled="true"
					items="${fns:getDictItemListL('yes_no','T')}"
					itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"
					class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
	</form:form>
</body>
</html>