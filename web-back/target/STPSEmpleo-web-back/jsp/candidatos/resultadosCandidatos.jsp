<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<%
String context = request.getContextPath() +"/";
%>

	   <table cellspacing="0" cellpadding="0" border="0" width="90%" id="dataTable"  class="offer" >
	   <tbody>
			<tr class="temas">
		    	<TH>CURP</TH>
		    	<TH>Correo Electrónico</TH>
		    	<TH>Nombre</TH>
		    	<TH>Fecha de nacimiento</TH>
		    	<TH>Fecha de alta</TH>	
		    	<TH>ID candidato</TH>	
			</tr>	
			<c:if test="${not empty PAGE_LIST}">

			<c:set var="sufijo" value="${null}" />
			<c:set var="pageList" value="PAGE_LIST${sufijo}" />
	
			<c:forEach var="lstCandidatos" items="${sessionScope[pageList]}" varStatus="rowCounter">
			<tr <c:out value="${rowCounter.count % 2 == 0?'':'class=odd'}"/>>
			    <td><a class="btn_portal" href="${pageContext.request.contextPath}/filtraCandidato.do?method=verCandidato&idCandidato=${lstCandidatos.idCandidato}">${lstCandidatos.curp}</a></td>
				<td>${lstCandidatos.correoElectronico} </td>
				<td>${lstCandidatos.nombre} ${lstCandidatos.apellido1} ${lstCandidatos.apellido2} </td>
		        <td>
				<c:if test="${not empty lstCandidatos.fechaNacimiento}">
					<fmt:formatDate value="${lstCandidatos.fechaNacimiento}" pattern="dd/MM/yyyy" />
		        </c:if>		
		        </td>
		        <td>
				<c:if test="${not empty lstCandidatos.fechaAlta}">
					<fmt:formatDate value="${lstCandidatos.fechaAlta.time}" pattern="dd/MM/yyyy" />
		        </c:if>		
		        </td>	
		        <td>
				<c:if test="${not empty lstCandidatos.idCandidato}">
					<td>${lstCandidatos.idCandidato} </td>
		        </c:if>		
		        </td>		        
		    </tr>
		    </c:forEach>	
		    </c:if>
		    <c:if test="${empty PAGE_LIST}">
		    	<tr><td colspan="6">No se encontraron registros con los criterios de búsqueda proporcionados</td></tr> 
		    </c:if>		    
	
	  </tbody>	  
	  </table>
	  
	  <jsp:include page="../layout/pager.jsp" flush="true">
		    <jsp:param name="SUFIJO" value="${sufijo}"/>
		    <jsp:param name="tipoRegistros" value="${null}"/>		 
	  </jsp:include>	  
