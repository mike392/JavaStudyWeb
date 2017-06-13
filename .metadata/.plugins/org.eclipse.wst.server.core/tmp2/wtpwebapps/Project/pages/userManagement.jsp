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
				<a id="edit-button" class="nav-link disabled" href="${pageContext.request.contextPath}/controller?command=user_edit_subscription"><fmt:message key="usermanagement.button.edit" bundle="${rb}"/></a>
		      </li>
		      <li class="nav-item">
				<a id="remove-button" class="nav-link disabled" href="${pageContext.request.contextPath}/controller?command=user_remove_subscription"><fmt:message key="usermanagement.button.remove" bundle="${rb}"/></a>
		      </li>
		      <li class="nav-item">
				<a id="block-button" class="nav-link disabled" href="${pageContext.request.contextPath}/controller?command=block_user_subscription"><fmt:message key="usermanagement.button.block" bundle="${rb}"/></a>
		      </li>
		      <li class="nav-item">
				<a id="unblock-button" class="nav-link disabled" href="${pageContext.request.contextPath}/controller?command=unblock_user_subscription"><fmt:message key="usermanagement.button.unblock" bundle="${rb}"/></a>
		      </li>
		      <li class="nav-item">
				<a id="add-button" class="nav-link" href="${pageContext.request.contextPath}/controller?command=user_edit_subscription"><fmt:message key="usermanagement.button.add" bundle="${rb}"/></a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="${pageContext.request.contextPath}/pages/adminMain.jsp"><fmt:message key="common.label.back" bundle="${rb}"/></a>
		      </li>
		      </ul>
		      </div>
		      </nav>
		    <c:choose>
    <c:when test="${successRemoveUserSubscription != null}">
		 <div class="alert alert-success" role="alert" id="successMessage">
  			<strong><fmt:message key="message.usersubscriptionremove.success" bundle="${rb}"/></strong>
		</div>
    </c:when> 
    <c:when test="${failedRemoveUserSubscription != null}">
    <div class="alert alert-warning" role="alert" id="warningMessage">
  			<strong><fmt:message key="message.usersubscriptionremove.failure" bundle="${rb}"/></strong>
		</div>
    </c:when>
        <c:when test="${successBlockUserSubscription != null}">
		 <div class="alert alert-success" role="alert" id="successMessage">
  			<strong><fmt:message key="message.usersubscriptionblock.success" bundle="${rb}"/></strong>
		</div>
    </c:when> 
    <c:when test="${failedBlockUserSubscription != null}">
    <div class="alert alert-warning" role="alert" id="warningMessage">
  			<strong><fmt:message key="message.usersubscriptionblock.failure" bundle="${rb}"/></strong>
		</div>
    </c:when>
        <c:when test="${successUnblockUserSubscription != null}">
		 <div class="alert alert-success" role="alert" id="successMessage">
  			<strong><fmt:message key="message.usersubscriptionunblock.success" bundle="${rb}"/></strong>
		</div>
    </c:when> 
    <c:when test="${failedUnblockUserSubscription != null}">
    <div class="alert alert-warning" role="alert" id="warningMessage">
  			<strong><fmt:message key="message.usersubscriptionunblock.failure" bundle="${rb}"/></strong>
		</div>
    </c:when>
    </c:choose>  
    <table id="table" 	data-toggle="table" 
					data-toolbar=".toolbar"
					data-query-params="queryParams"
					data-pagination="true"
       				data-page-list="[5, 10, 20, 50, 100, 200]"
       				data-search="true"
       				data-height="370">
    <thead>
    <tr>
		<th data-field="count"><fmt:message key="usersubscription.table.header.count" bundle="${rb}"/></th>
        <th data-field="username"><fmt:message key="usersubscription.table.header.username" bundle="${rb}"/></th>
        <th data-field="subname"><fmt:message key="usersubscription.table.header.subname" bundle="${rb}"/></th>
        <th data-field="balance"><fmt:message key="usersubscription.table.header.balance" bundle="${rb}"/></th>
        <th data-field="blocked"><fmt:message key="usersubscription.table.header.blocked" bundle="${rb}"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userSubs}" var="item" varStatus="status">
    <tr>
        <td data-field="count">${status.count}</td>
        <td data-field="username">${item.username}</td>
        <td data-field="subname">${item.subname}</td>
        <td data-field="balance">${item.balance}</td>
        <td data-field="blocked">${item.blocked}</td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<br>
<hr>
<br>
<ctg:styles/>
<ctg:footer/>
</div>
</body>
<script>
var $table = $('#table');

function queryParams() {
    return {
        type: 'owner',
        sort: 'updated',
        direction: 'desc',
        per_page: 100,
        page: 1
    };
}

$(function () {
  	$table.on('click-row.bs.table', function (e, row, $element) {
    		$('.alert-success').removeClass('alert-success');
    		$($element).addClass('alert-success');
    		$('#edit-button').removeClass('disabled');
    		$('#remove-button').removeClass('disabled');
    		$('#block-button').removeClass('disabled');
    		$('#unblock-button').removeClass('disabled');
  	});

  	$('#edit-button').click(function () {
  		var params = {
  			  username: getSelectedRow().username,
  			  subname: getSelectedRow().subname
  			};
    	var button = document.getElementById('edit-button');
    	button.href = button.href + "&username=" + params.username+"&subname="+params.subname;
    	console.log(params.username + ' ' + params.subname);
    	console.log(button.href);
    });
    $('#remove-button').click(function () {
    	var params = {
  			  username: getSelectedRow().username,
  			  subname: getSelectedRow().subname
  			};
    	var button = document.getElementById('remove-button');
    	button.href = button.href + "&username=" + params.username+"&subname="+params.subname;
    	console.log(params.username + ' ' + params.subname);
    	console.log(button.href);
    });
    $('#block-button').click(function () {
    	var params = {
  			  username: getSelectedRow().username,
  			  subname: getSelectedRow().subname
  			};
    	var button = document.getElementById('block-button');
    	button.href = button.href + "&username=" + params.username+"&subname="+params.subname;
    	console.log(params.username + ' ' + params.subname);
    	console.log(button.href);
    });
    
    $('#unblock-button').click(function () {
    	var params = {
  			  username: getSelectedRow().username,
  			  subname: getSelectedRow().subname
  			};
    	var button = document.getElementById('unblock-button');
    	button.href = button.href + "&username=" + params.username+"&subname="+params.subname;
    	console.log(params.username + ' ' + params.subname);
    	console.log(button.href);
    });
});

function getSelectedRow() {
    var index = $table.find('tr.alert-success').data('index');
    return $table.bootstrapTable('getData')[index];
}
</script>
</html>