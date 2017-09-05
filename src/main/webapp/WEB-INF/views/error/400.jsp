<%
response.setStatus(400);

// 获取异常类
Exception ex = (Exception)request.getAttribute("ex");

// 编译错误信息
StringBuilder sb = new StringBuilder("错误信息：");
if (ex != null) {
	if (ex instanceof BindException) {
		for (ObjectError e : ((BindException)ex).getGlobalErrors()){
			sb.append("☆" + e.getDefaultMessage() + "(" + e.getObjectName() + ")\n");
		}
		for (FieldError e : ((BindException)ex).getFieldErrors()){
			sb.append("☆" + e.getDefaultMessage() + "(" + e.getField() + ")\n");
		}
		LoggerFactory.getLogger("400.jsp").warn(ex.getMessage(), ex);
	}else if (ex instanceof ConstraintViolationException) {
		for (ConstraintViolation<?> v : ((ConstraintViolationException)ex).getConstraintViolations()) {
			sb.append("☆" + v.getMessage() + "(" + v.getPropertyPath() + ")\n");
		}
	} else {
		sb.append("☆" + ex.getMessage());
	}
} else {
	sb.append("内部错误.\n\n");
	Throwable re = Exceptions.getThrowable(request);
// Jsp 页面异常信息打印
	if (re != null){
		LoggerFactory.getLogger("500.jsp").error(re.getMessage(), re);
	}
}

// 如果是异步请求或是手机端，则直接返回信息
if (Servlets.isAjaxRequest(request)) {
	out.print(sb);
}

// 输出异常信息页面
else {
%>
<%@page import="com.boe.common.utils.Exceptions"%>
<%@page import="com.boe.common.utils.StringUtils"%>
<%@page import="com.boe.common.web.Servlets"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="org.springframework.validation.BindException"%>
<%@page import="org.springframework.validation.FieldError,org.springframework.validation.ObjectError"%>
<%@page import="javax.validation.ConstraintViolation"%>
<%@page import="javax.validation.ConstraintViolationException"%>
<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>400 - 请求出错</title>
	<meta name="decorator" content="default" />
</head>
<body>
	<div class="container-fluid">
		<div class="page-header"><h1>参数有误,服务器无法解析.</h1></div>
		<div class="errorMessage">
			<%=StringUtils.toHtml(sb.toString())%> <br/>
		</div>
		<script>try{top.$.jBox.closeTip();}catch(e){}</script>
	</div>
</body>
</html>
<%
} out = pageContext.pushBody();
%>