<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%String context = request.getContextPath() +"/";%>

	<h3 class="mis_ofertas">Candidatos</h3>
	<c:if test="${empty PAGE_LIST_CANDIDATOS}">
	<ul>
		<li class="ejemplo">No se encontraron candidatos que cumplan con los criterios de búsqueda, favor de utilizar la opción "Buscador de candidatos".</li>
	</ul>

	</c:if>
	<c:if test="${not empty PAGE_LIST_CANDIDATOS}">
		<ul>
			<li class="ejemplo">Encontramos <c:out value="${fn:length(FULL_LIST_CANDIDATOS)}"/> candidatos.</li>
		</ul>
		
		<div id="hideAllcand" style="display:none;"  align="left"><a href="javascript:mostrarTodas(2, ${NUM_REGISTROS_CANDIDATOS}, 'cand')" class="expand">[Ocultar resumen de todos los candidatos]</a></div>
		<div id="showAllcand" style="display:block;" align="left"><a href="javascript:mostrarTodas(1, ${NUM_REGISTROS_CANDIDATOS}, 'cand')" class="expand">[Ver resumen de todos los candidatos]</a></div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="offer">
			<tr class="temas" >
				<!--<th width="1%"><input type="checkbox" name="allCheck" value="" onClick="callable(this.form, this, 'mycandidates')" /></th>-->
		   		<th style="width: 250px; !important">Nombre(s), primer apellido y segundo apellido</th>
		       	<th style="width: 170px;">Grado de estudios - Carrera o especialidad</th>
		       	<th>Edad</th>
		       	<th>Entidad federativa - Municipio/Delegaci&oacuten</th>
		       	<th class="fin" align="center">Compatibilidad</th>
		   	</tr>
			<c:forEach var="candidatos" items="${PAGE_LIST_CANDIDATOS}"
				varStatus="rowCounter">
				<tr <c:out value="${rowCounter.count % 2 == 1?'':'class=odd'}"/>>
					<!--<td class="seleccion">
		           			<input type="checkbox" name="mycandidates" value="${candidatos.idCandidato}"/>
		           	</td>-->
					<td ><div class="profesion" align="center"><strong><a
						href="<%=context%>viewCandidateDetail.do?method=init&type=assisted&idc=${candidatos.idCandidato}&ido=${AdmonCandidatosForm.ofertaDetalle.idOfertaEmpleo}&ec=${candidatos.descEstatus}">${candidatos.nombre} ${candidatos.apellido1} ${candidatos.apellido2}</a></strong></div></td>
					<td align="center">${candidatos.carrera}</td>
		        	<td align="center">${candidatos.edad}</td>
		        	<td align="center">${candidatos.municipioEntidad}</td>
		        	<td align="center">${candidatos.compatibilidad} %</td>
				</tr>
			   
				<tr <c:out value="${rowCounter.count % 2 == 1?'':'class=odd'}"/>>
					<td colspan="7"><a href="javascript:mostrarResumen('cand${rowCounter.count}')" class="expand"><div id="hidecand${rowCounter.count}" style="display:none;">Ocultar resumen</div><div id="showcand${rowCounter.count}" style="display:block;">Ver resumen</div></a>							   
						<div  style="display:none;" id="cand${rowCounter.count}">
						<strong>Expectativas laborales:</strong> 
						Puesto solicitado: ${candidatos.subAreaLaboralDescripcion}, 
						<c:if test="${not empty candidatos.salario}">
							<fmt:setLocale value="en_US"/>
							Salario pretendido: <fmt:formatNumber type="CURRENCY" value="${candidatos.salario}" />
				        </c:if>,				        				
						<%-- Tipo de contrato: ${candidatos.descTipoContrato}, 
						Tipo de empleo: ${candidatos.horarioEmpleo}, --%>
						Idiomas:
						<c:if test="${not empty candidatos.lstIdiomas}">
							<c:forEach var="idioma" items="${candidatos.lstIdiomas}" varStatus="rowCounter">
								${idioma.idioma} ${idioma.dominio} 
						    </c:forEach>
						</c:if><%--,						
						Disponibilidad para viajar: ${candidatos.dispViajarFuera},
						Disponibilidad para radicar fuera: ${candidatos.dispRadicarFuera} --%>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<div style="margin-bottom:50px"></div>