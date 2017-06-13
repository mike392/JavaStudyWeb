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
<title><fmt:message key="signup.label.title" bundle="${rb}" /></title>
</head>
<body>
<div class="container">
<div class="col-sm-4 col-sm-offset-1">
						<form role="form" action="${pageContext.request.contextPath}/controller?command=signup" method="post" class="r-form">
	                    	<h2 class="form-signin-heading"><fmt:message key="signup.label.header" bundle="${rb}" /></h2>
	                    	<div class="form-group">
	                    		<label class="sr-only" for="r-form-first-name">Username</label>
	                    		<fmt:message key="signup.label.username" bundle="${rb}" var="username"/>
	                        	<input type="text" name="username" placeholder="${username}" class="r-form-first-name form-control" id="r-form-first-name">
	                        </div>
	                        <div class="form-group">
	                        	<label class="sr-only" for="r-form-last-name">Password</label>
	                        	<fmt:message key="signup.label.password" bundle="${rb}" var="password"/>
	                        	<input type="text" name="password" placeholder="${password}" class="r-form-last-name form-control" id="r-form-last-name">
	                        </div>
	                        <div class="form-group">
	                        	<label class="sr-only" for="r-form-last-name">Password</label>
	                        	<fmt:message key="signup.label.retypepass" bundle="${rb}" var="retypepass"/>
	                        	<input type="text" name="re-typed password" placeholder="${retypepass}" class="r-form-last-name form-control" id="r-form-last-name">
	                        </div>
				            <button type="submit" class="btn-lg btn-primary btn-block"><fmt:message key="signup.button.signup" bundle="${rb}" /></button>
						</form>
						<br>
						<fmt:message key="common.label.back" bundle="${rb}" var="back"/>
						<form action="${pageContext.request.contextPath}/pages/login.jsp">
							<input class="btn-lg btn-primary btn-block" type="submit" value="${back}">
						</form>
                    </div>
                    
                    </div>
                    ${rb.getResourceBundle().getLocale()}
                    ${locale}
                    <ctg:footer/>
                    <ctg:styles/>
</body>
</html>