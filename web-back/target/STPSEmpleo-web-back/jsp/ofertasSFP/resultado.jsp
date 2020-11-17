<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.List"%>
<%
	  String context = request.getContextPath() +"/";	 
%>
<c:set var="sufijo" value="_OFERTAS_SFP" />
<c:set var="pageList" value="PAGE_LIST${sufijo}" />
<c:set var="totalOfertas" value="${fn:length(PAGE_LIST_OFERTAS_SFP)}"/>



<c:if test="${not empty DETALLE and DETALLE==1}">
	<table>
		<tr><td>No se encontraron ofertas de empleo</td></tr>
	</table>
</c:if>

<c:if test="${not empty pageList}">

	<h3 class="mis_ofertas">Ofertas de empleo</h3>
	<table class="offer" width="80%" border="0" cellspacing="0" cellpadding="0" >
	    <tr class="temas">
	      <th width="30%">Título de la oferta <a id="titulo_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','1')"></a> <a id="titulo_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','1')"></a></th>
	      <th width="20%">Ubicación <a id="ubicacion_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','2')"></a> <a id="ubicacion_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','2')"></a></th>
	      <th width="20%">Empresa <a id="empresa_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','3')"></a> <a id="empresa_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','3')"></a></th>
	      <th width="10%">Salario neto ofrecido (mensual) <a id="salario_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','4')"></a> <a id="salario_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','4')"></a></th>	      
	      <c:if test="${empty USUARIO_APP}">
	      	<th  class="fin" >Fecha de publicación <a id="fecha_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','5')"></a> <a id="fecha_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','5')"></a></th>	      	      	      	      
	      </c:if>	
	      <c:if test="${not empty USUARIO_APP && USUARIO_APP.candidato}">	
	      	<th width="10%">Fecha de publicación <a id="fecha_orden_asc" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','5')"></a> <a id="fecha_orden_desc" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','5')"></a></th>	      	      	      
	      	<th class="fin">Compatibilidad</th>
	      </c:if>	
	    </tr>
	    
	    <c:forEach var="ofertasCanal" items="${PAGE_LIST_OFERTAS_SFP}" varStatus="rowCounter">
	    	<c:set var="rowClass" value="${rowCounter.count % 2 == '0' ? null : 'odd'}"/>
	    	<c:set var="rowClass_detalle" value="${rowCounter.count % 2 == '0' ? null : 'odd detalle'}"/>	    
	    
			<tr class="${rowClass}">
				<td>
					<div class="profesion"><strong><a href="javascript:mostrarDetalle(${ofertasCanal.idOfertaEmpleo})">${ofertasCanal.tituloOferta}</a></strong></div>
				</td>		    	
		    	<td>
			        ${ofertasCanal.entidad}
					<c:if test="${not empty ofertasCanal.municipio}">
						,&nbsp;${ofertasCanal.municipio} 
			        </c:if>			    			    				        
		    	</td>
		    	<td>${ofertasCanal.empresa}</td>
		    	<td>
					<c:if test="${not empty ofertasCanal.salario}">
						<fmt:setLocale value="en_US"/>
						<fmt:formatNumber type="CURRENCY" value="${ofertasCanal.salario}" />
			        </c:if>			    			    	
		    	</td>
		    	<td  width="10%">${ofertasCanal.fechaInicioString}</td>	
		    	
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
                    <strong>Conocimientos y habilidades generales:&nbsp;</strong> <c:out value="${ofertasCanal.habilidadGeneral}" />
                    <strong>Experiencia:&nbsp;</strong> <c:out value="${ofertasCanal.experiencia}" />
					<strong>Idiomas:&nbsp;</strong> <c:out value="${ofertasCanal.idiomas}" /> 
                    <strong>Rango de edad:&nbsp;</strong> <c:out value="${ofertasCanal.edad}" /><c:out value="${ofertasCanal.edad != 'N/A' ? ' años' : null}"/>
	    		</td>
	    	</tr>
	    </c:forEach>
    
    <%//request.setAttribute("PAGE_LIST", null); %>
    </table><br />
 
	<p align="center" >
			

		</p>
</c:if>

<jsp:include page="../portal/layout/pager.jsp" flush="true">
         <jsp:param name="SUFIJO" value="${sufijo}"/>
         <jsp:param name="tipoRegistros" value="ofertas"/>		 
</jsp:include>	
