<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat"%>
<div class="no-more-tables">
	<table class="offer table table-striped table-condensed" border="0" cellspacing="0" cellpadding="0" >
		<thead>
		    <tr class="temas">
		        <th>T&iacute;tulo de la oferta</th>
		        <th>Ubicaci&oacute;n</th>
		        <th>Fecha de publicaci&oacute;n</th>    
		        <th class="fin">Salario neto ofrecido (mensual)</th>               
		    </tr>
		</thead>
	    
		<c:set var="sufijo" value="" />
		<c:set var="pageList" value="PAGE_LIST${sufijo}" />
	    
	    <c:forEach var="ofertasCanal" items="${PAGE_LIST}" varStatus="rowCounter">
	    
	    	<c:set var="rowClass" value="${rowCounter.count % 2 == '0' ? null : 'odd'}"/>
	    	<c:set var="rowClass_detalle" value="${rowCounter.count % 2 == '0' ? null : 'odd detalle'}"/>
	    
			<tr class="${rowClass}">
	            <td data-title="Título de la oferta"><div class="profesion"><a href="${ofertasCanal.empresa}">${ofertasCanal.tituloOferta}</a></div></td>
	            <td data-title="Ubicación">${ofertasCanal.ubicacion}</td>
	            <td data-title="Fecha de publicación"><fmt:formatDate value="${ofertasCanal.fechaInicio}" pattern="dd/MM/yyyy" /></td>            
	        	<td data-title="Salario neto (mensual)" class="text-center">
					<c:if test="${not empty ofertasCanal.salario}">
						<fmt:setLocale value="en_US"/>
						<fmt:formatNumber type="CURRENCY" value="${ofertasCanal.salario}" />
			        </c:if>				        	
	        	</td>
	        </tr>
		</c:forEach>
	</table>
</div>
<jsp:include page="../layout/pager.jsp" flush="true">
         <jsp:param name="SUFIJO" value="${sufijo}"/>
</jsp:include>