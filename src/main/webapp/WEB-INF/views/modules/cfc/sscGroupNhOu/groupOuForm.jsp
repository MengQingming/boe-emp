<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构OU配置</title>
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
	</script>
</head>
<body>
<form:form id="inputForm" modelAttribute="nhOrg" action="${ctx}/cfc/ou/save" method="post" class="form-horizontal">
	<ul class="nav nav-tabs">
		<li><a href="#">机构OU配置</a></li>
	</ul>
	<c:if test="${group.groupName != null}">
		<div style="position:fixed;width: 100%;z-index:999" class="breadcrumb form-search">
		<shiro:hasPermission name="groupOrg:sscGroupOrg:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		</div>
	</c:if>
    <form:hidden path="id" />
	<form:hidden path="groupId" />
	<form:hidden path="groupNo" />
	<form:hidden path="groupName" />

	<div>
		<sys:message content="${message}"/>
		<div class="control-group" style="padding-top:60px;">
			<label class="control-label">机构名称:</label>
			<div class="controls" id="groupName">
				<c:if test="${group.groupName == null}">
					<h6 style="color: red">请选择组织结构!</h6>
				</c:if>
				<c:if test="${group.groupName != null}">
					<h6>${group.groupName}</h6>
				</c:if>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">
				<span class="help-inline"></span><p>OU 列表:</p>
			</label>
			<div class="controls">
				<form:radiobuttons path="ouId" items="${sscGroupNhOuList}" element="br" itemValue="id" itemLabel="ouName" htmlEscape="true" cssClass="required"/>
			</div>
		</div>
	</div>
</form:form>
</body>
</html>