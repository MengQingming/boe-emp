<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>	
	<style>
		#css-pMessage{
			margin-left: 30px;
		}
		label.error{background:url("");padding-left:18px;padding-bottom:2px;font-weight:bold;color:#ea5200;font-size:12px;position:absolute;right:0;top:28px;}
	</style>
	<title>审批</title>
		<script type="text/javascript">
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
	</head>
<body>
<link rel="stylesheet" href="${ctxStatic}/common/table.css">
<div id="content" style="width: 100%;">
	<div>
	<label>流程运行图
	<img src="${ctxStatic}/images/extend.png" title="展开" onclick="extendAct()" id="extendPic">
	<img alt="收缩" src="${ctxStatic}/images/shrink.png" onclick="shrinkAct()" id="shrinkPic" style="display: none;">
	<%-- <a href="#" onclick="javascript:top.$.jBox.open('iframe:${ctx}/runact?no=${formId}', '流程预览',700,400, { buttons: { '关闭': true},loaded:function(h){$('.jbox-content', top.document).css('overflow-y','hidden');} });"><img src="${ctxStatic}/images/data1.png" title="流程预览" width="13px" style="margin-top: -3px;"/></a>
 --%>	</label>
	<div id="actPic" style="display: none;">
		<img alt="" src="${ctx}/bpms/processTrace/${pid}">
	</div>
	</div>
	<sys:message content="${message}"/>
	审批记录
	<table id="contentTable" class="table table-striped table-bordered table-condensed css-contentTable">
		<thead>
		   <tr>
		      <th>处理时间</th>
		      <!--<th>接收时间</th> -->
		      <th>环节名称</th>
		      <!--<th>状态</th>-->
		      <th>审批人</th>
		      <!--<th>审批人部门</th> -->
 		      <th>操作描述</th>
		      <th>审批意见</th>   
		      <!--<th>处理时间</th> -->
		      <!--<th>来源</th> --> 
		   </tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="lists">
		 	<tr>
		 	<td>
		 		<c:choose>
		 			<c:when test="${lists.tempVal ne null}">
						${lists.tempVal}				
		 			</c:when>
		 			<c:otherwise>
					 	<c:if test="${ lists.taskEndTime!=null}">
					 		<fmt:formatDate value="${lists.taskEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			            </c:if>
			            <c:if test="${lists.taskEndTime==null}">
			            	处理中
			            </c:if>
		 			</c:otherwise>
		 		</c:choose>
		    </td>
		 	<!--
		 	<td><fmt:formatDate value="${lists.taskStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		 	-->
		 	<td>${lists.actDefName}</td>
		 	<!--  
		 		<td>
					<c:if test="${lists.taskStatus=='12'}">已办</c:if>
					<c:if test="${lists.taskStatus=='10'}">未办</c:if>
				</td>
			-->	
				<td>${lists.assigneeFullname}</td>
				<!-- <td>${lists.assigneeGroupName}</td>-->
				<td>${lists.bizStateDesc}</td>
				<td>${lists.commentContent}</td>
				
				<!--<td><fmt:formatDate value="${lists.taskEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>-->
				<!--<td>${tasksMap[lists.taskSource]}</td>-->
		 	</tr>
		 </c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>
