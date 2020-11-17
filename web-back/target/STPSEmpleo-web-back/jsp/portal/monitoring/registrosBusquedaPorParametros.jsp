<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
  String context = request.getContextPath() +"/";
%>
<c:choose>
	<c:when test="${empty PAGE_LIST_OFERTAS}">
		<p>No se encontraron ofertas compatibles a tus parámetros.</p>
	 </c:when>
   		 <c:otherwise>
     		  <p>Se encontraron <c:out value="${fn:length(FULL_LIST_OFERTAS)}"/> ofertas de empleo de acuerdo a tus parámetros.</p>
     </c:otherwise>
</c:choose>

<div class="publicados">
	<table class="seleccionados" width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr class="temas">
			<th>
				<div class="fl">Puesto</div>
				<div class="order-fix">
			 		<a id="puesto_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','puesto')"></a>
       	 			<a id="puesto_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','puesto')"></a>
       	 		</div>
      		</th>
			<th>
				<div class="fl">Empresa</div>
				<div class="order-fix">
		 			<a id="empresa_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','empresa')"></a>
           			<a id="empresa_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','empresa')"></a>
           		</div>
      		</th>		
			<th>
				<div class="fl">Ubicación</div>
				<div class="order-fix">
     				<a id="ubicacion_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','ubicacion')"></a>
		           <a id="ubicacion_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','ubicacion')"></a>
		        </div>		
			</th>
			<th>
				<div class="fl">Salario mensual</div>
				<div class="order-fix">
					<a id="salario_orden_asc" title="Orden Ascendente" style="display: inline;" class="ascendente" href="javascript:orderBy('asc','salario')"></a>
				    <a id="salario_orden_desc" title="Orden Descendente" style="display: inline;"  class="descendente" href="javascript:orderBy('desc','salario')"></a>
				</div>		
			</th>
			<th style="text-align: center">
				<div class="fl">Compatibilidad</div>
				<div class="order-fix"></div>
			</th>
		</tr>
		
		<c:forEach var="ofertasPerfil" items="${PAGE_LIST_OFERTAS}" varStatus="rowCounter">		
			<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
				<td>
					<div class="profesion">
						<strong><a href="${pageContext.request.contextPath}/detalleoferta.do?method=init&id_oferta_empleo=${ofertasPerfil.idOfertaEmpleo}">${ofertasPerfil.tituloOferta}</a></strong>
					</div>
				</td>
				<td>${ofertasPerfil.empresa}</td>
				<td>${ofertasPerfil.ubicacion}</td>
        		<td>
					<c:if test="${not empty ofertasPerfil.salario}">
						<fmt:setLocale value="en_US"/>
						<fmt:formatNumber type="CURRENCY" value="${ofertasPerfil.salario}" />
	        		</c:if>				        	
        		</td>
				<td style="text-align: center;">${ofertasPerfil.compatibilidad.compatibilidad} %</td>
			</tr>

			<tr class="detalles">
				<td colspan="5">					   
					<h3><Strong>Resumen:</Strong></h3>
					<div  id="${rowCounter.count}">
						${ofertasPerfil.tituloOferta} con ${ofertasPerfil.gradoEstudio}
						<c:if test="${ofertasPerfil.carrera != null}"> en ${ofertasPerfil.carrera}</c:if>
						<c:if test="${ofertasPerfil.funciones != 'Ninguna'}">, ${ofertasPerfil.funciones}</c:if>
						<c:if test="${ofertasPerfil.idiomasCert != null}">
							<c:if test="${ofertasPerfil.idiomasCert == 'No es requisito'}">, Idioma no es requerido</c:if>		
							<c:if test="${ofertasPerfil.idiomasCert != 'No es requisito'}">, ${fn:trim(ofertasPerfil.idiomasCert)}</c:if>			
						</c:if>
						<c:if test="${ofertasPerfil.numeroPlazas != null}">, ${ofertasPerfil.numeroPlazas} plaza(s)</c:if>
						<c:if test="${ofertasPerfil.contactoTel == 2}">, ${ofertasPerfil.telefonoOferta}</c:if>
						<c:if test="${ofertasPerfil.contactoCorreo == 2}">, ${ofertasPerfil.correoElectronicoContacto}</c:if>
						<c:if test="${ofertasPerfil.contactoDomicilio == 2}">, ${ofertasPerfil.domicilio}</c:if>					
						<c:if test="${empty ofertasPerfil.habilidades && not ofertasPerfil.habilidadGeneral == null}">, ${ofertasPerfil.habilidadGeneral}</c:if>		
						<c:if test="${not empty ofertasPerfil.habilidades}">, ${ofertasPerfil.habilidades}</c:if>	
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
