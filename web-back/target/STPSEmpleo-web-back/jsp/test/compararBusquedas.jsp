<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String context = request.getContextPath() + "/";
%>
<form name="compararBusquedasForm" id="compararBusquedasForm" method="post" action="compararBusquedas.do" dojoType="dijit.form.Form">

<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="offer">
	<tr><td>Criterios usados en la b�squeda: Distrito Federal</td></tr>
	
	<tr><td>Resultado(s) exclusivos de B�squeda Espec�fica</td></tr>
	<tr><td>		
	
		<c:choose>
			<c:when test="${empty compararBusquedasForm.indicesSoloBusquedaEspecifica}">
				No se encontraron ofertas exclusivas para B�squeda Especifica o todas estan incluidas en los resultados de Ocupate.
			</c:when>
			<c:otherwise>
				<c:forEach var="ofertaSoloEspecifica" items="${compararBusquedasForm.indicesSoloBusquedaEspecifica}">
					${ofertaSoloEspecifica}&nbsp;
				</c:forEach>		
			</c:otherwise>
		</c:choose>
	</td></tr>
		
	<tr><td>Resultado(s) exclusivos de B�squeda Ocupate</td></tr>
	<tr><td>
		<c:choose>
			<c:when test="${empty compararBusquedasForm.indicesSoloBusquedaOcupate}">
				No se encontraron ofertas exclusivas para b�squeda Ocupate o todas estan incluidas en los resultados de B�squeda Especifica.
			</c:when>
			<c:otherwise>
				<c:forEach var="ofertaSoloOcupate" items="${compararBusquedasForm.indicesSoloBusquedaOcupate}">
					${ofertaSoloOcupate}&nbsp;
				</c:forEach>				
			</c:otherwise>
		</c:choose>
	</td></tr>
	
	<tr><td>Resultado(s) completos de B�squeda Espec�fica</td></tr>
	<tr><td>		
		<c:choose>
			<c:when test="${empty compararBusquedasForm.indicesOfertasBusquedaEspecifica}">
				No se encontraron ofertas para B�squeda Especifica.
			</c:when>
			<c:otherwise>
				<c:forEach var="ofertaEspecifica" items="${compararBusquedasForm.indicesOfertasBusquedaEspecifica}">
					${ofertaEspecifica}&nbsp;
				</c:forEach>		
			</c:otherwise>
		</c:choose>
	</td></tr>

	<tr><td>Resultado(s) completos de B�squeda Ocupate</td></tr>
	<tr><td>
		<c:choose>
			<c:when test="${empty compararBusquedasForm.indicesOfertasBusquedaOcupate}">
				No se encontraron ofertas para b�squeda Ocupate.
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

	