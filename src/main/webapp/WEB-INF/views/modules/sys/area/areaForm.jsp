<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
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
			$("#btnSubmit").click(function(){
				if($("#areaName").val()==""){
					top.$.jBox.tip("请选择上级区域", 'info');
					return false;
				}
			});

		});
		
		function regNumber(v){
			var check =  /^[0-9]*[1-9][0-9]*$/;
			var value = $(v).val();
			if(!check.test(value)){
				$(v).val("");
				$("#spanCode").text("请输入数字 *"); 
			}else{
				$("#spanCode").text("*"); 
			}
		}
		
		function regNumberCode(v){
			var check =  /^[0-9]*[1-9][0-9]*$/;
			var value = $(v).val();
			if(!check.test(value)){
				$(v).val("");
				$("#spanDisplayOrder").text("请输入数字 *"); 
			}else{
				$("#spanDisplayOrder").text("*"); 
			}
		}
		
		function regChinese(v){
			var check = /^[\u4e00-\u9fa5\a-zA-Z]+$/;
			var value = $(v).val();
			if(!check.test(value)){
				$(v).val("");
				$("#spanName").text("请输入中文或字母 *"); 
			}else{
				$("#spanName").text("*"); 
			}
		}
		
	</script>
<%--	<script src="http://localhost/bundle.js"></script>
  	<link rel="stylesheet" href="http://localhost/bundle.css">--%>
	<link rel="stylesheet" href="${ctxStatic}/styles/pages/css-areaForm.css">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/area/list?id=${area.parent.id}">区域列表</a></li>
		<li class="active">
		<c:choose>
			<c:when test="${not empty area.id }">
				<a href="form?id=${area.id}&parent.id=${area.parent.id}">
					<shiro:hasPermission name="sys:area:edit">区域修改</shiro:hasPermission>
				</a>
			</c:when>
			<c:otherwise>
				<a href="form?id=${area.id}&parent.id=${area.parent.id}">
					<shiro:hasPermission name="sys:area:edit">区域添加</shiro:hasPermission>
				</a>
			</c:otherwise>
		</c:choose>
		<%-- <a href="form?id=${area.id}&parent.id=${area.parent.id}">区域
			<shiro:hasPermission name="sys:area:edit">${not empty area.id?'修改':'添加'}</shiro:hasPermission>
			<shiro:lacksPermission name="sys:area:edit">查看</shiro:lacksPermission> </a> --%>
		
		</li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="area" action="${ctx}/sys/area/save" method="post" class="form-horizontal">
		<div id="fixedTop" class="breadcrumb form-search">
			<shiro:hasPermission name="sys:area:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<form:hidden path="id"/>
		<form:hidden path="companyId"/>
		<form:hidden path="companyName"/>
		<form:hidden path="companyNo"/>
		<sys:message content="${message}"/>
		<!-- 添加或修改标志 -->
<%-- 		<c:choose>
			<c:when test="${not empty area.id }">
				<input name="saveOrUpdate" hidden="hidden" value="update"/>
			</c:when>
			<c:otherwise>
				<input name="type" hidden="hidden" value="save"/>
			</c:otherwise>
		</c:choose> --%>
			<div class="control-group css-head">
				<label class="control-label">上级区域:</label>
				<div class="controls">
				    
					<%--<sys:treeselect id="area" name="parent.id" value="${area.parent.id}" labelName="parent.name" labelValue="${area.parent.name}"
					title="区域" url="/sys/area/treeData1" extId="${area.id }" cssClass="required" allowClear="true"/>--%>
					<sys:treeselect2 id="area" name="parent.id" value="${area.parent.id}" labelCodeValue="${area.parent.code}" labelCodeName="parent.code" labelName="parent.name" labelValue="${area.parent.name}"
									title="区域" url="/sys/area/treeData1" extId="${area.id }" cssClass="required" allowClear="true"/>
				</div>
			</div>
		<%-- <c:if test="${ not empty area.id}">
			<div class="control-group" style="margin-top: 50px;">
				<label class="control-label">上级区域:</label>
				<div class="controls">
					<sys:treeselect id="area" name="parent.id" value="" labelName="parent.name" labelValue="${parent.name }"
					title="区域" url="/sys/area/treeData1" extId="${area.code }" cssClass="" allowClear="true"/>
				</div>
			</div>
		</c:if> --%>
		<%--<div class="control-group">
			<label class="control-label">区域名称111111:</label>
			<div class="controls">
				<input type="text" readonly="readonly" value="新疆维吾尔自治区,喀什地区,巴楚县" />
				<a class="region-picker" href="javascript:;" data-remote="/sys/area/treeData1" data-picked="新疆维吾尔自治区,喀什地区,巴楚县" data-visible="5">
					选择
				</a>
			</div>
		</div>--%>

		<div class="control-group">
			<label class="control-label">区域名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" onchange="regChinese(this)" onkeyup="regChinese(this)" class="required"/>
				<span class="help-inline" style="color:#ea5200;font-weight: bold" id="spanName"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区域编号:</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="50" onchange="regNumber(this)" onkeyup="regNumber(this)" class="required"/>
				<span class="help-inline" style="color:#ea5200;font-weight: bold" id="spanCode"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<form:input path="displayOrder" htmlEscape="false" maxlength="50" onchange="regNumberCode(this)" onkeyup="regNumberCode(this)" class="required"/>
				<span class="help-inline" style="color:#ea5200;font-weight: bold" id="spanDisplayOrder"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区域类型:</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge required" style="width:220px">
					<form:option value=""></form:option>
					<form:options items="${fns:getDictItemListL('sys_area_type','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">城市级别:</label>
			<div class="controls">
				<form:select path="level" class="input-xlarge required" style="width:220px">
					<form:option value=""></form:option>
					<form:options items="${fns:getDictItemListL('sys_area_level','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">城市分类:</label>
			<div class="controls">
				<form:select path="category" class="input-xlarge required" style="width:220px">
					<form:option value=""></form:option>
					<form:options items="${fns:getDictItemListL('sys_area_category','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区域自定义标签:</label>
			<div class="controls">
				<form:input path="areaTag" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否启用:</label>
			<div class="controls">
				<form:radiobuttons path="status"
					items="${fns:getDictItemListL('yes_no','T')}"
					itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"
					class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" style="width:220px" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
	</form:form>
</body>
</html>