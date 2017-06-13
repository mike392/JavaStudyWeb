<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
    <fmt:setLocale value="${locale}" scope="session" />
    <fmt:setBundle basename="resources.pagecontent" var="rb"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">         
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script> 
	<title>Главная</title>
	</head>
	<body>
		<div class="container">
		<jsp:useBean id="calendar" class="java.util.GregorianCalendar"/>
		<nav class="navbar navbar-inverse bg-primary navbar-toggleable-md" style="background-color: #e3f2fd;">
		  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <a class="navbar-brand" href="#">Меню</a>
		  <div class="collapse navbar-collapse" id="navbarNavDropdown">
		    <ul class="navbar-nav">
		      <li class="nav-item active">
		        <a class="nav-link" href="${pageContext.request.contextPath}/controller?time=${calendar.timeInMillis}&command=time">Посчитать время <span class="sr-only">(current)</span></a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=locale">Локализация</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/pages/upload.jsp">Загрузить файл XML</a>
		      </li>
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          Отобразить парсинг
		        </a>
		        <div class="dropdown-menu">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?parser=SAX&command=parse">SAX парсер</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?parser=DOM&command=parse">DOM парсер</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?parser=STAX&command=parse">StAX парсер</a>
		        </div>
		      </li>
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          Локализацию поменять
		        </a>
		        <div class="dropdown-menu">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=localechange&locale=ru_ru">Русская</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=localechange&locale=en_us">Английская</a>
		        </div>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=logout">Выйти</a>
		      </li>
		    </ul>
		  </div>
		</nav>
		${rb.getResourceBundle().getLocale() }
		${locale}
		</div>
	</body>
</html>