<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="mx.gob.stps.portal.web.admin.form.CurrentOfferReportForm"%>
<%@ page import="mx.gob.stps.portal.web.infra.utils.Utils"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Collections"%>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<script type="text/javascript">
  function doSubmitPager(method) {
	  alert(method);
        
      dojo.byId('method').value = method;
      dojo.xhrPost({url: 'repofertasvig.do', form:'reportEscolaridad', timeout:180000,
                    load: function(data) {
                          dojo.byId('reporte').innerHTML = data;
                    }});
  }
</script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>

<div id="reporte" name="reporte">
<table id="dataTable" class="offer" cellspacing="0" cellpadding="0" border="0" width="730px">
	<tbody>
		<tr class="temas">
	    	<TH width="75%">Entidad</TH>
	    	<TH width="15%" align="center">Masculino</TH>
	    	<TH width="15%" align="center">Femenino</TH>
	    	<TH width="15%" align="center">No requerido</TH>
	    	<TH width="15%" align="center">No especificado</TtH>	    	
	    	<TH width="15%" align="center">Total</TH>
		</tr>
		<c:forEach var="rowOfertasPortalEntidadGenero" items="${CurrentOfferReportForm.ofertasPortalEntidad}" varStatus="rowCounter">
			<c:choose>
			<c:when test="${(rowCounter.count) % 2 != '0'}">
				<tr class="odd"> 			
			</c:when>
			<c:otherwise>
				<tr>
			</c:otherwise>	
			</c:choose>

			<c:forEach var="colOfertasPortalEntidadGenero" items="${rowOfertasPortalEntidadGenero}" varStatus="columnCounter">
				<c:choose>
				<c:when test="${columnCounter.count == '1'}">
					<td align="left">
					<c:out value="${colOfertasPortalEntidadGenero}" />					
				</c:when>
				<c:otherwise>
					<td align="center">
					<fmt:formatNumber type="number" pattern="###,###" value="${colOfertasPortalEntidadGenero}" />
				</c:otherwise>
				</c:choose>
				</td>	
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
		<td align="left">TOTAL:</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasPortalMasculino}" />
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasPortalFemenino}" />				
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasPortalNoRequerido}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasIndiferente}" />		
		</td>
		<td align="center">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasPortal}"  />
		</td>
		</tr>
		
	</tbody>
</table>