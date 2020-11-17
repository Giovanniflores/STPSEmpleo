<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page
	import="mx.gob.stps.portal.web.admin.form.CurrentOfferReportForm"%>
<%@ page import="mx.gob.stps.portal.web.infra.utils.Utils"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Collections"%>

<table id="dataTable" class="offer" cellspacing="0" cellpadding="0"
	border="0" width="730px">
	<tbody>
		<tr class="temas">
			<TH width="75%">Entidad</T7>
			<TH width="15%">Sin experiencia</TH>
			<TH width="15%">6m - 1 a&ntilde;o</TH>
			<TH width="15%">1 - 2 a&ntilde;os</TH>
			<TH width="15%">2 - 3 a&ntilde;os</TH>
			<TH width="15%">3 - 4 a&ntilde;os</TH>
			<TH width="15%">4 - 5 a&ntilde;os</TH>
			<TH width="15%">M&aacute;s de 5 a&ntilde;os</TH>
			<TH width="15%">No es requisito</TH>
			<TH width="15%">Total</TH>
		</tr>
		
		<c:forEach var="ofertasEntidad" items="${CurrentOfferReportForm.ofertasPortalEntidadExperiencia}" varStatus="rowCounter">			
			<c:choose>
				<c:when test="${(rowCounter.count-1) % 2 == '0'}">		       	
					<tr class="odd">
				</c:when>
				<c:otherwise>
					<tr>
				</c:otherwise>
			</c:choose>
			<c:forEach var="ofertasEntidadExperiencia" items="${ofertasEntidad}" varStatus="rowCounterTotales">
				
				<c:choose>
				<c:when test="${rowCounterTotales.count == '1'}">
					<td align="left">
					<c:out value="${ofertasEntidadExperiencia}" />					
				</c:when>
				<c:otherwise>
					<td align="center">
					<fmt:formatNumber type="number" pattern="###,###" value="${ofertasEntidadExperiencia}" />
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
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasSinExperiencia}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasHasta1Anio}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasDe1A2Anios}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasDe2A3Anios}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasDe3A4Anios}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasDe4A5Anios}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasMasDe5Anios}" />
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasExpNoRequerida}" />		
		</td>
		<td align="center">		
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasEntidadExperiencia}" />		
		</td>
		</tr>
	</tbody>
</table>
