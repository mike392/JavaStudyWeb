<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
    <fmt:setLocale value="${locale}" scope="session" />
    <fmt:setBundle basename="resources.pagecontent" var="rb"/>
    <!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<ctg:imports/>
<title><fmt:message key="index.label.title" bundle="${rb}" /></title>
</head>
<body>
<div class="container">
<c:set var="currentNumber" value="118000"/>
<fmt:message key="index.label.menu" bundle="${rb}" />

<br>
<fmt:formatNumber value="${currentNumber}" />
<br>
<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="medium"/>
<br>
<fmt:formatDate value="${now}" type="both" dateStyle="medium" timeStyle="long"/>
	<form action="${pageContext.request.contextPath}/pages/adminMain.jsp">
		<input class="btn btn-primary" type="submit" value="Назад">
	</form>
	
		<br>

	</div>
	${rb.getResourceBundle().getLocale() }
		${locale}
		${rb.getResourceBundle().keySet() }

</body>
</html>