<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分配角色</title>
	<meta name="decorator" content="blank"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script type="text/javascript">
	
		var officeTree;
		var selectedTree;//zTree已选择对象
		
		// 初始化
		$(document).ready(function(){
			officeTree = $.fn.zTree.init($("#officeTree"), setting, officeNodes);
			selectedTree = $.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
			$('html').height('100%');
		});

		var setting = {view: {selectedMulti:false,nameIsHTML:true,showTitle:false,dblClickExpand:false},
				data: {simpleData: {enable: true}},
				callback: {onClick: treeOnClick}};
		
		var officeNodes=[
	            <c:forEach items="${officeList}" var="office">
	            {id:"${office.id}",
	             pId:"${not empty office.parent?office.parent.id:0}", 
	             name:"${office.groupName}"},
	            </c:forEach>];
	
		var pre_selectedNodes =[
   		        <c:forEach items="" var="user">
   		        {id:"${user.id}",
   		         pId:"0",
   		         name:"<font color='red' style='font-weight:bold;'>${user.userName}${user.fullName}</font>"},
   		        </c:forEach>];
		
		var selectedNodes =[
		        <c:forEach items="" var="user">
		        {id:"${user.id}",
		         pId:"0",
		         name:"<font color='red' style='font-weight:bold;'>${user.userName}${user.fullName}</font>"},
		        </c:forEach>];
		
		//var pre_ids = "${selectIds}".split(",");
		//var oids = "${selectIds}".split(",");
		var ids = new Array();
        var userNames = new Array();

		//点击选择项回调
		function treeOnClick(event, treeId, treeNode, clickFlag){
			$.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);
			if("officeTree"==treeId){
				$.get("${ctx}/sys/role/users?groupId=" + treeNode.id, function(userNodes){
					$.fn.zTree.init($("#userTree"), setting, userNodes);
				});
				$("#grouplabel").html(treeNode.name);
				$("#groupIdhidden").val(treeNode.id);
			}
			if("userTree"==treeId){
				if(/* $.inArray(String(treeNode.id), oids)<0 &&  */$.inArray(String(treeNode.id), ids)<0){
					selectedTree.addNodes(null, treeNode);
					ids.push(String(treeNode.id));
                    userNames.push(String(treeNode.name));     //出差申请单 使用, 请勿删除
				}else if($.inArray(String(treeNode.id), ids)>=0){
					
				}else{
					top.$.jBox.tip("用户"+treeNode.name+"已有此角色", 'info');
				}
			};
			if("selectedTree"==treeId){
				/* if($.inArray(String(treeNode.id), pre_ids)<0){
					
				}else{
					top.$.jBox.tip("角色原有成员不能清除！", 'info');
				} */
				selectedTree.removeNode(treeNode);
				ids.splice($.inArray(String(treeNode.id), ids), 1);
                userNames.splice($.inArray(String(treeNode.name), userNames), 1);
			}
		};
		function clearAssign(){
			var submit = function (v, h, f) {
			    if (v == 'ok'){
					var tips="";
					tips = "已选人员清除成功！";
					ids= new Array();
					selectedNodes=pre_selectedNodes;
					$.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
			    	top.$.jBox.tip(tips, 'info');
			    } else if (v == 'cancel'){
			    	// 取消
			    	top.$.jBox.tip("取消清除操作！", 'info');
			    }
			    return true;
			};
			tips="确定清除角色【${role.roleName}】下的已选人员？";
			top.$.jBox.confirm(tips, "清除确认", submit);
		};
		function findUser(){
			var groupId = $("#groupIdhidden").val();
			var userName = $("#userNameInput").val();
			$.ajax({ 
	           type: "post", 
	           url: "${ctx}/sys/role/users",
	           data: {"groupId":groupId,"userName":userName},
	           dataType: "json", 
	           success: function (data) { 
	        	   $.fn.zTree.init($("#userTree"), setting, data);
	           }
	   		});
		}
	</script>
	<style>
		body{
			height: 100%;
		}
	</style>
</head>
<body>
	<div id="assignRole" class="row-fluid span12" style="height: 100%;overflow-x: hidden">
		<div class="span4" style="overflow-x: scroll;height: 100%">
			<p>组织机构：</p>
			<div id="officeTree" class="ztree"></div>
		</div>
		<div class="span1" style="border-right: 1px solid #A8A8A8;height: 100%;width: 1px;">
		</div>
		<div class="span4">
			<p>待选人员：</p>
			<label id="grouplabel"></label>
			<input id="groupIdhidden" type="hidden" />
			<table>
				<tr>
					<td><input type="text" id="userNameInput" placeholder="输入姓名"  style="width: 120px;"/></td>
					<td><input id="btnSubmit" onclick="findUser()" class="btn btn-primary" style="margin-top: -14px;" type="button" value="查询"/></td>
				</tr>
			</table>
			
			<div id="userTree" class="ztree"></div>
		</div>
		<div class="span1" style="border-right: 1px solid #A8A8A8;height: 100%;width: 1px;"></div>
		<div class="span3">
			<p>已选人员：</p>
			<div id="selectedTree" class="ztree"></div>
		</div>
	</div>
</body>
</html>
