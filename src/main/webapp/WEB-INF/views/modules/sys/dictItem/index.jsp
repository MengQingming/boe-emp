<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>字典项管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
    $(document).ready(function() {
      var obj = $("#left",window.parent.document).find("a");
      var index;
      for(var i=0;i<obj.length;i++){
        var str = obj[i].innerText;
        if($.trim(str)=="字典项管理"){
          index=i;
        }
      }
      var className = obj[index-1].parentNode.className;
      if(className.indexOf("active")>=0)
		{
		   obj[index-1].parentNode.removeAttribute("class","active");
      	   obj[index].parentNode.setAttribute("class","menu3-10 hide active");
		}
		
		changeDict();
		queryList(30,1,'${dictItem.dictCodeQuery}');
    });
    
    function changeDict(){
    	$("#dictCodeQuery").val("");
    	$("#dictCodeQuery").empty();
    	$.ajax({ 
			url: "${ctx}/sys/dictItem/getDictByAppNo", 
			data:{
				appNo:$("#appNo").val()
			},
			type:"get",
			cache:false,
			success: function(data){
				$("#dictCodeQuery").append("<option value=''>　</option>"); 
				for(var i=0;i<data.length;i++){
				  if(data[i].dictCode == '${dictItem.dictCodeQuery}'){
	        			$("#dictCodeQuery").append("<option value='"+data[i].dictCode+"' selected='selected'>"+data[i].dictName+"</option>"); 
	        		}else{
	        			$("#dictCodeQuery").append("<option value='"+data[i].dictCode+"'>"+data[i].dictName+"</option>"); 
	        		}
	        	}
      		}
      	});
    }

	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/dictItem/">字典项列表</a></li>
		<shiro:hasPermission name="sys:dictItem:edit">
			<li><a href="${ctx}/sys/dictItem/form?sort=10">字典项添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dictItem" action="${ctx}/sys/dictItem/" method="post"
		class="breadcrumb form-search css-searchForm">
		<ul class="ul-form">
		  <li>
			<label>App：</label>
			<form:select id="appNo" path="appNo" class="input-medium" onchange="changeDict()">
				<form:option value="" label="　" />
				<form:option value="T" label="通用" />
				<form:options items="${fns:getAllApp()}" itemLabel="appName"
					itemValue="appNo" htmlEscape="false" />
			</form:select>
		  </li>
		  <li>
			<label>所属字典组：</label>
			<form:select id="dictCodeQuery" path="dictCodeQuery" class="input-medium">
				<form:option value="" label="　" />
				<form:options items="${fns:getAllDict()}" itemLabel="dictName"
					itemValue="dictCode" htmlEscape="false" />
			</form:select>
		  </li>
		  <li>
			<label>是否启用：</label>
			<form:select id="status" path="status" cssStyle="width:62px;">
				<form:option value="" label="" />
				<form:options
					items="${fns:getDictItemListL('yes_no','T')}"
					itemLabel="itemValue" itemValue="itemCode" htmlEscape="false" />
			</form:select>
		   </li>
		   <li>
			<label>语言：</label>
			<form:select id="language" path="language">
				<form:options
					items="${fns:getDictItemListL('sys_language','T')}"
					itemLabel="itemValue" itemValue="itemCode" htmlEscape="false" />
			</form:select>
		   </li>
		   <li style="margin-left: 20px;">
			<input id="btnSubmit" class="btn btn-primary" type="button" onclick="query()" value="查询" />
		   </li>
		   </ul>
	</form:form>
	<div id="content">
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed css-contentTable">
		<thead>
			<tr>
				<th>字典项名称</th>
				<th>字典项编码</th>
				<th>App</th>
				<th>所属字典组</th>
				<th>所属字典编码</th>
				<th>是否启用</th>
				<th>语言</th>
				<th>所属公司</th>
				<th>描述</th>
				<shiro:hasPermission name="sys:dictItem:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
	</table>
	<div class="pagination">${page}</div>
</div>
<script type="text/javascript">
function queryList(pageSize,pageNo,dictCode){
		loading('加载中，请稍等...');
		var dictCodeQuery = $("#dictCodeQuery").val();
		if(dictCode){
			dictCodeQuery = dictCode;
		}
		$.ajax({ 
			url: "${ctx}/sys/dictItem/list", 
			data:{
				pageSize:pageSize,
				pageNo:pageNo,
				status:$("#status").val(),
				appNo:$("#appNo").val(),
				dictCode : dictCodeQuery,
				language : $("#language").val()
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