<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Calendar, java.text.SimpleDateFormat"%>
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
<span class="entero">
	<strong>Fecha del reporte:</strong> <%=today%></br>
	<c:if test="${not empty USUARIO_APP}">
		<strong>Usuario que generó el reporte:</strong> ${activity.reporter}</br></br>
	</c:if>
</span>	    	
<table id="dataTable" class="offer" cellspacing="0" cellpadding="0" border="0" width="730px">
	<tbody>
		<tr class="temas">
			<TH width="30%">Publicador</TH>
		    <TH width="10%">Registros revisados</TH>
		    <TH width="10%">Registros autorizados</TH>
		    <TH width="10%">Registros rechazados</TH>
		    <TH width="10%">Empresas</TH>
		    <TH width="10%">Ofertas</TH>
		    <TH width="10%">Testimonios</TH>
		    <TH width="10%">Video CV</TH>			
		</tr>
		<c:forEach var="publisher" items="${PAGE_LIST}" varStatus="rowCounter">
			<tr <c:out value="${rowCounter.count % 2 != 0?'':'class=odd'}"/>>
				<td>${publisher.nombre}&nbsp;${publisher.apellido1}&nbsp;${publisher.apellido2}</td>
				<td>${publisher.total}</td>
				<td>${publisher.autorizados}</td>
				<td>${publisher.rechazados}</td>
				<td>${publisher.empresas}</td>
				<td>${publisher.ofertas}</td>
				<td>${publisher.testimonios}</td>
				<td>${publisher.videocv}</td>
			</tr>
		</c:forEach>
		<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
			<td>Totales</td>
			<td>${activity.totalReview}</td>
			<td>${activity.totalAuthorized}</td>
			<td>${activity.totalRejected}</td>
			<td>${activity.totalCompanies}</td>
			<td>${activity.totalOffers}</td>
			<td>${activity.totalTestimonies}</td>
			<td>${activity.totalVideocv}</td>
		</tr>
		<c:if test="${empty PAGE_LIST}">
		    <tr><td colspan="7">No se encontraron registros con los criterios de búsqueda proporcionados</td></tr> 
		</c:if>
	</tbody>	  
</table>
<c:if test="${not empty PAGE_LIST}">
	<span class="entero">
		<input type="button" id="btnExcel" value="Hoja de cálculo"  class="boton" onclick="doSubmitAction();" />
	</span>
</c:if>