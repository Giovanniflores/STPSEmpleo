<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
 

<form name="CurrentOfferReportForm" id="CurrentOfferReportForm" action="repofertasvig.do">
	<input type="hidden" name="method" value="ofertasPorAreaOcupacionReport"/>
	<input type="submit" value="Generar excel"/>
</form>
 
 <table id="dataTable" class="offer" cellspacing="0" cellpadding="0" border="0" width="730px">
 	<tbody> 	
		<tr class="temas">
			<th width="70%" colspan="2">Area laboral</th>
			<th width="15%">Subtotal</th>
			<th width="15%">Total</th>
		</tr> 	
 	
 	<!-- cursor que recorre las áreas -->
 	<c:set var="contador" value="1"/>
 	<c:forEach var="rowElement" items="${CurrentOfferReportForm.ofertasAreaOcupacion}" varStatus="rowCounter">
			<c:choose>
				<c:when test="${contador % 2 != '0'}">
					<tr class="odd">
				</c:when> 
				<c:otherwise> 			
 					<tr>
 				</c:otherwise>
 			</c:choose>
 			<td colspan="3">
 				<c:out value="${rowElement.area}" />
 			</td>
 			<td align="right">
 				<fmt:formatNumber type="number" pattern="###,###" value="${rowElement.areaTotalPlazas}" />
 			</td>
 			<c:set var="contador" value="${contador + 1}"/>	
 		</tr>
 		<!-- cursor que recorre las ocupaciones del área -->
 		<c:forEach var="listaOcupaciones" items="${rowElement.listaOcupaciones}" varStatus="listaOcupacionesRowCounter">
				<c:choose>
					<c:when test="${contador % 2 != '0'}">
						<tr class="odd">
					</c:when> 
					<c:otherwise> 			
 						<tr>
 					</c:otherwise>
 				</c:choose>
 				<c:if test="${listaOcupacionesRowCounter.count == '1'}">
 					<td rowspan="${rowElement.areaNumOcupaciones}">
 						Ocupaci&oacute;n
 					</td>
 				</c:if>
 				<!-- cursor que recorre para cada ocupación el array que contiene el nombre y el número de plazas -->
 				<c:forEach var="ocupacionPlazas" items="${listaOcupaciones}" varStatus="ocupacionPlazasRowCounter">
 					<c:choose>
 						<c:when test="${ocupacionPlazasRowCounter.count == '1'}">
 							<td><c:out value="${ocupacionPlazas}" /></td>
 						</c:when>
 						<c:otherwise>
 							<td align="right"><fmt:formatNumber type="number" pattern="###,###" value="${ocupacionPlazas}" /></td>
 							<td></td>						
 						</c:otherwise>
 					</c:choose>		
 				</c:forEach>
 					<c:set var="contador" value="${contador + 1}"/>		 				
 			</tr>
 		</c:forEach>
 	
 	</c:forEach>
		<c:choose>
			<c:when test="${contador % 2 != '0'}">
				<tr class="odd">
			</c:when> 
			<c:otherwise> 			
 				<tr>
 			</c:otherwise>
 		</c:choose>
		<td colspan = "3">
			TOTAL:
		</td>
		<td align="right">
			<fmt:formatNumber type="number" pattern="###,###" value="${CurrentOfferReportForm.totalOfertasAreaOcupacion}" />
		</td>
	</tr>
	
 	</tbody>
</table>