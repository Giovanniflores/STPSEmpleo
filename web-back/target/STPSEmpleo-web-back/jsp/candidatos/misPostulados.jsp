<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%
   String context = request.getContextPath() +"/";
    /*
  //LAS SIGUIENTES VARIABLES TERMINAN EN POST YA QUE SE UTILIZAN PARA LA PAGINACION DE LA TABLA DE CANDIDATOS
	//el sufijo solo se usa si se utilizan multiples tablas.
	// de no necesitarse se puede igualar a "" o simplemente eliminar toda referencia a la variable.
	String sufijoPost = "_POSTULADOS";
	//numero total de paginas del pager
	int paginaActualPost = 1;
	if(session.getAttribute("NUM_PAGE_LIST" + sufijoPost)!= null){
		paginaActualPost =((Integer) session.getAttribute("NUM_PAGE_LIST" + sufijoPost)).intValue();
	}
	//numero total de paginas del pager
	int totalPaginasPost = 0;
	if(session.getAttribute("TOTAL_PAGES" + sufijoPost)!= null){
		totalPaginasPost =((Integer) session.getAttribute("TOTAL_PAGES" + sufijoPost)).intValue();
	}
	//numero maximo de paginas mostradas en el paginador 
	int numeroPaginasMostrarPost = 0;
	if(session.getAttribute("PAGE_JUMP_SIZE" + sufijoPost)!= null){
		numeroPaginasMostrarPost =((Integer) session.getAttribute("PAGE_JUMP_SIZE" + sufijoPost)).intValue();
	}
	//multiplicador q indica cual es el rango de paginas mostrado actualmente
	//(ejemplo, si vale 3 y numeroPagina = 6 se mostrara desde la 19 a la 24)
	int saltoPaginaPost = 0;
	System.out.println(session.getAttribute("NUM_PAGE_JUMP" + sufijoPost));
	if(session.getAttribute("NUM_PAGE_JUMP" + sufijoPost)!= null){
		saltoPaginaPost =((Integer) session.getAttribute("NUM_PAGE_JUMP" + sufijoPost)).intValue();
	}
	int registrosVisiblesPost = 0;
	if(session.getAttribute("NUM_RECORDS_VISIBLE" + sufijoPost)!= null){
		registrosVisiblesPost =((Integer) session.getAttribute("NUM_RECORDS_VISIBLE" + sufijoPost)).intValue();
	}
	int registrosTotalPost = 0;
	if(session.getAttribute("NUM_RECORDS_TOTAL" + sufijoPost)!= null){
		registrosTotalPost =((Integer) session.getAttribute("NUM_RECORDS_TOTAL" + sufijoPost)).intValue();
	}
	
	//muestra los numeros de pagina correctanmente
	int sumarPaginasPost = numeroPaginasMostrarPost * saltoPaginaPost;
  */
