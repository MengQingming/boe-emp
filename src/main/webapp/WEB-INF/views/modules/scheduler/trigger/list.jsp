<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
	function page(n, s) {
		queryList(s,n);
		return false;
	}
</script>
<div id="content" style="width: 100%;">
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		   <tr>
			    <th>任务名称</th>
			    <th>任务编码</th>
			    <th>触发类型名称</th>
			    <th>CRON表达式</th>
			    <th>轮询周期时长</th>
			    <th>指定日期</th>
			    <th>是否启用</th>
				<th>创建日期</th>
				<shiro:hasPermission name="scheduler:trigger:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="trigger">
			<tr>
			    <td>${trigger.jobName}</td>
			    <td>${trigger.jobCode}</td>
			    <td>${trigger.triggerTypeName}</td>
			    <td>${trigger.triggerCron}</td>
			    <td>${trigger.triggerSleep}</td>
			    <td><fmt:formatDate value="${trigger.triggerDate}" pattern="yyyy-MM-dd hh:mm:ss.SSS"/></td>
			    <td>${fns:getDictItemValueL(trigger.triggerStatus,'yes_no','1','是')}</td>
				<td><fmt:formatDate value="${trigger.creationDate}" pattern="yyyy-MM-dd"/></td>
				<shiro:hasPermission name="scheduler:trigger:edit">
				<td>
    				<a href="${ctx}/scheduler/trigger/form?id=${trigger.id}"><img src="${ctxStatic}/images/02.png" onMouseOver="this.src='${ctxStatic}/images/2.png'" onMouseOut="this.src='${ctxStatic}/images/02.png'" title="修改"></a>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>