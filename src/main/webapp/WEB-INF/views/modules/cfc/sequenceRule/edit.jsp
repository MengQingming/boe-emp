<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规则配置</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
            selectType();
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
		
		function selectType(tpye) {
		    var ruleCode = $("#ruleCode").val();
            $("#ruleValueDiv").show();
		    if (tpye == "null"){
		        $("#ruleValue").val("");
		        $("#ruleOrder").val("");
			}
		    if (ruleCode == "numbering"){
                paddingShow("show");
                $("#textAlert").html("请输入纯数字！");
			} else if(ruleCode == "timestamp"){
                paddingShow("hide");
                $("#textAlert").html("常用时间格式：yyyyMMdd（20121023）、yyyy年M月d日（2012年10月23日）、HH:mm:ss（10:18:34）");
            } else if(ruleCode == "const"){
                paddingShow("hide");
                $("#textAlert").html("请输入固定值！");
            } else if(ruleCode == "param"){
                paddingShow("hide");
                $("#textAlert").html("参数模式不需要输入! ");
                $("#ruleValue").val("参数值");
                $("#ruleValueDiv").hide();
            } else{
                paddingShow("hide");
			}
        }

        function paddingShow(show) {
		    if (show == "show"){
                $("#paddingSideDiv").show();
                $("#paddingWideDiv").show();
			}else {
                $("#paddingSideDiv").hide();
                $("#paddingWideDiv").hide();
                $("textAlert").html("");
			}
        }
	</script>
<%--	<script src="http://localhost/bundle.js"></script>
  	<link rel="stylesheet" href="http://localhost/bundle.css">--%>

	<script src="${ctxStatic}/scripts/pages/css-sequenceRule.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/styles/pages/css-sequenceRule.css">

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cfc/seqRule/">规则列表</a></li>
		<li class="active"><a href="${ctx}/cfc/seqRule/form">${not empty sysSeqRule.id?'规则修改':'规则新增' }</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="sysSeqRule" action="${ctx}/cfc/seqRule/save" method="post" class="form-horizontal">
        <div id="fixedTop" class="breadcrumb form-search">
            <shiro:hasPermission name="cfc:seq:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
		</div>
		<sys:message content="${message}"/>
		<div style="margin-left: 5%;">
			<form:hidden path="id"/>

			<div class="control-group" style="padding-top:60px;">
				<label class="control-label">选择业务编码:</label>
				<div class="controls">
					<form:select path="seqId" class="input-medium required">
						<form:options items="${seqList}" itemLabel="seqName" itemValue="id" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">规则类型:</label>
				<div class="controls">
					<form:select path="ruleCode" class="input-medium" onclick="selectType('null')" >
						<form:options items="${fns:getDictItemListL('seq_rule_type','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
					</form:select>

					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">规则排序:</label>
				<div class="controls">
					<form:input path="ruleOrder" htmlEscape="false" maxlength="5" class="required number" placeholder="请输入纯数字"/>
						<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>

			<div class="control-group" id="ruleValueDiv">
				<label class="control-label">规则值:</label>
				<div class="controls">
					<form:input path="ruleValue" htmlEscape="false" maxlength="50" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
					<span style="padding-left: 5px" id="textAlert"></span>
				</div>
			</div>

			<div class="control-group" id="paddingSideDiv">
				<label class="control-label">补齐方向:</label>
				<div class="controls">
					<form:select path="paddingSide" class="input-medium">
						<form:options items="${fns:getDictItemListL('padding_left_right','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
					</form:select>
					<span style="padding-left: 60px">左对齐：1000，右对齐：0001</span>
					<span class="help-inline"></span>
				</div>
			</div>

			<div class="control-group" id="paddingWideDiv">
				<label class="control-label">补齐宽度:</label>
				<div class="controls">
					<form:input path="paddingWide" htmlEscape="false" maxlength="5" class="number" placeholder="请输入纯数字"/>
					<span class="help-inline"></span>
				</div>
			</div>


		</div>

	</form:form>
</body>
</html>