%>

	
<div class="no-more-tables">
		<table class="table table-striped table-condensed offer seleccionados"  cellspacing="0" cellpadding="0" border="0" id="tblPostulados">
		<caption>Candidatos postulados (<c:out value="${AdmonCandidatosForm.totalPostulados}"/>)</caption>
			<tr class="temas hidden-xs" >
    			<th>
    	  			<label for="checkbox"></label>
    	  		</th>
		        <th>Nombre</th>
		        <th>Puesto solicitado</th>
		        <th>Nivel de estudios carrera o especialidad</th>
		        <th>Edad</th>
		        <th>Salario pretendido</th>
		        <th>Ubicaci&oacute;n</th>
		        <th>Estatus</th>
		        <th class="fin">Compatibilidad con la oferta</th>	
		        
		        <%-- <th style="width: 4px">
    	  			<label for="checkbox"></label>
    	  		</th>
		        <th style="width: 160px">Nombre</th>
		        <th style="width: 160px">Ocupaci&oacute;n deseada</th>
		        <th style="width: 120px;">Nivel de estudios carrera o especialidad</th>
		        <th>Edad</th>
		        <th>Salario pretendido</th>
		        <th style="width: 120px">Ubicaci&oacute;n</th>
		        <th>Estatus</th>
		        <th class="fin">Compatibilidad con la oferta</th>   
		        --%>    	
        
		   	</tr>	   	
		   	
		   	<c:set var="sufijo" value="_POSTULADOS" />
			<c:set var="pageList" value="PAGE_LIST${sufijo}" />
		   	
	<c:forEach var="postulados" items="${sessionScope[pageList]}" varStatus="rowCounter">
				<tr class="odd">
					<td data-title="Marcar opción" class="checar">
		           		<input type="checkbox" name="chkPostulado" ${postulados.estatus == idEstatusProceso?'disabled':''} id="c${index + 1}" value="${postulados.idOferta}" )" />
		           	</td>
					<td data-title="Nombre"><a class="postulado" href="<%=context%>viewCandidateInfo.do?method=init&idc=${postulados.idCandidato}&idoc=${postulados.idOferta}">
							<strong>${postulados.nombre} ${postulados.apellido1} ${postulados.apellido2}</strong>
						</a>
					</td>
		        	<td data-title="Ocupación deseada">${postulados.subAreaLaboralDescripcion}</td>					
					<td data-title="Ocupación, nivel de estudios">${postulados.carrera}</td>
		        	<td data-title="Edad" style="text-align: center">${postulados.edad}</td>
		        	<td data-title="Salario pretendido"><c:if test="${not empty postulados.salario}">
						<fmt:setLocale value="en_US"/>
						<fmt:formatNumber type="CURRENCY" value="${postulados.salario}" />
			        </c:if></td>
		        	<td data-title="Ubicación">${postulados.municipioEntidad}</td>	
		        	<td data-title="Estatus">${postulados.descEstatus}&nbsp;</td>
		        	<td data-title="Compatibilidad con la oferta" style="text-align: center">${postulados.compatibilidad}%</td>		        	
				</tr>
				<tr>
					<td style="padding:0" colspan="9"><a href="javascript:mostrarResumen('post${rowCounter.count}')" class="expand"><!--<div id="hidepost${rowCounter.count}" style="display:none;">Ocultar resumen</div><div id="showpost${rowCounter.count}" style="display:block;">Ver resumen</div> --></a>							   
						<div style="padding-bottom: 10px;padding-left: 10px;padding-top: 10px" id="post${rowCounter.count}">
							<strong>Expectativas laborales:</strong> 
							Puesto solicitado: ${postulados.subAreaLaboralDescripcion},
							<c:if test="${not empty postulados.salario}">
								<fmt:setLocale value="en_US"/>
								Salario pretendido: <fmt:formatNumber type="CURRENCY" value="${postulados.salario}" />
					        </c:if>, 
							<%-- Tipo de contrato: ${postulados.descTipoContrato}, 
							Tipo de empleo: ${postulados.horarioEmpleo},--%>
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
</div>

		<ul id="hideAllpost" style="display:none;"  align="left" class="entero">
			<li><a class="accion" href="javascript:eliminarCandidatos(document.getElementById('AdmonCandidatosForm'), 'tblPostulados', 'chkPostulado');">Eliminar</a>
			</li>
			<li><a class="accion" href="javascript:mostrarTodas(2, ${NUM_REGISTROS_POSTULADOS}, 'post')" class="expand">Ocultar resumen de todos los candidatos</a>	
			</li>
		</ul>
		<ul id="showAllpost" style="display:block;" align="left" class="entero">
			<li><a class="accion" href="javascript:eliminarCandidatos(document.getElementById('AdmonCandidatosForm'), 'tblPostulados', 'chkPostulado');">Eliminar</a>
			</li>
			<li><a class="accion" href="javascript:mostrarTodas(1, ${NUM_REGISTROS_POSTULADOS}, 'post')" class="expand">Ver resumen de todos los candidatos</a>
			</li>
		</ul>
		<div class="clearfix"></div>
	
		<jsp:include page="../layout/pager.jsp" flush="true">
         	<jsp:param name="SUFIJO" value="${sufijo}"/>
         	<jsp:param name="tipoRegistros" value="candidatos"/>         	
		</jsp:include>
		