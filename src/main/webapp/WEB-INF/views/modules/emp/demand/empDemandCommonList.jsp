<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>demand管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function deletebyBatch(){
			var flag=false;
			var ck = document.getElementsByName("chkMsgId23");
			for ( var i = 0; i < ck.length; i++) {
				if (ck[i].checked ==true) {
					flag=true;
				}
			}
		 if(flag){
			 var fm=document.getElementById("chkMsgId23");
				  var selecteds = document.getElementsByName("chkMsgId23");
				  var checkedSels = "";
				  for(var i = 0;i < selecteds.length;i++){
						if(selecteds[i].checked){
							checkedSels += selecteds[i].value+",";	
						}
				  }
				  checkedSels = checkedSels.substring(0,checkedSels.length-1);
				 toDelete1(checkedSels);
		 }else{
			 alert("请选择要删除的数据");
		 }
		}
		function toDelete1(checkedSels){
			if(confirm("确定删除吗?")){
			 location.href="${ctx}/demand/empDemandCommon/deleteBatch1?ids="+checkedSels;
			}
		}
		function toDelete(checkedSels){
			if(confirm("确定删除吗?")){
			loading('处理中，请稍等...');
				$.ajax({
			        cache: true,
			        type: "POST",
			        url:"${ctx}/demand/empDemandCommon/deleteBatch",
			        data:{"ids":checkedSels},
			        async: false,
			        success: function(data) {
			        	closeLoading();
			        	if(!data.success)
				 			$.jBox.alert(data.error);
						else {
							
							window.location.reload();
						}
			        }
			    });
			}
		}
		function doCheck(obj)  
		{  
		    var inputs=document.getElementsByTagName("input");  
		    for(var i=0;i<inputs.length;i++)  
		    {  
		        if(inputs[i].type=="checkbox" && inputs[i].id!="chkMsgId") //刷选出所有复选框  
		        {  
		            inputs[i].checked=obj.checked;   
		        }  
		    }  
		 }
		function toChkSon(obj){
			 if(obj==false) //当此复选框未选中 全选为未选  
			    {  
			        document.getElementById("chkMsgId").checked=false;  
			        return ;  
			    }  
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/demand/empDemandCommon/">demand列表</a></li>
		<shiro:hasPermission name="demand:empDemandCommon:edit"><li><a href="${ctx}/demand/empDemandCommon/form">demand添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="empDemandCommon" action="${ctx}/demand/empDemandCommon/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>需求编码：</label>
				<form:input path="demandCode" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>标题：</label>
				<form:input path="demandTitle" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>需求分类：</label>
				<form:select path="demandCategory" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictItemListL('demand_category','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>需求类型：</label>
				<form:select path="demandType" class="input-medium">
				<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictItemListL('demand_type','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>收集渠道：</label>
				<form:select path="demandSource" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictItemListL('demand_source','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>实际提出人：</label>
				<form:input path="proposePerson" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>提出时间：</label>
				<input name="beginInvalidDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${empDemandCommon.beginInvalidDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endInvalidDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${empDemandCommon.endInvalidDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
				<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictItemListL('demand_status','T')}" itemLabel="itemValue" itemValue="itemCode" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	 &nbsp;<input id="btnSubmit" class="btn btn-primary" onclick="deletebyBatch()"  type="button" value="删除"/> 
	<sys:message content="${message}"/>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed"   >
		<thead>
			<tr>
				<th ><input type="checkbox" name="chkMsgId" id="chkMsgId" onclick="doCheck(this)"   /></th>
				<th>需求编码</th>
				<th>标题</th>
				<th>需求分类</th>
				<th>需求类型</th>
				<th>需求收集渠道</th>
				<th>涉及系统</th>
				<th>实际提出时间</th>
				<th>需求实际提出人</th>
				<th>状态</th>
				<shiro:hasPermission name="demand:empDemandCommon:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="empDemandCommon">
			<tr>
				 <td>
				 <c:if test="${ empDemandCommon.status eq 'returned' ||empDemandCommon.status eq 'temporary' ||empDemandCommon.status eq 'returnrec'}" >
				 <input type="checkbox" onclick="toChkSon(this.checked); " id="chkMsgId23" name="chkMsgId23" value="${empDemandCommon.id }"/>
				 </c:if>
				 </td>
				<td>
					${empDemandCommon.demandCode}
				</td>
				<td>
					${empDemandCommon.demandTitle}
				</td>
				<td>
					${fns:getDictItemValueL(empDemandCommon.demandCategory,'demand_category','T','')}
				</td>
				<td>
					${fns:getDictItemValueL(empDemandCommon.demandType,'demand_type','T','')}
				</td>
				<td>
					${fns:getDictItemValueL(empDemandCommon.demandSource,'demand_source','T','')}
				</td>
				<td>
					${empDemandCommon.involveSys}
				</td>
				<td>
					<fmt:formatDate value="${empDemandCommon.invalidDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${empDemandCommon.proposePersonName}
				</td>
				<td>
					${fns:getDictItemValueL(empDemandCommon.status,'demand_status','T','')}
				</td>
				<shiro:hasPermission name="demand:empDemandCommon:edit"><td>
    				<a href="${ctx}/demand/empDemandCommon/form?id=${empDemandCommon.id}">修改</a>
					<a href="${ctx}/demand/empDemandCommon/delete?id=${empDemandCommon.id}" onclick="return confirmx('确认要删除该demand吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>