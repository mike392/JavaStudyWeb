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
<title><fmt:message key="signup.label.title" bundle="${rb}" /></title>
</head>
<body>
<fmt:message key="common.label.passworderrorinput" bundle="${rb}" var="errpassmsg"/>
<fmt:message key="common.label.usernameerrorinput" bundle="${rb}" var="errusermsg"/>
<fmt:message key="common.label.retypederrorinput" bundle="${rb}" var="errretypedmsg"/>
<fmt:message key="common.label.numericerrorinput" bundle="${rb}" var="errnumericmsg"/>
<fmt:message key="common.label.errinput" bundle="${rb}" var="errmsg"/>
<fmt:message key="common.label.okinput" bundle="${rb}" var="okmsg"/>
<div class="wrapper container">
<div class="col-sm-4 col-sm-offset-1">
						<form role="form" action="${pageContext.request.contextPath}/controller?command=signup" method="post" class="r-form">
	                    	<h2 class="form-signin-heading"><fmt:message key="signup.label.header" bundle="${rb}" /></h2>
	                    	<div class="form-group">
	                    		<label class="sr-only" for="r-form-first-name">Username</label>
	                    		<fmt:message key="signup.label.username" bundle="${rb}" var="username"/>
	                        	<input type="text" name="username" placeholder="${username}" class="r-form-first-name form-control" id="r-form-first-name" required="true" onChange="validate(this)" pattern="[a-zA-Z][a-zA-Z0-9-_\.]{1,20}">
	                        	<span id="fusername" class="alert-success">
	                        	    <c:choose>
	    <c:when test="${failedUsername != null}">
	    <div class="alert alert-danger" role="alert" id="warningMessage">
	  			<strong>${errusermsg}</strong>
			</div>
	    </c:when>
	    </c:choose>
	                        	</span>
	                        </div>
	                        <div class="form-group">
	                        	<label class="sr-only" for="r-form-last-name">Password</label>
	                        	<fmt:message key="signup.label.password" bundle="${rb}" var="password"/>
	                        	<input type="text" name="password" placeholder="${password}" class="r-form-last-name form-control" id="r-form-last-name" required="true" onChange="validatePass(this)" pattern="^(?=.*\d).{4,8}$">
	                        	<span id="fpassword" class="alert-success">
	                        	<c:choose>
	    <c:when test="${failedPassword != null}">
	    <div class="alert alert-danger" role="alert" id="warningMessage">
	  			<strong>${errpassmsg}</strong>
			</div>
	    </c:when>
	    </c:choose>
	                        	</span>
	                        </div>
	                        <div class="form-group">
	                        	<label class="sr-only" for="r-form-last-name">Password</label>
	                        	<fmt:message key="signup.label.retypepass" bundle="${rb}" var="retypepass"/>
	                        	<input type="text" name="re-typed password" placeholder="${retypepass}" class="r-form-last-name form-control" id="r-form-last-name" required="true" onChange="validate(this)">
	                        	<span id="fre-typed password" class="alert-success">
	                        	<c:choose>
	    <c:when test="${failedRetyped != null}">
	    <div class="alert alert-danger" role="alert" id="warningMessage">
	  			<strong>${errretypedmsg}</strong>
			</div>
	    </c:when>
	    </c:choose></span>
	                        </div>
				            <button type="submit" class="btn-lg btn-primary btn-block"><fmt:message key="signup.button.signup" bundle="${rb}" /></button>
						</form>
						<br>
						<fmt:message key="common.label.back" bundle="${rb}" var="back"/>
						<form action="${pageContext.request.contextPath}/pages/login.jsp">
							<input class="btn-lg btn-primary btn-block" type="submit" value="${back}">
						</form>
                    </div>
                    <c:choose>
    <c:when test="${successSignup != null}">
		 <div class="alert alert-success" role="alert" id="successMessage">
  			<strong><fmt:message key="message.signup.success" bundle="${rb}"/></strong>
		</div>
    </c:when> 
    <c:when test="${failedSignup	 != null}">
    <div class="alert alert-warning" role="alert" id="warningMessage">
  			<strong><fmt:message key="message.signup.failure" bundle="${rb}"/></strong>
		</div>
    </c:when>
    </c:choose>
                   
                    <br>
                    <ctg:footer/>
                    <ctg:styles/>
                     </div>
</body>
<script>
function validate(element){    
    var el = document.getElementById('f' + element.name);
	console.log(el.id);	
    var regex = new RegExp(element.pattern);
    console.log(regex);
    if (regex.test(element.value)){
    	el.className = el.className.replace('alert-danger', 'alert-success')
    	el.innerText = "${okmsg}"
    } else {
    	el.className = el.className.replace('alert-success', 'alert-danger')
		el.innerText = "${errmsg}"
    }
    	}; 

 function validatePass(element){    
	 var el = document.getElementById('f' + element.name);
	 var reType = document.getElementsByName('re-typed password')[0];
	 reType.pattern = element.value	
	 console.log(el.id);	
	    var regex = new RegExp(element.pattern);
	    console.log(regex);
    if (regex.test(element.value)){
    	 el.className = el.className.replace('alert-danger', 'alert-success')
    	 el.innerText = "${okmsg}"
    } else {
    	 el.className = el.className.replace('alert-success', 'alert-danger')
    	 el.innerText = "${errmsg}"
    }
    };
</script>
</html>