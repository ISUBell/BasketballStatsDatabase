<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>

	<title>List of Teams</title>
	
	<!-- reference to our style sheet -->
	
	<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css" />
		
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Sports Teams Statistics Database</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		
			<!-- Add a new button: Add Team -->
			
			<input type="button" value="Add Team to Database"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button"
			/>
			
			<!-- Insert our Database of teams -->
			
			<table>
				<tr>
					<th>School Name</th>
					<th>Mascot</th>
					<th>Sport</th>
					<th>Wins</th>
					<th>Losses</th>
					<th>FG%</th>
					<th></th>
				</tr>
				
				<!-- Loop over team database and display results -->
				<c:forEach var="tempTeam" items="${teams}">
			
					<!-- construct a "delete" link using team id -->
					<c:url var="deleteLink" value="/team/delete">
						<c:param name="teamId" value='${tempTeam.id}' />
					</c:url>
					
					<!-- construct a "refresh" link using team id -->
					<c:url var="refreshLink" value="/team/refreshTeam">
						<c:param name="teamId" value='${tempTeam.id}' />
					</c:url>
			
					<tr>
						<td> ${tempTeam.schoolName} </td>
						<td> ${tempTeam.mascot} </td>
						<td> ${tempTeam.sport}</td>
						<td> ${tempTeam.wins}</td>
						<td> ${tempTeam.losses}</td>
						<td> ${tempTeam.fgPct}</td>
						
						<td>
						<a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want ot delete this team?'))) return false">Delete Team</a>
						</td>
						
						<td>|</td>
						
						<td>
						<a href="${refreshLink}">Refresh</a>
						</td>
					
					</tr>
				
				</c:forEach>
			</table>
		</div>
	</div>
</body>

</html>





