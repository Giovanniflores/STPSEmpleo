<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String context = request.getContextPath() + "/";
%>

<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="offer">
	<tr class="temas">
		<th>Título de la oferta</th>
		<th>Ubicación</th>
		<th>Empresa</th>
		<th>Salario neto ofrecido (mensual)</th>
		<th class="fin">Vigencia</th>
	</tr>
	<c:forEach var="lstOfertas" items="${PAGE_LIST}" varStatus="rowCounter">
		<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
			<td><div class="profesion">
					<strong><a
						href="<c:url value="/detalleoferta.do?method=init&id_oferta_empleo=${lstOfertas.idOfertaEmpleo}"/>">${lstOfertas.tituloOferta}</a>
					</strong>
				</div></td>
			<td>${lstOfertas.ubicacion}</td>
			<td>${lstOfertas.empresa}</td>
			<td><c:if test="${not empty lstOfertas.sSalario}"> ${lstOfertas.sSalario}</c:if>
			</td>
			<td>${lstOfertas.fechaInicioString} <c:if
					test="${not empty lstOfertas.fechaFinString}"> al ${lstOfertas.fechaFinString}</c:if>
			</td>
		</tr>
		<tr class="detalles">
			<td colspan="5"><a
				href="javascript:mostrarResumen('${rowCounter.count}')"
				class="expand"><div id="hide${rowCounter.count}"
						style="display: none;">Ocultar resumen</div>
					<div id="show${rowCounter.count}" style="display: block;">Ver
						resumen</div> </a>
				<div style="display: none;" id="${rowCounter.count}">
					Solicitamos ${lstOfertas.tituloOferta}<br />
					${lstOfertas.gradoEstudio} ${lstOfertas.carrera}<br />
					${lstOfertas.funciones} Edad: ${lstOfertas.edad} años, <br />					
					<c:if test="${not empty lstOfertas.idiomas}">Idioma requerido: ${lstOfertas.idiomas}<br /></c:if>
					<c:if test="${not empty lstOfertas.horario}">Horario de empleo: ${lstOfertas.horario},<br /></c:if>					
					Número de plazas: ${lstOfertas.numeroPlazas}. Medio para contactar
					la oferta: ${lstOfertas.medioContacto}
				</div>
			</td>
		</tr>

	</c:forEach>
</table>