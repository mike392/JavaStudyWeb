<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib prefix="ctg" uri="customtags" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <fmt:setBundle basename="resources.pagecontent" var="rb"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<ctg:imports/>
	<title><fmt:message key="login.label.title" bundle="${rb}" /></title>
</head>
<body>
<div class="wrapper container">
<div class="col-sm-4 col-sm-offset-1">
	    <form class="r-form" method="post" action="${pageContext.request.contextPath}/controller?command=login">       
	      <h2 class="form-signin-heading"><fmt:message key="login.label.header" bundle="${rb}" /></h2>
	      <div class="form-group">
	      <fmt:message key="login.label.username" bundle="${rb}" var="username"/>
	      	<input type="text" class="form-control" name="username" placeholder="${username}" required="" autofocus="" />
	      </div>
	      <div class="form-group">
	      <fmt:message key="login.label.password" bundle="${rb}" var="password"/>
	      	<input type="password" class="form-control" name="password" placeholder="${password}" required=""/>
	      </div>   
	      
	      <button class="btn btn-lg btn-primary btn-block" type="submit" name="command" value="login"><fmt:message key="login.button.login" bundle="${rb}" /></button> 
	    </form>
	    <br>
	    <div class="form-group">
	    <label class="sr-only" for="r-form-last-name">Signup</label>
	    <a class="btn btn-lg btn-primary btn-block" href="${pageContext.request.contextPath}/pages/signup.jsp"><fmt:message key="login.button.signup" bundle="${rb}" /></a>
	    </div>
	      
</div>
<c:choose>
    <c:when test="${errorLoginPassMessage != null}">
		 <div class="alert alert-warning" role="alert" id="errorMessage">
  			<strong>${errorLoginPassMessage}</strong>
		</div>
    </c:when> 
</c:choose>

<br>
<ctg:styles/>
<ctg:footer/>
</div>
</body>
</html>