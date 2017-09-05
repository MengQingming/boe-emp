<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
			<%
				Object statusObj = request.getAttribute("status");
				Object sizeObj = request.getAttribute("size");
				Object dataObj = request.getAttribute("data");
				String status = statusObj.toString();
				String size = sizeObj == null ? "" : sizeObj.toString();
				String data = dataObj == null ? "null" : dataObj.toString();
			%>
			<script type="text/javascript">
				var status = "<%=status %>";
				if(status == "overSize"){
					window.parent.top.$.jBox.alert("该附件超过<%=size %>");
				}else if(status == "exist"){
					window.parent.top.$.jBox.alert('该附件已经存在！');
				}else if(status == "success"){
					var jsonData = <%=data %>;
					window.parent.top.$.jBox.tip("附件上传成功！", 'info');
					window.parent.spliceHtml(jsonData);
				}
				
// 			    if(window.parent.document.getElementById("") != null){
// 					window.parent.document.getElementById("").value = "";
// 			    }
// 				window.parent.removeUpding("");
// 				window.parent.errormsg("上传成功");

			</script>
	</head>
	<body>
	</body>
</html>
