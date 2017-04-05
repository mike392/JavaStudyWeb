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
<title>Загрузка файла</title>
</head>
<body>
<div class="container">
	<div class="form-group">
		<form action="${pageContext.request.contextPath}/controller?command=upload" method="post" enctype="multipart/form-data">
			<input class="btn btn-primary" type="submit" value="Загрузить файл">
     		<input type="file" name="file">
     	</form>
	</div>
	<form action="${pageContext.request.contextPath}/pages/main.jsp">
			<input class="btn btn-primary" type="submit" value="Назад">
	</form>
	<c:choose>
    <c:when test="${info != null}">
		<div class="alert alert-success" role="alert">
  			<strong>${info}</strong>
		</div>
        <br />
    </c:when>    
    <c:when test="${err != null}">
		<div class="alert alert-warning" role="alert">
  			<strong>${err}</strong>
		</div>
        <br />
    </c:when> 
</c:choose>
	
</div>
</body>
</html>