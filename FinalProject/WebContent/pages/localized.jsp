<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
    <fmt:setLocale value="${locale}" scope="session" />
    <fmt:setBundle basename="resources.pagecontent" var="rb"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">         
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
<title><fmt:message key="label.title" bundle="${rb}" /></title>
</head>
<body>
<div class="container">
<c:set var="currentNumber" value="118000"/>
<fmt:message key="label.text" bundle="${rb}" />

<br>
<fmt:formatNumber value="${currentNumber}" />
<br>
<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="medium"/>
<br>
<fmt:formatDate value="${now}" type="both" dateStyle="medium" timeStyle="long"/>
	<form action="${pageContext.request.contextPath}/pages/main.jsp">
		<input class="btn btn-primary" type="submit" value="Назад">
	</form>
	
		<br>
${rb.getResourceBundle().getLocale() }
		${locale}
	</div>
	

</body>
</html>