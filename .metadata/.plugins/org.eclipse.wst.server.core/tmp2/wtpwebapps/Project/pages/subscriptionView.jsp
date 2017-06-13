<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <fmt:setBundle basename="resources.pagecontent" var="rb"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<ctg:imports/>
<title><fmt:message key="subscriptionview.label.title" bundle="${rb}" /></title>
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
		        <a id="modify-button" class="nav-link" href="${pageContext.request.contextPath}/controller?command=subscription_view_modify"><fmt:message key="subscriptionview.button.modify" bundle="${rb}"/></a>
		      </li>
		      <li class="nav-item">
		        <a id="pay-button" class="nav-link" href="${pageContext.request.contextPath}/controller?command=subscription_view_pay"><fmt:message key="subscriptionview.button.pay" bundle="${rb}"/></a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/pages/userMain.jsp"><fmt:message key="common.label.back" bundle="${rb}"/></a>
		      </li>
		     </ul>
		   </div>
   </nav>
   <c:choose>
    <c:when test="${successUpdateUserSubscription != null}">
		 <div class="alert alert-success" role="alert" id="successMessage">
  			<strong><fmt:message key="message.usersubscriptionupdate.success" bundle="${rb}"/></strong>
		</div>
    </c:when> 
    <c:when test="${failedUpdateUserSubscription != null}">
    <div class="alert alert-warning" role="alert" id="warningMessage">
  			<strong><fmt:message key="message.usersubscriptionupdate.failure" bundle="${rb}"/></strong>
		</div>
    </c:when>
        <c:when test="${successPay != null}">
		 <div class="alert alert-success" role="alert" id="successMessage">
  			<strong><fmt:message key="message.usersubscriptionpay.success" bundle="${rb}"/></strong>
		</div>
    </c:when> 
    <c:when test="${failedPay != null}">
    <div class="alert alert-warning" role="alert" id="warningMessage">
  			<strong><fmt:message key="message.usersubscriptionpay.failure" bundle="${rb}"/></strong>
		</div>
    </c:when>
</c:choose>
<br>
<fmt:message key="common.label.passworderrorinput" bundle="${rb}" var="erralphamsg"/>
<fmt:message key="common.label.numericerrorinput" bundle="${rb}" var="errnumericmsg"/>
<fmt:message key="common.label.errinput" bundle="${rb}" var="errmsg"/>
<fmt:message key="common.label.okinput" bundle="${rb}" var="okmsg"/>
   <form>
<div class="form-group row">
  
  <label for="example-text-input" class="col-2 col-form-label"><fmt:message key="subscriptionview.label.username" bundle="${rb}"/></label>
  <div class="col-10">
    <label class="col-2 col-form-label" name="name"><strong>${sub.username}</strong></label>
  </div>
</div>
<div class="form-group row">
  <label for="example-search-input" class="col-2 col-form-label"><fmt:message key="subscriptionview.label.subname" bundle="${rb}"/></label>
  <div class="col-10">
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
</div>
<div class="form-group row">
  <label for="example-text-input" class="col-2 col-form-label"><fmt:message key="subscriptionview.label.blocked" bundle="${rb}"/></label>
  <div class="col-10">
    <label class="col-2 col-form-label" name="blocked" id="blocked"><strong>${sub.blocked}</strong></label>
  </div>
</div>
<div class="form-group row">
  <label for="example-text-input" class="col-2 col-form-label"><fmt:message key="subscriptionview.label.balance" bundle="${rb}"/></label>
  <div class="col-10">
    <label class="col-2 col-form-label" name="balance"><strong>${sub.balance}</strong></label>
  </div>
</div>
<div class="form-group row">
  <label for="example-search-input" class="col-2 col-form-label"><fmt:message key="subscriptionview.label.payment" bundle="${rb}"/></label>
  <div class="col-10">
    <input class="form-control" type="text" name="payment" id="payment" pattern="^\d+$" onChange="validate(this)">
    <span id="fpayment" class="alert-success">
        <c:choose>
	    <c:when test="${failedPositiveNumber != null}">
	    <div class="alert alert-danger" role="alert" id="warningMessage">
	  			<strong>${errnumericmsg}</strong>
			</div>
	    </c:when>
	    </c:choose>
    </span>
  </div>
</div>
</form>
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
    	console.log("${okmsg}")
    	el.innerText = "${okmsg}"
    } else {
    	el.className = el.className.replace('alert-success', 'alert-danger')
		el.innerText = "${errmsg}"
    }
    	}; 
$(function () {
    $('#pay-button').click(function () {
    	var button = document.getElementById('pay-button');
    	console.log('hello');
    	button.href = modifyHref(button);
    	console.log(button.href);
    	})
    $('#modify-button').click(function () {
    	var button = document.getElementById('modify-button');
    	console.log('hello');
    	button.href = modifyHref(button);
    	console.log(button.href);
    	})	
    	
    function modifyHref(itemToModify){
    	var subname = document.getElementById('subname');
    	console.log(subname.value);
    	var payment = document.getElementById('payment');
    	console.log(payment.value);
    	itemToModify.href = itemToModify.href + "&subname=" + subname.value + "&payment=" + payment.value + "&blocked=" + "${sub.blocked}" + "&balance=" + "${sub.balance}";
       	console.log(itemToModify.href);
       	return itemToModify.href;
    }
});
</script>
</html>