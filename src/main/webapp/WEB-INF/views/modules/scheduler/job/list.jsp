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
		      <th>任务名称</th>
		      <th>任务编码</th>
		      <th>任务项目路径</th>
		      <th>调度执行方法名</th>
		      <th>IP白名单</th>
		      <th>授权服务</th>
			  <th>下次次调度时间</th>
			  <th>调度次数</th>
		      <th>启用</th>
		      <shiro:hasPermission name="scheduler:job:edit">
				<th>操作</th>
			  </shiro:hasPermission>
		   </tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="job">
			<tr>
			    <td><a href="${ctx}/scheduler/job/form?id=${job.id}">${job.jobName}</a></td>
				<td>${job.jobCode}</td>
				<td>${job.jobPath}</td>
				<td>${job.jobMethod}</td>
				<td>${job.jobIp}</td>
				<td>${job.jobService}</td>
				<td><fmt:formatDate value="${job.jobNextDate}" pattern="yyyy-MM-dd hh:mm:ss.SSS"/></td>
				<td>${job.jobNum}</td>
				<td>${fns:getDictItemValueL(job.jobStatus,'yes_no','1','是')}</td>
				<shiro:hasPermission name="scheduler:job:edit">
					<td>
	    				<a href="${ctx}/scheduler/job/form?id=${job.id}"><img src="${ctxStatic}/images/02.png" onMouseOver="this.src='${ctxStatic}/images/2.png'" onMouseOut="this.src='${ctxStatic}/images/02.png'" title="修改"></a>
						<a href="${ctx}/scheduler/trigger?jobCode=${job.jobCode}"><img src="${ctxStatic}/images/data.png" onMouseOver="this.src='${ctxStatic}/images/data1.png'"onMouseOut="this.src='${ctxStatic}/images/data.png'" title="触发器"></a>
						<a href="javascript:manual('${job.jobCode}')"><img src="${ctxStatic}/images/export2.png" onMouseOver="this.src='${ctxStatic}/images/export1.png'"onMouseOut="this.src='${ctxStatic}/images/export2.png'" title="手动调度"></a>
						<c:if test="${job.jobLock != 'noLock'}"><a href="javascript:openLock('${job.jobCode}')"><img src="${ctxStatic}/images/password.png" onMouseOver="this.src='${ctxStatic}/images/password.png'"onMouseOut="this.src='${ctxStatic}/images/password.png'" title="解锁"></a></c:if>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
		function manual(jobCode) {
			loading('处理中，请稍等...');
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:"${ctx}/scheduler/job/manual",
		        data:{jobCode:jobCode},
		        async: false,
		        success: function(data) {
		        	closeLoading();
		        	if(!data.success)
			 			$.jBox.alert(data.error);
					else {
						$.jBox.alert("手动调度任务执行成功");
						location.href = "${ctx}/scheduler/job";
					}
		        }
		    });
		}
		
		function openLock(jobCode) {
			loading('处理中，请稍等...');
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:"${ctx}/scheduler/job/openLock",
		        data:{jobCode:jobCode},
		        async: false,
		        success: function(data) {
		        	closeLoading();
		        	if(!data.success)
			 			$.jBox.alert(data.error);
					else {
						location.href = "${ctx}/scheduler/job";
					}
		        }
		    });
		}
	</script>
</div>