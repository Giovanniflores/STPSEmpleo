<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="publicados">
	<table id="tabla" class="seleccionados" style="margin-bottom: 40px; width: 100%;"> 
		<tbody>
			<tr class="temas">
				<th>
					<div class="fl ccpt">Puesto</div>
	 				<div class="fl level">
						<a id="puesto_orden_asc"  title="Orden Ascendente"   style="display: inline;"  class="ascendente"  href="javascript:orderBy('asc','puesto')"></a>
						<a id="puesto_orden_desc" title="Orden Descendente"  style="display: inline;"  class="descendente" href="javascript:orderBy('desc','puesto')"></a>	
					</div>
				</th>
				<th>
					<div class="fl ccpt">Empresa</div>
	 				<div class="fl level">
						<a id="empresa_orden_asc"  title="Orden Ascendente"   style="display: inline;"  class="ascendente"  href="javascript:orderBy('asc','empresa')"></a>
						<a id="empresa_orden_desc" title="Orden Descendente"  style="display: inline;"  class="descendente" href="javascript:orderBy('desc','empresa')"></a>				
					</div>
				</th>				
				<th>
					<div class="fl ccpt">Ubicaci&oacute;n</div>
	 				<div class="fl level">
						<a id="ubicacion_orden_asc"  title="Orden Ascendente"   style="display: inline;"  class="ascendente"  href="javascript:orderBy('asc','ubicacion')"></a>
						<a id="ubicacion_orden_desc" title="Orden Descendente"  style="display: inline;"  class="descendente" href="javascript:orderBy('desc','ubicacion')"></a>				
					</div>
				</th>		
				<th width="130">
					<div class="fl ccpt">Salario mensual</div>
					<div class="fl level">
						<a id="salario_mensual_asc"  title="Orden Ascendente"   style="display: inline;"  class="ascendente"  href="javascript:orderBy('asc','salario')"></a>
						<a id="salario_mensual_desc" title="Orden Descendente"  style="display: inline;"  class="descendente" href="javascript:orderBy('desc','salario')"></a>				
					</div>
				</th>		
				<th>
					<div class="fl ccpt">Compatibilidad</div>
	 				<div class="fl level">
						<a id="compatibilidad_asc"  title="Orden Ascendente"   style="display: inline;"  class="ascendente"  href="javascript:orderBy('asc','compatibilidad')"></a>
						<a id="compatibilidad_desc" title="Orden Descendente"  style="display: inline;"  class="descendente" href="javascript:orderBy('desc','compatibilidad')"></a>
					</div>			
				</th>
				<th>
					<div class="fl ccpt">Motivo</div>
				</th>
			</tr>
			<c:choose>
				<c:when test="${empty FULL_LIST}">
					<tr><td colspan="6"><div class="gris" style="text-align: center; font-weight: bold">No se encontraron ofertas disponibles</div></td></tr>
				</c:when>			
				<c:otherwise>
					<c:forEach var="ofertaNoPostulada" items="${PAGE_LIST}" varStatus="rowCounter">
						<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
							<td id="${ofertaNoPostulada.idOfertaEmpleo}">
								<div class="profesion">
									<input type="hidden" id="idOferta" name="idOferta" value="<c:out value="${ofertaNoPostulada.idOfertaEmpleo}"/>"/>
									<strong>
										<a href=<c:url value="/detalleoferta.do?method=init&id_oferta_empleo=${ofertaNoPostulada.idOfertaEmpleo}"/>">${ofertaNoPostulada.tituloOferta}</a>
									</strong>
								</div>
							</td>
							<td>${ofertaNoPostulada.nombreEmpresa}</td>
							<td>${ofertaNoPostulada.ubicacion}</td>
							<td>
								<c:if test="${not empty ofertaNoPostulada.salario}">
									<fmt:setLocale value="en_US"/>
									<fmt:formatNumber value="${ofertaNoPostulada.salario}" type="currency"/>
								</c:if>
							</td>
							<td style="text-align: center;">${ofertaNoPostulada.compatibilidad} %</td>
							<td>
								<c:choose>
									<c:when test="${ofertaNoPostulada.idMotivo == 0}">
										<a href="#" onclick="javascript:openDialogRegMotivos(<c:out value="${ofertaNoPostulada.idOfertaEmpleo}"/>);">No Postularse</a>
									</c:when>
									<c:otherwise>
										${ofertaNoPostulada.motivoDesc}
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr class="detalles">
							<td colspan="6">
							<strong>Resumen:</strong> <br/>${ofertaNoPostulada.detalleOferta}
							</td>
						</tr>
					</c:forEach>					
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
<jsp:include page="../layout/pager.jsp" flush="true">
	<jsp:param name="SUFIJO" value="${sufijo}"/>
	<jsp:param name="tipoRegistros" value="registros"/>		
</jsp:include>