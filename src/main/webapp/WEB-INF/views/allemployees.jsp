<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>University Enrollments</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
		


	</style>

<script type="text/javascript">
 var path = '${pageContext.request.contextPath}';
</script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css">
<link href="http://cdn.datatables.net/1.10.3/css/jquery.dataTables.css" rel="stylesheet"
 type="text/css">
<link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" 
rel="stylesheet" type="text/css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"
></script>


<script>

$(document).ready(function() {
    $('#example').DataTable();
} );

</script>
</head>


<body>
	<h2>List of Users</h2>	
	<table id="example">
	    <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
              <th>Role</th>
            <th>UserId</th>
            <th>UserName</th>
            <th>Delete</th>
        </tr>
    </thead>
   
		
		<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
			<td>${user.firstName}</td>
			<td>${user.lastName}</td>
			<td>${user.role}</td>
			<td><a href="<c:url value='/edit-${user.userId}-user' />">${user.userId}</a></td>
			<td>${user.userName}</td>
			<td><a href="<c:url value='/delete-${user.userId}-user' />">delete</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<br/>
	<div class="row col-md-12">
	<div class="col-md-3">
	<button><a href="<c:url value='/new' />">Add New User</a></button>
	</div>
	<div class="col-md-3">
	<button><a href="<c:url value='/newRole' />">Add New Role</a></button>
	</div>
	<div class="col-md-3">
	<button><a href="<c:url value='/newStore' />">Add New Store</a></button>
	</div>
	<div class="col-md-3">
	<button><a href="<c:url value='/userStoreMapping' />">Map User Store</a></button>
	</div>
	</div>
	
	
</body>

</html>