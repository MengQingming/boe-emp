<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构OU配置</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
        $(document).ready(function() {
        	$("#radiobuttons").hide();
        	$("#checkboxes").hide();
        	
            $("input[name='ouCheck']").click(function () {
           		if ($(this).val() != "r_ouCheck") {
           			$("#radiobuttons").hide();
                	$("#checkboxes").show();
           		} else {
           			$("#radiobuttons").show();
           			$("#checkboxes").hide();
           		}
           		$("input[name=ouList]").removeAttr("checked");
            });
            
            console.log('${fn:length(mGroupOrg.ouList)}')
            if ('${fn:length(mGroupOrg.ouList)}' == 1)
            	$("#radiobuttons").show();
            else
            	$("#checkboxes").show();
            
        });
        
        function saveOu(){
    		$.ajax({
    	        cache: true,
    	        type: "POST",
    	        url:"${ctx}/cfc/mOrg/save",
    	        data:$('#inputForm').serialize(),
    	        async: false,
    	        success: function(data) {
    	        	console.log(data);
    	        	if(!data.success)
    		 			$.jBox.alert(data.error);
    				else
    					$.jBox.alert(data.msg);
    	        }
    	    });
    	}
	</script>
</head>
<body>
<form:form id="inputForm" modelAttribute="mGroupOrg" action="#" method="post" class="form-horizontal">
	<ul class="nav nav-tabs">
		<li><a href="#">机构OU配置</a></li>
	</ul>
	<c:if test="${group.groupName != null}">
		<div style="position:fixed;width: 100%;z-index:999" class="breadcrumb form-search">
		<shiro:hasPermission name="cfc:mOrg:edit"><input class="btn btn-primary" type="button" onclick="saveOu();" value="保 存"/>&nbsp;</shiro:hasPermission>
		</div>
	</c:if>
    <form:hidden path="id" />
	<form:hidden path="groupId" />
	<form:hidden path="groupNo" />
	<form:hidden path="groupName" />
	<form:hidden path="groupPath" />

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
			<br>
			<label class="control-label">机构OU关联:</label>
			<div class="controls">
				<input type="radio" name="ouCheck" value="r_ouCheck" <c:if test="${fn:length(mGroupOrg.ouList) == 1}">checked="checked"</c:if>>单选
    			<input type="radio" name="ouCheck" value="c_ouCheck" <c:if test="${fn:length(mGroupOrg.ouList) != 1}">checked="checked"</c:if>>多选
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">
				<span class="help-inline"></span><p>OU 列表:</p>
			</label>
			<div class="controls" id="radiobuttons" disabled="true" >
				<form:radiobuttons path="ouList" items="${mOrgList}" element="br" itemValue="id" itemLabel="orgName" htmlEscape="true" cssClass="required"/>
			</div>
			<div class="controls" id="checkboxes" disabled="true">
				<form:checkboxes path="ouList" items="${mOrgList}" element="br" itemValue="id" itemLabel="orgName" htmlEscape="true" cssClass="required"/>
			</div>
		</div>
	</div>
</form:form>
</body>
</html>