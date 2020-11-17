<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Calendar, java.text.SimpleDateFormat"%>
<% 
	Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMM yyyy HH:mm");
    String today = sdf.format(cal.getTime());
%>
<%
	//el sufijo solo se usa si se utilizan multiples tablas.
	// de no necesitarse se puede igualar a "" o simplemente eliminar toda referencia a la variable.
	String sufijo = "";
	//numero total de paginas del pager
	int paginaActual = 1;
	if(session.getAttribute("NUM_PAGE_LIST" + sufijo)!= null){
		paginaActual =((Integer) session.getAttribute("NUM_PAGE_LIST" + sufijo)).intValue();
	}
	//numero total de paginas del pager
	int totalPaginas = 0;
	if(session.getAttribute("TOTAL_PAGES" + sufijo)!= null){
		totalPaginas =((Integer) session.getAttribute("TOTAL_PAGES" + sufijo)).intValue();
	}
	//numero maximo de paginas mostradas en el paginador 
	int numeroPaginasMostrar = 0;
	if(session.getAttribute("PAGE_JUMP_SIZE" + sufijo)!= null){
		numeroPaginasMostrar =((Integer) session.getAttribute("PAGE_JUMP_SIZE" + sufijo)).intValue();
	}
	//multiplicador q indica cual es el rango de paginas mostrado actualmente
	//(ejemplo, si vale 3 y numeroPagina = 6 se mostrara desde la 19 a la 24)
	int saltoPagina = 0;
	if(session.getAttribute("NUM_PAGE_JUMP" + sufijo)!= null){
		saltoPagina =((Integer) session.getAttribute("NUM_PAGE_JUMP" + sufijo)).intValue();
	}
	int registrosVisibles = 0;
	if(session.getAttribute("NUM_RECORDS_VISIBLE" + sufijo)!= null){
		registrosVisibles =((Integer) session.getAttribute("NUM_RECORDS_VISIBLE" + sufijo)).intValue();
	}
	int registrosTotal = 0;
	if(session.getAttribute("NUM_RECORDS_TOTAL" + sufijo)!= null){
		registrosTotal =((Integer) session.getAttribute("NUM_RECORDS_TOTAL" + sufijo)).intValue();
	}
	//muestra los numeros de pagina correctanmente
	int sumarPaginas = numeroPaginasMostrar * saltoPagina;
%>
<p>
	<strong>Fecha del reporte:</strong> <%=today%></br>
	<strong>Total de registros encontrados:</strong> <%=registrosTotal%> </br>	
</p>
<table id="dataTable" class="offer margin_top_40" cellspacing="0" cellpadding="0" border="0" width="940px">
	<tbody>
		<tr class="temas">
			<th>Folio</th>
			<th style="width: 120px;">Título</th>
			<th style="width: 120px;">Área de negocio</th>
			<th style="width: 120px;">Nivel de estudios</th>
			<th style="width: 120px;">Carrera</th>
			<th style="width:150px">Ubicación</th>
			<!-- th>No. de Plazas</th>
			<th>Género</th>
			<th>Experiencia</th> -->
			<th>Estatus</th>
			<!-- th>Persona de contacto</th>
			<th>Teléfono de contacto</th>
			<th>Correo electrónico de contacto</th> -->
			<th style="width:200px">Empresa que ofrece la vacante</th>
			<!-- th>Salario</th>  -->
		</tr>	
		<c:forEach var="ofertaVo" items="${PAGE_LIST}" varStatus="rowCounter">
			<tr <c:out value="${rowCounter.count % 2 != 0?'':'class=odd'}"/>>
				<td>${ofertaVo.idOfertaEmpleo}</td>
				<td>${ofertaVo.tituloOferta}</td>
				<td>${ofertaVo.subAreaLaboralDescripcion}</td>
				<td>${ofertaVo.gradoEstudios}</td>
				<td>${ofertaVo.carreraEspecialidad}</td>
				<td>${ofertaVo.municipio},${ofertaVo.entidad}</td>
				<!--td>${ofertaVo.numeroPlazas}</td>
				<td>${ofertaVo.genero}</td>
				<td>${ofertaVo.descripcionExperienciaAnios}</td-->
				<td>${ofertaVo.estatus}</td>
				<!-- td>${ofertaVo.nombreContacto}</td>
				<td>
					<c:if test="${!empty ofertaVo.telefonoPrincipal}">
						${ofertaVo.telefonoPrincipal} 
					</c:if>				
				</td>
				<td>${ofertaVo.correoElectronicoContacto}</td> -->
				<td>${ofertaVo.empresaNombre}</td>
				<!-- td>	
					<c:if test="${not empty ofertaVo.salario}">
						<fmt:setLocale value="en_US"/>
						<fmt:formatNumber type="CURRENCY" value="${ofertaVo.salario}"/><br>
					</c:if>	
				</td> -->			
						
			</tr>		
		</c:forEach>
		<c:if test="${empty PAGE_LIST}">
		    <tr><td colspan="8"><div class="gris" style="text-align: center; font-weight: bold">No se encontraron registros con los criterios de búsqueda proporcionados</div></td></tr> 
		</c:if>	
	</tbody>	  
</table>
<c:if test="${not empty PAGE_LIST}">
	<p style="text-align: center">
		<input type="button" id="btnExcel" value="Hoja de cálculo"  class="boton" onclick="doSubmitAction();" />
	</p>
</c:if>	

