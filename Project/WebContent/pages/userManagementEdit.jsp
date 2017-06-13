<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <fmt:setBundle basename="resources.pagecontent" var="rb"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-81">
<title><fmt:message key="usermanagement.label.title" bundle="${rb}" /></title>
<ctg:imports/>

</head>
<body>
<div class="wrapper container">
<nav class="navbar navbar-inverse bg-primary navbar-toggleable-md" style="background-color: #e3f2fd;">
		  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <a class="navbar-brand" href="#"><fmt:message key="admin.label.menu" bundle="${rb}" /></a>
		  <div class="collapse navbar-collapse" id="navbarNavDropdown">
		    <ul class="navbar-nav">
		      <li class="nav-item">
				<a id="update-button" class="nav-link" href="${pageContext.request.contextPath}/controller?command=user_update_subscription"><fmt:message key="usermanagement.button.update" bundle="${rb}"/></a>
		      </li>
		      <li class="nav-item">
				<a id="add-button" class="nav-link" href="${pageContext.request.contextPath}/controller?command=user_add_subscription"><fmt:message key="usermanagement.button.add" bundle="${rb}"/></a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=user_management"><fmt:message key="common.label.back" bundle="${rb}"/></a>
		      </li>
		      </ul>
		      </div>
		      </nav>
		    <c:choose>
    <c:when test="${successAddUserSubscription != null}">
		 <div class="alert alert-success" role="alert" id="successMessage">
  			<strong><fmt:message key="message.usersubscriptionadd.success" bundle="${rb}"/></strong>
		</div>
    </c:when> 
    <c:when test="${failedAddUserSubscription	 != null}">
    <div class="alert alert-warning" role="alert" id="warningMessage">
  			<strong><fmt:message key="message.usersubscriptionadd.failure" bundle="${rb}"/></strong>
		</div>
    </c:when>
    <c:when test="${successUpdateUserSubscription != null}">
		 <div class="alert alert-success" role="alert" id="successMessage">
  			<strong><fmt:message key="message.usersubscriptionupdate.success" bundle="${rb}"/></strong>
		</div>
    </c:when> 
    <c:when test="${failedUpdateUserSubscription	 != null}">
    <div class="alert alert-warning" role="alert" id="warningMessage">
  			<strong><fmt:message key="message.usersubscriptionupdate.failure" bundle="${rb}"/></strong>
		</div>
    </c:when>
    </c:choose>  
 <form>
  <div class="form-group row">
    <label for="exampleSelect1" class="col-2 col-form-label"><fmt:message key="subscriptionview.label.subname" bundle="${rb}"/></label>
    <select class="form-control" id="subname" name="subname">
      <c:forEach var="item" items="${res}" >
               <c:choose>
               <c:when test="${item.name eq sub.subname}">
                <option selected>${item.name}</option>
                </c:when>
                <c:otherwise>
                <option>${item.name}</option>
                </c:otherwise>
                </c:choose>
            </c:forEach>
    </select>
  </div>
<div class="form-group row">
    <label for="exampleSelect1" class="col-2 col-form-label"><fmt:message key="subscriptionview.label.username" bundle="${rb}"/></label>
    <select class="form-control" id="username" name="username">
      <c:forEach var="item" items="${users}" >
               <c:choose>
               <c:when test="${item.login eq sub.username}">
                <option selected>${item.login}</option>
                </c:when>
                <c:otherwise>
                <option>${item.login}</option>
                </c:otherwise>
                </c:choose>
            </c:forEach>
    </select>
  </div>
</form>
<br>
<br>
<hr>
<br>
<ctg:styles/>
<ctg:footer/>

</body>
<script>
$(function () {
    $('#add-button').click(function () {
    	var button = document.getElementById('add-button');
    	button.href = modifyHref(button);
    	})
    $('#update-button').click(function () {
    	var button = document.getElementById('update-button');
    	button.href = modifyHref(button);
    	})	
    	
    function modifyHref(itemToModify){
    	var user = document.getElementById('username');
    	console.log(name.login);
    	var sub = document.getElementById('subname');
    	console.log(sub.name);
    	itemToModify.href = itemToModify.href + "&username=" + user.value + "&subname="+sub.value;
       	console.log(itemToModify.href);
       	return itemToModify.href;
    }
});
</script>
</html>