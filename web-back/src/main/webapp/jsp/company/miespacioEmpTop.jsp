<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Calendar"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="holder_foto">
	<img src="imageAction.do?method=getImagen&<%="captcha?time="+ Calendar.getInstance().getTimeInMillis()%>"" alt="logo ${miEspacioEmpForm.nombreEmpresa}" />
</div>

<c:if test="${not empty ID_PORTALEMPLEO and not empty NOMBREEMPRESA and not empty NOMBRECONTACTOEMP}">
	<h2>${NOMBREEMPRESA}</h2>
	<p id="contacto_empresa">Contacto: ${NOMBRECONTACTOEMP}<br />ID del Portal del Empleo: ${ID_PORTALEMPLEO}</p>
</c:if>
<c:if test="${empty ID_PORTALEMPLEO or empty NOMBREEMPRESA or empty NOMBRECONTACTOEMP}">
	<h2>${miEspacioEmpForm.nombreEmpresa}</h2>
	<p id="contacto_empresa">Contacto: ${miEspacioEmpForm.contactoEmpresa} <br/>ID del Portal del Empleo: ${miEspacioEmpForm.idPortalEmpleo}</p>
</c:if>