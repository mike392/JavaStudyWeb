<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib prefix="ctg" uri="customtags" %>
    <fmt:setBundle basename="resources.pagecontent" var="rb"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">         
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script> 
	<title><fmt:message key="index.label.title" bundle="${rb}" /></title>
	</head>
	<body>
		<div class="wrapper container">
		<nav class="navbar navbar-inverse bg-primary navbar-toggleable-md" style="background-color: #e3f2fd;">
		  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <a class="navbar-brand" href="#"><fmt:message key="index.label.menu" bundle="${rb}" /></a>
		    <ul class="nav navbar-nav navbar-right">
		      <li class="nav-item active">
		        <a class="nav-link" href="pages/login.jsp"><fmt:message key="index.label.entry" bundle="${rb}" /><span class="sr-only">(current)</span></a>
		      </li>
		    </ul>
		</nav>
				
		</div>
		${locale}
		${rb.getResourceBundle().getLocale()}
<ctg:footer/>
<ctg:styles/>
	</body>
</html>