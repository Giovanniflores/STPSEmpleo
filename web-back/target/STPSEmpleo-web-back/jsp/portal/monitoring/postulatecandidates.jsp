<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
  String context = request.getContextPath() +"/";
%>
	<h3 class="mis_ofertas">Candidatos postulados</h3>
	<c:if test="${empty PAGE_LIST_POSTULADOS}">
		<li class="ejemplo">No se encontraron candidatos que cumplan con los criterios de búsqueda, favor de utilizar la opción "Buscador de Candidatos".</li>
	</c:if>
	<c:if test="${not empty PAGE_LIST_POSTULADOS}">
		<div id="hideAllpost" style="display:none;"  align="left"><a href="javascript:mostrarTodas(2, ${NUM_REGISTROS_POSTULADOS}, 'post')" class="expand">[Ocultar resumen de todos los postulados]</a></div>
		<div id="showAllpost" style="display:block;" align="left"><a href="javascript:mostrarTodas(1, ${NUM_REGISTROS_POSTULADOS}, 'post')" class="expand">[Ver resumen de todos los postulados]</a></div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tblPostulados" >
			<tr class="temas" >
				<th width="1%">
					<input name="" type="checkbox" value="" id ="allCheck" value="" onClick="callable(this.form, this,'mypostulates')"/>
				</th>
		   		<th width="50%">Nombre(s), primer apellido y segundo apellido</th>
		       	<th width="25%">Grado de estudios - Carrera o especialidad</th>
		       	<th>Edad</th>
		       	<th>Entidad federativa - Municipio/Delegaci&oacuten</th>
		       	<th class="fin">Estatus</th>
		   	</tr>
			<c:forEach var="postulados" items="${PAGE_LIST_POSTULADOS}"
				varStatus="rowCounter">
				<tr <c:out value="${rowCounter.count % 2 == 1?'':'class=odd'}"/>>
					<td class="seleccion">
		           			<input type="checkbox" name="mypostulates" value="${postulados.idCandidato}" />
		           	</td>
					<td><div class="profesion"><strong><a href="<%=context%>viewCandidateDetail.do?method=init&idc=${postulados.idCandidato}&ido=${AdmonCandidatosForm.ofertaDetalle.idOfertaEmpleo}">${postulados.nombre} ${postulados.apellido1} ${postulados.apellido2}</a></strong></div></td>
					<td>${postulados.carrera}</td>
		        	<td>${postulados.edad}</td>
		        	<td>${postulados.municipioEntidad}</td>	
		        	<td>${postulados.descEstatus}</td>	
				</tr>
			   
				<tr class="detalles">
					<td colspan="7"><a href="javascript:mostrarResumen('post${rowCounter.count}')" class="expand"><div id="hidepost${rowCounter.count}" style="display:none;">Ocultar resumen</div><div id="showpost${rowCounter.count}" style="display:block;">Ver resumen</div></a>							   
						<div  style="display:none;" id="post${rowCounter.count}">
						<strong>Expectativas laborales:</strong> 
							Puesto solicitado: ${postulados.subAreaLaboralDescripcion}, 
							<c:if test="${not empty postulados.salario}">
								<fmt:setLocale value="en_US"/>
								Salario pretendido: <fmt:formatNumber type="CURRENCY" value="${postulados.salario}" />
					        </c:if>, 							
							<%-- Tipo de contrato: ${postulados.descTipoContrato}, 
							Tipo de empleo: ${postulados.horarioEmpleo}, --%>
							Idiomas:
							<c:if test="${not empty postulados.lstIdiomas}">
								<c:forEach var="idioma" items="${postulados.lstIdiomas}" varStatus="rowCounter">
									${idioma.idioma} ${idioma.dominio} 
							    </c:forEach>
							</c:if><%--,								
							Disponibilidad para viajar: ${postulados.dispViajarFuera},
							Disponibilidad para radicar fuera: ${postulados.dispRadicarFuera} --%>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>