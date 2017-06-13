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
	<title><fmt:message key="main.label.title" bundle="${rb}" /></title>
	</head>
	<body>
		<div class="container">
		<nav class="navbar navbar-inverse bg-primary navbar-toggleable-md" style="background-color: #e3f2fd;">
		  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <a class="navbar-brand" href="#"><fmt:message key="user.label.menu" bundle="${rb}" /></a>
		  <div class="collapse navbar-collapse" id="navbarNavDropdown">
		    <ul class="navbar-nav">
		      <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=subscription_view"><fmt:message key="main.label.subscriptionview" bundle="${rb}" /></a>
		      </li>
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          <fmt:message key="main.label.changelocale" bundle="${rb}" />
		        </a>
		        <div class="dropdown-menu">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=locale_change&locale=ru_ru"><fmt:message key="main.label.ru_locale" bundle="${rb}" /></a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=locale_change&locale=en_us"><fmt:message key="main.label.en_locale" bundle="${rb}" /></a>
		        </div>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="main.label.quit" bundle="${rb}" /></a>
		      </li>
		    </ul>
		  </div>
		</nav>
		</div>
	</body>
</html>