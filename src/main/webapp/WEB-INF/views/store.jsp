<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Registration Form</title>

<style>

	.error {
		color: #ff0000;
	}
</style>

</head>

<body>

	<h2>Store Registration Form</h2>
 
	<form:form method="POST" modelAttribute="store">
		<form:input type="hidden" path="id" id="id"/>
		<table>
			<tr>
				<td><label for="storeName">Store Name: </label> </td>
				<td><form:input path="storeName" id="storeName"/></td>
				<td><form:errors path="storeName" cssClass="error"/></td>
		    </tr>
	    
			<tr>
				<td><label for="type">type: </label> </td>
				<td><form:input path="type" id="type"/></td>
				<td><form:errors path="type" cssClass="error"/></td>
		    </tr>
	
			<tr>
				<td><label for="region">Region: </label> </td>
				<td><form:input path="region" id="region"/></td>
				<td><form:errors path="region" cssClass="error"/></td>
		    </tr>
	
			<tr>
				<td><label for="storeId">storeId: </label> </td>
				<td><form:input path="storeId" id="storeId"/></td>
				<td><form:errors path="storeId" cssClass="error"/></td>
		    </tr>
	
			<tr>
				<td colspan="3">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update"/>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</form:form>
	<br/>
	<br/>
	Go back to <a href="<c:url value='/list' />">List of All Users</a>
</body>
</html>