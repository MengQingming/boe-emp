<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>运行日志</title>
	<meta name="decorator" content="default"/>
</head>
<body>
<div>
	<pre>${schedulerLog.runLog}</pre>
</div>
</body>