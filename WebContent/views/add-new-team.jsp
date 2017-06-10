<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Add New Team</title>
	
	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/form-style.css">
</head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>Sports Teams Statistics Database</h2>
		</div>
	</div>

	<div id="container">
		<h3>Add New Team</h3>
		
		<form:form action="addNewTeam" modelAttribute="team" method="POST">
		
		<!-- need to associate this data with team id -->
		<form:hidden path="id" />
		
		<table>
			<tbody>
				<tr>
					<td><label> School Name:</label></td>
					<td><form:input path="schoolName" /></td>
				</tr>
				
				<tr>
					<td><label> Mascot:</label></td>
					<td><form:input path="mascot" /></td>
				</tr>
				
				<tr>
					<td><label> Sport:</label></td>
					<td><form:input path="sport" /></td>
				</tr>
				
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save" class="save"/></td>
				</tr>
			
				
			</tbody>
		
		</table>
		
		</form:form>
		
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/team/list">Back to List</a>
		
	</div>

</body>



</html>