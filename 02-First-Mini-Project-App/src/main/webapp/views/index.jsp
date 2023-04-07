<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Report Application</title>
</head>
<body>
	<form:form action="search" method="post" modelAttribute="searchRequest">

		<table>

			<tr>

				<td>Plan Name: <form:select path="planName">
						<form:option value="" label="-select-" />
						<form:options items="${planItems}" />
					</form:select>
				</td>
				<td>Plan Status: <form:select path="planStatus">
						<form:option value="" label="-select-" />
						<form:options items="${ statusItems}" />
					</form:select>
				</td>
				<td>Plan Gender: <form:select path="gender">
						<form:option value="" label="-select-" />
						<form:option value="M" label="male" />
						<form:option value="F" label="female" />
					</form:select>
				</td>
			</tr>
			<tr>
				<td>Start Date: <input type="date" name="startDate">
				</td>
				<td>End Date: <input type="date" name="endDate">
				</td>
				<td><input type="submit" value="search"></td>
			</tr>
		</table>
	</form:form>

	<hr>
	<h1>Reports are here</h1>
	<table>
		<thead>
			<tr>
				<th>Citizen Id</th>
				<th>citizenName</th>
				<th>gender</th>
				<th>planName</th>
				<th>planStatus</th>
				<th>planStartDate</th>
				<th>planEndDate</th>
				<th>benefitAmount</th>
				<th>denialReason</th>
				<th>terminatedDate</th>
				<th>terminationReason</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="r" items="${report}">
				<tr>
					<td>${r.citizenId }</td>
					<td>${r.citizenName }</td>
					<td>${r.gender }</td>
					<td>${r.planName }</td>
					<td>${r.planStatus }</td>
					<td>${r.planStartDate }</td>
					<td>${r.planEndDate }</td>
					<td>${r.benefitAmount }</td>
					<td>${r.denialReason }</td>
					<td>${r.terminatedDate }</td>
					<td>${r.terminationReason }</td>

				</tr>
			</c:forEach>

		</tbody>

	</table>

	<hr>
	<table>
		<tr>
			<td>Export :</td>
			<td><input type="button" onclick="" value="Excel"></td>
			<td><input type="button" onclick="" value="PDF"></td>
		</tr>

	</table>
</body>
</html>