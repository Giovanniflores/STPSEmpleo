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
		        <th>Empresa</th>
		        <th>Salario neto ofrecido (mensual)</th>
		        <th class="fin">Bolsa de trabajo</th>
				<c:if test="${not empty USUARIO_APP && USUARIO_APP.candidato}">
					<th class="fin">Compatibilidad</th>
				</c:if>                
		    </tr>
		</thead>
	    
		<c:set var="sufijo" value="" />
		<c:set var="pageList" value="PAGE_LIST${sufijo}" />
	    
	    <c:forEach var="ofertasCanal" items="${PAGE_LIST}" varStatus="rowCounter">
	    
	    	<c:set var="rowClass" value="${rowCounter.count % 2 == '0' ? null : 'odd'}"/>
	    	<c:set var="rowClass_detalle" value="${rowCounter.count % 2 == '0' ? null : 'odd detalle'}"/>
	    
			<tr class="${rowClass}">
			
				<c:url var="urlDetalleOferta" value="detalleoferta.do">
					<c:param name="method" value="init"/>
					<c:param name="id_oferta_empleo" value="${ofertasCanal.idOfertaEmpleo}"/>
					
					<c:if test="${OFERTA_RECIENTE==1}">
						<c:param name="_urlpageinvoke" value="ofertasRecientesTodas"/>
					</c:if>
					<c:if test="${OFERTA_RECIENTE==2}">
						<c:param name="_urlpageinvoke" value="ofertasDestacadasTodas"/>
					</c:if>					
				</c:url>
	            <td data-title="Título de la oferta"><div class="profesion"><a href="${urlDetalleOferta}">${ofertasCanal.tituloOferta}</a></div></td>
	            <td data-title="Ubicación">${ofertasCanal.ubicacion}</td>
	            <td data-title="Fecha de publicación"><fmt:formatDate value="${ofertasCanal.fechaInicio}" pattern="dd/MM/yyyy" /></td>            
	            <td data-title="Empresa">${ofertasCanal.empresa}</td>
	        	<td data-title="Salario neto (mensual)" class="text-center">
				<c:if test="${not empty ofertasCanal.salario}">
					<fmt:setLocale value="en_US"/>
					<fmt:formatNumber type="CURRENCY" value="${ofertasCanal.salario}" />
		        </c:if>				        	
	        	</td>            
	            <td data-title="Bolsa de trabajo">${ofertasCanal.bolsaTrabajo}</td>
	            
				<c:if test="${not empty USUARIO_APP && USUARIO_APP.candidato}">				
					
					<c:choose>
					<c:when test="${ofertasCanal.compatibility >= 0}">
						<td style="text-align: center;">
							<c:out value="${ofertasCanal.compatibility}" />%
						</td>				
					</c:when>
					<c:otherwise>
							<td style="text-align: center;">---</td>
					</c:otherwise>
				</c:choose>				
				</c:if>
	        </tr>
	        
	        <tr class="${rowClass_detalle}">
	            <td colspan="7">
	            		<c:if test="${not empty ofertasCanal.funciones}">
	                    	<strong>Funciones:&nbsp;</strong> <c:out value="${ofertasCanal.funciones}" />
	                    </c:if>
						<c:if test="${not empty ofertasCanal.competencias}">
							<strong>Competencias:&nbsp;</strong><c:out value="${ofertasCanal.competencias}" />
						</c:if>
						<strong>Idiomas:&nbsp;</strong> <c:out value="${ofertasCanal.idiomas}" />
	            </td>
	        </tr>
	</c:forEach>
	</table>
</div>
<jsp:include page="../layout/pager.jsp" flush="true">
         <jsp:param name="SUFIJO" value="${sufijo}"/>
</jsp:include>
