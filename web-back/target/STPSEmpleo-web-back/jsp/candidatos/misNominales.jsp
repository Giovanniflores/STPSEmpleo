<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String context = request.getContextPath() + "/";
%>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<c:if test="${AdmonCandidatosForm.totalNominales > 0}">
	<table class="seleccionados" cellspacing="0" cellpadding="0" border="0" id="tblNominales">
		<caption>Candidatos nominales (<c:out value="${AdmonCandidatosForm.totalNominales}" />)</caption>
			<tr class="temas">
	    	<th style="width: 4px">
	    	  <label for="checkbox"></label>
			</th>
	        <th style="width: 160px">Nombre</th>
			<th style="width: 160px">Puesto solicitado</th>
	        <th style="width: 120px">Nivel de estudios carrera o especialidad</th>
	        <th>Edad</th>
	        <th>Salario pretendido</th>
			<th style="width: 120px">Ubicaci&oacute;n</th>
	        <th>Estatus</th>
	        <th class="fin">Compatibilidad con la oferta</th>    
	    </tr>	
	
		<c:set var="sufijo" value="_NOMINALES" />
		<c:set var="pageList" value="PAGE_LIST${sufijo}" />
		
		<c:forEach var="nominales" items="${sessionScope[pageList]}" varStatus="rowCounter">
			<tr class="odd">
				<td class="checar">
					<input type="checkbox" name="chkNominal" ${nominales.estatus==idEstatusProceso?'disabled':''} id="c${index + 1}" value="${nominales.idOferta}" )" />
				</td>
				<td>
					<a class="postulado" href="<%=context%>viewCandidateInfo.do?method=init&idc=${nominales.idCandidato}&idoc=${nominales.idOferta}">
						<strong>${nominales.nombre} ${nominales.apellido1} ${nominales.apellido2}</strong>
					</a>		
				</td>
				<td>${nominales.subAreaLaboralDescripcion}</td>
				<td>${nominales.carrera}</td>
				<td>${nominales.edad}</td>
				<td><c:if test="${not empty nominales.salario}">
						<fmt:setLocale value="en_US" />
						<fmt:formatNumber type="CURRENCY" value="${nominales.salario}" />
				</c:if></td>
				<td>${nominales.municipioEntidad}</td>
				<td>${nominales.descEstatus}&nbsp;</td>
				<td>${nominales.compatibilidad}%</td>
			</tr>
			<tr>
				<td style="padding:0" colspan="9">
					<a href="javascript:mostrarResumen('Noml${rowCounter.count}')" class="expand"></a>
					<div style="display: none;padding-bottom: 10px;padding-left: 10px;padding-top: 10px" id="Noml${rowCounter.count}">
						<strong>Expectativas laborales:</strong> Puesto solicitado:
						${nominales.subAreaLaboralDescripcion},
						<c:if test="${not empty nominales.salario}">
							<fmt:setLocale value="en_US" />
									Salario pretendido: <fmt:formatNumber type="CURRENCY"
								value="${nominales.salario}" />
						</c:if>
						<%--, Tipo de contrato: ${nominales.descTipoContrato}, Tipo de empleo:
						${nominales.horarioEmpleo}--%>, Idiomas:
						<c:if test="${not empty nominales.lstIdiomas}">
							<c:forEach var="idioma" items="${nominales.lstIdiomas}" varStatus="rowCounter">
											${idioma.idioma} ${idioma.dominio} 
							</c:forEach>
						</c:if><%--,
						Disponibilidad para viajar: ${nominales.dispViajarFuera},
						Disponibilidad para radicar fuera: ${nominales.dispRadicarFuera} --%>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<ul id="hideAllNoml" style="display: none;">
		<li><a class="accion" href="javascript:eliminarCandidatos(document.getElementById('AdmonCandidatosForm'), 'tblNominales', 'chkNominal');">Eliminar</a></li>
		<li><a class="accion" href="javascript:mostrarTodas(2, ${NUM_REGISTROS_NOMINALES}, 'Noml')" class="expand">Ocultar resumen de todos los candidatos</a></li>	
	</ul>
	<ul id="showAllNoml" style="display: block;">
		<li><a class="accion" href="javascript:eliminarCandidatos(document.getElementById('AdmonCandidatosForm'), 'tblNominales', 'chkNominal');">Eliminar</a></li>
		<li><a class="accion" href="javascript:mostrarTodas(1, ${NUM_REGISTROS_NOMINALES}, 'Noml')" class="expand">Ver resumen de todos los candidatos</a></li>
	</ul>
	<div class="clearfix"></div>
	
	<jsp:include page="../layout/pager.jsp" flush="true">
	         <jsp:param name="SUFIJO" value="${sufijo}"/>
	         <jsp:param name="tipoRegistros" value="candidatos"/>         
	</jsp:include>
</c:if>