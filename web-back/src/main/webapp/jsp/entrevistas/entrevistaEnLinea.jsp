<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mx.gob.stps.portal.core.infra.vo.EntrevistaVO, mx.gob.stps.portal.web.infra.utils.PropertiesLoader"%>
<%@ page import="java.util.List"%>
<%
	PropertiesLoader properties = PropertiesLoader.getInstance();
	String portal = properties.getProperty("app.domain.portal");
%>
<script>

	function abrirVentaChat(idEntrevista, nombreCandidato, idCandidato,
			idEmpresa, razonSocial, personaContacto) {
		if (idEntrevista != "") {
		var ventana = window.open("<%=portal%>/jsp/entrevistas/chatcitas.jsp?"
					+ getAgregarArgumentos(idEntrevista, nombreCandidato,
							idCandidato, idEmpresa, razonSocial,
							personaContacto), "Mi_entrevista_en_linea",
					"width=630," + "height=365," + "resize=no,"
							+ "scrollbars=no," + "location=no,"
							+ "statusbars=no," + "menubars=yes," + "status=no,"
							+ "directories=no," + "resizable=0");
		} else {
			alert("Debes de tener una Entrevista Activa para solicitar un chat");
		}

	}

	function getAgregarArgumentos(idEntrevista, nombreCandidato, idCandidato,
			idEmpresa, razonSocial, personaContacto) {
		var argumentos = "idEntrevista=" + idEntrevista + "&nombreUsuario="
				+ '${entrevistaForm.nombreUsuario}' + "&idCandidato="
				+ idCandidato + "&idEmpresa=" + idEmpresa + "&tipo="
				+ '${entrevistaForm.tipo}' + "&razonSocial=" + razonSocial
				+ "&personaContacto=" + personaContacto + "&nombreCandidato="
				+ nombreCandidato;
		return argumentos;
	}
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<div class="stat_entrevista">
<c:if test="${not empty entrevistaForm.listEntrevistaVo}">
	<table border="0" cellspacing="0" class="offer" style="width: 600px;">
	 <tbody>
		<tr class="temas_Chat">
			<th align="center" style="width:180px">Oferta <%=portal%></th>
			<th align="center" style="width:150px">${entrevistaForm.etiqueta}</th>
			<th align="center" style="width:50px">Hora</th>
			<th align="center" style="width:80px">Fecha</th>
			<th align="center" style="width:30px">Chat</th>
		</tr>
		<c:forEach var="entrevista" items="${entrevistaForm.listEntrevistaVo}"
			varStatus="rowCounter">
			<tr
				<c:out value="${rowCounter.count % 2 == 0 ?  'class=odd_Chat':'class=odd_Blanco_Chat'}"/>
				align="center">
				<td>${entrevista.tituloOferta}</td>
				<c:if test="${1 == entrevistaForm.tipoNumeric}">
					<td>${entrevista.nombre}</td>
				</c:if>
				<c:if test="${2 == entrevistaForm.tipoNumeric }">
					<td>${entrevista.contactoEmpresa}</td>
				</c:if>
				<td>${entrevista.hora}</td>
				<td>${entrevista.fechaString}</td>
				<td><img src="images/bt_chat.png" title="Realizar Chat"
					onclick="abrirVentaChat(
						 	  '${entrevista.idEntrevista}',
						 	  '${entrevista.nombre}',
						 	  '${entrevista.idCandidato}',
						 	  '${entrevista.idEmpresa}',
						 	  '${entrevista.razonSocial}',
						 	  '${entrevista.contactoEmpresa}')">
				</td>
			</tr>
		</c:forEach>
		 </tbody>
	</table>
</c:if> <c:if test="${empty entrevistaForm.listEntrevistaVo}">	
<br>
	<div class="gris margin_top_40" style="font-weight: bold; text-align: center">
			No tienes ninguna entrevista en l&iacute;nea.
	</div>
<br/>  
	

</c:if></div>