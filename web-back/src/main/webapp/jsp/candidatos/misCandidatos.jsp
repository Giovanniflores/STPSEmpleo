<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String context = request.getContextPath() + "/";

	//LAS SIGUIENTES VARIABLES TERMINAN EN CAND YA QUE SE UTILIZAN PARA LA PAGINACION DE LA TABLA DE CANDIDATOS
	//el sufijo solo se usa si se utilizan multiples tablas.
	// de no necesitarse se puede igualar a "" o simplemente eliminar toda referencia a la variable.
	/*String sufijo = "_CANDIDATOS";
	//numero total de paginas del pager
	int paginaActualCand = 1;
	if(session.getAttribute("NUM_PAGE_LIST" + sufijo)!= null){
		paginaActualCand =((Integer) session.getAttribute("NUM_PAGE_LIST" + sufijo)).intValue();
	}
	//numero total de paginas del pager
	int totalPaginasCand = 0;
	if(session.getAttribute("TOTAL_PAGES" + sufijo)!= null){
		totalPaginasCand =((Integer) session.getAttribute("TOTAL_PAGES" + sufijo)).intValue();
	}	
	//numero maximo de paginas mostradas en el paginador 
	int numeroPaginasMostrarCand = 0;
	if(session.getAttribute("PAGE_JUMP_SIZE" + sufijo)!= null){
		numeroPaginasMostrarCand =((Integer) session.getAttribute("PAGE_JUMP_SIZE" + sufijo)).intValue();
	}
	//multiplicador q indica cual es el rango de paginas mostrado actualmente
	//(ejemplo, si vale 3 y numeroPagina = 6 se mostrara desde la 19 a la 24)
	int saltoPaginaCand = 0;
	if(session.getAttribute("NUM_PAGE_JUMP" + sufijo)!= null){
		saltoPaginaCand =((Integer) session.getAttribute("NUM_PAGE_JUMP" + sufijo)).intValue();
	}
	int registrosVisiblesCand = 0;
	if(session.getAttribute("NUM_RECORDS_VISIBLE" + sufijo)!= null){
		registrosVisiblesCand =((Integer) session.getAttribute("NUM_RECORDS_VISIBLE" + sufijo)).intValue();
	}
	int registrosTotalCand = 0;
	if(session.getAttribute("NUM_RECORDS_TOTAL" + sufijo)!= null){
		registrosTotalCand =((Integer) session.getAttribute("NUM_RECORDS_TOTAL" + sufijo)).intValue();
	}
	//muestra los numeros de pagina correctanmente
	int sumarPaginasCand = numeroPaginasMostrarCand * saltoPaginaCand;
	 */
%>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>




<table class="seleccionados" cellspacing="0" cellpadding="0" border="0" id="tblCandidatos">
	<caption>Mis candidatos seleccionados (<c:out value="${AdmonCandidatosForm.totalCandidatos}" />)</caption>
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

	<c:set var="sufijo" value="_CANDIDATOS" />
	<c:set var="pageList" value="PAGE_LIST${sufijo}" />
	
	<c:forEach var="candidatos" items="${sessionScope[pageList]}" varStatus="rowCounter">
		<tr class="odd">
			<td class="checar">
				<input type="checkbox" name="chkCandidato" ${candidatos.estatus==idEstatusProceso?'disabled':''} id="c${index + 1}" value="${candidatos.idOferta}" )" />
			</td>
			<td>
				<a class="postulado" href="<%=context%>viewCandidateInfo.do?method=init&idc=${candidatos.idCandidato}&idoc=${candidatos.idOferta}">
					<strong>${candidatos.nombre} ${candidatos.apellido1} ${candidatos.apellido2}</strong>
				</a>		
			</td>
			<td>${candidatos.subAreaLaboralDescripcion}</td>
			<td>${candidatos.carrera}</td>
			<td>${candidatos.edad}</td>
			<td><c:if test="${not empty candidatos.salario}">
					<fmt:setLocale value="en_US" />
					<fmt:formatNumber type="CURRENCY" value="${candidatos.salario}" />
			</c:if></td>
			<td>${candidatos.municipioEntidad}</td>
			<td>${candidatos.descEstatus}&nbsp;</td>
			<td>${candidatos.compatibilidad}%</td>
		</tr>
		<tr>
			<td style="padding:0" colspan="9"><a
				href="javascript:mostrarResumen('cand${rowCounter.count}')"
				class="expand">
					<!--<div id="hidecand${rowCounter.count}" style="display:none;">Ocultar resumen</div><div id="showcand${rowCounter.count}" style="display:block;">Ver resumen</div>-->
			</a>
				<div style="display: none;padding-bottom: 10px;padding-left: 10px;padding-top: 10px" id="cand${rowCounter.count}">
					<strong>Expectativas laborales:</strong> Puesto solicitado:
					${candidatos.subAreaLaboralDescripcion},
					<c:if test="${not empty candidatos.salario}">
						<fmt:setLocale value="en_US" />
								Salario pretendido: <fmt:formatNumber type="CURRENCY"
							value="${candidatos.salario}" />
					</c:if>
					<%-- , Tipo de contrato: ${candidatos.descTipoContrato}, Tipo de empleo:
					${candidatos.horarioEmpleo} --%>, Idiomas:
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

<ul id="hideAllcand" style="display: none;">
	<li><a class="accion" 
		href="javascript:eliminarCandidatos(document.getElementById('AdmonCandidatosForm'), 'tblCandidatos', 'chkCandidato');">Eliminar</a>
	</li>
	<li><a class="accion" 
		href="javascript:mostrarTodas(2, ${NUM_REGISTROS_CANDIDATOS}, 'cand')"
		class="expand">Ocultar resumen de todos los candidatos</a>
	</li>	
</ul>
<ul id="showAllcand" style="display: block;">
	<li><a class="accion"
		href="javascript:eliminarCandidatos(document.getElementById('AdmonCandidatosForm'), 'tblCandidatos', 'chkCandidato');">Eliminar</a>
	</li>
	<li><a class="accion"
		href="javascript:mostrarTodas(1, ${NUM_REGISTROS_CANDIDATOS}, 'cand')"
		class="expand">Ver resumen de todos los candidatos</a>	
	</li>
</ul>
<div class="clearfix"></div>

<jsp:include page="../layout/pager.jsp" flush="true">
         <jsp:param name="SUFIJO" value="${sufijo}"/>
         <jsp:param name="tipoRegistros" value="candidatos"/>         
</jsp:include>

