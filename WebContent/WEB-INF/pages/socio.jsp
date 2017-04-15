<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Wallet Passcards  Socios</title>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	margin-left:15px;
	border-color: #ccc;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}
</style>
</head>
<body>
	<h1>Listado de socios con passcards</h1>

<!--  
	<c:url var="addAction" value="/socio/add"></c:url>

	<form:form action="${addAction}" commandName="socio">
		<table>
			<c:if test="${!empty socio.nroSocio}">
				<tr>
					<td><form:label path="id">
							<spring:message text="ID" />
						</form:label></td>
					<td><form:input path="id" readonly="true" size="8"
							disabled="true" /> <form:hidden path="id" /></td>
				</tr>
			</c:if>
			<tr>
				<td><form:label path="nroSocio">
						<spring:message text="nroSocio" />
					</form:label></td>
				<td><form:input path="nroSocio" /></td>
			</tr>
			<tr>
				<td><form:label path="dni">
						<spring:message text="DNI" />
					</form:label></td>
				<td><form:input path="dni" /></td>
			</tr>
			<tr>
				<td colspan="2"><c:if test="${!empty socio.dni}">
						<input type="submit" value="<spring:message text="Edit Socio"/>" />
					</c:if> <c:if test="${empty socio.dni}">
						<input type="submit" value="<spring:message text="Add Socio"/>" />
					</c:if></td>
			</tr>
		</table>
	</form:form>-->
	<br>
	<c:if test="${!empty listSocios}">
		<table class="tg">
			<tr>
				<th width="80">DNI</th>
				<th width="120">Nro Socio</th>
				<th width="120">Cantidad Clases</th>
				<th width="120">Fecha Vto</th>
				<th width="120">Estado</th>
				<th width="120">Pass Serial Number</th>
				
				<!-- <th width="60">Edit</th>
				<th width="60">Delete</th> -->
			</tr>
			<c:forEach items="${listSocios}" var="socio">
				<tr>
					<td>${socio.dni}</td>
					<td>${socio.nroSocio}</td>
					<td>${socio.cantClases}</td>
					<td>${socio.fechaVto}</td>
					<td>${socio.estado}</td>
					<td>${socio.serialNumber}</td>
					
					<!-- <td><a href="<c:url value='/edit/${socio.id}' />">Edit</a></td>
					<td><a href="<c:url value='/remove/${socio.id}' />">Delete</a></td>-->
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>