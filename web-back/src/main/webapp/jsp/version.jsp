<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>STPS Portal del Empleo 2.0</title>
</head>
<body>
	<p>
		<strong>Display name:</strong>PE-PREPROD-1217-003<br/>
		
		<strong>Sesiones activas: </strong>${COUNT_SESSION}<br/>
		<c:if test="${not empty FH_INICIO_APLICACION}">
			<strong>Aplicación iniciada el: </strong><fmt:formatDate value="${FH_INICIO_APLICACION.time}" pattern="dd/MM/yyyy hh:mm" /><br/>
		</c:if>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		
		<span style="color:White">Ver_CGSNEtrunk_01112017</span>
	</p>
</body>
</html>