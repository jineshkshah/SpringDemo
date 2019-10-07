<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	<h2>List of Users and Stores</h2>	
	<table id="example">
	    <thead>
        <tr>
          <th>UserName</th>
            <th>UserId</th>
            <th>Role</th>
              <th>Store</th>
            
        </tr>
    </thead>
   
		
		<tbody>
		<c:forEach items="${userstoremapping}" var="user">
			<tr>
			<td>${user.userName}</td>
			<td>${user.userId}</td>
			<td>${user.role}</td>
			<td>${user.storeName}</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	<br/>
	
	<div class="row">
		
	<form:form method="POST" modelAttribute="user">
<label for="">  .....Map User and Store...: </label> 
		<label for="">  UserName: </label> 
				<form:select path="userId" id="user">
				<form:option value="" label="...." />
				<c:forEach items="${userList}" var="user">
    			<form:option value="${user.id}" label="${user.userName}" />
    			</c:forEach>
    			</form:select>
    	
    			
	    <label for="">Store: </label>
				<form:select path="storeId" id="store">
					<form:option value="" label="...." />
	    			<c:forEach items="${storeList}" var="store">
    			<form:option value="${store.id}" label="${store.storeName}" />
    			</c:forEach>
	    		</form:select> 
	 
	    			
	    			<input type="submit" value="Map"/>
	    		
    	</form:form>	
    	</div>	
	</div>
	
	<div class="row col-md-12">
	<div class="col-md-4">
	<button><a href="<c:url value='/list' />">Back to list</a></button>
	</div>

	</div>
	
	
</body>

</html>