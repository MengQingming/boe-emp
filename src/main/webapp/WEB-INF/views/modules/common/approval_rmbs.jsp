<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

<html>
<head>
    <title>审批界面</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler : function(form) {
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer : "#messageBox",
				errorPlacement : function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")
							|| element.is(":radio")
							|| element.parent().is(
									".input-append")) {
						error.appendTo(element.parent()
								.parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			getAduitHis('${formId}', '${formType}');

			document.getElementsByTagName('iframe')[0].onload = function(){
				$("iframe").contents().find('#fixedTop').remove();
				$("iframe").contents().find('.css-pMessage').css('margin-top','0');
				$("iframe").height($("iframe").contents().find('body').height()+100+"px");
				var mynav=$("iframe").contents().find('.nav-tabs')[0].remove();
			}
		});
	
		function agree(){
			if(!checkCommentContent()){
				return ;
			}
            startCompleteTask(2);
		}
		
		function sendBack(){
			if(!checkCommentContent()){
				return ;
			}
			if($("#cyyj").val() == "同意"){
				alert("操作与审批意见不符");
				return;
			}
            startCompleteTask("1");
		}
		
		function startCompleteTask(flag){
			loading('请稍等...');
			var url = "";
			$.ajax({ 
			url: "${ctx}/myWorkbench/mybacklog/executeTask", 
			data:{
				flag :flag,
				formId:$("#formId").val(),
                formType:$("#formType").val(),
				commentContent:$("#commentContent").val(),
				id:$("#id").val()
			},
			type:"post",
			cache:false,
			context: document.body, 
			success: function(html){
					//var data = html.split(",");
					if(!html.flag){
						closeLoading();
						alertx(html.msg);
					}else{
						alertx("处理成功",function(){location.href="${ctx}/myWorkbench/mybacklog/"});
						top.$.jBox.close(true);
					}
      			}
      		});
		}
		
		//打开申请历史单
        function openHisAduit(formId){
            var url = "iframe:"+ctx+"/myWorkbench/mybacklog/openHisAduit?formId="+formId+"&pid="+${pid};
            top.$.jBox.open(url, "审批记录",700,$(top.document).height()-200,{
                buttons:{"关闭":true}, bottomText:"",submit:function(v, h, f){
                }, loaded:function(h){
                    $(".jbox-content", top.document).css("overflow-y","hidden");
                }
            });
        }
		
		
		
		function statusChange(commentContent){
			$("#commentContent").val(commentContent);
		}
		
		function cut1(){
			child.window.cut();
		}
		
		function checkCommentContent(){
			if($("#commentContent").val() == "" || $("#commentContent").val() == '请选择'){
				alertx("请填写审批意见");
				return false;
			}
			return true;
		}
		
		function getAduitHis(formId, formType){
			var url = "${ctx}/myWorkbench/mybacklog/openHisAduit";
			$.ajax({
				url: url,
				data:{
					formId:formId,
					formType:formType,
					pid : '${pid}'
				},
				cache:false,
				context: document.body,
				success: function(html){
					$("#getAduitHis").replaceWith(html);
				}
			});
		}
		
		function extendAct(){
			$("#extendPic").hide();
			$("#shrinkPic").show();
			$("#actPic").show();
		}
		function shrinkAct(){
			$("#actPic").hide();
			$("#shrinkPic").hide();
			$("#extendPic").show();
		}
	</script>
	<style>
	body{
		overflow-x:hidden;
	}
		#css-pMessage{
			margin-left: 30px;
		}
		label.error{background:url("");padding-left:18px;padding-bottom:2px;font-weight:bold;color:#ea5200;font-size:12px;position:absolute;right:0;top:28px;}
		#myTop{
			position: fixed;
			top: 0;
			width: 100%;
			z-index: 10000;
			overflow-x: hidden;}
	</style>
	<title>审批</title>
	<meta name="decorator" content="default" />
	</head>
