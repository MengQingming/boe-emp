<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>异常处理</title>
	<meta name="decorator" content="default" />
</head>
<body>
<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/sys/exc/">异常列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="sysException" action="${ctx}/sys/exc/" method="post" class="breadcrumb form-search css-searchForm">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	<ul class="ul-form">
		<li>
			<label style="width: 100px">处理状态：</label>
			<form:radiobutton path="status" value="未处理"/>未处理
			<form:radiobutton path="status" value="已处理"/>已处理
		</li>
		<li>
			<input id="btnSubmit" class="btn btn-primary " type="button" onclick="query()" value="查询" />
			<input id="css-back" class="btn btn-primary" type="button" value="返回"/>
		</li>
	</ul>
</form:form>
<div id="fixedTop" class="breadcrumb form-search">
	<input class="btn btn-primary css-search" type="button" value="查询">
</div>
<div id="content" style="width: 100%;">
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed css-contentTable">

		<thead>
		<tr>
			<th>应用NO</th>
			<th>处理状态</th>
			<th>异常类型</th>
			<th>异常编码</th>
			<th>异常消息</th>
			<th>创建日期</th>
			<th>操作</th>
		</tr>
		</thead>

		<tbody></tbody>
	</table>
	<div class="pagination">${page}</div>
</div>
<script type="text/javascript">
    $(function(){
        queryList(30,1);
    });

    function queryList(pageSize,pageNo){
        loading('加载中，请稍等...');
        var chkRadio = $('input:radio[name="status"]:checked').val();
        $.ajax({
            url: "${ctx}/sys/exc/list",
            data:{
                pageSize:pageSize,
                pageNo:pageNo,
                status:chkRadio
            },
            type:"post",
            cache:false,
            context: document.body,
            success: function(html){
                closeLoading();
                $("#css-back").click();
                $("#content").replaceWith(html);
                $('.table img').height(parseInt($('.table').css('line-height')) * 0.8 + 'px');
            }
        });
    }
    function query(){
        queryList(30,1);
    }
</script>
</body>
</html>