<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
function page(n,s) {
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	queryList();
}
</script>
<script src="${ctxStatic}/common/table.js"></script>
<link rel="stylesheet" href="${ctxStatic}/common/table.css">
<div id="content" style="width: 100%;">
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed css-contentTable">
		<thead>
		   <tr>
			    <th>调度任务名称</th>
			    <th>调度任务编码</th>
			    <th>调度任务类型</th>
				<th>调度执行IP</th>
				<th>调度执行服务</th>
				<th>调度开始时间</th>
				<th>调度结束时间</th>
				<th>是否成功</th>
				<th>调度信息</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="log">
			<tr>
			    <td>${log.jobName}</td>
			    <td>${log.jobCode}</td>
			    <td>${log.jobRunningType == 'M'?'手动调度':'自动调度'}</td>
			    <td>${log.jobIp}</td>
			    <td>${log.jobService}</td>
			    <td><fmt:formatDate value="${log.jobStartDate}" pattern="yyyy-MM-dd hh:mm:ss.SSS"/></td>
			    <td><fmt:formatDate value="${log.jobStopDate}" pattern="yyyy-MM-dd hh:mm:ss.SSS"/></td>
				<td>${log.jobStatus == '1'?'成功':'失败'}</td>
				<td>
					<c:if test="${empty log.jobShowMsg}">
						<a class="css-msg" href="javascript:showExcMsg('excMsg${log.id}')">异常信息</a>
						<div id="excMsg${log.id}" style="display: none;">
							<div style="line-height: 25px;margin:1px;word-break:break-all;">
								${log.jobException}
							</div>
						</div>
					</c:if>
					${log.jobShowMsg}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
	function showExcMsg(id){
	    $.jBox.open('id:'+id, "异常信息",800,500,{buttons:{"关闭":true}});
		$('#jbox').css({'position':'fixed','top':'30px'});
	}
	</script>
</div>