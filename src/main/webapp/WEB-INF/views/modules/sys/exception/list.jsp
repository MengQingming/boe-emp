<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
    function page(n, s) {
        queryList(s,n);
        return false;
    }
    function showExceptionMessage(id) {
        top.$.jBox.open("iframe:${ctx}/sys/exc/findExceptionMessage?id="+id,
			"异常详细信息",
			1024,
			$(top.document).height()-240,
			{
                draggable: true, /* 是否可以拖动窗口 */
                dragLimit: true, /* 在可以拖动窗口的情况下，是否限制在可视范围 */
            buttons:{"关闭":true},
            loaded:function(h){
                $(".jbox-content", top.document).css("overflow-y","hidden");
            }
       	 	});
    }
</script>
<div id="content" style="width: 100%;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed css-contentTable">
		<thead>
		<tr>
			<th>应用NO</th>
			<th>处理状态</th>
			<th>异常类型</th>
			<th>异常编码</th>
			<th>异常消息</th>
			<th>创建日期</th>
			<th>状态修改</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="exc">
			<tr>
				<td>${exc.appNo}</td>
				<td>${exc.status}</td>
				<td>${exc.exceptionType}</td>
				<td>${exc.exceptionCode}</td>
				<td><a href="javascript:void(0);" onclick="showExceptionMessage(${exc.id})">查看详情</a></td>
				<td><fmt:formatDate value="${exc.createDate}" type="both"/></td>
				<td>
					<c:if test="${exc.status != '已处理'}">
						<a href="${ctx}/sys/exc/updateExcStatus?id=${exc.id}" onclick="return confirmx('确认要修改状态为\'已处理\'吗？', this.href)">
							修改为：已处理
						</a>
					</c:if>
					<c:if test="${exc.status == '已处理'}">
						已处理
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>