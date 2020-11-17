<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page
	import="mx.gob.stps.portal.web.admin.form.CurrentOfferReportForm"%>
	
<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<table id="dataTable" class="offer" cellspacing="0" cellpadding="0"
	border="0" width="730px">
	<tbody>
		<tr class="temas">
			<TH width="75%">Actividad Econ&oacute;mica</TH>
			<TH width="25%">Total</TH>
		</tr>
		<c:forEach var="ofertasActividad" items="${CurrentOfferReportForm.ofertasPortalActividadEconomica}" varStatus="rowCounter">			
			<c:choose>
				<c:when test="${(rowCounter.count-1) % 2 == '0'}">		       	
					<tr class="odd">
				</c:when>
				<c:otherwise>
					<tr>
				</c:otherwise>
			</c:choose>
			<c:forEach var="columnElement" items="${ofertasActividad}" varStatus="rowCounterTotales">				
			<c:choose>
				<c:when test="${rowCounterTotales.count == '1'}">
					<td align="left">
					<c:out value="${columnElement}" />					
				</c:when>
				<c:otherwise>
					<td align="right">
					<fmt:formatNumber type="number" pattern="###,###" value="${columnElement}" />
				</c:otherwise>
			</c:choose>
			</c:forEach>			
			</tr>
		<c:set var="numRegistros" value="${rowCounter.count}"/>
		</c:forEach>
		<c:choose>
			<c:when test="${(numRegistros+1) % 2 != '0'}">
				<tr class="odd">
			</c:when>
			<c:otherwise>
				<tr>
			</c:otherwise>
		</c:choose>
		<td>TOTAL:</td>
		<td align="right">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasPortalActividadEconomica}" />		
		</td>
		</tr>
	</tbody>
</table>
