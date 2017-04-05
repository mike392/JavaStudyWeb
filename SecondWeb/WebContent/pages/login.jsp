<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">         
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script> 
<title>Вход в систему</title>
</head>
<body>
<div class="container">
<div class="col-sm-4 col-sm-offset-1">
	    <form class="r-form" method="post" action="${pageContext.request.contextPath}/controller?command=login">       
	      <h2 class="form-signin-heading">Вход в систему</h2>
	      <div class="form-group">
	      	<input type="text" class="form-control" name="username" placeholder="Пользователь" required="" autofocus="" />
	      </div>
	      <div class="form-group">
	      	<input type="password" class="form-control" name="password" placeholder="Пароль" required=""/>
	      </div>      
	      <button class="btn btn-lg btn-primary btn-block" type="submit" name="command" value="login">Вход</button> 
	    </form>
	    <br>
	    <div class="form-group">
	    <label class="sr-only" for="r-form-last-name">Signup</label>
	    <a class="btn btn-lg btn-primary btn-block" href="${pageContext.request.contextPath}/pages/signup.jsp">Регистрация</a>
	    </div>  
</div>
<c:choose>
    <c:when test="${errorLoginPassMessage != null}">
        <br />
       <div class="alert alert-warning" role="alert" id="errorMessage">
  			<strong>${errorLoginPassMessage}</strong>
		</div>
    </c:when> 
</c:choose>
</div>
</body>
</html>