<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>异常消息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
<div>
	<pre>${sysException.exceptionMessage}</pre>
</div>
</body>