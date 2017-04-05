<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">         
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>  
<title>Результаты парсинга</title>
</head>
<body>
<div class="container">

<c:choose>
    <c:when test="${res != null}">
		<div class="alert alert-success" role="alert">
  			<strong>Парсинг длился ${diff} мсек</strong>
		</div>
		 <table class="table">
		    <tr>
		        <th>Number</th>
		        <th>Certification number</th>
		        <th>Country</th>
		        <th>Dosage amount</th>
		        <th>Periodicity</th>
		        <th>Expiration date</th>
		        <th>Medicine group</th>
		    </tr>
		    <c:forEach items="${res}" var="item" varStatus="status">
		        <tr>
		            <td>${status.count}</td>
		            <td>${item.certNumber}</td>
		            <td>${item.country}</td>
		            <td>${item.dosageAmount}</td>
		            <td>${item.dosagePeriodicity}</td>
		            <td>${item.expirationDate}</td>
		            <td>${item.group}</td>
		        </tr>
		        <br>
		    </c:forEach>
		</table> 
        <br />
    </c:when>    
    <c:otherwise>
    <div class="alert alert-danger" role="alert">
  		<strong>${err}</strong>
	</div>
    </c:otherwise>
</c:choose>
<form action="${pageContext.request.contextPath}/pages/main.jsp">
		<input class="btn btn-primary" type="submit" value="Назад">
	</form>
</div>
</body>
</html>