
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page import="mx.gob.stps.portal.core.candidate.vo.CandidatoVo"%>
<%@ page import="java.util.List"%>

<%
  String context = request.getContextPath() +"/";
  String diferenciador = estableceDiferenciador(request);
  pageContext.setAttribute("diferenciador", diferenciador);
  
  List<CandidatoVo> registros = (List<CandidatoVo>)session.getAttribute("PAGE_LIST"+ diferenciador);
%>

	<c:set var="sufijo" value="${diferenciador}" />
	<c:set var="pageList" value="PAGE_LIST${sufijo}" />
	<c:set var="totalOfertas" value="${fn:length(sessionScope[pageList])}"/>
	
	<%String searchMessage = (null != session.getAttribute("searchMessage") ? (String)session.getAttribute("searchMessage") : "");
		out.println(searchMessage);%>

	<h3 class="mis_ofertas">Mis candidatos</h3>
	<%if (registros==null || registros.isEmpty()){%>
		<li class="ejemplo">No se encontraron candidatos para la búsqueda: <c:out value="${searchQ}"/></li>
		
	<%}else{%>

		<div id="hideAll" style="display:none;"  align="left"><a href="javascript:mostrarTodas(2, <%=(Integer)session.getAttribute("NUM_REGISTROS")%>)" class="expand">[Ocultar resumen de todos los candidatos]</a></div>
		<div id="showAll" style="display:block;" align="left"><a href="javascript:mostrarTodas(1, <%=(Integer)session.getAttribute("NUM_REGISTROS")%>)" class="expand">[Ver resumen de todos los candidatos]</a></div>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="offer">
			<tr class="temas" >
		   		<th style="width: 40%">Nombre(s), primer apellido y segundo apellido</th>
		       	<th>Grado de estudios - Carrera o especialidad</th>
		       	<th>Edad</th>
		       	<th>Entidad federativa - Municipio/Delegaci&oacuten</th>
		   	</tr>
			
			<c:set var="odd" value="${true}"/>
			<c:forEach var="candidatos" items="${sessionScope[pageList]}" varStatus="rowCounter">
			
				<tr class="${odd ? 'odd' : 'null'}">
					<td>
						<div class="profesion">
							<strong>
							<a href="<%=context%>buscarcandidatos.do?method=detail&idc=${candidatos.idCandidato}">${candidatos.nombre} ${candidatos.apellido1} ${candidatos.apellido2}</a>
							</strong>
						</div>
					</td>
					<td>${candidatos.carrera}</td>
		        	<td>${candidatos.edad}</td>
		        	<td>${candidatos.municipioEntidad}</td>	
				</tr>
			   
				<tr class="${odd ? 'odd' : 'null'}">
					<td colspan="7">
						<a href="javascript:mostrarResumen('${rowCounter.count}')" class="expand">
							<div id="hide${rowCounter.count}" style="display:none;">Ocultar resumen</div>
							<div id="show${rowCounter.count}" style="display:block;">Ver resumen</div>
						</a>							   
						<div  style="display:none;" id="${rowCounter.count}">
							<strong>Expectativas laborales:</strong> 
							<c:if test="${not empty candidatos.subAreaLaboralDescripcion}">
								Subárea laboral deseada: ${candidatos.subAreaLaboralDescripcion},
							 </c:if>
							<c:if test="${not empty candidatos.salario}">
								<fmt:setLocale value="en_US"/>
								Salario pretendido: <fmt:formatNumber type="CURRENCY" value="${candidatos.salario}" />
					        </c:if><!--  ,				        				
							Tipo de contrato: ${candidatos.descTipoContrato}, 
							Tipo de empleo: ${candidatos.horarioEmpleo},
							Disponibilidad para viajar: ${candidatos.dispViajarFuera},
							Disponibilidad para radicar fuera: ${candidatos.dispRadicarFuera} -->
						</div>
					</td>
				</tr>

				<c:set var="odd" value="${not odd}"/>

			</c:forEach>
		</table>

	<%}%>

<jsp:include page="../../layout/pager.jsp" flush="true">
         <jsp:param name="SUFIJO" value="${sufijo}"/>
         <jsp:param name="tipoRegistros" value="candidatos"/>		 
</jsp:include>	

<!--  a class="regresar_a_resultado" href="/buscarcandidatos.do?method=init">Regresar</a -->	
<a class="regresar_a_resultado" href="#" onclick="window.history.go(-1); return false;">Regresar</a>

<%!
	private String estableceDiferenciador(HttpServletRequest request){
		/*String query = request.getParameter("searchQ");
		String diferenciador = "";
		
		if (query!=null && !query.trim().isEmpty()){
			request.setAttribute("searchQ", query.trim());
			
			int hashcode = query.hashCode();
			hashcode = Math.abs(hashcode);
			
			diferenciador = hashcode>0?String.valueOf(hashcode):"";
		}

		return diferenciador;*/
		return "";
	}

 %>
