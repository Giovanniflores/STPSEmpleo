<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table id="dataTable" class="offer" cellspacing="0" cellpadding="0"
	border="0" width="730px">
	<tbody>
		<tr class="temas">
			<TH width="75%">Entidad</T7>
			<TH width="15%">Sin instrucci&oacute;n</TH>
			<TH width="15%">Leer y escribir</TH>
			<TH width="15%">Primaria</TH>
			<TH width="15%">Secundaria</TH>
			<TH width="15%">Carrera comercial</TH>
			<TH width="15%">Carrera t&eacute;cnica</TH>
			<TH width="15%">Profesional t&eacute;cnico</TH>
			<TH width="15%">Prepa o vocacional</TH>
			<TH width="15%">T. superior universitario</TH>
			<TH width="15%">Licenciatura</TH>
			<TH width="15%">Maestr&iacute;a</TH>
			<TH width="15%">Doctorado</TH>
			<TH width="15%">Total</TH>
		</tr>
		<c:forEach var="ofertasEntidad" items="${CurrentOfferReportForm.ofertasPortalEntidadEscolaridad}" varStatus="rowCounter">			
			<c:choose>
				<c:when test="${(rowCounter.count-1) % 2 == '0'}">		       	
					<tr class="odd">
				</c:when>
				<c:otherwise>
					<tr>
				</c:otherwise>
			</c:choose>
			
			<c:forEach var="ofertasEntidadEscolaridad" items="${ofertasEntidad}" varStatus="rowCounterTotales">
				<c:choose>
				<c:when test="${rowCounterTotales.count == '1'}">
					<td align="left">
					<c:out value="${ofertasEntidadEscolaridad}" />					
				</c:when>
				<c:otherwise>
					<td align="center">
					<fmt:formatNumber type="number" pattern="###,###" value="${ofertasEntidadEscolaridad}" />
				</c:otherwise>
				</c:choose>			
			</c:forEach>			
			</tr>
		<c:set var="numEntidades" value="${rowCounter.count}"/>
		</c:forEach>
		<c:choose>
			<c:when test="${(numEntidades+1) % 2 != '0'}">
				<tr class="odd">
			</c:when>
			<c:otherwise>
				<tr>
			</c:otherwise>
		</c:choose>
		<td>TOTAL:</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasSinInstruccion}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasLeerEscribir}" />
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasPrimaria}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasSecundaria}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasCarreraComercial}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasCarreraTecnica}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasProfesionalTecnico}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasPrepa}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasUniversitario}" />	
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasLicenciatura}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasMaestria}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasDoctorado}" />	
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasEntidadEscolaridad}" />		
		</td>	
		</tr>
	</tbody>
</table>
