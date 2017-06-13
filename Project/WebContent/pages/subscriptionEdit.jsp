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
<title><fmt:message key="subscriptionedit.label.title" bundle="${rb}" /></title>
</head>
<body>
<div class="container">
<nav class="navbar navbar-inverse bg-primary navbar-toggleable-md" style="background-color: #e3f2fd;">
		  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <a class="navbar-brand" href="#"><fmt:message key="admin.label.menu" bundle="${rb}" /></a>
		  <div class="collapse navbar-collapse" id="navbarNavDropdown">
		    <ul class="navbar-nav">
		      <li class="nav-item">
		        <a id="update-button" class="nav-link" href="${pageContext.request.contextPath}/controller?command=subscription_update"><fmt:message key="subscriptionedit.button.update" bundle="${rb}"/></a>
		      </li>
		      <li class="nav-item">
		        <a id="add-button" class="nav-link" href="${pageContext.request.contextPath}/controller?command=subscription_add"><fmt:message key="subscriptionedit.button.add" bundle="${rb}"/></a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=subscription_list"><fmt:message key="common.label.back" bundle="${rb}"/></a>
		      </li>
		     </ul>
		   </div>
   </nav>
   <c:choose>
    <c:when test="${successUpdate != null}">
		 <div class="alert alert-success" role="alert" id="successMessage">
  			<strong><fmt:message key="message.subscriptionupdate.success" bundle="${rb}"/></strong>
		</div>
    </c:when> 
    <c:when test="${failedUpdate != null}">
    <div class="alert alert-warning" role="alert" id="warningMessage">
  			<strong><fmt:message key="message.subscriptionupdate.failure" bundle="${rb}"/></strong>
		</div>
    </c:when>
        <c:when test="${successAdd != null}">
		 <div class="alert alert-success" role="alert" id="successMessage">
  			<strong><fmt:message key="message.subscriptionadd.success" bundle="${rb}"/></strong>
		</div>
    </c:when> 
    <c:when test="${failedAdd != null}">
    <div class="alert alert-warning" role="alert" id="warningMessage">
  			<strong><fmt:message key="message.subscriptionadd.failure" bundle="${rb}"/></strong>
		</div>
    </c:when>
</c:choose>
<br>
<fmt:message key="common.label.alphaerrorinput" bundle="${rb}" var="erralphamsg"/>
<fmt:message key="common.label.numericerrorinput" bundle="${rb}" var="errnumericmsg"/>
<fmt:message key="common.label.errinput" bundle="${rb}" var="errmsg"/>
<fmt:message key="common.label.okinput" bundle="${rb}" var="okmsg"/>
<div class="form-group row has-feedback">
  
  <label for="example-text-input" class="col-2 col-form-label"><fmt:message key="subscriptionedit.label.name" bundle="${rb}"/></label>
  <div class="col-10">
    <input class="form-control" type = "text" id="name" name="name" value="${sub.name}" required="true" pattern="[A-Za-zА-Я-а-я]{8,20}$" onChange="validate(this)">
    <span id="fname" class="alert-success">
	    <c:choose>
	    <c:when test="${failedAlpha != null}">
	    <div class="alert alert-danger" role="alert" id="warningMessage">
	  			<strong>${erralphamsg}</strong>
			</div>
	    </c:when>
	    </c:choose>
    </span>
    <input class="form-control" name="prevname" value="${sub.name}" hidden>
  </div>
</div>
<div class="form-group row">
  <label for="example-search-input" class="col-2 col-form-label"><fmt:message key="subscriptionedit.label.price" bundle="${rb}"/></label>
  <div class="col-10">
    <input class="form-control" type="text" id="price" name="price" value="${sub.price}" required="true" pattern="^\d+$" onChange="validate(this)">
    <span id="fprice" class="alert-success">
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
$(function () {
    $('#update-button').click(function () {
    	var button = document.getElementById('update-button');
    	button.href = modifyHref(button);
    	})
    $('#add-button').click(function () {
    	var button = document.getElementById('add-button');
    	button.href = modifyHref(button);
    	})	
    	
    function modifyHref(itemToModify){
    	var name = document.getElementsByName('name')[0];
    	console.log(name.value);
    	var prevname = document.getElementsByName('prevname')[0];
    	var price = document.getElementsByName('price')[0];
    	console.log(price.value);
    	itemToModify.href = itemToModify.href + "&name=" + name.value + "&prevname="+prevname.value+"&price="+price.value;
       	console.log(itemToModify.href);
       	return itemToModify.href;
    };
    var input = document.getElementById('price');
    input.addEventListener('invalid', function(event){
        event.preventDefault();
        if ( ! event.target.validity.valid ) {
            elem.textContent   = 'Username should only contain lowercase letters e.g. john';
            elem.className     = 'error';
            elem.style.display = 'block';
     
            input.className    = 'invalid animated shake';
        }
    });
    input.oninvalid = function(event) {
        event.target.setCustomValidity('Username should only contain lowercase letters. e.g. john');
    }
    
});
</script>
</html>