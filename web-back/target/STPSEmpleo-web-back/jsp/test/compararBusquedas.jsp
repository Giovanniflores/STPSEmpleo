<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String context = request.getContextPath() + "/";
%>
<form name="compararBusquedasForm" id="compararBusquedasForm" method="post" action="compararBusquedas.do" dojoType="dijit.form.Form">

<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="offer">
	<tr><td>Criterios usados en la búsqueda: Distrito Federal</td></tr>
	
	<tr><td>Resultado(s) exclusivos de Búsqueda Específica</td></tr>
	<tr><td>		
	
		<c:choose>
			<c:when test="${empty compararBusquedasForm.indicesSoloBusquedaEspecifica}">
				No se encontraron ofertas exclusivas para Búsqueda Especifica o todas estan incluidas en los resultados de Ocupate.
			</c:when>
			<c:otherwise>
				<c:forEach var="ofertaSoloEspecifica" items="${compararBusquedasForm.indicesSoloBusquedaEspecifica}">
					${ofertaSoloEspecifica}&nbsp;
				</c:forEach>		
			</c:otherwise>
		</c:choose>
	</td></tr>
		
	<tr><td>Resultado(s) exclusivos de Búsqueda Ocupate</td></tr>
	<tr><td>
		<c:choose>
			<c:when test="${empty compararBusquedasForm.indicesSoloBusquedaOcupate}">
				No se encontraron ofertas exclusivas para búsqueda Ocupate o todas estan incluidas en los resultados de Búsqueda Especifica.
			</c:when>
			<c:otherwise>
				<c:forEach var="ofertaSoloOcupate" items="${compararBusquedasForm.indicesSoloBusquedaOcupate}">
					${ofertaSoloOcupate}&nbsp;
				</c:forEach>				
			</c:otherwise>
		</c:choose>
	</td></tr>
	
	<tr><td>Resultado(s) completos de Búsqueda Específica</td></tr>
	<tr><td>		
		<c:choose>
			<c:when test="${empty compararBusquedasForm.indicesOfertasBusquedaEspecifica}">
				No se encontraron ofertas para Búsqueda Especifica.
			</c:when>
			<c:otherwise>
				<c:forEach var="ofertaEspecifica" items="${compararBusquedasForm.indicesOfertasBusquedaEspecifica}">
					${ofertaEspecifica}&nbsp;
				</c:forEach>		
			</c:otherwise>
		</c:choose>
	</td></tr>

	<tr><td>Resultado(s) completos de Búsqueda Ocupate</td></tr>
	<tr><td>
		<c:choose>
			<c:when test="${empty compararBusquedasForm.indicesOfertasBusquedaOcupate}">
				No se encontraron ofertas para búsqueda Ocupate.
			</c:when>
			<c:otherwise>
				<c:forEach var="ofertaOcupate" items="${compararBusquedasForm.indicesOfertasBusquedaOcupate}">
					${ofertaOcupate}&nbsp;
				</c:forEach>				
			</c:otherwise>
		</c:choose>
	</td></tr>
	
</table>

</form>

	