<body>
	<%-- <ul class="nav nav-tabs">
		<li ><a href="${ctx}/myWorkbench/mybacklog/">我的待办</a></li>
		<li class="active"><a href="#itm">审批详情</a></li>
	</ul> --%>
	<form id="inputForm" modelAttribute="" action="" method="post" class="form-horizontal">
		<div id="myTop" class="breadcrumb form-search">
		<c:if test='${actDefId != "rootDrafterActivity"}'>
				<shiro:hasPermission name="myWorkbench:mybacklog:edit">
					<input id="" class="btn btn-primary" type="button" onclick="agree()" value="同 意"/>&nbsp;
				</shiro:hasPermission>
				<shiro:hasPermission name="myWorkbench:mybacklog:edit">
					<input id="" class="btn btn-primary" type="button" onclick="sendBack()" value="退回"/>&nbsp;
				</shiro:hasPermission>
		</c:if>
		<c:if test='${actDefId eq "rootDrafterActivity"}'>
			<shiro:hasPermission name="myWorkbench:mybacklog:edit">
				<c:if test='${formType eq "RmbsClaim"}'>
					<a href="${ctx}/rmbs/common/open/edit?${wfWorkItem.formUrl}&rUrl=/myWorkbench/mybacklog/"><input id="" class="btn btn-primary" type="button" onclick="" value="编辑"/>&nbsp;</a>
				</c:if>
				<c:if test='${formType eq "HrEmpLeave"}'>
					<a href="${ctx}/tark/leave/appli/hrEmpLeave/form?id=${formId}"><input id="" class="btn btn-primary" type="button" onclick="" value="编辑"/>&nbsp;</a>
				</c:if>	
			
				<c:if test='${formType eq "PmRiskClaim"}'>
					<a href="${ctx}/pm/risk/pmRiskAssessment/form?id=${formId}"><input id="" class="btn btn-primary" type="button" onclick="" value="编辑"/>&nbsp;</a>
				</c:if>
				
				<c:if test='${formType eq "PmCostClaim"}'>
					<a href="${ctx}/tark/pm/pmCostPlan/planShow?id=${formId}"><input id="" class="btn btn-primary" type="button" onclick="" value="编辑"/>&nbsp;</a>
				</c:if>
				
				<c:if test='${formType eq "PmCollectionPlanClaim"}'>
					<a href="${ctx}/pm/collection/pmCollectionPlanLine/?planId=${formId}"><input id="" class="btn btn-primary" type="button" onclick="" value="编辑"/>&nbsp;</a>
				</c:if>
				
				<c:if test='${formType eq "PmCollectionPlanExeClaim"}'>
					<a href="${ctx}/pm/collection/pmCollectionPlanExe/form_act?id=${formId}"><input id="" class="btn btn-primary" type="button" onclick="" value="编辑"/>&nbsp;</a>
				</c:if>
				
				<c:if test='${formType eq "PmPaymentPlanClaim"}'>
					<a href="${ctx}/pm/payment/pmPaymentPlanLine/?planId=${formId}"><input id="" class="btn btn-primary" type="button" onclick="" value="编辑"/>&nbsp;</a>
				</c:if>
				
				<c:if test='${formType eq "PmPaymentPlanExeClaim"}'>
					<a href="${ctx}/pm/payment/pmPaymentPlanExe/form_act?id=${formId}"><input id="" class="btn btn-primary" type="button" onclick="" value="编辑"/>&nbsp;</a>
				</c:if>
			</shiro:hasPermission>
		</c:if>
			<shiro:hasPermission name="myWorkbench:mybacklog:edit">
				<input id="" class="btn btn-primary" type="button" onclick="history.go(-1)" value="返回"/>&nbsp;
			</shiro:hasPermission>
		</div>

		<div style="padding-top: 10px;">
			<fieldset class="bs-docs-example2 css-pMessage" style="<c:if test='${actDefId eq "drafterActivity"}'>display:none</c:if>">

				<input type="hidden" id="id" value="${id}"/>
				<input type="hidden" id="formId" value="${formId}"/>
				<input type="hidden" id="formType" value="${formType}"/>
				<input type="hidden" id="pid" value="${pid}"/>
				<input type="hidden" id="aid" value="${aid}"/>
				<input type="hidden" id="tid" value="${tid}"/>
				<input type="hidden" id="actDefId" value="${actDefId}"/>
				<input type="hidden" id="wfTaskStatus" value="${wfTaskStatus}"/>
				<h4>审批基础信息<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAQAAAAngNWGAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QAAKqNIzIAAAAHdElNRQfhAQYRJQ9sbxDaAAAAn0lEQVQoz9XRsQ3CMBCF4d8oUjpYAeQBKINYAWrIBukyQObIAGmhpkofCToWeIIVYAI6izgxpkO8zqfvLN8Z/jJK1Sn1q5MBMzSsaWQikIocyKn6Za9POw7usLfHAFTGude3spcRqDm3wUMW9u5BTXmMLmFmn2/DKKENbKtV4qCgJgvAjFruxpLiww8UlGBAG07EsjVaco0ywKj7hv00L0JaIixvQD/QAAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDE3LTAxLTA2VDE3OjM3OjE1KzA4OjAwW7cVswAAACV0RVh0ZGF0ZTptb2RpZnkAMjAxNy0wMS0wNlQxNzozNzoxNSswODowMCrqrQ8AAAAASUVORK5CYII="></h4>
	
				<table>
					<tr>
						<td><label class="control-label">常用意见：</label></td>
						<td>
							<select class="input-medium medium-width" id="cyyj" onchange="statusChange(this[selectedIndex].value)">
								<option value="请选择">请选择</option>
								<option value="同意">同意</option>
								<option value="请重新办理">请重新办理</option>
								<option value="请尽快办理">请尽快办理</option>
								<option value="请阅知">请阅知</option>
								<option value="请审核">请审核</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label class="control-label">备注意见：</label></td>
						<td colspan="5">
							<textarea id = "commentContent"></textarea>
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset class="bs-docs-example2 css-pMessage">
				<h4>审批记录 <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAQAAAAngNWGAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QAAKqNIzIAAAAHdElNRQfhAQYRJQ9sbxDaAAAAn0lEQVQoz9XRsQ3CMBCF4d8oUjpYAeQBKINYAWrIBukyQObIAGmhpkofCToWeIIVYAI6izgxpkO8zqfvLN8Z/jJK1Sn1q5MBMzSsaWQikIocyKn6Za9POw7usLfHAFTGude3spcRqDm3wUMW9u5BTXmMLmFmn2/DKKENbKtV4qCgJgvAjFruxpLiww8UlGBAG07EsjVaco0ywKj7hv00L0JaIixvQD/QAAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDE3LTAxLTA2VDE3OjM3OjE1KzA4OjAwW7cVswAAACV0RVh0ZGF0ZTptb2RpZnkAMjAxNy0wMS0wNlQxNzozNzoxNSswODowMCrqrQ8AAAAASUVORK5CYII="></h4>
				<div id="getAduitHis"></div>
			</fieldset>

			<fieldset class="bs-docs-example2 css-pMessage">
			<c:if test='${formType eq "RmbsClaim"}'>
				<h4>报账单详情<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAQAAAAngNWGAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QAAKqNIzIAAAAHdElNRQfhAQYRJQ9sbxDaAAAAn0lEQVQoz9XRsQ3CMBCF4d8oUjpYAeQBKINYAWrIBukyQObIAGmhpkofCToWeIIVYAI6izgxpkO8zqfvLN8Z/jJK1Sn1q5MBMzSsaWQikIocyKn6Za9POw7usLfHAFTGude3spcRqDm3wUMW9u5BTXmMLmFmn2/DKKENbKtV4qCgJgvAjFruxpLiww8UlGBAG07EsjVaco0ywKj7hv00L0JaIixvQD/QAAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDE3LTAxLTA2VDE3OjM3OjE1KzA4OjAwW7cVswAAACV0RVh0ZGF0ZTptb2RpZnkAMjAxNy0wMS0wNlQxNzozNzoxNSswODowMCrqrQ8AAAAASUVORK5CYII="></h4>

				<iframe src="${ctx}/bpms/common/openRmbs/view?id=${formId}" name="child" style="margin-top:20px;width: 100%;border: none;">
				</iframe>
			</c:if>
			<c:if test='${formType eq "HrEmpLeave"}'>
				<h4>休假申请详情<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAQAAAAngNWGAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QAAKqNIzIAAAAHdElNRQfhAQYRJQ9sbxDaAAAAn0lEQVQoz9XRsQ3CMBCF4d8oUjpYAeQBKINYAWrIBukyQObIAGmhpkofCToWeIIVYAI6izgxpkO8zqfvLN8Z/jJK1Sn1q5MBMzSsaWQikIocyKn6Za9POw7usLfHAFTGude3spcRqDm3wUMW9u5BTXmMLmFmn2/DKKENbKtV4qCgJgvAjFruxpLiww8UlGBAG07EsjVaco0ywKj7hv00L0JaIixvQD/QAAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDE3LTAxLTA2VDE3OjM3OjE1KzA4OjAwW7cVswAAACV0RVh0ZGF0ZTptb2RpZnkAMjAxNy0wMS0wNlQxNzozNzoxNSswODowMCrqrQ8AAAAASUVORK5CYII="></h4>

				<iframe src="${ctx}/tark/leave/appli/hrEmpLeave/formView?id=${formId}&workbench=true" name="child" style="margin-top:20px;width: 100%;border: none;">
				</iframe>
			</c:if>	
			
			<c:if test='${formType eq "PmRiskClaim"}'>
				<h4>项目风险评估详情<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAQAAAAngNWGAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QAAKqNIzIAAAAHdElNRQfhAQYRJQ9sbxDaAAAAn0lEQVQoz9XRsQ3CMBCF4d8oUjpYAeQBKINYAWrIBukyQObIAGmhpkofCToWeIIVYAI6izgxpkO8zqfvLN8Z/jJK1Sn1q5MBMzSsaWQikIocyKn6Za9POw7usLfHAFTGude3spcRqDm3wUMW9u5BTXmMLmFmn2/DKKENbKtV4qCgJgvAjFruxpLiww8UlGBAG07EsjVaco0ywKj7hv00L0JaIixvQD/QAAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDE3LTAxLTA2VDE3OjM3OjE1KzA4OjAwW7cVswAAACV0RVh0ZGF0ZTptb2RpZnkAMjAxNy0wMS0wNlQxNzozNzoxNSswODowMCrqrQ8AAAAASUVORK5CYII="></h4>

				<iframe src="${ctx}/pm/risk/pmRiskAssessment/formView?id=${formId}&workbench=true" name="child" style="margin-top:20px;width: 100%;border: none;">
				</iframe>
			</c:if>
			
			<c:if test='${formType eq "PmCostClaim"}'>
				<h4>项目费用计划详情<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAQAAAAngNWGAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QAAKqNIzIAAAAHdElNRQfhAQYRJQ9sbxDaAAAAn0lEQVQoz9XRsQ3CMBCF4d8oUjpYAeQBKINYAWrIBukyQObIAGmhpkofCToWeIIVYAI6izgxpkO8zqfvLN8Z/jJK1Sn1q5MBMzSsaWQikIocyKn6Za9POw7usLfHAFTGude3spcRqDm3wUMW9u5BTXmMLmFmn2/DKKENbKtV4qCgJgvAjFruxpLiww8UlGBAG07EsjVaco0ywKj7hv00L0JaIixvQD/QAAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDE3LTAxLTA2VDE3OjM3OjE1KzA4OjAwW7cVswAAACV0RVh0ZGF0ZTptb2RpZnkAMjAxNy0wMS0wNlQxNzozNzoxNSswODowMCrqrQ8AAAAASUVORK5CYII="></h4>

				<iframe src="${ctx}/tark/pm/pmCostPlan/formView?id=${formId}&workbench=true" name="child" style="margin-top:20px;width: 100%;border: none;">
				</iframe>
			</c:if>
			
			<c:if test='${formType eq "PmCollectionPlanClaim"}'>
				<h4>项目收款计划详情<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAQAAAAngNWGAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QAAKqNIzIAAAAHdElNRQfhAQYRJQ9sbxDaAAAAn0lEQVQoz9XRsQ3CMBCF4d8oUjpYAeQBKINYAWrIBukyQObIAGmhpkofCToWeIIVYAI6izgxpkO8zqfvLN8Z/jJK1Sn1q5MBMzSsaWQikIocyKn6Za9POw7usLfHAFTGude3spcRqDm3wUMW9u5BTXmMLmFmn2/DKKENbKtV4qCgJgvAjFruxpLiww8UlGBAG07EsjVaco0ywKj7hv00L0JaIixvQD/QAAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDE3LTAxLTA2VDE3OjM3OjE1KzA4OjAwW7cVswAAACV0RVh0ZGF0ZTptb2RpZnkAMjAxNy0wMS0wNlQxNzozNzoxNSswODowMCrqrQ8AAAAASUVORK5CYII="></h4>

				<iframe src="${ctx}/pm/collection/pmCollectionPlanLine/?planId=${formId}&workbench=true" name="child" style="margin-top:20px;width: 100%;border: none;">
				</iframe>
			</c:if>
			
			<c:if test='${formType eq "PmCollectionPlanExeClaim"}'>
				<h4>项目收款发票申请详情<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAQAAAAngNWGAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QAAKqNIzIAAAAHdElNRQfhAQYRJQ9sbxDaAAAAn0lEQVQoz9XRsQ3CMBCF4d8oUjpYAeQBKINYAWrIBukyQObIAGmhpkofCToWeIIVYAI6izgxpkO8zqfvLN8Z/jJK1Sn1q5MBMzSsaWQikIocyKn6Za9POw7usLfHAFTGude3spcRqDm3wUMW9u5BTXmMLmFmn2/DKKENbKtV4qCgJgvAjFruxpLiww8UlGBAG07EsjVaco0ywKj7hv00L0JaIixvQD/QAAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDE3LTAxLTA2VDE3OjM3OjE1KzA4OjAwW7cVswAAACV0RVh0ZGF0ZTptb2RpZnkAMjAxNy0wMS0wNlQxNzozNzoxNSswODowMCrqrQ8AAAAASUVORK5CYII="></h4>

				<iframe src="${ctx}/pm/collection/pmCollectionPlanExe/formView_act?id=${formId}&workbench=true" name="child" style="margin-top:20px;width: 100%;border: none;">
				</iframe>
			</c:if>
			
			<c:if test='${formType eq "PmPaymentPlanClaim"}'>
				<h4>项目付款计划详情<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAQAAAAngNWGAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QAAKqNIzIAAAAHdElNRQfhAQYRJQ9sbxDaAAAAn0lEQVQoz9XRsQ3CMBCF4d8oUjpYAeQBKINYAWrIBukyQObIAGmhpkofCToWeIIVYAI6izgxpkO8zqfvLN8Z/jJK1Sn1q5MBMzSsaWQikIocyKn6Za9POw7usLfHAFTGude3spcRqDm3wUMW9u5BTXmMLmFmn2/DKKENbKtV4qCgJgvAjFruxpLiww8UlGBAG07EsjVaco0ywKj7hv00L0JaIixvQD/QAAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDE3LTAxLTA2VDE3OjM3OjE1KzA4OjAwW7cVswAAACV0RVh0ZGF0ZTptb2RpZnkAMjAxNy0wMS0wNlQxNzozNzoxNSswODowMCrqrQ8AAAAASUVORK5CYII="></h4>

				<iframe src="${ctx}/pm/payment/pmPaymentPlanLine/?planId=${formId}&workbench=true" name="child" style="margin-top:20px;width: 100%;border: none;">
				</iframe>
			</c:if>
			
			<c:if test='${formType eq "PmPaymentPlanExeClaim"}'>
				<h4>项目下包付款申请详情<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAQAAAAngNWGAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QAAKqNIzIAAAAHdElNRQfhAQYRJQ9sbxDaAAAAn0lEQVQoz9XRsQ3CMBCF4d8oUjpYAeQBKINYAWrIBukyQObIAGmhpkofCToWeIIVYAI6izgxpkO8zqfvLN8Z/jJK1Sn1q5MBMzSsaWQikIocyKn6Za9POw7usLfHAFTGude3spcRqDm3wUMW9u5BTXmMLmFmn2/DKKENbKtV4qCgJgvAjFruxpLiww8UlGBAG07EsjVaco0ywKj7hv00L0JaIixvQD/QAAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDE3LTAxLTA2VDE3OjM3OjE1KzA4OjAwW7cVswAAACV0RVh0ZGF0ZTptb2RpZnkAMjAxNy0wMS0wNlQxNzozNzoxNSswODowMCrqrQ8AAAAASUVORK5CYII="></h4>

				<iframe src="${ctx}/pm/payment/pmPaymentPlanExe/formView_act?id=${formId}&workbench=true" name="child" style="margin-top:20px;width: 100%;border: none;">
				</iframe>
			</c:if>
			</fieldset>
		</div>
	</form>
</body>
</html>
